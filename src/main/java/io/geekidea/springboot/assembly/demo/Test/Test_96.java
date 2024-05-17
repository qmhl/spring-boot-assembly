package io.geekidea.springboot.assembly.demo.Test;

import com.alibaba.fastjson.JSON;
import io.geekidea.springboot.assembly.demo.Exception.BusinessException;
import io.geekidea.springboot.assembly.demo.model.Person;
import io.geekidea.springboot.assembly.demo.utils.ObjectUtil;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 参考链接：  https://blog.csdn.net/weixin_49114503/article/details/135196097
 */
@Slf4j
public class Test_96 {
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("1"));
        list.add(Arrays.asList("2"));
        list.add(Arrays.asList("3"));
        System.out.println(JSON.toJSONString(list));

        Long total = checkOneColumn(list);
        System.out.println(total);
    }


    private static Long checkOneColumn(List<List<String>> excelData) throws BusinessException {
        Long total = 0L;
        log.info("checkOneColumn excelData:{}", JSON.toJSONString(excelData));
        Pattern p = Pattern.compile("\\d+");
        for (List<String> rowData : excelData) {
            if (ObjectUtil.isEmpty(rowData)) {
                throw new BusinessException("上传的文件中有空行，请重新处理");
            }
            if (rowData.size() < 1) {
                throw new BusinessException("文件列中有空值，请补全文件内容");
            }
            String account = rowData.get(0);
            if (ObjectUtil.isEmpty(account) ) {
                throw new BusinessException("上传的文件中 账号有空值！");
            }
            if (!p.matcher(account).matches()) {
                throw new BusinessException("上传的文件中 账号值有非数字出现！");
            }
            total++;
            log.info("checkOneColumn total:{}", total);
        }
        return total;
    }

}



