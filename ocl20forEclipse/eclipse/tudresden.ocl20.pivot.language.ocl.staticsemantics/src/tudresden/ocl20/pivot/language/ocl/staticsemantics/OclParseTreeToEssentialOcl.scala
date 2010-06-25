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


trait OclParseTreeToEssentialOcl { selfType : OclStaticSemantics =>

    protected val computeConstraints : Attributable ==> Box[List[Constraint]] = {
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
  
  protected val computeConstraint : Attributable ==> Box[Constraint] = {
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
  
  protected val computeFeature : Attributable ==> Box[Tuple2[Feature, OclExpression]] = {
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
      
      case v@VariableDeclarationWithoutInitCS(variableName, typeName) => {
        (typeName->oclType).flatMap{tipe =>
          val property = PivotModelFactory.eINSTANCE.createProperty
			    property.setName(variableName.getSimpleName)
			    // TODO: does not work yet for nested collections
			    determineMultiplicities(tipe, property)
			    Full(property, null)
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
  
  protected val computeOclExpression : Attributable ==> Box[OclExpression] = {
    attr {
      case i@IntegerLiteralExpCS(integerLiteral) => {
        Full(factory.createIntegerLiteralExp(integerLiteral))
      }
      
      case r@RealLiteralExpCS(intValue, realValue, navigationOperator) => {
        import java.lang.Math._
        if (navigationOperator == "->") 
          yieldFailure("Cannot use '->' in a real expression.", r)
        else {
          Full(factory.createRealLiteralExp(intValue  + (realValue / pow(10, ("" + realValue).length)).toFloat))
        }
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
     
  	  case t@TupleLiteralExpCS(variableDeclarationList) => {
  	    val properties = variableDeclarationList.getVariableDeclarations.flatMap(vd => vd->computeFeature)
  	    if (properties.size != variableDeclarationList.getVariableDeclarations.size)
          Empty
       else {
         val tupleLiteralParts = properties.map{case(property, initExpression) =>
           val tupleLiteralPart = ExpressionsFactory.INSTANCE.createTupleLiteralPart
           tupleLiteralPart.setProperty(property.asInstanceOf[Property])
           tupleLiteralPart.setValue(initExpression)
           tupleLiteralPart
         }
         Full(factory.createTupleLiteralExp(tupleLiteralParts.toArray : _*))
       }
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
       		Empty
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
	        if (property.eIsProxy) 
           Empty
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
	        if (operation.eIsProxy) 
           Empty
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
	                  iteratorVar.setName("$implicitCollect" + ImplicitVariableNumberGenerator.getNumber + "$")
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
				   					  		    factory.createVariableExp(
				   					  		      factory.createVariable(v.getReferredVariable.getName, 
                                             					 v.getReferredVariable.getType, 
                                             					 v.getReferredVariable.getInitExpression))
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
      
      case i@IterateExpCS(_, _, bodyExpression) => {
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
        (oclExpression->variables).flatMap{ case (_, explicitVariables) =>
          explicitVariables.take(variableDeclarations.size).foldRight (oclExpression->computeOclExpression) { (explicitVariable, expression) =>
            expression.flatMap{expression =>
              Full(factory.createLetExp(explicitVariable, expression))
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
          Empty
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
      
      case s@StaticOperationCallExpCS(typeName, arguments) => {
        val operation = s.getOperationName
        if (operation.eIsProxy)
          Empty
        else {
          val argumentsEOcl = arguments.flatMap(arg => arg->computeOclExpression)
          if (argumentsEOcl.size != arguments.size)
            Empty
          else {
            (typeName->oclType).flatMap{tipe =>
              val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
              oce.setSourceType(operation.getOwningType)
              oce.setSource(factory.createTypeLiteralExp(operation.getOwningType.getQualifiedNameList))
              oce.setReferredOperation(operation)
              oce.getArgument.addAll(argumentsEOcl)
              oce.setOclLibrary(oclLibrary)
              Full(oce)
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
  
}
