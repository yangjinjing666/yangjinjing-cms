package com.yangjinjing.cms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.yangjinjing.cms.commen.Gender;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午2:27:17
* 类功能说明 
*/
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5151845071684357962L;

	private Integer id;
	
	@NotBlank(message="用户名不能为空")
	@Size(max = 10, min = 3, message="用户名应该大于等于6且小于10")
	private String username = null;
	
	@NotBlank(message="用户名不能为空")
	@Size(max = 16, min = 3, message="用户名应该大于等于6且小于10")
	private String password;
	
	private String nickname;
	private Date birthday;
	
	private Gender gender;
	private int locked;
	private Date createTime;
	private Date updateTime;
	
	private String url;	  //头像位置
	private String score;//积分
	private int role;	//角色
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public int getLocked() {
		return locked;
	}
	public void setLocked(int locked) {
		this.locked = locked;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
