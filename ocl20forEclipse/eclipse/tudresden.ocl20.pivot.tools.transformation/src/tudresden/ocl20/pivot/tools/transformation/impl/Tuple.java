/*
 * Created on 09.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.impl;

/**
 * This class implements a generic tuple of two java objects.
 * It is used to construct the output tuple of a parallel transformation
 * composition.
 * @author Christian Wende
 *
 * @param <ELEM1>
 * @param <ELEM2>
 */
public class Tuple<ELEM1, ELEM2> {

	private ELEM1 elem1;
	private ELEM2 elem2;

	public Tuple(ELEM1 elem1, ELEM2 elem2) {
		this.elem1 = elem1;
		this.elem2 = elem2;
	}
	
	public ELEM1 getElem1() {
		return  elem1;
	}
	
	public ELEM2 getElem2() {
		return elem2;
	}
}
