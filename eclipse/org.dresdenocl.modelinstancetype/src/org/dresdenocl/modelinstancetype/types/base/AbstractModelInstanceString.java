/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Plug-in of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelinstancetype.types.base;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceString;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an abstract adaptation for {@link String}s of an
 * <code>IModelInstance</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractModelInstanceString extends
		AbstractModelInstanceElement implements IModelInstanceString {

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceString#getString
	 * ()
	 */
	public String getName() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		/* Probably return the element's name. */
		if (this.myName != null) {
			resultBuffer.append(this.myName);
		}

		/* Else probably return the element's id. */
		else if (this.myId != null) {
			resultBuffer.append(this.myId);
		}

		/* Else construct a name of all implemented types. */
		else {
			resultBuffer.append("MIString");
			resultBuffer.append("[");

			if (this.isUndefined()) {
				resultBuffer.append("undefined");
			}

			else {
				resultBuffer.append(this.getString().toString());
			}

			resultBuffer.append("]");
		}
		// no else.

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		boolean result;

		if (object == null) {
			result = false;
		}

		else if (object instanceof AbstractModelInstanceString) {

			AbstractModelInstanceString other;
			other = (AbstractModelInstanceString) object;

			/*
			 * This should not happen. But anyway, null == null results in false.
			 */
			if (this.isUndefined() || other.isUndefined()) {
				result = false;
			}

			else {
				result = this.getString().equals(other.getString());
			}
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#hashCode()
	 */
	@Override
	public int hashCode() {

		int result;

		if (this.getString() == null) {
			result = 0;
		}

		else {
			result = this.getString().hashCode();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement#isKindOf
	 * (tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public boolean isKindOf(Type type) {

		return EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
				.getOclString().conformsTo(type);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return (this.getString() == null);
	}
}