/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Meta Model Architecture of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.test.msg;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * Localized {@link String} constants for the {@link EcorePlugin}.
 * 
 * @author Claas Wilke
 */
public class MetaModelTestSuiteMessages extends NLS {

	/** The name of the message resource bundle. */
	private static final String BUNDLE_NAME =
			"tudresden.ocl20.pivot.metamodels.test.msg.messages"; //$NON-NLS-1$

	public static String MetaModelTestSuite_CurrentlyTestedMetaModel;
	
	public static String MetaModelTestSuite_EnumerationNotFoundInModel;

	public static String MetaModelTestSuite_OperationNotFoundInModel;

	public static String MetaModelTestSuite_ClassNotFoundInModel;

	public static String MetaModelTestSuite_UnknownPrimitiveTypeKind;

	public static String MetaModelTestSuite_NoPrimitiveTypeFound;

	public static String MetaModelTestSuite_NoPrimitiveTypeOfKindFound;

	public static String MetaModelTestSuite_AssociationEndNotFound;

	public static String MetaModelTestSuite_PropertyNotFoundInModel;

	public static String MetaModelTestSuite_InterfaceNotFoundInModel;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, MetaModelTestSuiteMessages.class);
	}

	/**
	 * <p>
	 * Creates a new {@link MetaModelTestSuiteMessages} instance.
	 * </p>
	 */
	private MetaModelTestSuiteMessages() {

		/* No implementation necessary. */
	}
}