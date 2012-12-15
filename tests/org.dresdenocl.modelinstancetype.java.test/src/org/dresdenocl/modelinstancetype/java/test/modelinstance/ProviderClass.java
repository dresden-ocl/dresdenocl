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
package org.dresdenocl.modelinstancetype.java.test.modelinstance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.Class1;
import org.dresdenocl.modelinstancetype.test.testmodel.Class2;
import org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass;
import org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.Interface1;
import org.dresdenocl.modelinstancetype.test.testmodel.Interface2;
import org.dresdenocl.modelinstancetype.test.testmodel.Interface3;
import org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass;

/**
 * <p>
 * This {@link Class} is the {@link ProviderClass} for the
 * {@link IModelInstance} used for testing.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class ProviderClass {

	/**
	 * <p>
	 * Returns the {@link Object}s of the IModelInstance.
	 * </p>
	 * 
	 * @return The {@link Object}s of the IModelInstance.
	 */
	public static List<Object> getModelObjects() {

		List<Object> result;
		ContainerClass containerClass;

		containerClass = new ContainerClass();

		containerClass.primitiveTypeProviderClassInstances =
				new HashSet<PrimitiveTypeProviderClass>();
		containerClass.collectionTypeProviderClassInstances =
				new HashSet<CollectionTypeProviderClass>();
		containerClass.enumerationLiteralProviderClassInstances =
				new HashSet<EnumerationLiteralProviderClass>();

		containerClass.class1Instances = new HashSet<Class1>();
		containerClass.class2Instances = new HashSet<Class2>();
		containerClass.staticPropertyAndOperationClassInstances =
				new HashSet<StaticPropertyAndOperationClass>();
		containerClass.interface1Instances = new HashSet<Interface1>();
		containerClass.interface2Instances = new HashSet<Interface2>();
		containerClass.interface3Instances = new HashSet<Interface3>();

		containerClass.copyableClassInstances = new HashSet<CopyableClass>();
		containerClass.nonCopyableClassInstances = new HashSet<NonCopyableClass>();

		containerClass.primitiveTypeProviderClassInstances
				.add(new PrimitiveTypeProviderClass());

		containerClass.collectionTypeProviderClassInstances
				.add(new CollectionTypeProviderClass());

		containerClass.enumerationLiteralProviderClassInstances
				.add(new EnumerationLiteralProviderClass());

		containerClass.class1Instances.add(new Class1());
		containerClass.class1Instances.add(new C1Implementation());

		containerClass.class2Instances.add(new Class2());

		containerClass.interface1Instances.add(new I1Implementation());

		I1I2Implementation i1i2Implementation = new I1I2Implementation();
		containerClass.interface1Instances.add(i1i2Implementation);
		containerClass.interface2Instances.add(i1i2Implementation);

		containerClass.interface3Instances.add(new I3Implementation());

		I2C1Implementation i2c1Implementation = new I2C1Implementation();
		containerClass.interface2Instances.add(i2c1Implementation);
		containerClass.class1Instances.add(i2c1Implementation);

		I3C2Implementation i3c2Implementation = new I3C2Implementation();
		containerClass.interface3Instances.add(i3c2Implementation);
		containerClass.class2Instances.add(i3c2Implementation);

		StaticPropertyAndOperationClass staticPropertyAndOperationClass =
				new StaticPropertyAndOperationClass();
		containerClass.staticPropertyAndOperationClassInstances
				.add(staticPropertyAndOperationClass);

		containerClass.copyableClassInstances.add(new ClonableCopyableClass());
		containerClass.copyableClassInstances.add(new InitializableCopyableClass());

		containerClass.nonCopyableClassInstances.add(new NonCopyableClass(null));

		result = new ArrayList<Object>();
		result.add(containerClass);

		return result;
	}
}