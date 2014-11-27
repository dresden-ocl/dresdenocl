package org.dresdenocl.standardlibrary.java.internal.library;

import org.dresdenocl.essentialocl.standardlibrary.OclLibraryObject;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;

/**
 * This class implements an {@link OclLibraryObject} in Java.
 * 
 * @author Michael Thiele
 * 
 */
// FIXME Michael: still needed as invokeOperation has been moved to OclAny?
public abstract class JavaOclLibraryObject extends JavaOclAny implements
		OclLibraryObject {

	public JavaOclLibraryObject(IModelInstanceElement imiElement) {

		super(imiElement);
	}

	public JavaOclLibraryObject(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclLibraryObject(Throwable invalidReason) {

		super(invalidReason);
	}

}
