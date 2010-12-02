/*
 * MetaModelHelper.java
 * 
 * Copyright (c) 2005 Florian Heidenreich
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

package tudresden.ocl20.codegen.sql.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.jmi.uml15.core.Association;
import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.CorePackage;
import tudresden.ocl20.core.jmi.uml15.core.Feature;
import tudresden.ocl20.core.jmi.uml15.core.Generalization;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;

/**
 * Encapsulates some helper methods to gather information from the model and
 * model elements.
 * 
 * @author Florian Heidenreich
 * @deprecated See tudresden.ocl20.codegen.decl.tools.sql
 */
public class MetaModelHelper {

	/**
	 * Constant which contains all type names which are considered as Java types by the OCL22SQL tool 
	 */
	public static final String JAVATYPES = "Bag(OclAny) boolean boolean[] Boolean byte byte[] Byte char char[] Character Collection(OclAny) Color Date double double[] Double float float[] Float Hashtable int int[] Integer long long[] Long OclAny OclState OclVoid Point Real Rectangle Sequence(OclAny) Set(OclAny) Stack String Vector void";

	/**
	 * Returns true, if all AssiciationEnds of the given Association a are multiple (m:n)
	 * 
	 * @param ass an Association
	 * @return truem if all AssiciationEnds of the given Association a are multiple
	 */
	public static boolean allEndsAreMultiple(Association ass) {
		Iterator it = ass.getConnection().iterator();
		while (it.hasNext()) {
			AssociationEnd end = (AssociationEnd) it.next();
			if (!end.isMultipleA()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Method to gather all attributes related to a classifier
	 * 
	 * @param cls Classifier which attributes should be returned
	 * @return List of Attribute-instances associated to Classifier c
	 */
	public static List<Attribute> getAttributes(Classifier cls) {
		List<Attribute> attributes = new ArrayList<Attribute>();
		Iterator featuresIt = cls.getFeature().iterator();
		while (featuresIt.hasNext()) {
			Feature feature = (Feature) featuresIt.next();
			if (feature instanceof Attribute) {
				attributes.add((Attribute) feature);
			}
		}

		return attributes;
	}

	/**
	 * Method to identify the generalization root of a given Classifier
	 * @param cls Classifier which generalization root should be identified
	 * @return the generalization root of the Classifier cls, or the Classifier cls
	 * itself, if it is already the top of the generalization hierarchy.
	 * @throws IllegalStateException if number of generalization roots is greater than one
	 */
	public static Classifier getGeneralizationRoot(Classifier cls)
			throws IllegalStateException {
		Classifier result = null;
		Classifier c1 = cls;
		
		while (MetaModelHelper.hasSupertypes(c1)) {
			List<Classifier> supertypes = MetaModelHelper.getSupertypes(c1);
			if (supertypes.size() == 0) {
				throw new IllegalStateException(
						"Wrong return value of MetaModelHelper.hasSuperTypes !");
			} else if (supertypes.size() > 1) {
				throw new IllegalStateException(
						"Number of generalization roots is greater than one !");
			}

			c1 = supertypes.get(0);
		}
		
		result = c1;
		return result;
	}

	/**
	 * Returns a list of all direct supertypes of the given Classifier. 
	 * This method also filters the OclAny supertype from the class hierarchy.
	 * 
	 * @param cls a Classifier which supertypes should be determined
	 * @return a list of all supertypes of the given Classifier
	 */
	public static List<Classifier> getSupertypes(Classifier cls) {
		List<Classifier> parents = new ArrayList<Classifier>();
		Iterator i = cls.getParents().iterator();
		while (i.hasNext()) {
			Classifier o = (Classifier) i.next();
			if (o.getName().equals("OclAny")) {
				continue;
			}
			parents.add(o);
		}

		return parents;
	}
	
	/**
	 * Returns a list of all supertypes of the given Classifier. 
	 * This method also filters the OclAny supertype from the class hierarchy.
	 * 
	 * @param cls a Classifier which supertypes should be determined
	 * @return a list of all supertypes of the given Classifier
	 */
	public static List<Classifier> getAllSupertypes(Classifier cls) {
		List<Classifier> allSupertypes = new ArrayList<Classifier>();
		List<Classifier> directSupertypes = getSupertypes(cls);
		allSupertypes.addAll(directSupertypes);
		for (Classifier parent : directSupertypes) {
			allSupertypes.addAll(getAllSupertypes(parent));
		}
		
		return allSupertypes;
	}

	/**
	 * Determines if the Classifier cls has child classes.
	 * 
	 * @param model the model which contains the Classifier cls
	 * @param cls a Classifier
	 * @return true, if the Classifier has child classes, otherwise false
	 */
	public static boolean hasSubtypes(OclModel model, Classifier cls) {
		// specialization reference is missing in normative UML1.4 XMI
        // so we have to  use the association interface
		boolean hasSubtypes = false;
		CorePackage core = ((Uml15Package) model.getModel()).getCore();;
		
		Iterator it = core.getAParentSpecialization().getSpecialization(cls).iterator();
		while (it.hasNext()) {
			String childName = ((Generalization)it.next()).getChild().getNameA();
			
			// filter the standard OclVoid suptype from the specialization hierarchy
			if (!childName.equals("OclVoid")) {
				hasSubtypes = true;
				break;
			}
		}
		
		return hasSubtypes;
	}

	/**
	 * Determines if the Classifier cls has parent classes.
	 * This method also filters the OclAny supertype from the class hierarchy.
	 * 
	 * @param cls a Classifier
	 * @return true, if the Classifier has parent classes, otherwise false
	 */
	public static boolean hasSupertypes(Classifier cls) {
		boolean rc = false;
		Iterator i = cls.getParents().iterator();
		while (i.hasNext()) {
			if (((Classifier) i.next()).getName().equals("OclAny")) {
				continue;
			}
			rc = true;
			break;
		}

		return rc;
	}

	/**
	 * Checks whether the given type name belongs to a Java type 
	 * 
	 * @param typeName a Classifier name
	 * @return true, if typeName is a Java predefined type
	 * @link tudresden.ocl20.sql.util.MetaModelHelper.JAVATYPES
	 */
	public static boolean isJavaType(String typeName) {
		StringTokenizer st = new StringTokenizer(JAVATYPES);

		while (st.hasMoreTokens()) {
			if (st.nextToken().equals(typeName))
				return true;
		}

		return false;
	}
}