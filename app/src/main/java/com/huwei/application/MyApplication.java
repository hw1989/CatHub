package com.huwei.application;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.huwei.sqldata.InfoSqliteHelper;

/**
 * Created by wei.hu on 2016/1/21.
 */
public class MyApplication extends Application{
    public  static SQLiteDatabase database=null;
    @Override
    public void onCreate() {
        super.onCreate();
        database=InfoSqliteHelper.getInstance(this,1);
    }
}
