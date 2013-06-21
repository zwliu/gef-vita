package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/**
 * VOM图元的图形
 *
 * @author lzw
 */
public class VOMFigure extends Figure {
	
	/** 实例名*/
	private EditableLabel instanceName;
	
	/** 名称*/
	private Label name;
	
	/** 容纳VIO/Message的容器*/
	private FlowFigure vioAndMsg;
	
	/** vioAndMsg在VIO图元中的约束*/
	private GridData constraintForVioAndMsg = new GridData();
	
	
	public VOMFigure() {
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		layout.marginWidth = 10;
		layout.verticalSpacing = 8;
		GridData data = new GridData();
		data.horizontalAlignment = SWT.CENTER;
		data.verticalAlignment = SWT.CENTER;
		setLayoutManager(layout);
		instanceName = new EditableLabel();
		name = new Label();
		Label lb = new Label();
		GridLayout lbLayout = new GridLayout();
		lbLayout.numColumns = 3;
		lb.setLayoutManager(lbLayout);		
		lb.add(instanceName);
		lb.add(new Label(" / "));
		lb.add(name);
		lb.setLabelAlignment(PositionConstants.CENTER);
		add(lb);
		layout.setConstraint(lb, data);	
		vioAndMsg = new FlowFigure();		
		add(vioAndMsg);
		layout.setConstraint(vioAndMsg, constraintForVioAndMsg);
		setOpaque(true);
	}
	
	/**
	 * 设置VOM图元显示的实例名
	 *
	 * @param name VOM实例名
	 */
	public void setInstanceName(String name) {
		this.instanceName.setText(name);
	}
	
	/**
	 * 设置VOM图元显示的名称
	 *
	 * @param name VOM名称
	 */
	public void setName(String name) {
		this.name.setText(name);
	}
	
	/**
	 * 设置VOM图元的布局约束，并设置vioAndMsg的宽度为VOM图元的宽度
	 *
	 * @param constraint 布局约束
	 */
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
		constraintForVioAndMsg.widthHint = ((Rectangle)constraint).width;
	}
	
	public EditableLabel getNameLabel() {
		return instanceName;
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
	
	/**
	 * 绘制VOM图元
	 */
	@Override
	protected void paintFigure(Graphics graphics) {
	//	Label lb = (Label)getChildren().get(0);
		int titleHeight = 30;  //标题高度30
		if(bounds.height <= titleHeight) {//设置VOM图元的高度不小于标题高度的3倍，主要是刚创建图元时，不会只有标题
			bounds.height = titleHeight * 3;
			//getParent().getLayoutManager().setConstraint(this, new Rectangle(bounds.x);
		}
//		if(bounds.width <=  lb.getBounds().width) {
//			bounds.width =  lb.getBounds().width << 1;
//			//getParent().getLayoutManager().setConstraint(this, bounds);
//		}
		
		//绘制标题
		graphics.fillRectangle(bounds.x, bounds.y+titleHeight, bounds.width, bounds.height-titleHeight);
		graphics.setBackgroundColor(foregroundColor);
		graphics.fillRectangle(bounds.x, bounds.y, bounds.width, titleHeight);
	}
	
	/** 标题的背景颜色*/
	Color foregroundColor = null;
	
	/**
	 * 
	 *
	 * @return
	 */
	public IFigure getContentPane() {
		return vioAndMsg;
	}
	
}
