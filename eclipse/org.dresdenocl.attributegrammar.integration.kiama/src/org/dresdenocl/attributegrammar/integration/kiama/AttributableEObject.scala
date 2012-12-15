package org.dresdenocl.attributegrammar.integration.kiama

import java.lang.reflect.{ Proxy, InvocationHandler, Method }

import org.eclipse.emf.ecore._
import org.eclipse.emf.common.util._
import org.eclipse.emf.common.notify._

import org.kiama.attribution._

/**
 * <p>
 * Import this object to use EObjects that need to be attributed by Kiama.
 * There is a cache so that already adapted EObjects are not adapted over
 * and over again and their attribution is not lost over time.
 * </p>
 * 
 * <p>
 * There are some restrictions in the usage of the Kiama attribution
 * library when using the Ecore bridge:
 * <ul>
 * 	<li> Pivot Model types that are attributed need to be declared inside this
 * object. The field is called #pivotModelTypes.</li>
 * 	<li> The usage of the utility function "->" in org.kiama is not permitted 
 * as it circumvents the proxy mechanism. Use the attribution as a normal 
 * function call.</li>
 * </ul>
 * </p>
 */
object AttributableEObject {

  /*
   * Use a cache, so that the attribution of an EObject is not lost.
   * FIXME: this should be a WeakHashMap; unfortunately, the standard
   * implementation in the JDK SL only supports keys to be checked
   * against their equals method instead of identity. Since EObjects
   * are used as the key, the equals method cannot be overridden to
   * support such behaviour. 
   */
  private val cache = new java.util.IdentityHashMap[EObject, AttributableEObject]
  
  /**
   * Should be called when the attribution starts as all old values in
   * the cache of attributed EObjects are cleared.
   */
  def clearCache {
    cache.clear
  }

  /*
   * To get the right interface for Pivot Model elements to be part of
   * the proxy interface, we have to define what interface abstractions
   * we want to use.
   */
  private val pivotModelTypes = List("Namespace", "Operation")

  /**
   * Creates a new AttributableEObject and a dynamic Proxy object
   * so that calls to the original EObject and to the Attributable
   * interface are delegated correctly.
   */
  implicit def eObject2Attributable[T <: EObject](e: T): AttributableEObject = {
    e match {
      case a: AttributableEObject => a
      case _ =>
        if (cache.containsKey(e))
          cache.get(e)
        else {
          val attrEObject = new { val eObject = e } with AttributableEObject
          // find the appropriate abstraction (interface) of the EObject 
          val clazz = e.getClass
          clazz.getInterfaces find { e.getClass.getSimpleName startsWith _.getSimpleName } orElse {
            clazz.getInterfaces.find { pivotModelTypes contains _.getSimpleName }
          } match {
            case Some(interface) =>
              val proxy = Proxy.newProxyInstance(attrEObject.getClass.getClassLoader, Array(interface, classOf[AttributableEObject]),
                new InvocationHandler {
                  override def invoke(proxy: AnyRef, method: Method, args: Array[AnyRef]): AnyRef = {
                    // decide on which of the original objects the method call should be executed 
                    val obj =
                      if (classOf[EObject].isAssignableFrom(method.getDeclaringClass) || classOf[Notifier].isAssignableFrom(method.getDeclaringClass))
                        e
                      else
                        attrEObject
                    if (args == null)
                      method.invoke(obj)
                    else
                      method.invoke(obj, args: _*)
                  }
                }).asInstanceOf[AttributableEObject]
              cache put (e, proxy)
              proxy
            case None => throw new IllegalStateException("Cannot find interface for EObject " + e)
          }
        }
    }
  }
}

/**
 * Trait that implements Attributable and wraps the original
 * EObject. It therefore allows to use the Kiama attribution
 * on any EObject. To automatically convert EObjects to
 * Attributables when needed, use "import AttributableEObject._".
 */
trait AttributableEObject extends Attributable {

  import AttributableEObject._

  /**
   * The wrapped/decorated EObject.
   */
  val eObject: EObject

  /**
   * For caching purposes.
   */
  private var _prev: Option[Attributable] = null

  /**
   * Parent-Child connections are already available with Ecore;
   * therefore, do not do anything computationally here and just
   * set the parent to the container.
   */
  override def setChildConnections {
    if (eObject.eContainer != null)
      parent = eObject.eContainer
  }

  /**
   * Overridden to use the capabilities of EObject and its containment
   * structure.
   */
  override def prev[T]: T = {
    if (_prev == null) {
      if (parent != null) {
        val containmentFeature = eObject.eContainmentFeature
        if (containmentFeature.isMany) {
          val containedFeatures = parent match {
            case a: AttributableEObject => a.eObject.eGet(containmentFeature).asInstanceOf[EList[EObject]]
          }
          val index = containedFeatures.indexOf(eObject)
          if (index > 0)
            _prev = Some(containedFeatures.get(index - 1))
          else
            _prev = None
        } else
          _prev = None
      }
    }
    _prev.getOrElse(null).asInstanceOf[T]
  }

  override def productElement(index: Int) = eObject.eContents.get(index)

  override def productArity = eObject.eContents.size

  // FIXME mt: what to test here?
  override def canEqual(that: Any) = true

  /**
   * The equality test is passed on to the original EObject.
   */
  override def equals(other: Any) = eObject == other

  /**
   * The hashCode is the one from the original EObject.
   */
  override def hashCode = eObject.hashCode

}