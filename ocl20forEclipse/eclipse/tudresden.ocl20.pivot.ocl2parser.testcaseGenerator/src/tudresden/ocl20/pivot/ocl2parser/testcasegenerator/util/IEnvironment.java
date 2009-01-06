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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelRegistry;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IGenModelFactory;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.testcasegenerator.InvalidArgumentException;

public interface IEnvironment {
	public IModel getModel();
	public IMetamodelRegistry getMetamodelReg();
	public void setMetamodel(IMetamodel metamodel);
	public IMetamodel getMetamodel();
	public void setModel(IModel model);
	
	public List<IVariable> getVariables();
	public void setVariables(List<IVariable> var);
	public IVariable lookupVariable(IVariable var);
	
	public IEnvironment nestedEnvironment();
	
	public List<FilenameContainer> lookupTestElement(File file, String testName) throws InvalidArgumentException;
	
	public FilenameContainer getTestElement();
	public void setTestElement(FilenameContainer testElement);
	
	public IGenModelFactory getFactory();
	
	public HashMap getTemplateHashMap();
}
