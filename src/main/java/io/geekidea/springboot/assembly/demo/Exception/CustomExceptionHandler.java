package io.geekidea.springboot.assembly.demo.Exception;

import com.example.demo.model.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    private static final String JSF_SERVICE_HANDLER = "com.jd.qmx.promotool.exception.CustomExceptionHandler";



    //权限校验异常
    @ExceptionHandler(value = PermissionException.class)
    RestResponse handlePermissionException(PermissionException e, HttpServletRequest request) {
        log.error("handlePermissionException url {}, 出现异常：", request.getRequestURI(), e);
        String errMsg = "用户拥有的权限校验不通过，请勿越权访问！";
        RestResponse errorRes = RestResponse.failure(errMsg);
        return errorRes;
    }



    //捕获全局异常
    @ExceptionHandler(value = Exception.class)
    RestResponse handleException(Exception e, HttpServletRequest request) {
        log.error("handleException url {}, msg: ", request.getRequestURI(), e);
        String errMsg = "系统出错,请稍后重试！";
        RestResponse errorRes = RestResponse.failure(errMsg);
        return errorRes;
    }


}