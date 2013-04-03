package com.casa.vide.appassemble.model;

import java.util.ArrayList;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class NodePropertySource implements IPropertySource {
	
	private Node node = null;
	
	public NodePropertySource(Node node) {
		this.node = node;
	}

	@Override
	public Object getEditableValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		ArrayList<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		if(node instanceof APP) {
			properties.add(new TextPropertyDescriptor(APP.PROPERTY_NAME, "name"));
			properties.add(new ColorPropertyDescriptor(APP.PROPERTY_COLOR, "color"));
		}
		else if(node instanceof VOM) {
			properties.add(new PropertyDescriptor(VOM.PROPERTY_NAME, "name"));
			properties.add(new ColorPropertyDescriptor(VOM.PROPERTY_COLOR, "color"));
		}
		return properties.toArray(new IPropertyDescriptor[0]);
	}

	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if(id.equals(APP.PROPERTY_NAME))
			return ((APP)node).getName();
		else if(id.equals(APP.PROPERTY_COLOR)) {
			Color color = ((APP)node).getColor();
			return color == null ? null : color.getRGB();
		}
		else if(id.equals(VOM.PROPERTY_NAME))
			return ((VOM)node).getName();
		else if(id.equals(VOM.PROPERTY_COLOR)) {
			Color color = ((VOM)node).getColor();
			return color == null ? null : color.getRGB();
		}
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if(id.equals(APP.PROPERTY_NAME))
			((APP)node).setName((String)value);
		else if(id.equals(APP.PROPERTY_COLOR))
			((APP)node).setColor(new Color(null, (RGB)value));
		else if(id.equals(VOM.PROPERTY_NAME))
			((VOM)node).setName((String)value);
		else if(id.equals(VOM.PROPERTY_COLOR))
			((VOM)node).setColor(new Color(null, (RGB)value));
	}

}
