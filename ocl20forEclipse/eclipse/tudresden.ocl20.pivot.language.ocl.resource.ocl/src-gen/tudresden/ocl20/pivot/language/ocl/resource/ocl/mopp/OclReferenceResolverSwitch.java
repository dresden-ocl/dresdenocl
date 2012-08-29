/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclReferenceResolverSwitch implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolverSwitch {
	
	/**
	 * This map stores a copy of the options the were set for loading the resource.
	 */
	private java.util.Map<Object, Object> options;
	
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.PackageDeclarationNestedNamespaceCSNamespaceReferenceResolver packageDeclarationNestedNamespaceCSNamespaceReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.PackageDeclarationNestedNamespaceCSNamespaceReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.AttributeContextDeclarationCSPropertyReferenceResolver attributeContextDeclarationCSPropertyReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.AttributeContextDeclarationCSPropertyReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OperationDefinitionCSOperationReferenceResolver operationDefinitionCSOperationReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OperationDefinitionCSOperationReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.ParameterCSParameterReferenceResolver parameterCSParameterReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.ParameterCSParameterReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OperationCallBaseExpCSOperationNameReferenceResolver operationCallBaseExpCSOperationNameReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OperationCallBaseExpCSOperationNameReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.PropertyCallBaseExpCSPropertyReferenceResolver propertyCallBaseExpCSPropertyReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.PropertyCallBaseExpCSPropertyReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.TypePathNameSimpleCSTypeNameReferenceResolver typePathNameSimpleCSTypeNameReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.TypePathNameSimpleCSTypeNameReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.TypePathNameNestedCSNamespaceReferenceResolver typePathNameNestedCSNamespaceReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.TypePathNameNestedCSNamespaceReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.CollectionTypeIdentifierCSTypeNameReferenceResolver collectionTypeIdentifierCSTypeNameReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.CollectionTypeIdentifierCSTypeNameReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.StaticOperationCallExpCSOperationNameReferenceResolver staticOperationCallExpCSOperationNameReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.StaticOperationCallExpCSOperationNameReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.EnumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver enumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.EnumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver();
	protected tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.NamedLiteralExpCSNamedElementReferenceResolver namedLiteralExpCSNamedElementReferenceResolver = new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.NamedLiteralExpCSNamedElementReferenceResolver();
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS, tudresden.ocl20.pivot.pivotmodel.Namespace> getPackageDeclarationNestedNamespaceCSNamespaceReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS_Namespace(), packageDeclarationNestedNamespaceCSNamespaceReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS, tudresden.ocl20.pivot.pivotmodel.Property> getAttributeContextDeclarationCSPropertyReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS_Property(), attributeContextDeclarationCSPropertyReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS, tudresden.ocl20.pivot.pivotmodel.Operation> getOperationDefinitionCSOperationReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionCS_Operation(), operationDefinitionCSOperationReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.ParameterCS, tudresden.ocl20.pivot.pivotmodel.Parameter> getParameterCSParameterReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getParameterCS_Parameter(), parameterCSParameterReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS, tudresden.ocl20.pivot.pivotmodel.Operation> getOperationCallBaseExpCSOperationNameReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getOperationCallBaseExpCS_OperationName(), operationCallBaseExpCSOperationNameReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS, tudresden.ocl20.pivot.pivotmodel.Property> getPropertyCallBaseExpCSPropertyReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPropertyCallBaseExpCS_Property(), propertyCallBaseExpCSPropertyReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS, tudresden.ocl20.pivot.pivotmodel.Type> getTypePathNameSimpleCSTypeNameReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getTypePathNameSimpleCS_TypeName(), typePathNameSimpleCSTypeNameReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS, tudresden.ocl20.pivot.pivotmodel.Namespace> getTypePathNameNestedCSNamespaceReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getTypePathNameNestedCS_Namespace(), typePathNameNestedCSNamespaceReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS, tudresden.ocl20.pivot.pivotmodel.Type> getCollectionTypeIdentifierCSTypeNameReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS_TypeName(), collectionTypeIdentifierCSTypeNameReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS, tudresden.ocl20.pivot.pivotmodel.Operation> getStaticOperationCallExpCSOperationNameReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS_OperationName(), staticOperationCallExpCSOperationNameReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS, tudresden.ocl20.pivot.pivotmodel.NamedElement> getEnumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getEnumLiteralOrStaticPropertyExpCS_EnumLiteralOrStaticProperty(), enumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS, tudresden.ocl20.pivot.pivotmodel.NamedElement> getNamedLiteralExpCSNamedElementReferenceResolver() {
		return getResolverChain(tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getNamedLiteralExpCS_NamedElement(), namedLiteralExpCSNamedElementReferenceResolver);
	}
	
	public void setOptions(java.util.Map<?, ?> options) {
		if (options != null) {
			this.options = new java.util.LinkedHashMap<Object, Object>();
			this.options.putAll(options);
		}
		packageDeclarationNestedNamespaceCSNamespaceReferenceResolver.setOptions(options);
		attributeContextDeclarationCSPropertyReferenceResolver.setOptions(options);
		operationDefinitionCSOperationReferenceResolver.setOptions(options);
		parameterCSParameterReferenceResolver.setOptions(options);
		operationCallBaseExpCSOperationNameReferenceResolver.setOptions(options);
		propertyCallBaseExpCSPropertyReferenceResolver.setOptions(options);
		typePathNameSimpleCSTypeNameReferenceResolver.setOptions(options);
		typePathNameNestedCSNamespaceReferenceResolver.setOptions(options);
		collectionTypeIdentifierCSTypeNameReferenceResolver.setOptions(options);
		staticOperationCallExpCSOperationNameReferenceResolver.setOptions(options);
		enumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver.setOptions(options);
		namedLiteralExpCSNamedElementReferenceResolver.setOptions(options);
	}
	
	public void resolveFuzzy(String identifier, org.eclipse.emf.ecore.EObject container, org.eclipse.emf.ecore.EReference reference, int position, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolveResult<org.eclipse.emf.ecore.EObject> result) {
		if (container == null) {
			return;
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Namespace> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Namespace>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("namespace")) {
				packageDeclarationNestedNamespaceCSNamespaceReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Property> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Property>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("property")) {
				attributeContextDeclarationCSPropertyReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.AttributeContextDeclarationCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("operation")) {
				operationDefinitionCSOperationReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.OperationDefinitionCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getParameterCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Parameter> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Parameter>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("parameter")) {
				parameterCSParameterReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.ParameterCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getOperationCallBaseExpCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("operationName")) {
				operationCallBaseExpCSOperationNameReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.OperationCallBaseExpCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPropertyCallBaseExpCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Property> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Property>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("property")) {
				propertyCallBaseExpCSPropertyReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.PropertyCallBaseExpCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getTypePathNameSimpleCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Type> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Type>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("typeName")) {
				typePathNameSimpleCSTypeNameReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.TypePathNameSimpleCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getTypePathNameNestedCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Namespace> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Namespace>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("namespace")) {
				typePathNameNestedCSNamespaceReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Type> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Type>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("typeName")) {
				collectionTypeIdentifierCSTypeNameReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.CollectionTypeIdentifierCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.Operation>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("operationName")) {
				staticOperationCallExpCSOperationNameReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.StaticOperationCallExpCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getEnumLiteralOrStaticPropertyExpCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.NamedElement> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.NamedElement>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("enumLiteralOrStaticProperty")) {
				enumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.EnumLiteralOrStaticPropertyExpCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
		if (tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getNamedLiteralExpCS().isInstance(container)) {
			OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.NamedElement> frr = new OclFuzzyResolveResult<tudresden.ocl20.pivot.pivotmodel.NamedElement>(result);
			String referenceName = reference.getName();
			org.eclipse.emf.ecore.EStructuralFeature feature = container.eClass().getEStructuralFeature(referenceName);
			if (feature != null && feature instanceof org.eclipse.emf.ecore.EReference && referenceName != null && referenceName.equals("namedElement")) {
				namedLiteralExpCSNamedElementReferenceResolver.resolve(identifier, (tudresden.ocl20.pivot.language.ocl.NamedLiteralExpCS) container, (org.eclipse.emf.ecore.EReference) feature, position, true, frr);
			}
		}
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<? extends org.eclipse.emf.ecore.EObject, ? extends org.eclipse.emf.ecore.EObject> getResolver(org.eclipse.emf.ecore.EStructuralFeature reference) {
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPackageDeclarationNestedNamespaceCS_Namespace()) {
			return getResolverChain(reference, packageDeclarationNestedNamespaceCSNamespaceReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getAttributeContextDeclarationCS_Property()) {
			return getResolverChain(reference, attributeContextDeclarationCSPropertyReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getOperationDefinitionCS_Operation()) {
			return getResolverChain(reference, operationDefinitionCSOperationReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getParameterCS_Parameter()) {
			return getResolverChain(reference, parameterCSParameterReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getOperationCallBaseExpCS_OperationName()) {
			return getResolverChain(reference, operationCallBaseExpCSOperationNameReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getPropertyCallBaseExpCS_Property()) {
			return getResolverChain(reference, propertyCallBaseExpCSPropertyReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getTypePathNameSimpleCS_TypeName()) {
			return getResolverChain(reference, typePathNameSimpleCSTypeNameReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getTypePathNameNestedCS_Namespace()) {
			return getResolverChain(reference, typePathNameNestedCSNamespaceReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getCollectionTypeIdentifierCS_TypeName()) {
			return getResolverChain(reference, collectionTypeIdentifierCSTypeNameReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getStaticOperationCallExpCS_OperationName()) {
			return getResolverChain(reference, staticOperationCallExpCSOperationNameReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getEnumLiteralOrStaticPropertyExpCS_EnumLiteralOrStaticProperty()) {
			return getResolverChain(reference, enumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver);
		}
		if (reference == tudresden.ocl20.pivot.language.ocl.OclPackage.eINSTANCE.getNamedLiteralExpCS_NamedElement()) {
			return getResolverChain(reference, namedLiteralExpCSNamedElementReferenceResolver);
		}
		return null;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})	
	public <ContainerType extends org.eclipse.emf.ecore.EObject, ReferenceType extends org.eclipse.emf.ecore.EObject> tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<ContainerType, ReferenceType> getResolverChain(org.eclipse.emf.ecore.EStructuralFeature reference, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver<ContainerType, ReferenceType> originalResolver) {
		if (options == null) {
			return originalResolver;
		}
		Object value = options.get(tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.ADDITIONAL_REFERENCE_RESOLVERS);
		if (value == null) {
			return originalResolver;
		}
		if (!(value instanceof java.util.Map)) {
			// send this to the error log
			new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclRuntimeUtil().logWarning("Found value with invalid type for option " + tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + java.util.Map.class.getName() + ", but was " + value.getClass().getName() + ")", null);
			return originalResolver;
		}
		java.util.Map<?,?> resolverMap = (java.util.Map<?,?>) value;
		Object resolverValue = resolverMap.get(reference);
		if (resolverValue == null) {
			return originalResolver;
		}
		if (resolverValue instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver) {
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver replacingResolver = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver) resolverValue;
			if (replacingResolver instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclDelegatingReferenceResolver) {
				// pass original resolver to the replacing one
				((tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclDelegatingReferenceResolver) replacingResolver).setDelegate(originalResolver);
			}
			return replacingResolver;
		} else if (resolverValue instanceof java.util.Collection) {
			java.util.Collection replacingResolvers = (java.util.Collection) resolverValue;
			tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver replacingResolver = originalResolver;
			for (Object next : replacingResolvers) {
				if (next instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceCache) {
					tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver nextResolver = (tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolver) next;
					if (nextResolver instanceof tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclDelegatingReferenceResolver) {
						// pass original resolver to the replacing one
						((tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclDelegatingReferenceResolver) nextResolver).setDelegate(replacingResolver);
					}
					replacingResolver = nextResolver;
				} else {
					// The collection contains a non-resolver. Send a warning to the error log.
					new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclRuntimeUtil().logWarning("Found value with invalid type in value map for option " + tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclDelegatingReferenceResolver.class.getName() + ", but was " + next.getClass().getName() + ")", null);
				}
			}
			return replacingResolver;
		} else {
			// The value for the option ADDITIONAL_REFERENCE_RESOLVERS has an unknown type.
			new tudresden.ocl20.pivot.language.ocl.resource.ocl.util.OclRuntimeUtil().logWarning("Found value with invalid type in value map for option " + tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.ADDITIONAL_REFERENCE_RESOLVERS + " (expected " + tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclDelegatingReferenceResolver.class.getName() + ", but was " + resolverValue.getClass().getName() + ")", null);
			return originalResolver;
		}
	}
	
}
