package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.XYLayout;

public class FlowFigure extends Figure {

	public FlowFigure() {
		FlowLayout layout = new FlowLayout();
		setLayoutManager(layout);
		this.setOpaque(false);
	}
	
}
