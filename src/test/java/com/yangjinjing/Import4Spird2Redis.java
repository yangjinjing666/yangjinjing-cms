package com.yangjinjing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.service.RedisArticleService;
import com.yangjinjing.cms.utils.FileUtils;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月17日 下午1:48:24
* 类功能说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class Import4Spird2Redis {
	
	@Autowired
	private RedisArticleService rediseService;
	
	@Autowired
	private RedisTemplate redis;
	
	@Autowired
	private KafkaTemplate<String, String> kfk;
	
	
	@Test
	public void importForSpird() throws IOException{
		String filPath = "D:\\1708E1";
		File file = new File(filPath);
		String[] fileNameList = file.list();
		Article article = new Article();
		Random random = new Random();
		List<Article> articleList = new ArrayList<Article>();
		
		
		
		article.setPicture("9c522e72-5c07-430d-83bc-085d44e98f91.jpg");
		article.setChannelId(1);
		article.setCategoryId(1);
		article.setUserId(68);
		article.setHits(0);
		article.setStatus(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setCommentCnt(0);
		article.setComplainCnt(0);
		
		for (String fileName : fileNameList) {
			//System.err.println(fileName.substring(0,fileName.indexOf(".")));
			String title = fileName.substring(0,fileName.indexOf('.'));
			article.setTitle(title);
			String read = FileUtils.read(filPath+"/"+fileName);
			if(read.length() < 140){
				continue;
			}else{
				//System.err.println(fileName);
				
				String content = read.substring(0, 140);
				article.setContent(content);
				article.setHot(2);
				article.setArticleType(5);
				rediseService.save(article);
			}
		}
		
		
		
	}
	
	@Test
	public void redisList(){
		
		List<Article> articleList = redis.opsForList().range("article4spird", 0, -1);
		for (Article article : articleList) {
			String jsonString = JSON.toJSONString(article);
			System.err.println(jsonString);
			kfk.send("article",jsonString);
		}
		redis.expire("article4spird", 1, TimeUnit.SECONDS);
	}
}
