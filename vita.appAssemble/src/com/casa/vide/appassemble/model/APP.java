package com.casa.vide.appassemble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.eclipse.swt.graphics.Color;

import com.casa.vide.appassemble.modelinterface.IAPP;
import com.casa.vide.appassemble.modelinterface.IVOM;

/**
 * APP图元（应用图元）模型类
 *
 * @author lzw
 */
public class APP extends Node implements IAPP {

	/** 
	 * name属性的特征ID或名称，用于firePropertyChange的propertyName及NodePropertySource中属性描述的ID
	 * @see NodePropertySource
	 */
	public static final String PROPERTY_NAME = "AppName";
	
	/** 
	 * monitorOpen属性的特征ID或名称，用于firePropertyChange的propertyName及NodePropertySource中属性描述的ID
	 * @see NodePropertySource
	 */
	public static final String ATTRIBUTE_MONITOROPEN = "monitor open";
	
	/** 
	 * timeRegulation属性的特征ID或名称，用于firePropertyChange的propertyName及NodePropertySource中属性描述的ID
	 * @see NodePropertySource
	 */
	public static final String ATTRIBUTE_TIMEREGULATION = "time regulation";
	
	/** 应用名*/
	private String name;
	
	/** 应用图元的Monitor属性*/
	private String monitorOpen;
	
	/** 应用图元的TimeRegulation属性*/
	private String timeRegulation;
	
	/** 应用图元包含的所有VOM图元的模型，String是VOM模型的实例名，IVOM是VOM模型实例*/
	private Map<String, IVOM> voms;
	
	public static Color createRandomColor() { /** Just for Fun :) **/ return new Color(null, (new Double(Math.random() * 128)).intValue() + 128 , (new Double(Math.random() * 128)).intValue() + 128 , (new Double(Math.random() * 128)).intValue() + 128 ); }
	
	
	public APP() {
		name = null;
		monitorOpen = "true";
		timeRegulation = "0";
		voms = new HashMap<String, IVOM>();
	}
	
	/**
	 * 创建应用名为name的应用模型实例
	 *
	 * @param name 应用名
	 */
	public APP(String name) {
		this();
		this.name = name;
	}
	
	/**
	 * 设置应用名
	 *
	 * @param name 应用名
	 */
	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		listeners.firePropertyChange(PROPERTY_NAME, oldName, name);
	}
	
	/**
	 * 获取应用名
	 */
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
	
	/**
	 * 设置monitorOpen
	 *
	 * @param monitorOpen 新的monitorOpen
	 */
	public void setMonitorOpen(String monitorOpen) {
		if(monitorOpen != null &&
				(monitorOpen.equals("true") || monitorOpen.equals("false")))
			this.monitorOpen = monitorOpen;
	}

	/**
	 * 设置timeRegulation
	 *
	 * @param timeRegulation 新的timeRegulation
	 */
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
	
	/**
	 * 获取应用图元包含的VOM图元模型名称及实例的Map结构
	 *
	 * @return 应用图元包含的VOM图元模型名称及实例的Map结构
	 */
	public Map<String, IVOM> getVOMs() {
		return voms;
	}
	
}
