package com.twins.osama.salemsmarketandgrill.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {
    private Button buPlus;
    private Button buMins;
    private TextView txtResult;
    private Button bucheckout;
    private CheckOutSteps fragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    public EditText showPopupCart;
    private TextView componentSalry;
    private int componentSalryInteger = 1;
    private EditText spinner;

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.cart_fragment));

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        buPlus = (Button) view.findViewById(R.id.plus);
        buMins = (Button) view.findViewById(R.id.mins);
        txtResult = (TextView) view.findViewById(R.id.result);
        componentSalry = (TextView) view.findViewById(R.id.componentSalry);
        componentSalryInteger = Integer.parseInt(componentSalry.getText().toString());
        spinner = view.findViewById(R.id.spinner);

        buPlus.setOnClickListener(new View.OnClickListener() {
            private int oldValue;

            @Override
            public void onClick(View v) {
                oldValue = Integer.parseInt((String) txtResult.getText());
                oldValue++;
                componentSalryInteger *= oldValue;
                txtResult.setText("" + oldValue);
                componentSalry.setText("" + componentSalryInteger);
            }
        });
        buMins.setOnClickListener(new View.OnClickListener() {
            private int oldValue;

            @Override
            public void onClick(View v) {
                oldValue = Integer.parseInt((String) txtResult.getText());
                oldValue--;

                if (oldValue <= 0 | oldValue == 1) {
                    oldValue = 1;
                    componentSalryInteger = 9;
                }
                componentSalryInteger /= oldValue;
                txtResult.setText("" + oldValue);
                componentSalry.setText("" + componentSalryInteger);
            }
        });
//        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
//
//        final List<String> list = new ArrayList<String>();
//        list.add("United State (US)");
//        list.add("United State (US)");
//        list.add("United State (US)");
//        list.add("United State (US)");
//        list.add("United State (US)");
//        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        List<String> items = new ArrayList();
        items.add("United State (US)");
        items.add("United State (US)");
        items.add("United State (US)");
        items.add("United State (US)");
        items.add("United State (US)");
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,/* R.layout.spinner_layout, R.id.showPopupCart,*/items);

        spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
                dialog.setContentView(R.layout.spinner_layout);
                ListView listView = (ListView) dialog.findViewById(R.id.showPopupCart);
                final List<String> items = new ArrayList();
                items.add("United State (US)");
                items.add("United State (US)");
                items.add("United State (US)");
                items.add("United State (US)");
                items.add("United State (US)");
                ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,/* R.layout.spinner_layout, R.id.showPopupCart,*/items);
//
                listView.setAdapter(adapter);
//
                listView.setTextFilterEnabled(true);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        spinner.setText(items.get(position));
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });

        bucheckout = (Button) view.findViewById(R.id.bucheckout);
        bucheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new CheckOutSteps();
                mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frame_layout, fragment).commit();
            }
        });

        return view;
    }

}
