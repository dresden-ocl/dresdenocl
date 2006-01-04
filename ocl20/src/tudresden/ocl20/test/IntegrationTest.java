/*
 * Created on 02.11.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.ModelManagerException;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.jmi.uml15.core.Association;
import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.Enumeration;
import tudresden.ocl20.core.jmi.uml15.core.EnumerationLiteral;
import tudresden.ocl20.core.jmi.uml15.core.Interface;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.core.Namespace;
import tudresden.ocl20.core.jmi.uml15.core.Operation;
import tudresden.ocl20.core.jmi.uml15.core.Parameter;
import tudresden.ocl20.core.jmi.uml15.datatypes.MultiplicityRange;
import tudresden.ocl20.core.jmi.uml15.impl.core.ClassifierImpl;
import tudresden.ocl20.core.jmi.uml15.impl.modelmanagement.ModelHelper;
import tudresden.ocl20.core.jmi.uml15.impl.uml15ocl.types.OclLibraryHelper;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Model;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.integration.ModelFacade;

/**
 * This class can be used to test an implementation of the class ModelFacade.
 * @author Mirko
 */
public class IntegrationTest 
{
	private ModelManager mm = ModelManager.getInstance();   
    private Uml15Package model = null;
	public IntegrationTest(Object root, ModelFacade facade)
	{
		try 
		{
			mm.beginTrans(true);
			this.model = (Uml15Package) mm.createOclModel(MetaModelConst.UML15, this.getUniqueName());
			Model topPackage = model.getModelManagement().getModel().createModel();
			topPackage.setNameA("rootPackage");
			mm.endTrans(false); 
		    ModelFacade.addModelFacade(this.model.refMofId(), facade);
			ModelFacade instance = ModelFacade.getInstance(this.model.refMofId());
			instance.addRefObject(topPackage.refMofId(), root);		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Start the test.
	 */
	public void start()
	{
		Model topPackage = ModelHelper.getInstance(this.model).getTopPackage();
		mm.beginTrans(true);
		testOwnedElements((Namespace) topPackage, "TopPackage", "");
		testOCLVoid();
		testOCLAny("");
		mm.beginTrans(false);
	}
	private void testOwnedElements(Namespace topPackage, String s, String offset) 
	{
		Collection list = topPackage.getOwnedElement();
		Iterator it = list.iterator();
		
		while (it.hasNext())
		{
			Object o = it.next();
			if (o instanceof Package)
			{
				System.out.println("");
				System.out.println(offset+"Paket :" + ((ModelElement)o).getName());
				System.out.println("");
				this.testOwnedElements((Namespace)o, "Paket "+((ModelElement)o).getName(),offset+"##");
			}
			if (o instanceof Classifier && !(o instanceof Enumeration) && !(o instanceof Interface))
			{
				System.out.println(offset+"Classifier :" + ((Classifier)o).getPathNameA());
				Collection parents = ((Classifier)o).getParents();				
				Iterator itParents = parents.iterator();
				if (itParents.hasNext())
					System.out.println(offset+"##Oberklassen :");
				else
					System.out.println(offset+"##Oberklassen : keine");
				Object p = null;
				while (itParents.hasNext())
				{
					p = itParents.next();
					System.out.println(offset+"####"+((Classifier)p).getNameA());
				}
				Collection children = ((ClassifierImpl)o).getChildren();				
				Iterator itChilds = children.iterator();
				if (itChilds.hasNext())
					System.out.println(offset+"##Unterklassen :");
				else
					System.out.println(offset+"##Unterklassen : keine");
				
				while (itChilds.hasNext())
				{
					p = itChilds.next();
					System.out.println(offset+"####"+((Classifier)p).getNameA());
				}
				
				testClassifier((Classifier)o, "Classifier "+((ModelElement)o).getName(),offset+"##");
				this.testAllAssociations((Classifier)o, "Classifier "+((ModelElement)o).getName(),offset+"##");
				this.testOwnedElements((Namespace)o, "Classifier "+((ModelElement)o).getName(),offset+"##");
				System.out.println("");
			}
			if (o instanceof Enumeration && !(o instanceof Interface))
			{
				System.out.println(offset+"Aufzählung :");
				Collection literal = ((Enumeration)o).getLiteralA();				
				Iterator itLiteral = literal.iterator();
				EnumerationLiteral p = null;
				if (itLiteral.hasNext())
				{
					p = (EnumerationLiteral)itLiteral.next();
					System.out.println(offset+"##Literal:"+((EnumerationLiteral)p).getNameA());
				}
				System.out.println("");
			}
			if (o instanceof Interface)
			{
				System.out.println(offset+"Interface :" + ((Classifier)o).getPathNameA());
				Collection parents = ((Classifier)o).getParents();				
				Iterator itParents = parents.iterator();
				if (itParents.hasNext())
					System.out.println(offset+"##implementierte Interfaces :");
				else
					System.out.println(offset+"##implementierte Interfaces : keine");
				Object p = null;
				while (itParents.hasNext())
				{
					p = itParents.next();
					System.out.println(offset+"####"+((Classifier)p).getNameA());
				}
				Collection children = ((ClassifierImpl)o).getChildren();				
				Iterator itChilds = children.iterator();
				if (itChilds.hasNext())
					System.out.println(offset+"##implementiert von :");
				else
					System.out.println(offset+"##implementiert von : keine");
				
				while (itChilds.hasNext())
				{
					p = itChilds.next();
					System.out.println(offset+"####"+((Classifier)p).getNameA());
				}
				
				testClassifier((Classifier)o, "Interface "+((ModelElement)o).getName(),offset+"##");
				this.testAllAssociations((Classifier)o, "Interface "+((ModelElement)o).getName(),offset+"##");
				this.testOwnedElements((Namespace)o, "Interface "+((ModelElement)o).getName(),offset+"##");
				System.out.println("");
			}
		}
	}

	/**
	 * @param classifier
	 * @param string
	 */
	private void testAllAssociations(Classifier c, String s, String offset) 
	{
		ModelFacade instance = ModelFacade.getInstance(c.refOutermostPackage().refMofId());
    	Iterator it = instance.getAssociationEnds(c.refMofId()).iterator();
				
		while (it.hasNext())
		{
	         AssociationEnd e = (AssociationEnd) it.next();
	         Association a = e.getAssociation();
			 System.out.println(offset+"Assoziation :" + ((ModelElement)a).getName());
			 System.out.println(offset+"####Assoziationendenden:");
			 int i = 1;
			 Iterator oppositeEnds = a.getConnection().iterator();
	            while(oppositeEnds.hasNext())
	            {
	                AssociationEnd end2 = (AssociationEnd) oppositeEnds.next();
	                System.out.println(offset+"######Name :"+end2.getName());
	                System.out.println(offset+"######Multiplizität :"+((MultiplicityRange)(end2).getMultiplicity().getRange().iterator().next()).getUpper());
	                i++;
	            }	     			
		}
	}

	private void testClassifier(Classifier c, String s, String offset) 
	{
		Collection list = c.allAttributes();
		Iterator it = list.iterator();
		Object o = null;
		System.out.println(offset+"Attribute:");
		while (it.hasNext())
		{
			o = it.next();			
			System.out.println(offset+"####Attribut :" + ((ModelElement)o).getName());
			System.out.println(offset+"######Typ :" + ((Attribute)o).getType().getName());
			System.out.println(offset+"######Kardinalität :" + ((MultiplicityRange)((Attribute)o).getMultiplicity().getRange().iterator().next()).getUpper());
			//System.out.println(offset+"######Namensraum :" + ((Attribute)o).getNamespace().getName());
			System.out.println(offset+"######Owner :" + ((Attribute)o).getOwner().getName());
			System.out.println(offset+"######OrderingKind :" + ((Attribute)o).getOrdering());
			System.out.println(offset+"######OwnerScope :" + ((Attribute)o).getOwnerScope());				
		}
		list = c.allOperations();
		it = list.iterator();
		System.out.println(offset+"Methoden:");
		while (it.hasNext())
		{
			o = it.next();
			System.out.println(offset+"####Methode :" + ((ModelElement)o).getName());
			System.out.println(offset+"######Owner :" + ((Operation)o).getOwner().getName());
			System.out.println(offset+"######OwnerScope :" + ((Operation)o).getOwnerScope());
				
			if (!((Operation)o).getParameter().isEmpty())
				System.out.println(offset+"######Parameter:");
				
			Parameter p = (Parameter) ((Operation)o).getReturnParameterA();
			System.out.println(offset+"########Result :"+p.getName());
			System.out.println(offset+"##########Name :"+p.getName());
			System.out.println(offset+"##########Art  :"+p.getKind());
			System.out.println(offset+"##########Typ  :"+p.getType().getName());
			Iterator itParam =  ((Operation)o).getInParametersA().iterator();
			while (itParam.hasNext())
			{
				p = (Parameter) itParam.next();
				System.out.println(offset+"########InParam :"+p.getName());
				System.out.println(offset+"##########Name  :"+p.getName());
				System.out.println(offset+"##########Art   :"+p.getKind());
				System.out.println(offset+"##########Typ   :"+p.getType().getName());					
			}
			itParam =  ((Operation)o).getOutParametersA().iterator();
			while (itParam.hasNext())
			{
				p = (Parameter) itParam.next();
				System.out.println(offset+"########InParam :"+p.getName());
				System.out.println(offset+"##########Name  :"+p.getName());
				System.out.println(offset+"##########Art   :"+p.getKind());
				System.out.println(offset+"##########Typ   :"+p.getType().getName());					
			}
		}		
	}
	
	private void testOCLVoid()
	{
		Iterator it = OclLibraryHelper.getInstance(this.model).getVoid().getParents().iterator();
		while (it.hasNext())
			System.out.println("OCLVoid Superclass: " + ((Classifier)it.next()).getName());
	}
	
	private void testOCLAny(String offset)
	{
		Iterator it = OclLibraryHelper.getInstance(this.model).getAny().allOperations().iterator();
		System.out.println(offset+"Methoden:");
		while (it.hasNext())
		{
			Object o = it.next();
			System.out.println(offset+"####Methode :" + ((ModelElement)o).getName());
			System.out.println(offset+"######Namensraum :" + ((Operation)o).getNamespace().getName());
			System.out.println(offset+"######Owner :" + ((Operation)o).getOwner().getName());
			System.out.println(offset+"######OwnerScope :" + ((Operation)o).getOwnerScope());
				
			if (!((Operation)o).getParameter().isEmpty())
				System.out.println(offset+"######Parameter:");
				
			Parameter p = (Parameter) ((Operation)o).getReturnParameterA();
			System.out.println(offset+"########Result :"+p.getName());
			System.out.println(offset+"##########Name :"+p.getName());
			System.out.println(offset+"##########Art  :"+p.getKind());
			System.out.println(offset+"##########Typ  :"+p.getType().getName());
			Iterator itParam =  ((Operation)o).getInParametersA().iterator();
			while (itParam.hasNext())
			{
				p = (Parameter) itParam.next();
				System.out.println(offset+"########InParam :"+p.getName());
				System.out.println(offset+"##########Name  :"+p.getName());
				System.out.println(offset+"##########Art   :"+p.getKind());
				System.out.println(offset+"##########Typ   :"+p.getType().getName());					
			}
			itParam =  ((Operation)o).getOutParametersA().iterator();
			while (itParam.hasNext())
			{
				p = (Parameter) itParam.next();
				System.out.println(offset+"########InParam :"+p.getName());
				System.out.println(offset+"##########Name  :"+p.getName());
				System.out.println(offset+"##########Art   :"+p.getKind());
				System.out.println(offset+"##########Typ   :"+p.getType().getName());					
			}
		}		
	}

	private String getUniqueName()
	{
		try 
		{
			boolean found = false;
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
}
