# 高校学科竞赛管理系统



## 介绍

​	这是在javaee课程中的一个小组作业，旨在定义和描述一个基于SpringBoot的高校学科竞赛管理系统，以满足高效率、便捷、人性化等要求。

## 相关工具版本

- springboot 2.6.13
- redis 3.2
- mysql 8.0.11

## 开始使用

 	1.启动前，请配置`application.yml`中相关`redis`、`mysql`（创建好数据库product）

​	 2.请使用数据管理工具如 `navicat` 执行项目下的`product.sql`

​	 3.登录地址：http://localhost:8081/login

​     4.默认管理员登入 `username: admin  password: 123456`

## 项目说明及截图

### 1 后台首页

#### 	1.1 首页

​			提供系统给出的公告和操作说明

### 2 个人信息

#### 	2.1 个人信息修改

​			提供修改头像，用户名，手机号等修改功能

#### 	2.2 个人密码修改

​			修改旧密码并且验证

### 3 竞赛信息

#### 	3.1 竞赛信息列表

​			查询竞赛信息列表并且在管理员权限下可以修改竞赛信息

#### 	3.2 竞赛活动发布

​			提供发布竞赛信息的表单 申请后会提交管理员进行审核



### 4 报名信息

#### 	4.1 报名信息列表

​			查询报名信息列表并且在管理员权限下可以修改 报名信息

#### 	4.2 审核

​			 用于管理员审核个人信息

#### 5 公告信息

#### 	5.1 公告列表

​			添加修改系统公告和竞赛公告

## 其他



​	











