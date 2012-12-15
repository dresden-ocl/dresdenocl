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
 * A representation of the literals of the enumeration '<em><b>Nullable Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Used in Column.
 * Indicates if a Column may contain null values.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.resource.relational.enumerations.EnumerationsPackage#getNullableType()
 * @model
 * @generated
 */
public enum NullableType implements Enumerator {
	/**
	 * The '<em><b>Column No Nulls</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COLUMN_NO_NULLS_VALUE
	 * @generated
	 * @ordered
	 */
	COLUMN_NO_NULLS(0, "columnNoNulls", "columnNoNulls"),

	/**
	 * The '<em><b>Column Nullable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COLUMN_NULLABLE_VALUE
	 * @generated
	 * @ordered
	 */
	COLUMN_NULLABLE(1, "columnNullable", "columnNullable"),

	/**
	 * The '<em><b>Column Nullable Unknown</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COLUMN_NULLABLE_UNKNOWN_VALUE
	 * @generated
	 * @ordered
	 */
	COLUMN_NULLABLE_UNKNOWN(2, "columnNullableUnknown", "columnNullableUnknown");

	/**
	 * The '<em><b>Column No Nulls</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Column may not contain null values
	 * <!-- end-model-doc -->
	 * @see #COLUMN_NO_NULLS
	 * @model name="columnNoNulls"
	 * @generated
	 * @ordered
	 */
	public static final int COLUMN_NO_NULLS_VALUE = 0;

	/**
	 * The '<em><b>Column Nullable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The column may contain null values
	 * <!-- end-model-doc -->
	 * @see #COLUMN_NULLABLE
	 * @model name="columnNullable"
	 * @generated
	 * @ordered
	 */
	public static final int COLUMN_NULLABLE_VALUE = 1;

	/**
	 * The '<em><b>Column Nullable Unknown</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * It is not known whether null values are allowed or not. 
	 * <!-- end-model-doc -->
	 * @see #COLUMN_NULLABLE_UNKNOWN
	 * @model name="columnNullableUnknown"
	 * @generated
	 * @ordered
	 */
	public static final int COLUMN_NULLABLE_UNKNOWN_VALUE = 2;

	/**
	 * An array of all the '<em><b>Nullable Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NullableType[] VALUES_ARRAY =
		new NullableType[] {
			COLUMN_NO_NULLS,
			COLUMN_NULLABLE,
			COLUMN_NULLABLE_UNKNOWN,
		};

	/**
	 * A public read-only list of all the '<em><b>Nullable Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<NullableType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Nullable Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NullableType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NullableType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Nullable Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NullableType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NullableType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Nullable Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NullableType get(int value) {
		switch (value) {
			case COLUMN_NO_NULLS_VALUE: return COLUMN_NO_NULLS;
			case COLUMN_NULLABLE_VALUE: return COLUMN_NULLABLE;
			case COLUMN_NULLABLE_UNKNOWN_VALUE: return COLUMN_NULLABLE_UNKNOWN;
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
	private NullableType(int value, String name, String literal) {
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
	
} //NullableType
