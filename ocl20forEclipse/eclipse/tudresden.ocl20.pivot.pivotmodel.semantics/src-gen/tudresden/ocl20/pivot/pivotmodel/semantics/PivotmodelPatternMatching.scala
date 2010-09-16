package tudresden.ocl20.pivot.pivotmodel.semantics

import tudresden.attributegrammar.integration.kiama.util.CollectionConverterJ2S._
import tudresden.attributegrammar.integration.kiama.util.CollectionConverterS2J._


object NamedElement {

	def unapply(__namedElement : tudresden.ocl20.pivot.pivotmodel.NamedElement) : Some[String] = {
		Some(
			__namedElement.getName		
		)
	}

}


object TypedElement {

	def unapply(__typedElement : tudresden.ocl20.pivot.pivotmodel.TypedElement) : Some[Tuple2[tudresden.ocl20.pivot.pivotmodel.GenericType, String]] = {
		Some(
			__typedElement.getGenericType,
			__typedElement.getName		
		)
	}

}


object Feature {

	def unapply(__feature : tudresden.ocl20.pivot.pivotmodel.Feature) : Some[Tuple3[tudresden.ocl20.pivot.pivotmodel.GenericType, String, Boolean]] = {
		Some(
			__feature.getGenericType,
			__feature.getName,
			__feature.isStatic		
		)
	}

}


object GenericElement {

	def unapply(__genericElement : tudresden.ocl20.pivot.pivotmodel.GenericElement) : Some[Tuple2[List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], String]] = {
		Some(
			__genericElement.getOwnedTypeParameter,
			__genericElement.getName		
		)
	}

}


object ConstrainableElement {

	def unapply(__constrainableElement : tudresden.ocl20.pivot.pivotmodel.ConstrainableElement) : Boolean = {
		true
	}

}


object Namespace {
	def apply(
	          _name : String,
	          _ownedTypeParameter : List[tudresden.ocl20.pivot.pivotmodel.TypeParameter],
	          _ownedType : List[tudresden.ocl20.pivot.pivotmodel.Type],
	          _ownedRule : List[tudresden.ocl20.pivot.pivotmodel.Constraint],
	          _nestedNamespace : List[tudresden.ocl20.pivot.pivotmodel.Namespace],
	          _nestingNamespace : tudresden.ocl20.pivot.pivotmodel.Namespace	          
	          ) = {
		
		val __namespace = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createNamespace
		
			__namespace.setName(_name)
		if (_ownedTypeParameter != null)
			__namespace.getOwnedTypeParameter.addAll(_ownedTypeParameter)
		if (_ownedType != null)
			__namespace.getOwnedType.addAll(_ownedType)
		if (_ownedRule != null)
			__namespace.getOwnedRule.addAll(_ownedRule)
		if (_nestedNamespace != null)
			__namespace.getNestedNamespace.addAll(_nestedNamespace)
			__namespace.setNestingNamespace(_nestingNamespace)
		
		__namespace
		
	}

	def unapply(__namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) : Some[Tuple5[List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], List[tudresden.ocl20.pivot.pivotmodel.Type], List[tudresden.ocl20.pivot.pivotmodel.Constraint], List[tudresden.ocl20.pivot.pivotmodel.Namespace], String]] = {
		Some(
			__namespace.getOwnedTypeParameter,
			__namespace.getOwnedType,
			__namespace.getOwnedRule,
			__namespace.getNestedNamespace,
			__namespace.getName		
		)
	}

}


object Type {
	def apply(
	          _name : String,
	          _ownedTypeParameter : List[tudresden.ocl20.pivot.pivotmodel.TypeParameter],
	          _namespace : tudresden.ocl20.pivot.pivotmodel.Namespace,
	          _ownedOperation : List[tudresden.ocl20.pivot.pivotmodel.Operation],
	          _ownedProperty : List[tudresden.ocl20.pivot.pivotmodel.Property],
	          _superType : List[tudresden.ocl20.pivot.pivotmodel.Type],
	          _genericSuperType : List[tudresden.ocl20.pivot.pivotmodel.GenericType]	          
	          ) = {
		
		val __type = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createType
		
			__type.setName(_name)
		if (_ownedTypeParameter != null)
			__type.getOwnedTypeParameter.addAll(_ownedTypeParameter)
			__type.setNamespace(_namespace)
		if (_ownedOperation != null)
			__type.getOwnedOperation.addAll(_ownedOperation)
		if (_ownedProperty != null)
			__type.getOwnedProperty.addAll(_ownedProperty)
		if (_superType != null)
			__type.getSuperType.addAll(_superType)
		if (_genericSuperType != null)
			__type.getGenericSuperType.addAll(_genericSuperType)
		
		__type
		
	}

	def unapply(__type : tudresden.ocl20.pivot.pivotmodel.Type) : Some[Tuple5[List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], List[tudresden.ocl20.pivot.pivotmodel.Operation], List[tudresden.ocl20.pivot.pivotmodel.Property], List[tudresden.ocl20.pivot.pivotmodel.GenericType], String]] = {
		Some(
			__type.getOwnedTypeParameter,
			__type.getOwnedOperation,
			__type.getOwnedProperty,
			__type.getGenericSuperType,
			__type.getName		
		)
	}

}


object PrimitiveType {
	def apply(
	          _name : String,
	          _ownedTypeParameter : List[tudresden.ocl20.pivot.pivotmodel.TypeParameter],
	          _namespace : tudresden.ocl20.pivot.pivotmodel.Namespace,
	          _ownedOperation : List[tudresden.ocl20.pivot.pivotmodel.Operation],
	          _ownedProperty : List[tudresden.ocl20.pivot.pivotmodel.Property],
	          _superType : List[tudresden.ocl20.pivot.pivotmodel.Type],
	          _genericSuperType : List[tudresden.ocl20.pivot.pivotmodel.GenericType],
	          _kind : tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind	          
	          ) = {
		
		val __primitiveType = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createPrimitiveType
		
			__primitiveType.setName(_name)
		if (_ownedTypeParameter != null)
			__primitiveType.getOwnedTypeParameter.addAll(_ownedTypeParameter)
			__primitiveType.setNamespace(_namespace)
		if (_ownedOperation != null)
			__primitiveType.getOwnedOperation.addAll(_ownedOperation)
		if (_ownedProperty != null)
			__primitiveType.getOwnedProperty.addAll(_ownedProperty)
		if (_superType != null)
			__primitiveType.getSuperType.addAll(_superType)
		if (_genericSuperType != null)
			__primitiveType.getGenericSuperType.addAll(_genericSuperType)
			__primitiveType.setKind(_kind)
		
		__primitiveType
		
	}

	def unapply(__primitiveType : tudresden.ocl20.pivot.pivotmodel.PrimitiveType) : Some[Tuple6[List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], List[tudresden.ocl20.pivot.pivotmodel.Operation], List[tudresden.ocl20.pivot.pivotmodel.Property], List[tudresden.ocl20.pivot.pivotmodel.GenericType], String, tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind]] = {
		Some(
			__primitiveType.getOwnedTypeParameter,
			__primitiveType.getOwnedOperation,
			__primitiveType.getOwnedProperty,
			__primitiveType.getGenericSuperType,
			__primitiveType.getName,
			__primitiveType.getKind		
		)
	}

}


object Enumeration {
	def apply(
	          _name : String,
	          _ownedTypeParameter : List[tudresden.ocl20.pivot.pivotmodel.TypeParameter],
	          _namespace : tudresden.ocl20.pivot.pivotmodel.Namespace,
	          _ownedOperation : List[tudresden.ocl20.pivot.pivotmodel.Operation],
	          _ownedProperty : List[tudresden.ocl20.pivot.pivotmodel.Property],
	          _superType : List[tudresden.ocl20.pivot.pivotmodel.Type],
	          _genericSuperType : List[tudresden.ocl20.pivot.pivotmodel.GenericType],
	          _ownedLiteral : List[tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral]	          
	          ) = {
		
		val __enumeration = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createEnumeration
		
			__enumeration.setName(_name)
		if (_ownedTypeParameter != null)
			__enumeration.getOwnedTypeParameter.addAll(_ownedTypeParameter)
			__enumeration.setNamespace(_namespace)
		if (_ownedOperation != null)
			__enumeration.getOwnedOperation.addAll(_ownedOperation)
		if (_ownedProperty != null)
			__enumeration.getOwnedProperty.addAll(_ownedProperty)
		if (_superType != null)
			__enumeration.getSuperType.addAll(_superType)
		if (_genericSuperType != null)
			__enumeration.getGenericSuperType.addAll(_genericSuperType)
		if (_ownedLiteral != null)
			__enumeration.getOwnedLiteral.addAll(_ownedLiteral)
		
		__enumeration
		
	}

	def unapply(__enumeration : tudresden.ocl20.pivot.pivotmodel.Enumeration) : Some[Tuple6[List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], List[tudresden.ocl20.pivot.pivotmodel.Operation], List[tudresden.ocl20.pivot.pivotmodel.Property], List[tudresden.ocl20.pivot.pivotmodel.GenericType], List[tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral], String]] = {
		Some(
			__enumeration.getOwnedTypeParameter,
			__enumeration.getOwnedOperation,
			__enumeration.getOwnedProperty,
			__enumeration.getGenericSuperType,
			__enumeration.getOwnedLiteral,
			__enumeration.getName		
		)
	}

}


object EnumerationLiteral {
	def apply(
	          _name : String,
	          _enumeration : tudresden.ocl20.pivot.pivotmodel.Enumeration	          
	          ) = {
		
		val __enumerationLiteral = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createEnumerationLiteral
		
			__enumerationLiteral.setName(_name)
			__enumerationLiteral.setEnumeration(_enumeration)
		
		__enumerationLiteral
		
	}

	def unapply(__enumerationLiteral : tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral) : Some[String] = {
		Some(
			__enumerationLiteral.getName		
		)
	}

}


object Property {
	def apply(
	          _name : String,
	          _type : tudresden.ocl20.pivot.pivotmodel.Type,
	          _genericType : tudresden.ocl20.pivot.pivotmodel.GenericType,
	          _static : Boolean,
	          _semantics : tudresden.ocl20.pivot.pivotmodel.Constraint,
	          _owningType : tudresden.ocl20.pivot.pivotmodel.Type	          
	          ) = {
		
		val __property = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createProperty
		
			__property.setName(_name)
			__property.setType(_type)
			__property.setGenericType(_genericType)
			__property.setStatic(_static)
			__property.setSemantics(_semantics)
			__property.setOwningType(_owningType)
		
		__property
		
	}

	def unapply(__property : tudresden.ocl20.pivot.pivotmodel.Property) : Some[Tuple3[tudresden.ocl20.pivot.pivotmodel.GenericType, String, Boolean]] = {
		Some(
			__property.getGenericType,
			__property.getName,
			__property.isStatic		
		)
	}

}


object Operation {
	def apply(
	          _name : String,
	          _type : tudresden.ocl20.pivot.pivotmodel.Type,
	          _genericType : tudresden.ocl20.pivot.pivotmodel.GenericType,
	          _static : Boolean,
	          _semantics : tudresden.ocl20.pivot.pivotmodel.Constraint,
	          _ownedTypeParameter : List[tudresden.ocl20.pivot.pivotmodel.TypeParameter],
	          _owningType : tudresden.ocl20.pivot.pivotmodel.Type,
	          _ownedParameter : List[tudresden.ocl20.pivot.pivotmodel.Parameter]	          
	          ) = {
		
		val __operation = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createOperation
		
			__operation.setName(_name)
			__operation.setType(_type)
			__operation.setGenericType(_genericType)
			__operation.setStatic(_static)
			__operation.setSemantics(_semantics)
		if (_ownedTypeParameter != null)
			__operation.getOwnedTypeParameter.addAll(_ownedTypeParameter)
			__operation.setOwningType(_owningType)
		if (_ownedParameter != null)
			__operation.getOwnedParameter.addAll(_ownedParameter)
		
		__operation
		
	}

	def unapply(__operation : tudresden.ocl20.pivot.pivotmodel.Operation) : Some[Tuple5[tudresden.ocl20.pivot.pivotmodel.GenericType, List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], List[tudresden.ocl20.pivot.pivotmodel.Parameter], String, Boolean]] = {
		Some(
			__operation.getGenericType,
			__operation.getOwnedTypeParameter,
			__operation.getOwnedParameter,
			__operation.getName,
			__operation.isStatic		
		)
	}

}


object Parameter {
	def apply(
	          _name : String,
	          _type : tudresden.ocl20.pivot.pivotmodel.Type,
	          _genericType : tudresden.ocl20.pivot.pivotmodel.GenericType,
	          _kind : tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind,
	          _operation : tudresden.ocl20.pivot.pivotmodel.Operation	          
	          ) = {
		
		val __parameter = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createParameter
		
			__parameter.setName(_name)
			__parameter.setType(_type)
			__parameter.setGenericType(_genericType)
			__parameter.setKind(_kind)
			__parameter.setOperation(_operation)
		
		__parameter
		
	}

	def unapply(__parameter : tudresden.ocl20.pivot.pivotmodel.Parameter) : Some[Tuple3[tudresden.ocl20.pivot.pivotmodel.GenericType, String, tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind]] = {
		Some(
			__parameter.getGenericType,
			__parameter.getName,
			__parameter.getKind		
		)
	}

}


object GenericType {

	def unapply(__genericType : tudresden.ocl20.pivot.pivotmodel.GenericType) : Some[String] = {
		Some(
			__genericType.getName		
		)
	}

}


object ParameterGenericType {
	def apply(
	          _name : String,
	          _typeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter	          
	          ) = {
		
		val __parameterGenericType = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createParameterGenericType
		
			__parameterGenericType.setName(_name)
			__parameterGenericType.setTypeParameter(_typeParameter)
		
		__parameterGenericType
		
	}

	def unapply(__parameterGenericType : tudresden.ocl20.pivot.pivotmodel.ParameterGenericType) : Some[String] = {
		Some(
			__parameterGenericType.getName		
		)
	}

}


object ComplexGenericType {
	def apply(
	          _name : String,
	          _unboundType : tudresden.ocl20.pivot.pivotmodel.Type,
	          _typeArgument : List[tudresden.ocl20.pivot.pivotmodel.TypeArgument]	          
	          ) = {
		
		val __complexGenericType = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createComplexGenericType
		
			__complexGenericType.setName(_name)
			__complexGenericType.setUnboundType(_unboundType)
		if (_typeArgument != null)
			__complexGenericType.getTypeArgument.addAll(_typeArgument)
		
		__complexGenericType
		
	}

	def unapply(__complexGenericType : tudresden.ocl20.pivot.pivotmodel.ComplexGenericType) : Some[Tuple2[List[tudresden.ocl20.pivot.pivotmodel.TypeArgument], String]] = {
		Some(
			__complexGenericType.getTypeArgument,
			__complexGenericType.getName		
		)
	}

}


object TypeParameter {
	def apply(
	          _name : String,
	          _genericElement : tudresden.ocl20.pivot.pivotmodel.GenericElement	          
	          ) = {
		
		val __typeParameter = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createTypeParameter
		
			__typeParameter.setName(_name)
			__typeParameter.setGenericElement(_genericElement)
		
		__typeParameter
		
	}

	def unapply(__typeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter) : Some[String] = {
		Some(
			__typeParameter.getName		
		)
	}

}


object TypeArgument {
	def apply(
	          _name : String,
	          _type : tudresden.ocl20.pivot.pivotmodel.Type,
	          _genericType : tudresden.ocl20.pivot.pivotmodel.GenericType,
	          _owningGenericType : tudresden.ocl20.pivot.pivotmodel.ComplexGenericType	          
	          ) = {
		
		val __typeArgument = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createTypeArgument
		
			__typeArgument.setName(_name)
			__typeArgument.setType(_type)
			__typeArgument.setGenericType(_genericType)
			__typeArgument.setOwningGenericType(_owningGenericType)
		
		__typeArgument
		
	}

	def unapply(__typeArgument : tudresden.ocl20.pivot.pivotmodel.TypeArgument) : Some[Tuple2[tudresden.ocl20.pivot.pivotmodel.GenericType, String]] = {
		Some(
			__typeArgument.getGenericType,
			__typeArgument.getName		
		)
	}

}


object Constraint {
	def apply(
	          _name : String,
	          _kind : tudresden.ocl20.pivot.pivotmodel.ConstraintKind,
	          _namespace : tudresden.ocl20.pivot.pivotmodel.Namespace,
	          _specification : tudresden.ocl20.pivot.pivotmodel.Expression,
	          _constrainedElement : List[tudresden.ocl20.pivot.pivotmodel.ConstrainableElement],
	          _definedFeature : tudresden.ocl20.pivot.pivotmodel.Feature	          
	          ) = {
		
		val __constraint = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createConstraint
		
			__constraint.setName(_name)
			__constraint.setKind(_kind)
			__constraint.setNamespace(_namespace)
			__constraint.setSpecification(_specification)
		if (_constrainedElement != null)
			__constraint.getConstrainedElement.addAll(_constrainedElement)
			__constraint.setDefinedFeature(_definedFeature)
		
		__constraint
		
	}

	def unapply(__constraint : tudresden.ocl20.pivot.pivotmodel.Constraint) : Some[Tuple3[tudresden.ocl20.pivot.pivotmodel.Expression, String, tudresden.ocl20.pivot.pivotmodel.ConstraintKind]] = {
		Some(
			__constraint.getSpecification,
			__constraint.getName,
			__constraint.getKind		
		)
	}

}


object Expression {
	def apply(
	          _body : String,
	          _language : String,
	          _constraint : tudresden.ocl20.pivot.pivotmodel.Constraint	          
	          ) = {
		
		val __expression = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createExpression
		
			__expression.setBody(_body)
			__expression.setLanguage(_language)
			__expression.setConstraint(_constraint)
		
		__expression
		
	}

	def unapply(__expression : tudresden.ocl20.pivot.pivotmodel.Expression) : Some[Tuple2[String, String]] = {
		Some(
			__expression.getBody,
			__expression.getLanguage		
		)
	}

}


object AssociationProperty {
	def apply(
	          _name : String,
	          _type : tudresden.ocl20.pivot.pivotmodel.Type,
	          _genericType : tudresden.ocl20.pivot.pivotmodel.GenericType,
	          _static : Boolean,
	          _semantics : tudresden.ocl20.pivot.pivotmodel.Constraint,
	          _owningType : tudresden.ocl20.pivot.pivotmodel.Type,
	          _inverseAssociationProperties : List[tudresden.ocl20.pivot.pivotmodel.AssociationProperty]	          
	          ) = {
		
		val __associationProperty = tudresden.ocl20.pivot.pivotmodel.PivotModelFactory.eINSTANCE.createAssociationProperty
		
			__associationProperty.setName(_name)
			__associationProperty.setType(_type)
			__associationProperty.setGenericType(_genericType)
			__associationProperty.setStatic(_static)
			__associationProperty.setSemantics(_semantics)
			__associationProperty.setOwningType(_owningType)
		if (_inverseAssociationProperties != null)
			__associationProperty.getInverseAssociationProperties.addAll(_inverseAssociationProperties)
		
		__associationProperty
		
	}

	def unapply(__associationProperty : tudresden.ocl20.pivot.pivotmodel.AssociationProperty) : Some[Tuple4[tudresden.ocl20.pivot.pivotmodel.GenericType, List[tudresden.ocl20.pivot.pivotmodel.AssociationProperty], String, Boolean]] = {
		Some(
			__associationProperty.getGenericType,
			__associationProperty.getInverseAssociationProperties,
			__associationProperty.getName,
			__associationProperty.isStatic		
		)
	}

}

