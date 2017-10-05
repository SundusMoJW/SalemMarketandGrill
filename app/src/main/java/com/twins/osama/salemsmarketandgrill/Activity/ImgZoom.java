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
import io.realm.RealmResults;

import static com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment.isSlider;

public class ImgZoom /*extends Activity*/extends SwipeBackActivity {


    //                intent.putExtra("position", list.get(position).getId());
    Slider slider = new Slider();

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
//        image_zoom=(Gr)
//        view.setBackgroundColor(Color.TRANSPARENT);
//        View view =(View)findViewById(R.id.image_zoom) ;
//        view.getBackground().setAlpha(50);
        TypefaceUtil.applyFont(getApplicationContext(), findViewById(R.id.image_zoom));
        RealmController.with(this).refresh();
        realm = Realm.getDefaultInstance();
        if (isSlider) {
            slider = realm.where(Slider.class).equalTo("Id", position).findFirst();
            imgZoom = (ImageView) findViewById(R.id.imgZoom);
            RealmResults<Slider> realmResults = realm.where(Slider.class).findAll();
            Glide.with(getApplicationContext()).load(slider.getImages()).into(imgZoom);
        }
    }
}
