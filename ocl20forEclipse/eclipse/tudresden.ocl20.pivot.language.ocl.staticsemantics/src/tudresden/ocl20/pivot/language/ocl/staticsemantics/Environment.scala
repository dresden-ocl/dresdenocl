package tudresden.ocl20.pivot.language.ocl.staticsemantics

import tudresden.ocl20.pivot.language.ocl._
import tudresden.ocl20.pivot.pivotmodel._
import tudresden.ocl20.pivot.essentialocl._
import types._
import expressions._
import factory._
import tudresden.ocl20.pivot.model._

import tudresden.attributegrammar.integration.kiama.util.CollectionConverterS2J._
import tudresden.attributegrammar.integration.kiama.util.CollectionConverterJ2S._

import Box._

// TODO: remove sourceExpression
abstract class Environment(
		parent : Option[Environment],
		namespace : Box[Namespace],
		self : Option[Variable],
		context : Option[Type],
		explicitVariables : collection.mutable.Set[Variable],
		var sourceExpression : Box[OclExpression]
	) {

  protected val model : IModel
  
  protected val oclLibrary : OclLibrary
  
  val factory = new EssentialOclFactory(oclLibrary, model)
  
  
  def nestedEnvironment : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, self, context, collection.mutable.Set(), sourceExpression)
  }
  
  def nestedEnvironment(ns : Namespace) : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), !!(ns), self, context, collection.mutable.Set(), sourceExpression)
  }
  
  def nestedEnvironment(newSelf : Variable) : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, Some(newSelf), context, collection.mutable.Set(), Full(factory.createVariableExp(newSelf)))
  }
  
  def nestedEnvironment(newSelf : Variable, newContext : Type) = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, Some(newSelf), Some(newContext), collection.mutable.Set(), sourceExpression)
  }
  
  def lookup(name : List[String]) : Box[Type] = {
    lookupLocal(name) match {
      case Full(t) => Full(t)
      case Empty | Failure(_, _, _) => parent match {
      	case Some(env) => env.lookup(name)
      	// last resort; type not found yet -> look everywhere
      	case None => {
      	  try {
      	  	Full(factory.findType(name))
      	  } catch {
      	    case e : EssentialOclFactoryException => Empty
      	  }
        }
      }
    }
  }
  
  private def lookupLocal(name : List[String]) : Box[Type] = {
    name.take(name.length - 1).foldLeft (namespace){(ns, name) =>
      ns.flatMap(n => !!(n.lookupNamespace(name)))
    }.flatMap{ns =>
      !!(ns.lookupType(name.last))
    }
  }
  
//  def lookupPropertyOnType(name : SimpleNameCS, static : Boolean) : Option[Property] = {
//    self match {
//      case Some(s) => {
//        val allProperties = s.getType.allProperties
//	    for (i <- 0 until allProperties.size) {
//	      val property = allProperties.get(i)
//	      if (property.getName == name.getSimpleName && property.isStatic == static)
//	        return Some(property)
//	    }
//	    None
//      }
//      case None => None
//    }
//  }
  
  def lookupPropertyOnType(t : Type, name : String, static : Boolean) : Box[Property] = {
    t.allProperties.find(p => p.getName == name && p.isStatic == static) ?~ 
      ("Cannot find property " + name + " on type " + t + ".")
  }
  
  def lookupPropertyOnTypeFuzzy(t : Type, name : String, static : Boolean) : List[Property] = {
    t.allProperties.filter(p => p.getName.startsWith(name) && p.isStatic == static).toList
  }
  
  def lookupExplicitVariable(name : String) : Box[Variable] = {
    explicitVariables.find(_.getName == name) ?~
      ("Cannot find variable " + name + ".")
  }
  
  def lookupVariableFuzzy(name : String) : List[Variable] = {
    explicitVariables.filter(_.getName.startsWith(name)).toList
  }
  
  def addExplicitVariable(variable : Variable) = explicitVariables += variable
  
  def setSourceExpression(sourceExpression : Box[OclExpression]) {
    this.sourceExpression = sourceExpression
  }
  
  def getSourceExpression = sourceExpression
  
  
  def getNamespace = namespace
  
  def getSelf = self.getOrElse {
    throw new OclStaticSemanticsException("'self' is not defined.")
  }
  
  def getContext = context.getOrElse {
    throw new OclStaticSemanticsException("Context is not defined.")
  }
  
}
