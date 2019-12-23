package com.yangjinjing.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.yangjinjing.cms.commen.CmsContant;
import com.yangjinjing.cms.commen.CmsError;
import com.yangjinjing.cms.commen.CmsMessage;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.Comment;
import com.yangjinjing.cms.entity.User;
import com.yangjinjing.cms.service.ArticleService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月19日 下午7:20:13
* 类功能说明 
*/
@RequestMapping("article")
@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
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
	
	@RequestMapping("detail")
	public String detail(HttpServletRequest request,Integer id){
		
		Article article = articleService.getById(id);
		request.setAttribute("article", article);
		
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
	
}
