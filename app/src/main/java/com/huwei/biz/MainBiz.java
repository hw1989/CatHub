package com.huwei.biz;

import android.database.Cursor;

import com.huwei.application.MyApplication;
import com.huwei.entity.ProjectEntity;
import com.huwei.utils.MyLog;

import java.util.ArrayList;

/**
 * Created by wei.hu on 2016/1/26.
 */
public class MainBiz implements IMainBiz{
    @Override
    public ArrayList<ProjectEntity> getProjectList() {
        ArrayList<ProjectEntity> list=new ArrayList<>();
        Cursor cursor=MyApplication.database.query("project",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                ProjectEntity entity=new ProjectEntity();
                entity.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                entity.setProjectname(cursor.getString(cursor.getColumnIndex("projectname")));
                entity.setProjectlocal(cursor.getString(cursor.getColumnIndex("projectlocal")));
                entity.setProjectdescrib(cursor.getString(cursor.getColumnIndex("projectdescrib")));
                entity.setType(cursor.getInt(cursor.getColumnIndex("type")));
                entity.setCreattime(cursor.getLong(cursor.getColumnIndex("creattime")));
                list.add(entity);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
}
