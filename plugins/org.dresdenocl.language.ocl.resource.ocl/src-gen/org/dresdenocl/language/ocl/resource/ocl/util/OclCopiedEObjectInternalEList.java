/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.BasicInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;

public class OclCopiedEObjectInternalEList extends org.dresdenocl.language.ocl.resource.ocl.util.OclCopiedEList<EObject> implements InternalEList<EObject> {
	
	private InternalEList<EObject> original;
	private InternalEList<EObject> copy;
	
	public OclCopiedEObjectInternalEList(InternalEList<EObject> original) {
		super(original);
		this.original = original;
		this.copy = new BasicInternalEList<EObject>(EObject.class);
		this.copy.addAll(this.original);
	}
	
	public boolean basicContains(Object object) {
		return copy.basicContains(object);
	}
	
	public boolean basicContainsAll(Collection<?> collection) {
		return copy.basicContainsAll(collection);
	}
	
	public EObject basicGet(int index) {
		return copy.basicGet(index);
	}
	
	public int basicIndexOf(Object object) {
		return copy.basicIndexOf(object);
	}
	
	public Iterator<EObject> basicIterator() {
		return copy.basicIterator();
	}
	
	public int basicLastIndexOf(Object object) {
		return copy.basicLastIndexOf(object);
	}
	
	public List<EObject> basicList() {
		return copy.basicList();
	}
	
	public ListIterator<EObject> basicListIterator() {
		return copy.basicListIterator();
	}
	
	public ListIterator<EObject> basicListIterator(int index) {
		return copy.basicListIterator(index);
	}
	
	public Object[] basicToArray() {
		return copy.basicToArray();
	}
	
	public <T> T[] basicToArray(T[] array) {
		return copy.basicToArray(array);
	}
	
	public boolean equals(Object o) {
		return copy.equals(o);
	}
	
	public int hashCode() {
		return copy.hashCode();
	}
	
	public boolean addAllUnique(Collection<? extends EObject> collection) {
		copy.addAllUnique(collection);
		return original.addAllUnique(collection);
	}
	
	public boolean addAllUnique(int index, Collection<? extends EObject> collection) {
		copy.addAllUnique(index, collection);
		return original.addAllUnique(index, collection);
	}
	
	public void addUnique(EObject object) {
		copy.addUnique(object);
		original.addUnique(object);
	}
	
	public void addUnique(int index, EObject object) {
		copy.addUnique(index, object);
		original.addUnique(index, object);
	}
	
	public NotificationChain basicAdd(EObject object, NotificationChain notifications) {
		copy.basicAdd(object, notifications);
		return original.basicAdd(object, notifications);
	}
	
	public NotificationChain basicRemove(Object object,
	NotificationChain notifications) {
		copy.basicRemove(object, notifications);
		return original.basicRemove(object, notifications);
	}
	
	public EObject setUnique(int index, EObject object) {
		copy.setUnique(index, object);
		return original.setUnique(index, object);
	}
}
