

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modify` datetime NULL DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1, '关于计算机大赛讨论', '计算机大赛是一年一度的计算机竞赛机会，大家可以根计算机机会来加入Java开发部门的痛惜', 'http://cdn.zhaoguoshun.cn/markus-spiske-cvBBO4PzWPg-unsplash.jpg', '', '2021-10-31 18:26:56', '2021-10-31 18:26:56', 0);
INSERT INTO `article` VALUES (2, '有哪些值得推荐的计算机专业的竞赛？', '无论出于什么原因，你决定走上竞赛这一条道路，那么说明你在相关的领域，至少在竞赛的方向上已经有了超过普通人的资质/能力，并决定和与你一样优秀的人进行一些较量，在证明自我的情况下还能结实一些志同道合的朋友，在咕果搜寻「学科竞赛」后发现有非常多的结果', 'http://cdn.zhaoguoshun.cn/yancy-min-842ofHC6MaI-unsplash.jpg\n', '', '2021-10-31 18:28:13', '2021-10-31 18:28:13', 0);
INSERT INTO `article` VALUES (27, '国庆节去哪玩，在家好好学习！', '听音乐，怕呼呼 和大家撒谎打赏抵达首都华沙和和大家说的话啊和大家说的还多久啊是大', 'http://127.0.0.1:8081/upload/36367adc-468b-4928-ae59-6dec319c160e.jpg', 'o6G-35Gp76Pj55S9EyeIXe4TJuD4', NULL, NULL, 0);
INSERT INTO `article` VALUES (28, '123321', '12311231', 'http://127.0.0.1:8081/upload/c2a39dae-e8a6-4104-80e2-f0de42cf940f.jpg', 'o6G-35IO21GEas8-tYha4Ddu_q_k', '2021-11-11 09:33:13', '2021-11-11 09:33:13', 0);
INSERT INTO `article` VALUES (29, '好好学习学习浏览解决还会不能版本并行不悖下', '大厦东侧行啊撒少许下选择性Z下\n啊实打实的\n啊实打实的大苏打', 'http://127.0.0.1:8081/upload/2d1c18d4-8f08-403b-8000-70f0f5b043cf.jpg', 'o6G-35IO21GEas8-tYha4Ddu_q_k', '2021-11-11 15:19:26', '2021-11-11 15:19:26', 0);
INSERT INTO `article` VALUES (30, '的曹张新村放你从v不错现场v呈现出', 'vdvvfdfgd  cvcvbcvbcv', 'http://127.0.0.1:8081/upload/0357d7d9-6ecf-455b-aab2-163a2284246b.png', 'o6G-35IO21GEas8-tYha4Ddu_q_k', '2021-11-11 16:55:07', '2021-11-11 16:55:07', 0);

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  `isadmin` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', '许俊发', 2, '13425253055', 'xjf@sisel.com', '/upload/admin_716b2d15-3154-4ae2-bd99-c97ec73f5e19.jpg', 2, 0);
INSERT INTO `tb_admin` VALUES (26, 'superadmin', 'e10adc3949ba59abbe56e057f20f883e', '许俊发', 1, '15207522723', '175@qq.com', '/upload/superadmin_053e6bd8-80f4-4960-9df1-7240f8c57c74.jpg', 1, 1);
INSERT INTO `tb_admin` VALUES (28, 'test123', 'e10adc3949ba59abbe56e057f20f883e', 'sss', 2, '13005186808', '147@qq.com', '/upload/test123_cd801d4d-f59b-43a3-9945-addb7f847579.jpg', 2, 0);
INSERT INTO `tb_admin` VALUES (29, 'admin1', 'e10adc3949ba59abbe56e057f20f883e', '许俊发', 2, '13005186808', '164@qq.com', '/upload/admin1_4dbe088f-d274-4511-8163-e294113be83c.jpg', 2, 0);

-- ----------------------------
-- Table structure for tb_announcement
-- ----------------------------
DROP TABLE IF EXISTS `tb_announcement`;
CREATE TABLE `tb_announcement`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createTime` date NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_announcement
-- ----------------------------
INSERT INTO `tb_announcement` VALUES (11, '1212', '454545', '2021-09-19', 1);
INSERT INTO `tb_announcement` VALUES (18, '中秋快乐', '大苏打', '2021-09-08', 1);
INSERT INTO `tb_announcement` VALUES (19, '中秋快乐中秋快乐', '中秋快乐中秋快乐\n中秋快乐中秋快乐\n中秋快乐中秋快乐\n中秋快乐中秋快乐\n中秋快乐中秋快乐', '2021-09-21', 2);
INSERT INTO `tb_announcement` VALUES (26, '好开心', '大苏打的111', '2021-11-05', 2);
INSERT INTO `tb_announcement` VALUES (27, '好好学习', '大苏打所啊v程序艰苦艰苦立即执行把你们重新编写你怎么变成\n车站下车就这款新车\n锤子科技喜欢吃', '2021-11-06', 1);
INSERT INTO `tb_announcement` VALUES (28, '下次在这三道啊啊是查出看了看好好计划年可能呼唤ihi哈哈卡卡你看uii哈市打打算', '阿达萨达按教程健康证拉手机卡你想啊可实现暗杀看案件大手大脚卡时间看到按时打卡就挨打数据库\n的卡刷点卡阿加大家思考阿松大受打击那刻开始打扫\n那可是空间大暗杀看的哈阿克苏大会上打包', '2021-11-12', 2);
INSERT INTO `tb_announcement` VALUES (29, '打算迟迟不结婚自行车才能进行自己开车在才能自己辛苦你参考自行车', '从这些吃饱喝足你操作进行考察牛仔裤下次你再看下能看出自行车你弄成这些农村中学\n充值卡宣布参考价值牛仔裤新城控制性你仔细查看最新你在城南中学\n那次在看下操作只列出那些自行车这些年除了在\n才能看这些课程在那里新课程只能下次这些年出状况了你下次', '2021-11-12', 2);

-- ----------------------------
-- Table structure for tb_collect
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect`;
CREATE TABLE `tb_collect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `competition_id` int(11) NOT NULL,
  `active` int(1) NOT NULL DEFAULT 0,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modify` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 84 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_collect
-- ----------------------------
INSERT INTO `tb_collect` VALUES (76, 'o6G-35EZTatGrRZUoaMKO1KA_krQ', 78, 0, '2021-10-30 13:47:05', '2021-10-30 13:47:05');
INSERT INTO `tb_collect` VALUES (77, 'o6G-35PgU62A7dCX6G1iz-n4bnVU', 78, 0, '2021-10-31 21:18:32', '2021-10-31 21:18:32');
INSERT INTO `tb_collect` VALUES (78, 'o6G-35IO21GEas8-tYha4Ddu_q_k', 96, 0, '2021-11-10 16:39:21', '2021-11-10 16:39:21');
INSERT INTO `tb_collect` VALUES (79, 'o6G-35IO21GEas8-tYha4Ddu_q_k', 91, 0, '2021-11-16 09:11:54', '2021-11-16 09:11:54');
INSERT INTO `tb_collect` VALUES (80, 'o6G-35PgU62A7dCX6G1iz-n4bnVU', 96, 0, '2021-11-24 10:19:47', '2021-11-24 10:19:47');
INSERT INTO `tb_collect` VALUES (81, '7f406bd5-f9c6-4885-91fe-34053f015b83', 96, 0, '2021-11-24 17:16:59', '2021-11-24 17:16:59');
INSERT INTO `tb_collect` VALUES (82, 'ea870171-393d-4f56-8f34-96461188c0c7', 93, 0, '2021-11-24 17:17:56', '2021-11-24 17:17:56');
INSERT INTO `tb_collect` VALUES (83, '7f406bd5-f9c6-4885-91fe-34053f015b83', 91, 0, '2021-11-24 17:18:17', '2021-11-24 17:18:17');

-- ----------------------------
-- Table structure for tb_competition
-- ----------------------------
DROP TABLE IF EXISTS `tb_competition`;
CREATE TABLE `tb_competition`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deptId` int(11) NULL DEFAULT NULL,
  `publisher` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int(20) NULL DEFAULT NULL,
  `validTime` date NULL DEFAULT NULL,
  `createTime` date NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `activity_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 97 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_competition
-- ----------------------------
INSERT INTO `tb_competition` VALUES (90, 'ssh框架学习', 401, '王吉林', '123@qq.com', 3, '2021-11-06', '2021-11-05', 'dasda', 'dasd', 'd63d4f46-b7cf-4a9a-bba0-9db76906f458_1636096887003.jpg');
INSERT INTO `tb_competition` VALUES (91, '大数据学习', 403, '董少英', '1123@qq.com', 3, '2021-11-06', '2021-11-05', 'dasdasd', 'dasda', '8b9beb39-7f7e-4cac-9ead-af09a0400821_1636097731770.jpg');
INSERT INTO `tb_competition` VALUES (93, '2022新年快乐', 403, '许俊发', '164@qq.com', 44, '2021-11-30', '2021-11-05', 'dasdasd', 'dasdas', '5f0d461e-07ca-4d2c-b08c-d1bfbc21ba4b_1636116862256.jpg');
INSERT INTO `tb_competition` VALUES (95, '基于微信小程序的学科竞赛系统', 405, '许俊发', '165655@qq.com', 5, '2021-11-30', '2021-11-09', '1、犯得上发射点发射点发生发射点发生否收到；\n2、发士大夫士大夫犯得上发射点范德萨否；\n3、偶极矩那就看你能看见那就看你‘；\n4、大苏打打算空间打开撒大苏打啊；', '请在规定的时间内报名，逾期无效。', '5df5b61d-a6d1-4d3c-9b23-f9fa3c3e52ca_1636444287724.jpg');
INSERT INTO `tb_competition` VALUES (96, 'v需现场v下是持续性踩踩踩踩踩踩操作执行出现在曹张新村曹张新村', 401, '我是', 'dasda@qq.com', 3, '2021-11-10', '2021-11-10', 'dsadasd\ndasdasd\ndasd\nadsasd', 'dasdasda', '6b8f78a8-0097-468d-9b8b-a972bab66afd_1636531220066.jpg');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES (1, 'ROLE_superadmin', 1);
INSERT INTO `tb_role` VALUES (2, 'ROLE_admin', 2);

-- ----------------------------
-- Table structure for tb_students
-- ----------------------------
DROP TABLE IF EXISTS `tb_students`;
CREATE TABLE `tb_students`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `studentId` int(20) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `phone` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_students
-- ----------------------------
INSERT INTO `tb_students` VALUES (32, 1840706175, 'e10adc3949ba59abbe56e057f20f883e', '许俊发', 2, '15207522723', '1753160044@qq.com', 'http://127.0.0.1:8081/upload/ab7a83ab-166b-4a0e-b1ac-f3f19c11e375.jpg');
INSERT INTO `tb_students` VALUES (34, 1840706000, 'e10adc3949ba59abbe56e057f20f883e', '许俊发', 1, '13005186808', '1476445687', 'http://127.0.0.1:8081/upload/5ad7bec6-001a-4424-bb9c-2bdcf6af2593.jpg');
INSERT INTO `tb_students` VALUES (38, 1840706111, 'e10adc3949ba59abbe56e057f20f883e', '许俊发', 2, '13005186808', '1645@qq.com', 'http://127.0.0.1:8081/upload/20087ad5-b8b8-4707-9418-497ba345aa52.jpg');
INSERT INTO `tb_students` VALUES (39, 194712110, 'e10adc3949ba59abbe56e057f20f883e', 'ss', 1, NULL, NULL, NULL);
INSERT INTO `tb_students` VALUES (40, 123456, 'e10adc3949ba59abbe56e057f20f883e', 'shunzi', 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for tb_team
-- ----------------------------
DROP TABLE IF EXISTS `tb_team`;
CREATE TABLE `tb_team`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `teamName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `projectName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `members` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adviser` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `competitionTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tb_team
-- ----------------------------
INSERT INTO `tb_team` VALUES (3, '计算机2班', '智能汽车模拟开发', '小明、小红、小张', '闫艺婷', '智能车辆是一个集环境感知、规划决策、多等级辅助驾驶等功能于一体的综合系统，它集中运用了计算机、现代传感、信息融合、通讯、人工智能及自动控制等技术，是典型的高新技术综合体。对智能车辆的研究主要致力于提高汽车的安全性、舒适性，以及提供优良的人车交互界面。近年来，智能车辆己经成为世界车辆工程领域研究的热点和汽车工业增长的新动力，很多发达国家都将其纳入到各自重点发展的智能交通系统当中。', 3, '队长：某某某', '智能汽车', '项目重复', '');
INSERT INTO `tb_team` VALUES (13, '恭喜发财', '红包拿来', '许俊法', '研艺婷', '天天快乐', 2, 'dasdasd', '2022新年快乐', '', 'o6G-35Gp76Pj55S9EyeIXe4TJuD4');
INSERT INTO `tb_team` VALUES (14, 'web2班', '智能快捷汽车', '许俊发2.0', '王吉林', '队长：小明', 2, '', 'ssh框架学习', '', 'o6G-35IO21GEas8-tYha4Ddu_q_k');
INSERT INTO `tb_team` VALUES (15, '团结必胜', '无人船技术', '许俊发3.0', '董少英', '热情热情热情', 3, '', '大数据学习', '书写不规范', 'o6G-35IO21GEas8-tYha4Ddu_q_k');
INSERT INTO `tb_team` VALUES (16, '密码', '滴滴', '打算、大苏打', '实打实的', '大苏打', 1, '大苏打11', '基于微信小程序的学科竞赛系统', NULL, 'o6G-35IO21GEas8-tYha4Ddu_q_k');

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `open_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `active` int(1) NOT NULL DEFAULT 0,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modify` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`open_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES ('7f406bd5-f9c6-4885-91fe-34053f015b83', '', '123456', '', 0, '2021-11-24 17:16:36', '2021-11-24 17:16:36');
INSERT INTO `wx_user` VALUES ('ea870171-393d-4f56-8f34-96461188c0c7', '', '194712110', '', 0, '2021-11-24 17:17:44', '2021-11-24 17:17:44');
INSERT INTO `wx_user` VALUES ('o6G-35IO21GEas8-tYha4Ddu_q_k', '许俊发', NULL, '', 0, '2021-11-15 10:13:01', '2021-11-15 10:13:01');

SET FOREIGN_KEY_CHECKS = 1;
