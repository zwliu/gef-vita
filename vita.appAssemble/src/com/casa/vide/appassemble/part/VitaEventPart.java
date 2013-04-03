package com.casa.vide.appassemble.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;

import com.casa.vide.appassemble.figure.CircleFigure;
import com.casa.vide.appassemble.model.VitaEvent;


public class VitaEventPart extends NodePart {
	
	@Override
	public IFigure createFigure() {
		return new CircleFigure();
	}
	
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();  	//½ö½öÊÇlayout
	}
	
	@Override
	public void refreshVisuals() {
		CircleFigure figure = (CircleFigure)getFigure();
		VitaEvent model = (VitaEvent)getModel();
		figure.setType(model.getType());
		figure.setLayout(model.getLayout());
	}
	
	@Override
	public List<Object> getModelChildren() {
		return ((VitaEvent)getModel()).getChildren();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		super.propertyChange(arg0);
		if(arg0.getPropertyName().equals(VitaEvent.PROPERTY_TYPE))
			refreshVisuals();
	}
	
}
