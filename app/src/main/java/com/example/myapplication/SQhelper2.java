package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQhelper2 extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    public static String DB_NAME = "bag.db";
    public static final String U_USER_EXAM = "userExam";

    public SQhelper2(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 当这个SQLiteOpenHelper的子类类被实例化时会创建指定名的数据库，在onCreate中创建个人信息表
         * **/

        db.execSQL("CREATE TABLE IF NOT EXISTS " + U_USER_EXAM + "( "
                + "_id  INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "userName VARCHAR, "
                + "androidexam int, "
                + "javaexam int"
                + ")");

    }

    /**
     * 当数据库版本号增加才会调用此方法
     **/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + U_USER_EXAM);
        onCreate(db);
    }

}