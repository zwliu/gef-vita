package com.casa.vide.appassemble.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractEditPart;

import com.casa.vide.appassemble.model.APP;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.model.VitaEvent;
import com.casa.vide.appassemble.part.APPPart;
import com.casa.vide.appassemble.part.NodePart;
import com.casa.vide.appassemble.part.VOMPart;
import com.casa.vide.appassemble.part.VitaEventPart;


public class PartCreationFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub
		AbstractEditPart part = null;
		if(model instanceof VOM)
			part = new VOMPart();
		else if(model instanceof APP)
			part = new APPPart();
		else if(model instanceof VitaEvent)
			part = new VitaEventPart();
		else if(model instanceof Node)
			part = new NodePart();
		
		if(part != null)
			part.setModel(model);
		return part;
	}

}
