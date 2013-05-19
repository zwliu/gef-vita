package com.casa.vide.appassemble.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertySource;

import com.casa.vide.appassemble.config.ColorConstant;

public class Node implements IAdaptable {

	private List<Object> children = new ArrayList<Object>();
	private Object layout = new Rectangle();
	private Color color;// = APP.createRandomColor();
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	public static final String PROPERTY_LAYOUT = "NodeLayout";
	public static final String PROPERTY_ADD = "NodeAddChild";
	public static final String PROPERTY_REMOVE = "NodeRemoveChild";
	public static final String PROPERTY_COLOR = "Color";
	//属性名
	public static final String ATTRIBUTE_POSITION_X = "x";
	public static final String ATTRIBUTE_POSITION_Y = "y";
	public static final String ATTRIBUTE_WIDTH = "width";
	public static final String ATTRIBUTE_HEIGHT = "height";
	public static final String ATTRIBUTE_INDEX = "index";
	
	{
		if(this instanceof VIO)
			color = ColorConstant.VIO_COLOR;
		else if(this instanceof Message)
			color = ColorConstant.Message_COLOR;
		else if(this instanceof VOM)
			color = ColorConstant.VOM_COLOR;
		else if(this instanceof APP)
			color = ColorConstant.APP_COLOR;
		else
			color = APP.createRandomColor();
	}
	
	public boolean addChild(Object child) {
		boolean b = children.add(child);
		if(b)
			listeners.firePropertyChange(PROPERTY_ADD, null, child);
		return b;
	}
	
	public boolean removeChild(Object child) {
		boolean b = children.remove(child);
		if(b)
			listeners.firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}
	
	public List<Object> getChildren() {
		return children;
	}
	
	public void setLayout(Object rec) {
		Object old = layout;
		layout = rec;
		listeners.firePropertyChange(PROPERTY_LAYOUT, old, layout);
	}
	
	public Object getLayout() {
		return layout;
	}
	
	public boolean contains(Node child) { 
		return children.contains(child); 
	}
	
	public PropertyChangeSupport getListeners() {
		return listeners;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		// TODO Auto-generated method stub
		if(adapter == IPropertySource.class)
			return new NodePropertySource(this);
		return null;
	}
	
	public Map<Object, Object> getAttributes() {
		Map<Object, Object> m = new HashMap<Object, Object>();
		if(layout instanceof Rectangle) {
			m.put(ATTRIBUTE_POSITION_X, String.valueOf(((Rectangle)layout).x));
			m.put(ATTRIBUTE_POSITION_Y, String.valueOf(((Rectangle)layout).y));
			m.put(ATTRIBUTE_WIDTH, String.valueOf(((Rectangle)layout).width));
			m.put(ATTRIBUTE_HEIGHT, String.valueOf(((Rectangle)layout).height));
		}
		return m;
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
