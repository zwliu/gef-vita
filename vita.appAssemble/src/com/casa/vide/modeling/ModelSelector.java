package com.casa.vide.modeling;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.widgets.Shell;

public class ModelSelector implements IModelSelector {

	@Override
	public boolean openModelSelectWizard(Shell shell) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getVdlName() {
		// TODO Auto-generated method stub
		return "vdlName";
	}

	@Override
	public String getVomName() {
		// TODO Auto-generated method stub
		return "name";
	}
	
	@Override
	public String getInstanceName() {
		return "instancName";
	}

	@Override
	public Set<String> getImports() {
		// TODO Auto-generated method stub
		Set<String> imports = new HashSet<String>();
		imports.add("import 1");
		imports.add("import 2");
		imports.add("import 3");
		imports.add("import 4");
		imports.add("import 5");
		return imports;
	}

	@Override
	public Set<String> getVIOs() {
		// TODO Auto-generated method stub
		Set<String> imports = new HashSet<String>();
		imports.add("VIO 1");
		imports.add("VIO 2");
		imports.add("VIO 3");
		imports.add("VIO 4");
		imports.add("VIO 5");
		return imports;
	}

	@Override
	public Set<String> getMessages() {
		// TODO Auto-generated method stub
		Set<String> imports = new HashSet<String>();
		imports.add("Message 1");
		imports.add("Message 2");
		imports.add("Message 3");
		return imports;
	}

}
