/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
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
// FILE: d:/projekte/diplom/classes/tudresden/ocl/codegen/ProceduralCodeFragment.java

package tudresden.ocl.codegen;

public class ProceduralCodeFragment implements CodeFragment {

  protected String constraintName;
  protected String constrainedType;
  protected String constrainedOperation;
  /** one of CodeFragment.PRE, CodeFragment.POST, CodeFragement.INV,
   *  CodeFragment.PREPARATION, CodeFragment.TRANSFER
   */
  protected int constraintKind;

  protected String code;

  protected String resultVariable;

  /**
   *  @param constrainedOperation is <code>null</code> for invariants
   *  @param constraintKind one of CodeFragment.PRE, CodeFragment.POST,
   *              CodeFragement.INV, CodeFragment.PREPARATION, CodeFragment.TRANSFER
   */
  public ProceduralCodeFragment(
      String constraintName,
      String constrainedType,
      String constrainedOperation,
      int constraintKind,
      String resultVariable
  ) {
    this.constraintName=constraintName;
    this.constrainedType=constrainedType;
    this.constrainedOperation=constrainedOperation;
    this.constraintKind=constraintKind;
    this.resultVariable=resultVariable;
  }

  public String getConstrainedType() {
    return constrainedType;
  }

  public String getConstrainedOperation() {
    return constrainedOperation;
  }

  public String getCode() {
    if (code==null) {
      throw new RuntimeException("code queried without setting it");
    }
    return code;
  }

  public void setCode(String code) {
    this.code=code;
  }

  public String getName() {
    return constraintName;
  }

  public String getType() {
    return constrainedType;
  }

  public String getOperation() {
    return constrainedOperation;
  }

  public int getKind() {
    return constraintKind;
  }

  public String getResultVariable() {
    return resultVariable;
  }

  public void setResultVariable(String var) {
    resultVariable=var;
  }

} /* end class ProceduralCodeFragment */

