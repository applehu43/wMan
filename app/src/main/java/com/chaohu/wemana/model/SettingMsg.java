package com.chaohu.wemana.model;

/**
 * Created by chaohu on 2016/4/24.
 */
public class SettingMsg {
    private String name;
    private String text;
    private boolean show;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
