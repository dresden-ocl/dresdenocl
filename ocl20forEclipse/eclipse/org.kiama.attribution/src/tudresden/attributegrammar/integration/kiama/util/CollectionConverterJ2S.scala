package tudresden.attributegrammar.integration.kiama.util

object CollectionConverterJ2S {
 
  implicit def java2List[T] (source : java.util.List[T]) : List[T] = {
    val result = new collection.jcl.BufferWrapper[T]() { val underlying = source }
    result.toList
  }
  
}
