package com.uom.cse.shoppinglist;

import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.uom.cse.shoppinglist.DAO.LocationDBHandler;
import com.uom.cse.shoppinglist.gpsTracker.GPSTracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Map<Marker, com.uom.cse.shoppinglist.DAO.Location> markerMap = new HashMap<>();

    // GPSTracker class
    GPSTracker gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                AddNewLocationFragment.setLonLat(latLng.longitude, latLng.latitude);
                finish();
            }
        });

        Toast.makeText(this, "Long Press to Add New Location", Toast.LENGTH_LONG).show();

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                com.uom.cse.shoppinglist.DAO.Location location = markerMap.get(marker);

                if(location != null) {
                    AddNewLocationFragment.edit(location);
                    finish();
                }


                return true;
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        // create class object
        gps = new GPSTracker(MapsActivity.this);

        double lati = 0.0;
        double longi = 0.0;

        // check if GPS enabled
        if(gps.canGetLocation()){

            lati = gps.getLatitude();
            longi = gps.getLongitude();

        }

        LocationDBHandler handler = new LocationDBHandler(this);
        List<com.uom.cse.shoppinglist.DAO.Location> locations = handler.getAllItems();

        for(com.uom.cse.shoppinglist.DAO.Location location : locations){
            MarkerOptions options = new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude()))
                    .title(location.getName());

            Marker marker = mMap.addMarker(options);

            markerMap.put(marker, location);
        }

        mMap.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title("Current Location")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 16.0f));


    }
}
