/**
 * @FileName ImplementAction.java
 * @Package com.casa.vide.appassemble.action
 * 
 * @Copyright
 * 	   Copyright (C) 2013 JTang Middleware All Rights Reserved 
 * 
 * @Description
 * 	   
 * @author lzw
 * 
 * @date 2013-05-20
 * 
 * @version v1.0
 */
package com.casa.vide.appassemble.action;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.part.VOMPart;
import com.casa.vide.modeling.IModelImplementor;
import com.casa.vide.modeling.ModelImplementor;

/**
 * VOM的实现操作动作
 *  
 * @author lzw
 * 
 */
public class ImplementAction extends SelectionAction {

	public static final String id = "IMPLEMENT";
	
	/**
	 * 构造函数
	 * @param part workbench part
	 */
	public ImplementAction(IWorkbenchPart part) {
		super(part);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean calculateEnabled() {
		if(getSelectedObjects() != null && getSelectedObjects().size() > 0) {
			Object part = getSelectedObjects().get(0);
			if(part instanceof VOMPart)
				return true;
		}
		return false;
	}
	
	/**
	 * 初始化该动作
	 */
	@Override
	protected void init() {
		setText("Implement");
		setId(id);
		setEnabled(false);
	}
	
	/**
	 * 动作的执行，在该方法内执行VOM实现操作所需要完成的事情。
	 */
	@Override
	public void run() {
		IModelImplementor implementor = new ModelImplementor();
		VOM vom = getVOM();
		if(vom == null)
			return ;
		Shell shell = getWorkbenchPart().getSite().getShell();
		String[] temp = {};
		if(implementor.openModelImplWizard(shell, vom.getImports().keySet().toArray(temp), vom.getName())) {
			vom.setImports(implementor.getRequires());
		}
	}
	
	/**
	 * 获取选中的VOM图元的模型
	 * @return 返回选中的VOM模型
	 */
	public VOM getVOM() {
		if(calculateEnabled()) {
			VOMPart part = (VOMPart)getSelectedObjects().get(0);
			return (VOM)part.getModel();
		}
		return null;
	}

}
