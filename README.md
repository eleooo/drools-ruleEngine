# drools-ruleEngine
drools规则引擎
#swagger-ui访问路径:
http://localhost:1002/drools-ruleEngine/swagger-ui.html#/

#SpringBoot整合jsp
application.properties中去除关于spring.thymeleaf相关配置
添加spring.mvc.static-path-pattern=/static/**，解决解决SpringBoot前端jsp、html页面无法加载css、js等文件的问题
pom.xml去除中spring-boot-starter-thymeleaf以及thymeleaf.version相关配置
