package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;

public class NodeFigure extends Figure {
	
	public NodeFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
	}
}
