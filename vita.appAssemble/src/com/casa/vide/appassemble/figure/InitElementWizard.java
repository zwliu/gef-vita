package com.casa.vide.appassemble.figure;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.casa.vide.appassemble.model.Message;
import com.casa.vide.appassemble.model.VIO;
import com.casa.vide.appassemble.model.VOM;
import com.casa.vide.appassemble.modelinterface.IElement;

public class InitElementWizard extends Wizard {
	
	private class InitElementPage extends WizardPage {
		
		public Text instanceName;
		public Combo name;
		public Text vdlName;

		public InitElementPage(String pageName) {
			super(pageName);
			setTitle(pageName);
		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE);
			instanceName = new Text(composite, SWT.NONE);
			name = new Combo(composite, SWT.NONE);
			if(type == VIO.class)
				name.setItems(vom.getVIOs().toArray(new String[vom.getVIOs().size()]));
			else if(type == Message.class)
				name.setItems(vom.getMessages().toArray(new String[vom.getMessages().size()]));
			vdlName = new Text(composite, SWT.NONE);
			vdlName.setText(vom.getVdlName());
			vdlName.setEditable(false);
			RowLayout l = new RowLayout(); 
			composite.setLayout(l);
			setControl(composite);
		}
		
	}
	
	private VOM vom;
	private Class<? extends IElement> type;
	private String name;
	private String instanceName;
	
	public InitElementWizard(VOM vom, Class<? extends IElement> type) {
		this.vom = vom;
		this.type = type;	
		addPage(new InitElementPage("init element"));
	}

	@Override
	public boolean performFinish() {
		InitElementPage page = (InitElementPage)getPage("init element");
		if(page.instanceName.getText() == null 
				|| page.vdlName.getText() == null 
				|| page.name.getText() == null) {
			page.setErrorMessage("instance name、name、vdl name均不能为空");
			return false;
		}
		name = page.name.getText();
		instanceName = page.instanceName.getText();
		return true;
	}

	public String getName() {
		return name;
	}

	public String getInstanceName() {
		return instanceName;
	}
	
}
