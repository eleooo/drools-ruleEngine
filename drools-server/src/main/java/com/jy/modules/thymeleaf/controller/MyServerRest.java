package com.jy.modules.thymeleaf.controller;

import com.jy.modules.thymeleaf.dto.User;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/*
** SpringBoot整合Thymeleaf
*  Thymeleaf 是一个新颖的模板引擎，它提供了 一套很接近 HTML 的标签属性。
*  使用这些属性，可 以极大地减少程序对界面的侵入， 程序员与网页设计师（美工）能更好地协作，
  加入了程序逻辑的页面，美工也可以直接在浏览器中打开查看。
* */
@Controller
@RequestMapping(value = "/thymeleaf")
public class MyServerRest {

	@ApiOperation(value="测试页面", notes="测试页面")
	@RequestMapping(value = "/test",method= RequestMethod.GET)
	public String test() {
		return "test";
	}

	@ApiOperation(value="主页面", notes="主页面")
	@RequestMapping(value = "/index",method= RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("name", "World");
		return "welcome";
	}

	@ApiOperation(value="表单提交", notes="表单提交")
	@ApiImplicitParam(name = "loginUser", value = "登陆用户实体", required = true, dataType = "User")
	@RequestMapping(value = "/login",method= RequestMethod.GET)
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

	@ApiOperation(value="获取请求数据", notes="获取请求数据")
	@RequestMapping(value = "/value",method= RequestMethod.GET)
	public String setValue(HttpServletRequest request, HttpSession session) {
		request.setAttribute("valueA", "testA");
		session.setAttribute("valueB", "testB");
		session.getServletContext().setAttribute("valueC", "valueC");
		return "value";
	}

	@ApiOperation(value="调用bean方法", notes="调用bean方法")
	@RequestMapping(value = "/bean",method= RequestMethod.GET)
	public String bean() {
		return "bean";
	}

	@ApiOperation(value="便历集合", notes="便历集合")
	@RequestMapping(value = "/list",method= RequestMethod.GET)
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
