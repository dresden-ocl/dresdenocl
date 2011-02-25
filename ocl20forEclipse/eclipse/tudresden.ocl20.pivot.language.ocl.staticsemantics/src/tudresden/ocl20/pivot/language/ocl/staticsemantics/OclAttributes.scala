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

import org.kiama.attribution.Attribution._


trait OclAttributes { selfType : OclStaticSemantics =>

  protected val isMultipleNavigationCall = __isMultipleNavigationCall
  protected def __isMultipleNavigationCall : Attributable ==> Box[Boolean] = {
    childAttr{
      case child => {
        case _ => Full(false)
      }
    }
  }
  
  protected val namespace = __namespace
  protected def __namespace : Attributable ==> Box[Namespace] = {
    childAttr {
      case child => {
        case null => !!(model.getRootNamespace)
        case passOn => passOn->namespace
      }
    }
  }
  
  protected val lastNamespace = __lastNamespace
  protected def __lastNamespace : Attributable ==> Box[Namespace] = {
    attr {
      case unknown : AttributableEObject => yieldFailure("Cannot compute attribute 'lastNamespace' for " + unknown, unknown.getEObject)
    }
  }
  
  protected val self = __self
  protected def __self : Attributable ==> Box[Variable] = {
    childAttr {
      case child => {
        case null => Empty
        case passOn => passOn->self
      }
    }
  }
  
  protected val context = __context
  protected def __context : Attributable ==> Box[ConstrainableElement] = {
    childAttr {
      case child => {
        case null => Empty
        case passOn => passOn->context
      }
    }
  }
  
  /**
   * @return a Tuple2 with the first tuple element yielding the List of implicit variables,
   * 				 and the second tuple element a List of explicit variables.
   */
  def getVariables(attributable : EObject) : (java.util.List[Variable], java.util.List[Variable])= variables (attributable) match {
    case Full(variables) => (variables._1, variables._2)
    case Failure(msg, _, _) => throw new IllegalStateException(msg)
    case _ => throw new IllegalStateException("Cannot compute variables for " + attributable + ".")
  }
  protected val variables = __variables
  protected def __variables : Attributable ==> Box[Tuple2[List[Variable], List[Variable]]] = {
    childAttr {
      case child : AttributableEObject => {
        case null => Full(List(), List())        
        case passOn => passOn->variables
      }
    }
  }
  
  def getOclType(attributable : EObject) : Type = oclType (attributable) match {
    case Full(oclType) => oclType
    case Failure(msg, _, _) => throw new IllegalStateException(msg)
    case _ => throw new IllegalStateException("Cannot compute oclType for " + attributable + ".")
  }
  protected[staticsemantics] val oclType = __oclType
  protected def __oclType : Attributable ==> Box[Type] = {
    attr {
      case unknown : AttributableEObject => yieldFailure("Unkown OCL type: " + unknown, unknown)
    }
  }
  
  protected val sourceExpression = __sourceExpression
  protected def __sourceExpression : Attributable ==> Box[OclExpression] = {
    childAttr {
      case child : AttributableEObject => {
        case null => Empty
        case passOn => passOn->sourceExpression
      } 
    }
  }
  
  protected val isInPostCondition = __isInPostCondition
  protected def __isInPostCondition : Attributable ==> Boolean = {
    childAttr {
      case child => {
        case null => false
        case passOn => passOn->isInPostCondition
      }
    }
  }
  
  protected val isInStaticContext = __isInStaticContext
  protected def __isInStaticContext : Attributable ==> Boolean = {
    childAttr {
      case child => {
        case null => false
        case passOn => passOn->isInStaticContext
      }
    }
  }
  
  protected val propertyForVariableDeclarationWithInit = __propertyForVariableDeclarationWithInit
  protected def __propertyForVariableDeclarationWithInit : Attributable ==> Box[Property] = {
    attr {
  	  case v@VariableDeclarationWithInitCS(variableName, typeCS, initExp, _) => {
  	    (if (typeCS != null) {
	  	    typeCS->oclType
  	    } else {
          (initExp->computeOclExpression).flatMap { oclExpression =>
  	  	    Full(oclExpression.getType)
  	  	  }
        }).flatMap {tipe =>
		    	val property = PivotModelFactory.eINSTANCE.createProperty
		    	property.setName(variableName.getSimpleName)
		    	property.setType(tipe)
		    	Full(property)
        }
      }
  	  case _ => Empty
    }
  }
  
}
