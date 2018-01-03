package com.weChat.bean.menu;

/**
 * 菜单按钮-父菜单
 * 
 * @author 陈俊华
 * @date 2017年12月14日
 */
public class ParentButton extends BaseButton {

	// 子菜单集
	private BaseButton[] sub_button;

	public BaseButton[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(BaseButton[] sub_button) {
		this.sub_button = sub_button;
	}

}
