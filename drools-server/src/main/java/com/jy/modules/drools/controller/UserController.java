package com.jy.modules.drools.controller;

import com.jy.modules.drools.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @RequestMapping(value = "/login")
    public String login(@ModelAttribute User loginUser, Model model, HttpSession session) {
        if(loginUser.getUserName() == null || "".equals(loginUser.getUserName())) {
            model.addAttribute("message", "fail");
            // 失败，将原信息返回
            model.addAttribute("loginUser", loginUser);
        } else {
            System.out.println(loginUser.getUserName() + "---" + loginUser.getPassword());
            model.addAttribute("message", "success");
            // 登录成功，放入session
            session.setAttribute("loginUser", loginUser);
        }
        return "login";
    }

    @RequestMapping(value = "/value")
    public String setValue(HttpServletRequest request, HttpSession session) {
        request.setAttribute("valueA", "testA");
        session.setAttribute("valueB", "testB");
        session.getServletContext().setAttribute("valueC", "valueC");
        return "value";
    }

    @RequestMapping(value = "/bean")
    public String bean() {
        return "bean";
    }

    @RequestMapping(value = "/list")
    public String setValue(Model model) {
        List<User> users = new ArrayList<User>();
        for(int i = 0; i < 5; i++) {
            User u = new User();
            u.setUserName("user-" + i);
            u.setPassword(String.valueOf(i));
            users.add(u);
        }
        model.addAttribute("users", users);
        return "list";
    }
}
