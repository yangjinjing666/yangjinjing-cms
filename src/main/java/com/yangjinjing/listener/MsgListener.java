package com.yangjinjing.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.yangjinjing.cms.dao.ArticleMapper;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.service.ArticleService;

import scala.Enumeration.Val;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年1月9日 上午10:50:00
* 类功能说明 
*/
public class MsgListener implements MessageListener<String, String>{
	
	@Autowired
	private ArticleService service;


/*	@Override
	public void onMessage(ConsumerRecord<String, String> msg) {
		// TODO Auto-generated method stub
		String value = msg.value();
		Article article = JSON.parseObject(value,Article.class);
		service.add(article);
	}*/
	
	/*
	 * 接收kafka传来的消息
	 */
	@Override
	public void onMessage(ConsumerRecord<String, String> msg) {
		// TODO Auto-generated method stub
		String value = msg.value();
		Article article = JSON.parseObject(value,Article.class);
		service.add(article);
	}
	
	
}