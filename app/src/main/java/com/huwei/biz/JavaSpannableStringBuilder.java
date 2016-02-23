package com.huwei.biz;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;

import com.huwei.comman.SettingComman;
import com.huwei.utils.MyLog;
import com.huwei.utils.StringUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wei.hu on 2016/1/27.
 */
public class JavaSpannableStringBuilder extends BaseSpannableStringBuilder {
    private String code = "";
    //标记注释,必须使用 static
    private static boolean Note_Flag = false;
    //标记是否为字符串（单引号）
    private static  boolean Str_Flag1=false;
    //标记是否为字符串（双引号）
    private static  boolean Str_Flag2=false;
    public JavaSpannableStringBuilder(String str) {
        super(str);
        this.code = str;
        initForebg();
        //代码注释
//        initCodeNote();
        initCode();
        if (!Note_Flag&&!Str_Flag1&&!Str_Flag2) {
            //初始化正则表达式
            initModify();
            //关键字
            initSymbol();
            //类的光剑自
            initCodeclass();
            //数据类型
            initDataType();
            //
            initStrCode();
            //注解字符串
            initInject();
        }
    }
    private void initForebg(){
        ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getForeground());
        this.setSpan(span, 0, code.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
    }
    private void initModify() {
//        Pattern pattern=Pattern.compile("(?:while|volatile|void|try|true|transient|throws|throw|this|synchronized|switch|super|strictfp|static|short|return|public|protected|private|package|null|new|native|long|interface|int|instanceof|import|implements|if|goto|for|float|finally|final|false|extends|enum|else|double|do|default|continue|const|class|char|catch|case|byte|break|boolean|assert|abstract)");
        Pattern pattern = Pattern.compile("(?:static|public|protected|private|package|new|native|import|default|const|abstract)");
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (start != 0) {
                String s1 = code.substring(start - 1, start).trim();
                ArrayList<String> characters = StringUtil.SpecialCharacters();
                //防止出现前面匹配，后面不匹配
                if ((code.length() - 1) > end) {
                    String s2 = code.substring(end, end + 1).trim();
//                    if (!"".equals(s2)) {
//                        continue;
//                    }
                    if (!characters.contains(s2)) {
                        continue;
                    }
                }
                //防止前面不匹配，后面匹配
                if (!characters.contains(s1)) {
                    continue;
                }
            }
            ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getModify());
            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void initSymbol() {
//        String[] characters = {"(?:\\[)", "(?:\\])", "(?:\\{)", "(?:\\})", "(?:\\()", "(?:\\))"};
        String[] characters = {"(?:\\{)", "(?:\\})"};
        for (String chars : characters) {
            Pattern pattern = Pattern.compile(chars);
            Matcher matcher = pattern.matcher(code);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getSymbol());
                this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    /**
     * 标注注释的颜色
     */
    private void initCodeNote() {
        if(!Note_Flag){
            String[] characters = {"(?://)", "(?:/\\*)"};
            for (String chars : characters) {
                Pattern pattern = Pattern.compile(chars);
                Matcher matcher = pattern.matcher(code);
                while (matcher.find()) {
                    int start = matcher.start();
                    ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getCodenote());
                    if("(?://)".equals(chars)){
                        this.setSpan(span, start, code.length()-1, SPAN_EXCLUSIVE_EXCLUSIVE);
                        return;
                    }else if("(?:/\\*)".equals(chars)){
                        int end=code.indexOf("*/",start);
                        if(end==-1){
                            this.setSpan(span, start, code.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                            Note_Flag=true;
                            return;
                        }else if(end>-1){
                            Note_Flag=false;
                            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
                        }
                    }
                }
            }
        }else{
            int end=code.indexOf("*/");
            ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getCodenote());
            if(end==-1){
                this.setSpan(span, 0,code.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
            }else if(end>-1){
                this.setSpan(span, 0,end+2, SPAN_EXCLUSIVE_EXCLUSIVE);
                Note_Flag=false;
            }
        }
    }
    private void initCodeclass(){
//        Pattern pattern=Pattern.compile("(?:while|volatile|void|try|true|transient|throws|throw|this|synchronized|switch|super|strictfp|static|short|return|public|protected|private|package|null|new|native|long|interface|int|instanceof|import|implements|if|goto|for|float|finally|final|false|extends|enum|else|double|do|default|continue|const|class|char|catch|case|byte|break|boolean|assert|abstract)");
//        Pattern pattern = Pattern.compile("(?:static|short|public|protected|private|package|new|native|long|int|import|float|double|default|const|char|byte|boolean|abstract)");
        Pattern pattern = Pattern.compile("(?:class|interface|enum)");
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (start != 0) {
                String s1 = code.substring(start - 1, start).trim();
                ArrayList<String> characters = StringUtil.SpecialCharacters();
                //防止出现前面匹配，后面不匹配
                if ((code.length() - 1) > end) {
                    String s2 = code.substring(end, end + 1).trim();
//                    if (!"".equals(s2)) {
//                        continue;
//                    }
                    if (!characters.contains(s2)) {
                        continue;
                    }
                }
                //防止前面不匹配，后面匹配
                if (!characters.contains(s1)) {
                    continue;
                }
            }
            ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getCodeclass());
            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    /**
     *数据类型
     */
    private void initDataType(){
        Pattern pattern=Pattern.compile("(?:void|true|this|super|short|null|long|int|float|false|double|char|byte|boolean)");
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (start != 0) {
                String s1 = code.substring(start - 1, start).trim();
                ArrayList<String> characters = StringUtil.SpecialCharacters();
                //防止出现前面匹配，后面不匹配
                if ((code.length() - 1) > end) {
                    String s2 = code.substring(end, end + 1).trim();
//                    if (!"".equals(s2)) {
//                        continue;
//                    }
                    if (!characters.contains(s2)) {
                        continue;
                    }
                }
                //防止前面不匹配，后面匹配
                if (!characters.contains(s1)) {
                    continue;
                }
            }
            ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getDataType());
            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    /*
    *其他的关键字
    */
    private void initStrCode(){
        Pattern pattern=Pattern.compile("(?:while|volatile|try|transient|throws|throw|synchronized|switch|strictfp|return|package|new|native|instanceof|implements|if|goto|for|finally|final|extends|else|do|default|continue|const|catch|case|break|assert|abstract)");
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (start != 0) {
                String s1 = code.substring(start - 1, start).trim();
                ArrayList<String> characters = StringUtil.SpecialCharacters();
                //防止出现前面匹配，后面不匹配
                if ((code.length() - 1) > end) {
                    String s2 = code.substring(end, end + 1).trim();
//                    if (!"".equals(s2)) {
//                        continue;
//                    }
                    if (!characters.contains(s2)) {
                        continue;
                    }
                }
                //防止前面不匹配，后面匹配
                if (!characters.contains(s1)) {
                    continue;
                }
            }
            ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getStrCode());
            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    /**
     *字符串
     * 防止转义
     */
    private void initDataStr(){
//        String[] characters = {"(?:\")", "(?:')"};
//        for (String chars : characters) {
//            Pattern pattern = Pattern.compile(chars);
//            Matcher matcher = pattern.matcher(code);
//            while (matcher.find()) {
//                int start = matcher.start();
//                int end = matcher.end();
//                if(start==0){
//
//                }else if(start>0){
//                    //不是从第一个字符开始
//                    String s1=code.substring(start-1,start);
//                    if("\\".equals(s1)){
//                        //确定是转义
//
//                    }else{
//                        //不是转义
//
//                    }
//                }
//                ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getDataStr());
//                this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//        }

    }
    /**
     * 字符串与注释存在叠加冲突
     */
    private void initCode(){
        if(!"".equals(code.trim())){
            String s1="";
            String s2="";
            int start=0,end=code.length();
            int i=0;
            for(;i<code.length()-1;i++){
                s1=code.substring(i,i+1);
                if(Note_Flag){
                    //注释
                    end=code.indexOf("*/");
                    ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getCodenote());
                    if(end==-1){
                        this.setSpan(span, start,code.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    }else{
                        i+=i+1;
                        this.setSpan(span, start,i+2, SPAN_EXCLUSIVE_EXCLUSIVE);
                        Note_Flag=false;
                    }
                    continue;
                }
                if(Str_Flag2||Str_Flag1){
                    //字符串
                    if(Str_Flag1){
                        if("\"".equals(s1)){
                            if(i>0){
                                s2=code.substring(i-1,i);
                                //防止转义
                                if(!"\\".equals(s2)){
                                    Str_Flag1=false;
                                    ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getDataStr());
                                    this.setSpan(span, start, i+1, SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }else{
                                Str_Flag1=false;
                                ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getDataStr());
                                this.setSpan(span, start, i+1, SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                    }
                    if(Str_Flag2){
                        if("'".equals(s1)){
                            if(i>0){
                                s2=code.substring(i-1,i);
                                //防止转义
                                if(!"\\".equals(s2)){
                                    Str_Flag2=false;
                                    ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getDataStr());
                                    this.setSpan(span, start, i+1, SPAN_EXCLUSIVE_EXCLUSIVE);
                                }
                            }else{
                                Str_Flag2=false;
                                ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getDataStr());
                                this.setSpan(span, start, i+1, SPAN_EXCLUSIVE_EXCLUSIVE);
                            }
                        }
                    }
                    continue;
                }

                if("/".equals(s1)){
                    //获取第二个字符
                    if(code.length()-1<i+2){
                        return;
                    }
                    s2=code.substring(i+1,i+2);
//                    s2=code.substring(code.length()-1);
                    if("/".equals(s2)){
                        start=i;
                        end=code.length();
                        ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getCodenote());
                        this.setSpan(span, start,code.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    }else if("*".equals(s2)){
                        start=i;
                        Note_Flag=true;
                    }
                } else if("\"".equals(s1)){
                    if(i>0){
                        s2=code.substring(i-1,i);
//                        String m="\"";
                        //防止转义
                        if(!"\\".equals(s2)){
                            start=i;
                            Str_Flag1=true;
                        }
                    }else{
                        start=i;
                        Str_Flag1=true;
                    }
                }else if("'".equals(s1)){
                    if(i>0){
                        s2=code.substring(i-1,i);
//                        String m="'";
                        //防止转义
                        if(!"\\".equals(s2)){
                            start=i;
                            Str_Flag2=true;
                        }
                    }else{
                        start=i;
                        Str_Flag2=true;
                    }
                }
            }
        }
    }
    private void initNumber(){
        Pattern pattern = Pattern.compile("(?:0|1|2|3|4|5|6|7|8|9)");
        Matcher matcher = pattern.matcher(code);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (start != 0) {
                String s1 = code.substring(start - 1, start).trim();
                ArrayList<String> characters = new ArrayList<String>();
                characters.add("");
                characters.add("+");
                characters.add("-");
                characters.add("*");
                characters.add("/");
                characters.add("!");
                characters.add("%");
                characters.add("^");
                characters.add("&");
                characters.add("(");
                characters.add("{");
                characters.add("[");
                characters.add(";");
                characters.add(",");
                characters.add(":");
                characters.add(".");
                //防止出现前面匹配，后面不匹配
                if ((code.length() - 1) > end) {
                    String s2 = code.substring(end, end + 1).trim();
//                    if (!"".equals(s2)) {
//                        continue;
//                    }
                    if (!characters.contains(s2)) {
                        continue;
                    }
                }
                //防止前面不匹配，后面匹配
                if (!characters.contains(s1)) {
                    continue;
                }
            }
            ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getNumber());
            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    private void initInject(){
//        Pattern pattern=Pattern.compile("(@.*? |\\(|)");
        Pattern pattern=Pattern.compile("@[A-Za-z]+");
        Matcher matcher=pattern.matcher(code);
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getInjectStr());
            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
}
