package com.twins.osama.salemsmarketandgrill.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.VolleyRequests;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import io.realm.Realm;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;
import static com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment.constant;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;


public class RestaurantFragment extends Fragment {
    private Realm realm;
    private ArrayList<Meals> meals;
    private int position;
    private SimpleDraweeView restImg;
    private TextView restType;
    private TextView retTitel;
    private TextView restDescription;
    private ArrayList<TypeList> typeList;
    private TypeList typeObj = new TypeList();
    private String typeName;
    private Meals realMeals = new Meals();
    private Market realMarket = new Market();
    private String description;

    public static RestaurantFragment newInstance() {

        RestaurantFragment fragment = new RestaurantFragment();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        position = getArguments().getInt("position");

        Realm.init(getActivity());
        this.realm = RealmController.with(getActivity()).getRealm();
        RealmController.with(getActivity()).refresh();
        realm = Realm.getDefaultInstance();

//        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_restaurant, container, false);
        nav_back = 1;
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.restaurant_id));
        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);
        restImg = (SimpleDraweeView) view.findViewById(R.id.rest_img);
        restType = (TextView) view.findViewById(R.id.rest_type);
        retTitel = (TextView) view.findViewById(R.id.ret_titel);
        restDescription = (TextView) view.findViewById(R.id.rest_description);

        Log.i("///**", position + " ");
        if (constant == 1) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realMeals = realm.where(Meals.class).equalTo("Id", position).equalTo("isDeleted", false).findFirst();
                    Log.i("///", realMeals + " ");
                    typeObj = realm.where(TypeList.class).equalTo("Id", realMeals.getIdTypeList()).equalTo("isDeleted", false).findFirst();
                }
            });

            restType.setText(typeObj.getName());
            Uri uri = Uri.parse(IMG_URL + realMeals.getFilePath().replace("~", ""));
            restImg.setImageURI(uri);
            retTitel.setText(realMeals.getName());
            Log.d("//Status", realMeals.getId()+"");

            //getMealsDetails
            new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
                @Override
                public void onDataReceived(Object o) {
                    description = (String) o;
                    if (description.equals("null"))
                        restDescription.setText(" ");
                    else restDescription.setText(description);
                    Log.i("Object", (String) o);
                    Log.i("description", description);
                }
            }).getMealsDetails(realMeals.getId());


        } else if (constant == 2) {
            realMarket = realm.where(Market.class).equalTo("type", 2).equalTo("Id", position).equalTo("isDeleted", false).findFirst();
            typeObj = realm.where(TypeList.class).equalTo("Id", realMarket.getIdTypeList()).equalTo("isDeleted", false).findFirst();

            restType.setText(typeObj.getName());
            Uri uri = Uri.parse(IMG_URL + realMarket.getFilePath().replace("~", ""));
            restImg.setImageURI(uri);
            retTitel.setText(realMarket.getName());
            Log.d("//Status", realMarket.getId()+"");

            //getMarketDetails
            new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
                @Override
                public void onDataReceived(Object o) {
                    description = (String) o;
                    if (description.equals("null"))
                        restDescription.setText(" ");
                    else restDescription.setText(description);
                }
            }).getMarketDetails(realMarket.getId());


        } else if (constant == 3) {
            realMarket = realm.where(Market.class).equalTo("type", 1).equalTo("Id", position).equalTo("isDeleted", false).findFirst();
            typeObj = realm.where(TypeList.class).equalTo("Id", realMarket.getIdTypeList()).equalTo("isDeleted", false).findFirst();

            restType.setText(typeObj.getName());
            Uri uri = Uri.parse(IMG_URL + realMarket.getFilePath().replace("~", ""));
            restImg.setImageURI(uri);
            retTitel.setText(realMarket.getName());
            Log.d("//Status", realMarket.getId()+"");

            //getMarketDetails
            new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
                @Override
                public void onDataReceived(Object o) {
                    description = (String) o;
                    if (description.equals("null"))
                        restDescription.setText(" ");
                    else restDescription.setText(description);
                }
            }).getMarketDetails(realMarket.getId());
        }
        return view;
    }
}
