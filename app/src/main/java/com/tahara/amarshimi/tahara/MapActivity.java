package com.tahara.amarshimi.tahara;

import android.app.Activity;
import android.os.Bundle;

import com.tahata.amarshimi.tahara.R;

/**
 * Created by amarshimi on 2/22/2015.
 */
public class MapActivity extends Activity {

    //private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 /*
        try {
            // Loading map
           // initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }
 */
    }
 /*
    /**
     * function to load map. If map is not created it will create it for you
     * *//*
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 */
}