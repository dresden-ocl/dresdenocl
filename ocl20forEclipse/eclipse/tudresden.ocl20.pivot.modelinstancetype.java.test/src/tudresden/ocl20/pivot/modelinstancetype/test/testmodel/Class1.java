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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;

/**
 * <p>
 * A {@link Class} used to testing adaptations to {@link IModelInstanceObject}s.
 * </p>
 * <
 * 
 * @author Claas Wilke
 */
public class Class1 {

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * .
	 */
	protected String nonMultipleProperty = Class1.class.getCanonicalName();

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * .
	 */
	protected Set<String> multipleUniqueOrderedProperty;

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * .
	 */
	protected Set<String> multipleUniqueUnorderedProperty;

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * .
	 */
	protected List<String> multipleNonuniqueOrderedProperty;

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#getProperty(tudresden.ocl20.pivot.pivotmodel.Property)}
	 * .
	 */
	protected List<String> multipleNonuniqueUnorderedProperty;

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected boolean argumentPropertyBooleanNonMultiple = false;

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected String argumentPropertyStringNonMultiple = "true";

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected boolean[] argumentPropertyBooleanMultiple =
			new boolean[] { false, true, false };

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected List<String> argumentPropertyStringMultiple =
			new ArrayList<String>(Arrays.asList(new String[] { "1st", "2nd", "3rd" }));

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected Class1 argumentPropertyObjectNonMultiple = this;

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected List<Class1> argumentPropertyObjectMultiple =
			new ArrayList<Class1>(Arrays.asList(new Class1[] { this, this, this }));

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected Enumeration1 argumentPropertyEnumerationLiteralNonMultiple =
			Enumeration1.Literal1;

	/**
	 * A field to test the method
	 * {@link IModelInstanceObject#invokeOperation(Operation, List)}, use as
	 * argument.
	 */
	protected List<Enumeration1> argumentPropertyEnumerationLiteralMultiple =
			new ArrayList<Enumeration1>(Arrays.asList(new Enumeration1[] {
					Enumeration1.Literal1, Enumeration1.Literal2 }));

	/**
	 * A field to test the method
	 * {@link IModelInstance#getStaticPropert(Property)}.
	 */
	protected static String staticProperty = Class1.class.getCanonicalName();

	public Class1() {

		/* Initialize fields. */
		this.multipleUniqueOrderedProperty = new HashSet<String>();
		this.multipleUniqueOrderedProperty.add("1");
		this.multipleUniqueOrderedProperty.add("2");
		this.multipleUniqueOrderedProperty.add("3");

		this.multipleUniqueUnorderedProperty = new HashSet<String>();
		this.multipleUniqueUnorderedProperty.add("1");
		this.multipleUniqueUnorderedProperty.add("2");
		this.multipleUniqueUnorderedProperty.add("3");

		this.multipleNonuniqueOrderedProperty = new ArrayList<String>();
		this.multipleNonuniqueOrderedProperty.add("1");
		this.multipleNonuniqueOrderedProperty.add("2");
		this.multipleNonuniqueOrderedProperty.add("3");

		this.multipleNonuniqueUnorderedProperty = new ArrayList<String>();
		this.multipleNonuniqueUnorderedProperty.add("1");
		this.multipleNonuniqueUnorderedProperty.add("2");
		this.multipleNonuniqueUnorderedProperty.add("3");
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} without arguments and without result.
	 */
	protected void voidOperation() {

		/* Does nothing. */
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with non-multiple result.
	 */
	protected boolean nonMultipleOperation() {

		return false;
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with a multiple, unique, ordered result.
	 */
	protected Set<String> multipleUniqueOrderedOperation() {

		Set<String> result;

		result = new HashSet<String>();
		result.add("one");
		result.add("two");
		result.add("three");

		return result;
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with a multiple, unique, unordered result.
	 */
	protected Set<String> multipleUniqueUnorderedOperation() {

		Set<String> result;

		result = new HashSet<String>();
		result.add("one");
		result.add("two");
		result.add("three");

		return result;
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with a multiple, nonunique, ordered result.
	 */
	protected List<String> multipleNonuniqueOrderedOperation() {

		List<String> result;

		result = new ArrayList<String>();
		result.add("one");
		result.add("two");
		result.add("three");

		return result;
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with a multiple, nonunique, unordered result.
	 */
	protected List<String> multipleNonuniqueUnorderedOperation() {

		List<String> result;

		result = new ArrayList<String>();
		result.add("one");
		result.add("two");
		result.add("three");

		return result;
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument of a {@link PrimitiveType} and
	 * without result.
	 */
	protected void voidOperationWithBooleanArgument(boolean arg1) {

		/* Does nothing. */
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument of a {@link PrimitiveType} and
	 * without result.
	 */
	protected void voidOperationWithStringArgument(String arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}
		// no else.
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument of a {@link PrimitiveType} and
	 * without result.
	 */
	protected void voidOperationWithBooleanMultipleArgument(boolean[] arg1) {

		if (arg1.length == 0) {
			throw new NullPointerException();
		}
		// no else.
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument of a {@link CollectionType} and
	 * without result.
	 */
	protected void voidOperationWithStringMultipleArgument(List<String> arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}
		// no else.
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument and without result.
	 */
	protected void voidOperationWithObjectArgument(Class1 arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}
		// no else.
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument and without result.
	 */
	protected void voidOperationWithEnumerationLiteralMultipleArgument(
			List<Enumeration1> arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}
		// no else.
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument and without result.
	 */
	protected void voidOperationWithEnumerationLiteralArgument(Enumeration1 arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}
		// no else.
	}

	/**
	 * A method to test the method
	 * {@link IModelInstanceObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, java.util.List)}
	 * with an {@link Operation} with an argument and without result.
	 */
	protected void voidOperationWithObjectMultipleArgument(List<Class1> arg1) {

		if (arg1 == null) {
			throw new NullPointerException();
		}
		// no else.
	}

	/**
	 * A method to test the method
	 * {@link IModelInstance#invokeStaticOperation(Operation, List)}.
	 */
	protected static String staticOperation() {

		return Class1.class.getCanonicalName();
	}
}