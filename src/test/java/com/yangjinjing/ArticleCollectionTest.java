package com.yangjinjing;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.ArticleCollection;
import com.yangjinjing.cms.service.MyCollectionService;
import com.yangjinjing.utils.StringUtil;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月18日 上午10:22:44
* 类功能说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ArticleCollectionTest {
	
	@Autowired
	private MyCollectionService service;
	
	@Test
	public void list(){
		List<ArticleCollection> collectionList = service.collectionList(68);
		for (ArticleCollection article : collectionList) {
			System.err.println(article);
		}
	}
	
	@Test
	public void add(){
		ArticleCollection articleCollection = new ArticleCollection();
		articleCollection.setArticle_text("0218功能演示");
		articleCollection.setUser_id(68);
		articleCollection.setArticle_id(1);
		String url = "6513513";
		articleCollection.setUrl(url);
		boolean httpUrl = StringUtil.isHttpUrl(url);
		if(httpUrl){
			System.err.println("url正确");
			service.add(articleCollection);
		}else{
			System.err.println("url错误");
		}
	}
	
	@Test
	public void del(){
		service.del(19);
	}
}
