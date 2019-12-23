package com.yangjinjing.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.yangjinjing.cms.entity.Slide;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月19日 上午11:00:23
* 类功能说明 
*/
/**
 * 轮播图管理
 * @author Legna
 *
 */
public interface SlideMapper {
	
	@Select("select id,title,picture,url from cms_slide order by id")
	List<Slide> slideList();
}
