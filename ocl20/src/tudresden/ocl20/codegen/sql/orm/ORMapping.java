/*
 * ORMapping.java
 * 
 * Copyright (c) 2001 Sten Loecher
 * Modified 2005 by Florian Heidenreich
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
package tudresden.ocl20.codegen.sql.orm;

import java.util.List;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.codegen.sql.codegen.Guide;

/**
 * This interface defines some methods that should return informations
 * about the object relational mapping of classes and their properties to
 * a relational schema.
 * 
 * @author Sten Loecher, Florian Heidenreich
 */
public interface ORMapping {

	/**
	 * Returns a Map that maps the names of all association ends
	 * contained in the object-relational mapping to the class name.
	 * 
	 * @param classifier name of a classifier 
	 * @return a Map that maps the names of all association ends to the
	 *         according class name
	 */
	public Map<String, String> getAssociationEnds(String classifier);

	/**
	 * Returns all names of the attributes that are properties of 
	 * the given classifier name
	 *   
	 * @param classifier a classifier
	 * @return a Set containing all names of attributes that are properties of the classifier
	 */
	public Set<String> getAttributes(String classifier);

	/** 
	 * Returns all names of the classifiers from the object-relational mapping.
	 * 
	 * @return a Set containing the names of all classifiers
	 */
	public Set<String> getClassifiers();

	/** 
	 * @param classifier name of a classifier
	 * @return a List with all table objects the classifier was mapped to
	 * @see tudresden.ocl20.sql.orm.Table
	 */
	public List<Table> getClassTables(String classifier);

	/** 
	 * @param classifier name of a classifier
	 * @return a Set containing all the names of direct supertypes of the classifier
	 */
	public Set<String> getDirectSupertypeNames(String classifier);

	/** 
	 * @param classifier name of a classifier
	 * @return a Set containing all names of operations that are properties of the classifier
	 */
	public Set<String> getOperations(String classifier);

	/** 
	 * @return a List with all table objects created during the object relational mapping
	 * @see tudresden.ocl20.sql.orm.Table
	 */
	public List<Table> getTables();

	/** 
	 * @param classifier name of a classifier
	 * @param assEnd the name of the association end
	 * @return a List that contains guides to to the specified association end from the classifier
	 * @see tudresden.ocl20.sql.codegen.Guide
	 */
	public List<Guide> guidesToAssociationEnds(String classifier, String assEnd);
}
