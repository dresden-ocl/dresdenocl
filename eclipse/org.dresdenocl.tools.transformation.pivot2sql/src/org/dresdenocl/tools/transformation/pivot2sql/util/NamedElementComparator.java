package tudresden.ocl20.pivot.tools.transformation.pivot2sql.util;

import java.util.Comparator;

import tudresden.ocl20.pivot.pivotmodel.NamedElement;

public class NamedElementComparator implements Comparator<NamedElement> {

	public int compare(NamedElement o1, NamedElement o2) {

		String s1 = o1.getName() + "@" + Integer.toHexString(o1.hashCode());
		String s2 = o2.getName() + "@" + Integer.toHexString(o2.hashCode());
		return s1.compareTo(s2);
	}

}
