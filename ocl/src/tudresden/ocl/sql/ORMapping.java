/*
Copyright (C) 2001  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package tudresden.ocl.sql;

import java.util.*;

/**
 * This interface defines some functions that should return informations
 * about the object relational mapping of classes and their properties to
 * a relational scheme.
 */
public interface ORMapping {
  /** 
   * @return a List with all table objects created during the object relational mapping
   * @see tudresden.ocl.codegen.decl.Table
   */
  public List tables();
  
  /** 
   * @param classifier a classifier
   * @return a List with all table objects the classifier was mapped to
   * @see tudresden.ocl.codegen.decl.Table
   */
  public List getClassTables(String classifier);
  
  /** 
   * @return a Set containing the names of all classifiers
   */
  public Set classifiers();
  
  /** 
   * @param classifier a classifier
   * @return a Set containing all names of operations that are properties of the classifier
   */
  public Set operations(String classifier);
  
  /** 
   * @param classifier a classifier
   * @return a Set containing all the names of direct supertypes of the classifier
   */
  public Set directSupertypeNames(String classifier);
  
  /**
   * @param classifier a classifier
   * @return a Map that maps the names of all association ends to the
   *         according class name
   */
  public Map associationEnds(String classifier);
  
  /** 
   * @param classifier a classifier
   * @param assEnd the name of the association end
   * @return a List that contains guides to to the specified association end from the classifier
   * @see tudresden.ocl.codegen.decl.Guide
   */
  public List guidesToAssociationEnds(String classifier, String assEnd);
}