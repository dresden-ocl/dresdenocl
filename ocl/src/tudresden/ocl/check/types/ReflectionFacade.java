/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 1999, 2000 Frank Finger (frank@finger.org).         *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.check.types;

import tudresden.ocl.check.*;
import java.util.HashMap;
import java.lang.reflect.*;
import tudresden.ocl.lib.*;

/** <code>ReflectionFacade</code> implements <code>ModelFacade</code>
 *  by extracting the required
 *  information from compiled Java classes via Java Reflection.
 *  The package name where classes are to be found is given as constructor
 *  parameter, along with a <code>ReflectionAdapter</code>
 *  object that is used
 *  to map Java classes to OCL types and vice versa.
 *  Classes are then loaded using the
 *  standard Java class loader.
 *
 *  <p>While this approach is very flexible, it has some serious drawbacks. Association
 *  ends with a multiplicity greater than one are usually represented in Java
 *  classes as some Java collection type, e.g.
 *  <code>java.util.Vector</code>.
 *  Through static examination of the class it is not possible to find out the
 *  element type of the OCL collection type that is the result of navigation
 *  along this association in an OCL constraint. Hence the iterator type can not be
 *  determined whenever the expression contains a call to one of the iterating
 *  methods, and type checking will fail unless the iterator type is specifies
 *  explicitly.
 *
 *  <p>For the same reason it is not possible to type-check the properties
 *  <code>first</code> and <code>last</code> defined for the OCL type
 *  Sequence.
 *
 *  <p>Another problem with getting model information through reflection is that
 *  the security manager might deny access to non-public fields.
 *
 *  <p>In addition, the mapping of OCL types to Java types is usually not a
 *  homomorphism. For further explanation, see the description of the method
 *  <code>navigate(String name, Type[] params)</code>.
 *
 *  <p>As the result of these restrictions, the class <code>ReflectionFacade</code> can
 *  only be used if all iterators and their types are declared explicitly.
 *  Additional problems might arise from security restrictions.
 *
 *  @see ClassAny#navigateParameterized(String name, Type[] params)
 *  @see ReflectionAdapter
 *
 *  @author Frank Finger
 *
 */
public class ReflectionFacade implements ModelFacade {

  String[] packageNames;
  NameAdapter nameAdapter;

  ReflectionAdapter reflAdapter;

  /** this map is not static since different ReflectionFacades should
   *  use their own type representations as these are inner class objects,
   *  therefore dependent on their outer objects, which are the
   *  ReflectionFacades
   */
  protected HashMap classAnyInstances=new HashMap();

  /** @param packageNames the names of the Java packages that contain the
   *              classes that will be queried for model information, without
   *              trailing dot character (&quot;.&quot;);
   *              <code>null</code> or the empty string denote the root
   *              package (containing all classes not assign to a package
   *              through Java's package statement); when a class is searched
   *              for the package names are tried in their order in
   *              the array
   *  @param reflAdapter maps OCL types to Java types and vice versa
   *  @param nameAdapter maps OCL names to Java names and vice versa; see explanation
   *              in NameAdapter documentation
   */
  public ReflectionFacade(String[] packageNames, ReflectionAdapter reflAdapter, NameAdapter nameAdapter) {
    for (int i=0; i<packageNames.length; i++) {
      if (packageNames[i].equals("")) packageNames[i]=null;
    }
    this.packageNames=packageNames;
    this.reflAdapter=reflAdapter;
    this.nameAdapter=nameAdapter;
  }

  public Any getClassifier(String name) {
    ClassAny ret=null;
    for (int i=0; i<packageNames.length && ret==null; i++) {
      try {
        String className;
        if (packageNames[i]==null) {
          className=name;
        } else {
          className=packageNames[i]+"."+name;
        }
        Class c=Class.forName(className);
        ret=getClassAny(c);
      }
      catch (ClassNotFoundException cnf) {
        // try next package name
      }
    }
    if (ret==null) {
      StringBuffer sb=new StringBuffer();
      sb.append("ReflectionFacade could not find class "+name+" in packages ");
      for (int i=0; i<packageNames.length; i++) {
        if (i!=0) sb.append(", ");
        sb.append(packageNames[i]);
      }
      throw new OclTypeException(
        sb.toString()
      );
    }
    return ret;
  }

  protected ClassAny getClassAny(Class c) {
    ClassAny ret=(ClassAny)classAnyInstances.get(c);
    if (ret==null) {
      ret=new ClassAny(c, this);
      classAnyInstances.put(c, ret);
    }
    return ret;
  }



}
