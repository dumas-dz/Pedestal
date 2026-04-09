# Pedestal

> Java 微服务基础架构平台 — 基于 Spring Boot 3.5 / Spring Cloud 2023 / Spring Cloud Alibaba 的企业级微服务脚手架

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](LICENSE)
[![Java](https://img.shields.io/badge/JDK-17-green.svg)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Security](https://img.shields.io/badge/Dependabot-0%20alerts-success.svg)](https://github.com/dumas-dz/Pedestal/security)

## 项目简介

Pedestal 是一套企业级 Java 微服务基础架构平台，采用分层模块化设计，提供微服务架构中常用的公共能力封装，包括加解密、缓存、数据库访问、消息队列、API 文档、对象存储、链路追踪、API 网关等。各模块可独立引入，按需组合。

## 技术栈

| 类别 | 技术 | 版本 |
|------|------|------|
| 基础框架 | Spring Boot | 3.5.5 |
| 微服务 | Spring Cloud | 2023.0.6 |
| 微服务(阿里) | Spring Cloud Alibaba | 2023.0.1.0 |
| 注册/配置中心 | Nacos | 3.0.3 |
| ORM | MyBatis-Plus | 3.5.14 |
| 数据库 | MySQL | 9.4.0 |
| 连接池 | Druid | 1.2.27 |
| 缓存 | Redis (Spring Data + Redisson) | - |
| 消息队列 | RocketMQ | 5.3.3 |
| 对象存储 | 阿里云 OSS / MinIO | 3.18.3 / 8.6.0 |
| API 文档 | SpringDoc OpenAPI | 2.8.8 |
| 链路追踪 | Micrometer Tracing + Zipkin | - |
| API 网关 | Spring Cloud Gateway | - |
| 序列化 | Jackson / Protobuf | 2.18.3 / 4.32.0 |
| 工具库 | Hutool / Guava / Apache Commons | 5.8.36 / 33.4.8 / 多版本 |
| JDK | Java 17 | - |

## 模块架构

```
Pedestal
├── pedestal-core                         # 核心依赖层
│   ├── anc-parent                        # 依赖管理（根 POM）
│   ├── anc-common-model                  # 通用数据模型
│   ├── anc-common-util                   # 通用工具集
│   ├── anc-framework-cache               # Redis 缓存 + 分布式锁
│   ├── anc-framework-config-center       # 配置中心基础封装
│   ├── anc-framework-discovery           # 注册中心基础封装
│   ├── anc-framework-feign               # Feign 远程调用封装
│   ├── anc-framework-logger              # 业务日志框架
│   ├── anc-framework-mongodb             # MongoDB 工具封装
│   ├── anc-framework-mq                  # 消息队列框架封装
│   ├── anc-framework-mybatis             # MyBatis-Plus + AES 加密
│   ├── anc-framework-springboot          # SpringBoot 核心工具
│   ├── anc-framework-springboot-web      # Web 层（文件上传/日志/版本控制）
│   ├── anc-framework-swagger             # SpringDoc OpenAPI 文档
│   └── anc-framework-validation          # 自定义校验注解
│
├── pedestal-infrastructure               # 基础设施服务
│   ├── pedestal-api-gateway              # API 网关服务
│   ├── pedestal-config-center            # 配置中心服务
│   ├── pedestal-discovery                # 注册中心服务
│   ├── pedestal-inf-jobs-scheduler       # 分布式任务调度
│   ├── pedestal-inf-oss                  # 对象存储服务（OSS + MinIO）
│   ├── pedestal-inf-push-center          # 消息推送中心
│   ├── pedestal-inf-report-center        # 数据上报中心
│   └── pedestal-tracing                  # 链路追踪服务
│
├── pedestal-micro-service                # 微服务业务模块
│   ├── pedestal-ms-demo                  # 示例服务
│   ├── pedestal-ms-algorithm             # 算法练习
│   ├── pedestal-ms-crawler               # 爬虫服务
│   └── pedestal-ms-patterns              # 设计模式示例
│
├── pedestal-share                        # 共享模块
│   ├── share-api                         # 共享 API 定义
│   └── share-component                   # 共享组件
│
├── pedestal-tool                         # 开发工具
│   └── pedestal-tool-orm-generator       # MyBatis-Plus 代码生成器
│
└── resource                              # 资源文件
```

## 模块详细说明

### anc-parent

根 POM 模块，统一管理所有子模块和第三方依赖的版本号。所有业务项目继承此 POM 即可自动获得一致的依赖版本。

### anc-common-model

通用数据模型，定义了 API 响应结构、分页参数和异常体系。

| 类 | 说明 |
|----|------|
| `R<T>` | API 统一响应包装（code / msg / success / data） |
| `Ok` / `Fail` | 成功/失败响应快捷构造 |
| `PageVO<T>` | 分页结果包装（pageNo / pageSize / total / list） |
| `ListVO<T>` | 列表结果包装（total / list） |
| `PageParam` | 分页参数（pageNo / pageSize，默认从 1 开始） |
| `CascaderVO` | 级联下拉选项 |
| `ServiceException` | 业务异常基类 |
| `CommonResponseCode` | 通用响应码枚举 |

### anc-common-util

通用工具集，提供加解密、JSON、HTTP、文件、反射、日期等基础能力。

| 工具类 | 说明 |
|--------|------|
| `AESUtil` | AES 加解密（GCM 推荐 / ECB 兼容） |
| `RSAUtil` | RSA 非对称加解密（2048-bit + OAEP） |
| `MD5withRSAUtil` | MD5 with RSA 数字签名 |
| `HMACSHA256` | HMAC-SHA256 哈希 |
| `SignUtil` | 签名生成与验证 |
| `Base64Util` | Base64 编解码 |
| `Md5Utils` | MD5 哈希 |
| `DESUtil` | DES 加解密（已废弃，请使用 AESUtil） |
| `CodecUtil` | 编解码工具（已废弃，请使用 AESUtil） |
| `JacksonUtil` | JSON 序列化/反序列化 |
| `HttpUtil` / `HTTPClientUtil` / `HttpsUtil` | HTTP/HTTPS 请求工具 |
| `FileUtil` | 文件读写、删除、大小格式化 |
| `BeanUtil` | 对象拷贝（浅拷贝/深拷贝） |
| `ReflectUtils` | 反射工具（getter/setter/私有字段访问） |
| `DateUtil` / `TimeUtil` / `DateFormatUtil` | 日期时间工具 |
| `IdUtils` | ID 生成工具 |
| `SqlUtil` | SQL 注入防护（order by 参数校验） |
| `EscapeUtil` | HTML 转义/反转义/XSS 清理 |
| `VerifyCodeUtils` | 图形验证码生成 |

### anc-framework-cache

Redis 缓存框架封装，支持多种数据结构和分布式锁。

| 类 | 说明 |
|----|------|
| `RedisConfig` | Redis 连接配置 |
| `RedisCache` | 统一 Redis 操作入口 |
| `ValueRedisCache` | String 类型缓存操作 |
| `HashRedisCache` | Hash 类型缓存操作 |
| `ListRedisCache` | List 类型缓存操作 |
| `SetRedisCache` | Set 类型缓存操作 |
| `ZSetRedisCache` | Sorted Set 类型缓存操作 |
| `@RedisLock` | 分布式锁注解（基于 AOP） |
| `RedisLockHelper` | 分布式锁工具类 |

### anc-framework-mybatis

MyBatis-Plus 数据库访问层封装，集成字段加密、分页和自动填充。

| 类 | 说明 |
|----|------|
| `MyBatisPlusConfig` | MyBatis-Plus 配置（分页插件、乐观锁） |
| `AES` | AES 字段加密组件（密钥配置化） |
| `AESEncryptHandler` | MyBatis 字段加密 TypeHandler |
| `BaseEntity` | 基础实体（id / createTime / updateTime / createBy / delFlag） |
| `AutoFillConfiguration` | 自动填充配置（创建人/更新人） |
| `PageUtil` | 分页工具 |
| `DbGuardConfigFilter` | 数据库密码解密过滤器 |

### anc-framework-springboot-web

Web 层基础封装，提供文件上传、操作日志、API 版本控制。

| 类 | 说明 |
|----|------|
| `FileUploadUtils` | 文件上传工具（大小/类型/路径遍历校验） |
| `@Log` | 操作日志自定义注解 |
| `OperationLog` | 操作日志模型 |
| `BusinessType` | 业务操作类型枚举（查询/新增/修改/删除/导出/导入/授权） |
| `@ApiVersion` | API 版本控制注解 |

### anc-framework-springboot

SpringBoot 核心工具。

| 类 | 说明 |
|----|------|
| `SpringContextHolder` | Spring 上下文持有者（静态获取 Bean） |
| `AsynWorkPoolConfiguration` | 异步线程池配置 |

### anc-framework-swagger

基于 SpringDoc OpenAPI 的 API 文档自动生成。

| 类 | 说明 |
|----|------|
| `SwaggerConfig` | OpenAPI 配置（自动扫描 Controller 生成文档） |

支持通过配置属性自定义标题和描述，访问地址：`http://localhost:{port}/swagger-ui.html`

### anc-framework-validation

自定义 Bean Validation 注解（如 `@PhoneNumber`、`@IPAddress` 等）。

### anc-framework-mongodb

MongoDB 查询工具。

| 类 | 说明 |
|----|------|
| `MongoLikeUtil` | 模糊查询工具（左模糊/右模糊/全模糊/精确匹配） |

### anc-framework-mq

RocketMQ 消息队列框架封装。

### anc-framework-feign

Feign 远程调用基础封装。

### anc-framework-config-center / anc-framework-discovery

Nacos 配置中心 / 注册中心客户端基础封装。

### pedestal-infrastructure

基础设施服务模块，包含可独立部署的微服务应用：

| 服务 | 说明 |
|------|------|
| `pedestal-api-gateway` | API 网关（Spring Cloud Gateway + JWT 认证） |
| `pedestal-config-center` | 配置中心服务 |
| `pedestal-discovery` | 注册中心服务 |
| `pedestal-inf-jobs-scheduler` | 分布式任务调度 |
| `pedestal-inf-oss` | 对象存储服务（阿里云 OSS + MinIO 双存储） |
| `pedestal-inf-push-center` | 消息推送中心 |
| `pedestal-inf-report-center` | 数据上报中心 |
| `pedestal-tracing` | 链路追踪服务（Micrometer Tracing + Zipkin） |

### pedestal-micro-service

微服务业务模块示例：

| 服务 | 说明 |
|------|------|
| `pedestal-ms-demo` | 示例微服务（Hello World + 操作日志） |
| `pedestal-ms-algorithm` | 算法练习（排序/搜索/栈队列等） |
| `pedestal-ms-crawler` | 爬虫服务（基于 WebMagic 的知乎爬虫） |
| `pedestal-ms-patterns` | 常用设计模式示例 |

### pedestal-tool-orm-generator

MyBatis-Plus 代码生成器，快速生成 Entity / Mapper / Service / Controller 脚手架代码。

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis 6.0+
- Nacos 2.x+
- RocketMQ 5.x+（可选）

### 编译安装

```bash
# 克隆项目
git clone https://github.com/dumas-dz/Pedestal.git
cd Pedestal

# 设置 JDK 17
export JAVA_HOME=/path/to/jdk-17

# 编译
mvn compile

# 安装到本地仓库
mvn install -DskipTests
```

### 引入依赖

在业务项目 `pom.xml` 中继承父 POM：

```xml
<parent>
    <groupId>com.dumas.anc</groupId>
    <artifactId>anc-parent</artifactId>
    <version>2.0.0-SNAPSHOT</version>
</parent>
```

按需引入模块：

```xml
<!-- Web 项目（含 SpringBoot 核心 + 文件上传 + 日志） -->
<dependency>
    <groupId>com.dumas.anc</groupId>
    <artifactId>anc-framework-springboot-web</artifactId>
</dependency>

<!-- 数据库访问（MyBatis-Plus + 分页 + AES 加密） -->
<dependency>
    <groupId>com.dumas.anc</groupId>
    <artifactId>anc-framework-mybatis</artifactId>
</dependency>

<!-- Redis 缓存 + 分布式锁 -->
<dependency>
    <groupId>com.dumas.anc</groupId>
    <artifactId>anc-framework-cache</artifactId>
</dependency>

<!-- SpringDoc OpenAPI 文档 -->
<dependency>
    <groupId>com.dumas.anc</groupId>
    <artifactId>anc-framework-swagger</artifactId>
</dependency>

<!-- 通用工具集 -->
<dependency>
    <groupId>com.dumas.anc</groupId>
    <artifactId>anc-common-util</artifactId>
</dependency>
```

## 配置参考

### AES 字段加密

使用 MyBatis AES 字段加密时，**必须**在配置文件中指定密钥：

```properties
# 启用 AES 加密
aes.encrypt.enabled=true
# 密钥（32 位十六进制字符 = 128 bit）
aes.mobile.secret=your-32-char-hex-key-here
```

在实体类字段上使用：

```java
@TableField(typeHandler = AESEncryptHandler.class)
private String phone;
```

### SpringDoc API 文档

```yaml
swagger:
  doc:
    enabled: true
    title: 我的 API 文档
    desc: 项目接口说明
```

访问地址：`http://localhost:{port}/swagger-ui.html`

### 分布式锁

```java
@RedisLock(key = "order:#{#orderId}", expire = 30)
public void processOrder(Long orderId) {
    // 业务逻辑
}
```

### 统一响应封装

```java
// 成功响应
return Ok.of(userVO);

// 失败响应
return Fail.of("用户不存在");

// 分页响应
PageVO<UserVO> page = PageVO.ofPage(total, list, pageNo, pageSize);
```

## 安全说明

### 加密工具对照

| 工具类 | 算法 | 推荐场景 |
|--------|------|----------|
| `AESUtil` | AES/GCM/NoPadding | 字符串/字节流加解密（推荐） |
| `RSAUtil` | RSA 2048-bit + OAEP | 非对称加解密、数字签名 |
| `HMACSHA256` | HMAC-SHA256 | 消息认证码 |
| `DESUtil` | DES/ECB | **已废弃** — 请使用 AESUtil |
| `CodecUtil` | DES/ECB + MD5 | **已废弃** — 请使用 AESUtil |

### 安全防护措施

| 防护项 | 实现 |
|--------|------|
| SQL 注入 | `SqlUtil` — order by 参数正则白名单校验 |
| XSS 攻击 | `EscapeUtil` — HTML 特殊字符转义 |
| 路径遍历 | `FileUploadUtils` — 文件名清洗 |
| 文件上传 | 大小限制、扩展名白名单校验 |
| 分布式锁 | `@RedisLock` — Redis 分布式锁注解 |
| 数据库密码 | `DbGuardConfigFilter` — 配置文件密码加密 |

## 开发规约

### Git 工作流

| 分支 | 说明 |
|------|------|
| `main` | 主分支，稳定发布版本 |
| `develop` | 开发分支，日常集成 |
| `feature/*` | 功能分支 |
| `fix/*` | 修复分支 |

### 提交规范

```
<type>: <description>

type: feat | fix | refactor | docs | test | chore | perf | ci
```

参考：[Git Flow](https://www.git-tower.com/learn/git/ebook/cn/command-line/advanced-topics/git-flow/)

## 许可证

[Apache License 2.0](LICENSE)
