# HKD Common Library

HKD Exchange 通用库 - 提供所有微服务共享的基础功能

## 功能特性

### 1. 统一响应封装

```java
// 成功响应
Result<User> result = Result.success(user);

// 失败响应
Result<Void> result = Result.error(ErrorCode.USER_NOT_FOUND);

// 分页响应
PageResponse<User> page = PageResponse.of(1, 10, 100L, userList);
```

### 2. 异常处理

```java
// 抛出业务异常
throw new BusinessException(ErrorCode.USER_NOT_FOUND);

// 自定义消息
throw new BusinessException(ErrorCode.PARAM_INVALID, "用户名不能为空");

// 全局异常处理器自动捕获并返回统一格式
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 自动处理所有异常
}
```

### 3. 工具类

#### JsonUtil

```java
// 对象转JSON
String json = JsonUtil.toJson(user);

// JSON转对象
User user = JsonUtil.fromJson(json, User.class);

// JSON转List
List<User> users = JsonUtil.fromJson(json, new TypeReference<List<User>>() {});
```

#### DateUtil

```java
// 获取当前时间
LocalDateTime now = DateUtil.now();

// 格式化
String dateStr = DateUtil.format(now);

// 解析
LocalDateTime date = DateUtil.parse("2024-01-01 12:00:00");

// 计算时间差
long days = DateUtil.daysBetween(start, end);

// 增加时间
LocalDateTime tomorrow = DateUtil.plusDays(now, 1);
```

#### IdGenerator

```java
// 获取实例（单例模式）
IdGenerator generator = IdGenerator.getInstance(workerId);

// 生成ID
long id = generator.nextId();

// 生成字符串ID
String idStr = generator.nextIdStr();

// 解析ID
long timestamp = IdGenerator.parseTimestamp(id);
long workerId = IdGenerator.parseWorkerId(id);
```

### 4. 常量和枚举

#### Constants

```java
// HTTP Header
Constants.HEADER_AUTHORIZATION
Constants.BEARER_PREFIX

// 缓存Key前缀
Constants.CACHE_USER_PREFIX + userId

// Token过期时间
Constants.ACCESS_TOKEN_EXPIRE
```

#### Enums

```java
// 用户状态
UserStatus.ACTIVE
UserStatus.DISABLED

// KYC等级
KycLevel.L0  // 未认证
KycLevel.L1  // 基础认证
KycLevel.L2  // 高级认证
KycLevel.L3  // 专业认证

// 订单状态
OrderStatus.PENDING
OrderStatus.FILLED

// 订单类型
OrderType.MARKET
OrderType.LIMIT
```

## 使用方法

### 1. 添加依赖

在其他微服务的 `pom.xml` 中添加:

```xml
<dependency>
    <groupId>com.hkd</groupId>
    <artifactId>hkd-common</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### 2. 安装到本地Maven仓库

```bash
cd /home/judy/codebase/HKD/hkd-common
mvn clean install
```

### 3. 启用全局异常处理

在Spring Boot应用中，`GlobalExceptionHandler` 会自动被 `@RestControllerAdvice` 注解扫描到。

确保你的应用扫描到了 `com.hkd.common` 包:

```java
@SpringBootApplication
@ComponentScan(basePackages = {"com.hkd.service", "com.hkd.common"})
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

### 4. 配置IdGenerator

在应用启动时配置workerId:

```java
@Configuration
public class CommonConfig {

    @Value("${hkd.worker-id:0}")
    private long workerId;

    @PostConstruct
    public void init() {
        IdGenerator.getInstance(workerId);
    }
}
```

在 `application.yml` 中配置:

```yaml
hkd:
  worker-id: 1  # 每个服务实例应该有唯一的workerId (0-1023)
```

## 项目结构

```
src/main/java/com/hkd/common/
├── constant/          # 常量
│   └── Constants.java
├── dto/               # 数据传输对象
│   ├── PageRequest.java
│   ├── PageResponse.java
│   └── Result.java
├── enums/             # 枚举
│   ├── KycLevel.java
│   ├── OrderStatus.java
│   ├── OrderType.java
│   └── UserStatus.java
├── exception/         # 异常处理
│   ├── BusinessException.java
│   ├── ErrorCode.java
│   └── GlobalExceptionHandler.java
└── util/              # 工具类
    ├── DateUtil.java
    ├── IdGenerator.java
    └── JsonUtil.java
```

## 开发指南

### 添加新的ErrorCode

在 `ErrorCode.java` 中添加:

```java
// 按业务域划分错误码范围
// 8000-8999: 新业务域错误码
NEW_DOMAIN_ERROR(8001, "New Domain Error"),
```

### 添加新的枚举

创建新的枚举类:

```java
@Getter
public enum YourEnum {
    VALUE_1(1, "Description 1"),
    VALUE_2(2, "Description 2");

    private final Integer code;
    private final String description;

    YourEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
```

### 添加新的工具类

在 `util/` 包下创建新的工具类:

```java
public class YourUtil {
    // 工具方法应该是静态方法
    public static void yourMethod() {
        // ...
    }

    // 防止实例化
    private YourUtil() {}
}
```

## 版本历史

- **1.0.0-SNAPSHOT** (2024-11-17)
  - 初始版本
  - 提供基础DTO、异常处理、工具类、常量和枚举

## License

MIT License
