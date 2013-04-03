package com.casa.vide.appassemble.part;

import org.eclipse.draw2d.IFigure;

import com.casa.vide.appassemble.figure.VOMFigure;
import com.casa.vide.appassemble.model.VOM;


public class VOMPart extends NodePart {

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		VOMFigure figure = new VOMFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();	//������layout
	}
	
	@Override
	protected void refreshVisuals() {
		VOMFigure figure = (VOMFigure)getFigure();
		VOM model = (VOM)getModel();
		figure.setName(model.getName());
		figure.setLayout(model.getLayout());
	}

}
