package com.yangjinjing.cms.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.http.util.IPAddress;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.yangjinjing.cms.commen.CmsContant;
import com.yangjinjing.cms.commen.CmsError;
import com.yangjinjing.cms.commen.CmsMessage;
import com.yangjinjing.cms.dao.ArticleRep;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.Channel;
import com.yangjinjing.cms.entity.Comment;
import com.yangjinjing.cms.entity.Complain;
import com.yangjinjing.cms.entity.User;
import com.yangjinjing.cms.service.ArticleService;
import com.yangjinjing.cms.utils.HLUtils;
import com.yangjinjing.cms.utils.StringUtils;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月19日 下午7:20:13
* 类功能说明 
*/
@RequestMapping("article")
@Controller
public class ArticleController extends BaseController{
	
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleRep rep;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	@Autowired
	private KafkaTemplate<String, String> kfk; 
	@Autowired
	private RedisTemplate<String, String> redis;
	
	@RequestMapping("getDetail")
	@ResponseBody
	public CmsMessage getDetail(Integer id){
		
		if(id <= 0){
			
		}
		//获取文章详情
		Article article = articleService.getById(id);
		//不存在
		if(article == null){
			return new CmsMessage(CmsError.NOT_EXIST,"文章不存在,",null);
		}
		
		//返回数据
		return new CmsMessage(CmsError.SUCCESS,"",article);
	}
	
	//kafka完成浏览次数增加
/*	@RequestMapping("detail")
	public String detail(HttpServletRequest request,Integer id){
		Article article = articleService.getById(id);
		request.setAttribute("article", article);
		kfk.send("article",JSON.toJSONString("add"+id));
		return "detail";
	}*/
	
	//redis + 线程池完成浏览次数增加
	//redis 数据类型为key Hits_文章ID_用户IP
	
	@Autowired
	ThreadPoolTaskExecutor executor;
	
	@RequestMapping(value="detail")
	public String detail(HttpServletRequest request,Integer id) throws InterruptedException{
		
		//获取用户IP
		String ip = request.getRemoteAddr();
		//准备redis的key
		String key = "Hits"+id+ip;
		
		ValueOperations<String, String> opsForValue = redis.opsForValue();
		String string = opsForValue.get(key);
		Article article = articleService.getById(id);
		request.setAttribute("article", article);
		
		if(string != null){
			
		}else{
			executor.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					articleService.addLiuLan(id.toString());
					opsForValue.set(key,"");
					redis.expire(key, 5, TimeUnit.MINUTES);
					
				}
			});
			
		}
		System.err.println("----------id:"+id);
		return "detail";
	}
	
	
	@RequestMapping("postcomment")
	@ResponseBody
	public CmsMessage postcomment(HttpServletRequest request,Integer articleId,String content){
		
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		
		if(loginUser == null){
			return new CmsMessage(CmsError.NOT_LOGIN,"您尚未登陆",null);
		}
		
		Comment comment = new Comment();
		comment.setUserId(loginUser.getId());
		comment.setContent(content);
		comment.setArticleId(articleId);
		int result = articleService.addComment(comment);
		System.out.println("评论内容："+comment.getContent());
		if(result > 0)
			return new CmsMessage(CmsError.SUCCESS,"成功",null);
		
		return new CmsMessage(CmsError.FAILED_UPDATE_DB,"异常原因失败，请与管理员联系",null);
	}
	
	@RequestMapping("comments")
	public String comments(HttpServletRequest request,Integer id,int page){
		PageInfo<Comment> commentPage = articleService.getComments(id, page);
		request.setAttribute("commentPage", commentPage);
		return "comments";
	}
	
	/**
	 * 跳转到投诉的页面
	 * @param request
	 * @param articleId
	 * @return
	 */
	@RequestMapping(value="complain",method=RequestMethod.GET)
	public String complain(HttpServletRequest request,Integer articleId) {
		Article article= articleService.getById(articleId);
		request.setAttribute("article", article);
		request.setAttribute("complain", new Complain());
		return "article/complain";
	}


	/**
	 * 接受投诉页面提交的数据
	 * @param request
	 * @param articleId
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping(value="complain",method=RequestMethod.POST)
	public String complain(HttpServletRequest request,
			@ModelAttribute("complain") @Valid Complain complain,
			MultipartFile file,
			BindingResult result) throws IllegalStateException, IOException {

		if(!StringUtils.isHttpUrl(complain.getSrcUrl())) {
			result.rejectValue("srcUrl", "", "不是合法的url地址");
		}
		if(result.hasErrors()) {
			return "article/complain";
		}

		User loginUser  =  (User)request.getSession().getAttribute(CmsContant.USER_KEY);

		String picUrl = this.processFile(file);
		complain.setPicture(picUrl);


		//加上投诉人
		if(loginUser!=null)
			complain.setUserId(loginUser.getId());
		else
			complain.setUserId(0);

		articleService.addComplain(complain);

		return "redirect:/article/detail?id="+complain.getArticleId();

	}
	
	
	//complains?articleId
		@RequestMapping("complains")
		public String 	complains(HttpServletRequest request,int articleId,
				@RequestParam(defaultValue="1") int page) {
			PageInfo<Complain> complianPage = articleService.getComplains(articleId, page);
			request.setAttribute("complianPage", complianPage);
			return "article/complainslist";
		}
		@RequestMapping("search")
		public String searchByEs(Article article,String key,Model model,@RequestParam(defaultValue="1")int page,@RequestParam(defaultValue="5")int rows){
			//显示栏目
			//0.封装查询条件
					article.setStatus(1);
					model.addAttribute("article", article);
					//1. 查询出所有的栏目
							List<Channel> channels = articleService.getAllChannels();
							model.addAttribute("channels", channels);
			//实现高亮显示
			
			long begin = System.currentTimeMillis();
			PageInfo<Article> findByHighLight = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class, page, rows, new String[] {"title"}, "id", key);
			long end = System.currentTimeMillis();
			model.addAttribute("key", key);
			System.err.println(key);
			model.addAttribute("articlePage", findByHighLight);
			System.err.println("共用"+(end-begin)+"毫时");
			return "index";
		}
		

	
}
