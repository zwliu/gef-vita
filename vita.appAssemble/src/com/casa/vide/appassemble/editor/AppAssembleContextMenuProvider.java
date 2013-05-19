package com.casa.vide.appassemble.editor;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;

import com.casa.vide.appassemble.action.ImplementAction;
import com.casa.vide.appassemble.action.PublishAction;
import com.casa.vide.appassemble.action.SubscribeAction;

public class AppAssembleContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry actionRegistry;
	public AppAssembleContextMenuProvider(EditPartViewer viewer, ActionRegistry actionRegistry) {
		super(viewer);
		this.actionRegistry = actionRegistry;
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		// TODO Auto-generated method stub
		IAction action;
		GEFActionConstants.addStandardActionGroups(menu);
		action = actionRegistry.getAction(ImplementAction.id);
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		action = actionRegistry.getAction(SubscribeAction.id);
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		action = actionRegistry.getAction(PublishAction.id);
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
	}

	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	public void setActionRegistry(ActionRegistry actionRegistry) {
		this.actionRegistry = actionRegistry;
	}

}
