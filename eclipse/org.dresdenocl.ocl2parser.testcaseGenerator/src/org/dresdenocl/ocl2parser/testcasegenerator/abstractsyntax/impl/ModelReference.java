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

import org.dresdenocl.modelbus.IModel;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelNode;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;
import org.dresdenocl.ocl2parser.testcasegenerator.util.IEnvironment;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitor;
import org.dresdenocl.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class ModelReference implements IModelReference {
	private ITokenAS modelFileName;
	private IModel model;

	public ModelReference() {
		super();
	}
	
	public ModelReference(ITokenAS modelFile) {
		super();
		this.modelFileName = modelFile;
	}

	public ITokenAS getToken() {
		return modelFileName;
	}

	public void setToken(ITokenAS modelFile) {
		this.modelFileName = modelFile;
	}
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		File modelFile = new File(modelFileName.getValue());
		System.out.println("The root directory is " + new File(".").getAbsolutePath());
		
		if (!modelFile.exists()) throw new BuildingASMException("The model file doesn't exists.", modelFileName);
		
		IModel model = null;
		try {
			model = env.getMetamodel().getModelProvider().getModel(modelFile);
		} catch(ModelAccessException ex) {
			throw new BuildingASMException("The model file contains no model.", modelFileName, ex);
		}
		
		if (model == null) throw new BuildingASMException("The model file contains no model.", modelFileName);
		
		env.setModel(model);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitModelReference(this, env);
	}

	public IModel getModel() {
		return model;
	}

	public void setModel(IModel model) {
		this.model = model;
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitModelReference(this, buffer);
		
	}
	
}
