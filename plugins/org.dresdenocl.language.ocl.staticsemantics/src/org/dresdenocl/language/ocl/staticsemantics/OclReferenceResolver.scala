package org.dresdenocl.language.ocl.staticsemantics

import collection.JavaConversions._

import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import org.dresdenocl.attributegrammar.integration.kiama._
import org.dresdenocl.language.ocl._
import org.dresdenocl.pivotmodel._
import org.dresdenocl.essentialocl._
import expressions._
import types._
import factory._
import org.dresdenocl.model._
import Box._

import org.dresdenocl.language.ocl.resource.ocl.mopp._

import org.kiama._
import attribution._
import Attribution._

import AttributableEObject._

trait OclReferenceResolver { selfType: OclStaticSemantics =>

  protected val keywords = List("and", "body", "context", "def", "derive", "else", "endif", "endpackage", "if", "implies",
    "in", "init", "inv", "let", "not", "or", "package", "post", "pre", "static", "then", "xor", "exists",
    "forAll", "isUnique", "any", "one", "collect", "select", "reject", "collectNested", "sortedBy")

  protected val _resolveNamespace: Tuple2[String, Boolean] => Attributable ==> Box[List[Namespace]] = {
    paramAttr {
      case (identifier, fuzzy) => {
        case namespace: Namespace => {
          if (!fuzzy) {
            (!!(namespace.lookupNamespace(identifier)) ?~
              ("Cannot find nested namespace " + identifier +
                " in namespace " + namespace.getQualifiedName + ".")).
                flatMap(ns => Full(List(ns)))
          } else {
            val nestedNamespaces = namespace.getNestedNamespace.filter(nns => nns.getName.startsWith(identifier))
            Full(nestedNamespaces ++ (nestedNamespaces.flatMap(getAllNestedNamespaces(_))) toList)
          }
        }
        case other => {
          for (
            namespace <- namespace(other);
            resolvedNamespace <- _resolveNamespace(identifier, fuzzy)(namespace)
          ) yield resolvedNamespace
        }
      }
    }
  }

  protected def getAllNestedNamespaces(ns: Namespace): List[Namespace] = {
    ns.getNestedNamespace ++ (ns.getNestedNamespace.flatMap(getAllNestedNamespaces(_))) toList
  }

  protected val _resolveType: Tuple2[String, Boolean] => Attributable ==> Box[List[Type]] =
    paramAttr {
      case (identifier, fuzzy) => {
        case namespace: Namespace => {
          if (!fuzzy) {
            lookupType(identifier, namespace).flatMap { t =>
              Full(List(t))
            }
          } else
            Full(lookupTypeFuzzy(identifier, namespace))
        }
        case other =>
          for (
            namespace <- namespace(other);
            typeList <- _resolveType(identifier, fuzzy)(namespace)
          ) yield typeList
      }
    }

  protected val _resolveNamedElement: Tuple2[String, Boolean] => Attributable ==> Box[List[NamedElement]] = {
    paramAttr {
      case (identifier, fuzzy) => {
        case aeo: AttributableEObject => {
          (sourceExpression(aeo)).flatMap { sourceExpression =>
            (variables(aeo)).flatMap {
              case (implicitVariables, explicitVariables) =>
                val cleanedIdentifier = cleanIdentifier(identifier)
                if (!fuzzy) {
                  val sourceType = sourceExpression.getType
                  sourceExpression match {
                    // if the sourceExpression is an implicit variable (e.g., self or an iterator variable),
                    // the named element can be a variable, a property on the implicit variable or a type
                    case ve: VariableExp if !implicitVariables.filter(_ == ve.getReferredVariable).isEmpty => {
                      lookupVariable(identifier, aeo) match {
                        case Full(variable) => Full(List(variable))
                        case Empty | Failure(_, _, _) => {
                          val allProperties = implicitVariables.map { iv =>
                            lookupPropertyOnType(iv.getType, cleanedIdentifier, false)
                          }.filter(p => p.isDefined)
                          if (!allProperties.isEmpty)
                            Full(List(allProperties.first.open_!))
                          else {
                            (namespace(aeo)).flatMap { namespace =>
                              lookupType(cleanedIdentifier, namespace).flatMap { t =>
                                Full(List(t))
                              }
                            }
                          }
                        }
                      }
                    }
                    // in the other cases, the source is part of a navigation only(call) properties have
                    // to be considered
                    case other => {
                      lookupProperty(sourceExpression, aeo, cleanedIdentifier, false).flatMap { p =>
                        Full(List(p))
                      }
                    }
                  }
                } // fuzzy == true
                else {
                  sourceExpression match {
                    // SourceExpression is an implicit lookup(variable) of variables, properties on implicit variable or types 
                    case ve: VariableExp if !implicitVariables.filter(_ == ve.getReferredVariable).isEmpty => {
                      (namespace(aeo)).flatMap { namespace =>
                        Full(lookupVariableFuzzy(cleanedIdentifier, aeo) :::
                          implicitVariables.flatMap { iv =>
                            lookupPropertyOnTypeFuzzy(factory.createVariableExp(iv), aeo, cleanedIdentifier, false)
                          }.flatten(p => p) :::
                          lookupTypeFuzzy(cleanedIdentifier, namespace))
                      }
                    }
                    // SourceExpression not equal an implicit lookup(variable) in a navigation call
                    case other => {
                      lookupPropertyOnTypeFuzzy(other, aeo, cleanedIdentifier, false)
                    }
                  }
                }
            }
          }
        }
      }
    }
  }

  protected val _resolvePathName: Tuple2[List[String], Boolean] => Attributable ==> Box[List[NamedElement]] = {
    paramAttr {
      case (identifier, fuzzy) => {
        case s: PathNameSimpleCS => {
          if (!fuzzy) {
            for {
              namespace <- namespace(s)
              resolvedPath <- lookupPathName(List(cleanIdentifier(identifier.head)), namespace,s)
            } yield resolvedPath
          } else {
            Empty
          }
        }
        case n: NamedElementCS => {
          if (!fuzzy) {
            val pathName = n.eContainer.asInstanceOf[PathNamePathCS]
            var names : List[String] = List()
            for (uSN <- pathName.getPathName) {
              names = names :+ cleanIdentifier(uSN.eAdapters.get(0).asInstanceOf[OclLayoutInformationAdapter].getLayoutInformations.get(0).getVisibleTokenText)
            }
            namespace(pathName) match {
                case Full(namespace) => {
                 lookupPathName(names,namespace,pathName) match {
                   case Full(pathNames) => {
                     if (pathNames.size == names.size) {
                       var temp = pathNames.get(0)
                       for (uSN <- pathName.getPathName) {
                         if (n!=uSN) {
                           if (uSN.isInstanceOf[NamedElementCS]) 
                             uSN.asInstanceOf[NamedElementCS].setNamedElement(pathNames.get(pathName.getPathName.indexOf(uSN)))
                         } else {
                           temp = pathNames.get(pathName.getPathName.indexOf(uSN))
                         }
                       }
                       Full(List(temp))
                     } else {
                       Empty
                     }
                   }
                   case _ => {
                     Empty
                   }
                 }
                }
                case Empty => { 
                  Empty 
                }
              }
          } else {
            Empty
          }
        }
      }
    }
  }
  
  protected val _resolveOperation: Tuple4[String, Boolean, List[Type], Boolean] => Attributable ==> Box[List[Operation]] = {
    paramAttr {
      case (identifier, fuzzy, parameters, static) => {
        case soc: StaticOperationCallExpCS => {
          val cleanedIdentifier = cleanIdentifier(identifier)
          (oclType(soc.getTypeName)).flatMap { sourceType =>
            if (soc.getTypeName.getNamedElement.isInstanceOf[Variable] && static) {
              Failure("Variable "+soc.getTypeName.getNamedElement.getName + "is not allowed by a static operation")
            } else {
	            val argumentsEOcl = soc.getArguments.flatMap(arg => computeOclExpression(arg))
	            if (soc.getArguments.size != argumentsEOcl.size)
	              Failure("Parameters for operation " + identifier + " cannot be computed.")
	            else {
	              if (!fuzzy) {
	                lookupOperationOnType(sourceType, cleanedIdentifier, true, argumentsEOcl.map(_.getType) toList).flatMap { o =>
	                  Full(List(o))
	                }
	              } else {
	                Full(lookupOperationOnTypeFuzzy(sourceType, cleanedIdentifier, true))
	              }
	            }
            }
          }
        }
        case aeo: AttributableEObject => {
          (sourceExpression(aeo)).flatMap { sourceExpression =>
            (variables(aeo)).flatMap {
              case (implicitVariables, _) =>
                val cleanedIdentifier = cleanIdentifier(identifier)
                if (!fuzzy) {
                  sourceExpression match {
                    case ve: VariableExp if !implicitVariables.filter(_ == ve.getReferredVariable).isEmpty => {
                      val allOperations = implicitVariables.map { iv =>
                        lookupOperation(factory.createVariableExp(iv), aeo, cleanedIdentifier, static, parameters)
                      }.filter(_.isDefined)
                      if (allOperations.isEmpty)
                        yieldFailure("Unable to resolve operation " + identifier + " with parameters " + parameters.mkString(", "), aeo.eObject)
                      else {
                        val operation = allOperations.first.open_!
                        if (operation.getName == "oclIsNew" && operation.getInputParameter.isEmpty && operation.getType == oclLibrary.getOclBoolean &&
                          !(isInPostCondition(aeo)))
                          yieldFailure("The use of 'oclIsNew' outside of a postcondition is not allowed.", aeo.eObject)
                        else
                          Full(List(operation))
                      }
                    }
                    case _ =>
                      lookupOperation(sourceExpression, aeo, cleanedIdentifier, static, parameters).flatMap { operation =>
                        if (operation.getName == "oclIsNew" && operation.getInputParameter.isEmpty && operation.getType == oclLibrary.getOclBoolean &&
                          !(isInPostCondition(aeo)))
                          yieldFailure("The use of 'oclIsNew' outside of a postcondition is not allowed.", aeo.eObject)
                        else
                          Full(List(operation))
                      }
                  }
                } else
                  Full(lookupOperationOnTypeFuzzy(sourceExpression.getType, cleanedIdentifier, static))
            }
          }
        }
      }
    }
  }

  def cleanIdentifier(identifier: String) = {
    if (identifier.startsWith("_") && keywords.contains(identifier.substring(1)))
      identifier.substring(1)
    else
      identifier
  }

  def resolveNamespace(identifier: String, fuzzy: Boolean, container: EObject): java.util.List[Namespace] = {
    val result = _resolveNamespace(identifier, fuzzy)(container) match {
      case Full(namespaceList) => namespaceList
      case Failure(msg, _, _) => {
        iResource.addError(msg, container)
        List()
      }
      case Empty => List()
    }
    result
  }

  def resolveType(identifier: String, fuzzy: Boolean, container: EObject): java.util.List[Type] = {
    _resolveType(identifier, fuzzy)(container) match {
      case Full(typeList) => typeList
      case Failure(msg, _, _) => {
        iResource.addError(msg, container)
        List()
      }
      case Empty => List()
    }
  }

  def resolveNamedElement(identifier: String, fuzzy: Boolean, container: EObject): java.util.List[NamedElement] = {
    _resolveNamedElement(identifier, fuzzy)(container) match {
      case Full(namedElementList) => namedElementList
      case Failure(_, _, _) | Empty => List()
    }
  }
  
  def resolvePathName(identifier: String, fuzzy: Boolean, container: EObject): java.util.List[NamedElement] = {
    _resolvePathName(List(identifier), fuzzy)(container) match {
      case Full(namedElementList) => namedElementList
      case Failure(_, _, _) | Empty => List()
    }
  }

  def resolveProperty(identifier: String, fuzzy: Boolean, container: EObject): java.util.List[Property] = {
    _resolveNamedElement(identifier, fuzzy)(container) match {
      case Full(namedElementList) => namedElementList.flatMap {
        _ match {
          case p: Property => Some(p)
          case _ => None
        }
      }
      case Failure(msg, _, _) => iResource.addError(msg, container); List()
      case Empty => List()
    }
  }

  def resolvePropertyDefinition(identifier: String, fuzzy: Boolean, container: EObject): java.util.List[Property] = {
    container match {
      case a: AttributeContextDeclarationCS => {
        (oclType(a.getTypeName)).flatMap { tn =>
          if (!fuzzy) {
            (lookupPropertyOnType(tn, identifier, false) or
              lookupPropertyOnType(tn, identifier, true)).flatMap(p => Full(List(p)))
          } else {
            Full(lookupPropertyOnTypeFuzzy(tn, identifier, false))
          }
        } match {
          case Full(propertyList) => propertyList
          case Failure(msg, _, _) => iResource.addError(msg, container); List()
          case Empty => List()
        }
      }
    }
  }

  def resolveOperation(identifier: String, fuzzy: Boolean, container: EObject, reference: EReference,
    parameters: java.util.List[OclExpressionCS], static: Boolean): java.util.List[Operation] = {
    // TODO: set ownedParameters
    val parametersEOcl =
      for (
        parameter <- parameters;
        parameterEOcl <- computeOclExpression(parameter)
      ) yield {
        parameterEOcl
      }

    _resolveOperation(identifier, fuzzy, parametersEOcl.map(_.getType) toList, static)(container) match {
      case Full(operationList) => operationList
      case Failure(msg, _, _) => iResource.addError(msg, container); List()
      case Empty => List()
    }
  }

  def resolveOperationDefinition(identifier: String, fuzzy: Boolean, container: EObject,
    reference: EReference, parameters: java.util.List[ParameterCS],
    returnType: TypeCS): java.util.List[Operation] = {
    container match {
      case o: OperationDefinitionInContextCS => {
        val parametersEOcl = parameters.map(p => p.getParameter)
        parametersEOcl.find(_.eIsProxy) match {
          case Some(couldNotResolve) => List()
          case None => {
            (oclType(o.getTypeName)).flatMap { tipe =>
              if (!fuzzy) {
                val par = parametersEOcl.map(_.getType).toList
                (lookupOperationOnType(tipe, identifier, false, par) or
                  lookupOperationOnType(tipe, identifier, true, par))
                  .flatMap(o => Full(List(o)))
              } else
                Full(lookupOperationOnTypeFuzzy(tipe, identifier, false) ::: lookupOperationOnTypeFuzzy(tipe, identifier, true))
            } match {
              case Full(operationList) => {
                if (returnType != null) {
                  (oclType(returnType)).flatMap { returnType =>
                    if (!fuzzy)
                      if (!operationList.first.getType.equals(returnType))
                        yieldFailure("Operation return type " + operationList.first.getType.getName +
                          " is not equal to given type " + returnType.getName + ".", o)
                      else
                        Full(operationList)
                    else
                      Full(operationList.filter(o => o.getType.conformsTo(returnType)))
                  }.open_!
                } else
                  operationList
              }
              case Failure(msg, _, _) => iResource.addError(msg, container); List()
              case Empty => List()
            }
          }
        }
      }
      case o: OperationDefinitionInDefCS => {
        val parametersEOcl = parameters.map(p => p.getParameter)
        parametersEOcl.find(_.eIsProxy) match {
          case Some(couldNotResolve) => List()
          case None => {
            (context(o)).flatMap { context =>
              val rt =
                if (returnType == null) {
                  o.parent match {
                    case d: DefinitionExpOperationCS => {
                      (computeOclExpression(d.getOclExpression)) match {
                        case Full(oclExpressionEOcl) => Full(oclExpressionEOcl.getType)
                        case _ => Failure("Cannot compute oclExpression for operation definition.")
                      }
                    }
                  }
                } else
                  oclType(returnType)
              rt.flatMap { rt =>
                if (identifier != null && identifier != "" && context.isInstanceOf[Type]) {
                  val contextType = context.asInstanceOf[Type]
                  // this is some crude hack to ensure the descendency from OclAny for the type
                  val operations = contextType.allOperations.union(oclLibrary.getOclAny.allOperations)
                  operations.find(o => o.getName == identifier
                    && o.hasMatchingSignature(parametersEOcl.map(_.getType))
                    && o.getType.conformsTo(rt)) match {
                    case Some(_) => Failure("Operation " + identifier + " is already defined on " +
                      contextType.getName)
                    case None => {
                      val operation = PivotModelFactory.eINSTANCE.createOperation
                      operation.setName(identifier)
                      operation.setType(rt)
                      parametersEOcl.foreach(p => operation.addParameter(p))
                      val retParameter = PivotModelFactory.eINSTANCE.createParameter
                      retParameter.setKind(ParameterDirectionKind.RETURN)
                      retParameter.setType(rt)
                      operation.addParameter(retParameter)
                      Full(List(operation))
                    }
                  }
                } else
                  Empty
              }
            }
          } match {
            case Full(opList) => opList
            case Failure(msg, _, _) => iResource.addError(msg, container); List()
            case Empty => List()
          }
        }
      }
    }
  }

  def resolveParameterDefinition(identifier: String, fuzzy: Boolean, container: EObject,
    reference: EReference, parameterType: TypeCS): java.util.List[Parameter] = {
    (oclType(parameterType)).flatMap { parameterType =>
      val parameter = PivotModelFactory.eINSTANCE.createParameter
      parameter.setType(parameterType)
      parameter.setName(identifier)
      parameter.setKind(ParameterDirectionKind.IN)
      Full(parameter)
    } match {
      case Full(parameter) => List(parameter)
      case Failure(msg, _, _) => iResource.addError(msg, parameterType); List()
      case Empty => List()
    }
  }

}

