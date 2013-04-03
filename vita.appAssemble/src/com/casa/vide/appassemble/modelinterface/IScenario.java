package com.casa.vide.appassemble.modelinterface;

import java.util.Map;
import java.util.Vector;

public interface IScenario {
	/** 获取场景名称*/
	String getName();
	
	/**　获取场景中所有的VOM*/
	Map<String, IVOM> getVOMs();
	
	/**　获取场景中所有APP*/
	Vector<IAPP> getAPPs();

}
