package com.casa.vide.appassemble.part;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.draw2d.IFigure;

import com.casa.vide.appassemble.figure.APPFigure;
import com.casa.vide.appassemble.model.APP;


public class APPPart extends NodePart {

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		APPFigure figure = new APPFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();  	//½ö½öÊÇlayout
	}
	
	@Override
	protected void refreshVisuals() {
		APPFigure figure = (APPFigure)getFigure();
		APP model = (APP)getModel();
		figure.setName(model.getName());
		figure.setLayout(model.getLayout());
		figure.setBackgroundColor(model.getColor());
	}
	
	@Override
	public List<Object> getModelChildren() {
		return ((APP)getModel()).getChildren();
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		super.propertyChange(arg0);
		if(arg0.getPropertyName().equals(APP.PROPERTY_COLOR))
			refreshVisuals();
		else if(arg0.getPropertyName().equals(APP.PROPERTY_NAME))
			refreshVisuals();
	}

}
