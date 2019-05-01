package com.jy.modules.demo.dao;

import com.jy.modules.demo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


@Mapper
public interface UserMapper {

    //@Select("select id as id, cust_name as custName from user where id = #{id}")
    UserDTO selectNameById(Long id);

    Long insertUser(Map<String,Object> paramsMap);

    Long updateUserByPrimaryKey(Map<String,Object> paramsMap);
}
