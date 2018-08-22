package com.zc.filesearch.bean.file;

import java.util.Date;

/**
 * 文件Doc
 * Created by zengchao on 2018/8/22.
 */
public class FileDoc {
    /**文件名称*/
    private String fileName;
    /**文件类型*/
    private String type;
    /**最后修改时间*/
    private Date lastModified;
    /**文件内容*/
    private String content;
    /**文件的绝对路径*/
    private String path;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
