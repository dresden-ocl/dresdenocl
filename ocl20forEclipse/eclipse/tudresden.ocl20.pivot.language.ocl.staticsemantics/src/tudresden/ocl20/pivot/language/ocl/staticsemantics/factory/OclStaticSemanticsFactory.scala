package tudresden.ocl20.pivot.language.ocl.staticsemantics.factory

import tudresden.ocl20.pivot.essentialocl.types._
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._

trait OclStaticSemanticsFactory {

  def createOclStaticSemantics(_resource : IOclResource) : OclStaticSemantics  
}
