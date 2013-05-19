package com.casa.vide.appassemble.figure.border;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;

/**
 * 只要底部的边框
 * @author lzw
 *
 */
public class BottomBorder extends AbstractBorder{
	
	private Insets inset = new Insets(0, 0, 1, 0);
	private int width = 1;
	
	public BottomBorder(){}
	
	public BottomBorder(int width) {
		inset.bottom = width;
		this.width = width;
	}
	
	public Insets getInsets(IFigure figure) {
		return inset;
	}	
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		getPaintRectangle(figure, insets);
		if (width % 2 == 1) {
			tempRect.height--;
		}
		tempRect.shrink(0, width / 2);
		graphics.setLineWidth(width);
	    graphics.drawLine(tempRect.getBottomLeft(), tempRect.getBottomRight());
	}
}


