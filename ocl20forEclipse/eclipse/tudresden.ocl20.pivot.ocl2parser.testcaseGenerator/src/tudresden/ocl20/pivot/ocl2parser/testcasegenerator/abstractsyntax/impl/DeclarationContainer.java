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

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IDeclarationContainer;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;

public class DeclarationContainer implements IDeclarationContainer {
	private IModelReference modelRef;
	private IMetamodelReference metamodelRef;
	
	public DeclarationContainer() {
		super();
	}
	
	public DeclarationContainer(IMetamodelReference metamodelRef, IModelReference modelRef) {
		super();
		this.modelRef = modelRef;
		this.metamodelRef = metamodelRef;
	}
	public IModelReference getModelRef() {
		return modelRef;
	}
	public void setModelRef(IModelReference modelRef) {
		this.modelRef = modelRef;
	}
	public IMetamodelReference getMetamodelRef() {
		return metamodelRef;
	}
	public void setMetamodelRef(IMetamodelReference metamodelRef) {
		this.metamodelRef = metamodelRef;
	}
	
	
}
