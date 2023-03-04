/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 04/03/2023 09:57:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_customer
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer`;
CREATE TABLE `bus_customer`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `customername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `connectionpersion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus_customer
-- ----------------------------
INSERT INTO `bus_customer` VALUES (1, '小张超市', '111', '江西南昌1', '027-9123131', '张大明', '15279230588', '中国银行', '654431331343413', '213123@sina.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (2, '小明超市', '222', '青海省澳门半岛其它区', '0755-9123131', '张小明', '13064154936', '中国银行', '654431331343413', '213123@sina.com', '430000', 1);
INSERT INTO `bus_customer` VALUES (3, '快七超市', '430000', '青海省澳门半岛其它区', '027-11011011', '雷生', '13617020687', '招商银行', '6543123341334133', '6666@66.com', '545341', 1);
INSERT INTO `bus_customer` VALUES (4, '丽云超市', '332005', '青海省澳门半岛其它区', '0792-13658745', '射可可', '13648524759', '建设银行', '36245684125509', '13648524759@163.com', '152632', 1);
INSERT INTO `bus_customer` VALUES (8, '寻鲁超市', '234234', '北京', '234', '地方', '1234', '是否', '234234', '123123@23', '213', 1);
INSERT INTO `bus_customer` VALUES (11, 'asdf', 'afs', '青海省澳门半岛其它区', '123', 'dfs', '123', 'd sfd', '35234', 'q21341', '123', 1);

-- ----------------------------
-- Table structure for bus_goods
-- ----------------------------
DROP TABLE IF EXISTS `bus_goods`;
CREATE TABLE `bus_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `providerid` bigint NULL DEFAULT NULL,
  `produceplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodspackage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `productcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `promitcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sq0btr2v2lq8gt8b4gb8tlk0i`(`providerid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus_goods
-- ----------------------------
INSERT INTO `bus_goods` VALUES (1, '娃哈哈[120ML]', 3, '武汉', '120ML', '瓶', 'PH12345', 'PZ1234', '小孩子都爱的', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/b33ea626-e007-4ce2-866c-25c8ae5cb02djpeg', 1);
INSERT INTO `bus_goods` VALUES (2, '旺旺雪饼[小包]', 1, '仙桃', '包', '袋', 'PH12312312', 'PZ12312', '好吃不上火', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/cd4b599c-895e-4991-8dcb-e9c0e21e557bjpeg', 1);
INSERT INTO `bus_goods` VALUES (3, '旺旺大礼包', 1, '仙桃', '盒', '盒', '11', '11', '111', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/5d802e3e-d65d-4b8f-8e87-d6e6cf0d9bf4jpeg', 1);
INSERT INTO `bus_goods` VALUES (4, '娃哈哈[200ML]', 3, '武汉', '200ML', '瓶', '11', '111', '12321', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/b33ea626-e007-4ce2-866c-25c8ae5cb02djpeg', 1);
INSERT INTO `bus_goods` VALUES (5, '娃哈哈[200ML]', 3, '武汉', '300ML', '瓶', '1234', '12321', '22221111', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/b33ea626-e007-4ce2-866c-25c8ae5cb02djpeg', 1);
INSERT INTO `bus_goods` VALUES (6, '纯牛奶', 4, '内蒙古', '24瓶一箱', '瓶', 'SD13156146', '321651613', '牛奶', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/02add7a5-32f4-4ca6-8d79-0fb6240b847ejpeg', 1);
INSERT INTO `bus_goods` VALUES (18, '八宝粥', 2, '广州', '箱', '15', 'DFS234341231', 'DF123124324', '粥', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/42cab2cc-9469-4045-8f68-e66f5aa0db01jpeg', 1);
INSERT INTO `bus_goods` VALUES (22, '大面筋', 16, '河南', '包', '120g', NULL, NULL, '辣条', 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/20221115/460fc526-35d5-4728-864e-5329c86635d0jpeg', 1);

-- ----------------------------
-- Table structure for bus_inport
-- ----------------------------
DROP TABLE IF EXISTS `bus_inport`;
CREATE TABLE `bus_inport`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inporttime` datetime NULL DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `number` int NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inportprice` double NULL DEFAULT NULL,
  `providerid` bigint NULL DEFAULT NULL,
  `goodsid` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `bus_inport_ibfk_1`(`providerid`) USING BTREE,
  INDEX `bus_inport_ibfk_2`(`goodsid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus_inport
-- ----------------------------
INSERT INTO `bus_inport` VALUES (1, '微信', '2018-05-07 00:00:00', '张三', 100, '备注', 3.5, 1, 1);
INSERT INTO `bus_inport` VALUES (2, '支付宝', '2018-05-07 00:00:00', '张三', 1000, '无', 2.5, 3, 3);
INSERT INTO `bus_inport` VALUES (3, '银联', '2018-05-07 00:00:00', '张三', 100, '1231', 111, 3, 3);
INSERT INTO `bus_inport` VALUES (4, '银联', '2018-05-07 00:00:00', '张三', 1000, '无', 2, 3, 1);
INSERT INTO `bus_inport` VALUES (5, '银联', '2018-05-07 00:00:00', '张三', 100, '无', 1, 3, 1);
INSERT INTO `bus_inport` VALUES (6, '银联', '2018-05-07 00:00:00', '张三', 100, '1231', 2.5, 1, 2);
INSERT INTO `bus_inport` VALUES (8, '支付宝', '2018-05-07 00:00:00', '张三', 100, '', 1, 3, 1);
INSERT INTO `bus_inport` VALUES (10, '支付宝', '2018-08-07 17:17:57', '超级管理员', 100, 'sadfasfdsa', 1.5, 3, 1);
INSERT INTO `bus_inport` VALUES (11, '支付宝', '2018-09-17 16:12:25', '超级管理员', 21, '21', 21, 1, 3);
INSERT INTO `bus_inport` VALUES (12, '微信', '2018-12-25 16:48:24', '超级管理员', 100, '123213213', 12321321, 3, 1);

-- ----------------------------
-- Table structure for bus_outport
-- ----------------------------
DROP TABLE IF EXISTS `bus_outport`;
CREATE TABLE `bus_outport`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `providerid` bigint NULL DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outputtime` datetime NULL DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `outportprice` double(10, 2) NULL DEFAULT NULL,
  `number` int NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsid` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus_outport
-- ----------------------------
INSERT INTO `bus_outport` VALUES (1, 3, '微信', '2019-08-16 08:19:50', '超级管理员', 12321321.00, 1, '', 1);
INSERT INTO `bus_outport` VALUES (2, 3, '微信', '2019-08-16 08:26:54', '超级管理员', 12321321.00, 11, '11', 1);

-- ----------------------------
-- Table structure for bus_provider
-- ----------------------------
DROP TABLE IF EXISTS `bus_provider`;
CREATE TABLE `bus_provider`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `providername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `connectionpersion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bus_provider
-- ----------------------------
INSERT INTO `bus_provider` VALUES (1, '旺旺食品', '434000', '仙桃', '0728-4124312', '小明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 1);
INSERT INTO `bus_provider` VALUES (2, '达利园食品', '430000', '汉川', '0728-4124312', '大明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 1);
INSERT INTO `bus_provider` VALUES (3, '娃哈哈集团', '513131', '杭州', '21312', '小晨', '12312', '建设银行', '512314141541', '123131', '312312', 1);
INSERT INTO `bus_provider` VALUES (4, '蒙牛集团', '332005', '内蒙古', '0790-362514856', '姿态', '13617252689', '中国银行', '36214587962509', '13617252689@163.com', '364145', 1);
INSERT INTO `bus_provider` VALUES (5, '伊利集团', '562115', '内蒙古', '0792-36548569', 'Rita', '13698560566', '建设银行', '3621458963596509', '13698560566@163.com', '362514', 1);
INSERT INTO `bus_provider` VALUES (12, 'asdf', '123', 'sdf', '123', 'sda', '123', '123', '123', '132', '213', 1);
INSERT INTO `bus_provider` VALUES (16, '卫龙', NULL, '河南', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for bus_purchase
-- ----------------------------
DROP TABLE IF EXISTS `bus_purchase`;
CREATE TABLE `bus_purchase`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '采购单id',
  `assignee_id` bigint NULL DEFAULT NULL COMMENT '采购人id',
  `assignee_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '采购人名',
  `phone` char(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `amount` decimal(18, 2) NULL DEFAULT NULL COMMENT '总金额',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_purchase
-- ----------------------------
INSERT INTO `bus_purchase` VALUES (13, 3, '李月素', '13900007813', 3, 40.00, '2022-11-20 20:28:16', '2022-11-20 20:40:53');
INSERT INTO `bus_purchase` VALUES (17, NULL, NULL, NULL, 2, 2.00, '2023-02-07 17:59:10', '2023-02-07 18:16:24');

-- ----------------------------
-- Table structure for bus_repository
-- ----------------------------
DROP TABLE IF EXISTS `bus_repository`;
CREATE TABLE `bus_repository`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '库存id',
  `goods_id` bigint NULL DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名（冗余字段）',
  `ware_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `ware_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库名（冗余字段）',
  `stock` int NULL DEFAULT NULL COMMENT '库存',
  `threshold` int NULL DEFAULT NULL COMMENT '阈值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品库存' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_repository
-- ----------------------------
INSERT INTO `bus_repository` VALUES (5, 18, '八宝粥', 1, '1号仓库', 10, 0);
INSERT INTO `bus_repository` VALUES (6, 22, '大面筋', 1, '1号仓库', 10, 0);
INSERT INTO `bus_repository` VALUES (7, 6, '纯牛奶', 2, '2号仓库', 10, 10);

-- ----------------------------
-- Table structure for bus_requirement
-- ----------------------------
DROP TABLE IF EXISTS `bus_requirement`;
CREATE TABLE `bus_requirement`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '采购id',
  `purchase_id` bigint NULL DEFAULT NULL COMMENT '采购单id',
  `goods_id` bigint NULL DEFAULT NULL COMMENT '采购商品id',
  `num` int NULL DEFAULT NULL COMMENT '采购数量',
  `price` decimal(18, 2) NULL DEFAULT NULL COMMENT '采购金额',
  `ware_id` bigint NULL DEFAULT NULL COMMENT '仓库id',
  `status` int NULL DEFAULT NULL COMMENT '状态[0新建，1已分配，2正在采购，3已完成，4采购失败]',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '采购需求' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_requirement
-- ----------------------------
INSERT INTO `bus_requirement` VALUES (16, 13, 6, 10, 40.00, 2, 3, '2022-11-20 20:28:03', '2022-11-20 20:40:53');
INSERT INTO `bus_requirement` VALUES (19, 17, 1, 1, 1.00, 1, 2, '2023-02-07 17:58:40', '2023-02-07 18:16:24');
INSERT INTO `bus_requirement` VALUES (20, 17, 2, 1, 1.00, 2, 2, '2023-02-07 17:58:46', '2023-02-07 18:16:24');

-- ----------------------------
-- Table structure for bus_stockin
-- ----------------------------
DROP TABLE IF EXISTS `bus_stockin`;
CREATE TABLE `bus_stockin`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gid` bigint NULL DEFAULT NULL COMMENT '商品id',
  `pid` bigint NULL DEFAULT NULL COMMENT '单号',
  `uid` bigint NULL DEFAULT NULL COMMENT '操作人',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `wid` bigint NULL DEFAULT NULL COMMENT '仓库id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_stockin
-- ----------------------------

-- ----------------------------
-- Table structure for bus_stockout
-- ----------------------------
DROP TABLE IF EXISTS `bus_stockout`;
CREATE TABLE `bus_stockout`  (
  `id` bigint NOT NULL,
  `gid` bigint NULL DEFAULT NULL COMMENT '商品',
  `cid` bigint NULL DEFAULT NULL COMMENT '客户',
  `uid` bigint NULL DEFAULT NULL COMMENT '用户',
  `status` int NULL DEFAULT NULL COMMENT '状态',
  `wid` bigint NULL DEFAULT NULL COMMENT '仓库id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_stockout
-- ----------------------------

-- ----------------------------
-- Table structure for bus_ware
-- ----------------------------
DROP TABLE IF EXISTS `bus_ware`;
CREATE TABLE `bus_ware`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '仓库id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库名',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '仓库地址',
  `areacode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '区域编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '仓库' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_ware
-- ----------------------------
INSERT INTO `bus_ware` VALUES (1, '1号仓库', '北京', '101');
INSERT INTO `bus_ware` VALUES (2, '2号仓库', '上海', '201');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `open` int NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int NULL DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int NULL DEFAULT NULL COMMENT '排序码【为了调试显示顺序】',
  `createtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '总经办', 1, '大BOSS', '深圳', 1, 1, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (2, 1, '销售部', 0, '程序员', '武汉', 1, 2, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (3, 1, '运营部', 0, '无', '武汉', 1, 3, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (4, 1, '生产部', 0, '无', '武汉', 1, 4, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (5, 2, '销售一部', 0, '销售一部', '武汉', 1, 5, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (6, 2, '销售二部', 0, '销售二部', '武汉', 1, 6, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (7, 3, '运营一部', 0, '运营一部', '武汉', 1, 7, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (8, 2, '销售三部', 0, '销售三部', '11', 1, 8, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (9, 2, '销售四部', 0, '销售四部', '222', 1, 9, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (10, 2, '销售五部', 0, '销售五部', '33', 1, 10, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (18, 4, '生产一部', 0, '生产食品', '武汉', 1, 11, '2019-04-13 09:49:38');

-- ----------------------------
-- Table structure for sys_loginfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginfo`;
CREATE TABLE `sys_loginfo`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `loginid` bigint NULL DEFAULT NULL,
  `loginip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logintime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 327 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_loginfo
-- ----------------------------
INSERT INTO `sys_loginfo` VALUES (299, 'system', 1, '192.168.100.1', '2022-12-17 19:38:04');
INSERT INTO `sys_loginfo` VALUES (300, 'system', 1, '192.168.100.1', '2022-12-18 10:40:48');
INSERT INTO `sys_loginfo` VALUES (301, 'system', 1, '192.168.100.1', '2022-12-18 18:07:37');
INSERT INTO `sys_loginfo` VALUES (302, 'system', 1, '192.168.100.1', '2022-12-25 13:08:03');
INSERT INTO `sys_loginfo` VALUES (303, 'system', 1, '192.168.100.1', '2022-12-26 12:22:19');
INSERT INTO `sys_loginfo` VALUES (304, 'system', 1, '192.168.100.1', '2022-12-26 16:28:37');
INSERT INTO `sys_loginfo` VALUES (305, 'system', 1, '192.168.100.1', '2022-12-27 18:44:21');
INSERT INTO `sys_loginfo` VALUES (306, 'system', 1, '192.168.100.1', '2022-12-31 14:34:55');
INSERT INTO `sys_loginfo` VALUES (307, 'system', 1, '192.168.100.1', '2023-01-03 15:48:09');
INSERT INTO `sys_loginfo` VALUES (308, 'system', 1, '192.168.100.1', '2023-01-03 21:01:42');
INSERT INTO `sys_loginfo` VALUES (309, 'system', 1, '192.168.100.1', '2023-01-04 14:47:39');
INSERT INTO `sys_loginfo` VALUES (310, 'system', 1, '192.168.100.1', '2023-01-10 13:26:57');
INSERT INTO `sys_loginfo` VALUES (311, 'system', 1, '192.168.100.1', '2023-01-10 20:02:27');
INSERT INTO `sys_loginfo` VALUES (312, 'system', 1, '192.168.100.1', '2023-02-01 20:03:32');
INSERT INTO `sys_loginfo` VALUES (313, 'system', 1, '192.168.100.1', '2023-02-02 17:17:31');
INSERT INTO `sys_loginfo` VALUES (314, 'system', 1, '192.168.100.1', '2023-02-04 18:16:15');
INSERT INTO `sys_loginfo` VALUES (315, 'system', 1, '192.168.100.1', '2023-02-05 13:00:39');
INSERT INTO `sys_loginfo` VALUES (316, 'system', 1, '192.168.100.1', '2023-02-06 12:40:51');
INSERT INTO `sys_loginfo` VALUES (317, 'system', 1, '192.168.100.1', '2023-02-06 18:42:03');
INSERT INTO `sys_loginfo` VALUES (318, 'system', 1, '192.168.100.1', '2023-02-06 18:57:08');
INSERT INTO `sys_loginfo` VALUES (319, 'system', 1, '192.168.100.1', '2023-02-07 13:38:44');
INSERT INTO `sys_loginfo` VALUES (320, 'system', 1, '192.168.100.1', '2023-02-07 15:40:15');
INSERT INTO `sys_loginfo` VALUES (321, 'system', 1, '192.168.100.1', '2023-02-07 18:13:51');
INSERT INTO `sys_loginfo` VALUES (322, 'system', 1, '127.0.0.1', '2023-02-07 18:14:16');
INSERT INTO `sys_loginfo` VALUES (323, 'system', 1, '127.0.0.1', '2023-02-07 18:16:06');
INSERT INTO `sys_loginfo` VALUES (324, 'system', 1, '192.168.100.1', '2023-02-08 12:38:22');
INSERT INTO `sys_loginfo` VALUES (325, 'system', 1, '192.168.100.1', '2023-02-27 14:00:38');
INSERT INTO `sys_loginfo` VALUES (326, 'system', 1, '192.168.100.1', '2023-03-03 15:55:18');
INSERT INTO `sys_loginfo` VALUES (327, 'system', 1, '192.168.100.1', '2023-03-03 20:19:38');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operid` bigint NULL DEFAULT NULL,
  `opername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `createtime` datetime NULL DEFAULT NULL,
  `updatetime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (54, 'afds', NULL, '落亦-', 'dasf', '2020-03-08 03:48:47', NULL);
INSERT INTO `sys_notice` VALUES (55, '测试', NULL, '落亦-', '测试', '2020-03-08 03:53:03', NULL);
INSERT INTO `sys_notice` VALUES (56, 'sadf', NULL, '落亦-', 'asdf', '2020-03-08 04:17:44', NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pid` bigint NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `percode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ordernum` int NULL DEFAULT NULL,
  `available` int NULL DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, 'menu', '仓库管理系统', '', NULL, 'home', 1, 0);
INSERT INTO `sys_permission` VALUES (2, 1, 'menu', '基础管理', '', 'base', 'base', 2, 0);
INSERT INTO `sys_permission` VALUES (3, 1, 'menu', '仓库管理', '', 'ware', 'ware', 3, 0);
INSERT INTO `sys_permission` VALUES (5, 1, 'menu', '系统管理', '', 'system', 'system', 5, 0);
INSERT INTO `sys_permission` VALUES (6, 1, 'menu', '其它管理', '', 'other', 'other', 6, 0);
INSERT INTO `sys_permission` VALUES (7, 2, 'menu', '客户管理', '', 'base_customer', 'customer', 7, 0);
INSERT INTO `sys_permission` VALUES (8, 2, 'menu', '供应商管理', '', 'base_supply', 'supply', 8, 0);
INSERT INTO `sys_permission` VALUES (9, 2, 'menu', '商品管理', '', 'base_goods', 'product', 9, 0);
INSERT INTO `sys_permission` VALUES (10, 3, 'menu', '仓库站点', '', 'ware_site', 'ware_site', 10, 0);
INSERT INTO `sys_permission` VALUES (11, 3, 'menu', '商品库存', '', 'ware_repository', 'ware_repository', 11, 0);
INSERT INTO `sys_permission` VALUES (12, 3, 'menu', '入库', '', 'ware_stockin', 'ware_stockin', 12, 0);
INSERT INTO `sys_permission` VALUES (13, 3, 'menu', '出库', '', 'ware_stockout', 'ware_stockout', 13, 0);
INSERT INTO `sys_permission` VALUES (14, 5, 'menu', '部门管理', '', 'system_dept', 'dept', 14, 0);
INSERT INTO `sys_permission` VALUES (16, 5, 'menu', '权限管理', '', 'system_permission', 'permission', 16, 0);
INSERT INTO `sys_permission` VALUES (17, 5, 'menu', '角色管理', '', 'system_role', 'role', 17, 0);
INSERT INTO `sys_permission` VALUES (18, 5, 'menu', '用户管理', '', 'system_user', 'user', 18, 0);
INSERT INTO `sys_permission` VALUES (19, 3, 'menu', '采购需求', '', 'ware_requirement', 'ware_requirement', 19, 0);
INSERT INTO `sys_permission` VALUES (20, 3, 'menu', '采购单', '', 'ware_purchase', 'ware_purchase', 20, 0);
INSERT INTO `sys_permission` VALUES (21, 6, 'menu', '登陆日志', '', 'other_logged', 'log', 21, 0);
INSERT INTO `sys_permission` VALUES (22, 6, 'menu', '系统公告', '', 'other_board', 'board', 22, 0);
INSERT INTO `sys_permission` VALUES (30, 14, 'permission', '添加部门', 'dept:create', NULL, '', 24, 0);
INSERT INTO `sys_permission` VALUES (31, 14, 'permission', '修改部门', 'dept:update', NULL, '', 26, 0);
INSERT INTO `sys_permission` VALUES (32, 14, 'permission', '删除部门', 'dept:delete', NULL, '', 27, 0);
INSERT INTO `sys_permission` VALUES (38, 16, 'permission', '添加权限', 'permission:create', NULL, '', 33, 0);
INSERT INTO `sys_permission` VALUES (39, 16, 'permission', '修改权限', 'permission:update', NULL, '', 34, 0);
INSERT INTO `sys_permission` VALUES (40, 16, 'permission', '删除权限', 'permission:delete', NULL, '', 35, 0);
INSERT INTO `sys_permission` VALUES (42, 17, 'permission', '添加角色', 'role:create', NULL, '', 37, 0);
INSERT INTO `sys_permission` VALUES (43, 17, 'permission', '修改角色', 'role:update', NULL, '', 38, 0);
INSERT INTO `sys_permission` VALUES (44, 17, 'permission', '删除角色', 'role:delete', NULL, '', 39, 0);
INSERT INTO `sys_permission` VALUES (46, 17, 'permission', '分配权限', 'role:selectPermission', NULL, '', 41, 0);
INSERT INTO `sys_permission` VALUES (47, 18, 'permission', '添加用户', 'user:create', NULL, '', 42, 0);
INSERT INTO `sys_permission` VALUES (48, 18, 'permission', '修改用户', 'user:update', NULL, '', 43, 0);
INSERT INTO `sys_permission` VALUES (49, 18, 'permission', '删除用户', 'user:delete', NULL, '', 44, 0);
INSERT INTO `sys_permission` VALUES (51, 18, 'permission', '用户分配角色', 'user:selectRole', NULL, '', 46, 0);
INSERT INTO `sys_permission` VALUES (52, 18, 'permission', '重置密码', 'user:resetPwd', NULL, NULL, 47, 0);
INSERT INTO `sys_permission` VALUES (53, 14, 'permission', '部门查询', 'dept:view', NULL, NULL, 48, 0);
INSERT INTO `sys_permission` VALUES (55, 16, 'permission', '权限查询', 'permission:view', NULL, NULL, 50, 0);
INSERT INTO `sys_permission` VALUES (56, 17, 'permission', '角色查询', 'role:view', NULL, NULL, 51, 0);
INSERT INTO `sys_permission` VALUES (57, 18, 'permission', '用户查询', 'user:view', NULL, NULL, 52, 0);
INSERT INTO `sys_permission` VALUES (68, 7, 'permission', '客户查询', 'customer:view', NULL, NULL, 60, 0);
INSERT INTO `sys_permission` VALUES (69, 7, 'permission', '客户添加', 'customer:create', NULL, NULL, 61, 0);
INSERT INTO `sys_permission` VALUES (70, 7, 'permission', '客户修改', 'customer:update', NULL, NULL, 62, 0);
INSERT INTO `sys_permission` VALUES (71, 7, 'permission', '客户删除', 'customer:delete', NULL, NULL, 63, 0);
INSERT INTO `sys_permission` VALUES (73, 21, 'permission', '日志查询', 'info:view', NULL, NULL, 65, 0);
INSERT INTO `sys_permission` VALUES (74, 21, 'permission', '日志删除', 'info:delete', NULL, NULL, 66, 0);
INSERT INTO `sys_permission` VALUES (75, 21, 'permission', '日志批量删除', 'info:batchdelete', NULL, NULL, 67, 0);
INSERT INTO `sys_permission` VALUES (76, 22, 'permission', '公告查询', 'notice:view', NULL, NULL, 68, 0);
INSERT INTO `sys_permission` VALUES (77, 22, 'permission', '公告添加', 'notice:create', NULL, NULL, 69, 0);
INSERT INTO `sys_permission` VALUES (78, 22, 'permission', '公告修改', 'notice:update', NULL, NULL, 70, 0);
INSERT INTO `sys_permission` VALUES (79, 22, 'permission', '公告删除', 'notice:delete', NULL, NULL, 71, 0);
INSERT INTO `sys_permission` VALUES (81, 8, 'permission', '供应商查询', 'provider:view', NULL, NULL, 73, 0);
INSERT INTO `sys_permission` VALUES (82, 8, 'permission', '供应商添加', 'provider:create', NULL, NULL, 74, 0);
INSERT INTO `sys_permission` VALUES (83, 8, 'permission', '供应商修改', 'provider:update', NULL, NULL, 75, 0);
INSERT INTO `sys_permission` VALUES (84, 8, 'permission', '供应商删除', 'provider:delete', NULL, NULL, 76, 0);
INSERT INTO `sys_permission` VALUES (86, 22, 'permission', '公告查看', 'notice:viewnotice', NULL, NULL, 78, 0);
INSERT INTO `sys_permission` VALUES (91, 9, 'permission', '商品查询', 'goods:view', NULL, NULL, 79, 0);
INSERT INTO `sys_permission` VALUES (92, 9, 'permission', '商品添加', 'goods:create', NULL, NULL, 80, 0);
INSERT INTO `sys_permission` VALUES (116, 9, 'permission', '商品删除', 'goods:delete', NULL, NULL, 84, 0);
INSERT INTO `sys_permission` VALUES (117, 9, 'permission', '商品修改', 'goods:update', NULL, NULL, 85, 0);
INSERT INTO `sys_permission` VALUES (118, 9, 'permission', '商品查询', 'goods:view', NULL, NULL, 86, 0);
INSERT INTO `sys_permission` VALUES (119, 22, 'permission', '公告批量删除', 'notice:batchdelete', NULL, NULL, 87, 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `available` int NULL DEFAULT NULL,
  `createtime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '拥有所有菜单权限', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (4, '基础数据管理员', '基础数据管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (6, '销售管理员', '销售管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (8, '系统管理员', '管理所有的系统设置', 1, '2020-02-24 07:46:27');
INSERT INTO `sys_role` VALUES (10, '测试', '测试', 1, '2020-03-06 03:31:36');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `rid` bigint NOT NULL,
  `pid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, '1;2;7;8;9;3;10;11;19;20;4;12;13;5;14;16;17;18;6;21;22;2;3;4;5;6;7;8;9;10;11;12;13;14;16;17;18;19;20;21;22;30;31;32;38;39;40;42;43;44;46;47;48;49;51;52;53;55;56;57;68;69;70;71;73;74;75;76;77;78;79;81;82;83;84;86;91;92;116;117;118;119');
INSERT INTO `sys_role_permission` VALUES (4, NULL);
INSERT INTO `sys_role_permission` VALUES (6, '4;12;13');
INSERT INTO `sys_role_permission` VALUES (8, NULL);
INSERT INTO `sys_role_permission` VALUES (10, NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `sex` int NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `deptid` int NULL DEFAULT NULL,
  `hiredate` datetime NULL DEFAULT NULL,
  `mgr` int NULL DEFAULT NULL COMMENT '上级领导id',
  `available` int NULL DEFAULT 1 COMMENT '是否可用，0不可用，1可用',
  `ordernum` int NULL DEFAULT NULL COMMENT '排序码',
  `type` int NULL DEFAULT NULL COMMENT '用户类型[0超级管理员，1管理员，2普通用户]',
  `imgpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`id`, `loginname`) USING BTREE,
  UNIQUE INDEX `sys_user_loginname`(`loginname`) USING BTREE COMMENT '登陆名称唯一',
  INDEX `FK_sys_dept_sys_user`(`deptid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'system', '13900007811', '$2a$10$y2W2cpOZsUWbZ5rGNmAnHOgOcnefnVpA3aPXK6KXw/QOxStKjc1lW', '系统深处的男人', 1, '超级管理员', 1, '2018-06-25 00:00:00', NULL, 1, 1, 0, 'https://mall-tz.obs.cn-east-3.myhuaweicloud.com/wms/images/headportrait/1/face.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (2, '落亦-', 'luoyi', '13900007812', '$2a$10$A/iFDmV91JWgwGCiRkZRY.dUH20WipQNBCm2BwmWZ.Y7zX7sCqozy', '九江', 1, '超级管理员', 1, '2019-11-23 00:00:00', NULL, 1, 2, 0, '2020-03-08/0F8C9E01C1DF4A60BB0E2747F67D03BF.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (3, '李月素', 'liyuesu', '13900007813', '$2a$10$o0xcWodGqEARwIKGJo1Pke1Dw6Rlv7C9Bpd1.HvryG8AlOrz.V8rq', '九江', 1, '', 2, '2020-02-12 00:00:00', 5, 1, 3, 1, '2020-02-24/BF25CC186DA14E89BDA0FB061404E527.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (4, '李四', 'lisi', '13900007814', '$2a$10$MAw8NVULWfdUv8U4C3ZXe.OVK/BUtUXX1lslc4r0A4eQh2Dl0IWlK', '九江', 1, '普通用户', 3, '2020-02-09 00:00:00', 3, 1, 4, 1, '2020-02-24/795D8302F654489C8FA3E06F0DA8C141.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (5, '王五', 'wangwu', '13900007815', '$2a$10$BwghciIghMmMxoqndMOEnu2e0TAzBuujQi2JG.glWGBlR51Hv4H8y', '上海', 1, '普通用户', 5, '2019-12-02 00:00:00', 4, 1, 5, 1, '2020-02-24/014938189D454F95BAEB3AC439CD6703.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (6, '赵六', 'zhaoliu', '13900007816', '$2a$10$0tqPnFeZC312WeIvW0I98O8dYz.s.us5S5vpZ4ePjme4e3b2fTq5m', '广州', 1, '普通用户', 5, '2019-12-02 00:00:00', 5, 1, 6, 1, '2020-02-24/25E8E9C743844A5185BCE55D52CF7141.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (7, '陈七', 'chengqi', '13900007817', '$2a$10$7R0CAUhlixE29MFBP6MvG.DJAWxyP15rUjk7bIY2lUrTY7eikhnai', '深圳', 1, '普遍用户', 4, '2019-12-03 00:00:00', 3, 1, 7, 1, '2020-02-24/8258FCECC0D64A1DB3B457E7D51D6AB5.jpg', '04A93C74C8294AA09A8B974FD1F4ECBB');
INSERT INTO `sys_user` VALUES (10, '苏北旦', 'subeidan', '13900007818', '$2a$10$CxkuqnNaW8Q5xr39VftgjeoBsei.L5ShRoilf1rxnSFO.kTrmmsYu', '猎户座旋臂', 0, '将军', 3, '2019-12-03 00:00:00', 3, 1, 9, 1, '2020-02-24/8258FCECC0D64A1DB3B457E7D51D6AB5.jpg', '950289EBDBA24C7789E392E1D0254635');
INSERT INTO `sys_user` VALUES (11, '斯嘉丽约翰逊', 'sijialiyuehanxun', '13900007819', '$2a$10$4xmZmOrbABCLUDhTqIvnJeKIksexr5Gyj4sCRsBeV708IXUyX6dfu', '美国', 0, '演员', 7, '2019-12-03 00:00:00', 10, 1, 10, 1, '2020-02-24/8258FCECC0D64A1DB3B457E7D51D6AB5.jpg', '85DB5F84987146559A75B4B0DCB7DE4F');
INSERT INTO `sys_user` VALUES (12, '托尼', 'tuoni', '13900223245', '$2a$10$lXVBFxMXdg7wDyMVEgxHzeXXbyx.Sf6EuTNoLv0HlU7AoZRhzrP/m', '美国', 1, '钢铁侠', 7, '2019-12-03 00:00:00', 11, 1, 11, 1, '2020-02-24/8258FCECC0D64A1DB3B457E7D51D6AB5.jpg', '571059AF59E64A7D92FECB93FA1B0AEF');
INSERT INTO `sys_user` VALUES (13, '贾维斯', 'jiaweisi', '13900234234', '$2a$10$mAwaVg6zFaLC5McBPzit4.I/meqUrH.iiNYTOqq9TDWpr4knuGrEe', '美国', 1, '人工智能', 3, '2019-12-03 00:00:00', 12, 1, 12, 1, '2020-02-24/8258FCECC0D64A1DB3B457E7D51D6AB5.jpg', '7258E2D93A3F429085B34BBD8E345CE7');
INSERT INTO `sys_user` VALUES (14, '李九', 'lijiu', '13900234234', '$2a$10$j9ZIJ0GGk1xqjm20730o/ustxiIWr6SI8fPRF5AQYquiFDKaPl46K', '九江', 1, '测试', 4, '2020-03-05 00:00:00', 10, 1, 13, 1, '/images/defaultusertitle.jpg', 'D3FBF5E33F4D42FDACE85178FE84E95A');
INSERT INTO `sys_user` VALUES (17, '张十', 'zhangshi', '13900877894', '$2a$10$xvQB5MPtTo1.9k5RQWuj1.1xwkYbamC6H9SNJZwopEToGsC4Rh/oW', '南昌', 1, '测试', 4, '2020-03-06 00:00:00', 11, 1, 14, 1, '/images/defaultUserTitle.jpg', '5C6E7D2E2D8C4A8CB9DD4A9DF64DDB57');
INSERT INTO `sys_user` VALUES (19, 'test', 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `uid` bigint NOT NULL,
  `rid` bigint NOT NULL,
  PRIMARY KEY (`uid`, `rid`) USING BTREE,
  INDEX `FK_sys_user_role_1`(`rid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (6, 6);
INSERT INTO `sys_user_role` VALUES (7, 6);
INSERT INTO `sys_user_role` VALUES (3, 8);
INSERT INTO `sys_user_role` VALUES (17, 10);

SET FOREIGN_KEY_CHECKS = 1;
