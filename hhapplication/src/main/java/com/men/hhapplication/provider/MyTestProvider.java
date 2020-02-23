package com.men.hhapplication.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.men.hhapplication.db.MySqliteHelper;

public class MyTestProvider extends ContentProvider {
    private static UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int PEOPLE = 1;

    private static final int PEOPLE_ID = 2;

    private static final int CallS = 3;

    private static final int QUERY = 4;

    static{
        //authority 主机名 路径的前缀名
        mUriMatcher.addURI("com.men.hhapplication.provider.mytest","people",PEOPLE);
        mUriMatcher.addURI("com.men.hhapplication.provider.mytest","people/#",PEOPLE_ID);
        mUriMatcher.addURI("com.men.hhapplication.provider.mytestmytest","calls", CallS);
        mUriMatcher.addURI("com.men.hhapplication.provider.mytestmytest","query", QUERY);

        //content://包名/数据库表名  -->1
    }//添加匹配规则

    private MySqliteHelper mySqliteHelper;

    @Override
    public boolean onCreate() {
        mySqliteHelper = new MySqliteHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase readableDatabase = mySqliteHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.query("mytest", projection, selection, selectionArgs, null, null, sortOrder);
        if (cursor != null) {
            return cursor;
        } else {
            Log.i("tag","没有匹配上……");
            return null;
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
