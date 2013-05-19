package com.casa.vide.appassemble.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.casa.vide.appassemble.modelinterface.IAPP;
import com.casa.vide.appassemble.modelinterface.IScenario;
import com.casa.vide.appassemble.modelinterface.IVOM;

public class Scenario extends Node implements IScenario {

	private String name = null;
	private Vector<IAPP> apps = new  Vector<IAPP>();
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Map<String, IVOM> getVOMs() { 
		//只是取了APP中的VOM，多个APP中存在相同的String，即多个VOM名字相同.创建VOM时控制名字不同
		Map<String, IVOM> map = new HashMap<String, IVOM>();
		for(IAPP app : apps) {
			map.putAll(((APP)app).getVOMs());
		}
		return map;
	}

	@Override
	public Vector<IAPP> getAPPs() {
		// TODO Auto-generated method stub
		return apps;
	}
	
	@Override
	public boolean addChild(Object child) {
		if(child instanceof IAPP)
			return apps.add((IAPP)child);
		return false;
		//return super.addChild(child); 
	}
	
	@Override
	public boolean removeChild(Object child) {
		if(child instanceof IAPP)
			return apps.remove((IAPP)child);
		return true;
		//return super.removeChild(child);
	}

}
