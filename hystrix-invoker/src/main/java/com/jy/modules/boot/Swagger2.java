package com.jy.modules.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 通过@Configuration注解，表明它是一个配置类，@EnableSwagger2开启swagger2。
 * apiINfo()配置一些基本的信息。apis()指定扫描的包会生成文档。
 * swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等。
     @Api：修饰整个类，描述Controller的作用
     @ApiOperation：描述一个类的一个方法，或者说一个接口
     @ApiParam：单个参数描述
     @ApiModel：用对象来接收参数
     @ApiProperty：用对象接收参数时，描述对象的一个字段
     @ApiResponse：HTTP响应其中1个描述 @ApiResponses：HTTP响应整体描述
     @ApiIgnore：使用该注解忽略这个API
     @ApiError ：发生错误返回的信息
     @ApiImplicitParam：一个请求参数
     @ApiImplicitParams 多个请求参数
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .groupName("接口文档V1.0")
                .pathMapping("/")
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格")
                .version("1.0")
                .build();
    }

}
