/*
Copyright (C) 2000  Ralf Wiebicke
*/

package// hallo
  tudresden.ocl.injection;

import java.util.*;
import java.io.BufferedReader;

/**
   Represents an attribute or association partner of a class.
   Note: type==Model.AMIGOUS means, the attribute cannot be used in OCL due to attribute ambiguities.
   See OCL spec 5.4.1. for details.
   @see Model#AMBIGOUS
*/
public abstract class Example implements Runnable
{
  private String name;
  private Integer type=new Integer(5);
  private volatile Integer[] qualifiers;
  String hallo="hallo";

  public Example(String name, Integer type)
  {
    super();
  }

  public void set(String name, Integer type,// what a cool parameter
    Integer[] qualifiers)
  {
    if(name==null)
      throw new IllegalArgumentException(); // ugly comment : { {
    this.name=name;
    String x="ugly { string \" { literal";
    char c='{';

    if(type==null)
      throw new IllegalArgumentException(); // some other comment
    this.type=type;

    if(qualifiers!=null&&qualifiers.length==0)
      throw new IllegalArgumentException();
    this.qualifiers=qualifiers;
  }

  abstract void abstractMethod();

  /**
     Some example doc-comment.
  */
  public void run()
  {}/**
    Blah blah.
    Zippel zappel.
    @see #getName()
    @author ocl injector
  */void ocl_function()
  {
    // some ocl code.
  }

  /**
     An collection of Strings.
     @element-type java.lang.String
     @see java.lang.String
  */
  Vector strings=new Vector();

  public String getName()
  {
    return name;
  }

  public Integer getType_wrappedbyocl()
  {
    return type;
  }

  public Integer[] getQualifiers()
  {
    return qualifiers;
  }

  public Integer unqualifiedType=null;

  public Integer getUnqualifiedType() throws IllegalArgumentException
  {
    if(unqualifiedType!=null)
      return unqualifiedType;

    if(qualifiers==null)
      throw new IllegalArgumentException();

    unqualifiedType=
      (type instanceof Integer) ? type : type;
    return unqualifiedType;
  }

  private Object parent;

  public void setParent  (Object parent)
    throws
      IllegalArgumentException,
      NullPointerException
  {
    if(this.parent==null)
      this.parent=parent;
    else
      throw new IllegalArgumentException("An attributes parent cannot be set twice.");
  }

  public Object getParent()
  {
    return parent;
  }

  public void printData
    (java.io.PrintStream o)
  {
  }

}

class SecondExample{
  int i;
}

