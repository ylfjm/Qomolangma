package com.boz.common.utils;

import com.tocersoft.base.dto.BaseCondition;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public abstract class CommonUtil {

    private static final int BUFFER_SIZE = 2048000;
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    private static String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    private static Calendar calendar = Calendar.getInstance(Locale.CHINA);

    /**
     * String 转换 Long 数组
     */
    public static Long[] stringToLongArray(String ids) {
        String[] idArr = ids.split("-");
        Long[] idList = new Long[idArr.length];
        for (int i = 0; i < idArr.length; i++) {
            if (StringUtils.isNotBlank(idArr[i])) {
                try {
                    idList[i] = Long.valueOf(idArr[i]);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        return idList;
    }

    /**
     * 转义 特殊符
     */
    public static String escapeSpecialSign(String condition) {
        String bb = StringUtils.replace(condition, "/", "//");
        bb = StringUtils.replace(bb, "%", "/%");
        bb = StringUtils.replace(bb, "_", "/_");
        return bb;
    }

    /**
     * 随机生成指定位数且不重复的字符串.去除了部分容易混淆的字符，如1和l，o和0等，
     * <p/>
     * 随机范围1-9 a-z A-Z
     *
     * @param length 指定字符串长度
     * @return 返回指定位数且不重复的字符串
     */
    public static String getRandomString(int length) {
        StringBuffer bu = new StringBuffer();
        String[] arr = {"2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c",
                "d", "e", "f", "g", "h", "i", "j", "k", "m", "n", "p", "q",
                "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
                "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "P",
                "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random random = new Random();
        while (bu.length() < length) {
            String temp = arr[random.nextInt(57)];
            if (bu.indexOf(temp) == -1) {
                bu.append(temp);
            }
        }
        return bu.toString();
    }

    public static String formatDate(Date d) {
        return d != null ? sdf.format(d) : null;
    }

    public static String formatDate(Date d, String pattern) {
        if (d == null) {
            return "";
        }
        sdf.applyPattern(pattern);
        return sdf.format(d);
    }

    public static Date parseDate(String date, String pattern) {
        sdf.applyPattern(pattern);
        Date d = null;
        try {
            d = sdf.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return d;
    }

    public static Date parseDate(String date, String pattern, Locale locale) {
        Date result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
            result = simpleDateFormat.parse(date);
            return result;
        } catch (ParseException e) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", locale);
                result = simpleDateFormat.parse(date);
                return result;
            } catch (ParseException e1) {
                e1.printStackTrace();
                return result;
            }
        }

    }

    /**
     * 获取两个日期之间的天数
     *
     * @param d1 开始日期
     * @param d2 结束日期
     * @return
     */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
//		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
//			java.util.Calendar swap = d1;
//			d1 = d2;
//			d2 = swap;
//		}
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 设置天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date setDay(Date date, int day) {
        calendar.setTime(date);
//		calendar.set(Calendar.HOUR_OF_DAY, 0);
//		calendar.set(Calendar.MINUTE, 0);
//		calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 获取是周几 1表示周1
     *
     * @param d
     * @return
     */
    public static int getChineseWeekNum(Date d) {
        calendar.setTime(d);

        int week = calendar.get(Calendar.DAY_OF_WEEK);

        week = week - 1;    //默认周日为1

        return week;
    }

    public static int getDay(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取小时
     *
     * @param d
     * @return
     */
    public static int getHour(Date d) {
        calendar.setTime(d);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取星期几
     *
     * @param num
     * @return
     */
    public static String getChineseWeekNameByNum(int num) {
        return weeks[num];
    }

    /**
     * 获取日期
     *
     * @param d
     * @return
     */
    public static String getDate(Date d) {
        return sdf2.format(d);
    }

    /**
     * 获取当天起始时间 00:00
     *
     * @return
     */
    public static Date getCurrenDateStart() {
        Date now = new Date();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取当天结束时间 23:59:59
     *
     * @return
     */
    public static Date getCurrenDateEnd() {
        Date now = new Date();
        calendar.setTime(now);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        return calendar.getTime();
    }

    /**
     * 获取当周最后一天日期
     *
     * @param now
     * @return
     */
    public static Date getWeekEnd(Date now) {
        int addNum = 0;
        int nowWeek = getChineseWeekNum(now);
        if (nowWeek == 0) {
            addNum = 1;
        } else {
            addNum = 7 - nowWeek + 1;
        }

        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, addNum);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取当周第一天日期
     *
     * @param startDate
     * @return
     */
    public static Date getWeekStart(Date startDate) {
        int week = getChineseWeekNum(startDate);
        int subNum = 0;
        if (week == 0) {
            subNum = -6;
        } else {
            subNum = -week + 1;
        }
        calendar.setTime(startDate);
        calendar.add(Calendar.DAY_OF_YEAR, subNum);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 增加目标日期天数
     *
     * @param targetDate
     * @param addDays
     * @return
     */
    public static Date addTargetDateDay(Date targetDate, int addDays) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(targetDate);
        calendar.add(Calendar.DAY_OF_YEAR, addDays);
        return calendar.getTime();
    }

    /**
     * 获取指定日期月份有多少天
     *
     * @return
     */
    public static int getMonthHaveDayCount(Date date) {
        int result = 0;
        Calendar calendar1 = Calendar.getInstance(Locale.CHINA);
        calendar1.setTime(date);

        Calendar calendar2 = Calendar.getInstance(Locale.CHINA);
        calendar2.clear();
        calendar2.set(Calendar.YEAR, calendar1.get(Calendar.YEAR));
        calendar2.set(Calendar.MONTH, calendar1.get(Calendar.MONTH));
        result = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
        return result;
    }

    /**
     * 检查是否需要转义符号
     *
     * @param condition
     */
    public static void checkEscapeValue(BaseCondition condition) {
        Field[] fields = condition.getClass().getDeclaredFields();
        for (Field field : fields) {
            Object val = ReflectionUtil.getFieldValue(condition, field.getName());
            if (val instanceof String) {
                //只对string类型的数据进行like检查
                String str = val.toString();
                if (str.indexOf("%") != -1 || str.indexOf("_") != -1) {
                    condition.setEscapeSymbol(true);
                }
            }
        }
    }

    /**
     * 上传文件
     *
     * @param src 源文件
     * @param dst 目标文件
     */
    public static void uploadFile(File src, File dst) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
                out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
                byte[] buffer = new byte[BUFFER_SIZE];
                int length = 0;
                while ((length = in.read(buffer)) != -1) {
                    out.write(buffer, 0, length);
                }
                out.flush();
            } finally {
                IOUtils.closeQuietly(in);
                IOUtils.closeQuietly(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单元格中的值
     *
     * @param cell
     * @return
     */
    public static String getCellValue(HSSFCell cell) {
        String val = null;
        if (null != cell) {
            int cellType = cell.getCellType();
            switch (cellType) {
                case HSSFCell.CELL_TYPE_BLANK:
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        val = CommonUtil.formatDate(cell.getDateCellValue(), "yyyy.M.d").trim();
                    } else {
                        val = cell.getCellFormula().trim();
                    }
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    val = String.valueOf(cell.getBooleanCellValue());
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        val = CommonUtil.formatDate(cell.getDateCellValue(), "yyyy.M.d").trim();
                    } else {
                        BigDecimal bigDecimal = new BigDecimal(cell.getNumericCellValue());
                        val = bigDecimal.toPlainString();
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    val = cell.getStringCellValue().trim();
                    break;
                default:
                    break;
            }
        }
        if (val != null) {
            val = val.trim();
        }
        return val;
    }

    /**
     * 将字符串中包含"'"的字符替换为空白
     *
     * @param target 目标字符串
     * @return
     */
    public static String replaceTargetForBlank(String target) {
        if (StringUtils.isBlank(target)) {
            return "";
        }
        return StringUtils.replace(target, "'", "");
    }

    /**
     * 将图片字符串存储于本地磁盘中
     *
     * @param imgStr       Base64加码的图片字符串
     * @param destFilePath 目标文件绝对路径
     * @return
     */
    public static void saveBase64ImageToFile(String imgStr, String destFilePath) {
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null || "".equals(imgStr)) {
            //图像数据为空
            return;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        OutputStream os = null;
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    //调整异常数据
                    b[i] += 256;
                }
            }
            os = new FileOutputStream(destFilePath);
            os.write(b);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 将文本转成HTML格式
     *
     * @return String
     */
    public static String convertTextToHtml(String text) {
        if (StringUtils.isNotBlank(text)) {
            text = text.replace("\r\n", "<br/>");
            text = text.replace("\n", "<br/>");
            text = text.replace(" ", "&nbsp;");
            return text;
        }
        return null;
    }

    /**
     * 模糊搜索后，将关键词的样式设定为突出颜色
     *
     * @param text 原字符串
     * @param key  搜索关键词
     * @return
     */
    public static String convertLikeStyle(String text, String key) {
        if (StringUtils.isBlank(key)) {
            return text;
        } else {
            int index = text.indexOf(key);
            if (StringUtils.isNotBlank(key) && index != -1) {
                String red = "<span class='red'>" + key + "</span>";
                text = text.substring(0, index).concat(red).concat(text.substring(index + key.length()));
            }
        }
        return text;
    }

    public static void main(String[] args) {
        String imgStr = "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";
        CommonUtil.saveBase64ImageToFile(imgStr, "d:\\test.jpg");
    }

}
