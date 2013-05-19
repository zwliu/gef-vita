package com.casa.vide.appassemble.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.casa.vide.appassemble.command.ConnectionCommand;
import com.casa.vide.appassemble.model.Connection;
import com.casa.vide.appassemble.model.Shape;

public class ConnectionPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		ConnectionCommand command = (ConnectionCommand)request.getStartCommand();
		command.setTarget((Shape)getHost().getModel());
		return command;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		ConnectionCommand command = new ConnectionCommand();
		command.setConnection(new Connection());
		command.setSource((Shape)getHost().getModel());
		request.setStartCommand(command);
		return command;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		ConnectionCommand command = new ConnectionCommand();
		command.setSource((Shape)getHost().getModel());
		return command;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ConnectionCommand command = new ConnectionCommand();
		command.setTarget((Shape)getHost().getModel());
		return command;
	}

}
