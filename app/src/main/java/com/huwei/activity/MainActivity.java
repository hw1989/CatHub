package com.huwei.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.wind.annotation.ActivityInject;
import org.wind.annotation.ViewInject;

public class MainActivity extends Activity {
    //项目信息
//    @ViewInject(id = R.id.rv_main_project)
    private RecyclerView rv_project;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ActivityInject.getInstance().setInject(this);
    }
}
