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

import java.util.List;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelExpression;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class PropertyElement extends PivotmodelReferenceElement implements IPropertyElement {

	public PropertyElement() {
		super(Messages.getString("PropertyName"));
	}
	
	public PropertyElement(ITokenAS token) {
		super(token, Messages.getString("PropertyName"));
	}
	
	public void accept(IVisitorCodeGen visitor, StringBuffer buffer) throws BuildingASMException {
		visitor.visitPropertyElement(this, buffer);
	}

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitPropertyElement(this, env);
	}

}
