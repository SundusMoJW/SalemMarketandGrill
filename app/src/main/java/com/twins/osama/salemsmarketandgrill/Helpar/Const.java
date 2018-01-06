package com.twins.osama.salemsmarketandgrill.Helpar;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.twins.osama.salemsmarketandgrill.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Osama on 8/2/2017.
 * Exo_ExtraLight
 */

public class Const {

    public static final String KEY = "key";
    public static final String IMAGE_PROFILE = "imageProfile";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String MOBILE = "mobile";
    public static final String ADDRESS = "address";

    public static final String EMAIL = "email";
    public static final String LANG = "language";

    public static final String IMAGE_VIEW = "load_image";

    public static final String FONT_NAME = "fonts/Exo_Medium.otf";

    public static final String ADDRESS_NAME_SHARED_PREF = "addressInSharedPref";
    public static final String MOBILE_SHARED_PREF = "mobileInSharedPref";
    public static final String USER_NAME_SHARED_PREF = "loggedInSharedPref";
    public static final String FULL_NAME_SHARED_PREF = "fullNameSharedPref";
    public static final String ID_SHARED_PREF = "idSharedPref";
    public static final String GUID_SHARED_PREF = "guidSharedPref";
    public static final String EMAIL_SHARED_PREF = "emailSharedPref";
    public static final String STATUS_SHARED_PREF = "statusSharedPref";
    public static final String PASSWORD_SHARED_PREF = "passwordSharedPref";

    public static final String URL_LOGIN = "http://saleem.newsolutions.ps/APIs/CustomerLogin";
    public static final String URL_SIGNUP = "http://saleem.newsolutions.ps/APIs/CustomerSingUp";
    public static final String URL_CustomerEditProfile = "http://saleem.newsolutions.ps/APIs/CustomerEditProfile";
    public static final String URL_CustomerEditPassword = "http://saleem.newsolutions.ps/APIs/CustomerEditPassword";
    public static final String STATIC_URL = "http://saleem.newsolutions.ps/APIs/";
    public static final String URLF = "http://saleem.newsolutions.ps/APIs/getSlider";
    public static final String IMG_URL = "http://saleem.newsolutions.ps";

    public static final String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";


    public static void setLangSettings(Activity activity) {
        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(activity);
        Resources res = activity.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        if (TextUtils.isEmpty(sharedPrefUtil.getString(Const.LANG))) {
            if (res.getConfiguration().locale.getDisplayLanguage().equalsIgnoreCase(res.getString(R.string.Arabic))) {

                sharedPrefUtil.saveString(Const.LANG, "Arabic");
            } else {
                sharedPrefUtil.saveString(Const.LANG, "English");
            }
        } else {
            conf.locale = new Locale(sharedPrefUtil.getString(Const.LANG).substring(0, 2)
                    .toLowerCase());
        }
        res.updateConfiguration(conf, dm);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and
            // keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String strPath, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(strPath, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(strPath, options);
    }

    public static void wrongAlertDialog(Activity activity/*, JSONObject jsonObject*/) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Something went wrong");
//        builder.setMessage(jsonObject.optString("ResultText"));
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

    public static void saveData(Activity activity, int recourseTitel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(recourseTitel);
        builder.setMessage("Your data has been successfully saved");
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
//    public static String getDate(long time) {
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(time);
//        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
//        return date;
//    }

//    public static String getDate(long time) {
//        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
//        cal.setTimeInMillis(time * 1000L);
//        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
//        return date;
//    }
    public static String getDate(long time) {
        Date d;
        String s="";
        try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
          d = sdf.parse(time+"");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
           s=  sdf2.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Calendar c = Calendar.getInstance();
//        c.setTimeInMillis(time);
//        Date d = c.getTime();
        return s;
    }
}
//
