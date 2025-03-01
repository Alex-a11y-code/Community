# 社区平台后端

这是一个仿稀土掘金克隆项目的后端，旨在为社区平台提供文章发布、评论管理、点赞管理、用户管理等功能。项目使用了 Spring Boot 框架，集成 MySQL 数据库，并通过 Swagger 提供了完整的 API 文档和交互功能。

## 项目概述

该项目是一个社区平台后端，支持用户的基本管理功能（如注册、登录、更新个人信息、更新头像等），文章的管理（包括发布、删除、查看、点赞、评论），以及文章的点赞、评论功能。

## 主要功能

### 用户管理
- **用户注册**：用户提交用户名、密码、头像等信息进行注册。
- **用户登录**：通过用户名和密码进行登录。
- **获取用户信息**：根据用户ID获取用户的详细信息。
- **更新用户信息**：用户可以更新其用户名、密码、头像等信息。
- **更新头像**：用户可以更新自己的头像。

### 文章管理
- **发布文章**：用户可以发布文章，填写标题和内容。
- **获取文章详情**：根据文章ID获取文章的详细信息。
- **获取热门文章**：根据浏览量降序查询热门文章，支持分页。
- **获取最新文章**：根据发布时间降序查询最新文章，支持分页。
- **删除文章**：删除指定ID的文章。

### 评论管理
- **添加评论**：用户可以对文章进行评论，支持父评论和子评论。
- **获取文章的所有父评论**：获取某篇文章的所有父评论。
- **获取父评论的子评论**：获取某个父评论下的所有子评论。

### 点赞管理
- **点赞文章**：用户可以对文章进行点赞。
- **取消点赞**：用户可以取消点赞。
- **获取文章点赞数**：查询某篇文章的点赞数。
- **获取用户已点赞的文章**：查询某个用户已点赞的所有文章。

## 技术栈

- **Spring Boot**：用于构建和启动后端应用。
- **Spring Data JPA**：用于数据库操作，简化数据访问层的代码。
- **MySQL**：关系型数据库，存储用户、文章、评论和点赞数据。
- **Swagger**：用于生成和展示 API 文档，方便前端与后端的交互。
- **Lombok**：用于减少样板代码，自动生成 getter、setter、构造函数等。


## 项目结构

```plaintext
src/
├── main/
│   ├── java/
│   │   └── org/example/community/
│   │       ├── controller/        # 控制层（处理请求和响应）
│   │       ├── entity/           # 实体类（数据库表映射）
│   │       ├── repository/       # 数据层（接口）
│   │       ├── service/          # 服务层（业务逻辑）
│   │       ├── serviceimpl/      # 服务层实现
│   │       ├── utils/            # 工具类
│   │       └── config/           # 配置文件（如Swagger、CORS配置）
│   └── resources/
│       └── application.properties # 配置文件（数据库连接、端口等）
└── test/                         # 测试文件
