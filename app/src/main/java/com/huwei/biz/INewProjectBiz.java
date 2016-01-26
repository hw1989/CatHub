package com.huwei.biz;

import com.huwei.entity.ProjectEntity;

/**
 * Created by wei.hu on 2016/1/26.
 */
public interface INewProjectBiz {
    /*
    *本地数据库添加一个项目
    * name 项目名
    * dir 项目路径
    * describ 项目描述
    * type 项目类型
    */
    boolean setNewProject(String name, String dir, String describ, ProjectEntity.Type type);
}
