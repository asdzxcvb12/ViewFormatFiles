package com.test1.safezone_project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.*;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Timer extends Activity {

    private double latitude;
    private double longitude;

    Context mContext;
    private int sec = 0, min = 0, hour = 0, time = 0;
    private boolean thread = true, callCk = true, messageCk = true, thread2 = false;
    String Phone1 = "null", Phone2 = "null", Phone3 = "null", Call = "null", Message = "null", Time = "0", PhoneNum = null, PhoneCall = null, SmsTx = "도와주세요.";
    TextView goTime;
    Button start, purse, stop, stt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        mContext = this;

        GpsInfo gps = new GpsInfo(Timer.this);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (gps.isGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
        } else {
            latitude = 0;
            longitude = 0;
        }

        goTime = (TextView) findViewById(R.id.goTime);
        start = (Button) findViewById(R.id.timerStart);
        purse = (Button) findViewById(R.id.timerPuase);
        stop = (Button) findViewById(R.id.timerStop);
        stt = (Button) findViewById(R.id.stt);

        Intent get = getIntent();
if(get.hasExtra("Time")) {
        Time = get.getStringExtra("Time");
        Call = get.getStringExtra("Call");
        Message = get.getStringExtra("Message");
        Phone1 = get.getStringExtra("Phone1");
        Phone2 = get.getStringExtra("Phone2");
        Phone3 = get.getStringExtra("Phone3");

            time = Integer.parseInt(Time);

    PhoneNum = Phone1 + Phone2 + Phone3;
    PhoneCall = Phone1 + "-" + Phone2 + "-" + Phone3;

    if(!PhoneNum.equals("00000000000")  && Call.equals("true")) {
        callCk = true;
    } else {
        callCk = false;
    }

    if(!PhoneNum.equals("00000000000")  && Message.equals("true") ) {
        messageCk = true;
    } else {
        messageCk = false;
    }

            hour = time / 3600;
            min = (time % 3600) / 60;
            sec = (time % 3600) % 60;

            if(hour >= 10 && min >= 10 && sec >= 10) {
                goTime.setText(String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec));
            } else if(hour >= 10 && min >= 10 && sec < 10) {
                goTime.setText(String.valueOf(hour) + ":" + String.valueOf(min) + ":0" + String.valueOf(sec));
            } else if(hour >= 10 && min < 10 && sec >= 10) {
                goTime.setText(String.valueOf(hour) + ":0" + String.valueOf(min) + ":" + String.valueOf(sec));
            } else if(hour >= 10 && min < 10 && sec < 10) {
                goTime.setText(String.valueOf(hour) + ":0" + String.valueOf(min) + ":0" + String.valueOf(sec));
            } else if(hour < 10 && min >= 10 && sec >= 10) {
                goTime.setText("0" + String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec));
            } else if(hour < 10 && min >= 10 && sec < 10) {
                goTime.setText("0" + String.valueOf(hour) + ":" + String.valueOf(min) + ":0" + String.valueOf(sec));
            } else if(hour < 10 && min < 10 && sec >= 10) {
                goTime.setText("0" + String.valueOf(hour) + ":0" + String.valueOf(min) + ":" + String.valueOf(sec));
            } else if(hour < 10 && min < 10 && sec < 10) {
                goTime.setText("0" + String.valueOf(hour) + ":0" + String.valueOf(min) + ":0" + String.valueOf(sec));
            }
            Log.d("체크체크", "Time : "+Time);
            Log.d("체크체크", "time : "+time);
            Log.d("체크체크", "Call : "+Call);
            Log.d("체크체크", "Message : "+Message);
            Log.d("체크체크", "Phone1 : "+Phone1);
            Log.d("체크체크", "Phone2 : "+Phone2);
            Log.d("체크체크", "Phone3 : "+Phone3);

    if(callCk == true) {
        Log.d("체크체크", "true");
    } else {
        Log.d("체크체크", "false");
    }
        }
        message();
        onClick();
    }

    public void message() {
        String cityName = null;
        Geocoder gcd = new Geocoder(this, Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = gcd.getFromLocation(latitude,
                    longitude, 1);
            if (addresses.size() > 0)

                Log.v("이거이거","1 :" + addresses.get(0).getAddressLine(0).toString()); //대한민국 대구광역시 동구 신서동 1013-5
            Log.v("이거이거","4 :" + addresses.get(0).getCountryName()); //대한민국
            Log.v("이거이거","6 :" + addresses.get(0).getAdminArea()); //대구광역시
            Log.v("이거이거","7 :" + addresses.get(0).getLocality()); //동구
            Log.v("이거이거","14:" + addresses.get(0).getThoroughfare()); //신서동
            Log.v("이거이거","2 :" + addresses.get(0).getFeatureName()); //1013-5
            Log.v("이거이거","3 :" + addresses.get(0).getLocale()); //ko_KR
            Log.v("이거이거","5 :" + addresses.get(0).getCountryCode()); // KR

            cityName = addresses.get(0).getAdminArea().toString() + " " + addresses.get(0).getLocality().toString() + " " + addresses.get(0).getThoroughfare().toString()
            + " " + addresses.get(0).getFeatureName().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SmsTx = "도와주세요\n" + "경도:" + longitude + "\n위도:" + latitude + "\n위치:"
                + cityName;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };

    Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateThread2();
        }
    };

    public void onClick() {
        final Thread secThread = new Thread(new Runnable() {
            public void run() {
                while (thread2) {
                    try {
                        handler1.sendMessage(handler1.obtainMessage());
                        Thread.sleep(1000);
                        Log.d("확인확인","run");

                    } catch (Throwable t) {
                        Log.e("error", "error");
                    }
                }
            }
        });

        // Perform action on clicks
        final Thread myThread = new Thread(new Runnable() {
            public void run() {
                while (thread) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                        Log.d("확인확인", "gg");


                    } catch (Throwable t) {
                        Log.e("error", "error");
                    }

                    Log.d("확인확인", "나온거?");
                }
                secThread.start();
                stt.setVisibility(android.view.View.VISIBLE);
                Log.d("확인확인", "나온거??");
            }

        });
        if(myThread.isAlive()){
            Log.d("살아있나?","ㅇㅇ");
        }

        Log.d("확인확인","out");

        start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (time == 0) {
                    Toast.makeText(getApplicationContext(), "시간을 선택해주십시오.", Toast.LENGTH_SHORT).show();
                } else {
                    if (thread == false) {
                        thread = true;
                    }

                    if (thread == true) {
                        try {
                            myThread.start();
                        } catch (Exception e) {
                            Log.e("error", "error", e);
                        }
                    }
                }
            }


        });

        purse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              thread2 = false;
                stt.setVisibility(android.view.View.INVISIBLE);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread = false;
                hour = 0;
                min = 0;
                sec = 0;
                time = 0;
                goTime.setText("0" + String.valueOf(hour) + ":0" + String.valueOf(min) + ":0" + String.valueOf(sec));
            }
        });
    }

    private void updateThread2() {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(500);
    }

    private void updateThread() {
        time--;

        hour = time / 3600;
        min = (time % 3600) / 60;
        sec = (time % 3600) % 60;

        if(hour >= 10 && min >= 10 && sec >= 10) {
            goTime.setText(String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec));
        } else if(hour >= 10 && min >= 10 && sec < 10) {
            goTime.setText(String.valueOf(hour) + ":" + String.valueOf(min) + ":0" + String.valueOf(sec));
        } else if(hour >= 10 && min < 10 && sec >= 10) {
            goTime.setText(String.valueOf(hour) + ":0" + String.valueOf(min) + ":" + String.valueOf(sec));
        } else if(hour >= 10 && min < 10 && sec < 10) {
            goTime.setText(String.valueOf(hour) + ":0" + String.valueOf(min) + ":0" + String.valueOf(sec));
        } else if(hour < 10 && min >= 10 && sec >= 10) {
            goTime.setText("0" + String.valueOf(hour) + ":" + String.valueOf(min) + ":" + String.valueOf(sec));
        } else if(hour < 10 && min >= 10 && sec < 10) {
            goTime.setText("0" + String.valueOf(hour) + ":" + String.valueOf(min) + ":0" + String.valueOf(sec));
        } else if(hour < 10 && min < 10 && sec >= 10) {
            goTime.setText("0" + String.valueOf(hour) + ":0" + String.valueOf(min) + ":" + String.valueOf(sec));
        } else if(hour < 10 && min < 10 && sec < 10) {
            goTime.setText("0" + String.valueOf(hour) + ":0" + String.valueOf(min) + ":0" + String.valueOf(sec));
        }

        if(time <= 360) {
            goTime.setTextColor(Color.BLUE);
        } else if(time <= 60) {
            goTime.setTextColor(Color.RED);
        }

        if(time == 0) {
            thread = false;
            if(messageCk == true && callCk == false) {
                sendSMS(PhoneNum, SmsTx);
            } else if(messageCk == false && callCk == true){
                startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + PhoneCall)));
            } else if(messageCk == true && callCk == true){
                sendSMS(PhoneNum, SmsTx);
                startActivity(new Intent("android.intent.action.CALL", Uri.parse("tel:" + PhoneCall)));
            } else {
                thread2 = true;
            }
        }

    }


    public void timerSetting (View v) {
        Intent setting = new Intent(getApplicationContext(), Setting.class);
        startActivity(setting);
    }

    public void sendSMS(String smsNumber, String smsText){
        PendingIntent sentIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_SENT_ACTION"), 0);
        PendingIntent deliveredIntent = PendingIntent.getBroadcast(this, 0, new Intent("SMS_DELIVERED_ACTION"), 0);

        /**
         * SMS가 발송될때 실행
         * When the SMS massage has been sent
         */
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch(getResultCode()){
                    case Activity.RESULT_OK:
                        // 전송 성공
                        Toast.makeText(mContext, "전송 완료", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        // 전송 실패
                        Toast.makeText(mContext, "전송 실패", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        // 서비스 지역 아님
                        Toast.makeText(mContext, "서비스 지역이 아닙니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        // 무선 꺼짐
                        Toast.makeText(mContext, "무선(Radio)가 꺼져있습니다", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        // PDU 실패
                        Toast.makeText(mContext, "PDU Null", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT_ACTION"));

        /**
         * SMS가 도착했을때 실행
         * When the SMS massage has been delivered
         */
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()){
                    case Activity.RESULT_OK:
                        // 도착 완료
                        Toast.makeText(mContext, "SMS 도착 완료", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        // 도착 안됨
                        Toast.makeText(mContext, "SMS 도착 실패", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_DELIVERED_ACTION"));

        SmsManager mSmsManager = SmsManager.getDefault();
        mSmsManager.sendTextMessage(smsNumber, null, smsText, sentIntent, deliveredIntent);
    }
}
