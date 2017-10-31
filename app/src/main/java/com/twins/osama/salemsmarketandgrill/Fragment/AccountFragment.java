package com.twins.osama.salemsmarketandgrill.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.twins.osama.salemsmarketandgrill.Adapter.MyAdapter;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.OnDrawerItemClickListener;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.EMAILProfile;
import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.items;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.FULL_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.GUID_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ID_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.KEY;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.PASSWORD;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.PASSWORD_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URL_CustomerEditPassword;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URL_CustomerEditProfile;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USERNAME;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USER_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.saveData;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.wrongAlertDialog;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private LinearLayout userLayout;
    private EditText newName;
    private Button saveNewName;
    private LinearLayout emailLayout;
    private EditText newEmail;
    private Button saveNewEmail;
    private LinearLayout passwordLayout;
    private EditText newPass;
    private Button saveNewPass;
    Setting fragment;
    FragmentTransaction mFragmentTransaction;
    FragmentManager mFragmentManager;
    private SharedPrefUtil sharedPrefUtil;
    private EditText old_pass;
    private ProgressBar progressBar;
    public MyAdapter mAdapter;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.fragment_account));
        getActivity().findViewById(R.id.menu).setVisibility(View.GONE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.GONE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.GONE);
        getActivity().findViewById(R.id.search).setVisibility(View.GONE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.VISIBLE);
        mFragmentManager = getActivity().getSupportFragmentManager();
        sharedPrefUtil = new SharedPrefUtil(getActivity());
//        progressBar = (ProgressBar) getActivity().findViewById(R.id.loading_spinner);
        getActivity().findViewById(R.id.go_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new Setting();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }
        });
        mAdapter = new MyAdapter(getActivity(), items/*, NAME, EMAILProfile, PROFILE*/, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {
            }

            @Override
            public void onPhotoClick(View view) {
            }

            @Override
            public void onButtonClick(int postition) {

            }
        });
        String value = this.getArguments().getString(KEY);//get your parameters

        userLayout = (LinearLayout) view.findViewById(R.id.user_layout);
        newName = (EditText) view.findViewById(R.id.new_name);
        saveNewName = (Button) view.findViewById(R.id.save_new_name);

        emailLayout = (LinearLayout) view.findViewById(R.id.email_layout);
        newEmail = (EditText) view.findViewById(R.id.new_email);
        saveNewEmail = (Button) view.findViewById(R.id.save_new_email);

        passwordLayout = (LinearLayout) view.findViewById(R.id.password_layout);
        newPass = (EditText) view.findViewById(R.id.new_pass);
        saveNewPass = (Button) view.findViewById(R.id.save_new_pass);
        old_pass = (EditText) view.findViewById(R.id.old_pass);

        saveNewName.setOnClickListener(this);
        saveNewEmail.setOnClickListener(this);
        saveNewPass.setOnClickListener(this);
        CheckUpBundell(value);
        return view;


    }

    public void CheckUpBundell(String str) {
        switch (str) {
            case USERNAME:
                userLayout.setVisibility(View.VISIBLE);
                emailLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.GONE);
                break;
            case PASSWORD:
                userLayout.setVisibility(View.GONE);
                emailLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.VISIBLE);
                break;
            case EMAIL:
                userLayout.setVisibility(View.GONE);
                emailLayout.setVisibility(View.VISIBLE);
                passwordLayout.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        if (v == saveNewName) {
            SaveUserName();

        } else if (v == saveNewEmail) {
            SaveEmail();

        } else if (v == saveNewPass) {
            SavePassword();

        }
    }

    public void SaveUserName() {
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final int idSaredPref = sharedPrefUtil.getInt(ID_SHARED_PREF);
        final String guidSaredPref = sharedPrefUtil.getString(GUID_SHARED_PREF);
//        progressBar = (ProgressBar) getActivity().findViewById(R.id.loading_spinner);
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
//        enableProgressBar(progressBar);
//        progressBar.setVisibility(ProgressBar.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditProfile, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
//                    ProgressBar pb = (ProgressBar) findViewById(R.id.pbLoading);
// run a background job and once complete
//                    progressBar.setVisibility(ProgressBar.GONE);
                    //     boolean Status = jsonObject.optBoolean("Status");

                    if (OtherData != null) {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        String UserName = OtherData.optString("UserName");
                        sharedPrefUtil.saveString(USER_NAME_SHARED_PREF, UserName);
//                        disableProgressBar(progressBar);

                        saveData(getActivity(), R.string.save_new_user_name);
                        newName.setText("");
//                        mFragmentManager = getActivity().getSupportFragmentManager();
//                        fragment = new Setting();
//                        mFragmentTransaction = mFragmentManager.beginTransaction();
//                        mFragmentTransaction.commit();
//                        NAME = UserName;
                        mAdapter.setName(UserName);
                    } else {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
//                        progressBar.setVisibility(ProgressBar.GONE);
//                        disableProgressBar(progressBar);
                        wrongAlertDialog(getActivity());
                    }
                    newName.setText("");
                    //If the server response is not success
                    //Displaying an error message on toast
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                wrongAlertDialog(getActivity());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("UserName", newName.getText().toString().trim());
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id", String.valueOf(idSaredPref));
                param.put("GUID", guidSaredPref);
                return param;
            }
        };

        requestQueue.add(request);

    }

    public void SaveEmail() {
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final int idSaredPref = sharedPrefUtil.getInt(ID_SHARED_PREF);
        final String guidSaredPref = sharedPrefUtil.getString(GUID_SHARED_PREF);
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        String Email = OtherData.optString("Email");
                        sharedPrefUtil.saveString(EMAIL_SHARED_PREF, Email);

                        saveData(getActivity(), R.string.save_email);

                        EMAILProfile = Email;
                        mAdapter.setEmail(EMAILProfile);
                        newEmail.setText("");
                    } else {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        wrongAlertDialog(getActivity());
                    }
                    newEmail.setText("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                wrongAlertDialog(getActivity());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();

                param.put("UserName", sharedPrefUtil.getString(USER_NAME_SHARED_PREF));
                param.put("Email", newEmail.getText().toString().trim());
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id", String.valueOf(idSaredPref));
                param.put("GUID", guidSaredPref);
                return param;
            }
        };
        requestQueue.add(request);
    }

    public void SavePassword() {
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final int idSaredPref = sharedPrefUtil.getInt(ID_SHARED_PREF);
        final String guidSaredPref = sharedPrefUtil.getString(GUID_SHARED_PREF);
        final String OldPassword = sharedPrefUtil.getString(PASSWORD_SHARED_PREF);
        final String oldPass = old_pass.getText().toString().trim();
        final ProgressDialog pd = new ProgressDialog(getContext());
        pd.setTitle("Loading...");
        pd.setMessage("Please wait.");
        pd.setCancelable(false);
        pd.show();
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditPassword, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");

//                    OtherData.getInt("Id");
//                    sharedPrefUtil.saveString(FULL_NAME_SHARED_PREF, FullName);
//                    sharedPrefUtil.saveString(USER_NAME_SHARED_PREF, UserName);

                    //           boolean Status = jsonObject.optBoolean("Status");
                    if (OtherData != null && oldPass.equals(OldPassword)) {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        String newPasss = OtherData.optString("UserName");
                        sharedPrefUtil.saveString(PASSWORD_SHARED_PREF, newPasss);

                        newPass.setText("");
                        old_pass.setText("");

                    } else {
                        if (pd != null && pd.isShowing())
                            pd.dismiss();
                        wrongAlertDialog(getActivity());
                        newPass.setText("");
                        old_pass.setText("");

                    }
                    //If the server response is not success
                    //Displaying an error message on toast
                } catch (JSONException e) {
                    wrongAlertDialog(getActivity());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (pd != null && pd.isShowing())
                    pd.dismiss();
                Toast.makeText(getActivity(), " Error Response", Toast.LENGTH_SHORT).show();
                wrongAlertDialog(getActivity());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("UserName", sharedPrefUtil.getString(USER_NAME_SHARED_PREF));
                param.put("Password", newPass.getText().toString().trim());
                param.put("OldPassword", OldPassword);
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id", String.valueOf(idSaredPref));
                param.put("GUID", guidSaredPref);
                return param;
            }
        };

        requestQueue.add(request);

    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.notifyDataSetChanged();
    }
}