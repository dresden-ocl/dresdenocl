/*
Copyright (C) 2000  Sten Loecher

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package tudresden.ocl.codegen.decl;

import tudresden.ocl.codegen.CodeFragment;

/**
 * Objects of this class represent declarative code fragments.
 * @author Sten Loecher
 */
public class DeclarativeCodeFragment implements CodeFragment {
    protected String constraintName;
    protected String constraintType;
    protected String code;
    protected String additionalInfo[];

    public DeclarativeCodeFragment(String name, String type, String code, String[] additionalInfo) {
	constraintName = name;
	constraintType = type;
	this.code = code;
        this.additionalInfo = additionalInfo;
    }

    /**
     * @return the constrained type
     */
    public String getConstrainedType() {
	return constraintType;
    }

    /**
     * @return always null
     */
    public String getConstrainedOperation() {
	return null;
    }

    /**
     * @return the generated code for the constraint
     */
    public String getCode() {
	return code;
    }

    /**
     * @return the name of the constraint
     */
    public String getName() {
	return constraintName;
    }

    /**
     * @return always CodeFragment.INV
     */
    public int getKind() {
	return CodeFragment.INV;
    }

    /**
     * @return always null
     */
    public String getResultVariable() {
	return null;
    }
    
    /**
     * @return additional Information about the code fragment
     */
    public String[] getAdditionalInfo() {
        return additionalInfo;
    }
}