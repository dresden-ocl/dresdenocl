/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Interpreter                                                   *
 * Copyright (C) 2002 Nikolai Krambrock (nikk@gmx.de)                *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Construction, University Of Technology Aachen, Germany            *
 * (http://www-lufgi3.informatik.rwth-aachen.de).                    *
 * It was done in co-operation with Software & Design and Managment  *
 * Troisdorf, Germany (http://www.sdm.de)                            *
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
 * this project, please visit the project home page:                 *
 * http://dresden-ocl.sourceforge.net                                * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl.interp.types.reflect;

import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.Any;
import tudresden.ocl.check.types.ClassAny;
import tudresden.ocl.check.types.DefaultReflectionAdapter;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.check.types.ReflectionAdapter;
import tudresden.ocl.check.types.ReflectionExtender;
import tudresden.ocl.check.types.ReflectionFacade;

import tudresden.ocl.injection.ocl.SourceReflectionExtender;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.types.reflect.intern.MetaClassAny;

import tudresden.ocl.lib.NameAdapter;
import tudresden.ocl.lib.SimpleNameAdapter;


/**
 * This class is an extended ReflectionFacade. It works on MetaAny rather than
 * on normal types and is therefor able to answer reflective questions that
 * are done with OclType in the interpreter.
 * 
 * @see tudresden.ocl.interp.types.MetaAny
 * @see ModelFacade
 * @see ReflectionFacade
 */
public class MetaReflectionFacade extends ReflectionFacade
  implements ModelFacade {
  private ReflectionAdapter reflectionAdapter;
  private String[] packageNames;

  /**
   * Constructor for this ModelFacade that bases the capturing of information
   * on  reflection. This constructor should do for most cases.<br> 
   * The way of capturing the types of collections makes it nessary that you put 
   * tags into a comment before the declaration in the source-code you want to  
   * do OCL checks with:<br>
   * @element-type myType<br><br> 
   * 
   * Arrays do not need to have this tag.<br>
   * This constructor does not have default packages to look for classes. You
   * have to give the fully quallified name e.g. 
   * "tudresden::ocl::sample::Company" as a reference to the class
   */
  public MetaReflectionFacade() {
    this(new String[0]);
  }

  /**
   * Constructor for this ModelFacade that bases the capturing of information
   * on  reflection. This constructor should do for most cases.<br> 
   * The way of capturing the types of collections makes it nessary that you put 
   * tags into a comment before the declaration in the source-code you want to  
   * do OCL checks with:<br>
   * @element-type myType<br><br> 
   * 
   * Arrays do not need to have this tag
   * 
   * @param packageNames are the packages the Facade is looking into for
   *        classes
   */
  public MetaReflectionFacade(String[] packageNames) {
    this(packageNames, new DefaultReflectionAdapter(), new SimpleNameAdapter(), 
         new SourceReflectionExtender());
  }

  /**
   * Constructor for this ModelFacade that bases the capturing of information
   * on  reflection. If you need to use this constructor, get familiar with
   * the  ReflectionFacade before doing so.
   * 
   * @param packageNames are the packages the Facade is looking into for
   *        classes
   * @param reflAdapter does the convertion from Java classes in Types
   * @param nameAdapter maps names for attributes from the model to names in
   *        the checking
   * @param extender gives the types of collections (e.g. by looking into the
   *        source code)
   * @throws NullPointerException if reflectionAdapter == null or nameAdaperter
   *         == null
   * @see ReflectionFacade
   */
  public MetaReflectionFacade(String[] packageNames, 
                              ReflectionAdapter reflAdapter, 
                              NameAdapter nameAdapter, 
                              ReflectionExtender extender)
                       throws NullPointerException {
    super(packageNames, reflAdapter, nameAdapter, extender);
    Assert.notNull(packageNames, "MetaReflectionFacade", "packageNames");
    Assert.notNull(reflAdapter, "MetaReflectionFacade", "reflAdapter");
    this.reflectionAdapter = reflAdapter;
    this.packageNames = packageNames;
  }

  /**
   * Overwrites the source wrapper for class information with MetaAny that does
   * know more about the class than Any.
   * 
   * @see tudresden.ocl.interp.types.MetaAny
   * @see Any
   */
  protected ClassAny getClassAny(Class c) {
    MetaClassAny ret = (MetaClassAny)classAnyInstances.get(c);
    if (ret == null) {
      ret = new MetaClassAny(c, this);
      classAnyInstances.put(c, ret);
    }
    return ret;
  }

  /**
   * The method changes the behavior from the superclass, so that it is allowed
   * to put a base type (Integer, String ...) as the parameter.
   * 
   * @param name the name of an type (it can be the name of a predefined type,
   *        like &quot;Integer&quot;)
   * @return the type of this classifier
   * @throws OclTypeException if no type with the given name exists
   * @see ModelFacade
   */
  public Any getClassifier(String name) {
    Assert.notNull(name, "getClassifier", "name");
    Assert.assertTrue(name.indexOf(".") == -1);
    Assert.assertTrue(name.indexOf("::") != -1 || packageNames.length > 0);

    Any result = null;

    // Get the package-name extracted
    if (name.indexOf("::") != -1) {
      String className = "";
      while (name.indexOf("::") != -1) {
        className = className + name.substring(0, name.indexOf("::")).trim() + 
                    ".";
        name = name.substring(name.indexOf("::") + 2);
      }
      className = className + name.trim();
      name = className;
      result = findClass(className);
    }

    // Browse through the standard-packages to find the the class in
    // on of them
    for (int i = 0; i < packageNames.length && result == null; i++) {
      String className;
      if (packageNames[i] == null) {
        className = name;
      } else {
        className = packageNames[i] + "." + name;
      }
      result = findClass(className);
    }

    // If we have not found the class at all we throw an exception
    if (result == null) {
      StringBuffer sb = new StringBuffer();
      sb.append(
            "MetaReflectionFacade could not find class " + name + 
            " in packages ");
      for (int i = 0; i < packageNames.length; i++) {
        if (i != 0) {
          sb.append(", ");
        }
        sb.append(packageNames[i]);
      }
      throw new OclTypeException(sb.toString());
    }

    return result;
  }

  private Any findClass(String className) {
    try {
      Class c = Class.forName(className, false, getClass().getClassLoader());
      Any result = (Any)reflectionAdapter.getTypeForClass(c);
      if (result == null) {
        result = getClassAny(c);
      }
      return result;
    } catch (ClassNotFoundException cnf) {

      // we have not found the class in this package
      return null;
    }

  }
}