package com.test1.safezone_project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.provider.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.view.View.OnClickListener;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationServiceActivity extends Activity implements
        OnClickListener {

    private double latitude;
    private double longitude;

    private Button btnGetLocation, btnGpsStop;
    private EditText editLocation = null;
    private ProgressBar pb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_service);

        GpsInfo gps = new GpsInfo(LocationServiceActivity.this);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);

        editLocation = (EditText) findViewById(R.id.editTextLocation);

        btnGetLocation = (Button) findViewById(R.id.btnLocation);
        btnGetLocation.setOnClickListener(this);

        btnGpsStop = (Button) findViewById(R.id.btnStop);
        btnGpsStop.setOnClickListener(this);


        if (gps.isGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        } else {
            latitude = 0;
            longitude = 0;
        }

        btnGetLocation.setText(String.valueOf(latitude));

        btnGpsStop.setText(String.valueOf(longitude));

    }
    public void onClick(View v) {
        GpsInfo gps = new GpsInfo(LocationServiceActivity.this);

        switch (v.getId()) {
            case R.id.btnStop:
                gps.stopUsingGPS();
                pb.setVisibility(View.INVISIBLE);
                editLocation.setText("");
                break;
            case R.id.btnLocation:

                if (gps.isGetLocation() == true) {

                    editLocation.setText("방위가 바뀔때 까지 기다려주세요." + "\nWait..");
                    pb.setVisibility(View.VISIBLE);
                    get_cityname();

                } else {
                    alertbox("Gps 상태!!", "당신의 GPS 상태 : OFF");
                }
                break;
            default:
                break;
        }
    }

    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent myIntent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void get_cityname() {
        pb.setVisibility(View.INVISIBLE);
        String cityName = null;
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude,
                    longitude, 1);
            if (addresses.size() > 0)

            Log.v("이거이거","1 :" + addresses.get(0).getAddressLine(0).toString()); //대한민국 대구광역시 동구 신서동 1013-5
            Log.v("이거이거","4 :" + addresses.get(0).getCountryName()); //대한민국
            Log.v("이거이거","6 :" + addresses.get(0).getAdminArea()); //대구광역시
            Log.v("이거이거","7 :" + addresses.get(0).getLocality()); //동구
            Log.v("이거이거","14:" + addresses.get(0).getThoroughfare()); //신서동
            Log.v("이거이거","2 :" + addresses.get(0).getFeatureName()); //1013-5
            Log.v("이거이거","3 :" + addresses.get(0).getLocale()); //ko_KR
            Log.v("이거이거","5 :" + addresses.get(0).getCountryCode()); // KR

            cityName = addresses.get(0).getAddressLine(0).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String s = longitude + "\n" + latitude + "\n\n당신의 현재 도시명 : "
                + cityName;
        editLocation.setText(s);
    }

}
