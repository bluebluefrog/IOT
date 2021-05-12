/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : IOT

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 12/05/2021 14:15:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `product_id` int NOT NULL COMMENT '商品id',
  `user_id` int NOT NULL COMMENT '用户id',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '商品数量',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车';

-- ----------------------------
-- Records of cart
-- ----------------------------
BEGIN;
INSERT INTO `cart` VALUES (39, 17, 17, 1, '2021-05-11 17:27:27', '2021-05-11 17:27:27');
INSERT INTO `cart` VALUES (40, 16, 17, 1, '2021-05-11 17:27:32', '2021-05-11 17:27:32');
INSERT INTO `cart` VALUES (41, 24, 17, 1, '2021-05-11 17:27:45', '2021-05-11 17:27:45');
INSERT INTO `cart` VALUES (70, 23, 20, 3, '2021-05-11 17:52:16', '2021-05-11 17:52:46');
INSERT INTO `cart` VALUES (71, 24, 20, 1, '2021-05-11 17:52:20', '2021-05-11 17:52:20');
INSERT INTO `cart` VALUES (84, 24, 13, 1, '2021-05-12 13:29:13', '2021-05-12 13:29:13');
INSERT INTO `cart` VALUES (85, 23, 13, 1, '2021-05-12 13:33:18', '2021-05-12 13:33:18');
COMMIT;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '分类目录名称',
  `type` int NOT NULL COMMENT '分类目录级别，例如1代表一级，2代表二级，3代表三级',
  `parent_id` int NOT NULL COMMENT '父id，也就是上一级目录的id，如果是一级目录，那么父id为0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品分类 ';

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES (1, 'computer', 1, 0, '2021-05-04 16:24:29', '2021-05-09 18:03:44');
INSERT INTO `category` VALUES (2, 'phone', 1, 0, '2021-05-09 18:04:06', '2021-05-09 18:04:50');
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(128) NOT NULL DEFAULT '' COMMENT '订单号（非主键id）',
  `user_id` int NOT NULL COMMENT '用户id',
  `total_price` int NOT NULL COMMENT '订单总价格',
  `receiver_name` varchar(32) NOT NULL COMMENT '收货人姓名快照',
  `receiver_mobile` varchar(32) NOT NULL COMMENT '收货人手机号快照',
  `receiver_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '收货地址快照',
  `order_status` int NOT NULL DEFAULT '10' COMMENT '订单状态: 0用户已取消，10未付款（初始状态），20已付款，30已发货，40交易完成',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表;';

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
INSERT INTO `order` VALUES (20, '59bd1d2a-5680-42b9-8ff8-825a3b48027c1620718564965', 13, 2000, 'wjb', '123', 'wjbaddress', 2, '2021-05-11 17:36:04', '2021-05-11 17:36:16');
INSERT INTO `order` VALUES (21, 'e0fb43cc-e362-49a0-afb0-5d6a0820e9641620718935646', 19, 3000, 'Oswald Li', '0416140080', 'I forgot', 2, '2021-05-11 17:42:15', '2021-05-11 17:46:01');
INSERT INTO `order` VALUES (23, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 20, 19101, 'Esther', '0451108560', '13 m', 2, '2021-05-11 17:45:28', '2021-05-11 17:45:52');
INSERT INTO `order` VALUES (25, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 20, 34201, 'Esther', '0123456', '1Rhodes', 3, '2021-05-11 17:49:01', '2021-05-11 17:50:02');
INSERT INTO `order` VALUES (26, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 20, 24000, 'Esther', '012345678', 'Rhodes', 2, '2021-05-11 17:51:40', '2021-05-11 17:51:55');
INSERT INTO `order` VALUES (27, '78c85fb6-7399-4bb9-97ae-b17f73eadd9e1620719745338', 18, 20000, 'frank', '0410923879', '1136/185-211 Broadway Ultimo NSW 2007', 1, '2021-05-11 17:55:45', '2021-05-11 17:55:45');
INSERT INTO `order` VALUES (28, '045915c6-2681-419e-ba9c-0dbbf6a8f6a31620720609072', 13, 2000, '1', '1', '1', 2, '2021-05-11 18:10:09', '2021-05-11 18:10:11');
INSERT INTO `order` VALUES (29, 'cfeb9df5-a5be-4dd3-a99c-2c2f1f2fafcf1620721949224', 13, 4000, '1', '1', '1', 2, '2021-05-11 18:32:29', '2021-05-11 18:32:34');
INSERT INTO `order` VALUES (30, '200f4814-0104-4d2e-8b5e-6a41040ace4e1620722001555', 13, 2000, '1', '1', '1', 2, '2021-05-11 18:33:21', '2021-05-11 18:33:27');
INSERT INTO `order` VALUES (31, '1f0b9c05-3028-42a1-b8fa-6cba85a7106f1620722899205', 13, 2000, '1', '1', '1', 2, '2021-05-11 18:48:19', '2021-05-11 18:48:25');
INSERT INTO `order` VALUES (32, 'aded1419-e8c6-4695-8ac6-839c52cc8bf41620723076917', 13, 15000, '1', '1', '1', 2, '2021-05-11 18:51:16', '2021-05-11 18:51:20');
INSERT INTO `order` VALUES (33, 'edcd29ba-b993-424f-a068-43017552bd011620723112504', 13, 4000, '1', '1', '1', 2, '2021-05-11 18:51:52', '2021-05-11 18:51:54');
INSERT INTO `order` VALUES (34, '9ad2e03a-74c4-4af0-af21-7793c97b603d1620788965317', 21, 9700, 'Atif', '345', 'hfjhgiiyjbvlf', 2, '2021-05-12 13:09:25', '2021-05-12 13:10:16');
INSERT INTO `order` VALUES (35, '55f24685-1337-4dda-9c7d-9e0dd5c6b1551620789146880', 21, 4, 'n,n,nn', ',nnllkjljjjll', 'llnkgkhhkjhkjh', 3, '2021-05-12 13:12:26', '2021-05-12 13:12:34');
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `order_item_id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(128) NOT NULL DEFAULT '' COMMENT '归属订单id',
  `product_id` int NOT NULL COMMENT '商品id',
  `product_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
  `product_img` varchar(128) NOT NULL DEFAULT '' COMMENT '商品图片',
  `unit_price` int NOT NULL COMMENT '单价（下单时的快照）',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '商品数量',
  `total_price` int NOT NULL DEFAULT '0' COMMENT '商品总价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`order_item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单的商品表 ';

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
INSERT INTO `order_item` VALUES (21, '59bd1d2a-5680-42b9-8ff8-825a3b48027c1620718564965', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 17:36:04', '2021-05-11 17:36:04');
INSERT INTO `order_item` VALUES (22, 'e0fb43cc-e362-49a0-afb0-5d6a0820e9641620718935646', 18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 3000, 1, 3000, '2021-05-11 17:42:15', '2021-05-11 17:42:15');
INSERT INTO `order_item` VALUES (23, '4118f03f-3909-4af2-b7f8-0fe53e683a191620718935709', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 17:42:15', '2021-05-11 17:42:15');
INSERT INTO `order_item` VALUES (24, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 22, 'design laptop', '/upload/itemphoto/3692b3c8-b340-4b52-8489-c56d9a6a20ca.jpg', 5000, 1, 5000, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (25, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 20, 'gameing laptop', '/upload/itemphoto/c4af4b3f-3408-4274-a772-f9d2cff992eb.jpg', 5000, 1, 5000, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (26, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 19, 'laptop2', '/upload/itemphoto/ca569129-fed4-4537-af60-f9b39ba1c3cb.png', 3000, 1, 3000, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (27, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (28, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 16, 'laptop1', '/upload/itemphoto/018012f4-c770-4ef0-b1d8-b7f16ff2a55b.jpg', 1000, 1, 1000, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (29, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 3000, 1, 3000, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (30, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 23, 'laptop', '/upload/', 100, 1, 100, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (31, '8dc8fb5d-a4c2-46e7-baae-97f30d1abad51620719128171', 24, '1', '/upload/itemphoto/abfa938d-6f0b-49c1-91f1-029c0eac737c.jpg', 1, 1, 1, '2021-05-11 17:45:28', '2021-05-11 17:45:28');
INSERT INTO `order_item` VALUES (32, '608039e4-2420-4649-8fa8-9ef228f739231620719146225', 18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 3000, 1, 3000, '2021-05-11 17:45:46', '2021-05-11 17:45:46');
INSERT INTO `order_item` VALUES (33, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 16, 'laptop1', '/upload/itemphoto/018012f4-c770-4ef0-b1d8-b7f16ff2a55b.jpg', 1000, 1, 1000, '2021-05-11 17:49:00', '2021-05-11 17:49:00');
INSERT INTO `order_item` VALUES (34, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 17:49:00', '2021-05-11 17:49:00');
INSERT INTO `order_item` VALUES (35, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 3000, 1, 3000, '2021-05-11 17:49:00', '2021-05-11 17:49:00');
INSERT INTO `order_item` VALUES (36, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 19, 'laptop2', '/upload/itemphoto/ca569129-fed4-4537-af60-f9b39ba1c3cb.png', 3000, 1, 3000, '2021-05-11 17:49:00', '2021-05-11 17:49:00');
INSERT INTO `order_item` VALUES (37, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 20, 'gameing laptop', '/upload/itemphoto/c4af4b3f-3408-4274-a772-f9d2cff992eb.jpg', 5000, 2, 10000, '2021-05-11 17:49:00', '2021-05-11 17:49:00');
INSERT INTO `order_item` VALUES (38, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 21, 'gameing laptop2', '/upload/itemphoto/6725037e-2ca4-4150-99fd-9870279b2918.png', 5000, 1, 5000, '2021-05-11 17:49:00', '2021-05-11 17:49:00');
INSERT INTO `order_item` VALUES (39, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 22, 'design laptop', '/upload/itemphoto/3692b3c8-b340-4b52-8489-c56d9a6a20ca.jpg', 5000, 2, 10000, '2021-05-11 17:49:00', '2021-05-11 17:49:00');
INSERT INTO `order_item` VALUES (40, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 23, 'laptop', '/upload/', 100, 2, 200, '2021-05-11 17:49:01', '2021-05-11 17:49:01');
INSERT INTO `order_item` VALUES (41, 'e4ce039c-184b-4d57-a0e5-063133f466391620719340957', 24, '1', '/upload/itemphoto/abfa938d-6f0b-49c1-91f1-029c0eac737c.jpg', 1, 1, 1, '2021-05-11 17:49:01', '2021-05-11 17:49:01');
INSERT INTO `order_item` VALUES (42, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 3000, 1, 3000, '2021-05-11 17:51:40', '2021-05-11 17:51:40');
INSERT INTO `order_item` VALUES (43, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 17:51:40', '2021-05-11 17:51:40');
INSERT INTO `order_item` VALUES (44, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 16, 'laptop1', '/upload/itemphoto/018012f4-c770-4ef0-b1d8-b7f16ff2a55b.jpg', 1000, 1, 1000, '2021-05-11 17:51:40', '2021-05-11 17:51:40');
INSERT INTO `order_item` VALUES (45, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 19, 'laptop2', '/upload/itemphoto/ca569129-fed4-4537-af60-f9b39ba1c3cb.png', 3000, 1, 3000, '2021-05-11 17:51:40', '2021-05-11 17:51:40');
INSERT INTO `order_item` VALUES (46, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 21, 'gameing laptop2', '/upload/itemphoto/6725037e-2ca4-4150-99fd-9870279b2918.png', 5000, 1, 5000, '2021-05-11 17:51:40', '2021-05-11 17:51:40');
INSERT INTO `order_item` VALUES (47, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 22, 'design laptop', '/upload/itemphoto/3692b3c8-b340-4b52-8489-c56d9a6a20ca.jpg', 5000, 1, 5000, '2021-05-11 17:51:40', '2021-05-11 17:51:40');
INSERT INTO `order_item` VALUES (48, '2db97fa4-9f9a-4137-9981-f0b31b2492ce1620719500804', 20, 'gameing laptop', '/upload/itemphoto/c4af4b3f-3408-4274-a772-f9d2cff992eb.jpg', 5000, 1, 5000, '2021-05-11 17:51:40', '2021-05-11 17:51:40');
INSERT INTO `order_item` VALUES (49, '78c85fb6-7399-4bb9-97ae-b17f73eadd9e1620719745338', 21, 'gameing laptop2', '/upload/itemphoto/6725037e-2ca4-4150-99fd-9870279b2918.png', 5000, 4, 20000, '2021-05-11 17:55:45', '2021-05-11 17:55:45');
INSERT INTO `order_item` VALUES (50, '045915c6-2681-419e-ba9c-0dbbf6a8f6a31620720609072', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 18:10:09', '2021-05-11 18:10:09');
INSERT INTO `order_item` VALUES (51, 'cfeb9df5-a5be-4dd3-a99c-2c2f1f2fafcf1620721949224', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 2, 4000, '2021-05-11 18:32:29', '2021-05-11 18:32:29');
INSERT INTO `order_item` VALUES (52, '200f4814-0104-4d2e-8b5e-6a41040ace4e1620722001555', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 18:33:21', '2021-05-11 18:33:21');
INSERT INTO `order_item` VALUES (53, '1f0b9c05-3028-42a1-b8fa-6cba85a7106f1620722899205', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 1, 2000, '2021-05-11 18:48:19', '2021-05-11 18:48:19');
INSERT INTO `order_item` VALUES (54, 'aded1419-e8c6-4695-8ac6-839c52cc8bf41620723076917', 20, 'gaming laptop', '/upload/itemphoto/c4af4b3f-3408-4274-a772-f9d2cff992eb.jpg', 5000, 3, 15000, '2021-05-11 18:51:16', '2021-05-11 18:51:16');
INSERT INTO `order_item` VALUES (55, 'edcd29ba-b993-424f-a068-43017552bd011620723112504', 16, 'laptop1', '/upload/itemphoto/018012f4-c770-4ef0-b1d8-b7f16ff2a55b.jpg', 1000, 1, 1000, '2021-05-11 18:51:52', '2021-05-11 18:51:52');
INSERT INTO `order_item` VALUES (56, 'edcd29ba-b993-424f-a068-43017552bd011620723112504', 18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 3000, 1, 3000, '2021-05-11 18:51:52', '2021-05-11 18:51:52');
INSERT INTO `order_item` VALUES (57, '9ad2e03a-74c4-4af0-af21-7793c97b603d1620788965317', 18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 3000, 1, 3000, '2021-05-12 13:09:25', '2021-05-12 13:09:25');
INSERT INTO `order_item` VALUES (58, '9ad2e03a-74c4-4af0-af21-7793c97b603d1620788965317', 17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 2000, 3, 6000, '2021-05-12 13:09:25', '2021-05-12 13:09:25');
INSERT INTO `order_item` VALUES (59, '9ad2e03a-74c4-4af0-af21-7793c97b603d1620788965317', 25, 'laptop', '/upload/itemphoto/18906ecb-be5b-45cb-8911-0e6919d0bc69.jpg', 700, 1, 700, '2021-05-12 13:09:25', '2021-05-12 13:09:25');
INSERT INTO `order_item` VALUES (60, '55f24685-1337-4dda-9c7d-9e0dd5c6b1551620789146880', 24, '1', '/upload/itemphoto/abfa938d-6f0b-49c1-91f1-029c0eac737c.jpg', 1, 4, 4, '2021-05-12 13:12:26', '2021-05-12 13:12:26');
COMMIT;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `image` varchar(500) NOT NULL DEFAULT '',
  `detail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `category_id` int NOT NULL,
  `price` int NOT NULL,
  `stock` int NOT NULL,
  `status` int NOT NULL DEFAULT '1' COMMENT 'product status：0-not showing，1-showing',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表 ';

-- ----------------------------
-- Records of product
-- ----------------------------
BEGIN;
INSERT INTO `product` VALUES (16, 'laptop1', '/upload/itemphoto/018012f4-c770-4ef0-b1d8-b7f16ff2a55b.jpg', 'this is a laptop', 1, 1000, 96, 1, '2021-05-09 18:04:58', '2021-05-11 18:51:52');
INSERT INTO `product` VALUES (17, 'mac', '/upload/itemphoto/2857ad08-787f-48f1-b988-f1bcac62f7a2.jpg', 'this is a mac,apple very expensive this is a mac,apple very expensive this is a mac,apple very expensive', 1, 2000, 87, 1, '2021-05-09 18:06:05', '2021-05-12 13:09:25');
INSERT INTO `product` VALUES (18, 'alienware', '/upload/itemphoto/273d2778-b240-4e05-a4b0-18fc6484083c.jpg', 'this is a laptop', 1, 3000, 93, 1, '2021-05-09 18:06:39', '2021-05-12 13:09:25');
INSERT INTO `product` VALUES (19, 'laptop2', '/upload/itemphoto/ca569129-fed4-4537-af60-f9b39ba1c3cb.png', 'this is a laptop', 1, 3000, 97, 1, '2021-05-09 18:06:56', '2021-05-11 17:51:40');
INSERT INTO `product` VALUES (20, 'gaming laptop', '/upload/itemphoto/c4af4b3f-3408-4274-a772-f9d2cff992eb.jpg', 'this is a laptop', 1, 5000, 93, 1, '2021-05-09 18:07:18', '2021-05-11 18:51:16');
INSERT INTO `product` VALUES (21, 'gaming laptop2', '/upload/itemphoto/6725037e-2ca4-4150-99fd-9870279b2918.png', 'this is a laptop', 1, 5000, 94, 1, '2021-05-09 18:07:34', '2021-05-11 17:55:52');
INSERT INTO `product` VALUES (22, 'design laptop', '/upload/itemphoto/3692b3c8-b340-4b52-8489-c56d9a6a20ca.jpg', 'this is a laptop', 1, 5000, 96, 1, '2021-05-09 18:07:56', '2021-05-11 17:51:40');
INSERT INTO `product` VALUES (23, 'laptop', '/upload/itemphoto/be516138-cc34-4c51-b763-c4bc6e675106.jpg', '1', 1, 10000, 97, 1, '2021-05-09 21:23:28', '2021-05-12 13:33:44');
INSERT INTO `product` VALUES (24, '1', '/upload/itemphoto/6989bcb7-fc51-4c37-a061-001349e4c75f.jpg', '1', 1, 123, 94, 1, '2021-05-09 21:30:39', '2021-05-12 13:30:31');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `common` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '个性签名',
  `role` int NOT NULL DEFAULT '1' COMMENT '角色，1-普通用户，2-管理员',
  `balence` bigint DEFAULT NULL COMMENT 'acc money amount',
  `email` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL COMMENT 'photo',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表 ';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (13, 'wjb', '123456', '1', 2, 1000000, '123', '/upload/userphoto/6b118a7e-3c41-4fad-aed1-635a8cd8a42b.jpg', '2021-05-11 16:57:48', '2021-05-12 13:21:51');
INSERT INTO `user` VALUES (17, 'Suaige', 'Suaige', 'Suaige', 1, 50000, 'elsieyyyx@gmail.com', '/upload/userphoto/b7adc20a-3060-4584-93a7-5dd7e0510d52.jpg', '2021-05-11 17:27:06', '2021-05-11 17:37:35');
INSERT INTO `user` VALUES (18, 'Frank', '757857699', 'im frank', 1, 50000, '757857699@qq.com', '/', '2021-05-11 17:36:24', '2021-05-11 18:08:09');
INSERT INTO `user` VALUES (19, 'Oswald Li', 'li165650617', 'Handsome boy', 1, 47000, '2523298068@qq.com', '/', '2021-05-11 17:37:55', '2021-05-11 18:08:11');
INSERT INTO `user` VALUES (20, 'Esther', 'esther', '？', 1, 6, 'zhengyiii118@163.com', '/', '2021-05-11 17:39:40', '2021-05-11 18:08:12');
INSERT INTO `user` VALUES (21, 'atif', 'atif', 'why?', 1, 90300, 'atif@atif.com', '/upload/userphoto/ef0187ee-b49b-4ba2-ad26-742fc7812188.jpg', '2021-05-12 12:57:30', '2021-05-12 13:20:09');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
