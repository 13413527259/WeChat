package com.weChat.bean.message.req;

/**
 * 请求消息-基类
 * 
 * @author 13413527259
 *
 */
public class BaseMessage {

	// 开发者微信号
	private String ToUserName;

	// 发送方账号(OpenID)
	private String FromUserName;

	// 发送时间
	private long CreateTime;

	// 消息类型(text/image/location/link)
	private String MsgType;

	// 消息id
	private long MsgId;

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

	public long getMsgId() {
		return MsgId;
	}

	public void setMsgId(long msgId) {
		MsgId = msgId;
	}

	public BaseMessage(String toUserName, String fromUserName, long createTime, String msgType, long msgId) {
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
		MsgId = msgId;
	}

	public BaseMessage() {
	}

}
