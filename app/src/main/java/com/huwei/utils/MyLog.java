package com.huwei.utils;

import android.util.Log;

import com.huwei.comman.SettingComman;

/**
 * Created by wei.hu on 2016/1/21.
 */
public class MyLog {
    public static void printI(String text){
        if(SettingComman.Print_Log){
            Log.i(SettingComman.Print_Tag,text);
        }
    }
    public static void printE(String text){
        if(SettingComman.Print_Log){
            Log.e(SettingComman.Print_Tag,text);
        }
    }
}
