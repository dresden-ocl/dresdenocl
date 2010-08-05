package tudresden.ocl20.pivot.pivotmodel.semantics

trait PivotmodelAttributeMaker extends tudresden.attributegrammar.integration.kiama.AttributeMaker {
	self : tudresden.attributegrammar.integration.kiama.AttributeMaker =>
	
	protected abstract override def registeredAttributables : PartialFunction[org.eclipse.emf.ecore.EObject, tudresden.attributegrammar.integration.kiama.AttributableEObject] = {
		case s : tudresden.ocl20.pivot.pivotmodel.AssociationProperty => new {val attributeMaker = self; val eObject = s} with NDirectionalPropertyAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Enumeration => new {val attributeMaker = self; val eObject = s} with EnumerationAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.PrimitiveType => new {val attributeMaker = self; val eObject = s} with PrimitiveTypeAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Expression => new {val attributeMaker = self; val eObject = s} with ExpressionAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Constraint => new {val attributeMaker = self; val eObject = s} with ConstraintAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.TypeArgument => new {val attributeMaker = self; val eObject = s} with TypeArgumentAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.TypeParameter => new {val attributeMaker = self; val eObject = s} with TypeParameterAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.ComplexGenericType => new {val attributeMaker = self; val eObject = s} with ComplexGenericTypeAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.ParameterGenericType => new {val attributeMaker = self; val eObject = s} with ParameterGenericTypeAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Parameter => new {val attributeMaker = self; val eObject = s} with ParameterAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Operation => new {val attributeMaker = self; val eObject = s} with OperationAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Property => new {val attributeMaker = self; val eObject = s} with PropertyAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral => new {val attributeMaker = self; val eObject = s} with EnumerationLiteralAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Type => new {val attributeMaker = self; val eObject = s} with TypeAttributable
		case s : tudresden.ocl20.pivot.pivotmodel.Namespace => new {val attributeMaker = self; val eObject = s} with NamespaceAttributable
		case source => super.registeredAttributables(source)
	}
}
