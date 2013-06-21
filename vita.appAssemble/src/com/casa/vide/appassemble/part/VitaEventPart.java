package com.casa.vide.appassemble.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;

import com.casa.vide.appassemble.figure.CircleFigure;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.modelinterface.IElement;

/**
 * VIO/Message图元的EditPart
 *
 * @author lzw
 */
public class VitaEventPart extends NodePart {
	
	@Override
	public IFigure createFigure() {
		CircleFigure figure = new CircleFigure();
		Object model = getModel();
		figure.setLayout(((Node)model).getLayout());
		return figure;
	}
	
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();  	//������layout
	}
	
	@Override
	public void refreshVisuals() {
		CircleFigure figure = (CircleFigure)getFigure();
		IElement model = (IElement)getModel();
		figure.setType(model.getType());
		figure.setInstanceName(model.getInstanceName());
		figure.setBackgroundColor(((Node)model).getColor());
	}
	
	@Override
	public List<Object> getModelChildren() {
		return ((Node)getModel()).getChildren();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {	
		String name = arg0.getPropertyName();
		if(name.equals(IElement.ATTRIBUTE_EVENTTYPE))
			refreshVisuals();	
		else if(name.equals(IElement.ATTRIBUTE_INSTANCENAME))
			refreshVisuals();
		else
			super.propertyChange(arg0);
	}
	
}
