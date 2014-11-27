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
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

public class OclCopiedEList<E> implements EList<E> {
	
	private EList<E> original;
	private EList<E> copy;
	
	@SuppressWarnings("unchecked")
	public OclCopiedEList(EList<E> original) {
		super();
		this.original = original;
		this.copy = new BasicEList<E>();
		Object[] originalContent = this.original.toArray();
		for (Object next : originalContent) {
			this.copy.add((E) next);
		}
	}
	
	public void move(int newPosition, E object) {
		original.move(newPosition, object);
		copy.move(newPosition, object);
	}
	
	public E move(int newPosition, int oldPosition) {
		copy.move(newPosition, oldPosition);
		return original.move(newPosition, oldPosition);
	}
	
	public boolean add(E o) {
		copy.add(o);
		return original.add(o);
	}
	
	public void add(int index, E element) {
		copy.add(index, element);
		original.add(index, element);
	}
	
	public boolean addAll(Collection<? extends E> c) {
		copy.addAll(c);
		return original.addAll(c);
	}
	
	public boolean addAll(int index, Collection<? extends E> c) {
		copy.addAll(index, c);
		return original.addAll(index, c);
	}
	
	public void clear() {
		copy.clear();
		original.clear();
	}
	
	public boolean contains(Object o) {
		return copy.contains(o);
	}
	
	public boolean containsAll(Collection<?> c) {
		return copy.containsAll(c);
	}
	
	public E get(int index) {
		return copy.get(index);
	}
	
	public int indexOf(Object o) {
		return copy.indexOf(o);
	}
	
	public boolean isEmpty() {
		return copy.isEmpty();
	}
	
	public Iterator<E> iterator() {
		return copy.iterator();
	}
	
	public int lastIndexOf(Object o) {
		return copy.lastIndexOf(o);
	}
	
	public ListIterator<E> listIterator() {
		return copy.listIterator();
	}
	
	public ListIterator<E> listIterator(int index) {
		return copy.listIterator(index);
	}
	
	public boolean remove(Object o) {
		copy.remove(o);
		return original.remove(o);
	}
	
	public E remove(int index) {
		copy.remove(index);
		return original.remove(index);
	}
	
	public boolean removeAll(Collection<?> c) {
		copy.removeAll(c);
		return original.removeAll(c);
	}
	
	public boolean retainAll(Collection<?> c) {
		copy.retainAll(c);
		return original.retainAll(c);
	}
	
	public E set(int index, E element) {
		copy.set(index, element);
		return original.set(index, element);
	}
	
	public int size() {
		return copy.size();
	}
	
	public List<E> subList(int fromIndex, int toIndex) {
		return copy.subList(fromIndex, toIndex);
	}
	
	public Object[] toArray() {
		return copy.toArray();
	}
	
	public <T> T[] toArray(T[] a) {
		return copy.toArray(a);
	}
	
	public String toString() {
		return copy.toString();
	}
	
}
