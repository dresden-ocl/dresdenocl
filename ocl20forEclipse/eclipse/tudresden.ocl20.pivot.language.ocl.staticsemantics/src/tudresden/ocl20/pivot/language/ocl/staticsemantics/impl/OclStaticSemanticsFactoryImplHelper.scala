package tudresden.ocl20.pivot.language.ocl.staticsemantics.impl

import tudresden.ocl20.pivot.language.ocl.staticsemantics._
import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._ 

object OclStaticSemanticsFactoryImplHelper {

  def createOclStaticSemantics(_resource : IOclResource) : OclStaticSemantics = 
    new {val resource = _resource} with OclStaticSemantics with OclAttributesImpl with OclParseTreeToEssentialOclImpl
  
}
