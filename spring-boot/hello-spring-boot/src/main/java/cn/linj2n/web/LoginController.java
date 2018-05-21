package cn.linj2n.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;


import cn.linj2n.domain.User;
import cn.linj2n.service.UserServiceApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


// 标注成为一个Spring MVC 的Controller
@RestController
public class LoginController{
	private UserServiceApi userService;

	// 负责处理/index.html的请求
	@RequestMapping(value = {"/","/index.html"})
	public ModelAndView loginPage(){
		return new ModelAndView("login");
	}

	// 负责处理/loginCheck.html的请求
	@RequestMapping(value = "/loginCheck.html")
	public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){
		boolean isValidUser =  userService.hasMatchUser(loginCommand.getUserName(),
					                    loginCommand.getPassword());
		if (!isValidUser) {
			return new ModelAndView("login", "error", "用户名或密码错误。");
		} else {
			User user = userService.findUserByUserName(loginCommand
					.getUserName());
			user.setLastIp(request.getLocalAddr());
			user.setLastVisit(new Date());
			userService.loginSuccess(user);
			request.getSession().setAttribute("user", user);
			return new ModelAndView("main");
		}
	}

	@Autowired
	public void setUserService(UserServiceApi userService) {
		this.userService = userService;
	}
}
