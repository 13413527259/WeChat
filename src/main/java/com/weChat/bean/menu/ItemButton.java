package com.weChat.bean.menu;

/**
 * 菜单按钮-子菜单
 * 
 * @author 陈俊华
 * @date 2017年12月14日
 */
public class ItemButton extends BaseButton {

	// 菜单的响应动作类型
	private String type;

	// 菜单KEY值，用于消息接口推送
	private String key;
	
	private String url;
	
//	private String media_id;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
