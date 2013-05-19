package com.casa.vide.appassemble.figure;

import org.eclipse.swt.graphics.Font;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;

import com.casa.vide.appassemble.figure.border.BottomBorder;

public class MapFigure extends Figure {

	//private Map<Object, Object> content = null;
	private Label title = null;
	private Figure contentFigure = new Figure();
	private int gridWidth = 1;
	
	public MapFigure(String title) {		
		this(title, null);
	}
	
	public MapFigure(String title, Map<Object, Object> content) {
		ToolbarLayout layout = new ToolbarLayout();
		setLayoutManager(layout);
		this.title = new Label(title);
		this.title.setFont(new Font(null, "Arial", 10, SWT.BOLD));
		this.title.setBorder(new BottomBorder());
		add(this.title);
		GridLayout grid = new GridLayout(2, true);
		grid.verticalSpacing = 2;
		grid.setObserveVisibility(true);
		contentFigure.setLayoutManager(grid);
		add(contentFigure);
		setContent(content);
	}
	
	public void setContent(Map<Object, Object> content) {
		//this.content = content;
		contentFigure.removeAll();
		if(content == null)
			return ;
		Label value = null;
		Label key = null;
		Iterator<Entry<Object, Object>> iterator = content.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Object, Object> element = iterator.next();
			key = new Label(element.getKey().toString());
			String strValue = element.getValue() == null ? null : element.getValue().toString();
			value = new Label(strValue);
			contentFigure.add(key);
			contentFigure.add(value);
		}
	}
	
	@Override
	public void paintFigure(Graphics g) {
		super.paintFigure(g);
		Rectangle rec = contentFigure.getBounds();
		int rows = contentFigure.getChildren().size() / 2;
		int internal = rows == 0 ? rec.height : rec.height / rows;
		g.setLineWidth(gridWidth);
		g.drawLine(rec.x+rec.width/2, rec.y, rec.x+rec.width/2, rec.y+rec.height-1);
		for(int h = rec.y+internal; h < rec.y+rec.height-rows; h += internal)
			g.drawLine(rec.x, h-gridWidth, rec.x+rec.width, h-gridWidth);
	}

}
