package ts.calculator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-22.
 */
public class CalDBManager extends SQLiteOpenHelper {

    public CalDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
        db.execSQL("CREATE TABLE CALCULATOR( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, result TEXT, calculation TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }

    public ArrayList<String> nameArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> name = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from CALCULATOR", null);
        while(cursor.moveToNext()) {
            name.add(cursor.getString(1));
        }

        return name;
    }

    public ArrayList<String> resultArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> result = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from CALCULATOR", null);
        while(cursor.moveToNext()) {
            result.add(cursor.getString(2));
        }

        return result;
    }

    public ArrayList<String> calculationArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> calculation = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from CALCULATOR", null);
        while(cursor.moveToNext()) {
            calculation.add(cursor.getString(3));
        }

        return calculation;
    }

}