CREATE TABLE `oauth_code` (
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `code` varchar (255)  DEFAULT NULL,
  `authentication` blob NULL,
  INDEX `code_index` (`code`) USING BTREE
) COMMENT='授权码存储表' ;

