package com.example.apigoogle

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : FragmentActivity(), OnMapReadyCallback, GoogleMap.OnMapClickListener {

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
            CameraUpdateFactory.newLatLngZoom(LatLng(-1.080428, -79.501368), 19F)
        mMap.moveCamera(camUpd1)
        mMap.setOnMapClickListener(this);
        lista=PolylineOptions()
    }

    lateinit var lista:PolylineOptions
    var puntos=0

    override fun onMapClick(point: LatLng) {
        var punto = LatLng(point.latitude,point.longitude);
        if(puntos<5){
            lista.add(point)
            puntos++;
        }
        if(puntos==5){
            lista.width(8F);
            lista.color(Color.RED);
            mMap.addPolyline(lista);
            puntos=0;
            lista=PolylineOptions()
        }

        mMap.addMarker(
            MarkerOptions().position(punto));
        Toast.makeText(
        this,"Click\n" +"Lat: " + point.latitude + "\n" +"Lng: " + point.longitude + "\n",
        Toast.LENGTH_SHORT).show();

    }

}