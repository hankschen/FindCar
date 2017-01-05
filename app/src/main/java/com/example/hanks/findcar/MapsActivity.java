package com.example.hanks.findcar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    Double lat, lng;
    MyLocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getGPS();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * 操作地圖一旦可用。
     * This callback is triggered when the map is ready to be used.
     * 當地圖準備好使用時，會觸發此回調。
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * 這是我們可以添加標記或線條，添加監聽器或移動攝像頭的地方。 在這種情況下，我們只需在澳大利亞悉尼附近添加一個標記。
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     * 如果設備上未安裝Google Play服務，系統會提示用戶將其安裝在SupportMapFragment中。 此方法只有在用戶安裝Google Play服務並返回到應用後才會觸發。
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationListener = new MyLocationListener();
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, locationListener);
        }catch (SecurityException e){

        }
        //LatLng sydney = new LatLng(-34, 151);
        //LatLng foungyungStation = new LatLng(24.254235, 120.722931);
        LatLng myLocation = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(myLocation).title("My Location...."));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation)); //移到指定位置
        mMap.moveCamera(CameraUpdateFactory.zoomIn()); // 放大地圖
    }

    void getGPS() {
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("GPS定位功能")
                    .setMessage("GPS 尚未啟用!\n請問是否啟用GPS功能？")
                    .setPositiveButton("啟用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("不啟用", null)
                    .create()
                    .show();
        }
    }

    class MyLocationListener implements android.location.LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            Toast.makeText(MapsActivity.this, "經度座標為 " + lat + "\n" + "緯度座標為 " + lng + "\n", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        locationListener = new MyLocationListener();
//        try {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, locationListener);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, locationListener);
//        }catch (SecurityException e){
//
//        }
//    }

    // 離開app時，停止位置變化的監聽，這樣才不會浪費資源
    @Override
    protected void onPause() {
        super.onPause();
        try {
            locationManager.removeUpdates(locationListener);
        }catch (SecurityException e){

        }
    }
}
