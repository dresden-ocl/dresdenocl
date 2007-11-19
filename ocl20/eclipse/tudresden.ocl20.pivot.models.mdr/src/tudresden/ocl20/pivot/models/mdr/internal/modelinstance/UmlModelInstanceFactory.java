package tudresden.ocl20.pivot.models.mdr.internal.modelinstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceFactory;

public class UmlModelInstanceFactory extends AbstractModelInstanceFactory
		implements IModelInstanceFactory {

	public <T extends OclRoot> OclBag<T> createOclBag(T[] parts) {
		List<T> partList = Arrays.asList(parts);
		return (OclBag) Platform.getAdapterManager().getAdapter(partList, OclBag.class);
	}

	public <T extends OclRoot> OclOrderedSet<T> createOclOrderedSet(T[] parts) {
		List partList = new ArrayList();
		for (int i = 0; i<parts.length; i++)
		{
			if (!partList.contains(parts[i]))
				partList.add(parts[i]);
		}
		return (OclOrderedSet) Platform.getAdapterManager().getAdapter(partList, OclOrderedSet.class);
	}

	public <T extends OclRoot> OclSequence<T> createOclSequence(T[] parts) {
		List<T> partList = Arrays.asList(parts);
		return (OclSequence) Platform.getAdapterManager().getAdapter(partList, OclSequence.class);
	}

	public <T extends OclRoot> OclSet<T> createOclSet(T[] parts) {
		Set partList = new HashSet(Arrays.asList(parts));
		return (OclSet) Platform.getAdapterManager().getAdapter(partList, OclSet.class);
	}

	public OclTuple createOclTuple(String[] partNames, OclRoot[] partValues) {
		// TODO Auto-generated method stub
		return null;
	}

}
