package com.casa.vide.appassemble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.eclipse.swt.graphics.Color;

import com.casa.vide.appassemble.modelinterface.IAPP;
import com.casa.vide.appassemble.modelinterface.IVOM;

public class APP extends Node implements IAPP {

	public static final String PROPERTY_NAME = "AppName";
	public static final String ATTRIBUTE_MONITOROPEN = "monitor open";
	public static final String ATTRIBUTE_TIMEREGULATION = "time regulation";
	private String name;
	private String monitorOpen;
	private String timeRegulation;
	private Map<String, IVOM> voms;
	
	public static Color createRandomColor() { /** Just for Fun :) **/ return new Color(null, (new Double(Math.random() * 128)).intValue() + 128 , (new Double(Math.random() * 128)).intValue() + 128 , (new Double(Math.random() * 128)).intValue() + 128 ); }
	
	public APP() {
		name = null;
		monitorOpen = "true";
		timeRegulation = "0";
		voms = new HashMap<String, IVOM>();
	}
	
	public APP(String name) {
		this();
		this.name = name;
	}
	
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		listeners.firePropertyChange(PROPERTY_NAME, oldName, name);
	}
	
	public String getName() {
		return name;
	}

	@Override
	public Vector<String> getVomNames() {
		// TODO Auto-generated method stub
		return (Vector<String>) new Vector<String>(voms.keySet());
	}

	@Override
	public String getMonitorOpen() {
		// TODO Auto-generated method stub
		return monitorOpen;
	}

	@Override
	public String getTimeRegulation() {
		// TODO Auto-generated method stub
		return timeRegulation;
	}
	
	public void setMonitorOpen(String monitorOpen) {
		if(monitorOpen != null &&
				(monitorOpen.equals("true") || monitorOpen.equals("false")))
			this.monitorOpen = monitorOpen;
	}

	public void setTimeRegulation(String timeRegulation) {
		if(timeRegulation != null &&
				(timeRegulation.equals("0") || timeRegulation.equals("1")
				|| timeRegulation.equals("2") ||timeRegulation.equals("3")))
			this.timeRegulation = timeRegulation;
	}
	
	@Override
	public boolean addChild(Object child) {
		if(child instanceof IVOM)
			voms.put(((IVOM)child).getName(), (IVOM)child);  //name vom中唯一？
		return super.addChild(child);
	}
	
	@Override
	public boolean removeChild(Object child) {
		if(child instanceof IVOM)
			voms.remove(((IVOM)child).getName());  // name != null ？
		return super.removeChild(child);
	}
	
	public Map<String, IVOM> getVOMs() {
		return voms;
	}
	
}
