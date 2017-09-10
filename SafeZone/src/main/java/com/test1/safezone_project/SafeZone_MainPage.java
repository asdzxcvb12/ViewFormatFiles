package com.test1.safezone_project;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;


public class SafeZone_MainPage extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private TextView mStatus;
    private static final int RC_SIGN_IN = 9001;
    private Button btn_disconnect;
    private SignInButton sign_in_button;
    String telPhoneNo, i = null, p = null;
    ArrayList<memberList> memberList= new ArrayList<memberList>();
    TextView id, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_zone__main_page);

        startActivity(new Intent(this, SafeZone_Loading.class));

        TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        telPhoneNo = telephony.getLine1Number();

        id = (TextView) findViewById(R.id.editText_id);

        pw = (TextView) findViewById(R.id.editText_pw);
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        CheckBox cb = (CheckBox)findViewById(R.id.autoLogin);
        CheckBox check1 = (CheckBox) findViewById(R.id.autoLogin);
        Boolean chk1 = pref.getBoolean("check1", false);
        check1.setChecked(chk1);

        if(cb.isChecked()) {
            EditText edit1 = (EditText) findViewById(R.id.editText_id);
            String text = pref.getString("editText", "");
            edit1.setText(text);
        }

        //UI 가져오기
        sign_in_button = (SignInButton) findViewById(R.id.sign_in_button);
        btn_disconnect = (Button) findViewById(R.id.btn_discon);
        mStatus = (TextView) findViewById(R.id.status);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API)
                .addScope(new Scope(Scopes.PROFILE))
                .build();
        //로그인

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleApiClient.connect();
            }
        });
        //로그아웃

        btn_disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGoogleApiClient.isConnected()) {
                    Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
                    Plus.AccountApi.revokeAccessAndDisconnect(mGoogleApiClient);
                    mGoogleApiClient.disconnect();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        Account account = new Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String name = currentPerson.getDisplayName();
        String Name = name;
        String email = accountName;
        String tel = telPhoneNo;

        if (currentPerson != null) {


            insertToDatabase(Name, tel, email);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://chldnska23.dothome.co.kr/member/terms.php"));

            intent.setPackage("com.android.chrome");

            startActivity(intent);

        } else {
            Log.w("Main", "error");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, RC_SIGN_IN);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
        }
    }

    public void onClick_next(View v) {
        Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
        String accountName = Plus.AccountApi.getAccountName(mGoogleApiClient);
        String name = currentPerson.getDisplayName();

        if (currentPerson != null) {
        } else {
            Intent login = new Intent(getApplicationContext(), SafeZone_loginTS.class);
            startActivity(login);
        }
    }

    private void insertToDatabase(String Name, String tel, String email){

        class InsertData extends AsyncTask<String, Void, String>{
            ProgressDialog loading;



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SafeZone_MainPage.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String Name = (String)params[0];
                    String tel = (String)params[1];
                    String email = (String)params[2];

                    String link="http://chldnska23.dothome.co.kr/php/insertphp.php";
                    String data  = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(Name, "UTF-8");
                    data += "&" + URLEncoder.encode("tel", "UTF-8") + "=" + URLEncoder.encode(tel, "UTF-8");
                    data += "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");

                    Log.v("이거이거", "연결준비");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    Log.v("이거이거", "연결!!!");

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    Log.v("이거이거", "연결@@@@@@@");

                    wr.write( data );
                    wr.flush();

                    Log.v("이거이거", "데이터 넘겨줌!");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        Log.v("이거이거", "dddddddddddddddddddddddddddddddddddddddd");
                        sb.append(line);
                        Log.v("이거이거", "llllllllllllllllllllll");
                        break;
                    }
                    Log.v("이거이거", "finish");
                    return sb.toString();

                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        Log.v("이거이거", "gogo?");
        task.execute(Name,tel,email);
        Log.v("이거이거", "값"+Name+""+tel+""+email);
        Log.v("이거이거", "gogo");
    }

    private class phpDown extends AsyncTask<String, Integer,String>{

        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            Log.v("이거이거", "jsonHtml");

            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                Log.v("이거이거", "conn2");

                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    Log.v("이거이거", "!!!!!!!!!!!!!!!!!!!!!");
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        Log.v("이거이거", "@@@@@@@@@@@@@@@@");
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.getMessage();
            }
            return jsonHtml.toString();

        }

        protected void onPostExecute(String str){
            String user_id;

            String nick_name;

            String name;

            String birth;

            String sex;

            String tel;

            String email;

            String pw;

            try{



                JSONObject root = new JSONObject(str);

                JSONArray ja = root.getJSONArray("results");

                for(int i=0; i<ja.length(); i++){

                    JSONObject jo = ja.getJSONObject(i);

                    user_id = jo.getString("user_id");

                    nick_name = jo.getString("nick_name");

                    name = jo.getString("name");

                    birth = jo.getString("birth");

                    sex = jo.getString("sex");

                    tel = jo.getString("tel");

                    email = jo.getString("email");

                    pw = jo.getString("pw");

                    memberList.add(new memberList(user_id,nick_name,name,birth,sex,tel,email,pw));

                }

            }catch(JSONException e){

                e.printStackTrace();

            }


            i = memberList.get(0).getData(1);

            p = memberList.get(0).getData(6);


        }

    }

    public void onStop(){

        super.onStop();
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        EditText edit1 = (EditText)findViewById(R.id.editText_id);
        CheckBox check1 = (CheckBox)findViewById(R.id.autoLogin);

        editor.putString("editText", edit1.getText().toString());
        editor.putBoolean("check1", check1.isChecked());

        editor.commit();
    }

    public void onClick_login(View v) {



            Intent login = new Intent(getApplicationContext(), SafeZone_MainPageView.class);

            startActivity(login);

    }

}
