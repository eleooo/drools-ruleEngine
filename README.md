# drools-ruleEngine
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

