package com.jy.modules.invoker.rest;

import com.jy.modules.boot.feign.HelloClient;
import com.jy.modules.invoker.entity.Person;
import com.jy.modules.invoker.service.CacheService;
import com.jy.modules.invoker.service.CollapseService;
import com.jy.modules.invoker.service.ExceptionService;
import com.jy.modules.invoker.service.PersonService;
import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

/**
 * Hystrix熔断处理
 *
 * @author apple
 */
@Api(tags = {"Hystrix熔断处理"}, description = "Hystrix熔断处理")
@RestController
@RequestMapping(value = "/api/hystrix")
@Configuration
public class InvokerRest {

    private static Logger logger = LoggerFactory.getLogger(InvokerRest.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private ExceptionService exceptionService;

    @Autowired
    private CollapseService collapseService;

    private final String LOAN_SYS_CODE = "S001";
    private final String ANTF_SYS_CODE = "S002";
    private final String SYS_NAME = "资产管理平台";


    /**
     * 测试简单的熔断处理
     */
    @ApiOperation(value = "测试简单熔断处理", notes = "router")
    @RequestMapping(value = "/router/{personId}/v1", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person router(@PathVariable Integer personId) {
        Person p = personService.getPerson(personId);
        return p;
    }

    /**
     * 测试ignoreExceptions配置某异常类之后,发生此类异常不进行熔断处理
     */
    @ApiOperation(value = "测试@ignoreExceptions配置", notes = "testException")
    @RequestMapping(value = "/testException/v1", method = RequestMethod.GET)
    public String testException() {
        String result = exceptionService.testException();
        return result;
    }

    /**
     * 测试使用缓存@CacheResult
     */
    @ApiOperation(value = "测试使用缓存@CacheResult", notes = "testCacheResult")
    @RequestMapping(value = "/testCacheResult/{personId}/v1", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person testCacheResult(@PathVariable Integer personId) {
        int size = 3;
        // 调用多次服务
        for (int i = 0; i < size; i++) {
            Person p = cacheService.getPerson(personId);
            logger.info("控制器调用服务:{}", i);
        }
        return new Person();
    }

    /**
     * 测试删除缓存@CacheRemove
     */
    @ApiOperation(value = "测试删除缓存@CacheRemove", notes = "testCacheRemove")
    @RequestMapping(value = "/testCacheRemove/v1", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCacheRemove() {
        int size = 3;
        for (int i = 0; i < size; i++) {
            cacheService.cacheMethod(LOAN_SYS_CODE, SYS_NAME);
            cacheService.cacheMethod(ANTF_SYS_CODE, SYS_NAME);
            logger.info("控制器调用服务:{}", i);
        }
        // 清空缓存
        cacheService.updateMethod(LOAN_SYS_CODE, SYS_NAME);
        // 再执行多次
        for (int i = 0; i < size; i++) {
            cacheService.cacheMethod(LOAN_SYS_CODE, SYS_NAME);
            cacheService.cacheMethod(ANTF_SYS_CODE, SYS_NAME);
            cacheService.cacheMethod(ANTF_SYS_CODE, "风控管理平台");
            logger.info("控制器调用服务:{}", i);
        }
        return "";
    }

    /**
     * 测试删除缓存@CacheRemove
     */
    @ApiOperation(value = "测试合并请求注解@HystrixCollapser", notes = "collapse")
    @RequestMapping(value = "/collapse/v1", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCollapse() throws Exception {
        // 连续执行3次请求
        Future<Person> f1 = collapseService.getSinglePerson(1);
        Future<Person> f2 = collapseService.getSinglePerson(2);
        Future<Person> f3 = collapseService.getSinglePerson(3);
        Person p1 = f1.get();
        Person p2 = f2.get();
        Person p3 = f3.get();
        logger.info("Person1-id:{},name:{}", p1.getId(), p1.getName());
        logger.info("Person2-id:{},name:{}", p2.getId(), p2.getName());
        logger.info("Person3-id:{},name:{}", p3.getId(), p3.getName());
        return "";
    }

    @Autowired
    private HelloClient helloClient;

    /**
     * <br>Feign与HHystrix整合使用时，会自动帮我们生成CommandKey，
     * <br>格式为：Feign客户端接口名＃方法名。
     * <br>默认情况下,生成的GroupKey为＠FeignClient注解的name属性。
     **/
    @ApiOperation(value = "Feign与Hystrix整合", notes = "feignHello")
    @RequestMapping(value = "/feign/hello/v1", method = RequestMethod.GET)
    public String feignHello() {
        // hello方法会超时
        String helloResult = helloClient.hello();
        // 获取断路器(true-断开;false-连接)
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
                .getInstance(HystrixCommandKey.Factory
                        .asKey("HelloClient#hello()"));
        logger.info("断路器状态:{}", breaker.isOpen());
        return helloResult;
    }
}
