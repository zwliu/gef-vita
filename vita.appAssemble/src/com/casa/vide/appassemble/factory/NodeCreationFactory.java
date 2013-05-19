package com.casa.vide.appassemble.factory;

import org.eclipse.gef.requests.CreationFactory;

import com.casa.vide.appassemble.model.APP;
import com.casa.vide.appassemble.model.Message;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.VIO;
import com.casa.vide.appassemble.model.VOM;


public class NodeCreationFactory implements CreationFactory {
	
	private Class<? extends Node> node;

	public NodeCreationFactory(Class<? extends Node> node) {
		this.node = node;
	}
	
	@Override
	public Object getNewObject() {
		// TODO Auto-generated method stub
		Node obj = null;
		if(node == APP.class) {
			obj = new APP("APP Example");
		}
		else if(node == VOM.class) {
			obj = new VOM("VOM Example");
		}
		else if(node == VIO.class) {
			obj = new VIO();
		}
		else if(node == Message.class) {
			obj = new Message();
		}
		return obj;
	}

	@Override
	public Object getObjectType() {
		// TODO Auto-generated method stub
		return node;
	} 
	
	
	
}
