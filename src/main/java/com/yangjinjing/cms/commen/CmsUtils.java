package com.yangjinjing.cms.commen;

import org.apache.commons.codec.digest.DigestUtils;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午6:25:32
* 类功能说明 
*/
public class CmsUtils {
	
	public static String encry(String src,String salt){
		return DigestUtils.md5Hex(salt + src + salt);
	}
	
}
