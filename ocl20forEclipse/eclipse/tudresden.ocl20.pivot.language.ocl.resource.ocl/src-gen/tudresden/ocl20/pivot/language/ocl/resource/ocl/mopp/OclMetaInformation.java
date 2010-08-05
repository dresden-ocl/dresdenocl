/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

public class OclMetaInformation implements tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclMetaInformation {
	
	public java.lang.String getSyntaxName() {
		return "ocl";
	}
	
	public java.lang.String getURI() {
		return "http://www.tu-dresden.de/ocl20/pivot/language/ocl";
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextScanner createLexer() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclAntlrScanner(new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclLexer());
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTextParser createParser(java.io.InputStream inputStream, java.lang.String encoding) {
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
	
	public java.lang.String getPathToCSDefinition() {
		return "tudresden.ocl20.pivot.language.ocl/metamodel/OCL.cs";
	}
	
	public java.lang.String[] getTokenNames() {
		return new tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclParser(null).getTokenNames();
	}
	
	public tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclTokenStyle getDefaultTokenStyle(java.lang.String tokenName) {
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
	
}
