package com.jy.modules.invoker.controller;

import com.jy.modules.invoker.entity.Person;
import com.jy.modules.invoker.service.CacheService;
import com.jy.modules.invoker.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Configuration
public class InvokerController {

    @Autowired
    private PersonService personService;

    @Autowired
    private CacheService cacheService;


    @RequestMapping(value = "/router/{personId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person router(@PathVariable Integer personId) {
        Person p = personService.getPerson(personId);
        return p;
    }

    @RequestMapping(value = "/cache1/{personId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person testCacheResult(@PathVariable Integer personId) {
        // 调用多次服务
        for(int i = 0; i < 3; i++) {
            Person p = cacheService.getPerson(personId);
            System.out.println("控制器调用服务 " + i);
        }
        return new Person();
    }

    @RequestMapping(value = "/cache2", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String testCacheRemove() {
        for(int i = 0; i < 3; i++) {
            cacheService.cacheMethod("a");
            System.out.println("控制器调用服务 " + i);
        }
        // 清空缓存
        cacheService.updateMethod("a");
        System.out.println("==========  清空了缓存");
        // 再执行多次
        for(int i = 0; i < 3; i++) {
            cacheService.cacheMethod("a");
            System.out.println("控制器调用服务 " + i);
        }
        return "";
    }
}
