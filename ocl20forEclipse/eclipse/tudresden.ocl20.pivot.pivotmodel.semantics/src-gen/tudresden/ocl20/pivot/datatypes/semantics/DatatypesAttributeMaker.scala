package tudresden.ocl20.pivot.datatypes.semantics

trait DatatypesAttributeMaker extends tudresden.attributegrammar.integration.kiama.AttributeMaker {
	self : tudresden.attributegrammar.integration.kiama.AttributeMaker =>
	
	protected abstract override def registeredAttributables : PartialFunction[org.eclipse.emf.ecore.EObject, tudresden.attributegrammar.integration.kiama.AttributableEObject] = {
		case source => super.registeredAttributables(source)
	}
}
