/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.mopp;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class provides information about how brackets must be handled in the
 * editor (e.g., whether they must be closed automatically).
 */
public class OclBracketInformationProvider {
	
	public Collection<org.dresdenocl.language.ocl.resource.ocl.IOclBracketPair> getBracketPairs() {
		Collection<org.dresdenocl.language.ocl.resource.ocl.IOclBracketPair> result = new ArrayList<org.dresdenocl.language.ocl.resource.ocl.IOclBracketPair>();
		result.add(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclBracketPair("(", ")", true, false));
		result.add(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclBracketPair("{", "}", true, true));
		result.add(new org.dresdenocl.language.ocl.resource.ocl.mopp.OclBracketPair("'", "'", false, false));
		return result;
	}
	
}
