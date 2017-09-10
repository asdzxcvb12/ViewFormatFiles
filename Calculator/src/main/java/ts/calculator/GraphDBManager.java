package ts.calculator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-22.
 */
public class GraphDBManager extends SQLiteOpenHelper {

    public GraphDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // create table 테이블명 (컬럼명 타입 옵션);
        db.execSQL("CREATE TABLE GRAPH( _id INTEGER PRIMARY KEY AUTOINCREMENT, maxX TEXT, maxY TEXT, name TEXT, graph1 TEXT, graph2 TEXT, graph3 TEXT, graph1Val TEXT, graph2Val TEXT, graph3Val TEXT);");
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

    public ArrayList<String> maxXList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> maxX = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            maxX.add(cursor.getString(1));
        }

        return maxX;
    }

    public ArrayList<String> maxYList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> maxY = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            maxY.add(cursor.getString(2));
        }

        return maxY;
    }

    public ArrayList<String> nameArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> name = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            name.add(cursor.getString(3));
        }

        return name;
    }

    public ArrayList<String> graph1ArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> graph1 = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            graph1.add(cursor.getString(4));
        }

        return graph1;
    }

    public ArrayList<String> graph2ArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> graph2 = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            graph2.add(cursor.getString(5));
        }

        return graph2;
    }

    public ArrayList<String> graph3ArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> graph3 = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            graph3.add(cursor.getString(6));
        }

        return graph3;
    }

    public ArrayList<String> graph1ValArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> graph1Val = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            graph1Val.add(cursor.getString(7));
        }

        return graph1Val;
    }

    public ArrayList<String> graph2ValArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> graph2Val = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            graph2Val.add(cursor.getString(8));
        }

        return graph2Val;
    }

    public ArrayList<String> graph3ValArrayList() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<String> graph3Val = new ArrayList<String>();
        Cursor cursor = db.rawQuery("select * from GRAPH", null);
        while(cursor.moveToNext()) {
            graph3Val.add(cursor.getString(9));
        }

        return graph3Val;
    }


}