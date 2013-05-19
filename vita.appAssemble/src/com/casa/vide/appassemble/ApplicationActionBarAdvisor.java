package com.casa.vide.appassemble;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.casa.vide.appassemble.editor.AppAssembleEditor;
import com.casa.vide.appassemble.editor.AppAssembleEditorInput;
import com.casa.vide.appassemble.editor.ExpSchemeEditor;


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	IAction	action;
	IAction schemeAction;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	action = new Action("应用建模"){
    		public void run() {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new AppAssembleEditorInput("应用建模"), AppAssembleEditor.ID, false);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	};
    	action.setId("App_Modeling");
    	
    	schemeAction = new Action("试验规划") {
    		public void run() {
    			try {
    				PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new AppAssembleEditorInput("试验规划"), ExpSchemeEditor.ID, false);   			
    			}catch(PartInitException e) {
    				e.printStackTrace();
    			}
    		}
    	};
    	schemeAction.setId("Exp_Scheme");
		register(action);
		register(schemeAction);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	menuBar.add(action);
    	menuBar.add(schemeAction);
    }
    
}
