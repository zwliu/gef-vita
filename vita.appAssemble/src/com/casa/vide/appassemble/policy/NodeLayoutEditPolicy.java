package com.casa.vide.appassemble.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.graphics.Image;

import com.casa.vide.appassemble.command.NodeChangeBackgroundCommand;
import com.casa.vide.appassemble.command.NodeChangeLayoutCommand;
import com.casa.vide.appassemble.command.NodeCreateCommand;
import com.casa.vide.appassemble.model.APP;
import com.casa.vide.appassemble.model.EditableLabelModel;
import com.casa.vide.appassemble.model.Node;
import com.casa.vide.appassemble.model.Shape;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.modelinterface.IElement;

public class NodeLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		Command command = null;
		if(request.getType() == REQ_CREATE) {
			Object parent = getHost().getModel();
			Object child = request.getNewObject();
			if(parent instanceof Shape && child instanceof Image) {
				command = new NodeChangeBackgroundCommand(); 
				((NodeChangeBackgroundCommand)command).setNode((Shape)parent);
				((NodeChangeBackgroundCommand)command).setBackground((Image)child);
			}
			else if(parent instanceof Node && child instanceof Node) {
				if(child instanceof APP && parent.getClass() != Node.class) {
					return command;
				}
				else if(child instanceof VOM && !(parent instanceof APP)) {
					return command;
				}
				else if(child instanceof IElement && !(parent instanceof VOM)) {
					return command;
				}
				command = new NodeCreateCommand();
				((NodeCreateCommand)command).setParent((Node)parent);
				((NodeCreateCommand)command).setChild((Node)child);
				try {
					Object constraint = getConstraintFor(request);
					if(constraint instanceof Rectangle) {
						Rectangle rec = (Rectangle)constraint;
						rec.x = rec.x < 0 ? 0 : rec.x;
						rec.y = rec.y < 0 ? 0 : rec.y;
						if(child instanceof Shape) {
							rec.width = rec.width < 0 ? 100/*��ʱ��100*/  : rec.width;
							rec.height = rec.height < 0 ? 100 : rec.height;
						}
						else if(child instanceof EditableLabelModel) {
							rec.width = rec.width < 0 ? 60  : rec.width;
							rec.height = rec.height < 0 ? 20 : rec.height;
						}
						else if(child instanceof IElement) {
							rec.width = rec.width < 0 ? 50  : rec.width;
							rec.height = rec.height < 0 ? 50 : rec.height;
						}
					}				
					((NodeCreateCommand)command).setLayout(constraint);
				}catch(ClassCastException e) {
					if(child instanceof IElement)
						((NodeCreateCommand)command).setLayout(new Rectangle(0, 0, 50, 50));
					else
						((NodeCreateCommand)command).setLayout(new Rectangle(0, 0, -1, -1));
				}
			}
		}
		return command;
	}
	
	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		NodeChangeLayoutCommand command = new NodeChangeLayoutCommand();
		command.setModel(child.getModel());
		command.setConstraint(constraint);
		return command;
	}

}
