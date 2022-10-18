package io.geekidea.springboot.assembly.demo.Exception;

/**
 * @author zhangqi
 * @Description: 权限校验的异常处理类
 * @date 2022/02/25 11:18
 */
public class PermissionException extends RuntimeException {
    public PermissionException() {
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }

}
