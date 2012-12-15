package org.dresdenocl.language.ocl.staticsemantics

import collection.JavaConversions._

import org.kiama._
import org.kiama.attribution.Attribution._
import org.dresdenocl.attributegrammar.integration.kiama._
import org.eclipse.emf.ecore._
import org.dresdenocl.language.ocl._
import org.dresdenocl.model._
import org.dresdenocl.pivotmodel._
import org.dresdenocl.language.ocl.resource.ocl.mopp._


import AttributableEObject._


object OclStaticSemanticsTransactions {

  private val parsedConstraints = new java.util.IdentityHashMap[IOclResource, java.util.List[Constraint]]()

  /**
   * If there are constraints and defined operations/properties in the model,
   * remove those for the current OclResource.
   */
  def startStaticSemanticsAnalysis(oclStaticSemantics: OclStaticSemantics, root: EObject) = {
    if (parsedConstraints.containsKey(oclStaticSemantics.resource))
      oclStaticSemantics.model.removeConstraints(parsedConstraints.get(oclStaticSemantics.resource))
    // no else.
    parsedConstraints.remove(oclStaticSemantics.resource)
  }

  def getAllDefs(oclStaticSemantics: OclStaticSemantics, root: EObject) = {
    // try to find all defs
    val allDefs = new collection.mutable.HashMap[Type, collection.mutable.Set[VariableDeclarationWithInitCS]] with collection.mutable.MultiMap[Type, VariableDeclarationWithInitCS]
    val allDefsOp = new collection.mutable.HashMap[Type, collection.mutable.Set[Tuple2[OperationDefinitionInDefCS, OclExpressionCS]]] with collection.mutable.MultiMap[Type, Tuple2[OperationDefinitionInDefCS, OclExpressionCS]]
    root match {
      case p: PackageDeclarationCS => {
        p.getContextDeclarations foreach { cd =>
          cd match {
            case c: ClassifierContextDeclarationCS => {
              c.getInvariantsAndDefinitions foreach { iad =>
                iad match {
                  case d: DefinitionExpCS => {
                    d.getDefinitionExpPart match {
                      case dp: DefinitionExpPropertyCS => {
                        (oclStaticSemantics.oclType(c.getTypeName)) match {
                          case Full(tipe) => allDefs.add(tipe, dp.getVariableDeclaration)
                          case other => // ignore
                        }
                      }
                      case dp: DefinitionExpOperationCS => {
                        oclStaticSemantics.oclType(c.getTypeName) match {
                          case Full(tipe) => allDefsOp.add(tipe, (dp.getOperation, dp.getOclExpression))
                          case other => // ignore
                        }
                      }
                    }
                  }
                  case other => // ignore
                }
              }
            }
            case other => // ignore
          }
        }
      }
      case other => //ignore
    }
    (allDefs, allDefsOp)
  }

  /**
   * After the semantic analysis, the parsed constraints and defined
   * properties/operations are added to the model.
   */
  def endStaticSemanticsAnalysis(model: IModel, resource: IOclResource, constraints: java.util.List[Constraint]) = {
    val iter = constraints.iterator
    while (iter.hasNext) {
      val constraint = iter.next
      val iter2 = constraint.getConstrainedElement.iterator
      while (iter2.hasNext) {
        val constrainedElement = iter2.next
        constrainedElement match {
          case t: Type => {
            t.getNamespace.addRule(constraint)
            // in case there is a defined feature, add it to the model, too
            constraint.getDefinedFeature match {
              case null => // do nothing
              case p: Property => t.addProperty(p)
              case o: Operation => t.addOperation(o)
            }
          }
          case o: Operation => Box.!!(o.getOwningType).map { ot =>
            ot.getNamespace.addRule(constraint)
          }
          case p: Property => Box.!!(p.getOwningType).map { ot =>
            ot.getNamespace.addRule(constraint)
          }
        }
      }
    }
    parsedConstraints += resource -> constraints
  }

}

