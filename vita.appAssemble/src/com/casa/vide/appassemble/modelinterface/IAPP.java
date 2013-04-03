package com.casa.vide.appassemble.modelinterface;

import java.util.Vector;

public interface IAPP {
	/** 获取名称*/
	String getName();
	
	/** 获取APP中所有VOM的名字*/
	Vector<String> getVomNames();
	
	/** 获得Monitor打开状态*/
	String getMonitorOpen();
	
	/** 获得TimeRegulation的值*/
	int getTimeRegulation();
	
}
