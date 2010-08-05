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
		                    val allProperties = implicitVariables.map{iv =>
                        	lookupPropertyOnType(iv.getType, identifier, false)
		                    }.filter(p => p.isDefined)
		                    if (!allProperties.isEmpty)
		                    	Full(List(allProperties.first.open_!))
		                    else {
	                        (aeo->namespace).flatMap{namespace =>
	                          lookupType(identifier, namespace).flatMap{t =>
                             	Full(List(t))
	                          }
                          }
	                      }
		                  }
	                  }
	                }
	                // in the other cases, the source is part of a navigation call -> only properties have
	                // to be considered
	                case other => {
	                  lookupProperty(sourceExpression, aeo, identifier, false).flatMap{p =>
	                    Full(List(p))
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
		                	Full(lookupVariableFuzzy(identifier, aeo):::
                          implicitVariables.flatMap{iv =>
                          	lookupPropertyOnTypeFuzzy(factory.createVariableExp(iv), aeo, identifier, false)
                          }.flatten(p => p):::
                          lookupTypeFuzzy(identifier, namespace))
		                }
		              }
		              // SourceExpression not equal an implicit variable -> lookup in a navigation call
		              case other => {
		                lookupPropertyOnTypeFuzzy(other, aeo, identifier, false)
		              }
		            }
		          }
            }
          }
        }
      }
    }
  }
  
  protected val _resolveOperation : Tuple4[String, Boolean, List[Type], Boolean] => Attributable ==> Box[List[Operation]] = {
    paramAttr {
	    case (identifier, fuzzy, parameters, static) => {
	      case soc@StaticOperationCallExpCS(typeName, arguments) => {
	        (typeName->oclType).flatMap{sourceType =>
	          val argumentsEOcl = arguments.flatMap(arg => arg->computeOclExpression)
	          if (arguments.size != argumentsEOcl.size)
	          	Failure("Parameters for operation " + identifier + " cannot be computed.")
	          else {
	          	if (!fuzzy) {
	          		lookupOperationOnType(sourceType, identifier, true, argumentsEOcl.map(_.getType)).flatMap{o =>
	          		  Full(List(o))
	          		}
	          	}
	          	else {
	          		Full(lookupOperationOnTypeFuzzy(sourceType, identifier, true))
	          	}
	          }
	        }
        }
	      case aeo : AttributableEObject => {
	        (aeo->sourceExpression).flatMap{sourceExpression =>
	          (aeo->variables).flatMap{case (implicitVariables, _) =>
		          if (!fuzzy) {
		            sourceExpression match {
		              case ve : VariableExp if !implicitVariables.filter(_ == ve.getReferredVariable).isEmpty => {
		                val allOperations = implicitVariables.map{iv =>
		                  lookupOperation(factory.createVariableExp(iv), aeo, identifier, static, parameters)
		                }.filter(_.isDefined)
		                if (allOperations.isEmpty)
		                	yieldFailure("Unable to resolve operation " + identifier + " with parameters " + parameters.mkString(", "), aeo.getEObject)
                    else
                      Full(List(allOperations.first.open_!))
		              }
		              case _ =>
		                lookupOperation(sourceExpression, aeo, identifier, static, parameters).flatMap{operation =>
				              Full(List(operation))
				            }
		            }
		          }
		          else
		            Full(lookupOperationOnTypeFuzzy(sourceExpression.getType, identifier, static))
		        }
		      }
	      }
	    }
    }
  }
  
  def resolveNamespace(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Namespace] = {
    val result = _resolveNamespace(identifier, fuzzy) (container) match {
      case Full(namespaceList) => namespaceList
      case Failure(msg, _, _) => {
        iResource.addError(msg, container)
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
        iResource.addError(msg, container)
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
      case Failure(msg, _, _) => iResource.addError(msg, container); List()
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
          case Failure(msg, _, _) => iResource.addError(msg, container); List()
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
      	parameterEOcl
      }

    _resolveOperation(identifier, fuzzy, parametersEOcl.map(_.getType), static) (container) match {
      case Full(operationList) => operationList
      case Failure(msg, _, _) => iResource.addError(msg, container); List()
      case Empty => List()
    }
  }
  
  def resolveOperationDefinition(identifier : String, fuzzy : Boolean, container : EObject, 
                                 reference : EReference, parameters : java.util.List[ParameterCS], 
                                 returnType : TypeCS) : java.util.List[Operation] = {
    container match {
      case o@OperationDefinitionInContextCS(_, _, tn) => {
        val parametersEOcl = parameters.map(p => p.getParameter)
		    parametersEOcl.find(_.eIsProxy) match {
		      case Some(couldNotResolve) => List()
		      case None => {
		        (tn->oclType).flatMap{tipe =>
				      if (!fuzzy)
				      	(lookupOperationOnType(tipe, identifier, false, parametersEOcl.map(_.getType)) or
				      			lookupOperationOnType(tipe, identifier, true, parametersEOcl.map(_.getType)))
		              .flatMap(o => Full(List(o)))
				      else
				       Full(lookupOperationOnTypeFuzzy(tipe, identifier, false):::lookupOperationOnTypeFuzzy(tipe, identifier, true))
				    } match {
				      case Full(operationList) => {
				        if (returnType != null) {
				        	(returnType->oclType).flatMap{returnType =>
				        		if (!fuzzy)
				        			if (!operationList.first.getType.equals(returnType))
				        				yieldFailure("Operation return type " + operationList.first.getType.getName + 
		                             " is not equal to given type " + returnType.getName + ".", o)
				        			else
				        				Full(operationList)
				        		else
				        			Full(operationList.filter(o => o.getType.conformsTo(returnType)))
				        	}.open_!
				        } 
				        else
				        	operationList
				      }
				      case Failure(msg, _, _) => iResource.addError(msg, container); List()
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
                  // this is some crude hack to ensure the descendency from OclAny for the type
	            	  val operations = contextType.allOperations.union(oclLibrary.getOclAny.allOperations)
                  operations.find(o => o.getName == identifier 
                                                && o.hasMatchingSignature(parametersEOcl.map(_.getType))
              																	&& o.getType.conformsTo(rt))
              		match {
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
				        }
				        else
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
  
  def resolveParameterDefinition(identifier : String, fuzzy : Boolean, container : EObject, 
  															 reference : EReference, parameterType : TypeCS) : java.util.List[Parameter] = {
    (parameterType->oclType).flatMap{parameterType =>
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
