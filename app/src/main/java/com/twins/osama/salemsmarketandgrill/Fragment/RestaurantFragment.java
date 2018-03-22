package com.twins.osama.salemsmarketandgrill.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.MarketAdditionsAPI;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.MealsAdditionsAPI;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.VolleyRequests;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;
import static com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment.constant;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;


public class RestaurantFragment extends Fragment {
    private Realm realm;
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
    private TextView addToCart;
    private LinearLayout restaurant;
    private CallbackManager callbackManager;
    private LoginManager manager;
    private ImageView facebook;

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
        addToCart = view.findViewById(R.id.add_to_cart);
        restaurant=view.findViewById(R.id.restaurant);
        facebook=view.findViewById(R.id.facebook);

        Log.i("///**", position + " ");
        if (constant == 1) {
            /*****getData*******/
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realMeals = realm.where(Meals.class).equalTo("Id", position).equalTo("isDeleted", false).findFirst();
                    Log.i("///", realMeals + " ");
                    typeObj = realm.where(TypeList.class).equalTo("Id", realMeals.getIdTypeList()).equalTo("isDeleted", false).findFirst();
                    Log.i("/////", typeObj + " ");
                }
            });

            restType.setText(typeObj.getName());
            Uri uri = Uri.parse(IMG_URL + realMeals.getFilePath().replace("~", ""));
            restImg.setImageURI(uri);
            retTitel.setText(realMeals.getName());
            Log.d("//Status", realMeals.getId() + "");

            /********getItem*********/
            RealmList<MealsAdditionsAPI> mealsAdditionsAPIS = realMeals.getListAdditions();
            if (mealsAdditionsAPIS != null) {
                for (int i = 0; i < mealsAdditionsAPIS.size(); i++) {
                    getItemMeals(mealsAdditionsAPIS.get(i), i, realMeals.getId(), realMeals.getIdTypeList());
                }
            }
            /******getMealsDetails*****/
            new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
                @Override
                public void onDataReceived(Object o) {
                    description = (String) o;
                    if (description.equals("null"))
                        restDescription.setText("");
                    else restDescription.setText(description);
                    Log.i("Object", (String) o);
                    Log.i("description", description);
                }
            }).getMealsDetails(realMeals.getId());


        } else if (constant == 2) {
            /*****getData*******/
            realMarket = realm.where(Market.class).equalTo("type", 2).equalTo("Id", position).equalTo("isDeleted", false).findFirst();
            typeObj = realm.where(TypeList.class).equalTo("Id", realMarket.getIdTypeList()).equalTo("isDeleted", false).findFirst();

            restType.setText(typeObj.getName());
            Uri uri = Uri.parse(IMG_URL + realMarket.getFilePath().replace("~", ""));
            restImg.setImageURI(uri);
            retTitel.setText(realMarket.getName());
            Log.d("//Status", realMarket.getId() + "");
            /********getItem*********/
            RealmList<MarketAdditionsAPI> marketAdditionsAPIS = realMarket.getListAdditions();
            if (marketAdditionsAPIS != null) {
                for (int i = 0; i < marketAdditionsAPIS.size(); i++) {
                    getItemMarket(marketAdditionsAPIS.get(i), i, realMarket.getId(), realMarket.getIdTypeList());
                }
            }
            /*****getMarketDetails*****/
            new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
                @Override
                public void onDataReceived(Object o) {
                    description = (String) o;
                    if (description.equals("null"))
                        restDescription.setText("");
                    else restDescription.setText(description);
                }
            }).getMarketDetails(realMarket.getId());


        } else if (constant == 3) {
            /*****getData*******/
            realMarket = realm.where(Market.class).equalTo("type", 1).equalTo("Id", position).equalTo("isDeleted", false).findFirst();
            typeObj = realm.where(TypeList.class).equalTo("Id", realMarket.getIdTypeList()).equalTo("isDeleted", false).findFirst();

            restType.setText(typeObj.getName());
            Uri uri = Uri.parse(IMG_URL + realMarket.getFilePath().replace("~", ""));
            restImg.setImageURI(uri);
            retTitel.setText(realMarket.getName());
            Log.d("//Status", realMarket.getId() + "");
            /********getItem*********/
            RealmList<MarketAdditionsAPI> marketAdditionsAPIS = realMarket.getListAdditions();
            if (marketAdditionsAPIS != null) {
                for (int i = 0; i < marketAdditionsAPIS.size(); i++) {
                    getItemMarket(marketAdditionsAPIS.get(i), i, realMarket.getId(), realMarket.getIdTypeList());
                }
            }
            /*****getMarketDetails*****/
            //getMarketDetails
            new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
                @Override
                public void onDataReceived(Object o) {
                    description = (String) o;
                    if (description.equals("null"))
                        restDescription.setText("");
                    else restDescription.setText(description);
                }
            }).getMarketDetails(realMarket.getId());
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (constant == 1) {
                    int idTypeList = realMeals.getIdTypeList();
                    int id = realMeals.getId();
                    RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                    ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                    CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                    if (cartItem == null) {
                        ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                        CartItem putData = new CartItem(realMeals.getName(), realMeals.getId()
                                , realMeals.getFilePath(), realMeals.getIdTypeList()
                                , 1, realMeals.getPrice(),false, null, realMeals.getListAdditions());
                        RealmController.with(getActivity()).putInCartItem(putData);
                        if (getView() != null)
                            Snackbar.make(restaurant, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                    } else {
                        if (getView() != null)
                            Snackbar.make(restaurant, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();
                    }
                } else {
                    int idTypeList = realMarket.getIdTypeList();
                    int id = realMarket.getId();
                    RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                    ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                    CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                    if (cartItem == null) {
                        ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                        CartItem putData = new CartItem(realMarket.getName(), realMarket.getId()
                                , realMarket.getFilePath(), realMarket.getIdTypeList()
                                , 1, realMarket.getPrice(), false,realMarket.getListAdditions(), null);
                        RealmController.with(getActivity()).putInCartItem(putData);
                        if (getView() != null)
                            Snackbar.make(restaurant, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                    } else {
                        if (getView() != null)
                            Snackbar.make(restaurant, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();

                    }
                }
            }
        });


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FacebookSdk.sdkInitialize(getApplicationContext());

                callbackManager = CallbackManager.Factory.create();

                List<String> permissionNeeds = Arrays.asList("publish_actions");

                //this loginManager helps you eliminate adding a LoginButton to your UI
                manager = LoginManager.getInstance();

                manager.logInWithPublishPermissions(getActivity(), permissionNeeds);

                manager.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
                {
                    @Override
                    public void onSuccess(LoginResult loginResult)
                    {
                        sharePhotoToFacebook();
                    }

                    @Override
                    public void onCancel()
                    {
                        System.out.println("onCancel");
                    }

                    @Override
                    public void onError(FacebookException exception)
                    {
                        System.out.println("onError");
                    }
                });
            }
        });

        return view;
    }

    private LinearLayout getItemMeals(final MealsAdditionsAPI mealsAdditionsAPI, int i, int id, int idTypeList) {
        final LinearLayout addition_item = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.addition_item, null);
        CheckBox checkBox = addition_item.findViewById(R.id.checkBox);
        LinearLayout parentPanel1 = (LinearLayout) addition_item.findViewById(R.id.parentPanell);
        TypefaceUtil.applyFont(getContext(), parentPanel1);
        checkBox.setText(mealsAdditionsAPI.getAdditionsText());
        CartItem item = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
        if (item != null) {
            checkBox.setChecked(item.getMealListAdditions().get(position).isSelect());
        } else checkBox.setChecked(mealsAdditionsAPI.isSelect());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    mealsAdditionsAPI.setSelect(false);

                }
            }
        });

        return addition_item;
    }

    private LinearLayout getItemMarket(final MarketAdditionsAPI marketAdditionsAPI, int i, int id, int idTypeList) {
        final LinearLayout addition_item = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.addition_item, null);
        CheckBox checkBox = addition_item.findViewById(R.id.checkBox);
        LinearLayout parentPanel1 = (LinearLayout) addition_item.findViewById(R.id.parentPanell);
        TypefaceUtil.applyFont(getContext(), parentPanel1);
        checkBox.setText(marketAdditionsAPI.getAdditionsText());
        CartItem item = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
        if (item != null) {
            checkBox.setChecked(item.getMealListAdditions().get(position).isSelect());
        } else checkBox.setChecked(marketAdditionsAPI.isSelect());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!b) {
                    marketAdditionsAPI.setSelect(false);

                }
            }
        });

        return addition_item;
    }

    private void sharePhotoToFacebook(){
//        Bitmap image = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
//        SharePhoto photo = new SharePhoto.Builder()
//                .setImageUrl(image)
//                .setCaption("Give me my codez or I will ... you know, do that thing you don't like!")
//                .build();
//
//        SharePhotoContent content = new SharePhotoContent.Builder()
//                .addPhoto(photo)
//                .build();
//
//        ShareApi.share(content, null);

    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data)
    {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }

}

