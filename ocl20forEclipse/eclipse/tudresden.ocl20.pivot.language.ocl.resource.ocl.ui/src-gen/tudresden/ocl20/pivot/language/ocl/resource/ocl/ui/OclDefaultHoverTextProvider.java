/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.ui;

public class OclDefaultHoverTextProvider implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclHoverTextProvider {
	
	public java.lang.String getHoverText(org.eclipse.emf.ecore.EObject object) {
		if (object == null) {
			return null;
		}
		org.eclipse.emf.ecore.EClass eClass = object.eClass();
		String label = "<strong>" + eClass.getName() + "</strong>";
		String documentation = org.eclipse.emf.ecore.util.EcoreUtil.getDocumentation(eClass);
		String documentationHTML = documentation == null ? "" : " (" + documentation +")";
		label += documentationHTML;
		for (org.eclipse.emf.ecore.EAttribute attribute : eClass.getEAllAttributes()) {
			java.lang.Object value = null;
			try {
				value = object.eGet(attribute);
			} catch (java.lang.Exception e) {
				// Exception in eGet, do nothing
			}
			if (value != null && value.toString() != null && !value.toString().equals("[]")) {
				label += "<br />" + attribute.getName() + ": " + object.eGet(attribute).toString();
			}
		}
		return label;
	}
}
