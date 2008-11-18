package tudresden.ocl20.interpreter.ui.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.actions.ActionDelegate;

import tudresden.ocl20.interpreter.IEnvironment;
import tudresden.ocl20.interpreter.IOclInterpreter;
import tudresden.ocl20.interpreter.internal.Environment;
import tudresden.ocl20.interpreter.internal.OclInterpreter;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

public class InterpretConstraintsDelegate extends ActionDelegate implements
		IWorkbenchWindowActionDelegate {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(InterpretConstraintsDelegate.class);

	private IWorkbenchWindow window;
	
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
	
	private boolean useCache = false;

	public void run(IAction action) {
		if (logger.isDebugEnabled()) {
			logger.debug("run(IAction action=" + action + ") - enter");
		}

		IModel model = ModelBusPlugin.getModelRegistry().getActiveModel();
		
		IModelInstanceProvider mip = model.getModelInstanceProvider();
		IModelInstance mi = ModelBusPlugin.getModelInstanceRegistry().getActiveModelInstance(model);
		
		mi.getCurrentSlAF().registerAdapters();    
		
		System.out.println(mi.getObjects());
		
		IEnvironment env = Environment.getGlobalEnvironment();
		env.setModelInstance(mi);

		IOclInterpreter interp = new OclInterpreter(env);
		interp.setUseCache(false);
		
		long start = System.currentTimeMillis();
		
		try {
			Namespace namespace = model.getRootNamespace();
			Iterator<Namespace> nsit = namespace.getNestedNamespace().iterator();
						
			while (nsit.hasNext()) {
				Namespace curNamespace = nsit.next();
				System.out.println("Rules for Namespace: " + curNamespace.getName());
				Iterator<Constraint> it = curNamespace.getOwnedRule().iterator();
				while (it.hasNext()) {
					Constraint ci = it.next();
					if (ci.getKind().equals(ConstraintKind.BODY)) {
						System.out.println("Adding body constraint into environment: " + ci.getSpecification());
						env.addConstraint(((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName(), ci);
					}
					else if (ci.getKind().equals(ConstraintKind.INITIAL)) {
						System.out.println("Adding initial value constraint into environment: " + ci.getSpecification());
						env.addConstraint(((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName() + "-initial", ci);
					}
					else if (ci.getKind().equals(ConstraintKind.DERIVED)) {
						System.out.println("Adding derived value constraint into environment: " + ci.getSpecification());
						env.addConstraint(((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName() + "-derive", ci);
					}
					else if (ci.getKind().equals(ConstraintKind.DEFINITION)) {
						System.out.println("Adding definition constraint into environment: " + ci.getSpecification());
						// when parser supports definition change following line
						// until then for testing one definition with name 'newAtt' is possible
						String featureName = ci.getDefinedFeature() != null ? ci.getDefinedFeature().getName() : "newAtt";
						String definedFeature = ((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName() + "::" + featureName;
						env.addConstraint(definedFeature, ci);
						System.out.println("Interpreting definition " + definedFeature 
								+ " from environment: " 
								+ interp.interpret(env.getConstraint(definedFeature), null));
//						interp.interpret(env.getBodyConstraint(definedFeature));
					}
					else if (ci.getKind().equals(ConstraintKind.POSTCONDITION)) {
						System.out.println("Preparing postcondition: " + ci.getSpecification());
						List<String> currentTypePath = new ArrayList<String>(
								Arrays.asList(((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName().split("::")));
						List<IModelObject> objects = mi.getObjectsOfKind(currentTypePath);
						if (objects != null)
						{
							Iterator<IModelObject> objectIt = objects.iterator();
							System.out.println("Preparing: " + ci.getSpecification().getBody());
							while (objectIt.hasNext()) {
								interp.prepare(ci, objectIt.next());
							}
						}
					}
				}
				it = curNamespace.getOwnedRule().iterator();
				while (it.hasNext()) {
					Constraint ci = it.next();
					if (ci.getKind().equals(ConstraintKind.INVARIANT)) {
						if (logger.isInfoEnabled())
							logger.info("Interpreting invariant '" + ci.getName() + "' - body: " + ci.getSpecification().getBody());
						List<String> currentTypePath = new ArrayList<String>(
							Arrays.asList(((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName().split("::")));
						List<IModelObject> objects = mi.getObjectsOfKind(currentTypePath);
						if (objects != null)
						{
							Iterator<IModelObject> objectIt = objects.iterator();
							System.out.println("Interpreting invariant '" + ci.getName() + "' - body: " + ci.getSpecification().getBody());
							while (objectIt.hasNext()) {
								IModelObject mo = objectIt.next();
//								OclRoot self = mo.getOclObject();
//								OclRoot self = objects.get(0).getOclObject();
//								env.addVar("self", self);
								System.out.println("Result for " + mo + ": ");
								interp.interpret(ci, mo);
//								System.out.println("     " + interp.interpret(ci).toString());
//								interp.interpret(ci);
							}
							if (objects.size() == 0) {
//								env.addVar("self", null);
								System.out.println("Maybe static reference: ");
								System.out.println("     " + interp.interpret(ci, null).toString());
//								interp.interpret(ci);
							}
						}
					}
					else if (ci.getKind().equals(ConstraintKind.PRECONDITION)) {
						if (logger.isInfoEnabled())
							logger.info("Interpreting precondition '" + ci.getName() + "' - body: " + ci.getSpecification().getBody());
						List<String> currentTypePath = new ArrayList<String>(
							Arrays.asList(((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName().split("::")));
						List<IModelObject> objects = mi.getObjectsOfKind(currentTypePath);
						if (objects != null)
						{
							Iterator<IModelObject> objectIt = objects.iterator();
							System.out.println("Interpreting precondition '" + ci.getName() + "' - body: " + ci.getSpecification().getBody());
							env.addVar("value", mi.getFactory().createOclInteger(5));
							while (objectIt.hasNext()) {
								IModelObject mo = objectIt.next();
//								OclRoot self = mo.getOclObject();
//								OclRoot self = objects.get(0).getOclObject();
//								env.addVar("self", self);
								System.out.println("Result for " + mo + ": ");
								interp.interpret(ci, mo);
//								System.out.println("     " + interp.interpret(ci).toString());
//								interp.interpret(ci);
							}
							if (objects.size() == 0) {
//								env.addVar("self", null);
								System.out.println("Maybe static reference: ");
								System.out.println("     " + interp.interpret(ci, null).toString());
							}
						}
					}
					else if (ci.getKind().equals(ConstraintKind.POSTCONDITION)) {
						if (logger.isInfoEnabled())
							logger.info("Interpreting postcondition '" + ci.getName() + "' - body: " + ci.getSpecification().getBody());
						List<String> currentTypePath = new ArrayList<String>(
							Arrays.asList(((NamedElement)ci.getConstrainedElement().get(0)).getQualifiedName().split("::")));
						List<IModelObject> objects = mi.getObjectsOfKind(currentTypePath);
						if (objects != null)
						{
							Iterator<IModelObject> objectIt = objects.iterator();
							System.out.println("Interpreting postcondition '" + ci.getName() + "' - body: " + ci.getSpecification().getBody());
							env.addVar("result", mi.getFactory().createOclInteger(7));
							while (objectIt.hasNext()) {
								IModelObject mo = objectIt.next();
//								OclRoot self = mo.getOclObject();
//								OclRoot self = objects.get(0).getOclObject();
//								env.addVar("self", self);
								System.out.println("Result for " + mo + ": ");
								interp.interpret(ci, mo);
//								System.out.println("     " + interp.interpret(ci).toString());
//								interp.interpret(ci);
							}
							if (objects.size() == 0) {
//								env.addVar("self", null);
								System.out.println("Maybe static reference: ");
								System.out.println("     " + interp.interpret(ci, null).toString());
//								interp.interpret(ci);
							}
						}
					}
				}
			}
		} catch (ModelAccessException e) {
			logger.error("run(IAction)", e);

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long end = System.currentTimeMillis();
//		System.out.println("Duration: " + (end-start));
		
		env.clearCache();

		if (logger.isDebugEnabled()) {
			logger.debug("run(IAction) - exit");
		}
	}
	
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

}
