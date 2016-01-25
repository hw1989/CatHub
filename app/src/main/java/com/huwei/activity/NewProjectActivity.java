package com.huwei.activity;

import android.os.Bundle;

/**
 * Created by wei.hu on 2016/1/25.
 */
public class NewProjectActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_project_layout);
        initSystemBar(this,R.color.navi_bg_dark);
    }
}
