/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

 * OCL 2 Compiler                                                    *

 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *

 * All rights reserved.                                              *

 *                                                                   *

 * This work was done as a diploma project at the Chair for Software *

 * Technology, Dresden University Of Technology, Germany             *

 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *

 * modification not identified as such is not covered by the         *

 * preceding statement.                                              *

 *                                                                   *

 * This work is free software; you can redistribute it and/or        *

 * modify it under the terms of the GNU Library General Public       *

 * License as published by the Free Software Foundation; either      *

 * version 2 of the License, or (at your option) any later version.  *

 *                                                                   *

 * This work is distributed in the hope that it will be useful,      *

 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *

 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *

 * Library General Public License for more details.                  *

 *                                                                   *

 * You should have received a copy of the GNU Library General Public *

 * License along with this library; if not, write to the             *

 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *

 * Boston, MA  02111-1307, USA.                                      *

 *                                                                   *

 * To submit a bug report, send a comment, or get the latest news on *

 * this project and other projects, please visit the web site:       *

 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *

 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *

 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.core.lib;

/**
 * 
 * <p>Represents an object from the model.</p>
 * 
 * <p>In nearly all cases this class could be considered as an abstract 
 * class because sub classes like <code>JmiModelObject</code> or
 * <code>UmlModelObject</code> overwrite methods like 
 * <code>getFeaturerImpl</code> or <code>isEqualTo</code>.</p>
 * 
 * <p>But the class <code>Ocl</code> contains a method <code>toOclModelObject</code>
 * which casts instances of <code>OclRoot</code> to <code>OclModelObject</code>.
 * That's why <code>OclModelObject</code> isn't designed as an abstract class.</p>
 * 
 * <p><i>(Documentation completed by Claas Wilke in July 2007)</i></p>
 * 
 * @see tudresden.ocl20.core.lib.Ocl
 * @see tudresden.ocl20.core.lib.JmiModelObject
 * @see tudresden.ocl20.core.lib.UmlModelObject
 * 
 * @author Stefan Ocke
 * 
 */
public class OclModelObject extends OclAny {

	protected OclFactory factory;

	/** Creates a new instance of ModelObject */

	protected OclModelObject(OclFactory factory) {

		this.factory = factory;

	}

    /**
     * <p>Constructs an instance representing an undefined <code>OclModelObject.</code></p>
     * 
	 * @param undefinedreason The reason why the <code>OclModelObject</code> is undefined.
     */
	/* Documentation added an Constructor refactored by Claas Wilke in July 2007. */
	public OclModelObject(String undefinedReason) {
		super(undefinedReason);
	}

	
	/**
	 * <p>Compares the <code>OclModelObject</code> to a given <code>Object</code>.</p>
	 * 
	 * <p><b>ATTENTION: Reimplement this method in sub classes because this implementation always
	 * returns an undefined <code>OclBoolean</code>!</b></p>
	 * 
	 * <p><i>(Documentation completed by Claas Wilke in July 2007)</i></p>
	 *
	 * @return OclBoolean 
	 */
	public OclBoolean isEqualTo(Object o) {
		/* Constructor changed during refactoring by Claas Wilke in July 2007. */
		return new OclBoolean(getUndefinedReason());

	}

	/**
	 * <p>Checks, if the <code>OclModelObject</code> is in a given <code>OclState</code>.</p>
	 * 
	 * <p><b>ATTENTION: Reimplement this method in sub classes because this implementation always
	 * returns an undefined <code>OclBoolean</code>!</b></p>
	 * 
	 * <p><i>(Documentation completed by Claas Wilke in July 2007)</i></p>
	 *
	 * @return OclBoolean 
	 */
	public OclBoolean oclInState(OclState state) {
		/* Constructor changed during refactoring by Claas Wilke in July 2007. */
		return new OclBoolean(getUndefinedReason());

	}

	// /** Gets a feature of this modelobject. The OCL type of the result is
	// determined by its Java Class.*/

	// public OclRoot getFeature(String name){

	// if(isUndefined()){

	// return new OclUndefined(this.getUndefinedReason());

	// }

	// Object result = getFeatureImpl(name);

	//        

	// return factory.getOclRepresentationFor(result);

	// }

	/**
	 * Gets a feature of this modelobject. Explicitly stating the expected OCL
	 * type.
	 */

	public OclRoot getFeature(OclType type, String name) {

		if (isUndefined()) {

			return new OclUndefined(this.getUndefinedReason());

		}

		Object result = getFeatureImpl(name);

		OclRoot or = factory.getOclRepresentationFor(type, result);

		if (or.isUndefined()) {

			return new OclUndefined("feature " + name + " is null in " + this);

		}

		return or;

	}

	/**
	 * <p>Returns a feature implementation of this <code>OclModelObject</code>.</p>
	 * 
	 * <p><b>ATTENTION: Reimplement this method in sub classes because this implementation always
	 * returns <code>null</code>!</b></p>
	 * 
	 * <p><i>(Documentation completed by Claas Wilke in July 2007)</i></p>
	 *
	 * @return Object 
	 */
	protected Object getFeatureImpl(String name) {

		return null;

	}

	/**
	 * Calls an operation of this modelobject. Explicitly stating the expected
	 * OCL type.
	 */

	public OclRoot getFeature(OclType type, String name,
			OclParameter[] parameters) {

		if (isUndefined()) {

			return new OclUndefined(this.getUndefinedReason());

		}

		Object[] params;

		boolean hasOutParams = false;

		if (parameters == null) {

			params = new Object[0];

		}

		else {

			params = new Object[parameters.length];

			for (int i = 0; i < params.length; i++) {

				params[i] = factory.reconvert(parameters[i]);

				int dir = parameters[i].getDirection();

				if (dir == OclParameter.DIR_INOUT
						|| dir == OclParameter.DIR_OUT) {

					hasOutParams = true;

				}

			}

		}

		Object result = getFeatureImpl(name, params);

		OclRoot or;

		if (!hasOutParams) {

			or = factory.getOclRepresentationFor(type, result);

			// provide some more precise undefined reason

			if (or.isUndefined()) {

				or = new OclUndefined("feature " + name
						+ "(): result is null in " + this);

			}

		} else {

			OclTupleType tt = (OclTupleType) type;

			OclRoot value;

			// the operattion has out or inout-parameters -> construct a tuple
			// containing all results

			OclTuple tuple = new OclTuple();

			value = factory.getOclRepresentationFor(tt.getType("result"),
					result);

			if (value.isUndefined()) {

				value = new OclUndefined("feature " + name
						+ "(): result is null in " + this);

			}

			tuple.setValue("result", value);

			for (int i = 0; i < params.length; i++) {

				int dir = parameters[i].getDirection();

				if (dir == OclParameter.DIR_INOUT
						|| dir == OclParameter.DIR_OUT) {

					String paramName = parameters[i].getName();

					value = factory.getOclRepresentationFor(tt
							.getType(paramName), params[i]);

					if (value.isUndefined()) {

						value = new OclUndefined("feature " + name
								+ "(): out/inout parameter" + paramName
								+ " is null in " + this);

					}

					tuple.setValue(paramName, value);

				}

			}

			or = tuple;

		}

		return or;

	}

	/**
	 * <p>Returns a feature implementation of this <code>OclModelObject</code>.</p>
	 * 
	 * <p><b>ATTENTION: Reimplement this method in sub classes because this implementation always
	 * returns <code>null</code>!</b></p>
	 * 
	 * <p><i>(Documentation completed by Claas Wilke in July 2007)</i></p>
	 *
	 * @return Object 
	 */
	protected Object getFeatureImpl(String name, Object[] parameters) {

		return null;

	}

	// /** Calls an operation of this modelobject. The OCL type of the result is
	// determined by its Java Class.*/

	// // issue: what about out-parameters? TupleType ....

	//

	// public OclRoot getFeature(String name, OclRoot [] parameters){

	// if(isUndefined()){

	// return new OclUndefined(this.getUndefinedReason());

	// }

	// Object [] params;

	// if (parameters==null){

	// params = new Object[0];

	// }

	// else{

	// params = new Object[parameters.length];

	// for(int i = 0; i<params.length; i++){

	// params[i] = factory.reconvert(parameters[i]);

	// }

	// }

	// Object result = getFeatureImpl(name, params);

	//

	// return factory.getOclRepresentationFor(result);

	// }

}
