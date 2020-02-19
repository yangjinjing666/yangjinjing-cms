package com.yangjinjing.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.yangjinjing.cms.entity.Article;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年1月12日 上午11:22:01
* 类功能说明 
*/
public interface ArticleRep extends ElasticsearchRepository<Article, Integer>{
	
	List<Article> findByTitle(String title);
	
	
}
