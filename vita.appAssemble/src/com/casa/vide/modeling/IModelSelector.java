package com.casa.vide.modeling;

import java.util.Set;

import org.eclipse.swt.widgets.Shell;

public interface IModelSelector {
	
	/** 打开模型选择向导对话框
	 * 
	 * @return 是否成功打开
	 */
	boolean openModelSelectWizard(Shell shell);
	
	/** 获取VDL Name
	 * 
	 * @return VDL文件名称
	 */
	String getVdlName();
	
	String getInstanceName();
	
	/** 获取VOM Name
	 * 
	 * @return 对象模型名称
	 */
	String getVomName();
	
	/** 获取模型依赖项集
	 * 
	 * @return 所包含的其他模型的Set集合
	 */
	Set<String> getImports();
	
	/** 获取VIO集
	 * 
	 * @return 包含的VIO的Set集合
	 */
	Set<String> getVIOs();
	
	/** 获取Message集
	 * 
	 * @return 包含的Message的Set集合
	 */
	Set<String> getMessages();
}
