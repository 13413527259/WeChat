package com.weChat.bean.message.event;

/**
 * 事件消息-基类
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class BaseEvent {

	// 开发者微信号
	private String ToUserName;
	// 发送方帐号(OpenId)
	private String FromUserName;
	// 消息创建时间 
	private long CreateTime;
	// 消息类型(event)
	private String MsgType;
	// 事件类型(subscribe/unsubscribe/SCAN/LOCATION/CLICK/VIEW)
	private String Event;

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public long getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}

}
