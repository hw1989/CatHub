package com.huwei.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.huwei.entity.ProjectEntity;

import org.wind.annotation.ActivityInject;
import org.wind.annotation.ViewInject;

/**
 * Created by wei.hu on 2016/1/26.
 */
public class ProjectInfoActivity extends BaseActivity implements View.OnClickListener{
    @ViewInject(id = R.id.btn_check_source)
    private Button btn_check_source=null;
    @ViewInject(id = R.id.iv_check_project_type)
    private ImageView iv_type=null;
    private ProjectEntity entity=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_info_layout);
        initSystemBar(this, R.color.navi_bg_dark);
        ActivityInject.getInstance().setInject(this);
        entity=(ProjectEntity)getIntent().getSerializableExtra("project");
        btn_check_source.setOnClickListener(this);
        initView();
    }
    private  void initView(){
        if(entity.getType()==ProjectEntity.Type.C.getValue()){
            iv_type.setImageResource(R.drawable.project_c);
        }else if(entity.getType()==ProjectEntity.Type.CPP.getValue()){
            iv_type.setImageResource(R.drawable.project_cpp);
        }else if(entity.getType()==ProjectEntity.Type.CSharp.getValue()){
            iv_type.setImageResource(R.drawable.project_csharp);
        }else if(entity.getType()==ProjectEntity.Type.CSS.getValue()){
            iv_type.setImageResource(R.drawable.project_css);
        }else if(entity.getType()==ProjectEntity.Type.Java.getValue()){
            iv_type.setImageResource(R.drawable.project_java);
        }else if(entity.getType()==ProjectEntity.Type.JavaScript.getValue()){
            iv_type.setImageResource(R.drawable.project_js);
        }else if(entity.getType()==ProjectEntity.Type.Html.getValue()){
            iv_type.setImageResource(R.drawable.project_html);
        }else{
            iv_type.setImageResource(R.drawable.project_unknow);
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_check_source){
            Intent intent=new Intent(this,AllFileActivity.class);
            intent.putExtra("project",entity);
            startActivity(intent);
            finish();
        }
    }
}
