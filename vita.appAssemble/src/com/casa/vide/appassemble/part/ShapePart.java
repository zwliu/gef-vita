package com.casa.vide.appassemble.part;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.DropRequest;

import com.casa.vide.appassemble.figure.shape.ExpRectangleFigure;
import com.casa.vide.appassemble.model.Shape;
import com.casa.vide.appassemble.model.Shape.ShapeType;
import com.casa.vide.appassemble.policy.ConnectionPolicy;

public class ShapePart extends NodePart implements NodeEditPart {

	private List<FixedAnchor> anchors = new ArrayList<FixedAnchor>();
	
	@Override
	public IFigure createFigure() {
		IFigure figure = null;
		ShapeType type = getShapeType();
		switch(type) {
		case TYPE_NONE:figure = new Figure(); break;
		case TYPE_RECTANGLE:figure = new ExpRectangleFigure(); 
		anchors.add(new FixedAnchor(figure, 0.5, 0));
		anchors.add(new FixedAnchor(figure, 0.5, 1));
		anchors.add(new FixedAnchor(figure, 0, 0.5));
		anchors.add(new FixedAnchor(figure, 1, 0.5));
		break;
		}
		return figure;
	}
	
	public ShapeType getShapeType() {
		return ((Shape)getModel()).getType();
	}
	
	@Override
	public void refreshVisuals() {
		Shape model = (Shape)getModel();
		IFigure figure = getFigure();
		if(figure instanceof ExpRectangleFigure) {
			((ExpRectangleFigure)figure).setBackgroundColor(model.getBackground());
			((ExpRectangleFigure)figure).setLayout(model.getLayout());
		}
	}
	
	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();  	//������layout
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new ConnectionPolicy());
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		String name = arg0.getPropertyName();
		if(name.equals(Shape.PROPERTY_BACKGROUND))
			refreshVisuals();
		else if(name.equals(Shape.PROERTY_SOURCECONNECTION))
			refreshSourceConnections();
		else if(name.equals(Shape.PROERTY_TARGETCONNECTION))
			refreshTargetConnections();
		super.propertyChange(arg0);
	}
	
	@Override
	protected List<Object> getModelSourceConnections() {
		return ((Shape)getModel()).getSourceConnections();
	}
	
	@Override
	protected List<Object> getModelTargetConnections() {
		return ((Shape)getModel()).getTargetConnections();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(
			ConnectionEditPart connection) {
		return anchors.get(0);
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(
			ConnectionEditPart connection) {
		return anchors.get(0);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request request) {
		if(request instanceof DropRequest)
			return getClosestAnchor(request);
		return anchors.get(0);
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request request) {
		if(request instanceof DropRequest)
			return getClosestAnchor(request);
		return anchors.get(0);
	}
	
	 /** Returns the closest anchor to request's location. */
	  private ConnectionAnchor getClosestAnchor(Request iRequest)
	  {
	  	FixedAnchor closest = null;
	    Point pt = new Point(((DropRequest)iRequest).getLocation());
	    int minDistance = Integer.MAX_VALUE;
	    for(FixedAnchor anchor : anchors) {
	    	Point p = anchor.getLocation(null);
	    	int distance = pt.getDistance2(p);
			if (distance < minDistance) {
				minDistance = distance;
				closest = anchor;
			}
	    }
		return closest;
	  }
	
}
