package com.boz.common.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class CompresszZipFile {

    static final int BUFFER = 2048;

    /**
     * 读取ZIP文件，只适合于ZIP文件对于RAR文件无效，因为ZIP文件的压缩算法是公开的，而RAR不是
     *
     * @param zipfilepath：ZIP文件的路径，unzippath：要解压到的文件路径
     * @version 1.0
     */
    public void ReadZip(String zipfilepath, String unzippath) {

        try {
            System.out.print("jinru++++++++++++");
            BufferedOutputStream bos = null;
            //创建输入字节流
            FileInputStream fis = new FileInputStream(zipfilepath);
            //根据输入字节流创建输入字符流
            BufferedInputStream bis = new BufferedInputStream(fis);
            //根据字符流，创建ZIP文件输入流
            ZipInputStream zis = new ZipInputStream(bis);
            //zip文件条目，表示zip文件
            ZipEntry entry;

            //循环读取文件条目，只要不为空，就进行处理
            while ((entry = zis.getNextEntry()) != null) {

                int count;
                byte date[] = new byte[BUFFER];
                //如果条目是文件目录，则继续执行
                if (entry.isDirectory()) {
                    String basePath = zipfilepath.substring(0, zipfilepath.lastIndexOf("."));
                    String filePath = basePath + File.separator + entry.getName().substring(0, entry.getName().length() - 1);
                    File file = new File(filePath);
                    file.mkdir();
                    continue;
                } else {
                    int begin = zipfilepath.lastIndexOf(File.separator) + 1;
                    int end = zipfilepath.lastIndexOf(".");
                    String zipRealName = zipfilepath.substring(begin, end);
                    bos = new BufferedOutputStream(new FileOutputStream(this.getRealFileName(unzippath + File.separator + zipRealName, entry.getName())));
                    while ((count = zis.read(date)) != -1) {
                        bos.write(date, 0, count);
                    }
                    bos.flush();
                    bos.close();
                }
            }
            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private File getRealFileName(String zippath, String absFileName) {
        //判断是/还是\
        String[] dirs = null;

        int found = absFileName.indexOf("/");
        if (found != -1) {
            dirs = absFileName.split("/", absFileName.length());
        } else {
            dirs = absFileName.split("\\\\", absFileName.length());
        }

        //创建文件对象
        File file = new File(zippath);
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                //根据file抽象路径和dir路径字符串创建一个新的file对象，路径为文件的上一个目录
                file = new File(file, dirs[i]);
            }
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file, dirs[dirs.length - 1]);
        return file;
    }
}