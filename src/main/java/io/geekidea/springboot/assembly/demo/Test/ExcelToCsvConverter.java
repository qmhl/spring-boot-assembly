package io.geekidea.springboot.assembly.demo.Test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelToCsvConverter {

    public static void main(String[] args) {
        String excelFilePath = "/Users/zhangqi1092/Desktop/tets/标签初始化.xlsx"; // 这里填写你的Excel文件路径
        String csvFilePath = "output.csv"; // 输出CSV文件路径


        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis);
             FileWriter fileWriter = new FileWriter(csvFilePath)) {

            Sheet sheet = workbook.getSheetAt(0); // 选择第一个sheet

            // 写入CSV文件的头部（如果需要）
            Row header = sheet.getRow(0);
            for (int i = 0; i < header.getLastCellNum(); i++) {
                if (i > 0) fileWriter.append(",");
                fileWriter.append(header.getCell(i).toString());
            }
            fileWriter.append("\n");

            // 遍历Excel文件，从第二行开始（假设第一行是标题）
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    // 收集前四列数据
                    List<String> rowData = new ArrayList<>();
                    for (int i = 0; i < 4; i++) {
                        rowData.add(row.getCell(i).toString());
                    }

                    // 获取原子标签列，假设是第五列
                    Cell tagsCell = row.getCell(4);
                    if (tagsCell != null) {
                        String tags = tagsCell.getStringCellValue();
                        // 按照顿号分割标签，并将每个标签作为一个新的记录写入CSV
                        for (String tag : tags.split("、")) {
                            // 将前四列数据与当前标签组合成新的行
                            rowData.add(tag);
                            // 将行数据写入CSV文件
                            writeRowToCsv(fileWriter, rowData);
                            // 清空rowData以便下一次迭代使用
                            rowData.remove(rowData.size() - 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRowToCsv(FileWriter fileWriter, List<String> rowData) throws IOException {
        for (int i = 0; i < rowData.size(); i++) {
            if (i > 0) fileWriter.append(",");
            fileWriter.append(rowData.get(i));
        }
        fileWriter.append("\n");
    }
}