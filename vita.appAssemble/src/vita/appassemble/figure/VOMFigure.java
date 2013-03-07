package vita.appassemble.figure;

import java.io.File;
import java.io.FileInputStream;

import org.eclipse.draw2d.AbstractBackground;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

public class VOMFigure extends Figure {
	
	private Label name;
	private static Image background = null;
	static {
		System.out.println(new File("").getAbsolutePath());
		File file = new File("C:/Users/lzw/git/gef-vita/vita.appAssemble/icons/alt_about.gif");
		try {
			ImageData data = new ImageData(new FileInputStream(file));
			background = new Image(Display.getDefault(), data);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
	
	public void setLayout(Object constraint) {
		getParent().setConstraint(this, constraint);
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
