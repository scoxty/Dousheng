DROP DATABASE IF EXISTS `Dousheng`;
CREATE DATABASE `Dousheng` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `Dousheng`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` BIGINT NOT NULL,
	`name` VARCHAR(32) NOT NULL COMMENT '用户名',
  `password` VARCHAR(64) NOT NULL COMMENT '密码',
  `dousheng_num` VARCHAR(16) NOT NULL COMMENT '抖声号，唯一标识',
  `sex` INT(11) NOT NULL COMMENT '性别 1:男  0:女  2:保密',
  `birthday` DATE NOT NULL COMMENT '生日',
  `country` VARCHAR(32) DEFAULT NULL COMMENT '国家',
  `province` VARCHAR(32) DEFAULT NULL COMMENT '省份',
  `city` VARCHAR(32) DEFAULT NULL COMMENT '城市',
  `district` VARCHAR(32) DEFAULT NULL COMMENT '区县',
	`signature` VARCHAR(100) NOT NULL COMMENT '个性签名',
	`avatar` VARCHAR(128) NOT NULL COMMENT '头像',
  `background_image` VARCHAR(255) DEFAULT NULL COMMENT '个人介绍的背景图',
  `can_dousheng_num_be_updated` INT(1) NOT NULL COMMENT '抖声号能否被修改，1：默认，可以修改；0，无法修改',
  `create_time` DATETIME NOT NULL COMMENT '创建时间 创建时间',
  `update_time` DATETIME NOT NULL COMMENT '更新时间 更新时间',
	PRIMARY KEY (`id`),
  UNIQUE INDEX idx_name(name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';


-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` BIGINT NOT NULL,
  `author_id` BIGINT NOT NULL COMMENT '对应用户表id，视频的作者',
  `play_url` VARCHAR(255) NOT NULL COMMENT '视频播放地址',
  `cover_url` VARCHAR(255) NOT NULL COMMENT '视频封面地址',
  `title` VARCHAR(128) DEFAULT NULL COMMENT '视频标题，可以为空',
  `width` INT(6) NOT NULL COMMENT '视频width',
  `height` INT(6) NOT NULL COMMENT '视频height',
  `is_private` INT(1) NOT NULL COMMENT '是否私密，用户可以设置私密，如此可以不公开给比人看',
  `create_time` DATETIME NOT NULL COMMENT '创建时间 创建时间',
  `update_time` DATETIME NOT NULL COMMENT '更新时间 更新时间',
  PRIMARY KEY (`id`),
	INDEX idx_author_id(author_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短视频表';

-- ----------------------------
-- Table structure for favorite
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL COMMENT '用户id',
  `video_id` BIGINT NOT NULL COMMENT '喜欢的短视频id',
	PRIMARY KEY (`id`),
	INDEX idx_user_id(`user_id`),
	INDEX idx_video_id(`video_id`),
	UNIQUE INDEX idx_user_video(`user_id`, `video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='视频点赞表';


-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
	`id` BIGINT NOT NULL,
	`user_id` BIGINT NOT NULL COMMENT '用户id',
	`fan_id` BIGINT NOT NULL COMMENT '粉丝id',
	PRIMARY KEY (`id`),
	INDEX idx_user_id(`user_id`),
	INDEX idx_fan_id(`fan_id`),
	UNIQUE INDEX idx_user_fan(`user_id`, `fan_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='关注表';


-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` BIGINT NOT NULL,
	`father_comment_id` BIGINT NOT NULL COMMENT '如果是回复留言，则本条为子留言',
  `video_id` BIGINT NOT NULL COMMENT '回复的那个视频id',
	`author_id` BIGINT NOT NULL COMMENT '回复的那个视频作者id',
  `user_id` BIGINT NOT NULL COMMENT '发布留言的用户id',
  `content` VARCHAR(128) NOT NULL COMMENT '留言内容',
  `create_time` DATETIME NOT NULL COMMENT '留言时间',
  PRIMARY KEY (`id`),
	INDEX idx_video_id(`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论表';


-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` BIGINT NOT NULL,
  `sender_id` BIGINT NOT NULL COMMENT '发送者id',
  `receiver_id` BIGINT NOT NULL COMMENT '接收者id',
  `content` VARCHAR(255) NOT NULL COMMENT '消息内容',
  `create_time` DATETIME NOT NULL COMMENT '留言时间',
  PRIMARY KEY (`id`),
	INDEX idx_sender_id(`sender_id`),
	INDEX idx_receiver_id(`receiver_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

SET FOREIGN_KEY_CHECKS = 1;