/*
 * Created on Jul 25, 2004
 */
package com.casa.vide.appassemble.directedit;

/**
 * Represents interface for outputting validation error messages to some widget
 * @author lzw
 */
public interface ValidationMessageHandler
{

	public void setMessageText(String text);

	/**
	 * Resets so that the validation message is no longer shown
	 */
	public void reset();
}