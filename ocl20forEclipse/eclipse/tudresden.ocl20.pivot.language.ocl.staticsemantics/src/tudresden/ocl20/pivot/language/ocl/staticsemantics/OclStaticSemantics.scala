package tudresden.ocl20.pivot.language.ocl.staticsemantics

import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import tudresden.attributegrammar.integration.kiama._
import tudresden.ocl20.pivot.language.ocl.semantics._
import tudresden.ocl20.pivot.pivotmodel._
import tudresden.ocl20.pivot.pivotmodel.semantics._
import tudresden.ocl20.pivot.essentialocl._
import expressions._
import types._
import factory._
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
  protected[staticsemantics] val resource : OclResource
  
  /*
   * EssentialOclFactory to create essential OCL expressions
   */
  val factory = new EssentialOclFactory(oclLibrary, model)
  
  /*
   * Holds all definitions for a type; Should not be accessed directly! Use getAllDefs instead!
   */
  protected var allDefs : Tuple2[collection.mutable.MultiMap[Type, VariableDeclarationWithInitCS], collection.mutable.MultiMap[Type, Tuple2[OperationDefinitionInDefCS, OclExpressionCS]]] = _
  
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
    resource.addError(message, eObject)
    Failure(message)
  }
  
  /**
   * Adds a warning to the resource.
   */
  protected def addWarning(message : String, element : EObject) {
    val eObject = element match {
      case a : AttributableEObject => a.getEObject
      case eObject => eObject
    }
    resource.addWarning(message, eObject)
  }

  /**
   * If the given type is a CollectionType, set MuliplicityElement features
   */
  protected def determineMultiplicities(tipe : Type, multiplicityElement : MultiplicityElement with TypedElement) = {
    tipe match {
      case c : CollectionType => {
        multiplicityElement.setMultiple(true)
        if (c.getKind == CollectionKind.SET) {
          multiplicityElement.setUnique(true)
          multiplicityElement.setOrdered(false)
        } else if (c.getKind == CollectionKind.SEQUENCE) {
          multiplicityElement.setUnique(false)
          multiplicityElement.setOrdered(true)
        } else if (c.getKind == CollectionKind.ORDERED_SET) {
          multiplicityElement.setUnique(true)
          multiplicityElement.setOrdered(true)
        } else if (c.getKind == CollectionKind.BAG) {
          multiplicityElement.setUnique(false)
          multiplicityElement.setOrdered(false)
        }
        if (c.getElementType != null)
        	multiplicityElement.setType(c.getElementType)
      }
      case _ => multiplicityElement.setType(tipe)
    }
  }
  
  protected def determineMultiplicityElementType(multiplicityElement : MultiplicityElement with TypedElement) = {
    if (multiplicityElement.isMultiple) {
      if (multiplicityElement.isUnique) {
        if (multiplicityElement.isOrdered)
          oclLibrary.getOrderedSetType(multiplicityElement.getType)
        else
          oclLibrary.getSetType(multiplicityElement.getType) 
      }
      else {
        if (multiplicityElement.isOrdered)
          oclLibrary.getSequenceType(multiplicityElement.getType)
        else
          oclLibrary.getBagType(multiplicityElement.getType) 
      }
    }
    else
      multiplicityElement.getType
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
	          Full(initExp.getType)
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
    OclStaticSemanticsTransactions.startStaticSemanticsAnalysis(this, resource.getContents.get(0))
    val constraints = computeConstraints(root)
    // to avoid the conversion of Scala List to Java List multiple times
    val result : java.util.List[Constraint] = constraints.openOr {throw new OclStaticSemanticsException}
    OclStaticSemanticsTransactions.endStaticSemanticsAnalysis(model, resource, result)
    allDefs = null
    result
  }
  
  def getAllDefs = {
    if (allDefs == null) 
      allDefs = OclStaticSemanticsTransactions.getAllDefs(this, resource.getContents.get(0))
    allDefs
  }
}
