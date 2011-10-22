package tudresden.ocl20.pivot.tracer.ui.internal.views.util;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.interpreter.ui.InterpreterUIPlugin;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.*;


public class TracerItemAdapterFactoryLabelProvider extends
		AdapterFactoryLabelProvider {
	
	private TracerExpressionsSwitch tracerExprSwitch;
	
	public TracerItemAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		tracerExprSwitch = new TracerExpressionsSwitch();
	}
	
	public String getText(Object object) {		
		if(object instanceof TracerItem) {
			TracerItem item = (TracerItem)object;
			
			//check if the item has been fully build
			//
			if((item.getExpression() == null) && (item.getResult() == null)) {
				return null;
			}
			
			String result;
			result = tracerExprSwitch.doSwitch((EObject)item.getExpression());
			result += " RESULT: ";
			if(item != null) result += item.getResult();
			return result;
		}
		else return super.getText(object);
	}
	
	public Image getImage(Object object) {
		if(object instanceof TracerItem) {

			ImageDescriptor imageDescriptor;
			OclAny result = ((TracerItem)object).getResult();
			
			if(result instanceof OclBoolean) {
				OclBoolean anOclBoolean = (OclBoolean)result;
				
				//check the result of this OclBoolean
				//
				if(anOclBoolean.oclIsInvalid().isTrue()) {
					imageDescriptor = InterpreterUIPlugin.getImageDescriptor("icons/result_undefined.gif");
				}
				else if(anOclBoolean.oclIsUndefined().isTrue()) {
					imageDescriptor = InterpreterUIPlugin.getImageDescriptor("icons/result_invalid.gif");
				}
				else if(anOclBoolean.isTrue()) {
					imageDescriptor = InterpreterUIPlugin.getImageDescriptor("icons/result_true.gif");
				}
				else {
					imageDescriptor = InterpreterUIPlugin.getImageDescriptor("icons/result_false.gif");
				}
			}
			else imageDescriptor = null;
			
			if(imageDescriptor != null) {
				return imageDescriptor.createImage();
			}
		}

		return super.getImage(object);
	  }
}
