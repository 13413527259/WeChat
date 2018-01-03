package com.weChat.util;

/**
 * 消息类型-枚举
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public enum MessageType {

	// 请求信息类型-文本
	REQ_MESSAGE_TYPE_TEXT("text"),
	// 请求信息类型-图片
	REQ_MESSAGE_TYPE_IMAGE("image"),
	// 请求信息类型-语音
	REQ_MESSAGE_TYPE_VOICE("voice"),
	// 请求信息类型-视频
	REQ_MESSAGE_TYPE_VIDEO("video"),
	// 请求信息类型-位置
	REQ_MESSAGE_TYPE_LOCATION("location"),
	// 请求信息类型-链接
	REQ_MESSAGE_TYPE_LINK("link"),

	// 响应信息类型-文本
	RESQ_MESSAGE_TYPE_TEXT("text"),
	// 响应信息类型-图片
	RESQ_MESSAGE_TYPE_IMAGE("image"),
	// 响应信息类型-语音
	RESQ_MESSAGE_TYPE_VOICE("voice"),
	// 响应信息类型-视频
	RESQ_MESSAGE_TYPE_VIDEO("video"),
	// 响应信息类型-位置
	RESQ_MESSAGE_TYPE_MUSIC("music"),
	// 响应信息类型-图文
	RESQ_MESSAGE_TYPE_NEWS("news"),;

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private MessageType() {
	}

	private MessageType(String value) {
		this.value = value;
	}

}
