package com.yangjinjing.cms.service;

import javax.validation.Valid;

import com.yangjinjing.cms.entity.User;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月17日 上午9:34:25
* 类功能说明 
*/
public interface UserService {
	User getUserByUsername(String username);
	
	int register(@Valid User user);
	
	User login(User user);
}
