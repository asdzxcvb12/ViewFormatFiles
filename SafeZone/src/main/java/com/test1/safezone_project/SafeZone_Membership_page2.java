package com.test1.safezone_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SafeZone_Membership_page2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__membership_page2);
    }

    public void onClick_Duplication(View v) {
        EditText text_makeId = (EditText) findViewById(R.id.editText_makeId);
        String makeId = text_makeId.getText().toString();

        String id = "id";

        if(id == makeId){
            Toast.makeText(getApplicationContext(), "중복된 ID 입니다.\nID를 변경해주십시오.", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "사용하실수 있는 ID입니다.", Toast.LENGTH_LONG).show();

        }
    }

    public void onClick_Check(View v) {

        EditText text_makePw = (EditText) findViewById(R.id.editText_makePw);
        EditText text_makePw2 = (EditText) findViewById(R.id.editText_makePw2);

        TextView textView_a = (TextView) findViewById(R.id.textView_a);
        TextView textView_b = (TextView) findViewById(R.id.textView_b);

        String makePw = text_makePw.getText().toString();
        String makePw2 = text_makePw2.getText().toString();

        if(makePw == makePw2) {
            Intent nextPage = new Intent(getApplicationContext(), SafeZone_login.class);
            startActivity(nextPage);

        } else {
            Toast.makeText(getApplicationContext(), "PW와 PW확인번호가 다릅니다.\nPW를 다시 입력해주십시오.", Toast.LENGTH_LONG).show();
            textView_a.setText(String.valueOf(makePw));
            textView_b.setText(String.valueOf(makePw2));
        }
    }

    public void onClick_back(View v) {
        finish();
    }
}