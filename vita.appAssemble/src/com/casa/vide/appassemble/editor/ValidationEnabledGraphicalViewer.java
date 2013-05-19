/*
 * Created on Jul 19, 2004
 */
package com.casa.vide.appassemble.editor;

import org.eclipse.gef.ui.parts.AbstractEditPartViewer;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.swt.events.FocusEvent;

import com.casa.vide.appassemble.directedit.ValidationMessageHandler;

/**
 * GraphicalViewer which also knows about ValidationMessageHandler to output
 * error messages to
 * @author Phil Zoio
 */
public class ValidationEnabledGraphicalViewer extends ScrollingGraphicalViewer
{

	private ValidationMessageHandler messageHandler;

	/**
	 * ValidationMessageHandler to receive messages
	 * @param messageHandler
	 */
	public ValidationEnabledGraphicalViewer(ValidationMessageHandler messageHandler)
	{
		super();
		this.messageHandler = messageHandler;
	}

	/**
	 * @return Returns the messageLabel.
	 */
	public ValidationMessageHandler getValidationHandler()
	{
		return messageHandler;
	}

	/**
	 * This method is invoked when this viewer's control loses focus. It removes
	 * focus from the {@link AbstractEditPartViewer#focusPart focusPart}, if
	 * there is one.
	 * 
	 * @param fe
	 *            the focusEvent received by this viewer's control
	 */
	protected void handleFocusLost(FocusEvent fe)
	{
		//give the superclass a chance to handle this first
		super.handleFocusLost(fe);
		//call reset on the MessageHandler itself
		messageHandler.reset();
	}

}