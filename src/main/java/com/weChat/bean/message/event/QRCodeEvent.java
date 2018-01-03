package com.weChat.bean.message.event;

/**
 * 事件-扫描二维码
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class QRCodeEvent extends BaseEvent {

	// 事件KEY值
	private String EventKey;
	// 用于换取二维码图片
	private String Ticket;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

	public String getTicket() {
		return Ticket;
	}

	public void setTicket(String ticket) {
		Ticket = ticket;
	}

}
