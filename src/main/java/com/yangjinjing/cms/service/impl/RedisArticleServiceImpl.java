package com.yangjinjing.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.service.RedisArticleService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月17日 下午1:37:20
* 类功能说明 
*/
@Service
public class RedisArticleServiceImpl implements RedisArticleService{
	
	@Autowired
	private RedisTemplate<String, Article> redis;
	
	@Autowired
	private KafkaTemplate<String, String> kfk;


	@Override
	public void save(Article articleList) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				//先保存到redis中
				redis.opsForList().leftPush("article4spird", articleList);
				//再通过Kafka发送redis中的key
				List<Article> range = redis.opsForList().range("article4spird", 0, -1);
				
	}
	
}
