package tudresden.ocl20.pivot.language.ocl.staticsemantics.impl

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


trait OclAttributesImpl extends OclAttributes {selfType : OclStaticSemantics =>

  abstract override def __isMultipleNavigationCall : Attributable ==> Box[Boolean] = {
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
        case _ => super.__isMultipleNavigationCall(child)
      }
    }
  }
  
  abstract override def __namespace : Attributable ==> Box[Namespace] = { 
    childAttr {
      case child => {
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
        case _ => super.__namespace(child)
      }
    }
  }
  
  abstract override def __lastNamespace : Attributable ==> Box[Namespace] = {
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
      case unknown => super.__lastNamespace(unknown)
    }
  }
  
  abstract override def __self : Attributable ==> Box[Variable] = {
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

        case _ => super.__self(child)
      }
    }
  }
  
  abstract override def __context : Attributable ==> Box[ConstrainableElement] = {
    childAttr {
      case child => {
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
        case _ => super.__context(child)
      }
    }
  }
  
  abstract override def __variables : Attributable ==> Box[Tuple2[List[Variable], List[Variable]]] = {
    childAttr {
      case child : AttributableEObject => {
        
        case c@ClassifierContextDeclarationCS(typeCS, _) if child != typeCS =>
          for (self <- child->self) yield (List(self), List())
        
        case o@OperationContextDeclarationCS(operation, _) if child != operation => {
          (child->self).flatMap{self =>
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
				        Full(List(self), newVars)
				      }
		        }
          }
        }
        
        case a : AttributeContextDeclarationCS =>
          for (self <- child->self) yield (List(self), List())
        
        case p : PostConditionDeclarationCS => {
          (p->context).flatMap {context =>
	          context match {
	            case o : Operation => {
	              (p->variables).flatMap{case (implicitVariables, explicitVariables) =>
		              val result = ExpressionsFactory.INSTANCE.createVariable
				          result.setName("result")
				          result.setType(o.getType)
				          Full((implicitVariables, result::(explicitVariables)))
			          }
	            }
	            case other => yieldFailure("Post conditions are only allowed on operations.", p)
	          }
            
          }
        }
        
        case d@DefinitionExpOperationCS(operation, _) => {
          (d->variables).flatMap{case (implicitVariables, explicitVariables) =>
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
				        Full(implicitVariables, newVars:::explicitVariables)
				      }
		        }
          }
        }
        
        case l@LetExpCS(variableDeclarations, _) if !variableDeclarations.contains(child.getEObject) => {
          val last = variableDeclarations.last
          (last->variables).flatMap{case (implicitVariables, explicitVariables) =>
            (last.getInitialization->computeOclExpression).flatMap{initExp =>
	            checkVariableDeclarationType(last).flatMap{tipe =>
	              Full(implicitVariables, factory.createVariable(last.getVariableName.getSimpleName, tipe, initExp)::explicitVariables)
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
            ((prev)->variables).flatMap{ case (implicitVariables, explicitVariables) =>
              (prev.getInitialization->computeOclExpression).flatMap{initExp =>
		            checkVariableDeclarationType(prev).flatMap{tipe =>
		              Full(implicitVariables, factory.createVariable(prev.getVariableName.getSimpleName, tipe, initExp)::explicitVariables)
		            }
		          }
            }
          }
        }
        
        case i@IteratorExpCS(iteratorVariables, _, _) => {
        	(i->sourceExpression).flatMap{se =>
	          (i->variables).flatMap{case (implicitVariables, explicitVariables) =>
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
		              // implicit variables
		            	if (iteratorVariables.isEmpty)
			            	(factory.createVariable("$implicitVariable" + ImplicitVariableNumberGenerator.getNumber + "$", determineTypeOf(se), null))::implicitVariables
			            else
		                implicitVariables
		              ,
		              // explicit variables
		              if (iteratorVariables.size == 2)
	                  iteratorVariablesEOcl.first::iteratorVariablesEOcl.get(1)::explicitVariables
	                else
	                  if (iteratorVariables.isEmpty)
	                  	explicitVariables
                    else
                      iteratorVariablesEOcl.first::explicitVariables
			          )
	          }
	        }
        }
        
        case i@IterateExpCS(iteratorVariable, resultVariable, _) if child != resultVariable => {
          (i->variables).flatMap{case (implicitVariables, explicitVariables) =>
            (i->sourceExpression).flatMap{se =>
              if (iteratorVariable != null) {
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
              } else {
              	Full(factory.createVariable("$implicitIterateVar" + ImplicitVariableNumberGenerator.getNumber + "$", determineTypeOf(se), null))
              }
            }.flatMap{iv =>
              (resultVariable.getInitialization->computeOclExpression).flatMap{initExp =>
	              checkVariableDeclarationType(resultVariable).flatMap{tipe =>
	              	Full(
	              	  if (iteratorVariable != null)
	              	  	implicitVariables
	              	  else
	              	  	iv::implicitVariables
                    ,
                    if (iteratorVariable != null)
                    	iv::factory.createVariable(resultVariable.getVariableName.getSimpleName, tipe, initExp)::explicitVariables
                    else
                      factory.createVariable(resultVariable.getVariableName.getSimpleName, tipe, initExp)::explicitVariables
	              	)
	              }
              }
            }
          }
        }
        
        case _ => super.__variables(child)
      }
    }
  }
  
  abstract override def __oclType : Attributable ==> Box[Type] = {
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
      case t@TupleTypeCS(variableDeclarationList) => {
        val properties = variableDeclarationList.getVariableDeclarations.flatMap{vd =>
          (vd->computeFeature).flatMap{case (feature, _) =>
            Full(feature.asInstanceOf[Property])
          }
        }
        if (variableDeclarationList.getVariableDeclarations.size != properties.size)
          Empty
        else
        	Full(oclLibrary.makeTupleType(properties))
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
      case RealLiteralExpCS(_, _, _) => {
        Full(oclLibrary.getOclReal)
      }
      case BooleanLiteralExpCS(_) => {
        Full(oclLibrary.getOclBoolean)
      }
      case StringLiteralExpCS(_) => {
        Full(oclLibrary.getOclString)
      }
      case unknown => super.__oclType(unknown)
    }
  }
  
  abstract override def __sourceExpression : Attributable ==> Box[OclExpression] = {
    childAttr {
      case child : AttributableEObject => {
        case c : ContextDeclarationCS =>  for (self <- child->self) yield factory.createVariableExp(self)
        case n@NavigationCallExp(source, featureCalls, op) if child != source => {
          if (child.isFirst)
            source->computeOclExpression
          else
            child.prev->computeOclExpression
        }
        case o@OperationCallBaseExpCS(arguments, _) if arguments.contains(child.getEObject) => {
          (child->variables).flatMap{case (implicitVariables, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariables.first))
          }
        }
        case i@IteratorExpCS(iteratorVariables, bodyExpression, _) if child == bodyExpression => {
          (child->variables).flatMap{case (implicitVariables, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariables.first))
          }
        }
        case i@IterateExpCS(_, resultVariable, bodyExpression) if child == bodyExpression || child == resultVariable => {
          (child->variables).flatMap{case (implicitVariables, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariables.first))
          }
        }
        case _ => super.__sourceExpression(child)
      } 
    }
  }
  
  abstract override def __isInPostCondition : Attributable ==> Boolean = {
    childAttr {
      case child => {
        case p : PostConditionDeclarationCS => true
        case _ => super.__isInPostCondition(child)
      }
    }
  }
}
