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
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #set_wrappedbyocl(String, Integer, Integer[])
  */public void set(String name, Integer type, Integer[] qualifiers)
  {
    checkOclInvariants();
    set_wrappedbyocl(name, type, qualifiers);
    checkOclInvariants();
  }

  abstract void abstractMethod_wrappedbyocl();/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #abstractMethod_wrappedbyocl()
  */void abstractMethod()
  {
    checkOclInvariants();
    abstractMethod_wrappedbyocl();
    checkOclInvariants();
  }

  /**
     Some example doc-comment.
  */
  public void run_wrappedbyocl()
  {}/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #run_wrappedbyocl()
  */public void run()
  {
    checkOclInvariants();
    run_wrappedbyocl();
    checkOclInvariants();
  }

  /**
     An collection of Strings.
     @element-type java.lang.String
     @see java.lang.String
  */
  Vector strings=new Vector();

  public String getName_wrappedbyocl()
  {
    return name;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #getName_wrappedbyocl()
  */public String getName()
  {
    checkOclInvariants();
    String result=getName_wrappedbyocl();
    checkOclInvariants();
    return result;
  }

  public Integer getType_wrappedbyocl()
  {
    return type;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #getType_wrappedbyocl()
  */public Integer getType()
  {
    checkOclInvariants();
    Integer result=getType_wrappedbyocl();
    checkOclInvariants();
    return result;
  }

  public Integer[] getQualifiers_wrappedbyocl()
  {
    return qualifiers;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #getQualifiers_wrappedbyocl()
  */public Integer[] getQualifiers()
  {
    checkOclInvariants();
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode4=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclString tudOclNode5=tudresden.ocl.lib.Ocl.toOclString(tudOclNode4.getFeature("hallo"));
      final tudresden.ocl.lib.OclString tudOclNode6=new tudresden.ocl.lib.OclString("bello");
      final tudresden.ocl.lib.OclBoolean tudOclNode7=tudOclNode5.isEqualTo(tudOclNode6);
      if(!tudOclNode7.isTrue())
        System.out.println("ocl precondition tudOclPre0 violated");
    }
    tudresden.ocl.lib.OclString tudOclNode14;
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode12=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclAnyImpl tudOclResult1=tudresden.ocl.lib.OclAnyImpl.UNDEFINED;
      final tudresden.ocl.lib.OclString tudOclNode13=tudresden.ocl.lib.Ocl.toOclString(tudOclNode12.getFeature("hallo"));
      tudOclNode14=tudresden.ocl.lib.Ocl.toOclString(tudOclNode12.getFeature("hallo"));
      final tudresden.ocl.lib.OclBoolean tudOclNode15=tudOclNode13.isEqualTo(tudOclNode14);
    }
    Integer[] result=getQualifiers_wrappedbyocl();
    checkOclInvariants();
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode8=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclAnyImpl tudOclResult0=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(result) );
      final tudresden.ocl.lib.OclString tudOclNode9=tudresden.ocl.lib.Ocl.toOclString(tudOclNode8.getFeature("hallo"));
      final tudresden.ocl.lib.OclString tudOclNode10=new tudresden.ocl.lib.OclString("hallo");
      final tudresden.ocl.lib.OclBoolean tudOclNode11=tudOclNode9.isEqualTo(tudOclNode10);
      if(!tudOclNode11.isTrue())
        System.out.println("ocl postcondition tudOclPost0 violated");
    }
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode12=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclAnyImpl tudOclResult1=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(result) );
      final tudresden.ocl.lib.OclString tudOclNode13=tudresden.ocl.lib.Ocl.toOclString(tudOclNode12.getFeature("hallo"));
      final tudresden.ocl.lib.OclBoolean tudOclNode15=tudOclNode13.isEqualTo(tudOclNode14);
      if(!tudOclNode15.isTrue())
        System.out.println("ocl postcondition tudOclPost1 violated");
    }
    return result;
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
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #getUnqualifiedType_wrappedbyocl()
  */public Integer getUnqualifiedType() throws IllegalArgumentException
  {
    checkOclInvariants();
    Integer result=getUnqualifiedType_wrappedbyocl();
    checkOclInvariants();
    return result;
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
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #setParent_wrappedbyocl(Object)
  */public void setParent(Object parent) throws IllegalArgumentException, NullPointerException
  {
    checkOclInvariants();
    setParent_wrappedbyocl(parent);
    checkOclInvariants();
  }

  public Object getParent_wrappedbyocl()
  {
    return parent;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #getParent_wrappedbyocl()
  */public Object getParent()
  {
    checkOclInvariants();
    Object result=getParent_wrappedbyocl();
    checkOclInvariants();
    return result;
  }

  public void printData_wrappedbyocl
    (java.io.PrintStream o)
  {
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #printData_wrappedbyocl(java.io.PrintStream)
  */public void printData(java.io.PrintStream o)
  {
    checkOclInvariants();
    printData_wrappedbyocl(o);
    checkOclInvariants();
  }
  
  static public void main_wrappedbyocl(String[] args)
  {
    (new SecondExample()).getQualifiers();
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #main_wrappedbyocl(String[])
  */public static void main(String[] args)
  {
    main_wrappedbyocl(args);
  }

/**
    A method for checking ocl invariants.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
  */private final void checkOclInvariants()
  {
    for(Iterator i=strings.iterator(); i.hasNext(); )
      if(!(i.next() instanceof java.lang.String))
        System.out.println("element checker failed.");
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode0=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclString tudOclNode1=tudresden.ocl.lib.Ocl.toOclString(tudOclNode0.getFeature("hallo"));
      final tudresden.ocl.lib.OclString tudOclNode2=new tudresden.ocl.lib.OclString("hallo");
      final tudresden.ocl.lib.OclBoolean tudOclNode3=tudOclNode1.isEqualTo(tudOclNode2);
      if(!tudOclNode3.isTrue())
        System.out.println("ocl invariant tudOclInv0 violated");
    }
}}

class SecondExample extends Example{
  int i;
  
  SecondExample()
  {
    super("somename", new Integer(5));
  }

  void abstractMethod_wrappedbyocl()  {}/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
      @see #abstractMethod_wrappedbyocl()
  */void abstractMethod()
  {
    checkOclInvariants();
    abstractMethod_wrappedbyocl();
    checkOclInvariants();
  }
  
/**
    A method for checking ocl invariants.
    Generated automatically, DO NOT CHANGE!
      @author ocl injector
  */private final void checkOclInvariants()
  {
}}

