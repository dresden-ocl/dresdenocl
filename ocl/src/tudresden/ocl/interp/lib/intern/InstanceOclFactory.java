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

package tudresden.ocl.interp.lib.intern;

import tudresden.ocl.check.types.Basic;
import tudresden.ocl.check.types.Collection;
import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.core.intern.Assert;
import tudresden.ocl.interp.types.Instance;

import tudresden.ocl.lib.DefaultOclFactory;
import tudresden.ocl.lib.OclAnyImpl;
import tudresden.ocl.lib.OclRoot;
import tudresden.ocl.lib.OclType;


/**
 * This OclFactory overwrites the DefaultOclFactory and gives our own
 * InstanceOclAnyImpl for custom classes back (instead of the
 * OclAnyImpl).<br>
 * InstanceOclAnyImpl is used to get information out of the
 * running system via reflection.
 */
public class InstanceOclFactory extends DefaultOclFactory {
  ModelFacade modelFacade;

  public InstanceOclFactory(ModelFacade modelFacade) {
    this.modelFacade = modelFacade;
  }

  /**
   * Get an Ocl-Representation for any objects
   */
  public OclRoot getOclRepresentationFor(Object o) {
    Assert.assertTrue(o != null);

    if (o instanceof Instance) {
      Instance instance = (Instance)o;
      if (instance.getType() instanceof Basic) {
        return super.getOclRepresentationFor(instance.getBasicValue());
      } else if (instance.getType() instanceof Collection) {
        return super.getOclRepresentationFor(instance.getCollection());
      } else {
        return new InstanceOclAnyImpl((Instance)o, modelFacade);
      }
    } else {

      // o is a base Type
      OclRoot result = super.getOclRepresentationFor(o);
      Assert.assertTrue(!(result instanceof OclAnyImpl));
      return result;
    }
  }

  /**
   * Get the OclType from a class-name and the modelFacade that knows about
   * the class-structure.
   * @see InstanceOclType#getOclTypeFor(String)
   */
  public static OclType getOclTypeFor(String name, ModelFacade modelFacade) {
    return InstanceOclType.getOclTypeFor(name, modelFacade);
  }
}