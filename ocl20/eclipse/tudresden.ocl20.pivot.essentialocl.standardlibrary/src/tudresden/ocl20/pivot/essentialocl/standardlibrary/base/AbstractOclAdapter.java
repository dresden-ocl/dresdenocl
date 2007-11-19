package tudresden.ocl20.pivot.essentialocl.standardlibrary.base;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAdapter;

public class AbstractOclAdapter implements OclAdapter {

	protected Object adaptee;
	
	protected Class clazz = null;
	
	public AbstractOclAdapter(Object adaptee) {
		this.adaptee = adaptee;
	}

	public Object getAdaptee() {
		return adaptee;
	}
	
	public Class getAdapteeClass() {
		if (clazz == null)
			return getAdaptee() == null ? null : getAdaptee().getClass();
		return clazz;
	}
	
	public void setAdapteeClass(Class clazz) {
		if (clazz.isInstance(adaptee))
			this.clazz = clazz;
	}
}
