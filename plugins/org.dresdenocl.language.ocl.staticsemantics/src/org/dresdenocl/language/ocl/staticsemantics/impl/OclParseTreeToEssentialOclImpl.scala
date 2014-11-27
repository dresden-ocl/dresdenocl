package org.dresdenocl.language.ocl.staticsemantics.impl

import collection.JavaConversions._

import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import org.dresdenocl.attributegrammar.integration.kiama._
import org.dresdenocl.language.ocl._
import org.dresdenocl.pivotmodel._
import org.dresdenocl.essentialocl._
import expressions._
import types._
import factory._
import org.dresdenocl.model._
import org.dresdenocl.language.ocl.staticsemantics._
import Box._

import org.dresdenocl.language.ocl.resource.ocl.mopp._

import org.kiama._
import attribution._
import Attribution._


import AttributableEObject._


trait OclParseTreeToEssentialOclImpl extends OclParseTreeToEssentialOcl {selfType : OclStaticSemantics =>

  abstract override def __computeConstraints = {
    attr {
      case p : PackageDeclarationCS => {
        Full(p.getContextDeclarations.flatMap{c =>
          try {
          	computeConstraints(c)
          } catch {
            case e : Exception => yieldFailure(e, c)
          }
        }.flatten(i => i) toList)
      }
      
      case c : ClassifierContextDeclarationCS => {
        Full(c.getInvariantsAndDefinitions.flatMap{iad =>
          try {
            computeConstraint(iad)
          } catch {
            case e : Exception => yieldFailure(e, iad)
          }
        } toList)
      }
      
      case o : OperationContextDeclarationCS => {
        Full(o.getPrePostOrBodyDeclarations.flatMap{ppb =>
          try {
          	computeConstraint(ppb)
          } catch {
            case e : Exception => yieldFailure(e, ppb)
          }
        } toList)
      }
      
      case a : AttributeContextDeclarationCS => {
        if (a.getInitOrDeriveValue.size == 2) {
          if (a.getInitOrDeriveValue.get(0).isInstanceOf[InitValueCS] && 
              a.getInitOrDeriveValue.get(1).isInstanceOf[InitValueCS])
            yieldFailure("Cannot have more than one 'init' definition for an attribute.", a)
          else if (a.getInitOrDeriveValue.get(0).isInstanceOf[DeriveValueCS] && 
                   a.getInitOrDeriveValue.get(1).isInstanceOf[DeriveValueCS])
            yieldFailure("Cannot have more than one 'derive' definition for an attribute.", a)
          else Full(List())
        } else {
          Full(List())
        }.flatMap{_ =>
	        Full(a.getInitOrDeriveValue.flatMap{idv =>
	          try {
	          	computeConstraint(idv)
	          } catch {
	            case e : Exception => yieldFailure(e, idv)
	          }
	        } toList)
        }
      }
      
      case unknown => super.__computeConstraints(unknown)
    }
  }
  
}
