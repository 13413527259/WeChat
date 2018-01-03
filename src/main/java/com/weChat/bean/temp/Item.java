package com.weChat.bean.temp;

/**
 * 模板(数据-颜色)-bean
 * 
 * @author 陈俊华
 * @date 2017年12月21日
 */
public class Item {

	private String value;

	private String color;

	public Item() {
	}

	public Item(String value, String color) {
		this.value = value;
		this.color = color;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
