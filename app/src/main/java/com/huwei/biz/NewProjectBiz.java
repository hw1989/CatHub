package com.huwei.biz;

import android.content.ContentValues;

import com.huwei.application.MyApplication;
import com.huwei.entity.ProjectEntity;

import java.util.Date;

/**
 * Created by wei.hu on 2016/1/26.
 */
public class NewProjectBiz implements INewProjectBiz{


    @Override
    public boolean setNewProject(String name, String dir, String describ, ProjectEntity.Type type) {
        ContentValues values=new ContentValues();
        values.put("projectname",name);
        values.put("projectdescrib",describ);
        values.put("projectlocal",dir);
        values.put("type",type.getValue());
        values.put("creattime",new Date().getTime());
        long state=MyApplication.database.insert("project",null,values);
        if(state==-1){
            return false;
        }else{
            return true;
        }
    }
}
