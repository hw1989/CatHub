package com.huwei.activity;

import android.content.res.Configuration;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.huwei.adapter.AllProjectAdapter;
import com.huwei.entity.ProjectEntity;

import org.wind.annotation.ActivityInject;
import org.wind.annotation.ViewInject;

import java.io.File;

public class AllFileActivity extends BaseActivity {
    @ViewInject(id = R.id.rv_all_file)
    private RecyclerView rv_allfile=null;
    private ProjectEntity entity=null;
    private AllProjectAdapter adapter=null;
    private File file=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_file_layout);
        ActivityInject.getInstance().setInject(this);
        initSystemBar(this, R.color.navi_bg_dark);
        entity=(ProjectEntity)getIntent().getSerializableExtra("project");
        file=new File(entity.getProjectlocal());
        initView();
    }
    private void initView(){
        adapter=new AllProjectAdapter(this);
        adapter.setDataSource(file);
        rv_allfile.setHasFixedSize(true);
        GridLayoutManager manager=null;
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            manager=new GridLayoutManager(this,3);
        }else{
            manager=new GridLayoutManager(this,4);
        }
        rv_allfile.setLayoutManager(manager);
        rv_allfile.setAdapter(adapter);
    }

}
