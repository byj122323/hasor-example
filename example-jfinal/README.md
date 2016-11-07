#Hasor-JFinal 整合例子

## JFinal 整合 Hasor 之后 JFinal 会有哪些提升？

IoC/Aop
1. 支持 Controller 通过 @Inject 注解进行依赖注入服务。
2. 被注入的服务支持 IoC/Aop。
3. 被注入的服务支持 初始化 init 调用。
4. 接口可以被直接注入，接口定义通过 @ImplBy 指定实现类。
5. 被注入的服务支持 @Singleton 单例。

数据库操作方面
1. 提供三种途径控制事务，支持七种事务传播属性，标准的事务隔离级别，Spring 有的 JFinal 都会有。
2. 数据库嵌套事务，多层嵌套事务，不设上限
3. Hasor 的 JdbcTemplate 数据库操作接口，与 Spring 的 JdbcTemplate 功能 90% 相同。

分布式 RPC
1. 搭配 Hasor-RSF 框架之后，Hasor 可以为 JFinal 提供分布式服务的能力。
2. 有关 RSF 请查阅：(https://www.oschina.net/p/Hasor-RSF)[https://www.oschina.net/p/Hasor-RSF]

Web 方面
1. (暂略)

集成小建议，视您情况自行选择
1. HasorPlugin，必选，JFinal 中 Hasor 容器启动和销毁。
2. HasorInterceptor，可选，JFinal 拦截器，为 JFinal 提供 IoC/Aop功能。
3. HasorDataSourceProxy，可选，增强JFinal数据库事务管理能力。使用它之后请请不要在使用 JFinal 的事务功能，避免事务管理冲突。
4. HasorHandler，可选，在 JFinal 中使用 Hasor 的 Web 开发能力。
4. RSF分布式服务框架选配依赖
```
<dependency>
    <groupId>net.hasor</groupId>
    <artifactId>hasor-rsf</artifactId>
    <version>1.1.0</version>
</dependency>
```