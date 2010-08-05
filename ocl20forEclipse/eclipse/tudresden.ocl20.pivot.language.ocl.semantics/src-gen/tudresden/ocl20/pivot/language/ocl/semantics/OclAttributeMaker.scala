package tudresden.ocl20.pivot.language.ocl.semantics

trait OclAttributeMaker extends tudresden.attributegrammar.integration.kiama.AttributeMaker {
	self : tudresden.attributegrammar.integration.kiama.AttributeMaker =>
	
	protected abstract override def registeredAttributables : PartialFunction[org.eclipse.emf.ecore.EObject, tudresden.attributegrammar.integration.kiama.AttributableEObject] = {
		case s : tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS => new {val attributeMaker = self; val eObject = s} with OperationCallWithImlicitSourceExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS => new {val attributeMaker = self; val eObject = s} with OperationCallOnSelfExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS => new {val attributeMaker = self; val eObject = s} with PropertyCallOnSelfExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS => new {val attributeMaker = self; val eObject = s} with ImplicitOperationCallCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS => new {val attributeMaker = self; val eObject = s} with ImplicitPropertyCallCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.ParameterCS => new {val attributeMaker = self; val eObject = s} with ParameterCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS => new {val attributeMaker = self; val eObject = s} with OperationDefinitionInDefCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS => new {val attributeMaker = self; val eObject = s} with OperationDefinitionInContextCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS => new {val attributeMaker = self; val eObject = s} with BodyDeclarationCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS => new {val attributeMaker = self; val eObject = s} with PostConditionDeclarationCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS => new {val attributeMaker = self; val eObject = s} with PreConditionDeclarationCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS => new {val attributeMaker = self; val eObject = s} with DefinitionExpOperationCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS => new {val attributeMaker = self; val eObject = s} with DefinitionExpPropertyCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.DefinitionExpCS => new {val attributeMaker = self; val eObject = s} with DefinitionExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.InvariantExpCS => new {val attributeMaker = self; val eObject = s} with InvariantExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.DeriveValueCS => new {val attributeMaker = self; val eObject = s} with DeriveValueCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.InitValueCS => new {val attributeMaker = self; val eObject = s} with InitValueCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS => new {val attributeMaker = self; val eObject = s} with OperationContextDeclarationCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS => new {val attributeMaker = self; val eObject = s} with ClassifierContextDeclarationCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS => new {val attributeMaker = self; val eObject = s} with AttributeContextDeclarationCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS => new {val attributeMaker = self; val eObject = s} with PackageDeclarationWithoutNamespaceCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS => new {val attributeMaker = self; val eObject = s} with PackageDeclarationNestedNamespaceCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS => new {val attributeMaker = self; val eObject = s} with PackageDeclarationWithNamespaceCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.IfExpCS => new {val attributeMaker = self; val eObject = s} with IfExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.LetExpCS => new {val attributeMaker = self; val eObject = s} with LetExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS => new {val attributeMaker = self; val eObject = s} with NullLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS => new {val attributeMaker = self; val eObject = s} with InvalidLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS => new {val attributeMaker = self; val eObject = s} with StringLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS => new {val attributeMaker = self; val eObject = s} with BooleanLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS => new {val attributeMaker = self; val eObject = s} with RealLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS => new {val attributeMaker = self; val eObject = s} with IntegerLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS => new {val attributeMaker = self; val eObject = s} with TupleLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with LogicalImpliesOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with LogicalXorOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with LogicalOrOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with LogicalAndOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with EqualityOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with RelationalOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with MultOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with AdditiveOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS => new {val attributeMaker = self; val eObject = s} with OperationCallWithSourceExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with LogicalNotOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with UnaryOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS => new {val attributeMaker = self; val eObject = s} with StaticOperationCallExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS => new {val attributeMaker = self; val eObject = s} with PropertyCallExplicitPathExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS => new {val attributeMaker = self; val eObject = s} with PropertyCallBaseExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS => new {val attributeMaker = self; val eObject = s} with OperationCallBaseExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.NavigationCallExp => new {val attributeMaker = self; val eObject = s} with NavigationCallExpAttributable
		case s : tudresden.ocl20.pivot.language.ocl.IterateExpCS => new {val attributeMaker = self; val eObject = s} with IterateExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.IteratorExpCS => new {val attributeMaker = self; val eObject = s} with IteratorExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS => new {val attributeMaker = self; val eObject = s} with IteratorExpVariableCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.CollectionRangeCS => new {val attributeMaker = self; val eObject = s} with CollectionRangeCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS => new {val attributeMaker = self; val eObject = s} with CollectionLiteralPartsOclExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS => new {val attributeMaker = self; val eObject = s} with CollectionTypeIdentifierCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS => new {val attributeMaker = self; val eObject = s} with CollectionLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS => new {val attributeMaker = self; val eObject = s} with EnumLiteralOrStaticPropertyExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS => new {val attributeMaker = self; val eObject = s} with VariableDeclarationWithoutInitListCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS => new {val attributeMaker = self; val eObject = s} with VariableDeclarationWithInitListCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS => new {val attributeMaker = self; val eObject = s} with VariableDeclarationWithoutInitCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS => new {val attributeMaker = self; val eObject = s} with VariableDeclarationWithInitCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.TupleTypeCS => new {val attributeMaker = self; val eObject = s} with TupleTypeCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS => new {val attributeMaker = self; val eObject = s} with TypePathNameNestedCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS => new {val attributeMaker = self; val eObject = s} with TypePathNameSimpleCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.SimpleNameCS => new {val attributeMaker = self; val eObject = s} with SimpleNameCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.PathNameCS => new {val attributeMaker = self; val eObject = s} with PathNameCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS => new {val attributeMaker = self; val eObject = s} with NamedLiteralExpCSAttributable
		case s : tudresden.ocl20.pivot.language.ocl.BracketExpCS => new {val attributeMaker = self; val eObject = s} with BracketExpCSAttributable
		case source => super.registeredAttributables(source)
	}
}
