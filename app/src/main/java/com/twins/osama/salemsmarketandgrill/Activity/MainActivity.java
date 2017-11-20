package com.twins.osama.salemsmarketandgrill.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.DrawerItem;
import com.twins.osama.salemsmarketandgrill.Helpar.OnDrawerItemClickListener;
import com.twins.osama.salemsmarketandgrill.Helpar.SharedPrefUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static com.twins.osama.salemsmarketandgrill.Helpar.Const.EMAIL_SHARED_PREF;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMAGE_PROFILE;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.USER_NAME_SHARED_PREF;

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
    private Context co ;
    private ImageView search;
    private static int RESULT_LOAD_IMAGE = 1;
    private ImageView mImage;
    private SharedPrefUtil sharedPref;
    private String imageProfile;
    private FileOutputStream outStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Const.setLangSettings(this);
        setContentView(R.layout.activity_main);
        sharedPref = new SharedPrefUtil(getApplicationContext());
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.DrawerLayout));
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.drower_Item));

        tv_title = (TextView) findViewById(R.id.tv_title);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fillData();
        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);                 // Creating a layout Manager
        mFragmentManager = getSupportFragmentManager();
        mImage = (ImageView) findViewById(R.id.circleView);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Setting the layout Manager
        // Bitmap bitmap=Const.decodeSampledBitmapFromResource(picturePath,300,300);
//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.blank_profile_picture);
//        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//        File file = new File(extStorageDirectory, "blank_profile_picture.PNG");
//        try {
//            outStream = new FileOutputStream(file);
//            outStream.flush();
//            outStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        // imageProfile= sharedPref.getString(IMAGE_PROFILE);
//            String value = sharedPref.getString(IMAGE_PROFILE,null);
        //   ImageView imageView = (ImageView) findViewById(R.id.circleView);
        //  if (!(value==null) ){
//            Bitmap bitmap = Const.decodeSampledBitmapFromResource(imageProfile, 300, 300);
        //    imageView.setImageBitmap(bitmap);
        // PROFILE = new BitmapDrawable(getResources(), bitmap);
//        }else {
//            sharedPref.saveString(IMAGE_PROFILE,extStorageDirectory);
//           imageProfile= sharedPref.getString(IMAGE_PROFILE);
//            Bitmap bitmap = Const.decodeSampledBitmapFromResource(imageProfile, 300, 300);
//            //    imageView.setImageBitmap(bitmap);
//            PROFILE = new BitmapDrawable(getResources(), bitmap);
//        }
        //  Drawable drawable = new BitmapDrawable(getResources(), bitmap);
        //  PROFILE = ContextCompat.getDrawable(getApplicationContext(),R.drawable.blank_profile_picture);
//                    getResources().getDrawable(R.drawable.blank_profile_picture);

        NAME = sharedPref.getString(USER_NAME_SHARED_PREF);
        EMAILProfile = sharedPref.getString(EMAIL_SHARED_PREF);


        mAdapter = new MyAdapter(this, items, new OnDrawerItemClickListener() {
            @Override
            public void onClick(int position) {

                Drawer.closeDrawer(mRecyclerView);
                startEvent(position);
                updateDrawer(position);
            }

            @Override
            public void onPhotoClick(View view) {
//مثلا افتح ال gallary واختار من الصور وحطلي اياها بدل ال view الحالية بأحطها عند تغيير صورة البروفايل مثلا

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
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

//        searchView = (ImageView) findViewById(R.id.search);
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                handleMenuSearch();
//                //tv_title.setVisibility(View.GONE);
//            }
//        });

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


        if (nav_back != 0) {
            Log.i("////*", nav_back + "");
            fragment = new HomeFragment();
            nav_back = 0;
            tv_title.setText(getResources().getText(R.string.home));
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.frame_layout, fragment);
            mFragmentTransaction.commit();
            return;
        }
        super.onBackPressed();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Bitmap bitmap = Const.decodeSampledBitmapFromResource(picturePath, 300, 300);
            sharedPref.saveString(IMAGE_PROFILE, picturePath);
            ImageView imageView = (ImageView) findViewById(R.id.circleView);
            imageView.setImageBitmap(bitmap);
        }
    }
//            imageView.setImageBitmap(bmp);
//            SharedPrefUtil sharedPrefUtil = new SharedPrefUtil(getApplicationContext());
//            sharedPrefUtil.saveString(IMAGE_VIEW,bmp+"" );
//        }
//        if (resultCode == RESULT_OK) {
//            if (requestCode == RESULT_LOAD_IMAGE) {
//
//                //Get ImageURi and load with help of picasso
//                Uri selectedImageURI = data.getData();
//             //  imagePath = getRealPathFromURI(this, selectedImageURI);
//
//                Picasso.with(MainActivity.this).load(imagePath).noPlaceholder().error(R.drawable.crop).centerCrop().fit() .resize(100, 100)
//                        .into((ImageView) findViewById(R.id.circleView));
//            }
//
//        }
    //    Uri fileName = data.getData();
    // String completePath = Environment.getExternalStorageDirectory() + "/" + fileName;

    //File file = new File(String.valueOf(fileName));
    // Uri imageUri = Uri.fromFile(file);
    //  File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Running.jpg");
//            Glide.with(this)
//                    .load(imageUri)
//                    .into(imgView);.fit()
    //    Log.i("//****", fileName + "");
//            Picasso.with(getApplicationContext()).load(fileName).noPlaceholder().centerCrop().resize(100,100)
//                    .into(mImage);
    //         Log.i("////*", fileName + "");


    //    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
//        ParcelFileDescriptor parcelFileDescriptor =
//                getContentResolver().openFileDescriptor(uri, "r");
//        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
//        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
//        parcelFileDescriptor.close();
//        return image;
//    }
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
//    @Override
//    protected void onResumeFragments() {
//        super.onResumeFragments();
//        mAdapter.notifyDataSetChanged();
//    }

}


