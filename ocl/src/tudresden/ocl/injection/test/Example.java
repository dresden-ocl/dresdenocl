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

package// hallo
  tudresden.ocl.injection.test;

import java.util.*;
import java.io.BufferedReader;
import java.text.Format;

/**
   Represents an attribute or association partner of a class.
   Note: type==Model.AMIGOUS means, the attribute cannot be used in OCL due to attribute ambiguities.
   See OCL spec 5.4.1. for details.
*/
public abstract class Example implements Runnable
{
  private String name;
  private Integer type=new Integer(5);
  private volatile Integer[] qualifiers;
  String hallo="hallo";
  
  int commaSeparated1,commaSeparated2=0,commaSeparated3; 
  int commaSeparated4=80,commaSeparated5,commaSeparated6=200; 

  // these attributes test the ability of the parser
  // to skip more complex (ugly) attribute initializers
  String   uglyAttribute1="some'Thing{some\"Thing;Else";
  char     uglyAttribute2=';';
  char     uglyAttribute3='{';
  char     uglyAttribute4='"';
  char     uglyAttribute5='\'';
  String[] uglyAttribute6=
  {
    "some'Thing{some\"Thing;Else", // ugly ; { " ' comment
    "some'Thing{some\"Thing;Else"
  };
  char[]   uglyAttribute7={';','{','"','\''};
  Runnable uglyAttribute8=new Runnable()
  {
    // ugly ; { " ' comment
    String   uglyInnerAttribute1="some'Thing{some\"Thing;Else";
    char     uglyInnerAttribute2=';';
    char     uglyInnerAttribute3='{';
    char     uglyInnerAttribute4='"';
    char     uglyInnerAttribute5='\'';
    String[] uglyInnerAttribute6=
    {
      "some'Thing{some\"Thing;Else", // ugly ; { " ' comment
      "some'Thing{some\"Thing;Else"
    };
    char[]   uglyInnerAttribute7={';','{','"','\''};
    public void run()
    {
      // ugly ; { " ' comment
      String   uglyVariable1="some'Thing{some\"Thing;Else";
      char     uglyVariable2=';';
      char     uglyVariable3='{';
      char     uglyVariable4='"';
      char     uglyVariable5='\'';
      String[] uglyVariable6=
      {
        "some'Thing{some\"Thing;Else", // ugly ; { " ' comment
        "some'Thing{some\"Thing;Else"
      };
      char[]   uglyAttribute7={';','{','"','\''};
    }
    // ugly ; { " ' comment
  };
  // end of ugly attributes
  

  class Inner implements Runnable
  {
    class Drinner implements Runnable
    {
      boolean someDrinnerBoolean=true;
    
      public void run()
      {
      }
    }

    boolean someInnerBoolean=true;
    
    public void run()
    {
    }
  }  


  private Example()
  {
    namedIntegers.put("5", new Integer(5));
  }
  
  public Example(String name, Integer type)
  {
    super();
    qualifiers=new Integer[6];
    namedIntegers.put("5", new Integer(5));
  }

  public void set(String name, Integer type,// what a cool parameter
    final Integer[] qualifiers)
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
  {}

  /**
     A collection of Strings.
     @element-type java.lang.String
     @see java.lang.String
  */
  Set strings=new HashSet();
  
  /**
     @element-type Integer
  */
  Set integers=new HashSet();
  
  Integer anInteger=new Integer(5);
  
  /**
     @element-type Date
  */
  List dates=new ArrayList();
  
  Date aDate=new Date();
  
  /**
     @element-type AbstractImplementation
  */
  Set interfaces=new HashSet();
  
  AbstractImplementation anInterface=new Implementation();
  
  /**
     @element-type Format
  */
  Vector formats=new Vector();
  
  Format aFormat=new java.text.DecimalFormat();

  /**
     @element-type Integer
     @key-type String
  */
  HashMap namedIntegers=new HashMap();
  
  public boolean poly1(Interface someInterface)
  {
    return true;
  }

  public String getName()
  {
    return name;
  }

  public Integer getType()
  {
    return type;
  }

  public Integer[] getQualifiers()
  {
    namedIntegers.put("10", new Integer(10));
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
  
  private   void accessifierPrivate() {}
  protected void accessifierProtected() {}
            void accessifierPackage() {}
  public    void accessifierPublic() {}
  
  static public void main(String[] args)
  {
    tudresden.ocl.lib.Ocl.TOLERATE_NONEXISTENT_FIELDS=false;
    tudresden.ocl.lib.Ocl.setNameAdapter(new tudresden.ocl.lib.SimpleNameAdapter());
    SecondExample e2=new SecondExample();
    e2.getQualifiers();
    e2.i=10;
    e2.anInteger=new Integer(8);
    e2.getQualifiers();
  }

}

class SecondExample extends Example{
  int i;
  
  SecondExample()
  {
    super("somename", new Integer(5));
  }

  void abstractMethod()  {}
  
  static
  {
    // this has to be tested too.
  }
  
}

class ThirdExample extends SecondExample
{
  // this class has no explicit constructor
  void abstractMethod()  {}
}
