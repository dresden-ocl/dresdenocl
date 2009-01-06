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
package tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax;


public interface IGenModelFactory {
	public IAbstractModel createAbstractModel(ITokenAS token);
	public IAsSetElement crateAsSetElement(ITokenAS token);
	public ICollectionKindElement createCollectionKindElement(ITokenAS token);
	public IConstraintElement createConstraintElement(ITokenAS token);
	public IConstraintKindElement createConstraintKindElement(ITokenAS token);
	public IEnumerationLiteralElement createEnumerationLiteralElement(ITokenAS token);
	public IErrorElement createErrorElement(ITokenAS token);
	public IEssentialOclElement createEssentialOclElement(ITokenAS token);
	public IIntegerElement createIntegerElement(ITokenAS token);
	public IListElement createListElement(ITokenAS token);
	public IMetamodelReference createMetamodelReference(ITokenAS token);
	public IModelReference createModelReference(ITokenAS token);
	public INamespaceElement createNamespaceElement(ITokenAS token);
	public INullElement createNullElement(ITokenAS token);
	public IOperationNewElement createOperationNewElement(ITokenAS token);
	public IOperationStaticElement createOperationStaticElement(ITokenAS token);
	public IOperationElement createOperationElement(ITokenAS token);
	public IPackageDeclaration createPackageDeclaration(ITokenAS token);
	public IParameterElement createParameterElement(ITokenAS token);
	public IParameterKindElement createParameterKindElement(ITokenAS token);
	public IPropertyNewElement createPropertyNewElement(ITokenAS token);
	public IPropertyElement createPropertyElement(ITokenAS token);
	public IPropertyStaticElement createPropertyStaticElement(ITokenAS token);
	public IRealElement createRealElement(ITokenAS token);
	public IStringElement createStringElement(ITokenAS token);
	public ITestcase createTestcase(ITokenAS token);
	public ITestSuite createTestSuite(ITokenAS token);
	public ITokenAS createToken(String value, int line, int column);
	public ITypeElement createTypeElement(ITokenAS token);
	public IVariable createVariable(ITokenAS token);
	
	public IDeclarationContainer createDeclarationContainer(IMetamodelReference metaModelRef, IModelReference modelRef);
	
	public Object createNode(String className) throws FactoryException;
}
