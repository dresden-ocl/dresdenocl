package tudresden.ocl20.pivot.essentialocl.standardlibrary.base;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAdapter;

public class AbstractOclAdapter implements OclAdapter {

	protected Object adaptee;

	protected Class<?> clazz = null;

	public AbstractOclAdapter(Object adaptee) {

		this.adaptee = adaptee;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAdapter#getAdaptee()
	 */
	public Object getAdaptee() {

		return this.adaptee;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAdapter#getAdapteeClass
	 * ()
	 */
	public Class<?> getAdapteeClass() {

		/* Eventually initialize the clazz field. */
		if (this.clazz == null && !(this.getAdaptee() == null)) {
			this.clazz = this.getAdaptee().getClass();
		}
		// no else.

		return this.clazz;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAdapter#setAdapteeClass
	 * (java.lang.Class)
	 */
	public void setAdapteeClass(Class<?> clazz) {

		if (this.adaptee == null || clazz.isAssignableFrom(this.adaptee.getClass())) {
			this.clazz = clazz;
		}
		// no else.
	}
}