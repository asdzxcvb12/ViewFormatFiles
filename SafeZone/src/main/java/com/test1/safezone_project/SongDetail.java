package com.test1.safezone_project;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

/**
 * Created by user on 2016-10-12.
 */
public class SongDetail extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songdetail);

        TextView tvTitle = (TextView)findViewById(R.id.listMenu);
        TextView tvArtist = (TextView)findViewById(R.id.eMail);
        ImageView iv = (ImageView)findViewById(R.id.listImg);

        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        tvTitle.setText(intent.getStringExtra("nickname"));
        tvArtist.setText(intent.getStringExtra("email"));
        iv.setImageResource(intent.getIntExtra("img", 0));
    } // end of onCreate
} // end of class