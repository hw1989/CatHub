package com.huwei.biz;

/**
 * Created by wei.hu on 2016/2/16.
 */
public class CPPSpannableStringBuilder extends BaseSpannableStringBuilder {

    private String code = "";
    //标记注释,必须使用 static
    private static boolean Note_Flag = false;
    //标记是否为字符串（单引号）
    private static  boolean Str_Flag1=false;
    //标记是否为字符串（双引号）
    private static  boolean Str_Flag2=false;
    public CPPSpannableStringBuilder(String str) {
        super(str);
        this.code = str;
    }
}
