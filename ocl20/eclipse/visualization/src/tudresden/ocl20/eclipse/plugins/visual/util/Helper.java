package tudresden.ocl20.eclipse.plugins.visual.util;

import java.util.HashMap;

/**
 * Helper Class for Counting
 * @author Kai-Uwe Gärtner
 *
 */
public class Helper {
	private static HashMap<Integer,Integer> countMap=new HashMap<Integer,Integer>();
	public static int getNewCount(int level){
		if (!countMap.containsKey(new Integer(level))) 
			countMap.put(new Integer(level),new Integer(0));
		else countMap.put(new Integer(level),new Integer(((Integer)countMap.get(new Integer(level))).intValue()+1));
		return countMap.get(new Integer(level));
	}
}
