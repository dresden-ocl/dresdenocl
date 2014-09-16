/*
 * Copyright (C) 2012 by Bjoern Freitag (bjoern.freitag@inf.tu-dresden.de)
 * This file is part of the CWM Meta Model of Dresden OCL2 for Eclipse. Dresden
 * OCL2 for Eclipse is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. Dresden OCL2 for Eclipse is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with Dresden
 * OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.cwmrelational.internal.model;

import org.apache.log4j.Logger;
import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.metamodels.cwmrelational.CWMMetamodelPlugin;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.base.AbstractProperty;
import orgomg.cwm.foundation.keysindexes.UniqueKey;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.PrimaryKey;

/**
 * <p>
 * An implementation of the Pivot Model {@link Property} concept for CWMrelational.
 * </p>
 * 
 * @author Bjoern Freitag
 * @generated NOT
 */
public class CWMColumn extends AbstractProperty implements Property {

	/**
	 * <p>
	 * Logger for this class
	 * </p>
	 * 
	 * @generated NOT
	 */
	private static final Logger LOGGER = CWMMetamodelPlugin
			.getLogger(CWMColumn.class);

	/** The adapted {@link Column} class. */
	private Column dslColumn;

	/**
	 * <p>
	 * The {@link CWMAdapterFactory} used to create nested elements.
	 * </p>
	 * 
	 * @generated NOT
	 */
	private CWMAdapterFactory factory;

	/**
	 * <p>
	 * Creates a new <code>CWMColumn</code> instance.
	 * </p>
	 * 
	 * @param dslColumn
	 *            the {@link Column} that is adopted by this
	 *            class
	 * @param factory
	 *            The {@link CWMAdapterFactory} used to create nested elements.
	 * 
	 * @generated
	 */
	public CWMColumn(Column dslColumn,
			CWMAdapterFactory factory) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("CWMColumn(cwmTable = " + dslColumn + "factory = " + factory + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// initialize
		this.dslColumn = dslColumn;
		this.factory = factory;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("CWMTable() - exit"); //$NON-NLS-1$
		}
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getName()
	 */
	@Override
	public String getName() {
		return this.dslColumn.getName();
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getOwningType()
	 */
	@Override
	public Type getOwningType() {
		return this.factory.createType(dslColumn.getOwner());
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.base.AbstractProperty#getType()
	 */
	@Override
	public Type getType() {
		Type elementType = this.factory.createType(dslColumn.getType());
		if (dslColumn.getType().getName().contains(" ARRAY") || dslColumn.getType().getName().contains("[]")
				|| dslColumn.getType().getName().contains("[ ]")) {
			elementType = EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getBagType(elementType);
		}
		return elementType;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isIdentifier()
	 */
	@Override
	public boolean isIdentifier() {
		for (UniqueKey uk : dslColumn.getUniqueKey()) {
			if (uk instanceof PrimaryKey) return true;
		}
		return false;
	}

	
}