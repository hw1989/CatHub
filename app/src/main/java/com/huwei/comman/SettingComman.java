package com.huwei.comman;

import android.graphics.Color;

import com.huwei.entity.CodeEntity;

/**
 * Created by wei.hu on 2016/1/21.
 */
public class SettingComman {
    //设置log打印日志
    public  static final boolean Print_Log=true;
    //设置log打印tag
    public  static final String Print_Tag="cathub";

    public static final CodeEntity AndroidStudio=new CodeEntity(){
        @Override
        public int getBackground() {
            return Color.parseColor("#293134");
        }

        @Override
        public int getCodeclass() {
            return Color.parseColor("#678CB1");
        }

        @Override
        public int getCodenote() {
            return Color.parseColor("#FF0053");
        }

        @Override
        public int getForeground() {
            return Color.parseColor("#E0E2E4");
        }

        @Override
        public int getLineNumber() {
            return Color.parseColor("#81969A");
        }

        @Override
        public int getModify() {
            return Color.parseColor("#93C763");
        }

        @Override
        public int getStrCode() {
            return Color.parseColor("#EC7600");
        }

        @Override
        public int getSymbol() {
            return Color.parseColor("#FFFFFF");
        }
        @Override
        public int getDataType(){
            return Color.parseColor("#52E3F6");
        }

        @Override
        public int getDataStr() {
            return Color.parseColor("#ECE47E");
        }

        @Override
        public int getNumber() {
            return Color.parseColor("#C48CFF");
        }

        @Override
        public int getInjectStr() {
            return Color.parseColor("#FDCC9E");
        }
    };
}
