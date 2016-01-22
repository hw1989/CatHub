package com.huwei.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huwei.activity.R;
import com.huwei.utils.MyLog;

import java.lang.reflect.Field;

/**
 * Created by wei.hu on 2016/1/22.
 * 导航条
 */
public class NavigationBarView extends RelativeLayout {
    private String title = null;
    //标记是否从状态栏算起
    private boolean fromstatus = false;

    public NavigationBarView(Context context) {
        super(context);
        initAttr(context, null, 0);
    }

    public NavigationBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs, 0);
    }

    public NavigationBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
    }

    private void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NavigationBarView, defStyleAttr, 0);
        if (a == null) {
            return;
        }
        title = a.getString(R.styleable.NavigationBarView_navtitle);
        fromstatus = a.getBoolean(R.styleable.NavigationBarView_hasstatubar, false);
        a.recycle();
    }

    //初始化
    private void initView(Context context) {
        TextView tv_title = new TextView(context);
        tv_title.setTextSize(20);
        tv_title.setTextColor(Color.WHITE);
        if (title != null) {
            tv_title.setText(title);
        }
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        titleParams.setLayoutDirection(RelativeLayout.CENTER_IN_PARENT);
        titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL);//addRule参数对应RelativeLayout XML布局的属性
//        tv_title.setGravity(Gravity.CENTER);
        titleParams.setMargins(0,40, 0,40);
        tv_title.setLayoutParams(titleParams);
        this.addView(tv_title);
        this.setBackgroundResource(R.color.navi_bg_light);
//        RelativeLayout.LayoutParams parentLayout=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,50);
        LinearLayout.LayoutParams parentLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (fromstatus) {
            Class<?> c = null;
            Object obj = null;
            Field field = null;
            int x = 0, sbar = 0;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                obj = c.newInstance();
                field = c.getField("status_bar_height");
                x = Integer.parseInt(field.get(obj).toString());
                sbar = getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                MyLog.printE("get status bar height fail");
                e1.printStackTrace();
            }
//            Rect rect=new Rect();
//            ((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            parentLayout.setMargins(0, sbar, 0, 0);
        }
        this.setLayoutParams(parentLayout);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        initView(getContext());
    }
}
