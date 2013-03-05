package vita.appassemble.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import vita.appassemble.command.NodeDeleteCommand;

public class NodeDeleteEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		NodeDeleteCommand command = new NodeDeleteCommand();
		command.setChild(getHost().getModel());
		command.setParent(getHost().getParent().getModel());
		return command;
	}
}
