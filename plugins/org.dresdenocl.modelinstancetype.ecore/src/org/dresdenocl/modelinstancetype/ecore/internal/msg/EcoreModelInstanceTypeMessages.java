/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance Plug-in of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.modelinstancetype.ecore.internal.msg;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * Provides localized String constants for the Java Meta-Model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstanceTypeMessages extends NLS {

	private static final String BUNDLE_NAME =
			"org.dresdenocl.modelinstancetype.ecore.internal.msg.messages"; //$NON-NLS-1$


	public static String EcoreModelInstanceProvider_InvalidURL;

	public static String EcoreModelInstance_CannotRetrieveElements;
	public static String EcoreModelInstance_ObjectDoesNoMatchToModel;
	public static String EcoreModelInstance_CannotCast;
	public static String EcoreModelInstance_NoSupportOfStaticProperties;
	public static String EcoreModelInstance_NoSupportOfStaticOperations;
	public static String EcoreModelInstance_EnumerationLiteralNotFound;
	public static String EcoreModelInstance_CannotCastTypeClassNotFound;
	public static String EcoreModelInstance_CannotRecreateArray;
	public static String EcoreModelInstance_CannotRecreateCollection;
	public static String EcoreModelInstance_ClassNotFound;

	public static String EcoreModelInstanceFactory_AdapteeIsNoEObjectInstance;
	public static String EcoreModelInstanceFactory_TypeNotFoundInModel;
	public static String EcoreModelInstanceFactory_CannotAdaptToType;

	public static String EcoreModelInstanceObject_PropertyNotFoundInModelInstanceElement;
	public static String EcoreModelInstanceObject_CannotCopyForAtPre;
	public static String EcoreModelInstanceObject_OperationAccessFailed;
	public static String EcoreModelInstanceObject_OperationNotFound;
	public static String EcoreModelInstanceObject_PropertyAccessFailed;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, EcoreModelInstanceTypeMessages.class);
	}

	private EcoreModelInstanceTypeMessages() {

		/* No implementation necessary. */
	}
}