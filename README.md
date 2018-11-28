# Spring Boot 学习笔记

## 1. HelloWolrd

从 [Spring Initializr](https://start.spring.io/) 官网生成项目包，选择 web模块。

然后，新建 `HelloController`

```
@RestController
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }
}
```

在 `DemoApplication` 类中，运行程序：`Run DemoApplication.main()`，在浏览器地址栏输入：`http://localhost:8080/hello` ，就可以看到结果：`Hello World!`。

## 2. 日志 logback.xml 配置

参考：[logback的使用和logback.xml详解](https://www.cnblogs.com/warking/p/5710303.html)

日志输出级别：
根据Level的级别，优先级大的优先输出，优先级从大到小为：
`ERROR>WARN>INFO>DEBUG>TRACE`

logback.xml的例子：

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">

    <property name="LOG_HOME" value="/Users/youngbear/logs" />
    <property name="PROJECT_NAME" value="springbootdemo" />
    <!-- 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
    </appender>
    <!-- 输出到文件 -->
    <appender name="FILE"  class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!-- file 标签用来表示当前日志的文件，如果没有改标签的话，则使用FileNamePattern中的配置 -->
        <file>${LOG_HOME}/${PROJECT_NAME}/springbootdemo.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!--日志文件输出的文件名-->
            <FileNamePattern>${LOG_HOME}/${PROJECT_NAME}/springbootdemo.%d{yyyy-MM-dd}.log</FileNamePattern>
            <!--日志文件保留天数-->
            <MaxHistory>15</MaxHistory>
        </rollingPolicy>
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
        </encoder>
        <!--日志文件最大的大小-->
        <triggeringPolicy class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>50MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <!-- 日志输出级别 -->
    <root level="INFO">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE" />
    </root>
</configuration>
```

访问时，日志输出为：

```
2018-11-28 23:14:50.237 [http-nio-8080-exec-1] INFO  com.example.demo.controller.HelloController - info Hello
2018-11-28 23:14:50.246 [http-nio-8080-exec-1] WARN  com.example.demo.controller.HelloController - warn Hello
2018-11-28 23:14:50.247 [http-nio-8080-exec-1] ERROR com.example.demo.controller.HelloController - error Hello
```

## [Demo GitHub地址](https://github.com/YoungBear/SpringBootDemo)