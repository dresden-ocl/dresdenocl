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

  public Example(String name, Integer type)
  {
    super();
    qualifiers=new Integer[6];
  }

  public void set_wrappedbyocl(String name, Integer type,// what a cool parameter
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

  abstract void abstractMethod_wrappedbyocl();

  /**
     Some example doc-comment.
  */
  public void run_wrappedbyocl()
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
     @element-type Implementation
  */
  Set interfaces=new HashSet();
  
  Interface anInterface=new Implementation();
  
  /**
     @element-type Format
  */
  Vector formats=new Vector();
  
  Format aFormat=new java.text.DecimalFormat();
  
  public boolean poly1_wrappedbyocl(Interface someInterface)
  {
    return true;
  }

  public String getName_wrappedbyocl()
  {
    return name;
  }

  public Integer getType_wrappedbyocl()
  {
    return type;
  }

  public Integer[] getQualifiers_wrappedbyocl()
  {
    return qualifiers;
  }

  public Integer unqualifiedType=null;

  public Integer getUnqualifiedType_wrappedbyocl() throws IllegalArgumentException
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

  public void setParent_wrappedbyocl  (Object parent)
    throws
      IllegalArgumentException,
      NullPointerException
  {
    if(this.parent==null)
      this.parent=parent;
    else
      throw new IllegalArgumentException("An attributes parent cannot be set twice.");
  }

  public Object getParent_wrappedbyocl()
  {
    return parent;
  }

  public void printData_wrappedbyocl
    (java.io.PrintStream o)
  {
  }
  
  static public void main(String[] args)
  {
    tudresden.ocl.lib.Ocl.STRICT_CHECKING=true;
    tudresden.ocl.lib.Ocl.TOLERATE_NONEXISTENT_FIELDS=false;
    (new SecondExample()).getQualifiers();
  }

/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #set_wrappedbyocl(String, Integer, Integer[])
  */public void set(String name, Integer type, Integer[] qualifiers)
  {
    if(currently_checking_ocl)
      set_wrappedbyocl(name, type, qualifiers);
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      set_wrappedbyocl(name, type, qualifiers);
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #abstractMethod_wrappedbyocl()
  */void abstractMethod()
  {
    if(currently_checking_ocl)
      abstractMethod_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      abstractMethod_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #run_wrappedbyocl()
  */public void run()
  {
    if(currently_checking_ocl)
      run_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      run_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #poly1_wrappedbyocl(Interface)
  */public boolean poly1(Interface someInterface)
  {
    boolean result;
    if(currently_checking_ocl)
      result=poly1_wrappedbyocl(someInterface);
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      result=poly1_wrappedbyocl(someInterface);
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
    return result;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #getName_wrappedbyocl()
  */public String getName()
  {
    String result;
    if(currently_checking_ocl)
      result=getName_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      result=getName_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
    return result;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #getType_wrappedbyocl()
  */public Integer getType()
  {
    Integer result;
    if(currently_checking_ocl)
      result=getType_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      result=getType_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
    return result;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #getQualifiers_wrappedbyocl()
  */public Integer[] getQualifiers()
  {
    Integer[] result;
    if(currently_checking_ocl)
      result=getQualifiers_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
        tudresden.ocl.lib.OclString tudOclNode39;
      {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode29=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclString tudOclNode30=tudresden.ocl.lib.Ocl.toOclString(tudOclNode29.getFeature("hallo"));
        final tudresden.ocl.lib.OclString tudOclNode31=new tudresden.ocl.lib.OclString("bello");
        final tudresden.ocl.lib.OclBoolean tudOclNode32=tudOclNode30.isEqualTo(tudOclNode31);
        if(!tudOclNode32.isTrue())
          System.out.println("ocl precondition tudOclPre0 violated");
      }
      {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode37=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclAnyImpl tudOclResult1=tudresden.ocl.lib.OclAnyImpl.UNDEFINED;
        final tudresden.ocl.lib.OclString tudOclNode38=tudresden.ocl.lib.Ocl.toOclString(tudOclNode37.getFeature("hallo"));
        tudOclNode39=tudresden.ocl.lib.Ocl.toOclString(tudOclNode37.getFeature("hallo"));
        final tudresden.ocl.lib.OclBoolean tudOclNode40=tudOclNode38.isEqualTo(tudOclNode39);
      }
      currently_checking_ocl=false;
      result=getQualifiers_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode33=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclAnyImpl tudOclResult0=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(result) );
        final tudresden.ocl.lib.OclString tudOclNode34=tudresden.ocl.lib.Ocl.toOclString(tudOclNode33.getFeature("hallo"));
        final tudresden.ocl.lib.OclString tudOclNode35=new tudresden.ocl.lib.OclString("hallo");
        final tudresden.ocl.lib.OclBoolean tudOclNode36=tudOclNode34.isEqualTo(tudOclNode35);
        if(!tudOclNode36.isTrue())
          System.out.println("ocl postcondition tudOclPost0 violated");
      }
      {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode37=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclAnyImpl tudOclResult1=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(result) );
        final tudresden.ocl.lib.OclString tudOclNode38=tudresden.ocl.lib.Ocl.toOclString(tudOclNode37.getFeature("hallo"));
        final tudresden.ocl.lib.OclBoolean tudOclNode40=tudOclNode38.isEqualTo(tudOclNode39);
        if(!tudOclNode40.isTrue())
          System.out.println("ocl postcondition tudOclPost1 violated");
      }
      currently_checking_ocl=false;
    }
    return result;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #getUnqualifiedType_wrappedbyocl()
  */public Integer getUnqualifiedType() throws IllegalArgumentException
  {
    Integer result;
    if(currently_checking_ocl)
      result=getUnqualifiedType_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      result=getUnqualifiedType_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
    return result;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #setParent_wrappedbyocl(Object)
  */public void setParent(Object parent) throws IllegalArgumentException, NullPointerException
  {
    if(currently_checking_ocl)
      setParent_wrappedbyocl(parent);
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      setParent_wrappedbyocl(parent);
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #getParent_wrappedbyocl()
  */public Object getParent()
  {
    Object result;
    if(currently_checking_ocl)
      result=getParent_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      result=getParent_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
    return result;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #printData_wrappedbyocl(java.io.PrintStream)
  */public void printData(java.io.PrintStream o)
  {
    if(currently_checking_ocl)
      printData_wrappedbyocl(o);
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      printData_wrappedbyocl(o);
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
  }/**
    A flag, that currently ocl constraints are checked on this object.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
  */private boolean currently_checking_ocl=false;/**
    A method for checking ocl invariants.
    Generated automatically on Tue Jun 27 12:50:23 GMT+02:00 2000, DO NOT CHANGE!
    @author ocl_injector
  */private final void checkOclInvariants()
  {
    for(Iterator i=strings.iterator(); i.hasNext(); )
      if(!(i.next() instanceof java.lang.String))
        System.out.println("element checker failed.");
    for(Iterator i=integers.iterator(); i.hasNext(); )
      if(!(i.next() instanceof Integer))
        System.out.println("element checker failed.");
    for(Iterator i=dates.iterator(); i.hasNext(); )
      if(!(i.next() instanceof Date))
        System.out.println("element checker failed.");
    for(Iterator i=interfaces.iterator(); i.hasNext(); )
      if(!(i.next() instanceof Implementation))
        System.out.println("element checker failed.");
    for(Iterator i=formats.iterator(); i.hasNext(); )
      if(!(i.next() instanceof Format))
        System.out.println("element checker failed.");
    {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode0=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclString tudOclNode1=tudresden.ocl.lib.Ocl.toOclString(tudOclNode0.getFeature("hallo"));
        final tudresden.ocl.lib.OclString tudOclNode2=new tudresden.ocl.lib.OclString("hallo");
        final tudresden.ocl.lib.OclBoolean tudOclNode3=tudOclNode1.isEqualTo(tudOclNode2);
        if(!tudOclNode3.isTrue())
          System.out.println("ocl invariant tudOclInv0 violated");
    }
    {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode4=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclSet tudOclNode5=tudresden.ocl.lib.Ocl.toOclSet(tudOclNode4.getFeature("strings"));
        final tudresden.ocl.lib.OclString tudOclNode6=new tudresden.ocl.lib.OclString("hallo");
        final tudresden.ocl.lib.OclBoolean tudOclNode7=tudOclNode5.includes(tudOclNode6);
        if(!tudOclNode7.isTrue())
          System.out.println("ocl invariant tudOclInv1 violated");
    }
    {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode8=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclSet tudOclNode9=tudresden.ocl.lib.Ocl.toOclSet(tudOclNode8.getFeature("integers"));
        final tudresden.ocl.lib.OclInteger tudOclNode10=tudresden.ocl.lib.Ocl.toOclInteger(tudOclNode8.getFeature("anInteger"));
        final tudresden.ocl.lib.OclBoolean tudOclNode11=tudOclNode9.includes(tudOclNode10);
        if(!tudOclNode11.isTrue())
          System.out.println("ocl invariant tudOclInv2 violated");
    }
    {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode12=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclSequence tudOclNode13=tudresden.ocl.lib.Ocl.toOclSequence(tudOclNode12.getFeature("dates"));
        final tudresden.ocl.lib.OclAnyImpl tudOclNode14=tudresden.ocl.lib.Ocl.toOclAnyImpl(tudOclNode12.getFeature("aDate"));
        final tudresden.ocl.lib.OclBoolean tudOclNode15=tudOclNode13.includes(tudOclNode14);
        if(!tudOclNode15.isTrue())
          System.out.println("ocl invariant tudOclInv3 violated");
    }
    {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode16=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclSet tudOclNode17=tudresden.ocl.lib.Ocl.toOclSet(tudOclNode16.getFeature("interfaces"));
        final tudresden.ocl.lib.OclAnyImpl tudOclNode18=tudresden.ocl.lib.Ocl.toOclAnyImpl(tudOclNode16.getFeature("anInterface"));
        final tudresden.ocl.lib.OclBoolean tudOclNode19=tudOclNode17.includes(tudOclNode18);
        if(!tudOclNode19.isTrue())
          System.out.println("ocl invariant tudOclInv4 violated");
    }
    {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode20=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclSequence tudOclNode21=tudresden.ocl.lib.Ocl.toOclSequence(tudOclNode20.getFeature("formats"));
        final tudresden.ocl.lib.OclAnyImpl tudOclNode22=tudresden.ocl.lib.Ocl.toOclAnyImpl(tudOclNode20.getFeature("aFormat"));
        final tudresden.ocl.lib.OclBoolean tudOclNode23=tudOclNode21.includes(tudOclNode22);
        if(!tudOclNode23.isTrue())
          System.out.println("ocl invariant tudOclInv5 violated");
    }
    {
        final tudresden.ocl.lib.OclAnyImpl tudOclNode24=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
        final tudresden.ocl.lib.OclAnyImpl tudOclNode25=tudresden.ocl.lib.Ocl.toOclAnyImpl(tudOclNode24.getFeature("anInterface"));
        Object[] tudOclParam0=new Object[1];
        tudOclParam0[0]=tudresden.ocl.lib.Ocl.reconvert(null, tudOclNode25);
        final tudresden.ocl.lib.OclBoolean tudOclNode26=tudresden.ocl.lib.Ocl.toOclBoolean(tudOclNode24.getFeature("poly1", tudOclParam0));
        final tudresden.ocl.lib.OclBoolean tudOclNode27=tudresden.ocl.lib.OclBoolean.TRUE;
        final tudresden.ocl.lib.OclBoolean tudOclNode28=tudOclNode26.isEqualTo(tudOclNode27);
        if(!tudOclNode28.isTrue())
          System.out.println("ocl invariant tudOclInv6 violated");
    }
}}

class SecondExample extends Example{
  int i;
  
  SecondExample()
  {
    super("somename", new Integer(5));
  }

  void abstractMethod_wrappedbyocl()  {}
  
/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #abstractMethod_wrappedbyocl()
  */void abstractMethod()
  {
    if(currently_checking_ocl)
      abstractMethod_wrappedbyocl();
    else
    {
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
      abstractMethod_wrappedbyocl();
      currently_checking_ocl=true;
      checkOclInvariants();
      currently_checking_ocl=false;
    }
  }/**
    A flag, that currently ocl constraints are checked on this object.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
  */private boolean currently_checking_ocl=false;/**
    A method for checking ocl invariants.
    Generated automatically on Tue Jun 27 12:50:23 GMT+02:00 2000, DO NOT CHANGE!
    @author ocl_injector
  */private final void checkOclInvariants()
  {
}}

