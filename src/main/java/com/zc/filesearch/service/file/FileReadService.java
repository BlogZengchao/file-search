package com.zc.filesearch.service.file;

import com.zc.filesearch.bean.file.ReadFileResult;

import java.nio.file.Path;

/**
 * Created by zengchao on 2018/8/22.
 */
public interface FileReadService {
    /**
     * 读取文件
     * @param path
     * @return
     */
    ReadFileResult readFile(Path path);
}
