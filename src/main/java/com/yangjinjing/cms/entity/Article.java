package com.yangjinjing.cms.entity;

import java.util.Date;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午6:35:31
* 类功能说明 
*/
public class Article {
	private Integer id;
	private String title;
	private String content;
	private String picture;
	private int channelId;
	private int categoryId;	//分类
	private int userId;
	private int hits;
	private int hot;
	private int status;
	private int deleted;
	private Date created;
	private Date updated;
	private int commentCnt;
	private int articleType;
	
	private Channel channel;
	private Category category;
	private User user;
	private int complainCnt;

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + ", picture=" + picture
				+ ", channelId=" + channelId + ", categoryId=" + categoryId + ", userId=" + userId + ", hits=" + hits
				+ ", hot=" + hot + ", status=" + status + ", deleted=" + deleted + ", created=" + created + ", updated="
				+ updated + ", commentCnt=" + commentCnt + ", articleType=" + articleType + ", channel=" + channel
				+ ", category=" + category + ", user=" + user + ", complainCnt=" + complainCnt + "]";
	}
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the channelId
	 */
	public int getChannelId() {
		return channelId;
	}
	/**
	 * @param channelId the channelId to set
	 */
	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the hits
	 */
	public int getHits() {
		return hits;
	}
	/**
	 * @param hits the hits to set
	 */
	public void setHits(int hits) {
		this.hits = hits;
	}
	/**
	 * @return the hot
	 */
	public int getHot() {
		return hot;
	}
	/**
	 * @param hot the hot to set
	 */
	public void setHot(int hot) {
		this.hot = hot;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the deleted
	 */
	public int getDeleted() {
		return deleted;
	}
	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(int deleted) {
		this.deleted = deleted;
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
	 * @return the updated
	 */
	public Date getUpdated() {
		return updated;
	}
	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * @return the commentCnt
	 */
	public int getCommentCnt() {
		return commentCnt;
	}
	/**
	 * @param commentCnt the commentCnt to set
	 */
	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}
	/**
	 * @return the articleType
	 */
	public int getArticleType() {
		return articleType;
	}
	/**
	 * @param articleType the articleType to set
	 */
	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}
	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	/**
	 * @return the category
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Category category) {
		this.category = category;
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
	/**
	 * @return the complainCnt
	 */
	public int getComplainCnt() {
		return complainCnt;
	}
	/**
	 * @param complainCnt the complainCnt to set
	 */
	public void setComplainCnt(int complainCnt) {
		this.complainCnt = complainCnt;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
