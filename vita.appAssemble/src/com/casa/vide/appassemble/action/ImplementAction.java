package com.casa.vide.appassemble.action;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;

import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.part.VOMPart;
import com.casa.vide.modeling.IModelImplementor;
import com.casa.vide.modeling.ModelImplementor;

public class ImplementAction extends SelectionAction {

	public static final String id = "IMPLEMENT";
	
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
	
	@Override
	protected void init() {
		setText("Implement");
		setId(id);
		setEnabled(false);
	}
	
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
	
	public VOM getVOM() {
		if(calculateEnabled()) {
			VOMPart part = (VOMPart)getSelectedObjects().get(0);
			return (VOM)part.getModel();
		}
		return null;
	}

}
