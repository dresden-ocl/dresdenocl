/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.relationships;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Aggregation Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * An enumeration that denotes what kind of aggregation an Association defines. When placed on a target end, specifies the relationship of the target end to the source end. 
 * 
 * The default value is ak_none.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getAggregationKind()
 * @model
 * @generated
 */
public enum AggregationKind implements Enumerator {
	/**
	 * The '<em><b>Ak none</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AK_NONE_VALUE
	 * @generated
	 * @ordered
	 */
	AK_NONE(0, "ak_none", "ak_none"),

	/**
	 * The '<em><b>Ak aggregate</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AK_AGGREGATE_VALUE
	 * @generated
	 * @ordered
	 */
	AK_AGGREGATE(1, "ak_aggregate", "ak_aggregate"),

	/**
	 * The '<em><b>Ak composite</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AK_COMPOSITE_VALUE
	 * @generated
	 * @ordered
	 */
	AK_COMPOSITE(2, "ak_composite", "ak_composite");

	/**
	 * The '<em><b>Ak none</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The end is not an aggregate.
	 * <!-- end-model-doc -->
	 * @see #AK_NONE
	 * @model name="ak_none"
	 * @generated
	 * @ordered
	 */
	public static final int AK_NONE_VALUE = 0;

	/**
	 * The '<em><b>Ak aggregate</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The end is an aggregate; therefore, the other end is a part and must have the
	 * aggregation value of none. The part may be contained in other aggregates.
	 * <!-- end-model-doc -->
	 * @see #AK_AGGREGATE
	 * @model name="ak_aggregate"
	 * @generated
	 * @ordered
	 */
	public static final int AK_AGGREGATE_VALUE = 1;

	/**
	 * The '<em><b>Ak composite</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The end is a composite; therefore, the other end is a part and must have the aggregation value of none. The part is strongly owned by the composite and may not be part of any other composite.
	 * 
	 * <!-- end-model-doc -->
	 * @see #AK_COMPOSITE
	 * @model name="ak_composite"
	 * @generated
	 * @ordered
	 */
	public static final int AK_COMPOSITE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Aggregation Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AggregationKind[] VALUES_ARRAY =
		new AggregationKind[] {
			AK_NONE,
			AK_AGGREGATE,
			AK_COMPOSITE,
		};

	/**
	 * A public read-only list of all the '<em><b>Aggregation Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AggregationKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Aggregation Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AggregationKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AggregationKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Aggregation Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AggregationKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AggregationKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Aggregation Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static AggregationKind get(int value) {
		switch (value) {
			case AK_NONE_VALUE: return AK_NONE;
			case AK_AGGREGATE_VALUE: return AK_AGGREGATE;
			case AK_COMPOSITE_VALUE: return AK_COMPOSITE;
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
	private AggregationKind(int value, String name, String literal) {
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
	
} //AggregationKind
