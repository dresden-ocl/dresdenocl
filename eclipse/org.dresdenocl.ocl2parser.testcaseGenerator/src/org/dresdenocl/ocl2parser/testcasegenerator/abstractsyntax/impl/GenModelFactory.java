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

import java.util.ArrayList;

import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.FactoryException;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAbstractModel;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IAsSetElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ICollectionKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IConstraintElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IConstraintKindElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IDeclarationContainer;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEnumerationLiteralElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IErrorElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IEssentialOclElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IGenModelFactory;
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
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.ITypeElement;
import org.dresdenocl.ocl2parser.testcasegenerator.abstractsyntax.IVariable;

public class GenModelFactory implements IGenModelFactory {

	public IAsSetElement crateAsSetElement(ITokenAS token) {
		return new AsSetElement(token);
	}

	public IAbstractModel createAbstractModel(ITokenAS token) {
		return new AbstractModel(token);
	}

	public ICollectionKindElement createCollectionKindElement(ITokenAS token) {
		return new CollectionKindElement(token);
	}

	public IConstraintElement createConstraintElement(ITokenAS token) {
		return new ConstraintElement(token);
	}

	public IConstraintKindElement createConstraintKindElement(ITokenAS token) {
		return new ConstraintKindElement(token);
	}

	public IEnumerationLiteralElement createEnumerationLiteralElement(
			ITokenAS token) {
		return new EnumerationLiteralElement(token);
	}

	public IErrorElement createErrorElement(ITokenAS token) {
		return new ErrorElement(token);
	}

	public IEssentialOclElement createEssentialOclElement(ITokenAS token) {
		return new EssentialOclElement(token);
	}

	public IIntegerElement createIntegerElement(ITokenAS token) {
		return new IntegerElement(token);
	}

	public IListElement createListElement(ITokenAS token) {
		return new ListElement(token);
	}

	public IMetamodelReference createMetamodelReference(ITokenAS token) {
		return new MetamodelReference(token);
	}

	public IModelReference createModelReference(ITokenAS token) {
		return new ModelReference(token);
	}

	public INamespaceElement createNamespaceElement(ITokenAS token) {
		return new NamespaceElement(token);
	}

	public INullElement createNullElement(ITokenAS token) {
		return new NullElement(token);
	}

	public IOperationNewElement createOperationNewElement(ITokenAS token) {
		return new OperationNewElement(token);
	}

	public IOperationStaticElement createOperationStaticElement(ITokenAS token) {
		return new OperationStaticElement(token);
	}
	
	public IOperationElement createOperationElement(ITokenAS token) {
		return new OperationElement(token);
	}

	public IPackageDeclaration createPackageDeclaration(ITokenAS token) {
		return new PackageDeclaration(token);
	}

	public IParameterElement createParameterElement(ITokenAS token) {
		return new ParameterElement(token);
	}

	public IParameterKindElement createParameterKindElement(ITokenAS token) {
		return new ParameterKindElement(token);
	}

	public IPropertyNewElement createPropertyNewElement(ITokenAS token) {
		return new PropertyNewElement(token);
	}
	
	public IPropertyElement createPropertyElement(ITokenAS token) {
		return new PropertyElement(token);
	}

	public IPropertyStaticElement createPropertyStaticElement(ITokenAS token) {
		return new PropertyStaticElement(token);
	}

	public IRealElement createRealElement(ITokenAS token) {
		return new RealElement(token);
	}

	public IStringElement createStringElement(ITokenAS token) {
		return new StringElement(token);
	}

	public ITestSuite createTestSuite(ITokenAS token) {
		return new TestSuite(token);
	}

	public ITestcase createTestcase(ITokenAS token) {
		return new Testcase(token);
	}

	public ITypeElement createTypeElement(ITokenAS token) {
		return new TypeElement(token);
	}

	public IVariable createVariable(ITokenAS token) {
		return new Variable(token);
	}

	public Object createNode(String className) throws FactoryException {
		if (className.equals("List")) return new ArrayList();
		
		String parentPackage = "org.dresdenocl.ocl2parser.testcasegenerator";
		String[] packageNames = new String[]{parentPackage + ".abstractsyntax.impl."};
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		
		Class builtClass = null;
		String concreteClassName = className.substring(1,className.length());
		for(int i = 0; i < packageNames.length; i++) {
			String qualName = packageNames[i] + concreteClassName;
			try {
				builtClass = loader.loadClass(qualName);
			} catch(ClassNotFoundException ex) {
				
			}
			if (builtClass != null) break;
		}
		
		if (builtClass == null) throw new FactoryException("The class name was not found. Class name: " + concreteClassName);
				
		Object instance = null;
		
		try {
			instance = builtClass.newInstance();
		}
		catch(InstantiationException ex) {
			throw new FactoryException("An error ocurred while instantiating the instance. Class name: " + concreteClassName, ex);
		}
		catch(IllegalAccessException ex2) {
			throw new FactoryException("An error ocurred while instantiating the instance. Class name: " + concreteClassName, ex2);
		}
		return instance;
	}
	
	public IDeclarationContainer createDeclarationContainer(IMetamodelReference metaModelRef, IModelReference modelRef) {
		return new DeclarationContainer(metaModelRef, modelRef);
	}

	public ITokenAS createToken(String value, int line, int column) {
		return new TokenAS(value, line, column);
	}

	

}
