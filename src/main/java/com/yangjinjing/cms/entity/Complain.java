package com.yangjinjing.cms.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月23日 下午7:29:40
* 类功能说明 
*/
public class Complain {
	private Integer id;
	
	@NotNull
	private Integer articleId;
	
	private Integer userId;
	
	@NotNull
	private Integer complainType;
	
	@org.hibernate.validator.constraints.NotBlank
	private String complainOption;
	private String srcUrl;
	private String picture;
	private String content;
	
	@Email
	private String email;
	private String mobile;
	
	private Date created;
	private User user;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the articleId
	 */
	public Integer getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the complainType
	 */
	public Integer getComplainType() {
		return complainType;
	}
	/**
	 * @param complainType the complainType to set
	 */
	public void setComplainType(Integer complainType) {
		this.complainType = complainType;
	}
	/**
	 * @return the complainOption
	 */
	public String getComplainOption() {
		return complainOption;
	}
	/**
	 * @param complainOption the complainOption to set
	 */
	public void setComplainOption(String complainOption) {
		this.complainOption = complainOption;
	}
	/**
	 * @return the srcUrl
	 */
	public String getSrcUrl() {
		return srcUrl;
	}
	/**
	 * @param srcUrl the srcUrl to set
	 */
	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}
	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Complain [id=" + id + ", articleId=" + articleId + ", userId=" + userId + ", complainType="
				+ complainType + ", complainOption=" + complainOption + ", srcUrl=" + srcUrl + ", picture=" + picture
				+ ", content=" + content + ", email=" + email + ", mobile=" + mobile + ", created=" + created
				+ ", user=" + user + "]";
	}
	
}
