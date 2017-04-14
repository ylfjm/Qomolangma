//package com.boz.common.utils;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFFont;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.Date;
//import java.util.List;
//
//public class Excel2007Util {
//
//    private static Log logger = LogFactory.getLog(Excel2007Util.class);
//
//    /**
//     * 创建Excel行头
//     *
//     * @param titleList
//     * @param sheet
//     * @param headRowNumber
//     * @return
//     */
//    public static Row createFRow(List<String> titleList,
//                                 List<Integer> titleWidthList,
//                                 Workbook wb,
//                                 Sheet sheet,
//                                 Integer headRowNumber) {
//        // 列头
//        Row row = sheet.createRow(headRowNumber);
//        if (titleList != null && titleList.size() > 0) {
//            for (int i = 0; i < titleList.size(); i++) {
//                sheet.setColumnWidth(i, titleWidthList.get(i) * 256);
//                Cell cell = row.createCell(i);
//                cell.setCellValue(titleList.get(i));
//
//                CellStyle cellStyle = wb.createCellStyle();
//                // 指定单元格居中对齐
//                cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
//                // 指定单元格垂直居中对齐
//                cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
//                // 指定单元格自动换行
//                cellStyle.setWrapText(true);
//
//                // 设置单元格字体
//                Font font = wb.createFont();
//                font.setBoldweight(Font.BOLDWEIGHT_BOLD);
//                cellStyle.setFont(font);
//
//                // 设置单元格格式
//                cell.setCellStyle(cellStyle);
//            }
//        }
//        return row;
//    }
//
//    /**
//     * 创建Excel行头
//     *
//     * @param titles
//     * @param sheet
//     * @param headRowNumber
//     * @return
//     */
//    public static Row createRow(String[] titles,
//                                Integer[] titleWidths,
//                                Workbook wb,
//                                Sheet sheet,
//                                Integer headRowNumber) {
//        // 列头
//        Row row = sheet.createRow(headRowNumber);
//        if (titles != null && titles.length > 0) {
//            for (int i = 0; i < titles.length; i++) {
//                sheet.setColumnWidth(i, titleWidths[i] * 256);
//                Cell cell = row.createCell(i);
//                cell.setCellValue(titles[i]);
//
//                CellStyle cellStyle = wb.createCellStyle();
//                // 指定单元格居中对齐
//                cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
//                // 指定单元格垂直居中对齐
//                cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
//                // 指定单元格自动换行
//                cellStyle.setWrapText(true);
//
//                // 设置单元格字体
//                Font font = wb.createFont();
//                font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
//                cellStyle.setFont(font);
//
//                // 设置单元格格式
//                cell.setCellStyle(cellStyle);
//            }
//        }
//        return row;
//    }
//
//    public static void exportExcelData(String excelFileName,
//                                       HttpServletResponse response,
//                                       Workbook wb) {
//        String fileName = new Date().getTime() + "";
//        //获取文件名
//        fileName = excelFileName + CommonUtil.formatDate(new Date(), "yyyyMMdd") + ".xls";
//        OutputStream os = null;
//        try {
//            // 清空输出流
//            response.reset();
//            // 取得输出流
//            os = response.getOutputStream();
//            // 设定输出文件头
//            response.setHeader("Content-disposition",
//                    "attachment; filename=" + new String(fileName.getBytes(), "ISO-8859-1"));
//            // 定义输出类型
//            response.setContentType("application/msexcel");
//        } catch (IOException ex) {
//            // 捕捉异常
//            ex.printStackTrace();
//            logger.error("导出" + excelFileName + "发生异常:" + ex.getMessage());
//        }
//        try {
//            os.flush();
//            wb.write(os);
//        } catch (IOException e) {
//            e.printStackTrace();
//            logger.error("导出" + excelFileName + "发生异常:" + e.getMessage());
//        }
//    }
//}
