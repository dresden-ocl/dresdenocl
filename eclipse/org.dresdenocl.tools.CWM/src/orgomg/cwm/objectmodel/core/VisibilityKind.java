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
 * A representation of the literals of the enumeration '<em><b>Visibility Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * In the metamodel VisibilityKind defines an enumeration that denotes how the element to which it refers is seen outside the enclosing name space.
 * 
 * The default value is vk_public.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.objectmodel.core.CorePackage#getVisibilityKind()
 * @model
 * @generated
 */
public enum VisibilityKind implements Enumerator {
	/**
	 * The '<em><b>Vk public</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VK_PUBLIC_VALUE
	 * @generated
	 * @ordered
	 */
	VK_PUBLIC(0, "vk_public", "vk_public"),

	/**
	 * The '<em><b>Vk protected</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VK_PROTECTED_VALUE
	 * @generated
	 * @ordered
	 */
	VK_PROTECTED(1, "vk_protected", "vk_protected"),

	/**
	 * The '<em><b>Vk private</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VK_PRIVATE_VALUE
	 * @generated
	 * @ordered
	 */
	VK_PRIVATE(2, "vk_private", "vk_private"),

	/**
	 * The '<em><b>Vk package</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VK_PACKAGE_VALUE
	 * @generated
	 * @ordered
	 */
	VK_PACKAGE(3, "vk_package", "vk_package"),

	/**
	 * The '<em><b>Vk notapplicable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #VK_NOTAPPLICABLE_VALUE
	 * @generated
	 * @ordered
	 */
	VK_NOTAPPLICABLE(4, "vk_notapplicable", "vk_notapplicable");

	/**
	 * The '<em><b>Vk public</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Other elements may see and use the target element.
	 * <!-- end-model-doc -->
	 * @see #VK_PUBLIC
	 * @model name="vk_public"
	 * @generated
	 * @ordered
	 */
	public static final int VK_PUBLIC_VALUE = 0;

	/**
	 * The '<em><b>Vk protected</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Descendants of the source element may see and use the target element.
	 * <!-- end-model-doc -->
	 * @see #VK_PROTECTED
	 * @model name="vk_protected"
	 * @generated
	 * @ordered
	 */
	public static final int VK_PROTECTED_VALUE = 1;

	/**
	 * The '<em><b>Vk private</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Only the source element may see and use the target element.
	 * <!-- end-model-doc -->
	 * @see #VK_PRIVATE
	 * @model name="vk_private"
	 * @generated
	 * @ordered
	 */
	public static final int VK_PRIVATE_VALUE = 2;

	/**
	 * The '<em><b>Vk package</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Elements declared in the same package as the target element may see and use the target element. 
	 * <!-- end-model-doc -->
	 * @see #VK_PACKAGE
	 * @model name="vk_package"
	 * @generated
	 * @ordered
	 */
	public static final int VK_PACKAGE_VALUE = 3;

	/**
	 * The '<em><b>Vk notapplicable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * May be used where namespaces do not support the concept of visibility.
	 * <!-- end-model-doc -->
	 * @see #VK_NOTAPPLICABLE
	 * @model name="vk_notapplicable"
	 * @generated
	 * @ordered
	 */
	public static final int VK_NOTAPPLICABLE_VALUE = 4;

	/**
	 * An array of all the '<em><b>Visibility Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final VisibilityKind[] VALUES_ARRAY =
		new VisibilityKind[] {
			VK_PUBLIC,
			VK_PROTECTED,
			VK_PRIVATE,
			VK_PACKAGE,
			VK_NOTAPPLICABLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Visibility Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<VisibilityKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Visibility Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VisibilityKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VisibilityKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Visibility Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VisibilityKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			VisibilityKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Visibility Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VisibilityKind get(int value) {
		switch (value) {
			case VK_PUBLIC_VALUE: return VK_PUBLIC;
			case VK_PROTECTED_VALUE: return VK_PROTECTED;
			case VK_PRIVATE_VALUE: return VK_PRIVATE;
			case VK_PACKAGE_VALUE: return VK_PACKAGE;
			case VK_NOTAPPLICABLE_VALUE: return VK_NOTAPPLICABLE;
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
	private VisibilityKind(int value, String name, String literal) {
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
	
} //VisibilityKind
