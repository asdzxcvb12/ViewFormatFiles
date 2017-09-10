package com.test1.safezone_project;

/**
 * Created by user on 2016-11-25.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SelectWeather extends AppCompatActivity {

    ImageView weather1, weather2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_weather);

        weather1 = (ImageView) findViewById(R.id.weather1);
        weather2 = (ImageView) findViewById(R.id.weather2);

        onClick();
    }

    public void onClick() {

        weather1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weather1 = new Intent(getApplicationContext(), WeatherShort.class);
                startActivity(weather1);
            }
        });

        weather2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent weather2 = new Intent(getApplicationContext(), WeatherLong.class);
                startActivity(weather2);
            }
        });

    }
}
