package org.dresdenocl.tools.transformation.pivot2sql.util;

import java.util.Comparator;

import orgomg.cwm.objectmodel.core.ModelElement;

public class ModelElementComparator implements Comparator<ModelElement> {

	public int compare(ModelElement o1, ModelElement o2) {

		String s1 = o1.getName() + "@" + Integer.toHexString(o1.hashCode());
		String s2 = o2.getName() + "@" + Integer.toHexString(o2.hashCode());
		int compareValue = s1.compareTo(s2);
		if (compareValue != 0) compareValue = 1;
		return compareValue;
	}

}
