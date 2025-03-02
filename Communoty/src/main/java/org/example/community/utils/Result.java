package org.example.community.utils;
import lombok.Data;

/**
 * 统一 API 响应结果封装类
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;
    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 成功响应（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "成功", data);
    }
    // 失败响应（带错误信息）
    public static <T> Result<T> error(String message) {
        return new Result<>(400, message, null);
    }
}



