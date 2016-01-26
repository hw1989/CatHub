package com.huwei.activity;

import android.os.Bundle;

import org.wind.annotation.ActivityInject;

/**
 * Created by wei.hu on 2016/1/26.
 */
public class ProjectInfoActivity extends BaseActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_info_layout);
        initSystemBar(this, R.color.navi_bg_dark);
        ActivityInject.getInstance().setInject(this);
    }
}
