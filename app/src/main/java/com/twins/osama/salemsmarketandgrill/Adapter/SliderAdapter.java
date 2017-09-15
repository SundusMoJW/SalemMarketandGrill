package com.twins.osama.salemsmarketandgrill.Adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.Helpar.PagerFragment;

import java.util.ArrayList;

/**
 * Created by Osama on 7/24/2017.
 */

public class SliderAdapter extends FragmentStatePagerAdapter {
    String images[];
    String txt[];
//    private final OnDrawerItemClickListener listener;
ArrayList<Slider> arrSlider;
//    public SliderAdapter(FragmentManager fm,/*ArrayList<Slider> arrSlider */String[] images, String txt[]) {
//        super(fm);
////        this.arrSlider=arrSlider;
//        this.images = images;
//        this.txt = txt;
//    }
   public SliderAdapter(FragmentManager fm, ArrayList<Slider> arrSlider) {
        super(fm);
        this.arrSlider=arrSlider;
//       this.listener = listener;
//        this.images = images;
        this.txt = txt;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        // Our object is just an integer 😛
        args.putInt(PagerFragment.ARG_OBJECT, i);
//        args.putStringArray("images", images);
//        args.putStringArray("text", txt);
        args.putParcelableArrayList("arrSlider",arrSlider);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return arrSlider.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}