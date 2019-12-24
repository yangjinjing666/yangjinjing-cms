package com.yangjinjing.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.PageInfo;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.Category;
import com.yangjinjing.cms.entity.Channel;
import com.yangjinjing.cms.entity.Comment;
import com.yangjinjing.cms.entity.Complain;
import com.yangjinjing.cms.entity.Slide;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午7:32:37
* 类功能说明 
*/
public interface ArticleService {
	
	/**
	 * 根据用户获取文章的列表
	 * @param id
	 * @return
	 */
	PageInfo<Article> listByUser(Integer id,int page);
	
	/**
	 * 删除文章
	 * @param id
	 * @param status
	 * @return
	 */
	int delete(int id);
	
	/**	
	 * 获取所有栏目的方法
	 * @return
	 */
	List<Channel> getAllChannels();
	
	List<Category> getCategorisByCid(int cid);
	
	/**
	 * 根据id  获取分类
	 * @param cid
	 * @return
	 */
	List<Category> getCategoriByCid(int cid);
	
	/**
	 * 发布文章
	 * @param article
	 * @return
	 */
	int add(Article article);
	
	
	/**
	 * 根据文章id获取文章对象
	 * @param id
	 * @return
	 */
	Article getById(int id);
	
	/**
	 * 获取文章的简要信息   常常用于判断文章的存在性
	 */
	Article getInfoById(int id);
	
	/**
	 * @param article
	 * @return
	 */ 	
	int update(Article article,Integer id);
	
	/**
	 * 获取文章列表
	 * @param status 文章状态
	 * @return
	 */
	PageInfo<Article> list(int status,int page);
	
	
	int setHot(int id, int status);
	
	int setCheckStatus(int id, int status);
	
	/**
	 * 获取热门文章
	 */
	PageInfo<Article> hotList(int page);
	
	/***
	 * 获取最新的文章
	 */
	List<Article> lastList();
	
	/**
	 * 获取轮播图
	 */
	List<Slide> getSlides();
	
	/**
	 * 获取栏目下的文章
	 */
	PageInfo<Article> getArticle(int channleId,int catId,int page);
	
	/**
	 * 获取栏目下的分类
	 */
	List<Category> getCategoryByChannelId(int channleId);
	
	/**
	 * 发表评论
	 */
	int addComment(Comment comment);
	
	/**
	 * 根据文章id获取评论
	 */
	
	PageInfo<Comment> getComments(int articleId, int page);
	
	/**
	 * 投诉
	 * @param complain
	 * @return
	 */
	int addComplain(Complain complain);
	/**
	 * 获取投诉
	 * @param articleId
	 * @param page
	 * @return
	 */
	PageInfo<Complain> getComplains(int articleId,int page);
	
}
