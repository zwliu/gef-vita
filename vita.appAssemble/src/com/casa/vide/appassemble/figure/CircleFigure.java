package com.casa.vide.appassemble.figure;

import org.eclipse.draw2d.AbstractBorder;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.draw2d.geometry.Point;

public class CircleFigure extends Figure {
	
	private Label type;
	
//	public CircleFigure(EditableLabel name) {
//        this(name, null);
//        setOpaque(true);
//        setBackgroundColor(DPFEditorPreferences.getDefault().getNodeColor());
//        listenToNodeColorProperty();
//    }
//
//    @SuppressWarnings("rawtypes")
//    public CircleFigure(EditableLabel name, List colums) {
//            ToolbarLayout layout = new ToolbarLayout();
//            layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
//            layout.setStretchMinorAxis(false);
//            layout.setSpacing(2);
//            setLayoutManager(layout);
//            setBorder(new CircleBorder());
//            setOpaque(true);
//            add(name);
//            nameLabel = name;
//    }

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
                    int r = tempRect.height > tempRect.width ? tempRect.width : tempRect.height;
                    graphics.drawOval(c.x - r/2, c.y - r/2 , r, r);
            }

    }

	public CircleFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		layout.setHorizontal(false);
		layout.setMinorAlignment(ToolbarLayout.ALIGN_CENTER);
        layout.setStretchMinorAxis(false);
        layout.setSpacing(2);
		setLayoutManager(layout);
		type = new Label();
		add(type);
		setBorder(new CircleBorder());
		setOpaque(true);
	}
	
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
	}
	
	public void setType(String type) {
		this.type.setText(type);
	}
}
