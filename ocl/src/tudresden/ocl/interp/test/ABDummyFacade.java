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

package tudresden.ocl.interp.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import tudresden.ocl.check.OclTypeException;
import tudresden.ocl.check.types.Any;
import tudresden.ocl.check.types.Basic;
import tudresden.ocl.check.types.Collection;
import tudresden.ocl.check.types.DefaultTypeFactory;
import tudresden.ocl.check.types.ModelFacade;
import tudresden.ocl.check.types.Type;
import tudresden.ocl.check.types.TypeFactory;

import tudresden.ocl.interp.types.Instance;
import tudresden.ocl.interp.types.InstanceFacade;


/**
 * Stucture of the facade:<br><br>
 * 
 * class A
 * <ul>
 * <li>r1:B
 * <li>r2:Collection(B)
 * <li>d:Ineger
 * </ul><br>
 * 
 * class B
 * <ul>
 * <li>r3:A
 * <li>c:Ineger
 * </ul><br> 
 * 
 * instance a1
 * <ul>
 * <li>r1=b1
 * <li>r2={b1,b2,b3}
 * <li>d=1
 * </ul><br> 
 * 
 * instance b1
 * <ul>
 * <li>r3=a1
 * <li>c=2
 * </ul><br> 
 * 
 * instance b2
 * <ul>
 * <li>r3=null
 * <li>c=3
 * </ul><br> 
 * 
 * instance b3
 * <ul>
 * <li>r3=null
 * <li>c=4
 * </ul><br> 
 * 
 */
public class ABDummyFacade implements ModelFacade, InstanceFacade {
  Map classifiers = new HashMap();
  TypeFactory tf = new DefaultTypeFactory(this);
  List aInstance;
  List bInstance;
  
  public ABDummyFacade() {
    DummyAny classA = new DummyAny();
    DummyAny classB = new DummyAny();
    Type coll = tf.getCollection(classB);

    classA.register("r1", classB);
    classA.register("r2", coll);
    classA.register("d", tf.getInteger());

    classB.register("r3", classA);
    classB.register("c", tf.getInteger());

    classifiers.put("A", classA);
    classifiers.put("B", classB);

    DummyInstance a1 = new DummyInstance(classA);
    DummyInstance b1 = new DummyInstance(classB);
    DummyInstance b2 = new DummyInstance(classB);
    DummyInstance b3 = new DummyInstance(classB);
    List collInstance = new ArrayList();
    collInstance.add(b1);
    collInstance.add(b2);
    collInstance.add(b3);

    a1.register("r1", b1);
    a1.register("r2", new DummyInstance(coll, collInstance));
    a1.register("d", new DummyInstance(tf.getInteger(), new Integer(1)));

    b1.register("c", new DummyInstance(tf.getInteger(), new Integer(2)));
    b1.register("r3", a1);
    b2.register("c", new DummyInstance(tf.getInteger(), new Integer(3)));
    b3.register("c", new DummyInstance(tf.getInteger(), new Integer(4)));

    aInstance = Arrays.asList(new Object[]{a1});
    bInstance = Arrays.asList(new Object[]{b1,b2,b3});
  }

  /**
   * @see ModelFacade#getClassifier(String)
   */
  public Any getClassifier(String name) throws OclTypeException {
    return (Any)classifiers.get(name);
  }

  /**
   * @see InstanceFacade#getRelevantOf(String)
   */
  public Iterator getRelevantOf(String typeName) throws OclTypeException {
    if (typeName.equals("A")) {
      return aInstance.iterator();
    } else if (typeName.equals("B")){
      return bInstance.iterator();
    } else {
      System.out.println(
            "Warning: You got an empty iterator of the DummyFacade");
      return (new ArrayList(0)).iterator();
    }
  }
}

class DummyInstance implements Instance {
  private Type type;
  private Object object;
  Map qualis = new HashMap();

  public DummyInstance(Type type) {
    this.type = type;
  }

  public DummyInstance(Type type, Object object) {
    this(type);
    this.object = object;
  }

  void register(String name, Instance t) {
    qualis.put(name, t);
  }

  /*
  * @see Instance#getType()
  */
  public Type getType() {
    return type;
  }

  /*
  * @see Instance#navigateParameterized(String, Type[])
  */
  public Instance navigateParameterized(String name, Object[] params)
                                 throws OclTypeException {
    return null;
  }

  /*
  * @see Instance#navigateQualified(String, Type[])
  */
  public Instance navigateQualified(String name, Object[] qualifiers)
                             throws OclTypeException {
    return (Instance)qualis.get(name);
  }

  /*
  * @see Instance#getBasicValue()
  */
  public Object getBasicValue() {
    if (!(getType() instanceof Basic)) {
      throw new RuntimeException();
    }
    return object;
  }

  /*
  * @see Instance#getIterator()
  */
  public java.util.Collection getCollection() {
    if (!(getType() instanceof Collection)) {
      throw new RuntimeException();
    }
    return (java.util.Collection)object;
  }

  /*
  * @see Instance#getValue()
  */
  public Object reconvert() {
    return object;
  }
}

class DummyAny implements Any {
  Map qualis = new HashMap();

  void register(String name, Type t) {
    qualis.put(name, t);
  }

  /*
  * @see Type#conformsTo(Type)
  */
  public boolean conformsTo(Type t) {
    return false;
  }

  /*
  * @see Type#hasState(String)
  */
  public boolean hasState(String name) {
    return false;
  }

  /*
  * @see Type#navigateParameterized(String, Type[])
  */
  public Type navigateParameterized(String name, Type[] params)
                             throws OclTypeException {
    return null;
  }

  /*
  * @see Type#navigateQualified(String, Type[])
  */
  public Type navigateQualified(String name, Type[] qualifiers)
                         throws OclTypeException {
    return (Type)qualis.get(name);
  }
}