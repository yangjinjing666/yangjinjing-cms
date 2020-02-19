package com.yangjinjing;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yangjinjing.cms.dao.ArticleMapper;
import com.yangjinjing.cms.dao.ArticleRep;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.service.ArticleService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年1月12日 上午11:08:09
* 类功能说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class ImportForMysql {
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	ArticleRep articleRep;
	
	@Test
	public void importForMysql(){
		List<Article> findAllArticlesWithStatus = articleMapper.findAllArticlesWithStatus(1);
		//添加到es
		articleRep.saveAll(findAllArticlesWithStatus);
	}
	
}
