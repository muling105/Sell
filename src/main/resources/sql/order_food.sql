/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : order_food

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2018-06-07 17:28:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `detail_id` varchar(32) NOT NULL,
  `order_id` varchar(32) NOT NULL COMMENT '订单ID',
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `product_name` varchar(60) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_quant` int(11) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(600) DEFAULT NULL COMMENT '商品图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情';

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('1301', '1201', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-31 11:56:56');
INSERT INTO `order_detail` VALUES ('1302', '1201', '1003', '珍珠奶茶', '10.00', '1', 'http://*******.jgp', '2018-05-31 11:57:50');
INSERT INTO `order_detail` VALUES ('15277521040315411440', '15277521007765586579', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15277521049743830982', '15277521007765586579', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15278342268885521306', '15278342264863824656', '1003', '珍珠奶茶', '10.00', '2', 'http://*******.jgp', '2018-05-30 17:14:10');
INSERT INTO `order_detail` VALUES ('15283372257949059465', '15283372225804970981', '1003', '珍珠奶茶', '10.00', '2', 'http://*******.jgp', '2018-05-30 17:14:10');
INSERT INTO `order_detail` VALUES ('15283375923546586329', '15283375921754961518', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283375924187488237', '15283375921754961518', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283376997988564265', '15283376993149229638', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283377002354189847', '15283376993149229638', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283377732493152255', '15283377731727609764', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283377732783285730', '15283377731727609764', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283389975521857157', '15283389972003807696', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283389976133019267', '15283389972003807696', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283390070674661951', '15283390070656919156', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283390071041088762', '15283390070656919156', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283390133338950444', '15283390133309464711', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283390136033915027', '15283390133309464711', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283403980864532405', '15283403980576253377', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283403982115394104', '15283403980576253377', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283407635398390685', '15283407634877337801', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283407636482806155', '15283407634877337801', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');
INSERT INTO `order_detail` VALUES ('15283412629676416204', '15283412627423652228', '1001', '荷叶粥', '2.00', '1', 'http://*********.jpg', '2018-05-30 14:34:43');
INSERT INTO `order_detail` VALUES ('15283412632923162709', '15283412627423652228', '1002', '蛋花汤', '1.50', '1', 'http://*********2.jpg', '2018-05-30 16:29:47');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master` (
  `order_id` varchar(32) NOT NULL,
  `buyer_name` varchar(32) NOT NULL COMMENT '买家名称',
  `buyer_phone` varchar(32) NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(100) NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(32) NOT NULL COMMENT '买家微信openid',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '订单状态 默认0代表新订单',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT '支付状态 默认0代表未支付',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `order_amount` tinyint(5) DEFAULT NULL COMMENT '订单总价',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_openid` (`buyer_openid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1201', '张三', '13890901919', '齐鲁软件园', '12211', '0', '0', '2018-05-31 11:43:01', null);
INSERT INTO `order_master` VALUES ('1202', '李四', '13890901919', '银荷大厦', '12211', '0', '0', '2018-05-31 11:48:04', null);
INSERT INTO `order_master` VALUES ('15277521007765586579', '张三', '13890901919', '齐鲁软件园', '12213', '0', '1', '2018-05-31 15:35:05', '4');
INSERT INTO `order_master` VALUES ('15278342264863824656', '赵四', '13287493590', '山东菏泽东明赵庄', 'iyi09dfs8d9sd', '0', '0', '2018-06-01 14:23:47', '20');
INSERT INTO `order_master` VALUES ('15283372225804970981', '赵四', '13287493590', '山东菏泽东明赵庄', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:07:06', '20');
INSERT INTO `order_master` VALUES ('15283375921754961518', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:13:12', '4');
INSERT INTO `order_master` VALUES ('15283376993149229638', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:15:00', '4');
INSERT INTO `order_master` VALUES ('15283377731727609764', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:16:13', '4');
INSERT INTO `order_master` VALUES ('15283389972003807696', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:36:38', '4');
INSERT INTO `order_master` VALUES ('15283390070656919156', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:36:47', '4');
INSERT INTO `order_master` VALUES ('15283390133309464711', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:36:53', '4');
INSERT INTO `order_master` VALUES ('15283403980576253377', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 10:59:58', '4');
INSERT INTO `order_master` VALUES ('15283407634877337801', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 11:06:03', '4');
INSERT INTO `order_master` VALUES ('15283412627423652228', '杨七', '13387493518', '山东菏泽曹县张破钟村', 'iyi09dfs8d9sd', '0', '0', '2018-06-07 11:14:23', '4');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(60) NOT NULL COMMENT '类目名称',
  `category_type` int(11) NOT NULL COMMENT '类目编号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uqe_category_type` (`category_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='类目表';

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES ('1', '热销', '2', '2018-05-19 16:24:45');
INSERT INTO `product_category` VALUES ('3', '食品', '3', '2018-05-19 16:40:45');
INSERT INTO `product_category` VALUES ('9', '饮料', '4', '2018-05-19 16:54:57');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info` (
  `product_id` varchar(32) NOT NULL,
  `product_name` varchar(50) NOT NULL COMMENT '商品名称',
  `product_price` decimal(8,2) NOT NULL COMMENT '商品价格',
  `product_stock` int(11) DEFAULT NULL COMMENT '库存',
  `product_description` varchar(1000) DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(600) DEFAULT NULL COMMENT '商品图片',
  `category_type` int(11) NOT NULL COMMENT '商品类目',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `product_status` int(11) DEFAULT '0' COMMENT '0-正常 1-下架',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES ('1001', '荷叶粥', '2.00', '11', '健康', 'http://*********.jpg', '2', '2018-05-30 14:34:43', '0');
INSERT INTO `product_info` VALUES ('1002', '蛋花汤', '1.50', '21', '营养', 'http://*********2.jpg', '2', '2018-05-30 16:29:47', '0');
INSERT INTO `product_info` VALUES ('1003', '珍珠奶茶', '10.00', '100', '美味', 'http://*******.jgp', '4', '2018-05-30 17:14:10', '0');

-- ----------------------------
-- Table structure for seller_info
-- ----------------------------
DROP TABLE IF EXISTS `seller_info`;
CREATE TABLE `seller_info` (
  `seller_id` varchar(30) NOT NULL COMMENT '卖家ID',
  `username` varchar(60) NOT NULL COMMENT '卖家名称',
  `password` varchar(32) NOT NULL COMMENT '卖家密码',
  `openid` varchar(32) NOT NULL COMMENT '微信openid',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`seller_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卖家信息';

-- ----------------------------
-- Records of seller_info
-- ----------------------------
INSERT INTO `seller_info` VALUES ('15282645199263208140', 'admin', '123456', 'abc123', '2018-06-06 13:55:20');
