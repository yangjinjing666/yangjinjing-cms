package com.yangjinjing.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangjinjing.cms.commen.CmsContant;
import com.yangjinjing.cms.dao.ArticleMapper;
import com.yangjinjing.cms.dao.SlideMapper;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.Category;
import com.yangjinjing.cms.entity.Channel;
import com.yangjinjing.cms.entity.Comment;
import com.yangjinjing.cms.entity.Slide;
import com.yangjinjing.cms.service.ArticleService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午7:38:43
* 类功能说明 
*/
@Service
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	ArticleMapper dao;
	
	@Autowired
	SlideMapper slideMapper;
	
	
	@Override
	public PageInfo<Article> listByUser(Integer id,int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,CmsContant.PAGE_SIZE);
		PageInfo<Article> pageInfo = new PageInfo<>(dao.listByUser(id));
		return pageInfo;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return dao.updateStatus(id, CmsContant.ARTICLE_STATUS_DEL);
	}

	@Override
	public List<Channel> getAllChannels() {
		// TODO Auto-generated method stub
		return dao.getAllChannels();
	}

	@Override
	public List<Category> getCategoriByCid(int cid) {
		// TODO Auto-generated method stub
		return dao.getCategoriesByChannelId(cid);
	}

	@Override
	public int add(Article article) {
		// TODO Auto-generated method stub
		return dao.add(article);
	}

	@Override
	public Article getById(int id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public int update(Article article, Integer userId) {
		// TODO Auto-generated method stub
		Article articleSrc = this.getById(article.getId());
		if(articleSrc.getUserId() != userId){
			
		}
		return dao.update(article);
	}

	@Override
	public PageInfo<Article> list(int status, int page) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page,CmsContant.PAGE_SIZE);
		return new PageInfo<>(dao.list(status));
	}

	@Override
	public Article getInfoById(int id) {
		// TODO Auto-generated method stub
		return dao.getInfoById(id);
	}
	
	@Override
	public int setHot(int id, int status) {
		// TODO Auto-generated method stub
		return dao.setHot(id, status);
	}

	@Override
	public int setCheckStatus(int id, int status) {
		// TODO Auto-generated method stub
		return dao.CheckStatus(id, status);
	}

	@Override
	public PageInfo<Article> hotList(int page) {
		// TODO Auto-generated method stub
		return new PageInfo<>(dao.hotList());
	}

	@Override
	public List<Article> lastList() {
		// TODO Auto-generated method stub
		return dao.lastList(CmsContant.PAGE_SIZE);
	}

	@Override
	public List<Slide> getSlides() {
		// TODO Auto-generated method stub
		return slideMapper.slideList();
	}

	@Override
	public PageInfo<Article> getArticle(int channleId, int catId, int page) {
		// TODO Auto-generated method stub
		return new PageInfo<>(dao.getArticles(channleId, catId));
	}

	@Override
	public List<Category> getCategoryByChannelId(int channleId) {
		// TODO Auto-generated method stub
		return dao.getCategoriesByChannelId(channleId);
	}

	@Override
	public int addComment(Comment comment) {
		// TODO Auto-generated method stub
			int result = dao.addComment(comment);
		//文章评论书目自增
		if(result >0 ){
			dao.increaseCommentCnt(comment.getArticleId());
		}
			
			
		return result;
	}

	@Override
	public PageInfo<Comment> getComments(int articleId, int page) {
		// TODO Auto-generated method stub
		return new PageInfo<>(dao.getComments(articleId));
	}

	@Override
	public List<Category> getCategorisByCid(int cid) {
		// TODO Auto-generated method stub
		return dao.getCategorisByCid(cid);
	}


}
