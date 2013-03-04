package vita.appassemble.part;

import java.util.List;

import org.eclipse.draw2d.IFigure;

import vita.appassemble.figure.APPFigure;
import vita.appassemble.model.APP;

public class APPPart extends NodePart {

	@Override
	protected IFigure createFigure() {
		// TODO Auto-generated method stub
		APPFigure figure = new APPFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		super.createEditPolicies();  	//½ö½öÊÇlayout
	}
	
	@Override
	protected void refreshVisuals() {
		APPFigure figure = (APPFigure)getFigure();
		APP model = (APP)getModel();
		figure.setName(model.getName());
		figure.setLayout(model.getLayout());
	}
	
	@Override
	public List<Object> getModelChildren() {
		return ((APP)getModel()).getChildren();
	}

}
