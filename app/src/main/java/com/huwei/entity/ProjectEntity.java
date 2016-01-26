package com.huwei.entity;

import org.wind.annotation.Field;
import org.wind.annotation.Table;
import org.wind.database.DataType;

import java.io.Serializable;

/**
 * Created by wei.hu on 2016/1/21.
 * 项目的路径
 */
@Table(DTname = "project")
public class ProjectEntity implements Serializable{
    public enum Type{
        None(0),Java(1),CSharp(2),JavaScript(3),Html(4),CSS(5),CPP(6),C(7),UnKnow(8);
        private int value=0;
        private  Type(int value){
            this.value=value;
        }
        public int getValue(){
            return value;
        }
    }
    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectdescrib() {
        return projectdescrib;
    }

    public void setProjectdescrib(String projectdescrib) {
        this.projectdescrib = projectdescrib;
    }

    public String getProjectlocal() {
        return projectlocal;
    }

    public void setProjectlocal(String projectlocal) {
        this.projectlocal = projectlocal;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    //项目名
    @Field(name = "projectname",size =50)
    private String projectname;
    //项目描述
    @Field(name = "projectdescrib")
    private String projectdescrib;
    //项目路径
    @Field(name = "projectlocal",size = 60)
    private String projectlocal;
    //项目类型(java,php,c,c#,c++)
    @Field(name = "type")
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public long getCreattime() {
        return creattime;
    }

    public void setCreattime(long creattime) {
        this.creattime = creattime;
    }

    @Field(name = "creattime",type = DataType.TYPE_Real)
    private long creattime;
}
