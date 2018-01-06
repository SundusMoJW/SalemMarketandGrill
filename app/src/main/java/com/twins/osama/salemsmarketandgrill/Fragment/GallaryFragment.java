package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twins.osama.salemsmarketandgrill.Activity.MainActivity;
import com.twins.osama.salemsmarketandgrill.Adapter.GallaryAdapter;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;
import com.twins.osama.salemsmarketandgrill.Template.RVGallaryTemplate;

import java.util.ArrayList;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;

public class GallaryFragment extends Fragment {
    ArrayList<RVGallaryTemplate> data;
    int img2 = R.drawable.crop3;
    int img = R.drawable.resturant;
    int img1 = R.drawable.crop2;
    private String suspendisse_gravida;
    private String more_info;
   int expand_photo;

    public static GallaryFragment newInstance() {
        GallaryFragment fragment = new GallaryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_gallary, container, false);
        TypefaceUtil.applyFont(getActivity(),view.findViewById(R.id.gallary_fragment));
        TypefaceUtil.applyFont(getActivity(),view.findViewById(R.id.cardView));
        nav_back=1;
        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        data = fill_with_data();

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerviewGallary);
        GallaryAdapter rvadapter = new GallaryAdapter((MainActivity)getActivity(), data);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));cardView
        recyclerView.setAdapter(rvadapter);

  // expand_photo=(ImageView)view.findViewById(R.id.expand_photo);

        //        expand_photo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getActivity(),ImgZoom.class);
//                startActivity(intent);
//            }
//        });
        return view;

    }

    public ArrayList<RVGallaryTemplate> fill_with_data() {

        ArrayList<RVGallaryTemplate> data = new ArrayList<>();
        suspendisse_gravida=(String) getText(R.string.suspendisse_gravida);
        more_info=(String) getText(R.string.more_info);
        expand_photo=R.drawable.ic_scale_symbol;

        data.add(new RVGallaryTemplate(img,suspendisse_gravida ,more_info,expand_photo));
        data.add(new RVGallaryTemplate(img2,suspendisse_gravida ,more_info,expand_photo));
        data.add(new RVGallaryTemplate(img,suspendisse_gravida ,more_info,expand_photo));
        data.add(new RVGallaryTemplate(img1,suspendisse_gravida ,more_info,expand_photo));
        return data;
    }
}