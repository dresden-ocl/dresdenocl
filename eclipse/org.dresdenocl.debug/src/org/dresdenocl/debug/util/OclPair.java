package org.dresdenocl.debug.util;

public class OclPair<A, B> {

	private final A m_left;
	private final B m_right;

	public OclPair(A left, B right) {

		if (left == null || right == null) {
			throw new IllegalArgumentException("Cannot create null values in a pair");
		}

		m_left = left;
		m_right = right;
	}

	public A getLeft() {

		return m_left;
	}

	public B getRight() {

		return m_right;
	}

	public boolean equals(Object that) {

		if (that instanceof OclPair<?, ?>) {
			OclPair<?, ?> pair = (OclPair<?, ?>) that;
			return m_left.equals(pair.getLeft()) && m_right.equals(pair.getRight());
		}
		return false;
	}

	public int hashCode() {

		return 31 + m_left.hashCode() + m_right.hashCode();
	}

	public String toString() {

		return "Left: " + m_left + " , Right: " + m_right;
	}
}
