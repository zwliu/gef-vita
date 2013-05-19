package com.casa.vide.appassemble.figure.border;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;

public class RoundCornerBorder extends AbstractBorder {

	private Insets inset = new Insets(0);
	private int arcWidth = 30;
	private int arcHeight = 30;
	@Override
	public Insets getInsets(IFigure figure) {
		// TODO Auto-generated method stub
		return inset;
	}

	@Override
	public void paint(IFigure figure, Graphics graphics, Insets insets) {
		// TODO Auto-generated method stub
		 tempRect.setBounds(getPaintRectangle(figure, insets));
		 int width = graphics.getLineWidth();
         if(width == 0)
                 width = 1;
         tempRect.shrink(width, width);
         graphics.drawRoundRectangle(tempRect, arcWidth, arcHeight);
	}
	
	public void setInsets(Insets inset) {
		if(inset != null)
			this.inset = inset;
	}
	
	public void setArcWidth(int arcWidth) {
		this.arcWidth = arcWidth;
	}
	
	public void setArcHeight(int arcHeight) {
		this.arcHeight = arcHeight;
	}

	public int getArcWidth() {
		return arcWidth;
	}

	public int getArcHeight() {
		return arcHeight;
	}

}
