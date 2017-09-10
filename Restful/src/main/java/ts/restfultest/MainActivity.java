package ts.restfultest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*
        //노래 100 곡 1 페이지
        RestAPI.Melon melon = RestAPI.retrofitHttp.create(RestAPI.Melon.class);
        Call<MelonDataModel> melonDataModelCall = melon.createTask("100", "1");
        melonDataModelCall.enqueue(new Callback<MelonDataModel>() {
            @Override
            public void onResponse(Response<MelonDataModel> response) {
                Log.d(TAG, "현재의 1위곡 : " + response.body().getMelon().getSongs().getSong().get(0).getSongName());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
*/
        //여의도 좌표 lat 126.9305, lon 37.53431
        RestAPI.Wheather wheather = RestAPI.retrofitHttp.create(RestAPI.Wheather.class);
        Call<WeatherDataModel> weatherDataModelCall = wheather.createTask("37.53431", "126.9305");

        weatherDataModelCall.enqueue(new Callback<WeatherDataModel>() {
            @Override
            public void onResponse(Response<WeatherDataModel> response) {
                Log.d(TAG, "현재 여의도 기온 : " + response.body().getWeather().getHourly().get(0).getTemperature().getTc());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        //송도 센트럴파크 lat 37.504497, lon 127.048996
        RestAPI.TMap tMap = RestAPI.retrofitHttps.create(RestAPI.TMap.class);
        Call<TmapDataModel> tmapDataModelCall = tMap.createTask("37.504497", "127.048996", "38.191579", "128.60303", "WGS84GEO");

        tmapDataModelCall.enqueue(new Callback<TmapDataModel>() {
            @Override
            public void onResponse(Response<TmapDataModel> response) {
                Log.d(TAG, "송도 센트럴파크에서 속초까지의 거리 (M) : " + String.valueOf(response.body().getFeatures().get(0).getProperties().getTotalDistance()));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


}