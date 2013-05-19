package com.casa.vide.modeling;

import java.util.Map;
import org.eclipse.swt.widgets.Shell;

/**
 * @author zlf
 *
 */
public interface IModelImplementor {
	
	/** 打开模型选择向导对话框
	 * 
	 * @param shell 父Shell
	 * @param imports 包含的依赖项
	 * @Param VomElement 待实现的VOM元素名称，是一个VIO或者Message
	 * @return 是否成功打开
	 */
	boolean openModelImplWizard(Shell shell, String[] imports,
			String VomElement);
	
	/** 获取模型依赖项的具体实现集
	 * 
	 * @return Map<String, String>：参数1为Import的模型名称，参数2为参数1对应模型的具体实现
	 */
	Map<String, String> getRequires();
}
