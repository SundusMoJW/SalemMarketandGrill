package com.twins.osama.salemsmarketandgrill.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.CustomToast;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.FULL_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.GUID_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ID_SHARED_PREF;
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
    private ImageView progress_image;
    private Animation animPrograss;
    //    private LoginButton loginButton;
//    private CallbackManager callbackManager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.setLangSettings(this);
        setContentView(R.layout.activity_login);
        view = findViewById(R.id.login_layout);
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.login_layout));

//        progress_image = (ImageView)findViewById(R.id.progress_image);

        login = (Button) findViewById(R.id.login);
        show_hide_password = (CheckBox) findViewById(R.id.show_hide_password);
        ll_login = (LinearLayout) findViewById(R.id.ll_login);
        userLogin = (EditText) findViewById(R.id.email_login);
        passsword_login = (EditText) findViewById(R.id.passsword_login);
        login.setOnClickListener(this);
        createAccount = (TextView) findViewById(R.id.createAccount);
        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.shake);
//        animPrograss = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.progress_anim);
//        animPrograss.setDuration(1000);
//        progress_image.startAnimation(animPrograss);
//        animPrograss.setInterpolator(new Interpolator() {
//            private final int frameCount = 8;
//
//            @Override
//            public float getInterpolation(float input) {
//                return (float) Math.floor(input * frameCount) / frameCount;
//            }
//        });

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
//        callbackManager = CallbackManager.Factory.create();
//        loginButton = (LoginButton) findViewById(R.id.fce_login);
//        loginButton.setReadPermissions("email");
//        // If using in a fragment
//
//        // Callback registration
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                getUserDetails(loginResult);
//
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//
//        });
    }

    //
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
//    }
    //    protected void getUserDetails(LoginResult loginResult) {
//        GraphRequest data_request = GraphRequest.newMeRequest(
//                loginResult.getAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(
//                            JSONObject json_object,
//                            GraphResponse response) {
//
//                        try {
//                            json_object = new JSONObject("userProfile");
//                            JSONObject  profile_pic_data = new JSONObject(json_object.get("picture").toString());
//                            JSONObject profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
//
////                            Picasso.with(this).load(profile_pic_url.getString("url"))
////                                    .into(user_picture);
//                            int id = json_object.optInt("Id");
//                            String FullName = json_object.optString("name");
//                            String UserName = json_object.optString("name");
//                            String Email = json_object.optString("email");
//
//                            SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(Login.this);
//                            sharedPrefUtil.saveBoolean(STATUS_SHARED_PREF,true);
//                            sharedPrefUtil.saveString(FULL_NAME_SHARED_PREF, FullName);
//                            sharedPrefUtil.saveInt(ID_SHARED_PREF, id);
//                            sharedPrefUtil.saveString(GUID_SHARED_PREF, null);
//                            sharedPrefUtil.saveString(USER_NAME_SHARED_PREF, UserName);
//                            sharedPrefUtil.saveString(EMAIL_SHARED_PREF, Email);
//                             sharedPrefUtil.saveString(PASSWORD_SHARED_PREF, null);
//                            Intent intent = new Intent(Login.this,
//                                    MainActivity.class);
//                            intent.putExtra("userProfile", json_object.toString());
//                            startActivity(intent);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//                });
//        Bundle permission_param = new Bundle();
//        permission_param.putString("fields", "id,name,email,picture.width(120).height(120)");
//        data_request.setParameters(permission_param);
//        data_request.executeAsync();
//
//    }

    private boolean checkValidation() {
        boolean valid = true;
        // Get email id and password
        String userName = userLogin.getText().toString().trim();
        String getPassword = passsword_login.getText().toString().trim();

        // Check for both field is empty or not
        if (userName.equals("") || userName.length() == 0) {
            userLogin.setError("Enter your Name");
            ll_login.startAnimation(shakeAnimation);
            valid= false;
        } else {
            userLogin.setError(null);
        }

        if (getPassword.equals("") || getPassword.length() == 0) {
            ll_login.startAnimation(shakeAnimation);
            passsword_login.setError("Enter your Password");
            valid= false;
        } else {
            passsword_login.setError(null);
        }
        return valid;
    }

    private void login() {
        //Getting values from edit texts
        final String stringEmail = userLogin.getText().toString().trim();
        final String stringPassword = passsword_login.getText().toString().trim();
//        final ProgressDialog pd = new ProgressDialog(Login.this);
//        pd.setTitle("Loading...");
//        pd.setMessage("Please wait.");
//        pd.setCancelable(false);
//        pd.show();
//        progress_image.setVisibility(View.VISIBLE);
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
//                        if (pd != null && pd.isShowing())
//                            pd.dismiss();
//                        progress_image.setVisibility(View.GONE);
                        progressDialog.dismiss();

                        JSONObject data = jsonObject.optJSONObject("OtherData");
                        int id = data.optInt("Id");
                        String FullName = data.optString("FullName");
                        String GUID = data.optString("GUID");
                        String UserName = data.optString("UserName");
                        String Email = data.optString("Email");
                        String Pass = data.optString("Password");

                        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(Login.this);

                        sharedPrefUtil.saveString(FULL_NAME_SHARED_PREF, FullName);
                        sharedPrefUtil.saveBoolean(STATUS_SHARED_PREF, Status);
                        sharedPrefUtil.saveInt(ID_SHARED_PREF, id);
                        sharedPrefUtil.saveString(GUID_SHARED_PREF, GUID);
                        sharedPrefUtil.saveString(USER_NAME_SHARED_PREF, UserName);
                        sharedPrefUtil.saveString(EMAIL_SHARED_PREF, Email);
                        sharedPrefUtil.saveString(PASSWORD_SHARED_PREF, Pass);


                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {

//                        if (pd != null && pd.isShowing())
//                            pd.dismiss();
                        //If the server response is not success
                        //Displaying an error message on toast
//                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//                        builder.setTitle("Something went wrong");
////                        builder.setMessage(jsonObject.optString("ResultText"));
//                        builder.setMessage("Your User Name Id is Invalid");
//                        builder.setCancelable(true);
//                        final AlertDialog closedialog = builder.create();
//                        closedialog.show();
//                        final Timer timer2 = new Timer();
//                        timer2.schedule(new TimerTask() {
//                            public void run() {
//                                closedialog.dismiss();
//                                timer2.cancel(); //this will cancel the timer of the system
//                            }
//                        }, 3000);

                        progressDialog.dismiss();

                        new CustomToast().Show_Toast(getApplicationContext(), view,
                                "Your User Name is Invalid.");
                        progress_image.setVisibility(View.GONE);
                        ll_login.startAnimation(shakeAnimation);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
//                if (pd != null && pd.isShowing())
//                    pd.dismiss();
//                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
//                builder.setTitle("Something went wrong");
//                builder.setMessage(" Error Response");
//                builder.setCancelable(true);
//                final AlertDialog closedialog = builder.create();
//                closedialog.show();
//                final Timer timer2 = new Timer();
//                timer2.schedule(new TimerTask() {
//                    public void run() {
//                        closedialog.dismiss();
//                        timer2.cancel();
//                    }
//                }, 3000);
//                progress_image.setVisibility(View.GONE);
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
// else {
//            progressDialog.dismiss();
//
//        }
//            progress_image.setVisibility(View.GONE);

    }

    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
