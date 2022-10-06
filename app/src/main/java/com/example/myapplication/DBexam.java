package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBexam {
    private static DBexam instance = null;
    private static SQhelper2 helper;
    private static SQLiteDatabase db;


    public DBexam(Context context) {
        helper = new SQhelper2(context);
        db = helper.getWritableDatabase();
    }

    //获取实例
    public static DBexam getInstance(Context context) {
        if (instance == null) {
            instance = new DBexam(context);
        }
        return instance;
    }


    public void saveUserExam(Userexam userexam) {
        ContentValues cv = new ContentValues();
        cv.put("userName", userexam.userName);
        cv.put("androidexam", userexam.androidexam);
        cv.put("javaexam",userexam.javaexam);
        db.insert(SQhelper2.U_USER_EXAM, null, cv);
    }

    //获取信息
    @SuppressLint("Range")
    public Userexam getUserExam(String userName) {
        String sql = "SELECT * FROM " + SQhelper2.U_USER_EXAM + " WHERE userName=?";
        Cursor cursor = db.rawQuery(sql, new String[]{userName});
        Userexam exam = null;
        //Move the cursor to the next row.
        while (cursor.moveToNext()) {
            exam = new  Userexam();
            exam.userName = cursor.getString(cursor.getColumnIndex("userName"));
            exam.androidexam = cursor.getInt(cursor.getColumnIndex("androidexam"));
            exam.javaexam = cursor.getInt(cursor.getColumnIndex("javaexam"));
        }
        cursor.close();
        return exam;
    }


    public void updateUserExam(String key, int value, String userName) {
        ContentValues cv = new ContentValues();
        cv.put(key, value);
        db.update(SQhelper2.U_USER_EXAM, cv, "userName=?", new String[]
                {userName});
    }

}
