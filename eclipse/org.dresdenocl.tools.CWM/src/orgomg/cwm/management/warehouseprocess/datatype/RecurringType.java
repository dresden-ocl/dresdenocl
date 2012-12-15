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
 * A representation of the literals of the enumeration '<em><b>Recurring Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * everyYear, everyMonth, everyWeek, everyDay, everyHour, everyMinute
 * <!-- end-model-doc -->
 * @see orgomg.cwm.management.warehouseprocess.datatype.DatatypePackage#getRecurringType()
 * @model
 * @generated
 */
public enum RecurringType implements Enumerator {
	/**
	 * The '<em><b>Every Year</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVERY_YEAR_VALUE
	 * @generated
	 * @ordered
	 */
	EVERY_YEAR(0, "everyYear", "everyYear"),

	/**
	 * The '<em><b>Every Month</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVERY_MONTH_VALUE
	 * @generated
	 * @ordered
	 */
	EVERY_MONTH(1, "everyMonth", "everyMonth"),

	/**
	 * The '<em><b>Every Week</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVERY_WEEK_VALUE
	 * @generated
	 * @ordered
	 */
	EVERY_WEEK(2, "everyWeek", "everyWeek"),

	/**
	 * The '<em><b>Every Day</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVERY_DAY_VALUE
	 * @generated
	 * @ordered
	 */
	EVERY_DAY(3, "everyDay", "everyDay"),

	/**
	 * The '<em><b>Every Hour</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVERY_HOUR_VALUE
	 * @generated
	 * @ordered
	 */
	EVERY_HOUR(4, "everyHour", "everyHour"),

	/**
	 * The '<em><b>Every Minute</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #EVERY_MINUTE_VALUE
	 * @generated
	 * @ordered
	 */
	EVERY_MINUTE(5, "everyMinute", "everyMinute");

	/**
	 * The '<em><b>Every Year</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Every Year</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVERY_YEAR
	 * @model name="everyYear"
	 * @generated
	 * @ordered
	 */
	public static final int EVERY_YEAR_VALUE = 0;

	/**
	 * The '<em><b>Every Month</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Every Month</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVERY_MONTH
	 * @model name="everyMonth"
	 * @generated
	 * @ordered
	 */
	public static final int EVERY_MONTH_VALUE = 1;

	/**
	 * The '<em><b>Every Week</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Every Week</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVERY_WEEK
	 * @model name="everyWeek"
	 * @generated
	 * @ordered
	 */
	public static final int EVERY_WEEK_VALUE = 2;

	/**
	 * The '<em><b>Every Day</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Every Day</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVERY_DAY
	 * @model name="everyDay"
	 * @generated
	 * @ordered
	 */
	public static final int EVERY_DAY_VALUE = 3;

	/**
	 * The '<em><b>Every Hour</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Every Hour</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVERY_HOUR
	 * @model name="everyHour"
	 * @generated
	 * @ordered
	 */
	public static final int EVERY_HOUR_VALUE = 4;

	/**
	 * The '<em><b>Every Minute</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Every Minute</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #EVERY_MINUTE
	 * @model name="everyMinute"
	 * @generated
	 * @ordered
	 */
	public static final int EVERY_MINUTE_VALUE = 5;

	/**
	 * An array of all the '<em><b>Recurring Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final RecurringType[] VALUES_ARRAY =
		new RecurringType[] {
			EVERY_YEAR,
			EVERY_MONTH,
			EVERY_WEEK,
			EVERY_DAY,
			EVERY_HOUR,
			EVERY_MINUTE,
		};

	/**
	 * A public read-only list of all the '<em><b>Recurring Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<RecurringType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Recurring Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RecurringType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RecurringType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Recurring Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RecurringType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			RecurringType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Recurring Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RecurringType get(int value) {
		switch (value) {
			case EVERY_YEAR_VALUE: return EVERY_YEAR;
			case EVERY_MONTH_VALUE: return EVERY_MONTH;
			case EVERY_WEEK_VALUE: return EVERY_WEEK;
			case EVERY_DAY_VALUE: return EVERY_DAY;
			case EVERY_HOUR_VALUE: return EVERY_HOUR;
			case EVERY_MINUTE_VALUE: return EVERY_MINUTE;
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
	private RecurringType(int value, String name, String literal) {
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
	
} //RecurringType
