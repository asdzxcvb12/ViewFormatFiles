package com.test1.safezone_project;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;

public class SafeZone_Loading extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__loading);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        },2000);
    }
}
