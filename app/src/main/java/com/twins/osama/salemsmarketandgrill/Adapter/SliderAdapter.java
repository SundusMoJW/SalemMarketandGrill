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

ArrayList<Slider> arrSlider;

   public SliderAdapter(FragmentManager fm, ArrayList<Slider> arrSlider) {
        super(fm);
        this.arrSlider=arrSlider;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new PagerFragment();
        Bundle args = new Bundle();
        args.putInt(PagerFragment.ARG_OBJECT, i);
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