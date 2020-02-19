package com.yangjinjing.cms.service;

import java.util.List;

import com.yangjinjing.cms.entity.ArticleCollection;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月18日 上午10:20:02
* 类功能说明 
*/
public interface MyCollectionService {
	
	List<ArticleCollection> collectionList(Integer uid);
	
	void add(ArticleCollection article);
	
	int del(Integer id);
}
