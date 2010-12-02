package tudresden.ocl20.pivot.language.ocl.semantics

import tudresden.attributegrammar.integration.kiama.util.CollectionConverterJ2S._
import tudresden.attributegrammar.integration.kiama.util.CollectionConverterS2J._


object OclExpressionCS {

	def unapply(__oclExpressionCS : tudresden.ocl20.pivot.language.ocl.OclExpressionCS) : Boolean = {
		true
	}

}


object BracketExpCS {
	def apply(
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __bracketExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createBracketExpCS
		
			__bracketExpCS.setOclExpression(_oclExpression)
		
		__bracketExpCS
		
	}

	def unapply(__bracketExpCS : tudresden.ocl20.pivot.language.ocl.BracketExpCS) : Some[tudresden.ocl20.pivot.language.ocl.OclExpressionCS] = {
		Some(
			__bracketExpCS.getOclExpression		
		)
	}

}


object NamedLiteralExpCS {
	def apply(
	          _namedElement : tudresden.ocl20.pivot.pivotmodel.NamedElement	          
	          ) = {
		
		val __namedLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createNamedLiteralExpCS
		
			__namedLiteralExpCS.setNamedElement(_namedElement)
		
		__namedLiteralExpCS
		
	}

	def unapply(__namedLiteralExpCS : tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS) : Boolean = {
		true
	}

}


object PathNameCS {
	def apply(
	          _simpleName : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _pathName : tudresden.ocl20.pivot.language.ocl.PathNameCS	          
	          ) = {
		
		val __pathNameCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPathNameCS
		
			__pathNameCS.setSimpleName(_simpleName)
			__pathNameCS.setPathName(_pathName)
		
		__pathNameCS
		
	}

	def unapply(__pathNameCS : tudresden.ocl20.pivot.language.ocl.PathNameCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.PathNameCS]] = {
		Some(
			__pathNameCS.getSimpleName,
			__pathNameCS.getPathName		
		)
	}

}


object SimpleNameCS {
	def apply(
	          _simpleName : String	          
	          ) = {
		
		val __simpleNameCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createSimpleNameCS
		
			__simpleNameCS.setSimpleName(_simpleName)
		
		__simpleNameCS
		
	}

	def unapply(__simpleNameCS : tudresden.ocl20.pivot.language.ocl.SimpleNameCS) : Some[String] = {
		Some(
			__simpleNameCS.getSimpleName		
		)
	}

}


object TypeCS {

	def unapply(__typeCS : tudresden.ocl20.pivot.language.ocl.TypeCS) : Boolean = {
		true
	}

}


object TypePathNameCS {

	def unapply(__typePathNameCS : tudresden.ocl20.pivot.language.ocl.TypePathNameCS) : Boolean = {
		true
	}

}


object TypePathNameSimpleCS {
	def apply(
	          _typeName : tudresden.ocl20.pivot.pivotmodel.Type	          
	          ) = {
		
		val __typePathNameSimpleCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createTypePathNameSimpleCS
		
			__typePathNameSimpleCS.setTypeName(_typeName)
		
		__typePathNameSimpleCS
		
	}

	def unapply(__typePathNameSimpleCS : tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS) : Boolean = {
		true
	}

}


object TypePathNameNestedCS {
	def apply(
	          _namespace : tudresden.ocl20.pivot.pivotmodel.Namespace,
	          _typePathName : tudresden.ocl20.pivot.language.ocl.TypePathNameCS	          
	          ) = {
		
		val __typePathNameNestedCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createTypePathNameNestedCS
		
			__typePathNameNestedCS.setNamespace(_namespace)
			__typePathNameNestedCS.setTypePathName(_typePathName)
		
		__typePathNameNestedCS
		
	}

	def unapply(__typePathNameNestedCS : tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS) : Some[tudresden.ocl20.pivot.language.ocl.TypePathNameCS] = {
		Some(
			__typePathNameNestedCS.getTypePathName		
		)
	}

}


object TupleTypeCS {
	def apply(
	          _variableDeclarationList : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS	          
	          ) = {
		
		val __tupleTypeCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createTupleTypeCS
		
			__tupleTypeCS.setVariableDeclarationList(_variableDeclarationList)
		
		__tupleTypeCS
		
	}

	def unapply(__tupleTypeCS : tudresden.ocl20.pivot.language.ocl.TupleTypeCS) : Some[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS] = {
		Some(
			__tupleTypeCS.getVariableDeclarationList		
		)
	}

}


object CollectionTypeLiteralExpCS {
	def apply(
	          _collectionType : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS	          
	          ) = {
		
		val __collectionTypeLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createCollectionTypeLiteralExpCS
		
			__collectionTypeLiteralExpCS.setCollectionType(_collectionType)
		
		__collectionTypeLiteralExpCS
		
	}

	def unapply(__collectionTypeLiteralExpCS : tudresden.ocl20.pivot.language.ocl.CollectionTypeLiteralExpCS) : Some[tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS] = {
		Some(
			__collectionTypeLiteralExpCS.getCollectionType		
		)
	}

}


object TupleTypeLiteralExpCS {
	def apply(
	          _tupleType : tudresden.ocl20.pivot.language.ocl.TupleTypeCS	          
	          ) = {
		
		val __tupleTypeLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createTupleTypeLiteralExpCS
		
			__tupleTypeLiteralExpCS.setTupleType(_tupleType)
		
		__tupleTypeLiteralExpCS
		
	}

	def unapply(__tupleTypeLiteralExpCS : tudresden.ocl20.pivot.language.ocl.TupleTypeLiteralExpCS) : Some[tudresden.ocl20.pivot.language.ocl.TupleTypeCS] = {
		Some(
			__tupleTypeLiteralExpCS.getTupleType		
		)
	}

}


object VariableDeclarationCS {

	def unapply(__variableDeclarationCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationCS) : Some[tudresden.ocl20.pivot.language.ocl.SimpleNameCS] = {
		Some(
			__variableDeclarationCS.getVariableName		
		)
	}

}


object VariableDeclarationWithInitCS {
	def apply(
	          _variableName : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypeCS,
	          _initialization : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _equal : String	          
	          ) = {
		
		val __variableDeclarationWithInitCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitCS
		
			__variableDeclarationWithInitCS.setVariableName(_variableName)
			__variableDeclarationWithInitCS.setTypeName(_typeName)
			__variableDeclarationWithInitCS.setInitialization(_initialization)
			__variableDeclarationWithInitCS.setEqual(_equal)
		
		__variableDeclarationWithInitCS
		
	}

	def unapply(__variableDeclarationWithInitCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.TypeCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, String]] = {
		Some(
			__variableDeclarationWithInitCS.getVariableName,
			__variableDeclarationWithInitCS.getTypeName,
			__variableDeclarationWithInitCS.getInitialization,
			__variableDeclarationWithInitCS.getEqual		
		)
	}

}


object VariableDeclarationWithoutInitCS {
	def apply(
	          _variableName : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypeCS	          
	          ) = {
		
		val __variableDeclarationWithoutInitCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitCS
		
			__variableDeclarationWithoutInitCS.setVariableName(_variableName)
			__variableDeclarationWithoutInitCS.setTypeName(_typeName)
		
		__variableDeclarationWithoutInitCS
		
	}

	def unapply(__variableDeclarationWithoutInitCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.TypeCS]] = {
		Some(
			__variableDeclarationWithoutInitCS.getVariableName,
			__variableDeclarationWithoutInitCS.getTypeName		
		)
	}

}


object VariableDeclarationWithInitListCS {
	def apply(
	          _variableDeclarations : List[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS]	          
	          ) = {
		
		val __variableDeclarationWithInitListCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithInitListCS
		
		if (_variableDeclarations != null)
			__variableDeclarationWithInitListCS.getVariableDeclarations.addAll(_variableDeclarations)
		
		__variableDeclarationWithInitListCS
		
	}

	def unapply(__variableDeclarationWithInitListCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS) : Some[List[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS]] = {
		Some(
			__variableDeclarationWithInitListCS.getVariableDeclarations		
		)
	}

}


object VariableDeclarationWithoutInitListCS {
	def apply(
	          _variableDeclarations : List[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS]	          
	          ) = {
		
		val __variableDeclarationWithoutInitListCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createVariableDeclarationWithoutInitListCS
		
		if (_variableDeclarations != null)
			__variableDeclarationWithoutInitListCS.getVariableDeclarations.addAll(_variableDeclarations)
		
		__variableDeclarationWithoutInitListCS
		
	}

	def unapply(__variableDeclarationWithoutInitListCS : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitListCS) : Some[List[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithoutInitCS]] = {
		Some(
			__variableDeclarationWithoutInitListCS.getVariableDeclarations		
		)
	}

}


object LiteralExpCS {

	def unapply(__literalExpCS : tudresden.ocl20.pivot.language.ocl.LiteralExpCS) : Boolean = {
		true
	}

}


object EnumLiteralOrStaticPropertyExpCS {
	def apply(
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypePathNameCS,
	          _enumLiteralOrStaticProperty : tudresden.ocl20.pivot.pivotmodel.NamedElement	          
	          ) = {
		
		val __enumLiteralOrStaticPropertyExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createEnumLiteralOrStaticPropertyExpCS
		
			__enumLiteralOrStaticPropertyExpCS.setTypeName(_typeName)
			__enumLiteralOrStaticPropertyExpCS.setEnumLiteralOrStaticProperty(_enumLiteralOrStaticProperty)
		
		__enumLiteralOrStaticPropertyExpCS
		
	}

	def unapply(__enumLiteralOrStaticPropertyExpCS : tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS) : Some[tudresden.ocl20.pivot.language.ocl.TypePathNameCS] = {
		Some(
			__enumLiteralOrStaticPropertyExpCS.getTypeName		
		)
	}

}


object CollectionLiteralExpCS {
	def apply(
	          _collectionType : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS,
	          _collectionLiteralParts : List[tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS]	          
	          ) = {
		
		val __collectionLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralExpCS
		
			__collectionLiteralExpCS.setCollectionType(_collectionType)
		if (_collectionLiteralParts != null)
			__collectionLiteralExpCS.getCollectionLiteralParts.addAll(_collectionLiteralParts)
		
		__collectionLiteralExpCS
		
	}

	def unapply(__collectionLiteralExpCS : tudresden.ocl20.pivot.language.ocl.CollectionLiteralExpCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS, List[tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS]]] = {
		Some(
			__collectionLiteralExpCS.getCollectionType,
			__collectionLiteralExpCS.getCollectionLiteralParts		
		)
	}

}


object CollectionTypeIdentifierCS {
	def apply(
	          _typeName : tudresden.ocl20.pivot.pivotmodel.Type,
	          _genericType : tudresden.ocl20.pivot.language.ocl.TypeCS	          
	          ) = {
		
		val __collectionTypeIdentifierCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createCollectionTypeIdentifierCS
		
			__collectionTypeIdentifierCS.setTypeName(_typeName)
			__collectionTypeIdentifierCS.setGenericType(_genericType)
		
		__collectionTypeIdentifierCS
		
	}

	def unapply(__collectionTypeIdentifierCS : tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS) : Some[tudresden.ocl20.pivot.language.ocl.TypeCS] = {
		Some(
			__collectionTypeIdentifierCS.getGenericType		
		)
	}

}


object CollectionLiteralPartsCS {

	def unapply(__collectionLiteralPartsCS : tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsCS) : Boolean = {
		true
	}

}


object CollectionLiteralPartsOclExpCS {
	def apply(
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __collectionLiteralPartsOclExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createCollectionLiteralPartsOclExpCS
		
			__collectionLiteralPartsOclExpCS.setOclExpression(_oclExpression)
		
		__collectionLiteralPartsOclExpCS
		
	}

	def unapply(__collectionLiteralPartsOclExpCS : tudresden.ocl20.pivot.language.ocl.CollectionLiteralPartsOclExpCS) : Some[tudresden.ocl20.pivot.language.ocl.OclExpressionCS] = {
		Some(
			__collectionLiteralPartsOclExpCS.getOclExpression		
		)
	}

}


object CollectionRangeCS {
	def apply(
	          _from : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _to : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __collectionRangeCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createCollectionRangeCS
		
			__collectionRangeCS.setFrom(_from)
			__collectionRangeCS.setTo(_to)
		
		__collectionRangeCS
		
	}

	def unapply(__collectionRangeCS : tudresden.ocl20.pivot.language.ocl.CollectionRangeCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__collectionRangeCS.getFrom,
			__collectionRangeCS.getTo		
		)
	}

}


object CallExpCS {

	def unapply(__callExpCS : tudresden.ocl20.pivot.language.ocl.CallExpCS) : Boolean = {
		true
	}

}


object LoopExpCS {

	def unapply(__loopExpCS : tudresden.ocl20.pivot.language.ocl.LoopExpCS) : Boolean = {
		true
	}

}


object IteratorExpVariableCS {
	def apply(
	          _variableName : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypeCS	          
	          ) = {
		
		val __iteratorExpVariableCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createIteratorExpVariableCS
		
			__iteratorExpVariableCS.setVariableName(_variableName)
			__iteratorExpVariableCS.setTypeName(_typeName)
		
		__iteratorExpVariableCS
		
	}

	def unapply(__iteratorExpVariableCS : tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.TypeCS]] = {
		Some(
			__iteratorExpVariableCS.getVariableName,
			__iteratorExpVariableCS.getTypeName		
		)
	}

}


object IteratorExpCS {
	def apply(
	          _iteratorName : String,
	          _iteratorVariables : List[tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS],
	          _bodyExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __iteratorExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createIteratorExpCS
		
			__iteratorExpCS.setIteratorName(_iteratorName)
		if (_iteratorVariables != null)
			__iteratorExpCS.getIteratorVariables.addAll(_iteratorVariables)
			__iteratorExpCS.setBodyExpression(_bodyExpression)
		
		__iteratorExpCS
		
	}

	def unapply(__iteratorExpCS : tudresden.ocl20.pivot.language.ocl.IteratorExpCS) : Some[Tuple3[List[tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS], tudresden.ocl20.pivot.language.ocl.OclExpressionCS, String]] = {
		Some(
			__iteratorExpCS.getIteratorVariables,
			__iteratorExpCS.getBodyExpression,
			__iteratorExpCS.getIteratorName		
		)
	}

}


object IterateExpCS {
	def apply(
	          _iteratorVariable : tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS,
	          _resultVariable : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS,
	          _bodyExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __iterateExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createIterateExpCS
		
			__iterateExpCS.setIteratorVariable(_iteratorVariable)
			__iterateExpCS.setResultVariable(_resultVariable)
			__iterateExpCS.setBodyExpression(_bodyExpression)
		
		__iterateExpCS
		
	}

	def unapply(__iterateExpCS : tudresden.ocl20.pivot.language.ocl.IterateExpCS) : Some[Tuple3[tudresden.ocl20.pivot.language.ocl.IteratorExpVariableCS, tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__iterateExpCS.getIteratorVariable,
			__iterateExpCS.getResultVariable,
			__iterateExpCS.getBodyExpression		
		)
	}

}


object FeatureCallExpCS {

	def unapply(__featureCallExpCS : tudresden.ocl20.pivot.language.ocl.FeatureCallExpCS) : Boolean = {
		true
	}

}


object NavigationCallExp {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _navigationOperator : List[String],
	          _featureCalls : List[tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS]	          
	          ) = {
		
		val __navigationCallExp = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createNavigationCallExp
		
			__navigationCallExp.setSource(_source)
		if (_navigationOperator != null)
			__navigationCallExp.getNavigationOperator.addAll(_navigationOperator)
		if (_featureCalls != null)
			__navigationCallExp.getFeatureCalls.addAll(_featureCalls)
		
		__navigationCallExp
		
	}

	def unapply(__navigationCallExp : tudresden.ocl20.pivot.language.ocl.NavigationCallExp) : Some[Tuple3[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, List[tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS], List[String]]] = {
		Some(
			__navigationCallExp.getSource,
			__navigationCallExp.getFeatureCalls,
			__navigationCallExp.getNavigationOperator		
		)
	}

}


object OperationCallBaseExpCS {
	def apply(
	          _operationName : tudresden.ocl20.pivot.pivotmodel.Operation,
	          _arguments : List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS],
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __operationCallBaseExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createOperationCallBaseExpCS
		
			__operationCallBaseExpCS.setOperationName(_operationName)
		if (_arguments != null)
			__operationCallBaseExpCS.getArguments.addAll(_arguments)
			__operationCallBaseExpCS.setIsMarkedPre(_isMarkedPre)
		
		__operationCallBaseExpCS
		
	}

	def unapply(__operationCallBaseExpCS : tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS], Boolean]] = {
		Some(
			__operationCallBaseExpCS.getArguments,
			__operationCallBaseExpCS.isIsMarkedPre		
		)
	}

}


object PropertyCallBaseExpCS {
	def apply(
	          _property : tudresden.ocl20.pivot.pivotmodel.Property,
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __propertyCallBaseExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPropertyCallBaseExpCS
		
			__propertyCallBaseExpCS.setProperty(_property)
			__propertyCallBaseExpCS.setIsMarkedPre(_isMarkedPre)
		
		__propertyCallBaseExpCS
		
	}

	def unapply(__propertyCallBaseExpCS : tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS) : Some[Boolean] = {
		Some(
			__propertyCallBaseExpCS.isIsMarkedPre		
		)
	}

}


object ImplicitFeatureCallCS {

	def unapply(__implicitFeatureCallCS : tudresden.ocl20.pivot.language.ocl.ImplicitFeatureCallCS) : Boolean = {
		true
	}

}


object ImplicitPropertyCallCS {
	def apply(
	          _property : tudresden.ocl20.pivot.pivotmodel.Property,
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __implicitPropertyCallCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createImplicitPropertyCallCS
		
			__implicitPropertyCallCS.setProperty(_property)
			__implicitPropertyCallCS.setIsMarkedPre(_isMarkedPre)
		
		__implicitPropertyCallCS
		
	}

	def unapply(__implicitPropertyCallCS : tudresden.ocl20.pivot.language.ocl.ImplicitPropertyCallCS) : Some[Boolean] = {
		Some(
			__implicitPropertyCallCS.isIsMarkedPre		
		)
	}

}


object ImplicitOperationCallCS {
	def apply(
	          _operationName : tudresden.ocl20.pivot.pivotmodel.Operation,
	          _arguments : List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS],
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __implicitOperationCallCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createImplicitOperationCallCS
		
			__implicitOperationCallCS.setOperationName(_operationName)
		if (_arguments != null)
			__implicitOperationCallCS.getArguments.addAll(_arguments)
			__implicitOperationCallCS.setIsMarkedPre(_isMarkedPre)
		
		__implicitOperationCallCS
		
	}

	def unapply(__implicitOperationCallCS : tudresden.ocl20.pivot.language.ocl.ImplicitOperationCallCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS], Boolean]] = {
		Some(
			__implicitOperationCallCS.getArguments,
			__implicitOperationCallCS.isIsMarkedPre		
		)
	}

}


object PropertyCallExpCS {

	def unapply(__propertyCallExpCS : tudresden.ocl20.pivot.language.ocl.PropertyCallExpCS) : Boolean = {
		true
	}

}


object PropertyCallOnSelfExpCS {
	def apply(
	          _property : tudresden.ocl20.pivot.pivotmodel.Property,
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __propertyCallOnSelfExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPropertyCallOnSelfExpCS
		
			__propertyCallOnSelfExpCS.setProperty(_property)
			__propertyCallOnSelfExpCS.setIsMarkedPre(_isMarkedPre)
		
		__propertyCallOnSelfExpCS
		
	}

	def unapply(__propertyCallOnSelfExpCS : tudresden.ocl20.pivot.language.ocl.PropertyCallOnSelfExpCS) : Some[Boolean] = {
		Some(
			__propertyCallOnSelfExpCS.isIsMarkedPre		
		)
	}

}


object PropertyCallExplicitPathExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _propertyPath : tudresden.ocl20.pivot.language.ocl.PathNameCS,
	          _propertyName : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __propertyCallExplicitPathExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPropertyCallExplicitPathExpCS
		
			__propertyCallExplicitPathExpCS.setSource(_source)
			__propertyCallExplicitPathExpCS.setPropertyPath(_propertyPath)
			__propertyCallExplicitPathExpCS.setPropertyName(_propertyName)
			__propertyCallExplicitPathExpCS.setIsMarkedPre(_isMarkedPre)
		
		__propertyCallExplicitPathExpCS
		
	}

	def unapply(__propertyCallExplicitPathExpCS : tudresden.ocl20.pivot.language.ocl.PropertyCallExplicitPathExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.PathNameCS, tudresden.ocl20.pivot.language.ocl.SimpleNameCS, Boolean]] = {
		Some(
			__propertyCallExplicitPathExpCS.getSource,
			__propertyCallExplicitPathExpCS.getPropertyPath,
			__propertyCallExplicitPathExpCS.getPropertyName,
			__propertyCallExplicitPathExpCS.isIsMarkedPre		
		)
	}

}


object OperationCallExpCS {

	def unapply(__operationCallExpCS : tudresden.ocl20.pivot.language.ocl.OperationCallExpCS) : Boolean = {
		true
	}

}


object OperationCallOnSelfExpCS {
	def apply(
	          _operationName : tudresden.ocl20.pivot.pivotmodel.Operation,
	          _arguments : List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS],
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __operationCallOnSelfExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createOperationCallOnSelfExpCS
		
			__operationCallOnSelfExpCS.setOperationName(_operationName)
		if (_arguments != null)
			__operationCallOnSelfExpCS.getArguments.addAll(_arguments)
			__operationCallOnSelfExpCS.setIsMarkedPre(_isMarkedPre)
		
		__operationCallOnSelfExpCS
		
	}

	def unapply(__operationCallOnSelfExpCS : tudresden.ocl20.pivot.language.ocl.OperationCallOnSelfExpCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS], Boolean]] = {
		Some(
			__operationCallOnSelfExpCS.getArguments,
			__operationCallOnSelfExpCS.isIsMarkedPre		
		)
	}

}


object StaticOperationCallExpCS {
	def apply(
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypePathNameCS,
	          _operationName : tudresden.ocl20.pivot.pivotmodel.Operation,
	          _arguments : List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS]	          
	          ) = {
		
		val __staticOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createStaticOperationCallExpCS
		
			__staticOperationCallExpCS.setTypeName(_typeName)
			__staticOperationCallExpCS.setOperationName(_operationName)
		if (_arguments != null)
			__staticOperationCallExpCS.getArguments.addAll(_arguments)
		
		__staticOperationCallExpCS
		
	}

	def unapply(__staticOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.TypePathNameCS, List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS]]] = {
		Some(
			__staticOperationCallExpCS.getTypeName,
			__staticOperationCallExpCS.getArguments		
		)
	}

}


object UnaryOperationCallExpCS {
	def apply(
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __unaryOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createUnaryOperationCallExpCS
		
			__unaryOperationCallExpCS.setOperationName(_operationName)
			__unaryOperationCallExpCS.setTarget(_target)
		
		__unaryOperationCallExpCS
		
	}

	def unapply(__unaryOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.UnaryOperationCallExpCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, String]] = {
		Some(
			__unaryOperationCallExpCS.getTarget,
			__unaryOperationCallExpCS.getOperationName		
		)
	}

}


object LogicalNotOperationCallExpCS {
	def apply(
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __logicalNotOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createLogicalNotOperationCallExpCS
		
			__logicalNotOperationCallExpCS.setOperationName(_operationName)
			__logicalNotOperationCallExpCS.setTarget(_target)
		
		__logicalNotOperationCallExpCS
		
	}

	def unapply(__logicalNotOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.LogicalNotOperationCallExpCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, String]] = {
		Some(
			__logicalNotOperationCallExpCS.getTarget,
			__logicalNotOperationCallExpCS.getOperationName		
		)
	}

}


object OperationCallWithSourceExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __operationCallWithSourceExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createOperationCallWithSourceExpCS
		
			__operationCallWithSourceExpCS.setSource(_source)
			__operationCallWithSourceExpCS.setIsMarkedPre(_isMarkedPre)
		
		__operationCallWithSourceExpCS
		
	}

	def unapply(__operationCallWithSourceExpCS : tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean]] = {
		Some(
			__operationCallWithSourceExpCS.getSource,
			__operationCallWithSourceExpCS.isIsMarkedPre		
		)
	}

}


object OperationCallBinaryExpCS {

	def unapply(__operationCallBinaryExpCS : tudresden.ocl20.pivot.language.ocl.OperationCallBinaryExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__operationCallBinaryExpCS.getSource,
			__operationCallBinaryExpCS.getTarget,
			__operationCallBinaryExpCS.isIsMarkedPre,
			__operationCallBinaryExpCS.getOperationName		
		)
	}

}


object AdditiveOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __additiveOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createAdditiveOperationCallExpCS
		
			__additiveOperationCallExpCS.setSource(_source)
			__additiveOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__additiveOperationCallExpCS.setOperationName(_operationName)
			__additiveOperationCallExpCS.setTarget(_target)
		
		__additiveOperationCallExpCS
		
	}

	def unapply(__additiveOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.AdditiveOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__additiveOperationCallExpCS.getSource,
			__additiveOperationCallExpCS.getTarget,
			__additiveOperationCallExpCS.isIsMarkedPre,
			__additiveOperationCallExpCS.getOperationName		
		)
	}

}


object MultOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __multOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createMultOperationCallExpCS
		
			__multOperationCallExpCS.setSource(_source)
			__multOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__multOperationCallExpCS.setOperationName(_operationName)
			__multOperationCallExpCS.setTarget(_target)
		
		__multOperationCallExpCS
		
	}

	def unapply(__multOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.MultOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__multOperationCallExpCS.getSource,
			__multOperationCallExpCS.getTarget,
			__multOperationCallExpCS.isIsMarkedPre,
			__multOperationCallExpCS.getOperationName		
		)
	}

}


object RelationalOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __relationalOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createRelationalOperationCallExpCS
		
			__relationalOperationCallExpCS.setSource(_source)
			__relationalOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__relationalOperationCallExpCS.setOperationName(_operationName)
			__relationalOperationCallExpCS.setTarget(_target)
		
		__relationalOperationCallExpCS
		
	}

	def unapply(__relationalOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.RelationalOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__relationalOperationCallExpCS.getSource,
			__relationalOperationCallExpCS.getTarget,
			__relationalOperationCallExpCS.isIsMarkedPre,
			__relationalOperationCallExpCS.getOperationName		
		)
	}

}


object EqualityOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __equalityOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createEqualityOperationCallExpCS
		
			__equalityOperationCallExpCS.setSource(_source)
			__equalityOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__equalityOperationCallExpCS.setOperationName(_operationName)
			__equalityOperationCallExpCS.setTarget(_target)
		
		__equalityOperationCallExpCS
		
	}

	def unapply(__equalityOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.EqualityOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__equalityOperationCallExpCS.getSource,
			__equalityOperationCallExpCS.getTarget,
			__equalityOperationCallExpCS.isIsMarkedPre,
			__equalityOperationCallExpCS.getOperationName		
		)
	}

}


object LogicalAndOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __logicalAndOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createLogicalAndOperationCallExpCS
		
			__logicalAndOperationCallExpCS.setSource(_source)
			__logicalAndOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__logicalAndOperationCallExpCS.setOperationName(_operationName)
			__logicalAndOperationCallExpCS.setTarget(_target)
		
		__logicalAndOperationCallExpCS
		
	}

	def unapply(__logicalAndOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.LogicalAndOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__logicalAndOperationCallExpCS.getSource,
			__logicalAndOperationCallExpCS.getTarget,
			__logicalAndOperationCallExpCS.isIsMarkedPre,
			__logicalAndOperationCallExpCS.getOperationName		
		)
	}

}


object LogicalOrOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __logicalOrOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createLogicalOrOperationCallExpCS
		
			__logicalOrOperationCallExpCS.setSource(_source)
			__logicalOrOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__logicalOrOperationCallExpCS.setOperationName(_operationName)
			__logicalOrOperationCallExpCS.setTarget(_target)
		
		__logicalOrOperationCallExpCS
		
	}

	def unapply(__logicalOrOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.LogicalOrOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__logicalOrOperationCallExpCS.getSource,
			__logicalOrOperationCallExpCS.getTarget,
			__logicalOrOperationCallExpCS.isIsMarkedPre,
			__logicalOrOperationCallExpCS.getOperationName		
		)
	}

}


object LogicalXorOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __logicalXorOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createLogicalXorOperationCallExpCS
		
			__logicalXorOperationCallExpCS.setSource(_source)
			__logicalXorOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__logicalXorOperationCallExpCS.setOperationName(_operationName)
			__logicalXorOperationCallExpCS.setTarget(_target)
		
		__logicalXorOperationCallExpCS
		
	}

	def unapply(__logicalXorOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.LogicalXorOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__logicalXorOperationCallExpCS.getSource,
			__logicalXorOperationCallExpCS.getTarget,
			__logicalXorOperationCallExpCS.isIsMarkedPre,
			__logicalXorOperationCallExpCS.getOperationName		
		)
	}

}


object LogicalImpliesOperationCallExpCS {
	def apply(
	          _source : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _isMarkedPre : Boolean,
	          _operationName : String,
	          _target : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __logicalImpliesOperationCallExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createLogicalImpliesOperationCallExpCS
		
			__logicalImpliesOperationCallExpCS.setSource(_source)
			__logicalImpliesOperationCallExpCS.setIsMarkedPre(_isMarkedPre)
			__logicalImpliesOperationCallExpCS.setOperationName(_operationName)
			__logicalImpliesOperationCallExpCS.setTarget(_target)
		
		__logicalImpliesOperationCallExpCS
		
	}

	def unapply(__logicalImpliesOperationCallExpCS : tudresden.ocl20.pivot.language.ocl.LogicalImpliesOperationCallExpCS) : Some[Tuple4[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, Boolean, String]] = {
		Some(
			__logicalImpliesOperationCallExpCS.getSource,
			__logicalImpliesOperationCallExpCS.getTarget,
			__logicalImpliesOperationCallExpCS.isIsMarkedPre,
			__logicalImpliesOperationCallExpCS.getOperationName		
		)
	}

}


object OperationCallWithImlicitSourceExpCS {
	def apply(
	          _operationName : tudresden.ocl20.pivot.pivotmodel.Operation,
	          _arguments : List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS],
	          _isMarkedPre : Boolean	          
	          ) = {
		
		val __operationCallWithImlicitSourceExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createOperationCallWithImlicitSourceExpCS
		
			__operationCallWithImlicitSourceExpCS.setOperationName(_operationName)
		if (_arguments != null)
			__operationCallWithImlicitSourceExpCS.getArguments.addAll(_arguments)
			__operationCallWithImlicitSourceExpCS.setIsMarkedPre(_isMarkedPre)
		
		__operationCallWithImlicitSourceExpCS
		
	}

	def unapply(__operationCallWithImlicitSourceExpCS : tudresden.ocl20.pivot.language.ocl.OperationCallWithImlicitSourceExpCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.OclExpressionCS], Boolean]] = {
		Some(
			__operationCallWithImlicitSourceExpCS.getArguments,
			__operationCallWithImlicitSourceExpCS.isIsMarkedPre		
		)
	}

}


object TupleLiteralExpCS {
	def apply(
	          _variableDeclarations : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS	          
	          ) = {
		
		val __tupleLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createTupleLiteralExpCS
		
			__tupleLiteralExpCS.setVariableDeclarations(_variableDeclarations)
		
		__tupleLiteralExpCS
		
	}

	def unapply(__tupleLiteralExpCS : tudresden.ocl20.pivot.language.ocl.TupleLiteralExpCS) : Some[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitListCS] = {
		Some(
			__tupleLiteralExpCS.getVariableDeclarations		
		)
	}

}


object PrimitiveLiteralExpCS {

	def unapply(__primitiveLiteralExpCS : tudresden.ocl20.pivot.language.ocl.PrimitiveLiteralExpCS) : Boolean = {
		true
	}

}


object IntegerLiteralExpCS {
	def apply(
	          _integerLiteral : Int	          
	          ) = {
		
		val __integerLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createIntegerLiteralExpCS
		
			__integerLiteralExpCS.setIntegerLiteral(_integerLiteral)
		
		__integerLiteralExpCS
		
	}

	def unapply(__integerLiteralExpCS : tudresden.ocl20.pivot.language.ocl.IntegerLiteralExpCS) : Some[Int] = {
		Some(
			__integerLiteralExpCS.getIntegerLiteral		
		)
	}

}


object RealLiteralExpCS {
	def apply(
	          _intValue : Int,
	          _realValue : String,
	          _navigationOperator : String	          
	          ) = {
		
		val __realLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createRealLiteralExpCS
		
			__realLiteralExpCS.setIntValue(_intValue)
			__realLiteralExpCS.setRealValue(_realValue)
			__realLiteralExpCS.setNavigationOperator(_navigationOperator)
		
		__realLiteralExpCS
		
	}

	def unapply(__realLiteralExpCS : tudresden.ocl20.pivot.language.ocl.RealLiteralExpCS) : Some[Tuple3[Int, String, String]] = {
		Some(
			__realLiteralExpCS.getIntValue,
			__realLiteralExpCS.getRealValue,
			__realLiteralExpCS.getNavigationOperator		
		)
	}

}


object BooleanLiteralExpCS {
	def apply(
	          _booleanLiteral : Boolean	          
	          ) = {
		
		val __booleanLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createBooleanLiteralExpCS
		
			__booleanLiteralExpCS.setBooleanLiteral(_booleanLiteral)
		
		__booleanLiteralExpCS
		
	}

	def unapply(__booleanLiteralExpCS : tudresden.ocl20.pivot.language.ocl.BooleanLiteralExpCS) : Some[Boolean] = {
		Some(
			__booleanLiteralExpCS.isBooleanLiteral		
		)
	}

}


object StringLiteralExpCS {
	def apply(
	          _stringLiteral : String	          
	          ) = {
		
		val __stringLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createStringLiteralExpCS
		
			__stringLiteralExpCS.setStringLiteral(_stringLiteral)
		
		__stringLiteralExpCS
		
	}

	def unapply(__stringLiteralExpCS : tudresden.ocl20.pivot.language.ocl.StringLiteralExpCS) : Some[String] = {
		Some(
			__stringLiteralExpCS.getStringLiteral		
		)
	}

}


object InvalidLiteralExpCS {
	def apply(
	          
	          ) = {
		
		val __invalidLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createInvalidLiteralExpCS
		
		
		__invalidLiteralExpCS
		
	}

	def unapply(__invalidLiteralExpCS : tudresden.ocl20.pivot.language.ocl.InvalidLiteralExpCS) : Boolean = {
		true
	}

}


object NullLiteralExpCS {
	def apply(
	          
	          ) = {
		
		val __nullLiteralExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createNullLiteralExpCS
		
		
		__nullLiteralExpCS
		
	}

	def unapply(__nullLiteralExpCS : tudresden.ocl20.pivot.language.ocl.NullLiteralExpCS) : Boolean = {
		true
	}

}


object LetExpCS {
	def apply(
	          _variableDeclarations : List[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS],
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __letExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createLetExpCS
		
		if (_variableDeclarations != null)
			__letExpCS.getVariableDeclarations.addAll(_variableDeclarations)
			__letExpCS.setOclExpression(_oclExpression)
		
		__letExpCS
		
	}

	def unapply(__letExpCS : tudresden.ocl20.pivot.language.ocl.LetExpCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS], tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__letExpCS.getVariableDeclarations,
			__letExpCS.getOclExpression		
		)
	}

}


object IfExpCS {
	def apply(
	          _condition : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _thenBranch : tudresden.ocl20.pivot.language.ocl.OclExpressionCS,
	          _elseBranch : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __ifExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createIfExpCS
		
			__ifExpCS.setCondition(_condition)
			__ifExpCS.setThenBranch(_thenBranch)
			__ifExpCS.setElseBranch(_elseBranch)
		
		__ifExpCS
		
	}

	def unapply(__ifExpCS : tudresden.ocl20.pivot.language.ocl.IfExpCS) : Some[Tuple3[tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__ifExpCS.getCondition,
			__ifExpCS.getThenBranch,
			__ifExpCS.getElseBranch		
		)
	}

}


object PackageDeclarationCS {

	def unapply(__packageDeclarationCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationCS) : Some[List[tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS]] = {
		Some(
			__packageDeclarationCS.getContextDeclarations		
		)
	}

}


object PackageDeclarationWithNamespaceCS {
	def apply(
	          _contextDeclarations : List[tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS],
	          _nestedNamespace : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS	          
	          ) = {
		
		val __packageDeclarationWithNamespaceCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationWithNamespaceCS
		
		if (_contextDeclarations != null)
			__packageDeclarationWithNamespaceCS.getContextDeclarations.addAll(_contextDeclarations)
			__packageDeclarationWithNamespaceCS.setNestedNamespace(_nestedNamespace)
		
		__packageDeclarationWithNamespaceCS
		
	}

	def unapply(__packageDeclarationWithNamespaceCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS], tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS]] = {
		Some(
			__packageDeclarationWithNamespaceCS.getContextDeclarations,
			__packageDeclarationWithNamespaceCS.getNestedNamespace		
		)
	}

}


object PackageDeclarationNestedNamespaceCS {
	def apply(
	          _namespace : tudresden.ocl20.pivot.pivotmodel.Namespace,
	          _nestedNamespace : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS	          
	          ) = {
		
		val __packageDeclarationNestedNamespaceCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationNestedNamespaceCS
		
			__packageDeclarationNestedNamespaceCS.setNamespace(_namespace)
			__packageDeclarationNestedNamespaceCS.setNestedNamespace(_nestedNamespace)
		
		__packageDeclarationNestedNamespaceCS
		
	}

	def unapply(__packageDeclarationNestedNamespaceCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS) : Some[tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS] = {
		Some(
			__packageDeclarationNestedNamespaceCS.getNestedNamespace		
		)
	}

}


object PackageDeclarationWithoutNamespaceCS {
	def apply(
	          _contextDeclarations : List[tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS]	          
	          ) = {
		
		val __packageDeclarationWithoutNamespaceCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPackageDeclarationWithoutNamespaceCS
		
		if (_contextDeclarations != null)
			__packageDeclarationWithoutNamespaceCS.getContextDeclarations.addAll(_contextDeclarations)
		
		__packageDeclarationWithoutNamespaceCS
		
	}

	def unapply(__packageDeclarationWithoutNamespaceCS : tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithoutNamespaceCS) : Some[List[tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS]] = {
		Some(
			__packageDeclarationWithoutNamespaceCS.getContextDeclarations		
		)
	}

}


object ContextDeclarationCS {

	def unapply(__contextDeclarationCS : tudresden.ocl20.pivot.language.ocl.ContextDeclarationCS) : Boolean = {
		true
	}

}


object AttributeContextDeclarationCS {
	def apply(
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypePathNameCS,
	          _property : tudresden.ocl20.pivot.pivotmodel.Property,
	          _type : tudresden.ocl20.pivot.language.ocl.TypeCS,
	          _initOrDeriveValue : List[tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS]	          
	          ) = {
		
		val __attributeContextDeclarationCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createAttributeContextDeclarationCS
		
			__attributeContextDeclarationCS.setTypeName(_typeName)
			__attributeContextDeclarationCS.setProperty(_property)
			__attributeContextDeclarationCS.setType(_type)
		if (_initOrDeriveValue != null)
			__attributeContextDeclarationCS.getInitOrDeriveValue.addAll(_initOrDeriveValue)
		
		__attributeContextDeclarationCS
		
	}

	def unapply(__attributeContextDeclarationCS : tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS) : Some[Tuple3[tudresden.ocl20.pivot.language.ocl.TypePathNameCS, tudresden.ocl20.pivot.language.ocl.TypeCS, List[tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS]]] = {
		Some(
			__attributeContextDeclarationCS.getTypeName,
			__attributeContextDeclarationCS.getType,
			__attributeContextDeclarationCS.getInitOrDeriveValue		
		)
	}

}


object ClassifierContextDeclarationCS {
	def apply(
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypeCS,
	          _invariantsAndDefinitions : List[tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS]	          
	          ) = {
		
		val __classifierContextDeclarationCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createClassifierContextDeclarationCS
		
			__classifierContextDeclarationCS.setTypeName(_typeName)
		if (_invariantsAndDefinitions != null)
			__classifierContextDeclarationCS.getInvariantsAndDefinitions.addAll(_invariantsAndDefinitions)
		
		__classifierContextDeclarationCS
		
	}

	def unapply(__classifierContextDeclarationCS : tudresden.ocl20.pivot.language.ocl.ClassifierContextDeclarationCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.TypeCS, List[tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS]]] = {
		Some(
			__classifierContextDeclarationCS.getTypeName,
			__classifierContextDeclarationCS.getInvariantsAndDefinitions		
		)
	}

}


object OperationContextDeclarationCS {
	def apply(
	          _operation : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS,
	          _prePostOrBodyDeclarations : List[tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS]	          
	          ) = {
		
		val __operationContextDeclarationCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createOperationContextDeclarationCS
		
			__operationContextDeclarationCS.setOperation(_operation)
		if (_prePostOrBodyDeclarations != null)
			__operationContextDeclarationCS.getPrePostOrBodyDeclarations.addAll(_prePostOrBodyDeclarations)
		
		__operationContextDeclarationCS
		
	}

	def unapply(__operationContextDeclarationCS : tudresden.ocl20.pivot.language.ocl.OperationContextDeclarationCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS, List[tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS]]] = {
		Some(
			__operationContextDeclarationCS.getOperation,
			__operationContextDeclarationCS.getPrePostOrBodyDeclarations		
		)
	}

}


object InitOrDeriveValueCS {

	def unapply(__initOrDeriveValueCS : tudresden.ocl20.pivot.language.ocl.InitOrDeriveValueCS) : Some[tudresden.ocl20.pivot.language.ocl.OclExpressionCS] = {
		Some(
			__initOrDeriveValueCS.getOclExpression		
		)
	}

}


object InitValueCS {
	def apply(
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __initValueCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createInitValueCS
		
			__initValueCS.setOclExpression(_oclExpression)
		
		__initValueCS
		
	}

	def unapply(__initValueCS : tudresden.ocl20.pivot.language.ocl.InitValueCS) : Some[tudresden.ocl20.pivot.language.ocl.OclExpressionCS] = {
		Some(
			__initValueCS.getOclExpression		
		)
	}

}


object DeriveValueCS {
	def apply(
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __deriveValueCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createDeriveValueCS
		
			__deriveValueCS.setOclExpression(_oclExpression)
		
		__deriveValueCS
		
	}

	def unapply(__deriveValueCS : tudresden.ocl20.pivot.language.ocl.DeriveValueCS) : Some[tudresden.ocl20.pivot.language.ocl.OclExpressionCS] = {
		Some(
			__deriveValueCS.getOclExpression		
		)
	}

}


object InvariantOrDefinitionCS {

	def unapply(__invariantOrDefinitionCS : tudresden.ocl20.pivot.language.ocl.InvariantOrDefinitionCS) : Boolean = {
		true
	}

}


object InvariantExpCS {
	def apply(
	          _name : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __invariantExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createInvariantExpCS
		
			__invariantExpCS.setName(_name)
			__invariantExpCS.setOclExpression(_oclExpression)
		
		__invariantExpCS
		
	}

	def unapply(__invariantExpCS : tudresden.ocl20.pivot.language.ocl.InvariantExpCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__invariantExpCS.getName,
			__invariantExpCS.getOclExpression		
		)
	}

}


object DefinitionExpCS {
	def apply(
	          _static : Boolean,
	          _definitionExpPart : tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS	          
	          ) = {
		
		val __definitionExpCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createDefinitionExpCS
		
			__definitionExpCS.setStatic(_static)
			__definitionExpCS.setDefinitionExpPart(_definitionExpPart)
		
		__definitionExpCS
		
	}

	def unapply(__definitionExpCS : tudresden.ocl20.pivot.language.ocl.DefinitionExpCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS, Boolean]] = {
		Some(
			__definitionExpCS.getDefinitionExpPart,
			__definitionExpCS.isStatic		
		)
	}

}


object DefinitionExpPartCS {

	def unapply(__definitionExpPartCS : tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS) : Boolean = {
		true
	}

}


object DefinitionExpPropertyCS {
	def apply(
	          _variableDeclaration : tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS	          
	          ) = {
		
		val __definitionExpPropertyCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createDefinitionExpPropertyCS
		
			__definitionExpPropertyCS.setVariableDeclaration(_variableDeclaration)
		
		__definitionExpPropertyCS
		
	}

	def unapply(__definitionExpPropertyCS : tudresden.ocl20.pivot.language.ocl.DefinitionExpPropertyCS) : Some[tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS] = {
		Some(
			__definitionExpPropertyCS.getVariableDeclaration		
		)
	}

}


object DefinitionExpOperationCS {
	def apply(
	          _operation : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS,
	          _equal : String,
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __definitionExpOperationCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createDefinitionExpOperationCS
		
			__definitionExpOperationCS.setOperation(_operation)
			__definitionExpOperationCS.setEqual(_equal)
			__definitionExpOperationCS.setOclExpression(_oclExpression)
		
		__definitionExpOperationCS
		
	}

	def unapply(__definitionExpOperationCS : tudresden.ocl20.pivot.language.ocl.DefinitionExpOperationCS) : Some[Tuple3[tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS, String]] = {
		Some(
			__definitionExpOperationCS.getOperation,
			__definitionExpOperationCS.getOclExpression,
			__definitionExpOperationCS.getEqual		
		)
	}

}


object PrePostOrBodyDeclarationCS {

	def unapply(__prePostOrBodyDeclarationCS : tudresden.ocl20.pivot.language.ocl.PrePostOrBodyDeclarationCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__prePostOrBodyDeclarationCS.getName,
			__prePostOrBodyDeclarationCS.getOclExpression		
		)
	}

}


object PreConditionDeclarationCS {
	def apply(
	          _name : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __preConditionDeclarationCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPreConditionDeclarationCS
		
			__preConditionDeclarationCS.setName(_name)
			__preConditionDeclarationCS.setOclExpression(_oclExpression)
		
		__preConditionDeclarationCS
		
	}

	def unapply(__preConditionDeclarationCS : tudresden.ocl20.pivot.language.ocl.PreConditionDeclarationCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__preConditionDeclarationCS.getName,
			__preConditionDeclarationCS.getOclExpression		
		)
	}

}


object PostConditionDeclarationCS {
	def apply(
	          _name : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __postConditionDeclarationCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createPostConditionDeclarationCS
		
			__postConditionDeclarationCS.setName(_name)
			__postConditionDeclarationCS.setOclExpression(_oclExpression)
		
		__postConditionDeclarationCS
		
	}

	def unapply(__postConditionDeclarationCS : tudresden.ocl20.pivot.language.ocl.PostConditionDeclarationCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__postConditionDeclarationCS.getName,
			__postConditionDeclarationCS.getOclExpression		
		)
	}

}


object BodyDeclarationCS {
	def apply(
	          _name : tudresden.ocl20.pivot.language.ocl.SimpleNameCS,
	          _oclExpression : tudresden.ocl20.pivot.language.ocl.OclExpressionCS	          
	          ) = {
		
		val __bodyDeclarationCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createBodyDeclarationCS
		
			__bodyDeclarationCS.setName(_name)
			__bodyDeclarationCS.setOclExpression(_oclExpression)
		
		__bodyDeclarationCS
		
	}

	def unapply(__bodyDeclarationCS : tudresden.ocl20.pivot.language.ocl.BodyDeclarationCS) : Some[Tuple2[tudresden.ocl20.pivot.language.ocl.SimpleNameCS, tudresden.ocl20.pivot.language.ocl.OclExpressionCS]] = {
		Some(
			__bodyDeclarationCS.getName,
			__bodyDeclarationCS.getOclExpression		
		)
	}

}


object OperationDefinitionCS {

	def unapply(__operationDefinitionCS : tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.ParameterCS], tudresden.ocl20.pivot.language.ocl.TypeCS]] = {
		Some(
			__operationDefinitionCS.getParameters,
			__operationDefinitionCS.getReturnType		
		)
	}

}


object OperationDefinitionInContextCS {
	def apply(
	          _operation : tudresden.ocl20.pivot.pivotmodel.Operation,
	          _parameters : List[tudresden.ocl20.pivot.language.ocl.ParameterCS],
	          _returnType : tudresden.ocl20.pivot.language.ocl.TypeCS,
	          _typeName : tudresden.ocl20.pivot.language.ocl.TypePathNameCS	          
	          ) = {
		
		val __operationDefinitionInContextCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInContextCS
		
			__operationDefinitionInContextCS.setOperation(_operation)
		if (_parameters != null)
			__operationDefinitionInContextCS.getParameters.addAll(_parameters)
			__operationDefinitionInContextCS.setReturnType(_returnType)
			__operationDefinitionInContextCS.setTypeName(_typeName)
		
		__operationDefinitionInContextCS
		
	}

	def unapply(__operationDefinitionInContextCS : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInContextCS) : Some[Tuple3[List[tudresden.ocl20.pivot.language.ocl.ParameterCS], tudresden.ocl20.pivot.language.ocl.TypeCS, tudresden.ocl20.pivot.language.ocl.TypePathNameCS]] = {
		Some(
			__operationDefinitionInContextCS.getParameters,
			__operationDefinitionInContextCS.getReturnType,
			__operationDefinitionInContextCS.getTypeName		
		)
	}

}


object OperationDefinitionInDefCS {
	def apply(
	          _operation : tudresden.ocl20.pivot.pivotmodel.Operation,
	          _parameters : List[tudresden.ocl20.pivot.language.ocl.ParameterCS],
	          _returnType : tudresden.ocl20.pivot.language.ocl.TypeCS	          
	          ) = {
		
		val __operationDefinitionInDefCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createOperationDefinitionInDefCS
		
			__operationDefinitionInDefCS.setOperation(_operation)
		if (_parameters != null)
			__operationDefinitionInDefCS.getParameters.addAll(_parameters)
			__operationDefinitionInDefCS.setReturnType(_returnType)
		
		__operationDefinitionInDefCS
		
	}

	def unapply(__operationDefinitionInDefCS : tudresden.ocl20.pivot.language.ocl.OperationDefinitionInDefCS) : Some[Tuple2[List[tudresden.ocl20.pivot.language.ocl.ParameterCS], tudresden.ocl20.pivot.language.ocl.TypeCS]] = {
		Some(
			__operationDefinitionInDefCS.getParameters,
			__operationDefinitionInDefCS.getReturnType		
		)
	}

}


object ParameterCS {
	def apply(
	          _parameter : tudresden.ocl20.pivot.pivotmodel.Parameter,
	          _parameterType : tudresden.ocl20.pivot.language.ocl.TypeCS	          
	          ) = {
		
		val __parameterCS = tudresden.ocl20.pivot.language.ocl.OclFactory.eINSTANCE.createParameterCS
		
			__parameterCS.setParameter(_parameter)
			__parameterCS.setParameterType(_parameterType)
		
		__parameterCS
		
	}

	def unapply(__parameterCS : tudresden.ocl20.pivot.language.ocl.ParameterCS) : Some[tudresden.ocl20.pivot.language.ocl.TypeCS] = {
		Some(
			__parameterCS.getParameterType		
		)
	}

}

