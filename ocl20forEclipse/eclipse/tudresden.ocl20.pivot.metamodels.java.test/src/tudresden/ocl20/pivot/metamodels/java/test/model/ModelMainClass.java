/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.java.test.model;

import package1.package2.TestEnumeration;
import package1.package2.TestInnerClassesClass;
import package1.package2.TestOperationAndParameterClass;
import package1.package2.TestPrimitiveTypeClass;
import package1.package2.TestPropertyClass;
import package1.package2.TestTypeClass1;
import package1.package2.TestTypeClass2;
import package1.package2.TestTypeInterface1;
import package1.package2.TestTypeInterface2;

/**
 * <p>
 * This class is used to provide references to all Java {@link Class}es used to
 * test the Java Meta-Model adaptation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelMainClass {

	/** To test Type adaptation of {@link Class}es. */
	public TestTypeClass1 testTypeclass1;

	/** To test Type adaptation of {@link Class}es. */
	public TestTypeClass2 testTypeclass2;

	/** To test Type adaptation of interfaces. */
	public TestTypeInterface1 testTypeInterface1;

	/** To test Type adaptation of interfaces. */
	public TestTypeInterface2 testTypeInterface2;

	/** To test Enumeration adaptation. */
	public TestEnumeration testEnumeration;

	/** To test PrimitiveType adaptation. */
	public TestPrimitiveTypeClass testPrimitiveTypeClass;

	/** To test Operation and Parameter adaptation. */
	public TestOperationAndParameterClass testOperationAndParameterClass;

	/** To test Property adaptation. */
	public TestPropertyClass testPropertyClass;

	/** To test the adaptation of inner {@link Class}es. */
	public TestInnerClassesClass testInnerClassesClass;
}