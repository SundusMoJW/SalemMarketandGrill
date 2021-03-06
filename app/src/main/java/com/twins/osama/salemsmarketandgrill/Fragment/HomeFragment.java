package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Adapter.EventsAdapter;
import com.twins.osama.salemsmarketandgrill.Adapter.HomeAdapter;
import com.twins.osama.salemsmarketandgrill.Adapter.MarketAdapter;
import com.twins.osama.salemsmarketandgrill.Adapter.NewsAdapter;
import com.twins.osama.salemsmarketandgrill.Adapter.SliderAdapter;
import com.twins.osama.salemsmarketandgrill.Classes.CartItem;
import com.twins.osama.salemsmarketandgrill.Classes.Category;
import com.twins.osama.salemsmarketandgrill.Classes.Events;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.News;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Interface.OnDrawerItemClickListener;
import com.twins.osama.salemsmarketandgrill.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private SliderAdapter adapter;
    public static boolean isSlider = false;
    private ViewPager mViewPager;
    private static int currentPage = 0;
    //    ArrayList<HomeRecycleViewTemplate> data;
    private int img = R.drawable.crop3;
    private int NUM_PAGES;
    public static int constant = 1;
    private FragmentTransaction mFragmentTransaction;
    private CoordinatorLayout mCoordinatorLayout;
    private ArrayList<Slider> list;
    private Realm realm;
    private ArrayList<Meals> meals;
    private ArrayList<TypeList> typeList;
    private LinearLayout linearLayout;
    private TextView menu_tab;
    private TextView grocery;
    private TextView meat;
    private TextView all_txt;
    private RecyclerView recyclerView;
    private TextView txt;
    private LinearLayout parentPanel1;
    private HomeAdapter melsadapter;
    private ArrayList<Meals> groceryArrayList;
    private RealmResults<Meals> realmMealsResults;
    private ArrayList<Market> meatsArrayList;
    private RealmResults<Market> realmMeatsResults;
    private MarketAdapter marketAdapter;
    private ImageView more;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
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
        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.coordinator));
        nav_back=1;
        this.realm = RealmController.with(getActivity()).getRealm();
        RealmController.with(getActivity()).refresh();
        realm = Realm.getDefaultInstance();

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
        if (cartItems != null) {
            ArrayList<CartItem> cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
            ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + "");
        }
        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator);
        mViewPager = (ViewPager) view.findViewById(R.id.pagerhome);

        RealmResults<Slider> realmResults = realm.where(Slider.class).equalTo("isDeleted", false).findAll();

        list = (ArrayList<Slider>) realm.copyFromRealm(realmResults);
//        Log.i("///", "///*" + "" + list.size());
        if (adapter == null)
            adapter = new SliderAdapter(getChildFragmentManager(), list);
//        Log.i("///", "///*" + "" + adapter.getCount());
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(0);

        CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);

        indicator.setViewPager(mViewPager);

        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(4 * density);
        NUM_PAGES = list.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });
        linearLayout = (LinearLayout) view.findViewById(R.id.addView);
        menu_tab = (TextView) view.findViewById(R.id.menu_tab);
        grocery = (TextView) view.findViewById(R.id.grocery);
        meat = (TextView) view.findViewById(R.id.meat);
        all_txt = (TextView) view.findViewById(R.id.all_txt);
        more = view.findViewById(R.id.more);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        menu_tab.setOnClickListener(this);
        grocery.setOnClickListener(this);
        meat.setOnClickListener(this);
        all_txt.setOnClickListener(this);
        more.setOnClickListener(this);

        RealmResults<TypeList> realmTypeListResults = realm.where(TypeList.class).equalTo("isDeleted", false).findAll();
        typeList = (ArrayList<TypeList>) realm.copyFromRealm(realmTypeListResults);

        realmMealsResults = realm.where(Meals.class).equalTo("isDeleted", false).findAll();
        meals = (ArrayList<Meals>) realm.copyFromRealm(realmMealsResults);
        linearLayout.removeAllViews();
        if (typeList != null && typeList.size() > 0) {
            Category categoryAll = new Category(-1, "All");
            linearLayout.addView(getItem(categoryAll));
            for (int i = 0; i < typeList.size(); i++) {
                if (typeList.get(i).getIdType() == 2) {
                    linearLayout.addView(getItem(new Category(typeList.get(i).getId(), typeList.get(i).getName())));
                }
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        melsadapter = new HomeAdapter(getActivity(), meals, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {
                RestaurantFragment fragment = new RestaurantFragment();
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", meals.get(position).getId());
                Log.i("///*", meals.get(position).getId() + " ");
                fragment.setArguments(bundle);
                mFragmentTransaction.add(R.id.frame_layout, fragment);
                mFragmentTransaction.addToBackStack(null);
                //add to back stake
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {
                int idTypeList = meals.get(position).getIdTypeList();
                int id = meals.get(position).getId();
                RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                if (cartItem == null) {
                    ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                    CartItem putData = new CartItem(meals.get(position).getName(), meals.get(position).getId()
                            , meals.get(position).getFilePath(), meals.get(position).getIdTypeList()
                            , 1, meals.get(position).getPrice(), false, null, meals.get(position).getListAdditions());
                    RealmController.with(getActivity()).putInCartItem(putData);
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                } else {
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(melsadapter);
        melsadapter.notifyDataSetChanged();

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.menu_tab:
                constant = 1;
                linearLayout.removeAllViews();
                realmMealsResults = realm.where(Meals.class).equalTo("isDeleted", false).findAll();
                meals = (ArrayList<Meals>) realm.copyFromRealm(realmMealsResults);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                melsadapter = new HomeAdapter(getActivity(), meals, new OnDrawerItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        RestaurantFragment fragment = new RestaurantFragment();
                        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", meals.get(position).getId());
                        Log.i("///*", meals.get(position).getId() + " ");
                        fragment.setArguments(bundle);
                        mFragmentTransaction.add(R.id.frame_layout, fragment);
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.commit();
                    }

                    @Override
                    public void onPhotoClick(View view) {

                    }

                    @Override
                    public void onButtonClick(int position) {
                        int idTypeList = realmMealsResults.get(position).getIdTypeList();
                        int id = realmMealsResults.get(position).getId();
                        RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                        ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                        CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                        if (cartItem == null) {
                            ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                            CartItem putData = new CartItem(meals.get(position).getName(), meals.get(position).getId()
                                    , meals.get(position).getFilePath(), meals.get(position).getIdTypeList()
                                    , 1, meals.get(position).getPrice(), false, null, meals.get(position).getListAdditions());
                            RealmController.with(getActivity()).putInCartItem(putData);
                            if (getView() != null)
                                Snackbar.make(mCoordinatorLayout, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                        } else {
                            if (getView() != null)
                                Snackbar.make(mCoordinatorLayout, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
                recyclerView.setAdapter(melsadapter);
                melsadapter.notifyDataSetChanged();
                if (typeList != null && typeList.size() > 0) {
                    Category categoryAll = new Category(-1, "All");
                    linearLayout.addView(getItem(categoryAll));
                    for (int i = 0; i < typeList.size(); i++) {
                        if (typeList.get(i).getIdType() == 2) {
                            linearLayout.addView(getItem(new Category(typeList.get(i).getId(), typeList.get(i).getName())));
                        }
                        Log.i("///", typeList.get(i).getId() + " ");
                    }
                }
                break;
            case R.id.meat:
                constant = 2;
                linearLayout.removeAllViews();
                realmMeatsResults = realm.where(Market.class).equalTo("type", 2).equalTo("isDeleted", false).findAll();
                meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                marketAdapter = new MarketAdapter(getActivity(), meatsArrayList, new OnDrawerItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        RestaurantFragment fragment = new RestaurantFragment();
                        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", meatsArrayList.get(position).getId());
                        fragment.setArguments(bundle);
                        mFragmentTransaction.add(R.id.frame_layout, fragment);
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.commit();
                    }

                    @Override
                    public void onPhotoClick(View view) {

                    }

                    @Override
                    public void onButtonClick(int position) {
                        int idTypeList = meatsArrayList.get(position).getIdTypeList();
                        int id = meatsArrayList.get(position).getId();
                        RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                        ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                        CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                        if (cartItem == null) {
                            ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                            CartItem putData = new CartItem(meatsArrayList.get(position).getName(), meatsArrayList.get(position).getId()
                                    , meatsArrayList.get(position).getFilePath(), meatsArrayList.get(position).getIdTypeList()
                                    , 1, meatsArrayList.get(position).getPrice(), false, meatsArrayList.get(position).getListAdditions(), null);
                            RealmController.with(getActivity()).putInCartItem(putData);
                            if (getView() != null)
                                Snackbar.make(mCoordinatorLayout, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                        } else {
                            if (getView() != null)
                                Snackbar.make(mCoordinatorLayout, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
                recyclerView.setAdapter(marketAdapter);
                melsadapter.notifyDataSetChanged();
                if (typeList != null && typeList.size() > 0) {
                    Category categoryAll = new Category(-1, "All");
                    linearLayout.addView(getItem(categoryAll));
                    for (int i = 0; i < typeList.size(); i++) {
                        if (typeList.get(i).getIdType() == 7) {
                            linearLayout.addView(getItem(new Category(typeList.get(i).getId(), typeList.get(i).getName())));
                        }
                        Log.i("///", typeList.get(i).getId() + " ");
                    }
                }

                break;
            case R.id.grocery:
                constant = 3;
                linearLayout.removeAllViews();
                realmMeatsResults = realm.where(Market.class).equalTo("type", 1).equalTo("isDeleted", false).findAll();
                meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                marketAdapter = new MarketAdapter(getActivity(), meatsArrayList, new OnDrawerItemClickListener() {
                    @Override
                    public void onClick(int position) {
                        RestaurantFragment fragment = new RestaurantFragment();
                        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                        mFragmentTransaction = mFragmentManager.beginTransaction();
                        Bundle bundle = new Bundle();
                        bundle.putInt("position", meatsArrayList.get(position).getId());
//                Log.i("///*",  mealsArrayList.get(position).getId() + " ");
                        fragment.setArguments(bundle);
                        mFragmentTransaction.add(R.id.frame_layout, fragment);
                        mFragmentTransaction.addToBackStack(null);
                        mFragmentTransaction.commit();
                    }

                    @Override
                    public void onPhotoClick(View view) {

                    }

                    @Override
                    public void onButtonClick(int position) {
                        int idTypeList = meatsArrayList.get(position).getIdTypeList();
                        int id = meatsArrayList.get(position).getId();
                        RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                        ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                        CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                        if (cartItem == null) {
                            ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                            CartItem putData = new CartItem(meatsArrayList.get(position).getName(), meatsArrayList.get(position).getId()
                                    , meatsArrayList.get(position).getFilePath(), meatsArrayList.get(position).getIdTypeList()
                                    , 1, meatsArrayList.get(position).getPrice(), false, meatsArrayList.get(position).getListAdditions(), null);
                            RealmController.with(getActivity()).putInCartItem(putData);
                            if (getView() != null)
                                Snackbar.make(mCoordinatorLayout, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                        } else {
                            if (getView() != null)
                                Snackbar.make(mCoordinatorLayout, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();
                        }
                    }
                });
                recyclerView.setAdapter(marketAdapter);
                melsadapter.notifyDataSetChanged();
                if (typeList != null && typeList.size() > 0) {
                    Category categoryAll = new Category(-1, "All");
                    linearLayout.addView(getItem(categoryAll));
                    for (int i = 0; i < typeList.size(); i++) {
                        if (typeList.get(i).getIdType() == 6) {
                            linearLayout.addView(getItem(new Category(typeList.get(i).getId(), typeList.get(i).getName())));

                        }
                        Log.i("///", typeList.get(i).getId() + " ");
                    }
                }

                break;
            case R.id.more:
                constant = 4;
                linearLayout.removeAllViews();
                Category categoryAll = new Category(1, "News");
                linearLayout.addView(getItem(categoryAll));
                Category inside = new Category(2, "Inside Event");
                linearLayout.addView(getItem(inside));
                Category outside = new Category(3, "Outside Event");
                linearLayout.addView(getItem(outside));
                /*****  ne adapter    ********/

                ArrayList<News> news = RealmController.with(getActivity()).getNewsList();
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                NewsAdapter newsAdapter = new NewsAdapter(getActivity(), news);
                recyclerView.setAdapter(newsAdapter);
                newsAdapter.notifyDataSetChanged();
                break;
        }
    }

    private LinearLayout getItem(final Category item) {
        final LinearLayout ll = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.add_many_text, null);
        txt = (TextView) ll.findViewById(R.id.txt);
        parentPanel1 = (LinearLayout) ll.findViewById(R.id.parentPanell);
        TypefaceUtil.applyFont(getContext(), parentPanel1);

        // CommentsClass ptc = items.get(position);
        txt.setText(item.getName());
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (constant == 1) {
                    getMealItem(item.getId());
                } else if (constant == 2) {
                    getMeatList(item.getId());
                } else if (constant == 3) {
                    getGroceryList(item.getId());
                } else if (constant == 4) {
                    getMore(item.getId());
                }
            }
        });
        return ll;
    }

    private void getMealItem(final int id) {

        if (id == -1) {
            realmMealsResults = realm.where(Meals.class).equalTo("isDeleted", false).findAll();
            meals = (ArrayList<Meals>) realm.copyFromRealm(realmMealsResults);
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                public ArrayList<Meals> mealsResults = new ArrayList<Meals>();

                @Override
                public void execute(Realm realm) {

                    realmMealsResults = realm.where(Meals.class).equalTo("IdTypeList", id).equalTo("isDeleted", false).findAll();
                    meals = (ArrayList<Meals>) realm.copyFromRealm(realmMealsResults);
                }
            });
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        melsadapter = new HomeAdapter(getActivity(), meals, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {
                RestaurantFragment fragment = new RestaurantFragment();
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", meals.get(position).getId());
                fragment.setArguments(bundle);
                mFragmentTransaction.add(R.id.frame_layout, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {
                int idTypeList = meals.get(position).getIdTypeList();
                int id = meals.get(position).getId();
                RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                if (cartItem == null) {
                    ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                    CartItem putData = new CartItem(meals.get(position).getName(), meals.get(position).getId()
                            , meals.get(position).getFilePath(), meals.get(position).getIdTypeList()
                            , 1, meals.get(position).getPrice(), false, null, meals.get(position).getListAdditions());
                    RealmController.with(getActivity()).putInCartItem(putData);
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                } else {
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();

                }
            }
        });
        recyclerView.setAdapter(melsadapter);
        melsadapter.notifyDataSetChanged();
    }

    private void getMeatList(final int id) {

        if (id == -1) {
            realmMeatsResults = realm.where(Market.class).equalTo("type", 2).equalTo("isDeleted", false).findAll();
            meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmMeatsResults = realm.where(Market.class).equalTo("type", 2).equalTo("IdTypeList", id).equalTo("isDeleted", false).findAll();
                    meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
                }
            });
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        marketAdapter = new MarketAdapter(getActivity(), meatsArrayList, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {
                RestaurantFragment fragment = new RestaurantFragment();
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", meatsArrayList.get(position).getId());
                fragment.setArguments(bundle);
                mFragmentTransaction.add(R.id.frame_layout, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {
                int idTypeList = meatsArrayList.get(position).getIdTypeList();
                int id = meatsArrayList.get(position).getId();
                RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                if (cartItem == null) {
                    ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                    CartItem putData = new CartItem(meatsArrayList.get(position).getName(), meatsArrayList.get(position).getId()
                            , realmMeatsResults.get(position).getFilePath(), meatsArrayList.get(position).getIdTypeList()
                            , 1, meatsArrayList.get(position).getPrice(), false, meatsArrayList.get(position).getListAdditions(), null);
                    RealmController.with(getActivity()).putInCartItem(putData);
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                } else {
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(marketAdapter);
        melsadapter.notifyDataSetChanged();
    }

    private void getGroceryList(final int id) {

        if (id == -1) {
            realmMeatsResults = realm.where(Market.class).equalTo("type", 1).equalTo("isDeleted", false).findAll();
            meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmMeatsResults = realm.where(Market.class).equalTo("type", 1).equalTo("IdTypeList", id).equalTo("isDeleted", false).findAll();
                    meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
                }
            });
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        marketAdapter = new MarketAdapter(getActivity(), meatsArrayList, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {
                RestaurantFragment fragment = new RestaurantFragment();
                FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("position", meatsArrayList.get(position).getId());
                fragment.setArguments(bundle);
                mFragmentTransaction.add(R.id.frame_layout, fragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {
                int idTypeList = meatsArrayList.get(position).getIdTypeList();
                int id = meatsArrayList.get(position).getId();

                Log.d("//Id Button :", id + "   idType:" + idTypeList);

                RealmResults cartItems = RealmController.with(getActivity()).getCartItems();
                ArrayList cartItemsArr = (ArrayList<CartItem>) realm.copyFromRealm(cartItems);
                CartItem cartItem = RealmController.with(getActivity()).cheackCartItem(id, idTypeList);
                if (cartItem == null) {
                    ((TextView) getActivity().findViewById(R.id.adding_to_cart)).setText(cartItemsArr.size() + 1 + "");
                    CartItem putData = new CartItem(meatsArrayList.get(position).getName(), meatsArrayList.get(position).getId()
                            , meatsArrayList.get(position).getFilePath(), meatsArrayList.get(position).getIdTypeList()
                            , 1, meatsArrayList.get(position).getPrice(), false, meatsArrayList.get(position).getListAdditions(), null);
                    RealmController.with(getActivity()).putInCartItem(putData);
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.addToCart), Snackbar.LENGTH_SHORT).show();
                } else {
                    if (getView() != null)
                        Snackbar.make(mCoordinatorLayout, getString(R.string.beforeAddToCart), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        recyclerView.setAdapter(marketAdapter);
        marketAdapter.notifyDataSetChanged();
    }

    private void getMore(final int id) {
        if (id == 1) {
            ArrayList<News> news = RealmController.with(getActivity()).getNewsList();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            NewsAdapter newsAdapter = new NewsAdapter(getActivity(), news);
            recyclerView.setAdapter(newsAdapter);
            newsAdapter.notifyDataSetChanged();
        } else if (id == 2) {
            ArrayList<Events> events = RealmController.with(getActivity()).getInEventsList();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            EventsAdapter eventsAdapter = new EventsAdapter(getActivity(), events);
            recyclerView.setAdapter(eventsAdapter);
            eventsAdapter.notifyDataSetChanged();
        } else if (id == 3) {
            ArrayList<Events> events = RealmController.with(getActivity()).getOutEventsList();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            EventsAdapter eventsAdapter = new EventsAdapter(getActivity(), events);
            recyclerView.setAdapter(eventsAdapter);
            eventsAdapter.notifyDataSetChanged();
        }
    }


}
