package com.test1.safezone_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.*;

public class Setting extends AppCompatActivity {

    EditText hour, min, sec;
    TextView phone1, phone2, phone3;
    int time, Hour = 0, Min = 0, Sec = 0;
    String Call = null , Message = null, Phone1 = null, Phone2 = null, Phone3 = null, Time = null, H = null, M = null, S = null;

    CheckBox call, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        hour = (EditText) findViewById(R.id.hour);
        min = (EditText) findViewById(R.id.min);
        sec = (EditText) findViewById(R.id.sec);
        call = (CheckBox)findViewById(R.id.call);
        message = (CheckBox)findViewById(R.id.message);
        phone1 = (TextView)findViewById(R.id.phone1);
        phone2 = (TextView)findViewById(R.id.phone2);
        phone3 = (TextView)findViewById(R.id.phone3);

    }
    public void settingCancel (View v) {
        Log.d("체에크", "텍 : " + hour.getText().toString());
        Log.d("체에크", "힌 : " + hour.getHint().toString());
        if(hour.getText().toString().isEmpty()) {
            hour.setText("11");
        }
        Log.d("체에크", "텍 : " + min.getText().length());
        Log.d("체에크", "힌 : " + hour.getHint().toString());

    }

    public void settingOk (View v) {

        if(hour.getText().toString().isEmpty()) {
            Hour = 0;
        } else {
            Hour = Integer.parseInt(hour.getText().toString());
        }

        if(min.getText().toString().isEmpty()) {
            Min = 0;
        } else {
            Min = Integer.parseInt(min.getText().toString());
        }

        if(sec.getText().toString().isEmpty()) {
            Sec = 0;
        } else {
            Sec = Integer.parseInt(sec.getText().toString());
        }


        if(call.isChecked()) {
            Call = "true";
        } else {
            Call = "false";
        }

        if(message.isChecked()) {
            Message = "true";
        } else {
            Message = "false";
        }

        if(phone1.getText().toString().isEmpty()) {
            Phone1 = "000";
        } else {
            Phone1 = phone1.getText().toString();
        }
        if(phone2.getText().toString().isEmpty()) {
            Phone2 = "0000";
        } else {
            Phone2 = phone2.getText().toString();
        }
        if(phone3.getText().toString().isEmpty()) {
            Phone3 = "0000";
        } else {
            Phone3 = phone3.getText().toString();
        }

        if(Hour > 24) {
            Toast.makeText(getApplicationContext(), "시간은 24시를 넘기지 못합니다.", Toast.LENGTH_SHORT).show();
        } else if (Min > 59 || Sec > 59) {
            Toast.makeText(getApplicationContext(), "각 분/초는 59 초/분을 넘기지 못합니다.", Toast.LENGTH_SHORT).show();
        } else {
            time = Sec + (Min * 60) + (Hour * 3600);
            Time = String.valueOf(time);

            Intent values = new Intent(getApplicationContext(), Timer.class);
            values.putExtra("Time", Time);
            values.putExtra("Call", Call);
            values.putExtra("Message", Message);
            values.putExtra("Phone1", Phone1);
            values.putExtra("Phone2", Phone2);
            values.putExtra("Phone3", Phone3);
            startActivity(values);
        }
    }
}
