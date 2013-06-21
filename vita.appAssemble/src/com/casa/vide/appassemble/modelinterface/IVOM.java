package com.casa.vide.appassemble.modelinterface;

import java.util.Vector;

/**
 * 应用框架源代码生成接口中的VOM图元的接口
 *
 * @author lzw
 */
public interface IVOM {
	/** 获取VOM名称*/
	String getName();
	
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
