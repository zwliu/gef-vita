package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

/**
 * APP图元图形
 *
 * @author lzw
 */
public class APPFigure extends Figure {
	
	//private GroupBoxBorder name;
	/**　应用图元名称*/
	private Label name;
	
	public APPFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		name = new Label();//new GroupBoxBorder();	
		add(name);
		setConstraint(this.name, new Rectangle(0, 0, -1, -1));		
		name.setLabelAlignment(PositionConstants.CENTER);			
		//setBorder(name);
		setOpaque(true);
	}
	
	/**
	 * 设置APP图元的显示名称
	 *
	 * @param name 显示名称
	 */
	public void setName(String name) {
		this.name.setText(name);	
		//this.name.setLabel(name);
	}
	
	/**
	 * 设置图元在父图元中的约束
	 *
	 * @param constraint 约束
	 */
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
	}
	
	/**
	 * 设置背景颜色和标题背景颜色
	 */
	public void setBackgroundColor(Color bgColor) {
		super.setBackgroundColor(bgColor);
		int r, g, b;
		r = bgColor.getRed()-100;
		if(r < 0)
			r = 0;
		g = bgColor.getGreen()-100;
		if(g < 0)
			g = 0;
		b = bgColor.getBlue()-100;
		if(b < 0)
			b = 0;
		foregroundColor = new Color(null, r>255 ? 255 : r, g>255 ? 255 : g, b>255 ? 255 : b);
	}
	
	/** 标题背景颜色*/
	Color foregroundColor = null;
	
	/**
	 * 绘制APP图元
	 */
	protected void paintFigure(Graphics graphics) {
		Rectangle nameBounds = this.name.getBounds();
		int titleHeight = nameBounds.height << 1;
		if(titleHeight <= 0)
			titleHeight = 30;	
		setConstraint(this.name, new Rectangle((bounds.width-nameBounds.width)>>1, 
				(titleHeight-nameBounds.height)>>1, -1, -1));	//设置name的位置，标题居中		
		if(bounds.height <= titleHeight) {  //设置应用图元的高度不小于标题高度的3倍，主要是刚创建图元时，不会只有标题
			bounds.height = titleHeight * 3;
			getParent().getLayoutManager().setConstraint(this, bounds);
		}
		if(bounds.width <= nameBounds.width) {  //设置应用图元的宽度至少是name的两倍宽
			bounds.width = nameBounds.width << 1;
			getParent().getLayoutManager().setConstraint(this, bounds);
		}
		//绘制标题
		graphics.fillRectangle(bounds.x, bounds.y+titleHeight, bounds.width, bounds.height-titleHeight);
		graphics.setBackgroundColor(foregroundColor);
		graphics.fillArc(bounds.x, bounds.y, titleHeight<<1, titleHeight<<1, 90, 90);
		graphics.fillArc(bounds.x+bounds.width-(titleHeight<<1), bounds.y, titleHeight<<1,titleHeight<<1, 0, 90);
		graphics.fillRectangle(bounds.x+titleHeight, bounds.y, bounds.width-(titleHeight<<1), titleHeight);
	}

}
