package com.yangjinjing.demo;

import java.util.HashMap;
import java.util.Map;

/** 
* @author 作者:杨今敬
* @version 创建时间：2019年12月17日 下午1:30:37
* 类功能说明 
*/

public class ResponseData {
	private  final String message;
	private  final int code;
	private  final Map<String, Object> data = new HashMap<String, Object>();
	
	public String getMessage() {
		return message;
	}
	public int getCode() {
		return code;
	}
	public Map<String, Object> getData() {
		return data;
	}
	/**
	 * @param message
	 * @param code
	 */
	public ResponseData(int code,String message) {
		this.message = message;
		this.code = code;
	}
	
	public static ResponseData ok() {
		return new ResponseData(200, "ok");
	}
	public static ResponseData notFound() {
		return new ResponseData(404, "notFound");
	}
	public static ResponseData badRequest() {
		return new ResponseData(400, "badRequest");
	}
	public static ResponseData forbidden() {
		return new ResponseData(403, "forbidden");
	}
	public static ResponseData unauthorized() {
		return new ResponseData(401, "unauthorized");
	}
	public static ResponseData serverInternalError() {
		return new ResponseData(500, "serverInternalError");
	}
	public static ResponseData customerError() {
		return new ResponseData(1001, "customerError");
	}
	
	
	
	
	
}
