package com.huwei.activity;

import android.os.Bundle;

public class AddProjectActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project_layout);
        initSystemBar(this, R.color.navi_bg_dark);

    }


}
