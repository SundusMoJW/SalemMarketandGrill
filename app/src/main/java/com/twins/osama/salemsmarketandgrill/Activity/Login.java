package com.twins.osama.salemsmarketandgrill.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.twins.osama.salemsmarketandgrill.Helpar.CustomToast;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ADDRESS_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.FULL_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.GUID_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ID_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.MOBILE_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.PASSWORD_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.STATUS_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URL_LOGIN;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USER_NAME_SHARED_PREF;

//import com.facebook.appevents.AppEventsLogger;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static View view;

    private Button login;
    private EditText userLogin;
    private EditText passsword_login;
    private TextView createAccount;
    private CheckBox show_hide_password;
    private LinearLayout ll_login;
    private Animation shakeAnimation;
    //    private ImageView progress_image;
    private Animation animPrograss;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Const.setLangSettings(this);
        setContentView(R.layout.activity_login);
        view = findViewById(R.id.login_layout);
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.login_layout));
//        progress_image = (ImageView)findViewById(R.id.progress_image);
        login = (Button) findViewById(R.id.login);
        show_hide_password = (CheckBox) findViewById(R.id.show_hide_password);
        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        userLogin = (EditText) findViewById(R.id.user_login);
        passsword_login = (EditText) findViewById(R.id.passsword_login);
        login.setOnClickListener(this);
        createAccount = (TextView) findViewById(R.id.createAccount);
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.shake);

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_enter, R.anim.right_out);
                finish();
            }
        });

        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton button,
                                         boolean isChecked) {
                if (isChecked) {
                    show_hide_password.setText(R.string.hide_pwd);
                    passsword_login.setInputType(InputType.TYPE_CLASS_TEXT);
                    passsword_login.setTransformationMethod(HideReturnsTransformationMethod
                            .getInstance());// show password
                } else {
                    show_hide_password.setText(R.string.show_pwd);
                    passsword_login.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passsword_login.setTransformationMethod(PasswordTransformationMethod
                            .getInstance());// hide password

                }

            }
        });
    }

    private boolean checkValidation() {
        String userName = userLogin.getText().toString().trim();
        String getPassword = passsword_login.getText().toString().trim();
        if (userName.equals("") || userName.length() == 0) {
            userLogin.setError("Enter your Name");
            userLogin.startAnimation(shakeAnimation);
            return false;
        } else {
            userLogin.setError(null);
        }

        if (getPassword.equals("") || getPassword.length() == 0) {
            passsword_login.startAnimation(shakeAnimation);
            passsword_login.setError("Enter your Password");
            return false;
        } else {
            passsword_login.setError(null);
        }
        return true;
    }

    private void login() {
        //Getting values from edit texts
        final String stringEmail = userLogin.getText().toString().trim();
        final String stringPassword = passsword_login.getText().toString().trim();
        progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, URL_LOGIN
                , new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
                        progressDialog.dismiss();
                        JSONObject data = jsonObject.optJSONObject("OtherData");
                        int id = data.optInt("Id");
                        String FullName = data.optString("FullName");
                        String GUID = data.optString("GUID");
                        String UserName = data.optString("UserName");
                        String Email = data.optString("Email");
                        String Pass = data.optString("Password");
                        String Address = data.optString("Address");
                        String Mobile = data.optString("Mobile");

                        Log.i("data", data.toString());

                        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(Login.this);
                        sharedPrefUtil.saveString(FULL_NAME_SHARED_PREF, FullName);
                        sharedPrefUtil.saveBoolean(STATUS_SHARED_PREF, Status);
                        sharedPrefUtil.saveInt(ID_SHARED_PREF, id);
                        sharedPrefUtil.saveString(GUID_SHARED_PREF, GUID);
                        sharedPrefUtil.saveString(USER_NAME_SHARED_PREF, UserName);
                        sharedPrefUtil.saveString(MOBILE_SHARED_PREF, Mobile);
                        sharedPrefUtil.saveString(ADDRESS_NAME_SHARED_PREF, Address);
                        sharedPrefUtil.saveString(EMAIL_SHARED_PREF, Email);
                        Log.i("info", "id  :  " + id + "    GUID    :    " + GUID);
                        sharedPrefUtil.saveString(PASSWORD_SHARED_PREF, Pass);
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        progressDialog.dismiss();
                        new CustomToast().Show_Toast(getApplicationContext(), view,
                                "Your User Name is Invalid.");
                        ll_login.startAnimation(shakeAnimation);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                new CustomToast().Show_Toast(getApplicationContext(), view,
                        "No Internet connection");
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", stringEmail);
                params.put("Password", stringPassword);
                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public void onClick(View v) {
        if (checkValidation()) {
            login();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
