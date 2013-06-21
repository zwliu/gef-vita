package com.casa.vide.appassemble.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ColorPropertyDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.casa.vide.appassemble.modelinterface.IElement;

/**
 * 图元属性页，该类所有图元的属性编辑功能
 *
 * @author lzw
 */
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

	/**
	 * 设置图元属性表
	 */
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		// TODO Auto-generated method stub
		ArrayList<IPropertyDescriptor> properties = new ArrayList<IPropertyDescriptor>();
		if(!(node.getClass() == Node.class)) {
			properties.add(new TextPropertyDescriptor(Node.ATTRIBUTE_POSITION_X, Node.ATTRIBUTE_POSITION_X));
			properties.add(new TextPropertyDescriptor(Node.ATTRIBUTE_POSITION_Y, Node.ATTRIBUTE_POSITION_Y));
			properties.add(new TextPropertyDescriptor(Node.ATTRIBUTE_WIDTH, Node.ATTRIBUTE_WIDTH));
			properties.add(new TextPropertyDescriptor(Node.ATTRIBUTE_HEIGHT, Node.ATTRIBUTE_HEIGHT));
			//properties.add(new TextPropertyDescriptor(Node.ATTRIBUTE_INDEX, Node.ATTRIBUTE_WIDTH));
			properties.add(new ColorPropertyDescriptor(VOM.PROPERTY_COLOR, "color"));
			if(node instanceof APP) {
				properties.add(new TextPropertyDescriptor(APP.PROPERTY_NAME, "name"));
				String[] monitorOpen = {"true", "false"};
				String[] timeRegulation = {"0", "1", "2", "3"};
				properties.add(new ComboBoxPropertyDescriptor(APP.ATTRIBUTE_MONITOROPEN, APP.ATTRIBUTE_MONITOROPEN, monitorOpen));
				properties.add(new ComboBoxPropertyDescriptor(APP.ATTRIBUTE_TIMEREGULATION, APP.ATTRIBUTE_TIMEREGULATION, timeRegulation));
			}
			else if(node instanceof VOM) {
				properties.add(new PropertyDescriptor(VOM.PROPERTY_NAME, "name"));
				properties.add(new TextPropertyDescriptor(VOM.ATTRIBUTE_INSTANCENAME, VOM.ATTRIBUTE_INSTANCENAME));
				Map<String, String> imports = ((VOM)node).getImports();
				String[] values = new String[imports.size()];
				if(imports != null && imports.size() > 0)
				{					
					Iterator<String> keyIterator = imports.keySet().iterator();
					Iterator<String> valueIterator = imports.values().iterator();
					for(int i = 0; i < imports.size(); i++)
					{
						String str = keyIterator.next();
						str += " - ";
						str += valueIterator.next();
						values[i] = str;
					}
				}
				properties.add(new ComboBoxPropertyDescriptor(VOM.PROPERTY_IMPORTS, "imports", values));
			}
			else if(node instanceof IElement) {
				Set<String> names = ((IElement)node).getNames();
				properties.add(new ComboBoxPropertyDescriptor(IElement.ATRRIBUTE_NAME, "name", 
						names == null ? null : names.toArray(new String[names.size()])));
				properties.add(new TextPropertyDescriptor(IElement.ATTRIBUTE_INSTANCENAME, "instance name"));
				properties.add(new PropertyDescriptor(IElement.ATTRIBUTE_VDLNAME, "vdl name"));
			}
		}
		return properties.toArray(new IPropertyDescriptor[0]);
	}

	/**
	 * 获取ID为id的属性的值
	 */
	@Override
	public Object getPropertyValue(Object id) {
		// TODO Auto-generated method stub
		if(id.equals(Node.ATTRIBUTE_POSITION_X)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle)
				return String.valueOf(((Rectangle)obj).x);
			return null;
		}
		else if(id.equals(Node.ATTRIBUTE_POSITION_Y)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle)
				return String.valueOf(((Rectangle)obj).y);
			return null;
		}
		else if(id.equals(Node.ATTRIBUTE_WIDTH)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle)
				return String.valueOf(((Rectangle)obj).width);
			return null;
		}
		else if(id.equals(Node.ATTRIBUTE_HEIGHT)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle)
				return String.valueOf(((Rectangle)obj).height);
			return null;
		}
		else if(id.equals(Node.PROPERTY_COLOR)) {
			Color color = node.getColor();
			return color == null ? null : color.getRGB();
		}
		else if(id.equals(APP.PROPERTY_NAME))
			return ((APP)node).getName();
		else if(id.equals(APP.ATTRIBUTE_MONITOROPEN)) {
			if(((APP)node).getMonitorOpen().equals("true"))
				return 0;
			else if(((APP)node).getMonitorOpen().equals("false"))
				return 1;
			else
				return -1;
		}
		else if(id.equals(APP.ATTRIBUTE_TIMEREGULATION)) {
			try {
				return Integer.parseInt(((APP)node).getTimeRegulation());
			}catch(Exception e) {
				return -1;
			}
		}
		else if(id.equals(VOM.PROPERTY_NAME))
			return ((VOM)node).getName();
		else if(id.equals(VOM.ATTRIBUTE_INSTANCENAME))
			return ((VOM)node).getInstanceName();
		else if(id.equals(VOM.PROPERTY_IMPORTS))
		{
			if(((VOM)node).getImports().size() > 0)
				return 0;
			return -1;
		}
		else if(id.equals(IElement.ATRRIBUTE_NAME)) {
			String name = ((IElement)node).getName();
			Set<String> names = ((IElement)node).getNames();
			if(name != null && names != null) {
				java.util.Iterator<String> iterator = names.iterator();
				for(int i = 0; iterator.hasNext(); i++) {
					if(name.equals(iterator.next()))
						return i;
				}
			}
			return -1;
		}
		else if(id.equals(IElement.ATTRIBUTE_INSTANCENAME))
			return ((IElement)node).getInstanceName();
		else if(id.equals(IElement.ATTRIBUTE_VDLNAME))
			return ((IElement)node).getVdlName();
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

	/**
	 * 设置ID为id的属性的值
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		if(id.equals(Node.ATTRIBUTE_POSITION_X)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle) {
				try {
					int pos = Integer.parseInt((String)value);
					Rectangle recObj = ((Rectangle)obj);
					Rectangle rec = new Rectangle(pos, recObj.y, recObj.width, recObj.height);
					node.setLayout(rec);
				}catch(Exception e) {
					//e.printStackTrace();
				}
			}
		}
		else if(id.equals(Node.ATTRIBUTE_POSITION_Y)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle) {
				try {
					int pos = Integer.parseInt((String)value);
					Rectangle recObj = ((Rectangle)obj);
					Rectangle rec = new Rectangle(recObj.x, pos, recObj.width, recObj.height);
					node.setLayout(rec);
				}catch(Exception e) {
					//e.printStackTrace();
				}
			}
		}
		else if(id.equals(Node.ATTRIBUTE_WIDTH)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle) {
				try {
					int pos = Integer.parseInt((String)value);
					Rectangle recObj = ((Rectangle)obj);
					Rectangle rec = new Rectangle(recObj.x, recObj.y, pos, recObj.height);
					node.setLayout(rec);
				}catch(Exception e) {
					//e.printStackTrace();
				}
			}
		}
		else if(id.equals(Node.ATTRIBUTE_HEIGHT)) {
			Object obj = node.getLayout();
			if(obj instanceof Rectangle) {
				try {
					int pos = Integer.parseInt((String)value);
					Rectangle recObj = ((Rectangle)obj);
					Rectangle rec = new Rectangle(recObj.x, recObj.y, recObj.width, pos);
					node.setLayout(rec);
				}catch(Exception e) {
					//e.printStackTrace();
				}
			}
		}
		else if(id.equals(Node.PROPERTY_COLOR))
			node.setColor(new Color(null, (RGB)value));
		else if(id.equals(APP.PROPERTY_NAME))
			((APP)node).setName((String)value);
		else if(id.equals(APP.ATTRIBUTE_MONITOROPEN)) {
			if(value.equals(0))
				((APP)node).setMonitorOpen("true");
			else if(value.equals(1))
				((APP)node).setMonitorOpen("false");
		}
		else if(id.equals(APP.ATTRIBUTE_TIMEREGULATION)) {
			((APP)node).setTimeRegulation(String.valueOf(value));
		}
		else if(id.equals(VOM.PROPERTY_NAME))
			((VOM)node).setName((String)value);
		else if(id.equals(VOM.ATTRIBUTE_INSTANCENAME))
			((VOM)node).setInstanceName((String)value);
		else if(id.equals(IElement.ATRRIBUTE_NAME)) {
			((IElement)node).setName((String)((IElement)node).getNames().toArray()[((Integer)value).intValue()]);
		}
		else if(id.equals(IElement.ATTRIBUTE_INSTANCENAME))
			((IElement)node).setInstanceName((String)value);
		else if(id.equals(IElement.ATTRIBUTE_VDLNAME))
			((IElement)node).setVdlName((String)value);
	}

}
