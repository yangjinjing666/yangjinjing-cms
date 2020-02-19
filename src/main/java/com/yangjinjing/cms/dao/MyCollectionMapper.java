package com.yangjinjing.cms.dao;

import java.util.List;

import javax.xml.ws.soap.Addressing;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.yangjinjing.cms.entity.Article;
import com.yangjinjing.cms.entity.ArticleCollection;

/**
* @author 作者:杨今敬
* @version 创建时间：2020年2月18日 上午10:03:38
* 类功能说明 
*/
public interface MyCollectionMapper {
	
	@Select("select * from article_collection where user_id = #{uid} ORDER BY CREATED DESC")
	List<ArticleCollection> collectionList(Integer uid);
	
	
	@Insert("insert into article_collection set article_id=#{article_id},user_id=#{user_id},article_text=#{article_text},created=NOW()")
	void add(ArticleCollection article);
	
	@Delete("delete from article_collection where id = #{id}")
	int del(Integer id);
	
}
