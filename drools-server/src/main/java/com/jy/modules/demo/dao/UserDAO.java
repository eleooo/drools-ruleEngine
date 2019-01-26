package com.jy.modules.demo.dao;

import com.jy.modules.demo.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserDAO {

    //@Select("select id as id, cust_name as custName from user where id = #{id}")
    public UserDTO selectNameById(Long id);
}
