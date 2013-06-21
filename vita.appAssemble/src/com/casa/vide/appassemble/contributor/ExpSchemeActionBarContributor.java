package com.casa.vide.appassemble.contributor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.ActionFactory;

import com.casa.vide.appassemble.action.SaveRetargetAction;

/**
 * 试验系统建模的编辑器的工具条
 *
 * @author lzw
 */
public class ExpSchemeActionBarContributor extends CommonActionBarContributor {

	
	@Override
	protected void buildActions() {
		super.buildActions();
		addRetargetAction(new SaveRetargetAction());
	}

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) { 
		super.contributeToToolBar(toolBarManager);
		toolBarManager.appendToGroup(editGroup, getAction(ActionFactory.SAVE.getId()));
	}
	
}
