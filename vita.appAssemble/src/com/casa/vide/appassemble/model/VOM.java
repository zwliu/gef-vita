package com.casa.vide.appassemble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.casa.vide.appassemble.modelinterface.IBasicElement;
import com.casa.vide.appassemble.modelinterface.IVOM;

public class VOM extends Node implements IVOM {
	
	public static final String PROPERTY_NAME = "VomName";
	public static final String PROPERTY_IMPORTS = "VOM imports";
	public static final String PROPERTY_VIOS = "VOM vios";
	public static final String PROPERTY_MESSAGES = "VOM messages";
	public static final String ATTRIBUTE_NAME = "vom name";
	public static final String ATTRIBUTE_INSTANCENAME = "instance name";
	private String name;
	private String instanceName;
	private String vdlName;
	private Vector<IBasicElement> vioIns = new Vector<IBasicElement>();
	private Vector<IBasicElement> vioOuts = new Vector<IBasicElement>();
	private Vector<IBasicElement> messageIns = new Vector<IBasicElement>();
	private Vector<IBasicElement> messageOuts = new Vector<IBasicElement>();
	private Map<String, String> imports = null;
	private Set<String> vios = null;
	private Set<String> messages = null;
	
	public VOM() {
		name = null;
	}
	
	public VOM(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		String old = this.name;
		this.name = name;
		listeners.firePropertyChange(PROPERTY_NAME, old, name);
	}
	
	public String getName() {
		return name;
	}
	
	public String getInstanceName() {
		return instanceName;
	}

	public void setInstanceName(String instanceName) {
		String old = this.instanceName;
		this.instanceName = instanceName;
		listeners.firePropertyChange(PROPERTY_NAME, old, instanceName);
	}
	
	public String getVdlName() {
		return vdlName;
	}

	public void setVdlName(String vdlName) {
		this.vdlName = vdlName;
	}

	public Vector<IBasicElement> getVIOIns() {
		return vioIns;
	}

	public void setVIOIns(Vector<IBasicElement> vioIns) {
		this.vioIns = vioIns;
	}
	
	public Vector<IBasicElement> getVIOOuts() {
		return vioOuts;
	}

	public void setVIOOuts(Vector<IBasicElement> vioOuts) {
		this.vioOuts = vioOuts;
	}
	
	public Vector<IBasicElement> getMessageIns() {
		return messageIns;
	}

	public void setMessageIns(Vector<IBasicElement> messageIns) {
		this.messageIns = messageIns;
	}

	public Vector<IBasicElement> getMessageOuts() {
		return messageOuts;
	}
	
	public void setMessageOuts(Vector<IBasicElement> messageOuts) {
		this.messageOuts = messageOuts;
	}

	public void addVIOIn(IBasicElement vio) {
		if(!vioIns.contains(vio))
			vioIns.add(vio);
	}
	
	public void removeVIOIn(IBasicElement vio) {
		vioIns.remove(vio);
	}
	
	public void addVIOOut(IBasicElement vio) {
		if(!vioOuts.contains(vio))
			vioOuts.add(vio);
	}
	
	public void removeVIOOut(IBasicElement vio) {
		vioOuts.remove(vio);
	}
	
	public void addMessageIn(IBasicElement message) {
		if(!messageIns.contains(message))
			messageIns.add(message);
	}
	
	public void removeMessageIn(IBasicElement vio) {
		messageIns.remove(vio);
	}
	
	public void addMessageOut(IBasicElement message) {
		if(!messageOuts.contains(message))
			messageOuts.add(message);
	}
	
	public void removeMessageOut(IBasicElement vio) {
		messageOuts.remove(vio);
	}
	
	public Set<String> getVIOs() {
		return vios;
	}

	public void setVIOs(Set<String> vios) {
		Set<String> oldVIOs = this.vios;
		this.vios = vios;
		listeners.firePropertyChange(PROPERTY_VIOS, oldVIOs, vios);
	}

	public Set<String> getMessages() {
		return messages;
	}

	public void setMessages(Set<String> messages) {
		Set<String> oldMessages = this.messages;
		this.messages = messages;
		listeners.firePropertyChange(PROPERTY_MESSAGES, oldMessages, messages);
	}

	public Map<String, String> getImports() {
		return imports;
	}

	public void setImports(Map<String, String> imports) {
		Map<String, String> old = this.imports;
		this.imports = imports;
		listeners.firePropertyChange(PROPERTY_IMPORTS, old, imports);
	}
	
	public Map<Object, Object> getAttributes() {
		Map<Object, Object> m = new HashMap<Object, Object>();//super.getAttributes();
		m.put(ATTRIBUTE_NAME,  name);
		m.put(ATTRIBUTE_INSTANCENAME, instanceName);
		return m;
	}
	
}
