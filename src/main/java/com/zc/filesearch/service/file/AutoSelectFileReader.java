package com.zc.filesearch.service.file;

import com.zc.filesearch.bean.file.FileDoc;
import com.zc.filesearch.bean.file.ReadFileResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.List;

/**
 * Created by zengchao on 2018/8/22.
 */
public class AutoSelectFileReader implements FileReadService {
    private List<DocReader> docReaderList;
    private DocReader defaultDocReader = new NoSelectDocReader();

    public AutoSelectFileReader(List<DocReader> docReaderList, DocReader defaultDocReader) {
        this.docReaderList = docReaderList;
        this.defaultDocReader = defaultDocReader;
    }

    public AutoSelectFileReader(List<DocReader> docReaderList) {
        this.docReaderList = docReaderList;
    }

    /**
     * 读取文件
     * @param path
     * @return
     */
    @Override
    public ReadFileResult readFile(Path path) {
        //1.前置检查
        ReadFileResult result = afterCheck(path);
        if (!result.isSuccess()){
            return result;
        }
        //2.自动选择适配的文件读取实现
        String[] split = path.getFileName().toString().split("\\.");
        String suffix = split[split.length - 1];
        for (DocReader docReader : docReaderList) {
            if (suffix.equals(docReader.getDocSuffix())){
                defaultDocReader = docReader;
            }
        }
        //3.获取文件基本信息
        FileDoc fileDoc = new FileDoc();
        //设置文件内容
        fileDoc.setContent(null);
        //设置文件名称
        fileDoc.setFileName(path.getFileName().toString());
        //设置文件修改时间
        try {
            FileTime fileTime = Files.getLastModifiedTime(path);
            long millis = fileTime.toMillis();
            fileDoc.setLastModified(new Date(millis));
        } catch (IOException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setException(e);
            return result;
        }
        fileDoc.setPath(path.toString());
        fileDoc.setType(suffix);
        result.setFileDoc(fileDoc);
        //读取内容
        defaultDocReader.read(result,path);
        return result;
    }

    /**
     * 文件检查
     * @param path
     * @return
     */
    private ReadFileResult afterCheck(Path path){
        ReadFileResult result = new ReadFileResult();
        result.setSuccess(true);
        //判断文件是否存在
        if (!Files.exists(path)){
            result.setSuccess(false);
            result.setException(new RuntimeException("文件不存在!"+path));
            return result;
        }
        //判断是否是文件夹
        if (Files.isDirectory(path)){
            result.setSuccess(false);
            result.setException(new RuntimeException("不是文件!"+path));
            return result;
        }
        //判断是否可读
        if (!Files.isReadable(path)){
            result.setSuccess(false);
            result.setException(new RuntimeException("文件不可读!"+path));
            return result;
        }
        return result;
    }

    /**
     * 私有文件处理器
     */
    private class NoSelectDocReader implements DocReader{

        @Override
        public void read(ReadFileResult result, Path path) {
            throw new RuntimeException("没有注入文件读取器!");
        }

        @Override
        public String getDocSuffix() {
            throw new RuntimeException("没有注入文件读取器!");
        }
    }
}
