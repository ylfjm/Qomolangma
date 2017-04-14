package com.boz.common.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;


/**
 * 图片处理工具类
 *
 * @author vinartis
 * @createDate Apr 8, 2013
 */
public class ImageUtils {

    private static final Log logger = LogFactory.getLog(ImageUtils.class);

    //图片裁剪放缩
    public static void imageResizer(String filePath, Integer maxW, Integer maxH, double modelY, double modelX, double modelW, double modelH) {
        try {
            filePath = filePath.replace("\\\\", File.separator);
            File file = new File(filePath);
            // 图片缩放比例
            Double radio = 0D;
            if (ImageUtils.getWidth(file) > 0 || ImageUtils.getHeight(file) > 0) {
                // 原图的大小
                Integer sw = ImageUtils.getWidth(file);
                Integer sh = ImageUtils.getHeight(file);
                Integer tw = 0;
                Integer th = 0;
                // 等比例缩放后的大小
                if (sw >= maxW) {
                    tw = maxW;
                    th = tw * sh / sw;
                    radio = sw.doubleValue() / tw.doubleValue();
                }
                if (th >= maxH) {
                    th = maxH;
                    tw = th * sw / sh;
                    radio = sh.doubleValue() / th.doubleValue();
                }
                // 如果原图像的大小小于要缩放的图像大小，直接将要缩放的图像复制过去
            }

            DecimalFormat df = new DecimalFormat("#0");

            Integer y = Integer.parseInt(df.format(modelY * radio));
            Integer x = Integer.parseInt(df.format(modelX * radio));
            Integer w = Integer.parseInt(df.format(modelW * radio));
            Integer h = Integer.parseInt(df.format(modelH * radio));


            saveImage(file, filePath, y, x, w, h);

        } catch (IOException e) {
            String msg = "图片裁剪放缩：" + e.getMessage();
            logger.error(msg, e);
        }

    }


    /**
     * 保存图片
     *
     * @param img    原图路径
     * @param dest   目标图路径
     * @param top    选择框的左边y坐标
     * @param left   选择框的左边x坐标
     * @param width  选择框宽度
     * @param height 选择框高度
     * @return
     * @throws IOException
     */
    public static boolean saveImage(File img, String dest, int top, int left, int width, int height) throws IOException {
        File fileDest = new File(dest);
        if (!fileDest.getParentFile().exists()) {
            fileDest.getParentFile().mkdirs();
        }
        boolean result = false;
        String ext = getExtension(dest).toLowerCase();
        //裁剪图片
        BufferedImage bi = resizeImage(img, top, left, width, height);

        //压缩图片
        if (!"jpeg".equals(ext) && !"jpg".equals(ext)) {
            // || fileSize < 200000 小于200KB
            // 不压缩图片
            result = ImageIO.write(bi, ext, fileDest);
        } else {
            result = compressImage(bi, fileDest, 0.8f);
        }

        return result;
    }


    /**
     * 压缩图片
     *
     * @param img
     * @param fileDest
     * @param top
     * @param left
     * @param width
     * @param height
     * @param quantity
     */
    public static boolean compressImage(BufferedImage bi, File fileDest, Float quantity) {
        ByteArrayOutputStream bos = null;
        ByteArrayInputStream bis = null;
        try {
            bos = new ByteArrayOutputStream(); // 取得内存输出流
            JPEGEncodeParam param = JPEGCodec.getDefaultJPEGEncodeParam(bi);
            if (null == quantity) {
                quantity = 0.8f;
            }
            param.setQuality(quantity, false);
            // 设置编码器
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos, param);
            encoder.encode(bi);
            byte[] bytes = bos.toByteArray();

            bis = new ByteArrayInputStream(bytes); // 将b作为输入流；
            BufferedImage image = ImageIO.read(bis);
            return ImageIO.write(image, "jpeg", fileDest);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(bis);
        }

        return false;
    }

    public static String getExtension(File f) {
        return (f != null) ? getExtension(f.getName()) : "";
    }

    public static String getExtension(String filename) {
        return getExtension(filename, "");
    }

    public static String getExtension(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > -1) && (i < (filename.length() - 1))) {
                return filename.substring(i + 1);
            }
        }
        return defExt;
    }

    public static String trimExtension(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');
            if ((i > -1) && (i < (filename.length()))) {
                return filename.substring(0, i);
            }
        }
        return filename;
    }

    public static BufferedImage resizeImage(File img, int top, int left, int width, int height) throws IOException {
        BufferedImage bi = (BufferedImage) ImageIO.read(img);
        height = Math.min(height, bi.getHeight());
        width = Math.min(width, bi.getWidth());
        if (height <= 0)
            height = bi.getHeight();
        if (width <= 0)
            width = bi.getWidth();
        top = Math.min(Math.max(0, top), bi.getHeight() - height);
        left = Math.min(Math.max(0, left), bi.getWidth() - width);

        BufferedImage bi_cropper = bi.getSubimage(left, top, width, height);

        return bi_cropper;
    }

    /**
     * 获得图片宽度
     *
     * @param file 图片文件
     * @return
     */
    public static int getWidth(File file) {
        try {
            Image src = ImageIO.read(file);
            return src.getWidth(null);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获得图片高度
     *
     * @param file 图片文件
     * @return
     */
    public static int getHeight(File file) {
        try {
            Image src = ImageIO.read(file);
            return src.getHeight(null);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
