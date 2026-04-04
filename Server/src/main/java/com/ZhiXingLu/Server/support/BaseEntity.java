package com.ZhiXingLu.Server.support;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author closer
 * @Description: 实体基类，包含公共字段（id、createdAt、updatedAt）
 * @Create: 2026/4/4 21:25
 */
@Data
public abstract class BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID（雪花算法）
     */
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long id;

    /**
     * 创建时间（数据库自动填充）
     */
    @Column(onInsertValue = "now()")
    private LocalDateTime createdAt;

    /**
     * 最后更新时间（数据库自动填充）
     */
    @Column(onInsertValue = "now()", onUpdateValue = "now()")
    private LocalDateTime updatedAt;
}
