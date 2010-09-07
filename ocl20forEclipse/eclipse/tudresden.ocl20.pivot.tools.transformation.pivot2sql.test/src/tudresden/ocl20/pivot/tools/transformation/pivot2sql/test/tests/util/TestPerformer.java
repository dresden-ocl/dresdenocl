package tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util;

import java.io.File;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;



public class TestPerformer {

	private static String TEMPLATE_NAME = "Standard(SQL)";
	
	/**
	 * <p>
	 * Creates a new TestPerformer.
	 * </p>
	 */
	private TestPerformer() {
		
	}
	
	public static IModel addUMLModel(File file) throws IllegalArgumentException, ModelAccessException {
		return Ocl2ForEclipseFacade.getModel(file, Ocl2ForEclipseFacade.UML2_MetaModel);
	}
	
	public static boolean removeUMLModel(IModel model) {
		return Ocl2ForEclipseFacade.removeModel(model);
	}
	
	public static ITemplateGroup getTemplateGroup() {
		return Ocl2ForEclipseFacade.getTemplateGroup(TEMPLATE_NAME);
	}
	
	public static IOcl2DeclSettings getSettings() {
		IOcl2DeclSettings oclSettings = Ocl2ForEclipseFacade.getDeclCodeGeneratorSettings();
		oclSettings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry().getTemplateGroup(TEMPLATE_NAME));
		return oclSettings;
			
	}
}
