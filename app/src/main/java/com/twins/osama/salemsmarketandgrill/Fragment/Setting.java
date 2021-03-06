package com.twins.osama.salemsmarketandgrill.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Activity.Login;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ADDRESS;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.ADDRESS_NAME_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.KEY;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.MOBILE;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.MOBILE_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.PASSWORD;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USERNAME;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USER_NAME_SHARED_PREF;


public class Setting extends Fragment implements View.OnClickListener {
    private RelativeLayout userNameAccount;
    private TextView actauleName;
    private RelativeLayout emailAccount;
    private TextView actauleEmail;
    private RelativeLayout passwordAccount;
    private TextView actaulePassword;
    private TextView logout_account;
    private Switch switchNotification;
    private TextView aboutApp;
    private Bundle bundle;
    private SharedPrefUtil sharedPrefUtil;
    private Fragment nextFragment;
    private FragmentManager fragmentManager;
    //    private Switch switch_language;
    private String strLanguge;
    public Activity mActivity;
    private RelativeLayout address_account;
    private TextView actauleAddress;
    private RelativeLayout mobileAccount;
    private TextView actauleMobile;

    public static Setting newInstance() {
        Setting fragment = new Setting();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.setting_id));
        mActivity = getActivity();
        sharedPrefUtil = new SharedPrefUtil(getActivity());
        userNameAccount = (RelativeLayout) view.findViewById(R.id.user_name_account);
        actauleName = (TextView) view.findViewById(R.id.actaule_name);
        String user_name_shared_pref = sharedPrefUtil.getString(USER_NAME_SHARED_PREF);
        if (user_name_shared_pref.equals("null"))
            user_name_shared_pref = "";
        actauleName.setText(user_name_shared_pref);
        nav_back=1;
        emailAccount = (RelativeLayout) view.findViewById(R.id.email_account);
        actauleEmail = (TextView) view.findViewById(R.id.actaule_email);
        String email_shared_pref = sharedPrefUtil.getString(EMAIL_SHARED_PREF);
        if (email_shared_pref.equals("null"))
            email_shared_pref = "";
        actauleEmail.setText(email_shared_pref);

        passwordAccount = (RelativeLayout) view.findViewById(R.id.password_account);
        actaulePassword = (TextView) view.findViewById(R.id.actaule_password);
        logout_account = (TextView) view.findViewById(R.id.logout_account);
        switchNotification = (Switch) view.findViewById(R.id.switch_notification);
        address_account = (RelativeLayout) view.findViewById(R.id.address_account);
        actauleAddress = (TextView) view.findViewById(R.id.actaule_address);
        String address_name_shared_pref = sharedPrefUtil.getString(ADDRESS_NAME_SHARED_PREF);
        if (address_name_shared_pref.equals("null"))
            address_name_shared_pref = "";
        actauleAddress.setText(address_name_shared_pref);

        mobileAccount = (RelativeLayout) view.findViewById(R.id.mobile_account);
        actauleMobile = (TextView) view.findViewById(R.id.actaule_mobile);
        String mobile_shared_pref = sharedPrefUtil.getString(MOBILE_SHARED_PREF);
        if (mobile_shared_pref.equals("null"))
            mobile_shared_pref = "";
        actauleMobile.setText(mobile_shared_pref);

//        strLanguge = sharedPrefUtil.getString(LANG);

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

//        switch_language = (Switch) view.findViewById(R.id.switch_Language);
//        switch_language.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                String arabic = (String) getResources().getText(R.string.Arabic);
//                String english = (String) getResources().getText(R.string.English);
//                if (buttonView.isChecked()) {
//                    if (switch_language.getText() == arabic) {
//                        switch_language.setText(english);
//                        sharedPrefUtil.saveString(LANG, english);
//                        String languageToLoad = "en";
//                        Locale locale = new Locale(languageToLoad);
//                        Locale.setDefault(locale);
//                        Configuration config = new Configuration();
//                        config.locale = locale;
//                        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
//                        Intent intent = new Intent(getActivity(), Splash.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        getActivity().finish();
//
//                    } else {
//                        switch_language.setText((String) getResources().getText(R.string.Arabic));
//                        sharedPrefUtil.saveString(LANG, arabic);
//                        String languageToLoad = "ar";
//                        Locale locale = new Locale(languageToLoad);
//                        Locale.setDefault(locale);
//                        Configuration config = new Configuration();
//                        config.locale = locale;
//                        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
//                        Intent intent = new Intent(getActivity(), Splash.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        getActivity().finish();
//                    }
//                } else {
//
//                    if (switch_language.getText() == arabic) {
//                        switch_language.setText(english);
//                        sharedPrefUtil.saveString(LANG, english);
//                        String languageToLoad = "en";
//                        Locale locale = new Locale(languageToLoad);
//                        Locale.setDefault(locale);
//                        Configuration config = new Configuration();
//                        config.locale = locale;
//                        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
//                        Intent intent = new Intent(getActivity(), Splash.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        getActivity().finish();
//                    } else {
//                        switch_language.setText((String) getResources().getText(R.string.Arabic));
//                        sharedPrefUtil.saveString(LANG, arabic);
//                        String languageToLoad = "ar";
//                        Locale locale = new Locale(languageToLoad);
//                        Locale.setDefault(locale);
//                        Configuration config = new Configuration();
//                        config.locale = locale;
//                        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
//                        Intent intent = new Intent(getActivity(), Splash.class);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        getActivity().finish();
//                    }
//                }
//            }
//
//        });

        aboutApp = (TextView) view.findViewById(R.id.about_app);
        aboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle((String) getResources().getText(R.string.about_app));
                builder.setMessage((String) getResources().getText(R.string.message));
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        userNameAccount.setOnClickListener(this);
        emailAccount.setOnClickListener(this);
        passwordAccount.setOnClickListener(this);
        mobileAccount.setOnClickListener(this);
        address_account.setOnClickListener(this);
        logout_account.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_name_account:
                bundle = new Bundle();
                bundle.putString(KEY, USERNAME); // set your parameteres
                nextFragment = new AccountFragment();
                nextFragment.setArguments(bundle);

                break;
            case R.id.password_account:
                bundle = new Bundle();
                bundle.putString(KEY, PASSWORD); // set your parameteres
                nextFragment = new AccountFragment();
                nextFragment.setArguments(bundle);
                break;
            case R.id.mobile_account:
                bundle = new Bundle();
                bundle.putString(KEY, MOBILE); // set your parameteres
                nextFragment = new AccountFragment();
                nextFragment.setArguments(bundle);
                break;
            case R.id.address_account:
                bundle = new Bundle();
                bundle.putString(KEY, ADDRESS); // set your parameteres
                nextFragment = new AccountFragment();
                nextFragment.setArguments(bundle);
                break;
            case R.id.email_account:
                bundle = new Bundle();
                bundle.putString(KEY, EMAIL); // set your parameteres
                nextFragment = new AccountFragment();
                nextFragment.setArguments(bundle);
                break;
            case R.id.logout_account:
                nextFragment = new Setting();
                Logout(getActivity());
                break;
        }
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_layout, nextFragment).addToBackStack(null).commit();


    }

    private void Logout(final Activity activity) {
        //Creating an alert dialog to confirm logout
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        sharedPrefUtil = new SharedPrefUtil(getActivity());
                        sharedPrefUtil.clear();
                        Intent intent = new Intent(getActivity(), Login.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });
        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

}


