package tudresden.ocl20.pivot.language.ocl.staticsemantics.impl

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._ 

object OclStaticSemanticsFactoryImplHelper {

  def createOclStaticSemantics(_resource : IOclResource) : OclStaticSemantics = 
    new OclStaticSemantics() with OclAttributesImpl {
      val resource = _resource
    }
  
}
