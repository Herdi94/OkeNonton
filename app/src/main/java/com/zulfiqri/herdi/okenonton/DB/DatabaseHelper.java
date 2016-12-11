package com.zulfiqri.herdi.okenonton.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.version;

/**
 * Created by Herdi on 11/12/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "MovieDB";
    public static final String TABLE_NAME = "picture";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PHOTO = "photo";

    private static final int DB_VERSION = 1;

    ArrayList<String> data = new ArrayList<String>();

    public DatabaseHelper(Context context) {
        super(context,DB_NAME,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE"+TABLE_NAME
                +"("+COLUMN_ID+"INTEGER PRIMARY KEY AUTO_INCREMENT,"
                +COLUMN_PHOTO+"VARCHAR"+");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS COLUMN_PHOTO";
        db.execSQL(sql);
        onCreate(db);
    }

    //to insert Photo to MoviesDB
    public boolean addPhoto(String photo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_PHOTO,photo);

        db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return true;
    }

    public Cursor getPhoto(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM picture WHERE id="+id+";";

        /*
        //to hindari sql injection
        SQLiteStatement stmt = db.compileStatement(sql);

        stmt.execute();
        return true;
        */

        Cursor c = db.rawQuery(sql,null);
        return c;

    }

}
