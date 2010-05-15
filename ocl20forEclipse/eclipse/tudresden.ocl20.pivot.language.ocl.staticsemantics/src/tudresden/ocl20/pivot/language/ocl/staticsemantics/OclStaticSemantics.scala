package tudresden.ocl20.pivot.language.ocl.staticsemantics

import org.eclipse.emf.ecore._
import tudresden.attributegrammar.integration.kiama._
import tudresden.ocl20.pivot.language.ocl.semantics._
import tudresden.ocl20.pivot.pivotmodel._
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
  protected val model : IModel
  
  /*
   * Used to look up primitive types.
   */
  protected val oclLibrary : OclLibrary
  
  /*
   * Do not use resource directly. Instead refer to yieldFailure and addWarning.
   */
  protected val resource : OclResource
  
  /*
   * EssentialOclFactory to create essential OCL expressions
   */
  val factory = new EssentialOclFactory(oclLibrary, model)
  
  /*
   * Holds all definitions for a type
   */
  private var allDefs : collection.mutable.MultiMap[Type, VariableDeclarationWithInitCS] = _
  
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
  
  protected def lookup(name : String, namespace : Namespace) : Box[Type] = {
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
  
  // TODO: add fuzzy
  private def lookupLocal(name : String, namespace : Namespace) : Box[Type] = {
    !!(namespace.lookupType(name)) ?~ 
      ("Cannot find type " + name + " in namespace " + namespace.getName)
  }
  
  protected def lookupVariable(name : String, container : Attributable) : Box[Variable] = {
    for(variables <- container->variables;
    		variable <- variables.find(v => v.getName == name))
      yield variable 
  }
  
  protected def lookupVariableFuzzy(name : String, container : Attributable) : List[Variable] = {
    container->variables match {
      case Full(variables) => variables.filter(v => v.getName.startsWith(name))
      case Failure(_, _, _) | Empty => List()
    }
  }
  
  protected def lookupPropertyOnType(t : Type, name : String, static : Boolean) : Box[Property] = {
    t.allProperties.find(p => p.getName == name && p.isStatic == static) or {
    	allDefs.get(t) match {
        case Some(variableDeclarationWithInit) => {
          // TODO: avoid cycles!
          variableDeclarationWithInit.find(v => name == v.getVariableName.getSimpleName).flatMap{v =>
            if (v.getTypeName != null) {
              (v.getTypeName->oclType).flatMap{tipe =>
	              val property = PivotModelFactory.eINSTANCE.createProperty
	              property.setType(tipe)
	              determineMultiplicities(tipe, property)
	              Full(property)
              }
            } else {
              try {
                computeFeature(v).flatMap{f =>
                  f._1 match {
                    case p : Property => Full(p)
                    case _ => Failure("Cannot find property " + name + " on type " + t + ".", Empty, Empty)
                  }
                }
              } catch {
                case i : IllegalStateException => yieldFailure("Recursive property definition", v)
              }
            }
          }
        }
        case None => Failure("Cannot find property " + name + " on type " + t + ".", Empty, Empty)
      }
    }
  }
  
   def lookupPropertyOnTypeFuzzy(t : Type, name : String, static : Boolean) : List[Property] = {
    t.allProperties.filter(p => p.getName.startsWith(name) && p.isStatic == static).toList
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
            lookup(identifier, namespace).flatMap{t =>
              Full(List(t))
            }
          } else
          	findTypeFuzzy(namespace, identifier)
        }
        case other =>
          for (namespace <- other->namespace;
        			 typeList <- _resolveType(identifier, fuzzy) (namespace))
          	yield typeList
      }
    }
  
  private val _resolveTypedElement : Tuple2[String, Boolean] => Attributable ==> Box[List[TypedElement]] = {
    paramAttr {
      case (identifier, fuzzy) => {
        case aeo : AttributableEObject => {
          // TODO: replac with new code
          val name = identifier.split("::", -1).toList
          if (name.size == 1) {	// nothing static, no tuple
            if (!fuzzy) {
              (aeo->sourceExpression).flatMap{sourceExpression =>
              	(aeo->self).flatMap{self => 
                  val sourceType = sourceExpression.getType
                  if (sourceExpression == self) { // variable or implicit property
                    lookupVariable(identifier, aeo) match {
                      case Full(variable) => Full(List(variable))
                      case Empty | Failure(_, _, _) => 
                        lookupPropertyOnType(sourceType, identifier, false).flatMap{p =>
                          Full(List(p))
                        }
                    }
                  }
                  else // explicit source for a property
                    lookupPropertyOnType(sourceType, identifier, false).flatMap{p =>
                      Full(List(p))
                    }
              	}
              }
            }
            // fuzzy == true
            else {
              aeo->sourceExpression match {
                // SourceExpression set -> lookup in a navigation call
                case Full(sourceExpression) => {
                  Full(lookupPropertyOnTypeFuzzy(sourceExpression.getType, identifier, false))
                }
                // no SourceExpression set -> lookup on self
                case Failure(_, _, _) | Empty => {
                  (aeo->self).flatMap{self =>
		                Full(lookupVariableFuzzy(identifier, aeo):::lookupPropertyOnTypeFuzzy(self.getType, identifier, false))
		              }
                }
              }
            }
          } else {	// static property or tuple type; TODO: tuple type
            (_resolveType (name.take(name.size - 1).mkString("::"), false) (aeo)).flatMap {t =>
              if (!fuzzy) {
                lookupPropertyOnType(t.first, name.last, true).flatMap{p =>
                  Full(List(p))
                }
              } else {
                Full(lookupPropertyOnTypeFuzzy(t.first, name.last, true))
              }
            }
          }
        }
      }
    }
  }
  
  private def findTypeFuzzy(namespace : Namespace, identifier : String) : Box[List[Type]] = {
    Full(namespace.getOwnedType.filter(t => t.getName.startsWith(identifier))
    		:::(// add primitive types
			      oclLibrary.eContents.flatMap(_ match {
			        case t : Type => Full(t)
			        case unknown => Empty}
			      ).filter(t => t.getName.startsWith(identifier))
    		)
    )
  }
  
  private val _resolveOperation : Tuple3[String, Boolean, List[Parameter]] => Attributable ==> Box[List[Operation]] = {
    case (identifier, fuzzy, parameters) => {
      case aeo : AttributableEObject => {
        aeo->sourceExpression match {
          case Full(sourceExpression) => {	// this is an operation call with implicit source
            if (identifier.contains("::"))
              yieldFailure("Cannot call a static operation " + identifier + " in a chained feature call", aeo)
            else {
              // TODO: if not found look for defs!
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
                        // TODO: insert implicit collect!
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
                Full(sourceExpression.getType.allOperations.filter(_.getName.startsWith(identifier)).toList)
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
      case child => {
        case n@NavigationCallExp(source, featureCalls, ops) if child != source => {
          featureCalls.zip(ops).find(child == _._1) match {
            case Some(fc) => Full(fc._2 == "->")
            case None => Empty
          }
        }
        case i : ImplicitFeatureCallCS => i->isMultipleNavigationCall
        case _ => Empty
      }
    }
  
  private def lookupOperationOnType(tipe : Type, identifier : String, 
                                    parameters : List[Parameter], element : EObject,
                                    warning: Box[String]) : Box[List[Operation]] = {
    (!!(tipe.lookupOperation(identifier, parameters.map(_.getType))) ?~
    			("Cannot find operation " + identifier + " with parameters " + 
          parameters + " on type " + 
          tipe))
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
        case c@ClassifierContextDeclarationCS(typeCS, _) if child != typeCS => {
          (typeCS->oclType).flatMap{selfType =>
	          if (selfType.eIsProxy)
	            Empty
	          else
	            Full(factory.createVariable("self", selfType, null))
          }
        }
        case passOn => passOn->self
      }
    }
  }
  
  private val variables : Attributable ==> Box[List[Variable]] = {
    childAttr {
      case child => {
        case null => Full(List())
        case c@ClassifierContextDeclarationCS(typeCS, _) if child != typeCS =>
          for (self <- child->self) yield List(self)
        case passOn => passOn->variables
      }
    }
  }
  
  // TODO: remove as it is dangerous to determine type before it is inferred
  private val oclType : Attributable ==> Box[Type] = {
    attr {
      case v@VariableOrStaticPropertyOrEnumLiteralExpCS() => {
        val typedElement = v.getTypedElement
        if (typedElement.eIsProxy)
          Empty
        else
          Full(typedElement.getType)
      }
      case t@TypePathNameSimpleCS() => {
        val `type` = t.getTypeName
        if (`type`.eIsProxy)
          Empty
        else
          Full(`type`)
      }
      case v@VariableDeclarationWithInitCS(_, typeName, init) => {
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
      case i@ImplicitOperationCallCS(_) => {
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
      case LetExpCS(variableDeclaration, _) => {
        variableDeclaration->oclType
      }
      case unknown => yieldFailure("Unkown OCL type: " + unknown, unknown.asInstanceOf[EObject])
    }
  }
  
  private val sourceExpression : Attributable ==> Box[OclExpression] = {
    childAttr {
      case child => {
        case n@NavigationCallExp(source, featureCalls, op) if child != source => {
          if (child.isFirst)
            source->computeOclExpression
          else
            child.prev->computeOclExpression
        }
        // TODO: parameters may have another SE
        case other => for (self <- other->self) yield factory.createVariableExp(self)
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
      
      case c@ClassifierContextDeclarationCS(typeCS, invAndDefs) => {
        Full(invAndDefs.flatMap{iad =>
          computeConstraint(iad)
        })
      }
      case unknown => Empty
    }
  }
  
  private val computeConstraint : Attributable ==> Box[Constraint] = {
    attr {
      case i@InvariantExpCS(name, oclExpression) => {
	      computeOclExpression(oclExpression).flatMap{oclExpressionEOcl =>
          oclExpressionEOcl.getType match {
	          case pt : PrimitiveType => if (pt.getKind != PrimitiveTypeKind.BOOLEAN) 
	        	  						yieldFailure("Invariants must result in type Boolean and not " + 
	        	  										pt.getKind + ".", i)
	          case otherType => yieldFailure("Invariants must result in type Boolean and not " + 
	                                    otherType.getName + ".", i)
	        }
	        for(self <- i->self) yield {
          	val expression = factory.createExpressionInOcl(
		        	oclExpression.toString, oclExpressionEOcl, self, null)
		        var constraintName : String = ""
		        if (name != null)
		          constraintName = name.getSimpleName
		        val constraint = factory.createConstraint(
		        	constraintName, ConstraintKind.INVARIANT, expression, null, self.getType)
		        constraint
          }
	  		}
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
    }
  }
  
  private val computeFeature : Attributable ==> Box[Tuple2[Feature, OclExpression]] = {
    attr {
      case d@DefinitionExpPropertyCS(variableDeclaration) => {
        computeFeature(variableDeclaration)
      }
      case v@VariableDeclarationWithInitCS(variableName, typeName, initialization) => {
          for (oclExpressionEOcl <- initialization->computeOclExpression)
            yield {
              val property = PivotModelFactory.eINSTANCE.createProperty
				      property.setName(variableName.getSimpleName)
				      // TODO: does not work yet for nested collections
				      determineMultiplicities(oclExpressionEOcl.getType, property)
				      property.setType(oclExpressionEOcl.getType)
				      (property, oclExpressionEOcl)
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
     
  	  case v@VariableOrStaticPropertyOrEnumLiteralExpCS() => {
       	val typedElement = v.getTypedElement
       	if (typedElement.eIsProxy)
       		yieldFailure("Cannot find TypedElement for " + v, v)
       	else {
       		typedElement match {
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
       		}
       	}
  	  }
     
  	  case n@NavigationCallExp(source, featureCalls, op) => {
        featureCalls.last->computeOclExpression
  	  }
     
  	  case i@ImplicitPropertyCallCS(isMarkedPre) => {
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
     	
      case i@ImplicitOperationCallCS(arguments) => {
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
              for (sourceExpression <- i->sourceExpression) yield {
	              // TODO: put this into the EssentialOclFactory
		   					val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
		   					oce.setReferredOperation(operation)
		   					oce.setSourceType(operation.getType)
		   					argumentsEOcl.foreach {arg =>
		   					  oce.getArgument.add(arg.open_!)
		   					}
		   					// TODO: failure handling!
		   					oce.setSource(sourceExpression)
		   					oce.setOclLibrary(oclLibrary)
		   					oce
              }
	          }
          }
        }
      }
      
  	  case unknown => {
  	    unknown match {
  	      case u : AttributableEObject => resource.addError("unknown element", u)
  	      case _ => //ignore
  	    }
  	    Failure("unknown element: " + unknown)
      }
    }
  }
  
  /**
   * If the given type is a CollectionType, set MuliplicityElement features
   */
  private def determineMultiplicities(tipe : Type, multiplicityElement : MultiplicityElement) = {
    tipe match {
      case c : CollectionType => {
        multiplicityElement.setMultiple(true)
        if (c.getKind == CollectionKind.SET) {
          multiplicityElement.setUnique(true)
        } else if (c.getKind == CollectionKind.SEQUENCE) {
          multiplicityElement.setOrdered(true)
        } else if (c.getKind == CollectionKind.ORDERED_SET) {
          multiplicityElement.setUnique(true)
          multiplicityElement.setOrdered(true)
        }
      }
      case _ => // ignore
    }
  }
  
  @throws(classOf[OclStaticSemanticsException])
  def cs2EssentialOcl(attributable : EObject) : java.util.List[Constraint] = {
    allDefs = OclStaticSemanticsTransactions.startStaticSemanticsAnalysis(model, resource, attributable)
    val constraints = computeConstraints(attributable)
    // to avoid the conversion of Scala List to Java List multiple times
    val result : java.util.List[Constraint] = constraints.openOr {throw new OclStaticSemanticsException}
    OclStaticSemanticsTransactions.endStaticSemanticsAnalysis(model, resource, result)
    result
  }
  
  def resolveNamespace(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Namespace] = {
	  _resolveNamespace(identifier, fuzzy) (container) match {
      case Full(namespaceList) => namespaceList
      case Failure(msg, _, _) => {
        //resource.addError(msg, container)
        List()
      }
      case Empty => List()
    }
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
  
  def resolveTypedElement(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[TypedElement] = {
    _resolveTypedElement(identifier, fuzzy) (container) match {
      case Full(typedElementList) => typedElementList
      case Failure(_, _, _) | Empty => List()
    }
  }
  
  def resolveProperty(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Property] = {
    // TODO: resolve properties on their own
    
    _resolveTypedElement(identifier, fuzzy) (container) match {
      case Full(typedElementList) => typedElementList.flatMap {
      	_ match {
      	  case p : Property => Some(p)
      	  case _ => None
        }
      }
      case Failure(_, _, _) | Empty => List()
    }
  }
  
  def resolveOperation(identifier : String, fuzzy : Boolean, container : EObject, reference : EReference, parameters : java.util.List[OclExpressionCS]) : java.util.List[Operation] = {
    // TODO: set ownedParameters
    val parametersEOcl = 
      for (parameter <- parameters;
      		 parameterEOcl <- parameter->computeOclExpression)
      yield {
      	val parameter = PivotModelFactory.eINSTANCE.createParameter
      	parameter.setName(parameterEOcl.getName)
      	determineMultiplicities(parameterEOcl.getType, parameter)
      	parameterEOcl.getType match {
      	  case c : CollectionType => parameter.setType(c.getElementType)
      	  case g : GenericType => parameter.setGenericType(g)
      	  case otherType : Type => parameter.setType(otherType)
      	}
      	parameter.setKind(ParameterDirectionKind.IN)
      	parameter
      }

    _resolveOperation(identifier, fuzzy, parametersEOcl) (container) match {
      case Full(operationList) => operationList
      case Failure(msg, _, _) => println(msg); List()
      case Empty => List()
    }
  }
}
