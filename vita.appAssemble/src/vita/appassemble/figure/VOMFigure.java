package vita.appassemble.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class VOMFigure extends Figure {
	
	private Label name;
	
	public VOMFigure() {
		ToolbarLayout layout = new ToolbarLayout();
		layout.setHorizontal(false);
		layout.setSpacing(5);
		setLayoutManager(layout);
		name = new Label();
		add(name);
		setBackgroundColor(ColorConstants.lightGreen);
		setBorder(new LineBorder(1));
		setOpaque(true);
	}
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public void setLayout(Rectangle constraint) {
		getParent().setConstraint(this, constraint);
	}
	
}
