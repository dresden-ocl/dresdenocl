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

trait OclLookUpFunctions { selfType : OclStaticSemantics =>
	
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
    (container->variables).flatMap{case (implicitVariables, explicitVariables) =>
      Box(implicitVariables.filter(_.getName == name)) or explicitVariables.find(v => v.getName == name)
    }
  }
  
  protected def lookupVariableFuzzy(name : String, container : Attributable) : List[Variable] = {
    container->variables match {
      case Full((implicitVariables, explicitVariables)) =>
	      implicitVariables.filter(v => v.getName.startsWith(name)):::(explicitVariables.filter(v => v.getName.startsWith(name)))
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
  
  protected def lookupProperty(sourceExpression : OclExpression, element : AttributableEObject,
                               identifier : String, static : Boolean) : Box[Property] = {
    (element->isMultipleNavigationCall).flatMap{isMultipleNavigationCall =>
	    val sourceType = sourceExpression.getType
	    if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
	      lookupPropertyOnType(sourceType, identifier, static)
	    else if (isMultipleNavigationCall) {// implicit asSet
	    	addWarning("implicit asSet() on " + element, element.getEObject)
       	lookupPropertyOnType(sourceExpression.withAsSet.getType, identifier, static)
	    }
	    else {// implicit collect
	    	addWarning("implicit collect() on " + element, element.getEObject)
	      lookupPropertyOnType(sourceType.asInstanceOf[CollectionType].getElementType, identifier, static)
      }
    }
  }
  
  protected def lookupOperationOnType(t : Type, name : String, static : Boolean, parameters : List[Type]) : Box[Operation] = {
    !!(t.lookupOperation(name, parameters)).flatMap{o => if (o.isStatic == static) Full(o) else Empty} or {
      val d = getAllDefs._2.filter(d => t.conformsTo(d._1))
      if (d.isEmpty) 
        Failure("Cannot find operation " + name + " on " + t.getName + " with parameters " + parameters.mkString(", "))
      else {
      	d.flatMap{d =>
      	  val operationDefinitions = d._2.map(_._1)
      	  operationDefinitions.find(o => o.getOperation.getName == name && o.getOperation.isStatic == static &&
                                           o.getOperation.hasMatchingSignature(parameters)).flatMap{operationDefinition =>
      		  (operationDefinition->context).flatMap{context =>
      		    definedOperationsType.put(operationDefinition.getOperation, context.asInstanceOf[Type])
      		    Full(operationDefinition.getOperation)
      		  }
      		}
      	}.toList.firstOption
      }
    }
  }
  
  protected def lookupOperation(sourceExpression : OclExpression, element : AttributableEObject,
                                identifier : String, static : Boolean, parameters : List[Type]) : Box[Operation] = {
    (element->isMultipleNavigationCall).flatMap{isMultipleNavigationCall =>
	    val sourceType = sourceExpression.getType
	    if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
	      lookupOperationOnType(sourceType, identifier, static, parameters)
	    else if (isMultipleNavigationCall) {// implicit asSet
	    	addWarning("implicit asSet() on " + element, element.getEObject)
       	lookupOperationOnType(sourceExpression.withAsSet.getType, identifier, static, parameters)
	    }
	    else {// implicit collect
	    	addWarning("implicit collect() on " + element, element.getEObject)
	      lookupOperationOnType(sourceType.asInstanceOf[CollectionType].getElementType, identifier, static, parameters)
      }
    }
  }
  
  protected def lookupOperationOnTypeFuzzy(t : Type, name : String, static : Boolean) : List[Operation] = {
    t.allOperations.filter(o => o.getName.startsWith(name) && o.isStatic == static):::
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
  
  protected def lookupPropertyOnTypeFuzzy(sourceExpression : OclExpression, element : AttributableEObject,
                                          identifier : String, static : Boolean) : Box[List[Property]] = {
    (element->isMultipleNavigationCall).flatMap{isMultipleNavigationCall =>
	    val sourceType = sourceExpression.getType
	    if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
	      Full(lookupPropertyOnTypeFuzzy(sourceType, identifier, static))
	    else if (isMultipleNavigationCall) // implicit asSet
       	Full(lookupPropertyOnTypeFuzzy(sourceExpression.withAsSet.getType, identifier, static))
	    else // implicit collect
	      Full(lookupPropertyOnTypeFuzzy(sourceType.asInstanceOf[CollectionType].getElementType, identifier, static))
    }
  }
  
  protected def lookupPropertyOnTypeFuzzy(t : Type, name : String, static : Boolean) : List[Property] = {
    t.allProperties.filter(p => (if (p.getName != null) p.getName.startsWith(name) else false) && p.isStatic == static):::
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
        property.setType(tipe)
        if (property.getType == null) {
          // this can happen for not fully qualified collections, e.g., Set instead of Set(Integer)
          resolveTypeByComputingFeature(vd, name, t)
        } else 
        	Full(property)
      }
    } else {
      resolveTypeByComputingFeature(vd, name, t)
    }.flatMap{property =>
      definedPropertysType.put(property, t)
      Full(property)
    }
  }
}
