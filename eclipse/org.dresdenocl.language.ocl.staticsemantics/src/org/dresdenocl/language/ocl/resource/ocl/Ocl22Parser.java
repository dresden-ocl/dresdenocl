package org.dresdenocl.language.ocl.resource.ocl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import scala.Tuple2;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.language.ocl.resource.ocl.mopp.OclResource;
import org.dresdenocl.language.ocl.staticsemantics.OclStaticSemanticsException;
import org.dresdenocl.language.ocl.staticsemantics.postporcessor.OclStaticSemanticsProvider;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.parser.IOclParser;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.parser.SemanticException;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Type;

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

	public List<Constraint> doParse(IModel model, URI uri)
			throws ParseException {
		return doParse(model, uri, true);
	}

	public List<Constraint> doParse(IModel model, URI uri, boolean addToModel)
			throws ParseException {
		try {
			Collection<Constraint> alreadyParsedConstraints = model
					.getConstraints();
			try {
				// FIXME mt: is this the right place to do this?
				// It is wrong inside the static semantics module as the
				// reference resolver can
				// activate the computation of certain attributes before the
				// overall static
				// semantics is computed.

				// clear all caches first
				org.kiama.attribution.Attribution$.MODULE$.resetMemo();
				org.dresdenocl.attributegrammar.integration.kiama.AttributableEObject$.MODULE$
						.clearCache();

				ResourceSet rs = new ResourceSetImpl();
				OclResource resource = new OclResource(uri);
				rs.getResources().add(resource);
				resource.setModel(model);
				resource.load(Collections.EMPTY_MAP);

				List<Constraint> result = staticSemanticsAnalysis(resource);

				/* Probably remove the constraints from the model again. */
				if (!addToModel)
					model.removeConstraints(result);
				else if (result.size() > 0) {
					model.setChanged();
					model.notifiyListeners();
				}
				// no else.

				return result;
			}

			catch (IOException e) {
				/* Probably remove newly parsed constraints. */
				Collection<Constraint> newConstraints = model.getConstraints();
				newConstraints.removeAll(alreadyParsedConstraints);
				model.removeConstraints(newConstraints);
				throw new ParseException(e.getMessage(), e);
			}

			catch (ParseException e) {
				/* Probably remove newly parsed constraints. */
				Collection<Constraint> newConstraints = model.getConstraints();
				newConstraints.removeAll(alreadyParsedConstraints);
				model.removeConstraints(newConstraints);
				throw e;
			}
		}

		catch (IllegalArgumentException e) {
			throw new ParseException(
					"Was unable to remove parsed constraints from the given IModel again.",
					e);
		}

		catch (ModelAccessException e) {
			throw new ParseException(
					"Was unable to remove parsed constraints from the given IModel again.",
					e);
		}
	}

	public List<Constraint> parseOclString(String oclCode, IModel model)
			throws ParseException {
		return parseOclString(oclCode, model, true);
	}

	public List<Constraint> parseOclString(String oclCode, IModel model,
			boolean addToModel) throws ParseException {
		try {
			Collection<Constraint> alreadyParsedConstraints = model
					.getConstraints();
			ResourceSet rs = new ResourceSetImpl();
			OclResource resource = new OclResource(
					URI.createFileURI("temp.ocl"));
			rs.getResources().add(resource);
			resource.setModel(model);
			try {
				resource.load(new ByteArrayInputStream(oclCode.getBytes()),
						null);

				List<Constraint> result = staticSemanticsAnalysis(resource);

				/* Probably remove the constraints from the model again. */
				if (!addToModel)
					model.removeConstraints(result);
				// no else.

				return result;
			}

			catch (IOException e) {
				/* Probably remove newly parsed constraints. */
				Collection<Constraint> newConstraints = model.getConstraints();
				newConstraints.removeAll(alreadyParsedConstraints);
				model.removeConstraints(newConstraints);
				throw new ParseException(e.getMessage(), e);
			}

			catch (ParseException e) {
				/* Probably remove newly parsed constraints. */
				Collection<Constraint> newConstraints = model.getConstraints();
				newConstraints.removeAll(alreadyParsedConstraints);
				model.removeConstraints(newConstraints);
				throw e;
			}
		}

		catch (IllegalArgumentException e) {
			throw new ParseException(
					"Was unable to remove parsed constraints from the given IModel again.",
					e);
		}

		catch (ModelAccessException e) {
			throw new ParseException(
					"Was unable to remove parsed constraints from the given IModel again.",
					e);
		}
	}

	/**
	 * 
	 * @param eObject
	 *            context for which the variables are defined
	 * @return a Tuple with 2 Lists: the first List holds all implicit variables
	 *         (i.e., self, iterator variables), while the second List contains
	 *         all explicitly defined variables (i.e., operation parameters,
	 *         from let expressions, iterator variables, result variable in
	 *         postconditions).
	 */
	public Variables getVariables(EObject eObject) {
		Resource res = eObject.eResource();
		if (res instanceof OclResource) {
			org.dresdenocl.language.ocl.staticsemantics.OclStaticSemantics staticSemantics = OclStaticSemanticsProvider
					.getStaticSemantics((OclResource) res);
			Variables result = new Variables();
			Tuple2<List<Variable>, List<Variable>> variables = staticSemantics
					.getVariables(eObject);
			result.setImplicitVariables(variables._1());
			result.setExplicitVariables(variables._2());
			return result;
		} else
			throw new IllegalArgumentException("Resource of EObject " + eObject
					+ " is not of type OclResource.");
	}

	public Type getOclType(EObject eObject) {
		Resource res = eObject.eResource();
		if (res instanceof OclResource) {
			org.dresdenocl.language.ocl.staticsemantics.OclStaticSemantics staticSemantics = OclStaticSemanticsProvider
					.getStaticSemantics((OclResource) res);
			return staticSemantics.getOclType(eObject);
		} else
			throw new IllegalArgumentException("Resource of EObject " + eObject
					+ " is not of type OclResource.");
	}

	private List<Constraint> staticSemanticsAnalysis(OclResource resource)
			throws ParseException, SemanticException {
		checkForErrors(resource);

		org.dresdenocl.language.ocl.staticsemantics.OclStaticSemantics staticSemantics = OclStaticSemanticsProvider
				.getStaticSemantics(resource);
		List<Constraint> constraints;
		try {
			constraints = new ArrayList<Constraint>(
					staticSemantics.cs2EssentialOcl(resource.getContents().get(
							0)));
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
