# MOOC-SpringCloud

## 相关项目

- [MOOC](https://github.com/saiGou-14H/MOOC)

- [MOOC-Vue-Admin-Template](https://github.com/saiGou-14H/MOOC-Vue-Admin-Template)

- [MOOC-Android](https://github.com/saiGou-14H/MOOC-Android)

  

## 项目简介

​		企业内部培训软件用来支持企业内部教师和员工的在线授课和学习过程。企业内部培训软件是一个基于web的多终端（PC端、移动端）应用形式，可以通过互联网进行访问。

​		提出开发企业内部培训软件的客户是某医疗卫生服务单位，该单位职工规模达到了1000人，随着信息化潮流的影响以及其医疗服务员工规模的不断增加，对于管理人员来说，如何培训医疗服务人员变得越来越复杂、繁琐，急需建立一套系统来对员工进行信息化在线培训。

​		

## 项目实现

​		系统架构设计采用的是B/S三层结构模式，包含用户表示层、中间层和数据库层。使用该结构模式能使项目架构更为清晰明了，因为这三层每层都分工明确，各司其职，可以让系统服务有条不紊。	

​		后端基于java语言，通过SpringCloud框架搭建项目结构，同时将系统分为划分为微服务集群（路由网关、注册中心、用户服务、班级服务、课程服务、社区服务），并结合Redis数据库实现安全验证，使用MySql数据库和Mybatics-Plus框架进行数据持久化。为规范与前端交互的规则，通过API框架-Swagger定义接口文档。



## 后端技术栈

| 技术         | 说明            | 链接                                      |
| ------------ | --------------- | ----------------------------------------- |
| SpringBoot   | Web应用开发框架 | https://spring.io/projects/spring-boot    |
| SpringCloud  | 微服务框架      | https://spring.io/projects/spring-cloud   |
| MyBatis-Plus | ORM框架         | https://baomidou.com/                     |
| Redis        | 内存数据存储    | https://redis.io/                         |
| JWT          | JWT登录支持     | https://github.com/jwtk/jjwt              |
| Lombok       | Java语言增强库  | https://github.com/rzwitserloot/lombok    |
| Swagger-UI   | API文档生成工具 | https://github.com/swagger-api/swagger-ui |



## 运行环境

- IDEA编译器
- java 11
- jdk 1.8
- SpringBoot 2.3.7.RELAEASE
- SpringColud Hoxton.SR12
- MySQL 8.0.29
- mybatis-plus 3.5.1
- Redis