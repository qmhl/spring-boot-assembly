package io.geekidea.springboot.assembly.demo.Test;

import io.geekidea.springboot.assembly.demo.Exception.BusinessException;
import io.geekidea.springboot.assembly.demo.entity.ActiveExtendDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.SelectKey;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class Test13_exception {
    public static void main(String[] args) {
        try {
            testException();
        } catch (BusinessException e) {
            log.warn("业务异常： ", e);
        } catch (Exception e) {
            log.warn("异常： ", e);
        }


//        try {
//            testException();
//        } catch (Exception e) {
//            log.warn("异常： ", e);
//
//        }
    }

    public static void testException() throws Exception {

        if (true) {
            throw new BusinessException("项目id不存在！");
        }


    }
}
