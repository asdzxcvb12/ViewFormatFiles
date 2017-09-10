package com.test1.safezone_project;

/**
 * Created by user on 2016-11-25.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class WeatherShort extends AppCompatActivity {

    Button search1, search2, sh;
    TextView stv, gugu, sisi;
    String sido = null, sido2 = null, number = null, day = null, addsi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_short);

        search1 = (Button) findViewById(R.id.search1);
        search2 = (Button) findViewById(R.id.search2);
        sh = (Button) findViewById(R.id.sh);
        stv = (TextView) findViewById(R.id.stv);
        sisi = (TextView) findViewById(R.id.sisi);
        gugu = (TextView) findViewById(R.id.gugu);

        onClick();

    }

    public void onClick () {

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        WeatherShort.this);
                alertBuilder.setIcon(R.drawable.weather_burned);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                // List Adapter 생성
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        WeatherShort.this,
                        android.R.layout.select_dialog_singlechoice);
                adapter.add("강원도");
                adapter.add("경기도");
                adapter.add("경상남도");
                adapter.add("경상북도");
                adapter.add("광주광역시");
                adapter.add("대구광역시");
                adapter.add("대전광역시");
                adapter.add("부산광역시");
                adapter.add("서울특별시");
                adapter.add("세종특별자치시");
                adapter.add("울산광역시");
                adapter.add("인청광역시");
                adapter.add("전라남도");
                adapter.add("전라북도");
                adapter.add("제주특별자치도");
                adapter.add("충청남도");
                adapter.add("충청북도");

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
                                sido = strName;
                                Log.d("체크체크","cc : " + sido);

                                if(sido.equals("세종특별자치시")) {
                                    sisi.setText("세종시");
                                } else if(sido.equals("제주특별자치도")) {
                                    sisi.setText("제주도");
                                } else {
                                    sisi.setText(sido);
                                }
                                // AlertDialog 안에 있는 AlertDialog
                                                        dialog.dismiss();
                            }
                        });
                alertBuilder.show();

            }
        });

        search2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sido == null) {
                    Toast.makeText(getApplicationContext(), "시/도를 검색해주십시오.", Toast.LENGTH_SHORT).show();
                } else {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                            WeatherShort.this);
                    alertBuilder.setIcon(R.drawable.weather_burned);
                    alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                    // List Adapter 생성
                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            WeatherShort.this,
                            android.R.layout.select_dialog_singlechoice);
                    if(sido.equals("강원도")) {
                        adapter.add("동해시");
                        adapter.add("삼척시");
                        adapter.add("속초시");
                        adapter.add("양구군");
                        adapter.add("양양군");
                        adapter.add("영월군");
                        adapter.add("원주시");
                        adapter.add("인제군");
                        adapter.add("정선군");
                        adapter.add("철원군");
                        adapter.add("춘천시");
                    } else if(sido.equals("경기도")) {
                        adapter.add("가평군");
                        adapter.add("고양시");
                        adapter.add("과천시");
                        adapter.add("광명시");
                        adapter.add("구리시");
                        adapter.add("군포시");
                        adapter.add("김포시");
                        adapter.add("남양주시");
                        adapter.add("동두천시");
                        adapter.add("부천시");
                        adapter.add("성남시");
                        adapter.add("시흥시");
                        adapter.add("안산시");
                        adapter.add("안성시");
                        adapter.add("양주시");
                        adapter.add("양평군");
                        adapter.add("여주시");
                        adapter.add("연천군");
                        adapter.add("오산시");
                        adapter.add("용인시");
                        adapter.add("의왕시");
                        adapter.add("의정부시");
                        adapter.add("이천시");
                        adapter.add("파주시");
                        adapter.add("평택시");
                        adapter.add("포천시");
                        adapter.add("하남시");
                        adapter.add("화성시");
                    } else if(sido.equals("경상남도")) {
                        adapter.add("거제시");
                        adapter.add("거창군");
                        adapter.add("고성군");
                        adapter.add("김해시");
                        adapter.add("남해군");
                        adapter.add("밀양시");
                        adapter.add("사천시");
                        adapter.add("산청군");
                        adapter.add("양산시");
                        adapter.add("의령군");
                        adapter.add("진주시");
                        adapter.add("창녕군");
                        adapter.add("창원시");
                        adapter.add("통영시");
                        adapter.add("하동군");
                        adapter.add("함안군");
                        adapter.add("함양군");
                        adapter.add("합천군");
                    } else if(sido.equals("경상북도")) {
                        adapter.add("경산시");
                        adapter.add("경주시");
                        adapter.add("고령군");
                        adapter.add("구미시");
                        adapter.add("군위군");
                        adapter.add("밀양시");
                        adapter.add("김천시");
                        adapter.add("문경시");
                        adapter.add("봉화군");
                        adapter.add("상주시");
                        adapter.add("성주군");
                        adapter.add("안동시");
                        adapter.add("영덕군");
                        adapter.add("영양군");
                        adapter.add("영주시");
                        adapter.add("영천시");
                        adapter.add("예천군");
                        adapter.add("울릉군");
                        adapter.add("울진군");
                        adapter.add("의성군");
                        adapter.add("청도군");
                        adapter.add("청송군");
                        adapter.add("칠곡군");
                        adapter.add("포항시");
                    } else if(sido.equals("광주광역시")) {
                        adapter.add("광산구");
                        adapter.add("남구");
                        adapter.add("동구");
                        adapter.add("북구");
                        adapter.add("서구");
                    } else if(sido.equals("대구광역시")) {
                        adapter.add("남구");
                        adapter.add("달서구");
                        adapter.add("달성군");
                        adapter.add("동구");
                        adapter.add("북구");
                        adapter.add("서구");
                        adapter.add("수성구");
                        adapter.add("중구");
                    } else if(sido.equals("대전광역시")) {
                        adapter.add("대덕구");
                        adapter.add("동구");
                        adapter.add("서구");
                        adapter.add("유성구");
                        adapter.add("중구");
                    } else if(sido.equals("부산광역시")) {
                        adapter.add("강서구");
                        adapter.add("금정구");
                        adapter.add("기장군");
                        adapter.add("남구");
                        adapter.add("동구");
                        adapter.add("동래구");
                        adapter.add("부산진구");
                        adapter.add("북구");
                        adapter.add("사상구");
                        adapter.add("사하구");
                        adapter.add("서구");
                        adapter.add("수영구");
                        adapter.add("연제구");
                        adapter.add("중구");
                        adapter.add("해운대구");
                    } else if(sido.equals("서울특별시")) {
                        adapter.add("개포동");
                        adapter.add("논현동");
                        adapter.add("대치동");
                        adapter.add("도곡동");
                        adapter.add("삼성동");
                        adapter.add("세곡동");
                        adapter.add("수서동");
                        adapter.add("신사동");
                        adapter.add("압구정동");
                        adapter.add("역삼동");
                        adapter.add("일원동");
                        adapter.add("일원본동");
                        adapter.add("청담동");
                    } else if(sido.equals("세종특별자치시")) {
                        adapter.add("세종특별자치시");
                    } else if(sido.equals("울산광역시")) {
                        adapter.add("남구");
                        adapter.add("동구");
                        adapter.add("북구");
                        adapter.add("울주군");
                        adapter.add("중구");
                    } else if(sido.equals("인천광역시")) {
                        adapter.add("강화군");
                        adapter.add("계양구");
                        adapter.add("남구");
                        adapter.add("남동구");
                        adapter.add("동구");
                        adapter.add("부평구");
                        adapter.add("서구");
                        adapter.add("연수구");
                        adapter.add("웅진군");
                        adapter.add("중구");
                    } else if(sido.equals("전라남도")) {
                        adapter.add("강진군");
                        adapter.add("고흥군");
                        adapter.add("곡성군");
                        adapter.add("광양시");
                        adapter.add("구례군");
                        adapter.add("나주시");
                        adapter.add("담양군");
                        adapter.add("목포시");
                        adapter.add("무안군");
                        adapter.add("보성군");
                        adapter.add("순천시");
                        adapter.add("신안군");
                        adapter.add("여수시");
                        adapter.add("영광군");
                        adapter.add("염암군");
                        adapter.add("완도군");
                        adapter.add("장성군");
                        adapter.add("장흥군");
                        adapter.add("진도군");
                        adapter.add("함평군");
                        adapter.add("해남군");
                        adapter.add("화순군");
                    } else if(sido.equals("전라북도")) {
                        adapter.add("고창군");
                        adapter.add("군산시");
                        adapter.add("김제시");
                        adapter.add("남원시");
                        adapter.add("무주군");
                        adapter.add("부안군");
                        adapter.add("순창군");
                        adapter.add("완주군");
                        adapter.add("익산시");
                        adapter.add("임실군");
                        adapter.add("장수군");
                        adapter.add("전주시");
                        adapter.add("정읍시");
                        adapter.add("진안군");
                    } else if(sido.equals("제주특별자치도")) {
                        adapter.add("서귀포시");
                        adapter.add("이어도");
                        adapter.add("제주시");
                    } else if(sido.equals("충청남도")) {
                        adapter.add("계룡시");
                        adapter.add("공주시");
                        adapter.add("금산군");
                        adapter.add("논산시");
                        adapter.add("당진시");
                        adapter.add("보령시");
                        adapter.add("보여군");
                        adapter.add("서산시");
                        adapter.add("서천군");
                        adapter.add("아산시");
                        adapter.add("예천군");
                        adapter.add("천안시");
                        adapter.add("청양군");
                        adapter.add("태안군");
                        adapter.add("홍성군");
                    } else if(sido.equals("충청북도")) {
                        adapter.add("괴산군");
                        adapter.add("단양군");
                        adapter.add("보은군");
                        adapter.add("영동군");
                        adapter.add("옥천군");
                        adapter.add("음성군");
                        adapter.add("제천시");
                        adapter.add("증편군");
                        adapter.add("진천군");
                        adapter.add("청주시");
                        adapter.add("충주시");
                    }

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
                                    sido2 = strName;
                                    if(sido2.equals("세종특별자치시")) {
                                        gugu.setText("세종시");
                                    } else {
                                        gugu.setText(sido2);
                                    }

                                    // AlertDialog 안에 있는 AlertDialog
                                    dialog.dismiss();

                                }
                            });
                    alertBuilder.show();

                }
            }
        });

        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addsi = sido + sido2;
                if(sido != null && sido2 != null) {
                    if(sido.equals("강원도")){
                        if(sido2.equals("강릉시")){
                            number = "4215000000";
                        } else if(sido2.equals("고성군")) {
                            number = "4282000000";
                        } else if(sido2.equals("동해시")) {
                            number = "4217000000";
                        } else if(sido2.equals("삼척시")) {
                            number = "4223000000";
                        } else if(sido2.equals("속초시")) {
                            number = "4221000000";
                        } else if(sido2.equals("양구군")) {
                            number = "4280000000";
                        } else if(sido2.equals("양양군")) {
                            number = "4283000000";
                        } else if(sido2.equals("영월군")) {
                            number = "4275000000";
                        } else if(sido2.equals("원주시")) {
                            number = "4213000000";
                        } else if(sido2.equals("인제군")) {
                            number = "4281000000";
                        } else if(sido2.equals("정선군")) {
                            number = "4277000000";
                        } else if(sido2.equals("철원군")) {
                            number = "4278000000";
                        } else if(sido2.equals("춘천시")) {
                            number = "4211000000";
                        } else if(sido2.equals("강원도태백시")) {
                            number = "4219000000";
                        } else if(sido2.equals("평창군")) {
                            number = "4276000000";
                        } else if(sido2.equals("홍천군")) {
                            number = "4272000000";
                        } else if(sido2.equals("화천군")) {
                            number = "4279000000";
                        } else if(sido2.equals("횡성군")) {
                            number = "4273000000";
                        }
                    } else if(sido.equals("경기도")) {
                        if(sido2.equals("가평군")) {
                            number = "4182000000";
                        } else if(sido2.equals("고양시")) {
                            number = "4128100000";
                        } else if(sido2.equals("과천시")) {
                            number = "4129000000";
                        } else if(sido2.equals("광명시")) {
                            number = "4121000000";
                        } else if(sido2.equals("광주시")) {
                            number = "4161000000";
                        } else if(sido2.equals("구리시")) {
                            number = "4131000000";
                        } else if(sido2.equals("군포시")) {
                            number = "4141000000";
                        } else if(sido2.equals("김포시")) {
                            number = "4157000000";
                        } else if(sido2.equals("남양주시")) {
                            number = "4136000000";
                        } else if(sido2.equals("동두천시")) {
                            number = "4125000000";
                        } else if(sido2.equals("부천시")) {
                            number = "4119000000";
                        } else if(sido2.equals("성남시")) {
                            number = "4113500000";
                        } else if(sido2.equals("시흥시")) {
                            number = "4139000000";
                        } else if(sido2.equals("안산시")) {
                            number = "4127300000";
                        } else if(sido2.equals("안성시")) {
                            number = "4155000000";
                        } else if(sido2.equals("안양시")) {
                            number = "4117300000";
                        } else if(sido2.equals("양주시")) {
                            number = "4163000000";
                        } else if(sido2.equals("양평군")) {
                            number = "4183000000";
                        } else if(sido2.equals("여주시")) {
                            number = "4167000000";
                        } else if(sido2.equals("연천군")) {
                            number = "4180000000";
                        } else if(sido2.equals("오산시")) {
                            number = "4137000000";
                        } else if(sido2.equals("용인시")) {
                            number = "4146300000";
                        } else if(sido2.equals("의왕시")) {
                            number = "4143000000";
                        } else if(sido2.equals("의정부시")) {
                            number = "4115000000";
                        } else if(sido2.equals("이천시")) {
                            number = "4150000000";
                        } else if(sido2.equals("파주시")) {
                            number = "4148000000";
                        } else if(sido2.equals("평택시")) {
                            number = "4122000000";
                        } else if(sido2.equals("포천시")) {
                            number = "4165000000";
                        } else if(sido2.equals("하남시")) {
                            number = "4145000000";
                        } else if(sido2.equals("화성시")) {
                            number = "4159000000";
                        }
                    } else if(sido.equals("경상남도")) {
                        if(sido2.equals("거제시")) {
                            number = "4831000000";
                        } else  if(sido2.equals("거창군")) {
                            number = "4888000000";
                        } else  if(sido2.equals("고성군")) {
                            number = "4882000000";
                        } else  if(sido2.equals("김해시")) {
                            number = "4825000000";
                        } else  if(sido2.equals("남해군")) {
                            number = "4884000000";
                        } else  if(sido2.equals("밀양시")) {
                            number = "4827000000";
                        } else  if(sido2.equals("사천시")) {
                            number = "4824000000";
                        } else  if(sido2.equals("산청군")) {
                            number = "4886000000";
                        } else  if(sido2.equals("양산시")) {
                            number = "4833000000";
                        } else  if(sido2.equals("의령군")) {
                            number = "4872000000";
                        } else  if(sido2.equals("진주시")) {
                            number = "4817000000";
                        } else  if(sido2.equals("창녕군")) {
                            number = "4874000000";
                        } else  if(sido2.equals("창원시")) {
                            number = "4812500000";
                        } else  if(sido2.equals("통영시")) {
                            number = "4822000000";
                        } else  if(sido2.equals("하동군")) {
                            number = "4885000000";
                        } else  if(sido2.equals("함안군")) {
                            number = "4873000000";
                        } else  if(sido2.equals("함양군")) {
                            number = "4887000000";
                        } else  if(sido2.equals("합천군")) {
                            number = "4889000000";
                        }
                    } else if(sido.equals("경상북도")){
                        if(sido2.equals("경산시")) {
                            number = "4729000000";
                        } else  if(sido2.equals("경주시")) {
                            number = "4713000000";
                        } else  if(sido2.equals("고령군")) {
                            number = "4783000000";
                        } else  if(sido2.equals("구미시")) {
                            number = "4719000000";
                        } else  if(sido2.equals("군위군")) {
                            number = "4772000000";
                        } else  if(sido2.equals("김천시")) {
                            number = "4715000000";
                        } else  if(sido2.equals("문경시")) {
                            number = "4728000000";
                        } else  if(sido2.equals("봉화군")) {
                            number = "4792000000";
                        } else  if(sido2.equals("상주시")) {
                            number = "4725000000";
                        } else  if(sido2.equals("성주군")) {
                            number = "4784000000";
                        } else  if(sido2.equals("안동시")) {
                            number = "4717000000";
                        } else  if(sido2.equals("영덕군")) {
                            number = "4777000000";
                        } else  if(sido2.equals("영양군")) {
                            number = "4776000000";
                        } else  if(sido2.equals("영주시")) {
                            number = "4721000000";
                        } else  if(sido2.equals("영천시")) {
                            number = "4723000000";
                        } else  if(sido2.equals("예천군")) {
                            number = "4790000000";
                        } else  if(sido2.equals("울릉군")) {
                            number = "4794000000";
                        } else  if(sido2.equals("울진군")) {
                            number = "4793000000";
                        } else  if(sido2.equals("의성군")) {
                            number = "4773000000";
                        } else  if(sido2.equals("청도군")) {
                            number = "4782000000";
                        } else  if(sido2.equals("청송군")) {
                            number = "4775000000";
                        } else  if(sido2.equals("칠곡군")) {
                            number = "4785000000";
                        }
                    } else if(sido.equals("광주광역시")) {
                        if(sido2.equals("광산구")) {
                            number = "2920000000";
                        } else  if(sido2.equals("남구")) {
                            number = "2915500000";
                        } else  if(sido2.equals("동구")) {
                            number = "2911000000";
                        } else  if(sido2.equals("북구")) {
                            number = "2917000000";
                        } else  if(sido2.equals("서구")) {
                            number = "2914000000";
                        }
                    } else if(sido.equals("대구광역시")) {
                        if(sido2.equals("남구")) {
                            number = "2720000000";
                        } else  if(sido2.equals("달서구")) {
                            number = "2729000000";
                        } else  if(sido2.equals("달성군")) {
                            number = "2771000000";
                        } else  if(sido2.equals("동구")) {
                            number = "2714000000";
                        } else  if(sido2.equals("북구")) {
                            number = "2723000000";
                        } else  if(sido2.equals("서구")) {
                            number = "2717000000";
                        } else  if(sido2.equals("수성구")) {
                            number = "2726000000";
                        } else  if(sido2.equals("중구")) {
                            number = "2711000000";
                        }
                    } else if(sido.equals("대전광역시")) {
                        if (sido2.equals("대덕구")) {
                            number = "3023000000";
                        } else if (sido2.equals("동구")) {
                            number = "3011000000";
                        } else if (sido2.equals("서구")) {
                            number = "3017000000";
                        } else if (sido2.equals("유성구")) {
                            number = "3020000000";
                        } else if (sido2.equals("중구")) {
                            number = "3014000000";
                        }
                    }else if (sido.equals("부산광역시")) {
                        if (sido2.equals("강서구")) {
                            number = "2644000000";
                        } else if (sido2.equals("금정구")) {
                            number = "2641000000";
                        } else if (sido2.equals("기장군")) {
                            number = "2671000000";
                        } else if (sido2.equals("남구")) {
                            number = "2629000000";
                        } else if (sido2.equals("동구")) {
                            number = "2617000000";
                        } else if (sido2.equals("동래구")) {
                            number = "2626000000";
                        } else if (sido2.equals("부산진구")) {
                            number = "2623000000";
                        } else if (sido2.equals("북구")) {
                            number = "2632000000";
                        } else if (sido2.equals("사상구")) {
                            number = "2653000000";
                        } else if (sido2.equals("사하구")) {
                            number = "2638000000";
                        } else if (sido2.equals("서구")) {
                            number = "2614000000";
                        } else if (sido2.equals("수영구")) {
                            number = "2650000000";
                        } else if (sido2.equals("연제구")) {
                            number = "2647000000";
                        } else if (sido2.equals("영도구")) {
                            number = "2620000000";
                        } else if (sido2.equals("중구")) {
                            number = "2611000000";
                        } else if (sido2.equals("해운대구")) {
                            number = "2635000000";
                        }
                    } else if(sido.equals("서울특별시")) {
                        if(sido2.equals("개포동")) {
                            number = "1168066000";
                        } else if (sido2.equals("논현동")) {
                            number = "1168052100";
                        } else if (sido2.equals("대치동")) {
                            number = "1168060000";
                        } else if (sido2.equals("도곡동")) {
                            number = "1168065500";
                        } else if (sido2.equals("삼성동")) {
                            number = "1168058000";
                        } else if (sido2.equals("세곡동")) {
                            number = "1168070000";
                        } else if (sido2.equals("수서동")) {
                            number = "1168075000";
                        } else if (sido2.equals("신사동")) {
                            number = "1168051000";
                        } else if (sido2.equals("압구정동")) {
                            number = "1168054500";
                        } else if (sido2.equals("역삼동")) {
                            number = "1168073000";
                        } else if (sido2.equals("일원본동")) {
                            number = "1168072000";
                        } else if (sido2.equals("청담동")) {
                            number = "1168056500";
                        }
                    } else if(sido.equals("세종특별자치시")) {
                        if (sido2.equals("세종특별자치시")) {
                            number = "3611000000";
                        }
                    } else if(sido.equals("울산광역시")) {
                        if (sido2.equals("남구")) {
                            number = "3114000000";
                        } else if (sido2.equals("동구")) {
                            number = "3117000000";
                        } else if (sido2.equals("북구")) {
                            number = "3120000000";
                        } else if (sido2.equals("울주군")) {
                            number = "3171000000";
                        } else if (sido2.equals("중구")) {
                            number = "3111000000";
                        }
                    } else if(sido.equals("인천광역시")) {
                        if (sido2.equals("강화군")) {
                            number = "2871000000";
                        } else if (sido2.equals("계양구")) {
                            number = "2824500000";
                        } else if (sido2.equals("북구")) {
                            number = "3120000000";
                        } else if (sido2.equals("남구")) {
                            number = "2817000000";
                        } else if (sido2.equals("남동구")) {
                            number = "2820000000";
                        } else if (sido2.equals("동구")) {
                            number = "2814000000";
                        } else if (sido2.equals("부평구")) {
                            number = "2823700000";
                        } else if (sido2.equals("서구")) {
                            number = "2826000000";
                        } else if (sido2.equals("연수구")) {
                            number = "2818500000";
                        } else if (sido2.equals("옹진군")) {
                            number = "2872000000";
                        } else if (sido2.equals("중구")) {
                            number = "2811000000";
                        }
                    } else if(sido.equals("전라남도")) {
                        if (sido2.equals("강진군")) {
                            number = "4681000000";
                        } else if (sido2.equals("고흥군")) {
                            number = "4677000000";
                        } else if (sido2.equals("곡성군")) {
                            number = "4672000000";
                        } else if (sido2.equals("광양시")) {
                            number = "4623000000";
                        } else if (sido2.equals("구례군")) {
                            number = "4673000000";
                        } else if (sido2.equals("나주시")) {
                            number = "4617000000";
                        } else if (sido2.equals("담양군")) {
                            number = "4671000000";
                        } else if (sido2.equals("목포시")) {
                            number = "4611000000";
                        } else if (sido2.equals("무안군")) {
                            number = "4684000000";
                        } else if (sido2.equals("보성군")) {
                            number = "4678000000";
                        } else if (sido2.equals("순천시")) {
                            number = "4615000000";
                        } else if (sido2.equals("신안군")) {
                            number = "4691000000";
                        } else if (sido2.equals("여수시")) {
                            number = "4613000000";
                        } else if (sido2.equals("영광군")) {
                            number = "4687000000";
                        } else if (sido2.equals("영암군")) {
                            number = "4683000000";
                        } else if (sido2.equals("완도군")) {
                            number = "4689000000";
                        } else if (sido2.equals("장성군")) {
                            number = "4688000000";
                        } else if (sido2.equals("장흥군")) {
                            number = "4680000000";
                        } else if (sido2.equals("진도군")) {
                            number = "4690000000";
                        } else if (sido2.equals("함평군")) {
                            number = "4686000000";
                        } else if (sido2.equals("해남군")) {
                            number = "4682000000";
                        } else if (sido2.equals("화순군")) {
                            number = "4679000000";
                        }
                    } else if(sido.equals("전라북도")) {
                        if (sido2.equals("고창군")) {
                            number = "4579000000";
                        } else if (sido2.equals("군산시")) {
                            number = "4513000000";
                        } else if (sido2.equals("김제시")) {
                            number = "4521000000";
                        } else if (sido2.equals("남원시")) {
                            number = "4519000000";
                        } else if (sido2.equals("무주군")) {
                            number = "4573000000";
                        } else if (sido2.equals("부안군")) {
                            number = "4580000000";
                        } else if (sido2.equals("순창군")) {
                            number = "4577000000";
                        } else if (sido2.equals("완주군")) {
                            number = "4571000000";
                        } else if (sido2.equals("익산시")) {
                            number = "4514000000";
                        } else if (sido2.equals("임실군")) {
                            number = "4575000000";
                        } else if (sido2.equals("장수군")) {
                            number = "4574000000";
                        } else if (sido2.equals("전주시")) {
                            number = "4511300000";
                        } else if (sido2.equals("정읍시")) {
                            number = "4518000000";
                        } else if (sido2.equals("진안군")) {
                            number = "4572000000";
                        }
                    } else if(sido.equals("제주특별자치도")) {
                        if (sido2.equals("서귀포시")) {
                            number = "5013000000";
                        } else if (sido2.equals("이어도")) {
                            number = "5019000000";
                        } else if (sido2.equals("제주시")) {
                            number = "5011000000";
                        }
                    } else if(sido.equals("충청남도")) {
                        if (sido2.equals("계룡시")) {
                            number = "4425000000";
                        } else if (sido2.equals("공주시")) {
                            number = "4415000000";
                        } else if (sido2.equals("금산군")) {
                            number = "4471000000";
                        } else if (sido2.equals("논산시")) {
                            number = "4423000000";
                        } else if (sido2.equals("당진시")) {
                            number = "4427000000";
                        } else if (sido2.equals("보령시")) {
                            number = "4418000000";
                        } else if (sido2.equals("부여군")) {
                            number = "4476000000";
                        } else if (sido2.equals("서산시")) {
                            number = "4421000000";
                        } else if (sido2.equals("서천군")) {
                            number = "4477000000";
                        } else if (sido2.equals("아산시")) {
                            number = "4420000000";
                        } else if (sido2.equals("예산군")) {
                            number = "4481000000";
                        } else if (sido2.equals("천안시")) {
                            number = "4413100000";
                        } else if (sido2.equals("청양군")) {
                            number = "4479000000";
                        } else if (sido2.equals("태안군")) {
                            number = "4482500000";
                        } else if (sido2.equals("홍성군")) {
                            number = "4480000000";
                        }
                    } else if(sido.equals("충청북도")) {
                        if (sido2.equals("괴산군")) {
                            number = "4376000000";
                        } else if (sido2.equals("단양군")) {
                            number = "4380000000";
                        } else if (sido2.equals("보은군")) {
                            number = "4372000000";
                        } else if (sido2.equals("영동군")) {
                            number = "4374000000";
                        } else if (sido2.equals("옥천군")) {
                            number = "4373000000";
                        } else if (sido2.equals("음성군")) {
                            number = "4377000000";
                        } else if (sido2.equals("제천시")) {
                            number = "4315000000";
                        } else if (sido2.equals("증평군")) {
                            number = "4374500000";
                        } else if (sido2.equals("진천군")) {
                            number = "4375000000";
                        } else if (sido2.equals("청주시")) {
                            number = "4311100000";
                        } else if (sido2.equals("충주시")) {
                            number = "4313000000";
                        }
                    }
                    new ReceiveShortWeather().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "지역을 모두 선택하고 버튼을 눌러주십시오.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class ReceiveShortWeather extends AsyncTask<URL, Integer, Long> {


        ArrayList<ShortWeather> shortWeathers = new ArrayList<ShortWeather>();


        protected Long doInBackground(URL... urls) {


            String url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=" + number;


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

            for(int i=0; i<shortWeathers.size(); i++) {
                if(shortWeathers.get(i).getDay().equals("0")) {
                    day = "오늘";
                } else if (shortWeathers.get(i).getDay().equals("1")) {
                    day = "내일";
                } else if (shortWeathers.get(i).getDay().equals("2")) {
                    day = "모래";
                }
                Log.d("ddd", shortWeathers.get(i).getDay().toString());


                Log.d("ddd", "통과");

                data += "시간: " + shortWeathers.get(i).getHour() + " " +
                        "날짜: " + day + " " +
                        "온도: " + shortWeathers.get(i).getTemp() + " " +
                        "상태: " + shortWeathers.get(i).getWfKor() + " " +
                        "강수확률: " + shortWeathers.get(i).getPop() + "\n";



            }

            stv.setText(data);
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
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (tagName.equals("s06") && onEnd == false) {
                            i++;
                            onHour = false;
                            onDay = false;
                            onTem = false;
                            onWfKor = false;
                            onPop = false;
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
