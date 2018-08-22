package com.zc.filesearch.service.file;

import com.zc.filesearch.bean.file.ReadFileResult;

import java.nio.file.Path;

/**
 * Created by zengchao on 2018/8/22.
 */
public interface DocReader {
    /**
     * 读取文件内容接口
     * @param result
     * @param path
     */
    void read(ReadFileResult result, Path path);

    /**
     * 获取能读取的文件类型
     * @return
     */
    String getDocSuffix();
}
