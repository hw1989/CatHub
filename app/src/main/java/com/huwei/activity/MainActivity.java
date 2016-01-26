package com.huwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.huwei.adapter.MainAdapter;
import com.huwei.biz.IMainBiz;
import com.huwei.biz.MainBiz;
import com.huwei.entity.ProjectEntity;
import com.huwei.utils.MyLog;

import org.wind.annotation.ActivityInject;
import org.wind.annotation.ViewInject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Intent intent=null;
    //项目信息
    @ViewInject(id = R.id.rv_main_project)
    private RecyclerView rv_project;
    private MainAdapter adapter=null;
    @ViewInject(id = R.id.fab_main_project)
    private FloatingActionButton fab_project=null;
    private ArrayList<ProjectEntity> list=null;
    private IMainBiz biz=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        initSystemBar(this, R.color.navi_bg_dark);
        biz=new MainBiz();
//        setTranslucentStatus(this,true);
        ActivityInject.getInstance().setInject(this);
        initView();
    }
    private void initView(){
        adapter=new MainAdapter(this);
        rv_project.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //固定大小
        rv_project.setHasFixedSize(true);
        rv_project.setAdapter(adapter);
        //
        fab_project.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fab_main_project){
            intent=new Intent(this,AddProjectActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list=biz.getProjectList();
        adapter.setDataSource(list);
    }
}
