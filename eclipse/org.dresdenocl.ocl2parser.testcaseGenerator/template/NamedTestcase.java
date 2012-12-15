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

import org.dresdenocl.essentialocl.expressions.ExpressionInOcl;
import org.dresdenocl.essentialocl.expressions.IntegerLiteralExp;
import org.dresdenocl.essentialocl.expressions.IteratorExp;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.expressions.PropertyCallExp;
import org.dresdenocl.essentialocl.expressions.StringLiteralExp;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.VariableExp;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.modelbus.IMetamodel;
import org.dresdenocl.modelbus.IMetamodelRegistry;
import org.dresdenocl.modelbus.IModel;
import org.dresdenocl.modelbus.IModelFactory;
import org.dresdenocl.modelbus.ModelAccessException;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.modelbus.TypeNotFoundException;
import ${defaultpackage}.compare.CompareException;
import ${defaultpackage}.compare.ExpressionComparator;
import ${defaultpackage}.compare.BuildModelException;
import ${defaultpackage}.compare.stringTree.StringInnerNode;
import ${defaultpackage}.compare.stringTree.StringNode;
import org.dresdenocl.ocl2parser.parser.OCL2Parser;
import org.dresdenocl.ocl2parser.parser.exceptions.BuildingASTException;
import org.dresdenocl.ocl2parser.parser.exceptions.LexException;
import org.dresdenocl.ocl2parser.parser.exceptions.ParsingException;
import org.dresdenocl.ocl2parser.parser.exceptions.SemanticException;
import org.dresdenocl.pivotmodel.ConstrainableElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.ConstraintKind;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.ParameterDirectionKind;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

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
	
	#*
	#foreach($testcase in $testcaseelementsmap)
		#set( $testcasename = $testcase.get("testcasename"))
		#set( $oclexpression = $testcase.get("oclexpression"))
		#set( $code = $testcase.get("code"))
		#set( $variabledeclaration = $testcase.get("variabledeclaration"))
		#set( $errorelement = $testcase.get("errorelement"))
	*#

	#foreach($testcase in $testcaseelementsmap)
		#set( $testcasename = $testcase.getTestcaseName())
		#set( $oclexpression = $testcase.getOclExpression())
		#set( $code = $testcase.getCode())
		#set( $variabledeclaration = $testcase.getVariableDeclaration())
		#set( $errorelement = $testcase.containsErrorElement())
		
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
