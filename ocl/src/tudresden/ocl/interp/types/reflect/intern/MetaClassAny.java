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

package tudresden.ocl.interp.types.reflect.intern;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.HashSet;
import java.util.Set;

import tudresden.ocl.check.types.ClassAny;
import tudresden.ocl.check.types.ReflectionFacade;

import tudresden.ocl.interp.core.intern.ReflectionUtil;
import tudresden.ocl.interp.types.MetaAny;


/**
 * This class implements the MetaAny interface for reflection. Therefor it
 * gives model-information for a class, that are used for querys of the 
 * interpreter.
 */
public class MetaClassAny extends ClassAny implements MetaAny {
  ReflectionFacade reflectionFacade;

  public MetaClassAny(Class clazz, ReflectionFacade reflectionFacade) {
    super(clazz, reflectionFacade);
    this.reflectionFacade = reflectionFacade;
  }

  public String getName() {
    return c.getName();
  }

  public String[] getFields() {
    Field[] fields = c.getFields();
    String[] names = new String[fields.length];
    for (int i = 0; i < fields.length; i++) {
      names[i] = fields[i].getName();
    }
    return names;
  }

  public String[] getOperations() {
    Method[] methods = c.getMethods();
    String[] names = new String[methods.length];
    for (int i = 0; i < methods.length; i++) {
      names[i] = methods[i].getName();
    }
    return names;
  }

  public MetaAny[] getSupertypes() {
    Set types = getSupertypes(c);
    types.remove(null);
    return (MetaAny[])types.toArray(new MetaAny[types.size()]);
  }

  public MetaAny[] getAllSupertypes() {
    Set types = new HashSet();
    Class clazz = c;

    while (clazz != null) {
      types.addAll(getSupertypes(clazz));
      clazz = clazz.getSuperclass();
    }
    types.remove(null);
    return (MetaAny[])types.toArray(new MetaAny[types.size()]);
  }

  private Set getSupertypes(Class clazz) {
    String tmpName;
    Set types = new HashSet();
    Class[] interfaces;

    if (clazz.getSuperclass() != null) {
      tmpName = clazz.getSuperclass().getName();
      tmpName = ReflectionUtil.toColons(tmpName);
      types.add(reflectionFacade.getClassifier(tmpName));
    }
    interfaces = clazz.getInterfaces();
    for (int i = 0; i < interfaces.length; i++) {
      tmpName = interfaces[i].getName();
      tmpName = ReflectionUtil.toColons(tmpName);
      types.add(reflectionFacade.getClassifier(tmpName));
    }
    return types;
  }
}