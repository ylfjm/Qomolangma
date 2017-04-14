package com.boz.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.io.output.ByteArrayOutputStream;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Cartoon Zhang
 * @since 2016/11/7.
 */

public class QRCodeUtils {

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    public static byte[] generateQRCodeImgByteArray(String content, int width, int height) throws Exception {
        RenderedImage image = generateQRCodeImage(content, width, height);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static File generateQRCodeImgFile(String content, int width, int height) throws Exception {
        RenderedImage image = generateQRCodeImage(content, width, height);
        File resultFile = new File(System.getProperty("java.io.tmpdir") + File.separator + UUIDUtil.uuid() + ".jpg");
        ImageIO.write(image, "jpeg", resultFile);
        return resultFile;
    }

    static BufferedImage generateQRCodeImage(String content, int width, int height) throws Exception {
        if (width <= 0 || height <= 0) {
            width = 200;
            height = 200;
        }
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage image = toBufferedImage(bitMatrix);
        return attacheLogoImage(image);
    }

    static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    static BufferedImage attacheLogoImage(BufferedImage bufferedImage) throws IOException {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int logoWidth = width * 16 / 100;
        int logoHeight = height * 16 / 100;
        int x = (width - logoWidth) / 2;
        int y = (height - logoHeight) / 2;

//        BufferedImage logoImage = ImageIO.read(QRCodeUtils.class.getResourceAsStream("../webapp/static/base/images/apex_club.jpg"));
        //TODO Find the target path in webapp.
        String webappPath = QRCodeUtils.class.getResource("/").getPath();
        String logoImgPath = webappPath + "../../static/base/images/apex_club.jpg";
        BufferedImage logoImage = ImageIO.read(new File(logoImgPath));
        Graphics graphics = bufferedImage.getGraphics();
        graphics.drawImage(logoImage, x, y, logoWidth, logoHeight, null);
        return bufferedImage;
    }
}
