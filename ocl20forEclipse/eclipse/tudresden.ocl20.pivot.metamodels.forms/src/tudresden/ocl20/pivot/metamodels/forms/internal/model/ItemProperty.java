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

import org.apache.log4j.Logger;
import org.emftext.language.forms.Item;

import tudresden.ocl20.pivot.metamodels.forms.FormsMetaModelPlugin;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for
 * {@link Item}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ItemProperty extends AbstractProperty implements Property {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = FormsMetaModelPlugin
			.getLogger(ItemProperty.class);

	/** The adapted {@link Item}. */
	private Item item;

	/** The owning {@link Type} of this {@link ItemProperty}. */
	private Type owningType;

	/**
	 * <p>
	 * Creates a new {@link ItemProperty} instance.
	 * </p>
	 * 
	 * @param item
	 *            The adapted {@link Item}.
	 * @param owningType
	 *            The owning {@link Type} of this {@link ItemProperty}.
	 */
	public ItemProperty(Item item, Type owningType) {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ItemProperty(";
			msg += "item = " + item;
			msg += ", owningType = " + owningType;
			msg += ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Initialize adapted feature. */
		this.item = item;
		this.owningType = owningType;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ItemProperty(Item, Type) - exit";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
	 */
	@Override
	public String getName() {

		/* Returning the text without white spaces and points. */
		return this.item.getText().replace(" ", "").replace(".", "");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
	 */
	@Override
	public Type getOwningType() {

		return this.owningType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
	 */
	@Override
	public Type getType() {

		return FormsAdapterFactory.INSTANCE.createType(this.item.getItemType(),
				this.owningType.getNamespace(), this.item.getText());
	}
}