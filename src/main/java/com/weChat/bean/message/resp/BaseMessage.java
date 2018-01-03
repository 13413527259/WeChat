package com.weChat.bean.message.resp;

/**
 * 响应消息-基类
 * 
 * @author 13413527259
 *
 */
public class BaseMessage {

	// 接收方帐号（收到的OpenID）
	private String ToUserName;

	// 开发者微信号
	private String FromUserName;

	// 发送时间
	private long CreateTime;

	// 消息类型(text/image/location/link)
	private String MsgType;

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

	public BaseMessage(String toUserName, String fromUserName, long createTime, String msgType) {
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	}

	public BaseMessage() {
	}

}
