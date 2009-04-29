/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
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
 */
package tudresden.ocl20.pivot.modelbus.base;

import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.StandardlibraryAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModelInstanceFactory;

/**
 * Abstract implementation of {@link IModelInstanceFactory}. Creation of simple
 * OCL objects is delegated to {@link StandardlibraryAdapterFactory}
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class AbstractModelInstanceFactory implements
		IModelInstanceFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclBoolean(boolean)
	 */
	public OclBoolean createOclBoolean(boolean booleanLiteral) {
		return (OclBoolean) Platform.getAdapterManager().getAdapter(
				booleanLiteral, OclBoolean.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclInteger(int)
	 */
	public OclInteger createOclInteger(int integerLiteral) {
		return (OclInteger) Platform.getAdapterManager().getAdapter(
				integerLiteral, OclInteger.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclReal(float)
	 */
	public OclReal createOclReal(float realLiteral) {
		return (OclReal) Platform.getAdapterManager().getAdapter(realLiteral,
				OclReal.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceFactory#createOclString(java.lang.String)
	 */
	public OclString createOclString(String stringLiteral) {
		return (OclString) Platform.getAdapterManager().getAdapter(
				stringLiteral, OclString.class);
	}
}
