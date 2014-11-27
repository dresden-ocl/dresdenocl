/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the XML Model Instance Plug-in of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.modelinstancetype.xml.internal.msg;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * Provides localized String constants for the XML Model instance type.
 * </p>
 * 
 * @author Claas Wilke
 */
public class XmlModelInstanceTypeMessages extends NLS {

	private static final String BUNDLE_NAME =
			"org.dresdenocl.modelinstancetype.xml.internal.msg.messages"; //$NON-NLS-1$

	public static String XMLModelInstanceProvider_InvalidFileFormat;
	public static String XMLModelInstanceProvider_FileDoesNotExist;

	public static String XMLModelInstance_ParserWrongConfigured;
	public static String XMLModelInstance_SaxExceptionOccurred;
	public static String XMLModelInstance_IOExceptionOccurred;
	public static String XMLModelInstance_ObjectDoesNoMatchToModel;

	public static String XmlModelInstance_StaticOperationsAreNotSupported;
	public static String XmlModelInstance_StaticPropertiesAreNotSupported;

	public static String XmlModelInstanceElement_CannotCastToType;

	public static String XmlModelInstanceObject_OperationsAreNotSupported;
	public static String XmlModelInstanceObject_PropertyDoesNoExist;

	public static String XmlModelInstanceFactory_UnknownClassOfAdaptee;
	public static String XmlModelInstanceFactory_UnknownTypeOfAdaptee;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, XmlModelInstanceTypeMessages.class);
	}

	private XmlModelInstanceTypeMessages() {

		/* No implementation necessary. */
	}
}