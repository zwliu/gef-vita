package com.casa.vide.appassemble.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

/**
 * 实体图元模型
 *
 * @author lzw
 */
public class Shape extends Node {

	/** 形状类型*/
	private ShapeType type;
	
	/** 背景图片*/
	private Image background;
	
	/** 从该实体图元出发的连接*/
	private List<Object> sourceConnections = new ArrayList<Object>();
	
	/** 连接该实体图元的连接*/
	private List<Object> targetConnections = new ArrayList<Object>();
	
	public static String PROERTY_SOURCECONNECTION = "SOURCE_CONN";
	public static String PROERTY_TARGETCONNECTION = "TARGET_CONN";
	
	public static final String PROPERTY_BACKGROUND = "ShapeBackground";
	
	/** 形状类型*/
	public enum ShapeType {
		TYPE_NONE,
		TYPE_RECTANGLE,
		TYPE_TRIANGLE,
		TYPE_CYCLE,
		TYPE_ELLIPSE,
		TYPE_POLYGON,
	};
	
	/** 创建形状类型为ShapeType.TYPE_NONE的实体图元*/
	public Shape() {
		this(ShapeType.TYPE_NONE);
	}
	
	/** 创建形状类型为type的实体图元*/
	public Shape(ShapeType type) {
		this(type, null);
	}
	
	/** 创建形状类型为type，背景图片为background的实体图元*/
	public Shape(ShapeType type, Image background) {
		this.type = type;
		this.background = background;
	}

	/** 获取背景图片*/
	public Image getBackground() {
		return background;
	}

	/** 设置背景图片*/
	public void setBackground(Image background) {
		Image old = this.background;
		this.background = background;
		listeners.firePropertyChange(PROPERTY_BACKGROUND, old, background);
	}

	/**
	 * 获取实体图元的形状类型
	 *
	 * @return 实体图元的形状类型
	 */
	public ShapeType getType() {
		return type;
	}

	/**
	 * 设置实体图元的形状类型
	 *
	 * @param type 实体图元的形状类型
	 */
	public void setType(ShapeType type) {
		this.type = type;
	}
	
	/**
	 * 添加从该实体图元出发的连接
	 *
	 * @param s 从该实体图元出发的连接
	 */
	public void addSourceConnection(Connection s) {
		sourceConnections.add(s);
		listeners.firePropertyChange(PROERTY_SOURCECONNECTION, null, s);
	}
	
	/**
	 * 添加连接该实体图元的连接
	 *
	 * @param t 连接该实体图元的连接
	 */
	public void addTargetConnection(Connection t) {
		targetConnections.add(t);
		listeners.firePropertyChange(PROERTY_TARGETCONNECTION, null, t);
	}
	
	/**
	 * 获取所有从该实体图元出发的连接
	 *
	 * @return 所有从该实体图元出发的连接
	 */
	public List<Object> getSourceConnections() {
		return sourceConnections;
	}
	
	/**
	 * 获取所有连接该实体图元的连接
	 *
	 * @return 所有连接该实体图元的连接 
	 */
	public List<Object> getTargetConnections() {
		return targetConnections;
	}
	
	/**
	 * 从sourceConnections中删除 连接conn
	 *
	 * @param conn 要删除的连接
	 */
	public void removeSourceConnection(Object conn) {
		sourceConnections.remove(conn);
		listeners.firePropertyChange(PROERTY_SOURCECONNECTION, null, conn);
	}
	

	/**
	 * 从targetConnections中删除 连接conn
	 *
	 * @param conn 要删除的连接
	 */
	public void removeTargetConnection(Object conn) {
		targetConnections.remove(conn);
		listeners.firePropertyChange(PROERTY_TARGETCONNECTION, null, conn);
	}
	
}
