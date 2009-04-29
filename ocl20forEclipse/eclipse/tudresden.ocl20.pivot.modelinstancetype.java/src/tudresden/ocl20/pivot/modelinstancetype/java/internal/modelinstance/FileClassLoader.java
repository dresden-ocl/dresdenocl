/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the UML2 Meta Model of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <p>
 * A ClassLoader to load a class from a given location of the file system.
 * </p>
 * 
 * @author Claas Wilke
 */
public class FileClassLoader extends ClassLoader {

	private String classPath;

	/**
	 * <p>
	 * Creates a new {@link FileClassLoader} with a given srcPath.
	 * </p>
	 * 
	 * @param classPath
	 *            The path of the source folder where the classes shall be
	 *            loaded from. <strong>Must contain class files, not java
	 *            files!</strong>
	 */
	public FileClassLoader(String classPath) {
		this.classPath = classPath;
	}

	/**
	 * <p>
	 * Finds a {@link Class} to a given canonical name.
	 * </p>
	 * 
	 * @param canonicalClassName
	 *            The canonical name of the {@link Class} which shall be found.
	 * @return A {@link Class} to a given canonical name.
	 */
	public Class<?> findClass(String canonicalClassName)
			throws ClassNotFoundException {

		Class<?> result;
		byte[] classBytes;

		classBytes = loadClassData(canonicalClassName);

		if (classBytes == null) {

			throw new ClassNotFoundException(canonicalClassName);
		}

		else {
			result = defineClass(canonicalClassName, classBytes, 0,
					classBytes.length);
		}

		return result;
	}

	/**
	 * <p>
	 * Returns a found class as byte array.
	 * </p>
	 * 
	 * @param canonicalClassName
	 *            The canonical name of the class which data shall be returned.
	 * @return byte[] theClassBytes
	 */
	private byte[] loadClassData(String canonicalClassName) {

		byte result[];

		try {
			File classFile;
			FileInputStream fiStream;

			/* Convert the file name. */
			canonicalClassName = classPath
					+ canonicalClassName.replace('.', File.separatorChar)
					+ ".class";

			/* Open the File. */
			classFile = new File(canonicalClassName);
			fiStream = new FileInputStream(classFile);

			result = new byte[(int) classFile.length()];

			fiStream.read(result);
			fiStream.close();

			return result;
		}

		catch (FileNotFoundException e) {
			/* Result in null. */
			result = null;
		}

		catch (IOException e) {
			/* Result in null. */
			result = null;
		}

		return result;
	}
}