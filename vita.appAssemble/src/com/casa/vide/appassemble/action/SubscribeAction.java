/**
 * @FileName 
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
import org.eclipse.ui.IWorkbenchPart;

import com.casa.vide.appassemble.model.Message;
import com.casa.vide.appassemble.model.VIO;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.modelinterface.IElement;
import com.casa.vide.appassemble.modelinterface.IElement.ModelEventType;
import com.casa.vide.appassemble.part.VitaEventPart;

/**
 * VIO/Message的订阅动作
 *
 * @author lzw
 */
public class SubscribeAction extends SelectionAction {

	/**
	 * 动作的ID
	 */
	public static final String id = "SUBSCRIBE";
	
	/**
	 * 动作的构造函数
	 *
	 * @param part WorkbenchPart
	 */
	public SubscribeAction(IWorkbenchPart part) {
		super(part);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 计算动作能否执行
	 * 
	 * @return 当选中VIO/Message图元时，返回true；否则，返回false
	 */
	@Override
	protected boolean calculateEnabled() {
		if(getSelectedObjects() != null && getSelectedObjects().size() > 0) {
			Object part = getSelectedObjects().get(0);
			if(part instanceof VitaEventPart)
				return true;
		}
		return false;
	}
	
	@Override
	protected void init() {
		setText("Subscribe");
		setId(id);
		setEnabled(false);
	}
	
	/**
	 * 执行订阅操作，将VIO/Message图元的模型添加到父图元（VOM图元）模型的订阅VIO/Message集合中，
	 * 并从发布VIO/Message中移除，将VIO/Message的类型设置为ModelEventType.SUBSCRIPTION。
	 */
	@Override
	public void run() {
		VitaEventPart part = getPart();
		Object model = part.getParent().getModel();
		if(model instanceof VOM && part != null) {
			VOM vom = (VOM)model;
			IElement element = (IElement)part.getModel();
			if(element instanceof VIO) {
				vom.addVIOIn(element);
				vom.removeVIOOut(element);
			}
			else if(element instanceof Message) {
				vom.addMessageIn(element);
				vom.removeMessageOut(element);
			}
			element.setType(ModelEventType.SUBSCRIPTION);
		}
	}
	
	/**
	 * 获取选中图元的的模型
	 *
	 * @return VIO/Message图元返回对应的EditPart，其他图元返回null
	 */
	public VitaEventPart getPart() {
		if(calculateEnabled()) {
			return (VitaEventPart)getSelectedObjects().get(0);
		}
		return null;
	}

}
