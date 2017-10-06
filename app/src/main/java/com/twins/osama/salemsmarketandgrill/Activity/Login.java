package com.twins.osama.salemsmarketandgrill.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private EditText email_login;
    private EditText passsword_login;
    private TextView createAccount;
    private CheckBox show_hide_password;
    private LinearLayout ll_login;
    private Animation shakeAnimation;
//    private LoginButton loginButton;
//    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.setLangSettings(this);
        setContentView(R.layout.activity_login);
        view = findViewById(R.id.login_layout);

        login = (Button) findViewById(R.id.login);
        show_hide_password = (CheckBox) findViewById(R.id.show_hide_password);
        ll_login = (LinearLayout) findViewById(R.id.ll_login);

        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.shake);

        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.login_layout));
        email_login = (EditText) findViewById(R.id.email_login);
        passsword_login = (EditText) findViewById(R.id.passsword_login);
        login.setOnClickListener(this);
        createAccount = (TextView) findViewById(R.id.createAccount);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                overridePendingTransition(R.anim.left_enter,R.anim.right_out);
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
private void checkValidation() {
    // Get email id and password
    String getEmailId = email_login.getText().toString();
    String getPassword = passsword_login.getText().toString();

    // Check patter for email id
    Pattern p = Pattern.compile(Const.regEx);

    Matcher m = p.matcher(getEmailId);

    // Check for both field is empty or not
    if (getEmailId.equals("") || getEmailId.length() == 0
            || getPassword.equals("") || getPassword.length() == 0) {
        ll_login.startAnimation(shakeAnimation);
        new CustomToast().Show_Toast(getApplicationContext(), view,
                "Enter both credentials.");

    }
    // Check if email id is valid or not
    else if (!m.find())
        new CustomToast().Show_Toast(getApplicationContext(), view,
                "Your User Name Id is Invalid.");
        // Else do login and do your stuff
    else
        Toast.makeText(getApplicationContext(), "Do Login.", Toast.LENGTH_SHORT)
                .show();

}
    private void login() {
        //Getting values from edit texts
        final String stringEmail = email_login.getText().toString().trim();
        final String stringPassword = passsword_login.getText().toString().trim();
        checkValidation();

        final ProgressDialog pd = new ProgressDialog(Login.this);
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();

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
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
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
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        //If the server response is not success
                        //Displaying an error message on toast
                        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                        builder.setTitle("Something went wrong");
                        builder.setMessage(jsonObject.optString("ResultText"));
                        builder.setCancelable(true);
                        final AlertDialog closedialog = builder.create();
                        closedialog.show();
                        final Timer timer2 = new Timer();
                        timer2.schedule(new TimerTask() {
                            public void run() {
                                closedialog.dismiss();
                                timer2.cancel(); //this will cancel the timer of the system
                            }
                        }, 3000);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                builder.setTitle("Something went wrong");
                builder.setMessage(" Error Response");
                builder.setCancelable(true);
                final AlertDialog closedialog = builder.create();
                closedialog.show();
                final Timer timer2 = new Timer();
                timer2.schedule(new TimerTask() {
                    public void run() {
                        closedialog.dismiss();
                        timer2.cancel(); //this will cancel the timer of the system
                    }
                }, 3000);
            }
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("username", stringEmail);
                params.put("Password", stringPassword);
                //Creating a string request

                return params;
            }
        };
        requestQueue.add(request);
    }


    @Override
    public void onClick(View v) {

        login();

    }

    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
//        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
//        AppEventsLogger.deactivateApp(this);
    }
}
