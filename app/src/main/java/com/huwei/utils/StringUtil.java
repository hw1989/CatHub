package com.huwei.utils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/31.
 */
public class StringUtil {
    public static ArrayList<String> SpecialCharacters(){
        String s="+-*/!%^&(){}[];:,.=";
        ArrayList<String> list=new ArrayList<>();
        list.add("");
        for(int i=0;i<s.length();i++){
            list.add(s.substring(i,i+1));
        }
        return list;
    }
}
