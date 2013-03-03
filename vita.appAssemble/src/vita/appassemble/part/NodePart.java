package vita.appassemble.part;

import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import vita.appassemble.figure.NodeFigure;
import vita.appassemble.model.Node;

public class NodePart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return new NodeFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Object> getModelChildren() {
		return ((Node)getModel()).getChildren();
	}

}
