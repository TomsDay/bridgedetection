/*
Navicat MySQL Data Transfer

Source Server         : highwayold
Source Server Version : 50703
Source Host           : localhost:3306
Source Database       : bpmp_test

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2016-06-03 17:02:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for hw_daily_inspectlog
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_inspectlog`;
CREATE TABLE `hw_daily_inspectlog` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `gydw_id_` bigint(20) DEFAULT NULL COMMENT '管养单位ID',
  `gydw_name_` varchar(256) DEFAULT NULL COMMENT '管养单位',
  `lxid_` bigint(20) NOT NULL COMMENT '路线',
  `lxbh_` varchar(32) DEFAULT NULL COMMENT '路线编号',
  `lxmc_` varchar(20) DEFAULT NULL COMMENT '路线名称',
  `xcld_` varchar(64) DEFAULT NULL COMMENT '巡查路段',
  `jcks_` varchar(20) DEFAULT NULL COMMENT '检查开始',
  `jcjs_` varchar(20) DEFAULT NULL COMMENT '检查结束',
  `jcsj_` varchar(20) NOT NULL COMMENT '检查时间',
  `weather_` varchar(20) DEFAULT NULL COMMENT '天气',
  `xcry_` varchar(2000) DEFAULT NULL COMMENT '巡查人',
  `jcry_` varchar(64) NOT NULL COMMENT '检查人员',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `status_` varchar(1) DEFAULT NULL COMMENT '状态',
  `bno_` varchar(32) NOT NULL COMMENT '表单编号',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  `xclx_` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `AK_uni_daily_inspectlog_bno` (`bno_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='养护巡查日志表';

-- ----------------------------
-- Table structure for hw_daily_inspectlogdetail
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_inspectlogdetail`;
CREATE TABLE `hw_daily_inspectlogdetail` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `yh_insp_id_` bigint(20) DEFAULT NULL COMMENT '养护巡查日志记录ID',
  `yh_insp_no_` varchar(32) DEFAULT NULL COMMENT '巡查日志编号',
  `notice_id_` bigint(20) DEFAULT NULL COMMENT '养护通知单id',
  `notice_no_` varchar(32) DEFAULT NULL COMMENT '养护通知单编号',
  `lxid_` bigint(20) NOT NULL COMMENT '路线',
  `lxbh_` varchar(32) DEFAULT NULL COMMENT '路线编号',
  `lxmc_` varchar(20) DEFAULT NULL COMMENT '路线名称',
  `zhfw_` varchar(32) DEFAULT NULL COMMENT '桩号范围',
  `fx_` varchar(32) DEFAULT NULL COMMENT '方向',
  `yhzh_` decimal(9,3) DEFAULT NULL COMMENT '桩号',
  `bhid_` bigint(20) DEFAULT NULL COMMENT '病害id',
  `bhmc_` varchar(200) DEFAULT NULL COMMENT '病害名称',
  `bhwz_` varchar(300) DEFAULT NULL COMMENT '病害位置',
  `dw_` varchar(20) DEFAULT NULL COMMENT '单位',
  `ygsl_` decimal(9,3) DEFAULT NULL COMMENT '预估数量',
  `jcsj_` varchar(20) DEFAULT NULL COMMENT '检查时间',
  `remark_` varchar(2048) DEFAULT NULL COMMENT '病害描述',
  `picattachment_` varchar(2000) DEFAULT NULL COMMENT '图片附件IDS',
  `vidattachment_` varchar(2000) DEFAULT NULL COMMENT '视频附件IDS',
  `bno_` varchar(32) DEFAULT NULL COMMENT '表单编号',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `tpjd_` varchar(50) DEFAULT NULL COMMENT '图片经度',
  `tpwd_` varchar(50) DEFAULT NULL COMMENT '图片纬度',
  `yhzt_` varchar(1) DEFAULT NULL COMMENT '养护状态(1:发现病害;2:下达养护任务通知单;3:施工单位养护完成;4:巡查验收完成)',
  `yhztmc_` varchar(64) DEFAULT NULL COMMENT '养护状态名称',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='养护巡查日志明细表';

-- ----------------------------
-- Table structure for hw_daily_inspectlogdetails
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_inspectlogdetails`;
CREATE TABLE `hw_daily_inspectlogdetails` (
  `id_` bigint(20) NOT NULL,
  `yh_insp_id_` bigint(20) DEFAULT NULL COMMENT '养护巡查日志记录ID',
  `zhfw_` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '桩号范围',
  `fx_` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '方向',
  `yhzh_` decimal(9,3) DEFAULT NULL COMMENT '桩号',
  `bhid_` bigint(20) DEFAULT NULL COMMENT '病害id',
  `bhmc_` varchar(200) CHARACTER SET utf8 DEFAULT NULL COMMENT '病害名称',
  `bhwz_` varchar(300) CHARACTER SET utf8 DEFAULT NULL COMMENT '病害位置',
  `dw_` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '单位',
  `ygsl_` decimal(9,3) DEFAULT NULL COMMENT '预估数量',
  `jcsj_` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '检查时间',
  `remark_` varchar(2048) CHARACTER SET utf8 DEFAULT NULL COMMENT '病害描述',
  `picattachment_` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片附件IDS',
  `vidattachment_` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '视频附件IDS',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `tpjd_` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片经度',
  `tpwd_` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '图片纬度',
  `yhzt_` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '养护状态(1:发现病害;2:下达养护任务通知单;3:施工单位养护完成;4:巡查验收完成)',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='养护巡查日志明细表';

-- ----------------------------
-- Table structure for hw_daily_inspectlogs
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_inspectlogs`;
CREATE TABLE `hw_daily_inspectlogs` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `gydw_id_` bigint(20) DEFAULT NULL COMMENT '管养单位ID',
  `lxid_` bigint(20) NOT NULL COMMENT '路线',
  `lxbh_` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '路线编号',
  `xcld_` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '巡查路段',
  `jcks_` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '检查开始',
  `jcjs_` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '检查结束',
  `jcsj_` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '检查时间',
  `weather_` varchar(20) CHARACTER SET utf8 DEFAULT NULL COMMENT '天气',
  `xcry_` varchar(2000) CHARACTER SET utf8 DEFAULT NULL COMMENT '巡查人',
  `jcry_` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '检查人员',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `status_` varchar(1) CHARACTER SET utf8 DEFAULT NULL COMMENT '状态',
  `bno_` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '表单编号',
  PRIMARY KEY (`id_`),
  KEY `AK_uni_daily_inspectlog_bno` (`bno_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='养护巡查日志表';

-- ----------------------------
-- Table structure for hw_daily_maintenlog
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_maintenlog`;
CREATE TABLE `hw_daily_maintenlog` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `bytzid_` bigint(20) DEFAULT NULL COMMENT '维修保养通知单id',
  `bytzidbytzid_` varchar(32) DEFAULT NULL COMMENT '通知单编号',
  `gydw_id_` bigint(20) DEFAULT NULL COMMENT '管养单位ID',
  `gydw_name_` varchar(256) DEFAULT NULL COMMENT '管养单位',
  `bno_` varchar(32) NOT NULL COMMENT '表单编号',
  `wxlx_` varchar(20) DEFAULT NULL COMMENT '维修类型',
  `wxbmid_` bigint(20) DEFAULT NULL COMMENT '维修部门id',
  `wxbmmc_` varchar(200) DEFAULT NULL COMMENT '维修部门名称',
  `wxrq_` varchar(20) DEFAULT NULL COMMENT '维修日期',
  `weather_` varchar(20) DEFAULT NULL COMMENT '天气',
  `jcry_` varchar(2000) DEFAULT NULL COMMENT '检查人',
  `fzry_` varchar(2000) DEFAULT NULL COMMENT '负责人',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `status_` varchar(1) DEFAULT NULL COMMENT '状态',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  `byrzzt_` varchar(1) DEFAULT NULL COMMENT '保养日志状态  1 填写保养日志  2 检查 验收',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `AK_uni_daily_maintenlog_bno` (`bno_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修保养日志';

-- ----------------------------
-- Table structure for hw_daily_maintenlogdetail
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_maintenlogdetail`;
CREATE TABLE `hw_daily_maintenlogdetail` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `byrzid_` bigint(20) DEFAULT NULL COMMENT '维修保养日志id',
  `bytzid_` bigint(20) DEFAULT NULL COMMENT '维修保养通知单id',
  `bytzno_` varchar(32) DEFAULT NULL COMMENT '通知单编号',
  `xcbhid_` bigint(20) DEFAULT NULL COMMENT '巡查日志病害id',
  `bhid_` bigint(20) DEFAULT NULL COMMENT '病害id',
  `bhmc_` varchar(200) DEFAULT NULL COMMENT '工程名称',
  `fx_` varchar(32) DEFAULT NULL COMMENT '方向',
  `yhzh_` decimal(9,3) DEFAULT NULL COMMENT '桩号',
  `dw_` varchar(20) DEFAULT NULL COMMENT '单位',
  `wxsl_` decimal(9,3) DEFAULT NULL COMMENT '维修数量',
  `wcbl_` int(11) DEFAULT NULL COMMENT '完成比例',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  `clmc_` varchar(100) DEFAULT NULL COMMENT '材料名称',
  `picattachment_` varchar(2000) DEFAULT NULL COMMENT '图片附件IDS',
  `vidattachment_` varchar(2000) DEFAULT NULL COMMENT '视频附件IDS',
  `tpjd_` varchar(50) DEFAULT NULL COMMENT '图片经度',
  `tpwd_` varchar(50) DEFAULT NULL COMMENT '图片纬度',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修保养日志明细';

-- ----------------------------
-- Table structure for hw_daily_projaccept
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_projaccept`;
CREATE TABLE `hw_daily_projaccept` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `gydw_id_` bigint(20) DEFAULT NULL COMMENT '管养单位ID',
  `bno_` varchar(32) NOT NULL COMMENT '表单编号',
  `yhtzid_` bigint(20) DEFAULT NULL COMMENT '养护通知单id',
  `yhtzdno` varchar(32) DEFAULT NULL COMMENT '养护通知单编号',
  `gydw_name_` varchar(256) DEFAULT NULL COMMENT '管养单位',
  `sgdwid_` bigint(20) DEFAULT NULL COMMENT '施工单位id',
  `sgdwmc_` varchar(200) DEFAULT NULL COMMENT '施工单位名称',
  `sgks_` varchar(20) DEFAULT NULL COMMENT '施工日期开始',
  `sgjs_` varchar(20) DEFAULT NULL COMMENT '施工日期结束',
  `ysjg_` varchar(2000) DEFAULT NULL COMMENT '验收结果',
  `qrzs_` decimal(9,2) DEFAULT NULL COMMENT '确认总数',
  `ysry_` varchar(64) DEFAULT NULL COMMENT '验收人',
  `ysrq_` varchar(20) DEFAULT NULL COMMENT '验收负责人签字日期',
  `sgfzry_` varchar(64) DEFAULT NULL COMMENT '负责人',
  `sgfzdate_` varchar(20) DEFAULT NULL COMMENT '施工负责人签字日期',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `status_` varchar(1) DEFAULT NULL COMMENT '状态',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `AK_uni_daily_projaccept_bno` (`bno_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修保养工程验收单';

-- ----------------------------
-- Table structure for hw_daily_projacceptdetail
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_projacceptdetail`;
CREATE TABLE `hw_daily_projacceptdetail` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `gcysid_` bigint(20) DEFAULT NULL COMMENT '工程验收单id',
  `bytzid_` bigint(20) DEFAULT NULL COMMENT '维修保养通知单id',
  `bytzno_` varchar(32) DEFAULT NULL COMMENT '通知单编号',
  `xcbhid_` bigint(20) DEFAULT NULL COMMENT '巡查日志病害表id',
  `bhid_` bigint(20) DEFAULT NULL COMMENT '病害id',
  `bhmc_` varchar(200) DEFAULT NULL COMMENT '养护工程名称',
  `fx_` varchar(32) DEFAULT NULL COMMENT '方向',
  `yhzh_` varchar(30) DEFAULT NULL COMMENT '桩号',
  `dw_` varchar(20) DEFAULT NULL COMMENT '单位',
  `wxsl_` decimal(9,3) DEFAULT NULL COMMENT '维修数量',
  `ysjg_` varchar(2000) DEFAULT NULL COMMENT '验收结果',
  `remark_` varchar(2000) DEFAULT NULL COMMENT '备注',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  `clmc_` varchar(100) DEFAULT NULL COMMENT '材料名称',
  `picattachment_` varchar(2000) DEFAULT NULL COMMENT '图片附件IDS',
  `vidattachment_` varchar(2000) DEFAULT NULL COMMENT '视频附件IDS',
  `tpjd_` varchar(50) DEFAULT NULL COMMENT '图片经度',
  `tpwd_` varchar(50) DEFAULT NULL COMMENT '图片纬度',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修保养日志明细';

-- ----------------------------
-- Table structure for hw_daily_safetycheck
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_safetycheck`;
CREATE TABLE `hw_daily_safetycheck` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `gydw_id_` bigint(20) DEFAULT NULL COMMENT '管养单位ID',
  `gydw_name_` varchar(256) DEFAULT NULL COMMENT '管养单位',
  `bno_` varchar(32) NOT NULL COMMENT '表单编号',
  `yhrzid_` bigint(20) DEFAULT NULL COMMENT '养护巡查日志id',
  `yhrzbno` varchar(32) DEFAULT NULL COMMENT '养护巡查日志表单编号',
  `sgdwid_` bigint(20) DEFAULT NULL COMMENT '施工单位id',
  `sgdwmc_` varchar(200) DEFAULT NULL COMMENT '施工单位名称',
  `jcsj_` varchar(20) DEFAULT NULL COMMENT '检查时间',
  `xcnr_` varchar(1000) DEFAULT NULL COMMENT '巡查内容',
  `jcry_` varchar(64) DEFAULT NULL COMMENT '检查人员',
  `jcdate_` varchar(20) DEFAULT NULL COMMENT '检查人员签字日期',
  `aqgly_` varchar(64) DEFAULT NULL COMMENT '安全管理员',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `status_` varchar(1) DEFAULT NULL COMMENT '状态',
  `qtqk_` varchar(1000) DEFAULT NULL COMMENT '其它情况',
  `clyj_` varchar(1000) DEFAULT NULL COMMENT '处理意见',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `aqgldate_` varchar(20) DEFAULT NULL COMMENT '安全管理员签字日期',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `AK_uni_daily_safetycheck_bno` (`bno_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工安全检查表';

-- ----------------------------
-- Table structure for hw_daily_safetycheckdetail
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_safetycheckdetail`;
CREATE TABLE `hw_daily_safetycheckdetail` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `aqjcid_` bigint(20) DEFAULT NULL COMMENT '施工安全检查记录ID',
  `jcx_` varchar(128) DEFAULT NULL COMMENT '检查项',
  `jczt_` varchar(128) DEFAULT NULL COMMENT '检查状态',
  `picattachment_` varchar(2000) DEFAULT NULL COMMENT '图片附件IDS',
  `vidattachment_` varchar(2000) DEFAULT NULL COMMENT '视频附件IDS',
  `tpjd_` varchar(50) DEFAULT NULL COMMENT '图片经度',
  `tpwd_` varchar(50) DEFAULT NULL COMMENT '图片纬度',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工安全检查表明细表';

-- ----------------------------
-- Table structure for hw_daily_upkeepdisease
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_upkeepdisease`;
CREATE TABLE `hw_daily_upkeepdisease` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `bytzid_` bigint(20) DEFAULT NULL COMMENT '维修保养通知单id',
  `xcbhid_` bigint(20) DEFAULT NULL COMMENT '巡查日志病害id',
  `xcrzno_` varchar(32) DEFAULT NULL COMMENT '巡查日志单号',
  `bhid_` bigint(20) DEFAULT NULL COMMENT '病害id',
  `bhmc_` varchar(200) DEFAULT NULL COMMENT '病害名称',
  `bhwz_` varchar(300) DEFAULT NULL COMMENT '病害位置',
  `fx_` varchar(32) DEFAULT NULL COMMENT '方向',
  `yhzh_` decimal(9,3) DEFAULT NULL COMMENT '桩号',
  `dw_` varchar(20) DEFAULT NULL COMMENT '单位',
  `ygsl_` decimal(9,3) DEFAULT NULL COMMENT '预估数量',
  `remark_` varchar(2048) DEFAULT NULL COMMENT '病害简述',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修保养通知单病害信息';

-- ----------------------------
-- Table structure for hw_daily_upkeepnotice
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_upkeepnotice`;
CREATE TABLE `hw_daily_upkeepnotice` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `gydw_id_` bigint(20) DEFAULT NULL COMMENT '管养单位ID',
  `gydw_name_` varchar(256) DEFAULT NULL COMMENT '管养单位',
  `tzld_` varchar(32) DEFAULT NULL COMMENT '路段',
  `bno_` varchar(32) NOT NULL COMMENT '表单编号',
  `wxlx_` varchar(20) DEFAULT NULL COMMENT '维修类型',
  `wxbmid_` bigint(20) DEFAULT NULL COMMENT '维修部门id',
  `wxbmmc_` varchar(200) DEFAULT NULL COMMENT '维修部门名称',
  `wxks_` varchar(20) DEFAULT NULL COMMENT '维修期限开始',
  `wxjs_` varchar(20) DEFAULT NULL COMMENT '维修期限结束',
  `weather_` varchar(20) DEFAULT NULL COMMENT '天气',
  `qfry_` varchar(2000) DEFAULT NULL COMMENT '签发人',
  `qfrq_` datetime DEFAULT NULL COMMENT '签发日期',
  `tjsj_` datetime DEFAULT NULL COMMENT '提交时间',
  `status_` varchar(1) DEFAULT NULL COMMENT '状态',
  `tzdzt_` varchar(1) DEFAULT NULL COMMENT '通知单状态 1 通知下达  2 维修保养 3 检查验收',
  `zlyq_` varchar(2000) DEFAULT NULL COMMENT '质量要求',
  `bcsm_` varchar(2000) DEFAULT NULL COMMENT '补充说明',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `AK_uni_daily_upkeepnotice_bno` (`bno_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修保养通知单';

-- ----------------------------
-- Table structure for hw_daily_upkeepquality
-- ----------------------------
DROP TABLE IF EXISTS `hw_daily_upkeepquality`;
CREATE TABLE `hw_daily_upkeepquality` (
  `id_` bigint(20) NOT NULL,
  `orgid_` bigint(20) DEFAULT NULL COMMENT '归属组织机构',
  `versionno_` int(11) DEFAULT NULL COMMENT '版本号',
  `create_by_` bigint(20) NOT NULL COMMENT '创建者ID',
  `creator_` varchar(64) DEFAULT NULL COMMENT '创建者',
  `createtime_` datetime NOT NULL COMMENT '创建时间',
  `update_by_` bigint(20) DEFAULT NULL COMMENT '最后修改者ID',
  `updator_` varchar(64) DEFAULT NULL COMMENT '最后修改者',
  `updatetime_` datetime DEFAULT NULL COMMENT '最后修改时间',
  `flag_` int(11) DEFAULT NULL COMMENT '是否有效',
  `bytzid_` bigint(20) DEFAULT NULL COMMENT '维修保养通知单id',
  `zlyqid_` bigint(20) DEFAULT NULL COMMENT '质量要求id',
  `zlyqny_` varchar(1000) DEFAULT NULL COMMENT '质量要求内容',
  `cus1_` varchar(20) DEFAULT NULL COMMENT '自订字段1',
  `cus2_` varchar(10) DEFAULT NULL COMMENT '自订字段2',
  `cus3_` varchar(256) DEFAULT NULL COMMENT '自订字段3',
  `cus4_` bigint(20) DEFAULT NULL COMMENT '自订字段4',
  `cus5_` decimal(9,2) DEFAULT NULL COMMENT '自订字段5',
  `cus6_` varchar(128) DEFAULT NULL COMMENT '自订字段6',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维修保养通知单质量要求信息';
