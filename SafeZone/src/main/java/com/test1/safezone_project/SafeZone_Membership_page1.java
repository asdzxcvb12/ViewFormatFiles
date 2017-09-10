package com.test1.safezone_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


public class SafeZone_Membership_page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__membership_page1);
    }

    public void onClick_next(View v) {
        CheckBox cb01 = (CheckBox)findViewById(R.id.check01);
        CheckBox cb02 = (CheckBox)findViewById(R.id.check02);

        if(cb01.isChecked()){
            if(cb02.isChecked()) {
                Intent nextPage = new Intent(getApplicationContext(), SafeZone_Membership_page2.class);
                startActivity(nextPage);
            } else {
                Toast.makeText(getApplicationContext(), "두번째 약관에 동의해 주십시오.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "첫번째 약관에 동의해 주십시오.", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick_back(View v) {
        finish();
    }

}
