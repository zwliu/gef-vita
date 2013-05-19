package com.casa.vide.appassemble.contributor;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;

public class CommonActionBarContributor extends ActionBarContributor {


	public static String editGroup = "edit group";
	public static String viewGroup = "view group";
	
	@Override
	protected void buildActions() {
		// TODO Auto-generated method stub
		addRetargetAction(new UndoRetargetAction());	//添加撤销工具
		addRetargetAction(new RedoRetargetAction());	//添加重做工具
		addRetargetAction(new DeleteRetargetAction());	//删除工具
		addRetargetAction(new ZoomInRetargetAction());	//添加放大工具
		addRetargetAction(new ZoomOutRetargetAction());	//添加缩小工具
	}

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) { 
		toolBarManager.add(new Separator(editGroup));
		toolBarManager.add(new Separator(viewGroup));
		toolBarManager.appendToGroup(editGroup, getAction(ActionFactory.UNDO.getId())); 
		toolBarManager.appendToGroup(editGroup, getAction(ActionFactory.REDO.getId())); 
		toolBarManager.appendToGroup(editGroup, getAction(ActionFactory.DELETE.getId())); 
		toolBarManager.appendToGroup(viewGroup, getAction(GEFActionConstants.ZOOM_IN)); 
		toolBarManager.appendToGroup(viewGroup, getAction(GEFActionConstants.ZOOM_OUT)); 
		toolBarManager.appendToGroup(viewGroup, new ZoomComboContributionItem(getPage())); 
	}

}
