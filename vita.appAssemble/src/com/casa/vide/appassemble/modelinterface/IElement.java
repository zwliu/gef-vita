package com.casa.vide.appassemble.modelinterface;

import java.util.Set;

import com.casa.vide.appassemble.model.VOM;

/**
 * VIO和Message共同的接口，相对IBasicElement多了VIO/Message图元的事件类型（发布、订阅）
 * 以及设置VIO/Message图元属性的方法。
 *
 * @author lzw
 */
public interface IElement extends IBasicElement {

	String ATRRIBUTE_NAME = "element name";
	String ATTRIBUTE_INSTANCENAME = "element instance name";
	String ATTRIBUTE_VDLNAME = "vdl name";
	String ATTRIBUTE_EVENTTYPE = "event type";
	
	/** 获取VIO/Message图元的事件类型*/
	ModelEventType getType();
	
	/** 设置VIO/Message图元的事件类型*/
	void setType(ModelEventType type);
	
	/** 设置VIO/Message图元的名称*/
	void setName(String name);
	
	/** 设置VIO/Message图元的实例名*/
	void setInstanceName(String instanceName);
	
	/** 设置VIO/Message图元的VdlName*/
	void setVdlName(String vdlName);
	
	/** 设置VIO/Message图元的父图元（VOM图元）的模型*/
	void setParent(VOM parent);
	
	/** 获取VIO/Message图元可取的名称集合，VIO/Message图元的名称只能是该集合中的一个*/
	Set<String> getNames();
	
	/**
	 * VIO/Message图元的事件类型
	 *
	 * @author lzw
	 */
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
