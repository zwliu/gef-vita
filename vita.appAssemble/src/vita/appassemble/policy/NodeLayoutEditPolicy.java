package vita.appassemble.policy;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import vita.appassemble.command.NodeCreateCommand;
import vita.appassemble.model.Node;

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

}
