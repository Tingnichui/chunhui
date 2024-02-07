# chunhui

## 前言

`chunhui`项目致力于搭建一套拥有完善的权限限制的管理系统，采用非主流技术实现，尽量少的引用依赖。

## 项目文档

> 系统还在开发初始阶段，待完善

## 项目介绍

`chunhui`项目是一套后台管理系统，基于SpringBoot+MyBatis实现。后台管理系统包含用户管理、菜单管理、权限管理、角色管理等模块。未来逐渐接入业务模块，例如健身房管理、文件资源管理等业务模块。

### 组织结构

```
chunhui
├── admin-web -- 后台管理代码
├── document -- 文本文件，sql、功能说明、需求文档等等
├── generate -- 基础代码生成器
└── web-api -- 后台管理系统接口
```

### 项目演示

> 系统还在开发初始阶段，待完善

## 技术选型

#### 后端技术

| 技术                 | 说明            | 官网                                           |
| -------------------- | --------------- | ---------------------------------------------- |
| SpringBoot           | Web应用开发框架 | https://spring.io/projects/spring-boot         |
| MyBatis              | ORM框架         | http://www.mybatis.org/mybatis-3/zh/index.html |
| Redis                | 内存数据存储    | https://redis.io/                              |
| Nginx                | 静态资源服务器  | https://www.nginx.com/                         |
| OSS                  | 对象存储        | https://github.com/aliyun/aliyun-oss-java-sdk  |
| Lombok               | Java语言增强库  | https://github.com/rzwitserloot/lombok         |
| Hutool               | Java工具类库    | https://github.com/looly/hutool                |
| Hibernator-Validator | 验证框架        | http://hibernate.org/validator                 |

#### 前端技术

| 技术       | 说明                  | 官网                                                  |
| ---------- | --------------------- | ----------------------------------------------------- |
| Vue        | 前端框架              | https://vuejs.org/                                    |
| Vue-router | 路由框架              | https://router.vuejs.org/                             |
| Vuex       | 全局状态管理框架      | https://vuex.vuejs.org/                               |
| Element    | 前端UI框架            | [https://element.eleme.io](https://element.eleme.io/) |
| Axios      | 前端HTTP框架          | https://github.com/axios/axios                        |
| v-charts   | 基于Echarts的图表框架 | https://v-charts.js.org/                              |
| Js-cookie  | cookie管理工具        | https://github.com/js-cookie/js-cookie                |
| nprogress  | 进度条控件            | https://github.com/rstacruz/nprogress                 |

#### 移动端技术

> 移动端开发任务待定

| 技术    | 说明             | 官网                                                  |
| ------- | ---------------- | ----------------------------------------------------- |
| Vue     | 核心前端框架     | [https://vuejs.org](https://vuejs.org/)               |
| Vuex    | 全局状态管理框架 | [https://vuex.vuejs.org](https://vuex.vuejs.org/)     |
| uni-app | 移动端前端框架   | [https://uniapp.dcloud.io](https://uniapp.dcloud.io/) |

## 架构图

##### 系统架构图

> 系统还在开发初始阶段，待定

##### 业务架构图

> 系统还在开发初始阶段，待定

## 模块介绍

> 系统还在开发初始阶段，待完善

## 开发进度

> 系统还在开发初始阶段，待完善

## 环境搭建

### 开发工具

| 工具         | 说明                | 官网                                                  |
| ------------ | ------------------- | ----------------------------------------------------- |
| IDEA         | 开发IDE             | https://www.jetbrains.com/idea/download               |
| RedisDesktop | redis客户端连接工具 | https://github.com/qishibo/AnotherRedisDesktopManager |
| X-shell      | Linux远程连接工具   | http://www.netsarang.com/download/software.html       |
| Navicat      | 数据库连接工具      | http://www.formysql.com/xiazai.html                   |
| Snipaste     | 屏幕截图工具        | https://www.snipaste.com/                             |
| Postman      | API接口调试工具     | https://www.postman.com/                              |
| Typora       | Markdown编辑器      | https://typora.io/                                    |

### 开发环境

| 工具  | 版本号 | 下载                                                         |
| ----- | ------ | ------------------------------------------------------------ |
| JDK   | 1.8    | https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html |
| MySQL | 5.7    | https://www.mysql.com/                                       |
| Redis | 7.0    | https://redis.io/download                                    |
| Nginx | 1.22   | http://nginx.org/en/download.html                            |

### 搭建步骤

> 环境搭建

- [centos搭建环境](https://note.tingnichui.com/#/notes/3_%E6%8A%80%E6%9C%AF%E5%88%86%E4%BA%AB/0_%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA/0_CentOS7)

> 服务部署 jar包部署

- `mvn clean install`执行打包
- `java -jar  web-api-0.0.1-SNAPSHOT.jar `运行服务
