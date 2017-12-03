package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import io.realm.Realm;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;
import static com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment.constant;


public class RestaurantFragment extends Fragment {
    private Realm realm;
    private ArrayList<Meals> meals;
    private int position;
    private ImageView restImg;
    private TextView restType;
    private TextView retTitel;
    private TextView restDescription;
    private ArrayList<TypeList> typeList;
    private TypeList typeObj = new TypeList();
    private String typeName;
    private Meals realMeals = new Meals();
    private Market realMarket = new Market();


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
        restImg = (ImageView) view.findViewById(R.id.rest_img);
        restType = (TextView) view.findViewById(R.id.rest_type);
        retTitel = (TextView) view.findViewById(R.id.ret_titel);
        restDescription = (TextView) view.findViewById(R.id.rest_description);

        Log.i("///**", position + " ");
        if (constant == 1) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realMeals = realm.where(Meals.class).equalTo("Id", position).findFirst();
                    Log.i("///", realMeals + " ");
                    typeObj = realm.where(TypeList.class).equalTo("Id", realMeals.getIdTypeList()).findFirst();
                    restType.setText(typeObj.getName());
                }
            });

            Glide.with(this).load(realMeals.getFilePath()).into(restImg);
            retTitel.setText(realMeals.getName());

            if (realMeals.getDescription().equals("null"))
                restDescription.setText(" ");
            else restDescription.setText(realMeals.getDescription());

        } else if (constant == 2) {
            realMarket = realm.where(Market.class).equalTo("type", 2).equalTo("Id", position).findFirst();

            typeObj = realm.where(TypeList.class).equalTo("Id", realMarket.getIdTypeList()).findFirst();
            restType.setText(typeObj.getName());

            Glide.with(this).load(realMarket.getFilePath()).into(restImg);
            retTitel.setText(realMarket.getName());
            if (realMarket.getDescription().equals("null"))
                restDescription.setText(" ");
            else restDescription.setText(realMarket.getDescription());
        } else if (constant == 3) {
            realMarket = realm.where(Market.class).equalTo("type", 1).equalTo("Id", position).findFirst();

            typeObj = realm.where(TypeList.class).equalTo("Id", realMarket.getIdTypeList()).findFirst();
            restType.setText(typeObj.getName());

            Glide.with(this).load(realMarket.getFilePath()).into(restImg);
            retTitel.setText(realMarket.getName());
            if (realMarket.getDescription().equals("null"))
                restDescription.setText(" ");
            else restDescription.setText(realMarket.getDescription());
        }
        return view;
    }
}
