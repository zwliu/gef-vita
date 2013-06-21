package com.casa.vide.appassemble.model;

import java.util.Set;

import com.casa.vide.appassemble.modelinterface.IElement;

public class Message extends Node implements IElement {

	/**　Message图元名称*/
	private String name;
	
	/**　Message图元实例名*/
	private String instanceName;
	
	/**　Message图元所在VDL文件名*/
	private String vdlName;
	
	/**　Message图元的事件类型*/
	private ModelEventType type = ModelEventType.UNDEFINE;
	
	/**　Message图元所在的VOM图元模型*/
	private VOM parent = null;
	
	/**　实例化的Message图元个数*/
	private static int mcount = 0;
	
	/**  创建空的Message图元*/
	public Message() {
		this(null, null, null);
	}
	
	/**
	 * 创建名称为name， 实例名为instanceName，VDL文件名为vdlName的Message图元
	 *
	 * @param name Message图元名称
	 * @param instancName Message图元实例名
	 * @param vdlName Message图元所在VDL文件名
	 */
	public Message(String name, String instancName, String vdlName) {
		this.name = name;
		this.instanceName = name;
		this.vdlName = vdlName;
		mcount ++;
	}
	
	/**
	 * 获取实例化的Message图元个数
	 *
	 * @return 实例化的Message图元个数
	 */
	public static int getCount() {
		return mcount;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getInstanceName() {
		return instanceName;
	}
	
	public void setInstanceName(String instanceName) {
		String old = this.instanceName;
		this.instanceName = instanceName;
		listeners.firePropertyChange(IElement.ATTRIBUTE_INSTANCENAME, old, instanceName);
	}
	
	public String getVdlName() {
		return vdlName;
	}
	
	public void setVdlName(String vdlName) {
		this.vdlName = vdlName;
	}
	
	@Override
	public ModelEventType getType() {
		return type;
	}
	
	public void setType(ModelEventType type) {
		ModelEventType old = this.type;
		this.type = type;
		listeners.firePropertyChange(IElement.ATTRIBUTE_EVENTTYPE, old, type);
	}
	
	@Override
	public void setParent(VOM parent) {
		this.parent = parent;
	}

	@Override
	public Set<String> getNames() {
		// TODO Auto-generated method stub
		return parent.getMessages();
	}
	
}
