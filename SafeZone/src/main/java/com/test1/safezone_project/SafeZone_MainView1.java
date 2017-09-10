package com.test1.safezone_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


@SuppressLint("ValidFragment")
public class SafeZone_MainView1 extends Fragment {
    Context mContext;

    private double latitude;
    private double longitude;

    private TextView location = null;
    private TextView weather = null;
    private TextView weather2 = null;

    private TextView mDate;
    private TextView mTime;
    private final Handler mDateTimeHandler = new Handler();

    Calendar mcalendar = Calendar.getInstance();
    Date mcurMillis;
    int mcurYear;
    int mcurMonth;
    int mcurDay;
    int mcurHour;
    int mcurNoon;
    int mcurWeek;

    int mcurMinute;
    int mcurSecond;
    int test;

    String mNoon;
    String mstrDate;
    String mstrTime;
    String mstrWeek = null;
    String locationName = null;

    ImageView img;
    AnimationDrawable ani;

    public SafeZone_MainView1(Context context) {

        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_safe_zone__main_view1, null);

        GpsInfo gps = new GpsInfo(view.getContext());

        img=(ImageView)view.findViewById(R.id.img);

        if (gps.isGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        } else {
            latitude = 0;
            longitude = 0;
        }

        mDate = (TextView)view.findViewById(R.id.cal);
        mTime = (TextView)view.findViewById(R.id.time);
        location = (TextView)view.findViewById(R.id.location);
        weather = (TextView)view.findViewById(R.id.weather);

        displayCameraInformation();

        new ReceiveShortWeather().execute();

        get_cityname();


        return view;
    }

    public void displayCameraInformation(){
        displayCurrentTime();
    }

    private void displayCurrentTime(){
        TimerTask CurrentTimerTask = new TimerTask() {
            private String tag;
            public void run() {
                Update();
            }
        };
        Timer displayTimer = new Timer();
        displayTimer.schedule(CurrentTimerTask,0, 1000);
    }

    protected void Update(){
        mcalendar = Calendar.getInstance();
        mcurMillis = mcalendar.getTime();
        mcurYear = mcalendar.get(Calendar.YEAR);
        mcurMonth = mcalendar.get(Calendar.MONTH)+1;
        mcurDay = mcalendar.get(Calendar.DAY_OF_MONTH);
        mcurWeek = mcalendar.get(Calendar.DAY_OF_WEEK);
        mcurHour = mcalendar.get(Calendar.HOUR_OF_DAY);
        mcurNoon = mcalendar.get(Calendar.AM_PM);

        mcurMinute = mcalendar.get(Calendar.MINUTE);
        mcurSecond = mcalendar.get(Calendar.SECOND);

        if(mcurNoon == 0){
            mNoon = "AM";
        }else{
            mNoon = "PM";
            mcurHour -= 12;
        }

        Runnable updater = new Runnable() {
            public void run() {
                if((mcurMonth<10)&&(mcurDay<10)){
                    mstrDate = mcurYear+"/"+"0"+mcurMonth+"/"+"0"+mcurDay;
                }else if((mcurMonth<10)&&(mcurDay>=10)){
                    mstrDate = mcurYear+"/"+"0"+mcurMonth+"/"+mcurDay;
                }else if((mcurMonth>=10)&&(mcurDay<10)){
                    mstrDate = mcurYear+"/"+mcurMonth+"/"+"0"+mcurDay;
                }else{
                    mstrDate = mcurYear+"/"+mcurMonth+"/"+mcurDay;
                }

                if((mcurHour<10)&&(mcurMinute<10)&&(mcurSecond<10)){
                    mstrTime = mNoon+" "+"0"+mcurHour+":"+"0"+mcurMinute+":"+"0"+mcurSecond;
                }else if((mcurHour<10)&&(mcurMinute<10)&&(mcurSecond>=10)){
                    mstrTime = mNoon+" "+"0"+mcurHour+":"+"0"+mcurMinute+":"+mcurSecond;
                }else if((mcurHour<10)&&(mcurMinute>=10)&&(mcurSecond<10)){
                    mstrTime = mNoon+" "+"0"+mcurHour+":"+mcurMinute+":"+"0"+mcurSecond;
                }else if((mcurHour<10)&&(mcurMinute>=10)&&(mcurSecond>=10)){
                    mstrTime = mNoon+" "+"0"+mcurHour+":"+mcurMinute+":"+mcurSecond;
                }else if((mcurHour>=10)&&(mcurMinute<10)&&(mcurSecond<10)){
                    mstrTime = mNoon+" "+mcurHour+":"+"0"+mcurMinute+":"+"0"+mcurSecond;
                }else if((mcurHour>=10)&&(mcurMinute<10)&&(mcurSecond>=10)){
                    mstrTime = mNoon+" "+mcurHour+":"+"0"+mcurMinute+":"+mcurSecond;
                }else if((mcurHour>=10)&&(mcurMinute>=10)&&(mcurSecond<10)){
                    mstrTime = mNoon+" "+mcurHour+":"+mcurMinute+":"+"0"+mcurSecond;
                }else{
                    mstrTime = mNoon+" "+mcurHour+":"+mcurMinute+":"+mcurSecond;
                }

                if (mcurWeek == 1) {
                    mstrWeek = "일요일";
                } else if (mcurWeek == 2) {
                    mstrWeek = "월요일";
                } else if (mcurWeek == 3) {
                    mstrWeek = "화요일";
                } else if (mcurWeek == 4) {
                    mstrWeek = "수요일";
                } else if (mcurWeek == 5) {
                    mstrWeek = "목요일";
                } else if (mcurWeek == 6) {
                    mstrWeek = "금요일";
                } else if (mcurWeek == 7) {
                    mstrWeek = "토요일";
                }

                mDate.setText(mstrDate + " " + mstrWeek);
                mTime.setText(mstrTime);
            }
        };
        mDateTimeHandler.post(updater);
    }

    public void get_cityname() {

        String cityName = null;
        Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude, longitude, 1);
            if (addresses.size() > 0)

                Log.v("이거이거","1 :" + addresses.get(0).getAddressLine(0).toString()); //대한민국 대구광역시 동구 신서동 1013-5
            Log.v("이거이거","4 :" + addresses.get(0).getCountryName()); //대한민국
            Log.v("이거이거","6 :" + addresses.get(0).getAdminArea()); //대구광역시
            Log.v("이거이거","7 :" + addresses.get(0).getLocality()); //동구
            Log.v("이거이거","14:" + addresses.get(0).getThoroughfare()); //신서동
            Log.v("이거이거","2 :" + addresses.get(0).getFeatureName()); //1013-5
            Log.v("이거이거","3 :" + addresses.get(0).getLocale()); //ko_KR
            Log.v("이거이거","5 :" + addresses.get(0).getCountryCode()); // KR

            cityName = addresses.get(0).getAdminArea() + " " + addresses.get(0).getLocality() + " " + addresses.get(0).getThoroughfare();
            locationName = addresses.get(0).getAdminArea();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String loca = cityName;
        location.setText(loca);
    }

    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {


        ArrayList<ShortWeather> shortWeathers = new ArrayList<ShortWeather>();


        protected Long doInBackground(URL... urls) {

            String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4729025000";

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(url)
                    .build();


            Response response = null;


            try {
                response = client.newCall(request).execute();
                parseXML(response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(Long result) {
            String data = "";

                        data +="날씨:" + shortWeathers.get(0).getWfKor() + " " + "강수확률:" + shortWeathers.get(0).getPop()+"％" +
                                "\n            온도:" + shortWeathers.get(0).getTemp() + "℃" + " " +"습도:" + shortWeathers.get(0).getReh()+"％";

            weather.setText(data);
        }


        void parseXML(String xml) {
            try {
                String tagName = "";
                boolean onHour = false;
                boolean onDay = false;
                boolean onTem = false;
                boolean onWfKor = false;
                boolean onPop = false;
                boolean onEnd = false;
                boolean onReh = false;
                boolean isItemTag1 = false;
                int i = 0;


                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();


                parser.setInput(new StringReader(xml));


                int eventType = parser.getEventType();


                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        tagName = parser.getName();
                        if (tagName.equals("data")) {
                            shortWeathers.add(new ShortWeather());
                            onEnd = false;
                            isItemTag1 = true;
                        }
                    } else if (eventType == XmlPullParser.TEXT && isItemTag1) {
                        if (tagName.equals("hour") && !onHour) {
                            shortWeathers.get(i).setHour(parser.getText());
                            onHour = true;
                        }
                        if (tagName.equals("day") && !onDay) {
                            shortWeathers.get(i).setDay(parser.getText());
                            onDay = true;
                        }
                        if (tagName.equals("temp") && !onTem) {
                            shortWeathers.get(i).setTemp(parser.getText());
                            onTem = true;
                        }
                        if (tagName.equals("wfKor") && !onWfKor) {
                            shortWeathers.get(i).setWfKor(parser.getText());
                            onWfKor = true;
                        }
                        if (tagName.equals("pop") && !onPop) {
                            shortWeathers.get(i).setPop(parser.getText());
                            onPop = true;
                        }
                        if(tagName.equals("reh") && !onReh) {
                            shortWeathers.get(i).setReh(parser.getText());
                            onReh = true;
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (tagName.equals("s06") && onEnd == false) {
                            i++;
                            onHour = false;
                            onDay = false;
                            onTem = false;
                            onWfKor = false;
                            onPop = false;
                            onReh = false;
                            isItemTag1 = false;
                            onEnd = true;
                        }
                    }


                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}