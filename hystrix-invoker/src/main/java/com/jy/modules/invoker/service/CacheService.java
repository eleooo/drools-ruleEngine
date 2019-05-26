package com.jy.modules.invoker.service;

import com.jy.modules.invoker.entity.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 缓存的注解主要有以下3个:
 * <br> ＠CacheResult：该注解修饰方法，表示被修饰的方法返回结果将会被缓存，需要与@HystrixCommand一起使用。
 * <br> @CacheRemove ：用于修饰方法让缓存失效，需要与＠CacheResult的缓存key关联 。
 * <br> @CacheKey用于修饰方法参数，表示该参数作为缓存的key;
 * <br>    该注解用来在请求命令的参数上标记，使其作为缓存的Key值，如果没有标注则会使用所有参数。
 * <br>    如果还是使用了@CacheResult和@CacheRemove注解的cacheKeyMethod方法指定缓存Key的生成，那么该注解将不会起作用.
 */
@Service
public class CacheService {

    private static Logger logger = LoggerFactory.getLogger(CacheService.class);

    /**
     * 测试删除缓存
     * <br> 在Spring Cloud 中，可以通过注解＠CacheResult来实现缓存。
     *
     * @param id
     * @return
     */
    @CacheResult
    @HystrixCommand
    public Person getPerson(Integer id) {
        logger.info("==========执行getPerson方法==============");
        Person p = new Person();
        p.setId(id);
        p.setName("angus");
        return p;
    }

    /**
     * <br> 添加缓存
     *
     * @param sysCode 系统编码
     * @param sysName 系统名称
     * @return
     */
    @CacheResult
    @HystrixCommand(commandKey = "removeKey")
    public String cacheMethod(String sysCode, String sysName) {
        logger.info("执行命令,sysCode:{},sysName:{}", sysCode, sysName);
        return "hello";
    }

    /**
     * <br> @CacheRemove该注解用来让请求命令的缓存失效，失效的缓存根据定义Key决定
     * <br> 方法updateMethod被调用后，将会删除updateMethod的缓存
     *
     * @param sysCode 系统编码
     * @param sysName 系统名称
     */
    @CacheRemove(commandKey = "removeKey")
    @HystrixCommand
    public void updateMethod(String sysCode, String sysName) {
        logger.info("缓存已被删除,sysCode:{},sysName:{}", sysCode, sysName);
    }
}
