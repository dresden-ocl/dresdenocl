package tudresden.ocl20.pivot.tools.codegen.declarativ;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.tools.codegen.declarativ.impl.Ocl2DeclCode;
import tudresden.ocl20.pivot.tools.codegen.declarativ.impl.Ocl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.exception.Ocl2CodeException;

/**
 * <p>
 * This class provides methods to create declarative code generators for code
 * generation from loaded {@link IModel}s.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class Ocl2DeclCodeFactory {

	private static Ocl2DeclCodeFactory myInstance;

	/**
	 * <p>
	 * A private constructor to enforce the Singleton.
	 * </p>
	 */
	private Ocl2DeclCodeFactory() {

		/* Remains empty. */
	}

	/**
	 * @return The only instance of {@link Ocl2DeclCodeFactory}.
	 */
	public static Ocl2DeclCodeFactory getInstance() {

		if (myInstance == null) {
			myInstance = new Ocl2DeclCodeFactory();
		}
		// no else.

		return myInstance;
	}

	/**
	 * <p>
	 * Creates an {@link IOcl2DeclCode} code generator.
	 * </p>
	 * 
	 * @return A code generator which generates ddl code for loaded OCL
	 *         expressions.
	 * @throws Ocl2CodeException
	 *           Thrown if the initialization of a new Java code generator fails.
	 */
	public IOcl2DeclCode createOcl2DeclCodeGenerator() throws Ocl2CodeException {

		IOcl2DeclCode result;

		result = new Ocl2DeclCode();

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IOcl2DeclCode} code generator with given
	 * {@link IOcl22CodeSettings}.
	 * </p>
	 * 
	 * @return A code generator which generates ddl
	 * @throws Ocl2CodeException
	 *           Thrown if the initialization of a new Ocl2DeclCode code generator
	 *           fails.
	 */
	public IOcl2DeclCode createOcl2DeclCodeGenerator(IOcl2DeclSettings settings)
			throws Ocl2CodeException {

		IOcl2DeclCode result;

		result = new Ocl2DeclCode();
		result.setSettings(settings);

		return result;
	}

	/**
	 * <p>
	 * Creates {@link IOcl2DeclSettings} that can be used to configure a
	 * Declarative code generator.
	 * </p>
	 * 
	 * @return {@link IOcl2DeclSettings} that can be used to configure a
	 *         Declarative code generator.
	 */
	public IOcl2DeclSettings createOcl2DeclCodeSettings() {

		IOcl2DeclSettings result;

		result = new Ocl2DeclSettings();

		return result;
	}

}