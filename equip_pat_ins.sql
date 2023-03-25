/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : equip_pat_ins

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 25/03/2023 19:33:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apartment
-- ----------------------------
DROP TABLE IF EXISTS `apartment`;
CREATE TABLE `apartment`  (
  `aid` int(11) NOT NULL AUTO_INCREMENT COMMENT '教室id',
  `anumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '房间号',
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of apartment
-- ----------------------------
INSERT INTO `apartment` VALUES (1, '图书馆A101');
INSERT INTO `apartment` VALUES (2, '图书馆A102');
INSERT INTO `apartment` VALUES (3, '图书馆A103');
INSERT INTO `apartment` VALUES (4, '图书馆A104');
INSERT INTO `apartment` VALUES (5, '图书馆A201');
INSERT INTO `apartment` VALUES (6, '图书馆A202');
INSERT INTO `apartment` VALUES (7, '图书馆A203');
INSERT INTO `apartment` VALUES (8, '图书馆A204');
INSERT INTO `apartment` VALUES (9, '图书馆一楼大厅');

-- ----------------------------
-- Table structure for apartment_equip
-- ----------------------------
DROP TABLE IF EXISTS `apartment_equip`;
CREATE TABLE `apartment_equip`  (
  `aid` int(11) NOT NULL COMMENT '教室id',
  `eid` int(11) NOT NULL COMMENT '设备id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of apartment_equip
-- ----------------------------
INSERT INTO `apartment_equip` VALUES (1, 1);
INSERT INTO `apartment_equip` VALUES (2, 2);
INSERT INTO `apartment_equip` VALUES (2, 3);
INSERT INTO `apartment_equip` VALUES (6, 4);
INSERT INTO `apartment_equip` VALUES (1, 5);
INSERT INTO `apartment_equip` VALUES (2, 6);
INSERT INTO `apartment_equip` VALUES (3, 7);
INSERT INTO `apartment_equip` VALUES (3, 8);
INSERT INTO `apartment_equip` VALUES (4, 9);

-- ----------------------------
-- Table structure for equip
-- ----------------------------
DROP TABLE IF EXISTS `equip`;
CREATE TABLE `equip`  (
  `eid` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备id',
  `ename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备类型（比如电脑、空调）',
  `assetCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备资产编号',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '设备状态，0表示设备未使用，1表示设备使用正常，2表示设备故障，3表示设备维修中',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '设备录入时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '设备更新时间',
  `commit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`eid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of equip
-- ----------------------------
INSERT INTO `equip` VALUES (1, '华硕i7-8081', '计算机', 'CA-610500-SWPU-0001', '1', '2023-01-30 21:09:57', '2023-03-16 05:47:42', '厂家联系电话-199890793');
INSERT INTO `equip` VALUES (2, '华硕i7-8080', '计算机', 'CA-610500-SWPU-0002', '1', '2023-01-30 22:04:43', '2023-01-30 22:04:45', '厂家联系电话-199890793');
INSERT INTO `equip` VALUES (3, '美的635', '空调', 'CA-610500-SWPU-0003', '1', '2023-02-01 09:11:16', '2023-03-16 05:54:24', '厂家联系电话-18794667879');
INSERT INTO `equip` VALUES (4, '惠佰数科 HB-B611', '打印机', 'CA-610500-SWPU-0004', '1', '2023-02-01 13:32:10', '2023-02-03 21:35:12', '厂家联系电话-17368979675');
INSERT INTO `equip` VALUES (5, '恒书借阅BH907', '公共查询计算机', 'CA-610500-SWPU-0005', '1', '2023-02-01 13:35:48', '2023-02-01 13:35:48', '厂家联系电话-13366457645');
INSERT INTO `equip` VALUES (6, '天机98O-046', '公共查询计算机', 'CA-610500-SWPU-0006', '1', '2023-02-01 13:38:37', '2023-02-01 13:38:37', '厂家联系电话-13366457645');
INSERT INTO `equip` VALUES (7, '美的103', '空调', 'CA-610500-SWPU-0007', '3', '2023-02-19 03:44:52', '2023-02-19 03:44:52', '厂家联系电话-18794667879');
INSERT INTO `equip` VALUES (8, '华硕TI-9087', '计算机', 'CA-610500-SWPU-0008', '1', '2023-02-25 10:27:02', '2023-02-25 10:27:02', '厂家联系电话-199890793');
INSERT INTO `equip` VALUES (9, '华硕TI-9088', '计算机', 'CA-610500-SWPU-0009', '1', '2023-02-25 18:31:00', '2023-02-25 18:31:00', '厂家联系电话-199890793');

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT COMMENT '巡检计划id',
  `pname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '巡检计划名称（比如第一次巡检）',
  `state` int(11) NULL DEFAULT NULL COMMENT '巡检计划状态（0表示已过期、1表示未进行，2表示进行中）',
  `isdelete` int(11) NULL DEFAULT NULL COMMENT '巡检计划删除状态（1表示已删除，0表示正常）',
  `startTime` datetime(0) NULL DEFAULT NULL COMMENT '巡检计划开始时间',
  `endTime` datetime(0) NULL DEFAULT NULL COMMENT '巡检计划结束时间',
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES (1, '重置第一次巡检', 0, 0, '2023-02-06 14:54:27', '2023-02-06 14:04:27');
INSERT INTO `plan` VALUES (2, '二月第二次巡检', 0, 0, '2023-02-06 16:46:46', '2023-02-06 16:16:46');
INSERT INTO `plan` VALUES (3, '二月第三次巡检', 0, 1, '2023-02-10 04:00:00', '2023-02-10 05:00:00');
INSERT INTO `plan` VALUES (4, '二月第四次巡检', 0, 0, '2023-02-15 16:00:00', '2023-02-15 17:00:00');
INSERT INTO `plan` VALUES (8, '二月第三次巡检（大）', 0, 0, '2023-02-10 16:00:00', '2023-02-10 17:00:00');
INSERT INTO `plan` VALUES (9, '巡检测试', 2, 0, '2023-02-14 20:00:00', '2023-04-30 00:00:00');
INSERT INTO `plan` VALUES (10, '二月第三次巡检', 0, 0, '2023-02-25 00:00:00', '2023-02-27 00:00:00');
INSERT INTO `plan` VALUES (11, '设备维修', 0, 0, '2023-03-06 16:57:48', '2023-03-05 16:57:52');
INSERT INTO `plan` VALUES (12, 'test', 2, 0, '2023-03-15 00:00:00', '2023-03-31 00:00:00');
INSERT INTO `plan` VALUES (13, '大巡检', 2, 0, '2023-03-20 17:02:39', '2023-03-31 00:00:00');

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `eid` int(11) NOT NULL COMMENT '设备id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `state` int(2) NOT NULL COMMENT '维修状态，1表示需要维修，0表示已维修',
  `createTime` datetime(0) NOT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NOT NULL COMMENT '更新时间',
  `commit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1, 10, 0, '2023-03-05 10:11:02', '2023-03-05 10:11:02', '电脑不能正常开机');
INSERT INTO `repair` VALUES (7, 10, 0, '2023-03-06 09:25:26', '2023-03-06 09:26:18', '空调不能制冷');
INSERT INTO `repair` VALUES (7, 10, 1, '2023-03-08 18:32:55', '2023-03-08 18:32:55', '空调风扇不能转向');

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`  (
  `pid` int(11) NULL DEFAULT NULL COMMENT '巡检计划id',
  `uid` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `eid` int(11) NULL DEFAULT NULL COMMENT '设备id',
  `state` int(2) NULL DEFAULT NULL COMMENT '上传设备状态，1表示设备正常，2表示设备异常',
  `uploadTime` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `commit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '提交备注'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result
-- ----------------------------
INSERT INTO `result` VALUES (9, 10, 7, 1, '2023-02-19 12:57:26', '正常运行');
INSERT INTO `result` VALUES (9, 2, 1, 2, '2023-02-20 12:42:21', '故障');
INSERT INTO `result` VALUES (9, 2, 5, 1, '2023-02-28 09:53:02', '计算机正常运行');
INSERT INTO `result` VALUES (9, 2, 1, 1, '2023-03-01 21:23:28', '已经维修完成');
INSERT INTO `result` VALUES (9, 10, 7, 2, '2023-03-01 21:44:08', '设备故障');
INSERT INTO `result` VALUES (9, 2, 1, 2, '2023-03-03 13:22:23', '设备不能开机');
INSERT INTO `result` VALUES (11, 10, 1, 1, '2023-03-06 09:15:05', '维修完成设备正常');
INSERT INTO `result` VALUES (11, 10, 7, 1, '2023-03-06 09:26:18', '维修完成，能够正常制冷');
INSERT INTO `result` VALUES (9, 10, 7, 2, '2023-03-08 09:43:20', '风扇不能够转向');

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule`  (
  `pid` int(11) NOT NULL COMMENT '巡检计划id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `aid` int(11) NOT NULL COMMENT '房间号id',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '检查状态（0未开始检查、1已全部检查、2部分检查、3未检查已过期）',
  `commit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of schedule
-- ----------------------------
INSERT INTO `schedule` VALUES (4, 2, 1, '0', '');
INSERT INTO `schedule` VALUES (8, 3, 2, '0', NULL);
INSERT INTO `schedule` VALUES (4, 3, 4, '0', NULL);
INSERT INTO `schedule` VALUES (4, 4, 2, '0', NULL);
INSERT INTO `schedule` VALUES (4, 5, 7, '0', NULL);
INSERT INTO `schedule` VALUES (4, 6, 8, '0', NULL);
INSERT INTO `schedule` VALUES (4, 2, 5, '0', NULL);
INSERT INTO `schedule` VALUES (4, 3, 6, '0', NULL);
INSERT INTO `schedule` VALUES (4, 5, 3, '0', NULL);
INSERT INTO `schedule` VALUES (9, 2, 1, '0', '^_^');
INSERT INTO `schedule` VALUES (9, 2, 2, '0', NULL);
INSERT INTO `schedule` VALUES (9, 10, 3, '1', NULL);
INSERT INTO `schedule` VALUES (10, 2, 1, '0', NULL);
INSERT INTO `schedule` VALUES (9, 10, 9, '0', '温度测试：不高于40度\n性能测试：无明显卡段\n功能测试：功能无障碍');
INSERT INTO `schedule` VALUES (9, 10, 4, '0', '温度检测：40度 \n性能检测：流畅 \n功能检测：功能正常');
INSERT INTO `schedule` VALUES (12, 2, 1, '0', '仔细检查电子设备');
INSERT INTO `schedule` VALUES (13, 2, 3, '0', '');
INSERT INTO `schedule` VALUES (12, 2, 2, '0', '');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `uemail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `upassword` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户密码',
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `permission` int(1) NULL DEFAULT NULL COMMENT '用户权限：1表示管理员，0表示巡检人员',
  `ustate` int(1) NULL DEFAULT NULL COMMENT '用户状态：1表示用户正常，0表示用户注销，2表示用户正在审核，3表示用户申请未通过',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2630246701@qq.com', '123456', '刘英杰', 1, 1, '2023-01-16 10:05:15', '2023-03-14 10:20:20');
INSERT INTO `user` VALUES (2, '2345675@qq.com', 'admin', '詹姆士', 0, 1, '2023-01-17 13:37:39', '2023-01-30 17:49:26');
INSERT INTO `user` VALUES (3, '37482364@163.com', '123456', '刘诗诗', 0, 1, '2023-01-17 17:58:14', '2023-03-14 10:23:50');
INSERT INTO `user` VALUES (4, '325435345@163.com', '465365', '刘备', 0, 1, '2023-01-29 22:08:09', '2023-01-30 18:05:01');
INSERT INTO `user` VALUES (5, 'yaohuashou@199.com', '123456', '王五', 0, 1, '2023-01-29 22:11:09', '2023-03-14 10:21:07');
INSERT INTO `user` VALUES (6, 'isfd322@qq.com', '2143432', '李佳佳', 0, 2, '2023-01-29 22:11:42', '2023-01-29 22:11:46');
INSERT INTO `user` VALUES (7, '2778482011@qq.com', '123456', '王欣', 0, 1, '2023-02-12 16:51:56', NULL);
INSERT INTO `user` VALUES (9, '574977921@qq.com', '123456', '李紫桓', 0, 1, '2023-02-12 17:18:35', NULL);
INSERT INTO `user` VALUES (10, 'zou893@qq.com', '123456', '邹文天', 0, 1, '2023-02-19 03:38:33', NULL);
INSERT INTO `user` VALUES (11, '893901820@qq.com', '123456', '翟宇江', 0, 2, '2023-03-19 13:17:28', NULL);

SET FOREIGN_KEY_CHECKS = 1;
