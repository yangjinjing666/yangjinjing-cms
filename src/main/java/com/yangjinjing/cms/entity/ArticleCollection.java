package com.yangjinjing.cms.entity;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月18日 上午9:15:37
* 类功能说明 
*/
public class ArticleCollection {
	private Integer id;
	private Integer article_id;
	private Integer user_id;
	private String article_text;
	private String url;
	private String created;
	/**
	 * 
	 */
	public ArticleCollection() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleCollection [id=" + id + ", article_id=" + article_id + ", user_id=" + user_id + ", article_text="
				+ article_text + ", url=" + url + ", created=" + created + "]";
	}
	/**
	 * @param id
	 * @param article_id
	 * @param user_id
	 * @param article_text
	 * @param url
	 * @param created
	 */
	public ArticleCollection(Integer id, Integer article_id, Integer user_id, String article_text, String url,
			String created) {
		this.id = id;
		this.article_id = article_id;
		this.user_id = user_id;
		this.article_text = article_text;
		this.url = url;
		this.created = created;
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
	 * @return the article_id
	 */
	public Integer getArticle_id() {
		return article_id;
	}
	/**
	 * @param article_id the article_id to set
	 */
	public void setArticle_id(Integer article_id) {
		this.article_id = article_id;
	}
	/**
	 * @return the user_id
	 */
	public Integer getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the article_text
	 */
	public String getArticle_text() {
		return article_text;
	}
	/**
	 * @param article_text the article_text to set
	 */
	public void setArticle_text(String article_text) {
		this.article_text = article_text;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the created
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article_id == null) ? 0 : article_id.hashCode());
		result = prime * result + ((article_text == null) ? 0 : article_text.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
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
		ArticleCollection other = (ArticleCollection) obj;
		if (article_id == null) {
			if (other.article_id != null)
				return false;
		} else if (!article_id.equals(other.article_id))
			return false;
		if (article_text == null) {
			if (other.article_text != null)
				return false;
		} else if (!article_text.equals(other.article_text))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		return true;
	}
	
	
	
	
}
