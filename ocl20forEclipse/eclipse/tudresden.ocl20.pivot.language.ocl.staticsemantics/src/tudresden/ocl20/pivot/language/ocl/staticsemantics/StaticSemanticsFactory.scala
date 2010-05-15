package tudresden.ocl20.pivot.language.ocl.staticsemantics

import tudresden.ocl20.pivot.model._
import tudresden.ocl20.pivot.essentialocl.types._
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._

object StaticSemanticsFactory {

  def createOclStaticSemantics(_model : IModel, _oclLibrary : OclLibrary, _resource : OclResource) : OclStaticSemantics =
    new OclStaticSemantics(){
      val model = _model
      val oclLibrary = _oclLibrary
      val resource = _resource
    }
  
}
