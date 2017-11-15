package com.twins.osama.salemsmarketandgrill.Fragment;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;
import java.util.List;


public class CheckOutSteps extends Fragment implements View.OnClickListener {
    private LinearLayout select1;
    private LinearLayout select2;
    private LinearLayout select3;
    TextView tv_title;
    private LinearLayout contentStep3;
    private LinearLayout contentStep2;
    private LinearLayout contentStep1;
    private ImageView arrowUpS1;
    private ImageView arrowUpS2;
    private ImageView arrowUpS3;
    private ImageView arrowDownS1;
    private ImageView arrowDownS2;
    private ImageView arrowDownS3;
    private TextView direct_transfer;
    private TextView pay_pal;
    private TextView delivery;
    private TextView cheque_draft;
    private TextView on_place;
    Resources res;
    private EditText showPopupCheckout;
    private SharedPrefUtil sharedPrefUtil=new SharedPrefUtil(this.getActivity());

    public static CheckOutSteps newInstance() {
        CheckOutSteps fragment = new CheckOutSteps();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    res = getActivity().getResources();
        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_check_out_steps, container, false);
        TypefaceUtil.applyFont(getActivity(),view.findViewById(R.id.check_out_steps));

        View mainView = inflater.inflate(R.layout.activity_main, container, false);
        tv_title = (TextView) mainView.findViewById(R.id.tv_title);
        tv_title.setText(getText(R.string.checkout));

        ((TextView) getActivity().findViewById(R.id.tv_title)).setText(getText(R.string.checkout));
        select1 = (LinearLayout) view.findViewById(R.id.select1);
        select2 = (LinearLayout) view.findViewById(R.id.select2);
        select3 = (LinearLayout) view.findViewById(R.id.select3);

        contentStep1 = (LinearLayout) view.findViewById(R.id.contentStep1);
        contentStep2 = (LinearLayout) view.findViewById(R.id.contentStep2);
        contentStep3 = (LinearLayout) view.findViewById(R.id.contentStep3);

        arrowUpS1 = (ImageView) view.findViewById(R.id.arrow_up_step1);
        arrowUpS2 = (ImageView) view.findViewById(R.id.arrow_up_step2);
        arrowUpS3 = (ImageView) view.findViewById(R.id.arrow_up_step3);

        arrowDownS1 = (ImageView) view.findViewById(R.id.arrow_down_step1);
        arrowDownS2 = (ImageView) view.findViewById(R.id.arrow_down_step2);
        arrowDownS3 = (ImageView) view.findViewById(R.id.arrow_down_step3);

        select1.setOnClickListener(this);
        select2.setOnClickListener(this);
        select3.setOnClickListener(this);

        direct_transfer= (TextView)view.findViewById(R.id.direct_transfer);
        pay_pal= (TextView)view.findViewById(R.id.pay_pal);
        delivery= (TextView)view.findViewById(R.id.delivery);
         on_place = (TextView) view.findViewById(R.id.on_place);
        cheque_draft= (TextView)view.findViewById(R.id.cheque_draft);

        clearStylePaymentMethod();
        pay_pal.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        clearStyleDeliveryMethod();

        pay_pal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStylePaymentMethod();
                pay_pal.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        direct_transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStylePaymentMethod();
                direct_transfer.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        cheque_draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearStylePaymentMethod();
                cheque_draft.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
//        Const.LANG,"Arabic"
//        sharedPrefUtil=new SharedPrefUtil(this.getActivity());
        if(res.getConfiguration().locale.getDisplayLanguage().equalsIgnoreCase(res.getString(R.string.Arabic))) {
            delivery.setBackground(getResources().getDrawable(R.drawable.with_corner_right_fill));
            on_place.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearStyleDeliveryMethod();
                    on_place.setBackground(getResources().getDrawable(R.drawable.with_corner_left_fill));
                }
            });
            delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearStyleDeliveryMethod();
                    delivery.setBackground(getResources().getDrawable(R.drawable.with_corner_right_fill));

                }
            });
        }else {
            delivery.setBackground(getResources().getDrawable(R.drawable.with_corner_left_fill));
            delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearStyleDeliveryMethod();
                    delivery.setBackground(getResources().getDrawable(R.drawable.with_corner_left_fill));
                }
            });
            on_place.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearStyleDeliveryMethod();
                    on_place.setBackground(getResources().getDrawable(R.drawable.with_corner_right_fill));

                }
            });
        }
        showPopupCheckout = (EditText) view.findViewById(R.id.showPopupCheckout);
        showPopupCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.spinner_layout);
                ListView listView = (ListView) dialog.findViewById(R.id.showPopupCart);
                final List<String> items = new ArrayList();
                items.add("Country");
                items.add("Country");
                items.add("Country");
                items.add("Country");
                ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,/* R.layout.spinner_layout, R.id.showPopupCart,*/items);
//
                listView.setAdapter(adapter);
//
                listView.setTextFilterEnabled(true);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        showPopupCheckout.setText(items.get(position));
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        return view;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.select1:
                if (contentStep1.getVisibility() == View.GONE) {

                    contentStep1.setVisibility(View.VISIBLE);
                    contentStep2.setVisibility(View.GONE);
                    contentStep3.setVisibility(View.GONE);
                    arrowDownS1.setVisibility(View.VISIBLE);
                    arrowUpS1.setVisibility(View.GONE);

                    arrowDownS2.setVisibility(View.GONE);
                    arrowUpS2.setVisibility(View.VISIBLE);

                    arrowDownS3.setVisibility(View.GONE);
                    arrowUpS3.setVisibility(View.VISIBLE);

                } else {
                    contentStep1.setVisibility(View.GONE);
                    arrowDownS1.setVisibility(View.GONE);
                    arrowUpS1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.select2:
                if (contentStep2.getVisibility() == View.GONE) {
                    contentStep2.setVisibility(View.VISIBLE);
                    contentStep1.setVisibility(View.GONE);
                    contentStep3.setVisibility(View.GONE);
                    arrowDownS2.setVisibility(View.VISIBLE);
                    arrowUpS2.setVisibility(View.GONE);

                    arrowDownS1.setVisibility(View.GONE);
                    arrowUpS1.setVisibility(View.VISIBLE);

                    arrowDownS3.setVisibility(View.GONE);
                    arrowUpS3.setVisibility(View.VISIBLE);

                } else {
                    contentStep2.setVisibility(View.GONE);
                    arrowDownS2.setVisibility(View.GONE);
                    arrowUpS2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.select3:
                if (contentStep3.getVisibility() == View.GONE) {
                    contentStep3.setVisibility(View.VISIBLE);
                    contentStep2.setVisibility(View.GONE);
                    contentStep1.setVisibility(View.GONE);
                    arrowDownS3.setVisibility(View.VISIBLE);
                    arrowUpS3.setVisibility(View.GONE);

                    arrowDownS1.setVisibility(View.GONE);
                    arrowUpS1.setVisibility(View.VISIBLE);

                    arrowDownS2.setVisibility(View.GONE);
                    arrowUpS2.setVisibility(View.VISIBLE);

                } else {
                    contentStep3.setVisibility(View.GONE);
                    arrowDownS3.setVisibility(View.GONE);
                    arrowUpS3.setVisibility(View.VISIBLE);
                }
                break;
            default:
                if (contentStep1.getVisibility() == View.GONE) {
                    contentStep1.setVisibility(View.VISIBLE);
                } else {
                    contentStep1.setVisibility(View.GONE);
                }
                break;
        }
    }
    public void clearStylePaymentMethod(){
direct_transfer.setBackground(getResources().getDrawable(R.drawable.edit_text));
        cheque_draft.setBackground(getResources().getDrawable(R.drawable.edit_text));
        pay_pal.setBackground(getResources().getDrawable(R.drawable.edit_text));
    }
    public void clearStyleDeliveryMethod() {
        res = getActivity().getResources();
        if (res.getConfiguration().locale.getDisplayLanguage().equalsIgnoreCase(res.getString(R.string.Arabic))) {
            delivery.setBackground(getResources().getDrawable(R.drawable.with_corner_right));
            on_place.setBackground(getResources().getDrawable(R.drawable.with_corner_left));
        } else {
            on_place.setBackground(getResources().getDrawable(R.drawable.with_corner_right));
            delivery.setBackground(getResources().getDrawable(R.drawable.with_corner_left));
        }
    }


}
