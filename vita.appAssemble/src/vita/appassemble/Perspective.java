package vita.appassemble;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import vita.appassemble.editor.AppAssembleEditor;
import vita.appassemble.editor.AppAssembleEditorInput;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		try {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			page.openEditor(new AppAssembleEditorInput("Application Assemble"), AppAssembleEditor.ID, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
