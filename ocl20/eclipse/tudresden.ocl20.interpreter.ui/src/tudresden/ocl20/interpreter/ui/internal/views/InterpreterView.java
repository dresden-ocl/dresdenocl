/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.interpreter.IEnvironment;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.interpreter.InterpreterPlugin;
import tudresden.ocl20.interpreter.event.IInterpreterRegistryListener;
import tudresden.ocl20.interpreter.event.InterpreterRegistryEvent;
import tudresden.ocl20.interpreter.internal.Environment;
import tudresden.ocl20.interpreter.internal.OclInterpreter;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent;
import tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class InterpreterView extends ViewPart implements ISelectionListener,
		IModelInstanceRegistryListener, IModelRegistryListener,
		IInterpreterRegistryListener {

	// the viewer
	private TableViewer viewer;

	// the menu
	private IMenuManager menu;

	// actions needing selecting constraints
	private List<InterpretAction> csSelectionNeeded;

	// actions needing selecting model objects
	private List<InterpretAction> moSelectionNeeded;

	// actions perfoming interpretation
	private List<InterpretAction> interpretationActions;

	// actions perfoming preparation
	private List<InterpretAction> preparationActions;

	// the actual filter
	private ResultsFilter actualFilter = new ResultsFilter();

	// action removing results
	private InterpretAction remResultAction = null;

	// The double click action
	private Action doubleClickAction;

	/**
	 * The content provider class is responsible for providing objects to the
	 * view. ResultsContentProvider provides elements as List containing
	 * {@link Constraint}, {@link IModelObject} and {@link OclRoot} result
	 */
	class ResultsContentProvider implements IStructuredContentProvider {

		/** The list index containing the {@link IModelObject}. */
		public static final int MODELOBJECT = 0;

		/** The list index containing the {@link Constraint}. */
		public static final int CONSTRAINT = 1;

		/** The list index containing the result. */
		public static final int RESULT = 2;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
		}

		/**
		 * provides elements as List containing {@link Constraint},
		 * {@link IModelObject} and {@link OclRoot} result
		 * 
		 * @return an array of list containing {@link Constraint},
		 *         {@link IModelObject} and {@link OclRoot} result
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object parent) {
			if (parent instanceof IModelInstance) {
				Iterator<IModelObject> objectsIt = ((IModelInstance) parent)
						.getObjects().iterator();
				List<Object> result = new ArrayList<Object>();
				while (objectsIt.hasNext()) {
					IModelObject curObj = objectsIt.next();
					Map<Constraint, OclRoot> results = new HashMap<Constraint, OclRoot>(
							curObj.getResults());
					Set<Constraint> keySet = results.keySet();
					Iterator<Constraint> keySetIt = keySet.iterator();
					while (keySetIt.hasNext()) {
						Constraint key = keySetIt.next();
						List<Object> resultKey = new ArrayList<Object>();
						resultKey.add(curObj);
						resultKey.add(key);
						resultKey.add(results.get(key));
						result.add(resultKey);
					}
				}
				return result.toArray(new Object[result.size()]);
			}
			return new Object[0];
		}
	}

	/**
	 * The label provider for this view
	 */
	class ResultsLabelProvider extends LabelProvider implements
			ITableLabelProvider {

		/**
		 * using lists provides by {@link ResultsContentProvider}
		 * 
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
		 *      int)
		 */
		public String getColumnText(Object obj, int index) {
			if (obj instanceof List) {
				switch (index) {
				case ResultsContentProvider.MODELOBJECT:
				case ResultsContentProvider.RESULT:
					return ((List) obj).get(index) != null ? ((List) obj).get(
							index).toString() : "Problem recieving result";
				case ResultsContentProvider.CONSTRAINT:
					Constraint c = (Constraint) ((List) obj).get(index);
					return "context "
							+ ((NamedElement) c.getConstrainedElement().get(0))
									.getQualifiedName()
							+ " "
							+ c.getKind()
							+ " "
							+ c.getName()
							+ ": "
							+ (c.getSpecification().getBody() != null ? c
									.getSpecification().getBody().toString()
									: "");
				}
			}
			return "Error";
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
		 *      int)
		 */
		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	/**
	 * the filter for the results
	 */
	class ResultsFilter extends ViewerFilter {

		// the constraints for which results shall be shown
		private Set<Constraint> filterConstraint;

		// the model objects for which results shall be shown
		private Set<IModelObject> filterModelObject;

		/**
		 * Adds constraints to filter.
		 * 
		 * @param c
		 *            the {@link Constraint} for which results shall be shown
		 */
		public void addConstraintFilter(Constraint c) {
			if (filterConstraint == null)
				filterConstraint = new HashSet<Constraint>();
			this.filterConstraint.add(c);
		}

		/**
		 * Adds model object to filter.
		 * 
		 * @param mo
		 *            the {@link IModelObject} for which results shall be shown
		 */
		public void addModelObjectFilter(List<IModelObject> mo) {
			if (filterModelObject == null)
				filterModelObject = new HashSet<IModelObject>();
			this.filterModelObject.addAll(mo);
		}

		/**
		 * Clear constraint filter.
		 */
		public void clearConstraintFilter() {
			this.filterConstraint = null;
		}

		/**
		 * Clear model object filter.
		 */
		public void clearModelObjectFilter() {
			this.filterModelObject = null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (element instanceof List) {
				boolean filterObjectBool = (filterModelObject == null)
						|| filterModelObject.contains(((List) element)
								.get(ResultsContentProvider.MODELOBJECT));
				boolean filterConstraintBool = (filterConstraint == null)
						|| filterConstraint.contains(((List) element)
								.get(ResultsContentProvider.CONSTRAINT));
				return (filterConstraintBool && filterObjectBool);
			}
			return false;
		}
	}

	/**
	 * The actions for interpretation, preparation and removing results
	 */
	class InterpretAction extends Action implements IAction {

		/**
		 * action interpret selected {@link Constraint}s for selected
		 * {@link IModelObject}s
		 */
		public static final int INTERPRET_SEL = 0;

		/**
		 * action interpret all {@link Constraint}s for selected
		 * {@link IModelObject}s
		 */
		public static final int INTERPRET_SEL_MO = 1;

		/**
		 * action interpret selected {@link Constraint}s for all
		 * {@link IModelObject}s
		 */
		public static final int INTERPRET_SEL_CS = 2;

		/**
		 * action interpret all {@link Constraint}s for all
		 * {@link IModelObject}s
		 */
		public static final int INTERPRET_ALL = 3;

		/**
		 * action clear selected {@link Constraint}s for selected
		 * {@link IModelObject}s
		 */
		public static final int CLEAR_SEL = 4;

		/**
		 * action clear all {@link Constraint}s for selected
		 * {@link IModelObject}s
		 */
		public static final int CLEAR_SEL_MO = 5;

		/**
		 * action clear selected {@link Constraint}s for all
		 * {@link IModelObject}s
		 */
		public static final int CLEAR_SEL_CS = 6;

		/**
		 * action clear all {@link Constraint}s for all {@link IModelObject}s
		 */
		public static final int CLEAR_ALL = 7;

		/**
		 * action remove selected results
		 */
		public static final int REMOVE_SEL = 8;

		/**
		 * action prepare selected {@link Constraint}s
		 */
		public static final int PREPARE_SEL = 9;

		/**
		 * action prepare all {@link Constraint}s
		 */
		public static final int PREPARE_ALL = 10;

		// the action
		private int action;

		// shall cache be used
		private boolean useCache = false;

		// ConstraintKinds to be used
		private List<ConstraintKind> kinds;

		// model objects to be used
		private Set<IModelObject> modelObjects;

		// constraints to be used
		private Set<Constraint> constraints;

		/**
		 * Instantiates a new interpret action.
		 * 
		 * @param action
		 *            the action to be performed
		 */
		public InterpretAction(int action) {
			super(null, IAction.AS_PUSH_BUTTON);
			this.action = action;
			setText(toString());
			kinds = new ArrayList<ConstraintKind>();
			switch (action) {
			case INTERPRET_ALL:
			case INTERPRET_SEL:
			case INTERPRET_SEL_CS:
			case INTERPRET_SEL_MO:
				kinds.add(ConstraintKind.INVARIANT);
				kinds.add(ConstraintKind.PRECONDITION);
				kinds.add(ConstraintKind.POSTCONDITION);
				break;
			case PREPARE_ALL:
			case PREPARE_SEL:
				kinds.add(ConstraintKind.BODY);
				kinds.add(ConstraintKind.DEFINITION);
				kinds.add(ConstraintKind.POSTCONDITION);
				kinds.add(ConstraintKind.DERIVED);
				kinds.add(ConstraintKind.INITIAL);
				break;
			}
		}

		/**
		 * Sets action to use cache.
		 * 
		 * @param useCache
		 *            shall cache be used
		 */
		public void setUseCache(boolean useCache) {
			this.useCache = useCache;
		}

		/**
		 * Adds {@link ConstraintKind} to used kinds.
		 * 
		 * @param kind
		 *            the {@link ConstraintKind} to be added
		 */
		public void addKind(ConstraintKind kind) {
			if (!kinds.contains(kind))
				kinds.add(kind);
		}

		/**
		 * Removes {@link ConstraintKind}.
		 * 
		 * @param kind
		 *            the {@link ConstraintKind} to be removed
		 */
		public void removeKind(ConstraintKind kind) {
			kinds.remove(kind);
		}

		/**
		 * Adds {@link Constraint} to be used.
		 * 
		 * @param c
		 *            the {@link Constraint} to be added
		 */
		public void addSelectedConstraints(Constraint c) {
			if (constraints == null)
				constraints = new HashSet<Constraint>();
			constraints.add(c);
		}

		/**
		 * Adds {@link IModelObject} to be used.
		 * 
		 * @param mo
		 *            the {@link IModelObject} to be added
		 */
		public void addSelectedModelObjects(List<IModelObject> mo) {
			if (modelObjects == null)
				modelObjects = new HashSet<IModelObject>();
			modelObjects.addAll(mo);
		}

		/**
		 * Clear used {@link Constraint}.
		 */
		public void clearSelectedConstraints() {
			constraints = null;
		}

		/**
		 * Clear used {@link IModelObject}.
		 */
		public void clearSelectedModelObjects() {
			modelObjects = null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			switch (action) {
			case INTERPRET_SEL:
				if (modelObjects == null) {
					showMessage("No model objects selected");
					break;
				}
				if (constraints == null) {
					showMessage("No constraints selected");
					break;
				}
				interpretSelection(modelObjects, constraints);
				break;
			case INTERPRET_SEL_CS:
				if (constraints == null) {
					showMessage("No constraints selected");
					break;
				}
				interpretSelection(null, constraints);
				break;
			case INTERPRET_SEL_MO:
				if (modelObjects == null) {
					showMessage("No model objects selected");
					break;
				}
				interpretSelection(modelObjects, null);
				break;
			case INTERPRET_ALL:
				interpretSelection(null, null);
				break;
			case CLEAR_SEL:
			case REMOVE_SEL:
				if (modelObjects == null) {
					showMessage("No model objects selected");
					break;
				}
				if (constraints == null) {
					showMessage("No constraints selected");
					break;
				}
				clearSelection(modelObjects, constraints);
				break;
			case CLEAR_SEL_CS:
				if (constraints == null) {
					showMessage("No constraints selected");
					break;
				}
				clearSelection(null, constraints);
				break;
			case CLEAR_SEL_MO:
				if (modelObjects == null) {
					showMessage("No model objects selected");
					break;
				}
				clearSelection(modelObjects, null);
				break;
			case CLEAR_ALL:
				clearSelection(null, null);
				break;
			case PREPARE_SEL:
				if (constraints == null) {
					showMessage("No constraints selected");
					break;
				}
				prepareSelection(constraints);
				break;
			case PREPARE_ALL:
				prepareSelection(null);
				break;
			}
		}

		/**
		 * Remove results for selected {@link IModelObject}s and selected
		 * {@link Constraint}s
		 * 
		 * @param modelObjects
		 *            the {@link IModelObject}s for which results shall be
		 *            removed or <code>null</code> if all {@link IModelObject}s
		 *            shall be used
		 * @param constraints
		 *            the {@link Constraint}s for which results shall be
		 *            removed or <code>null</code> if all {@link Constraint}s
		 *            shall be used
		 */
		private void clearSelection(Set<IModelObject> modelObjects,
				Set<Constraint> constraints) {
			IModel activeModel = ModelBusPlugin.getModelRegistry()
					.getActiveModel();
			IModelInstance activeModelInstance = null;
			if (activeModel != null) {
				activeModelInstance = ModelBusPlugin.getModelInstanceRegistry()
						.getActiveModelInstance(activeModel);

				if (activeModelInstance != null) {
					IEnvironment env = Environment.getGlobalEnvironment();
					env.setModelInstance(activeModelInstance);
					IOclInterpreter interp = new OclInterpreter(Environment
							.getGlobalEnvironment());

					Set<IModelObject> usedModelObjects = null;
					if (modelObjects != null)
						usedModelObjects = modelObjects;
					else {
						usedModelObjects = new HashSet<IModelObject>(
								activeModelInstance.getObjects());
					}

					Set<Constraint> usedConstraints = constraints;

					if (usedConstraints == null)
						interp.clearResults(new ArrayList<IModelObject>(
								usedModelObjects));
					else {
						interp.removeResults(new ArrayList<IModelObject>(
								usedModelObjects), new ArrayList<Constraint>(
								usedConstraints));
					}
				} else
					showMessage("No model instance loaded for model "
							+ activeModel);
			} else
				showMessage("No model loaded");
		}

		/**
		 * Interpret selected {@link IModelObject}s and selected
		 * {@link Constraint}s
		 * 
		 * @param modelObjects
		 *            the {@link IModelObject}s that shall be interpreted or
		 *            <code>null</code> if all {@link IModelObject}s shall be
		 *            used
		 * @param constraints
		 *            the {@link Constraint}s that shall be interpreted or
		 *            <code>null</code> if all {@link Constraint}s shall be
		 *            used
		 */
		private void interpretSelection(Set<IModelObject> modelObjects,
				Set<Constraint> constraints) {
			IModel activeModel = ModelBusPlugin.getModelRegistry()
					.getActiveModel();
			IModelInstance activeModelInstance = null;
			if (activeModel != null) {
				activeModelInstance = ModelBusPlugin.getModelInstanceRegistry()
						.getActiveModelInstance(activeModel);

				if (activeModelInstance != null) {
					IEnvironment env = Environment.getGlobalEnvironment();
					env.setModelInstance(activeModelInstance);
					IOclInterpreter interp = new OclInterpreter(Environment
							.getGlobalEnvironment());

					interp.setUseCache(useCache);

					Set<IModelObject> usedModelObjects = null;
					if (modelObjects != null)
						usedModelObjects = modelObjects;
					else {
						usedModelObjects = new HashSet<IModelObject>(
								activeModelInstance.getObjects());
					}

					Set<Constraint> usedConstraints = null;
					if (constraints != null)
						usedConstraints = constraints;
					else {
						try {
							usedConstraints = new HashSet<Constraint>(
									activeModel.getRootNamespace()
											.getOwnedRule());
							for (Namespace namespace : activeModel
									.getRootNamespace().getNestedNamespace())
								usedConstraints
										.addAll(namespace.getOwnedRule());
						} catch (ModelAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					for (IModelObject object : usedModelObjects) {
						for (Constraint constraint : usedConstraints) {
							if (kinds.contains(constraint.getKind())) {
								if (((NamedElement) constraint
										.getConstrainedElement().get(0))
										.getQualifiedName()
										.equals(object.getQualifiedNameString()))
									interp.interpret(constraint, object);
								else if (((NamedElement) constraint
										.getConstrainedElement().get(0)) instanceof Operation)
									if (((Operation) ((NamedElement) constraint
											.getConstrainedElement().get(0)))
											.getOwningType()
											.getQualifiedName()
											.equals(
													object
															.getQualifiedNameString()))
										interp.interpret(constraint, object);
							}
						}
					}
				} else
					showMessage("No model instance loaded for model "
							+ activeModel);
			} else
				showMessage("No model loaded");
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			switch (action) {
			case INTERPRET_SEL:
				return "Interpret selected constraints for selected model objects";
			case INTERPRET_SEL_CS:
				return "Interpret selected constraints for all model objects";
			case INTERPRET_SEL_MO:
				return "Interpret all constraints for selected model objects";
			case INTERPRET_ALL:
				return "Interpret all constraints for all model objects";
			case CLEAR_SEL:
				return "Clear results for selected constraints for selected model objects";
			case CLEAR_SEL_CS:
				return "Clear results for selected constraints for all model objects";
			case CLEAR_SEL_MO:
				return "Clear results for all constraints for selected model objects";
			case CLEAR_ALL:
				return "Clear results for all constraints for all model objects";
			case REMOVE_SEL:
				return "Remove selected results";
			case PREPARE_SEL:
				return "Prepare selected constraints";
			case PREPARE_ALL:
				return "Prepare all constraints";
			default:
				return "No action selected";
			}
		}

		/**
		 * Prepare selected {@link Constraint}.
		 * 
		 * @param constraints
		 *            the {@link Constraint}s that shall be prepared or
		 *            <code>null</code> if all {@link Constraint}s shall be
		 *            used
		 */
		private void prepareSelection(Set<Constraint> constraints) {
			IModel activeModel = ModelBusPlugin.getModelRegistry()
					.getActiveModel();
			IModelInstance activeModelInstance = null;
			if (activeModel != null) {
				activeModelInstance = ModelBusPlugin.getModelInstanceRegistry()
						.getActiveModelInstance(activeModel);

				if (activeModelInstance != null) {
					IEnvironment env = Environment.getGlobalEnvironment();
					env.setModelInstance(activeModelInstance);
					IOclInterpreter interp = new OclInterpreter(Environment
							.getGlobalEnvironment());

					Set<Constraint> usedConstraints = null;
					if (constraints != null)
						usedConstraints = constraints;
					else {
						try {
							usedConstraints = new HashSet<Constraint>(
									activeModel.getRootNamespace()
											.getOwnedRule());
							for (Namespace namespace : activeModel
									.getRootNamespace().getNestedNamespace())
								usedConstraints
										.addAll(namespace.getOwnedRule());
						} catch (ModelAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					Set<IModelObject> usedModelObjects = null;
					usedModelObjects = new HashSet<IModelObject>(
							activeModelInstance.getObjects());

					for (Constraint constraint : usedConstraints) {
						if (constraint.getKind().equals(
								ConstraintKind.DEFINITION)) {
							if (kinds.contains(ConstraintKind.DEFINITION)) {
								// when parser supports definition change
								// following
								// line until then for testing one definition
								// with
								// name 'newAtt' is possible
								String featureName = constraint
										.getDefinedFeature() != null ? constraint
										.getDefinedFeature().getName()
										: "newAtt";
								String definedFeature = ((NamedElement) constraint
										.getConstrainedElement().get(0))
										.getQualifiedName()
										+ "::" + featureName;
								env.addConstraint(definedFeature, constraint);
							}
						} else if (constraint.getKind().equals(
								ConstraintKind.POSTCONDITION)) {
							if (kinds.contains(ConstraintKind.POSTCONDITION)) {
								for (IModelObject mo : usedModelObjects) {
									List<String> qn = new ArrayList<String>(
											Arrays
													.asList(((NamedElement) constraint
															.getConstrainedElement()
															.get(0))
															.getQualifiedName()
															.split("::")));
									String qnWithoutOperation = "";
									if (!qn.get(0).contains("("))
										qnWithoutOperation = qn.remove(0);
									Iterator<String> qnIt = qn.iterator();
									while (qnIt.hasNext()) {
										String current = qnIt.next();
										if (!current.contains("("))
											qnWithoutOperation = qnWithoutOperation
													+ "::" + current;
										else
											break;
									}
									if (qnWithoutOperation.equals(mo
											.getQualifiedNameString()))
										interp.prepare(constraint, mo);
								}
							}
						} else if (constraint.getKind().equals(
								ConstraintKind.BODY)
								|| constraint.getKind().equals(
										ConstraintKind.INITIAL)
								|| constraint.getKind().equals(
										ConstraintKind.DERIVED)) {
							if (kinds.contains(constraint.getKind())) {
								String append = "";
								if (constraint.getKind().equals(
										ConstraintKind.INITIAL))
									append = "-initial";
								else if (constraint.getKind().equals(
										ConstraintKind.DERIVED))
									append = "-derive";
								env.addConstraint(((NamedElement) constraint
										.getConstrainedElement().get(0))
										.getQualifiedName()
										+ append, constraint);
							}
						}
					}
				} else
					showMessage("No model instance loaded for model "
							+ activeModel);
			} else
				showMessage("No model loaded");
		}
	}

	/**
	 * The dialog for adding variable to environment.
	 */
	public class AddVariableDialog extends Dialog implements SelectionListener {

		Label varLabel;
		Text varText;

		Label typeLabel;
		Combo typeCb;

		Label valLabel;
		Text valText;

		Label resultLabel;
		Combo resultCb;

		/**
		 * Create a new instance of the receiver.
		 * 
		 * @param parentShell
		 *            the parent shell
		 */
		public AddVariableDialog(Shell parentShell) {
			super(parentShell);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
		 */
		protected void configureShell(Shell newShell) {
			super.configureShell(newShell);
			newShell.setText("Add var to environment");

		}

		// The results.
		private List<OclRoot> results = null;

		// The active model.
		private IModel activeModel = null;

		// The model instance.
		private IModelInstance modelInstance = null;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
		 */
		protected Control createDialogArea(Composite parent) {
			Composite top = (Composite) super.createDialogArea(parent);

			Composite editArea = new Composite(top, SWT.NONE);
			editArea.setLayout(new GridLayout());
			editArea.setLayoutData(new GridData(GridData.FILL_BOTH
					| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

			varLabel = new Label(editArea, SWT.RIGHT);
			varLabel.setText("Variable path");
			varText = new Text(editArea, SWT.BORDER);

			typeLabel = new Label(editArea, SWT.RIGHT);
			typeLabel.setText("Type");
			typeCb = new Combo(editArea, SWT.DROP_DOWN);

			typeCb.add("Integer");
			typeCb.add("Real");
			typeCb.add("String");
			typeCb.add("Boolean");
			typeCb.add("Result");
			typeCb.addSelectionListener(this);

			typeCb.select(0);

			valLabel = new Label(editArea, SWT.RIGHT);
			valLabel.setText("Value");
			valText = new Text(editArea, SWT.BORDER);

			resultLabel = new Label(editArea, SWT.RIGHT);
			resultLabel.setText("Result");
			resultCb = new Combo(editArea, SWT.DROP_DOWN);
			activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
			results = new ArrayList<OclRoot>();
			if (activeModel != null) {
				modelInstance = ModelBusPlugin.getModelInstanceRegistry()
						.getActiveModelInstance(activeModel);
				if (modelInstance != null)
					for (IModelObject modelObject : modelInstance.getObjects())
						for (OclRoot or : modelObject.getResults().values())
							results.add(or);
			}
			for (OclRoot or : results)
				resultCb.add(or.toString());
			resultCb.select(0);
			resultCb.setEnabled(false);

			return top;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
		 */
		protected void okPressed() {
			boolean success = false;
			OclRoot result = null;
			if (modelInstance != null) {
				String value = valText.getText();
				if (typeCb.getText().equals("Integer")) {
					if (!value.equals("")) {
						try {
							result = modelInstance.getFactory()
									.createOclInteger(Integer.parseInt(value));
						} catch (NumberFormatException e) {
							showMessage("The value " + value
									+ " is not an integer");
						}
					} else
						showMessage("No value given");
				} else if (typeCb.getText().equals("String")) {
					if (!value.equals("")) {
						result = modelInstance.getFactory().createOclString(
								value);
					} else
						showMessage("No value given");
				} else if (typeCb.getText().equals("Real")) {
					if (!value.equals("")) {
						try {
							result = modelInstance.getFactory().createOclReal(
									Float.parseFloat(value));
						} catch (NumberFormatException e) {
							showMessage("The value " + value + " is not a real");
						}
					} else
						showMessage("No value given");
				} else if (typeCb.getText().equals("Boolean")) {
					if (value.toLowerCase().equals("true"))
						result = modelInstance.getFactory().createOclBoolean(
								true);
					else if (value.toLowerCase().equals("false"))
						result = modelInstance.getFactory().createOclBoolean(
								false);
					else
						showMessage("Value for boolean must be either 'true' or 'false'");
				} else if (typeCb.getText().equals("Result") && results != null) {
					if (results.size() > 0) {
						if (typeCb.getSelectionIndex() >= 0)
							result = results
									.get(typeCb.getSelectionIndex() - 1);
						else
							showMessage("No result selected");
					}
				}
			} else
				showMessage("No model instance loaded");

			if (!varText.getText().equals("")) {
				if (result != null) {
					Environment.getGlobalEnvironment().addVar(
							varText.getText(), result);
					success = true;
				}
			} else
				showMessage("No variable pathname given");

			if (success)
				super.okPressed();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		public void widgetSelected(SelectionEvent e) {
			if (e.widget == typeCb) {
				if (((Combo) e.widget).getText().equals("Result")) {
					valText.setEnabled(false);
					resultCb.setEnabled(true);
				} else {
					valText.setEnabled(true);
					resultCb.setEnabled(false);
				}
			}
		}
	}

	/**
	 * The constructor.
	 */
	public InterpreterView() {
		ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);
		ModelBusPlugin.getModelInstanceRegistry()
				.addModelInstanceRegistryListener(this);
		InterpreterPlugin.getInterpreterRegistry()
				.addInterpreterRegistryListener(this);
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 * 
	 * @param parent
	 *            the parent
	 */
	public void createPartControl(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		viewer.setContentProvider(new ResultsContentProvider());
		viewer.setLabelProvider(new ResultsLabelProvider());

		final Table table = viewer.getTable();

		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		TableColumn column = new TableColumn(table, SWT.LEFT);

		column.setText("Object");

		column = new TableColumn(table, SWT.LEFT);

		column.setText("Constraint");

		column = new TableColumn(table, SWT.LEFT);

		column.setText("Result");

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		IModel activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
		if (activeModel != null)
			viewer.setInput(ModelBusPlugin.getModelInstanceRegistry()
					.getActiveModelInstance(activeModel));
		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack();
		}

		getViewSite().getPage().addSelectionListener(this);
		getViewSite().setSelectionProvider(viewer);
		hookDoubleClickAction();
		initMenu();
	}

	/**
	 * Hook double click action.
	 */
	private void hookDoubleClickAction() {
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				if (obj instanceof List)
					showMessage("Selected result: "
							+ ((List) obj).get(ResultsContentProvider.RESULT));
			}
		};

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	/**
	 * Show message.
	 * 
	 * @param message
	 *            the message
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"Interpreter Results", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/**
	 * Adds {@link Constraint} to actions needing constraint selection.
	 * 
	 * @param c
	 *            the {@link Constraint} to be added
	 */
	private void addConstraintSelection(Constraint c) {
		for (InterpretAction action : csSelectionNeeded)
			action.addSelectedConstraints(c);
	}

	/**
	 * Adds {@link IModelObject} to actions needing model object selection.
	 * 
	 * @param mo
	 *            the {@link IModelObject} to be added
	 */
	private void addModelObjectSelection(List<IModelObject> mo) {
		for (InterpretAction action : moSelectionNeeded)
			action.addSelectedModelObjects(mo);
	}

	/**
	 * Clear constraint selection for actions needing constraint selection.
	 */
	private void clearConstraintSelection() {
		for (InterpretAction action : csSelectionNeeded)
			action.clearSelectedConstraints();
	}

	/**
	 * Clear model object selection for actions needing model object selection.
	 */
	private void clearModelObjectSelection() {
		for (InterpretAction action : moSelectionNeeded)
			action.clearSelectedModelObjects();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part.getSite() != null
				&& part.getSite().getId().equals(
						IModelBusConstants.MODELS_VIEW_ID)) {
			if (selection instanceof TreeSelection) {
				viewer.removeFilter(actualFilter);
				Iterator selIt = ((TreeSelection) selection).iterator();
				actualFilter.clearModelObjectFilter();
				actualFilter.clearConstraintFilter();
				clearConstraintSelection();
				while (selIt.hasNext()) {
					Object o = selIt.next();
					if (o instanceof Constraint) {
						actualFilter.addConstraintFilter((Constraint) o);
						addConstraintSelection((Constraint) o);
					}
				}
				viewer.addFilter(actualFilter);
				refreshView();
			}
		} else if (part.getSite() != null
				&& part.getSite().getId().equals(
						IModelBusConstants.MODEL_INSTANCES_VIEW_ID)) {
			if (selection instanceof TreeSelection) {
				viewer.removeFilter(actualFilter);
				Iterator selIt = ((TreeSelection) selection).iterator();
				actualFilter.clearModelObjectFilter();
				clearModelObjectSelection();
				while (selIt.hasNext()) {
					Object o = selIt.next();
					if (o instanceof IModelObject) {
						actualFilter.addModelObjectFilter(Arrays
								.asList((IModelObject) o));
						addModelObjectSelection(Arrays.asList((IModelObject) o));
					} else if (o instanceof List) {
						List<IModelObject> objects = ModelBusPlugin
								.getModelInstanceRegistry()
								.getActiveModelInstance(
										ModelBusPlugin.getModelRegistry()
												.getActiveModel())
								.getObjectsOfKind((List<String>) o);
						actualFilter.addModelObjectFilter(objects);
						addModelObjectSelection(objects);
					}
				}
				viewer.addFilter(actualFilter);
				refreshView();
			}
		} else if (part instanceof InterpreterView) {
			if (selection instanceof StructuredSelection) {
				Iterator selIt = ((StructuredSelection) selection).iterator();
				remResultAction.clearSelectedConstraints();
				remResultAction.clearSelectedModelObjects();
				while (selIt.hasNext()) {
					Object o = selIt.next();
					if (o instanceof List) {
						remResultAction
								.addSelectedModelObjects(Arrays
										.asList((IModelObject) ((List) o)
												.get(ResultsContentProvider.MODELOBJECT)));
						remResultAction
								.addSelectedConstraints((Constraint) ((List) o)
										.get(ResultsContentProvider.CONSTRAINT));
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener#activeModelInstanceChanged(tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void activeModelInstanceChanged(ModelInstanceRegistryEvent event) {
		setActiveModelInstance(event.getAffectedModel(), event
				.getAffectedModelInstance());
	}

	/**
	 * Refresh the viewer after model instance changed.
	 * 
	 * @param affectedModel
	 *            the affected {@link IModel}
	 * @param activeModelInstance
	 *            the active {@link IModelInstance}
	 */
	private void setActiveModelInstance(IModel affectedModel,
			IModelInstance activeModelInstance) {
		viewer.setInput(activeModelInstance);
		refreshView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener#modelInstanceAdded(tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void modelInstanceAdded(ModelInstanceRegistryEvent event) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#activeModelChanged(tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void activeModelChanged(ModelRegistryEvent e) {
		setActiveModelInstance(e.getAffectedModel(), ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						e.getAffectedModel()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#modelAdded(tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void modelAdded(ModelRegistryEvent e) {
	}

	/**
	 * Gets the menu.
	 * 
	 * @return the menu
	 */
	protected IMenuManager getMenu() {
		if (menu == null) {
			menu = getViewSite().getActionBars().getMenuManager();
		}

		return menu;
	}

	/**
	 * Sets the interpreting actions to use cache.
	 * 
	 * @param useCache
	 *            shall cache be used
	 */
	private void setUseCache(boolean useCache) {
		for (InterpretAction action : interpretationActions)
			action.setUseCache(useCache);
	}

	/**
	 * Removes the constraint kinds to be used from actions.
	 * 
	 * @param kind
	 *            the {@link ConstraintKind}
	 * @param actions
	 *            the list of affected {@link InterpretAction}s
	 */
	private void removeKind(ConstraintKind kind, List<InterpretAction> actions) {
		for (InterpretAction action : actions)
			action.removeKind(kind);
	}

	/**
	 * Adds the constraint kinds to be used to the actions.
	 * 
	 * @param kind
	 *            the {@link ConstraintKind}
	 * @param actions
	 *            the list of affected {@link InterpretAction}s
	 */
	private void addKind(ConstraintKind kind, List<InterpretAction> actions) {
		for (InterpretAction action : actions)
			action.addKind(kind);
	}

	/**
	 * Initializes the drop-down menu of the view with all supported actions.
	 */
	protected void initMenu() {
		if (csSelectionNeeded == null)
			csSelectionNeeded = new ArrayList<InterpretAction>();

		if (moSelectionNeeded == null)
			moSelectionNeeded = new ArrayList<InterpretAction>();

		if (interpretationActions == null)
			interpretationActions = new ArrayList<InterpretAction>();

		if (preparationActions == null)
			preparationActions = new ArrayList<InterpretAction>();

		Action prepBody = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.BODY, preparationActions);
				else
					addKind(ConstraintKind.BODY, preparationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		prepBody.setChecked(true);
		prepBody.setText("BODY-Constraints");
		getMenu().add(prepBody);

		Action prepDef = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.DEFINITION, preparationActions);
				else
					addKind(ConstraintKind.DEFINITION, preparationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		prepDef.setChecked(true);
		prepDef.setText("DEFINITION-Constraints");
		getMenu().add(prepDef);

		Action prepDer = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.DERIVED, preparationActions);
				else
					addKind(ConstraintKind.DERIVED, preparationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		prepDer.setChecked(true);
		prepDer.setText("DERIVE-Constraints");
		getMenu().add(prepDer);

		Action prepIni = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.INITIAL, preparationActions);
				else
					addKind(ConstraintKind.INITIAL, preparationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		prepIni.setChecked(true);
		prepIni.setText("INITIAL-Constraints");
		getMenu().add(prepIni);

		Action prepPost = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.POSTCONDITION, preparationActions);
				else
					addKind(ConstraintKind.POSTCONDITION, preparationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		prepPost.setChecked(true);
		prepPost.setText("POSTCONDIOTION-Constraints");
		getMenu().add(prepPost);

		InterpretAction prepareSelAction = new InterpretAction(
				InterpretAction.PREPARE_SEL);
		csSelectionNeeded.add(prepareSelAction);
		preparationActions.add(prepareSelAction);
		getMenu().add(prepareSelAction);

		InterpretAction prepareAllAction = new InterpretAction(
				InterpretAction.PREPARE_ALL);
		preparationActions.add(prepareAllAction);
		getMenu().add(prepareAllAction);

		getMenu().add(new Separator());

		Action setUseCache = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = false;

			public void run() {
				setUseCache(!checked);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		setUseCache.setChecked(false);
		setUseCache.setText("Use cache");
		getMenu().add(setUseCache);

		getMenu().add(new Separator());

		Action intInv = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.INVARIANT, interpretationActions);
				else
					addKind(ConstraintKind.INVARIANT, interpretationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		intInv.setChecked(true);
		intInv.setText("INVARIANT-Constraints");
		getMenu().add(intInv);

		Action intPre = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.PRECONDITION,
							interpretationActions);
				else
					addKind(ConstraintKind.PRECONDITION, interpretationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		intPre.setChecked(true);
		intPre.setText("PRECONSTION-Constraints");
		getMenu().add(intPre);

		Action intPost = new Action(null, IAction.AS_CHECK_BOX) {

			boolean checked = true;

			public void run() {
				if (checked)
					removeKind(ConstraintKind.POSTCONDITION,
							interpretationActions);
				else
					addKind(ConstraintKind.POSTCONDITION, interpretationActions);
				checked = !checked;
				this.setChecked(checked);
			}
		};
		intPost.setChecked(true);
		intPost.setText("POSTCONDITION-Constraints");
		getMenu().add(intPost);

		InterpretAction interpretSelAction = new InterpretAction(
				InterpretAction.INTERPRET_SEL);
		moSelectionNeeded.add(interpretSelAction);
		csSelectionNeeded.add(interpretSelAction);
		interpretationActions.add(interpretSelAction);
		getMenu().add(interpretSelAction);

		InterpretAction interpretSelCSAction = new InterpretAction(
				InterpretAction.INTERPRET_SEL_CS);
		csSelectionNeeded.add(interpretSelCSAction);
		interpretationActions.add(interpretSelCSAction);
		getMenu().add(interpretSelCSAction);

		InterpretAction interpretSelMOAction = new InterpretAction(
				InterpretAction.INTERPRET_SEL_MO);
		moSelectionNeeded.add(interpretSelMOAction);
		interpretationActions.add(interpretSelMOAction);
		getMenu().add(interpretSelMOAction);

		InterpretAction interpretAllAction = new InterpretAction(
				InterpretAction.INTERPRET_ALL);
		interpretationActions.add(interpretAllAction);
		getMenu().add(interpretAllAction);

		getMenu().add(new Separator());

		InterpretAction clearSelAction = new InterpretAction(
				InterpretAction.CLEAR_SEL);
		moSelectionNeeded.add(clearSelAction);
		csSelectionNeeded.add(clearSelAction);
		getMenu().add(clearSelAction);

		InterpretAction clearSelCSAction = new InterpretAction(
				InterpretAction.CLEAR_SEL_CS);
		csSelectionNeeded.add(clearSelCSAction);
		getMenu().add(clearSelCSAction);

		InterpretAction clearSelMOAction = new InterpretAction(
				InterpretAction.CLEAR_SEL_MO);
		moSelectionNeeded.add(clearSelMOAction);
		getMenu().add(clearSelMOAction);

		InterpretAction clearAllAction = new InterpretAction(
				InterpretAction.CLEAR_ALL);
		getMenu().add(clearAllAction);

		getMenu().add(new Separator());

		if (remResultAction == null)
			remResultAction = new InterpretAction(InterpretAction.REMOVE_SEL);
		getMenu().add(remResultAction);

		getMenu().add(new Separator());

		Action addVar = new Action() {
			public void run() {
				Dialog vsd = new AddVariableDialog(viewer.getControl()
						.getShell());
				vsd.open();
			}
		};
		addVar.setText("Add var to environment");
		getMenu().add(addVar);

		getViewSite().getActionBars().updateActionBars();
	}

	/**
	 * Refresh view.
	 */
	private void refreshView() {
		for (int i = 0, n = viewer.getTable().getColumnCount(); i < n; i++) {
			viewer.getTable().getColumn(i).pack();
		}
		viewer.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.event.IInterpreterRegistryListener#interpretationFinished(tudresden.ocl20.interpreter.event.InterpreterRegistryEvent)
	 */
	public void interpretationFinished(InterpreterRegistryEvent e) {
		refreshView();
	}
}