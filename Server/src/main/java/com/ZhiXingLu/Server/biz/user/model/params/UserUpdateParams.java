package com.ZhiXingLu.Server.biz.user.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author closer
 * @Description:
 * @Create: 2026/4/5 20:35
 */
@Data
@Schema(description = "更新个人信息请求参数")
public class UserUpdateParams implements Serializable {

    @Serial
    private static final long serialVersionUID = 3567890123456789012L;

    @Size(min = 1, max = 31, message = "昵称长度必须在1到31个字符之间")
    @Schema(description = "昵称", example = "小白")
    private String nickname;

    @Size(max = 511, message = "头像 URL 最多 511 个字符")
    @Schema(description = "头像 URL", example = "https://example.com/avatar.jpg")
    private String avatar;

    @Min(value = 0, message = "性别只能是 0（未知）、1（男）、2（女）")
    @Max(value = 2, message = "性别只能是 0（未知）、1（男）、2（女）")
    @Schema(description = "性别（0:未知 1:男 2:女）", example = "1")
    private Integer gender;
}
