package com.casa.vide.appassemble.policy;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import com.casa.vide.appassemble.command.ConnectionCommand;
import com.casa.vide.appassemble.model.Connection;
import com.casa.vide.appassemble.model.Shape;

/**
 * 创建、修改连接的EditPolicy
 *
 * @author lzw
 */
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
	
	/**
	 * 获取连接源的连接点，并设置为该连接的源连接锚点
	 */
	@Override
	protected ConnectionAnchor getSourceConnectionAnchor(
			CreateConnectionRequest request) {
		ConnectionCommand command = (ConnectionCommand)request.getStartCommand();
		Connection conn = command.getConnection();
		ConnectionAnchor anchor = super.getSourceConnectionAnchor(request);
		if (conn != null)
			conn.setSourceAnchor(anchor); //设置为该连接的源连接锚点
		return anchor;
	}
	
	/**
	 * 获取连接目标的连接点，并设置为该连接的目标连接锚点
	 */
	@Override
	protected ConnectionAnchor getTargetConnectionAnchor(
			CreateConnectionRequest request) {
		ConnectionCommand command = (ConnectionCommand)request.getStartCommand();
		Connection conn = command.getConnection();
		ConnectionAnchor anchor = super.getTargetConnectionAnchor(request);
		if (conn != null)
			conn.setTargetAnchor(anchor); //设置为该连接的目标连接锚点
		return anchor;
	}

}
