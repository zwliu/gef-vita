package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Color;

public class VOMFigure extends Figure {
	
	private EditableLabel instanceName;
	private Label name;
	private FlowFigure vioAndMsg;
	
	public VOMFigure() {
		XYLayout layout = new XYLayout();
//		layout.setHorizontal(false);
//		layout.setSpacing(2);
		setLayoutManager(layout);
		instanceName = new EditableLabel();
		//instanceName.setFont(new Font(null, "Arial", 12, SWT.BOLD));
		name = new Label();
		Label lb = new Label();
		ToolbarLayout lbLayout = new ToolbarLayout();
		lbLayout.setHorizontal(true);
		lb.setLayoutManager(lbLayout);		
		lb.add(instanceName);
		lb.add(new Label(" / "));
		lb.add(name);
		add(lb);
		setConstraint(lb, new Rectangle(0, 0, -1, -1));		
		vioAndMsg = new FlowFigure();
		add(vioAndMsg);
		setOpaque(true);
	}
	
	public void setInstanceName(String name) {
		this.instanceName.setText(name);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
	}
	
	public EditableLabel getNameLabel() {
		return instanceName;
	}
	
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
	
	@Override
	protected void paintFigure(Graphics graphics) {
		Label lb = (Label)getChildren().get(0);
		int titleHeight = lb.getBounds().height << 1;
		if(titleHeight <= 0) 
			titleHeight = 30;
		if(bounds.height <= titleHeight) {
			bounds.height = titleHeight * 3;
			//getParent().getLayoutManager().setConstraint(this, new Rectangle(bounds.x);
		}
		if(bounds.width <=  lb.getBounds().width) {
			bounds.width =  lb.getBounds().width << 1;
			//getParent().getLayoutManager().setConstraint(this, bounds);
		}
		graphics.fillRectangle(bounds.x, bounds.y+titleHeight, bounds.width, bounds.height-titleHeight);
		graphics.setBackgroundColor(foregroundColor);
		graphics.fillRectangle(bounds.x, bounds.y, bounds.width, titleHeight);
	}
	
	Color foregroundColor = null;
	
	public IFigure getContentPane() {
		return vioAndMsg;
	}
	
}
