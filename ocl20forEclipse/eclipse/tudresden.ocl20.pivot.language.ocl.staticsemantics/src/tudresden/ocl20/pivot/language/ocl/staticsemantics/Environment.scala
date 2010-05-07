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
		self : Box[Variable],
		context : Box[Type],
		explicitVariables : collection.mutable.Set[Variable],
		sourceExpression : Box[OclExpression]
	) {

  protected val model : IModel
  
  protected val oclLibrary : OclLibrary
  
  val factory = new EssentialOclFactory(oclLibrary, model)
  
  
  def nestedEnvironment : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, self, context, collection.mutable.Set(), sourceExpression)
  }
  
  def nestedEnvironmentNS(newNamespace : Box[Namespace]) : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), newNamespace, self, context, collection.mutable.Set(), sourceExpression)
  }
  
  def nestedEnvironmentSelf(newSelf : Box[Variable]) : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, newSelf, context, collection.mutable.Set(), newSelf match {case Full(s) => Full(factory.createVariableExp(s)); case Failure(msg, _, _) => Failure(msg, Empty, Empty); case Empty => Empty})
  }
  
  def nestedEnvironment(newSelf : Box[Variable], newContext : Box[Type]) : Environment = {
    new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, newSelf, newContext, collection.mutable.Set(), sourceExpression)
  }
  
  def nestedEnvironmentSourceExpression(newSourceExpression : Box[OclExpression]) : Environment = {
  	new {val model = this.model; val oclLibrary = this.oclLibrary} with Environment(Some(this), namespace, self, context, collection.mutable.Set(), newSourceExpression)
  }
  
  def lookup(name : List[String]) : Box[Type] = {
    oclLibrary.eContents.flatMap{
      _ match {
			  case t : Type => Full(t)
			  case unknown => Empty}
			}
    .find(_.getName == name.first) match {
      case Some(oclLibraryType) => Full(oclLibraryType)
      case None => {
		    lookupLocal(name) match {
		      case Full(t) => Full(t)
		      case Empty | Failure(_, _, _) => parent match {
		      	case Some(env) => env.lookup(name)
		      	// last resort; type not found yet -> look everywhere
		      	case None => {
		      	  try {
		      	  	Full(factory.findType(name))
		      	  } catch {
		      	    case e : EssentialOclFactoryException => Failure(e.getMessage, Empty, Empty)
		      	  }
		        }
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
  
  def getSourceExpression = sourceExpression
    
  def getNamespace = namespace
  
  def getSelf = self
  
  def getContext = context
  
}
