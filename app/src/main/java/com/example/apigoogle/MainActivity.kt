package com.example.apigoogle

import android.graphics.Color
import android.graphics.Point
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

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
        var borrar:ArrayList<LatLng>
        borrar=ArrayList<LatLng>()
        mMap.setOnMarkerClickListener { marcador ->
            puntos=lista2.points.size-1;
            for (i in 0..lista2.points.size-1) {
                if (lista2.points[i]==marcador.position){
                    //borrar.add(marcador.position)
                    if(puntos==4){
                        lista.remove()
                    }
                    lista2.points.remove(marcador.position)
                    marcador.remove()
                    puntos=puntos-1;
                    break;
                }
            }
            true
        }
        lista2=PolylineOptions()
    }

    lateinit var lista:Polyline
    lateinit var lista2:PolylineOptions
    var puntos=0

    override fun onMapClick(point: LatLng) {
        var punto = LatLng(point.latitude,point.longitude);
        if(puntos<4){
            lista2.add(point)
            puntos++;
        }
        if(puntos==4){
            lista2.add(lista2.points[0])
            lista2.width(8F);
            lista2.color(Color.RED);
            lista=mMap.addPolyline(lista2);
            puntos=0;
        }

        mMap.addMarker(
            MarkerOptions().position(punto));
        Toast.makeText(
        this,"Click\n" +"Lat: " + point.latitude + "\n" +"Lng: " + point.longitude + "\n",
        Toast.LENGTH_SHORT).show();

    }

}