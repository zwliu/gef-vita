package com.casa.vide.appassemble.figure.shape;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

public class ExpPolygonFigure extends PolygonShape {
	
	private Image background = null;
	
	public void setBackgroundColor(Image background) {
		this.background = background;
		repaint();
	}
	
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
	}
	
	protected void fillShape(Graphics g) {
		if(background != null) {
			Rectangle area = getClientArea();
			org.eclipse.swt.graphics.Rectangle imageArea = background.getBounds();
			g.drawImage(background, imageArea.x, imageArea.y, imageArea.width, imageArea.height, area.x, area.y, area.width, area.height);
		}
		else
			super.fillShape(g);
	}
	
}
