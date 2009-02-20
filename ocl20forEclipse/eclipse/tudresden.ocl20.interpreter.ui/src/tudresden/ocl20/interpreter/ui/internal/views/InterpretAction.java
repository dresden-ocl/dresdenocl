/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import tudresden.ocl20.interpreter.IEnvironment;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.interpreter.internal.Environment;
import tudresden.ocl20.interpreter.internal.OclInterpreter;
import tudresden.ocl20.interpreter.ui.internal.OclInterpreterUIMessages;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;

/**
 * <p>
 * {@link InterpretAction} represents actions for interpretation, preparation
 * and removing results from the {@link InterpreterView}.
 * </p>
 * 
 * @author Claas Wilke
 */
class InterpretAction extends Action implements IAction {

	/**
	 * Clears all {@link Constraint}s for selected {@link IModelObject}s.
	 */
	public static final int CLEAR_ALL_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS = 5;

	/**
	 * Clears all {@link Constraint}s for all {@link IModelObject}s.
	 */
	public static final int CLEAR_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS = 7;

	/**
	 * Clears selected {@link Constraint}s for all {@link IModelObject}s.
	 */
	public static final int CLEAR_SELECTED_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS = 6;

	/**
	 * Clears selected {@link Constraint}s for selected {@link IModelObject}s.
	 */
	public static final int CLEAR_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS = 4;

	/**
	 * Interprets all {@link Constraint}s for all {@link IModelObject}s.
	 */
	public static final int INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS = 3;

	/**
	 * Interprets all {@link Constraint}s for selected {@link IModelObject}s.
	 */
	public static final int INTERPRET_ALL_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS = 1;

	/**
	 * Interprets selected {@link Constraint}s for all {@link IModelObject}s
	 */
	public static final int INTERPRET_SELECTED_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS = 2;

	/**
	 * Interprets selected {@link Constraint}s for selected {@link IModelObject}
	 * s.
	 */
	public static final int INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS = 0;

	/**
	 * Prepares all {@link Constraint}s.
	 */
	public static final int PREPARE_ALL_CONSTRAINTS = 10;

	/**
	 * Prepares selected {@link Constraint}s.
	 */
	public static final int PREPARE_SELECTED_CONSTRAINTS = 9;

	/**
	 * Removes selected results.
	 */
	public static final int REMOVE_SELECTED_CONSTRAINTS = 8;

	/** The action type of this {@link InterpretAction}. */
	private int actionType;

	/**
	 * The {@link Constraint}s which shall be used by this
	 * {@link InterpretAction}.
	 */
	private Set<Constraint> constraints;

	/**
	 * The {@link ConstraintKind}s which shall be used by this
	 * {@link InterpretAction}.
	 */
	private List<ConstraintKind> kinds;

	/**
	 * The {@link IModelObject}s which shall be used by this
	 * {@link InterpretAction}.
	 */
	private Set<IModelObject> modelObjects;

	/** The {@link Shell} used to display error messages. */
	private Shell parentShell;

	/** Specifies if a cache shall be used for this {@link InterpretAction}. */
	private boolean useCache = false;

	/**
	 * <p>
	 * A helper method which opens a {@link MessageDialog} to show a given
	 * message.
	 * </p>
	 * 
	 * @param message
	 *            The message which shall be shown.
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(this.parentShell, "Interpreter Results",
				message);
	}

	/**
	 * <p>
	 * Instantiates a new {@link InterpretAction}.
	 * </p>
	 * 
	 * @param actionType
	 *            The action type which shall be performed by this
	 *            {@link InterpretAction}.
	 */
	public InterpretAction(int actionType, Shell parentShell) {
		super(null, IAction.AS_PUSH_BUTTON);

		this.actionType = actionType;

		this.setText(toString());

		this.parentShell = parentShell;

		this.kinds = new ArrayList<ConstraintKind>();

		/*
		 * Initialize the constraint kinds used by this InterpretAction
		 * depending on its given action type.
		 */
		switch (actionType) {
		case INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
		case INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
		case INTERPRET_SELECTED_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
		case INTERPRET_ALL_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS: {

			kinds.add(ConstraintKind.INVARIANT);
			kinds.add(ConstraintKind.PRECONDITION);
			kinds.add(ConstraintKind.POSTCONDITION);
			break;
		}
		case PREPARE_ALL_CONSTRAINTS:
		case PREPARE_SELECTED_CONSTRAINTS: {

			kinds.add(ConstraintKind.BODY);
			kinds.add(ConstraintKind.DEFINITION);
			kinds.add(ConstraintKind.POSTCONDITION);
			kinds.add(ConstraintKind.DERIVED);
			kinds.add(ConstraintKind.INITIAL);

			break;
		}
		}
	}

	/**
	 * <p>
	 * Adds a {@link ConstraintKind} which shall be used to compute the results
	 * of this {@link InterpretAction}.
	 * </p>
	 * 
	 * @param kind
	 *            The {@link ConstraintKind} which shall be added.
	 */
	public void addKind(ConstraintKind kind) {
		if (!this.kinds.contains(kind)) {
			this.kinds.add(kind);
		}
		// no else.
	}

	/**
	 * <p>
	 * Adds a {@link Constraint} which shall be used to compute the results of
	 * this {@link InterpretAction}.
	 * </p>
	 * 
	 * @param kind
	 *            The {@link Constraint} which shall be added.
	 */
	public void addSelectedConstraints(Constraint c) {

		if (this.constraints == null) {
			this.constraints = new HashSet<Constraint>();
		}
		// no else.

		this.constraints.add(c);
	}

	/**
	 * <p>
	 * Adds a {@link IModelObject} which shall be used to compute the results of
	 * this {@link InterpretAction}.
	 * </p>
	 * 
	 * @param kind
	 *            The {@link IModelObject} which shall be added.
	 */
	public void addSelectedModelObjects(List<IModelObject> mo) {

		if (this.modelObjects == null) {
			this.modelObjects = new HashSet<IModelObject>();
		}
		// no else.

		this.modelObjects.addAll(mo);
	}

	/**
	 * <p>
	 * Clears the {@link List} of used {@link Constraint}s to compute the result
	 * of this {@link InterpretAction}.
	 * </p>
	 */
	public void clearSelectedConstraints() {
		this.constraints = null;
	}

	/**
	 * <p>
	 * Clears the {@link List} of used {@link IModelObject}s to compute the
	 * result of this {@link InterpretAction}.
	 * </p>
	 */
	public void clearSelectedModelObjects() {
		this.modelObjects = null;
	}

	/**
	 * Removes a {@link ConstraintKind} which shall not be used to compute the
	 * results of this {@link InterpretAction}.</p>
	 * 
	 * @param kind
	 *            The {@link ConstraintKind} which shall be removed.
	 */
	public void removeKind(ConstraintKind kind) {
		this.kinds.remove(kind);
	}

	/**
	 * <p>
	 * Enables that this {@link InterpretAction} will use a cache to compute its
	 * results.
	 * </p>
	 * 
	 * @param enabled
	 *            If true, a cache will be used.
	 */
	public void setUseCache(boolean enabled) {
		this.useCache = enabled;
	}

	/**
	 * <p>
	 * Remove results for selected {@link IModelObject}s and selected
	 * {@link Constraint}s.
	 * </p>
	 * 
	 * @param modelObjects
	 *            The {@link IModelObject}s for which results shall be removed
	 *            or <code>null</code> if all {@link IModelObject}s shall be
	 *            used.
	 * @param constraints
	 *            The {@link Constraint}s for which results shall be removed or
	 *            <code>null</code> if all {@link Constraint}s shall be used.
	 */
	private void clearSelection(Set<IModelObject> modelObjects,
			Set<Constraint> constraints) {
		IModel activeModel;
		IModelInstance activeModelInstance;

		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
		activeModelInstance = null;

		/* Check if a model has been set. */
		if (activeModel != null) {

			activeModelInstance = ModelBusPlugin.getModelInstanceRegistry()
					.getActiveModelInstance(activeModel);

			if (activeModelInstance != null) {

				IEnvironment globalEnv;
				IOclInterpreter interpreter;

				Set<IModelObject> usedModelObjects;
				Set<Constraint> usedConstraints;

				globalEnv = Environment.getGlobalEnvironment();
				globalEnv.setModelInstance(activeModelInstance);

				interpreter = new OclInterpreter(Environment
						.getGlobalEnvironment());

				usedModelObjects = null;

				if (modelObjects != null) {
					usedModelObjects = modelObjects;
				}

				else {
					usedModelObjects = new HashSet<IModelObject>(
							activeModelInstance.getObjects());
				}

				usedConstraints = constraints;

				if (usedConstraints == null) {
					interpreter.clearResults(new ArrayList<IModelObject>(
							usedModelObjects));
				}

				else {
					interpreter.removeResults(new ArrayList<IModelObject>(
							usedModelObjects), new ArrayList<Constraint>(
							usedConstraints));
				}
			}

			else {
				showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
						+ activeModel);
			}
		}

		else {
			showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModel);
		}
	}

	/**
	 * <p>
	 * Returns all {@link Constraint}s contained in a given {@link IModel}.
	 * 
	 * @param aModel
	 *            The {@link IModel} which {@link Constraint}s shall be
	 *            returned.
	 * @return A {@link Set} of {@link Constraint}s.
	 */
	private Set<Constraint> getAllConstraintsForModel(IModel aModel) {

		Set<Constraint> result;

		Namespace rootNamespace;

		try {
			rootNamespace = aModel.getRootNamespace();

			result = this.getAllConstraintsForNamespace(rootNamespace);
		}

		catch (ModelAccessException e) {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * Returns all {@link Constraint}s contained in a given {@link Namespace}.
	 * 
	 * @param aNamespace
	 *            The {@link Namespace} which {@link Constraint}s shall be
	 *            returned.
	 * @return A {@link Set} of {@link Constraint}s.
	 */
	private Set<Constraint> getAllConstraintsForNamespace(Namespace aNamespace) {

		Set<Constraint> result;

		List<Namespace> nestedNamespaces;

		result = new HashSet<Constraint>();

		nestedNamespaces = aNamespace.getNestedNamespace();

		/* Collect the constraints of all nested name spaces. */
		for (Namespace aNestedNamespace : nestedNamespaces) {
			result.addAll(this.getAllConstraintsForNamespace(aNestedNamespace));
		}

		/* Collect all constraints of the given name space. */

		result.addAll(aNamespace.getOwnedRule());

		return result;
	}

	/**
	 * <p>
	 * Interpret selected {@link IModelObject}s and selected {@link Constraint}
	 * s.
	 * </p>
	 * 
	 * @param modelObjects
	 *            The {@link IModelObject}s that shall be interpreted or
	 *            <code>null</code> if all {@link IModelObject}s shall be used.
	 * @param constraints
	 *            The {@link Constraint}s that shall be interpreted or
	 *            <code>null</code> if all {@link Constraint}s shall be used.
	 */
	private void interpretSelection(Set<IModelObject> modelObjects,
			Set<Constraint> constraints) {

		IModel activeModel;
		IModelInstance activeModelInstance;

		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
		activeModelInstance = null;

		if (activeModel != null) {
			activeModelInstance = ModelBusPlugin.getModelInstanceRegistry()
					.getActiveModelInstance(activeModel);

			if (activeModelInstance != null) {

				IEnvironment gloabelEnv;
				IOclInterpreter interpreter;

				Set<IModelObject> usedModelObjects;
				Set<Constraint> usedConstraints;

				gloabelEnv = Environment.getGlobalEnvironment();
				gloabelEnv.setModelInstance(activeModelInstance);

				interpreter = new OclInterpreter(Environment
						.getGlobalEnvironment());
				interpreter.setUseCache(this.useCache);

				usedModelObjects = null;

				if (modelObjects != null) {
					usedModelObjects = modelObjects;
				}

				else {
					usedModelObjects = new HashSet<IModelObject>(
							activeModelInstance.getObjects());
				}

				usedConstraints = null;

				if (constraints != null) {
					usedConstraints = constraints;
				} else {
					usedConstraints = this
							.getAllConstraintsForModel(activeModel);
				}

				/*
				 * Iterate through the model objects and constraints and compute
				 * their results.
				 */
				for (IModelObject aModelObject : usedModelObjects) {
					for (Constraint aConstraint : usedConstraints) {

						if (this.kinds.contains(aConstraint.getKind())) {

							NamedElement constrainedElem;

							String aConstrainedElemName;
							String aModelObjectName;

							constrainedElem = (NamedElement) aConstraint
									.getConstrainedElement().get(0);

							aConstrainedElemName = constrainedElem
									.getQualifiedName();

							aModelObjectName = aModelObject
									.getQualifiedNameString();

							if (aConstrainedElemName.equals(aModelObjectName)) {
								interpreter
										.interpret(aConstraint, aModelObject);
							}

							else if (constrainedElem instanceof Operation) {

								String operationTypeName;

								operationTypeName = ((Operation) constrainedElem)
										.getOwningType().getQualifiedName();

								if (operationTypeName.equals(aModelObjectName)) {
									interpreter.interpret(aConstraint,
											aModelObject);
								}
								// no else.
							}
						}
					}
					// end for.
				}
				// end for.
			}

			else {
				showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
						+ activeModel);
			}
		}

		else {
			showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModel);
		}
	}

	/**
	 * <p>
	 * Prepares a {@link Set} of selected {@link Constraint} if there
	 * {@link ConstraintKind} is valid to be prepared. {@link ConstraintKind}
	 * which can be prepared are definitions, body expressions, initial and
	 * derived values and postconditions.
	 * </p>
	 * 
	 * @param constraints
	 *            The {@link Constraint}s that shall be prepared or
	 *            <code>null</code> if all {@link Constraint}s shall be used.
	 */
	private void prepareSelection(Set<Constraint> constraints) {

		IModel activeModel;
		IModelInstance activeModelInstance;

		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
		activeModelInstance = null;

		/*
		 * If the active model has been set, try to get the active model
		 * instance.
		 */
		if (activeModel != null) {

			activeModelInstance = ModelBusPlugin.getModelInstanceRegistry()
					.getActiveModelInstance(activeModel);

			/*
			 * If the active model instance has been set, continue with
			 * preparation.
			 */
			if (activeModelInstance != null) {

				IEnvironment globalEnv;
				IOclInterpreter interpreter;

				Set<Constraint> usedConstraints;
				Set<IModelObject> usedModelObjects;

				globalEnv = Environment.getGlobalEnvironment();
				globalEnv.setModelInstance(activeModelInstance);

				interpreter = new OclInterpreter(Environment
						.getGlobalEnvironment());

				usedConstraints = null;

				/* Use all given constraints. */
				if (constraints != null) {
					usedConstraints = constraints;
				}

				/* Or compute all constraints from the active model. */
				else {
					usedConstraints = getAllConstraintsForModel(activeModel);
				}

				usedModelObjects = new HashSet<IModelObject>(
						activeModelInstance.getObjects());

				/* Iterate through the constraints and prepare them. */
				for (Constraint aConstraint : usedConstraints) {

					ConstraintKind aKind;

					aKind = aConstraint.getKind();

					/* Check if the constraint is a definition constraint. */
					if (aKind.equals(ConstraintKind.DEFINITION)) {

						/*
						 * Check if definition constraints shall be prepared.
						 */
						if (kinds.contains(ConstraintKind.DEFINITION)) {

							String featureName;
							String definedFeature;

							featureName = aConstraint.getDefinedFeature()
									.getName();

							definedFeature = ((NamedElement) aConstraint
									.getConstrainedElement().get(0))
									.getQualifiedName()
									+ "::" + featureName;

							/*
							 * Add the defined feature to the global
							 * environment.
							 */
							globalEnv
									.addConstraint(definedFeature, aConstraint);
						}
						// no else.
					}

					/* Else check if the constraint is a postcondition. */
					else if (aKind.equals(ConstraintKind.POSTCONDITION)) {

						/* Check if postconditions shall be prepared. */
						if (kinds.contains(ConstraintKind.POSTCONDITION)) {

							/* Iterate through all model objects. */
							for (IModelObject aModelObject : usedModelObjects) {

								List<String> aQualifiedName;

								String qualifiedNameWithoutOperation;
								Iterator<String> qualidiedNameIt;

								/*
								 * Get the qualified name and split it into
								 * packages.
								 */
								aQualifiedName = new ArrayList<String>(
										Arrays
												.asList(((NamedElement) aConstraint
														.getConstrainedElement()
														.get(0))
														.getQualifiedName()
														.split("::")));

								qualifiedNameWithoutOperation = "";

								/*
								 * Check if the first element is not an
								 * operation.
								 */
								if (!aQualifiedName.get(0).contains("(")) {
									qualifiedNameWithoutOperation = aQualifiedName
											.remove(0);
								}
								// no else.

								/*
								 * Iterate through the qualified name until the
								 * first operation.
								 */
								qualidiedNameIt = aQualifiedName.iterator();

								while (qualidiedNameIt.hasNext()) {

									String anElement = qualidiedNameIt.next();

									if (!anElement.contains("(")) {
										qualifiedNameWithoutOperation = qualifiedNameWithoutOperation
												+ "::" + anElement;
									} else {
										break;
									}
								}
								// end while.

								/*
								 * If the constructed name equals the qualified
								 * name of the object, prepare the object.
								 */
								if (qualifiedNameWithoutOperation
										.equals(aModelObject
												.getQualifiedNameString())) {
									interpreter.prepare(aConstraint,
											aModelObject);
								}
								// no else.
							}
							// end for.
						}
						// no else.
					}

					/*
					 * Else check if the constraint is a body definition, an
					 * initial or a derived value.
					 */
					else if (aKind.equals(ConstraintKind.BODY)
							|| aKind.equals(ConstraintKind.INITIAL)
							|| aKind.equals(ConstraintKind.DERIVED)) {

						interpreter.prepare(aConstraint, null);
					}
					// end for.
				}
				// no else.
			}

			/* Else show an error message. */
			else {
				showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
						+ activeModel);
			}
		}

		/* Else show an error message. */
		else {
			showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModel);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {

		/* Decide what its to do depending on the given type of action. */
		switch (actionType) {
		case INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS: {

			if (this.modelObjects == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoModelObectSelected);
			}

			else if (this.constraints == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}

			else {
				this.interpretSelection(this.modelObjects, this.constraints);
			}

			break;
		}

		case INTERPRET_SELECTED_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS: {

			if (constraints == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}

			else {
				this.interpretSelection(null, this.constraints);
			}

			break;
		}

		case INTERPRET_ALL_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS: {

			if (this.modelObjects == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoModelObectSelected);
			}

			else {
				this.interpretSelection(this.modelObjects, null);
			}

			break;
		}

		case INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS: {
			this.interpretSelection(null, null);
			break;
		}

		case CLEAR_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
		case REMOVE_SELECTED_CONSTRAINTS: {

			if (this.modelObjects == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoModelObectSelected);
			}

			else if (this.constraints == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}

			else {
				this.clearSelection(this.modelObjects, this.constraints);
			}

			break;
		}

		case CLEAR_SELECTED_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS: {

			if (this.constraints == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}

			else {
				this.clearSelection(null, this.constraints);
			}

			break;
		}

		case CLEAR_ALL_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS: {

			if (this.modelObjects == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoModelObectSelected);
			}

			else {
				this.clearSelection(this.modelObjects, null);
			}

			break;
		}

		case CLEAR_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS: {
			this.clearSelection(null, null);
			break;
		}

		case PREPARE_SELECTED_CONSTRAINTS: {

			if (this.constraints == null) {
				this
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}

			else {
				this.prepareSelection(this.constraints);
			}

			break;
		}
		case PREPARE_ALL_CONSTRAINTS: {
			this.prepareSelection(null);
			break;
		}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		switch (this.actionType) {
		
		case INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
			result = "Interpret selected constraints for selected model objects";
			break;

		case INTERPRET_SELECTED_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
			result = "Interpret selected constraints for all model objects";
			break;

		case INTERPRET_ALL_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
			result = "Interpret all constraints for selected model objects";
			break;

		case INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
			result = "Interpret all constraints for all model objects";
			break;

		case CLEAR_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
			result = "Clear results for selected constraints for selected model objects";
			break;

		case CLEAR_SELECTED_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
			result = "Clear results for selected constraints for all model objects";
			break;

		case CLEAR_ALL_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
			result = "Clear results for all constraints for selected model objects";
			break;

		case CLEAR_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
			result = "Clear results for all constraints for all model objects";
			break;

		case REMOVE_SELECTED_CONSTRAINTS:
			result = "Remove selected results";
			break;

		case PREPARE_SELECTED_CONSTRAINTS:
			result = "Prepare selected constraints";
			break;

		case PREPARE_ALL_CONSTRAINTS:
			result = "Prepare all constraints";
			break;

		default:
			result = "No action selected";
		}
		// end switch.

		return result;
	}
}