package com.example.spideroidreturns.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.spideroidreturns.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GeoCoderFragment extends Fragment {
    CardView cardViewGetLocation;
    Context context = getContext();
    int PERMISSION_ID = 44;
    String TAG = GeoCoderFragment.class.getSimpleName();
    TextView tvSubLocality;
    TextView tvLocality;
    TextView tvState;
    TextView tvCountry;
    int FLAG_CHECKLOCATION = 0;
    double lat;
    double lng;
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location lastLocation = locationResult.getLastLocation();

            lat = lastLocation.getLatitude();
            lng = lastLocation.getLongitude();
        }
    };


    public GeoCoderFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vRoot = inflater.inflate(R.layout.fragment_geo_coder, container, false);
        cardViewGetLocation = vRoot.findViewById(R.id.cardViewGetLocation);
        tvSubLocality = vRoot.findViewById(R.id.tvSubLocality);
        tvLocality = vRoot.findViewById(R.id.tvLocality);
        tvState = vRoot.findViewById(R.id.tvState);
        tvCountry = vRoot.findViewById(R.id.tvCountry);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        getLastLocation();

        cardViewGetLocation.setOnClickListener(v -> {
            Geocoder geocoder = new Geocoder(getContext());
            try {
                List<Address> arrAddress = geocoder.getFromLocation(lat, lng, 1);

                Log.d(TAG, "onCreateView: Maaro LatLambo: " + lat + " " + lng);
                tvSubLocality.setText(arrAddress.get(0).getSubLocality());
                tvLocality.setText(arrAddress.get(0).getLocality());
                tvState.setText(arrAddress.get(0).getAdminArea());
                tvCountry.setText(arrAddress.get(0).getCountryName());

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return vRoot;
    }// method to check for permissions

    private void getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                // getting last location from FusedLocationClient object
                if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location != null) {
                            requestNewLocationData();
                        } else {
                            FLAG_CHECKLOCATION = 1;
                            lat = location.getLatitude();
                            lng = location.getLongitude();
                        }
                    }
                });
            } else {
                Toast.makeText(context, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        com.google.android.gms.location.LocationRequest mLocationRequest = new com.google.android.gms.location.LocationRequest();
        mLocationRequest.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(15000);
        //mLocationRequest.setFastestInterval(1000);
        //mLocationRequest.setNumUpdates(10);

        // setting LocationRequest
        // on FusedLocationClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

}