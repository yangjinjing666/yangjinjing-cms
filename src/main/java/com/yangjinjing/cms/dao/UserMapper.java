package com.yangjinjing.cms.dao;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yangjinjing.cms.entity.User;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午6:59:48
* 类功能说明 
*/
public interface UserMapper {
	
	@Select("select * from cms_user where username = #{value} limit 1")
	User finUserByName(String username);
	
	
	@Insert("insert into cms_user(username,password,locked,create_time,score,role) values(#{username},#{password},0,now(),0,0)")
	int add(@Valid User user);
	
	/**
	 * 根据用户名和密码查询用户 登陆操作
	 * @param user
	 * @return
	 */
	@Select("select * from cms_user where username=#{username} and password=#{password}")
	User findByPwd(User user);
	
	//@Select("select * from cms_user where id = #{user_id}")
	User findUserById();
}
