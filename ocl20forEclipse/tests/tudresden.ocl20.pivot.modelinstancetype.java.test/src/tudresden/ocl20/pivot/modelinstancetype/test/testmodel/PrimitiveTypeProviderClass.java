/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Java Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelinstancetype.test.testmodel;

import java.math.BigDecimal;
import java.math.BigInteger;

import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <p>
 * This class provides some properties that can be used to load instances of
 * {@link PrimitiveType}s required for testing.
 * </p>
 * 
 * @author Claas Wilke
 */
public class PrimitiveTypeProviderClass {

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#BOOLEAN}.
	 */
	protected boolean booleanProperty1 = false;

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#BOOLEAN}.
	 */
	protected Boolean booleanProperty2 = new Boolean(true);

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected BigDecimal integerProperty1 = new BigDecimal(1);

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected BigInteger integerProperty2 = new BigInteger("42");

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected Byte integerProperty3 = new Byte("-34");

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected Integer integerProperty4 = new Integer(-23);

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected Long integerProperty5 = new Long(12);

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected Short integerProperty6 = new Short((short) 123);

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected byte integerProperty7 = new Byte("-34").byteValue();

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected int integerProperty8 = -23;

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected long integerProperty9 = 12;

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#INTEGER}.
	 */
	protected short integerProperty10 = (short) 123;

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#REAL}.
	 */
	protected Double realProperty1 = new Double(1.0);

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#REAL}.
	 */
	protected Float realProperty2 = new Float(3.7);

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#REAL}.
	 */
	protected double realProperty3 = 1.0;

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#REAL}.
	 */
	protected float realProperty4 = (float) 3.7;

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#STRING}.
	 */
	protected Character stringProperty1 = new Character('b');

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#STRING}.
	 */
	protected String stringProperty2 = "true";

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#STRING}.
	 */
	protected String stringProperty3 = "false";

	/**
	 * A {@link Property} that returns an instance of a {@link PrimitiveType} of
	 * the {@link PrimitiveTypeKind#STRING}.
	 */
	protected char stringProperty4 = 'b';
}