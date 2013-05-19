package com.casa.vide.appassemble.model;

import java.util.Set;

import com.casa.vide.appassemble.modelinterface.IElement;

public class VIO extends Node implements IElement {

	private String name;
	private String instanceName;
	private String vdlName;
	private ModelEventType type = ModelEventType.UNDEFINE;
	private VOM parent = null;
	private static int vcount = 0;
	
	public VIO() {
		this(null, null, null);
	}
	
	public VIO(String name, String instancName, String vdlName) {
		this.name = name;
		this.instanceName = name;
		this.vdlName = vdlName;
		vcount ++;
	}
	
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
