package com.huwei.biz;

import android.text.style.ForegroundColorSpan;

import com.huwei.comman.SettingComman;
import com.huwei.utils.MyLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wei.hu on 2016/1/27.
 */
public  class JavaSpannableStringBuilder  extends BaseSpannableStringBuilder {
    private String code="";
    private Pattern pattern1=null;
    public JavaSpannableStringBuilder(String str){
        super(str);
        this.code=str;
        //初始化正则表达式
        pattern1=Pattern.compile("/\\b(?:while|volatile|void|try|true|transient|throws|throw|this|synchronized|switch|super|strictfp|static|short|return|public|protected|private|package|null|new|native|long|interface|int|instanceof|import|implements|if|goto|for|float|finally|final|false|extends|enum|else|double|do|default|continue|const|class|char|catch|case|byte|break|boolean|assert|abstract)\\b/");
//        initModify();
        //关键字
        initSymbol();
    }
    private void initModify(){
        Matcher matcher=pattern1.matcher(code);
        ForegroundColorSpan span=new ForegroundColorSpan(SettingComman.AndroidStudio.getModify());
        while (matcher.find()){
            int start= matcher.start();
            int end=matcher.end();
            MyLog.printE("start="+start+"   end"+end);
            this.setSpan(span,start,end,SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }
    private void initSymbol(){
        Pattern pattern2=Pattern.compile("/\\[");
        ForegroundColorSpan span=new ForegroundColorSpan(SettingComman.AndroidStudio.getSymbol());
        Matcher matcher=pattern1.matcher(code);
        while (matcher.find()){
            int start= matcher.start();
            int end=matcher.end();
            MyLog.printE("start="+start+"   end"+end);
            this.setSpan(span,start,end,SPAN_EXCLUSIVE_EXCLUSIVE);
        }
//        for(int i=0;i<code.length()&&code.length()>1;i++){
//            boolean flag=false;
//            if("{".equals(code.substring(i,1))){
//                flag=true;
//            }else if("(".equals(code.substring(i,1))){
//                flag=true;
//            }else if(")".equals(code.substring(i,1))){
//                flag=true;
//            }else if("}".equals(code.substring(i,1))){
//                flag=true;
//            }else if("[".equals(code.substring(i,1))){
//                flag=true;
//            }else if("]".equals(code.substring(i,1))){
//                flag=true;
//            }
//            if (flag){
//                ForegroundColorSpan span=new ForegroundColorSpan(SettingComman.AndroidStudio.getSymbol());
//                this.setSpan(span,i,i+1,SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//        }
    }
}
