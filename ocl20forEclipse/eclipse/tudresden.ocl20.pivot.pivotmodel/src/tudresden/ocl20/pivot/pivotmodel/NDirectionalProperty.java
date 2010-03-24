/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.pivotmodel;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>NDirectional Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <p>
 * A <code>NDirectionalAssociation</code> is a {@link Property} 
 * typed element that represents an bidirectional association to another type.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty#getInverseNDirectionalProperties <em>Inverse NDirectional Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface NDirectionalProperty extends Property {
	/**
	 * Returns the value of the '<em><b>Inverse NDirectional Properties</b></em>' containment reference list.
	 * The list contents are of type {@link tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inverse NDirectional Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inverse NDirectional Properties</em>' containment reference list.
	 * @generated
	 */
	List<NDirectionalProperty> getInverseNDirectionalProperties();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The operation addAssociation added a <code> NDirectionalAssociation</code> to the inverse association list.
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	void addAssociation(NDirectionalProperty bProperty);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The operation removeAssociation removed a <code>NDirectionalAssociation</code> from the inverse association list.
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	NDirectionalProperty getAssociation(String propName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The operation removeAssociation removed a <code>NDirectionalAssociation</code> from the inverse association list.
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	void removeAssociation(NDirectionalProperty bProperty);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The operation isInverseAssociation ckecked a <code>NDirectionalAssociation</code> is in the inverse association list.
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean isInverseAssociation(NDirectionalProperty bProperty);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The operation addAssociation added a <code>NDirectionalAssociation</code> to the inverse association list.
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	void addAssociations(List<NDirectionalProperty> bProperty);

} // NDirectionalProperty
