package io.geekidea.springboot.assembly.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> implements Serializable {

    private int code = 200;

    private String message;

    private T data;

    public RestResponse(T data) {
        this.data = data;
    }

    public RestResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static <T> RestResponse<T> ok(T data) {
        return new RestResponse<T>(200, "成功", data);
    }

    /**
     * 请求成功
     *
     * @param <T>
     * @return 响应体封装
     */
    public static <T> RestResponse<T> ok() {
        return new RestResponse<T>(200, "成功");
    }

    /**
     * 请求失败
     *
     * @param msg 错误信息
     * @param <T>
     * @return 响应体封装
     */
    public static <T> RestResponse<T> failure(String msg) {
        return new RestResponse<T>(500, msg);
    }


}
