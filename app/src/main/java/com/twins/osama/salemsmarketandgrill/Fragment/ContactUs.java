package com.twins.osama.salemsmarketandgrill.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.twins.osama.salemsmarketandgrill.Helpar.Const;
import com.twins.osama.salemsmarketandgrill.Helpar.TypefaceUtil;
import com.twins.osama.salemsmarketandgrill.R;

public class ContactUs extends Fragment implements OnMapReadyCallback {

//    private GoogleMap mMap;
////    MapView mapView;
private GoogleMap mMap;

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
        Const.setLangSettings(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        TypefaceUtil.applyFont(getActivity(),view.findViewById(R.id.contact_us));

        getActivity().findViewById(R.id.menu).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.shopping).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.adding_to_cart).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.search).setVisibility(View.VISIBLE);
        getActivity().findViewById(R.id.go_back).setVisibility(View.GONE);

        SupportMapFragment mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//
//        mapView = (MapView) view.findViewById(R.id.mapview);
//        mapView.onCreate(savedInstanceState);
//
//        // Gets to GoogleMap from the MapView and does initialization stuff
//        map = mapView.getMap();
//        map.getUiSettings().setMyLocationButtonEnabled(false);
//        map.setMyLocationEnabled(true);
//
//        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
//        MapsInitializer.initialize(this.getActivity());
//
//        // Updates the location and zoom of the MapView
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
//        map.animateCamera(cameraUpdate);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
