package tudresden.attributegrammar.integration.kiama;

import org.kiama.attribution._
import org.eclipse.emf.ecore._
import org.eclipse.emf.ecore.util.EcoreUtil._

/**
 * Mix-in this trait to transform EObjects to Attributables either by calling
 * eObject2Attributable() explicitly or implicitly.
 */
trait AttributeMaker {
  
  protected def registeredAttributables : PartialFunction[EObject, AttributableEObject] = {
    case null => null
    case source => throw new IllegalArgumentException("Cannot find Attributable for " + source)
  }
  
  private val _eObject2Attributable = new collection.jcl.IdentityHashMap[EObject, AttributableEObject]()
  
  implicit def eObject2Attributable(eObject : EObject) : AttributableEObject = {
		eObject match {
		  case a : AttributableEObject => a
		  case _ => _eObject2Attributable.getOrElseUpdate(eObject, registeredAttributables(eObject))
		}
  }
   
  def eObject2Attributable[T <: EObject](list : java.util.List[T]) : Iterator[AttributableEObject] = {
    val result = new collection.mutable.ListBuffer[AttributableEObject]
    val iter = list.iterator
    while(iter.hasNext) {
      result += iter.next
    }
    result.elements
  }
  
  private val copier = new Copier
  
  /**
   * Utility method to copy an EObject.
   */
  protected def copy(eObject : EObject) = {
    copier.copy(eObject)
  }
  
}
