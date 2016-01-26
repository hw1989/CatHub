package com.huwei.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.huwei.biz.INewProjectBiz;
import com.huwei.biz.NewProjectBiz;
import com.huwei.entity.ProjectEntity;
import com.huwei.utils.MyToast;
import com.huwei.view.FlowRadioGroup;
import com.huwei.view.NavigationBarDelegate;
import com.huwei.view.NavigationBarView;

import org.wind.annotation.ActivityInject;
import org.wind.annotation.ViewInject;

/**
 * Created by wei.hu on 2016/1/25.
 */
public class NewProjectActivity extends BaseActivity implements NavigationBarDelegate{
    @ViewInject(id = R.id.nbv_new_project_title)
    private NavigationBarView nbv_title=null;
    @ViewInject(id = R.id.et_new_project_name)
    private EditText et_name=null;
    @ViewInject(id = R.id.frg_new_project_type)
    private FlowRadioGroup frg_type=null;
    @ViewInject(id = R.id.et_new_project_describ)
    private EditText et_describ=null;
    @ViewInject(id = R.id.tv_new_project_path)
    private TextView tv_path=null;
    private String fileDir=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_project_layout);
        initSystemBar(this, R.color.navi_bg_dark);
        ActivityInject.getInstance().setInject(this);
        Bundle bundle=getIntent().getExtras();
        fileDir=bundle.getString("path", "");
        nbv_title.setNaviDelegate(this);
        tv_path.setText(fileDir);
    }

    @Override
    public void leftBarClick() {
        this.finish();
    }

    @Override
    public void rightBarClick() {
        String name=et_name.getText().toString().trim();
        if("".equals(name)){
            MyToast.show(this,R.string.new_project_tip_nameisnull);
            return;
        }
        ProjectEntity.Type type= ProjectEntity.Type.None;
        switch (frg_type.getCheckedRadioButtonId()){
            case R.id.rb_new_project_type_c:
                type= ProjectEntity.Type.C;
                break;
            case R.id.rb_new_project_type_cpp:
                type= ProjectEntity.Type.CPP;
                break;
            case R.id.rb_new_project_type_csharp:
                type= ProjectEntity.Type.CSharp;
                break;
            case R.id.rb_new_project_type_css:
                type= ProjectEntity.Type.CSS;
                break;
            case R.id.rb_new_project_type_html:
                type= ProjectEntity.Type.Html;
                break;
            case R.id.rb_new_project_type_java:
                type= ProjectEntity.Type.Java;
                break;
            case R.id.rb_new_project_type_js:
                type= ProjectEntity.Type.JavaScript;
                break;
            case R.id.rb_new_project_type_unknow:
                type= ProjectEntity.Type.UnKnow;
                break;
        }
        if(type==ProjectEntity.Type.None){
            MyToast.show(this,R.string.new_project_tip_typeisnull);
            return;
        }
        if(fileDir==null||"".equals(fileDir)){
            MyToast.show(this,R.string.new_project_tip_fileisnull);
            return;
        }
        INewProjectBiz biz=new NewProjectBiz();
        boolean flag=biz.setNewProject(name,fileDir,et_describ.getText().toString(),type);
        if(flag){
            MyToast.show(this,R.string.new_project_tip_addsuccess);
            finish();
        }else{
            MyToast.show(this,R.string.new_project_tip_addfail);
        }
    }
}
