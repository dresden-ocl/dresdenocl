package tudresden.ocl20.pivot.language.ocl.staticsemantics

import tudresden.ocl20.pivot.essentialocl.types._
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._

object StaticSemanticsFactory {

  def createOclStaticSemantics(_resource : OclResource) : OclStaticSemantics =
    new OclStaticSemantics(){
      val resource = _resource
    }
  
}
