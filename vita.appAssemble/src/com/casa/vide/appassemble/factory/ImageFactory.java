package com.casa.vide.appassemble.factory;

import org.eclipse.swt.graphics.Image;
import org.eclipse.gef.requests.CreationFactory;

public class ImageFactory implements CreationFactory {

	private Image image;
	
	public ImageFactory(Image image) {
		this.image = image;
	}

	@Override
	public Object getNewObject() {
		// TODO Auto-generated method stub
		return image;
	}

	@Override
	public Object getObjectType() {
		// TODO Auto-generated method stub
		return Image.class;
	}
	
	
}
