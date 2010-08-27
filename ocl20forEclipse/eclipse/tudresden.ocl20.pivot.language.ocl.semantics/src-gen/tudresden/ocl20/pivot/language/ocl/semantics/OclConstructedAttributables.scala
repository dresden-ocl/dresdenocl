package tudresden.ocl20.pivot.language.ocl.semantics




	private trait BracketExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.BracketExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.BracketExpCS
	
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait NamedLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS
	
		override def getNamedElement() = eObject.getNamedElement()
		override def setNamedElement(_NamedElement : tudresden.ocl20.pivot.pivotmodel.NamedElement) = eObject.setNamedElement(_NamedElement : tudresden.ocl20.pivot.pivotmodel.NamedElement)
	}


	private trait PathNameCSAttributable extends tudresden.ocl20.pivot.language.ocl.PathNameCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PathNameCS
	
		override def getSimpleName() = eObject.getSimpleName()
		override def setSimpleName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setSimpleName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getPathName() = eObject.getPathName()
		override def setPathName(_PathNameCS : tudresden.ocl20.pivot.language.ocl.PathNameCS) = eObject.setPathName(_PathNameCS : tudresden.ocl20.pivot.language.ocl.PathNameCS)
	}


	private trait SimpleNameCSAttributable extends tudresden.ocl20.pivot.language.ocl.SimpleNameCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.SimpleNameCS
	
		override def getSimpleName() = eObject.getSimpleName()
		override def setSimpleName(_EString : String) = eObject.setSimpleName(_EString : String)
	}






	private trait TypePathNameSimpleCSAttributable extends tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS
	
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setTypeName(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
	}


	private trait TypePathNameNestedCSAttributable extends tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS
	
		override def getNamespace() = eObject.getNamespace()
		override def setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) = eObject.setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace)
		override def getTypePathName() = eObject.getTypePathName()
		override def setTypePathName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS) = eObject.setTypePathName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS)
	}


	private trait TupleTypeCSAttributable extends tudresden.ocl20.pivot.language.ocl.TupleTypeCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.TupleTypeCS
	
		override def getVariableDeclarationList() = eObject.getVariableDeclarationList()
		override def setVariableDeclarationList(_VariableDeclarationWithoutInitListCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS) = eObject.setVariableDeclarationList(_VariableDeclarationWithoutInitListCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS)
	}


	private trait CollectionTypeLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS
	
		override def getCollectionType() = eObject.getCollectionType()
		override def setCollectionType(_CollectionTypeIdentifierCS : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS) = eObject.setCollectionType(_CollectionTypeIdentifierCS : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS)
	}


	private trait TupleTypeLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS
	
		override def getTupleType() = eObject.getTupleType()
		override def setTupleType(_TupleTypeCS : tudresden.ocl20.pivot.language.ocl.TupleTypeCS) = eObject.setTupleType(_TupleTypeCS : tudresden.ocl20.pivot.language.ocl.TupleTypeCS)
	}




	private trait VariableDeclarationWithInitCSAttributable extends tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS
	
		override def getVariableName() = eObject.getVariableName()
		override def setVariableName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setVariableName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
		override def getInitialization() = eObject.getInitialization()
		override def setInitialization(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setInitialization(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def getEqual() = eObject.getEqual()
		override def setEqual(_EString : String) = eObject.setEqual(_EString : String)
	}


	private trait VariableDeclarationWithoutInitCSAttributable extends tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS
	
		override def getVariableName() = eObject.getVariableName()
		override def setVariableName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setVariableName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
	}


	private trait VariableDeclarationWithInitListCSAttributable extends tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS
	
		override def getVariableDeclarations() = eObject.getVariableDeclarations()
	}


	private trait VariableDeclarationWithoutInitListCSAttributable extends tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS
	
		override def getVariableDeclarations() = eObject.getVariableDeclarations()
	}




	private trait EnumLiteralOrStaticPropertyExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS
	
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS) = eObject.setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS)
		override def getEnumLiteralOrStaticProperty() = eObject.getEnumLiteralOrStaticProperty()
		override def setEnumLiteralOrStaticProperty(_NamedElement : tudresden.ocl20.pivot.pivotmodel.NamedElement) = eObject.setEnumLiteralOrStaticProperty(_NamedElement : tudresden.ocl20.pivot.pivotmodel.NamedElement)
	}


	private trait CollectionLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS
	
		override def getCollectionType() = eObject.getCollectionType()
		override def setCollectionType(_CollectionTypeIdentifierCS : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS) = eObject.setCollectionType(_CollectionTypeIdentifierCS : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS)
		override def getCollectionLiteralParts() = eObject.getCollectionLiteralParts()
	}


	private trait CollectionTypeIdentifierCSAttributable extends tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS
	
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setTypeName(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getGenericType() = eObject.getGenericType()
		override def setGenericType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setGenericType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
	}




	private trait CollectionLiteralPartsOclExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS
	
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait CollectionRangeCSAttributable extends tudresden.ocl20.pivot.language.ocl.CollectionRangeCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.CollectionRangeCS
	
		override def getFrom() = eObject.getFrom()
		override def setFrom(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setFrom(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def getTo() = eObject.getTo()
		override def setTo(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTo(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}






	private trait IteratorExpVariableCSAttributable extends tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS
	
		override def getVariableName() = eObject.getVariableName()
		override def setVariableName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setVariableName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
	}


	private trait IteratorExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.IteratorExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.IteratorExpCS
	
		override def getIteratorName() = eObject.getIteratorName()
		override def setIteratorName(_EString : String) = eObject.setIteratorName(_EString : String)
		override def getIteratorVariables() = eObject.getIteratorVariables()
		override def getBodyExpression() = eObject.getBodyExpression()
		override def setBodyExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setBodyExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait IterateExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.IterateExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.IterateExpCS
	
		override def getIteratorVariable() = eObject.getIteratorVariable()
		override def setIteratorVariable(_IteratorExpVariableCS : tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS) = eObject.setIteratorVariable(_IteratorExpVariableCS : tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS)
		override def getResultVariable() = eObject.getResultVariable()
		override def setResultVariable(_VariableDeclarationWithInitCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS) = eObject.setResultVariable(_VariableDeclarationWithInitCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS)
		override def getBodyExpression() = eObject.getBodyExpression()
		override def setBodyExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setBodyExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}




	private trait NavigationCallExpAttributable extends tudresden.ocl20.pivot.language.ocl.NavigationCallExp with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.NavigationCallExp
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def getNavigationOperator() = eObject.getNavigationOperator()
		override def getFeatureCalls() = eObject.getFeatureCalls()
	}


	private trait OperationCallBaseExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS
	
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
		override def getArguments() = eObject.getArguments()
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}


	private trait PropertyCallBaseExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS
	
		override def getProperty() = eObject.getProperty()
		override def setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property) = eObject.setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}




	private trait ImplicitPropertyCallCSAttributable extends tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS
	
		override def getProperty() = eObject.getProperty()
		override def setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property) = eObject.setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}


	private trait ImplicitOperationCallCSAttributable extends tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS
	
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
		override def getArguments() = eObject.getArguments()
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}




	private trait PropertyCallOnSelfExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS
	
		override def getProperty() = eObject.getProperty()
		override def setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property) = eObject.setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}


	private trait PropertyCallExplicitPathExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def getPropertyPath() = eObject.getPropertyPath()
		override def setPropertyPath(_PathNameCS : tudresden.ocl20.pivot.language.ocl.PathNameCS) = eObject.setPropertyPath(_PathNameCS : tudresden.ocl20.pivot.language.ocl.PathNameCS)
		override def getPropertyName() = eObject.getPropertyName()
		override def setPropertyName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setPropertyName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}




	private trait OperationCallOnSelfExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS
	
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
		override def getArguments() = eObject.getArguments()
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}


	private trait StaticOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS
	
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS) = eObject.setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
		override def getArguments() = eObject.getArguments()
	}


	private trait UnaryOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS
	
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait LogicalNotOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS
	
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait OperationCallWithSourceExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}




	private trait AdditiveOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait MultOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait RelationalOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait EqualityOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait LogicalAndOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait LogicalOrOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait LogicalXorOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait LogicalImpliesOperationCallExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS
	
		override def getSource() = eObject.getSource()
		override def setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setSource(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_EString : String) = eObject.setOperationName(_EString : String)
		override def getTarget() = eObject.getTarget()
		override def setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setTarget(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait OperationCallWithImlicitSourceExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS
	
		override def getOperationName() = eObject.getOperationName()
		override def setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperationName(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
		override def getArguments() = eObject.getArguments()
		override def isIsMarkedPre() = eObject.isIsMarkedPre()
		override def setIsMarkedPre(_EBoolean : Boolean) = eObject.setIsMarkedPre(_EBoolean : Boolean)
	}


	private trait TupleLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS
	
		override def getVariableDeclarations() = eObject.getVariableDeclarations()
		override def setVariableDeclarations(_VariableDeclarationWithInitListCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS) = eObject.setVariableDeclarations(_VariableDeclarationWithInitListCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS)
	}




	private trait IntegerLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS
	
		override def getIntegerLiteral() = eObject.getIntegerLiteral()
		override def setIntegerLiteral(_EInt : Int) = eObject.setIntegerLiteral(_EInt : Int)
	}


	private trait RealLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS
	
		override def getIntValue() = eObject.getIntValue()
		override def setIntValue(_EInt : Int) = eObject.setIntValue(_EInt : Int)
		override def getRealValue() = eObject.getRealValue()
		override def setRealValue(_EInt : Int) = eObject.setRealValue(_EInt : Int)
		override def getNavigationOperator() = eObject.getNavigationOperator()
		override def setNavigationOperator(_EString : String) = eObject.setNavigationOperator(_EString : String)
	}


	private trait BooleanLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS
	
		override def isBooleanLiteral() = eObject.isBooleanLiteral()
		override def setBooleanLiteral(_EBoolean : Boolean) = eObject.setBooleanLiteral(_EBoolean : Boolean)
	}


	private trait StringLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS
	
		override def getStringLiteral() = eObject.getStringLiteral()
		override def setStringLiteral(_EString : String) = eObject.setStringLiteral(_EString : String)
	}


	private trait InvalidLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS
	
	}


	private trait NullLiteralExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS
	
	}


	private trait LetExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.LetExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.LetExpCS
	
		override def getVariableDeclarations() = eObject.getVariableDeclarations()
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait IfExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.IfExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.IfExpCS
	
		override def getCondition() = eObject.getCondition()
		override def setCondition(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setCondition(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def getThenBranch() = eObject.getThenBranch()
		override def setThenBranch(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setThenBranch(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
		override def getElseBranch() = eObject.getElseBranch()
		override def setElseBranch(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setElseBranch(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}




	private trait PackageDeclarationWithNamespaceCSAttributable extends tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS
	
		override def getContextDeclarations() = eObject.getContextDeclarations()
		override def getNestedNamespace() = eObject.getNestedNamespace()
		override def setNestedNamespace(_PackageDeclarationNestedNamespaceCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS) = eObject.setNestedNamespace(_PackageDeclarationNestedNamespaceCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS)
	}


	private trait PackageDeclarationNestedNamespaceCSAttributable extends tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS
	
		override def getNamespace() = eObject.getNamespace()
		override def setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) = eObject.setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace)
		override def getNestedNamespace() = eObject.getNestedNamespace()
		override def setNestedNamespace(_PackageDeclarationNestedNamespaceCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS) = eObject.setNestedNamespace(_PackageDeclarationNestedNamespaceCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS)
	}


	private trait PackageDeclarationWithoutNamespaceCSAttributable extends tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS
	
		override def getContextDeclarations() = eObject.getContextDeclarations()
	}




	private trait AttributeContextDeclarationCSAttributable extends tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS
	
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS) = eObject.setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS)
		override def getProperty() = eObject.getProperty()
		override def setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property) = eObject.setProperty(_Property : tudresden.ocl20.pivot.pivotmodel.Property)
		override def getType() = eObject.getType()
		override def setType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
		override def getInitOrDeriveValue() = eObject.getInitOrDeriveValue()
	}


	private trait ClassifierContextDeclarationCSAttributable extends tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS
	
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setTypeName(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
		override def getInvariantsAndDefinitions() = eObject.getInvariantsAndDefinitions()
	}


	private trait OperationContextDeclarationCSAttributable extends tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS
	
		override def getOperation() = eObject.getOperation()
		override def setOperation(_OperationDefinitionInContextCS : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS) = eObject.setOperation(_OperationDefinitionInContextCS : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS)
		override def getPrePostOrBodyDeclarations() = eObject.getPrePostOrBodyDeclarations()
	}




	private trait InitValueCSAttributable extends tudresden.ocl20.pivot.language.ocl.InitValueCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.InitValueCS
	
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait DeriveValueCSAttributable extends tudresden.ocl20.pivot.language.ocl.DeriveValueCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.DeriveValueCS
	
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}




	private trait InvariantExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.InvariantExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.InvariantExpCS
	
		override def getName() = eObject.getName()
		override def setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait DefinitionExpCSAttributable extends tudresden.ocl20.pivot.language.ocl.DefinitionExpCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.DefinitionExpCS
	
		override def isStatic() = eObject.isStatic()
		override def setStatic(_EBoolean : Boolean) = eObject.setStatic(_EBoolean : Boolean)
		override def getDefinitionExpPart() = eObject.getDefinitionExpPart()
		override def setDefinitionExpPart(_DefinitionExpPartCS : tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS) = eObject.setDefinitionExpPart(_DefinitionExpPartCS : tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS)
	}




	private trait DefinitionExpPropertyCSAttributable extends tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS
	
		override def getVariableDeclaration() = eObject.getVariableDeclaration()
		override def setVariableDeclaration(_VariableDeclarationWithInitCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS) = eObject.setVariableDeclaration(_VariableDeclarationWithInitCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS)
	}


	private trait DefinitionExpOperationCSAttributable extends tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS
	
		override def getOperation() = eObject.getOperation()
		override def setOperation(_OperationDefinitionInDefCS : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS) = eObject.setOperation(_OperationDefinitionInDefCS : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS)
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}




	private trait PreConditionDeclarationCSAttributable extends tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS
	
		override def getName() = eObject.getName()
		override def setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait PostConditionDeclarationCSAttributable extends tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS
	
		override def getName() = eObject.getName()
		override def setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}


	private trait BodyDeclarationCSAttributable extends tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS
	
		override def getName() = eObject.getName()
		override def setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) = eObject.setName(_SimpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS)
		override def getOclExpression() = eObject.getOclExpression()
		override def setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) = eObject.setOclExpression(_OclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS)
	}




	private trait OperationDefinitionInContextCSAttributable extends tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS
	
		override def getOperation() = eObject.getOperation()
		override def setOperation(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperation(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
		override def getParameters() = eObject.getParameters()
		override def getReturnType() = eObject.getReturnType()
		override def setReturnType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setReturnType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
		override def getTypeName() = eObject.getTypeName()
		override def setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS) = eObject.setTypeName(_TypePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS)
	}


	private trait OperationDefinitionInDefCSAttributable extends tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS
	
		override def getOperation() = eObject.getOperation()
		override def setOperation(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperation(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
		override def getParameters() = eObject.getParameters()
		override def getReturnType() = eObject.getReturnType()
		override def setReturnType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setReturnType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
	}


	private trait ParameterCSAttributable extends tudresden.ocl20.pivot.language.ocl.ParameterCS with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.language.ocl.ParameterCS
	
		override def getParameter() = eObject.getParameter()
		override def setParameter(_Parameter : tudresden.ocl20.pivot.pivotmodel.Parameter) = eObject.setParameter(_Parameter : tudresden.ocl20.pivot.pivotmodel.Parameter)
		override def getParameterType() = eObject.getParameterType()
		override def setParameterType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) = eObject.setParameterType(_TypeCS : tudresden.ocl20.pivot.language.ocl.TypeCS)
	}

