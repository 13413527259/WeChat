package com.weChat.util;

public enum TokenInfo {
	
	APPID("wx9821aff994e422d9"),
	APPSECRET("ed77218b9de588780a92c8cd4da503d7");
	
//	APPID("wxdbe78f60a273e8ea"),
//	APPSECRET("f73dc26745930df92a13d297a50982a9");

	private String value;
	private TokenInfo(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
