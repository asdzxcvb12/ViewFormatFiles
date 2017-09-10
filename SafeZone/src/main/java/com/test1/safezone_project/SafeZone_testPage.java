package com.test1.safezone_project;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by user on 2016-07-06.
 */
public class SafeZone_testPage extends Activity implements
        OnClickListener {

    private Button button;
    private String Check = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone_test_page);

        button = (Button) findViewById(R.id.btn_alert);

        // 클릭 이벤트
        button.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alert:
                Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibe.vibrate(500);
                break;

            default:
                break;
        }
    }
    public void onClick_start(View v) {
        EditText vibrator = (EditText) findViewById(R.id.VIBRATOR);
        String strvibe = vibrator.getText().toString();
        int ivibe = 0;
        ivibe = Integer.parseInt(strvibe);

        for(int i = 0; i <= ivibe; i++) {
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(500);
            TextView test = (TextView) findViewById(R.id.textView_test);
            test.setText(String.valueOf(i));
        }
    }
    public void onClick_Start(View v) {
        while(true) {
            if(Check == "true") {
                break;
            }
            Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibe.vibrate(500);
        }
    }
    public void onClick_Stop(View v) {
        Check = "true";
    }
}
