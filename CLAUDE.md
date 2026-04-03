# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 在此代码仓库中工作时提供指导。

## 构建和运行命令

```bash
# 构建项目
mvn clean package

# 运行测试
mvn test

# 运行单个测试类
mvn test -Dtest=EmployeeControllerTest

# 运行应用程序（默认端口为 8888，见 application-dev.yml）
mvn spring-boot:run
```

## 架构

这是一个使用 **领域驱动设计（DDD）** 架构的 Spring Boot 2.7.18 应用。

### 包结构

```
com.example.demo
├── api/controller/          # REST 控制器
├── application/
│   ├── configuration/       # Spring 配置、事件、监听器
│   └── service/             # 应用服务及其实现
├── domain/
│   ├── entity/              # 领域实体（Book、EmployeeVo 等）
│   ├── repository/dao/       # MyBatis mapper 接口
│   └── service/             # 领域服务
└── infrastructure/
    ├── entity/              # ResultVo、Result（API 响应包装类）
    ├── enums/                # ErrorEnum 错误码定义
    ├── exception/            # DemoException 异常类
    └── utils/                # ResultVoUtils、RestTemplateUtils 工具类
```

### 关键模式

- **统一 API 响应**：`ResultVo<T>` 包装所有 API 响应，包含 `code`、`msg` 和 `result`（含 `total` 和 `data` 列表）
- **异常处理**：`DemoException` + `ErrorEnum` 实现类型化错误；`GlobalExceptionHandler`（`@RestControllerAdvice`）统一捕获异常
- **数据库**：MyBatis + XML mapper，mapper 文件位于 `src/main/resources/mapper/`
- **测试**：使用 `@SpringBootTest` + `MockMvc` 进行控制器测试；使用 `ArchUnit` 进行架构验证

### 多环境配置

通过 `application-{profile}.yml` 配置 Spring profiles：
- `dev`（application.yml 中默认）- 端口 8888
- `test`
- `prod`

### 技术版本

- Spring Boot：2.7.18
- JDK：1.8
- MySQL：8.4.0
- MyBatis：2.3.2

### 依赖

- Spring Boot Web、Test
- MyBatis Spring Boot Starter
- MySQL Connector
- Spring Data Redis
- Lombok
- ArchUnit（test 作用域）
