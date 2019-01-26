package com.jy.modules;

import com.netflix.discovery.shared.Applications;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Applications.class)// // 指定spring-boot的启动类
public class DroolsServerApplicationTests {

    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }


    @Test
    public void contextLoads() {
        System.out.println("启动测试类");
    }

    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }

}

