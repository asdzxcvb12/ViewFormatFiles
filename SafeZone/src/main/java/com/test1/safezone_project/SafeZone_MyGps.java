package com.test1.safezone_project;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SafeZone_MyGps extends FragmentActivity implements
        OnMapClickListener {

    private TextView mLongitude, mLatitude;
    private GoogleMap mGoogleMap;
    private SeekBar mZoomBar;

    private int DEFAULT_ZOOM_LEVEL = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__my_gps);

        mZoomBar = (SeekBar) findViewById(R.id.zoombar);

        // 이벤트 등록
        mZoomBar.setOnSeekBarChangeListener(seekBarChangeListener);

        // BitmapDescriptorFactory 생성하기 위한 소스
        MapsInitializer.initialize(getApplicationContext());

        GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(SafeZone_MyGps.this);
        mGoogleMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        this.setGpsCurrent("latitude", "longitude");
    }

    public void onClick_move(View v) {
        setGpsCurrent("35.873709", "128.722666");
    }

    public void onClick_mygps(View v) {
        setGpsCurrent("latitude", "longitude");
    }


    private void setZoomLevel(int level) {
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(level));
        Toast.makeText(this, "Zoom Level : " + String.valueOf(level),
                Toast.LENGTH_SHORT).show();
    }



    private SeekBar.OnSeekBarChangeListener seekBarChangeListener =
            new OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    setZoomLevel(progress + 1);
                }
            };


    /** Map 클릭시 터치 이벤트 */
    public void onMapClick(LatLng point) {

        // 현재 위도와 경도에서 화면 포인트를 알려준다
        Point screenPt = mGoogleMap.getProjection().toScreenLocation(point);

        // 현재 화면에 찍힌 포인트로 부터 위도와 경도를 알려준다.
        LatLng latLng = mGoogleMap.getProjection().fromScreenLocation(screenPt);

        Log.d("맵좌표", "좌표: 위도(" + String.valueOf(point.latitude) + "), 경도("
                + String.valueOf(point.longitude) + ")");
        Log.d("화면좌표", "화면좌표: X(" + String.valueOf(screenPt.x) + "), Y("
                + String.valueOf(screenPt.y) + ")");

        MarkerOptions optFirst = new MarkerOptions();
        optFirst.position(latLng);// 위도 • 경도
        optFirst.title("Current Position");// 제목 미리보기
        optFirst.snippet("Snippet");
        optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.selected));
        mGoogleMap.addMarker(optFirst).showInfoWindow();
    }

    /**
     * 초기화
     * @author
     */
    private void setGpsCurrent(String strLat, String strLng) {

        TextView textView_longitude = (TextView) findViewById(R.id.vibe);
        TextView textView_latitude = (TextView) findViewById(R.id.latitude);

        GooglePlayServicesUtil.isGooglePlayServicesAvailable(SafeZone_MyGps.this);
        mGoogleMap = ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        // 맵의 이동
        //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 15));

        GpsInfo gps = new GpsInfo(SafeZone_MyGps.this);
        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            double latitude = 0;
            double longitude = 0;

            if(strLat.equals("latitude") || strLng.equals("longitude")) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();
            } else {
                latitude = Double.parseDouble(strLat);
                longitude = Double.parseDouble(strLng);
            }

            textView_longitude.setText("longitude" + String.valueOf(longitude));
            textView_latitude.setText("laitude" + String.valueOf(latitude));

            // Creating a LatLng object for the current location
            LatLng latLng = new LatLng(latitude, longitude);

            // Showing the current location in Google Map
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

            // Map 을 zoom 합니다.
            this.setZoomLevel(DEFAULT_ZOOM_LEVEL);

            // 마커 설정.
            MarkerOptions optFirst = new MarkerOptions();
            optFirst.position(latLng);// 위도 • 경도
            optFirst.title("Current Position");// 제목 미리보기
            optFirst.snippet("here");
            optFirst.icon(BitmapDescriptorFactory.fromResource(R.drawable.selected));
            mGoogleMap.addMarker(optFirst).showInfoWindow();
        } else {
            gps.showSettingsAlert();
        }
    }
}
