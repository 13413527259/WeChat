package com.weChat.util;

public enum MethodType {
	
	GET("get"),
	POST("post"),
	PUT("put"),
	DELETE("delete");

	private String value;
	private MethodType(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

}
