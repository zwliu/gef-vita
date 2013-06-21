package com.casa.vide.appassemble.model;

/**
 * 可编辑Label的模型
 *
 * @author lzw
 */
public class EditableLabelModel extends Node {

	/** EditableLabel的文字*/
	private String text;
	
	public static String PROPERTY_TEXT = "CHANGE TEXT";
	
	/**
	 * 获取EditableLabel的文字
	 *
	 * @return
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置EditableLabel的文字
	 *
	 * @param text
	 */
	public void setText(String text) {
		String old = this.text;
		this.text = text;
		listeners.firePropertyChange(PROPERTY_TEXT, old, text);
	}
	
}
