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

  public Type navigateQualified(String name, Type[] qualifiers) 
    throws OclTypeException 
  {
    Type theQualifier=null;
    
    if(qualifiers!=null)
    {
      if(qualifiers.length==1) 
        theQualifier=qualifiers[0];
      else
        throw new OclTypeException("ReflectionFacade can handle one qualifier only");
    }
    //System.out.println("ClassAny.navigateQualified:"+this+" "+name);
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
    Type modeltype=getTypeForClass(type);
    
    if(modeltype instanceof Collection)
    {
      Class elementtype=rf.getElementType(f);

      if(rf.reflAdapter.isMap(type))
      {
        if(theQualifier==null)
        {
          if(elementtype!=null)
            return new Collection(
              ((Collection)modeltype).getCollectionKind(),
              getTypeForClass(elementtype));
          else
            return modeltype;
        }
        else
        {
          Class keytype_class=rf.getKeyType(f);
          Type keytype=null;
          if(keytype_class!=null)
            keytype=getTypeForClass(keytype_class);
          
          if(keytype!=null)
          {
            if(!theQualifier.equals(keytype))
              throw new OclTypeException("feature "+name+" in classifier "+c+": expected qualifier type "+keytype+" found "+theQualifier+".");
            if(elementtype!=null)
              return getTypeForClass(elementtype);
            else
              throw new OclTypeException("feature "+name+"["+keytype+"] in classifier "+c+" has no @element-type tag.");
          }
          else
            throw new OclTypeException("feature "+name+" in classifier "+c+": qualified with type "+theQualifier+", but feature has no @keytype tag.");
        }
      }
      else
      {
        if(theQualifier!=null)
          throw new OclTypeException("feature "+name+" in classifier "+c+" cannot be qualified.");

        if(elementtype!=null)
          return new Collection(
            ((Collection)modeltype).getCollectionKind(),
            getTypeForClass(elementtype));
        else
          return modeltype;
      }
    }
    else
    {
      if(theQualifier!=null)
        throw new OclTypeException("feature "+name+" in classifier "+c+" cannot be qualified.");
      return modeltype;
    }
  }

  public Type navigateParameterized(String name, Type[] params) throws OclTypeException 
  {
    Type ret=Basic.navigateAnyParameterized(name, params);
    if (ret!=null) 
      return ret;
    
    Method foundmethod=null;

    // this is very similar to tudresden.ocl.lib.OclAnyImpl.findMethod
    // if you find a bug here, its probably there as well.
    
    // suprisingly one has not to go after interfaces since methods
    // inherited from interfaces are automatically included into the 
    // implementing class. This does not happen for methods inherited
    // from the superclass, so we have to ascend to all superclasses.
    classloop: for(Class iclass=c; iclass!=null; iclass=iclass.getSuperclass())
    {
      Method[] methods=iclass.getDeclaredMethods();
      methodloop: for(int i=0; i<methods.length; i++)
      {
        if(!name.equals(methods[i].getName()))
          continue methodloop;
        Class[] methodparams=methods[i].getParameterTypes();
        if(params.length!=methodparams.length)
          continue methodloop;
        for(int j=0; j<params.length; j++)
          if(!params[j].conformsTo(getTypeForClass(methodparams[j])))
            continue methodloop;
        if(foundmethod==null)
          foundmethod=methods[i];
        else
          throw new OclTypeException("ambigious method "+name+" of "+c+") queried.");
        break classloop;
      }
    }

    if(foundmethod==null) 
    {
      StringBuffer sb=new StringBuffer();
      sb.append(c.toString()+" has no method "+name+" with parameters (");
      for (int i=0; i<params.length; i++) 
      {
        if (i!=0) 
          sb.append(", ");
        sb.append(params[i]+"/"+params[i]);
      }
      sb.append(")");
      throw new OclTypeException(sb.toString());
    }

    return getTypeForClass(foundmethod.getReturnType());
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

