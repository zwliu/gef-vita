package vita.appassemble.part;

import org.eclipse.draw2d.IFigure;

import vita.appassemble.figure.VOMFigure;
import vita.appassemble.model.VOM;

public class VOMPart extends NodePart {

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		VOMFigure figure = new VOMFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();	//½ö½öÊÇlayout
	}
	
	@Override
	protected void refreshVisuals() {
		VOMFigure figure = (VOMFigure)getFigure();
		VOM model = (VOM)getModel();
		figure.setName(model.getName());
		figure.setLayout(model.getLayout());
	}

}
