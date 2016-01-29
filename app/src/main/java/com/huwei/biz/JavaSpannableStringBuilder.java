package com.huwei.biz;

import android.text.style.ForegroundColorSpan;

import com.huwei.comman.SettingComman;
import com.huwei.utils.MyLog;

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

    public JavaSpannableStringBuilder(String str) {
        super(str);
        this.code = str;
        //代码注释
        initCodeNote();
        if (!Note_Flag) {
            //初始化正则表达式
            initModify();
            //关键字
            initSymbol();
        }

    }

    private void initModify() {
//        Pattern pattern=Pattern.compile("(?:while|volatile|void|try|true|transient|throws|throw|this|synchronized|switch|super|strictfp|static|short|return|public|protected|private|package|null|new|native|long|interface|int|instanceof|import|implements|if|goto|for|float|finally|final|false|extends|enum|else|double|do|default|continue|const|class|char|catch|case|byte|break|boolean|assert|abstract)");
        Pattern pattern = Pattern.compile("(?:static|short|public|protected|private|package|new|native|long|int|import|float|double|default|const|char|byte|boolean|abstract)");
        Matcher matcher = pattern.matcher(code);
        ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getModify());
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
                //防止出现前面匹配，后面不匹配
                if ((code.length() - 1) > end) {
                    String s2 = code.substring(end, end + 1).trim();
                    if (!"".equals(s2)) {
                        continue;
                    }
                }
                //防止前面不匹配，后面匹配
                if (!characters.contains(s1)) {
                    continue;
                }
            }
            this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private void initSymbol() {
        String[] characters = {"(?:\\[)", "(?:\\])", "(?:\\{)", "(?:\\})", "(?:\\()", "(?:\\))"};
        ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getSymbol());
        for (String chars : characters) {
            Pattern pattern = Pattern.compile(chars);
            Matcher matcher = pattern.matcher(code);
            while (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                this.setSpan(span, start, end, SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    /**
     * 标注注释的颜色
     */
    private void initCodeNote() {
        ForegroundColorSpan span = new ForegroundColorSpan(SettingComman.AndroidStudio.getCodenote());
        if(!Note_Flag){
            String[] characters = {"(?://)", "(?:/\\*)"};
            for (String chars : characters) {
                Pattern pattern = Pattern.compile(chars);
                Matcher matcher = pattern.matcher(code);
                while (matcher.find()) {
                    int start = matcher.start();
                    if("//".equals(chars)){
                        this.setSpan(span, start, code.length()-1, SPAN_EXCLUSIVE_EXCLUSIVE);
                        return;
                    }else if("/*".equals(chars)){
                        int end=code.indexOf("*/",start);
                        if(end==-1){
                            this.setSpan(span, start, code.length()-1, SPAN_EXCLUSIVE_EXCLUSIVE);
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
            if(end==-1){
                this.setSpan(span, 0,code.length()-1, SPAN_EXCLUSIVE_EXCLUSIVE);
            }else if(end>-1){
                this.setSpan(span, 0,end, SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }
}
