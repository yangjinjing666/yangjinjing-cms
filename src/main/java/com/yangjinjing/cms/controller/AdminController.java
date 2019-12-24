package com.yangjinjing.cms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.yangjinjing.cms.commen.CmsError;
import com.yangjinjing.cms.commen.CmsMessage;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.service.ArticleService;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月18日 下午7:13:06
* 类功能说明 
*/
@RequestMapping("admin")
@Controller
public class AdminController {

	@Autowired
	ArticleService articleService;
	
	@RequestMapping("index")
	public String index(){
		return "admin/index";
	}
	
	@RequestMapping("setArticeHot")
	@ResponseBody
	public CmsMessage setArticleHot(Integer id,int status){
		/**
		 * 数据合法性校验
		 */
		if(status != 0 && status != 1){
			
		}
		if(id<0){
			
		}
		int result = articleService.setHot(id, status);
		if(result < 1)
			return new CmsMessage(CmsError.FAILED_UPDATE_DB,"设置失败，请稍后再试",null);
		
		return new CmsMessage(CmsError.SUCCESS,"成功",null);
	}
	
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("setArticeStatus")
	@ResponseBody
	public CmsMessage setArticleStatus(Integer id,int status){
		
		/*
		 * 数据合法性校验
		 */
		
		if(status != 1 && status !=2){
			return new CmsMessage(CmsError.NOT_VALIDATED_ARGURMENT,"status蚕数不合法",null);
		}
		
		if(id<0){
			return new CmsMessage(CmsError.NOT_VALIDATED_ARGURMENT,"id蚕数值不合法",null);
		}
		
		Article article = articleService.getInfoById(id);
		if(article==null){
			return new CmsMessage(CmsError.NOT_EXIST,"数据不存在",null);
		}
		
		/*
		 * 修改数据
		 */
		int result = articleService.setCheckStatus(id, status);
		if(result < 1)
			return new CmsMessage(CmsError.FAILED_UPDATE_DB,"设置失败，请稍后再试",null);
		
		return new CmsMessage(CmsError.SUCCESS,"成功",null);
	}
	
	
	@RequestMapping("article")
	public String article(HttpServletRequest request, @RequestParam(defaultValue="-1")Integer status, @RequestParam(defaultValue="1")int page ){
		PageInfo<Article> srticlePage = articleService.list(status, page);
		request.setAttribute("status", status);
		request.setAttribute("articlePage", srticlePage);
		return "admin/article/list";
	}
	
	@RequestMapping("link")
	public String link(){
		
		return "";
	}
}
