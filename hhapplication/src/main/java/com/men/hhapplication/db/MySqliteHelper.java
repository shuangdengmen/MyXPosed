package com.men.hhapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(@Nullable Context context) {
        super(context, "mytestprovider.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table mytest(_people_id Intger  primary key ,people varchar(10) ,calls Intger  )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
