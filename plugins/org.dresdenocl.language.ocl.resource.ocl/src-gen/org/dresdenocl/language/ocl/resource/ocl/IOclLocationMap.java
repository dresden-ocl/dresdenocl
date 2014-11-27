/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl;

import java.util.List;
import org.eclipse.emf.ecore.EObject;

/**
 * A LocationMap maps EObjects to the position of their textual representations.
 * For each EObject the map contains information about the line, the column, the
 * character position where the object begins and the character position where the
 * object ends.
 */
public interface IOclLocationMap {
	
	/**
	 * Used by parsers to set location information.
	 */
	public void setLine(EObject element, int line);
	
	/**
	 * Returns the line where the given element starts.
	 */
	public int getLine(EObject element);
	
	/**
	 * Used by parsers to set location information.
	 */
	public void setColumn(EObject element, int column);
	
	/**
	 * Returns the column where the given element starts.
	 */
	public int getColumn(EObject element);
	
	/**
	 * Used by parsers to set location information.
	 */
	public void setCharStart(EObject element, int charStart);
	
	/**
	 * Returns the character position where the given element starts.
	 */
	public int getCharStart(EObject element);
	
	/**
	 * Used by parsers to set location information.
	 */
	public void setCharEnd(EObject element, int charEnd);
	
	/**
	 * Returns the character position where the given element ends.
	 */
	public int getCharEnd(EObject element);
	
	/**
	 * <p>
	 * Returns all EObjects that are located at the given offset in the text document.
	 * This method can return multiple elements, because containers include their
	 * children in the textual representation. The child elements are returned at the
	 * head of the result list.
	 * </p>
	 * 
	 * @param documentOffset
	 *  the offset where to search for elements
	 * 
	 * @return a list of elements located at the given offset
	 */
	public List<EObject> getElementsAt(int documentOffset);
	
	/**
	 * <p>
	 * Returns all EObjects that are located between the given offsets in the text
	 * document. The method can return multiple elements. This no guarantee about the
	 * order of the elements returned by this method. Even parsing the same document
	 * twice may yield a different order of elements.
	 * </p>
	 * 
	 * @param startOffset
	 * @param endOffset
	 * 
	 * @return a list of elements located between the given offsets
	 */
	public List<EObject> getElementsBetween(int startOffset, int endOffset);
	
}
