/*
 * Copyright (C) 2010 by Claas Wilke (claas.wilke@tudresden.de)
 *
 * This file is part of the Forms Meta-Model of DresdenOCL.
 *
 * DresdenOCL is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * DresdenOCL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along 
 * with DresdenOCL. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.forms.internal.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import javax.lang.model.type.PrimitiveType;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.emftext.language.forms.Choice;
import org.emftext.language.forms.Date;
import org.emftext.language.forms.Decision;
import org.emftext.language.forms.Form;
import org.emftext.language.forms.FreeText;
import org.emftext.language.forms.Group;
import org.emftext.language.forms.Item;
import org.emftext.language.forms.ItemType;
import org.emftext.language.forms.Option;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.metamodels.forms.FormsMetaModelPlugin;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * The {@link FormsAdapterFactory} is responsible for the adaptation of Forms
 * meta-model objects to the pivot model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class FormsAdapterFactory {

	/** The Singleton instance of the {@link FormsAdapterFactory}. */
	public static FormsAdapterFactory INSTANCE = new FormsAdapterFactory();

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = FormsMetaModelPlugin
			.getLogger(FormsAdapterFactory.class);

	/**
	 * A cache for previously created adapters. It is realized as a
	 * {@link WeakHashMap} because pivot model elements which are not referenced
	 * by any {@link IModel} anymore can be cleared by the garbage collector.
	 */
	private WeakHashMap<EObject, NamedElement> myCachedAdapters;

	/**
	 * Contains adaptations for {@link PrimitiveType}s and {@link Enumeration}s.
	 * The {@link Namespace} key is used to allocate each adaptation to a
	 * {@link FormsModel}'s root {@link Namespace}. The cache is realized as a
	 * {@link WeakHashMap} because pivot model elements which are not referenced
	 * by any {@link IModel} anymore can be cleared by the garbage collector.
	 */
	private WeakHashMap<Namespace, Map<Class<?>, Type>> myCachedItemTypes;

	/**
	 * <p>
	 * Clients are not supposed to instantiate an {@link FormsAdapterFactory}.
	 * </p>
	 */
	private FormsAdapterFactory() {

		this.myCachedAdapters = new WeakHashMap<EObject, NamedElement>();
		this.myCachedItemTypes = new WeakHashMap<Namespace, Map<Class<?>, Type>>();
	}

	/**
	 * <p>
	 * Creates an {@link Enumeration} for a given {@link Choice}.
	 * </p>
	 * 
	 * @param choice
	 *            The {@link Choice} which shall be adapted.
	 * @return The created {@link Enumeration} or a {@link CollectionType}
	 *         containing the
	 *         {@link tudresden.ocl20.pivot.pivotmodel.Enumeration}.
	 */
	public Type createEnumeration(Choice choice, Namespace nestingNamespace,
			String name) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumeration(";
			msg += "choice = " + choice;
			msg += "nestingNamespace = " + nestingNamespace;
			msg += "name = " + name;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Type result;

		/* Probably use a cached result. */
		if (this.myCachedAdapters.containsKey(choice)) {
			result = (tudresden.ocl20.pivot.pivotmodel.Enumeration) this.myCachedAdapters
					.get(choice);
		}

		/* Else create the Enumeration. */
		else {
			result = new ChoiceEnumeration(choice, nestingNamespace, name);

			/* Cache the result. */
			myCachedAdapters.put(choice, result);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumeration(Choice, Namespace, String) - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		if (choice.isMultiple()) {
			result = EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
					.getOrderedSetType(result);
		}

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link Enumeration} for a given {@link Decision}.
	 * </p>
	 * 
	 * @param decision
	 *            The {@link Decision} which shall be adapted.
	 * @return The created {@link Enumeration} .
	 */
	public tudresden.ocl20.pivot.pivotmodel.Enumeration createEnumeration(
			Decision decision, Namespace nestingNamespace, String name) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumeration(";
			msg += "decision = " + decision;
			msg += "nestingNamespace = " + nestingNamespace;
			msg += "name = " + name;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		tudresden.ocl20.pivot.pivotmodel.Enumeration result;

		/* Probably use a cached result. */
		if (this.myCachedAdapters.containsKey(decision)) {
			result = (tudresden.ocl20.pivot.pivotmodel.Enumeration) this.myCachedAdapters
					.get(decision);
		}

		/* Else create the Enumeration. */
		else {
			result = new DecisionEnumeration(decision, nestingNamespace, name);

			/* Cache the result. */
			myCachedAdapters.put(decision, result);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumeration(Decision, Namespace, String) - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link EnumerationLiteral} for a given {@link Option}.
	 * </p>
	 * 
	 * @param option
	 *            The {@link Option} which shall be adapted.
	 * @param owningEnumeration
	 *            The owning
	 *            {@link tudresden.ocl20.pivot.pivotmodel.Enumeration} of the
	 *            {@link EnumerationLiteral}.
	 * 
	 * @return The created {@link EnumerationLiteral}.
	 */
	public EnumerationLiteral createEnumerationLiteral(Option option,
			tudresden.ocl20.pivot.pivotmodel.Enumeration owningEnumeration) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumerationLiteral(";
			msg += "option = " + option;
			msg += ", owningEnumeration = " + owningEnumeration;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		EnumerationLiteral result;

		/* Probably use a cached result. */
		if (this.myCachedAdapters.containsKey(option)) {
			result = (EnumerationLiteral) this.myCachedAdapters.get(option);
		}

		/* Else create the Type. */
		else {
			result = new OptionEnumerationLiteral(option, owningEnumeration);

			/* Cache the result. */
			this.myCachedAdapters.put(option, result);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEnumerationLiteral(Option, Enumeration) - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Namespace} adapter for a {@link Form}.
	 * </p>
	 * 
	 * @param form
	 *            The {@link Form} that shall be adapted.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this {@link FormNamespace}.
	 */
	public Namespace createNamespace(Form form, Namespace nestingNamespace) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNamespace(";
			msg += "form = " + form;
			msg += "nestingNamespace = " + nestingNamespace;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Namespace result;

		/* Probably use a cached result. */
		if (this.myCachedAdapters.containsKey(form)) {
			result = (Namespace) this.myCachedAdapters.get(form);
		}

		/* Else create the name space. */
		else {
			result = new FormNamespace(form, nestingNamespace);

			/* Cache the result. */
			this.myCachedAdapters.put(form, result);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNamespace(Form, Namespace) - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Property} for a given {@link Item}.
	 * </p>
	 * 
	 * @param item
	 *            The {@link Item} to be adapted.
	 * @param owningType
	 *            The owning {@link Type} of the {@link Item}.
	 * @return The adapted {@link Property}.
	 */
	public Property createProperty(Item item, Type owningType) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createProperty(";
			msg += "item = " + item;
			msg += ", owningType = " + owningType;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Property result;

		/* Probably use a cached result. */
		if (this.myCachedAdapters.containsKey(item)) {
			result = (Property) this.myCachedAdapters.get(item);
		}

		/* Else create the Type. */
		else {
			result = new ItemProperty(item, owningType);

			/* Cache the result. */
			this.myCachedAdapters.put(item, result);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createProperty(Item, Type) - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Type} for a given {@link Group}.
	 * </p>
	 * 
	 * @param eClass
	 *            The {@link Group} which shall be adapted.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this {@link FormNamespace}.
	 * @return The created {@link Type}.
	 */
	public Type createType(Group group, Namespace nestingNamespace) {

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType(";
			msg += "group = " + group;
			msg += "nestingNamespace = " + nestingNamespace;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		Type result;

		/* Probably use a cached result. */
		if (this.myCachedAdapters.containsKey(group)) {
			result = (Type) this.myCachedAdapters.get(group);
		}

		/* Else create the Type. */
		else {
			result = new GroupType(group, nestingNamespace);

			/* Cache the result. */
			this.myCachedAdapters.put(group, result);
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType(Group, Namespace) - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Type} for a given {@link ItemType}.
	 * </p>
	 * 
	 * @param itemType
	 *            The {@link ItemType} which shall be adapted.
	 * @param nestingNamespace
	 *            The nesting {@link Namespace} of this {@link Type}.
	 * @param The
	 *            name of the field belonging to the {@link ItemType}. Required
	 *            for adaptation of
	 *            {@link tudresden.ocl20.pivot.pivotmodel.Enumeration}s because
	 *            {@link Choice}s do not have their own name. Ignored otherwise.
	 * @return The created {@link Type}.
	 */
	public Type createType(ItemType itemType, Namespace nestingNamespace,
			String name) {

		Type result;

		/* Probably log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType(";
			msg += "itemType = " + itemType;
			msg += "nestingNamespace = " + nestingNamespace;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (itemType instanceof Choice) {
			result = createEnumeration((Choice) itemType, nestingNamespace,
					name);
		}

		else if (itemType instanceof Date) {
			result = createDateType((Date) itemType, nestingNamespace);
		}

		if (itemType instanceof Decision) {
			result = createEnumeration((Decision) itemType, nestingNamespace,
					name);
		}

		else if (itemType instanceof FreeText) {
			result = createFreeTextType((FreeText) itemType, nestingNamespace);
		}

		else if (itemType instanceof org.emftext.language.forms.Number) {
			result = createNumberType(
					(org.emftext.language.forms.Number) itemType,
					nestingNamespace);
		}

		else {
			LOGGER.warn("Could not adapt ItemType " + itemType.toString()
					+ " to Type");
			result = null;
		}

		/* Probably log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createType(ItemType, Namespace) - exit";
			msg += " - return value = " + result;

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Type} for a given {@link Date}.
	 * </p>
	 * 
	 * @param freeText
	 *            The {@link Date} to be adapted.
	 * @param nestingNamespace
	 *            A {@link Namespace} of the {@link Date}'s {@link FormsModel}.
	 * @return The adapted {@link Type}.
	 */
	private Type createDateType(Date date, Namespace nestingNamespace) {

		Type result;
		result = null;

		/* Primitive Types are always in the root name space. */
		while (nestingNamespace.getNestingNamespace() != null) {
			nestingNamespace = nestingNamespace.getNestingNamespace();
		}
		// end while.

		Map<Class<?>, Type> cachedAdaptedItemTypes;
		cachedAdaptedItemTypes = null;

		/* Probably use a cached result. */
		if (this.myCachedItemTypes.containsKey(nestingNamespace)) {
			cachedAdaptedItemTypes = this.myCachedItemTypes
					.get(nestingNamespace);

			result = cachedAdaptedItemTypes.get(date.getClass());
		}
		// no else.

		/* Else create an adapter. */
		if (result == null) {

			result = new DatePrimitiveType(nestingNamespace);

			/* Cache the result. */
			if (cachedAdaptedItemTypes == null) {
				cachedAdaptedItemTypes = new HashMap<Class<?>, Type>();
			}

			cachedAdaptedItemTypes.put(date.getClass(), result);
			this.myCachedItemTypes
					.put(nestingNamespace, cachedAdaptedItemTypes);
		}
		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Type} for a given {@link FreeText}.
	 * </p>
	 * 
	 * @param freeText
	 *            The {@link FreeText} to be adapted.
	 * @param nestingNamespace
	 *            A {@link Namespace} of the {@link FreeText}'s
	 *            {@link FormsModel}.
	 * @return The adapted {@link Type}.
	 */
	private Type createFreeTextType(FreeText freeText,
			Namespace nestingNamespace) {

		Type result;
		result = null;

		/* Primitive Types are always in the root name space. */
		while (nestingNamespace.getNestingNamespace() != null) {
			nestingNamespace = nestingNamespace.getNestingNamespace();
		}
		// end while.

		Map<Class<?>, Type> cachedAdaptedItemTypes;
		cachedAdaptedItemTypes = null;

		/* Probably use a cached result. */
		if (this.myCachedItemTypes.containsKey(nestingNamespace)) {
			cachedAdaptedItemTypes = this.myCachedItemTypes
					.get(nestingNamespace);

			result = cachedAdaptedItemTypes.get(freeText.getClass());
		}
		// no else.

		/* Else create an adapter. */
		if (result == null) {

			result = new FreeTextPrimitiveType(nestingNamespace);

			/* Cache the result. */
			if (cachedAdaptedItemTypes == null) {
				cachedAdaptedItemTypes = new HashMap<Class<?>, Type>();
			}

			cachedAdaptedItemTypes.put(freeText.getClass(), result);
			this.myCachedItemTypes
					.put(nestingNamespace, cachedAdaptedItemTypes);
		}
		return result;
	}

	/**
	 * <p>
	 * Creates a {@link Type} for a given {@link Number}.
	 * </p>
	 * 
	 * @param freeText
	 *            The {@link Number} to be adapted.
	 * @param nestingNamespace
	 *            A {@link Namespace} of the {@link Number}'s {@link FormsModel}
	 *            .
	 * @return The adapted {@link Type}.
	 */
	private Type createNumberType(org.emftext.language.forms.Number number,
			Namespace nestingNamespace) {

		Type result;
		result = null;

		/* Primitive Types are always in the root name space. */
		while (nestingNamespace.getNestingNamespace() != null) {
			nestingNamespace = nestingNamespace.getNestingNamespace();
		}
		// end while.

		Map<Class<?>, Type> cachedAdaptedItemTypes;
		cachedAdaptedItemTypes = null;

		/* Probably use a cached result. */
		if (this.myCachedItemTypes.containsKey(nestingNamespace)) {
			cachedAdaptedItemTypes = this.myCachedItemTypes
					.get(nestingNamespace);

			result = cachedAdaptedItemTypes.get(number.getClass());
		}
		// no else.

		/* Else create an adapter. */
		if (result == null) {

			result = new NumberPrimitiveType(nestingNamespace);

			/* Cache the result. */
			if (cachedAdaptedItemTypes == null) {
				cachedAdaptedItemTypes = new HashMap<Class<?>, Type>();
			}

			cachedAdaptedItemTypes.put(number.getClass(), result);
			this.myCachedItemTypes
					.put(nestingNamespace, cachedAdaptedItemTypes);
		}
		return result;
	}
}