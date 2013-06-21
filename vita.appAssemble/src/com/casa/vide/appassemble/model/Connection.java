package com.casa.vide.appassemble.model;

import org.eclipse.draw2d.ConnectionAnchor;

/**
 * 实验系统建模中的连线模型
 *
 * @author lzw
 */
public class Connection {

	/** 作为源连接的实体图元的模型*/
	private Shape source;
	
	/** 作为目标连接的实体图元的模型*/
	private Shape target;
	
	/** 源连接的连接锚点*/
	private ConnectionAnchor sourceAnchor;
	
	/** 目标连接的连接锚点*/
	private ConnectionAnchor targetAnchor;
	
	/** 创建空连线*/
	public Connection() {
		this(null, null, null, null);
	}
	
	/**
	 * 创建源连接为source，目标连接为target的连线
	 *
	 * @param source 源连接的实体图元的模型
	 * @param target 目标连接的实体图元的模型
	 */
	public Connection(Shape source, Shape target) {
		this(source, target, null, null);
	}
	
	/**
	 * 创建源连接为source，目标连接为target的连线，源连接锚点为sourceAnchor，目标连接锚点为
	 * targetAnchor的连线
	 *
	 * @param source 源连接的实体图元的模型
	 * @param target 目标连接的实体图元的模型
	 * @param sourceAnchor 源连接锚点
	 * @param targetAnchor 目标连接锚点
	 */
	public Connection(Shape source, Shape target, 
			ConnectionAnchor sourceAnchor, ConnectionAnchor targetAnchor) {
		setSource(source);
		setTarget(target);
		this.sourceAnchor = sourceAnchor;
		this.targetAnchor = targetAnchor;
	}
	
	/**
	 * 获取源连接的实体图元的模型
	 *
	 * @return 源连接的实体图元的模型
	 */
	public Shape getSource() {
		return source;
	}

	/**
	 * 设置源连接的实体图元的模型
	 *
	 * @param source 源连接的实体图元的模型
	 */
	public void setSource(Shape source) {
		this.source = source;
		if(source != null)
			source.addSourceConnection(this);
	}

	/**
	 * 获取目标连接的实体图元的模型
	 *
	 * @return 目标连接的实体图元的模型
	 */
	public Shape getTarget() {
		return target;
	}

	/**
	 * 设置目标连接的实体图元的模型
	 *
	 * @param target 目标连接的实体图元的模型
	 */
	public void setTarget(Shape target) {
		this.target = target;
		if(target != null)
			target.addTargetConnection(this);
	}
	
	/**
	 * 移除源连接
	 */
	public void detachSource() {
		if(source != null)
			source.removeSourceConnection(this);
	}
	
	/**
	 * 移除目标连接
	 */
	public void detachTarget() {
		if(target != null)
			target.removeTargetConnection(this);
		
	}
	
	/**
	 * 获取源连接锚点
	 *
	 * @return 返回源连接锚点
	 */
	public ConnectionAnchor getSourceAnchor() {
		return sourceAnchor;
	}

	/**
	 * 设置源连接锚点
	 *
	 * @param sourceAnchor 源连接锚点
	 */
	public void setSourceAnchor(ConnectionAnchor sourceAnchor) {
		this.sourceAnchor = sourceAnchor;
	}

	/**
	 * 获取目标连接锚点
	 *
	 * @return 目标连接锚点
	 */
	public ConnectionAnchor getTargetAnchor() {
		return targetAnchor;
	}

	/**
	 * 设置目标连接锚点
	 *
	 * @param targetAnchor 目标连接锚点
	 */
	public void setTargetAnchor(ConnectionAnchor targetAnchor) {
		this.targetAnchor = targetAnchor;
	}
	
}
