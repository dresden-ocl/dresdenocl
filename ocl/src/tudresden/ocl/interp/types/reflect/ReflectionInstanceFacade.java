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

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.check.types.Type;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.types.Instance;
import tudresden.ocl.interp.types.InstanceFacade;
import tudresden.ocl.interp.types.reflect.intern.*;


/**
 * Implements the InstanceFacade, an easy iterface to get a number of wrapped
 * user-objects of a specific type;
 * 
 * @see InstanceFacade
 */
public class ReflectionInstanceFacade implements InstanceFacade {
  protected ModelFacade modelFacade;
  protected HashSet instanceCollection = new HashSet();

  /**
   * Get a new InstanceFacade that is used to evaluate ExpTrees.
   * 
   * @param modelFacade is the model in that your instances are working.
   *        Normaly this should be an MetaReflectionFacade
   * @param o is an objects, that is not yet wrapped into instances. This is
   *        the elements that is going to be checked if the type fits the type
   *        requested to the constraint. If not the constraint always
   *        evaluates to true
   * @throws NullPointerException if a parameter is null
   */
  public ReflectionInstanceFacade(ModelFacade modelFacade, Object o)
                           throws NullPointerException {
    Assert.notNull(modelFacade, "ReflectionInstanceFacade", "modelFacade");
    Assert.notNull(o, "ReflectionInstanceFacade", "o");
    this.modelFacade = modelFacade;
    instanceCollection.add(getInstance(o));
  }

  /**
   * Get a new InstanceFacade that is used to evaluate ExpTrees.
   * 
   * @param modelFacade is the model in that your instances are working.
   *        Normaly this should be an MetaReflectionFacade
   * @param coll is a collection of objects, that is not yet wrapped into
   *        instances. This are the elements that are going to be checked if
   *        they are of the type requested in the OCL-Constraint. If the
   *        requested type doen not match the types of objects in the
   *        collection the constraint evaluates to true.
   * @throws NullPointerException if a parameter is null
   */
  public ReflectionInstanceFacade(ModelFacade modelFacade, Collection coll)
                           throws NullPointerException {
    Assert.notNull(modelFacade, "ReflectionInstanceFacade", "modelFacade");
    Assert.notNull(coll, "ReflectionInstanceFacade", "coll");
    this.modelFacade = modelFacade;
    for (Iterator i = coll.iterator(); i.hasNext();) {
      instanceCollection.add(new ReflectionInstance(modelFacade, i.next()));
    }
  }

  /**
   * Used by the Interpreter only. This method gives the Interpreter an
   * Iterator on the instances contained in this facade.
   * 
   * @param typeName the requested type, or the context of the constraint
   * @return an iterator on instances
   * @throws NullPointerException if typeName == null
   * @throws OclTypeException if typeName does not exist as a type
   * @see InstanceFacade#getRelevantOf(String)
   */
  public Iterator getRelevantOf(String typeName) throws NullPointerException, 
                                                        OclTypeException {
    Assert.notNull(modelFacade, "ReflectionInstanceFacade", "modelFacade");
    return getRelevantOf(modelFacade.getClassifier(typeName));
  }

  /**
   * Get all the Instances confirming to the Type type
   */
  private Iterator getRelevantOf(Type type) {
    Collection coll = new HashSet();

    for (Iterator i = instanceCollection.iterator(); i.hasNext();) {
      Instance instance = (Instance)i.next();
      if (instance.getType() == type) {
        coll.add(instance);
      }
    }

    return coll.iterator();
  }

  /**
   * Get an instance from an object.
   * 
   * @param object the object that is to be put in the instance
   * @return the instance that hides the object
   */
  protected Instance getInstance(Object object) {
    return new ReflectionInstance(modelFacade, object);
  }
}