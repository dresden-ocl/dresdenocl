/*
 * MetaModelHelper.java
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

package tudresden.ocl20.codegen.decl.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier;

/**
 * Encapsulates some helper methods to gather information from the model and
 * model elements.
 * 
 * @author Florian Heidenreich
 */
public class MetaModelHelper {
	
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
			if (o.getNameA().equals("OclAny")) {
				continue;
			}
			parents.add(o);
		}

		return parents;
	}
}
