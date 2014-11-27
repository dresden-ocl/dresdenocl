/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

public class OclNewFileContentProvider {
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclMetaInformation getMetaInformation() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation();
	}
	
	public String getNewFileContent(String newFileName) {
		return getExampleContent(new EClass[] {
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithNamespaceCS(),
			org.dresdenocl.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationWithoutNamespaceCS(),
		}, getMetaInformation().getClassesWithSyntax(), newFileName);
	}
	
	protected String getExampleContent(EClass[] startClasses, EClass[] allClassesWithSyntax, String newFileName) {
		String content = "";
		for (EClass next : startClasses) {
			content = getExampleContent(next, allClassesWithSyntax, newFileName);
			if (content.trim().length() > 0) {
				break;
			}
		}
		return content;
	}
	
	protected String getExampleContent(EClass eClass, EClass[] allClassesWithSyntax, String newFileName) {
		// create a minimal model
		EObject root = new org.dresdenocl.language.ocl.resource.ocl.util.OclMinimalModelHelper().getMinimalModel(eClass, allClassesWithSyntax, newFileName);
		if (root == null) {
			// could not create a minimal model. returning an empty document is the best we
			// can do.
			return "";
		}
		// use printer to get text for model
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		org.dresdenocl.language.ocl.resource.ocl.IOclTextPrinter printer = getPrinter(buffer);
		try {
			printer.print(root);
		} catch (IOException e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Exception while generating example content.", e);
		}
		return buffer.toString();
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextPrinter getPrinter(OutputStream outputStream) {
		return getMetaInformation().createPrinter(outputStream, new org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource());
	}
	
}
