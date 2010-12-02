/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.datatype;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Wait Rule Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * waitForAll, waitForAny
 * <!-- end-model-doc -->
 * @see orgomg.cwm.management.warehouseprocess.datatype.DatatypePackage#getWaitRuleType()
 * @model
 * @generated
 */
public enum WaitRuleType implements Enumerator {
	/**
	 * The '<em><b>Wait For All</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WAIT_FOR_ALL_VALUE
	 * @generated
	 * @ordered
	 */
	WAIT_FOR_ALL(0, "waitForAll", "waitForAll"),

	/**
	 * The '<em><b>Wait For Any</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WAIT_FOR_ANY_VALUE
	 * @generated
	 * @ordered
	 */
	WAIT_FOR_ANY(1, "waitForAny", "waitForAny");

	/**
	 * The '<em><b>Wait For All</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Wait For All</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WAIT_FOR_ALL
	 * @model name="waitForAll"
	 * @generated
	 * @ordered
	 */
	public static final int WAIT_FOR_ALL_VALUE = 0;

	/**
	 * The '<em><b>Wait For Any</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Wait For Any</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #WAIT_FOR_ANY
	 * @model name="waitForAny"
	 * @generated
	 * @ordered
	 */
	public static final int WAIT_FOR_ANY_VALUE = 1;

	/**
	 * An array of all the '<em><b>Wait Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final WaitRuleType[] VALUES_ARRAY =
		new WaitRuleType[] {
			WAIT_FOR_ALL,
			WAIT_FOR_ANY,
		};

	/**
	 * A public read-only list of all the '<em><b>Wait Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<WaitRuleType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Wait Rule Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WaitRuleType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WaitRuleType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wait Rule Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WaitRuleType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			WaitRuleType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Wait Rule Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WaitRuleType get(int value) {
		switch (value) {
			case WAIT_FOR_ALL_VALUE: return WAIT_FOR_ALL;
			case WAIT_FOR_ANY_VALUE: return WAIT_FOR_ANY;
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
	private WaitRuleType(int value, String name, String literal) {
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
	
} //WaitRuleType
