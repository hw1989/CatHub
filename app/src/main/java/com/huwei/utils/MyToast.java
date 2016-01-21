package com.huwei.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by wei.hu on 2016/1/21.
 */
public class MyToast {
    public static void show(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
    public static void show(Context context, int strID) {
        Toast.makeText(context, strID, Toast.LENGTH_SHORT).show();
    }
}
