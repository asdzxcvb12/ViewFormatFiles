package com.test1.safezone_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Locale;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class SafeZone_MainView2 extends Fragment {

    ListView list;
    ListViewAdapter adapter;
    EditText editsearch;
    String[] rank;
    String[] country;
    String[] population;
    ArrayList<WorldPopulation> arraylist = new ArrayList<WorldPopulation>();

    Context mContext;

    public SafeZone_MainView2(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_safe_zone__main_view2, null);

        // Generate sample data
        rank = new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        country = new String[] { "이철수", "차영희", "서동수",
                "김민수", "김우남", "이재철", "사오정", "저팔계",
                "손오공", "배지터" };

        population = new String[] { "asd22@gmail.com", "forever@gmail.com",
                "wmck@gmail.com", "dfw23@gmail.com", "qwea12@gmail.com", "turmf2@gmail.com",
                "rmo3@gmail.com", "mmr2323@gmail.com", "gnfldi@gmail.com", "ilill2@gmail.com" };

        // Locate the ListView in listview_main.xml
        list = (ListView) view.findViewById(R.id.listview);


        for (int i = 0; i < rank.length; i++)
        {
            WorldPopulation wp = new WorldPopulation(rank[i], country[i],
                    population[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(view.getContext(), arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) view.findViewById(R.id.search);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });

        return view;
    }
}