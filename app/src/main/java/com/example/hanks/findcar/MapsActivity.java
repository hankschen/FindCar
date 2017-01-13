package com.example.hanks.findcar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    LocationManager locationManager;
    //MyLocationListener locationListener;
    String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
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
        gMap = googleMap;
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        getGPS();
        getLocation();

        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
        }catch (SecurityException e){

        }
//        //註冊位置更新監聽物件
//        locationListener = new MyLocationListener();
//        try {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
//        }catch (SecurityException e){
//
//        }
        //LatLng myLocation = new LatLng(-34, 151);
        //LatLng foungyungStation = new LatLng(24.254235, 120.722931);
        //LatLng myLocation = new LatLng(24.254235, 120.722931);
        //LatLng myLocation = new LatLng(locationListener.lat, locationListener.lng);
//        gMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
//        gMap.addMarker(new MarkerOptions().position(myLocation).title("My Location...."));
//        Toast.makeText(MapsActivity.this, "經度座標為 " + locationListener.lat + "\n" + "緯度座標為 " + locationListener.lng + "\n", Toast.LENGTH_LONG).show();
        //gMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation)); //移到指定位置
        //gMap.moveCamera(CameraUpdateFactory.zoomIn()); // 放大地圖
//        String uri = String.format("geo=%f,%f?z=18",locationListener.lat,locationListener.lng);
//        Intent goToMyLocation = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        startActivity(goToMyLocation);
    }

//    public void onMyLocation(View view){
//        LatLng myLocation = new LatLng(locationListener.lat, locationListener.lng);
//        gMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
//        gMap.addMarker(new MarkerOptions().position(myLocation).title("My Location...."));
//        Toast.makeText(MapsActivity.this, "經度座標為 " + locationListener.lat + "\n" + "緯度座標為 " + locationListener.lng + "\n", Toast.LENGTH_LONG).show();
        //gMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation)); //移到指定位置
        //gMap.moveCamera(CameraUpdateFactory.zoomIn()); // 放大地圖
//        String uri = String.format("geo=%f,%f?z=18",locationListener.lat,locationListener.lng);
//        Intent goToMyLocation = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        startActivity(goToMyLocation);
//    }

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

//    class MyLocationListener implements LocationListener {
//        double lat, lng;
//        @Override
//        public void onLocationChanged(Location location) {
//            if (location != null) {
//                currentLocation = location;
//                lat = currentLocation.getLatitude();
//                lng = currentLocation.getLongitude();
//                Toast.makeText(MapsActivity.this, "經度座標為 " + lat + "\n" + "緯度座標為 " + lng + "\n", Toast.LENGTH_LONG).show();
//            }else {
//                Toast.makeText(MapsActivity.this,"No Location Data..!",Toast.LENGTH_SHORT).show();
//            }
//        }

//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//
//        }
//    }

    // 開啟App即註冊位置更新監聽物件
//    @Override
//    protected void onResume() {
//        super.onResume();
////        locationListener = new MyLocationListener();
//        try {
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
//        }catch (SecurityException e){
//
//        }
//    }

    // 離開app時，停止位置變化的監聽，這樣才不會浪費資源
//    @Override
//    protected void onPause() {
//        super.onPause();
//        try {
//            locationManager.removeUpdates((android.location.LocationListener) locationListener);
//        }catch (SecurityException e){
//
//        }
//    }

    void getLocation() {
        //取得設定準則(標準)的物件(用以設定定位精準度跟省電程度...等)
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //建最佳位置提供者provider
        //在室外GPS信號很好時,可用此方法
        //provider = locationManager.getBestProvider(criteria, true);
        provider = LocationManager.GPS_PROVIDER;
        //在室內無GPS信號時,用此方法
        //provider = LocationManager.NETWORK_PROVIDER;
        //透過provider取得最後已知的位置
        Location location = null;

        //回家試沒加try catch會不會有問題
        try {
            location = locationManager.getLastKnownLocation(provider);
        } catch (SecurityException e) {

        }

        if (gMap != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16.0f));
            //要顯現走過的軌跡在此加入下面行,不要clear掉,就會邊走邊標位置
            //如果加clear就可一直更新你的位置
            //mMap.addMarker()
        }
    }

    android.location.LocationListener locationListener = new android.location.LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if(gMap!=null){
                LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,16.0f));
                //跳Toast顯示位置的位置提供者,海拔,方位角,速度,緯經度..等資訊
                //先建StringBuilder來串要顯示的資訊字串
                StringBuilder sb=new StringBuilder();
                sb.append("Provider:"+provider+"\n")
                        .append("海抜:"+location.getAltitude()+"公尺\n")
                        .append("方位:"+location.getBearing()+"\n")
                        .append("速度"+location.getSpeed()+"公尺/秒\n")
                        .append("緯度"+location.getLatitude()+"\n")
                        .append("經度" + location.getLongitude()+"\n");
                Toast.makeText(MapsActivity.this, sb, Toast.LENGTH_LONG).show();
            }
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
    };
}
