package com.jy.modules.demo;
import com.jy.modules.demo.dao.UserDAO;
import com.jy.modules.demo.dto.UserDTO;
import com.netflix.discovery.shared.Applications;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//https://blog.csdn.net/thomaslove/article/details/80281720
//https://blog.csdn.net/xinanrusu/article/details/52846243
@RunWith(SpringRunner.class)
@SpringBootTest(classes=Applications.class)
//使用@MybatisTest 默认会使用虚拟的数据源替代你配置的，如果想使用你配置的数据源操作真正的数据库则使用
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MapperScan(value = {"com.jy.modules.*.dao"})
@ComponentScan(basePackages = {"com.jy.modules"})
public class DemoTest {

    //日志打印
    Logger logger = LoggerFactory.getLogger(DemoTest.class);

    @Autowired
    private UserDAO userDao;

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
    }

    @Test
    //默认单元测试更新操作默认会回滚，如果你想看到实际数据库表中数据 则添加
    @Rollback(false)
    public  void testUpdateUser(){
        Map<String,Object>  paramsMap = new HashMap<>();
        paramsMap.put("dto",new UserDTO(1L,"张三"));
        userDao.updateUserByPrimaryKey(paramsMap);
    }
}
