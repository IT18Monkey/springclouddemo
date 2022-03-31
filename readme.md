### 分布式服务组件集成
1. eureka 注册中心
2. log-spring-boot-starter 自定义日志切面starter
3. spring-demo 业务服务
    * 日志 Log4J2
    * json解析  jackson
    * 负载均衡 Spring Cloud LoadBalancer
    * 单元测试 SpringBoot Test
    * ORM框架 mybatis-plus    https://baomidou.com/pages/24112f/#%E7%89%B9%E6%80%A7
    * redis lettuce + Redisson
    * zookeeper  待实现
    * mq消息  RocketMQ 
4. Spring Cloud Gateway  网关
    * 熔断 hystrix 待实现
    * 限流 redisRateLimiter 
5. 分布式配置中心 Apollo 待实现 
6. auth-server  认证授权服务 
    *  Spring Cloud Security 待实现
6. 分布式链路追踪 Spring Cloud Sleuth + Skywalking 待实现
7. netty-demo websocket服务器    
8. 搜索服务 elasticsearch-demo   待实现
9. 性能测试 Jmeter 待实现
10. CICD  jenkins+Kubernetes 待实现 https://blog.csdn.net/Cantevenl/article/details/116722510 https://www.kubernetes.org.cn/2995.html
11. 日志采集 Logstash+Kibana+Elasticsearch  待实现