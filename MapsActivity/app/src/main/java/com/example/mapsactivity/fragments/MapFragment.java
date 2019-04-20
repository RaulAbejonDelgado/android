package com.example.mapsactivity.fragments;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;

import com.example.mapsactivity.R;
import com.example.mapsactivity.activities.MapsActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment {

    private View v;
    private GoogleMap googleMap;
    private MapView mapView;

    private Geocoder geocoder;
    private List<Address> address;
    private MarkerOptions marker;
    private FloatingActionButton fab;


    public MapFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_map, container, false);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mapView = v.findViewById(R.id.map);

        this.checkGpsSignal();


        this.createMap();

        //this.enableLocation();





    }

    private boolean enableLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return false;
        }else{

            googleMap.setMyLocationEnabled(true);
            return true;
        }

    }

    private void checkGpsSignal(){
        try {
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(), Settings.Secure.LOCATION_MODE);
            if(gpsSignal == 0){
                //gps desactivated
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createMap(){

        if(mapView != null){

            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap = googleMap;

                    // Add a marker in Sydney and move the camera
                    LatLng dsCatering = new LatLng(43.27238178823771, -2.946892082691193);

                    marker = new MarkerOptions();
                    marker.position(dsCatering);
                    marker.title("Marcador");
                    marker.draggable(true);
                    marker.snippet("Caja de texto donde modificar los datos ");
                    marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.baseline_bug_report_black_18dp));
                    googleMap.addMarker(marker);

                    //googleMap.addMarker(new MarkerOptions().position(dsCatering).title("Catering Ds").visible(true).draggable(true));

                    /**
                     * set min max zoom allowed
                     */
                    googleMap.setMinZoomPreference(5);
                    googleMap.setMinZoomPreference(15);

                    /**
                     * Zoom values -> limit 21
                     * Bearing -> angle of camera to east
                     * tilt -> 3d effect
                     */
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(dsCatering)
                            .zoom(17)
                            .bearing(35)
                            .tilt(90)
                            .build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    //mMap.moveCamera(CameraUpdateFactory.newLatLng(dsCatering));

                    googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
//                            Toast.makeText(getActivity(), latLng.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                        @Override
                        public void onMarkerDragStart(Marker marker) {
                            marker.hideInfoWindow();

                        }

                        @Override
                        public void onMarkerDrag(Marker marker) {

                        }

                        @Override
                        public void onMarkerDragEnd(Marker marker) {

                            //Toast.makeText(getActivity(), marker.getPosition().toString(), Toast.LENGTH_SHORT).show();
                            double latitude = marker.getPosition().latitude;
                            double longitude = marker.getPosition().longitude;
                            try {
                                address = geocoder.getFromLocation(latitude,longitude, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            String addressNames = address.get(0).getAddressLine(0);
                            String city = address.get(0).getLocality();
                            String state = address.get(0).getAdminArea();
                            String country = address.get(0).getCountryName();
                            String postalCode = address.get(0).getPostalCode();

                            marker.setSnippet("addressNames -> "+ addressNames+"\n"+
                                    "city -> "+ city+"\n"+
                                    "state -> "+ state+"\n"+
                                    "country -> "+ country+"\n"+
                                    "postalCode ->"+ postalCode);

                            marker.showInfoWindow();

//
                            Toast.makeText(getContext(),
                                    "addressNames -> "+ addressNames+"\n"+
                                            "city -> "+ city+"\n"+
                                            "state -> "+ state+"\n"+
                                            "country -> "+ country+"\n"+
                                            "postalCode ->"+ postalCode,
                                    Toast.LENGTH_LONG).show();


                        }
                    });

                    geocoder = new Geocoder(getContext(), Locale.getDefault());

                }
            });

        }

    }
}
