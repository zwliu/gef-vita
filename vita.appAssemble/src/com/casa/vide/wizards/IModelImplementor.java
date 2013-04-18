package com.casa.vide.wizards;

import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Shell;

public interface IModelImplementor {
	
	/** 打开模型选择向导对话框
	 * 
	 * @param shell 父Shell
	 * @Param VomElement 待实现的VOM元素名称，是一个VIO或者Message
	 * @return 是否成功打开
	 */
	public boolean openModelImplWizard(Shell shell, String VomElement);
	
	/** 获取实现的回调类型的集合
	 * 
	 * @return 回调类型的Set集合
	 */
	public Set<String> getImplTypes();
	
	/** 获取模型依赖项的具体实现集
	 * 
	 * @return Map<String, String>：参数1为Import的模型名称，参数2为参数1对应模型的具体实现
	 */
	public Map<String, String> getRequires();

}
