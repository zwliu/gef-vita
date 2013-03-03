package vita.appassemble.factory;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractEditPart;

import vita.appassemble.model.APP;
import vita.appassemble.model.Node;
import vita.appassemble.model.VOM;
import vita.appassemble.part.APPPart;
import vita.appassemble.part.NodePart;
import vita.appassemble.part.VOMPart;

public class PartCreationFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		// TODO Auto-generated method stub
		AbstractEditPart part = null;
		if(model instanceof VOM)
			part = new VOMPart();
		else if(model instanceof APP)
			part = new APPPart();
		else if(model instanceof Node)
			part = new NodePart();
		
		if(part != null)
			part.setModel(model);
		return part;
	}

}
