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
    vacantInvariants.add(this);
    allInvariants.add(this);
  }
  
  // for debugging only
  public static final HashSet allInvariants=new HashSet();
  

  public static final String OBSERVER_SUFFIX="_oclobservinginvariants812374";
  public static final String BACKUP_SUFFIX="_oclbackup812374";
  
  private final void addObserver(String featurename, Object o)
  {
    try
    {
      //System.out.println("invariant "+name+" on object "+object+" observes feature "+featurename+" on object "+o);
      Class c=o.getClass();
      Field f=c.getField(featurename);
      HashSet observer=(HashSet)(f.get(o));
      observer.add(this);
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

  private static HashSet vacantInvariants=new HashSet();
  
  public static final String CHECKING_FLAG="tudresden.ocl.injection.lib.Invariant.checking_flag";
  public static boolean checking_flag=false;

  public static final String CHECKING_OPERATION="tudresden.ocl.injection.lib.Invariant.checkVacantInvariants";
  public static final void checkVacantInvariants()
  {
    if(vacantInvariants.isEmpty())
      return;
    
    HashSet todo=vacantInvariants;
    vacantInvariants=null;
    for(Iterator i=todo.iterator(); i.hasNext(); )
      ((Invariant)i.next()).invoke();
    vacantInvariants=new HashSet();
  }

  
  // notify invariants
  
  public static final String NOTIFY_OBSERVING_INVARIANTS="tudresden.ocl.injection.lib.Invariant.notifyObservingInvariants";

  /**
     Notifies the invariants observing an attribute, that 
     the attribute has changed.
     There is no notify method in class Invariant, 
     instead the observing invariants are included into a set
     of vacant invariants.

     After notifying, the observer set is cleared. 
     Next time, the invariant runs, the observers are registered again.
  
     @parameter observers 
        the attribute changed, 
        represented by its observers set.
  */
  public static final void notifyObservingInvariants(HashSet observers)
  {
    if(!observers.isEmpty())
    {
      vacantInvariants.addAll(observers);
      observers.clear();
    }
  }
      

  // hash codes for collections

  public static final String IDENTITY_HASH_CODE=
    "tudresden.ocl.injection.lib.Invariant.identityHashCode";
  
  /**
     Calculates the hash code as defined in 
     {@link List#hashCode()}, but calls 
     <code>System.identityHashCode(Object)</code>
     for each contained object.

     For null arguments it returns the same value as for
     empty lists.
  */
  public static final int identityHashCode(List list)
  {
    int hashCode=1;

    if(list==null)
      return hashCode;
    
    for(Iterator i=list.iterator(); i.hasNext(); ) 
    {
      Object obj=i.next();
      hashCode=31*hashCode+(obj==null ? 0 : System.identityHashCode(obj));
    }
    return hashCode;
  }

  /**
     Calculates the hash code equivalent to
     {@link #identityHashCode(List)}, 
     but for arrays instead of lists.

     For null arguments it returns the same value as for
     empty arrays.
  */
  public static final int identityHashCode(Object[] array)
  {
    int hashCode=1;

    if(array==null)
      return hashCode;
    
    for(int i=0; i<array.length; i++) 
    {
      Object obj=array[i];
      hashCode=31*hashCode+(obj==null ? 0 : System.identityHashCode(obj));
    }
    return hashCode;
  }

  /**
     Calculates the hash code as defined in 
     {@link Set#hashCode()}, but calls 
     <code>System.identityHashCode(Object)</code>
     for each contained object.
  
     For null arguments it returns the same value as for
     empty sets.
  */
  public static final int identityHashCode(Set set)
  {
    int hashCode=0;

    if(set==null)
      return hashCode;

    for(Iterator i=set.iterator(); i.hasNext(); ) 
    {
      Object obj=i.next();
      hashCode+=(obj==null ? 0 : System.identityHashCode(obj));
    }
    return hashCode;
  }

  /**
     Calculates the hash code as defined in 
     {@link Map#hashCode()}, but calls 
     <code>System.identityHashCode(Object)</code>
     for each contained object.
     @see Map.Entry#hashCode()
  
     For null arguments it returns the same value as for
     empty maps.
  */
  public static final int identityHashCode(Map map)
  {
    /*
      Implementation note:

      This method promises to be equal to Map.hashCode()
      except of the call to identityHashCode.
    
      For this implementation this is only fulfilled,
      if map.keySet().iterator() and map.values().iterator()
      enumerate the maps elements in the same order.
    
      As far as I understand the specification of java.util.Map,
      this is guaranteed. If not, a RuntimeException is thrown.
    
      If you are desperatly looking for runtime efficiency, you
      may disable the checks below.
    */
    int hashCode=0;

    if(map==null)
      return hashCode;

    Iterator ikey=map.keySet().iterator();
    Iterator ival=map.values().iterator();
    while(ikey.hasNext()) 
    {
      Object key=ikey.next();
      Object val=ival.next();
      hashCode+=
        (key==null ? 0 : System.identityHashCode(key)) ^
        (val==null ? 0 : System.identityHashCode(val));
      if(map.get(key)!=val) throw new RuntimeException();
    }
    if(ival.hasNext()) throw new RuntimeException();
    return hashCode;
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
