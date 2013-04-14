package com.casa.vide.appassemble.model;

import java.util.Vector;

import org.eclipse.swt.graphics.Color;

import com.casa.vide.appassemble.modelinterface.IBasicElement;
import com.casa.vide.appassemble.modelinterface.IVOM;

public class VOM extends Node implements IVOM {
	
	public static final String PROPERTY_NAME = "VomName";
	public static final String PROPERTY_COLOR = "VomColor";
	private String name;
	private String instanceName;
	private Vector<IBasicElement> vioIns;
	private Vector<IBasicElement> vioOuts;
	private Vector<IBasicElement> messageIns;
	private Vector<IBasicElement> messageOuts;
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
	
	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}

	public Vector<IBasicElement> getVIOIns() {
		return vioIns;
	}

	public Vector<IBasicElement> getVIOOuts() {
		return vioOuts;
	}

	public Vector<IBasicElement> getMessageIns() {
		return messageIns;
	}

	public Vector<IBasicElement> getMessageOuts() {
		return messageOuts;
	}
	
}
