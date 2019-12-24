package com.yangjinjing.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.Category;
import com.yangjinjing.cms.entity.Channel;
import com.yangjinjing.cms.entity.Comment;
import com.yangjinjing.cms.entity.Complain;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午7:07:39
* 类功能说明 
*/
public interface ArticleMapper {
	
	/**
	 * 根据用户获取文章的列表
	 * @param id
	 * @return
	 */
	List<Article> listByUser(Integer id);
	
	@Update("update cms_article set deleted = #{status} where id = #{id}")
	int updateStatus(@Param("id")int id,@Param("status")int status);
	
	/**
	 * 获取所有栏目的方法
	 * @return
	 */
	@Select("select id,name from cms_channel")
	List<Channel> getAllChannels();
	
	/**
	 * 根据id  获取分类
	 * @param cid
	 * @return
	 */
	@Select("select * from cms_category where channel_id = #{value}")
	List<Category> getCategorisByCid(int cid);
	
	
	@Insert("insert into cms_article(title,content,picture,channel_id,category_id,user_id,hits,hot,status,deleted,created,updated,commentCnt,articleType)"
			+ " VALUES(#{title},#{content},#{picture},#{channelId},#{categoryId},#{userId},0,0,0,0,now(),now(),0,#{articleType})")
	int add(Article article);
	
	/**
	 * @param id
	 * @return
	 */
	Article findById(int id);
	
	/**
	 * 修改文章内容
	 * @param article
	 * @return
	 */
	@Update("update cms_article SET title=#{title},content=#{content},picture=#{picture},channel_id=#{channelId},category_id=#{categoryId},status=0,updated=now() where id=#{id}")
	int update(Article article);													
	
	/**
	 * 文章列表
	 * @param status 文章状态
	 * @return
	 */
	List<Article> list(int status);
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Select("select * from cms_article where id = #{value}")
	Article getInfoById(int id);
	
	
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("update cms_article SET hot = #{hot} where id = #{myid}")
	int setHot(@Param("myid")int id,@Param("hot")int status);
	
	@Update("update cms_article SEt status=#{myStatus} where id=#{myid}")
	int CheckStatus(@Param("myid")int id,@Param("myStatus") int status);
	
	List<Article> hotList();
	
	List<Article> lastList(int pageSize);
	
	/**
	 * 根据根类和栏目获取文章
	 * @param channleId
	 * @param catId
	 * @return
	 */
	List<Article> getArticles(@Param("channelId") int channleId,@Param("catId")int catId);
	
	@Select("select id,name FROM cms_category where channel_id=#{value}")
	@ResultType(Category.class)
	List<Category> getCategoriesByChannelId(int channleId);
	
	@Insert("insert into cms_comment(articleId,userId,content,created) values(#{articleId},#{userId},#{content},NOW())")
	int addComment(Comment comment);
	
	/**
	 * 增加文章的评论数量
	 * @param id
	 * @return
	 */
	@Update("update cms_article SET commentCnt=commentCnt+1 where id=#{value}")
	int increaseCommentCnt(int id);
	
	@Select("select c.id,c.articleId,c.userId,u.username as userName,c.content,c.created FROM cms_comment as c "
			+ " left join cms_user as u on u.id = c.userid "
			+ " where articleId=#{value} order BY c.created DESC")
	List<Comment> getComments(int articleId);
	
	/**
	 * 
	 * @return
	 */
	@Insert("INSERT INTO cms_complain(article_id,user_id,complain_type,"
			+ "compain_option,src_url,picture,content,email,mobile,created)"
			+ "   VALUES(#{articleId},#{userId},"
			+ "#{complainType},#{complainOption},#{srcUrl},#{picture},#{content},#{email},#{mobile},now())")
	int addComplain(Complain complain);
	

	/**
	 * 
	 * @param articleId
	 */
	@Update("UPDATE cms_article SET complainCnt=complainCnt+1,status=if(complainCnt>10,2,status)  "
			+ " WHERE id=#{value}")
	void increaseComplainCnt(Integer articleId);

	/**
	 * 
	 * @param articleId
	 * @return
	 */
	List<Complain> getComplains(int articleId);
	
	
	
	
}
