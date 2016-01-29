package com.huwei.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;

import com.huwei.adapter.CodeAdapter;
import com.huwei.biz.JavaSpannableStringBuilder;
import com.huwei.comman.SettingComman;
import com.huwei.utils.MyLog;

import org.wind.annotation.ActivityInject;
import org.wind.annotation.ViewInject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by wei.hu on 2016/1/27.
 */
public class CodeActivity extends BaseActivity {
    @ViewInject(id = R.id.rv_code_file)
    private RecyclerView rv_code;
    private File file=null;
    private CodeAdapter adapter=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_layout);
        initSystemBar(this, R.color.navi_bg_dark);
        ActivityInject.getInstance().setInject(this);
        String filesrc=getIntent().getStringExtra("code");
        file=new File(filesrc);
        this.rv_code.setBackgroundColor(SettingComman.AndroidStudio.getBackground());
        initView();
    }
    public void initView(){
        adapter=new CodeAdapter(this);
        rv_code.setHasFixedSize(true);
        rv_code.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_code.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<SpannableStringBuilder> list=new ArrayList<>();
        BufferedReader reader=null;
        try{
            reader=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String code=null;
            while ((code=reader.readLine())!=null){
                JavaSpannableStringBuilder builder=new JavaSpannableStringBuilder(code);
                list.add(builder);
            }
        }catch (Exception ex){
            MyLog.printE(ex.getMessage());
        }finally {
            if(reader!=null){
                try{
                    reader.close();
                }catch (Exception ex){

                }
            }
        }
        adapter.setDataSource(list);
    }
}
