package com.java.leo.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用的返回的类
 */
public class Msg {
	//状态码   200-成功 
	private int code;
	//提示信息
	private String msg;
	
	//用户要返回给浏览器的数据
	private Map<String, Object> data = new HashMap<>();

	public static Msg success(){
		Msg result = new Msg();
		result.setCode(200);
		result.setMsg("处理成功！");
		return result;
	}
	
	public static Msg fail(){
		Msg result = new Msg();
		result.setCode(400);
		result.setMsg("处理失败！");
		return result;
	}
	
	public static Msg fail(String string){
		Msg result = new Msg();
		result.setCode(400);
		result.setMsg(string);
		return result;
	}
	
	public static Msg NotFound(){
		Msg result = new Msg();
		result.setCode(404);
		result.setMsg("未找到资源！");
		return result;
	}
	
	public static Msg NotFound(String string){
		Msg result = new Msg();
		result.setCode(404);
		result.setMsg(string);
		return result;
	}
	
	public static Msg error(int code, String string){
		Msg result = new Msg();
		result.setCode(code);
		result.setMsg(string);
		return result;
	}
	
	public Msg add(String key, Object value){
		this.getData().put(key, value);
		return this;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	
	
}
