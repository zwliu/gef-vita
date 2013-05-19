package com.casa.vide.appassemble.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.casa.vide.appassemble.model.EditableLabelModel;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.Shape;
import com.casa.vide.appassemble.part.EditableLabelPart;
import com.casa.vide.appassemble.part.NodePart;
import com.casa.vide.appassemble.part.ShapePart;

public class ExpPartCreationFactory implements EditPartFactory  {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart part = null;		
		if(model instanceof Shape) 
			part = new ShapePart();
		else if(model instanceof EditableLabelModel)
			part = new EditableLabelPart();
		else if(model instanceof Node)
			part = new NodePart();
		if(part != null)
			part.setModel(model);
		return part;
	}

}
