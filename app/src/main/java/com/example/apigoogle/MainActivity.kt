package com.example.apigoogle

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng

class MainActivity : FragmentActivity(), OnMapReadyCallback {

    lateinit var mMap:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mapFragment:SupportMapFragment = getSupportFragmentManager().findFragmentById(R.id.map) as SupportMapFragment;
        mapFragment
            .getMapAsync(this);



    }

    override fun onMapReady(googleMap:GoogleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true)
        var camUpd1 =
            CameraUpdateFactory.newLatLngZoom(LatLng(-1.080428, -79.501368), 19F);
        mMap.moveCamera(camUpd1)
    }

}