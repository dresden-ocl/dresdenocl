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

package tudresden.ocl.injection.lib;

import tudresden.ocl.lib.FeatureListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class Invariant implements FeatureListener
{
  private String name;
  private Object object;
  
  public Invariant(String name, Object object) 
  {
    this.name=name;
    this.object=object;
    vakantInvariants.add(this);
    allInvariants.add(this);
  }
  
  // for debugging only
  public static final HashSet allInvariants=new HashSet();
  

  public static final String OBSERVER_SUFFIX="_oclobservinginvariants812374";
  
  private HashSet observedfeatures=new HashSet();
  
  private final void addObserver(String featurename, Object o)
  {
    try
    {
      //System.out.println("invariant "+name+" on object "+object+" observes feature "+featurename+" on object "+o);
      Class c=o.getClass();
      Field f=c.getField(featurename);
      HashSet observer=(HashSet)(f.get(o));
      observer.add(this);
      observedfeatures.add(observer);
    }
    catch(NoSuchFieldException e)   { e.printStackTrace(); throw new RuntimeException(e.toString());}
    catch(IllegalAccessException e) { e.printStackTrace(); throw new RuntimeException(e.toString());}
  }
  
  public final void onField(Field f, Object o)
  {
    addObserver(f.getName()+OBSERVER_SUFFIX, o);
  }
  
  public final void onMethod(Method m, Object o)
  {
    //addObserver(m.getName()+getParameters(m)+OBSERVER_SUFFIX, o);
  }
  

  public static final String INVARIANT_METHOD="zzzCheckOclInvariantMethod812374_";
  public static final String INVARIANT_OBJECT="zzzCheckOclInvariantObject812374_";

  private Method method=null;
  
  public final void invoke()
  {
    try
    {
      //System.out.println("invoking "+name+" on "+object);
      if(method==null)
        method=object.getClass().getMethod(INVARIANT_METHOD+name, null);
      method.invoke(object, null);
    }
    catch(NoSuchMethodException e) 
    {
      e.printStackTrace();
      throw new RuntimeException(e.toString());
    }
    catch(InvocationTargetException e) 
    {
      e.printStackTrace();
      throw new RuntimeException(e.getTargetException().toString());
    }
    catch(IllegalAccessException e) 
    {
      e.printStackTrace();
      throw new RuntimeException(e.toString());
    }
  }    

  private static HashSet vakantInvariants=new HashSet();
  
  public static final String CHECKING_FLAG="tudresden.ocl.injection.lib.Invariant.checking_flag";
  public static boolean checking_flag=false;

  public static final String CHECKING_OPERATION="tudresden.ocl.injection.lib.Invariant.checkVakantInvariants";
  public static final void checkVakantInvariants()
  {
    if(vakantInvariants.isEmpty())
      return;
    
    HashSet todo=vakantInvariants;
    vakantInvariants=null;
    for(Iterator i=todo.iterator(); i.hasNext(); )
      ((Invariant)i.next()).invoke();
    vakantInvariants=new HashSet();
  }

  public static final String NOTIFY_OBSERVING_INVARIANTS="tudresden.ocl.injection.lib.Invariant.notifyObservingInvariants";
  public static final void notifyObservingInvariants(HashSet observers)
  {
    vakantInvariants.addAll(observers);
  }
      

  // checking element types
  
  public static final String CHECK_ELEMENT_TYPES="tudresden.ocl.injection.lib.Invariant.checkElementTypes";
  public static final String CHECK_KEY_TYPES=    "tudresden.ocl.injection.lib.Invariant.checkKeyTypes";
  
  public static final boolean checkElementTypes(Collection c, Class elementtype)
  {
    for(Iterator i=c.iterator(); i.hasNext(); )
      if(!elementtype.isAssignableFrom(i.next().getClass()))
        return false;
    return true;
  }

  public static final boolean checkElementTypes(Map m, Class elementtype)
  {
    for(Iterator i=m.values().iterator(); i.hasNext(); )
      if(!elementtype.isAssignableFrom(i.next().getClass()))
        return false;
    return true;
  }
  
  public static final boolean checkKeyTypes(Map m, Class keytype)
  {
    for(Iterator i=m.keySet().iterator(); i.hasNext(); )
      if(!keytype.isAssignableFrom(i.next().getClass()))
        return false;
    return true;
  }
  
}
