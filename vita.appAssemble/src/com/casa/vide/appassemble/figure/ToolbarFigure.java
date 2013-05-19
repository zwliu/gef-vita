package com.casa.vide.appassemble.figure;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;

import com.casa.vide.appassemble.figure.border.BottomBorder;

public class ToolbarFigure extends Figure {

	private Label title;
	//private List<IFigure> children = null;
	
	public ToolbarFigure() {
		this(null, null);
	}
	
	public ToolbarFigure(String title) {
		this(title, null);
	}
	
	public ToolbarFigure(String title, List<IFigure> children) {
		ToolbarLayout layout = new ToolbarLayout();
		layout.setSpacing(2);
		setLayoutManager(layout);
		this.title = new Label(title);	
		this.title.setFont(new Font(null, "Arial", 10, SWT.BOLD));
		this.title.setBorder(new BottomBorder());
		//this.children = children;
		setChildren(children);
	}
	
	public void setChildren(List<IFigure> children) {
		removeAll();
		add(this.title);
		if(children == null)
			return ;
		IFigure child = null;
		for(Iterator<IFigure> iterator = children.iterator(); iterator.hasNext(); ) {
			child = iterator.next();
			child.setBorder(new BottomBorder());
			add(child);
		}
		if(child != null)
			child.setBorder(null);
	}
	
	public void setHorizontal(boolean flag) {
		((ToolbarLayout)getLayoutManager()).setHorizontal(flag);
	}
	
	public void setChildren(Set<String> children) {
		removeAll();
		add(this.title);
		if(children == null)
			return ;
		Label lb = null;
		for(Iterator<String> iterator = children.iterator(); iterator.hasNext(); ) {
			lb = new Label(iterator.next());
			lb.setBorder(new BottomBorder());
			add(lb);
		}
		if(lb != null)
			lb.setBorder(null);
	}
	
}
