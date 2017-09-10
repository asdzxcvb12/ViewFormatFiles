package com.test1.safezone_project;

/**
 * Created by user on 2016-11-25.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.util.Log;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;

public class WeatherLong extends AppCompatActivity {

    TextView ws, wcb, wcn, wjb, wjn, wgb, wgn, ww, wg, ltv;
    String loName = null, doo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_long);

        ws = (TextView) findViewById(R.id.ws);
        wcb = (TextView) findViewById(R.id.wcb);
        wcn = (TextView) findViewById(R.id.wcn);
        wjb = (TextView) findViewById(R.id.wjb);
        wjn = (TextView) findViewById(R.id.wjn);
        wgb = (TextView) findViewById(R.id.wgb);
        wgn = (TextView) findViewById(R.id.wgn);
        ww = (TextView) findViewById(R.id.ww);
        wg = (TextView) findViewById(R.id.wg);
        ltv = (TextView) findViewById(R.id.ltv);

        onClick();
    }

    public void onClick() {
        ws.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("서울");
                adapter.add("인천");
                adapter.add("수원");
                adapter.add("파주");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "경기도";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
        wg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("춘천");
                adapter.add("원주");
                adapter.add("강릉");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "강원도";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
        wcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("청주");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "충북";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });

        wcn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("대전");
                adapter.add("서산");
                adapter.add("세종");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "충남";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
        wjb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("전주");
                adapter.add("군산");


                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "전북";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
        wjn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("광주");
                adapter.add("목포");
                adapter.add("여수");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "전남";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
        wgb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("대구");
                adapter.add("안동");
                adapter.add("포항");

                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "경북";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
        wgn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("부산");
                adapter.add("울산");
                adapter.add("창원");


                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "경남";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
        ww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherLong.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherLong.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("제주");
                adapter.add("서귀포");


                // 버튼 생성
                alertBuilder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dialog.dismiss();
                            }
                        });

                // Adapter 셋팅
                alertBuilder.setAdapter(adapter,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int id) {
                                String strName = adapter.getItem(id);
                                doo = "제주도";
                                loName = strName;
                                // AlertDialog 안에 있는 AlertDialog
                                new ReceiveLongWeather().execute();
                                dialog.dismiss();

                            }
                        });
                alertBuilder.show();

            }
        });
    }

    public class ReceiveLongWeather extends AsyncTask<URL, Integer, Long> {


        ArrayList<LongWeather> longWeathers = new ArrayList<LongWeather>();


        protected Long doInBackground(URL... urls) {
            String url = null;
            if(doo.equals("경기도")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109";
            } else if(doo.equals("강원도")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=105";
            } else if(doo.equals("충북")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=131";
            } else if(doo.equals("충남")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=133";
            } else if(doo.equals("전북")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=146";
            } else if(doo.equals("전남")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=156";
            } else if(doo.equals("경북")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=143";
            } else if(doo.equals("경남")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=159";
            } else if(doo.equals("제주도")) {
                url = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=184";
            }
            OkHttpClient client = new OkHttpClient();


            Request request = new Request.Builder()
                    .url(url)
                    .build();


            Response response = null;


            try {
                response = client.newCall(request).execute();
                parseWeekXML(response.body().string());
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }


        protected void onPostExecute(Long result) {
            String data = "";


            for(int i=0; i<longWeathers.size(); i++) {

                data += "" + (i+3) + "일 후: " + longWeathers.get(i).getTmEf() + "시 "
                      + "최저/고 온도: " + longWeathers.get(i).getTmn() + ", " +
                        longWeathers.get(i).getTmx() + " " +
                        longWeathers.get(i).getWf() + "\n";
            }


            ltv.setText(data);
        }

        void parseWeekXML(String xml) {
            try {
                String tagName = "";
                boolean onCity = false;
                boolean onTmEf = false;
                boolean onWf = false;
                boolean onTmn = false;
                boolean onTmx = false;
                boolean onEnd = false;
                boolean isItemTag1 = false;
                boolean check = false;
                int i = 0;


                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();


                parser.setInput(new StringReader(xml));


                int eventType = parser.getEventType();


                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        tagName = parser.getName();

                        if (tagName.equals("city")) {
                            eventType = parser.next();
                            Log.v("이거이거", parser.getText());
                            Log.v("이거이거", ""+(i+1)+" :===============================================city");


                            if (parser.getText().equals(loName)) {    // 파싱하고 싶은 지역 이름을 쓴다
                                onCity = true;
                                check = true;
                                Log.v("이거이거", "done!!ㅋ");
                            } else {
                                if (onCity) { // 이미 parsing을 끝냈을 경우
                                    Log.v("이거이거", "onCity Done!!!!!");
                                    break;
                                } else {        // 아직 parsing을 못했을 경우
                                    onCity = false;
                                    Log.v("이거이거", "onCity fail.......");
                                }
                            }
                        }


                        if (tagName.equals("data") && onCity) {

                            Log.v("이거이거", ""+(i+1)+" :===============================================data");

                            longWeathers.add(new LongWeather());
                            onEnd = false;
                            isItemTag1 = true;
                        }
                    } else if (eventType == XmlPullParser.TEXT && isItemTag1 && onCity) {

                        if (tagName.equals("tmEf") && !onTmEf) {

                            Log.v("이거이거", ""+(i+1)+" :===============================================tmEf");

                            longWeathers.get(i).setTmEf(parser.getText());
                            Log.v("이거이거", longWeathers.get(i).toString());

                            onTmEf = true;
                        }
                        if (tagName.equals("wf") && !onWf) {
                            longWeathers.get(i).setWf(parser.getText());
                            onWf = true;
                        }
                        if (tagName.equals("tmn") && !onTmn) {
                            longWeathers.get(i).setTmn(parser.getText());
                            onTmn = true;
                        }
                        if (tagName.equals("tmx") && !onTmx) {
                            longWeathers.get(i).setTmx(parser.getText());
                            onTmx = true;
                            check = true;
                        }
                    } else if (eventType == XmlPullParser.END_TAG && check == true) {
                        if (tagName.equals("reliability") && onEnd == false) {

                            Log.v("이거이거", ""+(i+1)+" : !!!!!!!!!!!!!!!!!!!!!!!!!!!!!reliability");

                            i++;
                            onTmEf = false;
                            onWf = false;
                            onTmn = false;
                            onTmx = false;
                            isItemTag1 = false;
                            check = false;
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
