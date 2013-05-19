package com.casa.vide.appassemble.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public class Shape extends Node {

	private ShapeType type;
	private Image background;
	private List<Object> sourceConnections = new ArrayList<Object>();
	private List<Object> targetConnections = new ArrayList<Object>();
	
	public static String PROERTY_SOURCECONNECTION = "SOURCE_CONN";
	public static String PROERTY_TARGETCONNECTION = "TARGET_CONN";
	
	public static final String PROPERTY_BACKGROUND = "ShapeBackground";
	
	public enum ShapeType {
		TYPE_NONE,
		TYPE_RECTANGLE,
		TYPE_TRIANGLE,
		TYPE_CYCLE,
		TYPE_ELLIPSE,
		TYPE_POLYGON,
	};
	
	public Shape() {
		this(ShapeType.TYPE_NONE);
	}
	
	public Shape(ShapeType type) {
		this(type, null);
	}
	
	public Shape(ShapeType type, Image background) {
		this.type = type;
		this.background = background;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		Image old = this.background;
		this.background = background;
		listeners.firePropertyChange(PROPERTY_BACKGROUND, old, background);
	}

	public ShapeType getType() {
		return type;
	}

	public void setType(ShapeType type) {
		this.type = type;
	}
	
	public void addSourceConnection(Connection s) {
		sourceConnections.add(s);
		listeners.firePropertyChange(PROERTY_SOURCECONNECTION, null, s);
	}
	
	public void addTargetConnection(Connection t) {
		targetConnections.add(t);
		listeners.firePropertyChange(PROERTY_TARGETCONNECTION, null, t);
	}
	
	public List<Object> getSourceConnections() {
		return sourceConnections;
	}
	
	public List<Object> getTargetConnections() {
		return targetConnections;
	}
	
	public void removeSourceConnection(Object conn) {
		sourceConnections.remove(conn);
		listeners.firePropertyChange(PROERTY_SOURCECONNECTION, null, conn);
	}
	
	public void removeTargetConnection(Object conn) {
		targetConnections.remove(conn);
		listeners.firePropertyChange(PROERTY_TARGETCONNECTION, null, conn);
	}
	
}
