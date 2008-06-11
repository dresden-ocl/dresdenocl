package tudresden.ocl20.pivot.codegen.adapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
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
	private static Map<String, GenClass> annotatedElements;
	private static Map<String, String> packageNames;

	private PivotAdapterGeneratorUtil() {
		super();
	}

	/**
	 * Should be called before a new adapter generation. Deletes caches for
	 * annotated elements and package names.
	 */
	public static void reset() {
		annotatedElements = null;
		packageNames = null;
	}

	public final static String TEMPLATE_LOCATION = PivotAdapterGeneratorPlugin.INSTANCE
			.getBaseURL().toString()
			+ "templates";

	public final static String CLASSPATH_VARIABLE_NAME = "TUDRESDEN_OCL20_PIVOT_ADAPTER";

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
		if (annotatedElements == null)
			createAnnotatedElements(genModel);

		Iterator<GenClass> dslTypeIter = annotatedElements.values().iterator();
		EClass commonSuperType = null;
		String origGenPackage = "";

		if (dslTypeIter.hasNext()) {
			GenClass dslTypeGenClass = dslTypeIter.next();
			// this trick is necessary, since all package information needed for code
			// generation is lost when the EClass, that is needed for the computation
			// of the common super class, is used
			origGenPackage = dslTypeGenClass.getGenPackage()
					.getInterfacePackageName();
			commonSuperType = dslTypeGenClass.getEcoreClass();
		} else
			// no elements are annotated, so no common super type can be found
			return "Object";

		while (dslTypeIter.hasNext()) {
			commonSuperType = getCommonSuperType(commonSuperType, dslTypeIter.next()
					.getEcoreClass());
			if (commonSuperType.getName().equals("EObject")) {
				return "Object";
			}
		}
		// the package of the original common super type is added to the name,
		// since there is no way for an EClass to find out about its real package
		// name
		return origGenPackage + "." + commonSuperType.getName();
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
			for (GenClass genClass : genPackage.getGenClasses()) {
				packageNames.put(genClass.getName(), (genPackage
						.getInterfacePackageName() == null ? "" : genPackage
						.getInterfacePackageName()
						+ "." + genClass.getName()));
			}
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
		annotatedElements = new HashMap<String, GenClass>();
		for (GenPackage genPackage : genModel.getGenPackages()) {
			for (GenClass genClass : genPackage.getGenClasses()) {
				for (EAnnotation annotation : genClass.getEcoreClass()
						.getEAnnotations()) {
					String pivotModelTypeName = getAnnotationDetails(annotation);
					if (pivotModelTypeName != null) {
						annotatedElements.put(pivotModelTypeName, genClass);
					}
				}
			}
		}
	}

	/**
	 * 
	 * @param genModel
	 *          the genModel of the DSL
	 * @param pivotModelType
	 *          the Pivot Model type the DSL type is looked up for
	 * @return the name of the DSL type that is mapped to the Pivot Model type
	 */
	public static String getPivotModelAttributeName(GenModel genModel,
			String pivotModelType) {
		// all annotated elements (Pivot Model -> Type, etc.) are cached
		if (annotatedElements == null) {
			createAnnotatedElements(genModel);
		}
		if (annotatedElements.containsKey(pivotModelType)) {
			// tests, if DSL type names equal Pivot Model elements -> to avoid
			// any confusion, the DSL types are used with full package name
			String dslModelTypeName = annotatedElements.get(pivotModelType).getName();
			if (dslModelTypeName.equalsIgnoreCase(pivotModelType))
				return getGenClassPackage(genModel, dslModelTypeName);
			else
				return dslModelTypeName;
		} else
			return null;
	}

	public static String getDSLElementName(String dslElement) {
		if (dslElement == null)
			return null;
		// if the DSL Type is used with package name in front of the class name,
		// skip the package name and put "dsl" in front of the name to distinguish
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
		return "// TODO: implement this method";
	}

	/**
	 * 
	 * @param genClass
	 *          the GenModelClass for which the adapter has to be generated
	 * @param suffix
	 *          the appropriate suffix for the generated adapter, e.g. "Type",
	 *          "Operation", ...
	 * @return the name for the adapter class
	 */
	public static String getAdapterClassName(GenModel genModel, String suffix) {
		return genModel.getModelName() + startWithCapitalLetter(suffix);
	}

	/**
	 * Attention: projectName == packageName
	 * 
	 * @param genModel
	 * @return the project/package name
	 */
	public static String getProjectName(GenModel genModel) {
		return "tudresden.ocl20.pivot.metamodels."
				+ startWithLowerCaseLetter(genModel.getModelName());
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

}
