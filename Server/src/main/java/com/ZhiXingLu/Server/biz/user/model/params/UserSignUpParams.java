package com.ZhiXingLu.Server.biz.user.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author closer
 * @Create: 2026/4/5 17:33
 */
@Data
@Schema(description = "用户注册请求参数")
public class UserSignUpParams implements Serializable {

    @Serial
    private static final long serialVersionUID = 4411757967204614144L;

    @NotBlank
    @Size(min = 2, max = 16)
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5\\p{Punct}]+$", message = "账号只能包含中英文、数字和常用标点符号")
    @Schema(description = "账号", example = "Closer")
    private String account;

    @NotBlank
    @Size(min = 8, max = 32)
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fa5\\p{Punct}]+$", message = "密码只能包含中英文、数字和常用标点符号")
    @Schema(description = "密码", example = "12345678")
    private String password;
}
