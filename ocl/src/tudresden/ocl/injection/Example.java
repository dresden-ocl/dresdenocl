/*
Copyright (C) 2000  Ralf Wiebicke
*/

package// hallo
  tudresden.ocl.injection;

import java.util.*;
import java.io.BufferedReader;
import java.text.Format;

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
    @author ocl_injector
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
    @author ocl_injector
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
    @author ocl_injector
    @see #run_wrappedbyocl()
  */public void run()
  {
    checkOclInvariants();
    run_wrappedbyocl();
    checkOclInvariants();
  }

  /**
     A collection of Strings.
     @element-type java.lang.String
     @see java.lang.String
  */
  Vector strings=new Vector();
  
  /**
     @element-type Integer
  */
  Vector integers=new Vector();
  
  Integer anInteger=new Integer(5);
  
  /**
     @element-type Date
  */
  Vector dates=new Vector();
  
  Date aDate=new Date();
  
  /**
     @element-type SourceReflectionExtender
  */
  Vector extenders=new Vector();
  
  SourceReflectionExtender anExtender=new SourceReflectionExtender();
  
  /**
     @element-type Format
  */
  Vector formats=new Vector();
  
  Format aFormat=new java.text.DecimalFormat();

  public String getName_wrappedbyocl()
  {
    return name;
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
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
    @author ocl_injector
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
    @author ocl_injector
    @see #getQualifiers_wrappedbyocl()
  */public Integer[] getQualifiers()
  {
    checkOclInvariants();
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode24=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclString tudOclNode25=tudresden.ocl.lib.Ocl.toOclString(tudOclNode24.getFeature("hallo"));
      final tudresden.ocl.lib.OclString tudOclNode26=new tudresden.ocl.lib.OclString("bello");
      final tudresden.ocl.lib.OclBoolean tudOclNode27=tudOclNode25.isEqualTo(tudOclNode26);
      if(!tudOclNode27.isTrue())
        System.out.println("ocl precondition tudOclPre0 violated");
    }
    tudresden.ocl.lib.OclString tudOclNode34;
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode32=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclAnyImpl tudOclResult1=tudresden.ocl.lib.OclAnyImpl.UNDEFINED;
      final tudresden.ocl.lib.OclString tudOclNode33=tudresden.ocl.lib.Ocl.toOclString(tudOclNode32.getFeature("hallo"));
      tudOclNode34=tudresden.ocl.lib.Ocl.toOclString(tudOclNode32.getFeature("hallo"));
      final tudresden.ocl.lib.OclBoolean tudOclNode35=tudOclNode33.isEqualTo(tudOclNode34);
    }
    Integer[] result=getQualifiers_wrappedbyocl();
    checkOclInvariants();
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode28=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclAnyImpl tudOclResult0=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(result) );
      final tudresden.ocl.lib.OclString tudOclNode29=tudresden.ocl.lib.Ocl.toOclString(tudOclNode28.getFeature("hallo"));
      final tudresden.ocl.lib.OclString tudOclNode30=new tudresden.ocl.lib.OclString("hallo");
      final tudresden.ocl.lib.OclBoolean tudOclNode31=tudOclNode29.isEqualTo(tudOclNode30);
      if(!tudOclNode31.isTrue())
        System.out.println("ocl postcondition tudOclPost0 violated");
    }
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode32=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclAnyImpl tudOclResult1=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(result) );
      final tudresden.ocl.lib.OclString tudOclNode33=tudresden.ocl.lib.Ocl.toOclString(tudOclNode32.getFeature("hallo"));
      final tudresden.ocl.lib.OclBoolean tudOclNode35=tudOclNode33.isEqualTo(tudOclNode34);
      if(!tudOclNode35.isTrue())
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
    @author ocl_injector
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
    @author ocl_injector
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
    @author ocl_injector
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
    @author ocl_injector
    @see #printData_wrappedbyocl(java.io.PrintStream)
  */public void printData(java.io.PrintStream o)
  {
    checkOclInvariants();
    printData_wrappedbyocl(o);
    checkOclInvariants();
  }
  
  static public void main_wrappedbyocl(String[] args)
  {
    tudresden.ocl.lib.Ocl.STRICT_CHECKING=true;
    tudresden.ocl.lib.Ocl.TOLERATE_NONEXISTENT_FIELDS=false;
    (new SecondExample()).getQualifiers();
  }/**
    A wrapper for checking ocl constraints.
    Generated automatically, DO NOT CHANGE!
    @author ocl_injector
    @see #main_wrappedbyocl(String[])
  */public static void main(String[] args)
  {
    main_wrappedbyocl(args);
  }

/**
    A method for checking ocl invariants.
    Generated automatically on Mon Jun 19 17:55:01 GMT+02:00 2000, DO NOT CHANGE!
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
    for(Iterator i=extenders.iterator(); i.hasNext(); )
      if(!(i.next() instanceof SourceReflectionExtender))
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
      final tudresden.ocl.lib.OclSequence tudOclNode5=tudresden.ocl.lib.Ocl.toOclSequence(tudOclNode4.getFeature("strings"));
      final tudresden.ocl.lib.OclString tudOclNode6=new tudresden.ocl.lib.OclString("hallo");
      final tudresden.ocl.lib.OclBoolean tudOclNode7=tudOclNode5.includes(tudOclNode6);
      if(!tudOclNode7.isTrue())
        System.out.println("ocl invariant tudOclInv1 violated");
    }
    {
      final tudresden.ocl.lib.OclAnyImpl tudOclNode8=tudresden.ocl.lib.Ocl.toOclAnyImpl( tudresden.ocl.lib.Ocl.getFor(this) );
      final tudresden.ocl.lib.OclSequence tudOclNode9=tudresden.ocl.lib.Ocl.toOclSequence(tudOclNode8.getFeature("integers"));
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
      final tudresden.ocl.lib.OclSequence tudOclNode17=tudresden.ocl.lib.Ocl.toOclSequence(tudOclNode16.getFeature("extenders"));
      final tudresden.ocl.lib.OclAnyImpl tudOclNode18=tudresden.ocl.lib.Ocl.toOclAnyImpl(tudOclNode16.getFeature("anExtender"));
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
    @author ocl_injector
    @see #abstractMethod_wrappedbyocl()
  */void abstractMethod()
  {
    checkOclInvariants();
    abstractMethod_wrappedbyocl();
    checkOclInvariants();
  }
  
/**
    A method for checking ocl invariants.
    Generated automatically on Mon Jun 19 17:55:02 GMT+02:00 2000, DO NOT CHANGE!
    @author ocl_injector
  */private final void checkOclInvariants()
  {
}}

