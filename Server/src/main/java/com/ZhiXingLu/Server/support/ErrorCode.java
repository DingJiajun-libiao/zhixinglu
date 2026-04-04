package com.ZhiXingLu.Server.support;

import lombok.Getter;

/**
 * @author closer
 * @Description: 错误码
 * @Create: 2026/4/4 21:27
 */
@Getter
public enum ErrorCode {

    BAD_REQUEST(400, "请求格式不正确"),

    INVALID_ARGUMENT(400, "参数不正确"),

    UNAUTHORIZED(401, "未登陆"),

    INVALID_AUTHENTICATION(401, "认证无效"),

    ACCESS_DENIED(403, "无权访问"),

    NOT_FOUND(404, "对象不存在"),

    INVALID_REQUEST(400, "非法请求"),

    INTERNAL_ERROR(500, "服务器内部错误"),

    PARAMS_ERROR(500, "参数错误");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
