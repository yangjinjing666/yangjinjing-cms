package com.yangjinjing.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yangjinjing.cms.dao.MyCollectionMapper;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.ArticleCollection;
import com.yangjinjing.cms.service.MyCollectionService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月18日 上午10:20:40
* 类功能说明 
*/
@Service
public class MyCollectionServiceImpl implements MyCollectionService{
	
	@Autowired
	private MyCollectionMapper dao;

	@Override
	public List<ArticleCollection> collectionList(Integer uid) {
		// TODO Auto-generated method stub
		return dao.collectionList(uid);
	}

	@Override
	public void add(ArticleCollection article) {
		// TODO Auto-generated method stub
		dao.add(article);
	}

	@Override
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return dao.del(id);
	}
	
	
}
