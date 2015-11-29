package com.example.antonio.quichica;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Antonio on 29/10/2015.
 */




/*
public class SecondaActivity extends FragmentActivity implements OnMapReadyCallback {
    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private Location location;


    private GoogleMap mMap;

    static final LatLng TutorialsPoint = new LatLng(46.493168, 11.3306379);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        GoogleMap SupportMapFragment = null;
        try {

            if (SupportMapFragment == null) {
                SupportMapFragment = ((SupportMapFragment) getSupportFragmentManager().
                        findFragmentById(R.id.map)).getMap();

            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onMapReady(GoogleMap map) {
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        mMap.setMyLocationEnabled(true);
        Marker TP = mMap.addMarker(new MarkerOptions().
                position(TutorialsPoint).title("TutorialsPoint"));
    }
    // Google Map

}*/
public class SecondaActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        map.setMyLocationEnabled(true);
    }

    }
