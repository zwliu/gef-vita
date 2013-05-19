package com.casa.vide.appassemble.action;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.casa.vide.appassemble.model.Message;
import com.casa.vide.appassemble.model.VIO;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.modelinterface.IElement;
import com.casa.vide.appassemble.modelinterface.IElement.ModelEventType;
import com.casa.vide.appassemble.part.VitaEventPart;

public class SubscribeAction extends SelectionAction {

	public static final String id = "SUBSCRIBE";
	
	public SubscribeAction(IWorkbenchPart part) {
		super(part);
		// TODO Auto-generated constructor stub
	}
	
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
	
	public VitaEventPart getPart() {
		if(calculateEnabled()) {
			return (VitaEventPart)getSelectedObjects().get(0);
		}
		return null;
	}

}
