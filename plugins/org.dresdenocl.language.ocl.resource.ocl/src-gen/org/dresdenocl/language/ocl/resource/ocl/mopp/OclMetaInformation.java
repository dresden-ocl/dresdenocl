/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource.Factory;

public class OclMetaInformation implements org.dresdenocl.language.ocl.resource.ocl.IOclMetaInformation {
	
	public String getSyntaxName() {
		return "ocl";
	}
	
	public String getURI() {
		return "http://www.tu-dresden.de/ocl20/pivot/language/ocl";
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextScanner createLexer() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclAntlrScanner(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclLexer());
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextParser createParser(InputStream inputStream, String encoding) {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclParser().createInstance(inputStream, encoding);
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTextPrinter createPrinter(OutputStream outputStream, org.dresdenocl.language.ocl.resource.ocl.IOclTextResource resource) {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclPrinter2(outputStream, resource);
	}
	
	public EClass[] getClassesWithSyntax() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxCoverageInformationProvider().getClassesWithSyntax();
	}
	
	public EClass[] getStartSymbols() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclSyntaxCoverageInformationProvider().getStartSymbols();
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclReferenceResolverSwitch getReferenceResolverSwitch() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclReferenceResolverSwitch();
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTokenResolverFactory getTokenResolverFactory() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenResolverFactory();
	}
	
	public String getPathToCSDefinition() {
		return "org.dresdenocl.language.ocl/metamodel/OCL.cs";
	}
	
	public String[] getTokenNames() {
		return org.dresdenocl.language.ocl.resource.ocl.mopp.OclParser.tokenNames;
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclTokenStyle getDefaultTokenStyle(String tokenName) {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyleInformationProvider().getDefaultTokenStyle(tokenName);
	}
	
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclBracketPair> getBracketPairs() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclBracketInformationProvider().getBracketPairs();
	}
	
	public EClass[] getFoldableClasses() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclFoldingInformationProvider().getFoldableClasses();
	}
	
	public Factory createResourceFactory() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclResourceFactory();
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.mopp.OclNewFileContentProvider getNewFileContentProvider() {
		return new org.dresdenocl.language.ocl.resource.ocl.mopp.OclNewFileContentProvider();
	}
	
	public void registerResourceFactory() {
		// if no resource factory registered, register delegator
		if (Factory.Registry.INSTANCE.getExtensionToFactoryMap().get(getSyntaxName()) == null) {
			Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(getSyntaxName(), new org.dresdenocl.language.ocl.resource.ocl.mopp.OclResourceFactoryDelegator());
		}
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
		return "org.dresdenocl.language.ocl.resource.ocl.ui.launchConfigurationType";
	}
	
	public org.dresdenocl.language.ocl.resource.ocl.IOclNameProvider createNameProvider() {
		return new org.dresdenocl.language.ocl.resource.ocl.analysis.OclDefaultNameProvider();
	}
	
	public String[] getSyntaxHighlightableTokenNames() {
		org.dresdenocl.language.ocl.resource.ocl.mopp.OclAntlrTokenHelper tokenHelper = new org.dresdenocl.language.ocl.resource.ocl.mopp.OclAntlrTokenHelper();
		List<String> highlightableTokens = new ArrayList<String>();
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
		highlightableTokens.add(org.dresdenocl.language.ocl.resource.ocl.mopp.OclTokenStyleInformationProvider.TASK_ITEM_TOKEN_NAME);
		return highlightableTokens.toArray(new String[highlightableTokens.size()]);
	}
	
}
