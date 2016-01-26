package com.huwei.entity;

import org.wind.annotation.Field;
import org.wind.annotation.Table;

/**
 * Created by wei.hu on 2016/1/21.
 * 记录文件的查看记录
 */
@Table(DTname = "read")
public class ReadEntity {
    //项目地址
    @Field(name = "projectID")
    private int projectID;
    //打开时间
    @Field(name = "opentime")
    private long opentime;
    //文件
    @Field(name = "filename")
    private String filename;
    //文件路径
    @Field(name = "filelocal")
    private String filelocal;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
