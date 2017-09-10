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
 * Created by user on 2016-10-12.
 */
class MyAdapter extends BaseAdapter { // 리스트 뷰의 아답타
    Context context;
    int layout;
    ArrayList<MyInfo> al;
    LayoutInflater inf;
    public MyAdapter(Context context, int layout, ArrayList<MyInfo> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
        inf = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return al.size();
    }
    @Override
    public Object getItem(int position) {
        return al.get(position);
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
        TextView tvName = (TextView)convertView.findViewById(R.id.listMenu);
        TextView tvEmail = (TextView)convertView.findViewById(R.id.eMail);

        MyInfo m = al.get(position);
        iv.setImageResource(m.img);
        tvName.setText(m.nickname);
        tvEmail.setText(m.email);

        return convertView;
    }
}
