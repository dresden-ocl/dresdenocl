/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp;

/**
 * An Excpetion to represent invalid content types for parser instances.
 * 
 * @see
 * tudresden.ocl20.pivot.language.ocl.resource.ocl.IOclOptions.RESOURCE_CONTENT_TYP
 * E
 */
public class OclUnexpectedContentTypeException extends org.antlr.runtime3_3_0.RecognitionException {
	
	private static final long serialVersionUID = 4791359811519433999L;
	
	private Object contentType = null;
	
	public  OclUnexpectedContentTypeException(Object contentType) {
		this.contentType = contentType;
	}
	
	public Object getContentType() {
		return contentType;
	}
	
}
