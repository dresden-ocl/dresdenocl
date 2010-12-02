package tudresden.ocl20.eclipse.plugins.visual.modelfactory;

import java.util.Collection;

import javax.jmi.reflect.RefPackage;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.jmi.cwm.relational.Column;
import tudresden.ocl20.core.jmi.cwm.relational.Table;
import tudresden.ocl20.eclipse.OCLToolkitForEclipsePlugin;
import tudresden.ocl20.eclipse.plugins.visual.model.DefaultVisualNode;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualNode;
import tudresden.ocl20.eclipse.plugins.visual.util.TopNode;
import tudresden.ocl20.transformation.TransformationEngine;
import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.exception.TransformationException;
import tudresden.ocl20.transformation.impl.Uml2CwmImpl;


public class CwmModelFactory extends Ocl2ToolkitModelFactory {

	
	public void init(){
		doNotCall.add("getNamespace");
		doNotCall.add("getChildren");
//		doNotCall.add("getFeature");
		doNotCall.add("getGeneralization");
		doNotCall.add("getSpecialisation");
		doNotCall.add("getOwnedElement");
		includeClasses.add(Table.class);
		includeClasses.add(Column.class);
		methodPrefix = "get";
		labelMethod = "getName";
		
	}
	@Override
	public Object getStartObject(Object context) {
		String modelName="";
		try {
			Collection c=ModelManager.getInstance().getAllModelNames();
			c.size();
		} catch (ModelManagerException e1) {
			e1.printStackTrace();
		}
		try {
			modelName=ModelManager.getInstance().getName((RefPackage)context);
		} catch (ModelManagerException e) {
			e.printStackTrace();
		}
		TransformationEngine te = TransformationEngine.getInstance();
		te.setResourcePath(OCLToolkitForEclipsePlugin.getDefault().getResource("resources/cwm/").getPath());
		te.setModel_inName(modelName);
		te.setModel_inType(MetaModelConst.UML15);
		te.setModel_outName(modelName+"-cwm");
		te.loadTransformation(Uml2CwmImpl.class.getSimpleName());
		try {
			te.invoke();
		} catch (TransformationException e) {
			e.printStackTrace();
		} catch (InvalidModelException e) {
			e.printStackTrace();
		}

		TopNode f=new TopNode((RefPackage)te.getResult(), Table.class);
		return f;
	}

	@Override
	public Collection getContextList() {
		try{
		return ModelManager.getInstance().getModels(MetaModelConst.UML15);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getContextDisplayName(Object context) {
		return ((RefPackage)context).refMofId();
	}

}
