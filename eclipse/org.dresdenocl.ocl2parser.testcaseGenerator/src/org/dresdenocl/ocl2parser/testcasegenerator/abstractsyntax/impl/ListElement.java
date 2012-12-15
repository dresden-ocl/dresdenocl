/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the testcase generator for OCL parser of the Dresden OCL2 for Eclipse.

    The testcase generator is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The testcase generator is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the testcase generator.  If not, see <http://www.gnu.org/licenses/>.
.
*/
package org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.LSTORE;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IListElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class ListElement extends ComplexElement implements IListElement  {
	private String listTypename = null;
	private String listPackageTypeName = null;
	private boolean arrayType = false;
	
	public ListElement() {
		super(Messages.getString("ListName"));
		super.setPackageName("");
	}
	
	public ListElement(ITokenAS token) {
		super(token, Messages.getString("ListName"));
	}
	
	/**
	 * This list has a number of model expressions. Each model
	 * expression has a type (name). The purpose of this method is
	 * to determine the common super type of all the model expressions.
	 * The name of the found type is set as the list type of this class.
	 * If no common super type is found, the list type will be null.
	 * A {@link ClassNotFoundException} can be thrown. This is the case
	 * when one of the type names of the model expressions is invalid, meaning
	 * that the corresponding class/interface name doesn't exist. But this
	 * is very strange if that would happen because the type names are explicitly
	 * set in the classes of the abstract syntax. One possible scenario would be
	 * if the denoted class/interface is not available because the user didn't import
	 * the necessary packages. Another scenario is that the user has typed in a wrong
	 * type, for example 'a:= OperationCalExp(...)' (not the absent of the second 'l'
	 * in 'Call'. This scenario is also strange because this method will be
	 * called by the visitor that computes the list typenames and this visitor must
	 * be called after calling the element resolution visitor that checks whether all types,
	 * properties etc. exist.
	 * @throws ClassNotFoundException is thrown if one of the types names of the model expressions
	 * denotes a class/interfaces that is not found
	 */ 
	private void computeListTypeName() {
		// We need two class instances to determine the common super type of the model expressions.
		Class firstType = null;
		Class secondType = null;
		
		// This represents the type name of one of the model expressions.
		String secondTypeName = null;
		
		/*
		 * Iterate over all model expressions. And determine step-by-step the
		 * common super type. First begin with the tuple (null, firstType), then
		 * (firstType, secondType), (secondType, thirdType) ... until
		 * the last model expression is reached.
		 */ 
		List<IModelExpression> modelElements = getParameter();
		for(int i = 0; i < modelElements.size(); i++) {
			// Get the type name of one of the model expressions.
			secondTypeName = modelElements.get(i).getFullQualifiedTypename();
			
			// A null element can be part of the list. This can't change the type of the list type.
			if (secondTypeName.equals("null")) continue;
			
			// Try to search for the type denoted by the type name.
			try {
				secondType = Class.forName(secondTypeName);
			}catch(ClassNotFoundException ex) {
				System.err.println("This is an internal error. A class type was not found while\n" +
						" determining the list typename of a list. This normally can't be happend\n" +
						" because the typenames of all model elements (subtypes of ModelExpression)\n" +
						" are set in the constructor of these classes explicitly. So in this case the\n" +
						" name was set wrong. Another possible cause can be that the user has typed in a\n" +
						" type name that doesn't exist. For example, he/she wrotes 'a:= OperationCalExp(...)'\n" +
						" (note the absent of the second 'l' in 'Call'). In this case a 'EssentialOclElement'\n" +
						" is created by the parser without checking whether this type realy exists. This\n" +
						" checking is done by element resolution visitor and this visitor is normally executed before the\n" +
						" list typename visitor is called. Here are information that can help to\n" +
						" inditified the error:\n The typename of the error caused element\n" +
						" was: " + secondTypeName + " and the type of the model element\n" +
								" was: " + modelElements.get(i).getClass().getName() + " .");
				// If the class/interface can't be found, thrown an exception.
				ex.printStackTrace();
			}
			
			/*
			 * A class/interface exists, so determine the common super type of both types.
			 * Save the common super type in the 'firstType' instance.
			 * In the next iteration the common super type will be determine against this
			 * type.
			 */ 
			firstType = commonSuperType(firstType, secondType);			
		}
		
		/* 
		 * After determining the common super type, set the list type. Note:
		 * a common super type can be null.
		 */
		if (firstType == null) {
			listTypename = null;
		} else {
			int lastPointIndex = firstType.getName().lastIndexOf(".");
			
			listTypename = firstType.getName().substring(lastPointIndex+1, firstType.getName().length());
			listPackageTypeName = firstType.getName().substring(0, lastPointIndex);
		}
		
	}
	
	/**
	 * This method computes the common super type of the parameters types.
	 * The method always returns a value because all java types have the common
	 * super type 'Object'.
	 * @param thisType the first type to be compared
	 * @param otherType the second type to be compared
	 * @return the common super type of the parameters
	 */
	private Class commonSuperType(Class thisType, Class otherType) {
		 	if ((thisType == null) && (otherType != null)) return otherType;
		 	
		 	if ((thisType != null) && (otherType == null)) return thisType;
		 	
		 	if ((thisType == null) && (otherType == null)) return null;
		 	
		 	 // by default there is no common supertype
		    Class commonSuperType = null;

		    // this type is the common supertype if types are equal or the other
		    // conforms to this one
		    if (thisType.getName().equals(otherType.getName()) || conformsType(otherType, thisType, null)) {
		      commonSuperType = thisType;
		    }

		    // else check inheritance hierachies of this and the other type
		    else {
		      // the super types in one inheritance level for this and the other type,
		      // respectively, and the transitive closure of all types in both
		      // hierachies
		      Set<Class> thisSuperTypes, otherSuperTypes, allThisTypes, allOtherTypes;

		      // initialize
		      thisSuperTypes = new HashSet<Class>();
		      otherSuperTypes = new HashSet<Class>();
		      allThisTypes = new HashSet<Class>();
		      allOtherTypes = new HashSet<Class>();

		      // add the parents of both types to the corresponding sets
		      thisSuperTypes.addAll(getSuperTypes(thisType));
		      otherSuperTypes.addAll(getSuperTypes(otherType));

		      // add this and the other type to the transitive closure
		      allThisTypes.add(thisType);
		      allOtherTypes.add(otherType);

		      // go up both inheritance hierarchies to the top
		      while (!(thisSuperTypes.isEmpty() && otherSuperTypes.isEmpty())) {
		        Set<Class> temp = new HashSet<Class>();

		        // add the current super types to the transitive closure of each
		        // hierarchy
		        allThisTypes.addAll(thisSuperTypes);
		        allOtherTypes.addAll(otherSuperTypes);

		        // check if one of this type's current supertypes is contained in the
		        // other type's hierarchy
		        for (Class type : thisSuperTypes) {

		          if (allOtherTypes.contains(type)) {
		            commonSuperType = type;
		            break;
		          }

		          // remember the types of the next hierarchy level
		          temp.addAll(getSuperTypes(type));
		        }

		        // quit if we have found a super type
		        if (commonSuperType != null) {
		          break;
		        }

		        // save the next hierarchy level types for the next iteration
		        thisSuperTypes = temp;
		        temp.clear();

		        // check if one of the other type's current supertypes is contained in
		        // this type's hierarchy
		        for (Class type : otherSuperTypes) {

		          if (allThisTypes.contains(type)) {
		            commonSuperType = type;
		            break;
		          }

		          // remember the types of the next hierarchy level
		          temp.addAll(getSuperTypes(type));
		        }

		        // quit if we have found a super type
		        if (commonSuperType != null) {
		          break;
		        }

		        // save the next hierarchy level types for the next iteration
		        otherSuperTypes = temp;
		        temp.clear();
		      }
		    }

		    return commonSuperType;
		  }
	
	/*public void setType(Class type) throws BuildingASMException {
		if (!(type.isArray())) throw new BuildingASMException("Try to set an element type on a list type.", value);
		
		className = type.getName();
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitListElement(this, env);
		
	}

	/*public String getListType() throws ClassNotFoundException {
		if (listType == null) computeListTypeName();
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}*/

	public String getListTypename() {
		if (listTypename != null) return listTypename;
		computeListTypeName();
		
		return listTypename;
	}

	/**
	 * This method is usually not called because
	 * the list typename is determined by the determining
	 * the common super type of all list elements.
	 * But the method <i>getListTypename()</i> make a caching meaning,
	 * if the list typename is determined once it will always return independently
	 * of adding elements to that list. So if you wish to refresh the caching, then
	 * set the list type name with this method to 'null'. Other strings than 'null'
	 * will not have an effect. 
	 */
	public void setListTypename(String typename) {
		if (typename != null) {
			if (typename.equals("null")) this.listTypename = null;
		} else {
			typename = null;
		}
		
	}

	public String getFullQualifiedListTypeName() {
		String dummy = getListTypename();
		return listPackageTypeName + "." + dummy;
	}

	public String getPackageListName() {
		if (listTypename == null) getListTypename();
		return this.listPackageTypeName;
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitListElement(this, buffer);
		
	}
	
	public boolean isArray() {
		return arrayType;
	}
	
	public void setArray(boolean arrayType) {
		this.arrayType = arrayType;
	}
	
}
