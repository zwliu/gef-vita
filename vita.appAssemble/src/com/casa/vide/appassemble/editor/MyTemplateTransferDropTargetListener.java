package com.casa.vide.appassemble.editor;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.graphics.Image;

import com.casa.vide.appassemble.factory.NodeCreationFactory;
import com.casa.vide.appassemble.model.Node;


public class MyTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

	public MyTemplateTransferDropTargetListener(EditPartViewer viewer) {
		super(viewer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CreationFactory getFactory(Object template) {
		if(template == Image.class)
			return null;//new NodeCreationFactory();
		else
			return new NodeCreationFactory((Class<? extends Node>)template);
	}
	
}
