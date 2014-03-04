package org.dresdenocl.ocl2parser.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Test;
import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.language.ocl.ClassifierContextDeclarationCS;
import org.dresdenocl.language.ocl.ContextDeclarationCS;
import org.dresdenocl.language.ocl.InvariantExpCS;
import org.dresdenocl.language.ocl.InvariantOrDefinitionCS;
import org.dresdenocl.language.ocl.IterateExpCS;
import org.dresdenocl.language.ocl.NavigationCallExp;
import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.PackageDeclarationCS;
import org.dresdenocl.language.ocl.RelationalOperationCallExpCS;
import org.dresdenocl.language.ocl.resource.ocl.Ocl22Parser;
import org.dresdenocl.language.ocl.resource.ocl.Variables;
import org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.ocl2parser.test.constrainttypes.AllConstraintTypeTests;
import org.dresdenocl.ocl2parser.test.exception.MetaModelNotFoundException;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

public class TestAttributeAccess {

	/**
	 * <p>
	 * A test case to retrieve attributes of the attribution process from the
	 * parser.
	 * </p>
	 */
	@Test
	public void testgetVariables() throws Exception {

		OclResource resource = parseOclFile();

		assertNotNull(resource.getContents());
		assertFalse(resource.getContents().isEmpty());

		EObject root = resource.getContents().get(0);
		assertTrue(root instanceof PackageDeclarationCS);

		List<ContextDeclarationCS> contextDeclarations = ((PackageDeclarationCS) root)
				.getContextDeclarations();
		assertTrue(contextDeclarations.size() == 1);

		ContextDeclarationCS contextDeclaration = contextDeclarations.get(0);
		assertTrue(contextDeclaration instanceof ClassifierContextDeclarationCS);

		List<InvariantOrDefinitionCS> invariantsOrDefinitions = ((ClassifierContextDeclarationCS) contextDeclaration)
				.getInvariantsAndDefinitions();
		assertTrue(invariantsOrDefinitions.size() == 1);
		assertTrue(invariantsOrDefinitions.get(0) instanceof InvariantExpCS);

		InvariantExpCS invariantExp = (InvariantExpCS) invariantsOrDefinitions
				.get(0);
		OclExpressionCS oclExpression = invariantExp.getOclExpression();

		Variables variables1 = Ocl22Parser.INSTANCE.getVariables(oclExpression);
		assertTrue(variables1.getImplicitVariables().size() == 1);
		assertTrue(variables1.getImplicitVariables().get(0).getName()
				.equals("self"));
		assertTrue(variables1.getExplicitVariables().isEmpty());

		assertTrue(oclExpression instanceof RelationalOperationCallExpCS);
		OclExpressionCS oclExpression2 = ((RelationalOperationCallExpCS) oclExpression)
				.getSource();
		assertTrue(oclExpression2 instanceof NavigationCallExp);
		NavigationCallExp navigationCallExp = (NavigationCallExp) oclExpression2;

		assertTrue(navigationCallExp.getFeatureCalls().size() == 2);
		assertTrue(navigationCallExp.getFeatureCalls().get(1) instanceof IterateExpCS);
		IterateExpCS iterateExp = (IterateExpCS) navigationCallExp
				.getFeatureCalls().get(1);
		Variables variables2 = Ocl22Parser.INSTANCE.getVariables(iterateExp);
		assertTrue(variables2.getExplicitVariables().isEmpty());

		OclExpressionCS oclExpression3 = iterateExp.getBodyExpression();
		Variables variables3 = Ocl22Parser.INSTANCE.getVariables(oclExpression3);
		assertTrue(variables3.getImplicitVariables().size() == 1);
		assertTrue(variables3.getImplicitVariables().get(0).getName() == "self");
		assertTrue(variables3.getExplicitVariables().size() == 2);
		assertTrue(variables3.getExplicitVariables().get(0).getName()
				.equals("anInstance"));
		assertTrue(variables3.getExplicitVariables().get(1).getName().equals("sum"));
	}

	/**
	 * <p>
	 * A test case to retrieve attributes of the attribution process from the
	 * parser.
	 * </p>
	 */
	@Test
	public void testgetOclType() throws Exception {

		OclResource resource = parseOclFile();

		Type booleanType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclBoolean();
		Type integerType = EssentialOclPlugin.getOclLibraryProvider()
				.getOclLibrary().getOclInteger();

		assertNotNull(resource.getContents());
		assertFalse(resource.getContents().isEmpty());

		EObject root = resource.getContents().get(0);
		assertTrue(root instanceof PackageDeclarationCS);

		List<ContextDeclarationCS> contextDeclarations = ((PackageDeclarationCS) root)
				.getContextDeclarations();
		assertTrue(contextDeclarations.size() == 1);

		ContextDeclarationCS contextDeclaration = contextDeclarations.get(0);
		assertTrue(contextDeclaration instanceof ClassifierContextDeclarationCS);

		List<InvariantOrDefinitionCS> invariantsOrDefinitions = ((ClassifierContextDeclarationCS) contextDeclaration)
				.getInvariantsAndDefinitions();
		assertTrue(invariantsOrDefinitions.size() == 1);
		assertTrue(invariantsOrDefinitions.get(0) instanceof InvariantExpCS);

		InvariantExpCS invariantExp = (InvariantExpCS) invariantsOrDefinitions
				.get(0);
		OclExpressionCS oclExpression = invariantExp.getOclExpression();
		Type type1 = Ocl22Parser.INSTANCE.getOclType(oclExpression);
		assertTrue(type1.equals(booleanType));

		assertTrue(oclExpression instanceof RelationalOperationCallExpCS);
		OclExpressionCS oclExpression2 = ((RelationalOperationCallExpCS) oclExpression)
				.getSource();
		assertTrue(oclExpression2 instanceof NavigationCallExp);
		NavigationCallExp navigationCallExp = (NavigationCallExp) oclExpression2;
		assertTrue(Ocl22Parser.INSTANCE.getOclType(navigationCallExp).equals(
				integerType));

	}

	private OclResource parseOclFile() throws MetaModelNotFoundException,
			ModelAccessException, FileNotFoundException, IOException {
		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "expressions/iterators/iteratePositive01.ocl";
		modelFileName = "testmodel.uml";

		/* Try to get the TestPerformer. */
		testPerformer = TestPerformer.getInstance(
				AllConstraintTypeTests.META_MODEL_ID,
				AllConstraintTypeTests.MODEL_BUNDLE,
				AllConstraintTypeTests.MODEL_DIRECTORY);
		testPerformer.setModel(modelFileName);
		
//		/* Try to parse the constraint file. */
//		String fileDirectory;
//		fileDirectory = Activator.getDefault().getBundle().getLocation();
//
//		/* Remove the 'reference:file:' from the beginning. */
//		fileDirectory = fileDirectory.substring(15);

		File oclFile;
		oclFile = AbstractDresdenOclTest.getFile(TestPerformer.OCL_FILE_DIRECTORY + oclFileName, Activator.PLUGIN_ID);
//		oclFile = new File(fileDirectory + TestPerformer.OCL_FILE_DIRECTORY
//				+ oclFileName);

		/* Check if the file exists at all. */
		if (!oclFile.exists()) {
			throw new FileNotFoundException(
					"The OCL test file does not exists. File name: "
							+ TestPerformer.OCL_FILE_DIRECTORY + oclFileName + ".");
		}
		// no else.

//		URI uri = URI.createPlatformPluginURI(oclFile.getAbsolutePath(), true);
		URI uri = URI.createFileURI(oclFile.getAbsolutePath());
		ResourceSet rs = new ResourceSetImpl();
		OclResource resource = new OclResource(uri);
		rs.getResources().add(resource);
		resource.setModel(testPerformer.getCurrentModel());
		resource.load(Collections.EMPTY_MAP);
		return resource;
	}

}
