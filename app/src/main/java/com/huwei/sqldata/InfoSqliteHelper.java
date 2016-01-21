package com.huwei.sqldata;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.huwei.entity.ProjectEntity;
import com.huwei.entity.ReadEntity;
import com.huwei.entity.SuffixEntity;
import org.wind.database.TableHelper;

/**
 * Created by wei.hu on 2016/1/21.
 */
public class InfoSqliteHelper extends SQLiteOpenHelper{
    private InfoSqliteHelper helper=null;
    public SQLiteDatabase getInstance(Context context,int version){
        if(helper==null){
            helper=new InfoSqliteHelper(context,"AppDataBase",null,version);
        }
        return helper.getReadableDatabase();
    }
    private InfoSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //项目信息
        TableHelper project=new TableHelper(ProjectEntity.class);
        db.execSQL(project.getSQL());
        TableHelper read=new TableHelper(ReadEntity.class);
        db.execSQL(read.getSQL());
        TableHelper suffix=new TableHelper(SuffixEntity.class);
        db.execSQL(suffix.getSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion){
            try{
                db.execSQL(" if exists drop project");
                db.execSQL(" if exists drop read");
                db.execSQL(" if exists drop suffix");
            }catch (Exception ex){

            }
        }
    }
}
