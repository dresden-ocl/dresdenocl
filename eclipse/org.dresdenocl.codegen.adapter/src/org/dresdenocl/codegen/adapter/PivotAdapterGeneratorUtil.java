package tudresden.ocl20.pivot.codegen.adapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Helper Class, mainly for naming conventions of the generated Pivot Model
 * adapters.
 * 
 * @author Michael Thiele
 * 
 */
public class PivotAdapterGeneratorUtil {

	// caches for all annotated elements ("PivotModel") and packages of all
	// classes in the DSL meta model
	private static Map<GenClass, String> dSL2PivotModel;
	private static Map<String, List<String>> pivotModel2DSL;
	private static Map<String, String> packageNames;
	private static List<EClass> allDSLTypes;
	private static String resourceName;

	private static String reservedKeywords[] =
			new String[] { "package", "class", "interface" };
	private static String pivotModelTypes[] =
			new String[] { "Enumeration", "EnumerationLiteral", "Namespace",
					"Operation", "Parameter", "PrimitiveType", "Property", "Type" };

	private PivotAdapterGeneratorUtil() {

		super();
	}

	/**
	 * Should be called before a new adapter generation. Deletes caches for
	 * annotated elements and package names.
	 */
	public static void reset() {

		dSL2PivotModel = null;
		pivotModel2DSL = null;
		packageNames = null;
		allDSLTypes = null;
		resourceName = null;
	}

	public final static String TEMPLATE_LOCATION =
			PivotAdapterGeneratorPlugin.INSTANCE.getBaseURL().toString()
					+ "templates";

	public final static String CLASSPATH_VARIABLE_NAME =
			"TUDRESDEN_OCL20_PIVOT_ADAPTER";

	/**
	 * Returns the common super type of 2 <code>EClass</code>es.
	 * 
	 * @param commonSuperType
	 *          the old common super type
	 * @param dslType
	 *          the second type
	 * @return the new common super type of the old common super type and the DSL
	 *         type
	 */
	protected static EClass getCommonSuperType(EClass commonSuperType,
			EClass dslType) {

		EList<EClass> allSuperTypes = dslType.getEAllSuperTypes();

		if (commonSuperType.equals(dslType)
				|| allSuperTypes.contains(commonSuperType))
			return commonSuperType;

		// breadth first search in all super types of common super type
		Queue<EClass> queue = new LinkedList<EClass>();
		queue.add(commonSuperType);
		while (!queue.isEmpty()) {
			EClass eClass = queue.poll();
			EList<EClass> directSuperTypes = eClass.getESuperTypes();
			for (EClass directSuperType : directSuperTypes) {
				if (allSuperTypes.contains(directSuperType))
					return directSuperType;
			}
			queue.addAll(directSuperTypes);
		}

		// no match could be found, so EObject is the common super type
		return EcoreFactory.eINSTANCE.createEObject().eClass();
	}

	/**
	 * Determines the common superclass of all DSL types that are mapped to Pivot
	 * Model types.
	 * 
	 * @param genModel
	 * @return the common superclass of the all DSL types
	 */
	public static String getCommonSuperType(GenModel genModel) {

		if (dSL2PivotModel == null)
			createAnnotatedElements(genModel);

		Iterator<GenClass> dslTypeIter = dSL2PivotModel.keySet().iterator();
		EClass commonSuperType = null;

		// find the first common super type
		if (dslTypeIter.hasNext()) {
			GenClass dslTypeGenClass = dslTypeIter.next();
			commonSuperType = dslTypeGenClass.getEcoreClass();
		}
		else
			// no elements are annotated, so no common super type can be found
			return "Object";

		while (dslTypeIter.hasNext()) {
			commonSuperType =
					getCommonSuperType(commonSuperType, dslTypeIter.next()
							.getEcoreClass());
			if (commonSuperType.getName().equals("EObject")) {
				return "Object";
			}
		}

		return genModel.findGenPackage(commonSuperType.getEPackage())
				.getInterfacePackageName()
				+ "." + commonSuperType.getName();
	}

	/**
	 * Computes the common super type for all DSL types that are mapped to the
	 * Pivot Model Type type.
	 * 
	 * @param genModel
	 * @return the name of the common super type for all DSL <code>Type</code>
	 *         types.
	 */
	public static String getCommonSuperTypeForDSLTypes(GenModel genModel) {

		EClass commonSuperType;
		if (pivotModel2DSL == null)
			createAnnotatedElements(genModel);
		if (!allDSLTypes.isEmpty()) {
			Iterator<EClass> typesIter = allDSLTypes.iterator();
			commonSuperType = typesIter.next();
			while (typesIter.hasNext()) {
				commonSuperType = getCommonSuperType(commonSuperType, typesIter.next());
			}
			return genModel.findGenPackage(commonSuperType.getEPackage())
					.getInterfacePackageName()
					+ "." + commonSuperType.getName();
		}
		else
			return null;
	}

	/**
	 * 
	 * @param genClass
	 * @return the full package name of the genClass
	 */
	public static String getPackageNameFor(GenClass genClass) {

		return (genClass.getGenPackage().getInterfacePackageName() == null ? ""
				: genClass.getGenPackage().getInterfacePackageName() + ".");
	}

	/**
	 * 
	 * @param annotation
	 * @return a String representing the Pivot Model type that is mapped to
	 */
	protected static String getAnnotationDetails(EAnnotation annotation) {

		if (annotation.getSource().equalsIgnoreCase(
				"http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel")) {
			EMap<String, String> annotationDetails = annotation.getDetails();
			if (annotationDetails != null) {
				if (annotationDetails.containsKey("PivotModel"))
					return annotationDetails.get("PivotModel");
				if (annotationDetails.containsKey("Pivotmodel"))
					return annotationDetails.get("Pivotmodel");
				if (annotationDetails.containsKey("pivotModel"))
					return annotationDetails.get("pivotModel");
				if (annotationDetails.containsKey("pivotmodel"))
					return annotationDetails.get("pivotmodel");
			}

		}
		return null;
	}

	protected static void createPackageNames(GenModel genModel) {

		packageNames = new HashMap<String, String>();
		for (GenPackage genPackage : genModel.getGenPackages()) {
			getGenPackages(genPackage);
		}
	}

	private static void getGenPackages(GenPackage genPackage) {

		for (GenPackage subPackage : genPackage.getNestedGenPackages()) {
			getGenPackages(subPackage);
		}
		for (GenClass genClass : genPackage.getGenClasses()) {
			packageNames.put(genClass.getName(), (genPackage
					.getInterfacePackageName() == null ? "" : genPackage
					.getInterfacePackageName()
					+ "." + genClass.getName()));
		}
	}

	/**
	 * 
	 * @param genModel
	 * @param genClassName
	 * @return the genClass's package name + the genClass's name
	 */
	public static String getGenClassPackage(GenModel genModel, String genClassName) {

		// all package names are cached.
		if (packageNames == null)
			createPackageNames(genModel);
		if (packageNames.containsKey(genClassName))
			return packageNames.get(genClassName);
		else
			return genClassName;
	}

	protected static void createAnnotatedElements(GenModel genModel) {

		dSL2PivotModel = new HashMap<GenClass, String>();
		pivotModel2DSL = new HashMap<String, List<String>>();
		allDSLTypes = new LinkedList<EClass>();
		for (GenPackage genPackage : genModel.getGenPackages()) {
			getGenClasses(genPackage);
		}
	}

	private static void getGenClasses(GenPackage genPackage) {

		for (GenPackage subPackage : genPackage.getNestedGenPackages()) {
			getGenClasses(subPackage);
		}
		for (GenClass genClass : genPackage.getGenClasses()) {
			for (EAnnotation annotation : genClass.getEcoreClass().getEAnnotations()) {
				String pivotModelTypeName = getAnnotationDetails(annotation);
				if (pivotModelTypeName != null) {
					// fill maps for both mapping directions
					dSL2PivotModel.put(genClass, pivotModelTypeName);
					if (pivotModel2DSL.containsKey(pivotModelTypeName))
						pivotModel2DSL.get(pivotModelTypeName).add(genClass.getName());
					else
						pivotModel2DSL.put(pivotModelTypeName, new LinkedList<String>(
								Arrays.asList(genClass.getName())));
					if (pivotModelTypeName.equals("Type")
							|| pivotModelTypeName.equals("PrimitiveType")
							|| pivotModelTypeName.equals("Enumeration"))
						allDSLTypes.add(genClass.getEcoreClass());
				}
			}
		}
	}

	/**
	 * Method tests if the DSL type name is unique. If not it generates an unique
	 * name.
	 * 
	 * @param genModel
	 *          the genModel of the DSL
	 * @param genClass
	 *          the DSL type
	 * @return a unique name of the DSL type that is mapped to a Pivot Model
	 *         element
	 */
	public static String getDSLModelUniqueTypeName(GenModel genModel,
			GenClass genClass) {

		// all annotated elements (DSL Type -> Pivot Model) are cached
		if (dSL2PivotModel == null) {
			createAnnotatedElements(genModel);
		}
		if (dSL2PivotModel.containsKey(genClass)) {
			// tests, if DSL type names equal Pivot Model elements -> to avoid
			// any confusion, the DSL types are used with full package name
			String dslModelTypeName = genClass.getName();
			if (Arrays.asList(pivotModelTypes).contains(dslModelTypeName))
				return getGenClassPackage(genModel, dslModelTypeName);
			else {
				// check whether the DSLs type name equals one of Java's
				// reserved
				// keywords -> full package name
				for (String keyword : reservedKeywords) {
					if (dslModelTypeName.equalsIgnoreCase(keyword))
						return getGenClassPackage(genModel, dslModelTypeName);
				}
				return dslModelTypeName;
			}
		}
		else
			return null;
	}

	/**
	 * Returns a <code>List</code> of (not necessarily unique) DSL type names for
	 * a Pivot Model type (more than 1 DSL type can be mapped to a Pivot Model
	 * type).
	 * 
	 * @param genModel
	 *          the genModel of the DSL
	 * @param givenPivotModelType
	 *          the Pivot Model type the DSL types are looked up for
	 * @return a <code>List</code> of (not necessarily unique) DSL type names that
	 *         are mapped to the <code>pivotModelType</code>
	 */
	public static List<String> getDSLModelTypeNames(GenModel genModel,
			String givenPivotModelType) {

		if (pivotModel2DSL == null)
			createAnnotatedElements(genModel);

		/*
		 * Since Type has subtypes PrimitiveType and Enumeration, also check for
		 * them
		 */
		List<String> givenPivotModelTypes = new LinkedList<String>();
		givenPivotModelTypes.add(givenPivotModelType);
		if (givenPivotModelType.equals("Type")) {
			givenPivotModelTypes.add("PrimitiveType");
			givenPivotModelTypes.add("Enumeration");
		}

		List<String> retList = new LinkedList<String>();
		for (String pivotModelName : givenPivotModelTypes) {
			if (pivotModel2DSL.containsKey(pivotModelName))
				retList.addAll(pivotModel2DSL.get(pivotModelName));
		}
		return retList;
	}

	/**
	 * Returns a <code>List</code> of unique DSL type names for a Pivot Model type
	 * (more than 1 DSL type can be mapped to a Pivot Model Type).
	 * 
	 * @param genModel
	 *          the genModel of the DSL
	 * @param givenPivotModelType
	 *          the Pivot Model type the DSL types are looked up for
	 * @return a <code>List</code> of unique DSL type names that are mapped to the
	 *         <code>pivotModelType</code>
	 */
	public static List<String> getDSLModelUniqueTypeNames(GenModel genModel,
			String givenPivotModelType) {

		if (pivotModel2DSL == null)
			createAnnotatedElements(genModel);

		/*
		 * Since Type has subtypes PrimitiveType and Enumeration, also check for
		 * them
		 */
		List<String> givenPivotModelTypes = new LinkedList<String>();
		givenPivotModelTypes.add(givenPivotModelType);
		if (givenPivotModelType.equals("Type")) {
			givenPivotModelTypes.add("PrimitiveType");
			givenPivotModelTypes.add("Enumeration");
		}

		List<String> dslModelUniqueTypeNames = new LinkedList<String>();
		for (String pivotModelType : givenPivotModelTypes) {
			if (pivotModel2DSL.containsKey(pivotModelType)) {
				// tests, if DSL type names equal Pivot Model elements -> to avoid
				// any confusion, the DSL types are used with full package name
				List<String> dslModelTypeNames = pivotModel2DSL.get(pivotModelType);
				for (String dslModelTypeName : dslModelTypeNames) {
					boolean withPackage = false;
					if (Arrays.asList(pivotModelTypes).contains(dslModelTypeName)) {
						dslModelUniqueTypeNames.add(getGenClassPackage(genModel,
								dslModelTypeName));
						withPackage = true;
					}
					else {
						// check whether the DSLs type name equals one of Java's
						// reserved
						// keywords -> full package name
						for (String keyword : reservedKeywords) {
							if (dslModelTypeName.equalsIgnoreCase(keyword)) {
								dslModelUniqueTypeNames.add(getGenClassPackage(genModel,
										dslModelTypeName));
								withPackage = true;
								break;
							}
						}
					}
					if (!withPackage)
						dslModelUniqueTypeNames.add(dslModelTypeName);
				}
			}
		}
		return dslModelUniqueTypeNames;
	}

	/**
	 * 
	 * @param dslElement
	 *          the name of the DSL element
	 * @return a name for this element that can be used without any naming
	 *         conflicts
	 */
	public static String getDSLElementName(String dslElement) {

		if (dslElement == null)
			return null;
		// test, if the name is one of the reserved keywords of Java
		for (String keyword : reservedKeywords) {
			if (dslElement.equalsIgnoreCase(keyword))
				dslElement = "a" + startWithCapitalLetter(dslElement);
		}
		// if the DSL Type is used with package name in front of the class name,
		// skip the package name and put "dsl" in front of the name to
		// distinguish
		// from the Pivot Model namespace name
		if (dslElement.contains("."))
			return "dsl"
					+ dslElement.substring(dslElement.lastIndexOf(".") + 1, dslElement
							.length());
		return PivotAdapterGeneratorUtil.startWithLowerCaseLetter(dslElement);
	}

	/**
	 * 
	 * @return the String which is displayed for all methods that require
	 *         implementation
	 */
	public static String getImplementThis() {

		return "// TODO: implement this method"
				+ System.getProperty("line.separator")
				+ "\t\t"
				+ "// don't forget to remove the @generated tag or change it to \"@generated NOT\"";
	}

	/**
	 * 
	 * @param genModel
	 * @param genClass
	 *          the DSL type
	 * @return the name for the adapter class
	 */
	public static String getAdapterClassName(GenModel genModel, GenClass genClass) {

		return genModel.getModelName() + startWithCapitalLetter(genClass.getName());
	}

	/**
	 * 
	 * @param genModel
	 * @param mappedType
	 *          the type name of the Pivot Model
	 * @return
	 */
	public static String getAdapterClassName(GenModel genModel, String mappedType) {

		return genModel.getModelName() + startWithCapitalLetter(mappedType);
	}

	/**
	 * Imports the adaptee's type declaration.
	 * 
	 * @param genModel
	 * @param genClass
	 * @return the fully qualified location of the adaptee
	 */
	public static String getAdapteeClassImport(GenModel genModel,
			GenClass genClass) {

		String uniqueTypeName = getDSLModelUniqueTypeName(genModel, genClass);
		String packageName = getPackageNameFor(genClass);
		if (packageName.contains(".") && uniqueTypeName.contains("."))
			return packageName
					+ uniqueTypeName.substring(uniqueTypeName.lastIndexOf(".") + 1);
		else
			return packageName + uniqueTypeName;
	}

	/**
	 * 
	 * @param typeName
	 * @return
	 */
	public static String getTypeNameWithoutPackage(String typeName) {

		typeName =
				startWithLowerCaseLetter(typeName
						.substring(typeName.lastIndexOf(".") + 1));
		if (Arrays.asList(reservedKeywords).contains(typeName))
			typeName = "a" + startWithCapitalLetter(typeName);
		return typeName;
	}

	/**
	 * Attention: projectName == packageName
	 * 
	 * @param genModel
	 * @return the project/package name
	 */
	public static String getProjectName(GenModel genModel) {

		return "tudresden.ocl20.pivot.metamodels."
				+ genModel.getModelName().toLowerCase();
	}

	public static String startWithCapitalLetter(String string) {

		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public static String startWithLowerCaseLetter(String string) {

		return string.substring(0, 1).toLowerCase() + string.substring(1);
	}

	/**
	 * 
	 * @param genModel
	 * @return the package where all adapters are generated
	 */
	public static String getAdapterPackage(GenModel genModel) {

		return getProjectName(genModel) + ".internal.model";
	}

	/**
	 * 
	 * @param genModel
	 * @return the package for the model provider
	 */
	public static String getProviderPackage(GenModel genModel) {

		return getProjectName(genModel) + ".internal.provider";
	}

	/**
	 * The user can define a mapping for the resource type. This is done via an
	 * {@link EAnnotation} with the source set to
	 * "http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel" and the
	 * {@link EAnnotation#getDetails() details} set to "Resource" -> the fully
	 * qualified name of the resource (e.g., {@link Resource
	 * org.eclipse.emf.ecore.resource.Resource})
	 * 
	 * @param genModel
	 * @return the fully qualified name of the resource or <code>null</code> if
	 *         not declared
	 */
	public static String getResourceType(GenModel genModel) {

		// search for the annotation, if not already determined
		if (resourceName != null)
			return resourceName;

		// only look in top-level packages
		for (GenPackage genPackage : genModel.getGenPackages()) {
			for (EAnnotation annotation : genPackage.getEcorePackage()
					.getEAnnotations()) {
				if (annotation.getSource().equalsIgnoreCase(
						"http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel")) {
					EMap<String, String> annotationDetails = annotation.getDetails();
					if (annotationDetails != null) {
						if (annotationDetails.containsKey("Resource")) {
							final String resourceType = annotationDetails.get("Resource");
							resourceName = resourceType;
							return resourceType;
						} // no else
					} // no else
				} // no else
			} // end iterate annotations
		} // end iterate genPackages
		return null;
	}
}
