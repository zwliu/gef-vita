package com.casa.vide.appassemble.part;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * 位置固定的锚点
 * @author lzw
 *
 */
public class FixedAnchor extends AbstractConnectionAnchor {
	
	/** 比例*/
	private PrecisionPoint scale;
	
	public FixedAnchor() {
		super();
		scale = new PrecisionPoint();
	}
	
	/** 构建一个this.owner为owner的FixedAnchor*/
	public FixedAnchor(IFigure owner) {
		super(owner);
		scale = new PrecisionPoint();
	}
	
	/** 构建一个this.owner为owner的FixedAnchor, X方向比例为Xscale， Y方向比例为Yscale*/
	public FixedAnchor(IFigure owner, double Xscale, double Yscale) {
		super(owner);
		scale = new PrecisionPoint(Xscale, Yscale);
	}
	
	@Override
	public Point getLocation(Point reference) {
		Rectangle rec = getOwner().getBounds();
		int x = (int) (rec.x + rec.width * scale.preciseX());
		int y = (int) (rec.y + rec.height * scale.preciseY());
		Point p = new PrecisionPoint(x, y);
		getOwner().translateToAbsolute(p);
		return p;
	}

}
