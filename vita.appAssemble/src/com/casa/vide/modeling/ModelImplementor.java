package com.casa.vide.modeling;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Shell;

public class ModelImplementor implements IModelImplementor {

	String[] imports;
	
	@Override
	public boolean openModelImplWizard(Shell shell, String[] imports,
			String VomElement) {
		this.imports = imports;
		return true;
	}

	@Override
	public Map<String, String> getRequires() {
		Map<String, String> map = new HashMap<String, String>();
		for(String str : imports) {
			map.put(str, str+" Implement");
		}
		return map;
	}

}
