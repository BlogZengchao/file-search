package com.zc.filesearch.service.file;

import com.zc.filesearch.bean.file.FileDoc;
import com.zc.filesearch.bean.file.ReadFileResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by zengchao on 2018/8/22.
 */
public class TxtReadService implements DocReader {
    private static final String TXT_SUFFIX = "txt";

    @Override
    public void read(ReadFileResult result, Path path) {
        try {
            List<String> lines = Files.readAllLines(path);
            StringBuilder sb = new StringBuilder();
            lines.forEach(sb::append);
            FileDoc fileDoc = result.getFileDoc();
            fileDoc.setContent(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setException(e);
        }
    }

    @Override
    public String getDocSuffix() {
        return TXT_SUFFIX;
    }
}
