/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the OCL parser of the Dresden OCL2 for Eclipse.

    The OCL parser is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The OCL parser is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the OCL parser.  If not, see <http://www.gnu.org/licenses/>.
.
 */

package tudresden.ocl20.pivot.ocl2parser.internal;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IModelFactory;
import tudresden.ocl20.pivot.modelbus.model.ITypeResolver;
import tudresden.ocl20.pivot.modelbus.model.exception.TypeNotFoundException;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

public class Environment {

	protected List<NamedElement> namedElements;
	protected Environment parent;
	protected IModel model;
	protected Type result;
	protected Type self;
	// protected List<Variable> variables;
	protected List<Variable> implicitVariables;
	protected List<Variable> explicitVariables;
	protected Namespace namespace;
	protected IModelFactory expFactory;
	protected ITypeResolver typeResolver;
	protected Type contextualClassifier = null;
	protected ConstrainableElement context = null;
	protected boolean specialOclOperation = false;
	protected Type sourceType = null;

	protected boolean pathNameType = false;

	public Environment(IModel model) {

		namedElements = new ArrayList<NamedElement>();
		// tempVariables = new ArrayList<Variable>();
		implicitVariables = new ArrayList<Variable>();
		explicitVariables = new ArrayList<Variable>();
		expFactory = model.getFactory();
		this.model = model;
		typeResolver = model.getTypeResolver();
	}

	/**
	 * Looks for the operation with the <i>name</i> and the parameters
	 * <i>params</i>. The search is being performed with including the parent
	 * environments. If in no environment of this chain the operation is found
	 * null will be returned, otherwise the operation.
	 * 
	 * @param name
	 *          the name of the operation to be looking for
	 * @param params
	 *          the parameters of the operation
	 * @return the operation if any is found, otherwise null
	 */
	/*
	 * public Operation lookupOperation(String name, List<Type> params) {
	 * Operation oper = self.lookupOperation(name, params); if (oper != null)
	 * return oper; if (parent != null) return parent.lookupOperation(name,
	 * params); return null; }
	 */

	/**
	 * Looks for a type with the given path name. The assumption is that the last
	 * element of the path name denotes a type. If the type is not found or the
	 * path name is ambiguous then the return value is null. Otherwise the type
	 * will be returned.
	 * 
	 * @param pathName
	 *          the path name to be look up for
	 * @return the type that was found or null if no type is found or the path is
	 *         ambiguous
	 */
	public Type lookupType(List<String> pathName) {

		Type type = null;

		try {
			type = typeResolver.findType(pathName);
		}

		catch (ModelAccessException e) {
			return null;
			/*
			 * logger.error("findType(pathName=" + pathName + ")", e);
			 * //$NON-NLS-1$//$NON-NLS-2$ throw new
			 * ParseRuntimeException("An error occured when accessing model '"
			 * //$NON-NLS-1$ + getModel().getDisplayName() + "'.", e); //$NON-NLS-1$
			 */
		}

		catch (TypeNotFoundException e) {
			return null;
			/*
			 * logger.error("findType(pathName=" + pathName + ")", e); //$NON-NLS-1$
			 * //$NON-NLS-2$ throw new ParseRuntimeException( "Failed to lookup type "
			 * + pathName + ".", e); //$NON-NLS-1$//$NON-NLS-2$
			 */
		} catch (ModelBusException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		}

		return type;
	}

	/**
	 * Looks for a property that is denote by the path name. The assumption is
	 * that the last element of the path name is the property and the element
	 * before last is a type. If no property is found, null will be returned, also
	 * when the property is ambiguous. Otherwise the property will be returned.
	 * 
	 * @param pathName
	 *          the path name to be look up for
	 * @return the property or null if no property with this path name exists or
	 *         the path name is ambiguous
	 */
	public Property lookupProperty(List<String> pathName) {

		/*
		 * if (logger.isDebugEnabled()) { logger.debug("findProperty(pathName=" +
		 * pathName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ }
		 */

		if (pathName.size() == 0)
			return null;

		Type contextualType;
		Property property;

		// find the contextual type
		contextualType = findContextualType(pathName);

		if (contextualType == null)
			return null;

		String propertyName = pathName.get(pathName.size() - 1);

		// lookup the property
		property = contextualType.lookupProperty(propertyName);

		/*
		 * if (logger.isDebugEnabled()) {
		 * logger.debug("findProperty() - exit - return value=" + property);
		 * //$NON-NLS-1$ }
		 */

		return property;
	}

	/**
	 * Looks for an operation with the given path name. The assumption is that the
	 * last element of the path name denotes an operation name. And the element
	 * before last denotes a type. The operation has parameters and their types
	 * are given by the list <i>parameterTypes</i>. That means that the operation
	 * is not only search by their name but also by the parameters. If an
	 * operation with the given parameters is found, this operation will be
	 * returned. If the operation path name is ambiguous or no operation exists
	 * then null will be returned.
	 * 
	 * @param pathName
	 *          the path name to be looking for
	 * @param parameterTypes
	 *          the list of parameter types that the operation must have
	 * @return the operation or null if the operation is not found or the path
	 *         name is ambiguous
	 */
	/*
	 * public Operation lookupOperation(List<String> pathName, List<Type>
	 * parameterTypes) { Operation operation = null; // find the contextual type
	 * Type contextualType = findContextualType(pathName); // the operation name
	 * is the last element of the path name String operationName =
	 * pathName.get(pathName.size()-1); // lookup the operation operation =
	 * contextualType.lookupOperation(operationName, parameterTypes); return
	 * operation; }
	 */

	/**
	 * Finds the contextual classifier in the path name. The assumption is that
	 * the path name last element is an operation or attribute name. So the
	 * element before last must be a type. This method looks for this type. If it
	 * is found, it will be returned. If the path name for this type is ambiguous,
	 * null will be returned. If the type is not found, null will also be
	 * returned.
	 * 
	 * @param pathName
	 *          the path name to be looking for the type
	 * @return the type or null if the type doesn't exists or the path name for
	 *         that type is ambiguous
	 */
	protected Type findContextualType(List<String> pathName) {

		// lookup the type
		Type contextualType = lookupType(pathName.subList(0, pathName.size() - 1));

		/*
		 * if (contextualType == null) { throw new IllegalArgumentException(
		 * "Unable to find the contextual type for constrained element " +
		 * pathName); //$NON-NLS-1$ }
		 */

		return contextualType;
	}

	/**
	 * Creates a nested environment. That means a new environment is created with
	 * <i>this</i> as parent. The model is the same, but the new environment has
	 * no other elements.
	 * 
	 * @return
	 */
	public Environment nestedEnvironment() {

		Environment env = new Environment(model);
		env.setPredecessor(this);
		return env;
	}

	/**
	 * Looks for an implicit property. To do this we search in the implicit
	 * variables. If the type of one variable know this property, we return the
	 * variable. If this failed, we search in the parent, if one exists. Otherwise
	 * we return null.
	 * 
	 * @param name
	 *          the name of the property to be searched
	 * @return the variable that contains the type that holds the property or
	 *         null, if no property or variable with this name exists.
	 */
	public Variable lookupImplicitProperty(String name) {

		Property prop = null;

		for (Variable var : implicitVariables) {
			Type varType = var.getType();
			prop = varType.lookupProperty(name);
			if (prop != null)
				return var;
		}

		/*
		 * for(Variable var : implicitVariables) { String varName = var.getName();
		 * if (varName.equals(name)) return var; }
		 */

		/*
		 * if (self != null) { prop = self.lookupProperty(name); if (prop != null)
		 * return prop; }
		 */

		if (parent != null)
			return parent.lookupImplicitProperty(name);

		return null;
	}

	/**
	 * Looks for an implicit variable that contains the operation that is given by
	 * the <code>name</code> and parameters <code>params</code> The search goes
	 * through the implicit variables. If an operation is found that corresponds
	 * to a type of the implicit variables, then this variable will be returned.
	 * Otherwise we will look up in the parent, if one exists. If no parent exists
	 * we will returned null.
	 * 
	 * @param name
	 *          the name of the operation to be searching for
	 * @param params
	 *          the ocl expression that forms the parameters
	 * @return the variable if any is found, otherwise null
	 */
	public Variable lookupImplicitOperation(String name,
			List<OclExpression> params) {

		Operation op = null;

		/*
		 * Convert the list of the ocl expression in a list of types. Because the
		 * lookup operation if type expect a list of types.
		 */
		List<Type> typeList = new ArrayList<Type>();
		for (OclExpression expression : params) {
			typeList.add(expression.getType());
		}

		/*
		 * Search the implicit variables for the operation.
		 */
		for (Variable var : implicitVariables) {
			Type varType = var.getType();
			op = varType.lookupOperation(name, typeList);
			if (op != null)
				return var;
		}

		/*
		 * We must lookup in the contextual classifier of the environment for the
		 * operation. This is used by the iterator expression.
		 */
		/*
		 * if (this.contextualClassifier != null) { Operation
		 * foundClassifierOperation = null; foundClassifierOperation =
		 * contextualClassifier.lookupOperation(name, typeList); if
		 * (foundClassifierOperation != null) return foundClassifierOperation; }
		 */

		// Search the self type for the operation if a self type exists.
		/*
		 * if (self != null) { op = self.lookupOperation(name, typeList); if (op !=
		 * null) return op; }
		 */

		// Search the parent for the operation if one parent exists.
		if (parent != null)
			return parent.lookupImplicitOperation(name, params);

		// If definitively no operation exists then return null.
		return null;
	}

	/**
	 * The given <i>operation</i> will be query for its owning type. If no owning
	 * type exists, it is a little bit strange, but the method will returned null.
	 * If an owning type exists, this will be packed into a variable. This
	 * variable has no init expression and the name beomes "".
	 * 
	 * @param operation
	 *          the operation to be query for the owning type
	 * @return a variable with the empty name "" and the owning type of the
	 *         operation, or null if no operation or owning type exists
	 */
	/*
	 * public Variable lookupImplicitSourceForOperation(Operation operation) {
	 * Type operationType = operation.getOwningType(); if (operationType == null)
	 * return null; Variable var = expFactory.createVariable(operation.getName() +
	 * "$AddedVariable", operationType, null); return var; }
	 */

	/**
	 * Looks for a namespace that is given through the parameter <i>pathName</i>.
	 * If the namespace is found, it will be returned, otherwise null.
	 * 
	 * @param pathName
	 *          the path name to be looked up for
	 * @return the namespace or null, if the namespace is not found
	 */
	public Namespace lookupNamespace(List<String> pathName) {

		try {
			return model.findNamespace(pathName);
		} catch (ModelAccessException e) {
			return null;
		}
	}

	/**
	 * Sets the parent of the environment.
	 * 
	 * @param env
	 *          the parent to be set
	 */
	public void setPredecessor(Environment env) {

		this.parent = env;
	}

	/**
	 * Sets the namespace.
	 * 
	 * @param ns
	 *          the namespace to be set
	 */
	public void setNamespace(Namespace ns) {

		namespace = ns;
	}

	/**
	 * Returns the namespace of the environment.
	 * 
	 * @return namespace of the environment
	 */
	public Namespace getNamespace() {

		return namespace;
	}

	/**
	 * Adds a variable to the environment. If this variable already exists, the
	 * variable will not added to the environment and the return value will be
	 * false. If the variable doesn't already exists in the environment, it will
	 * added to the environment and the return value will be true.
	 * 
	 * @param var
	 *          the variable to be added
	 * @return true if the variable was added, otherwise false
	 */
	/*
	 * public boolean addImplicitVariable(Variable var) { if
	 * (implicitVariables.contains(var)) return false; implicitVariables.add(var);
	 * return true; }
	 */

	/**
	 * Returns the expression factory of this environment.
	 * 
	 * @return the expression factory
	 */
	public IModelFactory getExpFactory() {

		return expFactory;
	}

	/**
	 * Returns the ocl library.
	 * 
	 * @return the ocl library
	 */
	public OclLibrary getOclLibrary() {

		return ModelBusPlugin.getOclLibraryProvider().getOclLibrary();
	}

	/**
	 * Adds an implicit variable to the list of the implicit variables. An
	 * implicit variable is build in the computation of the iterator/iterate
	 * expression. This variables are used to find an implicit operation or
	 * property. In the OCL-specification there is method <i>addElement</i> with
	 * the parameter <i>Imp</i> that denotes whether the element should be
	 * searched for an implicit operation or attribute. In this environment there
	 * are two methods: this method <i>addImplicitVariable</i> and
	 * <i>addVariable</i>. The first one is for the implicit variables and the
	 * second for the variables that are defined in the let expressions. Note that
	 * in the implicit search methods the normal variables will not be searched,
	 * but the implicit variables. If the variable <i>var</i> already exists in
	 * the list, nothing happens.
	 * 
	 * @param var
	 *          the variable to add to the implicit variables
	 */
	public boolean addImplicitVariable(Variable var) {

		if (implicitVariables.contains(var))
			return false;
		implicitVariables.add(var);

		return true;
	}

	/**
	 * Sets the self variable in the environment.
	 * 
	 * @param type
	 *          the type that becomes the self variable.
	 */
	/*
	 * public void setSelf(Type type) { self = type; }
	 */

	/**
	 * Returns the self type.
	 * 
	 * @return the self type
	 */
	/*
	 * public Type getSelf() { return self; }
	 */

	/**
	 * Sets the context of the environment.
	 * 
	 * @param con
	 *          the context to be set
	 */
	public void setContext(ConstrainableElement con) {

		context = con;
	}

	/**
	 * Returns the context of the environment.
	 * 
	 * @return the context of the environment
	 */
	public ConstrainableElement getContext() {

		return context;
	}

	/**
	 * Marks the environment as special ocl operation. This is set if one the
	 * operations 'oclIsKindOf', 'oclIsTypeOf' or 'oclAsType' is recognized. If
	 * so, we must notice this to compute a variable expression that holds the
	 * type.
	 * 
	 * @param value
	 *          the value to be set
	 */
	public void setSpecialOclOperation(boolean value) {

		specialOclOperation = value;
	}

	/**
	 * Returns whether the special operation flag is set or not.
	 * 
	 * @return the special flag
	 */
	public boolean isSpecialOclOperation() {

		return specialOclOperation;
	}

	public Type getSourceType() {

		return sourceType;
	}

	public void setSourceType(Type sourceType) {

		this.sourceType = sourceType;
	}

	/**
	 * Returns the self variable.
	 * 
	 * @return the self variable
	 */
	public Variable getSelfVariable() {

		return lookupExplicitVariable("self");
	}

	/**
	 * This method adds an explicit variable to the current environment. If the
	 * variable already exists then the method return false and the variable is
	 * not added twice. If the variable doesn't exist then the variable will be
	 * added and true will return.
	 * 
	 * @param var
	 *          the variable to be added
	 * @return true if the variable doesn't already exist in the environment,
	 *         otherwise false
	 */
	public boolean addExplicitVariable(Variable var) {

		if (explicitVariables.contains(var))
			return false;

		explicitVariables.add(var);

		return true;
	}

	/**
	 * This methods looks up for an explicit variable with the given
	 * <code>name</code>. If the variable is not found in the current environment,
	 * the search will continue in the parent environment if one exist. If no
	 * parent environment exist and the variable was not found, null will be
	 * returned.
	 * 
	 * @param name
	 *          the name of the variable to be searched for
	 * @return the variable if one is found in the environment chain, otherwise
	 *         false
	 */
	public Variable lookupExplicitVariable(String name) {

		for (Variable var : explicitVariables) {
			if (var.getName().equals(name))
				return var;
		}

		if (parent != null)
			return parent.lookupExplicitVariable(name);

		return null;
	}
}
