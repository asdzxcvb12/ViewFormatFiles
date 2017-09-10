package com.test1.safezone_project;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

    public class SafeZone_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone_login);

        findViewById(R.id.autoLogin).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v){
                printChecked(v);
            }
        });

        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        CheckBox cb = (CheckBox)findViewById(R.id.autoLogin);
        CheckBox check1 = (CheckBox) findViewById(R.id.autoLogin);
        Boolean chk1 = pref.getBoolean("check1", false);
        check1.setChecked(chk1);

        if(cb.isChecked()) {
            EditText edit1 = (EditText) findViewById(R.id.editText_id);
            String text = pref.getString("editText", "");
            edit1.setText(text);
        }
    }
        public void printChecked(View v) {
            CheckBox cb = (CheckBox)findViewById(R.id.autoLogin);
            String resultText = "";
            if(cb.isChecked()){
                resultText = "check";
            }
            if (resultText == "check"){
                resultText = "SAME";
            } else {
                resultText = "Different";
            }
            TextView tv = (TextView)findViewById(R.id.ex);
            tv.setText(resultText);
        }

        public void onStop(){

                super.onStop();
                SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                EditText edit1 = (EditText)findViewById(R.id.editText_id);
                CheckBox check1 = (CheckBox)findViewById(R.id.autoLogin);

                editor.putString("editText", edit1.getText().toString());
                editor.putBoolean("check1", check1.isChecked());

                editor.commit();
        }

        public void onClick_login(View v) {
            EditText text_id = (EditText) findViewById(R.id.editText_id);
            EditText text_pw = (EditText) findViewById(R.id.editText_pw);

            String id = text_id.getText().toString();
            String pw = text_pw.getText().toString();

            Intent login = new Intent(getApplicationContext(), SafeZone_Membership_page2.class);

            login.putExtra("입력한 아이디", id);
            login.putExtra("입력한 패스워드", pw);

            startActivity(login);
    }

        public void onClick_back01(View v) {
            Toast.makeText(getApplicationContext(), "첫 화면으로 돌아갑니다.", Toast.LENGTH_LONG).show();
            finish();
    }
        public void onClick_Membership(View v) {
            Intent Membership = new Intent(getApplicationContext(), SafeZone_Membership_page1.class);
            startActivity(Membership);
        }

        public void onClick_gps(View v) {
            Intent Membership = new Intent(getApplicationContext(), SafeZone_GPS.class);
            startActivity(Membership);
        }

        public void onClick_google(View v) {
            Intent Membership = new Intent(getApplicationContext(), SafeZone_Log.class);
            startActivity(Membership);
        }

        public void onClick_value(View v) {
            Intent Membership = new Intent(getApplicationContext(), SafeZone_loginTS.class);
            startActivity(Membership);
        }

        public void onClick_ts1(View v) {
            Intent Membership = new Intent(getApplicationContext(), SafeZone_testPage.class);
            startActivity(Membership);
        }

}
