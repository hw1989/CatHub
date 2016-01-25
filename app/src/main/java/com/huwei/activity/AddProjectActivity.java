package com.huwei.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.huwei.adapter.AddPathAdapter;
import com.huwei.adapter.PathListAdapter;

import org.wind.annotation.ActivityInject;
import org.wind.annotation.ViewInject;

public class AddProjectActivity extends BaseActivity {
    @ViewInject(id = R.id.rv_addproject_dics)
    private RecyclerView rv_pathlist=null;
    private PathListAdapter padapter=null;
    @ViewInject(id = R.id.rv_addproject_path)
    private RecyclerView rv_parent=null;
    private AddPathAdapter aadapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_project_layout);
        initSystemBar(this, R.color.navi_bg_dark);
        ActivityInject.getInstance().setInject(this);
        initView();
    }
    private void initView(){
        padapter=new PathListAdapter(this);
        padapter.setDataSource(Environment.getExternalStorageDirectory());
        rv_pathlist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_pathlist.setHasFixedSize(true);
        rv_pathlist.setAdapter(padapter);
        //
        aadapter=new AddPathAdapter(this);
        aadapter.setDataSource(Environment.getExternalStorageDirectory());
        rv_parent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rv_parent.setHasFixedSize(true);
        rv_parent.setAdapter(aadapter);
    }

}
