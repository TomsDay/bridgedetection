package com.suken.bridgedetection.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/7/6.
 */
public class UploadFileBean implements Serializable{
    private String fileId;
    private String fileName;

    public UploadFileBean() {
    }

    public UploadFileBean(String fileId, String fileName) {
        this.fileId = fileId;
        this.fileName = fileName;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
