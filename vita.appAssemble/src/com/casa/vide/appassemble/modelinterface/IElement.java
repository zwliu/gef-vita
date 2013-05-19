package com.casa.vide.appassemble.modelinterface;

import java.util.Set;

import com.casa.vide.appassemble.model.VOM;

public interface IElement extends IBasicElement {

	String ATRRIBUTE_NAME = "element name";
	String ATTRIBUTE_INSTANCENAME = "element instance name";
	String ATTRIBUTE_VDLNAME = "vdl name";
	String ATTRIBUTE_EVENTTYPE = "event type";
	
	ModelEventType getType();
	void setType(ModelEventType type);
	void setName(String name);
	void setInstanceName(String instanceName);
	void setVdlName(String vdlName);
	void setParent(VOM parent);
	Set<String> getNames();
	
	enum ModelEventType {
		SUBSCRIPTION("订"),
		PUBLISH("发"),
		UNDEFINE("?");
		public String type;
		
		private ModelEventType(String type) {
			this.type = type;
		}
		
		public String toString() {
			return type;
		}
	}
}
