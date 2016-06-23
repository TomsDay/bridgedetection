package com.suken.bridgedetection.bean;

/**
 * Created by Administrator on 2016/6/24.
 */
public class IVDesc {
    public String name;
    public String path;

    public IVDesc() {
    }

    public IVDesc(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
