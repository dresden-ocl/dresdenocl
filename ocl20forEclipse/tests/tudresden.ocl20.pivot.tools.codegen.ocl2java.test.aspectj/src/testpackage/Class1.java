/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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

package testpackage;

import java.util.Set;

/**
 * <p>
 * A test class for generated AspectJ code.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Class1 {

	/** Static field used in constraints. */
	public static Integer aStaticInteger01 = 42;

	/** Static field used in constraints. */
	public static Integer staticDeriveProperty01 = null;

	/** Static field used in constraints. */
	public static Integer staticInitProperty01 = null;

	/** Field used in constraints. */
	public Integer anInteger01 = 42;

	/** Field used in constraints. */
	public Integer deriveProperty01 = null;

	/** Field used in constraints. */
	public Integer initProperty01 = null;

	/**
	 * Indicates whether or not a invariant is violated. <code>false</code>
	 * means violation.
	 */
	public Boolean isInvariant01Violated = true;

	/**
	 * Indicates whether or not a invariant is violated. <code>false</code>
	 * means violation.
	 */
	public Boolean isInvariant02Violated = true;

	/**
	 * Indicates whether or not a invariant is violated. <code>false</code>
	 * means violation.
	 */
	public Boolean isInvariant03Violated = true;

	/**
	 * Indicates whether or not a invariant is violated. <code>false</code>
	 * means violation.
	 */
	public Boolean isInvariant04Violated = true;

	/**
	 * Parent relation used to test closure iterator.
	 */
	public Class1 parent = null;

	/**
	 * Children relation used to test closure iterator.
	 */
	public Set<Class1> children = null;

	/**
	 * <p>
	 * Empty constructor required for most test cases.
	 * </p>
	 */
	public Class1() {
		/* Remains empty. */
	}

	/**
	 * <p>
	 * Constructor to test definition of pre and postconditions on constructors.
	 * </p>
	 * 
	 * @param anInt
	 */
	public Class1(Integer anInt) {
		/* Remains empty. */
	}

	/**
	 * <p>
	 * Constructor to test definition of pre and postconditions on constructors.
	 * </p>
	 * 
	 * @param isInvariant01Violated
	 *            Indicates whether or not an invariant is violated.
	 *            <code>false</code> means violation.
	 * @param isInvariant02Violated
	 *            Indicates whether or not an invariant is violated.
	 *            <code>false</code> means violation.
	 * @param isInvariant03Violated
	 *            Indicates whether or not an invariant is violated.
	 *            <code>false</code> means violation.
	 * @param isInvariant04Violated
	 *            Indicates whether or not an invariant is violated.
	 *            <code>false</code> means violation.
	 */
	public Class1(Boolean isInvariant01Violated, Boolean isInvariant02Violated,
			Boolean isInvariant03Violated, Boolean isInvariant04Violated) {
		this.isInvariant01Violated = isInvariant01Violated;
		this.isInvariant02Violated = isInvariant02Violated;
		this.isInvariant03Violated = isInvariant03Violated;
		this.isInvariant04Violated = isInvariant04Violated;
	}

	/**
	 * <p>
	 * Used to test a body constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 * @throws RuntimeException
	 *             Thrown, if the AspectJ aspect was not instrumented correctly.
	 */
	public static Integer staticBodyOperation01() {
		/* Implemented by AspectJ file. */
		throw new RuntimeException(
				"AspectJ body expression was not instrumented correctly.");
	}

	/**
	 * <p>
	 * Used to test a OperationCallExp.
	 * </p>
	 * 
	 * @return An {@link Integer}.
	 */
	public static Integer getStaticInteger() {
		return 42;
	}

	/**
	 * <p>
	 * Used to test a post constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 */
	public static Integer staticPostOperation01(Integer in1) {
		return in1 + 2;
	}

	/**
	 * <p>
	 * Used to test a pre constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 */
	public static Integer staticPreOperation01(Integer in1) {
		return -in1;
	}

	/**
	 * <p>
	 * Used to test a body constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 * @throws RuntimeException
	 *             Thrown, if the AspectJ aspect was not instrumented correctly.
	 */
	public Integer bodyOperation01() {
		/* Implemented by AspectJ file. */
		throw new RuntimeException(
				"AspectJ body expression was not instrumented correctly.");
	}

	/**
	 * <p>
	 * Used to test a body constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 * @throws RuntimeException
	 *             Thrown, if the AspectJ aspect was not instrumented correctly.
	 */
	public Integer bodyOperation02(Integer in1) {
		/* Implemented by AspectJ file. */
		throw new RuntimeException(
				"AspectJ body expression was not instrumented correctly.");
	}

	/**
	 * <p>
	 * Used to test a OperationCallExp.
	 * </p>
	 * 
	 * @return An {@link Integer}.
	 */
	public Integer getInteger() {
		return 42;
	}

	/**
	 * <p>
	 * Used to test a post constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 */
	public Integer postOperation01(Integer in1) {
		return in1 + 2;
	}

	/**
	 * <p>
	 * Used to test a post constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 */
	public void postOperation02() {
		this.anInteger01++;
	}

	/**
	 * <p>
	 * Used to test a post constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 */
	public Class1 postOperation03() {
		return new Class1();
	}

	/**
	 * <p>
	 * Used to test a pre constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 */
	public Integer preOperation01(Integer in1) {
		return -in1;
	}

	public static void setaStaticInteger01(Integer aStaticInteger01) {
		Class1.aStaticInteger01 = aStaticInteger01;
	}

	public static void setStaticDeriveProperty01(Integer staticDeriveProperty01) {
		Class1.staticDeriveProperty01 = staticDeriveProperty01;
	}

	public static void setStaticInitProperty01(Integer staticInitProperty01) {
		Class1.staticInitProperty01 = staticInitProperty01;
	}

	public void setAnInteger01(Integer anInteger01) {
		this.anInteger01 = anInteger01;
	}

	public void setDeriveProperty01(Integer deriveProperty01) {
		this.deriveProperty01 = deriveProperty01;
	}

	public void setInitProperty01(Integer initProperty01) {
		this.initProperty01 = initProperty01;
	}

	public void setIsInvariant01Violated(Boolean isInvariant01Violated) {
		this.isInvariant01Violated = isInvariant01Violated;
	}

	public void setIsInvariant02Violated(Boolean isInvariant02Violated) {
		this.isInvariant02Violated = isInvariant02Violated;
	}

	public void setIsInvariant03Violated(Boolean isInvariant03Violated) {
		this.isInvariant03Violated = isInvariant03Violated;
	}
}