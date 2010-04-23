package tudresden.ocl20.pivot.language.ocl.staticsemantics

import tudresden.ocl20.pivot.model._
import tudresden.ocl20.pivot.pivotmodel._
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._

object OclStaticSemanticsTransactions {

  private val parsedConstraints = new collection.jcl.IdentityHashMap[OclResource, java.util.List[Constraint]]()
  
  /**
   * If there are constraints and defined operations/properties in the model,
   * remove those for the current OclResource.
   */
  def startStaticSemanticsAnalysis(model : IModel, resource : OclResource) {
     parsedConstraints.get(resource) match {
      case Some(constraints) => {
        model.removeConstraints(constraints)
      }
      case None => // do nothing
    }
  }
  
  /**
   * After the semantic analysis, the parsed constraints and defined
   * properties/operations are added to the model.
   */
  def endStaticSemanticsAnalysis(model : IModel, resource : OclResource, constraints : java.util.List[Constraint]) = {
    val iter = constraints.iterator
    while (iter.hasNext) {
      val constraint = iter.next
      val iter2 = constraint.getConstrainedElement.iterator
      while (iter2.hasNext) {
        val constrainedElement = iter2.next
        constrainedElement match {
          case t :Type => {
            t.getNamespace.addRule(constraint)
            // in case there is a defined feature, add it to the model, too
            constraint.getDefinedFeature match {
              case null => // do nothing
              case p : Property => t.addProperty(p)
              case o : Operation => t.addOperation(o)
            }
          }
          case o : Operation => o.getOwningType.getNamespace.addRule(constraint)
          case p : Property => p.getOwningType.getNamespace.addRule(constraint)
        }
      }
    }
    parsedConstraints += resource -> constraints
  }
  
}
