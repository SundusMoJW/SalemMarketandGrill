package com.twins.osama.salemsmarketandgrill.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Activity.MainActivity;
import com.twins.osama.salemsmarketandgrill.Adapter.CartItemAdapter;
import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;
import java.util.ListIterator;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;


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
    private ArrayList<CartItem> list = new ArrayList<>();
    private Realm realm;
    private CheckBox tvItem;
    private TextView shipping;
    private TextView subtotal;
    private TextView total;
    private ActionMode mActionmode;
    private int myPosition;
    private CartItemAdapter rvadapter;
    private Button updateCart;
    private CheckBox checkbox;
    private ImageView delete;

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
        nav_back = 1;
        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);
//        getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
        shipping = view.findViewById(R.id.shipping);
        subtotal = view.findViewById(R.id.subtotal);
        total = view.findViewById(R.id.total);
        checkbox = view.findViewById(R.id.checkbox);
        delete = view.findViewById(R.id.delete);
//        You have 1 item in your Cart
        tvItem = view.findViewById(R.id.checkbox);
        RealmResults<CartItem> cartItems = RealmController.with(getActivity()).getCartItems();
        Log.i("cartItems", cartItems.toString());
        Log.i("cartItems", cartItems.isEmpty() + "");
        list = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
        if (cartItems.isEmpty())
            tvItem.setText("You have 0 item in your Cart");
        else tvItem.setText("You have " + list.size() + " item in your Cart");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.itemCart);
        rvadapter = new CartItemAdapter((MainActivity) getActivity(), list, subtotal, shipping, total
//                ,new OnClickedCartItem() {
//                    @Override
//                    public boolean onItemLongClicked(int position) {
//                        myPosition = position;
//                        mActionmode = getActivity().startActionMode(mActionModeCallback);
//                        return true;
//                    }
//                }
        );
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
                mFragmentTransaction.add(R.id.frame_layout, fragment).commit();
            }
        });
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (CartItem cartItem : list) {
                    cartItem.setSelect(b);
                    rvadapter.notifyDataSetChanged();
//                    rvadapter = new CartItemAdapter((MainActivity) getActivity(), list, subtotal, shipping, total);

//                    final CartItem cheackCartItem = RealmController.with(getActivity()).cheackCartItem(cartItem.getId(),
//                            cartItem.getIdTypeList());
//                    realm.beginTransaction();
//                    cheackCartItem.setSelect(b);
//                    realm.commitTransaction();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure you want to Delete items?");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                deleteFromCartItem();
                                RealmResults<CartItem> realmResults = RealmController.with(getActivity()).getCartItems();
//                                list.clear();
                                rvadapter.notifyDataSetChanged();
                                list = (ArrayList<CartItem>) realm.copyFromRealm(realmResults);
                                ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(list.size() + "");
//                                rvadapter = new CartItemAdapter((MainActivity) getActivity(), list, subtotal, shipping, total);
                                rvadapter.notifyDataSetChanged();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });
                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
//        updateCart = (Button) view.findViewById(R.id.updateCart);
//        updateCart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        RealmResults<CartItem> result = realm.where(CartItem.class).findAll();
//                        if (!(result.isEmpty())) {
//                            result.deleteAllFromRealm();
//                            Toast.makeText(getActivity(), "Deleted All successfully", Toast.LENGTH_SHORT).show();
//                            rvadapter.notifyDataSetChanged();
//                        }
//                    }
//                });
//            }
//        });
        return view;
    }

//    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
//        @Override
//        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//            getActivity().getMenuInflater().inflate(R.menu.menu, menu);
//            return true;
//        }
//
//        @Override
//        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//            return false;
//        }
//
//        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
//        @Override
//        public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.delete:
////                    getActivity().findViewById(R.id.toolbar).setVisibility(View.GONE);
//
//                    realm.executeTransaction(new Realm.Transaction() {
//                        @Override
//                        public void execute(Realm realm) {
//                            CartItem cartItem = realm.where(CartItem.class)
//                                    .equalTo("Id", list.get(myPosition).getId())
//                                    .equalTo("IdTypeList", list.get(myPosition).getIdTypeList())
//                                    .findFirst();
//                            if (cartItem != null) {
//                                cartItem.deleteFromRealm();
//                                Toast.makeText(getActivity(), "Deleted successfully", Toast.LENGTH_SHORT).show();
//                                rvadapter.notifyDataSetChanged();
//                                mode.finish();
////                                getActivity().findViewById(R.id.toolbar).setVisibility(View.VISIBLE);
//                            }
//                        }
//                    });
//                    break;
//            }
//            return false;
//        }
//
//        @Override
//        public void onDestroyActionMode(ActionMode mode) {
//            mActionmode = null;
//        }
//    };

    private void deleteFromCartItem() {
//        RealmResults<CartItem> cartItems = RealmController.with(getActivity()).getCartItems();
//        list = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
        ListIterator<CartItem> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            CartItem item = listIterator.next();
            if ((item.isSelect())) {
                realm.beginTransaction();
                final CartItem cheackCartItem = RealmController.with(getActivity()).cheackCartItem(item.getId(),
                        item.getIdTypeList());
                cheackCartItem.deleteFromRealm();
                realm.commitTransaction();
//                rvadapter.notifyDataSetChanged();

            }
        }
    }
}
