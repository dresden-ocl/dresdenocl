package org.dresdenocl.language.ocl.staticsemantics.impl

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
import org.dresdenocl.language.ocl.staticsemantics._
import org.dresdenocl.language.ocl.staticsemantics.Box._

import org.dresdenocl.language.ocl.resource.ocl.mopp._

import org.kiama._
import attribution._
import Attribution._


import AttributableEObject._


trait OclAttributesImpl extends OclAttributes {selfType : OclStaticSemantics =>

  abstract override def __isMultipleNavigationCall : Attributable ==> Box[Boolean] = {
  	childAttr {
      case child : AttributableEObject => {
        case n : NavigationCallExp if child != n.getSource => {
          n.getFeatureCalls.zip(n.getNavigationOperator).find(child == _._1) match {
            case Some(fc) => Full(fc._2 == "->")
            case None => Empty
          }
        }
        // arguments of a chained operation call are not considered to be multiple
        case i : ImplicitOperationCallCS if i.getArguments.contains(child.eObject) => {
          Full(false)
        }
        case i : IteratorExpCS if child == i.getBodyExpression => {
          Full(false)
        }
        case i : IterateExpCS if child == i.getBodyExpression => {
          Full(false)
        }
        case i : ImplicitFeatureCallCS => isMultipleNavigationCall(i)
        case _ => super.__isMultipleNavigationCall(child)
      }
    }
  }
  
  abstract override def __namespace : Attributable ==> Box[Namespace] = { 
    childAttr {
      case child => {
        case p : PackageDeclarationWithNamespaceCS if child != p.getNestedNamespace => {
          lastNamespace(p.getNestedNamespace)
        }
        case p : PackageDeclarationNestedNamespaceCS => {
          val namespace = p.getNamespace
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
      case p : PackageDeclarationNestedNamespaceCS => {
        if (p.getNestedNamespace == null) {
          val namespace = p.getNamespace
          if (namespace.eIsProxy)
            Empty
          else
            Full(namespace)
        } else
          lastNamespace(p.getNestedNamespace)
      }
      case unknown => super.__lastNamespace(unknown)
    }
  }
  
  abstract override def __self : Attributable ==> Box[Variable] = {
    childAttr {
      case child => {
        
        case c : ClassifierContextDeclarationCS if child != c.getTypeName => {
          oclType(c.getTypeName).flatMap{selfType =>
	          if (selfType.eIsProxy)
	            Empty
	          else
	            Full(factory.createVariable("self", selfType, null))
          }
        }
        
        case o : OperationContextDeclarationCS if child != o.getOperation => {
          val op = o.getOperation.getOperation
          if (op.eIsProxy)
            Empty
          else {
            // get the operations owning type; if not present, the operation is defined
            // in a "def" -> get the owning type from the Map that keeps track of
            // defined operations
            var owningType = op.getOwningType
            if (owningType == null)
              owningType = definedOperationsType.get(op)
            Full(factory.createVariable("self", owningType, null))
          }
        }
        
        case a : AttributeContextDeclarationCS if child != a.getTypeName => {
          Full(factory.createVariable("self", a.getTypeName.getNamedElement.getOwner.asInstanceOf[Type], null))
        }

        case _ => super.__self(child)
      }
    }
  }
  
  abstract override def __context : Attributable ==> Box[ConstrainableElement] = {
    childAttr {
      case child => {
        case c : ClassifierContextDeclarationCS => oclType(c.getTypeName)
        case o : OperationContextDeclarationCS => {
          val op = o.getOperation.getOperation
          if (op.eIsProxy)
            Empty
          else
            Full(op)
        }
        case a : AttributeContextDeclarationCS => {
          val property = a.getTypeName.getNamedElement
          if (property.eIsProxy)
            Empty
          else if (property.isInstanceOf[Property])
            Full(property.asInstanceOf[Property])
          else
            Empty
        }
        case _ => super.__context(child)
      }
    }
  }
  
  abstract override def __variables : Attributable ==> Box[Tuple2[List[Variable], List[Variable]]] = {
    childAttr {
      case child : AttributableEObject => {
        
        case c : ClassifierContextDeclarationCS if child != c.getTypeName =>
          for (self <- self(child)) yield (List(self), List())
        
        case o : OperationContextDeclarationCS if child != o.getOperation => {
          self(child).flatMap{self =>
	          val parametersEOcl = o.getOperation.getParameters.map(p => p.getParameter)
				    parametersEOcl.find(_.eIsProxy) match {
				      case Some(couldNotResolve) => Empty
				      case None => {
				        val newVars = parametersEOcl.map{param =>
					        val variable = ExpressionsFactory.INSTANCE.createVariable
					        variable.setName(param.getName)
					        variable.setRepresentedParameter(param)
					        variable
				        }.toList
				        Full((List(self), newVars))
				      }
		        }
          }
        }
        
        case a : AttributeContextDeclarationCS =>
          for (self <- self(child)) yield (List(self), List())
        
        case p : PostConditionDeclarationCS => {
          context(p).flatMap {context =>
	          context match {
	            case o : Operation => {
	              variables(p).flatMap{case (implicitVariables, explicitVariables) =>
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
        
        case d : DefinitionExpOperationCS => {
          variables(d).flatMap{case (implicitVariables, explicitVariables) =>
	          val parametersEOcl = d.getOperation.getParameters.map(p => p.getParameter)
				    parametersEOcl.find(_.eIsProxy) match {
				      case Some(couldNotResolve) => Empty
				      case None => {
				        val newVars = parametersEOcl.map{param =>
					        val variable = ExpressionsFactory.INSTANCE.createVariable
					        variable.setName(param.getName)
					        variable.setRepresentedParameter(param)
					        variable
				        }.toList
				        Full(implicitVariables, newVars:::explicitVariables)
				      }
		        }
          }
        }
        
        case l : LetExpCS if !l.getVariableDeclarations.contains(child.eObject) => {
          val last = l.getVariableDeclarations.last
          variables(last).flatMap{case (implicitVariables, explicitVariables) =>
            computeOclExpression(last.getInitialization).flatMap{initExp =>
	            checkVariableDeclarationType(last).flatMap{tipe =>
	              Full(implicitVariables, factory.createVariable(last.getVariableName.getSimpleName, tipe, initExp)::explicitVariables)
	            }
	          }
          }
        }
        
        // a let exp with multiple variables can have references to previously defined vars
        case l : LetExpCS => {
          if (child.isFirst)
            variables(l)
          else {
            val prev = child.prev.asInstanceOf[VariableDeclarationWithInitCS]
            variables(prev).flatMap{ case (implicitVariables, explicitVariables) =>
              computeOclExpression(prev.getInitialization).flatMap{initExp =>
		            checkVariableDeclarationType(prev).flatMap{tipe =>
		              Full(implicitVariables, factory.createVariable(prev.getVariableName.getSimpleName, tipe, initExp)::explicitVariables)
		            }
		          }
            }
          }
        }
        
        case i : IteratorExpCS => {
        	sourceExpression(i).flatMap{se =>
	          variables(i).flatMap{case (implicitVariables, explicitVariables) =>
	            val iteratorVariablesEOcl = i.getIteratorVariables.flatMap{iv =>
	              if (iv.getTypeName != null) {
		              oclType(iv.getTypeName).flatMap{tipe =>
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
	            if (iteratorVariablesEOcl.size != i.getIteratorVariables.size)
                Empty
              else
		            Full((
		              // implicit variables
		            	if (i.getIteratorVariables.isEmpty)
			            	(factory.createVariable("$implicitVariable" + ImplicitVariableNumberGenerator.getNumber + "$", determineTypeOf(se), null))::implicitVariables
			            else
		                implicitVariables
		              ,
		              // explicit variables
		              if (i.getIteratorVariables.size == 2)
	                  iteratorVariablesEOcl.first::iteratorVariablesEOcl.get(1)::explicitVariables
	                else
	                  if (i.getIteratorVariables.isEmpty)
	                  	explicitVariables
                    else
                      iteratorVariablesEOcl.first::explicitVariables
			          ))
	          }
	        }
        }
        
        case i: IterateExpCS if child != i.getResultVariable => {
          variables(i).flatMap{case (implicitVariables, explicitVariables) =>
            sourceExpression(i).flatMap{se =>
              if (i.getIteratorVariable != null) {
	              if (i.getIteratorVariable.getTypeName != null) {
		              oclType(i.getIteratorVariable.getTypeName).flatMap{tipe =>
			              if (!tipe.conformsTo(determineTypeOf(se)))
			              	yieldFailure("Expected type " + tipe.getName + ", but found " + 
	                                determineTypeOf(se).getName, i.getIteratorVariable)
			              else
			              	Full(factory.createVariable(i.getIteratorVariable.getVariableName.getSimpleName, tipe, null))
			            }
	              }
	              else
	                Full(factory.createVariable(i.getIteratorVariable.getVariableName.getSimpleName, determineTypeOf(se), null))
              } else {
              	Full(factory.createVariable("$implicitIterateVar" + ImplicitVariableNumberGenerator.getNumber + "$", determineTypeOf(se), null))
              }
            }.flatMap{iv =>
              computeOclExpression(i.getResultVariable.getInitialization).flatMap{initExp =>
	              checkVariableDeclarationType(i.getResultVariable).flatMap{tipe =>
	              	Full((
	              	  if (i.getIteratorVariable != null)
	              	  	implicitVariables
	              	  else
	              	  	iv::implicitVariables
                    ,
                    if (i.getIteratorVariable != null)
                    	iv::factory.createVariable(i.getResultVariable.getVariableName.getSimpleName, tipe, initExp)::explicitVariables
                    else
                      factory.createVariable(i.getResultVariable.getVariableName.getSimpleName, tipe, initExp)::explicitVariables
	              	))
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
      case m : ModelElementCS => {
       oclType(m.getPathName)
      }
      case p : PathNamePathCS => {
       oclType(p.getPathName.last)
      }
      case s : PathNameSimpleCS => {
        val `type` = s.getNamedElement
        if (`type`.eIsProxy)
          Empty
        else
          `type` match {
            case t : TypedElement => Full(t.getType)
            case t : Type => Full(t)
          }
      }
      case n : NamedElementCS => {
        val `type` = n.getNamedElement
        if (`type`.eIsProxy)
          Empty
        else
          `type` match {
            case v : Variable => Full(v.getType)
            case t : TypedElement => Full(t.getType)
            case t : Type => Full(t)
            
          }
      }
      case t : TypeModelElementCS => {
        oclType(t.getModelElement)
      }
      case t : TupleTypeCS => {
        val properties = t.getVariableDeclarationList.getVariableDeclarations.flatMap{vd =>
          computeFeature(vd).flatMap{case (feature, _) =>
            Full(feature.asInstanceOf[Property])
          }
        }
        if (t.getVariableDeclarationList.getVariableDeclarations.size != properties.size)
          Empty
        else
        	Full(oclLibrary.makeTupleType(properties))
      }
      case c : CollectionTypeIdentifierCS => {
        val `type` = c.getTypeName
        if (`type`.eIsProxy)
          Empty
        else {
          if (c.getGenericType != null) {
            oclType(c.getGenericType).flatMap{gt =>
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
      case v : VariableDeclarationWithInitCS => {
        // TODO: should this always have a type?
        if (v.getTypeName != null)
          oclType(v.getTypeName)
        else
          for (initExp <- computeOclExpression(v.getInitialization);
               `type` <- oclType(initExp)) 
            yield `type`
      }
      case v : VariableDeclarationWithoutInitCS => {
        oclType(v.getTypeName)
      }
      case c : CollectionLiteralExpCS => {
        oclType(c.getCollectionType)
      }
      case c : CollectionLiteralPartsOclExpCS => {
        oclType(c.getOclExpression)
      }
      case c : CollectionRangeCS => {
        for (fromType <- oclType(c.getFrom);
             toType <- oclType(c.getTo)
        		 if (fromType.conformsTo(toType)))
	        yield {
	          fromType.commonSuperType(toType)
	        }
      }
      case n : NavigationCallExp => {
        if (n.getFeatureCalls.isEmpty)
          oclType(n.getSource)
        else
          oclType(n.getFeatureCalls.last)
      }
      case i : ImplicitPropertyCallCS => {
        val property = i.getProperty
        if (property.eIsProxy)
          Empty
        else
          Full(property.getType)
      }
      case i : ImplicitOperationCallCS => {
        val operation = i.getOperationName
        if (operation.eIsProxy)
          Empty
        else
          Full(operation.getType)
      }
      case o : OperationCallBinaryExpCS => {
        for (sourceType <- oclType(o.getSource);
        		 targetType <- oclType(o.getTarget);
        		 operation <- !!(sourceType.lookupOperation(o.getOperationName, List(targetType))))
          yield {
        		operation.getType
        	}
      }
      case _ : IntegerLiteralExpCS => {
        Full(oclLibrary.getOclInteger)
      }
      case _ : RealLiteralExpCS => {
        Full(oclLibrary.getOclReal)
      }
      case _ : BooleanLiteralExpCS => {
        Full(oclLibrary.getOclBoolean)
      }
      case _ : StringLiteralExpCS => {
        Full(oclLibrary.getOclString)
      }
      case i : IterateExpCS => {
        for (innerType <- oclType(i.getResultVariable)) yield {
          innerType
        }
      }
      case i : IteratorExpCS => {
        i.getIteratorName match {
          // FIXME: how to determine source type?
          case "select" => Empty
        }
      }
      case unknown => super.__oclType(unknown)
    }
  }
  
  abstract override def __sourceExpression : Attributable ==> Box[OclExpression] = {
    childAttr {
      case child : AttributableEObject => {
        case c : ContextDeclarationCS =>  for (self <- self(child)) yield factory.createVariableExp(self)
        case n : NavigationCallExp if child != n.getSource => {
          if (child.isFirst)
            computeOclExpression(n.getSource)
          else
            computeOclExpression(child.prev[AttributableEObject])
        }
        case o : OperationCallBaseExpCS if o.getArguments.contains(child.eObject) => {
          variables(child).flatMap{case (implicitVariables, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariables.first))
          }
        }
        case i : IteratorExpCS if child == i.getBodyExpression => {
          variables(child).flatMap{case (implicitVariables, explicitVariables) =>
            Full(factory.createVariableExp(implicitVariables.first))
          }
        }
        case i : IterateExpCS if (child == i.getBodyExpression) || (child == i.getResultVariable) => {
          variables(child).flatMap{case (implicitVariables, explicitVariables) =>
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
  
  abstract override def __isInStaticContext : Attributable ==> Boolean = {
    childAttr {
      case child => {
        case d : DefinitionExpCS => d.isStatic
        case o : OperationContextDeclarationCS => {
          val operation = o.getOperation.getOperation
          if (operation.eIsProxy)
            false
          else
            operation.isStatic
        }
        case a : AttributeContextDeclarationCS => {
          val property = a.getTypeName.getNamedElement
          if (property.eIsProxy)
            false
          else if (property.isInstanceOf[Property])  {
            property.asInstanceOf[Property].isStatic
          } else {
            false
          }
        }
        case _ => super.__isInStaticContext(child)
      }
    }
  }
}
