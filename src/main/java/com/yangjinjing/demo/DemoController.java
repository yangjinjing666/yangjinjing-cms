package com.yangjinjing.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yangjinjing.cms.entity.User;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月17日 下午1:26:06
* 类功能说明 
*/
@Controller
@RequestMapping("demo")
public class DemoController {
	
	@RequestMapping("toggle")
	public String toggle(){
		
		return "demo/toggle";
	}
	
	//处理登录
	@RequestMapping(value="login")
	public @ResponseBody ResponseData login(HttpServletRequest request,@RequestParam("email")String email,@RequestParam("password")String password){
		ResponseData responseData = ResponseData.ok();
		//先到数据库验证
		
		if(password.equals(email)){
			User User = new User();
			
		}
		
		return responseData;
	}

}
