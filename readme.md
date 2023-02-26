# MOOC-SpringCloud

## 相关项目

- [MOOC](https://github.com/saiGou-14H/MOOC)

- [MOOC-Vue-Admin-Template](https://github.com/saiGou-14H/MOOC-Vue-Admin-Template)

- [MOOC-Android](https://github.com/saiGou-14H/MOOC-Android)

  

## 项目简介

​		企业内部培训软件用来支持企业内部教师和员工的在线授课和学习过程。企业内部培训软件是一个基于web的多终端（PC端、移动端）应用形式，可以通过互联网进行访问。

​		提出开发企业内部培训软件的客户是某医疗卫生服务单位，该单位职工规模达到了1000人，随着信息化潮流的影响以及其医疗服务员工规模的不断增加，对于管理人员来说，如何培训医疗服务人员变得越来越复杂、繁琐，急需建立一套系统来对员工进行信息化在线培训。

​		系统架构设计采用的是B/S三层结构模式，包含用户表示层、中间层和数据库层。使用该结构模式能使项目架构更为清晰明了，因为这三层每层都分工明确，各司其职，可以让系统服务有条不紊。



## 项目实现

​	后端选择IDEA平台上集成开发，采用Java语言和MySQL数据库搭建成项目结构，使用SpringBoot和MybaticsPlus框架进行数据持久化，通过API框架-Swagger定义接口文档。此外，后端应用WebSocket协议与闸机系统进行全双工通信，实时接收前端发送的文字消息与图片流，使得客户端和服务器之间的数据交互变得更加简单，允许服务端主动向客户端主动推送数据，浏览器和服务器只需要完成一次握手，两者之间就直接可以创建持久性的连接，并进行双向数据传输。后端应用Http超文本传输协议与人脸识别模块进行数据交互，基于请求/响应的形式使得数据简单传输并及时反馈给服务器。两种通信方式相辅相成，使得用户并发刷脸签到的完成时间控制在2-3秒以内，大大提高了客户端与服务器端的请求响应速率。



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