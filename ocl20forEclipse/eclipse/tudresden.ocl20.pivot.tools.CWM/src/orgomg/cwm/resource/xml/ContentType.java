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
 * A representation of the literals of the enumeration '<em><b>Content Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * empty, any, mixed, element
 * <!-- end-model-doc -->
 * @see orgomg.cwm.resource.xml.XmlPackage#getContentType()
 * @model
 * @generated
 */
public enum ContentType implements Enumerator {
	/**
	 * The '<em><b>Xml empty</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_EMPTY_VALUE
	 * @generated
	 * @ordered
	 */
	XML_EMPTY(0, "xml_empty", "xml_empty"),

	/**
	 * The '<em><b>Xml any</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_ANY_VALUE
	 * @generated
	 * @ordered
	 */
	XML_ANY(1, "xml_any", "xml_any"),

	/**
	 * The '<em><b>Xml mixed</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_MIXED_VALUE
	 * @generated
	 * @ordered
	 */
	XML_MIXED(2, "xml_mixed", "xml_mixed"),

	/**
	 * The '<em><b>Xml element</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_ELEMENT_VALUE
	 * @generated
	 * @ordered
	 */
	XML_ELEMENT(3, "xml_element", "xml_element");

	/**
	 * The '<em><b>Xml empty</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml empty</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_EMPTY
	 * @model name="xml_empty"
	 * @generated
	 * @ordered
	 */
	public static final int XML_EMPTY_VALUE = 0;

	/**
	 * The '<em><b>Xml any</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml any</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_ANY
	 * @model name="xml_any"
	 * @generated
	 * @ordered
	 */
	public static final int XML_ANY_VALUE = 1;

	/**
	 * The '<em><b>Xml mixed</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml mixed</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_MIXED
	 * @model name="xml_mixed"
	 * @generated
	 * @ordered
	 */
	public static final int XML_MIXED_VALUE = 2;

	/**
	 * The '<em><b>Xml element</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml element</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_ELEMENT
	 * @model name="xml_element"
	 * @generated
	 * @ordered
	 */
	public static final int XML_ELEMENT_VALUE = 3;

	/**
	 * An array of all the '<em><b>Content Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ContentType[] VALUES_ARRAY =
		new ContentType[] {
			XML_EMPTY,
			XML_ANY,
			XML_MIXED,
			XML_ELEMENT,
		};

	/**
	 * A public read-only list of all the '<em><b>Content Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ContentType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Content Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ContentType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContentType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Content Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ContentType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ContentType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Content Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ContentType get(int value) {
		switch (value) {
			case XML_EMPTY_VALUE: return XML_EMPTY;
			case XML_ANY_VALUE: return XML_ANY;
			case XML_MIXED_VALUE: return XML_MIXED;
			case XML_ELEMENT_VALUE: return XML_ELEMENT;
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
	private ContentType(int value, String name, String literal) {
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
	
} //ContentType
