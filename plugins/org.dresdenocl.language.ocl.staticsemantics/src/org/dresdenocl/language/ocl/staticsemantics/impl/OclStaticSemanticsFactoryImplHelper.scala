package org.dresdenocl.language.ocl.staticsemantics.impl

import org.dresdenocl.language.ocl.staticsemantics._
import org.dresdenocl.language.ocl.resource.ocl.mopp._ 

object OclStaticSemanticsFactoryImplHelper {

  def createOclStaticSemantics(_resource : IOclResource) : OclStaticSemantics = 
    new {val resource = _resource} with OclStaticSemantics with OclAttributesImpl with OclParseTreeToEssentialOclImpl
  
}
