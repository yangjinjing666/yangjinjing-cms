package com.yangjinjing.cms.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageInfo;
import com.yangjinjing.cms.commen.CmsContant;
import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.Category;
import com.yangjinjing.cms.entity.Channel;
import com.yangjinjing.cms.entity.User;
import com.yangjinjing.cms.service.ArticleService;
import com.yangjinjing.cms.service.UserService;
import com.yangjinjing.cms.utils.HtmlUtils;
import com.yangjinjing.cms.utils.StringUtils;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月17日 下午3:58:24
* 类功能说明 
*/
@Controller
@RequestMapping("user")
public class UserController extends BaseController {

	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("home")
	public String home(){
		return "user/home";
	}
	
	/**
	 * 跳转到注册界面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register(HttpServletRequest request){
		User user = new User();
		request.setAttribute("user", user);
		return "user/register";
	}
	
	/**
	 * 从注册页面发过来的请求
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String register(HttpServletRequest request,@Valid @ModelAttribute("user")User user,BindingResult result){
		
		//有错误返回到注册页面
		if(result.hasErrors()){
			return "user/register";
		}
		///进行唯一校验
		User existUser = userService.getUserByUsername(user.getUsername());
		if(existUser != null){
			result.rejectValue("username", "", "用户名不能全是数字");
			return "user/register";
		}
		
		//加一个手动的校验
		if(StringUtils.isNumber(user.getPassword())){
			result.rejectValue("error", "", "密码不能全是数字");
			return "user/register";
		}
		
		//去注册
		int reRegister = userService.register(user);
		
		//注册失败
		if(reRegister<1){
			request.setAttribute("error", "注册失败,请稍后再试");
			return "user/login";
		}
		
		//跳转到登录页面
		return "redirect:login";
	}
	
	
	
	
	/**
	 * 跳转登录页面
	 */
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		return "user/login";
	}
	
	
	
	
	/**
	 * 接受登录界面的请求
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public String login(HttpServletRequest request,HttpServletResponse response,User user){
		String pwd = new String(user.getPassword());
		
		User loginUser = userService.login(user);
		
		//登陆失败
		if(loginUser == null){
			request.setAttribute("error", "用户名");
			return "/user/login";
		}
		
		//登录成功，用户信息存放到session当中
		request.getSession().setAttribute(CmsContant.USER_KEY, loginUser);
		
		//保存用户的用户名密码
		Cookie cookieUserName = new Cookie("username", user.getUsername());
		cookieUserName.setPath("/");
		cookieUserName.setMaxAge(10*24*3600);
		response.addCookie(cookieUserName);
		Cookie cookieUserPwd = new Cookie("userpwd", pwd);
		cookieUserPwd.setPath("/");
		cookieUserPwd.setMaxAge(10*24*3600);
		response.addCookie(cookieUserPwd);
		
		//进入管理界面
		if(loginUser.getRole()==CmsContant.USER_ROLE_ADMIN){
			return "redirect:/admin/index";
		}
		
		//进入个人中心
		return "redirect:/user/home";
	}
	
	
	
	
	/**
	 * @param username
	 * @return
	 */
	@RequestMapping("checkname")
	@ResponseBody
	public boolean checkUserName(String username){
		User userByUsername = userService.getUserByUsername(username);
		return userByUsername == null;
	}
	
	
	@RequestMapping("deletearticle")
	@ResponseBody
	public boolean deleteArticle(Integer id){
		int result = articleService.delete(id);
		return result > 0;
	}
	
	//
	@RequestMapping("articles")
	public String articles(HttpServletRequest request,@RequestParam(defaultValue="1")int page){
		
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		System.out.println(loginUser.getId());
		PageInfo<Article> articlePage = articleService.listByUser(loginUser.getId(), page);
		System.out.println(articlePage);
		request.setAttribute("articlePage", articlePage);
		
		return "user/article/list";
	}
	
	@RequestMapping("comments")
	public String comments(){
		return "user/comment/list";
	}
	
	/**
	 * 跳转到发布文章的页面
	 * @param request
	 * @return
	 */
	@RequestMapping("postArticle")
	public String postArticle(HttpServletRequest request){
		List<Channel> channels = articleService.getAllChannels();
		request.setAttribute("channels", channels);
		return "user/article/post";
	}
	
	/**
	 * 跳转到修改文章的页面
	 * @return
	 */
	@RequestMapping(value="updateArticle",method=RequestMethod.GET)
	public String updateArticle(HttpServletRequest request,Integer id){
		
		//获取栏目
		List<Channel> allChannels = articleService.getAllChannels();
		request.setAttribute("channels", allChannels);
		
		//获取文章
		Article article = articleService.getById(id);
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		if(loginUser.getId() != article.getUserId()){
			//to do 做好异常处理的！！
		}
		request.setAttribute("article", article);
		request.setAttribute("contentl", HtmlUtils.htmlspecialchars(article.getContent()));
		
		return "user/article/update";
	}
	
	/**
	 * 获取分类
	 * @param cid
	 * @return
	 */
	@RequestMapping("getCategoris")
	@ResponseBody
	public List<Category> getCategoris(Integer cid){
		List<Category> categoriByCid = articleService.getCategoriByCid(cid);
		return categoriByCid;
	}
	
	@RequestMapping(value="postArticle",method=RequestMethod.POST)
	@ResponseBody
	public boolean postArticle(HttpServletRequest request,Article article,MultipartFile file){
		String picUrl = "";
		
		//处理上传文件
		try {
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//当前用户是文章的作者
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		article.setUserId(loginUser.getId());
		
		return articleService.add(article)>0;
	}
	
	/**
	 * 接受修改文章的页面提交的数据
	 * @param request
	 * @param article
	 * @param file
	 * @return
	 */
	@RequestMapping(value="updateArticle",method=RequestMethod.POST)
	@ResponseBody
	public boolean updateArticle(HttpServletRequest request,Article article,MultipartFile file){
		
		System.out.println("article is " + article);
		String picUrl = "";
		try {
			picUrl = processFile(file);
			article.setPicture(picUrl);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//当前文章用户是文章的作者
		User loginUser = (User) request.getSession().getAttribute(CmsContant.USER_KEY);
		int update = articleService.update(article, loginUser.getId());
		
		return update > 0;
	}
	
	
}
