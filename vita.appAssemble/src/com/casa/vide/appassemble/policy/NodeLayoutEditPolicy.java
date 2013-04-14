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
import com.casa.vide.appassemble.model.Node;


public class NodeLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		Command command = null;
		if(request.getType() == REQ_CREATE) {
			Object parent = getHost().getModel();
			Object child = request.getNewObject();
			if(parent instanceof APP && child instanceof Image) {
				command = new NodeChangeBackgroundCommand(); 
				((NodeChangeBackgroundCommand)command).setNode((APP)parent);
				((NodeChangeBackgroundCommand)command).setBackground((Image)child);
			}
			else if(parent instanceof Node && child instanceof Node) {
				command = new NodeCreateCommand();
				((NodeCreateCommand)command).setParent((Node)parent);
				((NodeCreateCommand)command).setChild((Node)child);
				Rectangle constraint = (Rectangle)getConstraintFor(request); //当建app与vom有重叠时出现异常
				constraint.x = constraint.x < 0 ? 0 : constraint.x;
				constraint.y = constraint.y < 0 ? 0 : constraint.y;
				constraint.width = constraint.width < 0 ? 100/*��ʱ��100*/  : constraint.width;
				constraint.height = constraint.height < 0 ? 100 : constraint.height;
				((NodeCreateCommand)command).setLayout(constraint);
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
