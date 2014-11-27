/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <p>
 * A basic implementation of the ILocationMap interface. Instances store
 * information about element locations using four maps.
 * </p>
 * <p>
 * <p>
 * </p>
 * <p>
 * The set-methods can be called multiple times by the parser that may visit
 * multiple children from which it copies the localization information for the
 * parent element (i.e., the element for which set-method is called). It
 * implements the following behavior:
 * </p>
 * <p>
 * <p>
 * </p>
 * <p>
 * Line:   The lowest of all sources is used for target<br>
 * </p>
 * <p>
 * Column: The lowest of all sources is used for target<br>
 * </p>
 * <p>
 * Start:  The lowest of all sources is used for target<br>
 * </p>
 * <p>
 * End:    The highest of all sources is used for target<br>
 * </p>
 */
public class OclLocationMap implements org.dresdenocl.language.ocl.resource.ocl.IOclLocationMap {
	
	/**
	 * A basic interface that can be implemented to select EObjects based of their
	 * location in a text resource.
	 */
	public interface ISelector {
		boolean accept(int startOffset, int endOffset);
	}
	
	protected Map<EObject, Integer> columnMap = new IdentityHashMap<EObject, Integer>();
	protected Map<EObject, Integer> lineMap = new IdentityHashMap<EObject, Integer>();
	protected Map<EObject, Integer> charStartMap = new IdentityHashMap<EObject, Integer>();
	protected Map<EObject, Integer> charEndMap = new IdentityHashMap<EObject, Integer>();
	
	public void setLine(EObject element, int line) {
		setMapValueToMin(lineMap, element, line);
	}
	
	public int getLine(EObject element) {
		return getMapValue(lineMap, element);
	}
	
	public void setColumn(EObject element, int column) {
		setMapValueToMin(columnMap, element, column);
	}
	
	public int getColumn(EObject element) {
		return getMapValue(columnMap, element);
	}
	
	public void setCharStart(EObject element, int charStart) {
		setMapValueToMin(charStartMap, element, charStart);
	}
	
	public int getCharStart(EObject element) {
		return getMapValue(charStartMap, element);
	}
	
	public void setCharEnd(EObject element, int charEnd) {
		setMapValueToMax(charEndMap, element, charEnd);
	}
	
	public int getCharEnd(EObject element) {
		return getMapValue(charEndMap, element);
	}
	
	private int getMapValue(Map<EObject, Integer> map, EObject element) {
		if (!map.containsKey(element)) return -1;
		Integer value = map.get(element);
		return value == null ? -1 : value.intValue();
	}
	
	private void setMapValueToMin(Map<EObject, Integer> map, EObject element, int value) {
		// We need to synchronize the write access, because other threads may iterate over
		// the map concurrently.
		synchronized (this) {
			if (element == null || value < 0) return;
			if (map.containsKey(element) && map.get(element) < value) return;
			map.put(element, value);
		}
	}
	
	private void setMapValueToMax(Map<EObject, Integer> map, EObject element, int value) {
		// We need to synchronize the write access, because other threads may iterate over
		// the map concurrently.
		synchronized (this) {
			if (element == null || value < 0) return;
			if (map.containsKey(element) && map.get(element) > value) return;
			map.put(element, value);
		}
	}
	
	public List<EObject> getElementsAt(final int documentOffset) {
		List<EObject> result = getElements(new ISelector() {
			public boolean accept(int start, int end) {
				return start <= documentOffset && end >= documentOffset;
			}
		});
		// sort elements according to containment hierarchy
		Collections.sort(result, new Comparator<EObject>() {
			public int compare(EObject objectA, EObject objectB) {
				if (EcoreUtil.isAncestor(objectA, objectB)) {
					return 1;
				} else {
					if (EcoreUtil.isAncestor(objectB, objectA)) {
						return -1;
					} else {
						return 0;
					}
				}
			}
		});
		return result;
	}
	
	public List<EObject> getElementsBetween(final int startOffset, final int endOffset) {
		List<EObject> result = getElements(new ISelector() {
			public boolean accept(int start, int end) {
				return start >= startOffset && end <= endOffset;
			}
		});
		return result;
	}
	
	private List<EObject> getElements(ISelector s) {
		// There might be more than one element at the given offset. Thus, we collect all
		// of them and sort them afterwards.
		List<EObject> result = new ArrayList<EObject>();
		
		// We need to synchronize the write access, because other threads may iterate over
		// the map concurrently.
		synchronized (this) {
			for (EObject next : charStartMap.keySet()) {
				Integer start = charStartMap.get(next);
				Integer end = charEndMap.get(next);
				if (start == null || end == null) {
					continue;
				}
				if (s.accept(start, end)) {
					result.add(next);
				}
			}
		}
		Collections.sort(result, new Comparator<EObject>() {
			public int compare(EObject objectA, EObject objectB) {
				int lengthA = getCharEnd(objectA) - getCharStart(objectA);
				int lengthB = getCharEnd(objectB) - getCharStart(objectB);
				return lengthA - lengthB;
			}
		});
		return result;
	}
}
