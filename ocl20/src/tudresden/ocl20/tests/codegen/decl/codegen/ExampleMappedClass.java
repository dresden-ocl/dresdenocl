/*
 * ExampleMappedClass.java
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

package tudresden.ocl20.tests.codegen.decl.codegen;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.codegen.decl.mapping.Guide;
import tudresden.ocl20.codegen.decl.mapping.MappedClass;

/**
 * An example implementation of <code>MappedClass</code> used by the test case 
 * <code>TestDeclarativeCodeGenerator</code>.
 * 
 * @author Florian Heidenreich
 * 
 * @see tudresden.ocl2.tests.codegen.decl.codegen.TestDeclarativeCodeGenerator.java
 */
/*
 * Javadoc added by Claas Wilke in 2007.
 */
public class ExampleMappedClass implements MappedClass {

	private String name;
	private Guide classGuide;
	private Map<String, Guide> attributeGuides;
	private Map<String, Guide> assEndGuides;
	
	public ExampleMappedClass(String name) {
		this.name = name;
		attributeGuides = new HashMap<String, Guide>();
		assEndGuides = new HashMap<String, Guide>();
	}
	
	public String getName() {
		return name;
	}

	public Guide getAttributeGuide(String attrName) {
		return attributeGuides.get(attrName);
	}

	public Guide getAssociationEndGuide(String assEndName) {
		return assEndGuides.get(assEndName);
	}

	public Guide getClassGuide() {
		return classGuide;
	}

	public boolean isAttribute(String attrName) {
		return attributeGuides.containsKey(attrName);
	}

	public boolean isAssociationEnd(String assEndName) {
		return assEndGuides.containsKey(assEndName);
	}

	public void addAttributeGuide(String attrName, Guide guide) {
		attributeGuides.put(attrName, guide);		
	}

	public void addAssociationEndGuide(String assEndName, Guide guide) {
		assEndGuides.put(assEndName, guide);		
	}

	public void setClassGuide(Guide guide) {
		classGuide = guide;		
	}

}
