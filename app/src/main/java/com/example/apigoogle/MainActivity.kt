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
    lateinit var lista:ArrayList<LatLng>

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
        mMap.setOnMapClickListener(this);
    }

    override fun onMapClick(point: LatLng) {
        var punto = LatLng(point.latitude,point.longitude);
        lista.add(point)
        mMap.addMarker(
            MarkerOptions().position(punto));

        var lineas = PolylineOptions()
            .add(LatLng(45.0, -12.0))
            .add(LatLng(45.0, 5.0))
            .add(LatLng(34.5, 5.0))
            .add(LatLng(34.5, -12.0))
            .add(LatLng(45.0, -12.0));
        lineas.width(8F);
        lineas.color(Color.RED);
        mMap.addPolyline(lineas);


        Toast.makeText(
        this,"Click\n" +"Lat: " + point.latitude + "\n" +"Lng: " + point.longitude + "\n",
        Toast.LENGTH_SHORT).show();

    }

}