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

import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ICollectionKindElement;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.abstractsyntax.ITokenAS;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.BuildingASMException;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.util.IEnvironment;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitor;
import tudresden.ocl20.pivot.ocl2parser.testcasegenerator.visitors.IVisitorCodeGen;

public class CollectionKindElement extends KindElement implements ICollectionKindElement {
	public CollectionKindElement() {
		super(Messages.getString("CollectionKindName"));
		setPackageName(Messages.getString("EssentialOclExpressionsPackageName"));
	}
	
	public CollectionKindElement(ITokenAS token) {
		super(token, Messages.getString("CollectionKindName"));
		setPackageName(Messages.getString("EssentialOclExpressionsPackageName"));
	}
	/*private TokenAS collectionKindToken = null;
	private CollectionKind kind;

	public CollectionKindElement() {
		super("CollectionKind");
	}*/
	
	/*public CollectionKindElement(TokenAS name) {
		
	}
	
	public String getClassName() {
		return "tudresden.ocl20.pivot.essentialocl.expressions." + className;
	}/
	
	/*public void computeTypeConformance(Environment env) throws BuildingASMException {
		CollectionKind kind = CollectionKind.get(value.getValue());
		
		if (kind == null) throw new BuildingASMException("The collection kind doesn't exists.", value);
	}*/

	public void accept(IVisitor visitor, IEnvironment env) throws BuildingASMException {
		visitor.visitCollectionKindElement(this, env);	
	}

	public void accept(IVisitorCodeGen visitor, StringBuffer buffer)
			throws BuildingASMException {
		visitor.visitCollectionKindElement(this, buffer);
		
	}

	/*public CollectionKind getKind() {
		return kind;
	}

	public void setKind(CollectionKind kind) {
		this.kind = kind;
	}

	public TokenAS getCollectionKindToken() {
		return collectionKindToken;
	}

	public void setCollectionKindToken(TokenAS collectionKindToken) {
		this.collectionKindToken = collectionKindToken;
	}*/
	
	

}
