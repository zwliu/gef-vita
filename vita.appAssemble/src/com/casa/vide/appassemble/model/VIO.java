package com.casa.vide.appassemble.model;

import java.util.Set;

import com.casa.vide.appassemble.modelinterface.IElement;

public class VIO extends Node implements IElement {

	/**　VIO图元名称*/
	private String name;
	
	/**　VIO图元实例名*/
	private String instanceName;
	
	/**　VIO图元所在VDL文件名*/
	private String vdlName;
	
	/**　VIO图元的事件类型*/
	private ModelEventType type = ModelEventType.UNDEFINE;
	
	/**　VIO图元所在的VOM图元模型*/
	private VOM parent = null;
	
	/**　实例化的VIO图元个数*/
	private static int vcount = 0;
	
	/**  创建空的VIO图元*/
	public VIO() {
		this(null, null, null);
	}
	
	/**
	 * 创建名称为name， 实例名为instanceName，VDL文件名为vdlName的VIO图元
	 *
	 * @param name VIO图元名称
	 * @param instancName VIO图元实例名
	 * @param vdlName VIO图元所在VDL文件名
	 */
	public VIO(String name, String instancName, String vdlName) {
		this.name = name;
		this.instanceName = name;
		this.vdlName = vdlName;
		vcount ++;
	}
	
	/**
	 * 获取实例化的VIO图元个数
	 *
	 * @return 实例化的VIO图元个数
	 */
	public static int getCount() {
		return vcount;
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
		return parent.getVIOs();
	}
}
