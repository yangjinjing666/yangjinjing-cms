package com.yangjinjing;

import java.util.List;

import org.jboss.netty.handler.ipfilter.IpV4Subnet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.support.http.util.IPAddress;
import com.yangjinjing.cms.dao.ArticleRep;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.service.ArticleService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2020年2月14日 下午2:16:43
* 类功能说明 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-beans.xml")
public class Import4Mysql2Es {
	
	@Autowired
	private ArticleRep rep;
	
	@Autowired
	private ArticleService service;
	
	@Test
	public void add(){
		List<Article> listAll = service.listAll();
		/*for (Article article : listAll) {
			System.err.println(article.getContent());
		}*/
		rep.saveAll(listAll);
	}
	
	@Test
	public void ipTest(){
		
		IPAddress address = new IPAddress("192.168.198.128");
		String ip = "ip:"+address;
		System.err.println(address);
		
		
	}
	
}
