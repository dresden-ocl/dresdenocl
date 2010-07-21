package tudresden.ocl20.pivot.language.ocl.staticsemantics.impl

import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util._
import tudresden.attributegrammar.integration.kiama._
import tudresden.ocl20.pivot.language.ocl.semantics._
import tudresden.ocl20.pivot.pivotmodel._
import tudresden.ocl20.pivot.pivotmodel.semantics._
import tudresden.ocl20.pivot.essentialocl._
import expressions._
import types._
import factory._
import tudresden.ocl20.pivot.model._
import Box._

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp._

import tudresden.attributegrammar.integration.kiama.util.CollectionConverterS2J._
import tudresden.attributegrammar.integration.kiama.util.CollectionConverterJ2S._

import kiama.attribution.Attribution._


trait OclParseTreeToEssentialOclImpl extends OclParseTreeToEssentialOcl {selfType : OclStaticSemantics =>

  abstract override def __computeConstraints = {
    attr {
      case p@PackageDeclarationCS(contexts) => {
        Full(contexts.flatMap{c =>
          c->computeConstraints
        }.flatten(i => i))
      }
      
      case c@ClassifierContextDeclarationCS(_, invAndDefs) => {
        Full(invAndDefs.flatMap{iad =>
          computeConstraint(iad)
        })
      }
      
      case o@OperationContextDeclarationCS(_, prePostOrBodyDecls) => {
        Full(prePostOrBodyDecls.flatMap{ppb =>
          computeConstraint(ppb)
        })
      }
      
      case a@AttributeContextDeclarationCS(_, _, initOrDeriveValues) => {
        if (initOrDeriveValues.size == 2) {
          if (initOrDeriveValues(0).isInstanceOf[InitValueCS] && 
              initOrDeriveValues(1).isInstanceOf[InitValueCS])
            yieldFailure("Cannot have more than one 'init' definition for an attribute.", a)
          else if (initOrDeriveValues(0).isInstanceOf[DeriveValueCS] && 
                   initOrDeriveValues(1).isInstanceOf[DeriveValueCS])
            yieldFailure("Cannot have more than one 'derive' definition for an attribute.", a)
          else Full(List())
        } else {
          Full(List())
        }.flatMap{_ =>
	        Full(initOrDeriveValues.flatMap{idv =>
	          computeConstraint(idv)
	        })
        }
      }
      
      case unknown => super.__computeConstraints(unknown)
    }
  }
  
}
