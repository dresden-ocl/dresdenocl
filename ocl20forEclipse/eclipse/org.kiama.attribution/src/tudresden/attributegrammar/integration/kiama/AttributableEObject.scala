package tudresden.attributegrammar.integration.kiama;

import org.kiama.attribution._

import org.eclipse.emf._
import ecore._
import common.util._
import common.notify._


trait AttributableEObject extends EObject with Attributable {
  
  protected type T <: EObject
  
  protected val eObject : T
  
  protected val attributeMaker : AttributeMaker
  
  private var _prev : Option[Attributable] = null
  
  def getEObject : EObject = eObject
  
  override protected def setChildConnections {
    parent =
    	if (eObject != null)
    		if (eObject.eContainer != null) {
    			attributeMaker.eObject2Attributable(eObject.eContainer)
    		}
    		else null
    	else null
  }
  
  override def children = {
    attributeMaker.eObject2Attributable(eObject.eContents)
  }
  
  override def prev : this.type = {
    if (_prev == null) {
      if (parent != null) {
        val containmentFeature = eObject.eContainmentFeature
        if (containmentFeature.isMany) {
        	val containedFeatures = parent.asInstanceOf[AttributableEObject].eGet(containmentFeature).asInstanceOf[EList[EObject]]
          val index = containedFeatures.indexOf(eObject)
          if (index > 0)
            _prev = Some(attributeMaker.eObject2Attributable(containedFeatures.get(index - 1)))
          else
            _prev = None
        } else
        	_prev = None
      }
    }
    _prev.getOrElse(null).asInstanceOf[this.type]
  }
  
  override def productArity = eObject.eContents.size
  
  override def productElement(index : Int) = {
    attributeMaker.eObject2Attributable(eObject.eContents.get(index))
  }
  
  override def equals(other : Any) = {
    other match {
      case e : AttributableEObject => e.eObject.equals(eObject)
      case e : EObject => eObject.equals(e)
      case _ => false
    }
  }
  
  override def hashCode = eObject.hashCode
  
  override def toString = eObject.toString
  
  override def eAllContents = eObject.eAllContents
  
  override def eClass = eObject.eClass
  
  override def eContainer = eObject.eContainer
  
  override def eContainingFeature = eObject.eContainingFeature
  
  override def eContainmentFeature = eObject.eContainmentFeature
  
  override def eContents = eObject.eContents
  
  override def eCrossReferences = eObject.eCrossReferences
  
  override def eGet(sf : EStructuralFeature) = eObject.eGet(sf)
  
  override def eGet(sf : EStructuralFeature, b : Boolean) = eObject.eGet(sf, b)
  
  override def eIsProxy = eObject.eIsProxy
  
  override def eIsSet(sf : EStructuralFeature) = eObject.eIsSet(sf)
  
  override def eResource = eObject.eResource
  
  override def eSet(sf : EStructuralFeature, o : AnyRef) = eObject.eSet(sf, o)
  
  override def eUnset(sf : EStructuralFeature) = eObject.eUnset(sf)
  
  override def eAdapters = eObject.eAdapters
  
  override def eDeliver = eObject.eDeliver
  
  override def eNotify(n : Notification) = eObject.eNotify(n)
  
  override def eSetDeliver(b : Boolean) = eObject.eSetDeliver(b)
  
  // this is due to EMF 2.6
  override def eInvoke(operation : EOperation, arguments : EList[_]) = eObject.eInvoke(operation, arguments)
  
}
