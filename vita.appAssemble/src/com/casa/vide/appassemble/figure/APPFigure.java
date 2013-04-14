package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.AbstractBackground;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GroupBoxBorder;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.TitleBarBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.casa.vide.appassemble.Activator;


public class APPFigure extends Figure {
	
	private Label name;
	private Image background = null;
	
	public APPFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
		name = new Label();		
		add(name);
		setConstraint(name, new Rectangle(0, 0, -1, -1));
		
		setBackgroundColor(ColorConstants.yellow);
		setBorder(new TitleBarBorder());//LineBorder(1));
		setOpaque(true);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
	}
	
//	public void setBackgroundColor(Color bgColor) {
//		super.setBackgroundColor(bgColor);
//	}
	
	public void setBackgroundColor(Image background) {
		this.background = background;
		repaint();
	}
	
	@Override
	protected void paintFigure(Graphics graphics) {
		if (isOpaque()) {
			if(background != null) {
				Rectangle area = getClientArea();
				org.eclipse.swt.graphics.Rectangle imageArea = background.getBounds();
				graphics.drawImage(background, imageArea.x, imageArea.y, imageArea.width, imageArea.height, area.x, area.y, area.width, area.height);
			}
		}
		if (getBorder() instanceof AbstractBackground)
			((AbstractBackground) getBorder()).paintBackground(this, graphics,
					NO_INSETS);
	}
	
}
