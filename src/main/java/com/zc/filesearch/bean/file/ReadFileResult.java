package com.zc.filesearch.bean.file;

/**
 * Created by zengchao on 2018/8/22.
 */
public class ReadFileResult {
    /**文件读取内容*/
    private FileDoc fileDoc;
    /**是否成功*/
    private boolean isSuccess;
    /**异常*/
    private Exception exception;

    public FileDoc getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(FileDoc fileDoc) {
        this.fileDoc = fileDoc;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}
