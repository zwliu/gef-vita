package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.FontMetrics;

import  com.casa.vide.appassemble.modelinterface.IElement.ModelEventType;;

/**
 * VIO/Message图元的图形
 *
 * @author lzw
 */
public class CircleFigure extends Figure {
	
	/** VIO/Message的事件类型*/
	private String type;
	
	/** VIO/Message实例名*/
	private String instanceName;

	/**
	 * 设置VIO/Message图元的大小
	 *
	 * @param constraint 布局约束
	 */
	public void setLayout(Object constraint) {
		this.setSize(new Dimension(((Rectangle)constraint).width,
				((Rectangle)constraint).height));
		
	}
	
	/**
	 * 设置VIO/Message图元的事件类型
	 *
	 * @param type VIO/Message图元的事件类型
	 */
	public void setType(ModelEventType type) {
		this.type = type.toString();
		this.repaint();
	}
	
	/**
	 * 设置实例名
	 *
	 * @param instanceName 实例名
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
		this.setSize(this.getPreferredSize());
		this.repaint();
	}
	
//	@Override
//	public void repaint() {
//		Rectangle rec = getBounds();
//		if(oldBound.width != rec.width || oldBound.height != rec.height) {	//若是在XYLayout中会用到		
//			int r = rec.height > rec.width ? rec.height : rec.width;
//			rec.expand((r-rec.width)/2+5, (r-rec.height)/2+5);		
//			oldBound.x = rec.x;
//			oldBound.y = rec.y;
//			oldBound.width = rec.width;
//			oldBound.height = rec.height;
//		} 
//		super.repaint();
//	}
	
	//private Rectangle oldBound = new Rectangle(0, 0, 0, 0);
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
