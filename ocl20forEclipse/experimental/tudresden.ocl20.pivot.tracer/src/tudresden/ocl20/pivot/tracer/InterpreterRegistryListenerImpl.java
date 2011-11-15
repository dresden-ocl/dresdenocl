/*
Copyright (C) 2011 by Lars Schütze (lschuetze@gmx.net)

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

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclModelInstanceObject;
import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclModelInstanceObject;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;
import tudresden.ocl20.pivot.tracer.tracermodel.impl.TracermodelPackageImpl;

/**
 * @author Lars Schütze
 */
public class InterpreterRegistryListenerImpl implements
	IInterpreterTraceListener {
    /** Holding all root elements of this forest. */
    private TracerRoot roots;

    /** Pointing to the current parent */
    private TracerItem currentParent;

    /** The factory to create new instances */
    private TracermodelFactory factory;

    /** Holds all root elements of the forst that have been selected */
    private TracerRoot tracedRoots;

    /** Indicated whether we have to trace all or just a selection */
    private boolean isTracingSelection;

    public InterpreterRegistryListenerImpl() {
	tracedRoots = null;
	roots = null;
	currentParent = null;
	isTracingSelection = false;
	TracermodelPackageImpl.init();
	factory = TracermodelFactory.eINSTANCE;
    }

    /**
     * <p>
     * Sets the current parent of a tree after inserting a dummy entry which is
     * to be filled later by calling {@link partialInterpretationFinished}.
     * </p>
     * 
     * @param guid
     *            The UUID which identifies this entry later.
     */
    public void interpretationTreeDepthIncreased(UUID guid) {
	TracerItem dummy = factory.createTracerItem();
	dummy.setGuid(guid);

	if ((currentParent == null) && (roots == null)) {
	    // there has been no insertion before
	    // this is the first item in the tree
	    roots = factory.createTracerRoot();
	    roots.getRootItems().add(dummy);
	    currentParent = dummy;
	} else {
	    if (currentParent == null) {
		roots.getRootItems().add(dummy);
		currentParent = dummy;
	    } else {
		currentParent.getChildren().add(dummy);
		dummy.setParent(currentParent);
		currentParent = dummy;
	    }
	}
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
	    } else {
		currentParent = null;
	    }
	}
	// no else
	/*
	 * this should normally not happen that {@see
	 * interpretationTreeDepthIncrease} is called when currentParent == null
	 */
    }

    public void partialInterpretationFinished(InterpreterTraceEvent event) {
	Iterator<TracerItem> iterator;
	boolean found = false;

	if (currentParent == null) {
	    iterator = roots.getRootItems().iterator();
	    while (!found && iterator.hasNext()) {
		TracerItem item = iterator.next();
		if (item.getGuid() == event.getUUID()) {
		    // set the values for this item
		    //
		    item.setExpression(event.getExpression());
		    item.setResult(event.getResult());
		    found = true;
		}
		// no else
	    }
	    // end while
	} else {
	    if (!found && (currentParent.getGuid() == event.getUUID())) {
		currentParent.setExpression(event.getExpression());
		currentParent.setResult(event.getResult());
		found = true;
	    }
	    // no else

	    iterator = currentParent.getChildren().iterator();

	    while (!found && iterator.hasNext()) {
		TracerItem item = iterator.next();
		if (item.getGuid() == event.getUUID()) {
		    item.setExpression(event.getExpression());
		    item.setResult(event.getResult());
		    found = true;
		}
		// no else
	    }
	    // end while
	}
	// end else
    }

    /**
     * 
     * @return Returns the root object {@link TracerRoot} of the tree.
     */
    public TracerRoot getTree() {
	return isTracingSelection ? tracedRoots : roots;
    }

    public void interpretationCleared() {
	Iterator<EObject> it = factory.eContents().iterator();

	// clear all saved TracerItems due to memory leaks
	while (it.hasNext()) {
	    it.remove();
	}

	isTracingSelection = false;
	roots = null;
	currentParent = null;
	tracedRoots = null;
    }

    public void traceSelectedConstraints(List<Object[]> constraints) {
	isTracingSelection = true;
	tracedRoots = factory.createTracerRoot();

	for (Object[] aRow : constraints) {
	    /*
	     * aRow[0] holds the ModelInstanceElement aRow[1] holds the
	     * Constraint itself aRow[2] holds the result
	     * 
	     * Now we need to find the specific element in our tree and return
	     * its subtree
	     */

	    for (TracerItem i : roots.getRootItems()) {
		if ((i.getExpression() == aRow[1])
			&& (i.getResult() == aRow[2])) {
		    /* It is always a JavaModelInstanceObject in aRow[0] */
		    IModelInstanceObject obj = (IModelInstanceObject) aRow[0];

		    if (DFS(i, obj)) {
			/* Add the item to be displayed later */
			tracedRoots.getRootItems().add(i);
		    }
		    // no else
		}
		// no else
	    }
	    // end for
	}
	// end for
    }

    /**
     * <p>
     * Deep first search (DFS) searching for the specified
     * {@link IModelInstanceObject}.
     * </p>
     * 
     * @param node
     *            The {@link TracerItem} to be searched.
     * @param obj
     *            The {@link IModelInstanceObject} for what is searched.
     * @return true if the node's result equals the obj<br />
     *         false otherwise
     */
    private boolean DFS(TracerItem node, IModelInstanceObject obj) {

	/* Check whether the current node fits the abort criteria */
	if (node.getResult() instanceof JavaOclModelInstanceObject) {
	    IModelInstanceObject nodeResult = ((OclModelInstanceObject) node
		    .getResult()).getModelInstanceObject();
	    if (nodeResult == obj) {
		return true;
	    }
	    // no else
	} else {
	    boolean result = false;
	    Iterator<TracerItem> it = node.getChildren().iterator();
	    while (!result && it.hasNext()) {
		result = DFS(it.next(), obj);
	    }
	    // end while
	    return result;
	}
	// end else
	return false;
    }
}
