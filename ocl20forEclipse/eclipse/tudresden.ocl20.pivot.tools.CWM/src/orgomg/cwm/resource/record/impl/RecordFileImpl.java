/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.record.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

import orgomg.cwm.resource.record.RecordDef;
import orgomg.cwm.resource.record.RecordFile;
import orgomg.cwm.resource.record.RecordPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.record.impl.RecordFileImpl#isIsSelfDescribing <em>Is Self Describing</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.impl.RecordFileImpl#getRecordDelimiter <em>Record Delimiter</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.impl.RecordFileImpl#getSkipRecords <em>Skip Records</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.impl.RecordFileImpl#getRecord <em>Record</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RecordFileImpl extends PackageImpl implements RecordFile {
	/**
	 * The default value of the '{@link #isIsSelfDescribing() <em>Is Self Describing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSelfDescribing()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_SELF_DESCRIBING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsSelfDescribing() <em>Is Self Describing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsSelfDescribing()
	 * @generated
	 * @ordered
	 */
	protected boolean isSelfDescribing = IS_SELF_DESCRIBING_EDEFAULT;

	/**
	 * The default value of the '{@link #getRecordDelimiter() <em>Record Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecordDelimiter()
	 * @generated
	 * @ordered
	 */
	protected static final long RECORD_DELIMITER_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getRecordDelimiter() <em>Record Delimiter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecordDelimiter()
	 * @generated
	 * @ordered
	 */
	protected long recordDelimiter = RECORD_DELIMITER_EDEFAULT;

	/**
	 * The default value of the '{@link #getSkipRecords() <em>Skip Records</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipRecords()
	 * @generated
	 * @ordered
	 */
	protected static final long SKIP_RECORDS_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getSkipRecords() <em>Skip Records</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSkipRecords()
	 * @generated
	 * @ordered
	 */
	protected long skipRecords = SKIP_RECORDS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRecord() <em>Record</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRecord()
	 * @generated
	 * @ordered
	 */
	protected EList<RecordDef> record;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RecordFileImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RecordPackage.Literals.RECORD_FILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsSelfDescribing() {
		return isSelfDescribing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsSelfDescribing(boolean newIsSelfDescribing) {
		boolean oldIsSelfDescribing = isSelfDescribing;
		isSelfDescribing = newIsSelfDescribing;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RecordPackage.RECORD_FILE__IS_SELF_DESCRIBING, oldIsSelfDescribing, isSelfDescribing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getRecordDelimiter() {
		return recordDelimiter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRecordDelimiter(long newRecordDelimiter) {
		long oldRecordDelimiter = recordDelimiter;
		recordDelimiter = newRecordDelimiter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RecordPackage.RECORD_FILE__RECORD_DELIMITER, oldRecordDelimiter, recordDelimiter));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getSkipRecords() {
		return skipRecords;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSkipRecords(long newSkipRecords) {
		long oldSkipRecords = skipRecords;
		skipRecords = newSkipRecords;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RecordPackage.RECORD_FILE__SKIP_RECORDS, oldSkipRecords, skipRecords));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RecordDef> getRecord() {
		if (record == null) {
			record = new EObjectWithInverseResolvingEList.ManyInverse<RecordDef>(RecordDef.class, this, RecordPackage.RECORD_FILE__RECORD, RecordPackage.RECORD_DEF__FILE);
		}
		return record;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RecordPackage.RECORD_FILE__RECORD:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRecord()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case RecordPackage.RECORD_FILE__RECORD:
				return ((InternalEList<?>)getRecord()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RecordPackage.RECORD_FILE__IS_SELF_DESCRIBING:
				return isIsSelfDescribing();
			case RecordPackage.RECORD_FILE__RECORD_DELIMITER:
				return getRecordDelimiter();
			case RecordPackage.RECORD_FILE__SKIP_RECORDS:
				return getSkipRecords();
			case RecordPackage.RECORD_FILE__RECORD:
				return getRecord();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RecordPackage.RECORD_FILE__IS_SELF_DESCRIBING:
				setIsSelfDescribing((Boolean)newValue);
				return;
			case RecordPackage.RECORD_FILE__RECORD_DELIMITER:
				setRecordDelimiter((Long)newValue);
				return;
			case RecordPackage.RECORD_FILE__SKIP_RECORDS:
				setSkipRecords((Long)newValue);
				return;
			case RecordPackage.RECORD_FILE__RECORD:
				getRecord().clear();
				getRecord().addAll((Collection<? extends RecordDef>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case RecordPackage.RECORD_FILE__IS_SELF_DESCRIBING:
				setIsSelfDescribing(IS_SELF_DESCRIBING_EDEFAULT);
				return;
			case RecordPackage.RECORD_FILE__RECORD_DELIMITER:
				setRecordDelimiter(RECORD_DELIMITER_EDEFAULT);
				return;
			case RecordPackage.RECORD_FILE__SKIP_RECORDS:
				setSkipRecords(SKIP_RECORDS_EDEFAULT);
				return;
			case RecordPackage.RECORD_FILE__RECORD:
				getRecord().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case RecordPackage.RECORD_FILE__IS_SELF_DESCRIBING:
				return isSelfDescribing != IS_SELF_DESCRIBING_EDEFAULT;
			case RecordPackage.RECORD_FILE__RECORD_DELIMITER:
				return recordDelimiter != RECORD_DELIMITER_EDEFAULT;
			case RecordPackage.RECORD_FILE__SKIP_RECORDS:
				return skipRecords != SKIP_RECORDS_EDEFAULT;
			case RecordPackage.RECORD_FILE__RECORD:
				return record != null && !record.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isSelfDescribing: ");
		result.append(isSelfDescribing);
		result.append(", recordDelimiter: ");
		result.append(recordDelimiter);
		result.append(", skipRecords: ");
		result.append(skipRecords);
		result.append(')');
		return result.toString();
	}

} //RecordFileImpl
