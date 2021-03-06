package com.casa.vide.appassemble.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertySource;

import com.casa.vide.appassemble.config.ColorConstant;

/**
 * 图元基本模型，包含图元的孩子、颜色、constraint，实现图元的resize，移动，修改颜色，添加删除孩子功能
 *
 * @author lzw
 */
public class Node implements IAdaptable {

	/** 子图元的模型集合*/
	private List<Object> children = new ArrayList<Object>();
	
	/** 图元的约束*/
	private Object layout = new Rectangle();
	
	/** 图元背景颜色*/
	private Color color;// = APP.createRandomColor();
	
	/** 属性页*/
	private NodePropertySource property = new NodePropertySource(this);
	
	/** 监听器*/
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
	
	/**
	 * 添加子图元模型
	 *
	 * @param child 子图元模型
	 * @return 添加成功，返回true；添加失败，返回false
	 */
	public boolean addChild(Object child) {
		boolean b = children.add(child);
		if(b)
			listeners.firePropertyChange(PROPERTY_ADD, null, child);
		return b;
	}
	
	/**
	 * 删除子图元模型
	 *
	 * @param child 子图元模型
	 * @return 删除成功，返回true；删除失败，返回false
	 */
	public boolean removeChild(Object child) {
		boolean b = children.remove(child);
		if(b)
			listeners.firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}
	
	/**
	 * 获取所有子图元模型
	 *
	 * @return 所有子图元模型
	 */
	public List<Object> getChildren() {
		return children;
	}
	
	/**
	 * 设置图元的约束
	 *
	 * @param rec 图元的约束
	 */
	public void setLayout(Object rec) {
		Object old = layout;
		layout = rec;
		listeners.firePropertyChange(PROPERTY_LAYOUT, old, layout);
	}
	
	/**
	 * 获取图元的约束
	 *
	 * @return 图元的约束
	 */
	public Object getLayout() {
		return layout;
	}
	
	/**
	 * 判断该Node是否包含child
	 *
	 * @param child 孩子Node
	 * @return 包含，返回true；否则，返回false。
	 */
	public boolean contains(Node child) { 
		return children.contains(child); 
	}
	
	/**
	 * 获取PropertyChangeSupport
	 *
	 * @return 返回PropertyChangeSupport
	 */
	public PropertyChangeSupport getListeners() {
		return listeners;
	}
	
	/**
	 * 添加 PropertyChangeListener
	 *
	 * @param listener 要添加的PropertyChangeListener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}
	
	/**
	 * 删除PropertyChangeListener
	 *
	 * @param listener 要删除的PropertyChangeListener
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		// TODO Auto-generated method stub
		if(adapter == IPropertySource.class)
			return property;
		return null;
	}
	
//	public Map<Object, Object> getAttributes() {
//		Map<Object, Object> m = new HashMap<Object, Object>();
//		if(layout instanceof Rectangle) {
//			m.put(ATTRIBUTE_POSITION_X, String.valueOf(((Rectangle)layout).x));
//			m.put(ATTRIBUTE_POSITION_Y, String.valueOf(((Rectangle)layout).y));
//			m.put(ATTRIBUTE_WIDTH, String.valueOf(((Rectangle)layout).width));
//			m.put(ATTRIBUTE_HEIGHT, String.valueOf(((Rectangle)layout).height));
//		}
//		return m;
//	}
	
	/**
	 * 设置背景颜色
	 *
	 * @param color 背景颜色
	 */
	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		listeners.firePropertyChange(PROPERTY_COLOR, oldColor, color);
	}
	
	/**
	 * 获取背景颜色
	 *
	 * @return 背景颜色
	 */
	public Color getColor() {
		return color;
	}

}
