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
 * A representation of the literals of the enumeration '<em><b>Occurrence Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * one, zeroOrOne, zeroOrMore, OneOrMore
 * <!-- end-model-doc -->
 * @see orgomg.cwm.resource.xml.XmlPackage#getOccurrenceType()
 * @model
 * @generated
 */
public enum OccurrenceType implements Enumerator {
	/**
	 * The '<em><b>Xml one</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_ONE_VALUE
	 * @generated
	 * @ordered
	 */
	XML_ONE(0, "xml_one", "xml_one"),

	/**
	 * The '<em><b>Xml zero Or One</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_ZERO_OR_ONE_VALUE
	 * @generated
	 * @ordered
	 */
	XML_ZERO_OR_ONE(1, "xml_zeroOrOne", "xml_zeroOrOne"),

	/**
	 * The '<em><b>Xml zero Or More</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_ZERO_OR_MORE_VALUE
	 * @generated
	 * @ordered
	 */
	XML_ZERO_OR_MORE(2, "xml_zeroOrMore", "xml_zeroOrMore"),

	/**
	 * The '<em><b>Xml one Or More</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #XML_ONE_OR_MORE_VALUE
	 * @generated
	 * @ordered
	 */
	XML_ONE_OR_MORE(3, "xml_oneOrMore", "xml_oneOrMore");

	/**
	 * The '<em><b>Xml one</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml one</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_ONE
	 * @model name="xml_one"
	 * @generated
	 * @ordered
	 */
	public static final int XML_ONE_VALUE = 0;

	/**
	 * The '<em><b>Xml zero Or One</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml zero Or One</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_ZERO_OR_ONE
	 * @model name="xml_zeroOrOne"
	 * @generated
	 * @ordered
	 */
	public static final int XML_ZERO_OR_ONE_VALUE = 1;

	/**
	 * The '<em><b>Xml zero Or More</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml zero Or More</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_ZERO_OR_MORE
	 * @model name="xml_zeroOrMore"
	 * @generated
	 * @ordered
	 */
	public static final int XML_ZERO_OR_MORE_VALUE = 2;

	/**
	 * The '<em><b>Xml one Or More</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Xml one Or More</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #XML_ONE_OR_MORE
	 * @model name="xml_oneOrMore"
	 * @generated
	 * @ordered
	 */
	public static final int XML_ONE_OR_MORE_VALUE = 3;

	/**
	 * An array of all the '<em><b>Occurrence Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final OccurrenceType[] VALUES_ARRAY =
		new OccurrenceType[] {
			XML_ONE,
			XML_ZERO_OR_ONE,
			XML_ZERO_OR_MORE,
			XML_ONE_OR_MORE,
		};

	/**
	 * A public read-only list of all the '<em><b>Occurrence Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<OccurrenceType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Occurrence Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OccurrenceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OccurrenceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Occurrence Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OccurrenceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OccurrenceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Occurrence Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OccurrenceType get(int value) {
		switch (value) {
			case XML_ONE_VALUE: return XML_ONE;
			case XML_ZERO_OR_ONE_VALUE: return XML_ZERO_OR_ONE;
			case XML_ZERO_OR_MORE_VALUE: return XML_ZERO_OR_MORE;
			case XML_ONE_OR_MORE_VALUE: return XML_ONE_OR_MORE;
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
	private OccurrenceType(int value, String name, String literal) {
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
	
} //OccurrenceType
