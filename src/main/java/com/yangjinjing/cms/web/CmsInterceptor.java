package com.yangjinjing.cms.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yangjinjing.cms.commen.CmsContant;
import com.yangjinjing.cms.entity.User;
import com.yangjinjing.cms.service.UserService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午6:42:31
* 类功能说明 
*/
public class CmsInterceptor implements HandlerInterceptor{
	
	@Autowired
	UserService userservice;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(loginUser != null){
			//放行
			return true;
		}
		User user = new User();
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if("username".equals(cookies[i].getName())){
				user.setUsername(cookies[i].getValue());
			}
			if("userpwd".equals(cookies[i].getName())){
				user.setPassword(cookies[i].getValue());
			}
		}
		//说明cookie中存放的用户信息不完整
		if(null==user.getUsername() || null==user.getPassword()){
			
			response.sendRedirect("/user/login");
			
			return false;
		}
		//利用cookie中的用户信息进行登录操作
		loginUser = userservice.login(user);
		if(loginUser != null){
			request.getSession().setAttribute(CmsContant.USER_KEY,loginUser);
			return true;
		}
		
		response.sendRedirect("/user/login");
		return false;
	}
}
