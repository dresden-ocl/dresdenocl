/*
 * Created on 25.08.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.integration;

import java.io.PushbackReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.jmi.reflect.RefAssociation;
import javax.jmi.reflect.RefAssociationLink;
import javax.jmi.reflect.RefObject;

import org.netbeans.api.mdr.events.MDRChangeEvent;
import org.netbeans.api.mdr.events.MDRChangeListener;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.NetBeansRepository;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.RepositoryManager;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.impl.modelmanagement.ModelHelper;
import tudresden.ocl20.core.jmi.uml15.impl.uml15ocl.types.OclLibraryHelper;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Model;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.core.parser.astgen.Heritage;
import tudresden.ocl20.core.parser.astgen.LAttrAstGenerator;
import tudresden.ocl20.core.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.core.parser.sablecc.node.Start;
import tudresden.ocl20.core.parser.sablecc.parser.Parser;

/**
 * This class can be use to start the validation of a constraint.
 * @author Mirko 
 */
public class OCLChecker implements MDRChangeListener 
{
	public final static String DEBUG = "OCLChecker.DEBUG";
	
	public static String topPackageName = "topPackage";
	public static HashMap<Object, OCLChecker> instances = new HashMap<Object, OCLChecker>();
	
	private ArrayList<RefAssociation> assoc = new ArrayList<RefAssociation>();
	private ArrayList<RefObject> classes = new ArrayList<RefObject>();
	
	/**
	 * Returns the existing OCLChecker instance for the given object or creates a new instance. 
	 */
	public static OCLChecker getInstance(Object rootPackage)
	{
		if (instances.get(rootPackage) == null)
		{
			try 
			{
				OCLChecker instance =  new OCLChecker();	
				instance.initModel();
				instances.put(rootPackage, instance);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		return (OCLChecker) instances.get(rootPackage);         
	}
	
	/**
	 * Deletes all models in the MDR.
	 *
	 */
	private static void deleteModels() 
	{
		for (int i = 0; i < instances.size(); i++)
		{	
			((OCLChecker)instances.get(i)).deleteModel();
		}			
	}

	private static ModelManager mm = ModelManager.getInstance();   
    private Uml15Package model = null;

	private boolean rollBack;
    
    private OCLChecker()
    {
    	
    }	
	
    /**
     * Creates a new UML model in the MDR with one Model instance. 
     */
	public void initModel() throws Exception
    {     
		mm.beginTrans(true);
		model = (Uml15Package) mm.createOclModel(MetaModelConst.UML15, this.getUniqueName());
		Model rootModel = model.getModelManagement().getModel().createModel();
	    rootModel.setNameA(topPackageName);	 
	    model.getUml15ocl().getTypes().getOclLibrary().getInstance();
	    ((NetBeansRepository)RepositoryManager.getRepository()).addRepositoryListener(this);
		mm.endTrans(false);    	
    } 
	
	/**
	 * Sets the ModelFacade for the model of the OCLChecker instance.
	 */
	public void setModelFacade(ModelFacade facade) throws Exception
	{
		ModelFacade.addModelFacade(this.model.refMofId(), facade);
		ModelFacade instance = ModelFacade.getInstance(this.model.refMofId());
		Model topPackage = ModelHelper.getInstance(this.model).getTopPackage();
		
		Iterator it = instances.keySet().iterator();
		while (it.hasNext())
		{
			Object caseTop = it.next();
			if (instances.get(caseTop) != null)
				if (instances.get(caseTop).equals(this))
						instance.addRefObject(topPackage.refMofId(), caseTop);					
		}
	}
	
	/**
	 * Validates the given constraint. 
	 */
	public void validate(String constraint) throws Exception
	{
		if (this.model != null)
		{
			OclModel oclModel = null;
			Start cst = null;
			try
			{
				this.rollBack();
				this.assoc.clear();
				this.classes.clear();
				oclModel =  new OclModel(MetaModelConst.UML15, model);
				Lexer lexer = new Lexer (new PushbackReader(new StringReader(constraint)));
				Parser parser = new Parser(lexer);			
				cst = parser.parse();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw e;
			}
			try
			{
				LAttrAstGenerator astgen = new LAttrAstGenerator(oclModel);
				Heritage hrtg = new Heritage();
				oclModel.beginTrans(true);							
				cst.apply(astgen, hrtg);
				mm.endTrans(false);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				mm.endTrans(false);
				throw e;
			}					
		}
		else
			System.out.println("Please initialize the model first!");
	}
	
	/**
	 * Deletes the models of all OCLChecker instances.
	 */
	protected void finalize() 
	{
		Iterator it = instances.values().iterator();
		while (it.hasNext())
			((OCLChecker)it.next()).deleteModel();	
	}
	
	/**
	 * Deletes the element in the MDR assoziated to the given object.
	 */
	public void deleteElements(Object refObject) 
	{
		ModelFacade.deleteElements(refObject);
	}
	
	/**
	 * Deletes the model of the OCLChecker instance.
	 */
	private void deleteModel() 
	{
		mm.beginTrans(true);
			if (this.model != null)
				mm.deleteModel(model);
		mm.endTrans(false);
	}
	
	/**
	 * Removes all created OCL metmodel instances from the MDR.
	 *
	 */
	private void rollBack()
	{
		this.rollBack = true;
		int DEBUG_ASSOC_COUNT = 0;
		int DEBUG_CLASSES_COUNT = 0;
		
		if (System.getProperty(DEBUG) != null)
		{
			System.out.println("ROLLBACK - AssocSize: " + assoc.size());
			System.out.println("ROLLBACK - ClassesSize: " + classes.size());
		}
		
		for (int i = 0; i < this.classes.size(); i++)
		{
			RefObject object = this.classes.get(i);
			
			if (ModelFacade.isRepresentative(object.refMofId()))
			{
				if (object instanceof Classifier)
				{
					Object[] types = OclLibraryHelper.getInstance(object.refOutermostPackage()).findCollectionTypes((Classifier) object).toArray();
					for (int j = 0; j < types.length; j++)
					{
						RefObject colType = (RefObject) types[j];
						classes.remove(colType);
					}
				}
				classes.remove(i);
			}
		}
		
		for (int i = 0; i < this.assoc.size(); i++)
		{
			RefAssociation assoc = this.assoc.get(i);
			if (assoc.refAllLinks() != null)
			{
				ArrayList<RefAssociationLink> links = new ArrayList<RefAssociationLink>(assoc.refAllLinks());
				for (int j = 0; j < links.size(); j++)
				{
					RefAssociationLink link = links.get(j);
					if (this.classes.contains(link.refFirstEnd()) ||
						this.classes.contains(link.refSecondEnd()))
					{
						assoc.refRemoveLink(link.refFirstEnd(), link.refSecondEnd());
						DEBUG_ASSOC_COUNT++;						
					}
				}
			}
		}
		
		for (int i = 0; i < this.classes.size(); i++)
		{
			RefObject object = this.classes.get(i);
			try
			{
				object.refDelete();
			}
			catch (Exception e)
			{
				//object already deleted
			}
			DEBUG_CLASSES_COUNT++;			
		}
		if (System.getProperty(DEBUG) != null)
		{
			System.out.println("ROLLBACK - AssocRemoved: " + DEBUG_ASSOC_COUNT);
			System.out.println("ROLLBACK - ClassesRemoved: " + DEBUG_CLASSES_COUNT);
		}
		this.rollBack = false;
	}
	
	/**
	 * Returns an unique name for a new model.
	 */
	private String getUniqueName()
	{
		try 
		{
			Collection col = mm.getAllModelNames();
			String name = "WorkingModel";
			int i = col.size()+1;
			while (true)
			{
				name = "WorkingModel" + i;
				if (!col.contains(name))
					return name;
				i++;
			}			
		} 
		catch (ModelManagerException e) 
		{
			e.printStackTrace();
			return "";
		}
		
	}

	public void change(MDRChangeEvent evt) 
	{
		try
		{
			if (!this.rollBack)
			{
				if (evt.isOfType(MDRChangeEvent.EVENTMASK_ON_ASSOCIATION))
					if (!this.assoc.contains(evt.getSource()))
						this.assoc.add((RefAssociation) evt.getSource());
				if (evt.isOfType(MDRChangeEvent.EVENTMASK_ON_INSTANCE) &&
					evt.getSource() instanceof RefObject)
					if (!this.classes.contains(evt.getSource()))
						this.classes.add((RefObject) evt.getSource());
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Uml15Package getModel()
	{
		return model;
	}
}
