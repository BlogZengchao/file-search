package com.zc.filesearch.config;

import com.zc.filesearch.service.file.AutoSelectFileReader;
import com.zc.filesearch.service.file.DocReader;
import com.zc.filesearch.service.file.FileReadService;
import com.zc.filesearch.service.file.TxtReadService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by zengchao on 2018/8/22.
 */
@Configuration
public class FileReadServiceConfig {

    @Bean
    public FileReadService createFileReadService(){
        List<DocReader> docReaders = new LinkedList<>();
        docReaders.add(new TxtReadService());
        return new AutoSelectFileReader(docReaders);
    }

}
