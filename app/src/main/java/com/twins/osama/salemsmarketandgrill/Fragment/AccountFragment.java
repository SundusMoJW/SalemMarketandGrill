package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.twins.osama.salemsmarketandgrill.Activity.MainActivity;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ADDRESS;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ADDRESS_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.FULL_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.GUID_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ID_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.KEY;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.MOBILE;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.PASSWORD;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.PASSWORD_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URL_CustomerEditPassword;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.URL_CustomerEditProfile;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USERNAME;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USER_NAME_SHARED_PREF;

public class AccountFragment extends Fragment implements View.OnClickListener {
    private LinearLayout userLayout;
    private EditText newName;
    private CircularProgressButton saveNewName;
    private LinearLayout emailLayout;
    private EditText newEmail;
    private CircularProgressButton saveNewEmail;
    private LinearLayout passwordLayout;
    private EditText newPass;
    private EditText confPass;
    private CircularProgressButton saveNewPass;
    private Runnable run;
    private Handler handler;
    private Setting fragment;
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;
    private SharedPrefUtil sharedPrefUtil;
    private EditText old_pass;
    private String oldPass;
    private LinearLayout adress_layout;
    private LinearLayout mobile_layout;
    private EditText new_adress;
    private CircularProgressButton save_new_adress;
    private EditText new_mobile;
    private CircularProgressButton save_new_mobile;

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.mAdapter.notifyDataSetChanged();
        /********************/// getActivity().etILitene(thi);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.fragment_account));
        getActivity().findViewById(R.id.menu).setVisibility(View.GONE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.GONE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.GONE);
        getActivity().findViewById(R.id.search).setVisibility(View.GONE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.VISIBLE);

        ((TextView) getActivity().findViewById(R.id.tv_title)).setText(getText(R.string.account));

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
        String value = this.getArguments().getString(KEY);//get your parameters

        userLayout = (LinearLayout) view.findViewById(R.id.user_layout);
        newName = (EditText) view.findViewById(R.id.new_name);
        saveNewName = (CircularProgressButton) view.findViewById(R.id.save_new_name);
        saveNewName.setIndeterminateProgressMode(true);
        saveNewName.setProgress(0);

        emailLayout = (LinearLayout) view.findViewById(R.id.email_layout);
        newEmail = (EditText) view.findViewById(R.id.new_email);
        saveNewEmail = (CircularProgressButton) view.findViewById(R.id.save_new_email);
        saveNewEmail.setIndeterminateProgressMode(true);
        saveNewEmail.setProgress(0);

        passwordLayout = (LinearLayout) view.findViewById(R.id.password_layout);
        newPass = (EditText) view.findViewById(R.id.new_pass);
        confPass = (EditText) view.findViewById(R.id.conf_pass);
        saveNewPass = (CircularProgressButton) view.findViewById(R.id.save_new_pass);
        saveNewPass.setIndeterminateProgressMode(true);
        saveNewPass.setProgress(0);
        old_pass = (EditText) view.findViewById(R.id.old_pass);

        adress_layout = (LinearLayout) view.findViewById(R.id.adress_layout);
        new_adress = (EditText) view.findViewById(R.id.new_adress);
        save_new_adress = (CircularProgressButton) view.findViewById(R.id.save_new_adress);
        save_new_adress.setIndeterminateProgressMode(true);
        save_new_adress.setProgress(0);

        mobile_layout = (LinearLayout) view.findViewById(R.id.mobile_layout);
        new_mobile = (EditText) view.findViewById(R.id.new_mobile);
        save_new_mobile = (CircularProgressButton) view.findViewById(R.id.save_new_mobile);
        save_new_mobile.setIndeterminateProgressMode(true);
        save_new_mobile.setProgress(0);

        saveNewName.setOnClickListener(this);
        saveNewEmail.setOnClickListener(this);
        saveNewPass.setOnClickListener(this);
        save_new_adress.setOnClickListener(this);
        save_new_mobile.setOnClickListener(this);
        CheckUpBundell(value);
        return view;
    }

    public void CheckUpBundell(String str) {
        switch (str) {
            case USERNAME:
                userLayout.setVisibility(View.VISIBLE);
                emailLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.GONE);
                adress_layout.setVisibility(View.GONE);
                mobile_layout.setVisibility(View.GONE);
                break;
            case PASSWORD:
                userLayout.setVisibility(View.GONE);
                emailLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.VISIBLE);
                adress_layout.setVisibility(View.GONE);
                mobile_layout.setVisibility(View.GONE);
                break;
            case EMAIL:
                userLayout.setVisibility(View.GONE);
                emailLayout.setVisibility(View.VISIBLE);
                passwordLayout.setVisibility(View.GONE);
                adress_layout.setVisibility(View.GONE);
                mobile_layout.setVisibility(View.GONE);
                break;
                /*********************/
            case MOBILE:
                userLayout.setVisibility(View.GONE);
                emailLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.GONE);
                adress_layout.setVisibility(View.GONE);
                mobile_layout.setVisibility(View.VISIBLE);
                break;
            case ADDRESS:
                userLayout.setVisibility(View.GONE);
                emailLayout.setVisibility(View.GONE);
                passwordLayout.setVisibility(View.GONE);
                adress_layout.setVisibility(View.VISIBLE);
                mobile_layout.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == saveNewName) {
            String name = newName.getText().toString();

            if(!(name.isEmpty())||name.length()!=0){
                SaveUserName();
            }else {
                saveNewName.setProgress(-1);
                saveNewName.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        saveNewName.setEnabled(true);
                        saveNewName.setProgress(0);
                    }
                };
                newName.setText("");
            }
        } else if (v == saveNewEmail) {
            String email = newEmail.getText().toString();
            if (!(email.isEmpty()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                SaveEmail();
            }else {
                saveNewEmail.setProgress(-1);
                saveNewEmail.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        saveNewEmail.setEnabled(true);
                        saveNewEmail.setProgress(0);
                    }
                };
                handler = new Handler();
                handler.postDelayed(run, 5000);
                newEmail.setText("");
            }
        } else if (v == saveNewPass) {
//            final String OldPassword = sharedPrefUtil.getString(PASSWORD_SHARED_PREF);
            oldPass = old_pass.getText().toString().trim();
            if (newPass.getText().equals(confPass.getText())){
                SavePassword();
            }
            else {
                saveNewPass.setProgress(-1);
                saveNewPass.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        saveNewPass.setEnabled(true);
                        saveNewPass.setProgress(0);
                    }
                };
                handler = new Handler();
                handler.postDelayed(run, 5000);
                newPass.setText("");
                old_pass.setText("");
                confPass.setText("");
            }
        } else if (v == save_new_adress) {
          String newAdress = new_adress.getText().toString();

            if(!(newAdress.isEmpty())||newAdress.length()!=0){
                SaveAddress();
            }else {
                save_new_adress.setProgress(-1);
                save_new_adress.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        save_new_adress.setEnabled(true);
                        save_new_adress.setProgress(0);
                    }
                };
                new_adress.setText("");
            }
        } else if (v == save_new_mobile) {
          String newMobile = new_mobile.getText().toString();

            if(!(newMobile.isEmpty())||newMobile.length()!=0){
                SaveMobile();
            }else {
                save_new_mobile.setProgress(-1);
                save_new_mobile.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        save_new_mobile.setEnabled(true);
                        save_new_mobile.setProgress(0);
                    }
                };
                new_mobile.setText("");
            }
        }
    }

    public void SaveUserName() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final int idSaredPref = sharedPrefUtil.getInt(ID_SHARED_PREF);
        final String guidSaredPref = sharedPrefUtil.getString(GUID_SHARED_PREF);
        saveNewName.setProgress(50);
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
                    boolean Status = jsonObject.optBoolean("Status");

                    if (Status) {
                        saveNewName.setProgress(0);
                        String UserName = OtherData.optString("UserName");
                        sharedPrefUtil.saveString(USER_NAME_SHARED_PREF, UserName);
                        newName.setText("");
                    } else {
                        saveNewName.setProgress(-1);
                        saveNewName.setEnabled(false);
                        run = new Runnable() {
                            @Override
                            public void run() {
                                saveNewName.setEnabled(true);
                                saveNewName.setProgress(0);
                            }
                        };
                        newName.setText("");
                    }
                } catch (JSONException e) {
                    saveNewName.setProgress(-1);
                    saveNewName.setEnabled(false);
                    run = new Runnable() {
                        @Override
                        public void run() {
                            saveNewName.setEnabled(true);
                            saveNewName.setProgress(0);
                        }
                    };
                    newName.setText("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                saveNewName.setProgress(-1);
                saveNewName.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        saveNewName.setEnabled(true);
                        saveNewName.setProgress(0);
                    }
                };
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("UserName", newName.getText().toString().trim());
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id",idSaredPref+"");
                param.put("GUID", guidSaredPref);
                return param;
            }
        };
        requestQueue.add(request);
    }

    public void SaveEmail() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final int idSaredPref = sharedPrefUtil.getInt(ID_SHARED_PREF);
        final String guidSaredPref = sharedPrefUtil.getString(GUID_SHARED_PREF);
        saveNewEmail.setProgress(50);
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
                        saveNewEmail.setProgress(0);
                        String Email = OtherData.optString("Email");
                        sharedPrefUtil.saveString(EMAIL_SHARED_PREF, Email);
                        newEmail.setText("");
                        MainActivity.mAdapter.notifyDataSetChanged();
                    } else {
                        saveNewEmail.setProgress(-1);
                        saveNewEmail.setEnabled(false);
                        run = new Runnable() {
                            @Override
                            public void run() {
                                saveNewEmail.setEnabled(true);
                                saveNewEmail.setProgress(0);
                            }
                        };
                        handler = new Handler();
                        handler.postDelayed(run, 5000);
                        newEmail.setText("");
                    }
                } catch (JSONException e) {
                    saveNewEmail.setProgress(-1);
                    saveNewEmail.setEnabled(false);
                    run = new Runnable() {
                        @Override
                        public void run() {
                            saveNewEmail.setEnabled(true);
                            saveNewEmail.setProgress(0);
                        }
                    };
                    handler = new Handler();
                    handler.postDelayed(run, 5000);
                    newEmail.setText("cah");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                saveNewEmail.setProgress(-1);
                saveNewEmail.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        saveNewEmail.setEnabled(true);
                        saveNewEmail.setProgress(0);
                    }
                };
                handler = new Handler();
                handler.postDelayed(run, 5000);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("UserName", sharedPrefUtil.getString(USER_NAME_SHARED_PREF));
                param.put("Email", newEmail.getText().toString().trim());
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id",""+idSaredPref);
                param.put("GUID", guidSaredPref);
                return param;
            }
        };
        requestQueue.add(request);
    }

    public void SaveMobile() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final int idSaredPref = sharedPrefUtil.getInt(ID_SHARED_PREF);
        final String guidSaredPref = sharedPrefUtil.getString(GUID_SHARED_PREF);
        save_new_mobile.setProgress(50);
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
                        save_new_mobile.setProgress(0);
                        String Mobile = OtherData.optString("Mobile");
                        sharedPrefUtil.saveString(EMAIL_SHARED_PREF, Mobile);
                        new_mobile.setText("");
                    } else {
                        save_new_mobile.setProgress(-1);
                        save_new_mobile.setEnabled(false);
                        run = new Runnable() {
                            @Override
                            public void run() {
                                save_new_mobile.setEnabled(true);
                                save_new_mobile.setProgress(0);
                            }
                        };
                        handler = new Handler();
                        handler.postDelayed(run, 5000);
                        new_mobile.setText("");
                    }
                } catch (JSONException e) {
                    save_new_mobile.setProgress(-1);
                    save_new_mobile.setEnabled(false);
                    run = new Runnable() {
                        @Override
                        public void run() {
                            save_new_mobile.setEnabled(true);
                            save_new_mobile.setProgress(0);
                        }
                    };
                    handler = new Handler();
                    handler.postDelayed(run, 5000);
                    new_mobile.setText("");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                save_new_mobile.setProgress(-1);
                save_new_mobile.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        save_new_mobile.setEnabled(true);
                        save_new_mobile.setProgress(0);
                    }
                };
                handler = new Handler();
                handler.postDelayed(run, 5000);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("UserName", sharedPrefUtil.getString(USER_NAME_SHARED_PREF));
                param.put("Mobile", new_mobile.getText().toString().trim());
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id",""+idSaredPref);
                param.put("GUID", guidSaredPref);
                return param;
            }
        };
        requestQueue.add(request);
    }

    public void SaveAddress() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        final int idSaredPref = sharedPrefUtil.getInt(ID_SHARED_PREF);
        final String guidSaredPref = sharedPrefUtil.getString(GUID_SHARED_PREF);
        save_new_adress.setProgress(50);
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditProfile, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
                        save_new_adress.setProgress(0);
                        String Email = OtherData.optString("Email");
                        sharedPrefUtil.saveString(ADDRESS_NAME_SHARED_PREF, Email);
                        save_new_adress.setText("");
                    } else {
                        save_new_adress.setProgress(-1);
                        save_new_adress.setEnabled(false);
                        run = new Runnable() {
                            @Override
                            public void run() {
                                save_new_adress.setEnabled(true);
                                save_new_adress.setProgress(0);
                            }
                        };
                        handler = new Handler();
                        handler.postDelayed(run, 5000);
                        new_adress.setText("");
                    }
                } catch (JSONException e) {
                    save_new_adress.setProgress(-1);
                    save_new_adress.setEnabled(false);
                    run = new Runnable() {
                        @Override
                        public void run() {
                            save_new_adress.setEnabled(true);
                            save_new_adress.setProgress(0);
                        }
                    };
                    handler = new Handler();
                    handler.postDelayed(run, 5000);
                    new_adress.setText("cah");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                save_new_adress.setProgress(-1);
                save_new_adress.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        save_new_adress.setEnabled(true);
                        save_new_adress.setProgress(0);
                    }
                };
                handler = new Handler();
                handler.postDelayed(run, 5000);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("UserName", sharedPrefUtil.getString(USER_NAME_SHARED_PREF));
                param.put("Address", new_adress.getText().toString().trim());
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id",idSaredPref+"");
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
        saveNewPass.setProgress(50);
        StringRequest request = new StringRequest(Request.Method.POST, URL_CustomerEditPassword, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject OtherData = jsonObject.optJSONObject("OtherData");
                    boolean Status = jsonObject.optBoolean("Status");
                    if (Status) {
                        saveNewPass.setProgress(0);
                        String GUID = OtherData.optString("GUID");
                        sharedPrefUtil.saveString(GUID_SHARED_PREF, GUID);
                        newPass.setText("");
                        old_pass.setText("");
                        confPass.setText("");
                    } else {
                        saveNewPass.setProgress(-1);
                        saveNewPass.setEnabled(false);
                        run = new Runnable() {
                            @Override
                            public void run() {
                                saveNewPass.setEnabled(true);
                                saveNewPass.setProgress(0);
                            }
                        };
                        handler = new Handler();
                        handler.postDelayed(run, 5000);
                        newPass.setText("");
                        old_pass.setText("");
                        confPass.setText("");
                    }
                } catch (JSONException e) {
                    saveNewPass.setProgress(-1);
                    saveNewPass.setEnabled(false);
                    run = new Runnable() {
                        @Override
                        public void run() {
                            saveNewPass.setEnabled(true);
                            saveNewPass.setProgress(0);
                        }
                    };
                    handler = new Handler();
                    handler.postDelayed(run, 5000);
                    newPass.setText("");
                    old_pass.setText("");
                    confPass.setText("");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                saveNewPass.setProgress(-1);
                saveNewPass.setEnabled(false);
                run = new Runnable() {
                    @Override
                    public void run() {
                        saveNewPass.setEnabled(true);
                        saveNewPass.setProgress(0);
                    }
                };
                handler = new Handler();
                handler.postDelayed(run, 5000);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param = new HashMap<String, String>();
                param.put("UserName", sharedPrefUtil.getString(USER_NAME_SHARED_PREF));
                param.put("Password", newPass.getText().toString().trim());
                param.put("OldPassword", oldPass);
                param.put("FullName", sharedPrefUtil.getString(FULL_NAME_SHARED_PREF));
                param.put("Id", String.valueOf(idSaredPref));
                param.put("GUID", guidSaredPref);
                return param;
            }
        };
        requestQueue.add(request);
    }


}