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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelNode;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class MetamodelReference implements IMetamodelReference {
	private ITokenAS metamodelName;
	private IMetamodel metamodel;

	public MetamodelReference() {
		super();
	}
	
	public MetamodelReference(ITokenAS metamodelName) {
		super();
		this.metamodelName = metamodelName;
	}

	public ITokenAS getToken() {
		return metamodelName;
	}

	public void setToken(ITokenAS metamodelName) {
		this.metamodelName = metamodelName;
	}
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		IMetamodelRegistry reg = env.getMetamodelReg();
		IMetamodel metamodel = reg.getMetamodel(metamodelName.getValue());
		
		if (metamodel == null) throw new BuildingASMException("The metamodel was not found.", metamodelName);
		
		env.setMetamodel(metamodel);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitMetamodelReference(this, env);
	}

	public IMetamodel getMetamodel() {
		return metamodel;
	}

	public void setMetamodel(IMetamodel metamodel) {
		this.metamodel = metamodel;
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitMetamodelReference(this, buffer);
		
	}
	
	
}
