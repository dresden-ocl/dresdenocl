package tudresden.ocl20.pivot.pivotmodel.semantics












	private trait NamespaceAttributable extends tudresden.ocl20.pivot.pivotmodel.Namespace with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Namespace
	
		override def addType(_type : tudresden.ocl20.pivot.pivotmodel.Type) : tudresden.ocl20.pivot.pivotmodel.Namespace = eObject.addType( _type )
		override def addRule(_rule : tudresden.ocl20.pivot.pivotmodel.Constraint) : tudresden.ocl20.pivot.pivotmodel.Namespace = eObject.addRule( _rule )
		override def addNestedNamespace(_namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) : tudresden.ocl20.pivot.pivotmodel.Namespace = eObject.addNestedNamespace( _namespace )
		override def getOwnedAndNestedRules() : java.util.List[tudresden.ocl20.pivot.pivotmodel.Constraint] = eObject.getOwnedAndNestedRules()
		override def lookupType(_name : String) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.lookupType( _name )
		override def lookupNamespace(_name : String) : tudresden.ocl20.pivot.pivotmodel.Namespace = eObject.lookupNamespace( _name )
		override def removeOwnedAndNestedRules() : Boolean = eObject.removeOwnedAndNestedRules()
		override def removeOwnedAndNestedRules(_constraints : java.util.List[tudresden.ocl20.pivot.pivotmodel.Constraint]) : Boolean = eObject.removeOwnedAndNestedRules( _constraints )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def bindTypeParameter(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.NamedElement = eObject.bindTypeParameter( _parameters ,  _types )
		override def addTypeParameter(_typeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter) : tudresden.ocl20.pivot.pivotmodel.GenericElement = eObject.addTypeParameter( _typeParameter )
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getOwnedTypeParameter() = eObject.getOwnedTypeParameter()
		override def getOwnedType() = eObject.getOwnedType()
		override def getOwnedRule() = eObject.getOwnedRule()
		override def getNestedNamespace() = eObject.getNestedNamespace()
		override def getNestingNamespace() = eObject.getNestingNamespace()
		override def setNestingNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) = eObject.setNestingNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace)
	}


	private trait TypeAttributable extends tudresden.ocl20.pivot.pivotmodel.Type with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Type
	
		override def conformsTo(_other : tudresden.ocl20.pivot.pivotmodel.Type) : Boolean = eObject.conformsTo( _other )
		override def commonSuperType(_other : tudresden.ocl20.pivot.pivotmodel.Type) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.commonSuperType( _other )
		override def allProperties() : java.util.List[tudresden.ocl20.pivot.pivotmodel.Property] = eObject.allProperties()
		override def allOperations() : java.util.List[tudresden.ocl20.pivot.pivotmodel.Operation] = eObject.allOperations()
		override def lookupProperty(_name : String) : tudresden.ocl20.pivot.pivotmodel.Property = eObject.lookupProperty( _name )
		override def lookupOperation(_name : String, _paramTypes : java.util.List[tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.Operation = eObject.lookupOperation( _name ,  _paramTypes )
		override def addProperty(_property : tudresden.ocl20.pivot.pivotmodel.Property) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addProperty( _property )
		override def addOperation(_operation : tudresden.ocl20.pivot.pivotmodel.Operation) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addOperation( _operation )
		override def addSuperType(_type : tudresden.ocl20.pivot.pivotmodel.Type) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addSuperType( _type )
		override def removeProperty(_property : tudresden.ocl20.pivot.pivotmodel.Property) : Boolean = eObject.removeProperty( _property )
		override def removeOperation(_operation : tudresden.ocl20.pivot.pivotmodel.Operation) : Boolean = eObject.removeOperation( _operation )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def bindTypeParameter(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.bindTypeParameter( _parameters ,  _types )
		override def addTypeParameter(_typeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter) : tudresden.ocl20.pivot.pivotmodel.GenericElement = eObject.addTypeParameter( _typeParameter )
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getOwnedTypeParameter() = eObject.getOwnedTypeParameter()
		override def getNamespace() = eObject.getNamespace()
		override def setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) = eObject.setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace)
		override def getOwnedOperation() = eObject.getOwnedOperation()
		override def getOwnedProperty() = eObject.getOwnedProperty()
		override def getSuperType() = eObject.getSuperType()
		override def getGenericSuperType() = eObject.getGenericSuperType()
	}


	private trait PrimitiveTypeAttributable extends tudresden.ocl20.pivot.pivotmodel.PrimitiveType with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.PrimitiveType
	
		override def conformsTo(_other : tudresden.ocl20.pivot.pivotmodel.Type) : Boolean = eObject.conformsTo( _other )
		override def commonSuperType(_other : tudresden.ocl20.pivot.pivotmodel.Type) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.commonSuperType( _other )
		override def allProperties() : java.util.List[tudresden.ocl20.pivot.pivotmodel.Property] = eObject.allProperties()
		override def allOperations() : java.util.List[tudresden.ocl20.pivot.pivotmodel.Operation] = eObject.allOperations()
		override def lookupProperty(_name : String) : tudresden.ocl20.pivot.pivotmodel.Property = eObject.lookupProperty( _name )
		override def lookupOperation(_name : String, _paramTypes : java.util.List[tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.Operation = eObject.lookupOperation( _name ,  _paramTypes )
		override def addProperty(_property : tudresden.ocl20.pivot.pivotmodel.Property) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addProperty( _property )
		override def addOperation(_operation : tudresden.ocl20.pivot.pivotmodel.Operation) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addOperation( _operation )
		override def addSuperType(_type : tudresden.ocl20.pivot.pivotmodel.Type) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addSuperType( _type )
		override def removeProperty(_property : tudresden.ocl20.pivot.pivotmodel.Property) : Boolean = eObject.removeProperty( _property )
		override def removeOperation(_operation : tudresden.ocl20.pivot.pivotmodel.Operation) : Boolean = eObject.removeOperation( _operation )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def bindTypeParameter(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.bindTypeParameter( _parameters ,  _types )
		override def addTypeParameter(_typeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter) : tudresden.ocl20.pivot.pivotmodel.GenericElement = eObject.addTypeParameter( _typeParameter )
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getOwnedTypeParameter() = eObject.getOwnedTypeParameter()
		override def getNamespace() = eObject.getNamespace()
		override def setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) = eObject.setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace)
		override def getOwnedOperation() = eObject.getOwnedOperation()
		override def getOwnedProperty() = eObject.getOwnedProperty()
		override def getSuperType() = eObject.getSuperType()
		override def getGenericSuperType() = eObject.getGenericSuperType()
		override def getKind() = eObject.getKind()
		override def setKind(_PrimitiveTypeKind : tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind) = eObject.setKind(_PrimitiveTypeKind : tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind)
	}


	private trait EnumerationAttributable extends tudresden.ocl20.pivot.pivotmodel.Enumeration with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Enumeration
	
		override def addLiteral(_literal : tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral) : tudresden.ocl20.pivot.pivotmodel.Enumeration = eObject.addLiteral( _literal )
		override def lookupLiteral(_name : String) : tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral = eObject.lookupLiteral( _name )
		override def conformsTo(_other : tudresden.ocl20.pivot.pivotmodel.Type) : Boolean = eObject.conformsTo( _other )
		override def commonSuperType(_other : tudresden.ocl20.pivot.pivotmodel.Type) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.commonSuperType( _other )
		override def allProperties() : java.util.List[tudresden.ocl20.pivot.pivotmodel.Property] = eObject.allProperties()
		override def allOperations() : java.util.List[tudresden.ocl20.pivot.pivotmodel.Operation] = eObject.allOperations()
		override def lookupProperty(_name : String) : tudresden.ocl20.pivot.pivotmodel.Property = eObject.lookupProperty( _name )
		override def lookupOperation(_name : String, _paramTypes : java.util.List[tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.Operation = eObject.lookupOperation( _name ,  _paramTypes )
		override def addProperty(_property : tudresden.ocl20.pivot.pivotmodel.Property) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addProperty( _property )
		override def addOperation(_operation : tudresden.ocl20.pivot.pivotmodel.Operation) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addOperation( _operation )
		override def addSuperType(_type : tudresden.ocl20.pivot.pivotmodel.Type) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.addSuperType( _type )
		override def removeProperty(_property : tudresden.ocl20.pivot.pivotmodel.Property) : Boolean = eObject.removeProperty( _property )
		override def removeOperation(_operation : tudresden.ocl20.pivot.pivotmodel.Operation) : Boolean = eObject.removeOperation( _operation )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def bindTypeParameter(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.Type = eObject.bindTypeParameter( _parameters ,  _types )
		override def addTypeParameter(_typeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter) : tudresden.ocl20.pivot.pivotmodel.GenericElement = eObject.addTypeParameter( _typeParameter )
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getOwnedTypeParameter() = eObject.getOwnedTypeParameter()
		override def getNamespace() = eObject.getNamespace()
		override def setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) = eObject.setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace)
		override def getOwnedOperation() = eObject.getOwnedOperation()
		override def getOwnedProperty() = eObject.getOwnedProperty()
		override def getSuperType() = eObject.getSuperType()
		override def getGenericSuperType() = eObject.getGenericSuperType()
		override def getOwnedLiteral() = eObject.getOwnedLiteral()
	}


	private trait EnumerationLiteralAttributable extends tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral
	
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getEnumeration() = eObject.getEnumeration()
		override def setEnumeration(_Enumeration : tudresden.ocl20.pivot.pivotmodel.Enumeration) = eObject.setEnumeration(_Enumeration : tudresden.ocl20.pivot.pivotmodel.Enumeration)
	}


	private trait PropertyAttributable extends tudresden.ocl20.pivot.pivotmodel.Property with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Property
	
		override def cmpSlots(_p : tudresden.ocl20.pivot.pivotmodel.Property) : Boolean = eObject.cmpSlots( _p )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getType() = eObject.getType()
		override def setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getGenericType() = eObject.getGenericType()
		override def setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType) = eObject.setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType)
		override def isStatic() = eObject.isStatic()
		override def setStatic(_Boolean : Boolean) = eObject.setStatic(_Boolean : Boolean)
		override def getOwningType() = eObject.getOwningType()
		override def setOwningType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setOwningType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getDefinition = eObject.getDefinition
		override def setDefinition(definition : tudresden.ocl20.pivot.pivotmodel.Constraint) = eObject.setDefinition(definition)
	}


	private trait OperationAttributable extends tudresden.ocl20.pivot.pivotmodel.Operation with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Operation
	
		override def hasMatchingSignature(_paramTypes : java.util.List[tudresden.ocl20.pivot.pivotmodel.Type]) : Boolean = eObject.hasMatchingSignature( _paramTypes )
		override def addParameter(_param : tudresden.ocl20.pivot.pivotmodel.Parameter) : tudresden.ocl20.pivot.pivotmodel.Operation = eObject.addParameter( _param )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def bindTypeParameter(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type]) : tudresden.ocl20.pivot.pivotmodel.Operation = eObject.bindTypeParameter( _parameters ,  _types )
		override def addTypeParameter(_typeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter) : tudresden.ocl20.pivot.pivotmodel.GenericElement = eObject.addTypeParameter( _typeParameter )
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getType() = eObject.getType()
		override def setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getGenericType() = eObject.getGenericType()
		override def setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType) = eObject.setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType)
		override def isStatic() = eObject.isStatic()
		override def setStatic(_Boolean : Boolean) = eObject.setStatic(_Boolean : Boolean)
		override def getOwnedTypeParameter() = eObject.getOwnedTypeParameter()
		override def getOwningType() = eObject.getOwningType()
		override def setOwningType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setOwningType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getOwnedParameter() = eObject.getOwnedParameter()
		override def getInputParameter() = eObject.getInputParameter()
		override def getOutputParameter() = eObject.getOutputParameter()
		override def getReturnParameter() = eObject.getReturnParameter()
		override def getSignatureParameter() = eObject.getSignatureParameter()
		override def getDefinition = eObject.getDefinition
		override def setDefinition(definition : tudresden.ocl20.pivot.pivotmodel.Constraint) = eObject.setDefinition(definition)
	}


	private trait ParameterAttributable extends tudresden.ocl20.pivot.pivotmodel.Parameter with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Parameter
	
		override def asProperty() : tudresden.ocl20.pivot.pivotmodel.Property = eObject.asProperty()
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getType() = eObject.getType()
		override def setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getGenericType() = eObject.getGenericType()
		override def setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType) = eObject.setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType)
		override def getKind() = eObject.getKind()
		override def setKind(_ParameterDirectionKind : tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind) = eObject.setKind(_ParameterDirectionKind : tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind)
		override def getOperation() = eObject.getOperation()
		override def setOperation(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation) = eObject.setOperation(_Operation : tudresden.ocl20.pivot.pivotmodel.Operation)
	}




	private trait ParameterGenericTypeAttributable extends tudresden.ocl20.pivot.pivotmodel.ParameterGenericType with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.ParameterGenericType
	
		override def bindGenericType(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type], _typedElement : tudresden.ocl20.pivot.pivotmodel.TypedElement) : Boolean = eObject.bindGenericType( _parameters ,  _types ,  _typedElement )
		override def bindGenericSuperType(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type], _subType : tudresden.ocl20.pivot.pivotmodel.Type) : Boolean = eObject.bindGenericSuperType( _parameters ,  _types ,  _subType )
		override def isConformant(_type : tudresden.ocl20.pivot.pivotmodel.Type) : Boolean = eObject.isConformant( _type )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getTypeParameter() = eObject.getTypeParameter()
		override def setTypeParameter(_TypeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter) = eObject.setTypeParameter(_TypeParameter : tudresden.ocl20.pivot.pivotmodel.TypeParameter)
	}


	private trait ComplexGenericTypeAttributable extends tudresden.ocl20.pivot.pivotmodel.ComplexGenericType with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.ComplexGenericType
	
		override def addTypeArgument(_typeArgument : tudresden.ocl20.pivot.pivotmodel.TypeArgument) : tudresden.ocl20.pivot.pivotmodel.ComplexGenericType = eObject.addTypeArgument( _typeArgument )
		override def bindGenericType(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type], _typedElement : tudresden.ocl20.pivot.pivotmodel.TypedElement) : Boolean = eObject.bindGenericType( _parameters ,  _types ,  _typedElement )
		override def bindGenericSuperType(_parameters : java.util.List[tudresden.ocl20.pivot.pivotmodel.TypeParameter], _types : java.util.List[_ <: tudresden.ocl20.pivot.pivotmodel.Type], _subType : tudresden.ocl20.pivot.pivotmodel.Type) : Boolean = eObject.bindGenericSuperType( _parameters ,  _types ,  _subType )
		override def isConformant(_type : tudresden.ocl20.pivot.pivotmodel.Type) : Boolean = eObject.isConformant( _type )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getUnboundType() = eObject.getUnboundType()
		override def setUnboundType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setUnboundType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getTypeArgument() = eObject.getTypeArgument()
	}


	private trait TypeParameterAttributable extends tudresden.ocl20.pivot.pivotmodel.TypeParameter with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.TypeParameter
	
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getGenericElement() = eObject.getGenericElement()
		override def setGenericElement(_GenericElement : tudresden.ocl20.pivot.pivotmodel.GenericElement) = eObject.setGenericElement(_GenericElement : tudresden.ocl20.pivot.pivotmodel.GenericElement)
	}


	private trait TypeArgumentAttributable extends tudresden.ocl20.pivot.pivotmodel.TypeArgument with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.TypeArgument
	
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getType() = eObject.getType()
		override def setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getGenericType() = eObject.getGenericType()
		override def setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType) = eObject.setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType)
		override def getOwningGenericType() = eObject.getOwningGenericType()
		override def setOwningGenericType(_ComplexGenericType : tudresden.ocl20.pivot.pivotmodel.ComplexGenericType) = eObject.setOwningGenericType(_ComplexGenericType : tudresden.ocl20.pivot.pivotmodel.ComplexGenericType)
	}


	private trait ConstraintAttributable extends tudresden.ocl20.pivot.pivotmodel.Constraint with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Constraint
	
		override def addConstrainedElement(_constrainedElement : tudresden.ocl20.pivot.pivotmodel.ConstrainableElement) : tudresden.ocl20.pivot.pivotmodel.Constraint = eObject.addConstrainedElement( _constrainedElement )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getKind() = eObject.getKind()
		override def setKind(_ConstraintKind : tudresden.ocl20.pivot.pivotmodel.ConstraintKind) = eObject.setKind(_ConstraintKind : tudresden.ocl20.pivot.pivotmodel.ConstraintKind)
		override def getNamespace() = eObject.getNamespace()
		override def setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace) = eObject.setNamespace(_Namespace : tudresden.ocl20.pivot.pivotmodel.Namespace)
		override def getSpecification() = eObject.getSpecification()
		override def setSpecification(_Expression : tudresden.ocl20.pivot.pivotmodel.Expression) = eObject.setSpecification(_Expression : tudresden.ocl20.pivot.pivotmodel.Expression)
		override def getConstrainedElement() = eObject.getConstrainedElement()
		override def getDefinedFeature() = eObject.getDefinedFeature()
		override def setDefinedFeature(_Feature : tudresden.ocl20.pivot.pivotmodel.Feature) = eObject.setDefinedFeature(_Feature : tudresden.ocl20.pivot.pivotmodel.Feature)
	}


	private trait ExpressionAttributable extends tudresden.ocl20.pivot.pivotmodel.Expression with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.Expression
	
		override def getBody() = eObject.getBody()
		override def setBody(_String : String) = eObject.setBody(_String : String)
		override def getLanguage() = eObject.getLanguage()
		override def setLanguage(_String : String) = eObject.setLanguage(_String : String)
		override def getConstraint() = eObject.getConstraint()
		override def setConstraint(_Constraint : tudresden.ocl20.pivot.pivotmodel.Constraint) = eObject.setConstraint(_Constraint : tudresden.ocl20.pivot.pivotmodel.Constraint)
	}


	private trait NDirectionalPropertyAttributable extends tudresden.ocl20.pivot.pivotmodel.AssociationProperty with tudresden.attributegrammar.integration.kiama.AttributableEObject {
		type T = tudresden.ocl20.pivot.pivotmodel.AssociationProperty
	
		override def addAssociation(_bProperty : tudresden.ocl20.pivot.pivotmodel.AssociationProperty) : Unit = eObject.addAssociation( _bProperty )
		override def getAssociation(_propName : String) : tudresden.ocl20.pivot.pivotmodel.AssociationProperty = eObject.getAssociation( _propName )
		override def removeAssociation(_bProperty : tudresden.ocl20.pivot.pivotmodel.AssociationProperty) : Unit = eObject.removeAssociation( _bProperty )
		override def isInverseAssociation(_bProperty : tudresden.ocl20.pivot.pivotmodel.AssociationProperty) : Boolean = eObject.isInverseAssociation( _bProperty )
		override def addAssociations(_bProperty : java.util.List[tudresden.ocl20.pivot.pivotmodel.AssociationProperty]) : Unit = eObject.addAssociations( _bProperty )
		override def cmpSlots(_p : tudresden.ocl20.pivot.pivotmodel.Property) : Boolean = eObject.cmpSlots( _p )
		override def getQualifiedNameList() : java.util.List[String] = eObject.getQualifiedNameList()
		override def getName() = eObject.getName()
		override def setName(_String : String) = eObject.setName(_String : String)
		override def getQualifiedName() = eObject.getQualifiedName()
		override def getOwner() = eObject.getOwner()
		override def getType() = eObject.getType()
		override def setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getGenericType() = eObject.getGenericType()
		override def setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType) = eObject.setGenericType(_GenericType : tudresden.ocl20.pivot.pivotmodel.GenericType)
		override def isStatic() = eObject.isStatic()
		override def setStatic(_Boolean : Boolean) = eObject.setStatic(_Boolean : Boolean)
		override def getOwningType() = eObject.getOwningType()
		override def setOwningType(_Type : tudresden.ocl20.pivot.pivotmodel.Type) = eObject.setOwningType(_Type : tudresden.ocl20.pivot.pivotmodel.Type)
		override def getInverseAssociationProperties() = eObject.getInverseAssociationProperties()
		override def getDefinition = eObject.getDefinition
		override def setDefinition(definition : tudresden.ocl20.pivot.pivotmodel.Constraint) = eObject.setDefinition(definition)
	}

