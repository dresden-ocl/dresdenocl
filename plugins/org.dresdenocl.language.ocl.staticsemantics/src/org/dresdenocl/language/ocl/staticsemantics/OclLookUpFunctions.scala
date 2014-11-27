package org.dresdenocl.language.ocl.staticsemantics

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
import Box._

import org.dresdenocl.language.ocl.resource.ocl.mopp._

import org.kiama._
import attribution._
import Attribution._

import AttributableEObject._

trait OclLookUpFunctions { selfType: OclStaticSemantics =>

  protected def lookupLibraryType(name: String): Option[Type] = {
    oclLibrary.eContents.flatMap {
      _ match {
        case t: Type => Full(t)
        case unknown => Empty
      }
    }
      .find(_.getName == name)
  }

  protected def lookupType(name: String, namespace: Namespace): Box[Type] = {
    if (name == null || name == "")
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

  protected def lookupPathName(names: List[String], namespace: Namespace, container: AttributableEObject): Box[List[NamedElement]] = {
    lookupLibraryType(names.last) match {
      case Some(oclLibraryType) => Full(List(oclLibraryType))
      case None => {
        var element = namespace.lookupPathName(names).toList
        if (element.size == 0 && names.size == 2) {
          element = List(model.findType(List(names.first)))
          if (element.size != 0) {
            element = element :+ (element.first match {
              case e: Enumeration => {
                e.lookupLiteral(names.last)
              }
              case t: Type => {
                t.lookupProperty(names.last)
              }
            })
          }
        }
        if (element.size == 0 && names.size == 1) {
          element = List(model.findType(names))
          if (element.size == 1 && element.first == null) {
            (sourceExpression(container)).flatMap { sourceExpression =>
              (variables(container)).flatMap {
                case (implicitVariables, explicitVariables) =>
                  val sourceType = sourceExpression.getType
                  sourceExpression match {
                    // if the sourceExpression is an implicit variable (e.g., self or an iterator variable),
                    // the named element can be a variable, a property on the implicit variable or a type
                    case ve: VariableExp if !implicitVariables.filter(_ == ve.getReferredVariable).isEmpty => {
                      lookupVariable(names.last, container) match {
                        case Full(variable) => Full(List(variable))
                        case Empty | Failure(_, _, _) => {
                          val allProperties = implicitVariables.map { iv =>
                            lookupPropertyOnType(iv.getType, names.last, false)
                          }.filter(p => p.isDefined)
                          if (!allProperties.isEmpty)
                            Full(List(allProperties.first.open_!))
                          else Empty
                        }
                      }
                    }
                  }
              }
            }
          } else {
            !!(element) ?~
              ("Cannot find element " + names.head + " in namespace " + namespace.getName)
          }
        } else {
          !!(element) ?~
            ("Cannot find element " + names.head + " in namespace " + namespace.getName)
        }

      }
    }
  }

  private def lookupLocal(name: String, namespace: Namespace): Box[Type] = {
    !!(namespace.lookupType(name)) or !!(model.findType(List(name))) ?~
      ("Cannot find type " + name + " in namespace " + namespace.getName)
  }

  protected def lookupTypeFuzzy(identifier: String, namespace: Namespace): List[Type] = {
    namespace.getOwnedType.filter(t => t.getName.startsWith(identifier)) ++ (
      // add primitive types
      oclLibrary.eContents.flatMap(_ match {
        case t: Type => Full(t)
        case unknown => Empty
      }).filter(t => t.getName.startsWith(identifier))) toList
  }

  protected def lookupVariable(name: String, container: Attributable): Box[Variable] = {
    (variables(container)).flatMap {
      case (implicitVariables, explicitVariables) =>
        Box(
          if (isInStaticContext(container))
            implicitVariables.filter(iv => iv.getName != "self" && iv.getName == name)
          else
            implicitVariables.filter(_.getName == name))
          .or(explicitVariables.find(v => v.getName == name))
    }
  }

  protected def lookupVariableFuzzy(name: String, container: Attributable): List[Variable] = {
    variables(container) match {
      case Full((implicitVariables, explicitVariables)) =>
        (if (isInStaticContext(container))
          implicitVariables.filter(v => v.getName != "self" && v.getName.startsWith(name))
        else
          implicitVariables.filter(v => v.getName.startsWith(name))) ::: (explicitVariables.filter(v => v.getName.startsWith(name)))
      case Failure(_, _, _) | Empty => List()
    }
  }

  protected def lookupPropertyOnType(t: Type, name: String, static: Boolean): Box[Property] = {
    t.allProperties.find(p => p.getName == name && p.isStatic == static) or {
      val d = getAllDefs._1.filter(d => t.conformsTo(d._1))
      if (d.isEmpty) Failure("Cannot find property " + name + " on " + t.getName, Empty, Empty)
      else {
        // TODO: avoid cycles!
        d.flatMap { d =>
          d._2.find(v => name == v.getVariableName.getSimpleName) match {
            case Some(v) => getPropertyFromVariableDeclaration(v, t, name)
            case None => Empty
          }
        }.toList.firstOption
      }
    }
  }

  protected def lookupProperty(sourceExpression: OclExpression, element: AttributableEObject,
    identifier: String, static: Boolean): Box[Property] = {
    (isMultipleNavigationCall(element)).flatMap { isMultipleNavigationCall =>
      val sourceType = sourceExpression.getType
      if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
        lookupPropertyOnType(sourceType, identifier, static)
      else if (isMultipleNavigationCall) { // implicit asSet
        addWarning("implicit asSet() on " + element, element.eObject)
        lookupPropertyOnType(sourceExpression.withAsSet.getType, identifier, static)
      } else { // implicit collect
        addWarning("implicit collect() on " + element, element.eObject)
        lookupPropertyOnType(sourceType.asInstanceOf[CollectionType].getElementType, identifier, static)
      }
    }
  }

  protected def lookupOperationOnType(t: Type, name: String, static: Boolean, parameters: List[Type]): Box[Operation] = {
    !!(t.lookupOperation(name, parameters)).flatMap { o => if (o.isStatic == static) Full(o) else Empty } or {
      val d = getAllDefs._2.filter(d => t.conformsTo(d._1))
      if (d.isEmpty)
        Failure("Cannot find operation " + name + " on " + t.getName + " with parameters " + parameters.mkString(", "))
      else {
        d.flatMap { d =>
          val operationDefinitions = d._2.map(_._1)
          operationDefinitions.find(o => o.getOperation.getName == name && o.getOperation.isStatic == static &&
            o.getOperation.hasMatchingSignature(parameters)).flatMap { operationDefinition =>
            (context(operationDefinition)).flatMap { context =>
              definedOperationsType.put(operationDefinition.getOperation, context.asInstanceOf[Type])
              Full(operationDefinition.getOperation)
            }
          }
        }.toList.firstOption
      }
    }
  }

  protected def lookupOperation(sourceExpression: OclExpression, element: AttributableEObject,
    identifier: String, static: Boolean, parameters: List[Type]): Box[Operation] = {
    (isMultipleNavigationCall(element)).flatMap { isMultipleNavigationCall =>
      val sourceType = sourceExpression.getType
      if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
        lookupOperationOnType(sourceType, identifier, static, parameters)
      else if (isMultipleNavigationCall) { // implicit asSet
        addWarning("implicit asSet() on " + element, element.eObject)
        lookupOperationOnType(sourceExpression.withAsSet.getType, identifier, static, parameters)
      } else { // implicit collect
        addWarning("implicit collect() on " + element, element.eObject)
        lookupOperationOnType(sourceType.asInstanceOf[CollectionType].getElementType, identifier, static, parameters)
      }
    }
  }

  protected def lookupOperationOnTypeFuzzy(t: Type, name: String, static: Boolean): List[Operation] = {
    t.allOperations.filter(o => o.getName.startsWith(name) && o.isStatic == static) ++
      getAllDefs._2.filter(d => t.conformsTo(d._1)).flatMap { d =>
        d._2.map(_._1.getOperation).filter(o => o.getName.startsWith(name) && o.isStatic == static)
      } toList
  }

  protected def resolveTypeByComputingFeature(v: VariableDeclarationWithInitCS,
    name: String,
    t: Type): Box[Property] = {
    try {
      computeFeature(v).flatMap { f =>
        f._1 match {
          case p: Property => Full(p)
          case _ => Failure("Cannot find property " + name + " on type " + t + ".", Empty, Empty)
        }
      }
    } catch {
      case i: IllegalStateException => yieldFailure("Recursive property definition.", v)
    }
  }

  protected def lookupPropertyOnTypeFuzzy(sourceExpression: OclExpression, element: AttributableEObject,
    identifier: String, static: Boolean): Box[List[Property]] = {
    (isMultipleNavigationCall(element)).flatMap { isMultipleNavigationCall =>
      val sourceType = sourceExpression.getType
      if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
        Full(lookupPropertyOnTypeFuzzy(sourceType, identifier, static))
      else if (isMultipleNavigationCall) // implicit asSet
        Full(lookupPropertyOnTypeFuzzy(sourceExpression.withAsSet.getType, identifier, static))
      else // implicit collect
        Full(lookupPropertyOnTypeFuzzy(sourceType.asInstanceOf[CollectionType].getElementType, identifier, static))
    }
  }

  protected def lookupPropertyOnTypeFuzzy(t: Type, name: String, static: Boolean): List[Property] = {
    t.allProperties.filter(p => (if (p.getName != null) p.getName.startsWith(name) else false) && p.isStatic == static) ++
      (getAllDefs._1.filter(d => t.conformsTo(d._1)).flatMap { d =>
        d._2.filter(vd => vd.getVariableName.getSimpleName.startsWith(name)).map { vd =>
          getPropertyFromVariableDeclaration(vd, t, name)
        }
      } flatten (i => i)) toList
  }

  private def getPropertyFromVariableDeclaration(vd: VariableDeclarationWithInitCS, t: Type, name: String): Box[Property] = {
    (if (vd.getTypeName != null) {
      (propertyForVariableDeclarationWithInit(vd)).flatMap { property =>
        if (property.getType == null) {
          // this can happen for not fully qualified collections, e.g., Set instead of Set(Integer)
          resolveTypeByComputingFeature(vd, name, t)
        } else
          Full(property)
      }
    } else {
      resolveTypeByComputingFeature(vd, name, t)
    }).flatMap { property =>
      definedPropertysType.put(property, t)
      Full(property)
    }
  }
}


