package com.zc.filesearch.service.impl;

import com.zc.filesearch.service.IndexService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by zengchao on 2018/8/22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexServiceImplTest {
    @Autowired
    private IndexService indexService;
    @Test
    public void createIndex() {
        indexService.createIndex("C:\\Users\\buydeem\\Desktop\\sso");
    }
}