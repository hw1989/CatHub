package com.huwei.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.huwei.utils.MyLog;
import com.huwei.view.SystemBarTintManager;

import java.lang.reflect.Field;

/**
 * Created by wei.hu on 2016/1/22.
 */
public class BaseActivity extends FragmentActivity{

    public static void initSystemBar(Activity activity, int colorid) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
// 使用颜色资源
        tintManager.setStatusBarTintResource(colorid);
//        if (Build.VERSION.SDK_INT >= 19)
//        {
//            ViewGroup group=(ViewGroup)activity.getWindow().getDecorView();
//            View view=new View();
//            // 透明状态栏
////            activity.getWindow().addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
    }

    @TargetApi(19)
    public static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
