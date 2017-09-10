package com.test1.safezone_project;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class SafeZone_Camera extends AppCompatActivity {

    private Camera camera = null;
    CheckBox sosCheck;
    private TextView tv;
    private SeekBar seekbar;
    private boolean mode = false, thread = true;
    private int seek = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__camera);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        camera = Camera.open();

        sosCheck = (CheckBox) findViewById(R.id.sosCheck);
        tv = (TextView) findViewById(R.id.flashText);
        seekbar = (SeekBar) findViewById(R.id.selectTime);

        seekbar.setOnSeekBarChangeListener(seekBarChangeListener);

    }

    public void check(View v) {
        if(sosCheck.isChecked()){
            Log.d("ch","gogogo");
            tv.setVisibility(android.view.View.VISIBLE);
            seekbar.setVisibility(android.view.View.VISIBLE);
            mode = true;

        } else {
            tv.setVisibility(android.view.View.INVISIBLE);
            seekbar.setVisibility(android.view.View.INVISIBLE);
            mode = false;

        }
    }

    private void setZoomLevel(int level) {
        Toast.makeText(this, "현재 주기는 : " + String.valueOf(level) + "초입니다.",
                Toast.LENGTH_SHORT).show();
        seek = level * 1000;
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener =
            new SeekBar.OnSeekBarChangeListener() {

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

    private void opencamera() {
        Camera.Parameters param = camera.getParameters();
        param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(param);
        camera.startPreview();
    }

    private void closecamera() {
        Camera.Parameters param = camera.getParameters();
        param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(param);
        camera.stopPreview();
    }


    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            opencamera();
            Log.d("check","check");
        }
    };

    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            closecamera();
        }
    };



    public void flashbutton(View v) {

            ToggleButton togglebutton = (ToggleButton) findViewById(R.id.toggleButton);

            if(mode == false) {
                // Perform action on clicks
                if (togglebutton.isChecked()) { //토클버튼이 ON인 상태인 경우
                    Camera.Parameters param = camera.getParameters();
                    param.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(param);
                    camera.startPreview();
                } else { //토글버튼이 OFF인 경우
                    Camera.Parameters param = camera.getParameters();
                    param.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(param);
                    camera.stopPreview();
                }
            }else if(mode == true) {

                final Thread myThread = new Thread(new Runnable() {
                    public void run() {
                        while (thread == true) {
                            try {

                                handler1.sendMessage(handler1.obtainMessage());

                                if (seek != 0) {
                                    Thread.sleep(seek);
                                } else if (seek == 0) {
                                    Thread.sleep(1000);
                                }
                                handler2.sendMessage(handler2.obtainMessage());
                                Log.d("run","run");

                            } catch (Throwable t) {
                                Log.e("error", "error");
                            }
                        }
                    }
                });
                Log.d("run","out");
                if(togglebutton.isChecked()) {
                    if(thread == false) {
                        thread = true;
                    }
                    myThread.start();
                } else {
                    thread = false;
                }
            }
        }

    @Override
    protected void onPause() {
        super.onPause();
        if (camera != null) {
            camera.release(); // 이 앱이 사라질때 카메라 객체를 박살낸다.
        }
    }

}
