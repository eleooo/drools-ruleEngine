#drools-ruleEngine
  drools规则引擎
#swagger-ui访问路径:
  http://localhost:1002/drools-ruleEngine/swagger-ui.html#/

#SpringBoot整合jsp
    application.properties中去除关于spring.thymeleaf相关配置
    添加spring.mvc.static-path-pattern=/static/**，解决解决SpringBoot前端jsp、html页面无法加载css、js等文件的问题
    pom.xml去除中spring-boot-starter-thymeleaf以及thymeleaf.version相关配置

#Ribbon-Netflix的负载均衡项目
  它在集群中为各个客户端的通讯提供了支持(对各种协议提供支持，例如：HTTP、TCP、UDP)，主要实现中间层应用程序的负载均衡。

  ①负载均衡测试: 测试主页面
  　http://localhost:9000/ribbon-invoker/api/loadBalanced/router
  ②负载均衡测试: 查找服务器实例
  　http://localhost:9000/ribbon-invoker/api/loadBalanced/findServiceInstance
  ③负载均衡测试: 输出默认配置
    http://localhost:9000/ribbon-invoker/api/loadBalanced/defaultValue

#微服务启动指令：运行一次maven install，然后使用java -jar并指定target目录下生成的jar文件来运行这个应用程序
  java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\drools-server\target\drools-server-0.0.1-SNAPSHOT.jar --server.port=1001
  java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\drools-server\target\drools-server-0.0.1-SNAPSHOT.jar --server.port=1002
  java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\ribbon-invoker\target\ribbon-invoker-0.0.1-SNAPSHOT.jar --server.port=9000

#Rest客户端Feign
   Feign是GitHub上的一个开源项目，目的是简化 Web Service客户端的开发 。在使用Feign时，可以使用注解来修饰接口，被注解修饰的接口具有访问WebService的能力。
   这些注解中既包括Feign自带的注解，也支持使用第三方的注解 。除此之外， Feign还支持插件式的编码器和解码器，使用者可以通过该特性对请求和响应进行不同的封装与解析。
   Feign使用的是JDK的动态代理，生成的代理类会将请求的信息封装，交给feign.Client接口发送请求，而该接口的默认实现类最终会使用java.net.HttpURLConnection 来发送HTTP请求。
   Feign 框架被集成到 Spring Cloud 的 Netflix 项目中，主要作为REST客户端。
   该框架的主要优点在于，它的插件式机制可以灵活地被整合到项目中 。Feign自带Ribbon模块，本身就具有负载均衡的能力，可以访问集群的服务。

 >> 在Spring Cloud中整合Feign
    服务提供者(feign-provider)
    服务调用者(drools-server)的pom.xml文件中添加以下依赖：
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-openfeign-core</artifactId>
        <version>2.0.0.RELEASE</version>
    </dependency>
    在服务调用者的启动类中，打开Feign开关：
    com.jy.modules.drools.DroolsServerApplication
    @EnableFeignClients(basePackages = "com.jy.modules.boot.feign")
    接下来编写客户端接口
    com.jy.modules.boot.feign.PersonClient
    接口使用了＠FeignClient 注解来修饰，井且声明了需要调用的服务名称，本例的服务提供者名称为feign-provider。

  >> spring cloud调用feign请求超时 feign.RetryableException: Read timed out executing POST
    解决方法：在服务调用者的application.properties添加以下配置
    #请求处理的超时时间
    ribbon.ReadTimeout=60000
    #请求连接的超时时间
    ribbon.ConnectTimeout=60000

  >> 服务启动顺序
    启动Eureka服务器(eureka-server)
    java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\eureka-server\target\eureka-server-0.0.1-SNAPSHOT.jar --server.port=1000
    启动两个服务提供者(feign-provider)
    java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\feign-provider\target\feign-provider-0.0.1-SNAPSHOT.jar --server.port=1003
    java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\feign-provider\target\feign-provider-0.0.1-SNAPSHOT.jar --server.port=1004
    启动一个服务调用者
    java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\drools-server\target\drools-server-0.0.1-SNAPSHOT.jar --server.port=1002

 >> Spring Cloud的保护机制-集群容错框架(Hystrix)

    针对复杂的集群，加强了对服务节点的控制,加入了故障报告、紧急故障处理等机制，非常重要。
    在分布式环境中，总会有一些被依赖的服务会失效，例如像网络短暂无法访问、服务器宕机等情况。
    Hystrix是Netflix下的一个Java库,Spring Cloud将Hystrix整合到Netflix项目中，Hystrix通过添加延迟阀值以及容错的逻辑，来帮助我们控制分布式系统间组件的交互。
    Hystrix通过隔离服务间的访问点、停止它们之间的级联故障、提供可回退操作来实现。
    ======================应用程序正常调用关系为===================================
           用户访问→服务A→基础服务→数据库
    ===========================================================================
    @@ 当基础服务(数据库)不可用时，服务器A将对其进行"熔断",在一定的时间内，服务A都不会再调用基础服务，以维持本身的稳定。
    ======================应用程序正常调用关系演变为===============================
           用户访问→服务A→X→数据库
           用户访问→服务A→回退→回退逻辑
    ===========================================================================

    在Spring Cloud中整合Hystrix

    1.服务提供者(feign-provider)
    2.服务调用者(hystrix-invoker)的pom.xml文件中添加以下依赖：
     <dependency>
          <groupId>org.springframework.cloud</groupId>
          <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
     </dependency>

     <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
     </dependency>

     3.在服务调用者的应用启动类HystrixInvokerApplication中，加入启用断路器的注解@EnableCircuitBreaker.
     4.在服务类PersonService服务方法使用＠HystrixCommand注解进行修饰，并且配置了回退方法 。
       被＠HystrixCommand 修饰的方法， Hystrix(javanica）会使用 AspectJ 对其进行代理，
     5.服务启动顺序
       ①启动Eureka服务器(eureka-server)
       ②启动服务提供者(feign-provider)
       ③启动服务调用者(hystrix-invoker)
       在浏览器输入http://localhost:9001/router/1，输出如下：
       {"id":1,"name":"Crazyit","age":30,"message":"http://DESKTOP-SG1V52N:1003/person/1"}
       停止服务提供者(feign-provider)，再次访问，输出如下：
       {"id":0,"name":"Crazyit","age":-1,"message":"request error"}

     > springboot之Filter指定过滤URL的常见问题
        https://blog.csdn.net/east123321/article/details/79694974

     > Hystrix监控台地址:
       http://localhost:9001/hystrix
       以下命令主要用于测试Feign的断路器，判断断路器开启状态(CircuitStatus-open开启;closed-关闭)
       http://localhost:9001/actuator/hystrix.stream
     > Hystrix熔断处理Swagger文档地址:
        http://localhost:9001/swagger-ui.html#/

  >> 代码检查
     1.使用阿里代码规范插件
       https://blog.csdn.net/weixin_39220472/article/details/80077803[IDEA安装阿里代码规范插件]
     2.使用FindBugs插件
       https://blog.csdn.net/wangwang00001/article/details/80966341[IDEA安装findbugs插件]
