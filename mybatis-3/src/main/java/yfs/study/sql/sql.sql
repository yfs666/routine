CREATE TABLE `graze_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birth_day` date DEFAULT NULL COMMENT '生日',
  `gmt_create` datetime DEFAULT NULL COMMENT '数据创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;