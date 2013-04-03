package com.casa.vide.appassemble.modelinterface;

import java.util.Vector;

public interface IVOM {
	/** 获取VOM名称*/
	String getVOMName();
	
	/** 获取VOM实例名*/
	String getInstanceName();
	
	/** 获取VIOIns*/
	Vector<IBasicElement> getVIOIns();
	
	/** 获取VIOOuts*/
	Vector<IBasicElement> getVIOOuts();
	
	/** 获取MessageIns*/
	Vector<IBasicElement> getMessageIns();
	
	/** 获取MessageOuts*/
	Vector<IBasicElement> getMessageOuts();
}
