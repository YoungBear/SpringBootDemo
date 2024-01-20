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



```
%d{yyyy-MM-dd'T'HH:mm:ss.SSS zXXX} 表示显示带时区的时间格式，如：
022-02-25T22:18:54.373 CST+08:00 [main] INFO  com.example.demo.DemoApplication:61 - xxx
```



logback-spring.xml的例子：

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration>

    <!-- springboot 提供的显示颜色的工具 -->
    <conversionRule conversionWord="clr" converterClass="org.springframework.boot.logging.logback.ColorConverter" />

    <property name="LOG_HOME" value="/Users/youngbear/logs" />
    <property name="PROJECT_NAME" value="springbootdemo" />
    <!-- 控制台输出 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%L表示打印日志所在文件的行数，%n是换行符-->
            <pattern>%d{yyyy-MM-dd'T'HH:mm:ss.SSS zXXX} [%thread] %-5level %logger{50}:%L - %msg%n</pattern>
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
            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%L表示打印日志所在文件的行数，%n是换行符-->
            <pattern>%d{yyyy-MM-dd'T'HH:mm:ss.SSS zXXX} [%thread] %-5level %logger{50}:%L - %msg%n</pattern>
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
2022-02-25T22:32:49.434 CST+08:00 [http-nio-8888-exec-1] INFO  com.example.demo.service.impl.HelloServiceImpl:27 - info Hello null
2022-02-25T22:32:49.434 CST+08:00 [http-nio-8888-exec-1] WARN  com.example.demo.service.impl.HelloServiceImpl:28 - warn Hello null
2022-02-25T22:32:49.435 CST+08:00 [http-nio-8888-exec-1] ERROR com.example.demo.service.impl.HelloServiceImpl:29 - error Hello null
```

## 3. 返回 Json 串

使用 `@RestController`:

```
@RestController
@RequestMapping("/v1/book")
public class BookController {

    @RequestMapping(value = "/books", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public List<Book> test() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setName("数学之美");
        b1.setPublisher("人民邮电出版社");
        b1.setAuther("吴军");
        Book b2 = new Book();
        b2.setName("重构 改善既有代码的设计");
        b2.setPublisher("人民邮电出版社");
        b2.setAuther("Martin Fowler");
        Book b3 = new Book();
        b3.setName("机器学习实战");
        b3.setPublisher("人民邮电出版社");
        b3.setAuther("Peter Harrington");
        Book b4 = new Book();
        b4.setName("Effective Java中文版");
        b4.setPublisher("机械工业出版社");
        b4.setAuther("Joshua Bloch");
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        return books;
    }

}
```

使用 curl 访问：

```
192:SpringBootDemo youngbear$ curl http://localhost:8080/v1/book/books -X POST
[{"name":"数学之美","publisher":"人民邮电出版社","author":"吴军"},{"name":"重构 改善既有代码的设计","publisher":"人民邮电出版社","author":"Martin Fowler"},{"name":"机器学习实战","publisher":"人民邮电出版社","author":"Peter Harrington"},{"name":"Effective Java中文版","publisher":"机械工业出版社","author":"Joshua Bloch"}]
```



## 4. 使用 Tomcat 部署

### 4.1 设置打包为 war

`<packaging>war</packaging>`

### 4.2 设置 war 包的名称

`<finalName>Demo</finalName>`



详细配置如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>demo</name>
    <description>Demo project for Spring Boot</description>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.0.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
        <finalName>Demo</finalName>
    </build>


</project>

```

### 4.3 配置 Application

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoApplication.class);
    }
}
```

### 4.4 配置 tomcat (可选)

配置端口：

tomcat 默认端口为8080，如果需要更改，则在tomcat安装目录/conf/server.xml中，更改 `<Connector` 标签的 port 属性即可。如下，我们将tomcat端口改为9090：



```xml
<?xml version='1.0' encoding='utf-8'?>
<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<!-- Note:  A "Server" is not itself a "Container", so you may not
     define subcomponents such as "Valves" at this level.
     Documentation at /docs/config/server.html
 -->
<Server port="8005" shutdown="SHUTDOWN">
  <Listener className="org.apache.catalina.startup.VersionLoggerListener" />
  <!-- Security listener. Documentation at /docs/config/listeners.html
  <Listener className="org.apache.catalina.security.SecurityListener" />
  -->
  <!--APR library loader. Documentation at /docs/apr.html -->
  <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
  <!-- Prevent memory leaks due to use of particular java/javax APIs-->
  <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
  <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
  <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />

  <!-- Global JNDI resources
       Documentation at /docs/jndi-resources-howto.html
  -->
  <GlobalNamingResources>
    <!-- Editable user database that can also be used by
         UserDatabaseRealm to authenticate users
    -->
    <Resource name="UserDatabase" auth="Container"
              type="org.apache.catalina.UserDatabase"
              description="User database that can be updated and saved"
              factory="org.apache.catalina.users.MemoryUserDatabaseFactory"
              pathname="conf/tomcat-users.xml" />
  </GlobalNamingResources>

  <!-- A "Service" is a collection of one or more "Connectors" that share
       a single "Container" Note:  A "Service" is not itself a "Container",
       so you may not define subcomponents such as "Valves" at this level.
       Documentation at /docs/config/service.html
   -->
  <Service name="Catalina">

    <!--The connectors can use a shared executor, you can define one or more named thread pools-->
    <!--
    <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
        maxThreads="150" minSpareThreads="4"/>
    -->


    <!-- A "Connector" represents an endpoint by which requests are received
         and responses are returned. Documentation at :
         Java HTTP Connector: /docs/config/http.html (blocking & non-blocking)
         Java AJP  Connector: /docs/config/ajp.html
         APR (HTTP/AJP) Connector: /docs/apr.html
         Define a non-SSL/TLS HTTP/1.1 Connector on port 8080
    -->
    <Connector port="9090" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
    <!-- A "Connector" using the shared thread pool-->
    <!--
    <Connector executor="tomcatThreadPool"
               port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
    -->
    <!-- Define a SSL/TLS HTTP/1.1 Connector on port 8443
         This connector uses the NIO implementation that requires the JSSE
         style configuration. When using the APR/native implementation, the
         OpenSSL style configuration is required as described in the APR/native
         documentation -->
    <!--
    <Connector port="8443" protocol="org.apache.coyote.http11.Http11NioProtocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" />
    -->

    <!-- Define an AJP 1.3 Connector on port 8009 -->
    <Connector port="8009" protocol="AJP/1.3" redirectPort="8443" />


    <!-- An Engine represents the entry point (within Catalina) that processes
         every request.  The Engine implementation for Tomcat stand alone
         analyzes the HTTP headers included with the request, and passes them
         on to the appropriate Host (virtual host).
         Documentation at /docs/config/engine.html -->

    <!-- You should set jvmRoute to support load-balancing via AJP ie :
    <Engine name="Catalina" defaultHost="localhost" jvmRoute="jvm1">
    -->
    <Engine name="Catalina" defaultHost="localhost">

      <!--For clustering, please take a look at documentation at:
          /docs/cluster-howto.html  (simple how to)
          /docs/config/cluster.html (reference documentation) -->
      <!--
      <Cluster className="org.apache.catalina.ha.tcp.SimpleTcpCluster"/>
      -->

      <!-- Use the LockOutRealm to prevent attempts to guess user passwords
           via a brute-force attack -->
      <Realm className="org.apache.catalina.realm.LockOutRealm">
        <!-- This Realm uses the UserDatabase configured in the global JNDI
             resources under the key "UserDatabase".  Any edits
             that are performed against this UserDatabase are immediately
             available for use by the Realm.  -->
        <Realm className="org.apache.catalina.realm.UserDatabaseRealm"
               resourceName="UserDatabase"/>
      </Realm>

      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

        <!-- SingleSignOn valve, share authentication between web applications
             Documentation at: /docs/config/valve.html -->
        <!--
        <Valve className="org.apache.catalina.authenticator.SingleSignOn" />
        -->

        <!-- Access log processes all example.
             Documentation at: /docs/config/valve.html
             Note: The pattern used is equivalent to using pattern="common" -->
        <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs"
               prefix="localhost_access_log" suffix=".txt"
               pattern="%h %l %u %t &quot;%r&quot; %s %b" />

      </Host>
    </Engine>
  </Service>
</Server>

```

### 4.5 构建包

`mvn package`

### 4.6 部署

将 war 包拷贝到tomcat的 `/webapps/` 目录下，重启tomcat：

```shell
# 拷贝 war 包
192:apache-tomcat-8.0.46 youngbear$ pwd
/Users/youngbear/setup/apache-tomcat-8.0.46
192:apache-tomcat-8.0.46 youngbear$ cp ~/IdeaProjects/SpringBootDemo/target/Demo.war webapps/
# 重启 tomcat
192:apache-tomcat-8.0.46 youngbear$ cd bin/
192:bin youngbear$ ./shutdown.sh
Using CATALINA_BASE:   /Users/youngbear/setup/apache-tomcat-8.0.46
Using CATALINA_HOME:   /Users/youngbear/setup/apache-tomcat-8.0.46
Using CATALINA_TMPDIR: /Users/youngbear/setup/apache-tomcat-8.0.46/temp
Using JRE_HOME:        /Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home
Using CLASSPATH:       /Users/youngbear/setup/apache-tomcat-8.0.46/bin/bootstrap.jar:/Users/youngbear/setup/apache-tomcat-8.0.46/bin/tomcat-juli.jar

192:bin youngbear$ ./startup.sh
Using CATALINA_BASE:   /Users/youngbear/setup/apache-tomcat-8.0.46
Using CATALINA_HOME:   /Users/youngbear/setup/apache-tomcat-8.0.46
Using CATALINA_TMPDIR: /Users/youngbear/setup/apache-tomcat-8.0.46/temp
Using JRE_HOME:        /Library/Java/JavaVirtualMachines/jdk1.8.0_73.jdk/Contents/Home
Using CLASSPATH:       /Users/youngbear/setup/apache-tomcat-8.0.46/bin/bootstrap.jar:/Users/youngbear/setup/apache-tomcat-8.0.46/bin/tomcat-juli.jar
Tomcat started.
192:bin youngbear$

```

### 4.7 访问接口

**注意：**

使用 `tomcat` 的部署方式，在访问接口时，url 需要加上上下文，即 War 包的名称，部署成功时，/webapps/下会生成一个与 war 包同名的目录，访问时需要带上，如：

```shell
# 使用 SpringBoot 的jar包启动时的访问
192:SpringBootDemo youngbear$ curl http://localhost:8080/hello -X GET
Hello World!
# 使用 tomcat 部署时的访问
192:SpringBootDemo youngbear$ curl http://localhost:9090/Demo/hello -X GET
Hello World!
```



## 5. 返回统一的json格式

### 5.1 创建返回对象泛型类

统一对象类： `ResultVo.java`

```java
import lombok.Data;

@Data
public class ResultVo<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 数据
     */
    private Result<T> result;
}

```

其中，`Result`的定义为：

`Result.java`

```java
import lombok.Data;
import java.util.List;
@Data
public class Result<T> {
    /**
     * 数据总数
     */
    private Integer total;
    /**
     * 当前页数据
     */
    private List<T> data;
}

```

### 5.2 创建异常类

错误定义枚举：`ErrorEnum.java`

```java
package com.example.demo.enums;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:35
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 错误定义：错误码及错误信息
 */
public enum ErrorEnum {

    UNKNOWN_ERROR(-1, "unknown error."),
    HTTP_REQUEST_ERROR(9999, "http request error"),
    BOOK_NAME_NULL_ERROR(10001, "book name is null."),
    HELLO_NAME_NULL_ERROR(20001, "hi name is null.");


    final Integer errorCode;
    final String errorMessage;

    ErrorEnum(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

```

异常类：`DemoException.java`

```java
package com.example.demo.exception;

import com.example.demo.enums.ErrorEnum;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:31
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 统一异常
 */
public class DemoException extends RuntimeException {

    private final ErrorEnum errorEnum;

    public DemoException(ErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public DemoException(Throwable cause, ErrorEnum errorEnum) {
        super(cause);
        this.errorEnum = errorEnum;
    }

    public ErrorEnum getErrorEnum() {
        return errorEnum;
    }
}

```

### 5.3 创建工具类

创建工具类 `ResultUtils.java` ，进行封装返回成功信息，异常信息。

```java
package com.example.demo.utils;

import com.example.demo.entity.common.Result;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.DemoException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 22:19
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public class ResultVoUtils {

    /**
     * 成功返回
     *
     * @param data 返回数据
     * @param <T>  数据类型
     * @return 统一的返回值
     */
    public static <T> ResultVo<T> success(T data) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(Result.SUCCESS_CODE);
        resultVo.setMsg(Result.SUCCESS_MESSAGE);
        Result<T> result = new Result<>();
        if (data != null) {
            List<T> dataList = new ArrayList<>(1);
            dataList.add(data);
            result.setTotal(1);
            result.setData(dataList);
        } else {
            result.setTotal(0);
        }
        resultVo.setResult(result);
        return resultVo;
    }

    public static <T> ResultVo<T> success(List<T> dataList) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(Result.SUCCESS_CODE);
        resultVo.setMsg(Result.SUCCESS_MESSAGE);
        Result<T> result = new Result<>();
        if (dataList != null && dataList.size() > 0) {
            result.setTotal(dataList.size());
            result.setData(dataList);
        } else {
            result.setTotal(0);
        }
        resultVo.setResult(result);
        return resultVo;
    }

    /**
     * 指定total，返回数据，用于分页场景
     *
     * @param total    总数量
     * @param dataList 当前数据
     * @param <T>      泛型参数
     * @return resultVo
     */
    public static <T> ResultVo<T> success(int total, List<T> dataList) {
        ResultVo<T> resultVo = success(dataList);
        resultVo.getResult().setTotal(total);
        return resultVo;
    }

    /**
     * 异常返回
     *
     * @param demoException
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(DemoException demoException) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(demoException.getErrorEnum().getErrorCode());
        resultVo.setMsg(demoException.getErrorEnum().getErrorMessage());
        return resultVo;
    }

    /**
     * 异常返回
     *
     * @param errorEnum
     * @param <T>
     * @return
     */
    public static <T> ResultVo<T> error(ErrorEnum errorEnum) {
        ResultVo<T> resultVo = new ResultVo<>();
        resultVo.setCode(errorEnum.getErrorCode());
        resultVo.setMsg(errorEnum.getErrorMessage());
        return resultVo;
    }
}

```

### 5.4 实践

以 `BookController` 为例，进行正常返回对象，正常返回数组，异常返回。

```java
package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IBookService;
import com.example.demo.utils.ResultVoUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2018/12/10 23:07
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@RequestMapping(value = "/v1/book", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BookController {

    @Autowired
    private IBookService bookService;

    @RequestMapping(value = "/book-list", method = RequestMethod.POST)
    public ResultVo<Book> bookList() {
        try {
            List<Book> books = bookService.bookList();
            return ResultVoUtils.success(books);
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }

    }

    @RequestMapping(value = "one-book", method = RequestMethod.POST)
    public ResultVo<Book> oneBook(@RequestBody Book book) {
        try {
            Book book1 = bookService.oneBook(book.getName(), book.getAuther(), book.getPublisher());
            return ResultVoUtils.success(book1);
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

}
```

**对应`IService`文件：`IBookService.java`**

```java
package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 23:01
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public interface IBookService {

    /**
     * 返回测试列表
     * @return
     */
    List<Book> bookList();

    /**
     * 构造一个书的对象
     * @param name
     * @param author
     * @param publisher
     * @return
     */
    Book oneBook(String name, String author, String publisher);

}

```

**Service实现类：`BookServiceImpl.java`**

```java
package com.example.demo.service.impl;

import com.example.demo.entity.Book;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.enums.ErrorEnum.BOOK_NAME_NULL_ERROR;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-04-30 23:05
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Service
public class BookServiceImpl implements IBookService {
    @Override
    public List<Book> bookList() {
        List<Book> books = new ArrayList<>();
        Book b1 = new Book();
        b1.setName("数学之美");
        b1.setPublisher("人民邮电出版社");
        b1.setAuther("吴军");
        Book b2 = new Book();
        b2.setName("重构 改善既有代码的设计");
        b2.setPublisher("人民邮电出版社");
        b2.setAuther("Martin Fowler");
        Book b3 = new Book();
        b3.setName("机器学习实战");
        b3.setPublisher("人民邮电出版社");
        b3.setAuther("Peter Harrington");
        Book b4 = new Book();
        b4.setName("Effective Java中文版");
        b4.setPublisher("机械工业出版社");
        b4.setAuther("Joshua Bloch");
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        return books;
    }

    @Override
    public Book oneBook(String name, String author, String publisher) {
        if (null == name) {
            throw new DemoException(BOOK_NAME_NULL_ERROR);
        }
        Book book = new Book();
        book.setName(name);
        book.setAuther(author);
        book.setPublisher(publisher);
        return book;
    }
}

```



#### 1. 获取列表

**请求：**

```shell
curl -X POST "http://localhost:8080/v1/book/book-list" -H  "accept: application/json;charset=UTF-8"
```

**对应返回值：**

```json
{
	"code": 0,
	"msg": "request successful.",
	"result": {
		"total": 4,
		"data": [{
			"name": "数学之美",
			"publisher": "人民邮电出版社",
			"author": "吴军"
		}, {
			"name": "重构 改善既有代码的设计",
			"publisher": "人民邮电出版社",
			"author": "Martin Fowler"
		}, {
			"name": "机器学习实战",
			"publisher": "人民邮电出版社",
			"author": "Peter Harrington"
		}, {
			"name": "Effective Java中文版",
			"publisher": "机械工业出版社",
			"author": "Joshua Bloch"
		}]
	}
}
```

#### 2. 生成单个对象

**请求：**

```shell
curl --location --request POST 'http://localhost:8888/v1/book/one-book' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "机器学习实战",
    "publisher": "人民邮电出版社",
    "author": "Peter Harrington"
}'
```

**返回结果：**

```json
{
    "code": 0,
    "msg": "request successful.",
    "result": {
        "total": 1,
        "data": [
            {
                "name": "机器学习实战",
                "publisher": "人民邮电出版社",
                "author": "Peter Harrington"
            }
        ]
    }
}
```

#### 3. 返回异常信息：

**请求：**

```shell
curl --location --request POST 'http://localhost:8888/v1/book/one-book' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": null,
    "publisher": "人民邮电出版社",
    "author": "Peter Harrington"
}'
```

**返回结果：**

```json
{
    "code": 10001,
    "msg": "book name is null.",
    "result": null
}
```

用postman请求也可以实现同样的效果。具体可参考源代码。

## 6. 处理全局异常

使用注解 `@RestControllerAdvice`，处理全局异常，在请求发生异常时，会通过该类进行处理：

```java
package com.example.demo.configuration;

import com.example.demo.entity.common.ResultVo;
import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.DemoException;
import com.example.demo.utils.ResultVoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-05-04 21:53
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description 全局异常处理
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 未知异常
    @ExceptionHandler(value = Exception.class)
    public ResultVo<String> defaultErrorHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return ResultVoUtils.error(ErrorEnum.UNKNOWN_ERROR);
    }

    // 自定义的异常
    @ExceptionHandler(value = DemoException.class)
    public ResultVo<String> errorHandler(DemoException e) {
        LOGGER.error(e.getMessage(), e);
        return ResultVoUtils.error(e.getErrorEnum());
    }
}

```



## 7. 多环境配置

在 `src/main/resources` 下新建文件:

```shell
application.yml
application-dev.yml
application-test.yml
application-prod.yml
```

其中，`application.yml` 用来指定具体使用哪个配置文件，其内容为：

```yaml
spring:
  profiles:
    active: test
```

则表示是使用的为 `application-test.yml`。

dev,test,prod分别表示开发，测试，生产环境。在实际的工作中，部署时使用脚本动态替换application.yml的active的值则可以做到多环境的部署。

可以在不同的环境下，配置不同的端口，数据库，日志等。



## 8. 集成数据库

### 8.1 添加依赖

```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>

        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis.version}</version>
        </dependency>
```



### 8.2 添加数据库配置

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springbootdemo
    username: bearyang
    password: 123456
mybatis:
  mapper-locations: classpath:mapper/*.xml
```



### 8.3 实现java代码

首先创建数据库相关信息：

```mysql
DROP TABLE IF EXISTS EMPLOYEE;
-- create table
CREATE TABLE EMPLOYEE (
    ID INT UNSIGNED AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    HIRE_DATE BIGINT,
    SALARY DECIMAL(10,2),
    DEPT_NO INT(2),
    PRIMARY KEY (ID)
);
-- 2010-09-14 00:00:00
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小杨', 1284393600000, 8000.0, '06');
-- 2010-09-15 00:00:00
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小张', 1284480000000, 9000.0, '05');
-- 2014-09-01 00:00:00
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小孙', 1409500800000, 12000.0, '05');
-- 2014-09-02 00:00:00
INSERT INTO EMPLOYEE (NAME, HIRE_DATE, SALARY, DEPT_NO) VALUES ('小雷', 1409587200000, 12000.0, '05');
```

#### 8.3.1 实体类

```java
package com.example.demo.entity;

public class Employee {
    private Integer id;
    private String name;
    private String hireDate;
    private Float salary;
    private Integer deptNo;
    // 省略 getter and setter
}
```

#### 8.3.2 DAO代码

java 代码：

```java
package com.example.demo.dao;

import com.example.demo.entity.EmployeeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IEmployeeDao {

    Integer add(EmployeeVo employeeVo);

    Integer delete(Integer id);

    void update(EmployeeVo employeeVo);

    EmployeeVo findEmployeeById(Integer id);

    List<EmployeeVo> selectAll();
}

```

对应xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.demo.dao.IEmployeeDao">

    <resultMap id="EmployeeResultMap" type="com.example.demo.entity.EmployeeVo">
        <resultVo column="ID" jdbcType="INTEGER" property="id"/>
        <resultVo column="NAME" jdbcType="VARCHAR" property="name"/>
        <resultVo column="HIRE_DATE" jdbcType="BIGINT" property="hireDate"/>
        <resultVo column="SALARY" jdbcType="DECIMAL" property="salary"/>
        <resultVo column="DEPT_NO" jdbcType="INTEGER" property="deptNo"/>
    </resultMap>

    <insert id="add" parameterType="com.example.demo.entity.EmployeeVo">
        INSERT INTO EMPLOYEE (ID, NAME, HIRE_DATE, SALARY, DEPT_NO)
        VALUES (#{id}, #{name}, #{hireDate}, #{salary}, #{deptNo})
    </insert>

    <delete id="delete" parameterType="INTEGER">
        DELETE FROM EMPLOYEE WHERE ID = #{id}
    </delete>

    <update id="update" parameterType="com.example.demo.entity.EmployeeVo">
        UPDATE EMPLOYEE SET
        NAME=#{name}, HIRE_DATE=#{hireDate}, SALARY=#{salary}, DEPT_NO=#{deptNo}
        WHERE ID = #{id}
    </update>

    <select id="findEmployeeById" resultMap="EmployeeResultMap">
        SELECT ID, NAME, HIRE_DATE, SALARY, DEPT_NO FROM EMPLOYEE
        WHERE ID = #{id}
    </select>

    <select id="selectAll" resultMap="EmployeeResultMap">
        SELECT * FROM EMPLOYEE
    </select>

</mapper>
```

其中，名称为EmployeeResultMap的resultMap，作用是将数据库表的字段和java实体类的属性映射起来，在下边的查询语句中，可以直接使用resultMap="xxx"，即可实现返回结果为实体类的类型。

#### 8.3.2 接口相关代码

IService:

```java
package com.example.demo.service;

import com.example.demo.entity.EmployeeVo;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:44
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
public interface IEmployeeService {

    /**
     * 添加一个 Employee
     * @param employeeVo
     * @return 1-添加成功
     */
    Integer addEmployee(EmployeeVo employeeVo);

    /**
     * 根据 id 删除一个 Employee
     * @param id
     * @return 1-删除成功
     */
    Integer deleteEmployee(Integer id);

    /**
     * 更新一个 Employee
     * @param employeeVo
     * @return 更新成功后的结果
     */
    EmployeeVo updateEmployee(EmployeeVo employeeVo);

    /**
     * 根据 id 查询 Employee
     * @param id
     * @return
     */
    EmployeeVo queryEmployee(Integer id);

    /**
     * 查询所有 Employee
     * @return
     */
    List<EmployeeVo> selectAll();

}


```

ServiceImpl:

```java
package com.example.demo.service.impl;

import com.example.demo.dao.IEmployeeDao;
import com.example.demo.entity.EmployeeVo;
import com.example.demo.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:44
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeDao employeeDao;

    @Override
    public Integer addEmployee(EmployeeVo employeeVo) {
        return employeeDao.add(employeeVo);
    }

    @Override
    public Integer deleteEmployee(Integer id) {
        return employeeDao.delete(id);
    }

    @Override
    public EmployeeVo updateEmployee(EmployeeVo employeeVo) {
        employeeDao.update(employeeVo);
        return employeeDao.findEmployeeById(employeeVo.getId());
    }

    @Override
    public EmployeeVo queryEmployee(Integer id) {
        return employeeDao.findEmployeeById(id);
    }

    @Override
    public List<EmployeeVo> selectAll() {
        return employeeDao.selectAll();
    }
}
```

Controller:

```java
package com.example.demo.controller;

import com.example.demo.entity.EmployeeVo;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.exception.DemoException;
import com.example.demo.service.IEmployeeService;
import com.example.demo.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2019-07-21 18:43
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@RequestMapping(value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVo<Integer> add(@RequestBody EmployeeVo employeeVo) {
        try {
            return ResultVoUtils.success(employeeService.addEmployee(employeeVo));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public ResultVo<Integer> deleteById(@PathVariable("id") Integer id) {
        try {
            return ResultVoUtils.success(employeeService.deleteEmployee(id));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVo<EmployeeVo> updateEmployee(@RequestBody EmployeeVo employeeVo) {
        try {
            return ResultVoUtils.success(employeeService.updateEmployee(employeeVo));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/query/{id}", method = RequestMethod.GET)
    public ResultVo<EmployeeVo> queryById(@PathVariable("id") Integer id) {
        try {
            return ResultVoUtils.success(employeeService.queryEmployee(id));
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public ResultVo<EmployeeVo> queryAll() {
        try {
            return ResultVoUtils.success(employeeService.selectAll());
        } catch (DemoException demoException) {
            return ResultVoUtils.error(demoException);
        }
    }
}

```

### 8.4 运行结果

get请求：`http://localhost:8888/employee/query/3`

返回结果：

```json
{
    "code": 0,
    "msg": "request successful.",
    "result": {
        "total": 1,
        "data": [
            {
                "id": 3,
                "name": "小孙",
                "hireDate": 1409500800000,
                "salary": 12000.0,
                "deptNo": 5,
                "hireDateFormat": "2014-09-01"
            }
        ]
    }
}
```



### 8.5 日志配置显示sql日志

在 `logback-spring.xml` 中配置：

```xml
    <!-- mybatis 显示日志配置 -->
    <logger name="com.example.demo.dao" level="DEBUG" />
```

其中，`com.example.demo.dao` 表示dao代码所在目录。

这样，我们就可以看到sql执行的日志了，如上边请求的日志为：

```
2019-07-21 19:14:21.297 [http-nio-8888-exec-2] DEBUG com.example.demo.dao.IEmployeeDao.findEmployeeById - ==>  Preparing: SELECT ID, NAME, HIRE_DATE, SALARY, DEPT_NO FROM EMPLOYEE WHERE ID = ? 
2019-07-21 19:14:21.349 [http-nio-8888-exec-2] DEBUG com.example.demo.dao.IEmployeeDao.findEmployeeById - ==> Parameters: 2(Integer)
2019-07-21 19:14:21.423 [http-nio-8888-exec-2] DEBUG com.example.demo.dao.IEmployeeDao.findEmployeeById - <==      Total: 1
```



## 9. 集成Redis

### Redis 基础

#### 1. Redis 简介

Redis 全称： REmote DIctionary Server，即远程字典服务。

是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可以持久化的日志型、Key-Value型数据库。

#### 2. redis安装

##### 2.1 windows下安装

参考：https://www.runoob.com/redis/redis-install.html

下载地址：https://github.com/tporadowski/redis/releases

将 `Redis-x64-5.0.14.1.zip` 解压后，把该路径添加到环境变量，打开命令行，就可以运行。

```shell
# 指定配置文件启动
redis-server redis.windows.conf

# 连接
redis-cli -h 127.0.0.1 -p 6379

# 设置并查看
127.0.0.1:6379> set nametest valuetest123
OK
127.0.0.1:6379> get nametest
"valuetest123"


```



windows下查看并删除redis进程：

```shell
# 根据端口查看
netstat -ano | findstr 6379
# 根据进程名查看
tasklist | findstr redis
# 杀掉进程
taskkill -pid <进程号> -f -t
```





#### 3. 集成springboot

##### 3.1 添加pom依赖

```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```



##### 3.2 配置文件

```
spring:
  redis:
    # Redis数据库索引（默认为0）
    database: 1
    # Redis本地服务器地址，默认为127.0.0.1
    host: 127.0.0.1
    # Redis服务器端口,默认为6379.若有改动按改动后的来
    port: 6379
    #Redis服务器连接密码，默认为空，若有设置按设置的来
    password:
    jedis:
      pool:
        # 连接池最大连接数，若为负数则表示没有任何限制
        max-active: 8
        # 连接池最大阻塞等待时间，若为负数则表示没有任何限制
        max-wait: -1
        # 连接池中的最大空闲连接
        max-idle: 8
```





##### 3.3 添加测试代码

```java
package com.example.demo.controller;

import com.example.demo.entity.common.ResultVo;
import com.example.demo.utils.ResultVoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022/5/9 23:28
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description redis基础
 */
@RestController
@RequestMapping(value = "redis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RedisController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping(value = "/setString", method = RequestMethod.POST)
    public ResultVo<String> setString(@RequestParam(required = true) String key, @RequestParam(required = true) String value) {

        redisTemplate.opsForValue().set(key, value);
        // 设置过期时间为1小时
        redisTemplate.expire(key, 3600L, TimeUnit.SECONDS);
        return ResultVoUtils.success("set successful.");
    }


    @RequestMapping(value = "/getString", method = RequestMethod.GET)
    public ResultVo<String> getString(@RequestParam(required = true) String key) {
        String value = redisTemplate.opsForValue().get(key);
        Long expire = redisTemplate.getExpire(key);
        LOGGER.info("value: {}, expire: {}", value, expire);
        return ResultVoUtils.success("value: " + value + ", expire: " + expire);
    }
}

```



访问查看效果：

```shell
# 设置
curl -X POST "http://localhost:8888/redis/setString?key=name1&value=value1" -H "accept: application/json"

{
  "code": 0,
  "msg": "request successful.",
  "resultVo": {
    "total": 1,
    "data": [
      "set successful."
    ]
  }
}
# 查看

curl -X GET "http://localhost:8888/redis/getString?key=name1" -H "accept: application/json"

{
  "code": 0,
  "msg": "request successful.",
  "resultVo": {
    "total": 1,
    "data": [
      "value: value1, expire: 3580"
    ]
  }
}
```





#### todo

##### 1. redis基础

##### 2. redis常用命令

##### 3. springboot集成redis常用api



## 10. RestTemplate工具类



RestTemplateUtils工具类代码：

```java
package com.example.demo.utils;

import com.example.demo.enums.ErrorEnum;
import com.example.demo.exception.DemoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Http请求工具类
 *
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022/8/7 23:32
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@Component
public class RestTemplateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateUtils.class);

    @Resource
    private RestTemplate restTemplate;

    /**
     * http get 请求
     *
     * @param url url
     * @param responseType response类型
     * @param <T> 类型参数
     * @return response body
     */
    public <T> T get(String url, ParameterizedTypeReference<T> responseType) {
        return http(url, HttpMethod.GET, null, responseType);
    }

    /**
     * http post 请求
     *
     * @param url url
     * @param request 请求体
     * @param responseType response类型
     * @param <T> 类型参数
     * @return response body
     */
    public <T> T post(String url, Object request, ParameterizedTypeReference<T> responseType) {
        return http(url, HttpMethod.POST, request, responseType);
    }

    /**
     * http 请求
     *
     * @param url url
     * @param httpMethod method
     * @param request request
     * @param responseType response类型
     * @param <T> 类型参数
     * @return response body
     */
    public <T> T http(String url, HttpMethod httpMethod, Object request, ParameterizedTypeReference<T> responseType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestData = new HttpEntity<>(request, httpHeaders);
        ResponseEntity<T> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, httpMethod, requestData, responseType);
        } catch (RestClientException e) {
            LOGGER.error("RestClientException when exchange", e);
            throw new DemoException(e, ErrorEnum.HTTP_REQUEST_ERROR);
        }
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            LOGGER.error("http request has response but status is not ok");
            throw new DemoException(ErrorEnum.HTTP_REQUEST_ERROR);
        }
        T body = responseEntity.getBody();
        if (body == null) {
            LOGGER.error("http request has response but response body is null");
            throw new DemoException(ErrorEnum.HTTP_REQUEST_ERROR);
        }
        return body;
    }

}

```



验证：

```java
package com.example.demo.controller;

import com.example.demo.entity.Book;
import com.example.demo.entity.EmployeeVo;
import com.example.demo.entity.common.ResultVo;
import com.example.demo.exception.DemoException;
import com.example.demo.utils.RestTemplateUtils;
import com.example.demo.utils.ResultVoUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2018/11/28 21:55
 * @blog https://blog.csdn.net/next_second
 * @github https://github.com/YoungBear
 * @description
 */
@RestController
@RequestMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @Resource
    private RestTemplateUtils restTemplateUtils;

    @GetMapping(value = "/request-get")
    public ResultVo<Void> requestGet() {
        String url = "http://localhost:8888/employee/query/2";
        ResultVo<EmployeeVo> resultVo = restTemplateUtils.get(url,
                new EmployeeVoParameterizedTypeReference());
        LOGGER.info("code: {}", resultVo.getCode());
        EmployeeVo employeeVo = resultVo.getResult().getData().get(0);
        LOGGER.info("employeeVo: {}", new Gson().toJson(employeeVo));
        return ResultVoUtils.success(null);
    }

    @GetMapping(value = "/request-post")
    public ResultVo<Void> requestPost() {
        Book book = new Book();
        book.setAuthor("吴军");
        book.setPublisher("人民邮电出版社");
        book.setName("数学之美");
        String url = "http://localhost:8888/v1/book/one-book";
        ResultVo<Book> resultVo = restTemplateUtils.post(url,
                book, new BookParameterizedTypeReference());
        LOGGER.info("code: {}", resultVo.getCode());
        Book responseBook = resultVo.getResult().getData().get(0);
        LOGGER.info("employeeVo: {}", new Gson().toJson(responseBook));
        return ResultVoUtils.success(null);
    }

    private static class EmployeeVoParameterizedTypeReference extends ParameterizedTypeReference<ResultVo<EmployeeVo>> {
    }

    ;

    private static class BookParameterizedTypeReference extends ParameterizedTypeReference<ResultVo<Book>> {
    }

    ;


}

```





## [Demo GitHub地址](https://github.com/YoungBear/SpringBootDemo)

