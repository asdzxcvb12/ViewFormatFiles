package com.test1.safezone_project;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class FragmentRecord extends Fragment{

    static final String RECORDED_FILE = "/sdcard/recorded.mp4";

    ImageView startButton, stopButton;
    MediaRecorder recorder;
    int recordNum = 0;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_fragment_record
                , container, false);
        startButton = (ImageView) view.findViewById(R.id.recordStart_Btn);
        stopButton = (ImageView) view.findViewById(R.id.recordStop_Btn);



        startButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(recorder != null){
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                }// TODO Auto-generated method stub
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILE);
                try{
                    Toast.makeText(getView().getContext(),
                            "녹음을 시작합니다.", Toast.LENGTH_LONG).show();
                    recorder.prepare();
                    recorder.start();
                }catch (Exception ex){
                    Log.e("SampleAudioRecorder", "Exception : ", ex);
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(recorder == null)
                    return;
try {
    recorder.stop();
    recorder.release();
    recorder = null;
}catch (Exception e) {
    Log.e("error", "error", e);
}
                Toast.makeText(getView().getContext(),
                        "녹음이 중지되었습니다.", Toast.LENGTH_LONG).show();
                // TODO Auto-generated method stub


            }
        });

        return view;
    }

    public void onPause(){
        if(recorder != null){
            recorder.release();
            recorder = null;
        }
        super.onPause();

    }
}