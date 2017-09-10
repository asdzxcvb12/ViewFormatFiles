package com.test1.safezone_project;
        import java.io.File;
        import java.util.ArrayList;
        import java.util.List;
        import android.app.AlertDialog;
        import android.app.ListActivity;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.TextView;

public class sdcard extends ListActivity {
    private List<String> item = null;
    private List<String> path = null;
    private String root = "/sdcard";
    private TextView mPath;
    private String fileName = null;
    String selectName = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sdcard);
        mPath = (TextView) findViewById(R.id.path);
        getDir(root);
    }

    private void getDir(String dirPath) {
        mPath.setText("Location: " + dirPath);
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

        for (int i = 0; i < files.length; i++) {
            File file = files[i];

            path.add(file.getPath());

            if(file.getName().length() > 3) {

                if (file.getName().substring(file.getName().length() - 3, file.getName().length()).equals("mp4"))
                    item.add(file.getName());
            } else {

            }
        }
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, R.layout.roww, item);
        setListAdapter(fileList);
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        File file = new File(path.get(position));
        fileName = file.getName();


        if (file.isDirectory()) {
            if (file.canRead())
                getDir(path.get(position));
            else {
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.charactor)
                        .setTitle("[" + file.getName() + "] folder can't be read!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                // TODO Auto-generated method stub
                            }
                        }).show();
            }
        } else {
            new AlertDialog.Builder(this)
                    .setIcon(R.drawable.charactor)
                    .setTitle("[" + fileName + "]")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            select();
                            Intent select = new Intent(getApplicationContext(), SafeZone_Recorder.class);
                            startActivity(select);
                            if(select().substring(select().length()-3,select().length()).equals("mp4")) {
                                Log.d("확인확인", select().substring(select().length() - 3, select().length()));
                            } else {
                                Log.d("확인확인", select().toString());
                            }
                            // TODO Auto-generated method stub
                        }
                    }).show();
        }
    }
    public String select() {
        selectName = fileName;
        return selectName;
    }
}

