package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;

/**
 * FlowLayout的Figure，在VOM图元中，用来容纳VIO/Message图元
 *
 * @author lzw
 */
public class FlowFigure extends Figure {

	public FlowFigure() {
		FlowLayout layout = new FlowLayout();
		setLayoutManager(layout);
		this.setOpaque(false);
	}
	
}
