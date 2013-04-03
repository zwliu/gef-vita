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


public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	IAction	action;
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	action = new Action("open"){
    		public void run() {
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().openEditor(new AppAssembleEditorInput("WBHGEF"), AppAssembleEditor.ID, false);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	};
    	action.setId("test");
		register(action);
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	menuBar.add(action);
    }
    
}
