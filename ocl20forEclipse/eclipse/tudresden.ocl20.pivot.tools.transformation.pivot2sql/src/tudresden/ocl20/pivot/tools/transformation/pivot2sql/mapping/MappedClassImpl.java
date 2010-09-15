/*
 * Created on 06.02.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.mapping;

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.Guide;
import tudresden.ocl20.pivot.tools.codegen.declarativ.mapping.IMappedClass;

/**
 * This is the implementation for the interface MappedClass. This implementation
 * is used by the Pivot2MappedModel transformation to provide the information,
 * that is needed by the DeclarativeCodeGenerator to navigate in the target
 * model.
 * 
 * @author Christian Wende
 * 
 */
public class MappedClassImpl implements IMappedClass {

	private static final String NO_ATT_FOR_NAME =
			"There is no attribute for the given name.";
	private static final String NO_AEND_FOR_NAME =
			"There is no association end for the given name.";
	private static final String NO_CLASSGUIDE =
			"There is no classguide for this class.";

	private Map<String, Guide> attributeGuides;
	private HashMap<String, Guide> associationEndGuides;
	private Guide classGuide;
	private String className;

	/**
	 * The constructor for a MappedClassImpl
	 * 
	 * @param className
	 *          The name for the MappedClass
	 */
	public MappedClassImpl(String className) {

		attributeGuides = new HashMap<String, Guide>();
		associationEndGuides = new HashMap<String, Guide>();
		this.className = className;
	}

	/**
	 * Returns a Guide object to the given attribute name
	 * 
	 * @param attrName
	 *          the name of the attribute
	 * @return a Guide object to the given attribute name
	 */
	public Guide getAttributeGuide(String attributeName) {

		Guide g = attributeGuides.get(attributeName);
		if (g != null) {
			return g;
		}
		else {
			throw new IllegalArgumentException(NO_ATT_FOR_NAME + attributeName);
		}
	}

	/**
	 * Returns a Guide object to the given association end name
	 * 
	 * @param assEndName
	 *          the name of the association end
	 * @return a Guide object to the given association end name
	 */
	public Guide getAssociationEndGuide(String associationEndName) {

		Guide gl = associationEndGuides.get(associationEndName);
		if (gl != null) {
			return gl;
		}
		else {
			throw new IllegalArgumentException(NO_AEND_FOR_NAME + associationEndName);
		}
	}

	/**
	 * Returns a Guide object to the MappedClass in the target model
	 * 
	 * @return a Guide object to the MappedClass in the target model
	 */
	public Guide getClassGuide() {

		Guide g = classGuide;
		if (g != null) {
			return g;
		}
		else {
			throw new IllegalArgumentException(NO_CLASSGUIDE + this.className);
		}
	}

	/**
	 * Sets the given guide as the class guide for the MappedClass.
	 * 
	 * @param g
	 *          The guide, that should be added.
	 */
	public void addClassGuide(Guide g) {

		classGuide = g;
	}

	/**
	 * Adds the given guide as the attribute guide for the attribute with the
	 * given name.
	 * 
	 * @param name
	 *          The name of the attribute, the guide should be added for.
	 * @param guide
	 *          The guide, that should be added.
	 */
	public void addAttributeGuide(String name, Guide guide) {

		attributeGuides.put(name, guide);
	}

	/**
	 * Adds the given guide as the association end guide for the association end
	 * with the given name.
	 * 
	 * @param name
	 *          The name of the association end, the guide should be added for.
	 * @param guide
	 *          The guide, that should be added.
	 */
	public void addAssociationEndGuide(String assEndName, Guide guide) {

		associationEndGuides.put(assEndName, guide);
	}

	/**
	 * @return the name of the MappedClass
	 */
	public String getName() {

		return className;
	}

	/**
	 * Returns true if the given parameter represents an attribute
	 * 
	 * @param attrName
	 * @return true if the given parameter represents an attribute
	 */
	public boolean isAttribute(String attrName) {

		return attributeGuides.keySet().contains(attrName);
	}

	/**
	 * Returns true if the given parameter represents an association end
	 * 
	 * @param attrName
	 * @return true if the given parameter represents an association end
	 */
	public boolean isAssociationEnd(String assEndName) {

		return associationEndGuides.keySet().contains(assEndName);
	}

}
