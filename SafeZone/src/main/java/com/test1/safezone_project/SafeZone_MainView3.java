package com.test1.safezone_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;


@SuppressLint("ValidFragment")
public class SafeZone_MainView3 extends Fragment {
    ArrayList<MyInfo> al = new ArrayList<MyInfo>();
    ArrayList<Array> ls = new ArrayList<Array>();

    Context mContext;

    public SafeZone_MainView3(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_safe_zone__main_view3, null);

        if(al.isEmpty() && ls.isEmpty()) {
            al.add(new MyInfo("송광섭", R.drawable.police, "asdzxcvb12@gmail.com"));
            ls.add(new Array(R.drawable.alarm, "앱 소개"));
            ls.add(new Array(R.drawable.alarm, "친구목록 관리"));
            ls.add(new Array(R.drawable.alarm, "기기 정보"));
            ls.add(new Array(R.drawable.alarm, "안드로이드 버전"));
            ls.add(new Array(R.drawable.alarm, "데이터 연결 상태"));
            ls.add(new Array(R.drawable.alarm, "언어 설정"));
            ls.add(new Array(R.drawable.alarm, "웹사이트 연결"));
            ls.add(new Array(R.drawable.alarm, "관리자 문의"));
        }


        MyAdapter adapter = new MyAdapter(
                view.getContext(), // 현재화면의 제어권자
                R.layout.row,  // 리스트뷰의 한행의 레이아웃
                al);         // 데이터

        MyAdapter2 adapter2 = new MyAdapter2(
                view.getContext(),
                R.layout.row2,
                ls);

        ListView lv = (ListView)view.findViewById(R.id.listView1);
        ListView lv2 = (ListView) view.findViewById(R.id.listView2);
        lv.setAdapter(adapter);
        lv2.setAdapter(adapter2);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 상세정보 화면으로 이동하기(인텐트 날리기)
                // 1. 다음화면을 만든다

                // 3. Intent 객체를 생성하여 날린다
                Intent intent = new Intent(
                        view.getContext(), // 현재화면의 제어권자
                        SongDetail.class); // 다음넘어갈 화면

                // intent 객체에 데이터를 실어서 보내기
                // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                intent.putExtra("nickname", al.get(position).nickname);
                intent.putExtra("img", al.get(position).img);
                intent.putExtra("email", al.get(position).email);

                startActivity(intent);
            }
        });

        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 상세정보 화면으로 이동하기(인텐트 날리기)
                // 1. 다음화면을 만든다

                // 3. Intent 객체를 생성하여 날린다
                if(ls.get(position).listName.equals("앱 소개")) {
                    Intent intent = new Intent(view.getContext(), two.class);
                    // intent 객체에 데이터를 실어서 보내기
                    // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                    intent.putExtra("ln", ls.get(position).listName);
                    intent.putExtra("img", ls.get(position).img);

                    startActivity(intent);
                } else if(ls.get(position).listName.equals("친구목록 관리")) {
                    Intent intent = new Intent(view.getContext(), two.class);
                    // intent 객체에 데이터를 실어서 보내기
                    // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                    intent.putExtra("ln", ls.get(position).listName);
                    intent.putExtra("img", ls.get(position).img);

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(view.getContext(), two.class);
                    // intent 객체에 데이터를 실어서 보내기
                    // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                    intent.putExtra("ln", ls.get(position).listName);
                    intent.putExtra("img", ls.get(position).img);

                    startActivity(intent);
                }
            }
        });


        return view;
    }

}