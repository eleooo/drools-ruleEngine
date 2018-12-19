package com.jy.modules.drools.service;

import com.jy.modules.drools.domain.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public User getUser(String name) {
        User u = new User();
        u.setUserName(name);
        u.setPassword("password");
        return u;
    }
}
