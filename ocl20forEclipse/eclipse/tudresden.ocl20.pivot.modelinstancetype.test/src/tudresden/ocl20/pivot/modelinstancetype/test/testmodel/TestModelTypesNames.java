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

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class contains provides contants for the names of all {@link Type}s that
 * are part of the test model of the {@link ModelInstanceTypeTestPlugin}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelTypesNames {

	/**
	 * The name of the {@link Namespace} in the {@link IModel} in that all the
	 * {@link Type}s are located.
	 */
	public static final String NAMESPACE_NAME_TESTMODEL =
			"tudresden::ocl20::pivot::modelinstancetype::test::testmodel";

	/**
	 * The name of the {@link Type} <code>Class1</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_CONTAINER_CLASS =
			NAMESPACE_NAME_TESTMODEL + "::ContainerClass";

	/**
	 * The name of the {@link Type} <code>Enumeration1</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_ENUMERATION1 =
			NAMESPACE_NAME_TESTMODEL + "::Enumeration1";

	/**
	 * The name of the {@link Type} <code>Class1</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_CLASS1 =
			NAMESPACE_NAME_TESTMODEL + "::Class1";

	/**
	 * The name of the {@link Type} <code>Class2</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_CLASS2 =
			NAMESPACE_NAME_TESTMODEL + "::Class2";

	/**
	 * The name of the {@link Type} <code>StaticPropertyAndOperationClass</code>
	 * that belongs to the {@link IModel}.
	 */
	public static final String TYPE_NAME_STATIC_PROPERTY_AND_OPERATION_CLASS =
			NAMESPACE_NAME_TESTMODEL + "::StaticPropertyAndOperationClass";

	/**
	 * The name of the {@link Type} <code>Interface1</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_INTERFACE1 =
			NAMESPACE_NAME_TESTMODEL + "::Interface1";

	/**
	 * The name of the {@link Type} <code>Interface2</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_INTERFACE2 =
			NAMESPACE_NAME_TESTMODEL + "::Interface2";

	/**
	 * The name of the {@link Type} <code>Interface3</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_INTERFACE3 =
			NAMESPACE_NAME_TESTMODEL + "::Interface3";

	/**
	 * The name of the {@link Type} <code>CopyableClass</code> that belongs to the
	 * {@link IModel}.
	 */
	public static final String TYPE_NAME_COPYABLE_CLASS =
			NAMESPACE_NAME_TESTMODEL + "::CopyableClass";

	/**
	 * The name of the {@link Type} <code>NonCopyableClass</code> that belongs to
	 * the {@link IModel}.
	 */
	public static final String TYPE_NAME_NON_COPYABLE_CLASS =
			NAMESPACE_NAME_TESTMODEL + "::NonCopyableClass";

	/**
	 * The name of the {@link Type} <code>PrimitiveTypeProviderClass</code> that
	 * belongs to the {@link IModel}.
	 */
	public static final String TYPE_NAME_PRIMITIVE_TYPE_PROVIDER_CLASS =
			NAMESPACE_NAME_TESTMODEL + "::PrimitiveTypeProviderClass";

	/**
	 * The name of the {@link Type} <code>PrimitiveTypeProviderClass</code> that
	 * belongs to the {@link IModel}.
	 */
	public static final String TYPE_NAME_COLLECTION_TYPE_PROVIDER_CLASS =
			NAMESPACE_NAME_TESTMODEL + "::CollectionTypeProviderClass";

	/**
	 * The name of the {@link Type} <code>EnumerationLiteralProviderClass</code>
	 * that belongs to the {@link IModel}.
	 */
	public static final String TYPE_NAME_ENUMERATION_LITERAL_PROVIDER_CLASS =
			NAMESPACE_NAME_TESTMODEL + "::EnumerationLiteralProviderClass";
}