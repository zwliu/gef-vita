package vita.appassemble.figure;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Rectangle;

public class APPFigure extends Figure {
	
private Label name;
	
	public APPFigure() {
		XYLayout layout = new XYLayout();
//		layout.setHorizontal(false);
//		layout.setSpacing(5);
		setLayoutManager(layout);
		name = new Label();		
		add(name);
		setConstraint(name, new Rectangle(0, 0, -1, -1));
		
		setBackgroundColor(ColorConstants.yellow);
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
