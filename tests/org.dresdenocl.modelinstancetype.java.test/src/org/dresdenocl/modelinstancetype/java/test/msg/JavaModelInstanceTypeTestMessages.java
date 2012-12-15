/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Generic Java Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

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
package org.dresdenocl.modelinstancetype.java.test.msg;

import org.eclipse.osgi.util.NLS;

/**
 * <p>
 * Localized {@link String} constants for the {@link EcorePlugin}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceTypeTestMessages extends NLS {

	/** The name of the message resource bundle. */
	private static final String BUNDLE_NAME =
			"org.dresdenocl.modelinstancetype.java.test.msg.messages"; //$NON-NLS-1$

	public static String TestModelInstanceElementAdataptation_AdaptationIsWrong;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME,
				JavaModelInstanceTypeTestMessages.class);
	}

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceTypeTestMessages} instance.
	 * </p>
	 */
	private JavaModelInstanceTypeTestMessages() {

		/* No implementation necessary. */
	}
}