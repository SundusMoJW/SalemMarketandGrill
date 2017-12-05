package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Activity.MainActivity;
import com.twins.osama.salemsmarketandgrill.Adapter.CartItemAdapter;
import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


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
    private ArrayList<CartItem> list=new ArrayList<>();
    private Realm realm;
    private TextView tvItem;
    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.cart_fragment));
        this.realm = RealmController.with(getActivity()).getRealm();

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

//        You have 1 item in your Cart
        tvItem=view.findViewById(R.id.tv);
        RealmResults<CartItem> cartItems = RealmController.with(getActivity()).getCartItems();
        Log.i("cartItems",cartItems.toString());
        Log.i("cartItems",cartItems.isEmpty()+"");
        list = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
        if(cartItems.isEmpty())
        tvItem.setText("You have 0 item in your Cart");
        else tvItem.setText("You have "+list.size() +" item in your Cart");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.itemCart);
        CartItemAdapter rvadapter = new CartItemAdapter((MainActivity)getActivity(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(rvadapter);

//        buPlus = (Button) view.findViewById(R.id.plus);
////        buMins = (Button) view.findViewById(R.id.mins);
//        txtResult = (TextView) view.findViewById(R.id.result);
//        componentSalry = (TextView) view.findViewById(R.id.componentSalry);
//        componentSalryInteger = Integer.parseInt(componentSalry.getText().toString());
//        spinner = view.findViewById(R.id.spinner);

//        buPlus.setOnClickListener(new View.OnClickListener() {
//            private int oldValue;
//
//            @Override
//            public void onClick(View v) {
//                oldValue = Integer.parseInt((String) txtResult.getText());
//                oldValue++;
//                componentSalryInteger *= oldValue;
//                txtResult.setText("" + oldValue);
//                componentSalry.setText("" + componentSalryInteger);
//            }
//        });
//        buMins.setOnClickListener(new View.OnClickListener() {
//            private int oldValue;
//
//            @Override
//            public void onClick(View v) {
//                oldValue = Integer.parseInt((String) txtResult.getText());
//                oldValue--;
//
//                if (oldValue <= 0 | oldValue == 1) {
//                    oldValue = 1;
//                    componentSalryInteger = 9;
//                }
//                componentSalryInteger /= oldValue;
//                txtResult.setText("" + oldValue);
//                componentSalry.setText("" + componentSalryInteger);
//            }
//        });
//        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
//
//        final List<String> list = new ArrayList<String>();
//        list.add("United State (US)");
//        list.add("United State (US)");
//        list.add("United State (US)");
//        list.add("United State (US)");
//        list.add("United State (US)");
//        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
//
//
// List<String> items = new ArrayList();
//        items.add("United State (US)");
//        items.add("United State (US)");
//        items.add("United State (US)");
//        items.add("United State (US)");
//        items.add("United State (US)");
//        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,/* R.layout.spinner_layout, R.id.showPopupCart,*/items);
//
//        spinner.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final Dialog dialog = new Dialog(getActivity());
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //before
//                dialog.setContentView(R.layout.spinner_layout);
//                ListView listView = (ListView) dialog.findViewById(R.id.showPopupCart);
//                final List<String> items = new ArrayList();
//                items.add("United State (US)");
//                items.add("United State (US)");
//                items.add("United State (US)");
//                items.add("United State (US)");
//                items.add("United State (US)");
//                ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,/* R.layout.spinner_layout, R.id.showPopupCart,*/items);
////
//                listView.setAdapter(adapter);
////
//                listView.setTextFilterEnabled(true);
//                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        spinner.setText(items.get(position));
//                        dialog.cancel();
//                    }
//                });
//                dialog.show();
//            }
//        });

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
