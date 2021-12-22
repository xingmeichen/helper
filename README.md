# My purpose -> Manage Your Time, Enjoy Your Life, To Be Yourself

## Relative Notes On Shimo web site https://shimo.im/docs/6K3wgcyGhTK9YKDj

## 通过git构建配置中心
1. 详情配置请参看config应用的配置文件对于git的配置，以及父项目helper下的spring-cloud-in-action目录
2. 测试查看配置是否正确，访问路径 http://localhost:8050/config/dev  （其中config是应用名称，dev是配置文件后缀），如果返回结果带有相关的配置信息，说明配置成功


## Problems during the development
### To solve the gateway timeout problem
The detail of the problem is that when I try to update database, it will always timeout but query request works well.
Actually, I just make a mistake of spelling, set read timeout for ribbon works well. So, this problem has been resolved now.

### Hystrix and ribbon timeout setting
The Hystrix timeout of 60000ms for the command user is set lower than the combination of the Ribbon read and connect timeout, 122000ms.

## Swagger
1. UI url: http://ip:port/swagger-ui.html

## Spring Security
1. import dependency in parent pom file, spring-boot-starter-security, 
   then access every module will need to login first.
2. 

## Redis
1. Spring Boot 2.1.3.RELEASE 这个版本的Redis starter 默认用的是lettuce客户端，我在使用的过程中总是抱连接超时错误，
   网上查到有人说是因为lettuce没有重连机制，具体原因我也没有查明，改用了Jedis客户端
2. 使用Jedis客户端需要在 Redis starter中将lettuce客户端移除，并且还要加上Jedis客户端的依赖（详情见helper/pom.xml）
3. 通过redis-cli 连接redis, 会看到设置的key 和 value 值都不是我们所预想的样子，而是带别的字符的，
   这是因为默认情况下，Spring 使用的序列化器是JdkSerializationRedisSerializer, 可以通过自己写配置类进行修改



## TODO
1. integrate logback
2. 了解滑动时间窗口计数实现
3. spring-cloud-alibaba (注册中心：Nacos，服务降级机制：Sentinel)
4. 注册表的并发冲突问题
