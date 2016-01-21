package com.huwei.entity;

import org.wind.annotation.Field;
import org.wind.annotation.Table;

/**
 * Created by wei.hu on 2016/1/21.
 * 项目的路径
 */
@Table(DTname = "project")
public class ProjectEntity {
    public enum Type{
        Java(1),CSharp(2),JavaScript(3),Html(4),CSS(5),CPP(6);
        private int value=0;
        private  Type(int value){
            this.value=value;
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
    @Field(name = "projectname")
    private String projectname;
    //项目描述
    @Field(name = "projectdescrib")
    private String projectdescrib;
    //项目路径
    @Field(name = "projectlocal")
    private String projectlocal;
    //项目类型(java,php,c,c#,c++)
    @Field(name = "type")
    private int type;
}
