package tudresden.ocl20.pivot.essentialocl.standardlibrary.provider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.impl.TypesPackageImpl;
import tudresden.ocl20.pivot.essentialocl.types.util.TypeResolver;

/**
 * <p>
 * This implementation of {@link IOclLibraryProvider} supports loading the OCL
 * standard library from a location in the file system or from an URI.
 * </p>
 * <p>
 * Thus, it has to be used by stand-alone applications of DresdenOCL as the
 * standard {@link OclLibraryProvider} requires a running Eclipse instance.
 * </p>
 * 
 * @see ModelBusPlugin#setOclLibraryProvider(IOclLibraryProvider)
 * 
 * @author Michael Thiele
 * 
 */
public class StandaloneOclLibraryProvider implements IOclLibraryProvider {

	/** The loaded OCL standard library. */
	private OclLibrary oclLibrary;

	/** An URI-based location of the OCL standard library. */
	private URI oclStandardLibraryLocation;

	/** In case the OCL standard library is given as a InputStream. */
	private InputStream inputStream;

	private TypeResolver typeResolver;

	/**
	 * The provider will load the OCL standard library using the given URI.
	 * 
	 * @param oclStandardLibraryLocation
	 *          the {@link URI} pointing to the OCL standard library
	 */
	public StandaloneOclLibraryProvider(URI oclStandardLibraryLocation) {

		this.oclStandardLibraryLocation = oclStandardLibraryLocation;
	}

	/**
	 * The provider will load the OCL standard library using the given
	 * {@link File}'s location.
	 * 
	 * @param oclStandardLibraryFile
	 *          the {@link File} pointing to the OCL standard library
	 */
	public StandaloneOclLibraryProvider(File oclStandardLibraryFile) {

		oclStandardLibraryLocation =
				URI.createFileURI(oclStandardLibraryFile.getAbsolutePath());
	}

	/**
	 * The provider will load the OCL standard library using the given {@link URL}
	 * .
	 * 
	 * @param url
	 *          the {@link URL} of the OCL standard library
	 */
	public StandaloneOclLibraryProvider(InputStream inputStream) {

		oclStandardLibraryLocation = URI.createFileURI("temp.types");
		this.inputStream = inputStream;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.model.IOclLibraryProvider#getOclLibrary()
	 */
	public OclLibrary getOclLibrary() {

		if (oclLibrary == null) {

			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(
					"types", new XMIResourceFactoryImpl());
			TypesPackageImpl.eINSTANCE.eClass();

			ResourceSet rs = new ResourceSetImpl();

			Resource r = rs.createResource(oclStandardLibraryLocation);

			try {
				if (inputStream != null)
					r.load(inputStream, Collections.EMPTY_MAP);
				else
					r.load(Collections.EMPTY_MAP);
			} catch (IOException e) {
				e.printStackTrace();
			}

			oclLibrary = (OclLibrary) r.getContents().get(0);
		}

		return oclLibrary;
	}
	
	public TypeResolver getTypeResolver() {
	
		if (typeResolver == null) {
			typeResolver = new TypeResolver(getOclLibrary());
		}
		
		return typeResolver;
	}

}
