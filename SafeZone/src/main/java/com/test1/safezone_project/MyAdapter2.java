package com.test1.safezone_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 2016-10-19.
 */
public class MyAdapter2 extends BaseAdapter { // 리스트 뷰의 아답타
    Context context;
    int layout;
    ArrayList<Array> ls;
    LayoutInflater inf;
    public MyAdapter2(Context context, int layout, ArrayList<Array> ls) {
        this.context = context;
        this.layout = layout;
        this.ls = ls;
        inf = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return ls.size();
    }
    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inf.inflate(layout, null);
        }
        ImageView iv = (ImageView)convertView.findViewById(R.id.listImg);
        TextView ln = (TextView)convertView.findViewById(R.id.listMenu);

        Array m = ls.get(position);
        iv.setImageResource(m.img);
        ln.setText(m.listName);

        return convertView;
    }
}
