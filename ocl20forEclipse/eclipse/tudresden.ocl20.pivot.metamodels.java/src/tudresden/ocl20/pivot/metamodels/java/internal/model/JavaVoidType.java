/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.java.internal.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.metamodels.java.JavaMetaModelPlugin;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType;

/**
 * <p>
 * An implementation of the Pivot Model {@link PrimitiveType} concept for the
 * {@link PrimitiveTypeKind#VOID} for Java.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaVoidType extends AbstractPrimitiveType implements
		PrimitiveType {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = JavaMetaModelPlugin
			.getLogger(JavaVoidType.class);

	/** The {@link JavaAdapterFactory} the {@link JavaVoidType} belongs to. */
	private JavaAdapterFactory myFactory;

	/**
	 * <p>
	 * Creates a new {@link JavaVoidType} instance.
	 * </p>
	 * 
	 * @param aFactory
	 *            The {@link JavaAdapterFactory} the {@link JavaVoidType}
	 *            belongs to.
	 */
	public JavaVoidType(JavaAdapterFactory aFactory) {

		/* Eventually log the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaVoidType(aFactory = " + aFactory + ") - enter";

			LOGGER.debug(msg); //$NON-NLS-1$
		}
		// no else.

		this.myFactory = aFactory;

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("JavaVoidType() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getKind()
	 */
	@Override
	public PrimitiveTypeKind getKind() {

		return PrimitiveTypeKind.VOID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getName()
	 */
	@Override
	public String getName() {

		return void.class.getSimpleName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.pivotmodel.base.AbstractPrimitiveType#getNamespace
	 * ()
	 */
	@Override
	public Namespace getNamespace() {

		Namespace result;

		String[] namespacePath;
		List<String> namespaceList;

		namespaceList = new ArrayList<String>();
		namespaceList.add(ModelConstants.ROOT_PACKAGE_NAME);

		/* Add all packages of the canonical name to the path. */
		namespacePath = void.class.getCanonicalName().split("\\.");

		for (int index = 0; index < namespacePath.length - 1; index++) {
			namespaceList.add(namespacePath[index]);
		}

		/* Create the name space. */
		result = this.myFactory.createNamespace(namespaceList);

		return result;
	}
}