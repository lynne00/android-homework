package com.example.myapplication;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class DBsqlite extends SQLiteOpenHelper {
    private static DBsqlite instance = null;
    private  SQLiteDatabase db;

    public DBsqlite(Context context) {
        super(context, "db_test", null, 1);
        db = getReadableDatabase();
        db = getWritableDatabase();
    }

    public static DBsqlite getInstance(Context context) {
        if (instance == null) {
            instance = new DBsqlite(context);
        }
        return instance;
    }
    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQhelper.U_USER_INFO, cv, "userName=?", new String[]
                {userName});
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS user(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," + "signature VARCHAR," + "nickName VARCHAR, " + "sex VARCHAR, "+
                "password TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    public void add(String name, String password) {
        db.execSQL("INSERT INTO user (name,password) VALUES(?,?)", new Object[]{name, password});
    }

    public void delete(String name, String password) {
        db.execSQL("DELETE FROM user WHERE name = AND password =" + name + password);
    }

    public void update(String password) {
        db.execSQL("UPDATE user SET password = ?", new Object[]{password});
    }

    public  ArrayList<User> getAllData() {

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user", null, null, null, null, null, "name DESC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(name, password));
        }
        return list;
    }
}