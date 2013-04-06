package org.dresdenocl.tools.transformation.pivot2sql.test.tests.util;

import static org.junit.Assert.fail;

import java.io.File;

import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.Ocl2DeclCodeFactory;
import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.exception.TemplateException;

public class TestPerformer {

	private static String TEMPLATE_NAME = "Standard(SQL)";

	/**
	 * <p>
	 * Creates a new TestPerformer.
	 * </p>
	 */
	private TestPerformer() {

	}

	public static IModel addUMLModel(File file) throws IllegalArgumentException,
			ModelAccessException {

		return ModelBusPlugin.getMetamodelRegistry()
				.getMetamodel(UML2MetamodelPlugin.ID).getModelProvider().getModel(file);
	}
	
	
	public static IModel addEcoreModel(File file) throws IllegalArgumentException,
	ModelAccessException {

		return ModelBusPlugin.getMetamodelRegistry()
		.getMetamodel(EcoreMetamodelPlugin.ID).getModelProvider().getModel(file);
	}

	public static boolean removeUMLModel(IModel model) {

		return ModelBusPlugin.getModelRegistry().removeModel(model);
	}

	public static ITemplateGroup getTemplateGroup() {

		try {
			return TemplatePlugin.getTemplateGroupRegistry().getTemplateGroup(
					TEMPLATE_NAME);
		} catch (TemplateException e) {
			fail("Can't load the template group.");
			return null;
		}
	}

	public static IOcl2DeclSettings getSettings() {

		IOcl2DeclSettings oclSettings =
				Ocl2DeclCodeFactory.getInstance().createOcl2DeclCodeSettings();
		try {
			oclSettings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
					.getTemplateGroup(TEMPLATE_NAME));
		} catch (TemplateException e) {
			fail("Can't load the template group.");
		}
		return oclSettings;

	}
}
