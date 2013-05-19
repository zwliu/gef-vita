package com.casa.vide.appassemble.model;

public class EditableLabelModel extends Node {

	private String text;
	public static String PROPERTY_TEXT = "CHANGE TEXT";
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		String old = this.text;
		this.text = text;
		listeners.firePropertyChange(PROPERTY_TEXT, old, text);
	}
	
}
