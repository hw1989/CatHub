package com.huwei.entity;

import org.wind.annotation.Field;
import org.wind.annotation.Table;

/**
 * Created by wei.hu on 2016/1/21.
 * 文件的过滤
 */
@Table(DTname = "suffix")
public class SuffixEntity {
    //文件的后缀
    @Field(name = "suffix")
    private String suffix;
}
