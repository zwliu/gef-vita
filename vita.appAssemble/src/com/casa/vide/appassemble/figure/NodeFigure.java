package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.XYLayout;

/**
 * 基本图元的图形，是一个XYLayout的空白容器
 *
 * @author lzw
 */
public class NodeFigure extends Figure {
	
	public NodeFigure() {
		XYLayout layout = new XYLayout();
		setLayoutManager(layout);
	}
}
