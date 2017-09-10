package com.test1.safezone_project;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.ArrayAdapter;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Fragment_Player extends Fragment{

    String FILE_NAME = "- 플레이어 -";
    String check = null;

    static final String RECORDED_FILE = "/sdcard/";

    private boolean playAu = false;
    MediaPlayer player;

    SeekBar seekBar;
    TextView text, MusicName;

    int playbackPosition = 0;

    ImageView img, button, button2, button3;
    AnimationDrawable ani;


    private List<String> item = null;
    private List<String> path = null;
    private String root = "/sdcard";
    private TextView mPath;
    private String fileName = null;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,

                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_fragment__player, container, false);

        button = (ImageView) view.findViewById(R.id.S);
        button2 = (ImageView) view.findViewById(R.id.T);
        button3 = (ImageView) view.findViewById(R.id.R);

        text = (TextView)view.findViewById(R.id.text1);

        seekBar = (SeekBar)view.findViewById(R.id.playbar);

        img=(ImageView)view.findViewById(R.id.img);

        if(fileName == null) {

            seekBar.setVisibility(ProgressBar.INVISIBLE);

        } else {
            Log.d("확인확인", fileName);
            seekBar.setVisibility(ProgressBar.VISIBLE);

        }



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser) {
                    player.seekTo(progress);
                }
                int m = progress / 60000;
                int s = (progress % 60000) / 1000;
                String strTime = String.format("%02d:%02d", m, s);
                text.setText(strTime);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(fileName == null) {
                    Toast.makeText(getView().getContext(), "재생할 음악 파일을 선택하여 주십시오.", Toast.LENGTH_SHORT).show();
                }else {
                    if (playAu == true) {
                        try {
                            rePlayAudio(RECORDED_FILE + fileName);
                            String t = String.valueOf(playbackPosition);
                            Log.d("확크인", t);

                            Toast.makeText(getView().getContext(), "음악파일 재생 재시작됨.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {

                        try {
                            playAudio(RECORDED_FILE + fileName);
                            ani.start();

                            Toast.makeText(getView().getContext(), "음악파일 재생 시작됨.", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                seekBar.setMax(player.getDuration());

                if(!ani.isRunning()) {

                    ani = (AnimationDrawable) img.getDrawable();

                    ani.start();
                }

                Thread();
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(player != null){
                    playbackPosition = player.getCurrentPosition();
                    playAu = true;
                    player.stop();
                    if(ani.isRunning()) ani.stop();
                    try {
                        player.prepare();
                    } catch (Exception e){
                        Log.e("error" , "error1");
                    }
                    Toast.makeText(getView().getContext(), "음악 파일 재생 중지됨.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String dirPath = root;
                item = new ArrayList<String>();
                path = new ArrayList<String>();
                File f = new File(dirPath);
                File[] files = f.listFiles();
                if (!dirPath.equals(root)) {
                    item.add(root);
                    path.add(root);
                    item.add("../");
                    path.add(f.getParent());
                }


                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(
                        getView().getContext());
                alertBuilder.setIcon(R.drawable.pink_run_001);
                alertBuilder.setTitle("항목중에 하나를 선택하세요.");

                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getView().getContext(),
                        android.R.layout.select_dialog_singlechoice);
                // List Adapter 생성

                for (int i = 0; i < files.length; i++) {
                    File file = files[i];

                    path.add(file.getPath());

                    if(file.getName().length() > 3) {

                        if (file.getName().substring(file.getName().length() - 3, file.getName().length()).equals("mp4"))
                            adapter.add(file.getName());
                    } else {

                    }
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

                                // AlertDialog 안에 있는 AlertDialog
                                String strName = adapter.getItem(id);
                                fileName = strName;
                                AlertDialog.Builder innBuilder = new AlertDialog.Builder(
                                        getView().getContext());
                                innBuilder.setMessage(strName);
                                innBuilder.setTitle("당신이 선택한 것은 ");
                                innBuilder
                                        .setPositiveButton(
                                                "확인",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int which) {
                                                        if(player == null) {
                                                            seekBar.setVisibility(ProgressBar.VISIBLE);

                                                        } else {
                                                            player.stop();
                                                            if(ani.isRunning()) ani.stop();

                                                            try {
                                                                player.prepare();
                                                            } catch (Exception e) {
                                                                Log.e("error", "error1");
                                                            }
                                                            player.seekTo(0);
                                                            Toast.makeText(getView().getContext(), fileName, Toast.LENGTH_SHORT).show();
                                                        }
                                                        dialog.dismiss();
                                                    }
                                                });
                                innBuilder.show();
                            }
                        });
                alertBuilder.show();

            }
        });

        return view;
    }

    public void Thread(){
        Runnable task = new Runnable(){


            public void run(){
                // 음악이 재생중일때
                while(player.isPlaying()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    seekBar.setProgress(player.getCurrentPosition());
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    private void rePlayAudio(String url) throws Exception{

        try {
            player.start();
        } catch (Exception e) {
            Log.e("error" , "error2");
        }
    }


    private void playAudio(String url) throws Exception{
        killMediaPlayer();

        player = new MediaPlayer();
        player.setDataSource(url);
        player.prepare();
        player.start();
    }

    public void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    public void onPause(){
        if (player != null){
            player.release();
            player = null;
        }

        super.onPause();

    }

    private void killMediaPlayer() {
        if(player != null){
            try {
                playAu = false;
                player.release();
            } catch(Exception e){
                e.printStackTrace();
            }
        }

    }

}

