package org.eclipse.m2m.atl.examples.counteobjects;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.m2m.atl.emftvm.Model;
import org.eclipse.m2m.atl.emftvm.util.LazyList;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.PlatformUI;

public class PopupAction implements IActionDelegate {

	final CountEObjects countEObjects = new CountEObjects();

	IAction action;
	ISelection selection;

	@Override
	public void run(final IAction action) {
		try {
			final IFile file = (IFile) ((IStructuredSelection) selection).getFirstElement();
			final Model outModel = countEObjects.apply(file);
			final LazyList<EObject> eAnnotations = outModel.allInstancesOf(EcorePackage.eINSTANCE.getEAnnotation());
			final EAnnotation count = (EAnnotation) eAnnotations.get(0);

			final MessageDialog messageDialog = new MessageDialog(
					PlatformUI.getWorkbench().getDisplay().getActiveShell(), "CountEObjects", null,
					"The number of EObjects in the selected file is: " + count.getSource(), MessageDialog.INFORMATION,
					0, "OK");
			PlatformUI.getWorkbench().getDisplay().syncExec(() -> messageDialog.open());
		} catch (final RuntimeException e) {
			final MessageDialog messageDialog = new MessageDialog(
					PlatformUI.getWorkbench().getDisplay().getActiveShell(), "CountEObjects", null,
					"Could not count EObjects: " + e.getMessage(), MessageDialog.ERROR,
					0, "OK");
			PlatformUI.getWorkbench().getDisplay().syncExec(() -> messageDialog.open());
		}
	}

	@Override
	public void selectionChanged(final IAction action, final ISelection selection) {
		this.action = action;
		this.selection = selection;
	}

}
