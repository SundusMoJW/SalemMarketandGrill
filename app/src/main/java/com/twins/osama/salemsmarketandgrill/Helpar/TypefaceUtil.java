package com.twins.osama.salemsmarketandgrill.Helpar;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.FONT_NAME;

/**
 * Created by Osama on 8/8/2017.
 */

public class TypefaceUtil {

    private static Typeface font;

        public static void applyFont(final Context context, final View v) {
            font = Typeface.createFromAsset(context.getAssets(),  FONT_NAME);
            try {
                if (v instanceof ViewGroup) {
                    ViewGroup vg = (ViewGroup) v;
                    for (int i = 0; i < vg.getChildCount(); i++) {
                        View child = vg.getChildAt(i);
                        applyFont(context, child);
                    }
                } else if (v instanceof TextView) {
                    ((TextView) v).setTypeface(font);
                } else if (v instanceof EditText) {
                    ((EditText) v).setTypeface(font);
                } else if (v instanceof Button) {
                    ((Button) v).setTypeface(font);
                } else if (v instanceof RadioButton) {
                    ((RadioButton) v).setTypeface(font);
                }
            } catch (Exception e) {
                e.printStackTrace();
                // ignore
            }
        }
    }

