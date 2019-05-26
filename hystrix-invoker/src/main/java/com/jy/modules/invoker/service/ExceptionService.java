package com.jy.modules.invoker.service;

import com.jy.modules.invoker.exception.MyException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ＠HystrixCommand 注解还可以使用 ignoreExceptions来处理异常的传播
 * Created by apple on 2019/5/26.
 */
@Service
public class ExceptionService {
    private static Logger logger = LoggerFactory.getLogger(ExceptionService.class);


    /**
     * 声明了忽略MyException，如果方法抛出MyException，则不会触发回退
     */
    @HystrixCommand(ignoreExceptions = {MyException.class},
            fallbackMethod = "testExceptionFallBack")
    public String testException() {
        logger.info("进入方法");
        throw new MyException("自定义异常测试");
    }

    public String testExceptionFallBack() {
        return "error";
    }
}
