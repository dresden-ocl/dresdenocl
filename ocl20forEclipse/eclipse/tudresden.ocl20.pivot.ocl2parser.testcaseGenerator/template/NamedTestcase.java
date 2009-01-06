package $packagename;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelFactory;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.TypeNotFoundException;
import ${defaultpackage}.compare.CompareException;
import ${defaultpackage}.compare.ExpressionComparator;
import ${defaultpackage}.compare.BuildModelException;
import ${defaultpackage}.compare.stringTree.StringInnerNode;
import ${defaultpackage}.compare.stringTree.StringNode;
import tudresden.ocl20.pivot.ocl2parser.parser.OCL2Parser;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.BuildingASTException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.LexException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.ParsingException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.SemanticException;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

public class $testname {
	private IModel referencedModel = null;
	private IMetamodel metamodel = null;
	private IModelFactory modelFactory = null;
	private PivotModelFactory pivotFactory = null;
	private OclLibrary oclLibrary = null;
	
	@Before
	public void setUp() {
		ModelBusPlugin plugin = ModelBusPlugin.getDefault();
		IMetamodelRegistry metaModelRegistry = ModelBusPlugin.getMetamodelRegistry();
		metamodel = metaModelRegistry.getMetamodel("$metamodel");
		File modelFile = new File("$modelfile");
		
		try {
			referencedModel = metamodel.getModelProvider().getModel(modelFile);
		} catch(ModelAccessException modelException) {
			System.err.println("An exception occurred while loading the model. ");
			modelException.printStackTrace();
		}
		
		modelFactory = referencedModel.getFactory();
		
		pivotFactory = PivotModelFactory.INSTANCE;
		
		oclLibrary = referencedModel.getOclLibraryProvider().getOclLibrary();
	}
	
	#foreach($testcase in $testcaseelementsmap)
		#set( $testcasename = $testcase.get("testcasename"))
		#set( $oclexpression = $testcase.get("oclexpression"))
		#set( $code = $testcase.get("code"))
		#set( $variabledeclaration = $testcase.get("variabledeclaration"))
		#set( $errorelement = $testcase.get("errorelement"))
		
	/**
	 * This test case stands for the following ocl-expression:
	 * 
	 * $oclexpression
	 */
	@Test
	public void $testcasename() {
		#if (!$errorelement)
		$variabledeclaration
		
		#end
		#*
		#foreach( $vardecl in $vardecls)
		$vardecl.type $vardecl.name = null;
		#end
		*#
		
		#if(!$errorelement)
		try {
			$code.toString()
			#*
			#foreach($modelconstruct in $modelconstructs)
				$modelconstruct ;
			#end
			*#
		}catch(Exception ex) {
			ex.printStackTrace();
			fail();
		}
		#end
		
		
		String oclString = $oclexpression ;

		StringReader oclReader = new StringReader(oclString);

		OCL2Parser parser = new OCL2Parser(referencedModel, oclReader);
		try {
			parser.parse();
		} catch (ParsingException e) {
			e.printStackTrace();
			#if($errorelement)
				assertTrue(true);
			#else
				fail();
			#end
			return;
		} catch (LexException e) {
			e.printStackTrace();
			#if($errorelement)
				assertTrue(true);
			#else
				fail();
			#end
			return;
		} catch (IOException e) {
			e.printStackTrace();
			#if($errorelement)
				assertTrue(true);
			#else
				fail();
			#end
			return;
		} catch (BuildingASTException e) {
			e.printStackTrace();
			#if($errorelement)
				assertTrue(true);
			#else
				fail();
			#end
			return;
		} catch (SemanticException e) {
			e.printStackTrace();
			#if($errorelement)
				assertTrue(true);
			#else
				fail();
			#end
			return;
		}
		
		#if($errorelement)
			fail();
		#end
		
		#if(!$errorelement)
		/*
		 * Here we compare the namespace that was altered while parsing with the constructed namespace of the test model.
		 */
		ExpressionComparator comparator = new ExpressionComparator();
		StringNode node = new StringInnerNode();
		try {
			
			comparator.compare(model, referencedModel.getRootNamespace(), node);
			System.out.println(node);
		} catch (CompareException e) {
			System.out.println(node);
			e.printStackTrace();
			
			fail();
			return;
		} catch (ModelAccessException e) {
			e.printStackTrace();
			fail();
			return;
		} 
		
		assertTrue(true);
		#end
	}
	#end
	
	
}
