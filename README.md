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
    启动两个服务提供者(feign-provider)
    java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\feign-provider\target\feign-provider-0.0.1-SNAPSHOT.jar --server.port=1003
    java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\feign-provider\target\feign-provider-0.0.1-SNAPSHOT.jar --server.port=1004
    启动一个服务调用者
    java -jar C:\Users\apple\IdeaProjects\drools-ruleEngine\drools-server\target\drools-server-0.0.1-SNAPSHOT.jar --server.port=1002