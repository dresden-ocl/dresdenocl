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

import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;

/**
   Represents an attribute or association partner of a class.
   Note: type==Model.AMIGOUS means, the attribute cannot be used in OCL due to attribute ambiguities.
   See OCL spec 5.4.1. for details.
   @see Model#AMBIGOUS
*/
public final class ModelAttribute implements Comparable
{
  private String name;
  private Type type;
  private Type[] qualifiers;

  public ModelAttribute(String name, Type type)
  {
    this(name, type, null);
  }

  public ModelAttribute(String name, Type type, Type[] qualifiers)
  {
    if(name==null)
      throw new IllegalArgumentException();
    this.name=name;

    if(type==null)
      throw new IllegalArgumentException();
    this.type=type;

    if(qualifiers!=null&&qualifiers.length==0)
      throw new IllegalArgumentException();
    this.qualifiers=qualifiers;
  }

  public String getName()
  {
    return name;
  }

  public Type getType()
  {
    return type;
  }

  public Type[] getQualifiers()
  {
    return qualifiers;
  }

  public Type unqualifiedType=null;
  
  public Type getUnqualifiedType()
  {
    if(unqualifiedType!=null)
      return unqualifiedType;
    
    if(qualifiers==null)
      throw new IllegalArgumentException();
    
    unqualifiedType=
      (type instanceof Collection) ? type : new Collection(Collection.SET, type);
    return unqualifiedType;
  }

  private ModelClass parent;

  public void setParent(ModelClass parent)
  {
    if(this.parent==null)
      this.parent=parent;
    else
      throw new IllegalArgumentException("An attributes parent cannot be set twice.");
  }

  public ModelClass getParent()
  {
    return parent;
  }

  public void printData(java.io.PrintStream o)
  {
    o.println("  " + Model.typeString(type) + " " + Model.qualifierString(name, qualifiers));
  }

  public int compareTo(Object o)
  {
    ModelAttribute other=(ModelAttribute)o;
    return
      name.compareTo(other.name);
  }

}
