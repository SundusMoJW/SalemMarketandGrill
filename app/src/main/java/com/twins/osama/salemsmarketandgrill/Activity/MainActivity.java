package com.twins.osama.salemsmarketandgrill.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.twins.osama.salemsmarketandgrill.Adapter.MyAdapter;
import com.twins.osama.salemsmarketandgrill.Fragment.BookEvent;
import com.twins.osama.salemsmarketandgrill.Fragment.CartFragment;
import com.twins.osama.salemsmarketandgrill.Fragment.ContactUs;
import com.twins.osama.salemsmarketandgrill.Fragment.GallaryFragment;
import com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment;
import com.twins.osama.salemsmarketandgrill.Fragment.Setting;
import com.twins.osama.salemsmarketandgrill.Helpar.DrawerItem;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Interface.OnDrawerItemClickListener;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<DrawerItem> items;
    private ArrayList<String> itemsTitles;
    public static int nav_back = 0;
    public static String NAME;
    public static String EMAILProfile;
    public static int PROFILE = R.drawable.blank_profile_picture;
    private Toolbar toolbar;
    public RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout Drawer;
    private Fragment fragment = null;
    private ActionBarDrawerToggle mDrawerToggle;
    private ImageView menu;
    private ImageView shopping;
    private TypedArray navMenuIcons;
    private TypedArray navMenuIcons_on;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private TextView tv_title;
    public static Context co;
    private ImageView search;
    private SharedPrefUtil sharedPref;

//       private OnBackPressedListener myBack = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Const.setLangSettings(this);
        setContentView(R.layout.activity_main);
        sharedPref = new SharedPrefUtil(getApplicationContext());
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.DrawerLayout));
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.drower_Item));
//myBack=new ;
        co=MainActivity.this;
        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fillData();
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);                 // Creating a layout Manager
        mFragmentManager = getSupportFragmentManager();
        mRecyclerView.setLayoutManager(mLayoutManager);


        mAdapter = new MyAdapter(this, items, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {

                Drawer.closeDrawer(mRecyclerView);
                startEvent(position);
                updateDrawer(position);
            }

            @Override
            public void onPhotoClick(View view) {
            }

            @Override
            public void onButtonClick(int postition) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }


        }; // Drawer Toggle Object Made
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        menu = (ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSlidingMenu();
            }
        });
        shopping = (ImageView) findViewById(R.id.shopping);
        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = new CartFragment();
                tv_title.setText(getResources().getText(R.string.cart));
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frame_layout, fragment);
                mFragmentTransaction.commit();
            }
        });
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchActivity = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(searchActivity);
            }
        });

        Drawer.setDrawerListener(mDrawerToggle);
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_layout, new HomeFragment()).commit();
    }

    private void fillData() {
        itemsTitles = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.drawerItems)));
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navMenuIcons_on = getResources().obtainTypedArray(R.array.nav_drawer_icons_on);
        items = new ArrayList<>();
        for (int i = 0; i < itemsTitles.size(); i++)
            items.add(new DrawerItem(itemsTitles.get(i), navMenuIcons.getResourceId(i, -1),
                    navMenuIcons_on.getResourceId(i, -1), i == 0 ? true : false));
        navMenuIcons.recycle();
    }

    public void updateDrawer(int position) {
        for (int i = 0; i < items.size(); i++) {
            items.get(i).setIsSelected(i == position ? true : false);
            Log.i("xcxc", position + "");
        }
        mAdapter.notifyDataSetChanged();
    }

    public void startEvent(int position) {

        switch (position) {
            case 0:
                nav_back = 0;
                fragment = new HomeFragment();
                tv_title.setText(getResources().getText(R.string.home));
                menu.setVisibility(View.VISIBLE);
                break;
            case 1:
                nav_back = 1;
                fragment = new CartFragment();
                tv_title.setText(getResources().getText(R.string.cart));
                //iv_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
                break;
            case 2:
                nav_back = 1;
                fragment = new GallaryFragment();
                tv_title.setText(getResources().getText(R.string.gallary));
                // iv_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
                break;
            case 3:
                nav_back = 1;
                fragment = new BookEvent();
                tv_title.setText(getResources().getText(R.string.book_event));
                //iv_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
                break;
            case 4:
                nav_back = 1;
                fragment = new Setting();
                tv_title.setText(getResources().getText(R.string.settings));
                //iv_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
                break;
            case 5:
                nav_back = 1;
                fragment = new ContactUs();
                tv_title.setText(getResources().getText(R.string.contact_us));
                //iv_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
                break;
            case 6:
                nav_back = 1;
                fragment = new HomeFragment();
                Logout();
                //iv_back.setVisibility(View.GONE);
                menu.setVisibility(View.VISIBLE);
                break;
        }

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.frame_layout, fragment);
        mFragmentTransaction.commit();
        mFragmentManager.executePendingTransactions();
        updateDrawer(position);
    }

    private void toggleSlidingMenu() {
        if (Drawer.isDrawerOpen(mRecyclerView)) {
            Drawer.closeDrawer(mRecyclerView);
            mAdapter.notifyDataSetChanged();
        } else {
            Drawer.openDrawer(mRecyclerView);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        if (nav_back != 0 && getFragmentManager().getBackStackEntryCount() <=0 ) {
            Log.i("////*", nav_back + "");
            fragment = new HomeFragment();
            nav_back = 0;
            tv_title.setText(getResources().getText(R.string.home));
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frame_layout, fragment);
            mFragmentTransaction.commit();
            return;
        }else {
            getFragmentManager().popBackStack();
        }
//           myBack.doBack();
        super.onBackPressed();
    }

    public void Logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(getApplicationContext());

                        sharedPrefUtil.clear();


                        //Starting login activity
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        finish();
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

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}


