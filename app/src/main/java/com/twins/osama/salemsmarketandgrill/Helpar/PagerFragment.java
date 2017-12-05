package com.twins.osama.salemsmarketandgrill.Helpar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Activity.ImgZoom;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import static com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment.isSlider;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;


public class PagerFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private SimpleDraweeView img;
    private TextView tv;
    private View pagerFragment;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        TypefaceUtil.applyFont(getActivity(), view);
        Bundle args = getArguments();
        final ArrayList<Slider> list = args.getParcelableArrayList("arrSlider");
        final int position = args.getInt(ARG_OBJECT);
        img = (SimpleDraweeView) view.findViewById(R.id.imge);
//        Glide.with(getContext()).load(IMG_URL +list.get(position).getFilePath().replace("~", "")).into(img);
        Uri uri = Uri.parse(IMG_URL +list.get(position).getFilePath().replace("~", ""));
        img.setImageURI(uri);
        tv = (TextView) view.findViewById(R.id.tv);
        tv.setText(list.get(position).getTitel());
        pagerFragment = view.findViewById(R.id.pagerFragment);
        pagerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSlider=true;

                Intent intent = new Intent(getActivity(), ImgZoom.class);
                intent.putExtra("position", list.get(position).getId());
                startActivity(intent);
            }
        });
        return view;

    }
}