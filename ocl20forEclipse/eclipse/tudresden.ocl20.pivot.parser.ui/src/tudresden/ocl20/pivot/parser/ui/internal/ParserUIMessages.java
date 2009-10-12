/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.parser.ui.internal;

import org.eclipse.osgi.util.NLS;

/**
 * Localized messages for the Parser UI Plugin.
 * 
 * @author Matthias Braeuer
 */
public class ParserUIMessages extends NLS {

	private static final String BUNDLE_NAME =
			"tudresden.ocl20.pivot.parser.ui.internal.messages"; //$NON-NLS-1$

	public static String ParseOCLPage_Description;
	public static String ParseOCLPage_SelectOCLFileLabel;
	public static String ParseOCLPage_Title;
	public static String ParseOCLPage_LocationLabelText;
	public static String ParseOCLPage_BrowseWorkspaceButtonText;
	public static String ParseOCLPage_BrowseFileSystemButtonText;
	public static String ParseOCLPage_SelectOCLFile;
	public static String ParseOCLPage_OCLFileInvalid;
	public static String ParseOCLPage_FileDoesNotExist;

	public static String ParseOCLWizard_CheckLog;
	public static String ParseOCLWizard_ErrorMessageDialogTitle;
	public static String ParseOCLWizard_ErrorOccuredDuringParsing;
	public static String ParseOCLWizard_NoActiveModelErrorMessage;
	public static String ParseOCLWizard_UnexpectedError;
	public static String ParseOCLWizard_WindowTitle;
	public static String ParseOCLWizard_ModelsViewActivationError;

	static {
		/* Initialize resource bundle. */
		NLS.initializeMessages(BUNDLE_NAME, ParserUIMessages.class);
	}

	private ParserUIMessages() {

		/* No implementation necessary. */
	}
}