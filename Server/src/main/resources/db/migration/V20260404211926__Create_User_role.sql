-- 用户角色关联表
CREATE TABLE `user_role`
(
    `id`         BIGINT UNSIGNED NOT NULL COMMENT 'ID',
    `user_id`    BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    `role_id`    BIGINT UNSIGNED NOT NULL COMMENT '角色ID',
    `created_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user` (`user_id`),
    KEY `idx_role` (`role_id`)
) COMMENT ='用户角色关联';
