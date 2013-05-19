package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.FontMetrics;

import  com.casa.vide.appassemble.modelinterface.IElement.ModelEventType;;

public class CircleFigure extends Figure {
	
	private String type;
	private String instanceName;

	public void setLayout(Object constraint) {
		//getParent().setConstraint(this, constraint);
		this.setBounds((Rectangle)constraint);
	}
	
	public void setType(ModelEventType type) {
		this.type = type.toString();	
	}
	
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
	
	@Override
	public void repaint() {
		Rectangle rec = getBounds();
		if(oldBound.width != rec.width || oldBound.height != rec.height) {			
			int r = rec.height > rec.width ? rec.height : rec.width;
			rec.expand((r-rec.width)/2+5, (r-rec.height)/2+5);		
			oldBound.x = rec.x;
			oldBound.y = rec.y;
			oldBound.width = rec.width;
			oldBound.height = rec.height;
		} 
		super.repaint();
	}
	
	private Rectangle oldBound = new Rectangle(0, 0, 0, 0);
	@Override
	protected void paintFigure(Graphics graphics) {
		Rectangle rec = getBounds();
		graphics.fillOval(rec.getExpanded(-2, -2)); //背景
		FontMetrics fontMetrics = graphics.getFontMetrics();
		int w = fontMetrics.getAverageCharWidth();
		int h = fontMetrics.getHeight();
		graphics.setForegroundColor(ColorConstants.black);
		//绘制类型（发布、订阅、未定）及实例名
		if(type != null) 
			graphics.drawText(type, rec.x + (rec.width-type.length()*w)/2, rec.y + (rec.height-h*2)/2-2);
		if(instanceName != null)
			graphics.drawText(instanceName, rec.x + (rec.width-instanceName.length()*w)/2, rec.y + rec.height/2+2);
	}
	
}
