package tudresden.ocl.check.types;

import java.lang.reflect.*;
import tudresden.ocl.check.*;
import tudresden.ocl.lib.NameAdapter;

/** an implementation of the Any interface backed by a java.lang.Class object
 */
public class ClassAny implements Any {

    protected Class c;
    ReflectionFacade rf;

    /** use getClassAny(Class c) instead of this constructor
     */
    protected ClassAny(Class c, ReflectionFacade rf) {
      this.c=c;
      this.rf=rf;
    }

    public Type navigateQualified(String name, Type[] qualifiers) throws OclTypeException {
      if (qualifiers!=null && qualifiers.length!=0) {
        throw new OclTypeException("ReflectionFacade cannot handle qualifiers");
      }
      Type ret=Basic.navigateAnyQualified(name, this, qualifiers);
      if (ret!=null) return ret;
      String [] javaNames=rf.nameAdapter.getNames(name);
      Field f=null;
      for (int i=0; i<javaNames.length && f==null; i++) {
        Class nextClass=c;
        while (nextClass!=null && f==null) {
          try {
            f=nextClass.getDeclaredField(javaNames[i]);
          }
          catch (NoSuchFieldException nsf) {
            // try next class
          }
          nextClass=nextClass.getSuperclass();
        }
      }
      if (f==null) {
        throw new OclTypeException(c.getName()+" has no field "+name);
      }
      Class type=f.getType();
      return getTypeForClass(type);
    }

    /** this method has to solve the additional problem that the mapping from
     *  OCL types to Java types is not a homomorhpism: Java <code>int</code>s
     *  do not conform to Java <code>float</code>s while OCL Integer is a
     *  subtype of OCL Real; therefore all possible replacements of
     *  Integer with Real have to be tried
     */
    public Type navigateParameterized(String name, Type[] params) throws OclTypeException {
      Type ret=Basic.navigateAnyParameterized(name, params);
      if (ret!=null) return ret;
      Method m=null;
      int countInteger=countIntegerInParams(params);
      int countCombinations=(int) Math.pow(2.0d , (double)countInteger);
      for (int convCode=0; m==null && convCode<countCombinations; convCode++) {
        Class[] jParams=getClassArrayForTypeArray(params, convCode);
        Class nextClass=c;
        while (nextClass!=null && m==null) {
          try {
            m=nextClass.getDeclaredMethod(name, jParams);
          }
          catch (NoSuchMethodException nsm) {
            // try next class
          }
          nextClass=nextClass.getSuperclass();
        }
      }
      if (m==null) {
        StringBuffer sb=new StringBuffer();
        sb.append(c.toString()+" has no method "+name+" with parameters (");
        for (int i=0; i<params.length; i++) {
          if (i!=0) {
            sb.append(", ");
          }
          sb.append(params[i]+"/"+params[i]);
        }
        sb.append(")");
        throw new OclTypeException(sb.toString());
      }
      return getTypeForClass(m.getReturnType());
    }

    public boolean hasState(String stateName) {
      // let oclInState pass instead of making every state access illegal...
      return true;
    }

    public boolean conformsTo(Type t) {
      if (t instanceof ClassAny) {
        ClassAny ca=(ClassAny) t;
        Class otherClass=ca.c;
        return otherClass.isAssignableFrom(c);
      } else {
        return false;
      }
    }

    /** @param convCode conversion code; if convCode is seen as bit array then
     *         convCode[i]==1 means that the i-th occurence of Integer in the
     *         parameter type array <code>ocl</code> is replaced by Real
     */
    protected Class[] getClassArrayForTypeArray(Type[] ocl, int convCode) {
      Class[] java=new Class[ocl.length];
      int integerCount=0;
      for (int i=0; i<ocl.length; i++) {
        Type t=ocl[i];
        if (t==Basic.INTEGER) {
          if ( ((convCode >> integerCount) & 1) == 1) {
            // i-th bit is "1"
            t=Basic.REAL;
          }
          integerCount++;
        }
        Class c=getClassForType(t);
        java[i]=c;
      }
      return java;
    }

    protected int countIntegerInParams(Type[] params) {
      int ret=0;
      for (int i=0; i<params.length; i++) {
        if (params[i]==Basic.INTEGER) {
          ret++;
        }
      }
      return ret;
    }

    protected Class getClassForType(Type t) {
      Class ret=rf.reflAdapter.getClassForType(t);
      if (ret==null) {
        ClassAny ca=(ClassAny)t;
        ret=ca.c;
      }
      return ret;
    }

    public boolean equals(Object o) {
      if (o instanceof ClassAny) {
        ClassAny otherAny=(ClassAny) o;
        return otherAny.c==this.c;
      } else {
        return false;
      }
    }

    public int hashCode() {
      return c.hashCode();
    }

    public String toString() {
      String result=c.getName();
      return result.substring(result.lastIndexOf(".")+1);
    }

  protected Type getTypeForClass(Class c) {
    Type t=rf.reflAdapter.getTypeForClass(c);
    if (t==null)
      return rf.getClassAny(c);
    else
      return t;
  }
}

