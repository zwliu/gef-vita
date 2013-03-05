package vita.appassemble.part;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import vita.appassemble.figure.NodeFigure;
import vita.appassemble.model.Node;
import vita.appassemble.policy.NodeDeleteEditPolicy;
import vita.appassemble.policy.NodeLayoutEditPolicy;

public class NodePart extends AbstractGraphicalEditPart implements PropertyChangeListener {

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		return new NodeFigure();
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new NodeLayoutEditPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new NodeDeleteEditPolicy());
	}
	
	@Override
	public List<Object> getModelChildren() {
		return ((Node)getModel()).getChildren();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		String name = arg0.getPropertyName();
		if(name == Node.PROPERTY_ADD || name == Node.PROPERTY_REMOVE) {
			refreshChildren();
		}
		else if(name == Node.PROPERTY_LAYOUT) {
			refreshVisuals();
		}
	}
	
	@Override
	public void activate() {
		super.activate();
		((Node)getModel()).addPropertyChangeListener(this);
	}
	
	@Override
	public void deactivate() {
		super.deactivate();
		((Node)getModel()).removePropertyChangeListener(this);
	}

}
