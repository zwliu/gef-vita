package com.casa.vide.appassemble.figure.shape;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

/**
 * 实体图元的矩形图形
 *
 * @author lzw
 */
public class ExpRectangleFigure extends RectangleFigure {

	/** 背景图片*/
	private Image background = null;
	
	public ExpRectangleFigure() {
		super();
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		this.setVisible(true);
		this.setOpaque(true);
		this.setOutline(true);
	}
	
	/**
	 * 
	 *
	 * @param background
	 */
	public void setBackgroundColor(Image background) {
		this.background = background;
		repaint();
	}
	
	/**
	 * 
	 */
	protected void fillShape(Graphics g) {
		if(background != null) {
			Rectangle area = getClientArea();
			org.eclipse.swt.graphics.Rectangle imageArea = background.getBounds();
			g.drawImage(background, imageArea.x, imageArea.y, imageArea.width, imageArea.height, area.x, area.y, area.width, area.height);
		}
		else
			super.fillShape(g);
	}
	
	/**
	 * 
	 *
	 * @param constraint
	 */
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
	}
	
}
