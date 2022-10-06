package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBinfo {
    private static DBinfo instance = null;
    private static SQhelper helper;
    private static SQLiteDatabase db;


    public DBinfo(Context context) {
        helper = new SQhelper(context);
        db = helper.getWritableDatabase();
    }

//获取实例
    public static DBinfo getInstance(Context context) {
        if (instance == null) {
            instance = new DBinfo(context);
        }
        return instance;
    }


    public void saveUserInfo(Useinfo useinfo) {
        ContentValues cv = new ContentValues();
        cv.put("userName", useinfo.userName);
        cv.put("nickName", useinfo.nickName);
        cv.put("sex", useinfo.sex);
        cv.put("signature", useinfo.signature);
        db.insert(SQhelper.U_USER_INFO, null, cv);
    }

//获取信息
    @SuppressLint("Range")
    public Useinfo getUserInfo(String userName) {
        String sql = "SELECT * FROM " + SQhelper.U_USER_INFO + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        Useinfo info = null;
        //Move the cursor to the next row.
        while (cursor.moveToNext()) {
            info = new Useinfo();
            info.userName = cursor.getString(cursor.getColumnIndex("userName"));
            info.nickName = cursor.getString(cursor.getColumnIndex("nickName"));
            info.sex = cursor.getString(cursor.getColumnIndex("sex"));
            info.signature = cursor.getString(cursor.getColumnIndex("signature"));
        }
        cursor.close();
        return info;
    }


    public void updateUserInfo(String key, String value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQhelper.U_USER_INFO, cv, "userName=?", new String[]
                {userName});
    }


}
