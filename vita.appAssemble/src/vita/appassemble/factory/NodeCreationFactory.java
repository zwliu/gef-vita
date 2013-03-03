package vita.appassemble.factory;

import org.eclipse.gef.requests.CreationFactory;

import vita.appassemble.model.APP;
import vita.appassemble.model.Node;
import vita.appassemble.model.VOM;

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
		return obj;
	}

	@Override
	public Object getObjectType() {
		// TODO Auto-generated method stub
		return node;
	} 
	
	
	
}
