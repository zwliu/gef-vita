package com.casa.vide.appassemble.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.casa.vide.appassemble.model.EditableLabelModel;
import com.casa.vide.appassemble.model.Shape;
import com.casa.vide.appassemble.model.Shape.ShapeType;

public class ExpNodeCreationFactory implements CreationFactory {

	private Object type;
	
	public ExpNodeCreationFactory(Object t) {
		type = t;
	}
	
	@Override
	public Object getNewObject() {
		// TODO Auto-generated method stub
		if(type instanceof ShapeType)
			return new Shape((ShapeType)type);
		else if(type == EditableLabelModel.class)
			return new EditableLabelModel();
		return null;
	}

	@Override
	public Object getObjectType() {
		// TODO Auto-generated method stub
		return type;
	}

}
