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

package tudresden.ocl.injection.ocl.lib;

import tudresden.ocl.lib.FeatureListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public final class Invariant implements FeatureListener, Serializable
{
  private String name;
  private Object object;
  
  public Invariant(String name, Object object)
  {
    this.name=name;
    this.object=object;
    vacantInvariants.add(this);
    
    // for debugging only
    if(allInvariants!=null)
      allInvariants.add(this);
  }
  
  // for debugging only
  public static HashSet allInvariants=null;
  
  
  public static final String OBSERVER_SUFFIX="_oclobservinginvariants812374";
  
  private final void addObserver(String featurename, Object o)
  {
    try
    {
      //System.out.println("invariant "+name+" on object "+object+" observes feature "+featurename+" on object "+o);
      Field f=null;
      for(Class c=o.getClass(); c!=null; c=c.getSuperclass())
      {
        try
        {
          f=c.getDeclaredField(featurename);
          break;
        }
        catch(NoSuchFieldException e)
        {}
      }
      if(f==null)
        throw new RuntimeException("error accessing feature "+featurename+" on object "+o);
      f.setAccessible(true);
      HashSet observer=(HashSet)(f.get(o));
      observer.add(this);
    }
    catch(IllegalAccessException e)
    {
      e.printStackTrace();
      throw new RuntimeException(e.toString());
    }
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
  
  private transient Method method=null;
  
  public final void invoke()
  {
    try
    {
      //System.out.println("invoking "+name+" on "+object);
      if(method==null)
      {
        method=object.getClass().getMethod(INVARIANT_METHOD+name, null);
        method.setAccessible(true);
      }
      method.invoke(object, null);
    }
    catch(NoSuchMethodException e)
    {
      System.out.println("NoSuchMethodException invoking >"+name+"< on >"+object+"<");
      e.printStackTrace();
      throw new RuntimeException(e.toString());
    }
    catch(InvocationTargetException e)
    {
      System.out.println("InvocationTargetException invoking >"+name+"< on >"+object+"<");
      e.printStackTrace();
      throw new RuntimeException(e.getTargetException().toString());
    }
    catch(IllegalAccessException e)
    {
      System.out.println("IllegalAccessException invoking >"+method+"< on >"+object+"<");
      e.printStackTrace();
      throw new RuntimeException(e.toString());
    }
  }
  
  private static HashSet vacantInvariants=new HashSet();
  
  public static final String CHECKING_FLAG="tudresden.ocl.injection.ocl.lib.Invariant.checking_flag";
  public static boolean checking_flag=false;
  
  public static final String CHECKING_OPERATION="tudresden.ocl.injection.ocl.lib.Invariant.checkVacantInvariants";
  public static final void checkVacantInvariants()
  {
    if(vacantInvariants.isEmpty())
      return;
    
    try
    {
      final HashSet todo=vacantInvariants;
      vacantInvariants=null;
      for(Iterator i=todo.iterator(); i.hasNext(); )
        ((Invariant)i.next()).invoke();
    }
    finally
    {
      vacantInvariants=new HashSet();
    }
  }
  
  
  // notify invariants
  
  public static final String NOTIFY_OBSERVING_INVARIANTS="tudresden.ocl.injection.ocl.lib.Invariant.notifyObservingInvariants";
  
  /**
   * Notifies the invariants observing an attribute, that
   * the attribute has changed.
   * There is no notify method in class Invariant,
   * instead the observing invariants are included into a set
   * of vacant invariants.
   *
   * After notifying, the observer set is cleared.
   * Next time, the invariant runs, the observers are registered again.
   *
   * @parameter observers
   * the attribute changed,
   * represented by its observers set.
   */
  public static final void notifyObservingInvariants(HashSet observers)
  {
    if(!observers.isEmpty())
    {
      vacantInvariants.addAll(observers);
      observers.clear();
    }
  }
  
  
}
