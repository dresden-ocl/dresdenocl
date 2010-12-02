/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclNewFileContentProvider {
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclMetaInformation getMetaInformation() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclMetaInformation();
	}
	
	public String getNewFileContent(String newFileName) {
		return getExampleContent(new org.eclipse.emf.ecore.EClass[] {
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(),
			tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(),
		}, getMetaInformation().getClassesWithSyntax(), newFileName);
	}
	
	protected String getExampleContent(org.eclipse.emf.ecore.EClass[] startClasses, org.eclipse.emf.ecore.EClass[] allClassesWithSyntax, String newFileName) {
		String content = "";
		for (org.eclipse.emf.ecore.EClass next : startClasses) {
			content = getExampleContent(next, allClassesWithSyntax, newFileName);
			if (content.trim().length() > 0) {
				break;
			}
		}
		return content;
	}
	
	protected String getExampleContent(org.eclipse.emf.ecore.EClass eClass, org.eclipse.emf.ecore.EClass[] allClassesWithSyntax, String newFileName) {
		// create a minimal model
		org.eclipse.emf.ecore.EObject root = new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclMinimalModelHelper().getMinimalModel(eClass, allClassesWithSyntax, newFileName);
		// use printer to get text for model
		java.io.ByteArrayOutputStream buffer = new java.io.ByteArrayOutputStream();
		tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextPrinter printer = getPrinter(buffer);
		try {
			printer.print(root);
		} catch (java.io.IOException e) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPlugin.logError("Exception while generating example content.", e);
		}
		return buffer.toString();
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextPrinter getPrinter(java.io.OutputStream outputStream) {
		return getMetaInformation().createPrinter(outputStream, new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource());
	}
	
}
