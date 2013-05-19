package com.casa.vide.appassemble.figure.border;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class CircleBorder extends AbstractBorder {

    public Insets getInsets(IFigure figure) {
            return new Insets(10);
    }

    public void paint(IFigure figure, Graphics graphics, Insets insets) {
            tempRect.setBounds(getPaintRectangle(figure, insets));          
            int width = graphics.getLineWidth();
            if(width == 0)
                    width = 1;
            tempRect.shrink(width, width);
            Point c = tempRect.getCenter();
            int r = tempRect.height > tempRect.width ? tempRect.height : tempRect.width;
            Rectangle rec = (Rectangle)figure.getParent().getLayoutManager().getConstraint(figure);
            rec.height = r;
            rec.width = r;
            graphics.drawOval(c.x - r/2, c.y - r/2 , r, r);
    }

}
