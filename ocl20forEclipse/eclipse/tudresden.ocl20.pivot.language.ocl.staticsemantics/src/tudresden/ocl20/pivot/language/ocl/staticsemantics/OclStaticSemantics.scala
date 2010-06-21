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

trait OclStaticSemantics extends ocl.semantics.OclAttributeMaker with pivotmodel.semantics.PivotmodelAttributeMaker {
  
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
  private var allDefs : Tuple2[collection.mutable.MultiMap[Type, VariableDeclarationWithInitCS], collection.mutable.MultiMap[Type, Tuple2[OperationDefinitionInDefCS, OclExpressionCS]]] = _
  
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
  
  protected def lookupLibraryType(name : String) : Option[Type] = {
     oclLibrary.eContents.flatMap{
      _ match {
			  case t : Type => Full(t)
			  case unknown => Empty}
			}
    .find(_.getName == name)
  }
  
  protected def lookupType(name : String, namespace : Namespace) : Box[Type] = {
    if (name == null || name.isEmpty)
      Failure("Cannot resolve Type with no name.")
    else {
      lookupLibraryType(name) match {
	      case Some(oclLibraryType) => Full(oclLibraryType)
	      case None => {
			    lookupLocal(name, namespace)
		    }
	    }
    }
  }
  
  private def lookupLocal(name : String, namespace : Namespace) : Box[Type] = {
    !!(namespace.lookupType(name)) ?~ 
      ("Cannot find type " + name + " in namespace " + namespace.getName)
  }
  
    
  protected def lookupTypeFuzzy(identifier : String, namespace : Namespace) : List[Type] = {
    namespace.getOwnedType.filter(t => t.getName.startsWith(identifier)):::(
      // add primitive types
		      oclLibrary.eContents.flatMap(_ match {
		        case t : Type => Full(t)
		        case unknown => Empty}
		      ).filter(t => t.getName.startsWith(identifier))
    		)
  }
  
  protected def lookupVariable(name : String, container : Attributable) : Box[Variable] = {
    (container->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
      implicitVariableBox.filter(_.getName == name) or explicitVariables.find(v => v.getName == name)
    }
  }
  
  protected def lookupVariableFuzzy(name : String, container : Attributable) : List[Variable] = {
    container->variables match {
      case Full((_, explicitVariables)) => explicitVariables.filter(v => v.getName.startsWith(name))
      case Failure(_, _, _) | Empty => List()
    }
  }
  
  protected def lookupPropertyOnType(t : Type, name : String, static : Boolean) : Box[Property] = {
    t.allProperties.find(p => p.getName == name && p.isStatic == static) or {
    	val d = getAllDefs._1.filter(d => t.conformsTo(d._1))
    	if (d.isEmpty) Failure("Cannot find property " + name + " on " + t.getName, Empty, Empty)
    	else {
        // TODO: avoid cycles!
    	  d.flatMap{d =>
	        d._2.find(v => name == v.getVariableName.getSimpleName) match {
	    	    case Some(v) => getPropertyFromVariableDeclaration(v, t, name)
	    	    case None => Empty
	        }
        }.toList.firstOption
      }
    }
  }
  
  protected def lookupOperationOnType(t : Type, name : String, static : Boolean, parameters : List[Parameter]) : Box[Operation] = {
    val parameterTypes = parameters.map(determineMultiplicityElementType(_))
    !!(t.lookupOperation(name, parameterTypes)) or {
      val d = getAllDefs._2.filter(d => t.conformsTo(d._1))
      if (d.isEmpty) Failure("Cannot find operation " + name + " on " + t.getName + " with parameters + " + parameters.mkString(", "))
      else {
      	d.flatMap{d =>
      	  d._2.map(_._1.getOperation).find(o => o.getName == name && o.isStatic == static &&
                                           o.hasMatchingSignature(parameterTypes)).flatMap{operation =>
      		  Full(operation)
      		}
      	}.toList.firstOption
      }
    }
  }
  
  protected def lookupOperationOnTypeFuzzy(t : Type, name : String, static : Boolean) : List[Operation] = {
    t.allOperations.filter(_.getName.startsWith(name)):::
      getAllDefs._2.filter(d => t.conformsTo(d._1)).flatMap{d =>
        d._2.map(_._1.getOperation).filter(o => o.getName.startsWith(name) && o.isStatic == static)
      }.toList
  }
  
  protected def resolveTypeByComputingFeature(v : VariableDeclarationWithInitCS,
                                              name : String,
                                              t : Type) : Box[Property] = {
    try {
			computeFeature(v).flatMap{f =>
		    f._1 match {
		      case p : Property => Full(p)
		      case _ => Failure("Cannot find property " + name + " on type " + t + ".", Empty, Empty)
		    }
		  }
		} catch {
		  case i : IllegalStateException => yieldFailure("Recursive property definition.", v)
		}
  }
  
  protected def lookupPropertyOnTypeFuzzy(t : Type, name : String, static : Boolean) : List[Property] = {
    t.allProperties.filter(p => p.getName.startsWith(name) && p.isStatic == static):::
      (getAllDefs._1.filter(d => t.conformsTo(d._1)).flatMap{d =>
      	d._2.filter(vd => vd.getVariableName.getSimpleName.startsWith(name)).map{vd =>
      	  getPropertyFromVariableDeclaration(vd, t, name)
      	}
      }).toList.flatten(i => i)
  }
  
  private def getPropertyFromVariableDeclaration(vd : VariableDeclarationWithInitCS, t : Type, name : String) : Box[Property] = {
    if (vd.getTypeName != null) {
      (vd.getTypeName->oclType).flatMap{tipe =>
        val property = PivotModelFactory.eINSTANCE.createProperty
        determineMultiplicities(tipe, property)
        if (property.getType == null) {
          // this can happen for not fully qualified collections, e.g., Set instead of Set(Integer)
          resolveTypeByComputingFeature(vd, name, t)
        } else 
        	Full(property)
      }
    } else {
      resolveTypeByComputingFeature(vd, name, t)
    }
  }
  
  private val _resolveNamespace : Tuple2[String, Boolean] => Attributable ==> Box[List[Namespace]] = {
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
  
  private def getAllNestedNamespaces(ns : Namespace) : List[Namespace] = {
    ns.getNestedNamespace:::(ns.getNestedNamespace.flatMap(getAllNestedNamespaces(_)))
  }
  
  private val _resolveType : Tuple2[String, Boolean] => Attributable ==> Box[List[Type]] =
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
  
  private val _resolveNamedElement : Tuple2[String, Boolean] => Attributable ==> Box[List[NamedElement]] = {
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
            (aeo->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
          		if (!fuzzy) {
          			val sourceType = sourceExpression.getType
	              sourceExpression match {
	                // if the sourceExpression is an implicit variable (e.g., self or an iterator variable),
	                // the named element can be a variable, a property on the implicit variable or a type
	                case ve : VariableExp if implicitVariableBox.filter(_ == ve.getReferredVariable).isDefined => {
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
		              case ve : VariableExp if implicitVariableBox.filter(_ == ve.getReferredVariable).isDefined => {
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
  
  private val _resolveOperation : Tuple3[String, Boolean, List[Parameter]] => Attributable ==> Box[List[Operation]] = {
    case (identifier, fuzzy, parameters) => {
      case aeo : AttributableEObject => {
        aeo->sourceExpression match {
          case Full(sourceExpression) => {	// this is an operation call with implicit source
          	if (identifier.contains("::"))
              yieldFailure("Cannot call a static operation " + identifier + " in a chained feature call", aeo)
            else {
              if (!fuzzy) {
                isMultipleNavigationCall(aeo) match {
                  case Full(true) => {
                    sourceExpression.getType match {
                      case c : CollectionType =>
                        lookupOperationOnType(c, identifier, parameters, aeo, Empty)
                      case notMultiple : Type =>
                         lookupOperationOnType(oclLibrary.getSetType(sourceExpression.getType), identifier, 
                                              parameters, aeo, Full("implicit as Set()"))
                    }
                  }
                  case Full(false) => {
                    sourceExpression.getType match {
                      case c : CollectionType => {
                        // TODO: lookupOperationOnType!!!
                        (!!(c.getElementType.lookupOperation(identifier, parameters.map(_.getType))) ?~
			              			("Cannot find operation " + identifier + " with parameters " + parameters + " on type " + 
			                    c.getElementType.getName)).flatMap{o => {
			                      addWarning("implicit collect() on " + o.getName, aeo)
			                      Full(List(o))}
                      		}
                      }
                      case notMultiple =>
                        lookupOperationOnType(notMultiple, identifier, parameters, aeo, Empty)
                    }
                  }
                  case Failure(msg, _, _) => Failure(msg, Empty, Empty)
                  case Empty => yieldFailure("Cannot determine sourceExpression.", aeo)
                }
              }
              else
                Full(lookupOperationOnTypeFuzzy(sourceExpression.getType, identifier, false))
            }
          }
          case Failure(_, _, _) | Empty => {	// static operation call or on self
            // TODO: implement
            Empty
          }
        }
      }
    }
  }

  private val isMultipleNavigationCall : Attributable ==> Box[Boolean] =
    childAttr {
      case child : AttributableEObject => {
        case n@NavigationCallExp(source, featureCalls, ops) if child != source => {
          featureCalls.zip(ops).find(child == _._1) match {
            case Some(fc) => Full(fc._2 == "->")
            case None => Empty
          }
        }
        // arguments of a chained operation call are not considered to be multiple
        case i@ImplicitOperationCallCS(arguments, _) if arguments.contains(child.getEObject) => {
          Full(false)
        }
        case i@IteratorExpCS(_, `child`, _) => {
          Full(false)
        }
        case i@IterateExpCS(_, _, `child`) => {
          Full(false)
        }
        case i : ImplicitFeatureCallCS => i->isMultipleNavigationCall
        case _ => Full(false)
      }
    }
  
  private def lookupOperationOnType(tipe : Type, identifier : String, 
                                    parameters : List[Parameter], element : EObject,
                                    warning: Box[String]) : Box[List[Operation]] = {
    // FIXME: find out if the operation is static
    lookupOperationOnType(tipe, identifier, false, parameters)
      .flatMap{o =>
      	warning match {
      	  case Full(w) => addWarning(w + " on " + o.getName, element)
      	  case Failure(_, _, _) | Empty => // ignore
      	}
      	Full(List(o))
      }
  }
  
  private val namespace : Attributable ==> Box[Namespace] = {
    childAttr {
      case child => {
        case null => !!(model.getRootNamespace)
        case PackageDeclarationWithNamespaceCS(_, namespace) if child != namespace => {
          namespace->lastNamespace
        }
        case p@PackageDeclarationNestedNamespaceCS(_) => {
          val namespace = p.getNamespace
          if (namespace.eIsProxy)
          	Empty
          else
            Full(namespace)
        }
        case t@TypePathNameNestedCS(_) => {
          val namespace = t.getNamespace
          if (namespace.eIsProxy)
            Empty
          else
            Full(namespace)
        }
        case passOn => passOn->namespace
      }
    }
  }
  
  private val lastNamespace : Attributable ==> Box[Namespace] = {
    attr {
      case p@PackageDeclarationNestedNamespaceCS(nestedNamespace) => {
        if (nestedNamespace == null) {
          val namespace = p.getNamespace
          if (namespace.eIsProxy)
            Empty
          else
            Full(namespace)
        } else
          nestedNamespace->lastNamespace
      }
    }
  }
  
  private val self : Attributable ==> Box[Variable] = {
    childAttr {
      case child => {
        case null => Empty
        
        case c@ClassifierContextDeclarationCS(typeCS, _) if child != typeCS => {
          (typeCS->oclType).flatMap{selfType =>
	          if (selfType.eIsProxy)
	            Empty
	          else
	            Full(factory.createVariable("self", selfType, null))
          }
        }
        
        case o@OperationContextDeclarationCS(operation, _) if child != operation => {
          val op = operation.getOperation
          if (op.eIsProxy)
            Empty
          else
            Full(factory.createVariable("self", op.getOwningType, null))
        }
        
        case o@AttributeContextDeclarationCS(typeName, _, _) if child != typeName => {
          (typeName->oclType).flatMap{tipe =>
            Full(factory.createVariable("self", tipe, null))
          }
        }
        
        case passOn => passOn->self
      }
    }
  }
  
  private val context : Attributable ==> Box[ConstrainableElement] = {
    childAttr {
      case child => {
        case null => Empty
        case ClassifierContextDeclarationCS(typeCS, _) => typeCS->oclType
        case OperationContextDeclarationCS(operation, _) => {
          val op = operation.getOperation
          if (op.eIsProxy)
            Empty
          else
            Full(op)
        }
        case a : AttributeContextDeclarationCS => {
          val property = a.getProperty
          if (property.eIsProxy)
            Empty
          else
            Full(property)
        }
        case passOn => passOn->context
      }
    }
  }
  
  /**
   * @return a Tuple2 with the first tuple element yielding the current implicit variable,
   * 				 and the second tuple element a List of explicit variables.
   */
  private val variables : Attributable ==> Box[Tuple2[Box[Variable], List[Variable]]] = {
    childAttr {
      case child : AttributableEObject => {
        case null => Full(Empty, List())
        
        case c@ClassifierContextDeclarationCS(typeCS, _) if child != typeCS =>
          for (self <- child->self) yield (Full(self), List())
        
        case o@OperationContextDeclarationCS(operation, _) if child != operation =>
          for (self <- child->self) yield (Full(self), List())
        
        case a : AttributeContextDeclarationCS =>
          for (self <- child->self) yield (Full(self), List())
        
        case p : PostConditionDeclarationCS => {
          (p->context).flatMap {context =>
	          context match {
	            case o : Operation => {
	              (p->variables).flatMap{otherVars =>
		              val result = ExpressionsFactory.INSTANCE.createVariable
				          result.setName("result")
				          result.setType(o.getType)
				          Full((otherVars._1, result::(otherVars._2)))
			          }
	            }
	            case other => yieldFailure("Post conditions are only allowed on operations.", p)
	          }
            
          }
        }
        
        case d@DefinitionExpOperationCS(operation, _) => {
          (d->variables).flatMap{otherVars =>
	          val parametersEOcl = operation.getParameters.map(p => p.getParameter)
				    parametersEOcl.find(_.eIsProxy) match {
				      case Some(couldNotResolve) => Empty
				      case None => {
				        val newVars = parametersEOcl.map{param =>
					        val variable = ExpressionsFactory.INSTANCE.createVariable
					        variable.setName(param.getName)
					        variable.setRepresentedParameter(param)
					        variable
				        }
				        Full(otherVars._1, newVars:::otherVars._2)
				      }
		        }
          }
        }
        
        case l@LetExpCS(variableDeclarations, _) if !variableDeclarations.contains(child.getEObject) => {
          val last = variableDeclarations.last
          (last->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
            (last.getInitialization->computeOclExpression).flatMap{initExp =>
	            checkVariableDeclarationType(last).flatMap{tipe =>
	              Full(implicitVariableBox, factory.createVariable(last.getVariableName.getSimpleName, tipe, initExp)::explicitVariables)
	            }
	          }
          }
        }
        
        // a let exp with multiple variables can have references to previously defined vars
        case l@LetExpCS(variableDeclarations, _) => {
          if (child.isFirst)
            l->variables
          else {
            val prev = child.prev.asInstanceOf[VariableDeclarationWithInitCS]
            ((prev)->variables).flatMap{ case (implicitVariableBox, explicitVariables) =>
              (prev.getInitialization->computeOclExpression).flatMap{initExp =>
		            checkVariableDeclarationType(prev).flatMap{tipe =>
		              Full(implicitVariableBox, factory.createVariable(prev.getVariableName.getSimpleName, tipe, initExp)::explicitVariables)
		            }
		          }
            }
          }
        }
        
        case i@IteratorExpCS(iteratorVariables, _, _) => {
        	(i->sourceExpression).flatMap{se =>
	          (i->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
	            implicitVariableBox.flatMap{implicitVariable =>
		            val iteratorVariablesEOcl = iteratorVariables.flatMap{iv =>
		              if (iv.getTypeName != null) {
			              (iv.getTypeName->oclType).flatMap{tipe =>
				              if (!tipe.conformsTo(determineTypeOf(se)))
				              	yieldFailure("Expected type " + tipe.getName + ", but found " + 
		                                determineTypeOf(se).getName, iv)
				              else
				              	Full(factory.createVariable(iv.getVariableName.getSimpleName, tipe, null))
				            }
		              }
		              else
		                Full(factory.createVariable(iv.getVariableName.getSimpleName, determineTypeOf(se), null))
		            }
		            // if something went wrong on iterator variable evaluation, return Empty
		            if (iteratorVariablesEOcl.size != iteratorVariables.size)
	                Empty
	              else
			            Full(
			              // implicit variable
			            	if (iteratorVariables.isEmpty) // TODO: add unique identifier
				            	Full(factory.createVariable("$implicitVariable$", determineTypeOf(se), null))
				            else
			                Full(iteratorVariablesEOcl.first)
			              ,
			              // explicit variables
			              if (iteratorVariables.size == 2)
		                  iteratorVariablesEOcl.get(1)::implicitVariable::explicitVariables
		                else
		                  implicitVariable::explicitVariables
				          )
		          }
            }
	        }
        }
        
        case i@IterateExpCS(iteratorVariable, resultVariable, _) => {
          (i->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
            implicitVariableBox.flatMap{implicitVariable =>
	            (i->sourceExpression).flatMap{se =>
	              if (iteratorVariable.getTypeName != null) {
		              (iteratorVariable.getTypeName->oclType).flatMap{tipe =>
			              if (!tipe.conformsTo(determineTypeOf(se)))
			              	yieldFailure("Expected type " + tipe.getName + ", but found " + 
	                                determineTypeOf(se).getName, iteratorVariable)
			              else
			              	Full(factory.createVariable(iteratorVariable.getVariableName.getSimpleName, tipe, null))
			            }
	              }
	              else
	                Full(factory.createVariable(iteratorVariable.getVariableName.getSimpleName, determineTypeOf(se), null))
	            }.flatMap{iv =>
	              (resultVariable.getInitialization->computeOclExpression).flatMap{initExp =>
		              checkVariableDeclarationType(resultVariable).flatMap{tipe =>
		              	Full(Full(iv), factory.createVariable(resultVariable.getVariableName.getSimpleName, tipe, initExp)::implicitVariable::explicitVariables)
		              }
	              }
	            }
            }
          }
        }
        
        case passOn => passOn->variables
      }
    }
  }
  
  protected[staticsemantics] val oclType : Attributable ==> Box[Type] = {
    attr {
      case v@NamedLiteralExpCS() => {
        val namedElement = v.getNamedElement
        if (namedElement.eIsProxy)
          Empty
        else
          namedElement match {
            case t : TypedElement => Full(t.getType)
            case t : Type => Full(t)
          }
      }
      case t@TypePathNameSimpleCS() => {
        val `type` = t.getTypeName
        if (`type`.eIsProxy)
          Empty
        else
          Full(`type`)
      }
      case t@TypePathNameNestedCS(typePathName) => {
        typePathName->oclType
      }
      case c@CollectionTypeIdentifierCS(genericType) => {
        val `type` = c.getTypeName
        if (`type`.eIsProxy)
          Empty
        else {
          if (genericType != null) {
            (genericType->oclType).flatMap{gt =>
              `type` match {
                case b : BagType => Full(oclLibrary.getBagType(gt))
                case s : SetType => Full(oclLibrary.getSetType(gt))
                case s : SequenceType => Full(oclLibrary.getSequenceType(gt))
                case o : OrderedSetType => Full(oclLibrary.getOrderedSetType(gt))
                case c : CollectionType => Full(oclLibrary.getCollectionType(gt))
              }
            }
          } else
            Full(`type`)
        }
      }
      case v@VariableDeclarationWithInitCS(_, typeName, init, _) => {
        // TODO: should this always have a type?
        if (typeName != null)
          typeName->oclType
        else
          for (initExp <- init->computeOclExpression;
               `type` <- initExp->oclType) 
            yield `type`
      }
      case v@VariableDeclarationWithoutInitCS(_, typeName) => {
        typeName->oclType
      }
      case c@CollectionLiteralExpCS(collectionType, _) => {
        collectionType->oclType
      }
      case c@CollectionLiteralPartsOclExpCS(oclExpression) => {
        oclExpression->oclType
      }
      case c@CollectionRangeCS(from, to) => {
        for (fromType <- from->oclType;
             toType <- to->oclType
        		 if (fromType.conformsTo(toType)))
	        yield {
	          fromType.commonSuperType(toType)
	        }
      }
      case NavigationCallExp(source, featureCalls, op) => {
        if (featureCalls.isEmpty)
          source->oclType
        else
          featureCalls.last->oclType
      }
      case i@ImplicitPropertyCallCS(_) => {
        val property = i.getProperty
        if (property.eIsProxy)
          Empty
        else
          Full(property.getType)
      }
      case i@ImplicitOperationCallCS(_, _) => {
        val operation = i.getOperationName
        if (operation.eIsProxy)
          Empty
        else
          Full(operation.getType)
      }
      case o@OperationCallBinaryExpCS(source, target, _, operationName) => {
        for (sourceType <- source->oclType;
        		 targetType <- target->oclType;
        		 operation <- !!(sourceType.lookupOperation(operationName, Array(targetType))))
          yield {
        		operation.getType
        	}
      }
      case IntegerLiteralExpCS(_) => {
        Full(oclLibrary.getOclInteger)
      }
      case RealLiteralExpCS(_) => {
        Full(oclLibrary.getOclReal)
      }
      case BooleanLiteralExpCS(_) => {
        Full(oclLibrary.getOclBoolean)
      }
      case StringLiteralExpCS(_) => {
        Full(oclLibrary.getOclString)
      }
      case unknown => yieldFailure("Unkown OCL type: " + unknown, unknown.asInstanceOf[EObject])
    }
  }
  
  private val sourceExpression : Attributable ==> Box[OclExpression] = {
    childAttr {
      case child : AttributableEObject => {
        case null => Empty
        case c : ContextDeclarationCS =>  for (self <- child->self) yield factory.createVariableExp(self)
        case n@NavigationCallExp(source, featureCalls, op) if child != source => {
          if (child.isFirst)
            source->computeOclExpression
          else
            child.prev->computeOclExpression
        }
        case o@OperationCallBaseExpCS(arguments, _) if arguments.contains(child.getEObject) => {
          (child->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariableBox.open_!))
          }
        }
        case i@IteratorExpCS(iteratorVariables, bodyExpression, _) if child == bodyExpression => {
          (child->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariableBox.open_!))
          }
        }
        case i@IterateExpCS(iteratorVariable, _, bodyExpression) if child == bodyExpression => {
          (child->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariableBox.open_!))
          }
        }
        case passOn => passOn->sourceExpression
      } 
    }
  }
  
  private val isInPostCondition : Attributable ==> Boolean = {
    childAttr {
      case child => {
        case null => false
        case p : PostConditionDeclarationCS => true
        case passOn => passOn->isInPostCondition
      }
    }
  }
  
  private val computeConstraints : Attributable ==> Box[List[Constraint]] = {
    attr {
      case p@PackageDeclarationCS(contexts) => {
        Full(contexts.flatMap{c =>
          c->computeConstraints
        }.flatten(i => i))
      }
      
      case c@ClassifierContextDeclarationCS(_, invAndDefs) => {
        Full(invAndDefs.flatMap{iad =>
          computeConstraint(iad)
        })
      }
      
      case o@OperationContextDeclarationCS(_, prePostOrBodyDecls) => {
        Full(prePostOrBodyDecls.flatMap{ppb =>
          computeConstraint(ppb)
        })
      }
      
      case a@AttributeContextDeclarationCS(_, _, initOrDeriveValues) => {
        if (initOrDeriveValues.size == 2) {
          if (initOrDeriveValues(0).isInstanceOf[InitValueCS] && 
              initOrDeriveValues(1).isInstanceOf[InitValueCS])
            yieldFailure("Cannot have more than one 'init' definition for an attribute.", a)
          else if (initOrDeriveValues(0).isInstanceOf[DeriveValueCS] && 
                   initOrDeriveValues(1).isInstanceOf[DeriveValueCS])
            yieldFailure("Cannot have more than one 'derive' definition for an attribute.", a)
          else Full(List())
        } else {
          Full(List())
        }.flatMap{_ =>
	        Full(initOrDeriveValues.flatMap{idv =>
	          computeConstraint(idv)
	        })
        }
      }
      
      case unknown => Empty
    }
  }
  
  private val computeConstraint : Attributable ==> Box[Constraint] = {
    attr {
      case i@InvariantExpCS(name, oclExpression) => {
	      computeBooleanConstraint(i, name, oclExpression, ConstraintKind.INVARIANT)
      }
      
      case d@DefinitionExpCS(definitionExpPart, static) => {
        for(self <- d->self;
        		(feature, init) <- computeFeature(definitionExpPart))
          yield {
	          feature.setStatic(static)
	          val expression = factory.createExpressionInOcl(
		        	init.toString, init, self, null)
		        val constraint = factory.createConstraint(
		        	"", ConstraintKind.DEFINITION, expression, feature, self.getType)
		        constraint
	        }
     	}
      
      case p@PreConditionDeclarationCS(name, oclExpression) => {
        computeBooleanConstraint(p, name, oclExpression, ConstraintKind.PRECONDITION)
      }
      
      case p@PostConditionDeclarationCS(name, oclExpression) => {
        computeBooleanConstraint(p, name, oclExpression, ConstraintKind.POSTCONDITION)
      }
      
      case b@BodyDeclarationCS(name, oclExpression) => {
        (b->self).flatMap{self =>
      		(b->context).flatMap{context =>
        		(oclExpression->computeOclExpression).flatMap{oclExpressionEOcl =>
	            val operation = context.asInstanceOf[Operation]
              if (!oclExpressionEOcl.getType.conformsTo(operation.getType))
                yieldFailure("Expected type " + operation.getType.getName + ", but found " + oclExpressionEOcl.getType.getName, oclExpression)
              else {
		            val result = factory.createVariable(operation.getReturnParameter)
		            val parameters = operation.getInputParameter.map(p => factory.createVariable(p)).toArray
		        		val expression = factory.createExpressionInOcl(
				        	oclExpression.toString, oclExpressionEOcl, self, result, parameters : _*)
				        var constraintName : String = ""
				        if (name != null)
				          constraintName = name.getSimpleName
				        val constraint = factory.createConstraint(
				        	constraintName, ConstraintKind.BODY, expression, null, context)
				        Full(constraint)
              }
        		}
        	}
        }
      }
      
      case i@InitOrDeriveValueCS(oclExpression) => {
        (i->self).flatMap{self =>
	        (i->context).flatMap{context =>
	          (oclExpression->computeOclExpression).flatMap{oclExpressionEOcl =>
	            val property = context.asInstanceOf[Property]
	            if (!oclExpressionEOcl.getType.conformsTo(determineMultiplicityElementType(property)))
	              yieldFailure("Expected type " + property.getType.getName + ", but found " + oclExpressionEOcl.getType.getName, oclExpression)
	            else {
		        		val expression = factory.createExpressionInOcl(
				        	oclExpression.toString, oclExpressionEOcl, self, null)
				        val constraint = factory.createConstraint(
				        	"", i match {
				        	  case _ : InitValueCS => ConstraintKind.INITIAL
				        	  case _ : DeriveValueCS => ConstraintKind.DERIVED
				        	}, expression, null, context)
				        Full(constraint)
	            }
	          }
	        }
	      }
      }
    }
  }
  
  def computeBooleanConstraint(element : AttributableEObject, name : SimpleNameCS, oclExpression : OclExpressionCS, constraintKind : ConstraintKind) : Box[Constraint] = {
    (oclExpression->computeOclExpression).flatMap{oclExpressionEOcl =>
      oclExpressionEOcl.getType match {
        case pt : PrimitiveType => if (pt.getKind != PrimitiveTypeKind.BOOLEAN) 
					yieldFailure(constraintKind.getName + "s must result in type Boolean and not " + 
									pt.getKind + ".", element)
        case otherType => yieldFailure(constraintKind.getName + "s must result in type Boolean and not " + 
                                  otherType.getName + ".", element)
      }
      for(self <- element->self;
      		context <- element->context) yield {
      	val expression = factory.createExpressionInOcl(
        	oclExpression.toString, oclExpressionEOcl, self, null)
        var constraintName : String = ""
        if (name != null)
          constraintName = name.getSimpleName
        val constraint = factory.createConstraint(
        	constraintName, constraintKind, expression, null, context)
        constraint
      }
		}
  }
  
  private val computeFeature : Attributable ==> Box[Tuple2[Feature, OclExpression]] = {
    attr {
      case d@DefinitionExpPropertyCS(variableDeclaration) => {
        computeFeature(variableDeclaration)
      }
      
      case v@VariableDeclarationWithInitCS(variableName, typeName, initialization, _) => {
        (initialization->computeOclExpression).flatMap{oclExpressionEOcl =>
          checkVariableDeclarationType(v).flatMap {tipe =>
	          val property = PivotModelFactory.eINSTANCE.createProperty
			      property.setName(variableName.getSimpleName)
			      // TODO: does not work yet for nested collections
			      determineMultiplicities(tipe, property)
			      (v->self).flatMap{self =>
			      	self.getType.allProperties.find(_.getName == property.getName) match {
			      	  case Some(p) => yieldFailure("Property " + p.getName + " is already defined on " + 
                                           		self.getType.getName, v)
			      	  case None => Full((property, oclExpressionEOcl))
			      	}
			      }
          }
        }
      }
      
      case d@DefinitionExpOperationCS(operationDefinition, oclExpression) => {
        val operation = operationDefinition.getOperation
        if (operation.eIsProxy)
          Empty
        else {
          (oclExpression->computeOclExpression).flatMap{oclExpressionEOcl =>
            val returnType = operationDefinition.getReturnType
            val typeConformance = if (returnType != null) {
	            (returnType->oclType).flatMap{t =>
	              if (!oclExpressionEOcl.getType.conformsTo(t))
	                yieldFailure("Expected type " + t.getName + ", but found " + 
                                oclExpressionEOcl.getType.getName, d)
	              else
	                Full(true)
	            }
	          } else
	            Full(true)
	          typeConformance.flatMap {_ =>
	            Full((operation, oclExpressionEOcl))
	          }
          }
        }
      }
    }
  }
  
  private val computeOclExpression : Attributable ==> Box[OclExpression] = {
    attr {
      case i@IntegerLiteralExpCS(integerLiteral) => {
        Full(factory.createIntegerLiteralExp(integerLiteral))
      }
      
      case r@RealLiteralExpCS(realLiteral) => {
        // TODO: parse Double, but use Float
        Full(factory.createRealLiteralExp(realLiteral.toFloat))
      }
      
      case s@StringLiteralExpCS(stringLiteral) => {
        Full(factory.createStringLiteralExp(stringLiteral))
      }
      
  	  case b@BooleanLiteralExpCS(booleanLiteral) => {
        Full(factory.createBooleanLiteralExp(booleanLiteral))
  	  }
     
  	  case i@InvalidLiteralExpCS() => {
  	    Full(factory.createInvalidLiteralExp)
  	  }
     
  	  case n@NullLiteralExpCS() => {
  	    Full(factory.createUndefinedLiteralExp)
  	  }
  	  
  	  case o@OperationCallBinaryExpCS(source, target, isMarkedPre, operationName) => {
  	    try {
  	    	for (sourceEOcl <- source->computeOclExpression;
  	    		 	 targetEOcl <- target->computeOclExpression;
  	    			 result <- Full(factory.createOperationCallExp(sourceEOcl, operationName, targetEOcl))
  	    		 	)
  	    		yield {
  	    			result
  	     		}
        } catch {
  	    	case e : IllegalArgumentException => yieldFailure(e.getMessage, o) 
        }
  	  }
     
  	  case u@UnaryOperationCallExpCS(target, operationName) => {
  	    try { 
  	    	for (targetEOcl <- computeOclExpression(target);	  	      
		  	    	 result <- Full(factory.createOperationCallExp(targetEOcl, operationName)))
  	    		yield result
        } catch {
        	case e : IllegalArgumentException => yieldFailure(e.getMessage, u)
        }
  	  }
     
  	  case l@LogicalNotOperationCallExpCS(target, operationName) => {
  	  	try { 
  	    	for (targetEOcl <- computeOclExpression(target);	  	      
		  	    	 result <- Full(factory.createOperationCallExp(targetEOcl, operationName)))
  	    		yield result
        } catch {
        	case e : IllegalArgumentException => yieldFailure(e.getMessage, l)
        }
  	  }
     
  	  case v@NamedLiteralExpCS() => {
       	val namedElement = v.getNamedElement
       	if (namedElement.eIsProxy)
       		yieldFailure("Cannot find NamedElement for " + v, v)
       	else {
       		namedElement match {
       		  case v : Variable => Full(factory.createVariableExp(v))
       			case p : Property => {
       				if (p.isStatic) {
       					// TODO: put this into the EssentialOclFactory
       					val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
       					pce.setReferredProperty(p)
       					pce.setSourceType(p.getOwningType)
       					pce.setSource(factory.createTypeLiteralExp(p.getOwningType.getQualifiedNameList))
       					pce.setOclLibrary(oclLibrary)
       					Full(pce)
       				}
       				else {
       				  for (self <- v->self) yield {
       				    // TODO: put this into the EssentialOclFactory
       				  	val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
         					pce.setReferredProperty(p)
         					pce.setSourceType(self.getType)
         					pce.setSource(factory.createVariableExp(self))
         					pce.setOclLibrary(oclLibrary)
         					pce
       				  }
       				}
       			}
       			case t : Type => {
       			  val tle = ExpressionsFactory.INSTANCE.createTypeLiteralExp
       			  tle.setReferredType(t)
       			  tle.setOclLibrary(oclLibrary)
       			  Full(tle)
       			}
       		}
       	}
  	  }
     
  	  case n@NavigationCallExp(source, featureCalls, op) => {
        featureCalls.last->computeOclExpression
  	  }
     
  	  case i@PropertyCallBaseExpCS(isMarkedPre) => {
        if (isMarkedPre && !(i->isInPostCondition))
          yieldFailure("Cannot use @pre outside of a post condition.", i)
        else {
	  	    val property = i.getProperty
	        if (property.eIsProxy) {
	          val typeName = i->sourceExpression match {
	            case Full(t) => t.getType.getName
	            case Failure(_, _, _) | Empty => "unknown type"
	          }
	          yieldFailure("Cannot find property on " + typeName + ".", i)
	        }
	        else {
	          for (sourceExpression <- i->sourceExpression)
	            yield {
			          // TODO: put this into the EssentialOclFactory
			 					val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
			 					pce.setReferredProperty(property)
			 					pce.setSource(sourceExpression)
			 					pce.setOclLibrary(oclLibrary)
			 					pce
		          }
	        }
        }
      }
     	
      case i@OperationCallBaseExpCS(arguments, isMarkedPre) => {
        if (isMarkedPre && !(i->isInPostCondition))
          yieldFailure("Cannot use @pre outside of a post condition.", i)
        else {
	        val operation = i.getOperationName
	        if (operation.eIsProxy) {
	          val typeName = i->sourceExpression match {
	            case Full(t) => t.getType.getName
	            case Failure(_, _, _) | Empty => "unknown type"
	          }
	          yieldFailure("Cannot find operation on " + typeName +  ".", i)
	        }
	        else {
	          val argumentsEOcl = arguments.map(arg => arg->computeOclExpression)
	          argumentsEOcl.find(!_.isDefined) match {
	            case Some(f) => f.asInstanceOf[Box[FeatureCallExp]]
	            case None => {
	              for (sourceExpression <- i->sourceExpression;
	              		 multipleNavigationCall <- i->isMultipleNavigationCall) yield {
	                if (sourceExpression.getType.isInstanceOf[CollectionType] && !multipleNavigationCall) {
	                  // implicit collect()
	                  val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
				   					oce.setReferredOperation(operation)
				   					argumentsEOcl.foreach {arg =>
				   					  oce.getArgument.add(arg.open_!)
				   					}
	                  val iteratorVar = ExpressionsFactory.INSTANCE.createVariable
	                  iteratorVar.setName("$implicitCollect$")
	                  iteratorVar.setType(sourceExpression.getType.asInstanceOf[CollectionType].getElementType)
	                  oce.setSource(factory.createVariableExp(iteratorVar))
				   					oce.setOclLibrary(oclLibrary)
				   					factory.createIteratorExp(sourceExpression, "collect", oce, iteratorVar)
	                } else {
				   					val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
				   					oce.setReferredOperation(operation)	
				   					argumentsEOcl.foreach {arg =>
				   					  oce.getArgument.add(arg.open_!)
				   					}
	                	if (!sourceExpression.getType.isInstanceOf[CollectionType] && multipleNavigationCall)
				   						// implicit asSet()
				   						oce.setSource(sourceExpression.withAsSet)
				   					else {
				   						// make sure, the source is not already contained by another element
				   					  // This can only happen if the sourceExpression is a VariableExp!
				   					  val cleanSourceExpression = 
				   					  	if (sourceExpression.eContainer != null) {
				   					  		sourceExpression match {
				   					  		  case v : VariableExp =>
				   					  		    factory.createVariableExp(factory.createVariable(v.getReferredVariable.getName, v.getReferredVariable.getType, null))
				   					  		}
				   					  	}
				   					  	else
				   					  		sourceExpression
				   					  oce.setSource(cleanSourceExpression)
				   					}
				   					oce.setOclLibrary(oclLibrary)
				   					oce
	                }
	              }
		          }
	          }
	        }
        }
      }
      
      case i@IteratorExpCS(iteratorVariables, bodyExpression, iteratorName) => {
        (i->sourceExpression).flatMap{se =>
	        (bodyExpression->computeOclExpression).flatMap{bodyEOcl =>
	          (bodyExpression->variables).flatMap {case (implicitVariableBox, explicitVariables) =>
         		  implicitVariableBox.flatMap{implicitVariable =>
		            val iteratorNames = iteratorVariables.map(_.getVariableName.getSimpleName)
	         		  val iteratorExp = factory.createIteratorExp(se.getType match {
	         		    		case _ : CollectionType => se
	         		    		case _ : Type => se.withAsSet
	         		    	}, iteratorName, bodyEOcl,
	         		  		(implicitVariable ::
	         		  			explicitVariables.filter(ev => iteratorNames.contains(ev.getName))).toArray : _*)
	         		  // triggers WFR checks in EssentialOcl -> if WFRException is thrown yield a Failure
	         		  try {
	         		    iteratorExp.getType
	         		    Full(iteratorExp)
	         		  } catch {
	         		    case e: WellformednessException => yieldFailure(e.getMessage, i)
	         		  }
         		  }
            }
	        }
        }
      }
      
      case i@IterateExpCS(iteratorVariable, resultVariable, bodyExpression) => {
        (i->sourceExpression).flatMap{se =>
	        (bodyExpression->computeOclExpression).flatMap{bodyEOcl =>
	          (bodyExpression->variables).flatMap {case (implicitVariableBox, explicitVariables) =>
	            (implicitVariableBox).flatMap{implicitVariable =>
		            val iteratorExp = factory.createIterateExp(se.getType match {
	         		    		case _ : CollectionType => se
	         		    		case _ : Type => se.withAsSet
	         		    	}, bodyEOcl, explicitVariables.first, implicitVariable)
	         		  // triggers WFR checks in EssentialOcl -> if WFRException is thrown yield a Failure
	         		  try {
	         		    iteratorExp.getType
	         		    Full(iteratorExp)
	         		  } catch {
	         		    case e: WellformednessException => yieldFailure(e.getMessage, i)
	         		  }
	            }
            }
	        }
        }
      }
      
      case c@CollectionLiteralExpCS(collectionType, collectionLiteralParts) => {
        val literalPartsEOcl = collectionLiteralParts.map(clp => clp->computeLiteralPart)
        literalPartsEOcl.find(!_.isDefined) match {
          case Some(failure) => failure match {
            case Failure(msg, _, _) => Failure(msg, Empty, Empty)
            case Empty => Empty
            case Full(_) => Empty // this cannot happen, but to prevent a warning the case is here
          } 
          case None => {
            (collectionType->oclType).flatMap{ct =>
              val unboxedLiteralParts = literalPartsEOcl.flatten(l => l)
              val genericType = ct.asInstanceOf[CollectionType].getElementType
              val typeConformance = if (genericType != null && !unboxedLiteralParts.isEmpty) {
                val commonSuperType = unboxedLiteralParts.map(_.getType).reduceLeft((a, b) => a.commonSuperType(b))
                if (!commonSuperType.conformsTo(genericType))
                  yieldFailure("Exprected type " + genericType.getName + ", but found " + commonSuperType.getName, c)
                else
                  Full(true)
              } else
                Full(true)
              typeConformance.flatMap{_ =>
                val collectionKind = ct match {
	                case b : BagType => Full(CollectionKind.BAG)
	                case s : SetType => Full(CollectionKind.SET)
	                case s : SequenceType => Full(CollectionKind.SEQUENCE)
	                case o : OrderedSetType => Full(CollectionKind.ORDERED_SET)
	                case c : CollectionType => yieldFailure("'Collection' cannot be initialised.", c)
	              }
                collectionKind.flatMap{collectionKind =>
	              	Full(factory.createCollectionLiteralExp(collectionKind, unboxedLiteralParts.toArray : _*))
	              }
              }
            }
          }
        }
      }
      
      case i@IfExpCS(condition, thenBranch, elseBranch) => {
        for (conditionEOcl <- condition->computeOclExpression;
        		 thenEOcl <- thenBranch->computeOclExpression;
        		 elseEOcl <- elseBranch->computeOclExpression)
          yield factory.createIfExp(conditionEOcl, thenEOcl, elseEOcl)
      }
      
      case l@LetExpCS(variableDeclarations, oclExpression) => {
        variableDeclarations.foldRight (oclExpression->computeOclExpression) {(vd1, vd2) =>
        	vd2.flatMap{vd2 =>
        	  (vd1.getInitialization->computeOclExpression).flatMap{initExp =>
        	  	checkVariableDeclarationType(vd1).flatMap{tipe =>
        	  	  val variable = factory.createVariable(vd1.getVariableName.getSimpleName, tipe, initExp)
		        	  Full(factory.createLetExp(variable, vd2))
        	  	}
            }
          }
        }
      }
      
      case BracketExpCS(oclExpression) => {
        oclExpression->computeOclExpression
      }
      
      case e@EnumLiteralOrStaticPropertyExpCS(typeName) => {
        val enumLitOrProp = e.getEnumLiteralOrStaticProperty
        if (enumLitOrProp.eIsProxy)
          yieldFailure("Cannot resolve enumeration literal or static property.", e)
        else {
          enumLitOrProp match {
            case e : EnumerationLiteral => {
              // TODO: move to EssentialOclFactory
              val exp = ExpressionsFactory.INSTANCE.createEnumLiteralExp
              exp.setReferredEnumLiteral(e)
              exp.setOclLibrary(oclLibrary)
              Full(exp)
            }
            case p : Property => {
              // TODO: move to EssentialOclFactory
              val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
     					pce.setReferredProperty(p)
     					pce.setSourceType(p.getOwningType)
     					pce.setSource(factory.createTypeLiteralExp(p.getOwningType.getQualifiedNameList))
     					pce.setOclLibrary(oclLibrary)
     					Full(pce)
            }
          }
        }
      }
      
  	  case unknown => {
  	    unknown match {
  	      case u : AttributableEObject => resource.addError("unknown element", u.getEObject)
  	      case _ => //ignore
  	    }
  	    Failure("unknown element: " + unknown)
      }
    }
  }
  
  protected val computeLiteralPart : Attributable ==> Box[CollectionLiteralPart] = {
    attr {
      case CollectionLiteralPartsOclExpCS(oclExpression) => {
        (oclExpression->computeOclExpression).flatMap{oclExpressionEOcl =>
          Full(factory.createCollectionItem(oclExpressionEOcl))
        }
      }
      case c@CollectionRangeCS(from, to) => {
        (from->computeOclExpression).flatMap{fromEOcl =>
          (to->computeOclExpression).flatMap{toEOcl =>
            val ok_? = fromEOcl.getType match {
              case pt : PrimitiveType if pt.getKind != PrimitiveTypeKind.INTEGER =>
                yieldFailure("Collection Ranges can only contain Integers. Found: " + fromEOcl.getType.getName, from)
              case ok => Full(true)
            }
            ok_?.flatMap{_ =>
              val ok_? = toEOcl.getType match {
                case pt : PrimitiveType if pt.getKind != PrimitiveTypeKind.INTEGER =>
                  yieldFailure("Collection Ranges can only contain Integers. Found: " + toEOcl.getType.getName, to)
                case ok => Full(true)
              }
              ok_?.flatMap{_ =>
                Full(factory.createCollectionRange(fromEOcl, toEOcl))
              }
            }
          }
        }
      }
    }
  }
  
  /**
   * If the given type is a CollectionType, set MuliplicityElement features
   */
  private def determineMultiplicities(tipe : Type, multiplicityElement : MultiplicityElement with TypedElement) = {
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
  
  private def determineMultiplicityElementType(multiplicityElement : MultiplicityElement with TypedElement) = {
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
  
  private def determineTypeOf(oclExpression : OclExpression) = {
		oclExpression.getType match {
			case c : CollectionType => c.getElementType
			case t : Type => t
		}
  }
  
  private def checkVariableDeclarationType(vd : VariableDeclarationWithInitCS) : Box[Type] = {
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
    OclStaticSemanticsTransactions.startStaticSemanticsAnalysis(this, resource.getContents.get(0))
    val constraints = computeConstraints(root)
    // to avoid the conversion of Scala List to Java List multiple times
    val result : java.util.List[Constraint] = constraints.openOr {throw new OclStaticSemanticsException}
    OclStaticSemanticsTransactions.endStaticSemanticsAnalysis(model, resource, result)
    allDefs = null
    result
  }
  
  def resolveNamespace(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Namespace] = {
    val result = _resolveNamespace(identifier, fuzzy) (container) match {
      case Full(namespaceList) => namespaceList
      case Failure(msg, _, _) => {
        //resource.addError(msg, container)
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
        //resource.addError(msg, container)
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
    // TODO: resolve properties on their own
    
    _resolveNamedElement(identifier, fuzzy) (container) match {
      case Full(namedElementList) => namedElementList.flatMap {
      	_ match {
      	  case p : Property => Some(p)
      	  case _ => None
        }
      }
      case Failure(_, _, _) | Empty => List()
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
          case Empty | Failure(_, _, _) => List()
        }
      }
    }
  }
  
  def resolveOperation(identifier : String, fuzzy : Boolean, container : EObject, reference : EReference, 
                       parameters : java.util.List[OclExpressionCS]) : java.util.List[Operation] = {
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

    _resolveOperation(identifier, fuzzy, parametersEOcl) (container) match {
      case Full(operationList) => operationList
      case Failure(msg, _, _) => println(msg); List()
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
				      	(!!(tipe.lookupOperation(identifier, parametersEOcl.map(_.getType))) ?~
		              ("Cannot resolve operation with name "+ identifier + " and parameter types (" + 
		                 parametersEOcl.map(_.getType.getName).mkString(", ") + ")"))
		              .flatMap(o => Full(List(o)))
				      else
				       Full(tipe.allOperations.filter(o => o.getName.startsWith(identifier)))
				    } match {
				      case Full(operationList) => {
				        if (returnType != null) {
				        	(returnType->oclType).flatMap{returnType =>
				        		if (!fuzzy)
				        			if (!operationList.first.getType.conformsTo(returnType))
				        				yieldFailure("Operation return type " + operationList.first.getType.getName + 
		                             " does not conform to given type " + returnType.getName + ".", container)
				        			else
				        				Full(operationList)
				        		else
				        			Full(operationList.filter(o => o.getType.conformsTo(returnType)))
				        	}.open_!
				        } 
				        else
				        	operationList
				      }
				      case Failure(msg, _, _) => yieldFailure(msg, container); List()
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
								  	case Some(_) => yieldFailure("Operation " + identifier + " is already defined on " +
								  																contextType.getName, o)
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
		        case Failure(_, _, _) | Empty => List() 
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
      case Failure(msg, _, _) => yieldFailure(msg, parameterType); List()
      case Empty => List()
    }
  }
  
  def getAllDefs = {
    if (allDefs == null) 
      allDefs = OclStaticSemanticsTransactions.getAllDefs(this, resource.getContents.get(0))
    allDefs
  }
}
