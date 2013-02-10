package org.dresdenocl.language.ocl.staticsemantics

import collection.JavaConversions._

import org.emftext.access._
import org.emftext.access.resource._
import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import org.dresdenocl.attributegrammar.integration.kiama._
import org.dresdenocl.language.ocl._
import org.dresdenocl.pivotmodel._
import org.dresdenocl.essentialocl._
import expressions._
import expressions.util._
import types._
import expressions.factory._
import org.dresdenocl.model._
import Box._

import org.dresdenocl.language.ocl.resource.ocl.mopp._


import AttributableEObject._


trait OclStaticSemantics extends OclLookUpFunctions
  with OclReferenceResolver
  with OclParseTreeToEssentialOcl
  with OclAttributes {

  /**
   * Used to create continuous numbers for implicit variable names in order to
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
  protected[staticsemantics] lazy val model: IModel = {
    var m = resource.getModel(resource.getContents)
    if (m == null) {
      m = org.dresdenocl.modelbus.ModelBusPlugin.getModelRegistry.getActiveModel
      if (m == null) {
        val exception = new OclStaticSemanticsException("Cannot check OCL static semantics. Please load a model first.")
        yieldFailure(exception, resource.getContents.get(0))
        throw exception
      }
    }
    m
  }

  /*
   * Used to look up primitive types.
   */
  protected[staticsemantics] val oclLibrary: OclLibrary = {
    val oclLibraryProvider = EssentialOclPlugin.getOclLibraryProvider
    oclLibraryProvider.getOclLibrary
  }

  /*
   * Do not use resource directly. Instead refer to yieldFailure and addWarning.
   */
  protected[staticsemantics] val resource: IOclResource

  /*
   * EssentialOclFactory to create essential OCL expressions
   */
  val factory = new EssentialOclFactory(oclLibrary, model)

  /*
   * Holds all definitions for a type; Should not be accessed directly! Use getAllDefs instead!
   */
  protected var allDefs: Tuple2[collection.mutable.MultiMap[Type, VariableDeclarationWithInitCS], collection.mutable.MultiMap[Type, Tuple2[OperationDefinitionInDefCS, OclExpressionCS]]] = _

  /*
   * Holds all defined operations and their corresponding type
   */
  protected val definedOperationsType: java.util.Map[Operation, Type] = new java.util.IdentityHashMap[Operation, Type]

  /*
   * Holds all defined properties and their corresponding type
   */
  protected val definedPropertysType: java.util.Map[Property, Type] = new java.util.IdentityHashMap[Property, Type]

  /**
   * For access to general methods of the resource interface
   */
  protected lazy val iResource: IResource = EMFTextAccessProxy.get(resource, classOf[IResource]).asInstanceOf[IResource]

  protected var lastRoot: EObject = null
  
  //maps EssentialOcl to their corresponding CS
  //@author Lars Schuetze
  protected var allMappings : java.util.Map[EObject, EObject] = new java.util.IdentityHashMap[EObject, EObject]

  /*
   * For cached attributes.
   */
  import org.kiama.attribution.Attribution._

  /**
   * Adds an error to the resource and returns a Failure with the given message.
   */
  protected def yieldFailure(message: String, element: EObject) = {
    val eObject = element match {
      case a: AttributableEObject => a.eObject
      case eObject => eObject
    }
    iResource.addError(message, eObject)
    Failure(message)
  }

  protected def yieldFailure(exception: Exception, element: EObject) = {
    val eObject = element match {
      case a: AttributableEObject => a.eObject
      case eObject => eObject
    }
    iResource.addError(exception.getMessage, eObject)
    Failure(exception.getMessage, Full(exception), Empty)
  }

  /**
   * Adds a warning to the resource.
   */
  protected def addWarning(message: String, element: EObject) {
    val eObject = element match {
      case a: AttributableEObject => a.eObject
      case eObject => eObject
    }
    iResource.addWarning(message, eObject)
  }

  protected def printOclExpression(expression: EObject) = {
    // FIXME: this is a hack, so that OclExpressions can be printed in pure OCL
    resource match {
      case or: OclResource => {
        val baos = new java.io.ByteArrayOutputStream
        val printer = or.getMetaInformation.createPrinter(baos, or)
        printer.print(expression)
        baos.toString
      }
      case _ => "" // do nothing
    }
  }

  protected def determineTypeOf(oclExpression: OclExpression) = {
    oclExpression.getType match {
      case c: CollectionType => c.getElementType
      case t: Type => t
    }
  }

  protected def checkVariableDeclarationType(vd: VariableDeclarationWithInitCS): Box[Type] = {
    if (vd.getTypeName != null) {
      (oclType(vd.getTypeName)).flatMap { tipe =>
        (computeOclExpression(vd.getInitialization)).flatMap { initExp =>
          if (!initExp.getType.conformsTo(tipe))
            yieldFailure("Expected type " + tipe.getName + ", but found " +
              initExp.getType.getName, vd)
          else
            Full(tipe)
        }
      }
    } else
      (computeOclExpression(vd.getInitialization)).flatMap { initExp =>
        Full(initExp.getType)
      }
  }

  @throws(classOf[OclStaticSemanticsException])
  def cs2EssentialOcl(root: EObject): java.util.List[Constraint] = {
    if (root ne lastRoot) {      
      model.getRootNamespace
      OclStaticSemanticsTransactions.startStaticSemanticsAnalysis(this, iResource.getContents.get(0))
      val constraints = computeConstraints(root)
      // to avoid the conversion of Scala List to Java List multiple times
      val result: java.util.List[Constraint] = constraints.openOr { throw new OclStaticSemanticsException }
      OclStaticSemanticsTransactions.endStaticSemanticsAnalysis(model, resource, result, getAllEssentialOcl2CsMappings)
      //definedOperationsType.clear
      allDefs = null
      lastRoot = root
      result
    } else
      computeConstraints(root).openOr { throw new OclStaticSemanticsException }
  }

  def getAllDefs = {
    if (allDefs == null)
      allDefs = OclStaticSemanticsTransactions.getAllDefs(this, iResource.getContents.get(0))
    allDefs
  }
  
  def getAllEssentialOcl2CsMappings : java.util.Map[EObject, Integer] = {
  	
  	val result = new java.util.IdentityHashMap[EObject, Integer]
  	// eocl = essential ocl
  	// ecs = concrete syntax
  	for ( (eocl, ecs) <- allMappings ) {
      var e : EObject = ecs match {
        case aeo : AttributableEObject => {
          aeo.eObject
        }
        case eobj : EObject => {
          eobj
        }
      }
      var line : Integer = Integer.valueOf(-1)
      while( line == -1 && e != null ) {
        line = Integer.valueOf(iResource.getLocationMap.getLine( e ))
        e = e.eContainer
      }
      result.put( eocl, line )
  	}
  	// return result
  	result
  }
}

