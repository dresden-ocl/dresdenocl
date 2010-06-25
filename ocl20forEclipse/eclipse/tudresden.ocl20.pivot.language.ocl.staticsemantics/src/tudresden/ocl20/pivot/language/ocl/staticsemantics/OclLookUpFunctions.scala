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
    (container->variables).flatMap{case (implicitVariableBox, explicitVariables) =>
      implicitVariableBox.filter(_.getName == name) or explicitVariables.find(v => v.getName == name)
    }
  }
  
  protected def lookupVariableFuzzy(name : String, container : Attributable) : List[Variable] = {
    container->variables match {
      case Full((implicitVariableBox, explicitVariables)) => implicitVariableBox match {
        case Full(implicitVariable) => {
	        if (implicitVariable.getName.startsWith(name))
	          implicitVariable::(explicitVariables.filter(v => v.getName.startsWith(name)))
	        else
	        	explicitVariables.filter(v => v.getName.startsWith(name))
        }
        case _ => List()
      }
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
    !!(t.lookupOperation(name, parameterTypes)).flatMap{o => if (o.isStatic == static) Full(o) else Empty} or {
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
  
  protected def lookupOperationOnType(tipe : Type, identifier : String, 
                                    parameters : List[Parameter], static : Boolean,
                                    element : EObject,
                                    warning: Box[String]) : Box[List[Operation]] = {
    lookupOperationOnType(tipe, identifier, static, parameters)
      .flatMap{o =>
      	warning match {
      	  case Full(w) => addWarning(w + " on " + o.getName, element)
      	  case Failure(_, _, _) | Empty => // ignore
      	}
      	Full(List(o))
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
}
