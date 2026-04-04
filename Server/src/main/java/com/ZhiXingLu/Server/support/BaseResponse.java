package com.ZhiXingLu.Server.support;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author closer
 * @Description: 通用返回类
 * @Create: 2026/4/4 21:26
 */
@Data
public class BaseResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -52922753885915169L;

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

}
