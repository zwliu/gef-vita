package com.casa.vide.appassemble.modelinterface;

import java.util.Map;
import java.util.Vector;

/**
 * 应用框架源代码生成接口中的的最外层接口，通过该接口可获得应用的所有所需信息
 *
 * @author lzw
 */
public interface IScenario {
	/** 获取场景名称*/
	String getName();
	
	/**　获取场景中所有的VOM*/
	Map<String, IVOM> getVOMs();
	
	/**　获取场景中所有APP*/
	Vector<IAPP> getAPPs();

}
