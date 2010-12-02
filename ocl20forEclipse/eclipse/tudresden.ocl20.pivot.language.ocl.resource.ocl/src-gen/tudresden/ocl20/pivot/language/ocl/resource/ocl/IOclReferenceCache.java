/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package tudresden.ocl20.pivot.language.ocl.resource.ocl;

public interface IOclReferenceCache {
	public Object get(String identifier);
	public void put(String identifier, Object target);
}
