package org.dresdenocl.language.ocl.staticsemantics

import collection.JavaConversions._

import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import org.dresdenocl.pivotmodel._
import org.dresdenocl.language.ocl._
import org.dresdenocl.essentialocl._
import expressions._
import types._
import factory._
import org.dresdenocl.model._
import org.dresdenocl.attributegrammar.integration.kiama._
import Box._

import org.dresdenocl.language.ocl.resource.ocl.mopp._

import org.kiama._
import attribution._
import Attribution._

import AttributableEObject._

trait OclAttributes { selfType: OclStaticSemantics =>

  protected val isMultipleNavigationCall = __isMultipleNavigationCall
  protected def __isMultipleNavigationCall: Attributable ==> Box[Boolean] = {
    childAttr {
      case child => {
        case _ => Full(false)
      }
    }
  }

  protected val namespace = __namespace
  protected def __namespace: Attributable ==> Box[Namespace] = {
    childAttr {
      case child => {
        case null => !!(model.getRootNamespace)
        case passOn => namespace(passOn)
      }
    }
  }

  protected val lastNamespace = __lastNamespace
  protected def __lastNamespace: Attributable ==> Box[Namespace] = {
    attr {
      case unknown: AttributableEObject => yieldFailure("Cannot compute attribute 'lastNamespace' for " + unknown, unknown.eObject)
    }
  }

  protected val self = __self
  protected def __self: Attributable ==> Box[Variable] = {
    childAttr {
      case child => {
        case null => Empty
        case passOn => self(passOn)
      }
    }
  }

  protected val context = __context
  protected def __context: Attributable ==> Box[ConstrainableElement] = {
    childAttr {
      case child => {
        case null => Empty
        case passOn => context(passOn)
      }
    }
  }

  /**
   * @return a Tuple2 with the first tuple element yielding the List of implicit variables,
   * 				 and the second tuple element a List of explicit variables.
   */
  def getVariables(attributable: EObject): (java.util.List[Variable], java.util.List[Variable]) = variables(attributable) match {
    case Full(variables) => (variables._1, variables._2)
    case Failure(msg, _, _) => throw new IllegalStateException(msg)
    case _ => throw new IllegalStateException("Cannot compute variables for " + attributable + ".")
  }
  protected val variables = __variables
  protected def __variables: Attributable ==> Box[Tuple2[List[Variable], List[Variable]]] = {
    childAttr {
      case child: AttributableEObject => {
        case null => Full(List(), List())
        case passOn => variables(passOn)
      }
    }
  }

  def getOclType(attributable: EObject): Type = oclType(attributable) match {
    case Full(oclType) => oclType
    case Failure(msg, _, _) => throw new IllegalStateException(msg)
    case _ => throw new IllegalStateException("Cannot compute oclType for " + attributable + ".")
  }
  protected[staticsemantics] val oclType = __oclType
  protected def __oclType: Attributable ==> Box[Type] = {
    attr {
      case unknown: AttributableEObject => yieldFailure("Unkown OCL type: " + unknown, unknown.eObject)
    }
  }

  protected val sourceExpression = __sourceExpression
  protected def __sourceExpression: Attributable ==> Box[OclExpression] = {
    childAttr {
      case child: AttributableEObject => {
        case null => Empty
        case passOn => sourceExpression(passOn)
      }
    }
  }

  protected val isInPostCondition = __isInPostCondition
  protected def __isInPostCondition: Attributable ==> Boolean = {
    childAttr {
      case child => {
        case null => false
        case passOn => isInPostCondition(passOn)
      }
    }
  }

  protected val isInStaticContext = __isInStaticContext
  protected def __isInStaticContext: Attributable ==> Boolean = {
    childAttr {
      case child => {
        case null => false
        case passOn => isInStaticContext(passOn)
      }
    }
  }

  protected val propertyForVariableDeclarationWithInit = __propertyForVariableDeclarationWithInit
  protected def __propertyForVariableDeclarationWithInit: Attributable ==> Box[Property] = {
    attr {
      case v: VariableDeclarationWithInitCS => {
        (if (v.getTypeName != null) {
          oclType(v.getTypeName)
        } else {
          (computeOclExpression(v.getInitialization)).flatMap { oclExpression =>
            Full(oclExpression.getType)
          }
        }).flatMap { tipe =>
          val property = PivotModelFactory.eINSTANCE.createProperty
          property.setName(v.getVariableName.getSimpleName)
          property.setType(tipe)
          allMappings.put(property, v)
          Full(property)
        }
      }
      case _ => Empty
    }
  }

}


