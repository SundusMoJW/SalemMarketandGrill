package com.twins.osama.salemsmarketandgrill.Helpar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twins.osama.salemsmarketandgrill.Activity.ImgZoom;
import com.twins.osama.salemsmarketandgrill.Classes.Slider;
import com.twins.osama.salemsmarketandgrill.R;

import java.util.ArrayList;

import static com.twins.osama.salemsmarketandgrill.Fragment.HomeFragment.isSlider;


public class PagerFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private ImageView img;
    TextView tv;
    private View pagerFragment;
    private FragmentTransaction mFragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        TypefaceUtil.applyFont(getActivity(), view);
        Bundle args = getArguments();
//        String images[] = (args.getStringArray("images"));
        final ArrayList<Slider> list = args.getParcelableArrayList("arrSlider");
        final int position = args.getInt(ARG_OBJECT);
        img = (ImageView) view.findViewById(R.id.imge);
//        img.setImageResource(images[position]);
        Glide.with(getContext()).load(list.get(position).getImages()).into(img);

        tv = (TextView) view.findViewById(R.id.tv);
//        String stv[] = (args.getStringArray("text"));
        tv.setText(list.get(position).getTitels());
        pagerFragment = view.findViewById(R.id.pagerFragment);
        pagerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSlider=true;
//                Bundle gameData = new Bundle();
//                gameData.putStringArrayList("p",players);
//                gameData.putString(Constant.KEY_TEAM_NAME,custom_team_name);
//                gameData.putInt("position", list.get(position).getId());

//                Intent intent = getActivity().getIntent();
                Intent intent = new Intent(getActivity(), ImgZoom.class);
//                intent.putExtras(gameData);
                intent.putExtra("position", list.get(position).getId());
                startActivity(intent);
            }
        });
//        tv.setText(stv[position]);
        return view;

    }
}