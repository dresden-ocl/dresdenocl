/*
Copyright (C) 2011 by Lars Schuetze (lschuetze@gmx.net)

This file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.tracer;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.WeakHashMap;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;
import tudresden.ocl20.pivot.tracer.tracermodel.impl.TracermodelPackageImpl;

/**
 * @author Lars Schuetze
 */
public class InterpreterRegistryListenerImpl implements
		IInterpreterTraceListener {

	/** Holding all root elements of this forest. */
	private TracerRoot originalTrace;

	/** Holds all root elements of the forest that have been selected */
	private TracerRoot filteredTrace;

	/** Pointing to the current parent */
	private TracerItem currentParent;

	/** The factory to create new instances */
	private TracermodelFactory factory;

	/** This map holds the TracerItems in a cache for fast access */
	private WeakHashMap<UUID, TracerItem> cachedItems;

	/** Indicated whether we have to trace all or just a selection */
	private boolean isTracingSelection;

	public InterpreterRegistryListenerImpl() {

		filteredTrace = null;
		originalTrace = null;
		currentParent = null;
		isTracingSelection = false;
		cachedItems = new WeakHashMap<UUID, TracerItem>();

		TracermodelPackageImpl.init();
		factory = TracermodelFactory.eINSTANCE;
	}

	/**
	 * <p>
	 * Sets the current parent of a tree after inserting a dummy entry which is to
	 * be filled later by calling {@link partialInterpretationFinished}.
	 * </p>
	 * 
	 * @param uuid
	 *          The UUID which identifies this entry later.
	 */
	public void interpretationTreeDepthIncreased(UUID uuid) {

		TracerItem dummy = factory.createTracerItem();
		dummy.setUUID(uuid);

		/* Add the dummy to the cache to be localized easier later */
		cachedItems.put(uuid, dummy);

		/* Add the dummy to the tree structure */
		if ((currentParent == null) && (originalTrace == null)) {
			/* Set the tracer to send all elements to its listeners. */
			isTracingSelection = false;

			/* This is the first item in the tree. */
			originalTrace = factory.createTracerRoot();
			originalTrace.getRootItems().add(dummy);
			currentParent = dummy;
		}
		else {
			if (currentParent == null) {
				originalTrace.getRootItems().add(dummy);
				currentParent = dummy;
			}
			else {
				currentParent.getChildren().add(dummy);
				dummy.setParent(currentParent);
				currentParent = dummy;
			}
		}
	}

	/**
	 * <p>
	 * This method overloads the
	 * {@link InterpreterRegistryListenerImpl#interpretationTreeDepthIncreased(UUID)}
	 * and adds the {@link IModelInstanceElement} to the resulting
	 * {@link TracerItem} in the tree.
	 * </p>
	 */
	public void interpretationTreeDepthIncreased(UUID uuid,
			IModelInstanceElement modelInstanceElement) {

		/* Call this method since it is overloaded by this method */
		interpretationTreeDepthIncreased(uuid);

		/* Set the modelInstanceElement from the just added item */
		TracerItem item = cachedItems.get(uuid);
		item.setModelInstanceElement(modelInstanceElement);
	}

	/**
	 * <p>
	 * Sets the current parent of the tree.
	 * </p>
	 */
	public void interpretationTreeDepthDecreased() {

		// check if the tree has been initialized
		if (currentParent != null) {
			// check if currentParent is the root element
			if (currentParent.getParent() != null) {
				currentParent = currentParent.getParent();
			}
			else {
				currentParent = null;
				/*
				 * To reduce events fired by the registry we just fire the event when a
				 * new root element is reached.
				 */
				/* Inform all listeners that the root object has been changed */
				TracerPlugin.getTracerRegistry().fireUpdateTrace(this.getCurrentRoot());
			}
		}
		// no else
		/*
		 * this should normally not happen that {@see
		 * interpretationTreeDepthIncrease} is called when currentParent == null
		 */
	}

	public void partialInterpretationFinished(InterpreterTraceEvent event) {

		/* Search the corresponding TracerItem */
		TracerItem item = cachedItems.get(event.getUUID());

		/* Probably check for null reference */
		if (item != null) {
			item.setExpression(event.getExpression());
			item.setResult(event.getResult());
		}
		// no else
	}

	/**
	 * 
	 * @return Returns the root object {@link TracerRoot} of the tree.
	 */
	public TracerRoot getCurrentRoot() {

		return isTracingSelection ? filteredTrace : originalTrace;
	}

	public void interpretationCleared() {

		isTracingSelection = false;
		currentParent = null;
		originalTrace = null;
		filteredTrace = null;

		/* Inform all listeners that the root object has been changed */
		TracerPlugin.getTracerRegistry().fireUpdateTrace(this.getCurrentRoot());
	}


	public void traceSelectedConstraints(List<Object[]> constraints) {

		isTracingSelection = true;
		filteredTrace = factory.createTracerRoot();

		for (Object[] aRow : constraints) {

			/* Check if aRow has at least three values */
			if (aRow.length >= 3) {
				
				/* Make sure all types are correct */
				IModelInstanceElement _miElement = null;
				Constraint _constraint = null;
				OclAny _result = null;

				if (aRow[0] instanceof IModelInstanceElement) {
					_miElement = (IModelInstanceElement) aRow[0];
				}

				if (aRow[1] instanceof Constraint) {
					_constraint = (Constraint) aRow[1];
				}

				if (aRow[2] instanceof OclAny) {
					_result = (OclAny) aRow[2];
				}

				if (_miElement != null && _constraint != null && _result != null) {
					List<TracerItem> readOnlyList;
					readOnlyList =
							Collections.unmodifiableList(originalTrace.getRootItems());
					for (TracerItem item : readOnlyList) {
						if ((item.getExpression() == _constraint)
								&& (item.getResult() == _result)
								&& (item.getModelInstanceElement() == _miElement)) {

							filteredTrace.getRootItems().add(item);
						}
						// no else
					}
					// end for
				}
				// no else (since one or more values or null)
			}
			// no else (aRow has less than three values)
		}
		// end for

		/* Inform all listeners that the root object has been changed */
		TracerPlugin.getTracerRegistry().fireUpdateTrace(this.getCurrentRoot());
	}
}
