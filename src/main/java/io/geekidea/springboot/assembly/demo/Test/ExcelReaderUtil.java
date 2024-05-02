package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.context.AnalysisContext;
import io.geekidea.springboot.assembly.demo.model.MyObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// 假设你有一个DataClass类，它映射了Excel文件中的数据
//public class DataClass {
//    // 定义你的字段
//    // ...
//}

/**
 * 参考链接： https://wenku.csdn.net/answer/b99c84cf0def469583520fa1cd41dcf9
 */
public class ExcelReaderUtil {

    public static List<MyObject> readExcel(InputStream inputStream) {
        List<MyObject> list = new ArrayList<>();
        EasyExcel.read(inputStream, MyObject.class, new ReadListener<MyObject>() {
            @Override
            public void onException(Exception exception, AnalysisContext context) {
                // 异常处理
            }

            @Override
            public void invoke(MyObject data, AnalysisContext context) {
                list.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 所有数据解析完成后的操作
            }
        }).sheet().doRead();

        return list;
    }
}

// 在你的Controller中使用
//public class YourController {
//
//    @RequestMapping("/readExcel")
//    public List<DataClass> readExcel(InputStream inputStream) {
//        return ExcelReaderUtil.readExcel(inputStream);
//    }
//}