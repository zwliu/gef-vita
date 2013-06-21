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

/**
 * 修改布局约束的EditPolicy
 *
 * @author lzw
 */
public class NodeLayoutEditPolicy extends XYLayoutEditPolicy {

	/**
	 * 获取创建图元的命令
	 */
	@Override
	protected Command getCreateCommand(CreateRequest request) {
		// TODO Auto-generated method stub
		Command command = null;
		if(request.getType() == REQ_CREATE) {   //创建
			Object parent = getHost().getModel();
			Object child = request.getNewObject();
			if(parent instanceof Shape && child instanceof Image) {  //实体图元拖拽换背景
				command = new NodeChangeBackgroundCommand(); 
				((NodeChangeBackgroundCommand)command).setNode((Shape)parent);
				((NodeChangeBackgroundCommand)command).setBackground((Image)child);
			}
			else if(parent instanceof Node && child instanceof Node) {  
				if(child instanceof APP && parent.getClass() != Node.class) { //APP图元只能放在最外层
					return command;
				}
				else if(child instanceof VOM && !(parent instanceof APP)) { //VOM图元只能放在APP图元中
					return command;
				}
				else if(child instanceof IElement && !(parent instanceof VOM)) { //VIO/Message图元只能放在VOM图元中
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
						if(child instanceof Shape) { //实体图元默认大小100，100
							rec.width = rec.width < 0 ? 100/*��ʱ��100*/  : rec.width;
							rec.height = rec.height < 0 ? 100 : rec.height;
						}
						else if(child instanceof EditableLabelModel) { //EditaleLabel默认大小60，20
							rec.width = rec.width < 0 ? 60  : rec.width;
							rec.height = rec.height < 0 ? 20 : rec.height;
						}
						else if(child instanceof IElement) { //VIO/Message图元默认大小50，50
							rec.width = rec.width < 0 ? 50  : rec.width;
							rec.height = rec.height < 0 ? 50 : rec.height;
						}
					}				
					((NodeCreateCommand)command).setLayout(constraint);
				}catch(ClassCastException e) {
					if(child instanceof IElement) //VIO/Message图元默认大小50，50
						((NodeCreateCommand)command).setLayout(new Rectangle(0, 0, 50, 50));
					else
						((NodeCreateCommand)command).setLayout(new Rectangle(0, 0, -1, -1));
				}
			}
		}
		return command;
	}
	
	/**
	 * 创建修改布局约束的命令，即NodeChangeLayoutCommand
	 */
	@Override
	protected Command createChangeConstraintCommand(EditPart child, Object constraint) {
		NodeChangeLayoutCommand command = new NodeChangeLayoutCommand();
		command.setModel(child.getModel());
		command.setConstraint(constraint);
		return command;
	}

}
