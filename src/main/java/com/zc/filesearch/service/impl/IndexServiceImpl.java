package com.zc.filesearch.service.impl;

import com.zc.filesearch.bean.file.ReadFileResult;
import com.zc.filesearch.service.IndexService;
import com.zc.filesearch.service.file.FileReadService;
import com.zc.filesearch.utils.FileUtils;
import org.apache.lucene.index.IndexWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zengchao on 2018/8/22.
 */
@Service
public class IndexServiceImpl implements IndexService {
    @Value("#{'${doc.suffix}'.split(',')}")
    private List<String> suffixList;
    @Autowired
    private FileReadService fileReadService;
    @Autowired
    private IndexWriter indexWriter;

    @Override
    public void createIndex(String path) {
        List<Path> pathList = new LinkedList<>();
        try {
            pathList = FileUtils.gerAllFiles(path,suffixList.toArray(new String[suffixList.size()]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<ReadFileResult> list = pathList.stream()
                .map(item -> fileReadService.readFile(item))
                .collect(Collectors.toList());


    }
}
