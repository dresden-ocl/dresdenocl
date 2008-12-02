package tudresden.ocl20.pivot.models.mdr.internal.provider;

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