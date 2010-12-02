package tudresden.ocl20.pivot.language.ocl.resource.ocl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tudresden.ocl20.pivot.language.ocl.resource.ocl.mopp.OclResource;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemanticsException;
import tudresden.ocl20.pivot.language.ocl.staticsemantics.postporcessor.OclStaticSemanticsProvider;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.parser.IOclParser;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.parser.SemanticException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class Ocl22Parser implements IOclParser {

	private static Ocl22Parser instance;

	private Ocl22Parser() {

	}

	private static Ocl22Parser instance() {
		if (instance == null)
			instance = new Ocl22Parser();
		return instance;
	}

	public static Ocl22Parser INSTANCE = instance();

	public List<Constraint> doParse(IModel model, URI uri) throws ParseException {
		return doParse(model, uri, true);
	}

	public List<Constraint> doParse(IModel model, URI uri, boolean addToModel)
			throws ParseException {
		try {
			ResourceSet rs = new ResourceSetImpl();
			OclResource resource = new OclResource(uri);
			rs.getResources().add(resource);
			resource.setModel(model);
			resource.load(Collections.EMPTY_MAP);

			return staticSemanticsAnalysis(resource);
		} catch (IOException e) {
			throw new ParseException(e.getMessage(), e);
		}
	}

	public List<Constraint> parseOclString(String oclCode, IModel model)
			throws ParseException {
		return parseOclString(oclCode, model, true);
	}

	public List<Constraint> parseOclString(String oclCode, IModel model,
			boolean addToModel) throws ParseException {
		ResourceSet rs = new ResourceSetImpl();
		OclResource resource = new OclResource(URI.createFileURI("temp.ocl"));
		rs.getResources().add(resource);
		resource.setModel(model);
		try {
			resource.load(new ByteArrayInputStream(oclCode.getBytes()), null);

			return staticSemanticsAnalysis(resource);
		} catch (IOException e) {
			throw new ParseException("Unable to load OCL file.", e);
		}
	}

	private List<Constraint> staticSemanticsAnalysis(OclResource resource)
			throws ParseException, SemanticException {
		checkForErrors(resource);

		tudresden.ocl20.pivot.language.ocl.staticsemantics.OclStaticSemantics staticSemantics = OclStaticSemanticsProvider
				.getStaticSemantics(resource);
		List<Constraint> constraints;
		try {
			constraints = staticSemantics.cs2EssentialOcl(resource.getContents().get(
					0));
		} catch (OclStaticSemanticsException e) {
			throw new SemanticException(e.getMessage(), e);
		}

		checkForErrors(resource);

		return constraints;
	}

	private void checkForErrors(OclResource resource) throws ParseException {

		if (!resource.getErrors().isEmpty()) {
			StringBuffer errorMsg = new StringBuffer();
			for (Resource.Diagnostic error : resource.getErrors()) {
				errorMsg.append("line " + error.getLine() + ", coloumn "
						+ error.getColumn() + ": " + error.getMessage()
						+ System.getProperty("line.separator"));
			}
			throw new SemanticException(errorMsg.toString());
		}
	}
}
