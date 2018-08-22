package com.zc.filesearch.utils;


import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zengchao on 2018/8/22.
 */
public class FileUtils {
    public static void main(String[] args) throws IOException {
        String path = "C:\\\\Users\\\\buydeem\\\\Desktop";
        String[] suffix = {"txt"};
        List<Path> paths = FileUtils.gerAllFiles(path, suffix);
    }

    /**
     * 获取指定文件下的所有文件
     * @param pathStr 路径
     * @param suffix 后缀
     * @return
     */
    public static List<Path> gerAllFiles(String pathStr,String[] suffix) throws IOException {
        Path path = Paths.get(pathStr);
        SuffixFileVisitor suffixFileVisitor = new SuffixFileVisitor(suffix);
        Files.walkFileTree(path,suffixFileVisitor);
        return suffixFileVisitor.getFiles();
    }

    /**
     * 文件遍历器
     */
    static class SuffixFileVisitor implements FileVisitor<Path> {
        private String[] suffixList;
        private List<Path> files;

        public SuffixFileVisitor(String[] suffixList) {
            this.suffixList = suffixList;
            this.files = new LinkedList<>();
        }

        public String[] getSuffixList() {
            return suffixList;
        }

        public void setSuffixList(String[] suffixList) {
            this.suffixList = suffixList;
        }

        public List<Path> getFiles() {
            return files;
        }

        public void setFiles(List<Path> files) {
            this.files = files;
        }

        /**
         * 访问一个目录前
         * @param dir
         * @param attrs
         * @return
         * @throws IOException
         */
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        /**
         * 正在访问一个目录
         * @param file
         * @param attrs
         * @return
         * @throws IOException
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            String fileName = file.getFileName().toString();
            String[] fileNameSplit = fileName.split("\\.");
            String suffix = fileNameSplit[fileNameSplit.length-1];
            boolean contains = Arrays.asList(suffixList).contains(suffix);
            if (contains){
                files.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }

}
