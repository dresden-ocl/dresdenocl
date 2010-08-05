package tudresden.attributegrammar.integration.kiama.util

object CollectionConverterS2J {

//  implicit def scala2JavaList[T] (source : Iterable[T]) : java.util.List[T] = {
//    val result = new java.util.ArrayList[T]()
//    source.foreach(result.add(_))
//    result
//  }
  
  implicit def scala2EList[T] (source : Iterable[T]) : org.eclipse.emf.common.util.EList[T]= {
    val result = new org.eclipse.emf.common.util.BasicEList[T]()
    source.foreach(result.add(_))
    result
  }
  
  def iterable2Collection[T] (source : Iterable[T]) : java.util.Collection[T] = {
    val result = new java.util.LinkedList[T]()
    source.foreach(result.add(_))
    result
  }
  
}
