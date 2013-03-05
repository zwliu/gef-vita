package vita.appassemble.contributor;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.DeleteRetargetAction;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.ui.actions.ActionFactory;

public class AppAssembleActiobBarContributor extends ActionBarContributor {

	@Override
	protected void buildActions() {
		// TODO Auto-generated method stub
		addRetargetAction(new UndoRetargetAction());
		addRetargetAction(new RedoRetargetAction());
		addRetargetAction(new DeleteRetargetAction());
	}

	@Override
	protected void declareGlobalActionKeys() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) { 
		toolBarManager.add(getAction(ActionFactory.UNDO.getId())); 
		toolBarManager.add(getAction(ActionFactory.REDO.getId())); 
		toolBarManager.add(getAction(ActionFactory.DELETE.getId()));
	}

}
