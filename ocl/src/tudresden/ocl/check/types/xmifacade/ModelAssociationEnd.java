/*
Copyright (C) 2000  Ralf Wiebicke

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

package tudresden.ocl.check.types.xmifacade;

import tudresden.ocl.check.types.Type;
import tudresden.ocl.check.types.Collection;

/**
   Encapsulates an association end. Needed only during parsing.
*/
public class ModelAssociationEnd
{
  String name;
  ModelClass modelclass;
  Type type;
  Type[] qualifiers;

  /**
    Is more than one member allowed.
  */
  boolean isMultiple;

  boolean othersAreMultiple;

  ModelAssociationEnd(String name,
                      ModelClass modelclass,
                      String multiplicity,
                      boolean isOrdered,
                      Type[] qualifiers)
  {
    if(name==null||name.length()==0)
      name=modelclass.getImplicitRoleName();
    this.name=name;

    this.modelclass=modelclass;

    if("1".equals(multiplicity) ||
       "1..1".equals(multiplicity) ||
       "0..1".equals(multiplicity))   isMultiple=false;
    else
    if("0..*".equals(multiplicity) ||
       "1..*".equals(multiplicity))   isMultiple=true;
    else
      throw new XmiException("Multiplicity not allowed: "+multiplicity);

    if(isMultiple)
    {
      int kind=isOrdered ? Collection.SEQUENCE : Collection.SET;
      type=new Collection(kind, modelclass);
    }
    else
      type=modelclass;

    if(qualifiers!=null&&qualifiers.length==0)
      throw new IllegalArgumentException();
    this.qualifiers=qualifiers;

    //System.out.println("  AssociationEnd "+type+" "+(name!=null?name:"")+" "+(isOrdered?"ORDERED ":"")+(isMultiple?"MULTIPLE ":"")+(isOptional?"OPTIONAL ":""));
  }
}

