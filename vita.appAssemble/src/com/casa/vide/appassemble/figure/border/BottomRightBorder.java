package com.casa.vide.appassemble.figure.border;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;

public class BottomRightBorder extends AbstractBorder {

	private Insets inset = new Insets(0, 0, 1, 1);
	private int width = 1;
	
	public BottomRightBorder(){}
	
	public BottomRightBorder(int width) {
		inset.bottom = width;
		inset.right = width;
		this.width = width;
	}
	
	@Override
	public Insets getInsets(IFigure figure) {
		// TODO Auto-generated method stub
		return inset;
	}

	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		// TODO Auto-generated method stub
		getPaintRectangle(figure, insets);
		if (width % 2 == 1) {
			tempRect.height--;
			tempRect.width--;
		}
		tempRect.shrink(width / 2, width / 2);
		graphics.setLineWidth(width);
		graphics.drawLine(tempRect.getBottomLeft(), tempRect.getBottomRight());
		graphics.drawLine(tempRect.getTopRight(), tempRect.getBottomRight());
	}

}
