package com.jy.modules.invoker.service;

import com.jy.modules.invoker.entity.Person;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * 在Spring Cloud中同样支持合并请求，在一次HTTP请求的过程中，收集一段时间内的相同请求，放到一个批处理命令中执行，实现合并请求
 **/
@Service
public class CollapseService {

    private static Logger logger = LoggerFactory.getLogger(CollapseService.class);

    /**
     * <br> 真实执行的方法为 getPersons, getSinglePerson方法使用了@HystrixCollapser 注解来修饰，
     * <br> 会收集l秒内调用getSinglePerson的请求，放到getPersons法中进行批处理 。
     * <br> 控制器中多次调用getSinglePerson方法
     */
    @HystrixCollapser(batchMethod = "getPersons", collapserProperties =
            {
                    //配置收集1秒内的请求
                    @HystrixProperty(name = "timerDelayInMilliseconds", value = "1000")
            }
    )
    public Future<Person> getSinglePerson(Integer id) {
        logger.info("执行单个获取的方法");
        return null;
    }

    @HystrixCommand
    public List<Person> getPersons(List<Integer> ids) {
        logger.info("收集请求，参数数量：{}", ids.size());
        List<Person> ps = new ArrayList<Person>();
        ids.stream().forEach(id -> {
            Person p = new Person();
            p.setId(id);
            p.setName("chenxbook");
            ps.add(p);
        });
        return ps;
    }


}
