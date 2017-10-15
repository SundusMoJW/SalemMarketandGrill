package com.twins.osama.salemsmarketandgrill.Activity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liuguangqiang.swipeback.SwipeBackActivity;
import com.liuguangqiang.swipeback.SwipeBackLayout;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

import io.realm.Realm;

import static com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment.isSlider;

public class ImgZoom extends SwipeBackActivity {


    private Slider slider = new Slider();
    private ImageView imgZoom;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        int position = getIntent().getIntExtra("position", 0);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Const.setLangSettings(this);
        setContentView(R.layout.activity_img_zoom);
        setDragEdge(SwipeBackLayout.DragEdge.TOP);
        setDragEdge(SwipeBackLayout.DragEdge.BOTTOM);
//        overridePendingTransition(R.anim.swipeback_stack_to_front, R.anim.swipeback_stack_to_back);

        imgZoom = (ImageView) findViewById(R.id.imgZoom);


        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.image_zoom));
        RealmController.with(this).refresh();
        realm = Realm.getDefaultInstance();
        if (isSlider) {
            slider = realm.where(Slider.class).equalTo("Id", position).findFirst();
            imgZoom = (ImageView) findViewById(R.id.imgZoom);
//            RealmResults<Slider> realmResults = realm.where(Slider.class).findAll();
            Glide.with(getApplicationContext()).load(slider.getImages()).into(imgZoom);
        }
    }
}
