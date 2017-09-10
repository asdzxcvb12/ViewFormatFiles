package com.test1.safezone_project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SafeZone_loginTS extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone_login_ts);

        GpsInfo gps = new GpsInfo(SafeZone_loginTS.this);

        TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        TextView tell = (TextView) findViewById(R.id.textView_id);
        TextView tell2 = (TextView) findViewById(R.id.textView_tell2);

        TextView textView_latitude = (TextView) findViewById(R.id.textView_pw);
        TextView textView_longitude = (TextView) findViewById(R.id.textView_ts);

        TextView textView_name = (TextView) findViewById(R.id.textView5);
        TextView textView_email = (TextView) findViewById(R.id.textView8);

        Intent login = getIntent();

        String name = login.getStringExtra("name");
        String email = login.getStringExtra("email");

        textView_name.setText(String.valueOf(name));
        textView_email.setText(String.valueOf(email));

        String telPhoneNo = telephony.getLine1Number();

        tell2.setText(String.valueOf(telPhoneNo));
        tell.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        tell.setText(String.valueOf(telPhoneNo));

        // GPS 사용유무 가져오기
        if (gps.isGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            textView_latitude.setText("latitude" + String.valueOf(latitude));
            textView_longitude.setText("longitude" + String.valueOf(longitude));
        }
    }

    public void onClick_back02(View v) {

        finish();
    }

}
