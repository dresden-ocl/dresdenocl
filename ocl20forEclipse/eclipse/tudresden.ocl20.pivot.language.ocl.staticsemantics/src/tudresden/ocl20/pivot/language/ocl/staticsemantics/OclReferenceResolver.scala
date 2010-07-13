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

import kiama.attribution.Attribution._

trait OclReferenceResolver { selfType : OclStaticSemantics =>
   
  protected val _resolveNamespace : Tuple2[String, Boolean] => Attributable ==> Box[List[Namespace]] = {
    paramAttr {
      case (identifier, fuzzy) => {
        case namespace : Namespace => {
          if (!fuzzy) {
            (!!(namespace.lookupNamespace(identifier)) ?~ 
              ("Cannot find nested namespace " + identifier + 
                 " in namespace " + namespace.getQualifiedName + ".")).
              flatMap(ns => Full(List(ns)))
          } else {
            val nestedNamespaces = namespace.getNestedNamespace.filter(nns => nns.getName.startsWith(identifier))
            Full(nestedNamespaces:::(nestedNamespaces.flatMap(getAllNestedNamespaces(_))))
          }
        }
        case other => {
          for (namespace <- other->namespace;
               resolvedNamespace <- _resolveNamespace (identifier, fuzzy) (namespace))
          	yield resolvedNamespace
        }
      }
    }
  }
  
  protected def getAllNestedNamespaces(ns : Namespace) : List[Namespace] = {
    ns.getNestedNamespace:::(ns.getNestedNamespace.flatMap(getAllNestedNamespaces(_)))
  }
  
  protected val _resolveType : Tuple2[String, Boolean] => Attributable ==> Box[List[Type]] =
    paramAttr {
      case (identifier, fuzzy) => {
        case namespace : Namespace => {
          if (!fuzzy) {
            lookupType(identifier, namespace).flatMap{t =>
              Full(List(t))
            }
          } else
          	Full(lookupTypeFuzzy(identifier, namespace))
        }
        case other =>
          for (namespace <- other->namespace;
        			 typeList <- _resolveType(identifier, fuzzy) (namespace))
          	yield typeList
      }
    }
  
  protected val _resolveNamedElement : Tuple2[String, Boolean] => Attributable ==> Box[List[NamedElement]] = {
    paramAttr {
      case (identifier, fuzzy) => {
        case e@EnumLiteralOrStaticPropertyExpCS(typeName) => {
	        (typeName->oclType).flatMap{tipe =>
	          if (!fuzzy) {
		          tipe match {
		            case enum : Enumeration => (!!(enum.lookupLiteral(identifier))).flatMap{lit =>
		              Full(List(lit))
		            }
		            case t : Type => lookupPropertyOnType(t, identifier, true).flatMap{p =>
		              Full(List(p))
		            }
		          }
	          } else { // fuzzy == true
	            tipe match {
	              case enum : Enumeration => 
	                Full(enum.getOwnedLiteral.filter(l => l.getName.startsWith(identifier)))
	              case t : Type => Full(lookupPropertyOnTypeFuzzy(t, identifier, true))
	            }
	          }
	        }
	      }
        case aeo : AttributableEObject => {
          (aeo->sourceExpression).flatMap{sourceExpression =>
            (aeo->variables).flatMap{case (implicitVariables, explicitVariables) =>
          		if (!fuzzy) {
          			val sourceType = sourceExpression.getType
	              sourceExpression match {
	                // if the sourceExpression is an implicit variable (e.g., self or an iterator variable),
	                // the named element can be a variable, a property on the implicit variable or a type
	                case ve : VariableExp if !implicitVariables.filter(_ == ve.getReferredVariable).isEmpty => {
	                  lookupVariable(identifier, aeo) match {
		                  case Full(variable) => Full(List(variable))
		                  case Empty | Failure(_, _, _) => {
		                    lookupPropertyOnType(sourceType, identifier, false) match {
		                      case Full(property) => Full(List(property)) 
		                      case Empty | Failure(_, _, _) => {
		                        (aeo->namespace).flatMap{namespace =>
		                          lookupType(identifier, namespace).flatMap{t =>
	                             	Full(List(t))
		                          }
	                          }
		                      }
		                    }
		                  }
	                  }
	                }
	                // in the other cases, the source is part of a navigation call -> only properties have
	                // to be considered
	                case other => {
	                  (aeo->isMultipleNavigationCall).flatMap{isMultiple =>
		                  if ((isMultiple && sourceType.isInstanceOf[CollectionType]) || (!isMultiple && !sourceType.isInstanceOf[CollectionType]))
		                    lookupPropertyOnType(sourceType, identifier, false).flatMap{p =>
			                    Full(List(p))
			                  }
		                  else if (isMultiple) // implicit asSet
                      	lookupPropertyOnType(sourceExpression.withAsSet.getType, identifier, false).flatMap{p =>
                         	addWarning("implicit asSet() on " + aeo, aeo)
                      	  Full(List(p))
                       	}
		                  else // implicit collect
		                  	lookupPropertyOnType(sourceType.asInstanceOf[CollectionType].getElementType, identifier, false).flatMap{p =>
		                  		addWarning("implicit collect() on " + aeo, aeo)
		                  	  Full(List(p))
                      	}
	                  }
	                }
	              }
              }
		          // fuzzy == true
		          else {
		            sourceExpression match {
		              // SourceExpression is an implicit variable -> lookup of variables, properties on implicit variable or types 
		              case ve : VariableExp if !implicitVariables.filter(_ == ve.getReferredVariable).isEmpty => {
		                (aeo->namespace).flatMap{namespace =>
		                  // TODO: add fuzzy type lookup
		                	Full(lookupVariableFuzzy(identifier, aeo):::
                          lookupPropertyOnTypeFuzzy(ve.getType, identifier, false):::
                          lookupTypeFuzzy(identifier, namespace))
		                }
		              }
		              // SourceExpression not equal an implicit variable -> lookup in a navigation call
		              case other => {
		                Full(lookupPropertyOnTypeFuzzy(other.getType, identifier, false))
		              }
		            }
		          }
            }
          }
        }
      }
    }
  }
  
  protected val _resolveOperation : Tuple4[String, Boolean, List[Parameter], Boolean] => Attributable ==> Box[List[Operation]] = {
    case (identifier, fuzzy, parameters, static) => {
      case aeo : AttributableEObject => {
        (aeo->sourceExpression).flatMap{sourceExpression =>
          if (!fuzzy) {
            isMultipleNavigationCall(aeo) match {
              case Full(true) => {
                sourceExpression.getType match {
                  case c : CollectionType =>
                    lookupOperationOnType(c, identifier, parameters, static, aeo, Empty)
                  case notMultiple : Type =>
                     lookupOperationOnType(oclLibrary.getSetType(sourceExpression.getType), identifier, 
                                          parameters, static, aeo, Full("implicit as Set()"))
                }
              }
              case Full(false) => {
                sourceExpression.getType match {
                  case c : CollectionType => {
                    // TODO: lookupOperationOnType!!!
                    (!!(c.getElementType.lookupOperation(identifier, parameters.map(determineMultiplicityElementType(_)))) ?~
	              			("Cannot find operation " + identifier + " with parameters " + parameters.mkString(", ") + " on type " + 
	                    c.getElementType.getName)).flatMap{o =>
	                      if (o.isStatic == static) {
	                      	addWarning("implicit collect() on " + o.getName, aeo)
	                      	Full(List(o))
	                    	} else {
	                    	  Failure("Found operation " + identifier + ", but was " + (if (static) " static." else " not static."))
                      	}
                  		}
                  }
                  case notMultiple =>
                    lookupOperationOnType(notMultiple, identifier, parameters, static, aeo, Empty)
                }
              }
              case Failure(msg, _, _) => Failure(msg, Empty, Empty)
              case Empty => Failure("Cannot determine sourceExpression.")
            }
          }
          else
            Full(lookupOperationOnTypeFuzzy(sourceExpression.getType, identifier, static))
        }
      }
    }
  }
  
  def resolveNamespace(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Namespace] = {
    val result = _resolveNamespace(identifier, fuzzy) (container) match {
      case Full(namespaceList) => namespaceList
      case Failure(msg, _, _) => {
        resource.addError(msg, container)
        List()
      }
      case Empty => List()
    }
	  result
  }
  
  def resolveType(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Type] = {
    _resolveType(identifier, fuzzy) (container) match {
      case Full(typeList) => typeList
      case Failure(msg, _, _) => {
        resource.addError(msg, container)
        List()
      }
      case Empty => List()
    }
  }
  
  def resolveNamedElement(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[NamedElement] = {
    _resolveNamedElement(identifier, fuzzy) (container) match {
      case Full(namedElementList) => namedElementList
      case Failure(_, _, _) | Empty => List()
    }
  }
  
  def resolveProperty(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Property] = {
    _resolveNamedElement(identifier, fuzzy) (container) match {
      case Full(namedElementList) => namedElementList.flatMap {
      	_ match {
      	  case p : Property => Some(p)
      	  case _ => None
        }
      }
      case Failure(msg, _, _) => resource.addError(msg, container); List()
      case Empty => List()
    }
  }
  
  def resolvePropertyDefinition(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Property] = {
    container match {
      case AttributeContextDeclarationCS(typeName, _, _) => {
        (typeName->oclType).flatMap{tn =>
          if (!fuzzy) {
            lookupPropertyOnType(tn, identifier, false).flatMap(p => Full(List(p)))
          } else {
            Full(lookupPropertyOnTypeFuzzy(tn, identifier, false))
          }
        } match {
          case Full(propertyList) => propertyList
          case Failure(msg, _, _) => resource.addError(msg, container); List()
          case Empty => List()
        }
      }
    }
  }
  
  def resolveOperation(identifier : String, fuzzy : Boolean, container : EObject, reference : EReference, 
                       parameters : java.util.List[OclExpressionCS], static : Boolean) : java.util.List[Operation] = {
    // TODO: set ownedParameters
    val parametersEOcl = 
      for (parameter <- parameters;
      		 parameterEOcl <- parameter->computeOclExpression)
      yield {
      	val parameter = PivotModelFactory.eINSTANCE.createParameter
      	parameter.setName(parameterEOcl.getName)
      	determineMultiplicities(parameterEOcl.getType, parameter)
      	parameter.setKind(ParameterDirectionKind.IN)
      	parameter
      }

    _resolveOperation(identifier, fuzzy, parametersEOcl, static) (container) match {
      case Full(operationList) => operationList
      case Failure(msg, _, _) => resource.addError(msg, container); List()
      case Empty => List()
    }
  }
  
  def resolveOperationDefinition(identifier : String, fuzzy : Boolean, container : EObject, 
                                 reference : EReference, parameters : java.util.List[ParameterCS], 
                                 returnType : TypeCS) : java.util.List[Operation] = {
    container match {
      case OperationDefinitionInContextCS(_, _, tn) => {
        val parametersEOcl = parameters.map(p => p.getParameter)
		    parametersEOcl.find(_.eIsProxy) match {
		      case Some(couldNotResolve) => List()
		      case None => {
		        (tn->oclType).flatMap{tipe =>
				      if (!fuzzy)
				      	lookupOperationOnType(tipe, identifier, false, parametersEOcl)
		              .flatMap(o => Full(List(o)))
				      else
				       Full(lookupOperationOnTypeFuzzy(tipe, identifier, false))
				    } match {
				      case Full(operationList) => {
				        if (returnType != null) {
				        	(returnType->oclType).flatMap{returnType =>
				        		if (!fuzzy)
				        			if (!operationList.first.getType.conformsTo(returnType))
				        				Failure("Operation return type " + operationList.first.getType.getName + 
		                             " does not conform to given type " + returnType.getName + ".")
				        			else
				        				Full(operationList)
				        		else
				        			Full(operationList.filter(o => o.getType.conformsTo(returnType)))
				        	}.open_!
				        } 
				        else
				        	operationList
				      }
				      case Failure(msg, _, _) => resource.addError(msg, container); List()
				      case Empty => List()
				    }
		      }
		    }
      }
      case o@OperationDefinitionInDefCS(_, _) => {
        val parametersEOcl = parameters.map(p => p.getParameter)
		    parametersEOcl.find(_.eIsProxy) match {
		      case Some(couldNotResolve) => List()
		      case None => {
		        (o->context).flatMap{context =>
			        val rt =
			        	if (returnType == null) {
				          o.parent match {
				            case DefinitionExpOperationCS(_, oclExpression) => {
				              (oclExpression->computeOclExpression) match {
				                case Full(oclExpressionEOcl) => Full(oclExpressionEOcl.getType)
				                case _ => Failure("Cannot compute oclExpression for operation definition.")  
				              }
				            }
				          }
			        	} else
			        	  returnType->oclType
		          rt.flatMap{rt =>
	            	if (identifier != null && !identifier.isEmpty && context.isInstanceOf[Type]) {
	            		val contextType = context.asInstanceOf[Type]
	            		// TODO: add parameter check 
	            	  contextType.allOperations.find(o => o.getName == identifier
              																	&& o.getType.conformsTo(rt))
              		match {
								  	case Some(_) => Failure("Operation " + identifier + " is already defined on " +
								  																contextType.getName)
								  	case None => {
			            	  val operation = PivotModelFactory.eINSTANCE.createOperation
						          operation.setName(identifier)
						          determineMultiplicities(rt, operation)
						          parametersEOcl.foreach(p => operation.addParameter(p))
						          val retParameter = PivotModelFactory.eINSTANCE.createParameter
						          retParameter.setKind(ParameterDirectionKind.RETURN)
						          determineMultiplicities(rt, retParameter)
						          operation.addParameter(retParameter)
						          Full(List(operation))
									  }
								  }
				        }
				        else
				        	Empty
			        }
	          }
		      } match {
		        case Full(opList) => opList
		        case Failure(msg, _, _) => resource.addError(msg, container); List()
		        case Empty => List() 
		      }
	      }
	    }
	  }
  }
  
  def resolveParameterDefinition(identifier : String, fuzzy : Boolean, container : EObject, 
  															 reference : EReference, parameterType : TypeCS) : java.util.List[Parameter] = {
    (parameterType->oclType).flatMap{parameterType =>
      val parameter = PivotModelFactory.eINSTANCE.createParameter
      determineMultiplicities(parameterType, parameter)
      parameter.setName(identifier)
      parameter.setKind(ParameterDirectionKind.IN)
      Full(parameter)
    } match {
      case Full(parameter) => List(parameter)
      case Failure(msg, _, _) => resource.addError(msg, parameterType); List()
      case Empty => List()
    }
  }
  
}
