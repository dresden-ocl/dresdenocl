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
 * A representation of the literals of the enumeration '<em><b>Scope Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * In the metamodel ScopeKind defines an enumeration that denotes whether a feature belongs to individual instances or an entire classifier.
 * 
 * The default value is sk_instance.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.objectmodel.core.CorePackage#getScopeKind()
 * @model
 * @generated
 */
public enum ScopeKind implements Enumerator {
	/**
	 * The '<em><b>Sk instance</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SK_INSTANCE_VALUE
	 * @generated
	 * @ordered
	 */
	SK_INSTANCE(0, "sk_instance", "sk_instance"),

	/**
	 * The '<em><b>Sk classifier</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SK_CLASSIFIER_VALUE
	 * @generated
	 * @ordered
	 */
	SK_CLASSIFIER(1, "sk_classifier", "sk_classifier");

	/**
	 * The '<em><b>Sk instance</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The feature pertains to instances of a Classifier. For example, it is a distinct attribute in each instance or an operation that works on an instance.
	 * <!-- end-model-doc -->
	 * @see #SK_INSTANCE
	 * @model name="sk_instance"
	 * @generated
	 * @ordered
	 */
	public static final int SK_INSTANCE_VALUE = 0;

	/**
	 * The '<em><b>Sk classifier</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The feature pertains to an entire Classifier. For example, it is an attribute shared by the entire Classifier or an operation that works on the Classifier, such as a creation operation.
	 * <!-- end-model-doc -->
	 * @see #SK_CLASSIFIER
	 * @model name="sk_classifier"
	 * @generated
	 * @ordered
	 */
	public static final int SK_CLASSIFIER_VALUE = 1;

	/**
	 * An array of all the '<em><b>Scope Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ScopeKind[] VALUES_ARRAY =
		new ScopeKind[] {
			SK_INSTANCE,
			SK_CLASSIFIER,
		};

	/**
	 * A public read-only list of all the '<em><b>Scope Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ScopeKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Scope Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScopeKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ScopeKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scope Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScopeKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ScopeKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Scope Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ScopeKind get(int value) {
		switch (value) {
			case SK_INSTANCE_VALUE: return SK_INSTANCE;
			case SK_CLASSIFIER_VALUE: return SK_CLASSIFIER;
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
	private ScopeKind(int value, String name, String literal) {
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
	
} //ScopeKind
