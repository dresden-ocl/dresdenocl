/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Ordering Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * In the metamodel OrderingKind defines an enumeration that specifies how the elements of a set are arranged. Used in conjunction with elements that have a multiplicity in cases when the multiplicity value is greater than one. The ordering must be determined and maintained by operations that modify the set.
 * 
 * The default value is ok_unordered.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.objectmodel.core.CorePackage#getOrderingKind()
 * @model
 * @generated
 */
public enum OrderingKind implements Enumerator {
	/**
	 * The '<em><b>Ok unordered</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OK_UNORDERED_VALUE
	 * @generated
	 * @ordered
	 */
	OK_UNORDERED(0, "ok_unordered", "ok_unordered"),

	/**
	 * The '<em><b>Ok ordered</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OK_ORDERED_VALUE
	 * @generated
	 * @ordered
	 */
	OK_ORDERED(1, "ok_ordered", "ok_ordered");

	/**
	 * The '<em><b>Ok unordered</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The elements of the set have no inherent ordering.
	 * <!-- end-model-doc -->
	 * @see #OK_UNORDERED
	 * @model name="ok_unordered"
	 * @generated
	 * @ordered
	 */
	public static final int OK_UNORDERED_VALUE = 0;

	/**
	 * The '<em><b>Ok ordered</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The elements of the set have a sequential ordering.
	 * <!-- end-model-doc -->
	 * @see #OK_ORDERED
	 * @model name="ok_ordered"
	 * @generated
	 * @ordered
	 */
	public static final int OK_ORDERED_VALUE = 1;

	/**
	 * An array of all the '<em><b>Ordering Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final OrderingKind[] VALUES_ARRAY =
		new OrderingKind[] {
			OK_UNORDERED,
			OK_ORDERED,
		};

	/**
	 * A public read-only list of all the '<em><b>Ordering Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<OrderingKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Ordering Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			OrderingKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Ordering Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static OrderingKind get(int value) {
		switch (value) {
			case OK_UNORDERED_VALUE: return OK_UNORDERED;
			case OK_ORDERED_VALUE: return OK_ORDERED;
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
	private OrderingKind(int value, String name, String literal) {
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
	
} //OrderingKind
