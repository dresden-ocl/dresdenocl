package tudresden.ocl20.pivot.language.ocl.staticsemantics

import org.emftext.access._
import org.emftext.access.resource._
import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import tudresden.attributegrammar.integration.kiama._
import tudresden.ocl20.pivot.language.ocl.semantics._
import tudresden.ocl20.pivot.pivotmodel._
import tudresden.ocl20.pivot.pivotmodel.semantics._
import tudresden.ocl20.pivot.essentialocl._
import expressions._
import expressions.util._
import types._
import expressions.factory._
import tudresden.ocl20.pivot.model._
import Box._

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._

import tudresden.attributegrammar.integration.kiama.util.CollectionConverterS2J._
import tudresden.attributegrammar.integration.kiama.util.CollectionConverterJ2S._



trait OclStaticSemantics extends ocl.semantics.OclAttributeMaker
												 with pivotmodel.semantics.PivotmodelAttributeMaker 
												 with OclLookUpFunctions 
												 with OclReferenceResolver 
												 with OclParseTreeToEssentialOcl 
												 with OclAttributes {
  
	/**
   * Used to create contiuous numbers for implicit variable names in order to
   * let those numbers be unique
   */
	object ImplicitVariableNumberGenerator {
	  var number = Stream.from(0)
	  
	  def getNumber = {
	    number.head
	  }
	}
 
  /*
   * Used for type lookup and adding defs.
   */
  protected[staticsemantics] val model : IModel = {
    var m = resource.getModel
    if (m == null) {
      m = modelbus.ModelBusPlugin.getModelRegistry.getActiveModel
      if (m == null)
        throw new OclStaticSemanticsException("No active model")
    }
    m
  }
  
  /*
   * Used to look up primitive types.
   */
  protected[staticsemantics] val oclLibrary : OclLibrary = {
    val oclLibraryProvider = EssentialOclPlugin.getOclLibraryProvider
		oclLibraryProvider.getOclLibrary
  }
  
  /*
   * Do not use resource directly. Instead refer to yieldFailure and addWarning.
   */
  protected[staticsemantics] val resource : IOclResource
  
  /*
   * EssentialOclFactory to create essential OCL expressions
   */
  val factory = new EssentialOclFactory(oclLibrary, model)
  
  /*
   * Holds all definitions for a type; Should not be accessed directly! Use getAllDefs instead!
   */
  protected var allDefs : Tuple2[collection.mutable.MultiMap[Type, VariableDeclarationWithInitCS], collection.mutable.MultiMap[Type, Tuple2[OperationDefinitionInDefCS, OclExpressionCS]]] = _
  
  /**
   * For access to general methods of the resource interface
   */
  protected val iResource : IResource = EMFTextAccessProxy.get(resource, classOf[IResource]).asInstanceOf[IResource]
  
  /*
   * For cached attributes.
   */
  import kiama.attribution.Attribution._
  
  /**
   * Adds an error to the resource and returns a Failure with the given message.
   */
  protected def yieldFailure(message : String, element : EObject) = {
    val eObject = element match {
      case a : AttributableEObject => a.getEObject
      case eObject => eObject
    }
    iResource.addError(message, eObject)
    Failure(message)
  }
  
  protected def yieldFailure(exception : Exception, element : EObject) = {
    val eObject = element match {
      case a : AttributableEObject => a.getEObject
      case eObject => eObject
    }
    iResource.addError(exception.getMessage, eObject)
    Failure(exception.getMessage, Full(exception), Empty)
  }
  
  /**
   * Adds a warning to the resource.
   */
  protected def addWarning(message : String, element : EObject) {
    val eObject = element match {
      case a : AttributableEObject => a.getEObject
      case eObject => eObject
    }
    iResource.addWarning(message, eObject)
  }
  
  protected def printOclExpression(expression : EObject) = {
    // FIXME: this is a hack, so that OclExpressions can be printed in pure OCL
    resource match {
      case or : OclResource => {
        val baos = new java.io.ByteArrayOutputStream
        val printer = or.getMetaInformation.createPrinter(baos, or)
        printer.print(expression)
        baos.toString
      }
      case _ => "" // do nothing
    }
  }
  
  protected def determineTypeOf(oclExpression : OclExpression) = {
		oclExpression.getType match {
			case c : CollectionType => c.getElementType
			case t : Type => t
		}
  }
  
  protected def checkVariableDeclarationType(vd : VariableDeclarationWithInitCS) : Box[Type] = {
    if (vd.getTypeName != null) {
      (vd.getTypeName->oclType).flatMap{tipe =>
        (vd.getInitialization->computeOclExpression).flatMap{initExp =>
	        if (!initExp.getType.conformsTo(tipe))
	        	yieldFailure("Expected type " + tipe.getName + ", but found " + 
	                        initExp.getType.getName, vd)
	        else 
	        	Full(tipe)
        }
      }
    }
    else
      (vd.getInitialization->computeOclExpression).flatMap{initExp => 
        Full(initExp.getType)
      }
  }
  
  @throws(classOf[OclStaticSemanticsException])
  def cs2EssentialOcl(root : EObject) : java.util.List[Constraint] = {
    resetMemo
    OclStaticSemanticsTransactions.startStaticSemanticsAnalysis(this, iResource.getContents.get(0))
    val constraints = computeConstraints(root)
    // to avoid the conversion of Scala List to Java List multiple times
    val result : java.util.List[Constraint] = constraints.openOr {throw new OclStaticSemanticsException}
    OclStaticSemanticsTransactions.endStaticSemanticsAnalysis(model, resource, result)
    allDefs = null
    result
  }
  
  def getAllDefs = {
    if (allDefs == null) 
      allDefs = OclStaticSemanticsTransactions.getAllDefs(this, iResource.getContents.get(0))
    allDefs
  }
}
