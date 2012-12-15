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

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyStaticElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;
import org.dresdenocl.pivotmodel.Property;

public class PropertyStaticElement extends PivotmodelReferenceElement implements IPropertyStaticElement {
	private Property refProperty;
	
	public PropertyStaticElement() {
		super(Messages.getString("PropertyName"));
	}
	
	public PropertyStaticElement(ITokenAS token) {
		super(token, Messages.getString("PropertyName"));
	}
	/*public PropertyStaticElement() {
		super("Property");
	}
	
	public PropertyStaticElement(TokenAS name) {
		super(name, "Property");
	}*/
	
	/*public String getClassName() {
		return "org.dresdenocl.pivotmodel." + className;
	}*/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		List<String> pathName = UtilClass.transformPathName2List(this.value.getValue());
		
		List<String> pathNameType = pathName.subList(0, pathName.size());
		Property prop = null;
		try {
			Type type = env.getModel().findType(pathNameType);
			prop = type.lookupProperty(pathName.get(pathName.size()-1));
		} catch (ModelAccessException e) {
			throw new BuildingASMException("The property was not found in the model.", value, e);
		}
		
		if (prop == null) throw new BuildingASMException("The property was not found in the model.", value);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitPropertyStaticElement(this, env);
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitPropertyStaticElement(this, buffer);
		
	}

	/*public Property getRefProperty() {
		return refProperty;
	}

	public void setRefProperty(Property refProperty) {
		this.refProperty = refProperty;
	}*/

}
