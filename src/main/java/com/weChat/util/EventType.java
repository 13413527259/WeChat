package com.weChat.util;

/**
 * 事件类型-枚举
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public enum EventType {

	// 请求消息类型：事件推送
	REQ_MESSAGE_TYPE_EVENT("event"),
	// 事件类型：subscribe(订阅)
	EVENT_TYPE_SUBSCRIBE("subscribe"),
	// 事件类型：unsubscribe(取消订阅)
	EVENT_TYPE_UNSUBSCRIBE("unsubscribe"),
	// 事件类型：scan(用户已关注时的扫描带参数二维码)
	EVENT_TYPE_SCAN("SCAN"),
	// 事件类型：LOCATION(上报地理位置)
	EVENT_TYPE_LOCATION("LOCATION"),
	// 事件类型：CLICK(自定义菜单)
	EVENT_TYPE_CLICK("CLICK");

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private EventType() {

	}

	private EventType(String value) {
		this.value = value;
	}

}
