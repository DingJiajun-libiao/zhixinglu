package com.ZhiXingLu.Server.support.exception;

import com.ZhiXingLu.Server.support.ErrorCode;
import lombok.Getter;

import java.io.Serial;

/**
 * @author closer
 * @Description: 自定义异常类
 * @Create: 2026/4/4 21:50
 */
@Getter
public class BusinessException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = -1889147832712544158L;

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

}

