/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.io.PrintWriter;

import org.eclipse.emf.ecore.EObject;

public class OclPrinter implements org.dresdenocl.language.ocl.resource.ocl.IOclTextPrinter {
	
	protected org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolverFactory tokenResolverFactory = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenResolverFactory();
	
	protected java.io.OutputStream outputStream;
	
	/**
	 * Holds the resource that is associated with this printer. This may be null if
	 * the printer is used stand alone.
	 */
	private org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource;
	
	private java.util.Map<?, ?> options;
	private String encoding = System.getProperty("file.encoding");
	
	public OclPrinter(java.io.OutputStream outputStream, org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource) {
		super();
		this.outputStream = outputStream;
		this.resource = resource;
	}
	
	protected int matchCount(java.util.Map<String, Integer> featureCounter, java.util.Collection<String> needed) {
		int pos = 0;
		int neg = 0;
		
		for (String featureName : featureCounter.keySet()) {
			if (needed.contains(featureName)) {
				int value = featureCounter.get(featureName);
				if (value == 0) {
					neg += 1;
				} else {
					pos += 1;
				}
			}
		}
		return neg > 0 ? -neg : pos;
	}
	
	protected void doPrint(org.eclipse.emf.ecore.EObject element, java.io.PrintWriter out, String globaltab) {
		if (element == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write.");
		}
		if (out == null) {
			throw new java.lang.IllegalArgumentException("Nothing to write on.");
		}
		
		if (element instanceof org.dresdenocl.language.ocl.SimpleNameCS) {
			print_org_dresdenocl_language_ocl_SimpleNameCS((org.dresdenocl.language.ocl.SimpleNameCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS) {
			print_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS((org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS) {
			print_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS((org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS) {
			print_org_dresdenocl_language_ocl_PackageDeclarationWithoutNamespaceCS((org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationContextDeclarationCS) {
			print_org_dresdenocl_language_ocl_OperationContextDeclarationCS((org.dresdenocl.language.ocl.OperationContextDeclarationCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.AttributeContextDeclarationCS) {
			print_org_dresdenocl_language_ocl_AttributeContextDeclarationCS((org.dresdenocl.language.ocl.AttributeContextDeclarationCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ClassifierContextDeclarationCS) {
			print_org_dresdenocl_language_ocl_ClassifierContextDeclarationCS((org.dresdenocl.language.ocl.ClassifierContextDeclarationCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.InitValueCS) {
			print_org_dresdenocl_language_ocl_InitValueCS((org.dresdenocl.language.ocl.InitValueCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DeriveValueCS) {
			print_org_dresdenocl_language_ocl_DeriveValueCS((org.dresdenocl.language.ocl.DeriveValueCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.InvariantExpCS) {
			print_org_dresdenocl_language_ocl_InvariantExpCS((org.dresdenocl.language.ocl.InvariantExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DefinitionExpCS) {
			print_org_dresdenocl_language_ocl_DefinitionExpCS((org.dresdenocl.language.ocl.DefinitionExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DefinitionExpPropertyCS) {
			print_org_dresdenocl_language_ocl_DefinitionExpPropertyCS((org.dresdenocl.language.ocl.DefinitionExpPropertyCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.DefinitionExpOperationCS) {
			print_org_dresdenocl_language_ocl_DefinitionExpOperationCS((org.dresdenocl.language.ocl.DefinitionExpOperationCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PreConditionDeclarationCS) {
			print_org_dresdenocl_language_ocl_PreConditionDeclarationCS((org.dresdenocl.language.ocl.PreConditionDeclarationCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PostConditionDeclarationCS) {
			print_org_dresdenocl_language_ocl_PostConditionDeclarationCS((org.dresdenocl.language.ocl.PostConditionDeclarationCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.BodyDeclarationCS) {
			print_org_dresdenocl_language_ocl_BodyDeclarationCS((org.dresdenocl.language.ocl.BodyDeclarationCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationDefinitionInContextCS) {
			print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS((org.dresdenocl.language.ocl.OperationDefinitionInContextCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationDefinitionInDefCS) {
			print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS((org.dresdenocl.language.ocl.OperationDefinitionInDefCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ParameterCS) {
			print_org_dresdenocl_language_ocl_ParameterCS((org.dresdenocl.language.ocl.ParameterCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_LogicalImpliesOperationCallExpCS((org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_LogicalXorOperationCallExpCS((org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_LogicalOrOperationCallExpCS((org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_LogicalAndOperationCallExpCS((org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.EqualityOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_EqualityOperationCallExpCS((org.dresdenocl.language.ocl.EqualityOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.RelationalOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_RelationalOperationCallExpCS((org.dresdenocl.language.ocl.RelationalOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.AdditiveOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_AdditiveOperationCallExpCS((org.dresdenocl.language.ocl.AdditiveOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.MultOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_MultOperationCallExpCS((org.dresdenocl.language.ocl.MultOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.UnaryOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_UnaryOperationCallExpCS((org.dresdenocl.language.ocl.UnaryOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_LogicalNotOperationCallExpCS((org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.NavigationCallExp) {
			print_org_dresdenocl_language_ocl_NavigationCallExp((org.dresdenocl.language.ocl.NavigationCallExp) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ImplicitOperationCallCS) {
			print_org_dresdenocl_language_ocl_ImplicitOperationCallCS((org.dresdenocl.language.ocl.ImplicitOperationCallCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.ImplicitPropertyCallCS) {
			print_org_dresdenocl_language_ocl_ImplicitPropertyCallCS((org.dresdenocl.language.ocl.ImplicitPropertyCallCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IteratorExpCS) {
			print_org_dresdenocl_language_ocl_IteratorExpCS((org.dresdenocl.language.ocl.IteratorExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IterateExpCS) {
			print_org_dresdenocl_language_ocl_IterateExpCS((org.dresdenocl.language.ocl.IterateExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IteratorExpVariableCS) {
			print_org_dresdenocl_language_ocl_IteratorExpVariableCS((org.dresdenocl.language.ocl.IteratorExpVariableCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TypePathNameSimpleCS) {
			print_org_dresdenocl_language_ocl_TypePathNameSimpleCS((org.dresdenocl.language.ocl.TypePathNameSimpleCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TypePathNameNestedCS) {
			print_org_dresdenocl_language_ocl_TypePathNameNestedCS((org.dresdenocl.language.ocl.TypePathNameNestedCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TupleTypeCS) {
			print_org_dresdenocl_language_ocl_TupleTypeCS((org.dresdenocl.language.ocl.TupleTypeCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionTypeIdentifierCS) {
			print_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS((org.dresdenocl.language.ocl.CollectionTypeIdentifierCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS) {
			print_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS((org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS) {
			print_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS((org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithInitCS) {
			print_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS((org.dresdenocl.language.ocl.VariableDeclarationWithInitCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS) {
			print_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS((org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.OperationCallOnSelfExpCS) {
			print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS((org.dresdenocl.language.ocl.OperationCallOnSelfExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.StaticOperationCallExpCS) {
			print_org_dresdenocl_language_ocl_StaticOperationCallExpCS((org.dresdenocl.language.ocl.StaticOperationCallExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.EnumLiteralOrStaticPropertyExpCS) {
			print_org_dresdenocl_language_ocl_EnumLiteralOrStaticPropertyExpCS((org.dresdenocl.language.ocl.EnumLiteralOrStaticPropertyExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TupleLiteralExpCS) {
			print_org_dresdenocl_language_ocl_TupleLiteralExpCS((org.dresdenocl.language.ocl.TupleLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IfExpCS) {
			print_org_dresdenocl_language_ocl_IfExpCS((org.dresdenocl.language.ocl.IfExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionLiteralExpCS) {
			print_org_dresdenocl_language_ocl_CollectionLiteralExpCS((org.dresdenocl.language.ocl.CollectionLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionRangeCS) {
			print_org_dresdenocl_language_ocl_CollectionRangeCS((org.dresdenocl.language.ocl.CollectionRangeCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS) {
			print_org_dresdenocl_language_ocl_CollectionLiteralPartsOclExpCS((org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS) {
			print_org_dresdenocl_language_ocl_CollectionTypeLiteralExpCS((org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.TupleTypeLiteralExpCS) {
			print_org_dresdenocl_language_ocl_TupleTypeLiteralExpCS((org.dresdenocl.language.ocl.TupleTypeLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS) {
			print_org_dresdenocl_language_ocl_PropertyCallOnSelfExpCS((org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.LetExpCS) {
			print_org_dresdenocl_language_ocl_LetExpCS((org.dresdenocl.language.ocl.LetExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.RealLiteralExpCS) {
			print_org_dresdenocl_language_ocl_RealLiteralExpCS((org.dresdenocl.language.ocl.RealLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.IntegerLiteralExpCS) {
			print_org_dresdenocl_language_ocl_IntegerLiteralExpCS((org.dresdenocl.language.ocl.IntegerLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.BooleanLiteralExpCS) {
			print_org_dresdenocl_language_ocl_BooleanLiteralExpCS((org.dresdenocl.language.ocl.BooleanLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.StringLiteralExpCS) {
			print_org_dresdenocl_language_ocl_StringLiteralExpCS((org.dresdenocl.language.ocl.StringLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.InvalidLiteralExpCS) {
			print_org_dresdenocl_language_ocl_InvalidLiteralExpCS((org.dresdenocl.language.ocl.InvalidLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.NullLiteralExpCS) {
			print_org_dresdenocl_language_ocl_NullLiteralExpCS((org.dresdenocl.language.ocl.NullLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.NamedLiteralExpCS) {
			print_org_dresdenocl_language_ocl_NamedLiteralExpCS((org.dresdenocl.language.ocl.NamedLiteralExpCS) element, globaltab, out);
			return;
		}
		if (element instanceof org.dresdenocl.language.ocl.BracketExpCS) {
			print_org_dresdenocl_language_ocl_BracketExpCS((org.dresdenocl.language.ocl.BracketExpCS) element, globaltab, out);
			return;
		}
		
		addWarningToResource("The printer can not handle " + element.eClass().getName() + " elements", element);
	}
	
	protected org.dresdenocl.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch getReferenceResolverSwitch() {
		return (org.dresdenocl.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch) new org.dresdenocl.language.ocl.resource.ocl.mopp.OclMetaInformation().getReferenceResolverSwitch();
	}
	
	protected void addWarningToResource(final String errorMessage, org.eclipse.emf.ecore.EObject cause) {
		org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource = getResource();
		if (resource == null) {
			// the resource can be null if the printer is used stand alone
			return;
		}
		resource.addProblem(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclProblem(errorMessage, org.dresdenocl.language.ocl.resource.ocl.OclEProblemType.PRINT_PROBLEM, org.dresdenocl.language.ocl.resource.ocl.OclEProblemSeverity.WARNING), cause);
	}
	
	public void setOptions(java.util.Map<?,?> options) {
		this.options = options;
	}
	
	public java.util.Map<?,?> getOptions() {
		return options;
	}
	
	public void setEncoding(String encoding) {
		if (encoding != null) {
			this.encoding = encoding;
		}
	}
	
	public String getEncoding() {
		return encoding;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextResource getResource() {
		return resource;
	}
	
	/**
	 * Calls {@link #doPrint(EObject, PrintWriter, String)} and writes the result to
	 * the underlying output stream.
	 */
	public void print(org.eclipse.emf.ecore.EObject element) throws java.io.IOException {
		java.io.PrintWriter out = new java.io.PrintWriter(new java.io.OutputStreamWriter(new java.io.BufferedOutputStream(outputStream), encoding));
		doPrint(element, out, "");
		out.flush();
		out.close();
	}
	
	public void print_org_dresdenocl_language_ocl_SimpleNameCS(org.dresdenocl.language.ocl.SimpleNameCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME));
		printCountingMap.put("simpleName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("simpleName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.SIMPLE_NAME_CS__SIMPLE_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("simpleName", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS(org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__CONTEXT_DECLARATIONS));
		printCountingMap.put("contextDeclarations", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__LAYOUT_INFORMATION));
		printCountingMap.put("layoutInformation", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE));
		printCountingMap.put("nestedNamespace", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("package");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("nestedNamespace");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("nestedNamespace", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("endpackage");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_PackageDeclarationWithNamespaceCS_0(org.dresdenocl.language.ocl.PackageDeclarationWithNamespaceCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("contextDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__CONTEXT_DECLARATIONS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("contextDeclarations", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS(org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE));
		printCountingMap.put("namespace", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NESTED_NAMESPACE));
		printCountingMap.put("nestedNamespace", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("namespace");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getPackageDeclarationNestedNamespaceCSNamespaceReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Namespace) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NAMESPACE), element));
				out.print(" ");
			}
			printCountingMap.put("namespace", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_PackageDeclarationNestedNamespaceCS_0(org.dresdenocl.language.ocl.PackageDeclarationNestedNamespaceCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("nestedNamespace");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_NESTED_NAMESPACE_CS__NESTED_NAMESPACE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("nestedNamespace", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_PackageDeclarationWithoutNamespaceCS(org.dresdenocl.language.ocl.PackageDeclarationWithoutNamespaceCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS__CONTEXT_DECLARATIONS));
		printCountingMap.put("contextDeclarations", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS__LAYOUT_INFORMATION));
		printCountingMap.put("layoutInformation", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("contextDeclarations");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PACKAGE_DECLARATION_WITHOUT_NAMESPACE_CS__CONTEXT_DECLARATIONS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("contextDeclarations", 0);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_OperationContextDeclarationCS(org.dresdenocl.language.ocl.OperationContextDeclarationCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS));
		printCountingMap.put("prePostOrBodyDeclarations", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("context");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("operation");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("operation", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("prePostOrBodyDeclarations");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("prePostOrBodyDeclarations", 0);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_AttributeContextDeclarationCS(org.dresdenocl.language.ocl.AttributeContextDeclarationCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY));
		printCountingMap.put("property", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE));
		printCountingMap.put("type", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE));
		printCountingMap.put("initOrDeriveValue", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("context");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("property");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getAttributeContextDeclarationCSPropertyReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Property) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__PROPERTY), element));
				out.print(" ");
			}
			printCountingMap.put("property", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_AttributeContextDeclarationCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initOrDeriveValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("initOrDeriveValue", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initOrDeriveValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__INIT_OR_DERIVE_VALUE));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("initOrDeriveValue", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_AttributeContextDeclarationCS_0(org.dresdenocl.language.ocl.AttributeContextDeclarationCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("type");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ATTRIBUTE_CONTEXT_DECLARATION_CS__TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("type", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_ClassifierContextDeclarationCS(org.dresdenocl.language.ocl.ClassifierContextDeclarationCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS));
		printCountingMap.put("invariantsAndDefinitions", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("context");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("invariantsAndDefinitions");
		if (count > 0) {
			java.util.List<?> list = (java.util.List<?>)element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.CLASSIFIER_CONTEXT_DECLARATION_CS__INVARIANTS_AND_DEFINITIONS));
			int index  = list.size() - count;
			if (index < 0) {
				index = 0;
			}
			java.util.ListIterator<?> it  = list.listIterator(index);
			while (it.hasNext()) {
				Object o = it.next();
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("invariantsAndDefinitions", 0);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_InitValueCS(org.dresdenocl.language.ocl.InitValueCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INIT_VALUE_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("init");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INIT_VALUE_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_DeriveValueCS(org.dresdenocl.language.ocl.DeriveValueCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DERIVE_VALUE_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("derive");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DERIVE_VALUE_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_InvariantExpCS(org.dresdenocl.language.ocl.InvariantExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("inv");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("name", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_DefinitionExpCS(org.dresdenocl.language.ocl.DefinitionExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__STATIC));
		printCountingMap.put("static", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART));
		printCountingMap.put("definitionExpPart", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_DefinitionExpCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("def");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("definitionExpPart");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("definitionExpPart", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_DefinitionExpCS_0(org.dresdenocl.language.ocl.DefinitionExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("static");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__STATIC));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("STATIC");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_CS__STATIC), element));
				out.print(" ");
			}
			printCountingMap.put("static", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_DefinitionExpPropertyCS(org.dresdenocl.language.ocl.DefinitionExpPropertyCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_PROPERTY_CS__VARIABLE_DECLARATION));
		printCountingMap.put("variableDeclaration", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclaration");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_PROPERTY_CS__VARIABLE_DECLARATION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclaration", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_DefinitionExpOperationCS(org.dresdenocl.language.ocl.DefinitionExpOperationCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__EQUAL));
		printCountingMap.put("equal", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("operation");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OPERATION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("operation", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("equal");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__EQUAL));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__EQUAL), element));
				out.print(" ");
			}
			printCountingMap.put("equal", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.DEFINITION_EXP_OPERATION_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_PreConditionDeclarationCS(org.dresdenocl.language.ocl.PreConditionDeclarationCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("pre");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_PreConditionDeclarationCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_PreConditionDeclarationCS_0(org.dresdenocl.language.ocl.PreConditionDeclarationCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PRE_CONDITION_DECLARATION_CS__NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_PostConditionDeclarationCS(org.dresdenocl.language.ocl.PostConditionDeclarationCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("post");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_PostConditionDeclarationCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_PostConditionDeclarationCS_0(org.dresdenocl.language.ocl.PostConditionDeclarationCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.POST_CONDITION_DECLARATION_CS__NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_BodyDeclarationCS(org.dresdenocl.language.ocl.BodyDeclarationCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__NAME));
		printCountingMap.put("name", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("body");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_BodyDeclarationCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_BodyDeclarationCS_0(org.dresdenocl.language.ocl.BodyDeclarationCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("name");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BODY_DECLARATION_CS__NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("name", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS(org.dresdenocl.language.ocl.OperationDefinitionInContextCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS));
		printCountingMap.put("parameters", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__RETURN_TYPE));
		printCountingMap.put("returnType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operation");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationDefinitionCSOperationReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__OPERATION), element));
			}
			printCountingMap.put("operation", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS_0(org.dresdenocl.language.ocl.OperationDefinitionInContextCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS_0_0(org.dresdenocl.language.ocl.OperationDefinitionInContextCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__PARAMETERS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInContextCS_1(org.dresdenocl.language.ocl.OperationDefinitionInContextCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("returnType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_CONTEXT_CS__RETURN_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("returnType", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS(org.dresdenocl.language.ocl.OperationDefinitionInDefCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION));
		printCountingMap.put("operation", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS));
		printCountingMap.put("parameters", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__RETURN_TYPE));
		printCountingMap.put("returnType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operation");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationDefinitionCSOperationReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__OPERATION), element));
				out.print(" ");
			}
			printCountingMap.put("operation", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS_0(org.dresdenocl.language.ocl.OperationDefinitionInDefCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS_0_0(org.dresdenocl.language.ocl.OperationDefinitionInDefCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameters");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__PARAMETERS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("parameters", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationDefinitionInDefCS_1(org.dresdenocl.language.ocl.OperationDefinitionInDefCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("returnType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_DEFINITION_IN_DEF_CS__RETURN_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("returnType", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_ParameterCS(org.dresdenocl.language.ocl.ParameterCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER));
		printCountingMap.put("parameter", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER_TYPE));
		printCountingMap.put("parameterType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("parameter");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getParameterCSParameterReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Parameter) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER), element));
				out.print(" ");
			}
			printCountingMap.put("parameter", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("parameterType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PARAMETER_CS__PARAMETER_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("parameterType", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_LogicalImpliesOperationCallExpCS(org.dresdenocl.language.ocl.LogicalImpliesOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("IMPLIES_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_IMPLIES_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_LogicalXorOperationCallExpCS(org.dresdenocl.language.ocl.LogicalXorOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("XOR_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_XOR_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_LogicalOrOperationCallExpCS(org.dresdenocl.language.ocl.LogicalOrOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("OR_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_OR_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_LogicalAndOperationCallExpCS(org.dresdenocl.language.ocl.LogicalAndOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("AND_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_AND_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_EqualityOperationCallExpCS(org.dresdenocl.language.ocl.EqualityOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_dresdenocl_language_ocl_EqualityOperationCallExpCS_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_EqualityOperationCallExpCS_0(org.dresdenocl.language.ocl.EqualityOperationCallExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NEQUALITY_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
			count = printCountingMap.get("operationName");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME));
				if (o != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.EQUALITY_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
					out.print(" ");
				}
				printCountingMap.put("operationName", count - 1);
			}
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_RelationalOperationCallExpCS(org.dresdenocl.language.ocl.RelationalOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("RELATIONAL_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.RELATIONAL_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_AdditiveOperationCallExpCS(org.dresdenocl.language.ocl.AdditiveOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("ADDITIVE_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ADDITIVE_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_MultOperationCallExpCS(org.dresdenocl.language.ocl.MultOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("MULT_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.MULT_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_UnaryOperationCallExpCS(org.dresdenocl.language.ocl.UnaryOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("ADDITIVE_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.UNARY_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_LogicalNotOperationCallExpCS(org.dresdenocl.language.ocl.LogicalNotOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__TARGET));
		printCountingMap.put("target", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NOT_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("target");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LOGICAL_NOT_OPERATION_CALL_EXP_CS__TARGET));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("target", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_NavigationCallExp(org.dresdenocl.language.ocl.NavigationCallExp element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__SOURCE));
		printCountingMap.put("source", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR));
		printCountingMap.put("navigationOperator", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS));
		printCountingMap.put("featureCalls", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("source");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__SOURCE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("source", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("navigationOperator");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NAVIGATION_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR), element));
			}
			printCountingMap.put("navigationOperator", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("featureCalls");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("featureCalls", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_NavigationCallExp_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_NavigationCallExp_0(org.dresdenocl.language.ocl.NavigationCallExp element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("navigationOperator");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NAVIGATION_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__NAVIGATION_OPERATOR), element));
			}
			printCountingMap.put("navigationOperator", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("featureCalls");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAVIGATION_CALL_EXP__FEATURE_CALLS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("featureCalls", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_ImplicitOperationCallCS(org.dresdenocl.language.ocl.ImplicitOperationCallCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_dresdenocl_language_ocl_ImplicitOperationCallCS_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_ImplicitOperationCallCS_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_ImplicitOperationCallCS_0(org.dresdenocl.language.ocl.ImplicitOperationCallCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 2;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 3;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 4;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 5;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 6;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 7;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NEQUALITY_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 2:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NOT_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 3:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("AND_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 4:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("OR_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 5:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("XOR_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 6:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("IMPLIES_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 7:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
			count = printCountingMap.get("operationName");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME));
				if (o != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__OPERATION_NAME), element));
					out.print(" ");
				}
				printCountingMap.put("operationName", count - 1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_ImplicitOperationCallCS_1(org.dresdenocl.language.ocl.ImplicitOperationCallCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_ImplicitOperationCallCS_1_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_ImplicitOperationCallCS_1_0(org.dresdenocl.language.ocl.ImplicitOperationCallCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_OPERATION_CALL_CS__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_ImplicitPropertyCallCS(org.dresdenocl.language.ocl.ImplicitPropertyCallCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY));
		printCountingMap.put("property", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("property");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getPropertyCallBaseExpCSPropertyReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Property) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__PROPERTY), element));
				out.print(" ");
			}
			printCountingMap.put("property", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_ImplicitPropertyCallCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_ImplicitPropertyCallCS_0(org.dresdenocl.language.ocl.ImplicitPropertyCallCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("isMarkedPre");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__IS_MARKED_PRE));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("IS_MARKED_PRE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IMPLICIT_PROPERTY_CALL_CS__IS_MARKED_PRE), element));
				out.print(" ");
			}
			printCountingMap.put("isMarkedPre", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_IteratorExpCS(org.dresdenocl.language.ocl.IteratorExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME));
		printCountingMap.put("iteratorName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES));
		printCountingMap.put("iteratorVariables", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION));
		printCountingMap.put("bodyExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("iteratorName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("ITERATOR_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME), element));
			}
			printCountingMap.put("iteratorName", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_IteratorExpCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("bodyExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("bodyExpression", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_IteratorExpCS_0(org.dresdenocl.language.ocl.IteratorExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("iteratorVariables");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("iteratorVariables", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_IteratorExpCS_0_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("|");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_IteratorExpCS_0_0(org.dresdenocl.language.ocl.IteratorExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("iteratorVariables");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("iteratorVariables", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_IterateExpCS(org.dresdenocl.language.ocl.IterateExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE));
		printCountingMap.put("iteratorVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE));
		printCountingMap.put("resultVariable", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION));
		printCountingMap.put("bodyExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("iterate");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_IterateExpCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("resultVariable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("resultVariable", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("|");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("bodyExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("bodyExpression", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_IterateExpCS_0(org.dresdenocl.language.ocl.IterateExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("iteratorVariable");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("iteratorVariable", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(";");
		out.print(" ");
	}
	
	
	public void print_org_dresdenocl_language_ocl_IteratorExpVariableCS(org.dresdenocl.language.ocl.IteratorExpVariableCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME));
		printCountingMap.put("variableName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__VARIABLE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableName", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_IteratorExpVariableCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_IteratorExpVariableCS_0(org.dresdenocl.language.ocl.IteratorExpVariableCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ITERATOR_EXP_VARIABLE_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_TypePathNameSimpleCS(org.dresdenocl.language.ocl.TypePathNameSimpleCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_SIMPLE_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_SIMPLE_CS__TYPE_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getTypePathNameSimpleCSTypeNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Type) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_SIMPLE_CS__TYPE_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_SIMPLE_CS__TYPE_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("typeName", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_TypePathNameNestedCS(org.dresdenocl.language.ocl.TypePathNameNestedCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE));
		printCountingMap.put("namespace", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME));
		printCountingMap.put("typePathName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("namespace");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getTypePathNameNestedCSNamespaceReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Namespace) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE), element));
			}
			printCountingMap.put("namespace", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typePathName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typePathName", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_TupleTypeCS(org.dresdenocl.language.ocl.TupleTypeCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST));
		printCountingMap.put("variableDeclarationList", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("Tuple");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarationList");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_CS__VARIABLE_DECLARATION_LIST));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarationList", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS(org.dresdenocl.language.ocl.CollectionTypeIdentifierCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE));
		printCountingMap.put("genericType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("COLLECTION_TYPES");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getCollectionTypeIdentifierCSTypeNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Type) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__TYPE_NAME), element));
				out.print(" ");
			}
			printCountingMap.put("typeName", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_CollectionTypeIdentifierCS_0(org.dresdenocl.language.ocl.CollectionTypeIdentifierCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("genericType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_IDENTIFIER_CS__GENERIC_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("genericType", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
	public void print_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitCS(org.dresdenocl.language.ocl.VariableDeclarationWithoutInitCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__VARIABLE_NAME));
		printCountingMap.put("variableName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__VARIABLE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableName", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS(org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS));
		printCountingMap.put("variableDeclarations", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarations", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_VariableDeclarationWithoutInitListCS_0(org.dresdenocl.language.ocl.VariableDeclarationWithoutInitListCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITHOUT_INIT_LIST_CS__VARIABLE_DECLARATIONS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarations", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS(org.dresdenocl.language.ocl.VariableDeclarationWithInitCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(4);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__VARIABLE_NAME));
		printCountingMap.put("variableName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION));
		printCountingMap.put("initialization", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL));
		printCountingMap.put("equal", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__VARIABLE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableName", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("equal");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL), element));
				out.print(" ");
			}
			printCountingMap.put("equal", count - 1);
		}
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("initialization");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("initialization", count - 1);
		}
	}
	
	public void print_org_dresdenocl_language_ocl_VariableDeclarationWithInitCS_0(org.dresdenocl.language.ocl.VariableDeclarationWithInitCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(":");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS(org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS));
		printCountingMap.put("variableDeclarations", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarations", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_VariableDeclarationWithInitListCS_0(org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.VARIABLE_DECLARATION_WITH_INIT_LIST_CS__VARIABLE_DECLARATIONS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarations", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS(org.dresdenocl.language.ocl.OperationCallOnSelfExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		// print collected hidden tokens
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_0(element, localtab, out, printCountingMap);
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_1(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_2(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_0(org.dresdenocl.language.ocl.OperationCallOnSelfExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 2;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 3;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 4;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 5;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 6;
			matches = tempMatchCount;
		}
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"operationName"		));
		if (tempMatchCount > matches) {
			alt = 7;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NEQUALITY_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 2:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NOT_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 3:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("AND_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 4:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("OR_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 5:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("XOR_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 6:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("IMPLIES_OPERATOR");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			case 7:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("operationName");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
						out.print(" ");
					}
					printCountingMap.put("operationName", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
			count = printCountingMap.get("operationName");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME));
				if (o != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("EQUALITY_OPERATOR");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getOperationCallBaseExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__OPERATION_NAME), element));
					out.print(" ");
				}
				printCountingMap.put("operationName", count - 1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_1(org.dresdenocl.language.ocl.OperationCallOnSelfExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("isMarkedPre");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("IS_MARKED_PRE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), element));
			}
			printCountingMap.put("isMarkedPre", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
	}
	
	public void print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_2(org.dresdenocl.language.ocl.OperationCallOnSelfExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_2_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_OperationCallOnSelfExpCS_2_0(org.dresdenocl.language.ocl.OperationCallOnSelfExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.OPERATION_CALL_ON_SELF_EXP_CS__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_StaticOperationCallExpCS(org.dresdenocl.language.ocl.StaticOperationCallExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME));
		printCountingMap.put("operationName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS));
		printCountingMap.put("arguments", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("operationName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getStaticOperationCallExpCSOperationNameReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Operation) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME), element));
			}
			printCountingMap.put("operationName", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_StaticOperationCallExpCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_StaticOperationCallExpCS_0(org.dresdenocl.language.ocl.StaticOperationCallExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_StaticOperationCallExpCS_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_StaticOperationCallExpCS_0_0(org.dresdenocl.language.ocl.StaticOperationCallExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("arguments");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("arguments", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_EnumLiteralOrStaticPropertyExpCS(org.dresdenocl.language.ocl.EnumLiteralOrStaticPropertyExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME));
		printCountingMap.put("typeName", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY));
		printCountingMap.put("enumLiteralOrStaticProperty", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("typeName");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__TYPE_NAME));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("typeName", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("::");
		out.print(" ");
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("enumLiteralOrStaticProperty");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getEnumLiteralOrStaticPropertyExpCSEnumLiteralOrStaticPropertyReferenceResolver().deResolve((org.dresdenocl.pivotmodel.NamedElement) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.ENUM_LITERAL_OR_STATIC_PROPERTY_EXP_CS__ENUM_LITERAL_OR_STATIC_PROPERTY), element));
				out.print(" ");
			}
			printCountingMap.put("enumLiteralOrStaticProperty", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_TupleLiteralExpCS(org.dresdenocl.language.ocl.TupleLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS));
		printCountingMap.put("variableDeclarations", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("Tuple");
		out.print(" ");
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarations", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	
	public void print_org_dresdenocl_language_ocl_IfExpCS(org.dresdenocl.language.ocl.IfExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__CONDITION));
		printCountingMap.put("condition", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__THEN_BRANCH));
		printCountingMap.put("thenBranch", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__ELSE_BRANCH));
		printCountingMap.put("elseBranch", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("if");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("condition");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__CONDITION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("condition", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("then");
		out.print(" ");
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("thenBranch");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__THEN_BRANCH));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("thenBranch", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("else");
		out.print(" ");
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("elseBranch");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.IF_EXP_CS__ELSE_BRANCH));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("elseBranch", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (CsString)
		out.print("endif");
		out.print(" ");
	}
	
	
	public void print_org_dresdenocl_language_ocl_CollectionLiteralExpCS(org.dresdenocl.language.ocl.CollectionLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE));
		printCountingMap.put("collectionType", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS));
		printCountingMap.put("collectionLiteralParts", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		// print collected hidden tokens
		int count;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("collectionType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("collectionType", count - 1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("{");
		out.print(" ");
		// DEFINITION PART BEGINS (CompoundDefinition)
		sWriter = new java.io.StringWriter();
		out1 = new java.io.PrintWriter(sWriter);
		printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
		print_org_dresdenocl_language_ocl_CollectionLiteralExpCS_0(element, localtab, out1, printCountingMap1);
		if (printCountingMap.equals(printCountingMap1)) {
			out1.close();
		} else {
			out1.flush();
			out1.close();
			out.print(sWriter.toString());
			printCountingMap.putAll(printCountingMap1);
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("}");
		out.print(" ");
	}
	
	public void print_org_dresdenocl_language_ocl_CollectionLiteralExpCS_0(org.dresdenocl.language.ocl.CollectionLiteralExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("collectionLiteralParts");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("collectionLiteralParts", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_CollectionLiteralExpCS_0_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
	}
	
	public void print_org_dresdenocl_language_ocl_CollectionLiteralExpCS_0_0(org.dresdenocl.language.ocl.CollectionLiteralExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("collectionLiteralParts");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_EXP_CS__COLLECTION_LITERAL_PARTS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("collectionLiteralParts", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_CollectionRangeCS(org.dresdenocl.language.ocl.CollectionRangeCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__FROM));
		printCountingMap.put("from", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__TO));
		printCountingMap.put("to", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("from");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__FROM));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("from", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print("..");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("to");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_RANGE_CS__TO));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("to", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_CollectionLiteralPartsOclExpCS(org.dresdenocl.language.ocl.CollectionLiteralPartsOclExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_PARTS_OCL_EXP_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_LITERAL_PARTS_OCL_EXP_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_CollectionTypeLiteralExpCS(org.dresdenocl.language.ocl.CollectionTypeLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE));
		printCountingMap.put("collectionType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("collectionType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.COLLECTION_TYPE_LITERAL_EXP_CS__COLLECTION_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("collectionType", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_TupleTypeLiteralExpCS(org.dresdenocl.language.ocl.TupleTypeLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE));
		printCountingMap.put("tupleType", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("tupleType");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.TUPLE_TYPE_LITERAL_EXP_CS__TUPLE_TYPE));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("tupleType", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_PropertyCallOnSelfExpCS(org.dresdenocl.language.ocl.PropertyCallOnSelfExpCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY));
		printCountingMap.put("property", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE));
		printCountingMap.put("isMarkedPre", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("property");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getPropertyCallBaseExpCSPropertyReferenceResolver().deResolve((org.dresdenocl.pivotmodel.Property) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__PROPERTY), element));
			}
			printCountingMap.put("property", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("isMarkedPre");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("IS_MARKED_PRE");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.PROPERTY_CALL_ON_SELF_EXP_CS__IS_MARKED_PRE), element));
				out.print(" ");
			}
			printCountingMap.put("isMarkedPre", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_LetExpCS(org.dresdenocl.language.ocl.LetExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(2);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS));
		printCountingMap.put("variableDeclarations", temp == null ? 0 : ((java.util.Collection<?>) temp).size());
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		boolean iterate = true;
		java.io.StringWriter sWriter = null;
		java.io.PrintWriter out1 = null;
		java.util.Map<String, Integer> printCountingMap1 = null;
		// DEFINITION PART BEGINS (CsString)
		out.print("let");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarations", count - 1);
		}
		// DEFINITION PART BEGINS (CompoundDefinition)
		iterate = true;
		while (iterate) {
			sWriter = new java.io.StringWriter();
			out1 = new java.io.PrintWriter(sWriter);
			printCountingMap1 = new java.util.LinkedHashMap<String, Integer>(printCountingMap);
			print_org_dresdenocl_language_ocl_LetExpCS_0(element, localtab, out1, printCountingMap1);
			if (printCountingMap.equals(printCountingMap1)) {
				iterate = false;
				out1.close();
			} else {
				out1.flush();
				out1.close();
				out.print(sWriter.toString());
				printCountingMap.putAll(printCountingMap1);
			}
		}
		// DEFINITION PART BEGINS (CsString)
		out.print("in");
		out.print(" ");
		// DEFINITION PART BEGINS (LineBreak)
		localtab += "	";
		out.println();
		out.print(localtab);
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
		// DEFINITION PART BEGINS (LineBreak)
		out.println();
		out.print(localtab);
	}
	
	public void print_org_dresdenocl_language_ocl_LetExpCS_0(org.dresdenocl.language.ocl.LetExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		String localtab = outertab;
		int count;
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(",");
		out.print(" ");
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("variableDeclarations");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS));
			java.util.List<?> list = (java.util.List<?>) o;
			int index = list.size() - count;
			if (index >= 0) {
				o = list.get(index);
			} else {
				o = null;
			}
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("variableDeclarations", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_RealLiteralExpCS(org.dresdenocl.language.ocl.RealLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(3);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE));
		printCountingMap.put("intValue", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE));
		printCountingMap.put("realValue", temp == null ? 0 : 1);
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR));
		printCountingMap.put("navigationOperator", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("intValue");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("INTEGER_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__INT_VALUE), element));
			}
			printCountingMap.put("intValue", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("navigationOperator");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("NAVIGATION_OPERATOR");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__NAVIGATION_OPERATOR), element));
			}
			printCountingMap.put("navigationOperator", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CompoundDefinition)
		print_org_dresdenocl_language_ocl_RealLiteralExpCS_0(element, localtab, out, printCountingMap);
	}
	
	public void print_org_dresdenocl_language_ocl_RealLiteralExpCS_0(org.dresdenocl.language.ocl.RealLiteralExpCS element, String outertab, java.io.PrintWriter out, java.util.Map<String, Integer> printCountingMap) {
		int count;
		int alt = -1;
		alt = 0;
		int matches = 		matchCount(printCountingMap, java.util.Arrays.asList(		"realValue"		));
		int tempMatchCount;
		tempMatchCount = 		matchCount(printCountingMap, java.util.Arrays.asList(		"realValue"		));
		if (tempMatchCount > matches) {
			alt = 1;
			matches = tempMatchCount;
		}
		switch(alt) {
			case 1:			{
				// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
				count = printCountingMap.get("realValue");
				if (count > 0) {
					Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE));
					if (o != null) {
						org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("INTEGER_LITERAL");
						resolver.setOptions(getOptions());
						out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), element));
						out.print(" ");
					}
					printCountingMap.put("realValue", count - 1);
				}
			}
			break;
			default:			// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
			count = printCountingMap.get("realValue");
			if (count > 0) {
				Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE));
				if (o != null) {
					org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("INTEGER_0");
					resolver.setOptions(getOptions());
					out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.REAL_LITERAL_EXP_CS__REAL_VALUE), element));
					out.print(" ");
				}
				printCountingMap.put("realValue", count - 1);
			}
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_IntegerLiteralExpCS(org.dresdenocl.language.ocl.IntegerLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL));
		printCountingMap.put("integerLiteral", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("integerLiteral");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("INTEGER_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.INTEGER_LITERAL_EXP_CS__INTEGER_LITERAL), element));
				out.print(" ");
			}
			printCountingMap.put("integerLiteral", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_BooleanLiteralExpCS(org.dresdenocl.language.ocl.BooleanLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL));
		printCountingMap.put("booleanLiteral", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("booleanLiteral");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("BOOLEAN_LITERAL");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BOOLEAN_LITERAL_EXP_CS__BOOLEAN_LITERAL), element));
				out.print(" ");
			}
			printCountingMap.put("booleanLiteral", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_StringLiteralExpCS(org.dresdenocl.language.ocl.StringLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL));
		printCountingMap.put("stringLiteral", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderInQuotes)
		count = printCountingMap.get("stringLiteral");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("QUOTED_39_39");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve((Object) o, element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.STRING_LITERAL_EXP_CS__STRING_LITERAL), element));
				out.print(" ");
			}
			printCountingMap.put("stringLiteral", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_InvalidLiteralExpCS(org.dresdenocl.language.ocl.InvalidLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("invalid");
		out.print(" ");
	}
	
	
	public void print_org_dresdenocl_language_ocl_NullLiteralExpCS(org.dresdenocl.language.ocl.NullLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		// print collected hidden tokens
		// DEFINITION PART BEGINS (CsString)
		out.print("null");
		out.print(" ");
	}
	
	
	public void print_org_dresdenocl_language_ocl_NamedLiteralExpCS(org.dresdenocl.language.ocl.NamedLiteralExpCS element, String outertab, java.io.PrintWriter out) {
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_LITERAL_EXP_CS__NAMED_ELEMENT));
		printCountingMap.put("namedElement", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (PlaceholderUsingSpecifiedToken)
		count = printCountingMap.get("namedElement");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_LITERAL_EXP_CS__NAMED_ELEMENT));
			if (o != null) {
				org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolver resolver = tokenResolverFactory.createTokenResolver("SIMPLE_NAME");
				resolver.setOptions(getOptions());
				out.print(resolver.deResolve(getReferenceResolverSwitch() == null ? null : getReferenceResolverSwitch().getNamedLiteralExpCSNamedElementReferenceResolver().deResolve((org.dresdenocl.pivotmodel.NamedElement) o, element, (org.eclipse.emf.ecore.EReference) element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_LITERAL_EXP_CS__NAMED_ELEMENT)), element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.NAMED_LITERAL_EXP_CS__NAMED_ELEMENT), element));
				out.print(" ");
			}
			printCountingMap.put("namedElement", count - 1);
		}
	}
	
	
	public void print_org_dresdenocl_language_ocl_BracketExpCS(org.dresdenocl.language.ocl.BracketExpCS element, String outertab, java.io.PrintWriter out) {
		String localtab = outertab;
		// The printCountingMap contains a mapping from feature names to the number of
		// remaining elements that still need to be printed. The map is initialized with
		// the number of elements stored in each structural feature. For lists this is the
		// list size. For non-multiple features it is either 1 (if the feature is set) or
		// 0 (if the feature is null).
		java.util.Map<String, Integer> printCountingMap = new java.util.LinkedHashMap<String, Integer>(1);
		Object temp;
		temp = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BRACKET_EXP_CS__OCL_EXPRESSION));
		printCountingMap.put("oclExpression", temp == null ? 0 : 1);
		// print collected hidden tokens
		int count;
		// DEFINITION PART BEGINS (CsString)
		out.print("(");
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (Containment)
		count = printCountingMap.get("oclExpression");
		if (count > 0) {
			Object o = element.eGet(element.eClass().getEStructuralFeature(org.dresdenocl.language.ocl.OclPackage.BRACKET_EXP_CS__OCL_EXPRESSION));
			if (o != null) {
				doPrint((org.eclipse.emf.ecore.EObject) o, out, localtab);
			}
			printCountingMap.put("oclExpression", count - 1);
		}
		// DEFINITION PART BEGINS (WhiteSpaces)
		// DEFINITION PART BEGINS (CsString)
		out.print(")");
		out.print(" ");
	}
	
	
}
