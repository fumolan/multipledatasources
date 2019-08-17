
CREATE TABLE `teachers` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`userName` VARCHAR(32) NULL DEFAULT NULL COMMENT '用户名',
	`passWord` VARCHAR(32) NULL DEFAULT NULL COMMENT '密码',
	`user_sex` VARCHAR(32) NULL DEFAULT NULL,
	`nick_name` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
CREATE TABLE `users` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
	`userName` VARCHAR(32) NULL DEFAULT NULL COMMENT '用户名',
	`passWord` VARCHAR(32) NULL DEFAULT NULL COMMENT '密码',
	`user_sex` VARCHAR(32) NULL DEFAULT NULL,
	`nick_name` VARCHAR(100) NULL DEFAULT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=1
;
