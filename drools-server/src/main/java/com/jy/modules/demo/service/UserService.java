package com.jy.modules.demo.service;

import com.jy.modules.demo.dao.UserDAO;
import com.jy.modules.demo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by apple on 2019/1/16.
 */
@Service("com.jy.modules.demo.service.UserService")
public class UserService {
    @Autowired
    private UserDAO userDao;

    public UserDTO getNameById(UserDTO user) {
        return userDao.selectNameById(user.getId());
    }
}
