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
package org.dresdenocl.ocl2parser.testcasegenerator.visitors;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ICollectionKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IConstraintElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IConstraintKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEnumerationLiteralElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IErrorElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IIntegerElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IListElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.INamespaceElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.INullElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IOperationElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IOperationNewElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IOperationStaticElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPackageDeclaration;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IParameterElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IParameterKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyNewElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IPropertyStaticElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IRealElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IStringElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestSuite;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestcase;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITypeElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import org.dresdenocl.ocl2parser.testcasegenerator.util.BuildingASMException;

public interface IVisitorCodeGen {
	
	public void visitTestSuite(ITestSuite node, StringBuffer buffer) throws BuildingASMException;
	public void visitTestcase(ITestcase node, StringBuffer buffer) throws BuildingASMException;
	public void visitTestcaseElement(ITestcaseElement node, StringBuffer buffer) throws BuildingASMException;
	
	public void visitErrorElement(IErrorElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitAbstractModel(IAbstractModel node, StringBuffer buffer) throws BuildingASMException;
	public void visitVariable(IVariable node, StringBuffer buffer) throws BuildingASMException;
	
	public void visitCollectionKindElement(ICollectionKindElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitConstraintKindElement(IConstraintKindElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitParameterKindElement(IParameterKindElement node, StringBuffer buffer) throws BuildingASMException;
	
	public void visitListElement(IListElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitEssentialOclElement(IEssentialOclElement node, StringBuffer buffer) throws BuildingASMException;
	
	public void visitNullElement(INullElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitRealElement(IRealElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitIntegerElement(IIntegerElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitStringElement(IStringElement node, StringBuffer buffer) throws BuildingASMException;
	
	public void visitOperationStaticElement(IOperationStaticElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitOperationElement(IOperationElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitTypeElement(ITypeElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitNamespaceElement(INamespaceElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitEnumerationLiteralElement(IEnumerationLiteralElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitPropertyStaticElement(IPropertyStaticElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitParameterElement(IParameterElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitOperationNewElement(IOperationNewElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitPropertyNewElement(IPropertyNewElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitPropertyElement(IPropertyElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitConstraintElement(IConstraintElement node, StringBuffer buffer) throws BuildingASMException;
	public void visitAsSetElement(IAsSetElement node, StringBuffer buffer) throws BuildingASMException;
	
	public void visitPackageDeclaration(IPackageDeclaration node, StringBuffer buffer) throws BuildingASMException;
	public void visitModelReference(IModelReference node, StringBuffer buffer) throws BuildingASMException;
	public void visitMetamodelReference(IMetamodelReference node, StringBuffer buffer) throws BuildingASMException;
}
