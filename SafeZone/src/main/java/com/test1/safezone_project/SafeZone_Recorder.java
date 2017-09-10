package com.test1.safezone_project;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import android.widget.*;

public class SafeZone_Recorder extends ActionBarActivity implements View.OnClickListener {


    Button btn[] = new Button[2];
    ViewPager viewPager = null;
    Thread thread = null;
    Handler handler = null;
    int p=0;	//페이지번호
    int v=1;	//화면 전환 뱡향
    TextView MusicName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__recorder);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        viewPager = (ViewPager)findViewById(R.id.viewPager);
        MyViewPagerAdapter adapter = new MyViewPagerAdapter(getSupportFragmentManager());


        viewPager.setAdapter(adapter);

        btn[0] = (Button)findViewById(R.id.btn_a);
        btn[1] = (Button)findViewById(R.id.btn_b);

        for(int i=0;i<btn.length; i++){
            btn[i].setOnClickListener(this);
        }

    }


    public void onClick(View v) {

        switch(v.getId()){

            case R.id.btn_a:

                viewPager.setCurrentItem(0);

                Toast.makeText(this,"A버튼", Toast.LENGTH_SHORT).show();

                break;

            case R.id.btn_b:

                viewPager.setCurrentItem(1);

                Toast.makeText(this,"B버튼", Toast.LENGTH_SHORT).show();
                btn[2].setTextColor(5);

                break;

            default:

                break;

        }
    }






}
