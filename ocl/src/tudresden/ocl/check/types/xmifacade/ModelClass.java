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

package tudresden.ocl.check.types.xmifacade;

import java.util.*;
import tudresden.ocl.check.types.*;
import tudresden.ocl.check.OclTypeException;

public final class ModelClass implements Any, Comparable
{
  /**
     Fully qualified name.
     This includes package prefix, without any spaces.
     This name is unique over the whole model.
  */
  private String fullName;

  /**
     This name ist unique in the package only.
  */
  private String shortName;

  private HashSet directSupertypes=new HashSet();
  private HashSet allSupertypes;

  /**
     Maps attribute/relation partner names to the attributes with this name.
  */
  private final HashMap attributes=new HashMap();

  /**
     Maps operation names to Collections of operations with this name
  */
  private final HashMap operations=new HashMap();

  /**
     @parameter packagepath List of strings, describing the absolute package path, the class is in.
  */
  public ModelClass(List packagePath, String name)
  {
    shortName=name;

    StringBuffer buf=new StringBuffer();
    for(Iterator i=packagePath.iterator(); i.hasNext(); )
    {
      buf.append((String)i.next());
      buf.append("::");
    }
    buf.append(name);
    fullName=buf.toString();

    //System.out.println("Class "+fullName+" "+shortName);
  }

  public String getFullName()
  {
    return fullName;
  }

  public String getShortName()
  {
    return shortName;
  }

  private String implicitRoleName=null;
  /**
     OCL specification 5.4.1:
     if role name is missing, use class name starting with lowercase letter.
  */
  public String getImplicitRoleName()
  {
    if(implicitRoleName==null)
    {
      StringBuffer buf=new StringBuffer(shortName);
      buf.setCharAt(0, Character.toLowerCase(buf.charAt(0)));
      implicitRoleName=new String(buf);
    }
    //System.out.println("xmifacade: used implicit role name \""+implicitRoleName+"\" for class \""+this+"\", according OCL spec 5.4.1.");
    return implicitRoleName;
  }

  private Model model=null;

  public void setModel(Model model)
  {
    if(this.model==null)
      this.model=model;
    else
      throw new IllegalArgumentException();
  }

  public Model getModel()
  {
    return model;
  }

  public void addDirectSupertype(ModelClass s)
  {
    directSupertypes.add(s);
  }

  /**
     Return true, if and only if this is a (possibly indirect) Generalization of this.
  */
  public boolean isSupertype(ModelClass s)
  {
    return allSupertypes.contains(s);
  }

  /**
     Adds an attribute/role name to the class.
     An attribute cannot be added twice to the same class or different classes.
     Note: this method cannot be used for flattening, due to special handling of ambiguities here.
  */
  public void addAttribute(ModelAttribute attr)
  {
    String attrname=attr.getName();

    ModelAttribute ambig=(ModelAttribute)(attributes.put(attrname, attr));
    if(ambig==null)
    {
      attr.setParent(this);
      return;
    }

    if(ambig.getType()!=Model.AMBIGOUS)
    {
      System.out.println(
        "xmifacade: warning: ambigous attribute/role name \"" + attrname +
        "\" in class \"" + fullName + "\", disabled for use in OCL.");
      attr=new ModelAttribute(attrname, Model.AMBIGOUS);
      attributes.put(attrname, attr);
      attr.setParent(this);
    }
    else
      System.out.println(
        "xmifacade: warning: ambigous attribute/role name \"" + attrname +
        "\" in class \"" + fullName + "\", disabled for use in OCL. (once more)");
  }

  public boolean containsOperation(ModelOperation oper)
  {
    Object operset=operations.get(oper.getName());
    return (operset!=null) && ((Set)operset).contains(oper);
  }

  public ModelOperation getEqualOperation(ModelOperation oper)
  {
    Object operset=operations.get(oper.getName());
    if(operset==null)
      return null;

    for(Iterator i=((Set)operset).iterator(); i.hasNext(); )
    {
      ModelOperation result=(ModelOperation)(i.next());
      if(oper.equals(result))
        return result;
    }
    return null;
  }

  public ModelOperation getMatchingOperation(String name, Type[] params)
  {
    Set opset=(Set)(operations.get(name));
      if(opset==null)
        return null;

    ModelOperation matchop=null;
    for(Iterator i=opset.iterator(); i.hasNext(); )
    {
      ModelOperation op=(ModelOperation)(i.next());
      if(op.matchesTo(params))
      {
        if(matchop==null)
          matchop=op;
        else
        {
          System.out.println(
            "xmifacade: warning: operation request \"" + Model.signatureString(name, params) +
            "\" matches at least two operations in class \"" + fullName +
            "\" by polymorphism, thus they are not avialable to OCL.");
          return null;
        }
      }
    }
    return matchop;
  }

  /**
     Adds an operation to a class.
     An operation cannot be added twice to the same class or different classes.
     Thus, this method cannot be used for flattening.
     Use addOperationLesser instead.
     @see #addOperationLesser(ModelOperation)
  */
  public void addOperation(ModelOperation oper)
  {
    addOperationLesser(oper);
    oper.setParent(this);
  }

  /**
     This operation ist used internally only be method flatten().
     @see #flatten
  */
  private void addOperationLesser(ModelOperation oper)
  {
    String name=oper.getName();
    HashSet opset=(HashSet)(operations.get(name));
    if(opset==null)
    {
      opset=new HashSet();
      operations.put(name, opset);
    }
    if(!opset.add(oper))
      throw new ModelException("ambigous operation signature \""+oper+"\" in class \""+fullName+"\".");
  }

  /**
     Flattens inheritance.
     This method inserts all attributes/operations of supertypes into itself,
     which are not overridden in thes class.
     Additionally the supertypes relation is extended to be transitive.
  */
  public void flatten()
  {
    if(directSupertypes==null) return;
    //System.out.println("flattening class "+name);

    HashSet superset=new HashSet();
    for(Iterator i=directSupertypes.iterator(); i.hasNext(); )
    {
      ModelClass c=(ModelClass)(i.next());
      c.flatten();
      superset.addAll(c.allSupertypes);

      // pull down all inherited attributes/associations, which are not overridden in this class.
      for(Iterator ia=c.attributes.values().iterator(); ia.hasNext(); )
      {
        ModelAttribute attr=(ModelAttribute)(ia.next());
        String attrname=attr.getName();
        if(attributes.containsKey(attrname))
          System.out.println(
            "xmifacade: warning: class \"" + fullName +
            "\" overrides attribute \"" + attrname +
            "\" inherited from class \"" + attr.getParent().fullName + "\".");
        else
          attributes.put(attrname,attr);
      }

      // pull down all inherited operations, which are not overridden in this class.
      for(Iterator io=c.operations.values().iterator(); io.hasNext(); )
      {
        Set opset=(Set)(io.next());
        for(Iterator ioo=opset.iterator(); ioo.hasNext(); )
        {
          ModelOperation oper=(ModelOperation)(ioo.next());
          if(containsOperation(oper))
          {
            ModelOperation oper2=getEqualOperation(oper);
            if(!oper2.getType().equals(oper.getType()))
               System.out.println(
                 "xmifacade: warning: class \"" + fullName +
                 "\" overrides operation \"" + oper +
                 "\" inherited from class \"" + oper.getParent().fullName +
                 "\" with \"" + oper2 + "\" having a different return type.");
          }
          else
            addOperationLesser(oper);
        }
      }
    }
    superset.addAll(directSupertypes);
    directSupertypes=null; // not needed anymore, indicates, that flattening is done.
    allSupertypes=superset;
  }

  /**
     Useful for debugging.
  */
  public void printData(java.io.PrintStream o)
  {
    o.println("class "+fullName+" "+shortName);
    for(Iterator i=(new TreeSet(allSupertypes)).iterator(); i.hasNext(); )
    {
      ModelClass c=(ModelClass)(i.next());
      o.println("  implements "+c.getFullName());
    }
    //o.println("zippo");

    for(Iterator i=(new TreeSet(attributes.values())).iterator(); i.hasNext(); )
      ((ModelAttribute)(i.next())).printData(o);

    //o.println("zippa");
    TreeSet sortedOperations=new TreeSet();
    for(Iterator i=operations.values().iterator(); i.hasNext(); )
      for(Iterator ii=((HashSet)(i.next())).iterator(); ii.hasNext(); )
        sortedOperations.add((ModelOperation)(ii.next()));
    for(Iterator i=sortedOperations.iterator(); i.hasNext(); )
      ((ModelOperation)(i.next())).printData(o);
  }

  public Type navigateQualified(String name, Type[] qualifiers) throws OclTypeException
  {
    if(qualifiers!=null && qualifiers.length==0)
    {
      // if the exception causes problems, use
      //qualifiers=null;
      // instead.
      throw new IllegalArgumentException();
    }

    Type type;
    ModelAttribute attr=(ModelAttribute)(attributes.get(name));

    if(attr!=null)
    {
      type=attr.getType();
      if(type==Model.AMBIGOUS)
        throw new OclTypeException(
          "Expected attribute \""+Model.qualifierString(name,qualifiers)+
          "\" in classifier \""+fullName+
          "\" cannot be used in OCL due to ambiguity. See OCL spec 5.4.1.");

      Type[] attrqualifiers=attr.getQualifiers();

      if(attrqualifiers==null)
      {
        if(qualifiers==null)
          return type;
        else
          throw new OclTypeException(
            "Expected attribute \""+Model.qualifierString(name,qualifiers)+
            "\" in classifier \""+fullName+"\" is not qualified in model.");
      }
      else
      {
        if(qualifiers==null)
          return attr.getUnqualifiedType();
        else
        {
          if(attrqualifiers.length!=qualifiers.length)
            throw new OclTypeException(
              "Expected attribute \""+Model.qualifierString(name,qualifiers)+
              "\" in classifier \""+fullName+"\" , not found, number of qualifiers does not match.");
          for(int i=0; i<qualifiers.length; i++)
            if(!qualifiers[i].conformsTo(attrqualifiers[i]))
              throw new OclTypeException(
                "Expected attribute \""+Model.qualifierString(name,qualifiers)+
                "\" in classifier \""+fullName+
                "\" , not found, qualifier "+(i+1)+" does not match.");
          return type;
        }
      }
    }

    type=Basic.navigateAnyQualified(name, this, qualifiers);
    if(type!=null)
      return type;

    throw new OclTypeException(
      "Expected attribute \""+Model.qualifierString(name,qualifiers)+
      "\" in classifier \""+fullName+"\" not found.");
  }

  /**
     Assumes, that all methods are queried using this method, even method with empty
     parameter list.
  */
  public Type navigateParameterized(String name, Type[] params) throws OclTypeException
  {

    ModelOperation oper=getMatchingOperation(name, params);
    if(oper!=null)
      return oper.getType();

    Type type=Basic.navigateAnyParameterized(name, params);
    if(type!=null)
      return type;

    throw new OclTypeException(
      "Expected operation \""+Model.signatureString(name,params)+
      "\" in classifier \""+fullName+"\" not found.");
  }

  /**
     States are not supported.
     This method always returns true.
  */
  public boolean hasState(String name)
  {
    return true;
  }

  public boolean conformsTo(Type t)
  {
    return
      (t==this) ||
      ((t instanceof ModelClass) ? isSupertype((ModelClass)t) : false);
  }

  /**
     Does object identity comparision.
     There should be only one ModelClass instance for each Class in the xmi file.
     Object.hashCode() isn't overridden for the same reason.
  */
  public boolean equals(Object o)
  {
    return this==o;
  }

  public String toString()
  {
    return shortName;
  }

  public int compareTo(Object o)
  {
    ModelClass other=(ModelClass)o;
    return
      fullName.compareTo(other.fullName);
  }

  // ---------------------------------------------------------------------
  /**
   * This methode will be called instead of flatten if the underlying model is rough.
   * It determines all generalization relationships but does no flattening.
   * @author Sten Loecher
   */
  public void determineAllSupertypes() {
 	if(directSupertypes==null) return;

    	HashSet superset=new HashSet();
    	for(Iterator i=directSupertypes.iterator(); i.hasNext(); ) {
      		ModelClass c=(ModelClass)(i.next());
      		c.determineAllSupertypes();
      		superset.addAll(c.allSupertypes);
      	}

	superset.addAll(directSupertypes);
    	allSupertypes=superset;
  }

  /**
   * @return true if the model class has supertypes, false otherwise
   * @author Sten Loecher
   */
  public boolean hasSupertypes() {
  	if (directSupertypes.isEmpty()) {
  		return false;
	} else {
		return true;
	}
  }

  /**
   * @return the name of the topmost parent class of the generalization hierarchy of this model class
   * @exception IllegalStateException if more than one generalization root exists
   * @author Sten Loecher
   */
  public String generalizationRoot()
  throws IllegalStateException {
        String result = null;

        if (allSupertypes != null) {
  		Iterator i = allSupertypes.iterator();
  		ModelClass c;

    		while (i.hasNext()) {
  			c = (ModelClass)i.next();
  			if (!c.hasSupertypes()) {
                          if (result == null) {
                            result = c.getShortName();
                          } else {
                            throw new IllegalStateException("Number of generalization roots is greater than one !");
                          }
                        }
  		}
	}

        if (result == null) result = shortName;
	return result;
  }

  /**
   * @return an unmodifiable Map that contains all attributes (and association partners if model is not in rough mode)
   * @author Sten Loecher
   */
  public Map attributes() {
  	return Collections.unmodifiableMap(attributes);
  }

  /**
   * @return an unmodifiable Map that contains all operations of the class
   * @author Sten Loecher
   */
  public Map operations() {
  	return Collections.unmodifiableMap(operations);
  }

  /**
   * @return true if class s is a direct supertype of this class, false otherwise
   * @author Sten Loecher
   */
  public boolean isDirectSupertype(ModelClass s) {
	return directSupertypes.contains(s);
  }
}
