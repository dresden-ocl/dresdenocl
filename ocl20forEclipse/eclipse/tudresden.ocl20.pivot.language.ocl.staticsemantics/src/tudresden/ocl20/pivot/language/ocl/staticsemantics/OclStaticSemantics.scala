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

class OclStaticSemantics(model : IModel, oclLibrary : OclLibrary, resource : OclResource) extends semantics.OclAttributeMaker {
  
  /*
   * Holds all definitions for a type
   */
  private var allDefs : collection.mutable.MultiMap[Type, VariableDeclarationWithInitCS] = _
  
  /*
   * For cached attributes.
   */
  import kiama.attribution.Attribution._
  
  private def yieldFailure(message : String, element : EObject) = {
    resource.addError(message, element)
    Failure(message)
  }
  
  private val _resolveNamespace : Tuple3[String, Boolean, Environment] => Attributable ==> Box[List[Namespace]] = {
    paramAttr {
      case (identifier, fuzzy, env) => {
        case _ : AttributableEObject => {
          val _namespaceNames = identifier.split("::", -1).toList
          val namespaceNames = _namespaceNames.splitAt(_namespaceNames.size - 1)
          // TODO: insert check for parent environment
          namespaceNames._1.foldLeft (env.getNamespace) {(ns, i) =>
            ns.flatMap(nns => !!(nns.lookupNamespace(i)) ?~ 
            		("Cannot find nested namespace " + i + " in namespace " + 
                nns.getQualifiedName + "."))
          }.flatMap { ns =>
            if (!fuzzy) {
              (!!(ns.lookupNamespace(namespaceNames._2.first)) ?~ 
	              ("Cannot find nested namespace " + namespaceNames._2.first + 
	                 " in namespace " + ns.getQualifiedName + ".")).flatMap(ns => Full(List(ns)))
	          } else {
              val nestedNamespaces = ns.getNestedNamespace.filter(nns => nns.getName.startsWith(namespaceNames._2.first))
	            Full(nestedNamespaces:::(nestedNamespaces.flatMap(getAllNestedNamespaces(_))))
	          }
          }
        }
      }
    }
  }
  
  private def getAllNestedNamespaces(ns : Namespace) : List[Namespace] = {
    ns.getNestedNamespace:::(ns.getNestedNamespace.flatMap(getAllNestedNamespaces(_)))
  }
  
  private val _resolveType : Tuple3[String, Boolean, Environment] => Attributable ==> Box[List[Type]] =
    paramAttr {
      case (identifier, fuzzy, env) => {
        case something => {
          val typeName = identifier.split("::", -1).toList
          if (!fuzzy) {
            env.lookup(typeName).flatMap{t =>
              Full(List(t))
            }
          } else {
            if (typeName.size > 1) {
	            val typeNameNamespace = typeName.splitAt(typeName.size - 1)
	            (_resolveNamespace (typeNameNamespace._1.mkString("::"), false, env) (something)).flatMap{ns =>
	              findTypeFuzzy(ns.first, typeNameNamespace._2.first, env)
	            }
            } else {
              env.getNamespace.flatMap{ns =>
	            	findTypeFuzzy(ns, identifier, env)
              }
            }
          }
        }
      }
    }
  
  private val _resolveTypedElement : Tuple3[String, Boolean, Environment] => Attributable ==> Box[List[TypedElement]] = {
    paramAttr {
      case (identifier, fuzzy, env) => {
        case something => {
          val name = identifier.split("::", -1).toList
          if (name.size == 1) {	// variable or implicit/explicit property
            if (!fuzzy) {
              env.lookupExplicitVariable(identifier) match {
                case Full(variable) => Full(List(variable))
                case Failure(_, _, _) | Empty => {
                  env.getSourceExpression.flatMap{se =>
	                  env.lookupPropertyOnType(se.getType, identifier, false) match {
	                    case Full(p) => Full(List(p))
	                    case Failure(msg, _, _) => {
	                      allDefs.get(se.getType) match {
	                        case Some(variableDeclarationWithInit) => {
	                          // TODO: avoid cycles!
	                          variableDeclarationWithInit.find(v => identifier == v.getVariableName.getSimpleName).flatMap{v =>
	                            if (v.getTypeName != null) {
	                              val tipe = v.getTypeName.getTypeName
	                              val property = PivotModelFactory.eINSTANCE.createProperty
	                              property.setType(tipe)
	                              determineMultiplicities(tipe, property)
	                              Full(List(property)) 
	                            } else {
		                            try {
			                            computeFeature(v).flatMap{f =>
			                              Full(List(f._1))
			                            }
		                            } catch {
		                              case i : IllegalStateException => yieldFailure("Recursive property definition", v)
		                            }
	                            }
	                          }
	                        }
	                        case None => Failure(msg, Empty, Empty)
	                      }
	                    }
	                  }
                  }
                }
              }
            } else {
              env.getSourceExpression match {
                // SourceExpression set -> lookup in a navigation call
                case Full(se) => {
                  Full(env.lookupPropertyOnTypeFuzzy(se.getType, identifier, false))
                }
                // no SourceExpression set -> lookup on self
                case Failure(_, _, _) | Empty => {
                  env.getSelf.flatMap{self =>
		                Full(env.lookupVariableFuzzy(identifier):::env.lookupPropertyOnTypeFuzzy(self.getType, identifier, false))
		              }
                }
              }
            }
          } else {	// static property or tuple type; TODO: tuple type
            (_resolveType (name.take(name.size - 1).mkString("::"), false, env) (something)).flatMap {t =>
              if (!fuzzy) {
                env.lookupPropertyOnType(t.first, name.last, true).flatMap{p =>
                  Full(List(p))
                }
              } else {
                Full(env.lookupPropertyOnTypeFuzzy(t.first, name.last, true))
              }
            }
          }
        }
      }
    }
  }
  
  private def findTypeFuzzy(namespace : Namespace, identifier : String, env : Environment) : Box[List[Type]] = {
    Full(namespace.getOwnedType.filter(t => t.getName.startsWith(identifier))
    		:::(// add primitive types
			      oclLibrary.eContents.flatMap(_ match {
			        case t : Type => Full(t)
			        case unknown => Empty}
			      ).filter(t => t.getName.startsWith(identifier))
    		)
    )
  }
  
  private val _resolveOperation : Tuple4[String, Boolean, Environment, List[Parameter]] => Attributable ==> Box[List[Operation]] = {
    case (identifier, fuzzy, env, parameters) => {
      case something : AttributableEObject => {
        env.getSourceExpression match {
          case Full(se) => {	// this is an operation call with implicit source
            if (identifier.contains("::"))
              yieldFailure("Cannot call a static operation " + identifier + " in a chained feature call", something)
            else {
              // TODO: if not found look for defs; also for properties!
              if (!fuzzy) {
                isMultipleNavigationCall(something) match {
                  case Full(true) => {
                    se.getType match {
                      case c : CollectionType =>
                        lookupOperationOnType(c, identifier, parameters, something, Empty)
                      case notMultiple =>
                        lookupOperationOnType(se.withAsSet.getType, identifier, parameters, something, Full("implicit as Set()"))
                    }
                  }
                  case Full(false) => {
                    se.getType match {
                      case c : CollectionType => {
                        // TODO: insert implicit collect!
                        (!!(c.getElementType.lookupOperation(identifier, parameters.map(_.getType))) ?~
			              			("Cannot find operation " + identifier + " with parameters " + parameters + " on type " + 
			                    c.getElementType.getName)).flatMap{o => {
			                      resource.addWarning("implicit collect()", something)
			                      Full(List(o))}
                      		}
                      }
                      case notMultiple =>
                        lookupOperationOnType(notMultiple, identifier, parameters, something, Empty)
                    }
                  }
                  case Failure(msg, _, _) => Failure(msg, Empty, Empty)
                  case Empty => yieldFailure("Cannot determine sourceExpression.", something)
                }
              }
              else
                Full(se.getType.allOperations.filter(_.getName.startsWith(identifier)).toList)
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
        case i : ImplicitFeatureCallCS => isMultipleNavigationCall(i)
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
      	  case Full(w) => resource.addWarning(w, element)
      	  case Failure(_, _, _) | Empty => // ignore
      	}
      	Full(List(o))
      }
  }
  
  private val env : Attributable ==> Box[Environment] =
    childAttr {
      case child => {
        case null => Full(new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(None, Full(model.getRootNamespace), None, None, collection.mutable.Set(), Empty))
        case p@PackageDeclarationCS(_) => {
          env(p).flatMap{e =>
            Full(e.nestedEnvironmentNS(!! (p.getNamespace) ?~ ("Cannot find namespace for " + p)))
          }
        }
        case c@ClassifierContextDeclarationCS(typeCS, _) if child != typeCS => {
          env(c).flatMap{e =>
            if (typeCS.getTypeName.eIsProxy) 
              yieldFailure("Cannot find type " + typeCS + " in namespace " + e.getNamespace, typeCS)
            else {
              val variable = e.factory.createVariable("self", typeCS.getTypeName, null)
              Full(e.nestedEnvironmentSelf(Full(variable)))
            }
          }
        }
        case o@NavigationCallExp(source, featureCalls, op) if child != source => {
          if (child.prev == null) {
            env(o).flatMap{env =>
            	Full(env.nestedEnvironmentSourceExpression(computeOclExpression(source)))
            }
          } else {
            env(child.prev).flatMap{env =>
              Full(env.nestedEnvironmentSourceExpression(computeOclExpression(child.prev)))
            }
          }
        }
        // the arguments need 'self' to be set as source expression as they are not directly
        // part of the navigation call
        case i@ImplicitOperationCallCS(arguments) => {
          for (env <- env(i); self <- env.getSelf) yield {
            env.nestedEnvironmentSourceExpression(Full(env.factory.createVariableExp(self)))
          }
        }
        
        case unknown => env(unknown)
      }
    }
  
  private val computeConstraints : Attributable ==> Box[List[Constraint]] = {
    attr {
      case p@PackageDeclarationCS(contexts) => {
        Full(contexts.flatMap{c =>
          computeConstraints(c)
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
	        env(i).flatMap{env=>
	          env.getSelf.flatMap{self =>
            	val expression = env.factory.createExpressionInOcl(
			        	oclExpression.toString, oclExpressionEOcl, self, null)
			        var constraintName : String = ""
			        if (name != null)
			          constraintName = name.getSimpleName
			        val constraint = env.factory.createConstraint(
			        	constraintName, ConstraintKind.INVARIANT, expression, null, self.getType)
			        Full(constraint)
	          }
	        }
	  		}
  	  }
      case d@DefinitionExpCS(definitionExpPart, static) => {
        env(d).flatMap{env=>
          env.getSelf.flatMap{self =>
		        computeFeature(definitionExpPart).flatMap{feature =>
		          // also lookup other defs
		          if (env.lookupPropertyOnType(self.getType, feature._1.getName, static).isDefined) {
		        	  yieldFailure("Property " + feature._1 + " is already defined on " + 
		                   self.getType.getName, d)
		        	}
		          else {
			          feature._1.setStatic(static)
			          val expression = env.factory.createExpressionInOcl(
				        	feature._2.toString, feature._2, self, null)
				        val constraint = env.factory.createConstraint(
				        	"", ConstraintKind.DEFINITION, expression, feature._1, self.getType)
				        Full(constraint)
		          }
		        }
          }
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
        env(v).flatMap{env =>
          computeOclExpression(initialization).flatMap{oclExpressionEOcl =>
            env.getSelf.flatMap{self =>
              val property = PivotModelFactory.eINSTANCE.createProperty
				      property.setName(variableName.getSimpleName)
				      // TODO: does not work yet for nested collections
				      determineMultiplicities(oclExpressionEOcl.getType, property)
				      property.setType(oclExpressionEOcl.getType)
				      Full(property, oclExpressionEOcl)
            }
          }
        }
      }
    }
  }
  
  private val computeOclExpression : Attributable ==> Box[OclExpression] = {
    attr {
      case i@IntegerLiteralExpCS(integerLiteral) => {
        env(i).flatMap{e=>
          Full(e.factory.createIntegerLiteralExp(integerLiteral))
        } 
      }
      
      case r@RealLiteralExpCS(realLiteral) => {
        // TODO: parse Double, but use Float
        env(r).flatMap{e=>
          Full(e.factory.createRealLiteralExp(realLiteral.toFloat))
        }
      }
      
      case s@StringLiteralExpCS(stringLiteral) => {
        env(s).flatMap{e=>
          Full(e.factory.createStringLiteralExp(stringLiteral))
        }
      }
      
  	  case b@BooleanLiteralExpCS(booleanLiteral) => {
  	    env(b).flatMap{e=>
          Full(e.factory.createBooleanLiteralExp(booleanLiteral))
        }
  	  }
  	  
  	  case o@OperationCallBinaryExpCS(source, target, isMarkedPre, operationName) => {
  	    computeOclExpression(source).flatMap {sourceEOcl =>
  	      computeOclExpression(target).flatMap {targetEOcl =>
  	        env(o).flatMap{e=>
	  	        try {
			  	      Full(e.factory.createOperationCallExp(sourceEOcl, operationName, targetEOcl))
			  	    } catch {
			  	      case e : IllegalArgumentException => yieldFailure(e.getMessage, o)
			  	    }
  	        }
  	      }
  	    }  	    
  	  }
     
  	  case u@UnaryOperationCallExpCS(target, operationName) => {
  	    computeOclExpression(target).flatMap{targetEOcl =>
	  	    env(u).flatMap{e=>
	  	      try { 
		  	    	Full(e.factory.createOperationCallExp(targetEOcl, operationName))
		  	    } catch {
		  	    	case e : IllegalArgumentException => yieldFailure(e.getMessage, u)
		  	    }
	  	    }
  	    }
  	  }
     
  	  case l@LogicalNotOperationCallExpCS(target, operationName) => {
  	    computeOclExpression(target).flatMap{targetEOcl =>
  	    	env(l).flatMap{e=>
	  	      try { 
		  	    	Full(e.factory.createOperationCallExp(targetEOcl, operationName))
		  	    } catch {
		  	    	case e : IllegalArgumentException => yieldFailure(e.getMessage, l)
		  	    }
  	    	}
  	    }
  	  }
     
  	  case v@VariableOrStaticPropertyOrEnumLiteralExpCS() => {
  	  	env(v).flatMap{env =>
         	val typedElement = v.getTypedElement
         	if (typedElement.eIsProxy)
         		yieldFailure("Cannot find TypedElement for " + v, v)
         	else {
         		typedElement match {
         			case v : Variable => Full(env.factory.createVariableExp(v))
         			case p : Property => {
         				if (p.isStatic) {
         					// TODO: put this into the EssentialOclFactory
         					val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
         					pce.setReferredProperty(p)
         					pce.setSourceType(p.getOwningType)
         					pce.setSource(env.factory.createTypeLiteralExp(p.getOwningType.getQualifiedNameList))
         					pce.setOclLibrary(oclLibrary)
         					Full(pce)
         				}
         				else {
         				  env.getSelf.flatMap{self =>
         				    // TODO: put this into the EssentialOclFactory
         				  	val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
	         					pce.setReferredProperty(p)
	         					pce.setSourceType(self.getType)
	         					pce.setSource(env.factory.createVariableExp(self))
	         					pce.setOclLibrary(oclLibrary)
	         					Full(pce)
         				  }
         				}
         			}
         		}
         	}
  	  	}
  	  }
     
  	  case n@NavigationCallExp(source, featureCalls, op) => {
  	    env(n).flatMap{env =>
          computeOclExpression(featureCalls.last)
  	    }
  	  }
     
  	  case i@ImplicitPropertyCallCS(isMarkedPre) => {
        val property = i.getProperty
        if (property.eIsProxy) {
          val typeName = env(i).flatMap{env =>
           	env.getSourceExpression.flatMap{se =>
             	Full(se.getType)
           	}    
          } match {
            case Full(t) => t.getName
            case Failure(_, _, _) | Empty => "unknown type"
          }
          yieldFailure("Cannot find property on " + typeName + ".", i)
        }
        else {
          // TODO: put this into the EssentialOclFactory
 					val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
 					pce.setReferredProperty(property)
 					pce.setSourceType(property.getType)
 					// TODO: failure handling!
 					pce.setSource(env(i).open_!.getSourceExpression.open_!)
 					pce.setOclLibrary(oclLibrary)
 					Full(pce)
        }
      }
     	
      case i@ImplicitOperationCallCS(arguments) => {
        val operation = i.getOperationName
        if (operation.eIsProxy) {
          val typeName = env(i).flatMap{env =>
           	env.getSourceExpression.flatMap{se =>
             	Full(se.getType)
           	}    
          } match {
            case Full(t) => t.getName
            case Failure(_, _, _) | Empty => "unknown type"
          }
          yieldFailure("Cannot find operation on " + typeName +  ".", i)
        }
        else {
          // TODO: better solution for this part?
          val argumentsEOcl = arguments.map(computeOclExpression(_))
          argumentsEOcl.find(_ match {
            case Full(_) => false
            case Empty | Failure(_, _, _) => true
          }) match {
            case Some(f) => f.asInstanceOf[Box[FeatureCallExp]]
            case None => {
              // TODO: put this into the EssentialOclFactory
	   					val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
	   					oce.setReferredOperation(operation)
	   					oce.setSourceType(operation.getType)
	   					argumentsEOcl.foreach {arg =>
	   					  oce.getArgument.add(arg.open_!)
	   					}
	   					// TODO: failure handling!
	   					oce.setSource(env(i).open_!.getSourceExpression.open_!)
	   					oce.setOclLibrary(oclLibrary)
	   					Full(oce)
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
  
//  
//  private val env : String => Attributable ==> Environment =
//    paramAttr {
//      case identifier => {
//		    childAttr {
//		      case child => {
//		        // root node -> create a new and empty environment
//		        case null => new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(None, model.getRootNamespace, None, None, collection.mutable.Set())
//		        
//		        case p@PackageDeclarationCS(_, context) if child != context && child.prev != null => {
//		          env(identifier) (child.prev)
//		        }
//		        case p@PackageDeclarationCS(namespaces, _) => {
//		          val environment = env(p)
//		          val namespace = namespaces.find(_ == child).get
//		          val currentNS = environment.getNamespace.lookupNamespace(namespace) 
//		          if (currentNS == null) {
//		            resource.addError("Cannot find nested Namespace " + namespace + " in " + oldNS.getQualifiedName, packageName)
//		            environment
//		          } else
//		             environment.nestedEnvironment(currentNS)
//		        }
//		        
//		        /*
//		         * @see OperationContextDeclarationCS
//		         */
//		        case c@ClassifierContextDeclarationCS(typeName, _) if child != typeName && child.prev != null => {
//		          env(child.prev)
//		        }
//		        case c@ClassifierContextDeclarationCS(typeName, _) => {
//		          val environment = env(c)
//		          environment.lookup(pathName2String(typeName)) match {
//		            case Some(tipe) => {
//		              val self = environment.factory.createVariable("self", tipe, null)
//		              environment.nestedEnvironment(self)
//		            }
//		            case None => {
//		              resource.addError("Cannot find type " + typeName + " in Namespace " + environment.getNamespace.getQualifiedName + ".", typeName)
//		              environment.nestedEnvironment
//		            }
//		          }
//		        }
//		        
//		        /* 
//		         * Check if the child is the operation definition;
//		         * if yes, simply return the parent environment to avoid cycles.
//		         */
//		        case o@OperationContextDeclarationCS(operation, _) if child == operation => {
//		          env(o)
//		        }
//		        /*
//		         * For efficiency reasons: all pre- and post-conditions as well as body
//		         * declarations share the same environment.
//		         */
//		        case o@OperationContextDeclarationCS(_, _) if child.prev != null => {
//		          env(child.prev)
//		        }
//		        case o@OperationContextDeclarationCS(operation, _) => {
//		          val environment = env(o)
//		          try {
//			          operation match {
//			            case OperationCS(name, parameters, tipeName) => {
//			              val opName = pathName2String(name)
//			              val typeAndOperation = opName.splitAt(opName.size - 1)
//			              environment.lookup(typeAndOperation._1) match {
//			                case Some(tipe) => {
//			                  val self = environment.factory.createVariable("self", tipe, null)
//			                  val parameterTypes = parameters.map(p => computeType(p.getParameterType))
//			                  tipe.lookupOperation(typeAndOperation._2.first, parameterTypes) match {
//			                    case null => yieldError("Cannot find operation " + operation + " on type " +
//			                                              tipe.getQualifiedName + ".", operation, null)
//			                    case o : Operation => {
//			                      val result = environment.nestedEnvironment(self, o.getType)
//			                      if (tipeName != null) {
//			                        if (!computeType(tipeName).conformsTo(o.getType)) yieldError(
//			                        		"Cannot find operation " + operation + " with return type " +
//			                             computeType(tipeName).getQualifiedName +
//			                             " on type " + tipe.getQualifiedName + ".", operation, null)
//			                      }
//			                      parameters.zip(parameterTypes).foreach {p =>
//			                        val variable = environment.factory.createVariable(p._1.getParameterName.getSimpleName, p._2, null)
//			                        result.addExplicitVariable(variable)
//			                      }
//		                        result
//			                    }
//			                  }
//			                }
//			                case None => yieldError("Cannot find type " + typeAndOperation._1 + " in Namespace " + 
//			                                          environment.getNamespace + ".", name, null)
//			              }
//				          }
//			          }
//		          } catch {
//		            case e : OclStaticSemanticsException => throw new OclStaticSemanticsContextException
//		          }
//		        }
//		        
//		        case p@PostConditionDeclarationCS(_, _) => {
//		          val environment = env(p)
//		          environment.addExplicitVariable(environment.factory.createVariable("result", environment.getContext, null))
//		          environment
//		        }
//		        
//		        /*
//		         * For all other elements, simply forward to parent's environment. 
//		         */
//		        case unknown => env(unknown)
//		      }
//		    }
//      }
//    }
//  
//  private val computeOclExpression : Attributable ==> OclExpression =
//    attr {
//      case i@IntegerLiteralExpCS(integerLiteral) => {
//        env(i).factory.createIntegerLiteralExp(integerLiteral)
//      }
//      case r@RealLiteralExpCS(value) => {
//        // TODO: parse Double, but use Float
//        env(r).factory.createRealLiteralExp(value.toFloat)
//      }
//      case s@StringLiteralExpCS(value) => {
//        env(s).factory.createStringLiteralExp(value)
//      }
//  	  case b@BooleanLiteralExpCS(value) => {
//  	    env(b).factory.createBooleanLiteralExp(value)
//  	  }
//     
//  	  
//  	  case o@OperationCallBinaryExpCS(source, target, isMarkedPre, operationName) => {
//  	    val sourceEOcl = computeOclExpression(source)
//  	    val targetEOcl = computeOclExpression(target)
//  	    try {
//  	      env(o).factory.createOperationCallExp(sourceEOcl, operationName, targetEOcl)
//  	    } catch {
//  	      case e : IllegalArgumentException => yieldError(e.getMessage, o, e)
//  	    }
//  	  }
//     
//  	  case c@CollectionLiteralExpCS(collectionType, collectionLiteralParts) => {
//  	  	val collectionTypeEOcl = computeType(collectionType).asInstanceOf[CollectionType]
//  	    val collectionKind = collectionTypeEOcl.getKind
//  	    val collectionLiteralPartsEOcl = collectionLiteralParts.map(computeCollectionLiteralPart(_)).toArray
//  	    val result = env(c).factory.createCollectionLiteralExp(collectionKind, collectionLiteralPartsEOcl : _*)
//  	    if (!result.getType.conformsTo(collectionTypeEOcl)) {
//  	      yieldError("Collection type " + result.getType.getName + 
//                      " is not conformant with expected type " + 
//                      collectionTypeEOcl.getName + ".", collectionType, null)
//  	    }
//  	    result
//  	  }
//     
//  	  case u@UnaryOperationCallExpCS(target, operationName) => {
//  	    val targetEOcl = computeOclExpression(target)
//  	    try { 
//  	    	env(u).factory.createOperationCallExp(targetEOcl, operationName)
//  	    } catch {
//  	    	case e : IllegalArgumentException => yieldError(e.getMessage, u, e)
//  	    }
//  	  }
//     
//  	  case l@LogicalNotOperationCallExpCS(target, operationName) => {
//  	    val targetEOcl = computeOclExpression(target)
//  	    env(l).factory.createOperationCallExp(targetEOcl, operationName)
//  	  }
//     
//  	  case v@VariableOrStaticPropertyOrEnumLiteralExpCS(name) => {
//  	    val environment = env(v)
//  	    // the name does not contain "::" -> variable or property
//  	    if (name.getPathName == null) {
//  	      environment.lookupExplicitVariable(name.getSimpleName) match {
//  	        case Some(variable) => {
//  	          environment.factory.createVariableExp(variable)
//  	        }
//  	        case None => {
//              // can be a property
//              try {
//	              val selfExp = environment.factory.createVariableExp(environment.getSelf)
//	              environment.factory.createPropertyCallExp(selfExp, name.getSimpleName.getSimpleName, null)
//              } catch {
//  	            case e : EssentialOclFactoryException => yieldError(e.getMessage, name, e)
//  	          }
//  	        }
//  	      }
//  	    }
//        else {
//          yieldError("Cannot find something", name, null)
//        }
//  	  }
//      
//  	  case unknown => {
//  	    unknown match {
//  	      case u : AttributableEObject => resource.addError("unknown element", u)
//  	      case _ => //ignore
//  	    }
//  	    throw new OclStaticSemanticsException("unknown element: " + unknown)
//      }
//    }
//  
//  val computeConstraint : Attributable ==> Constraint =
//    attr {
//      case i@InvariantExpCS(name, oclExpression) => {
//  		if (oclExpression == null)
//  		  yieldError("Invariant has no expression.", i, null)
//        val oclExpressionEOcl = computeOclExpression(oclExpression)
//        oclExpressionEOcl.getType match {
//          case pt : PrimitiveType => if (pt.getKind != PrimitiveTypeKind.BOOLEAN) 
//        	  							yieldError("Invariants must result in boolean type and not " + 
//        	  										pt.getKind + ".", oclExpression, null)
//          case otherType => yieldError("Invariants must result in boolean type and not " + 
//                                         otherType.getName + ".", oclExpression, null)
//        }
//        val environment = env(i)
//        val expression = environment.factory.createExpressionInOcl(
//        	oclExpression.toString, oclExpressionEOcl, environment.getSelf, null)
//        var constraintName : String = ""
//        if (name != null)
//          constraintName = name.getSimpleName
//        val constraint = environment.factory.createConstraint(
//        	constraintName, ConstraintKind.INVARIANT, expression, null, environment.getSelf.getType)
//        constraint
//  	  }
//      
//      case d@DefinitionExpCS(name, oclExpression, variable, operation, static) => {
//        if (oclExpression == null)
//           yieldError("Definition has no expression.", d, null)
//        val environment = env(d)
//        val oclExpressionEOcl = computeOclExpression(oclExpression)
//        
//        if (environment.lookupPropertyOnType(variable, static).isDefined)
//          yieldError("Property " + variable + " is already defined on " + 
//                       environment.getSelf.getType, variable, null)
//        
//        val expression = environment.factory.createExpressionInOcl(
//        	oclExpression.toString, oclExpressionEOcl, environment.getSelf, null)
//        
//        val property = PivotModelFactory.eINSTANCE.createProperty
//        property.setName(variable.getSimpleName)
//        // TODO: does not work yet for nested collections
//        determineFeatureType(oclExpressionEOcl.getType, property)
//        property.setStatic(static)
//        
//        var constraintName : String = ""
//        if (name != null)
//          constraintName = name.getSimpleName
//        val constraint = environment.factory.createConstraint(
//        	constraintName, ConstraintKind.DEFINITION, expression, property, environment.getSelf.getType)
//        constraint
//      }
//           
//      case p@PrePostOrBodyDeclarationCS(name, oclExpression) => {
//        if (oclExpression == null)
//          throw new OclStaticSemanticsException("Pre, Post or Body definition is null")
//        val environment = env(p)
//        val oclExpressionEOcl = computeOclExpression(oclExpression)
//        // check whether the return type of the expression is OK
//        p match {
//          case _ : PreConditionDeclarationCS | _ : PostConditionDeclarationCS => {
//            if (!oclExpressionEOcl.getType.conformsTo(oclLibrary.getOclBoolean)) {
//              yieldError("Pre- or Postcondition has wrong type " + 
//                           oclExpressionEOcl.getType.getQualifiedName +
//            							"; expected 'Boolean'.", oclExpression, null)
//            }
//          }
//          case _ : BodyDeclarationCS => {
//            if (!oclExpressionEOcl.getType.conformsTo(environment.getContext)) {
//            	yieldError("Body definition has wrong type " + 
//                          oclExpressionEOcl.getType.getQualifiedName +
//            							"; expected " + environment.getContext + ".", oclExpression, null)
//            }
//          }
//        }
//        
//        val expression = environment.factory.createExpressionInOcl(
//        	oclExpression.toString, oclExpressionEOcl, environment.getSelf, null)
//        var constraintName : String = ""
//        if (name != null)
//          constraintName = name.getSimpleName
//        val constraint = environment.factory.createConstraint(
//        	constraintName, p match {
//        	  case p : PreConditionDeclarationCS => ConstraintKind.PRECONDITION
//        	  case p : PostConditionDeclarationCS => ConstraintKind.POSTCONDITION
//        	  case b : BodyDeclarationCS => ConstraintKind.BODY
//        	}, expression, null, environment.getSelf.getType)
//        constraint
//      }
//      
//      case unknown => {
//  	    unknown match {
//  	      case u : AttributableEObject => resource.addError("unknown element", u)
//  	      case _ => //ignore
//  	    }
//  	    throw new OclStaticSemanticsException("unknown element: " + unknown)
//      }
//    }
//  
//  val computeConstraints : Attributable ==> List[Constraint] =
//    attr {
//      case PackageDeclarationCS(_, contextDeclarations) => {
//        val result = new collection.mutable.ListBuffer[Constraint]
//        contextDeclarations.foreach{ cd =>
//          try {
//            result ++= computeConstraints(cd)
//	  	  } catch {
//	  	    case e : OclStaticSemanticsException => // ignore 
//	  	  }
//        }
//        result.toList
//  	  }
//  	  case c@ClassifierContextDeclarationCS(_, invAndDefs) => {
//  	    import collection.mutable.Set
//  	    val result = new collection.mutable.ListBuffer[Constraint]
//  	    // TODO: use Feature instead of Type to determine if a Property was static and to differentiate between Operations
//        val definitions = new collection.mutable.HashMap[String, Set[List[Type]]] with collection.mutable.MultiMap[String, List[Type]]
//  	    invAndDefs.foreach{ iad =>
//  	      try {
//  	    	val constraint = computeConstraint(iad)
//  	    	// test if there are definitions of properties with the same name
//  	    	if (constraint.getDefinedFeature != null) {
//  	    	  definitions.get(constraint.getDefinedFeature.getName) match {
//  	    	    case Some(typeSet) => constraint.getDefinedFeature match {
//  	    	      case p : Property => yieldError("Cannot define properties with same name: " + 
//                                                	constraint.getDefinedFeature.getName, iad, null)
//  	    	      case o : Operation => {
//  	    	        typeSet.foreach { types =>
////  	    	          if (types == o.getInputParameter.map(_.getType))
////  	    	        	yieldError("Cannot define operations with same name: " + 
////  	    	        				constraint.getDefinedFeature.getName, iad, null)
////  	    	          definitions.add(constraint.getDefinedFeature.getName, types)
//  	    	        }
//  	    	      }
//  	    	    }
//  	    	    case None => definitions.add(constraint.getDefinedFeature.getName, Nil)
//  	    	  }
//  	    	}
//  	    	result += constraint
//  	      } catch {
//  	        case e : OclStaticSemanticsContextException => throw new OclStaticSemanticsException(e)
//  	      	case e : OclStaticSemanticsException => // ignore 
//  	      }
//        }
//  	    result.toList
//  	  }
//      case o@OperationContextDeclarationCS(_, prePostOrBodyDecl) => {
//        val result = new collection.mutable.ListBuffer[Constraint]
//        // TODO: addWarning for multiple body definitions
//        prePostOrBodyDecl.foreach {ppbd =>
//          try {
//            result += computeConstraint(ppbd)
//          } catch {
//            case e : OclStaticSemanticsContextException => throw new OclStaticSemanticsException(e)
//            case e : OclStaticSemanticsException => // ignore
//          }
//        }
//        result.toList
//      }
//    }
//  
//  private val computeCollectionLiteralPart : Attributable ==> CollectionLiteralPart =
//	attr {
//	  case c@CollectionRangeCS(from, to) => {
//  	    val fromEOcl = computeOclExpression(from)
//  	    val toEOcl = computeOclExpression(to)
//  	    env(c).factory.createCollectionRange(fromEOcl, toEOcl)
//  	  }
//	  case c@CollectionLiteralPartsOclExpCS(oclExpression) => {
//  	    val oclExpressionEOcl = computeOclExpression(oclExpression)
//  	    env(c).factory.createCollectionItem(oclExpressionEOcl)
//  	}
//	  case unknown => {
//	    unknown match {
//	      case u : AttributableEObject => resource.addError("Unknown collection literal part.", u)
//	      case _ => //ignore
//	    }
//	    throw new OclStaticSemanticsException("Unknown collection literal part: " + unknown)
//    }
//  }
//  
//  /**
//   * Compute the PivotModel Type for a given TypeCS.
//   */
//  private val computeType : Attributable ==> Type =
//    attr {
//      case c@CollectionTypeIdentifierCS(genericType, collectionTypeName) => {
//		    val genType = if (genericType != null) computeType(genericType) else null
//		    collectionTypeName match {
//		      case "Set" => {
//		        if(genType != null)
//		          oclLibrary.getSetType(genType)
//		        else
//		          oclLibrary.getOclSet
//		      }
//		      case "Bag" =>  {
//		        if(genType != null)
//		          oclLibrary.getBagType(genType)
//		        else
//		          oclLibrary.getOclBag
//		      }
//		      case "Sequence" =>  {
//		        if(genType != null)
//		          oclLibrary.getSequenceType(genType)
//		        else
//		          oclLibrary.getOclSequence
//		      }
//		      case "Collection" =>  {
//		        if(genType != null)
//		          oclLibrary.getCollectionType(genType)
//		        else
//		          oclLibrary.getOclCollection
//		      }
//		      case "OrderedSet" =>  {
//		        if(genType != null)
//		          oclLibrary.getOrderedSetType(genType)
//		        else
//		          oclLibrary.getOclOrderedSet
//		      }
//		      case unknown => yieldError("Unknown Collection type " + unknown + ".", c, null)
//		   }
//    } 
//	  case t@TupleTypeCS(variableDeclarationList) => {
//	    null
//	  }
//	  case t@TypePathNameCS(pathName) => {
//	     env(t).lookup(pathName2String(pathName)) match {
//	       case Some(tipe) => tipe
//	       case None => yieldError("Cannot find type " + pathName + " in namespace " + env(t).getNamespace.getQualifiedName, pathName, null)
//	     }
//	  }
//   
//    // TODO: for testing purposes only:
//	  case p@PathNameCS(_, _) => {
//	    env(p).lookup(pathName2String(p)) match {
//	       case Some(tipe) => tipe
//	       case None => yieldError("Cannot find type " + p + " in namespace " + env(p).getNamespace.getQualifiedName, p, null)
//	     }
//	  }
//   
//	  case unknown => {
//	    unknown match {
//	      case u : AttributableEObject => resource.addError("Unknown type.", u)
//	      case _ => //ignore
//	    }
//	    throw new OclStaticSemanticsException("Unknown type: " + unknown)
//    }
//  }
//  
//  /**
//   * Returnes a List of String that the given PathNameCS represents.
//   */
//  def pathName2String(pathName : PathNameCS) : List[String] = {
//    val result = new collection.mutable.ListBuffer[String]()
//    if (pathName != null) {
//      if (pathName.getSimpleName != null)
//        result += pathName.getSimpleName.getSimpleName
//      result ++= pathName2String(pathName.getPathName)
//      }
//    result.toList
//  }
//  
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
    env(container).flatMap{e=>
	    _resolveNamespace(identifier, fuzzy, e) (container)
    } match {
      case Full(namespaceList) => namespaceList
      case Failure(msg, _, _) => {
        //resource.addError(msg, container)
        List()
      }
      case Empty => List()
    }
  }
  
  def resolveType(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Type] = {
    env(container).flatMap{e =>
      _resolveType(identifier, fuzzy, e) (container)
    } match {
      case Full(typeList) => typeList
      case Failure(msg, _, _) => {
        //resource.addError(msg, container)
        List()
      }
      case Empty => List()
    }
  }
  
  def resolveTypedElement(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[TypedElement] = {
    env(container).flatMap{e =>
      _resolveTypedElement(identifier, fuzzy, e) (container)
    } match {
      case Full(typedElementList) => typedElementList
      case Failure(_, _, _) | Empty => List()
    }
  }
  
  def resolveProperty(identifier : String, fuzzy : Boolean, container : EObject) : java.util.List[Property] = {
    // TODO: resolve properties on their own
    
    val e : Box[Environment] = container match {
      case i@ImplicitFeatureCallCS() => {
        i.eContainer match {
		      case n@NavigationCallExp(source, featureCalls, op) => {
		        env(featureCalls.dropWhile(fc => fc != i).first)
		      }
	      }
      }
      case doesntMatter => env(container)
    }
    
    e.flatMap{e =>
      _resolveTypedElement(identifier, fuzzy, e) (container)
    } match {
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
    val parametersEOcl = parameters.flatMap{p =>
      computeOclExpression(p).flatMap{parameterEOcl => 
      	val parameter = PivotModelFactory.eINSTANCE.createParameter
      	parameter.setName(parameterEOcl.getName)
      	determineMultiplicities(parameterEOcl.getType, parameter)
      	parameterEOcl.getType match {
      	  case c : CollectionType => parameter.setType(c.getElementType)
      	  case g : GenericType => parameter.setGenericType(g)
      	  case otherType : Type => parameter.setType(otherType)
      	}
      	parameter.setKind(ParameterDirectionKind.IN)
      	Full(parameter)
      }
    }
    
    val e : Box[Environment] = container match {
      case i@ImplicitFeatureCallCS() => {
        i.eContainer match {
		      case n@NavigationCallExp(source, featureCalls, op) => {
		        env(featureCalls.dropWhile(fc => fc != i).first)
		      }
	      }
      }
      case doesntMatter => env(container)
    }
    
    e.flatMap{e =>
      _resolveOperation(identifier, fuzzy, e, parametersEOcl) (container)
    } match {
      case Full(operationList) => operationList
      case Failure(msg, _, _) => println(msg); List()
      case Empty => List()
    }
  }
}
