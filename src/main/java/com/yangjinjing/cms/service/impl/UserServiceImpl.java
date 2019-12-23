package com.yangjinjing.cms.service.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangjinjing.cms.commen.CmsUtils;
import com.yangjinjing.cms.dao.UserMapper;
import com.yangjinjing.cms.entity.User;
import com.yangjinjing.cms.service.UserService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月17日 上午9:36:14
* 类功能说明 
*/
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper mapper;

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return mapper.finUserByName(username);
	}
	
	@Override
	public int register(@Valid User user) {
		// TODO Auto-generated method stub
		//计算密文
		String encryPwd = CmsUtils.encry(user.getPassword(), user.getUsername());
		user.setPassword(encryPwd);
		return mapper.add(user);
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		user.setPassword(CmsUtils.encry(user.getPassword(), user.getUsername()));
		User loginUser = mapper.findByPwd(user);
		return loginUser;
	}

	
	
	
	
}
