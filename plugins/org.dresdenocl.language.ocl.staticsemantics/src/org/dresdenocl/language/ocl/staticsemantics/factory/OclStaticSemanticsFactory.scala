package org.dresdenocl.language.ocl.staticsemantics.factory

import org.dresdenocl.language.ocl.staticsemantics._
import org.dresdenocl.essentialocl.types._
import org.dresdenocl.language.ocl.resource.ocl.mopp._

trait OclStaticSemanticsFactory {

  def createOclStaticSemantics(_resource : IOclResource) : OclStaticSemantics 
}
