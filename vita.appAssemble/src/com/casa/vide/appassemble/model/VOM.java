package com.casa.vide.appassemble.model;

import org.eclipse.swt.graphics.Color;

public class VOM extends Node {
	
	public static final String PROPERTY_NAME = "VomName";
	public static final String PROPERTY_COLOR = "VomColor";
	private String name;
	private Color color;
	
	public VOM() {
		name = null;
		color = null;
	}
	
	public VOM(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		listeners.firePropertyChange(PROPERTY_COLOR, oldColor, color);
	}
	
	public Color getColor() {
		return color;
	}
	
}
