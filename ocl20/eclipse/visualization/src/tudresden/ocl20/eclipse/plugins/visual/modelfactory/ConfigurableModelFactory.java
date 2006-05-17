package tudresden.ocl20.eclipse.plugins.visual.modelfactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.eclipse.plugins.visual.exceptions.ConfigurableModelFactoryException;
import tudresden.ocl20.eclipse.plugins.visual.exceptions.ModelFactoryException;
import tudresden.ocl20.eclipse.plugins.visual.model.DefaultVisualConnection;
import tudresden.ocl20.eclipse.plugins.visual.model.DefaultVisualModel;
import tudresden.ocl20.eclipse.plugins.visual.model.DefaultVisualNode;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualConnection;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualModel;
import tudresden.ocl20.eclipse.plugins.visual.model.VisualNode;

/**
 * Configurable Model Factory
 * 
 * This Model Factory is a base class for a specific model factory. 
 * It produces a VisualModel by recursing through an object model. It is 
 * configurable by several parameters.
 * 
 * Working principle: This class takes a start object and calls all its 
 * parameterless methods. All returned Objects are new start objects. The 
 * algorithm work recursively.
 * 
 * Possible restrictions for the algorithm:
 * 
 * doNotCall-List: Use this list for method names that should not 
 * be called
 * 
 * includeClasses: Use this list to define accepted types. Only objects that 
 * are instances of one of these types were accepted. Note that the objects type
 * must not be exactly the type in the list. It can be a subclass too.
 * 
 * excludeClasses: All Objects with a type from this list will be dropped.
 * 
 * methodPrefix: Call only methods with a specified prefix.
 * 
 * labelMethod: Method to determine the name of the object.
 * 
 * @author Kai-Uwe Gärtner
 *
 */
public abstract class ConfigurableModelFactory implements ModelFactory {
	
	/**
	 * Inner Class for a connected Element.
	 * @author Kai
	 *
	 */
	private class ConnectedElement {
		Object element;

		String connectionDecoration;

		public ConnectedElement(Object element, String conDeco) {
			this.element = element;
			this.connectionDecoration = conDeco;
		}

		/**
		 * @return Returns the connectionDecoration.
		 */
		public String getConnectionDecoration() {
			return connectionDecoration;
		}

		/**
		 * @param connectionDecoration
		 *            The connectionDecoration to set.
		 */
		public void setConnectionDecoration(String connectionDecoration) {
			this.connectionDecoration = connectionDecoration;
		}

		/**
		 * @return Returns the element.
		 */
		public Object getElement() {
			return element;
		}

		/**
		 * @param element
		 *            The element to set.
		 */
		public void setElement(Object element) {
			this.element = element;
		}

	}

	/*
	 * private String oclfile;
	 * 
	 * private String xmifile;
	 */

	protected List<String> doNotCall;
	
	protected List<Class> includeClasses;
	
	protected List<Class> excludeClasses;


	protected String methodPrefix = "";
	
	protected String labelMethod = "toString()";
	

	/*
	 * Constructor. Initializes the lists.
	 */
	public ConfigurableModelFactory() {
		doNotCall = new ArrayList<String>();
		includeClasses=new ArrayList<Class>();
		excludeClasses=new ArrayList<Class>();
		init();
	}
	
	/**
	 * Overwrite this Method for Configuring the ModelFactory.
	 *
	 */
	public abstract void init();


	/**
	 * Returns the start object fot the CMF.
	 * @param context model-specific context
	 * @return the start object
	 */
	public abstract Object getStartObject(Object context);
	
	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.modelfactory.ModelFactory#getVisualModel(java.lang.Object)
	 */
	public VisualModel getVisualModel(Object context) throws ModelFactoryException{
		DefaultVisualModel vmodel = new DefaultVisualModel();
		Object start=getStartObject(context);
		try {
		if (start!=null) processObject(vmodel, start, null, "");
		}
		catch (ConfigurableModelFactoryException e){
			throw new ModelFactoryException(e);
		}
		return vmodel;
	}
	
	/**
	 * Processes an object and it's children recursively
	 * @param vmodel the produced visual model
	 * @param obj the proecessed object
	 * @param node the VisualNode of the parent object
	 * @param connectionLabel a label for the connection
	 */
	protected void processObject(VisualModel vmodel, Object obj,
			VisualNode node, String connectionLabel) throws ConfigurableModelFactoryException{
		// Finding node for ModelElement
		VisualNode elementVNode;
		elementVNode = vmodel.getNodeByObject(obj);
		if (elementVNode == null) {
			String nodeLabel = "";
			try {
				Method m = obj.getClass().getMethod(labelMethod, new Class[0]);
				if (m != null) {
					nodeLabel = m.invoke(obj,new Object[0]).toString();
				}
			} catch (Exception e) {

			}
			elementVNode = getNewNodeInstance();
			elementVNode.setName(nodeLabel + ":"
					+ obj.getClass().getSimpleName());
			elementVNode.setObject(obj);
			vmodel.addNode(elementVNode);
		}
		if (node != null) {
			VisualConnection vc=vmodel.getConnection(node, elementVNode);
			// Stop if Connection exists (Loop protection)
			if (vc != null)
				return;
			//Stop if reverse Connection exists
			if (vmodel.getConnection(elementVNode,node)!=null){
				vmodel.getConnection(elementVNode,node).setTargetLabel(connectionLabel);
				return;
			}
			vc=new DefaultVisualConnection(node,
					elementVNode);
			vc.setSourceLabel(connectionLabel);
			vmodel.addConnection(vc);
		}

		List<ConnectedElement> l = getConnectedElements(obj);
		Iterator it = l.iterator();
		while (it.hasNext()) {
			ConnectedElement ce = (ConnectedElement) it.next();
			processObject(vmodel, ce.getElement(), elementVNode, ce.getConnectionDecoration());
		}
	}

	/**
	 * Generates a ConnectedElement and adds it to a list.
	 * @param l list of connected Elements
	 * @param o the Object
	 * @param conDeco the connection label
	 */
	private void addNotNull(List<ConnectedElement> l, Object o, String conDeco) {
		if (o != null) {
			boolean add=true;
			Iterator it=l.iterator();
			while (it.hasNext()){
				ConnectedElement e=(ConnectedElement)it.next();
				if (e.getElement()==o) add=false;
			}
			if (add) l.add(new ConnectedElement(o, conDeco));
		}
	}

	/**
	 * Tests if a method is in the doNotCall-List.
	 * @param s
	 * @return
	 */
	private boolean inDoNotCallList(String s) {
		Iterator it = doNotCall.iterator();
		while (it.hasNext()) {
			if (((String) it.next()).equals(s))
				return true;
		}
		return false;
	}
	
	/**
	 * Checks a type
	 * @param type type
	 * @return true, if the type is in includeClasses and not in excludeClasses
	 */
	private boolean typeOk(Object o){
		//System.out.println("TypeOK: "+type.getSimpleName());
		boolean ok=false;
		Iterator it;
		it=includeClasses.iterator();
		while (it.hasNext()){
			if (((Class)it.next()).isInstance(o)) ok=true;
		}
		it=excludeClasses.iterator();
		while (it.hasNext()){
			if (((Class)it.next()).isInstance(o)) ok=false;
		}
		return ok;
	}

	/**
	 * Returns all connected objects of an object by respecting the configuration
	 * @param obj the object
	 * @return list of connected objects
	 * @throws ConfigurableModelFactoryException 
	 */
	private List<ConnectedElement> getConnectedElements(Object obj) throws ConfigurableModelFactoryException {
		ArrayList<ConnectedElement> l = new ArrayList<ConnectedElement>();
		Method[] m = obj.getClass().getMethods();
		for (int i = 0; i < m.length; i++) {
			Method meth = m[i];
			String methodName = meth.getName();
			// pick methods with specific prefix and without parameters
			//System.out.println("Prcessed Method: "+methodName);
			if (methodName.startsWith(methodPrefix)
					&& (meth.getParameterTypes().length == 0)
					&& (!inDoNotCallList(methodName))) {
				Object result=null;
				try {
					result = meth.invoke(obj, new Object[0]);
				}catch (InvocationTargetException e){
					//e.printStackTrace();
				}
				catch (Exception e) {
					throw new ConfigurableModelFactoryException("Error calling Method: "+methodName+" on Type "+obj.getClass().getSimpleName(),e);
				}
			
				//Check if return-type is a collection
				if ((result!=null)&&result instanceof Collection) {
						Collection c = (Collection) result;
						if (c!=null){
							Iterator it = c.iterator();
							while (it.hasNext()) {
								Object item = it.next();
								if (typeOk(item)) {
									addNotNull(l, item, methodName.substring(methodPrefix.length(),methodName.length()));
								}
							}
						}
				}else
						if ((result!=null)&&typeOk(result)) addNotNull(l, result, methodName.substring(methodPrefix.length(),methodName.length()));
			}
		}
		return l;
	}


	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.modelfactory.ModelFactory#loadRessource(java.io.File)
	 */
	public abstract void loadRessource(File file);


	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.modelfactory.ModelFactory#getContexts()
	 */
	public abstract Collection getContextList();

	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.modelfactory.ModelFactory#getContextDisplayName()
	 */
	public abstract String getContextDisplayName(Object context);


	/* (non-Javadoc)
	 * @see tudresden.ocl20.eclipse.plugins.visual.modelfactory.ModelFactory#getContextByDisplayName(java.lang.String)
	 */
	public Object getContextByDisplayName(String displayName) {
		Collection contexts=getContextList();
		Iterator it=contexts.iterator();
		while (it.hasNext()){
			Object o=it.next();
			if (getContextDisplayName(o).equals(displayName)) return o;
		}
		return null;
	}

	/**
	 * @return Returns the doNotCall.
	 */
	public List<String> getDoNotCall() {
		return doNotCall;
	}


	/**
	 * @return Returns the excludeClasses.
	 */
	public List<Class> getExcludeClasses() {
		return excludeClasses;
	}


	/**
	 * @return Returns the includeClasses.
	 */
	public List<Class> getIncludeClasses() {
		return includeClasses;
	}
	
	public VisualNode getNewNodeInstance() {
		return new DefaultVisualNode();
	}
}
