package io.geekidea.springboot.assembly.demo.utils;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

public class ExcelMergeRowUtils implements CellWriteHandler {
 
    /**
     * 合并字段的下标
     */
    private int[] mergeColumnIndex;
    /**
     * 合并几行
     */
    private int mergeRowIndex;
 
    public ExcelMergeRowUtils(int mergeRowIndex, int[] mergeColumnIndex) {
        this.mergeRowIndex = mergeRowIndex;
        this.mergeColumnIndex = mergeColumnIndex;
    }
 
    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer integer, Integer integer1, Boolean aBoolean) {
 
    }
 
    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer integer, Boolean aBoolean) {
 
 
 
 
    }
 
 
 

 
    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<WriteCellData<?>> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
 
        //当前行
        int curRowIndex = cell.getRowIndex();
        //当前列
        int curColIndex = cell.getColumnIndex();
 
        if (curRowIndex > mergeRowIndex) {
            for (int i = 0; i < mergeColumnIndex.length; i++) {
                if (curColIndex == mergeColumnIndex[i]) {
                    mergeWithPrevRow(writeSheetHolder, cell, curRowIndex, curColIndex);
                    break;
                }
            }
        }
 
    }
 
    private void mergeWithPrevRow(WriteSheetHolder writeSheetHolder, Cell cell, int curRowIndex, int curColIndex) {
        //获取当前行的当前列的数据和上一行的当前列列数据，通过上一行数据是否相同进行合并
        Object curData = cell.getCellTypeEnum() == CellType.STRING ? cell.getStringCellValue() :
                cell.getNumericCellValue();
        Row preRow = cell.getSheet().getRow(curRowIndex - 1);
        if (preRow == null) {
            // 当获取不到上一行数据时，使用缓存sheet中数据
            preRow = writeSheetHolder.getCachedSheet().getRow(curRowIndex - 1);
        }
        Cell preCell=preRow.getCell(curColIndex);
            Object preData = preCell.getCellTypeEnum() == CellType.STRING ? preCell.getStringCellValue() :
                    preCell.getNumericCellValue();
            // 比较当前行的第一列的单元格与上一行是否相同，相同合并当前单元格与上一行
            if (curData.equals(preData)) {
                Sheet sheet = writeSheetHolder.getSheet();
                List<CellRangeAddress> mergeRegions = sheet.getMergedRegions();
                boolean isMerged = false;
                for (int i = 0; i < mergeRegions.size() && !isMerged; i++) {
                    CellRangeAddress cellRangeAddr = mergeRegions.get(i);
                    // 若上一个单元格已经被合并，则先移出原有的合并单元，再重新添加合并单元
                    if (cellRangeAddr.isInRange(curRowIndex - 1, curColIndex)) {
                        sheet.removeMergedRegion(i);
                        cellRangeAddr.setLastRow(curRowIndex);
                        sheet.addMergedRegion(cellRangeAddr);
                        isMerged = true;
                    }
                }
                // 若上一个单元格未被合并，则新增合并单元
                if (!isMerged) {
                    CellRangeAddress cellRangeAddress = new CellRangeAddress(curRowIndex - 1, curRowIndex, curColIndex,
                            curColIndex);
                    sheet.addMergedRegion(cellRangeAddress);
                }
            }
    }
}