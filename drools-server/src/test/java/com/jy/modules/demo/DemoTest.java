package com.jy.modules.demo;

import com.jy.modules.DroolsServerApplicationTests;
import com.jy.modules.demo.dao.UserMapper;
import com.jy.modules.demo.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//https://blog.csdn.net/thomaslove/article/details/80281720
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DroolsServerApplicationTests.class)// // 指定spring-boot的启动类
public class DemoTest {

    //日志打印
    Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @Autowired
    private UserMapper userDao;

    @Test
    public void testGetNameById(){
        UserDTO userDTO = userDao.selectNameById(1l);
        logger.info("查询用户信息：{}",userDTO);
    }

    @Test
    //默认单元测试更新操作默认会回滚，如果你想看到实际数据库表中数据 则添加
    @Rollback(false)
    public  void testInsertUser(){
        Map<String,Object>  paramsMap = new HashMap<>();
        paramsMap.put("dto",new UserDTO("李四"));
        userDao.insertUser(paramsMap);
        logger.info("新增用户信息成功");
    }

    @Test
    //默认单元测试更新操作默认会回滚，如果你想看到实际数据库表中数据 则添加
    @Rollback(false)
    public  void testUpdateUser(){
        Map<String,Object>  paramsMap = new HashMap<>();
        paramsMap.put("dto",new UserDTO(1L,"张三"));
        userDao.updateUserByPrimaryKey(paramsMap);
        logger.info("修改用户信息成功");
    }
}
