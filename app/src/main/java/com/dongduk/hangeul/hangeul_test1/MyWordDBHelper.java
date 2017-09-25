package com.dongduk.hangeul.hangeul_test1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yoon1 on 2017-09-22.
 */

public class MyWordDBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "myword_db";
    public final static String TALBE_NAME = "myword_table";

    public MyWordDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TALBE_NAME + " ( _id integer primary key autoincrement,"
                + "date TEXT, word TEXT, desc1 TEXT, desc2 TEXT);");

//		샘플 데이터
        db.execSQL("INSERT INTO " + TALBE_NAME + " VALUES (null, '08.20', '꽃샘', '봄철 꽃이 필 무렵의 추위.', '');");
        db.execSQL("INSERT INTO " + TALBE_NAME + " VALUES (null, '08.23', '모가', '낮은 패의 우두머리.', '인부나 광대 등의 우두머리.');");
        db.execSQL("INSERT INTO " + TALBE_NAME + " VALUES (null, '09.10', '가시버리', '부부를 속되게 이르는 말', '');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
