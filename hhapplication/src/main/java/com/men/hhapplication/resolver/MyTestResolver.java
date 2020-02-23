package com.men.hhapplication.resolver;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class MyTestResolver extends ContentResolver {

    private int query_id;
    private String querystr;
    private int calls;

    public MyTestResolver(Context context) {
        super(context);
    }

    public String getResult() {
        Uri uri = Uri.parse("content://com.men.hhapplication.provider.mytest/query");
        Cursor query = query(uri, new String[]{"_query_id","query","calls"}, null,null,null);
        if (query != null) {
            while (query.moveToNext()) {
                query_id = query.getInt(0);
                querystr = query.getString(1);
                calls = query.getInt(2);
                return toString();
            }
        }
        query.close();
        return null;
    }

    @Override
    public String toString() {
        return "MyTestResolver{" +
                "query_id=" + query_id +
                ", querystr='" + querystr + '\'' +
                ", calls=" + calls +
                '}';
    }
}
