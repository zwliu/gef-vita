package com.casa.vide.appassemble.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.ui.views.properties.IPropertySource;

public class Node implements IAdaptable {

	private List<Object> children = new ArrayList<Object>();
	private Object layout = new Rectangle();
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	public static final String PROPERTY_LAYOUT = "NodeLayout";
	public static final String PROPERTY_ADD = "NodeAddChild";
	public static final String PROPERTY_REMOVE = "NodeRemoveChild";
	
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

}
