-- 用户基础信息表
CREATE TABLE `user`
(
    `id`         BIGINT UNSIGNED NOT NULL COMMENT 'ID',
    `account`    VARCHAR(31)     NOT NULL COMMENT '账号',
    `password`   VARCHAR(255)    NULL COMMENT '密码（加密存储）',
    `nickname`   VARCHAR(63)     NULL COMMENT '昵称',
    `avatar`     VARCHAR(511)    NULL COMMENT '头像URL',
    `gender`     TINYINT         NULL DEFAULT 0 COMMENT '性别：0-未知 1-男 2-女',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_account` (`account`)
) COMMENT ='用户';