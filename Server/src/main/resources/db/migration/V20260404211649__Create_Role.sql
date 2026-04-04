-- 角色表
CREATE TABLE `role`
(
    `id`         BIGINT UNSIGNED NOT NULL COMMENT 'ID',
    `code`       VARCHAR(63)     NOT NULL COMMENT '角色编码（USER:普通用户, ADMIN:管理员, ROOT:超级管理员）',
    `name`       VARCHAR(63)     NOT NULL COMMENT '角色名称',
    `status`     VARCHAR(31)     NOT NULL DEFAULT 'ENABLED' COMMENT '状态(ENABLED:正常, DISABLED:禁用)',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_code` (`code`)
) COMMENT ='角色';

-- 初始化角色数据
INSERT INTO `role` (`id`, `code`, `name`, `status`, `created_at`, `updated_at`)
VALUES (372651392227246080, 'USER', '普通用户', 'ENABLED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (365329103907336192, 'ADMIN', '管理员', 'ENABLED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       (372651466541924352, 'ROOT', '超级管理员', 'ENABLED', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);