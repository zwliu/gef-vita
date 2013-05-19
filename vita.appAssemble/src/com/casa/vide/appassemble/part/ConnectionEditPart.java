package com.casa.vide.appassemble.part;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ManhattanConnectionRouter;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;

/**
 * 图元连接的编辑部分
 * @author lzw
 *
 */
public class ConnectionEditPart extends AbstractConnectionEditPart {
	
	@Override
	protected IFigure createFigure() {
		PolylineConnection conn = new PolylineConnection();
//		Connection model = (Connection)getModel();
//		Shape source = model.getSource();
//		Shape  target = model.getTarget();
		PolygonDecoration decoration = new PolygonDecoration();
		conn.setTargetDecoration(decoration);
		conn.setConnectionRouter(new ManhattanConnectionRouter());
		return conn;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	
}
