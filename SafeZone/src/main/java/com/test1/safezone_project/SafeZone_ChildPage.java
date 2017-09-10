package com.test1.safezone_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;
import android.widget.TextView;

public class SafeZone_ChildPage extends AppCompatActivity {

    private TextView tv;
    private LocationManager lm;
    private LocationListener ll;
    double mySpeed, maxSpeed, crSpeed;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__child_page);

        tv = (TextView) findViewById(R.id.tttt);

        maxSpeed = mySpeed = crSpeed = 0;
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        ll = new SpeedoActionListener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);

        i = 0;

        tv.setText("\nCurrent Speed : " + (mySpeed * 3.6) + " km/h, Max Speed : "
                + (maxSpeed * 3.6) + " km/h" + "\ncr Speed : " + (crSpeed * 3.6) + " km/h" + "\ni : " + i);
    }

    private class SpeedoActionListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                if(location.hasSpeed()) {
                    Log.d("spd", "바뀜바뀜!?");
                    crSpeed = location.getAccuracy();
                    mySpeed = location.getSpeed();
                    i++;
                }
                if (mySpeed > maxSpeed) {
                    maxSpeed = mySpeed;
                }
                tv.setText("\nCurrent Speed : " + (mySpeed * 3.6) + " km/h, Max Speed : "
                        + (maxSpeed * 3.6) + " km/h" + "\ncr Speed : " + (crSpeed * 3.6) + " km/h" + "\ni : " + i);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    }
}
