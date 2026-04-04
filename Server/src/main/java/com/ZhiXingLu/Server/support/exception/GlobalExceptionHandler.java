package com.ZhiXingLu.Server.support.exception;

import com.ZhiXingLu.Server.support.BaseResponse;
import com.ZhiXingLu.Server.support.BaseResult;
import com.ZhiXingLu.Server.support.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author closer
 * @Description: 全局异常处理器
 * @Create: 2026/4/4 22:18
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return BaseResult.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return BaseResult.error(ErrorCode.INTERNAL_ERROR, "系统错误");
    }

    @ExceptionHandler(Exception.class)
    public BaseResponse<?> exceptionHandler(Exception e) {
        log.error("未处理的异常", e);
        return BaseResult.error(ErrorCode.INTERNAL_ERROR, "系统错误");
    }

}

