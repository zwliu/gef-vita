package com.casa.vide.appassemble.figure.border;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;

public class RightBorder extends AbstractBorder {
	
	private Insets inset = new Insets(0, 0, 0, 1);
	private int width = 1;
	
	public Insets getInsets(IFigure figure) {
		return inset;
	}
	
	public RightBorder(){}
	
	public RightBorder(int width) {
		inset.right = width;
		this.width = width;
	}
	
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		getPaintRectangle(figure, insets);
		if (width % 2 == 1) {
			tempRect.width--;
		}
		tempRect.shrink(width / 2, 0);
		graphics.setLineWidth(width);
	    graphics.drawLine(tempRect.getTopRight(), tempRect.getBottomRight());
	}
	
}
