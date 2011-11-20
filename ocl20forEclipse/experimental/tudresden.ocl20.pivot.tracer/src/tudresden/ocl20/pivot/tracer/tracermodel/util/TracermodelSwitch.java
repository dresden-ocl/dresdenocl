/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.util;

import java.util.Map;
import java.util.UUID;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import tudresden.ocl20.pivot.tracer.tracermodel.*;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage
 * @generated
 */
public class TracermodelSwitch<T> extends Switch<T> {
    /**
         * The cached model package
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * @generated
         */
    protected static TracermodelPackage modelPackage;

    /**
         * Creates an instance of the switch.
         * <!-- begin-user-doc --> <!--
     * end-user-doc -->
         * @generated
         */
    public TracermodelSwitch() {
                if (modelPackage == null) {
                        modelPackage = TracermodelPackage.eINSTANCE;
                }
        }

    /**
     * Checks whether this is a switch for the given package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
                return ePackage == modelPackage;
        }

    /**
         * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
         * <!-- begin-user-doc --> <!--
     * end-user-doc -->
         * @return the first non-null result returned by a <code>caseXXX</code> call.
         * @generated
         */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
                switch (classifierID) {
                        case TracermodelPackage.TRACER_ITEM: {
                                TracerItem tracerItem = (TracerItem)theEObject;
                                T result = caseTracerItem(tracerItem);
                                if (result == null) result = defaultCase(theEObject);
                                return result;
                        }
                        case TracermodelPackage.TRACER_ROOT: {
                                TracerRoot tracerRoot = (TracerRoot)theEObject;
                                T result = caseTracerRoot(tracerRoot);
                                if (result == null) result = defaultCase(theEObject);
                                return result;
                        }
                        case TracermodelPackage.UUID_TO_TRACER_ITEM_MAP: {
                                @SuppressWarnings("unchecked") Map.Entry<UUID, TracerItem> uuidToTracerItemMap = (Map.Entry<UUID, TracerItem>)theEObject;
                                T result = caseUUIDToTracerItemMap(uuidToTracerItemMap);
                                if (result == null) result = defaultCase(theEObject);
                                return result;
                        }
                        default: return defaultCase(theEObject);
                }
        }

    /**
         * Returns the result of interpreting the object as an instance of '<em>Tracer Item</em>'.
         * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
         * @param object the target of the switch.
         * @return the result of interpreting the object as an instance of '<em>Tracer Item</em>'.
         * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
         * @generated
         */
    public T caseTracerItem(TracerItem object) {
                return null;
        }

    /**
         * Returns the result of interpreting the object as an instance of '<em>Tracer Root</em>'.
         * <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
         * @param object the target of the switch.
         * @return the result of interpreting the object as an instance of '<em>Tracer Root</em>'.
         * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
         * @generated
         */
    public T caseTracerRoot(TracerRoot object) {
                return null;
        }

    /**
         * Returns the result of interpreting the object as an instance of '<em>UUID To Tracer Item Map</em>'.
         * <!-- begin-user-doc -->
         * This implementation returns null;
         * returning a non-null result will terminate the switch.
         * <!-- end-user-doc -->
         * @param object the target of the switch.
         * @return the result of interpreting the object as an instance of '<em>UUID To Tracer Item Map</em>'.
         * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
         * @generated
         */
        public T caseUUIDToTracerItemMap(Map.Entry<UUID, TracerItem> object) {
                return null;
        }

/**
         * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
         * <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
         * @param object the target of the switch.
         * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
         * @see #doSwitch(org.eclipse.emf.ecore.EObject)
         * @generated
         */
    @Override
    public T defaultCase(EObject object) {
                return null;
        }

} // TracermodelSwitch
