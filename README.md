# Pedestal

Java 微服务基础架构平台，基于 Spring Boot 3.5 + Spring Cloud 2023 + Spring Cloud Alibaba 构建，提供开箱即用的微服务开发基础设施。

## 技术栈

| 类别 | 技术 | 版本 |
|------|------|------|
| 基础框架 | Spring Boot | 3.5.5 |
| 微服务框架 | Spring Cloud | 2023.0.6 |
| 注册/配置中心 | Spring Cloud Alibaba + Nacos | 2023.0.1.0 |
| ORM | MyBatis-Plus | 3.5.14 |
| 数据库 | MySQL | 9.4.0 |
| 缓存 | Redis (Redisson) | - |
| 消息队列 | RocketMQ | 5.3.3 |
| API 文档 | SpringDoc OpenAPI | 2.8.8 |
| 对象存储 | 阿里云 OSS / MinIO | - |
| 链路追踪 | Micrometer Tracing + Zipkin | - |
| 网关 | Spring Cloud Gateway | - |
| 构建工具 | Maven | - |
| JDK | Java 17 | 17+ |

## 项目结构

```
Pedestal
├── pedestal-core                    -- 核心依赖层
│   ├── anc-parent                   -- 全局依赖版本管理 (BOM)
│   ├── anc-common-model             -- 通用数据模型 (VO, DTO, Param)
│   ├── anc-common-util              -- 通用工具类 (HTTP, 加解密, 编解码)
│   ├── anc-framework-cache          -- 缓存框架封装 (Redis)
│   ├── anc-framework-config-center  -- 配置中心客户端 (Nacos Config)
│   ├── anc-framework-discovery      -- 服务发现客户端 (Nacos Discovery)
│   ├── anc-framework-feign          -- Feign 远程调用封装
│   ├── anc-framework-logger         -- 日志框架封装
│   ├── anc-framework-mongodb        -- MongoDB 封装
│   ├── anc-framework-mq             -- 消息队列封装 (RocketMQ)
│   ├── anc-framework-mybatis        -- MyBatis-Plus 数据访问封装
│   ├── anc-framework-springboot     -- Spring Boot 基础框架
│   ├── anc-framework-springboot-web -- Web 层封装 (Controller 基类, 拦截器)
│   ├── anc-framework-swagger        -- API 文档 (SpringDoc OpenAPI)
│   └── anc-framework-validation     -- 参数校验封装
│
├── pedestal-infrastructure          -- 基础设施服务
│   ├── pedestal-api-gateway         -- API 网关服务
│   ├── pedestal-config-center       -- 配置中心服务
│   ├── pedestal-discovery           -- 服务发现中心
│   ├── pedestal-inf-jobs-scheduler  -- 任务调度中心
│   ├── pedestal-inf-oss             -- 对象存储服务 (OSS + MinIO)
│   ├── pedestal-inf-push-center     -- 消息推送中心
│   ├── pedestal-inf-report-center   -- 数据上报中心
│   └── pedestal-tracing             -- 链路追踪服务
│
├── pedestal-micro-service           -- 微服务业务模块
│   ├── pedestal-ms-demo             -- 示例服务
│   ├── pedestal-ms-algorithm        -- 算法服务
│   ├── pedestal-ms-crawler          -- 爬虫服务
│   └── pedestal-ms-patterns         -- 设计模式示例
│
├── pedestal-share                   -- 共享模块
│   ├── share-api                    -- 共享 API 定义
│   └── share-component              -- 共享组件
│
├── pedestal-tool                    -- 开发工具
│   └── pedestal-tool-orm-generator  -- MyBatis-Plus 代码生成器
│
└── resource                         -- 资源文件
```

## 开发环境

| 工具 | 版本要求 | 说明 |
|------|---------|------|
| JDK | 17+ | 必须，项目使用 Java 17 |
| Maven | 3.8+ | 构建工具 |
| MySQL | 8.0+ | 数据库 |
| Redis | 6.0+ | 分布式缓存 |
| Nacos | 2.x+ | 注册中心 / 配置中心 |
| RocketMQ | 5.x+ | 消息中间件 |
| IDE | IntelliJ IDEA / VS Code | 推荐使用 IDEA |

### 快速开始

```bash
# 1. 克隆项目
git clone https://github.com/dumas-dz/Pedestal.git
cd Pedestal

# 2. 设置 JDK 17
export JAVA_HOME=/path/to/jdk-17

# 3. 编译项目
mvn compile

# 4. 安装到本地仓库
mvn install -DskipTests
```

## 模块依赖关系

```
anc-parent (BOM)
  └── pedestatl-core
        ├── anc-common-util
        ├── anc-common-model
        ├── anc-framework-springboot
        │     └── anc-framework-springboot-web
        ├── anc-framework-mybatis
        ├── anc-framework-feign
        ├── anc-framework-cache
        ├── anc-framework-mq
        ├── anc-framework-config-center
        ├── anc-framework-discovery
        ├── anc-framework-swagger
        ├── anc-framework-validation
        ├── anc-framework-logger
        └── anc-framework-mongodb
```

## 核心模块说明

### anc-common-util
通用工具类集合，包含：
- HTTP 客户端工具 (`HttpUtil`, `HTTPClientUtil`, `HttpsUtil`)
- 加解密工具 (`AESUtil`, `DESUtil`, `RSAUtil`, `HMACSHA256`)
- 编解码工具 (`CodecUtil`)
- 验证码生成 (`VerifyCodeUtils`)

### anc-common-model
通用数据模型定义，包含：
- 统一响应封装 `R<T>`
- 分页参数 `PageParam`
- 分页结果 `PageVO<T>`
- 列表结果 `ListVO<T>`

### anc-framework-swagger
基于 SpringDoc OpenAPI 的 API 文档自动生成，支持通过配置开关控制：
```yaml
swagger:
  doc:
    enabled: true
    title: 服务名称
    desc: 服务描述
```

访问地址：`http://localhost:{port}/swagger-ui.html`

## 规约

### 开发规范
- 遵循 [Google Java Style](https://google.github.io/styleguide/javaguide.html)
- 使用 Lombok 减少样板代码
- Controller 层不包含业务逻辑
- Service 层使用接口 + 实现
- 统一使用 `R<T>` 作为 API 响应封装

### Git 工作流
- 主分支：`main`
- 开发分支：`develop`
- 功能分支：`feature/{feature-name}`
- 修复分支：`fix/{bug-name}`
- 遵循 [Git Flow](https://www.git-tower.com/learn/git/ebook/cn/command-line/advanced-topics/git-flow/) 分支管理

### 提交规范
```
<type>: <description>

type: feat | fix | refactor | docs | test | chore | perf | ci
```

## License

Private Project - All Rights Reserved.
