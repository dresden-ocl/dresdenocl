package org.dresdenocl.language.ocl.staticsemantics

import collection.JavaConversions._

import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import org.dresdenocl.attributegrammar.integration.kiama._
import org.dresdenocl.pivotmodel._
import org.dresdenocl.language.ocl._
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

trait OclParseTreeToEssentialOcl { selfType : OclStaticSemantics =>

  protected val computeConstraints = __computeConstraints
  protected def __computeConstraints : Attributable ==> Box[List[Constraint]] = {
    attr {
      case unknown : AttributableEObject => yieldFailure("Cannot compute constraints for " + unknown, unknown.eObject)
    }
  }

  protected val computeConstraint : Attributable ==> Box[Constraint] = {
    attr {
      case i : InvariantExpCS => {
        for (
          inv <- computeBooleanConstraint(i, i.getName, i.getOclExpression, ConstraintKind.INVARIANT, null) 
        ) yield {
          allMappings.put(inv, i)
          inv
        }      
      }

      case d : DefinitionExpCS => {
        for (
          self <- self(d);
          (feature, init) <- computeFeature(d.getDefinitionExpPart)
        ) yield {
          feature.setStatic(d.isStatic)
          val expression = factory.createExpressionInOcl(
            printOclExpression(d), init, self, null)
          val constraint = factory.createConstraint(
            "", ConstraintKind.DEFINITION, expression, feature, self.getType)
          allMappings.put(expression, d)
          allMappings.put(constraint, d)
          constraint
        }
      }

      case p : PreConditionDeclarationCS => {
        (variables(p)).flatMap {
          case (_, explicitVariables) =>
            val representedParameter = explicitVariables.filter(_.getRepresentedParameter != null)
            computeBooleanConstraint(p, p.getName, p.getOclExpression, ConstraintKind.PRECONDITION, representedParameter)
        }
      }

      case p : PostConditionDeclarationCS => {
        (variables(p)).flatMap {
          case (_, explicitVariables) =>
            val representedParameter = explicitVariables.filter(_.getRepresentedParameter != null)
            computeBooleanConstraint(p, p.getName, p.getOclExpression, ConstraintKind.POSTCONDITION, representedParameter)
        }
      }

      case b : BodyDeclarationCS => {
        (self(b)).flatMap { self =>
          (context(b)).flatMap { context =>
            (computeOclExpression(b.getOclExpression)).flatMap { oclExpressionEOcl =>
              (variables(b)).flatMap {
                case (_, explicitVariables) =>
                  val operation = context.asInstanceOf[Operation]
                  if (!oclExpressionEOcl.getType.conformsTo(operation.getType))
                    yieldFailure("Expected type " + operation.getType.getName + ", but found " + oclExpressionEOcl.getType.getName, b.getOclExpression)
                  else {
                    val result = factory.createVariable(operation.getReturnParameter)
                    val representedParameter = explicitVariables.filter(_.getRepresentedParameter != null).toArray
                    val expression = factory.createExpressionInOcl(
                      printOclExpression(b), oclExpressionEOcl, self, result, representedParameter : _*)
                    var constraintName : String = ""
                    if (b.getName != null)
                      constraintName = b.getName.getSimpleName
                    val constraint = factory.createConstraint(
                      constraintName, ConstraintKind.BODY, expression, operation, context)
                    allMappings.put(expression, b)
                    allMappings.put(constraint, b)
                    Full(constraint)
                  }
              }
            }
          }
        }
      }

      case i : InitOrDeriveValueCS => {
        (self(i)).flatMap { self =>
          (context(i)).flatMap { context =>
            (computeOclExpression(i.getOclExpression)).flatMap { oclExpressionEOcl =>
              val property = context.asInstanceOf[Property]
              if (!oclExpressionEOcl.getType.conformsTo(property.getType))
                yieldFailure("Expected type " + property.getType.getName + ", but found " + oclExpressionEOcl.getType.getName, i.getOclExpression)
              else {
                val expression = factory.createExpressionInOcl(
                  printOclExpression(i), oclExpressionEOcl, self, null)
                val constraint = factory.createConstraint(
                  "", i match {
                    case _ : InitValueCS   => ConstraintKind.INITIAL
                    case _ : DeriveValueCS => ConstraintKind.DERIVED
                  }, expression, property, context)
                allMappings.put(expression, i)
                allMappings.put(constraint, i)
                Full(constraint)
              }
            }
          }
        }
      }
    }
  }

  def computeBooleanConstraint(element : AttributableEObject, name : SimpleNameCS,
                               oclExpression : OclExpressionCS, constraintKind : ConstraintKind,
                               parameters : List[Variable]) : Box[Constraint] = {
    (computeOclExpression(oclExpression)).flatMap { oclExpressionEOcl =>
      oclExpressionEOcl.getType match {
        case pt : PrimitiveType => if (pt.getKind != PrimitiveTypeKind.BOOLEAN)
          yieldFailure(constraintKind.getName + "s must result in type Boolean and not " +
            pt.getKind + ".", element.eObject)
        case otherType => yieldFailure(constraintKind.getName + "s must result in type Boolean and not " +
          otherType.getName + ".", element.eObject)
      }
      for (
        self <- self(element);
        context <- context(element)
      ) yield {
        val expression =
          if (parameters == null)
            factory.createExpressionInOcl(
              printOclExpression(element.eObject), oclExpressionEOcl, self, null)
          else
            factory.createExpressionInOcl(
              printOclExpression(element.eObject), oclExpressionEOcl, self, null, parameters : _*)
        var constraintName : String = ""
        if (name != null)
          constraintName = name.getSimpleName
        val constraint = factory.createConstraint(
          constraintName, constraintKind, expression, null, context)
        allMappings.put(expression, element.eObject)
        allMappings.put(constraint, element.eObject)
        constraint
      }
    }
  }

  protected val computeFeature : Attributable ==> Box[Tuple2[Feature, OclExpression]] = {
    attr {
      case d : DefinitionExpPropertyCS => {
        // check for self containment in the assignment
        checkSelfcontainment(d.getVariableDeclaration) match {
          case Full(true) => yieldFailure("Assignment is self containing", d.getVariableDeclaration)
          case _          => computeFeature(d.getVariableDeclaration)
        }
      }

      case v : VariableDeclarationWithInitCS => {
        (computeOclExpression(v.getInitialization)).flatMap { oclExpressionEOcl =>
          checkVariableDeclarationType(v).flatMap { tipe =>
            (propertyForVariableDeclarationWithInit(v)).flatMap { property =>
              v.parent match {
                case v : VariableDeclarationWithInitListCS => v.parent match {
                  case _ : TupleLiteralExpCS => Full((property, oclExpressionEOcl))
                }
                case _ => {
                  (self(v)).flatMap { self =>
                    self.getType.allProperties.find(_.getName == property.getName) match {
                      case Some(p) => yieldFailure("Property " + p.getName + " is already defined on " +
                        self.getType.getName, v)
                      case None => Full((property, oclExpressionEOcl))
                    }
                  }
                }
              }
            }
          }
        }
      }

      case v : VariableDeclarationWithoutInitCS => {
        (oclType(v.getTypeName)).flatMap { tipe =>
          val property = PivotModelFactory.eINSTANCE.createProperty
          property.setName(v.getVariableName.getSimpleName)
          // TODO: does not work yet for nested collections
          property.setType(tipe)
          allMappings.put(property, v)
          Full(property, null)
        }
      }

      case d : DefinitionExpOperationCS => {
        val operation = d.getOperation.getOperation
        if (operation.eIsProxy)
          Empty
        else {
          (computeOclExpression(d.getOclExpression)).flatMap { oclExpressionEOcl =>
            val returnType = d.getOperation.getReturnType
            val typeConformance = if (returnType != null) {
              (oclType(returnType)).flatMap { t =>
                if (!oclExpressionEOcl.getType.conformsTo(t))
                  yieldFailure("Expected type " + t.getName + ", but found " +
                    oclExpressionEOcl.getType.getName, d)
                else
                  Full(true)
              }
            }
            else
              Full(true)
            typeConformance.flatMap { _ =>
              Full((operation, oclExpressionEOcl))
            }
          }
        }
      }
    }
  }

  protected val computeOclExpression : Attributable ==> Box[OclExpression] = {
    attr {
      case i : IntegerLiteralExpCS => {
      	val intLit = factory.createIntegerLiteralExp(i.getIntegerLiteral)
      	allMappings.put(intLit, i)
        Full(intLit)
      }

      case r : RealLiteralExpCS => {
        import java.lang.Math._
        if (r.getNavigationOperator == "->")
          yieldFailure("Cannot use '->' in a real expression.", r)
        else {
          val realValueInt = Integer.parseInt(r.getRealValue)
          val rFloat = factory.createRealLiteralExp(r.getIntValue + (realValueInt / pow(10, r.getRealValue.length)).toFloat)
          allMappings.put(rFloat, r)
          Full(rFloat)
        }
      }

      case s : StringLiteralExpCS => {
      	val sLit = factory.createStringLiteralExp(s.getStringLiteral)
      	allMappings.put(sLit, s) 
        Full(sLit)
      }

      case b : BooleanLiteralExpCS => {
      	val bLit = factory.createBooleanLiteralExp(b.isBooleanLiteral)
      	allMappings.put(bLit, b)
        Full(bLit)
      }

      case i : InvalidLiteralExpCS => {
    		val iLit = factory.createInvalidLiteralExp
    		allMappings.put(iLit, i) 
        Full(iLit)
      }

      case n : NullLiteralExpCS => {
      	val nLit = factory.createUndefinedLiteralExp
      	allMappings.put(nLit, n)
        Full(nLit)
      }

      case t : TupleLiteralExpCS => {
        val properties = t.getVariableDeclarations.getVariableDeclarations.flatMap(vd => computeFeature(vd))
        if (properties.size != t.getVariableDeclarations.getVariableDeclarations.size)
          Empty
        else {
          if (properties.size != properties.map(_._1.getName).toList.removeDuplicates.size)
            yieldFailure("Cannot have tuple elements with the same name.", t)
          val tupleLiteralParts = properties.map {
            case (property, initExpression) =>
              val tupleLiteralPart = ExpressionsFactory.INSTANCE.createTupleLiteralPart
              tupleLiteralPart.setProperty(property.asInstanceOf[Property])
              tupleLiteralPart.setValue(initExpression)
              allMappings.put(tupleLiteralPart, t)
              tupleLiteralPart
          }
          val tLit = factory.createTupleLiteralExp(tupleLiteralParts.toArray : _*)
          //TODO Lars: How to map tLit to a CS element? Needed?
          allMappings.put(tLit, t)
          Full(tLit)
        }
      }

      case o : OperationCallBinaryExpCS => {
        try {
          for (
            sourceEOcl <- computeOclExpression(o.getSource);
            targetEOcl <- computeOclExpression(o.getTarget);
            result <- Full(factory.createOperationCallExp(sourceEOcl, o.getOperationName, targetEOcl))
          ) yield {
            if (o.isIsMarkedPre) {
              allMappings.put(result.withAtPre, o)
              result.withAtPre
            }
            else {
              allMappings.put(result, o)
              result
            }
          }
        }
        catch {
          case e : IllegalArgumentException => yieldFailure(e.getMessage, o)
        }
      }

      case u : UnaryOperationCallExpCS => {
        try {
          for (
            targetEOcl <- computeOclExpression(u.getTarget);
            result <- Full(factory.createOperationCallExp(targetEOcl, u.getOperationName))
          ) yield {
          	allMappings.put(result, u)
          	result
          }
        }
        catch {
          case e : IllegalArgumentException => yieldFailure(e.getMessage, u)
        }
      }

      case l : LogicalNotOperationCallExpCS => {
        try {
          for (
            targetEOcl <- computeOclExpression(l.getTarget);
            result <- Full(factory.createOperationCallExp(targetEOcl, l.getOperationName))
          ) yield {
            allMappings.put(result, l)
          	result
          }
        }
        catch {
          case e : IllegalArgumentException => yieldFailure(e.getMessage, l)
        }
      }

/*
      case v : NamedLiteralExpCS => {
        val namedElement = v.getNamedElement
        if (namedElement.eIsProxy)
          Empty
        else {
          namedElement match {
            case v2 : Variable => {
              val varExp = factory.createVariableExp(v2)
              allMappings.put(varExp, v)
              Full(varExp)
            }
            case p : Property => {
              if (p.isStatic) {
                var tle = factory.createTypeLiteralExp(p.getOwningType.getQualifiedNameList)
              	val statp = factory.createPropertyCallExp(tle, p)
                allMappings.put(tle, v)
              	allMappings.put(statp, v)
                Full(statp)
              }
              else {
                (variables(v)).flatMap {
                  case (implicitVariables, _) =>
                    val propertyOwner = if (p.getOwningType != null) p.getOwningType else definedPropertysType.get(p)
                    implicitVariables.flatMap { iv =>
                      if (iv.getType.conformsTo(propertyOwner))
                        Full(iv)
                      else
                        Empty
                    }.firstOption.flatMap { iv =>
                      val sourceExpression = factory.createVariableExp(iv)
                      // TODO: put this into the EssentialOclFactory
                      val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
                      pce.setReferredProperty(p)
                      pce.setSourceType(sourceExpression.getType)
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
                      allMappings.put(cleanSourceExpression, v)
                      pce.setSource(cleanSourceExpression)
                      pce.setOclLibrary(oclLibrary)
                      allMappings.put(pce, v)
                      Full(pce)
                    }
                }
              }
            }
            case t : Type => {
              val tle = ExpressionsFactory.INSTANCE.createTypeLiteralExp
              tle.setReferredType(t)
              tle.setOclLibrary(oclLibrary)
              allMappings.put(tle, v)
              Full(tle)
            }
          }
        }
      }
*/
      case n : NavigationCallExp => {
        computeOclExpression(n.getFeatureCalls.last)
      }

      case p : PropertyCallBaseExpCS => {
        if (p.isIsMarkedPre && !(isInPostCondition(p)))
          yieldFailure("Cannot use @pre outside of a post condition.", p)
        else {
          val property = p.getProperty
          if (property.eIsProxy)
            Empty
          else {
            // the owner is not set for defined operations; in that case use a helper Map
            val propertyOwner = if (property.getOwningType != null) property.getOwningType else definedPropertysType.get(property)
            object PropertyExpressionForm extends scala.Enumeration {
              type PropertyExpressionForm = Value
              val Normal, ImplicitCollect, ImplicitAsSet = Value
            }
            import PropertyExpressionForm._
            val pef =
              (sourceExpression(p)).flatMap { sourceExpression =>
                (isMultipleNavigationCall(p)).flatMap { isMultipleNavigationCall =>
                  // Make sure, the source is not already contained by another element.
                  // If it's a VariableExp, copy it; if it's not, then there has been
                  // an implicit collect() or implicit asSet(), so go to the eContainer().
                  val normalisedSourceExpression =
                    if (sourceExpression.eContainer != null) {
                      sourceExpression match {
                        case v : VariableExp =>
                          factory.createVariableExp(
                            factory.createVariable(v.getReferredVariable.getName,
                              v.getReferredVariable.getType,
                              v.getReferredVariable.getInitExpression))
                        case unknown => unknown.eContainer.asInstanceOf[OclExpression]
                      }
                    }
                    else
                      sourceExpression

                  val sourceType = normalisedSourceExpression.getType
                  if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
                    Full(Normal, normalisedSourceExpression)
                  else if (isMultipleNavigationCall)
                    Full(ImplicitAsSet, normalisedSourceExpression)
                  else
                    Full(ImplicitCollect, normalisedSourceExpression)
                }
              }
            pef.flatMap {
              case (pef, sourceExpression) =>
                val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
                pce.setReferredProperty(property)
                pce.setOclLibrary(oclLibrary)

                if (pef == Normal) {
                  pce.setSource(sourceExpression)
                  if (p.isIsMarkedPre) {
                    allMappings.put(pce.withAtPre, p)
                    Full(pce.withAtPre)
                  }
                  else {
                    allMappings.put(pce, p)
                    Full(pce)
                  }
                }
                else if (pef == ImplicitCollect) {
                  val iteratorVar = ExpressionsFactory.INSTANCE.createVariable
                  iteratorVar.setName("$implicitCollect" + ImplicitVariableNumberGenerator.getNumber + "$")
                  iteratorVar.setType(sourceExpression.getType.asInstanceOf[CollectionType].getElementType)
                  pce.setSource(factory.createVariableExp(iteratorVar))
                  val it = factory.createIteratorExp(sourceExpression, "collect", pce, iteratorVar)
                  allMappings.put(it, p)
                  allMappings.put(pce, p)
                  Full(it)
                }
                else {
                  pce.setSource(sourceExpression.withAsSet)
                  allMappings.put(pce, p)
                  Full(pce)
                }
            }
          }
        }
      }

      case o : OperationCallBaseExpCS => {
        if (o.isIsMarkedPre && !(isInPostCondition(o)))
          yieldFailure("Cannot use @pre outside of a post condition.", o)
        else {
          val operation = o.getOperationName
          if (operation.eIsProxy)
            Empty
          else {
            val argumentsEOcl = o.getArguments.map(arg => computeOclExpression(arg))
            argumentsEOcl.find(!_.isDefined) match {
              case Some(f) => f.asInstanceOf[Box[FeatureCallExp]]
              case None => {
                // the owner is not set for defined operations; in that case use a helper Map
                val operationOwner = if (operation.getOwningType != null) operation.getOwningType else definedOperationsType.get(operation)
                object OperationExpressionForm extends scala.Enumeration {
                  type OperationExpressionForm = Value
                  val Normal, ImplicitCollect, ImplicitAsSet = Value
                }
                import OperationExpressionForm._
                val oef = o match {
                  case o : OperationCallOnSelfExpCS => {
                    (variables(o)).flatMap {
                      case (implicitVariables, _) =>
                        implicitVariables.flatMap { iv =>
                          if (iv.getType.conformsTo(operationOwner)) {
                            var v = factory.createVariableExp(iv)
                            allMappings.put(v, o)
                            Full(Normal, v)
                          }
                          //	              		    else {
                          //	              		      if (iv.getType.isInstanceOf[CollectionType] && iv.getType.asInstanceOf[CollectionType].getElementType.conformsTo(operationOwner))
                          //	              		      	Full(ImplicitCollect, factory.createVariableExp(iv))
                          //	              		      else
                          //	              		      	if(operationOwner.isInstanceOf[SetType])
                          //	              		      		Full(ImplicitAsSet, factory.createVariableExp(iv))
                          else
                            Empty
                          //	              		    }	
                        }.firstOption // the first value is OK, since implicit variables are ordered from innermost to outermost
                    }
                  }
                  case i : ImplicitOperationCallCS => {
                    (sourceExpression(o)).flatMap { sourceExpression =>
                      // Make sure, the source is not already contained by another element.
                      // If it's a VariableExp, copy it; if it's not, then there has been
                      // an implicit collect() or implicit asSet(), so go to the eContainer().
                      val normalisedSourceExpression =
                        if (sourceExpression.eContainer != null) {
                          sourceExpression match {
                            case v : VariableExp =>
                              factory.createVariableExp(
                                factory.createVariable(v.getReferredVariable.getName,
                                  v.getReferredVariable.getType,
                                  v.getReferredVariable.getInitExpression))
                            case unknown => unknown.eContainer.asInstanceOf[OclExpression]
                          }
                        }
                        else
                          sourceExpression
                      (isMultipleNavigationCall(o)).flatMap { isMultipleNavigationCall =>
                        val sourceType = normalisedSourceExpression.getType
                        if ((isMultipleNavigationCall && sourceType.isInstanceOf[CollectionType]) || (!isMultipleNavigationCall && !sourceType.isInstanceOf[CollectionType]))
                          Full(Normal, normalisedSourceExpression)
                        else if (isMultipleNavigationCall)
                          Full(ImplicitAsSet, normalisedSourceExpression)
                        else
                          Full(ImplicitCollect, normalisedSourceExpression)
                      }
                    }
                  }
                }
                oef.flatMap {
                  case (oef, sourceExpression) =>
                    val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
                    oce.setReferredOperation(operation)
                    argumentsEOcl.foreach { arg =>
                      oce.getArgument.add(arg.open_!)
                    }
                    oce.setOclLibrary(oclLibrary)

                    if (oef == Normal) {
                      oce.setSource(sourceExpression)
                      if (o.isIsMarkedPre) {
                        allMappings.put(oce.withAtPre, o)
                        Full(oce.withAtPre)
                      }
                      else {
                        allMappings.put(oce, o)
                        Full(oce)
                      }
                    }
                    else if (oef == ImplicitCollect) {
                      val iteratorVar = ExpressionsFactory.INSTANCE.createVariable
                      iteratorVar.setName("$implicitCollect" + ImplicitVariableNumberGenerator.getNumber + "$")
                      iteratorVar.setType(sourceExpression.getType.asInstanceOf[CollectionType].getElementType)
                      oce.setSource(factory.createVariableExp(iteratorVar))
                      val it = factory.createIteratorExp(sourceExpression, "collect", oce, iteratorVar)
                      allMappings.put(oce, o)
                      allMappings.put(it, o)
                      allMappings.put(iteratorVar, o)
                      Full(it)
                    }
                    else {
                      oce.setSource(sourceExpression.withAsSet)
                      allMappings.put(oce, o)
                      Full(oce)
                    }
                }
              }
            }
          }
        }
      }

      case i : IteratorExpCS => {
        (sourceExpression(i)).flatMap { se =>
          (computeOclExpression(i.getBodyExpression)).flatMap { bodyEOcl =>
            (variables(i.getBodyExpression)).flatMap {
              case (implicitVariables, explicitVariables) =>
                val iteratorNames = i.getIteratorVariables.map(_.getVariableName.getSimpleName)
                val iteratorExp = factory.createIteratorExp(se.getType match {
                  case _ : CollectionType => se
                  case _ : Type           => se.withAsSet
                }, i.getIteratorName, bodyEOcl,
                  (if (i.getIteratorVariables.isEmpty)
                    Array(implicitVariables.first)
                  else if (i.getIteratorVariables.size == 1)
                    Array(explicitVariables.first)
                  else
                    Array(explicitVariables.first, explicitVariables.get(1))) : _*)
                // triggers WFR checks in if(EssentialOcl) WFRException is thrown yield a Failure
                try {
                  iteratorExp.getType
                  allMappings.put(iteratorExp, i)
                  Full(iteratorExp)
                }
                catch {
                  case e : WellformednessException => yieldFailure(e.getMessage, i)
                }
            }
          }
        }
      }

      case i : IterateExpCS => {
        (sourceExpression(i)).flatMap { se =>
          (variables(i.getBodyExpression)).flatMap {
            case (implicitVariables, explicitVariables) =>
              (computeOclExpression(i.getBodyExpression)).flatMap { bodyEOcl =>
                val iteratorExp = factory.createIterateExp(se.getType match {
                  case _ : CollectionType => se
                  case _ : Type           => se.withAsSet
                }, bodyEOcl,
                  if (i.getIteratorVariable != null) explicitVariables.get(1) else explicitVariables.first,
                  if (i.getIteratorVariable != null) explicitVariables.first else implicitVariables.first)
                // triggers WFR checks in if(EssentialOcl) WFRException is thrown yield a Failure
                try {
                  iteratorExp.getType
                  allMappings.put(iteratorExp, i)
                  Full(iteratorExp)
                }
                catch {
                  case e : WellformednessException => yieldFailure(e.getMessage, i)
                }
              }
          }
        }
      }

      case c : CollectionLiteralExpCS => {
        val literalPartsEOcl = c.getCollectionLiteralParts.map(clp => computeLiteralPart(clp))
        literalPartsEOcl.find(!_.isDefined) match {
          case Some(Failure(msg, _, _)) => Failure(msg, Empty, Empty)
          case Some(_)                  => Empty
          case None => {
            (oclType(c.getCollectionType)).flatMap { ct =>
              val unboxedLiteralParts = literalPartsEOcl.flatten(l => l)
              // Michael: type conformance testing is done in CollectionLiteralExp.getType()
              //              val genericType = ct.asInstanceOf[CollectionType].getElementType
              //              val typeConformance = if (genericType != null && !unboxedLiteralParts.isEmpty) {
              //                val commonSuperType = unboxedLiteralParts.map(_.getType).reduceLeft((a, b) => a.commonSuperType(b))
              //                if (!commonSuperType.conformsTo(genericType))
              //                  yieldFailure("Exprected type " + genericType.getName + ", but found " + commonSuperType.getName, c)
              //                else
              //                  Full(true)
              //              } else
              //                Full(true)
              //              typeConformance.flatMap{_ =>
              val collectionKind = ct match {
                case b : BagType        => Full(CollectionKind.BAG)
                case s : SetType        => Full(CollectionKind.SET)
                case s : SequenceType   => Full(CollectionKind.SEQUENCE)
                case o : OrderedSetType => Full(CollectionKind.ORDERED_SET)
                case c : CollectionType => yieldFailure("'Collection' cannot be initialised.", c)
              }
              collectionKind.flatMap { collectionKind =>
                val result = factory.createCollectionLiteralExp(collectionKind, ct.asInstanceOf[CollectionType].getElementType, unboxedLiteralParts.toArray : _*)
                try {
                  result.getType
                  allMappings.put(result, c)
                  Full(result)
                }
                catch {
                  case e : WellformednessException => yieldFailure(e.getMessage, c); Failure(e.getMessage, Empty, Empty)
                }
              }
            }
            //            }
          }
        }
      }

      case i : IfExpCS => {
        for (
          conditionEOcl <- computeOclExpression(i.getCondition);
          thenEOcl <- computeOclExpression(i.getThenBranch);
          elseEOcl <- computeOclExpression(i.getElseBranch)
        ) yield {
          val ifExp = factory.createIfExp(conditionEOcl, thenEOcl, elseEOcl)
          allMappings.put(ifExp, i)
          allMappings.put(thenEOcl, i.getThenBranch)
          allMappings.put(elseEOcl, i.getElseBranch)
          ifExp        
        }
      }

      case l : LetExpCS => {
        (variables(l.getOclExpression)).flatMap {
          case (_, explicitVariables) =>
            explicitVariables.take(l.getVariableDeclarations.size).foldLeft(computeOclExpression(l.getOclExpression)) { (expression, explicitVariable) =>
              expression.flatMap { expression =>
              	val letExp = factory.createLetExp(explicitVariable, expression)
              	allMappings.put(letExp, l)
                Full(letExp)
              }
            }
        }
      }

      case b : BracketExpCS => {
        computeOclExpression(b.getOclExpression)
      }

      case e : ModelElementCS => {
        val refElement = e.getNamedElement
        if (refElement.eIsProxy)
          Empty
        else {
          refElement match {
          	case v : Variable => {
          		val va = factory.createVariableExp(v)
          		allMappings.put(va, v)
          		Full(va)
          	}
            case e : EnumerationLiteral => {
              // TODO: move to EssentialOclFactory
              val exp = ExpressionsFactory.INSTANCE.createEnumLiteralExp
              exp.setReferredEnumLiteral(e)
              exp.setOclLibrary(oclLibrary)
              allMappings.put(exp, e)
              Full(exp)
            }
            //TODO Lars: add mapping here
            case p : Property => {
              if (p.isStatic) {
                Full(factory.createPropertyCallExp(
                  factory.createTypeLiteralExp(p.getOwningType.getQualifiedNameList), p))
              }
              else {
                (variables(e)).flatMap {
                  case (implicitVariables, _) =>
                    val propertyOwner = if (p.getOwningType != null) p.getOwningType else definedPropertysType.get(p)
                    implicitVariables.flatMap { iv =>
                      if (iv.getType.conformsTo(propertyOwner))
                        Full(iv)
                      else
                        Empty
                    }.firstOption.flatMap { iv =>
                      val sourceExpression = factory.createVariableExp(iv)
                      // TODO: put this into the EssentialOclFactory
                      val pce = ExpressionsFactory.INSTANCE.createPropertyCallExp
                      pce.setReferredProperty(p)
                      pce.setSourceType(sourceExpression.getType)
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
                      pce.setSource(cleanSourceExpression)
                      pce.setOclLibrary(oclLibrary)
                      Full(pce)
                    }
                }
              }
            }
            case t : Type => {
              // TODO: move to EssentialOclFactory
              val tle = ExpressionsFactory.INSTANCE.createTypeLiteralExp
              tle.setReferredType(t)
              tle.setOclLibrary(oclLibrary)
              Full(tle)
            }
            case o : Operation => {
              // TODO: move to EssentialOclFactory
              val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
              oce.setSourceType(o.getOwningType)
              oce.setSource(factory.createTypeLiteralExp(o.getOwningType.getQualifiedNameList))
              oce.setReferredOperation(o)
              oce.setOclLibrary(oclLibrary)
              Full(oce)
            }
          }
        }
      }

      case s : StaticOperationCallExpCS => {
        val operation = s.getOperationName
        if (operation.eIsProxy)
          Empty
        else {
          val argumentsEOcl = s.getArguments.flatMap(arg => computeOclExpression(arg))
          if (argumentsEOcl.size != s.getArguments.size)
            Empty
          else {
            (oclType(s.getTypeName)).flatMap { tipe =>
              val oce = ExpressionsFactory.INSTANCE.createOperationCallExp
              oce.setSourceType(operation.getOwningType)
              val tle = factory.createTypeLiteralExp(operation.getOwningType.getQualifiedNameList)
              oce.setSource(tle)
              oce.setReferredOperation(operation)
              oce.getArgument.addAll(argumentsEOcl)
              oce.setOclLibrary(oclLibrary)
              allMappings.put(tle, s)
              allMappings.put(oce, s)
              Full(oce)
            }
          }
        }
      }

      case c : CollectionTypeLiteralExpCS => {
        (oclType(c.getCollectionType)).flatMap { oclType =>
          val tle = ExpressionsFactory.INSTANCE.createTypeLiteralExp
          tle.setReferredType(oclType)
          tle.setOclLibrary(oclLibrary)
          allMappings.put(tle, c)
          Full(tle)
        }
      }

      case t : TupleTypeLiteralExpCS => {
        (oclType(t.getTupleType)).flatMap { oclType =>
          val tle = ExpressionsFactory.INSTANCE.createTypeLiteralExp
          tle.setReferredType(oclType)
          tle.setOclLibrary(oclLibrary)
          allMappings.put(tle, t)
          Full(tle)
        }
      }

      case unknown => {
        unknown match {
          case u : AttributableEObject => iResource.addError("unknown element", u.eObject)
          case _                       => //ignore
        }
        Failure("unknown element: " + unknown)
      }
    }
  }

  protected val computeLiteralPart : Attributable ==> Box[CollectionLiteralPart] = {
    attr {
      case c : CollectionLiteralPartsOclExpCS => {
        (computeOclExpression(c.getOclExpression)).flatMap { oclExpressionEOcl => {
            val ci = factory.createCollectionItem(oclExpressionEOcl)
            allMappings.put(ci, c)
            Full(ci)
          }
        }
      }
      case c : CollectionRangeCS => {
        (computeOclExpression(c.getFrom)).flatMap { fromEOcl =>
          (computeOclExpression(c.getTo)).flatMap { toEOcl =>
            val ok_? = fromEOcl.getType match {
              case pt : PrimitiveType if pt.getKind != PrimitiveTypeKind.INTEGER =>
                yieldFailure("Collection Ranges can only contain Integers. Found: " + fromEOcl.getType.getName, c.getFrom)
              case ok => Full(true)
            }
            ok_?.flatMap { _ =>
              val ok_? = toEOcl.getType match {
                case pt : PrimitiveType if pt.getKind != PrimitiveTypeKind.INTEGER =>
                  yieldFailure("Collection Ranges can only contain Integers. Found: " + toEOcl.getType.getName, c.getTo)
                case ok => Full(true)
              }
              ok_?.flatMap { _ => {
                  val cr = factory.createCollectionRange(fromEOcl, toEOcl)
                  allMappings.put(cr, c)
                  Full(cr)
                }
              }
            }
          }
        }
      }
    }
  }

  protected val checkSelfcontainment : VariableDeclarationWithInitCS ==> Box[Boolean] = {
    attr { v =>
      // check for self containment in the assignment
      v.getInitialization match {
        // a = b
        // a = a = true
        case eq : EqualityOperationCallExpCS => {
        	Full(true)
//          eq.getSource match {
//            case n : NamedLiteralExpCS =>
//              if (n.getNamedElement.getName.equals(v.getVariableName.getSimpleName)) {
//                Full(true)
//              }
//              else None
//          }
        }
        // a = not a
        case lno : LogicalNotOperationCallExpCS => {
          Full(true)
//          lno.getTarget match {
//            case n : NamedLiteralExpCS =>
//              if (n.getNamedElement.getName.equals(v.getVariableName.getSimpleName)) {
//                Full(true)
//              }
//              else None
//          }
        }
        case _ => None
      }
    }
  }
}

