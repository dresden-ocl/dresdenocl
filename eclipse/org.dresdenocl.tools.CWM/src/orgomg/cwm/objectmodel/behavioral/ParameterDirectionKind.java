/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Parameter Direction Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * In the metamodel ParameterDirectionKind defines an enumeration that denotes if a Parameter is used for supplying an argument and/or for returning a value. The enumeration values are:
 * 
 * The default value is pdk_in.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getParameterDirectionKind()
 * @model
 * @generated
 */
public enum ParameterDirectionKind implements Enumerator {
	/**
	 * The '<em><b>Pdk in</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PDK_IN_VALUE
	 * @generated
	 * @ordered
	 */
	PDK_IN(0, "pdk_in", "pdk_in"),

	/**
	 * The '<em><b>Pdk inout</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PDK_INOUT_VALUE
	 * @generated
	 * @ordered
	 */
	PDK_INOUT(1, "pdk_inout", "pdk_inout"),

	/**
	 * The '<em><b>Pdk out</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PDK_OUT_VALUE
	 * @generated
	 * @ordered
	 */
	PDK_OUT(2, "pdk_out", "pdk_out"),

	/**
	 * The '<em><b>Pdk return</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PDK_RETURN_VALUE
	 * @generated
	 * @ordered
	 */
	PDK_RETURN(3, "pdk_return", "pdk_return");

	/**
	 * The '<em><b>Pdk in</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An input Parameter (may not be modified).
	 * <!-- end-model-doc -->
	 * @see #PDK_IN
	 * @model name="pdk_in"
	 * @generated
	 * @ordered
	 */
	public static final int PDK_IN_VALUE = 0;

	/**
	 * The '<em><b>Pdk inout</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An input Parameter that may be modified.
	 * <!-- end-model-doc -->
	 * @see #PDK_INOUT
	 * @model name="pdk_inout"
	 * @generated
	 * @ordered
	 */
	public static final int PDK_INOUT_VALUE = 1;

	/**
	 * The '<em><b>Pdk out</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An output Parameter (may be modified to communicate information to the caller).
	 * <!-- end-model-doc -->
	 * @see #PDK_OUT
	 * @model name="pdk_out"
	 * @generated
	 * @ordered
	 */
	public static final int PDK_OUT_VALUE = 2;

	/**
	 * The '<em><b>Pdk return</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A return value of a call.
	 * <!-- end-model-doc -->
	 * @see #PDK_RETURN
	 * @model name="pdk_return"
	 * @generated
	 * @ordered
	 */
	public static final int PDK_RETURN_VALUE = 3;

	/**
	 * An array of all the '<em><b>Parameter Direction Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ParameterDirectionKind[] VALUES_ARRAY =
		new ParameterDirectionKind[] {
			PDK_IN,
			PDK_INOUT,
			PDK_OUT,
			PDK_RETURN,
		};

	/**
	 * A public read-only list of all the '<em><b>Parameter Direction Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ParameterDirectionKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Parameter Direction Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterDirectionKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParameterDirectionKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Parameter Direction Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterDirectionKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ParameterDirectionKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Parameter Direction Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ParameterDirectionKind get(int value) {
		switch (value) {
			case PDK_IN_VALUE: return PDK_IN;
			case PDK_INOUT_VALUE: return PDK_INOUT;
			case PDK_OUT_VALUE: return PDK_OUT;
			case PDK_RETURN_VALUE: return PDK_RETURN;
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
	private ParameterDirectionKind(int value, String name, String literal) {
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
	
} //ParameterDirectionKind
