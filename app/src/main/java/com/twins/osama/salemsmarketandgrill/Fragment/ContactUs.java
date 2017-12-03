package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.Helpar.WorkaroundMapFragment;
import com.twins.osama.salemsmarketandgrill.R;

public class ContactUs extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ScrollView mScrollView;

    public static ContactUs newInstance() {
        ContactUs fragment = new ContactUs();
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        TypefaceUtil.applyFont(getActivity(), view.findViewById(R.id.contact_us));
        mScrollView = (ScrollView) view.findViewById(R.id.contact_us);

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        SupportMapFragment mapFragment = ((WorkaroundMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map));
        mapFragment.getMapAsync(this);
        ((WorkaroundMapFragment) mapFragment).setListener(new WorkaroundMapFragment.OnTouchListener() {
            @Override
            public void onTouch() {
                mScrollView.requestDisallowInterceptTouchEvent(true);
            }
        });

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        googleMap.getUiSettings().setScrollGesturesEnabled(false);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
