package com.casa.vide.appassemble.model;

import com.casa.vide.appassemble.modelinterface.IBasicElement;

public class VIO extends Node implements IBasicElement {

	private String name;
	private String instanceName;
	private String vdlName;
	
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
		this.instanceName = instanceName;
	}
	public String getVdlName() {
		return vdlName;
	}
	public void setVdlName(String vdlName) {
		this.vdlName = vdlName;
	}
}
