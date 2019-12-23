package com.yangjinjing.cms.commen;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月16日 下午6:27:22
* 类功能说明 
*/
public class FileResult {
	
	int error = 0;
	String url = "";
	/**
	 * @param error
	 * @param url
	 */
	public FileResult(int error, String url) {
		this.error = error;
		this.url = url;
	}
	@Override
	public String toString() {
		return "FileResult [error=" + error + ", url=" + url + "]";
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
	
	
}
