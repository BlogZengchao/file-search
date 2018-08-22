package com.zc.filesearch.config;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by zengchao on 2018/8/22.
 */
@Configuration
public class LuceneConfig {

    @Value("#{'index.path'}")
    private String indexPath;

    @Bean
    public IndexWriter createIndexWriter() throws IOException {
        IndexWriterConfig config = new IndexWriterConfig();
        config.setInfoStream(System.out);
        config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriter writer = new IndexWriter(directory,config);
        return writer;
    }
}
