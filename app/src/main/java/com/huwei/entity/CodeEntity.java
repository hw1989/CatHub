package com.huwei.entity;

import android.graphics.Color;

/**
 * Created by wei.hu on 2016/1/28.
 */
public class CodeEntity {
    //代码前景色
    private int foreground;
    //代码背景色
    private int background;
    //行号
    private int lineNumber;
    //代码注释
    private int codenote;
    //类关键字class interface enum
    private int codeclass;
    //类型修饰 public abstuct private final static
    private int modify;

    public int getForeground() {
        return foreground;
    }

    public void setForeground(int foreground) {
        this.foreground = foreground;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public int getCodenote() {
        return codenote;
    }

    public void setCodenote(int codenote) {
        this.codenote = codenote;
    }

    public int getCodeclass() {
        return codeclass;
    }

    public void setCodeclass(int codeclass) {
        this.codeclass = codeclass;
    }

    public int getModify() {
        return modify;
    }

    public void setModify(int modify) {
        this.modify = modify;
    }

    public int getSymbol() {
        return symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public int getStrCode() {
        return StrCode;
    }

    public void setStrCode(int strCode) {
        StrCode = strCode;
    }

    //符号{ [] }
    private int symbol;
    //字符串 "" ''
    private int StrCode;
}
