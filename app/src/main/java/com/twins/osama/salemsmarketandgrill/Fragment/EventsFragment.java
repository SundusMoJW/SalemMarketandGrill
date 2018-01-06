package com.twins.osama.salemsmarketandgrill.Fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.twins.osama.salemsmarketandgrill.Classes.Events;
import com.twins.osama.salemsmarketandgrill.Helpar.RealmController;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.VolleyRequests;
import com.twins.osama.salemsmarketandgrill.R;

import io.realm.Realm;

import static com.twins.osama.salemsmarketandgrill.Activity.MainActivity.nav_back;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.IMG_URL;
import static com.twins.osama.salemsmarketandgrill.Helpar.Const.getDate;

public class EventsFragment extends Fragment {


    private int position;
    private Realm realm;
    private SimpleDraweeView restImg;
    private TextView retTitel;
    private TextView restDescription;
    private TextView from;
    private TextView to;
    private TextView maxPeople;
    private LinearLayout eventsFragment;
    private String description;

    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.realm = RealmController.with(getActivity()).getRealm();
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        nav_back=1;
        position = getArguments().getInt("position");
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.ScrolleventsFragment));
        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);
        restImg = (SimpleDraweeView) view.findViewById(R.id.rest_img);
        retTitel = (TextView) view.findViewById(R.id.ret_titel);
        restDescription = (TextView) view.findViewById(R.id.rest_description);
        eventsFragment = view.findViewById(R.id.eventsFragment);
        from = view.findViewById(R.id.from);
        to = view.findViewById(R.id.to);
        maxPeople = view.findViewById(R.id.maxPeople);

        Events events = RealmController.with(getActivity()).cheackEvents(position);
        Uri uri = Uri.parse(IMG_URL + events.getFilePath().replace("~", ""));
        restImg.setImageURI(uri);
        retTitel.setText(events.getTitle());
        from.setText(getDate(events.getFromDate()));
        to.setText(getDate(events.getToDate()));
        maxPeople.setText(events.getMaxPeople()+"");

        new VolleyRequests().setIReceiveData(new VolleyRequests.IReceiveData() {
            @Override
            public void onDataReceived(Object o) {
                description = (String) o;
                if (description.equals("null"))
                    restDescription.setText("");
                else restDescription.setText(description);
                Log.i("Object", (String) o);
                Log.i("description", description);
            }
        }).getEventsDetails(events.getId());
        return view;
    }

}
