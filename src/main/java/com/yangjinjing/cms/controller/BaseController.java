package com.yangjinjing.cms.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.yangjinjing.cms.utils.FileUtils;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月22日 下午7:48:43
* 类功能说明 
*/
public class BaseController {
	
	@Value("${upload.path}")
	String picRootPath;
	
	@Value("${pic.path}")
	String picUrl;
	
	
	protected String processFile(MultipartFile file) throws IllegalStateException, IOException {
		
		//判断目标目录是否存在
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String subPath = sdf.format(new Date());
		//图片存放的路径
		File path = new File(picRootPath+"/"+subPath);
		//路径不存在则创建
		if(!path.exists()){
			path.mkdirs();
		}
		
		//计算新的文件名称
		String suffixName = FileUtils.getSuffixName(file.getOriginalFilename());
		
		//随机生成文件名
		String fileName = UUID.randomUUID().toString() + suffixName;
		//文件另存
		file.transferTo(new File(picRootPath+"/"+subPath+"/"+fileName));
		
		return subPath + "/" + fileName;
	}
	
}
