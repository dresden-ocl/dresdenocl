/*
 * MappedClass.java
 * 
 * Copyright (c) 2006 Florian Heidenreich
 * Contact: <mail@fheidenreich.de>
 *
 * This file is part of the Dresden OCL2.0 Toolkit
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.pivot.tools.codegen.declarativ.mapping;

/**
 * The MappedClass is a target model representation of the corresponding
 * Classifier from the base model.
 * 
 * It's the only class which actually know how to navigate through the target
 * model and is heavily used in the DeclarativeCodeGenerator.
 * 
 * @author Florian Heidenreich
 */
public interface IMappedClass {

	/**
	 * @return the name of the MappedClass
	 */
	public String getName();

	/**
	 * Returns a Guide object to the given attribute name
	 * 
	 * @param attrName
	 *          the name of the attribute
	 * @return a Guide object to the given attribute name
	 */
	public Guide getAttributeGuide(String attrName);

	/**
	 * Returns a Guide object to the given association end name
	 * 
	 * @param assEndName
	 *          the name of the association end
	 * @return a Guide object to the given association end name
	 */
	public Guide getAssociationEndGuide(String assEndName);

	/**
	 * Returns a Guide object to the MappedClass in the target model
	 * 
	 * @return a Guide object to the MappedClass in the target model
	 */
	public Guide getClassGuide();

	/**
	 * Returns true if the given parameter represents an attribute
	 * 
	 * @param attrName
	 *          the name of the attribute
	 * @return true if the given parameter represents an attribute
	 */
	public boolean isAttribute(String attrName);

	/**
	 * Returns true if the given parameter represents an association end
	 * 
	 * @param assEndName
	 *          the name of the association end
	 * @return true if the given parameter represents an association end
	 */
	public boolean isAssociationEnd(String assEndName);
}