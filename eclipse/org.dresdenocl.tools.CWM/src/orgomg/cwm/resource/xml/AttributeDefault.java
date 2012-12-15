/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Attribute Default</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * required, implied, default, fixed
 * <!-- end-model-doc -->
 * @see orgomg.cwm.resource.xml.XmlPackage#getAttributeDefault()
 * @model
 * @generated
 */
public enum AttributeDefault implements Enumerator {
	/**
	 * The '<em><b>Xml required</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_REQUIRED_VALUE
	 * @generated
	 * @ordered
	 */
	XML_REQUIRED(0, "xml_required", "xml_required"),

	/**
	 * The '<em><b>Xml implied</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_IMPLIED_VALUE
	 * @generated
	 * @ordered
	 */
	XML_IMPLIED(1, "xml_implied", "xml_implied"),

	/**
	 * The '<em><b>Xml default</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_DEFAULT_VALUE
	 * @generated
	 * @ordered
	 */
	XML_DEFAULT(2, "xml_default", "xml_default"),

	/**
	 * The '<em><b>Xml fixed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_FIXED_VALUE
	 * @generated
	 * @ordered
	 */
	XML_FIXED(3, "xml_fixed", "xml_fixed");

	/**
	 * The '<em><b>Xml required</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml required</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_REQUIRED
	 * @model name="xml_required"
	 * @generated
	 * @ordered
	 */
	public static final int XML_REQUIRED_VALUE = 0;

	/**
	 * The '<em><b>Xml implied</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml implied</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_IMPLIED
	 * @model name="xml_implied"
	 * @generated
	 * @ordered
	 */
	public static final int XML_IMPLIED_VALUE = 1;

	/**
	 * The '<em><b>Xml default</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml default</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_DEFAULT
	 * @model name="xml_default"
	 * @generated
	 * @ordered
	 */
	public static final int XML_DEFAULT_VALUE = 2;

	/**
	 * The '<em><b>Xml fixed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml fixed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_FIXED
	 * @model name="xml_fixed"
	 * @generated
	 * @ordered
	 */
	public static final int XML_FIXED_VALUE = 3;

	/**
	 * An array of all the '<em><b>Attribute Default</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AttributeDefault[] VALUES_ARRAY =
		new AttributeDefault[] {
			XML_REQUIRED,
			XML_IMPLIED,
			XML_DEFAULT,
			XML_FIXED,
		};

	/**
	 * A public read-only list of all the '<em><b>Attribute Default</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AttributeDefault> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Attribute Default</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AttributeDefault get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AttributeDefault result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Attribute Default</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AttributeDefault getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AttributeDefault result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Attribute Default</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AttributeDefault get(int value) {
		switch (value) {
			case XML_REQUIRED_VALUE: return XML_REQUIRED;
			case XML_IMPLIED_VALUE: return XML_IMPLIED;
			case XML_DEFAULT_VALUE: return XML_DEFAULT;
			case XML_FIXED_VALUE: return XML_FIXED;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private AttributeDefault(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //AttributeDefault
