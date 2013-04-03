package com.casa.vide.appassemble.model;

public class VitaEvent extends Node {
	
	public static String PROPERTY_TYPE = "Event Type";
	private String type = null;
	
	public VitaEvent(String type) {
		this.type = type;
	}
	
	public void setType(String type) {
		String oldType = this.type;
		this.type = type;
		listeners.firePropertyChange(PROPERTY_TYPE, oldType, type);
	}
	
	public String getType() {
		return type;
	}
}
