package vita.appassemble.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import vita.appassemble.model.Node;
import vita.appassemble.treepart.NodeTreePart;

public class TreePartCreationFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub
		EditPart part = null;
		if(model instanceof Node)
			part = new NodeTreePart();
		if(part != null)
			part.setModel(model);
		return part;
	}

}
