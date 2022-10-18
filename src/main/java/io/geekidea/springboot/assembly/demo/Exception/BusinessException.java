package io.geekidea.springboot.assembly.demo.Exception;


/**
 * 处理业务异常，业务异常包装中文提示信息，直接返回给用户进行提示，不允许不进行包装就直接返回
 * @author gaorunding1
 * @date 2022-03-03 10:26
 */
public class BusinessException extends RuntimeException {
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
