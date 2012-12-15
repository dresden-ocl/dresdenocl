/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclMetaInformation implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclMetaInformation {
	
	public String getSyntaxName() {
		return "ocl";
	}
	
	public String getURI() {
		return "http://www.tu-dresden.de/ocl20/pivot/language/ocl";
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextScanner createLexer() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclAntlrScanner(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLexer());
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextParser createParser(java.io.InputStream inputStream, String encoding) {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclParser().createInstance(inputStream, encoding);
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextPrinter createPrinter(java.io.OutputStream outputStream, tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextResource resource) {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclPrinter2(outputStream, resource);
	}
	
	public org.eclipse.emf.ecore.EClass[] getClassesWithSyntax() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxCoverageInformationProvider().getClassesWithSyntax();
	}
	
	public org.eclipse.emf.ecore.EClass[] getStartSymbols() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclSyntaxCoverageInformationProvider().getStartSymbols();
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclReferenceResolverSwitch getReferenceResolverSwitch() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch();
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenResolverFactory getTokenResolverFactory() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclTokenResolverFactory();
	}
	
	public String getPathToCSDefinition() {
		return "tudresden.ocl20.pivot.language.ocl/metamodel/OCL.cs";
	}
	
	public String[] getTokenNames() {
		return tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclParser.tokenNames;
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenStyle getDefaultTokenStyle(String tokenName) {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclTokenStyleInformationProvider().getDefaultTokenStyle(tokenName);
	}
	
	public java.util.Collection<tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclBracketPair> getBracketPairs() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclBracketInformationProvider().getBracketPairs();
	}
	
	public org.eclipse.emf.ecore.EClass[] getFoldableClasses() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclFoldingInformationProvider().getFoldableClasses();
	}
	
	public org.eclipse.emf.ecore.resource.Resource.Factory createResourceFactory() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResourceFactory();
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclNewFileContentProvider getNewFileContentProvider() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclNewFileContentProvider();
	}
	
	public void registerResourceFactory() {
		org.eclipse.emf.ecore.resource.Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(getSyntaxName(), new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResourceFactory());
	}
	
	/**
	 * Returns the key of the option that can be used to register a preprocessor that
	 * is used as a pipe when loading resources. This key is language-specific. To
	 * register one preprocessor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getInputStreamPreprocessorProviderOptionKey() {
		return getSyntaxName() + "_" + "INPUT_STREAM_PREPROCESSOR_PROVIDER";
	}
	
	/**
	 * Returns the key of the option that can be used to register a post-processors
	 * that are invoked after loading resources. This key is language-specific. To
	 * register one post-processor for multiple resource types, it must be registered
	 * individually using all keys.
	 */
	public String getResourcePostProcessorProviderOptionKey() {
		return getSyntaxName() + "_" + "RESOURCE_POSTPROCESSOR_PROVIDER";
	}
	
	public String getLaunchConfigurationType() {
		return "tudresden.ocl20.pivot.language.ocl.resource.ocl.ui.launchConfigurationType";
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclNameProvider createNameProvider() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.analysis.OclDefaultNameProvider();
	}
	
	public String[] getSyntaxHighlightableTokenNames() {
		tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclAntlrTokenHelper tokenHelper = new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclAntlrTokenHelper();
		java.util.List<String> highlightableTokens = new java.util.ArrayList<String>();
		String[] parserTokenNames = getTokenNames();
		for (int i = 0; i < parserTokenNames.length; i++) {
			// If ANTLR is used we need to normalize the token names
			if (!tokenHelper.canBeUsedForSyntaxHighlighting(i)) {
				continue;
			}
			String tokenName = tokenHelper.getTokenName(parserTokenNames, i);
			if (tokenName == null) {
				continue;
			}
			highlightableTokens.add(tokenName);
		}
		highlightableTokens.add(tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclTokenStyleInformationProvider.TASK_ITEM_TOKEN_NAME);
		return highlightableTokens.toArray(new String[highlightableTokens.size()]);
	}
	
}
