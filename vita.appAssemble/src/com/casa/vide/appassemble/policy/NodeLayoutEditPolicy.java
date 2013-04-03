package com.casa.vide.appassemble.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import com.casa.vide.appassemble.command.NodeChangeLayoutCommand;
import com.casa.vide.appassemble.command.NodeCreateCommand;
import com.casa.vide.appassemble.model.Node;


public class NodeLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		NodeCreateCommand command = null;
		if(request.getType() == REQ_CREATE) {
			//包含规则？？？？
			command = new NodeCreateCommand();
			Object parent = getHost().getModel();
			Object child = request.getNewObject();
			if(parent instanceof Node)
				command.setParent((Node)parent);
			if(child instanceof Node)
				command.setChild((Node)child);
			Rectangle constraint = (Rectangle)getConstraintFor(request);
			constraint.x = constraint.x < 0 ? 0 : constraint.x;
			constraint.y = constraint.y < 0 ? 0 : constraint.y;
			constraint.width = constraint.width < 0 ? 100/*暂时定100*/  : constraint.width;
			constraint.height = constraint.height < 0 ? 100 : constraint.height;
			command.setLayout(constraint);
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
