package tudresden.ocl20.pivot.language.ocl.staticsemantics

import tudresden.ocl20.pivot.language.ocl._
import tudresden.ocl20.pivot.pivotmodel._
import tudresden.ocl20.pivot.essentialocl._
import types._
import expressions._
import factory._
import tudresden.ocl20.pivot.model._

import tudresden.attributegrammar.integration.kiama.util.CollectionConverterS2J._

abstract class Environment(
		parent : Option[Environment],
		namespace : Namespace,
		self : Option[Variable],
		context : Option[Type],
		explicitVariables : collection.mutable.Set[Variable]
	) {

  protected val model : IModel
  
  protected val oclLibrary : OclLibrary
  
  val factory = new EssentialOclFactory(oclLibrary, model)
  
  
  def nestedEnvironment : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, self, context, collection.mutable.Set())
  }
  
  def nestedEnvironment(ns : Namespace) : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), ns, self, context, collection.mutable.Set())
  }
  
  def nestedEnvironment(newSelf : Variable) : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, Some(newSelf), context, collection.mutable.Set())
  }
  
  def nestedEnvironment(newSelf : Variable, newContext : Type) = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, Some(newSelf), Some(newContext), collection.mutable.Set())
  }
  
  def lookup(name : List[String]) : Option[Type] = {
    lookupLocal(name) match {
      case Some(t) => Some(t)
      case None => parent match {
      	case Some(env) => env.lookup(name)
      	// last resort; type not found yet -> look everywhere
      	case None => {
      	  try {
      		Some(factory.findType(name))
      	  } catch {
      	    case e : EssentialOclFactoryException => None
      	  }
        }
      }
    }
  }
  
  private def lookupLocal(name : List[String]) : Option[Type] = {
    var currentNS = namespace
    name.take(name.length - 1).foreach {n =>
      currentNS = currentNS.lookupNamespace(n)
      if (currentNS == null)
        return None
    }
    val tipe = currentNS.lookupType(name.last)
    if (tipe == null)
      None
    else
      Some(tipe)
  }
  
  def lookupPropertyOnType(name : SimpleNameCS, static : Boolean) : Option[Property] = {
    self match {
      case Some(s) => {
        val allProperties = s.getType.allProperties
	    for (i <- 0 until allProperties.size) {
	      val property = allProperties.get(i)
	      if (property.getName == name.getSimpleName && property.isStatic == static)
	        return Some(property)
	    }
	    None
      }
      case None => None
    }
  }
  
  def lookupExplicitVariable(name : SimpleNameCS) : Option[Variable] = {
    explicitVariables.find(_.getName == name.getSimpleName)
  }
  
  def addExplicitVariable(variable : Variable) = explicitVariables += variable
  
  
  def getNamespace = namespace
  
  def getSelf = self.getOrElse {
    throw new OclStaticSemanticsException("'self' is not defined.")
  }
  
  def getContext = context.getOrElse {
    throw new OclStaticSemanticsException("Context is not defined.")
  }
  
}
