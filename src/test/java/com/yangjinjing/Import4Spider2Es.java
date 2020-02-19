package com.yangjinjing;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.utils.FileUtils;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月13日 下午1:46:52
* 类功能说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class Import4Spider2Es {
	
	@Autowired
	KafkaTemplate<String, String> kfk;
	
	@Test
	public void testImportData() throws IOException{
		Random random = new Random();
		File file = new File("D:\\1708E1");
		String[] fileNameList = file.list();
		Article article = new Article();
		article.setPicture("20191224/f018506e-41ff-4f22-b988-2d0ea0b41c1d");
		article.setChannelId(1);
		article.setCategoryId(1);
		article.setUserId(68);
		article.setStatus(0);
		article.setDeleted(0);
		article.setCreated(new Date());
		article.setUpdated(new Date());
		article.setCommentCnt(0);
		article.setArticleType(0);
		article.setComplainCnt(0);
		
		for (String string : fileNameList) {
			//System.err.println(file+"/"+string);
			String read = FileUtils.read(file+"/"+string);
			System.err.println(read);
			article.setTitle(string.substring(0,string.indexOf('.')));
			article.setContent(read.substring(0, 140));
			article.setHot(random.nextInt(2));
			article.setHits(random.nextInt());
			
			String jsonString = JSON.toJSONString(article);
			kfk.send("article",jsonString);
		}
		
		
	}
	
	
	
}
