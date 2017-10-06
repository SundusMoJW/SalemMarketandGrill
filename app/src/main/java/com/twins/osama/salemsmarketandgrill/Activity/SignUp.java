package com.twins.osama.salemsmarketandgrill.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URL_SIGNUP;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private Button signup;
    private TextView go_to_login;
    private EditText emailSignup;
    private EditText passswordSignup;
    private EditText confirmPassswordSignup;
    private EditText fullNameSignup;
    private EditText ueserNameSignup;
    private EditText adressSignup;
    private EditText mobileSignup;
    SharedPrefUtil sharedPrefUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.setLangSettings(this);
        setContentView(R.layout.activity_sign_up);

        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.signup_layout));
        findViews();
        go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_enter,R.anim.left_out);
                finish();
            }
        });
//        findViewById(R.id.sign).setOnClickListener(new View.OnClickListener() {
//            @Override
//
//            public void onClick(View v) {
//                String username = et_name.getText().toString();
//             /*   if (username.isEmpty()){
//                    et_name.setError("الحقل مطلوب");
//                }*/

    }

    public void SignUp() {
        //Adding the string request to the queue
        final ProgressDialog pd = new ProgressDialog(SignUp.this);
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, URL_SIGNUP, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
                    if (OtherData == null) {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
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
                    } else {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        Intent intent = new Intent(SignUp.this, Login.class);
                        startActivity(intent);
                        finish();
                    }

                    //If the server response is not success
                    //Displaying an error message on toast
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
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
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
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("FullName", fullNameSignup.getText().toString().trim());
                param.put("UserName", ueserNameSignup.getText().toString().trim());
                param.put("Password", passswordSignup.getText().toString().trim());
                param.put("Mobile", mobileSignup.getText().toString().trim());
                param.put("Email", emailSignup.getText().toString().trim());
                param.put("Address", adressSignup.getText().toString().trim());
                return param;
            }
        };

        requestQueue.add(request);

    }

    @Override
    public void onClick(View v) {
        String username = fullNameSignup.getText().toString().trim();
        String passs1 = passswordSignup.getText().toString().trim();
        String passs2 = confirmPassswordSignup.getText().toString().trim();
        String emailcon = emailSignup.getText().toString().trim();
        if (username.equals("")&& isEmailValid(emailcon) && isPasswordValid(passs1, passs2)) {

            Toast.makeText(SignUp.this, "Enter basic account data", Toast.LENGTH_SHORT).show();
        } else
            SignUp();

    }


    private void findViews() {
        signup = (Button) findViewById(R.id.signup);
        go_to_login = (TextView) findViewById(R.id.go_to_login);
        emailSignup = (EditText) findViewById(R.id.email_signup);
        passswordSignup = (EditText) findViewById(R.id.passsword_signup);
        confirmPassswordSignup = (EditText) findViewById(R.id.confirm_passsword_signup);
        fullNameSignup = (EditText) findViewById(R.id.full_name_signup);
        ueserNameSignup = (EditText) findViewById(R.id.ueser_name_signup);
        adressSignup = (EditText) findViewById(R.id.adress_signup);
        mobileSignup = (EditText) findViewById(R.id.mobile_signup);
        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(this);
    }

    private boolean isEmailValid(String email) {

        return email.contains("@");
    }

    private boolean isPasswordValid(String password, String confPassword) {
        //TODO: Replace this with your own logic
        if (password.equals(confPassword) && password.length() > 4) {
            return true;
        } else {
            return false;
        }

    }


}
