/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.enumerations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Deferrability Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Used in Foreign Keys
 * Indicates if the Foreign Key validation should be deferred to the end of the transaction or executed immadiately at the end of the statement.
 * This is the value used at the beginning of a transaction. It can be changed within a transaction.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.resource.relational.enumerations.EnumerationsPackage#getDeferrabilityType()
 * @model
 * @generated
 */
public enum DeferrabilityType implements Enumerator {
	/**
	 * The '<em><b>Initially Deferred</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INITIALLY_DEFERRED_VALUE
	 * @generated
	 * @ordered
	 */
	INITIALLY_DEFERRED(0, "initiallyDeferred", "initiallyDeferred"),

	/**
	 * The '<em><b>Initially Immediate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INITIALLY_IMMEDIATE_VALUE
	 * @generated
	 * @ordered
	 */
	INITIALLY_IMMEDIATE(1, "initiallyImmediate", "initiallyImmediate"),

	/**
	 * The '<em><b>Not Deferrable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_DEFERRABLE_VALUE
	 * @generated
	 * @ordered
	 */
	NOT_DEFERRABLE(2, "notDeferrable", "notDeferrable");

	/**
	 * The '<em><b>Initially Deferred</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * By default, the test for Foreign Key validity is performed at the end of the transaction
	 * <!-- end-model-doc -->
	 * @see #INITIALLY_DEFERRED
	 * @model name="initiallyDeferred"
	 * @generated
	 * @ordered
	 */
	public static final int INITIALLY_DEFERRED_VALUE = 0;

	/**
	 * The '<em><b>Initially Immediate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * By default, the test for ForeignKey validity is performed at each statement
	 * <!-- end-model-doc -->
	 * @see #INITIALLY_IMMEDIATE
	 * @model name="initiallyImmediate"
	 * @generated
	 * @ordered
	 */
	public static final int INITIALLY_IMMEDIATE_VALUE = 1;

	/**
	 * The '<em><b>Not Deferrable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The test for Foreign Key validity is performed at each statement and cannot be changed in the transaction.
	 * <!-- end-model-doc -->
	 * @see #NOT_DEFERRABLE
	 * @model name="notDeferrable"
	 * @generated
	 * @ordered
	 */
	public static final int NOT_DEFERRABLE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Deferrability Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final DeferrabilityType[] VALUES_ARRAY =
		new DeferrabilityType[] {
			INITIALLY_DEFERRED,
			INITIALLY_IMMEDIATE,
			NOT_DEFERRABLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Deferrability Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<DeferrabilityType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Deferrability Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeferrabilityType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DeferrabilityType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Deferrability Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeferrabilityType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			DeferrabilityType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Deferrability Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DeferrabilityType get(int value) {
		switch (value) {
			case INITIALLY_DEFERRED_VALUE: return INITIALLY_DEFERRED;
			case INITIALLY_IMMEDIATE_VALUE: return INITIALLY_IMMEDIATE;
			case NOT_DEFERRABLE_VALUE: return NOT_DEFERRABLE;
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
	private DeferrabilityType(int value, String name, String literal) {
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
	
} //DeferrabilityType
