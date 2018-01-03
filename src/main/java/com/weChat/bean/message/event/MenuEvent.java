package com.weChat.bean.message.event;

/**
 * 事件-菜单
 * 
 * @author 陈俊华
 * @date 2017年12月13日
 */
public class MenuEvent extends BaseEvent {

	// 事件KEY值，与自定义菜单接口中KEY值对应
	private String EventKey;

	public String getEventKey() {
		return EventKey;
	}

	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}

}
