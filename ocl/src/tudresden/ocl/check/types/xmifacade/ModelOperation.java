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

import java.util.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;

/**
   Note:
   Operations value identity is determined by name and parameter types only.
   Return type and isquery flag are not significant for value identity.
   Method equals and hashCode are implemented accordingly.
   @see #equals(Object)
   @see #hashCode()
*/
public final class ModelOperation implements Comparable
{
  private String name;
  private Type[] params;
  private Type type;
  private boolean isquery;

  public ModelOperation(String name, Type[] params, Type type, boolean isquery)
  {
    if(name==null) throw new IllegalArgumentException();
    this.name=name;
    if(params==null) throw new IllegalArgumentException();
    this.params=params;
    if(type==null) throw new IllegalArgumentException();
    this.type=type;
    this.isquery=isquery;
    if(isquery)
      System.out.println("xmifacade: Operation "+this+" is a query. This information is ignored, since now known tool supports it.");
  }

  public String getName()
  {
    return name;
  }

  public Type[] getParams()
  {
    return params;
  }

  public Type getType()
  {
    return type;
  }

  public boolean isQuery()
  {
    return isquery;
  }

  private ModelClass parent;

  public void setParent(ModelClass parent)
  {
    if(this.parent==null)
      this.parent=parent;
    else
      throw new IllegalArgumentException("An operations parent cannot be set twice.");
  }

  public ModelClass getParent()
  {
    return parent;
  }

  public boolean matchesTo(Type[] other)
  {
    if(params.length!=other.length)
      return false;

    for(int i=0; i<params.length; i++)
      if(!other[i].conformsTo(params[i]))
        return false;

    return true;
  }

  /**
     Returns true, if both operations have the same signature.
     For each class there shouldn't be two operations beeing equal.
     This method cannot be used for navigate, since it does not care
     about polymorphism of parameter types.
     @see ModelClass#navigateParameterized(String, Type[])
  */
  public boolean equals(Object o)
  {
    ModelOperation other=(ModelOperation)o;
    if(!name.equals(other.name) || params.length!=other.params.length)
      return false;
    for(int i=0; i<params.length; i++)
      if(!params[i].equals(other.params[i]))
        return false;
    return true;
  }

  /**
     Implements hashCode to be compatible to equals, according to the
     semantics of Object.hashCode().
     @see #equals(Object)
     @see Object#hashCode()
  */
  private int hash=0;
  public int hashCode()
  {
    if(hash!=0) return hash;
    hash=name.hashCode();
    for(int i=0; i<params.length; i++)
      hash|=params[i].hashCode();
    if(hash==0) hash=345296;  // should almost never happen
    return hash;
  }

  public final String toString()
  {
    return Basic.signatureString(name, params);
  }

  public void printData(java.io.PrintStream o)
  {
    o.println("  "+Basic.typeString(type)+" "+this+(isquery?" QUERY":""));
  }

  public int compareTo(Object o)
  {
    ModelOperation other=(ModelOperation)o;

    int result=name.compareTo(other.name);
    if(result!=0)
      return result;

    if(params.length<other.params.length)
      return -1;
    if(params.length>other.params.length)
      return 1;

    for(int i=0; i<params.length; i++)
    {
      result=params[i].toString().compareTo(other.params[i].toString());
      if(result!=0)
        return result;
    }
    return 0;
  }

}
