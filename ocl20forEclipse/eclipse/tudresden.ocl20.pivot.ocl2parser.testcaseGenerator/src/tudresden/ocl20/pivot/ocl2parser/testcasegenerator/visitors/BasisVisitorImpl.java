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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors;

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ICollectionKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IConstraintElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IConstraintKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IEnumerationLiteralElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IErrorElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IIntegerElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IListElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IMetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IModelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.INamespaceElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.INullElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IOperationStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPackageDeclaration;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IParameterElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IParameterKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPropertyElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPropertyNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IPropertyStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IRealElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IStringElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestSuite;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcase;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITestcaseElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITypeElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.IVariable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.AbstractModel;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.CollectionKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ConstraintKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.EnumerationLiteralElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ErrorElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.EssentialOclElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.IntegerElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ListElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.MetamodelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ModelReference;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.NamespaceElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.NullElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.OperationNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.OperationStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.PackageDeclaration;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ParameterElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.ParameterKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.PropertyElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.PropertyNewElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.PropertyStaticElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.RealElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.StringElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.TestSuite;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.Testcase;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.TestcaseElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.TypeElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.impl.Variable;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;

/**
 * This class is a very simple basis implementation of the IVisitor
 * interface. However, it is still useful because the subclasses don't
 * implement each method.
 * @author Nils
 *
 */
abstract public class BasisVisitorImpl implements IVisitor {

	public void visitAbstractModel(IAbstractModel node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitAsSetElement(IAsSetElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitCollectionKindElement(ICollectionKindElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitConstraintElement(IConstraintElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitConstraintKindElement(IConstraintKindElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitEnumerationLiteralElement(IEnumerationLiteralElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitErrorElement(IErrorElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitEssentialOclElement(IEssentialOclElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitIntegerElement(IIntegerElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitListElement(IListElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitMetamodelReference(IMetamodelReference node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitModelReference(IModelReference node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitNamespaceElement(INamespaceElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitNullElement(INullElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitOperationElement(IOperationElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitOperationNewElement(IOperationNewElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitOperationStaticElement(IOperationStaticElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitPackageDeclaration(IPackageDeclaration node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitParameterElement(IParameterElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitParameterKindElement(IParameterKindElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitPropertyElement(IPropertyElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitPropertyNewElement(IPropertyNewElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitPropertyStaticElement(IPropertyStaticElement node,
			IEnvironment env) throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitRealElement(IRealElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitStringElement(IStringElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitTestSuite(ITestSuite node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitTestcase(ITestcase node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitTestcaseElement(ITestcaseElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitTypeElement(ITypeElement node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	public void visitVariable(IVariable node, IEnvironment env)
			throws BuildingASMException {
		// TODO Auto-generated method stub
		
	}

	
	
	

}
