/*
 * Created on 06.02.2006
 *
 */
package tudresden.ocl20.transformation.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.codegen.decl.mapping.Guide;
import tudresden.ocl20.codegen.decl.mapping.MappedModel;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.jmi.uml15.core.Association;
import tudresden.ocl20.core.jmi.uml15.core.AssociationEnd;
import tudresden.ocl20.core.jmi.uml15.core.Attribute;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.GeneralizableElement;
import tudresden.ocl20.core.jmi.uml15.core.Generalization;
import tudresden.ocl20.core.jmi.uml15.core.UmlClass;
import tudresden.ocl20.core.jmi.uml15.datatypes.MultiplicityRange;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.transformation.exception.InvalidModelException;
import tudresden.ocl20.transformation.inspect.interfaces.MappedClassImpl;
import tudresden.ocl20.transformation.inspect.interfaces.MappedModelImpl;
import tudresden.ocl20.transformation.interfaces.M2XTransformation;
import tudresden.ocl20.transformation.interfaces.TConfiguration;
import tudresden.ocl20.transformation.interfaces.TParameter;
import tudresden.ocl20.transformation.interfaces.TraceType;
import tudresden.ocl20.transformation.util.ModelAnalyser;

/**
 * The class Uml2MappedModelImpl implements the transformation of an instance of the Uml Metamodel to a
 * MappedModel.
 * @author Christian Wende
 *
 */
public class Uml2MappedModelImpl extends M2XTransformation<Uml15Package, MappedModel> {

	private static final String ASS_2_ENDS = "An Association must have exactly 2 Association Ends.";
	private static final String PART_CLASS = "The partitipants of an Association must be UmlClasses.";
	private static final String ONLY_SINGE_INHERIT = "The model has multiple inheritance structures. ";

	/**
	 * The configuration parameter for the Foreign Key Prefix.
	 */
	public static TParameter FK_PREFIX;
	/**
	 * The configuration parameter for the Association Table Prefix.
	 */
	public static TParameter ASSOCIATION_TABLE_PREFIX;
	/**
	 * The configuration parameter for the Object View Prefix.
	 */
	public static TParameter OBJECT_VIEW_PREFIX;
	/**
	 * The configuration parameter for the Primary Key Prefix.
	 */
	public static TParameter PRIMARY_KEY_PREFIX;

	/**
	 * The type of the transformations in model.
	 */
	public static String in_type = MetaModelConst.UML15;
	/**
	 * The type of the transformations out model.
	 */
	public static String out_type = "A Mapped Model";
	
	private MappedModelImpl mappedModel;
	
	/**
	 * The constructor for a Uml2MappdedModel transformation.
	 * @param modelInName The name of the in model.
	 * @param outname The name of the out model.
	 * @throws Exception
	 */
	public Uml2MappedModelImpl(String modelInName, String outname) throws Exception {
		super(modelInName, outname);
	}

	
	@Override
	protected void initRequiredParameters() {
		Set<TParameter> paras = new HashSet<TParameter>();

		OBJECT_VIEW_PREFIX = new TParameter("Object View Prefix", new String[]{"OV_", "O_"}, TParameter.FREE);
		PRIMARY_KEY_PREFIX = new TParameter("Primary Key Prefix", new String[]{"PK_"},TParameter.FREE);
		ASSOCIATION_TABLE_PREFIX = new TParameter("Association Table Prefix", new String[]{"ASS_", "A_"},TParameter.FREE);
		FK_PREFIX = new TParameter("Foreign Key Prefix", new String[]{"FK_"},TParameter.FREE);
		
		paras.add(OBJECT_VIEW_PREFIX);
		paras.add(PRIMARY_KEY_PREFIX);
		paras.add(ASSOCIATION_TABLE_PREFIX);
		paras.add(FK_PREFIX);
		conf = new TConfiguration(paras);

	}

	@Override
	public void invoke() throws InvalidModelException {
		/** TRACING **/
		trace(TraceType.TRANSFORMATION ,"Starting Uml2MappedModel transformation");
		
		String settings = "\n\n";
		for (TParameter p : conf.getRequiredParameters()) {
			
			settings += "\t\t" + p.getKey() + "::= "+conf.get(p.getKey()) + "\n";
		}
		trace(TraceType.TRANSFORMATION, "Parameter Settings: " + settings + "\n");
		
		
		
		mappedModel = new MappedModelImpl();
		/** INVOKE MAPPINGS **/
		
		Set<UmlClass> classes = ModelAnalyser.getInstancesOfType(model_in, UmlClass.class);
		for(UmlClass umlCls : classes) {
			map_Class2MappedClass(umlCls);
		}
		
		Set<Association> associations = query_allAssociations();
		for (Association association : associations) {
			map_association2guide(association);
		}
			
		this.out = mappedModel;

		trace(TraceType.TRANSFORMATION ,"Finished UML to MappedModel transformation");
	
	}

	private Set<UmlClass> query_subclassesForClass(UmlClass umlCls) {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all subclasses for UmlClass: " + umlCls.getName());

		Set<UmlClass> allSubclasses = new HashSet<UmlClass>();
		Set<UmlClass> subclasses = new HashSet<UmlClass>();
		Collection<Generalization> allSpecialisations = this.model_in.getCore().getAParentSpecialization().getSpecialization(umlCls);
		for(Generalization obj : allSpecialisations) {
			GeneralizableElement element = obj.getChild();
			if(!element.getNameA().equals("OclVoid")) {
				if(ModelAnalyser.instanceIsOfType(element, UmlClass.class)) {
					subclasses.add((UmlClass)element);
					allSubclasses.add((UmlClass) element);
				}
			}
		}
		while(subclasses.size()>0) {
			Set<UmlClass> newSubclasses = new HashSet<UmlClass>();
			for(UmlClass cls : subclasses) {
				allSpecialisations = this.model_in.getCore().getAParentSpecialization().getSpecialization(cls);
				for(Generalization obj : allSpecialisations) {
					GeneralizableElement element = obj.getChild();
					if(!element.getNameA().equals("OclVoid")) {
						if(ModelAnalyser.instanceIsOfType(element, UmlClass.class)) {
							newSubclasses.add((UmlClass)element);
							allSubclasses.add((UmlClass) element);
						}
					}
				}
			}
			subclasses = newSubclasses;
		}
		
		return allSubclasses;
	}

	private Set<UmlClass> query_superclassesForClass(UmlClass umlCls, boolean recurse) {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all superclasses for UmlClass: " + umlCls.getName());

		Set<UmlClass> allSuperclasses = new HashSet<UmlClass>();
		Set<UmlClass> superclasses = ModelAnalyser.getInstancesOfType(umlCls.getParents(), UmlClass.class);
		
		while(superclasses.size()>0) {
			Set<UmlClass> newSuperclasses = new HashSet<UmlClass>();
			for(UmlClass obj : superclasses) {
				allSuperclasses.add(obj);
				if(recurse) {
					newSuperclasses.addAll( ModelAnalyser.getInstancesOfType(obj.getParents(), UmlClass.class));
				}
			}
			superclasses = newSuperclasses;
		}
		return allSuperclasses;
	}
	
	private Set<Association> query_allAssociations() {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all associations of input model");
		
		Set<Association> associations = new HashSet<Association>();
		associations.addAll(model_in.getCore().getAssociation().refAllOfType());
	  	return associations;
	}

	
	private Set<Attribute> query_attributesForClass(UmlClass clz) {
		/** TRACING **/
		trace(TraceType.QUERY, "Query all attributes of UmlClass " + clz.getName());
			
		Set<Attribute> attributes = ModelAnalyser.getInstancesOfType(clz.getFeature(), Attribute.class);
		return attributes;
	}
	
	private String query_pkNameForClass(UmlClass cls) throws InvalidModelException {
		String pkname = conf.get(PRIMARY_KEY_PREFIX.getKey()) + query_nameOfGenroot(cls);
//		System.out.println("Primary key for " + cls.getName() +" is " + pkname);
		return pkname;
	}

	

	
	private String query_nameOfGenroot(UmlClass cls) throws InvalidModelException {
		UmlClass current = cls;
		Set<UmlClass> directsuperclass = query_superclassesForClass(cls, false);
		
		while (directsuperclass.size() > 0) {
			if (directsuperclass.size()>1) {
				throw new InvalidModelException(ONLY_SINGE_INHERIT + cls.getName(), this.model_in, this);
			}
			current = (UmlClass) directsuperclass.toArray()[0];
			directsuperclass = query_superclassesForClass(current, false);
				
			}
		
		return current.getName();
	
	}

	private void map_Class2MappedClass(UmlClass umlCls) throws InvalidModelException {
			
		String classname = umlCls.getName();
		String pkname = query_pkNameForClass(umlCls);
		String ovname = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classname;
		Set<Attribute> attributes = query_attributesForClass(umlCls);
		Set<UmlClass> subclasses = query_subclassesForClass(umlCls);
		
		
		MappedClassImpl mc = accessMappedClass(umlCls);
		
		for (Attribute attribute : attributes) {
			Guide ag = new Guide(false);
			ag.add(attribute.getName(), ovname, pkname);
			trace(TraceType.MAPPING, "Creating Attribute Guide for Attribute " + attribute.getName() + " in MappedClass " + mc.getName() + "\n" + ag);
			mc.addAttributeGuide(attribute.getName(), ag);
		
			
			for(UmlClass subClass : subclasses) {
				String classname_sub = subClass.getName();
				String ovname_sub = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classname_sub;
								
				MappedClassImpl sub = accessMappedClass(subClass);
				
				Guide ag_sub = new Guide(false);
				ag_sub.add(attribute.getName(), ovname_sub, pkname);
				
				trace(TraceType.MAPPING, "Creating Attribute Guide for Attribute " + attribute.getName() + " in MappedClass " + sub.getName() + "\n" + ag_sub);
				sub.addAttributeGuide(attribute.getName(), ag_sub);
				
			}
		}
	}


	private MappedClassImpl accessMappedClass(UmlClass clz) throws InvalidModelException {
		String classname = clz.getName();
		String pkname = query_pkNameForClass(clz);
		String ovname = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classname;
		
		MappedClassImpl mc;
		
		if (mappedModel.isClass(classname)) { // mappedClass for clz already exists
			mc = (MappedClassImpl) mappedModel.getClass(classname);
		}
		else {
			/** TRACING **/
			trace(TraceType.MAPPING, "Creating MappedClass for UmlClass " + clz.getName());
			
			mc = new MappedClassImpl(classname);
			Guide g = new Guide(false);
			mappedModel.addMappedClass(classname, mc);
			
			/** TRACING **/
			trace(TraceType.MAPPING, "Creating Class Guide for UmlClass " + clz.getName() + "\n" + g);
			g.add(pkname, ovname, pkname );
			mc.addClassGuide(g);
			
		}
		return mc;
	}

	private int assTabCounter = 0;
	private String getUniqueAssTabName() {
		assTabCounter++;
		return "ASSTAB" + assTabCounter;
	}
	
	private void map_association2guide(Association association) throws InvalidModelException {
		String assName = association.getName();
		if (assName == null) assName = getUniqueAssTabName();
		
		String assTableName = conf.get(ASSOCIATION_TABLE_PREFIX.getKey()) + assName;
		List<AssociationEnd> aEnds = association.getConnection();
		if(!(aEnds.size()==2) ) {
			throw new InvalidModelException(ASS_2_ENDS + "[" +assName + "]", this.model_in, this);
		}
		
		Classifier cA =((AssociationEnd) aEnds.toArray()[0]).getParticipant();
		String nameA = ((AssociationEnd) aEnds.toArray()[0]).getName();
		Classifier cB = (UmlClass) ((AssociationEnd) aEnds.toArray()[1]).getParticipant();
		String nameB = ((AssociationEnd) aEnds.toArray()[1]).getName();
		
		UmlClass classA = null;
		if(!(ModelAnalyser.instanceIsOfType(cA, UmlClass.class))) {
			throw new InvalidModelException(PART_CLASS +"[" + assName + ", " +cA.getName() + "]", this.model_in, this);
		}
		else {
			classA = (UmlClass) cA;
		}
	
		UmlClass classB = null;
		if(!(ModelAnalyser.instanceIsOfType(cB, UmlClass.class))) {
			throw new InvalidModelException(PART_CLASS +"[" + assName + ", " +cB.getName() + "]", this.model_in, this);
		}
		else {
			classB = (UmlClass) cB;
		}
		MultiplicityRange mulRangeA = (MultiplicityRange) ((AssociationEnd) aEnds.toArray()[0]).getMultiplicity().getRange().toArray()[0];
		MultiplicityRange mulRangeB = (MultiplicityRange) ((AssociationEnd) aEnds.toArray()[1]).getMultiplicity().getRange().toArray()[0];
		
		

		/** MAPPING PHASE**/
	
		if(mulRangeA.getUpper()==1 && mulRangeB.getUpper()==1) {
	    	map_1to1_Association2Guide(classA, nameA, classB, nameB);
	    }
		else if(mulRangeA.getUpper()==-1 && mulRangeB.getUpper()==1) {
	    	map_1toN_Association2Guide(classB, nameB, classA, nameA);
	    }
		else if(mulRangeA.getUpper()==1 && mulRangeB.getUpper()==-1) {
	    	map_1toN_Association2Guide(classA, nameA, classB, nameB);
	    }
	    else if(mulRangeA.getUpper()==-1 && mulRangeB.getUpper()==-1) {
	    	map_MtoN_Association2Guide(classA, nameA, classB, nameB, assTableName);
	    }

	}

	private void map_1toN_Association2Guide( UmlClass classB, String nameB, UmlClass classA, String nameA) throws InvalidModelException {
		String pknameA = query_pkNameForClass(classA);
		//String pknameB = query_pkNameForClass(classB);
		String ovnameA = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classA.getName();
		//String ovnameB = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classB.getName();
		//String fknameA = conf.get(FK_PREFIX.getKey()) + nameA;
		String fknameB = conf.get(FK_PREFIX.getKey()) + nameB;
		Set<UmlClass> subclassesA = query_subclassesForClass(classA);
		Set<UmlClass> subclassesB = query_subclassesForClass(classB);
		
		
		MappedClassImpl mcA = accessMappedClass(classA);
		MappedClassImpl mcB = accessMappedClass(classB);
		
		Guide a2b = new Guide(true);
		
		a2b.add(fknameB, ovnameA, pknameA);
		mcA.addAssociationEndGuide(nameB, a2b);
		for (UmlClass subClass : subclassesA) {
			MappedClassImpl mcA_sub = accessMappedClass(subClass);
			
			Guide sa2b = new Guide(true);
			String ovnameA_sub = conf.get(OBJECT_VIEW_PREFIX.getKey()) + subClass.getName();
			 
			sa2b.add(fknameB, ovnameA_sub, pknameA);
			mcA_sub.addAssociationEndGuide(nameB, sa2b);
			
		}
		
		
		Guide b2a = new Guide(true);
		b2a.add(pknameA, ovnameA, fknameB);
		mcB.addAssociationEndGuide(nameA, b2a);
		for (UmlClass subClass : subclassesB) {
			MappedClassImpl mcB_sub = accessMappedClass(subClass);
			
			Guide sb2a = new Guide(true);
			 
			sb2a.add(pknameA, ovnameA, fknameB);
			mcB_sub.addAssociationEndGuide(nameA, sb2a);
			
		}
		
	}

	private void map_MtoN_Association2Guide(UmlClass classA, String nameA, UmlClass classB, String nameB, String assTableName) throws InvalidModelException {
		String pknameA = conf.get(PRIMARY_KEY_PREFIX.getKey()) + classA.getName();
		String pknameB = conf.get(PRIMARY_KEY_PREFIX.getKey()) + classB.getName();
		String ovnameA = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classA.getName();
		String ovnameB = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classB.getName();
		String fknameA = conf.get(FK_PREFIX.getKey()) + nameA;
		String fknameB = conf.get(FK_PREFIX.getKey()) + nameB;
		Set<UmlClass> subclassesA = query_subclassesForClass(classA);
		Set<UmlClass> subclassesB = query_subclassesForClass(classB);
		
		MappedClassImpl mcA = (MappedClassImpl) mappedModel.getClass(classA.getName());
		MappedClassImpl mcB = (MappedClassImpl) mappedModel.getClass(classB.getName());
		
		Guide a2b = new Guide(true);
		
		a2b.add(pknameB, ovnameB, pknameB);
		a2b.add(fknameB, assTableName, fknameA);
		a2b.add(pknameA, ovnameA, pknameA);
		mcA.addAssociationEndGuide(nameB, a2b);
		for (UmlClass subClass : subclassesA) {
			MappedClassImpl mcA_sub = accessMappedClass(subClass);
			
			Guide sa2b = new Guide(true);
			String ovnameA_sub = conf.get(OBJECT_VIEW_PREFIX.getKey()) + subClass.getName();
//			String pknameA_sub = conf.get(PRIMARY_KEY_PREFIX.getKey()) + subClass.getName();
			
			sa2b.add(pknameB, ovnameB, pknameB);
			sa2b.add(fknameB, assTableName, fknameA);
			sa2b.add(pknameA, ovnameA_sub, pknameA);
			mcA_sub.addAssociationEndGuide(nameB, sa2b);
			
		}
		
		Guide b2a = new Guide(true);
		
		b2a.add(pknameA, ovnameA, pknameA);
		b2a.add(fknameA, assTableName, fknameB);
		b2a.add(pknameB, ovnameB, pknameB);
		mcB.addAssociationEndGuide(nameA, b2a);
		for (UmlClass subClass : subclassesB) {
			MappedClassImpl mcB_sub = accessMappedClass(subClass);
			
			Guide sb2a = new Guide(true);
			String ovnameB_sub = conf.get(OBJECT_VIEW_PREFIX.getKey()) + subClass.getName();
//			String pknameB_sub = conf.get(PRIMARY_KEY_PREFIX.getKey()) + subClass.getName();
			 
			sb2a.add(fknameA, ovnameA, pknameA);
			sb2a.add(fknameA, assTableName, fknameB);
			sb2a.add(pknameB, ovnameB_sub, pknameB);
			mcB_sub.addAssociationEndGuide(nameB, sb2a);
		}
	}

	private void map_1to1_Association2Guide(UmlClass classA, String nameA, UmlClass classB, String nameB) throws InvalidModelException {
		String pknameA = query_pkNameForClass(classA);
		String pknameB = query_pkNameForClass(classB);
		String ovnameA = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classA.getName();
		String ovnameB = conf.get(OBJECT_VIEW_PREFIX.getKey()) + classB.getName();
		String fknameA = conf.get(FK_PREFIX.getKey()) + nameA;
		String fknameB = conf.get(FK_PREFIX.getKey()) + nameB;
		Set<UmlClass> subclassesA = query_subclassesForClass(classA);
		Set<UmlClass> subclassesB = query_subclassesForClass(classB);
		
		MappedClassImpl mcA = (MappedClassImpl) mappedModel.getClass(classA.getName());
		MappedClassImpl mcB = (MappedClassImpl) mappedModel.getClass(classB.getName());
		
		Guide a2b = new Guide(true);
		a2b.add(fknameB, ovnameA, pknameA);
		mcA.addAssociationEndGuide(nameB, a2b);
		for (UmlClass subClass : subclassesA) {
			MappedClassImpl mcA_sub = accessMappedClass(subClass);
			
			Guide sa2b = new Guide(true);
//			String ovnameA_sub = conf.get(OBJECT_VIEW_PREFIX.getKey()) + subClass.getName();
//			String fknameA_sub = conf.get(FK_PREFIX.getKey()) + subClass.getName();
			 
			sa2b.add(fknameB, ovnameA, pknameA);
			mcA_sub.addAssociationEndGuide(nameB, sa2b);
			
		}
		
		Guide b2a = new Guide(true);
		b2a.add(fknameA, ovnameB, pknameB);
		mcB.addAssociationEndGuide(nameA, b2a);
		for (UmlClass subClass : subclassesB) {
			MappedClassImpl mcB_sub = accessMappedClass(subClass);
			
			Guide sb2a = new Guide(true);
//			String ovnameB_sub = conf.get(OBJECT_VIEW_PREFIX.getKey()) + subClass.getName();
//			String fknameB_sub = conf.get(FK_PREFIX.getKey()) + subClass.getName();
			 
			sb2a.add(fknameA, ovnameB, pknameB);
			mcB_sub.addAssociationEndGuide(nameA, sb2a);
			
		}

	}


	
}
