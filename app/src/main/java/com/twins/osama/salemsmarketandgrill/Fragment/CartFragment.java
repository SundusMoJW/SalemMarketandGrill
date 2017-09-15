package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;


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
    private int componentSalryInteger=1;


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
        TypefaceUtil.applyFont(getActivity(),view.findViewById(R.id.cart_fragment));

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
        buPlus.setOnClickListener(new View.OnClickListener() {
            private int oldValue;

            @Override
            public void onClick(View v) {
                oldValue = Integer.parseInt((String) txtResult.getText());
                oldValue++;
                componentSalryInteger *= oldValue;
                txtResult.setText("" + oldValue);
                componentSalry.setText(""+componentSalryInteger);
            }
        });
        buMins.setOnClickListener(new View.OnClickListener() {
            private int oldValue;

            @Override
            public void onClick(View v) {
                oldValue = Integer.parseInt((String) txtResult.getText());
                oldValue--;

                if (oldValue <= 0|oldValue==1) {
                    oldValue = 1;
                    componentSalryInteger=9;
                }
                componentSalryInteger /= oldValue;
                txtResult.setText("" + oldValue);
                componentSalry.setText(""+componentSalryInteger);
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
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner.setAdapter(arrayAdapter);
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

        showPopupCart = (EditText) view.findViewById(R.id.showPopupCart);
        showPopupCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });
        return view;
    }

    public void showPopup(View view) {
        PopupMenu popup = new PopupMenu(getActivity(), view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popup1u:
                        showPopupCart.setText(item.getTitle());
                        return true;
                    case R.id.popup2u:
                        showPopupCart.setText(item.getTitle());
                        return true;
                    case R.id.popup3u:
                        showPopupCart.setText(item.getTitle());
                        return true;
                    default:
                        return false;
                }
            }

        });

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_cart, popup.getMenu());

        popup.show();
    }


}
