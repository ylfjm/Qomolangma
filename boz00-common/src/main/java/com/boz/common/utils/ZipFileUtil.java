package com.boz.common.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ZipFileUtil {

    private static Log logger = LogFactory.getLog(ZipFileUtil.class);


    /**
     * zip压缩功能. 压缩baseDir(文件夹目录)下所有文件，包括子目录
     *
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void zipFile(String baseDir, String fileName) {
        List fileList = getSubFiles(new File(baseDir));
        ZipOutputStream zos = null;

        try {
            zos = new ZipOutputStream(new FileOutputStream(fileName));
            zos.setEncoding("GBK");    //设置为GBK不会乱码了
            ZipEntry ze = null;
            byte[] buf = new byte[1024];
            int readLen = 0;
            for (int i = 0; i < fileList.size(); i++) {
                File f = (File) fileList.get(i);
                ze = new ZipEntry(getAbsFileName(baseDir, f));
                ze.setSize(f.length());
                ze.setTime(f.lastModified());
                zos.putNextEntry(ze);
                InputStream is = new BufferedInputStream(new FileInputStream(f));
                while ((readLen = is.read(buf, 0, 1024)) != -1) {
                    zos.write(buf, 0, readLen);
                }
                zos.flush();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != zos) {
                IOUtils.closeQuietly(zos);
            }
        }

    }


    /**
     * 给定根目录，返回另一个文件名的相对路径，用于zip文件中的路径.
     *
     * @param baseDir      java.lang.String 根目录
     * @param realFileName java.io.File 实际的文件名
     * @return 相对文件名
     */
    private static String getAbsFileName(String baseDir, File realFileName) throws Exception {
        File real = realFileName;
        File base = new File(baseDir);
        logger.debug("realName:" + real.getName());
        String ret = real.getName();
        while (true) {
            real = real.getParentFile();
            if (real == null)
                break;
            if (real.equals(base))
                break;
            else {
                logger.debug("realName:" + real.getName());
                ret = real.getName() + "/" + ret;
            }

        }
        return ret;
    }

    /**
     * 取得指定目录下的所有文件列表，包括子目录.
     *
     * @param baseDir File 指定的目录
     * @return 包含java.io.File的List
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static List getSubFiles(File baseDir) {
        List ret = new ArrayList();
        File[] tmp = baseDir.listFiles();
        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i].isFile())
                ret.add(tmp[i]);
            if (tmp[i].isDirectory())
                ret.addAll(getSubFiles(tmp[i]));
        }
        return ret;
    }
}
