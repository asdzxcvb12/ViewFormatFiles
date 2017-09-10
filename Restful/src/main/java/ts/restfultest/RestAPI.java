package ts.restfultest;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by user on 2017-03-04.
 */
public interface RestAPI {

    interface Melon {

        @Headers({
                "Accept: application/json",
                "appKey: 6522d8b8-a71a-3cf6-a4c3-4cad3af49db3"
        })
        @GET("/melon/charts/realtime?&version=1")
        Call<MelonDataModel> createTask(
                @Query("count") String count,
                @Query("page") String page);
    }

    interface Wheather {

        @Headers({
                "Accept: application/json",
                "appKey: 6522d8b8-a71a-3cf6-a4c3-4cad3af49db3"
        })
        @GET("/weather/current/hourly?&version=1")
        Call<WeatherDataModel> createTask(
                @Query("lat") String lat,
                @Query("lon") String lon
        );


    }

    interface TMap {

        @Headers({
                "Accept: application/json",
                "appKey: 6522d8b8-a71a-3cf6-a4c3-4cad3af49db3"
        })
        @FormUrlEncoded
        @POST("/tmap/routes?&version=1")
        Call<TmapDataModel> createTask(
                @Field("startY") String Start_lat,
                @Field("startX") String Start_lon,
                @Field("endY") String End_lat,
                @Field("endX") String End_lon,
                @Field("reqCoordType") String reqCoordType);
    }

    Retrofit retrofitHttp = new Retrofit.Builder()
            .baseUrl("http://apis.skplanetx.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    Retrofit retrofitHttps = new Retrofit.Builder()
            .baseUrl("https://apis.skplanetx.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}