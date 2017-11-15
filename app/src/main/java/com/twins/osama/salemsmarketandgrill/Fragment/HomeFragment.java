package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Adapter.HomeAdapter;
import com.twins.osama.salemsmarketandgrill.Adapter.MarketAdapter;
import com.twins.osama.salemsmarketandgrill.Adapter.SliderAdapter;
import com.twins.osama.salemsmarketandgrill.Classes.Category;
import com.twins.osama.salemsmarketandgrill.Classes.Market;
import com.twins.osama.salemsmarketandgrill.Classes.Meals;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Classes.TypeList;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.OnDrawerItemClickListener;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import io.realm.Realm;
import io.realm.RealmResults;

//import static com.facebook.FacebookSdk.getApplicationContext;


public class HomeFragment extends Fragment implements View.OnClickListener {
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
    //    public String filePth;
//    public String[] arrTitels = new String[]{};
//    public String[] arrImage = new String[]{};
    private ArrayList<Slider> list;
    private Realm realm;
    private ArrayList<Meals> meals;
    private  ArrayList<TypeList> typeList;
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
        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.coordinator));

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        mCoordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.coordinator);
        mViewPager = (ViewPager) view.findViewById(R.id.pagerhome);

        this.realm = RealmController.with(getActivity()).getRealm();
        RealmController.with(getActivity()).refresh();
        realm = Realm.getDefaultInstance();

        RealmResults<Slider> realmResults = realm.where(Slider.class).findAll();

        list = (ArrayList<Slider>) realm.copyFromRealm(realmResults);
//        Log.i("///", "///*" + "" + list.size());
        if (adapter == null)
            adapter = new SliderAdapter(getChildFragmentManager(), list);
//        Log.i("///", "///*" + "" + adapter.getCount());
        adapter.notifyDataSetChanged();
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(0);
//        adapter = new SliderAdapter(getChildFragmentManager(), ICONS,TITEL);
//        mViewPager = (ViewPager) view.findViewById(R.id.pager);
//        mViewPager.setAdapter(adapter);
//        mViewPager.setCurrentItem(0);
//        mViewPager.setOffscreenPageLimit(0);
//        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.indicator);cardView_home
//        indicator.setViewPager(mViewPager);
//
//        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == ICONS.length) {
//                    currentPage = 0;
//                }
//                mViewPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 3000, 3000);
        CirclePageIndicator indicator = (CirclePageIndicator)
                view.findViewById(R.id.indicator);

        indicator.setViewPager(mViewPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
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
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        menu_tab.setOnClickListener(this);
        grocery.setOnClickListener(this);
        meat.setOnClickListener(this);
        all_txt.setOnClickListener(this);

        RealmResults<TypeList> realmTypeListResults = realm.where(TypeList.class).findAll();
        typeList = (ArrayList<TypeList>) realm.copyFromRealm(realmTypeListResults);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        realmMealsResults = realm.where(Meals.class).findAll();
        meals = (ArrayList<Meals>) realm.copyFromRealm(realmMealsResults);
//        Log.i("///", realmMealsResults + " ");

        linearLayout.removeAllViews();
        if (typeList != null && typeList.size() > 0) {
            Category categoryAll = new Category(-1, "All");
            linearLayout.addView(getItem(categoryAll));
            for (int i = 0; i < typeList.size(); i++) {
                if (typeList.get(i).getIdType() == 2) {
                    linearLayout.addView(getItem(new Category(typeList.get(i).getId(), typeList.get(i).getName())));

                }
//                Log.i("///", typeList.get(i).getId() + " ");
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
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {

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
                realmMealsResults = realm.where(Meals.class).findAll();
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
                        mFragmentTransaction.replace(R.id.frame_layout, fragment);
                        mFragmentTransaction.commit();
                    }

                    @Override
                    public void onPhotoClick(View view) {

                    }

                    @Override
                    public void onButtonClick(int position) {

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
                realmMeatsResults = realm.where(Market.class).equalTo("type", 2).findAll();
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
                        mFragmentTransaction.replace(R.id.frame_layout, fragment);
                        mFragmentTransaction.commit();
                    }

                    @Override
                    public void onPhotoClick(View view) {

                    }

                    @Override
                    public void onButtonClick(int position) {

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
//                for (int i = 0; i < typeList.size(); i++) {
//                    if (typeList.get(i).getIdType() == 7) {
//                        TextView tv = new TextView(getContext());
//                        typeList.get(i).getName();
//                        tv.setTextColor(Color.parseColor("#ef5438"));
//                        tv.setTextSize(14);
//                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                        llp.setMargins(10, 10, 10, 10); // llp.setMargins(left, top, right, bottom);
//                        tv.setGravity(Gravity.CENTER);
//                        tv.setLayoutParams(llp);
//                        tv.setText(typeList.get(i).getName());
//                        linearLayout.addView(tv);
//                        TypefaceUtil.applyFont(getContext(), tv);
//                    }
//                }
                break;
            case R.id.grocery:
                constant = 3;
                linearLayout.removeAllViews();
                realmMeatsResults = realm.where(Market.class).equalTo("type", 1).findAll();
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
                        mFragmentTransaction.replace(R.id.frame_layout, fragment);
                        mFragmentTransaction.commit();
                    }

                    @Override
                    public void onPhotoClick(View view) {

                    }

                    @Override
                    public void onButtonClick(int position) {

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
                }
            }
        });
        return ll;
    }

    public void getMealItem(final int id) {

        if (id == -1) {
            realmMealsResults = realm.where(Meals.class).findAll();
            meals = (ArrayList<Meals>) realm.copyFromRealm(realmMealsResults);
//            Log.i("///", realmMealsResults + " ");
        } else {
//            Log.i("///",  "****** ");

            realm.executeTransaction(new Realm.Transaction() {
                public ArrayList<Meals> mealsResults = new ArrayList<Meals>();

                @Override
                public void execute(Realm realm) {

                    realmMealsResults = realm.where(Meals.class).equalTo("IdTypeList", id).findAll();
                    meals = (ArrayList<Meals>) realm.copyFromRealm(realmMealsResults);
//                    Log.i("///*",meals+ " ");
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
//                Log.i("///*",  mealsArrayList.get(position).getId() + " ");
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {

            }
        });
        recyclerView.setAdapter(melsadapter);
        melsadapter.notifyDataSetChanged();
    }

    public void getMeatList(final int id) {

        if (id == -1) {
            realmMeatsResults = realm.where(Market.class).equalTo("type", 2).findAll();
            meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
//            Log.i("///", realmMealsResults + " ");
        } else {
//            Log.i("///",  "****** ");

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmMeatsResults = realm.where(Market.class).equalTo("type", 2).equalTo("IdTypeList", id).findAll();
                    meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
//                    Log.i("///*",meals+ " ");
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
//                Log.i("///*",  mealsArrayList.get(position).getId() + " ");
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {

            }
        });
        recyclerView.setAdapter(marketAdapter);
        melsadapter.notifyDataSetChanged();
    }

    public void getGroceryList(final int id) {

        if (id == -1) {
            realmMeatsResults = realm.where(Market.class).equalTo("type", 1).findAll();
            meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
//            Log.i("///", realmMealsResults + " ");
        } else {
//            Log.i("///",  "****** ");

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realmMeatsResults = realm.where(Market.class).equalTo("type", 1).equalTo("IdTypeList", id).findAll();
                    meatsArrayList = (ArrayList<Market>) realm.copyFromRealm(realmMeatsResults);
//                    Log.i("///*",meals+ " ");
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
//                Log.i("///*",  mealsArrayList.get(position).getId() + " ");
                fragment.setArguments(bundle);
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }

            @Override
            public void onPhotoClick(View view) {

            }

            @Override
            public void onButtonClick(int position) {

            }
        });
        recyclerView.setAdapter(marketAdapter);
        marketAdapter.notifyDataSetChanged();
    }
}
