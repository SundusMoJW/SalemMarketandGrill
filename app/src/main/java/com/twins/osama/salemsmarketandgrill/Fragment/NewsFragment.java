package com.twins.osama.salemsmarketandgrill.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Classes.News;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.VolleyRequests;
import com.twins.osama.salemsmarketandgrill.R;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;

public class NewsFragment extends Fragment {

    private int position;
    private SimpleDraweeView restImg;
    private TextView retTitel;
    private TextView restDescription;
    private LinearLayout newsFragment;
    private String description;

    public NewsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
                View view=inflater.inflate(R.layout.fragment_news, container, false);
        position = getArguments().getInt("position");
        nav_back=1;
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.ScrollnewsFragment));
        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);
        restImg = (SimpleDraweeView) view.findViewById(R.id.rest_img);
        retTitel = (TextView) view.findViewById(R.id.ret_titel);
        restDescription = (TextView) view.findViewById(R.id.rest_description);
        newsFragment=view.findViewById(R.id.newsFragment);


        News news= RealmController.with(getActivity()).cheackNews(position);
        Uri uri = Uri.parse(IMG_URL + news.getFilePath().replace("~", ""));
        restImg.setImageURI(uri);
        retTitel.setText(news.getTitle());

        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                description = String.valueOf(Html.fromHtml(Html.fromHtml((String)o).toString()));
                if (description.equals("null"))
                    restDescription.setText("");
                else restDescription.setText(description);
                Log.i("Object", (String) o);
                Log.i("description", description);
            }
        }).getNewsDetails(news.getId());
        return view;
    }

}
