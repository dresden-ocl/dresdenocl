/*
 * Created on 02.11.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.core.jmi.uml15.impl.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

import tudresden.ocl20.integration.ModelFacade;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.Namespace;
import tudresden.ocl20.core.jmi.uml15.datatypes.VisibilityKind;

/**
 * @author Mirko
 */
public abstract class NamespaceImpl extends ModelElementImpl implements
		tudresden.ocl20.core.jmi.uml15.core.Namespace {

	/**
	 * @param arg0
	 */
	protected NamespaceImpl(StorableObject arg0) 
	{
		super(arg0);
	}

	/**
	 * Returns all classifiers, which have no subclasses.
	 */
	public Collection getAllClassesWithoutSpez() 
	{
		HashSet result = new HashSet();
		Iterator it = this.getOwnedElement().iterator();
		while (it.hasNext())
		{
			Object o = it.next();
			if (o instanceof Classifier)
			{
				ClassifierImpl c = (ClassifierImpl)o;
				if (c.getSpecialisation().isEmpty())
					result.add(c);
			}
			else
				result.addAll(((NamespaceImpl)o).getAllClassesWithoutSpez());//result.add();
		}
		if (this instanceof Classifier)
		{
			ClassifierImpl c = (ClassifierImpl)this;
			if (c.getSpecialisation().isEmpty())
				result.add(c);
		}
		return result;
	}
	
	protected abstract Collection super_getOwnedElement();
    
	/**
     * Returns all elements within an namespace. If an instance of the class ModelFacade exists 
     * the method getOwnedElement() of the class ModelFacade is used.
     */
    public Collection getOwnedElement()
    {
    	Collection oclLib = new ArrayList();
    	Collection ownedElements = new ArrayList();
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		ownedElements.addAll(instance.getOwnedElements(this.refMofId()));
    		oclLib.addAll(super_getOwnedElement());
    		ownedElements.addAll(oclLib);
    		
    		return ownedElements;
    	}
    	
    	return super_getOwnedElement();
    }

}
