package com.yangjinjing.cms.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yangjinjing.cms.commen.CmsContant;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.Category;
import com.yangjinjing.cms.entity.Channel;
import com.yangjinjing.cms.entity.Slide;
import com.yangjinjing.cms.service.ArticleService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月21日 上午8:14:08
* 类功能说明 
*/
@Controller
public class IndexController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@RequestMapping(value={"index","/"})
	public String index(HttpServletRequest request,@RequestParam(defaultValue="1")int page) throws InterruptedException{
		
		Thread t1 = new Thread(){
			public void run(){
				//获取所有的栏目
				List<Channel> channels = articleService.getAllChannels();
				request.setAttribute("channels", channels);
			};
		};
		
		Thread t2 = new Thread(){
			@SuppressWarnings("unchecked")
			public void run(){
				//获取热门文章
				
				//0.redis优化热门文章
				//1.先从redis中查询热门文章
				List<Article> hotArticleList = redisTemplate.opsForList().range("hot_article", 0, -1);
				//2.判断从redis中查询出来的热门文章是否为空
				if(hotArticleList == null || hotArticleList.size() == 0){
					//3.如果为空
					//4.从mysql中查询出来的热门文章保存到redis
					PageInfo<Article> articlePage = articleService.hotList(page);
					System.err.println("从redis中查询了热门文章");
					List<Article> list = articlePage.getList();
					redisTemplate.opsForList().leftPushAll("hot_article", list);
					redisTemplate.expire("hot_article", 5, TimeUnit.MINUTES);
					request.setAttribute("articlePage", articlePage);
				}else{
					 PageHelper.startPage(page,CmsContant.PAGE_SIZE);
					 PageInfo<Article> articlePage = new PageInfo<>(hotArticleList);
					 request.setAttribute("articlePage", articlePage);
				}
			};
		};
		
		Thread t3 = new Thread(){
			@SuppressWarnings("unchecked")
			public void run(){
				//0.redis作为缓存优化最新文章
				//1.先从Redis中查询最新文章
				List<Article> redisArticle = redisTemplate.opsForList().range("new_articles", 0, -1);
				//2.判断Redis中查询的是否为空
				if(redisArticle == null || redisArticle.size() == 0){
					//3.如果为空
					//4.从MySQL中查最新文章  并且返回给前台
					List<Article> lastArticles = articleService.lastList();
					System.err.println("从mysql中查询了最新文章");
					redisTemplate.opsForList().leftPushAll("new_articles", lastArticles.toArray());
					redisTemplate.expire("new_articles", 5, TimeUnit.MINUTES);
					System.err.println("从Redis中查询了数据");
					request.setAttribute("lastArticles", lastArticles);
				}else{
					//5.不为空  直接返回给前台
					request.setAttribute("lastArticles", redisArticle);
				}
			};
		};
		
		Thread t4 = new Thread(){
			public void run(){
				//轮播图
				List<Slide> slides = articleService.getSlides();
				request.setAttribute("slides", slides);
			};
		};
		
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		
		return "index";
	}
	
	
	@RequestMapping("channel")
	public String channel(HttpServletRequest request,
			Integer channelId,
			@RequestParam(defaultValue="0")int catId,
			@RequestParam(defaultValue="1")int page) throws InterruptedException{
		Thread  t1 =  new Thread() {
			public void run() {
		// 获取所有的栏目
		List<Channel> channels = articleService.getAllChannels();
		request.setAttribute("channels", channels);
			};
		};
		
		Thread  t2 =  new Thread() {
			public void run() {
		// 当前栏目下  当前分类下的文章
		PageInfo<Article> articlePage= articleService.getArticle(channelId,catId, page);
		request.setAttribute("articlePage", articlePage);
			};
		};
		
		Thread  t3 =  new Thread() {
			public void run() {
		// 获取最新文章
		List<Article> lastArticles = articleService.lastList();
		request.setAttribute("lastArticles", lastArticles);
			};
		};
		
		Thread  t4 =  new Thread() {
			public void run() {
		// 轮播图
		List<Slide> slides = articleService.getSlides();
		request.setAttribute("slides", slides);
			};
		};
		
		// 获取当前栏目下的所有的分类 catId
		Thread  t5 =  new Thread() {
			public void run() {
		// 
		List<Category> categoris= articleService.getCategoryByChannelId(channelId);
		request.setAttribute("categoris", categoris);
		System.err.println("categoris is " + categoris);
			};
		};
		
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		
		// 参数回传
		request.setAttribute("catId", catId);
		request.setAttribute("channelId", channelId);
		
		return "channel";
	}
}
