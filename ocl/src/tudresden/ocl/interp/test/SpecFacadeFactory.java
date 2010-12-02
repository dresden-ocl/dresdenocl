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

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import tudresden.ocl.check.types.ModelFacade;

import tudresden.ocl.interp.types.InstanceFacade;
import tudresden.ocl.interp.types.reflect.MetaReflectionFacade;
import tudresden.ocl.interp.types.reflect.ReflectionInstanceFacade;


public class SpecFacadeFactory {
  private static ModelFacade modelFacade = new MetaReflectionFacade(
                                                 new String[] {
    "tudresden.ocl.interp.test", "java.lang", null
  });
  private static InstanceFacade instanceFacade = getFacade(modelFacade);
  private static ModelFacade noPackageFacade = new MetaReflectionFacade();
  private static InstanceFacade noInstanceFacade = getFacade(noPackageFacade);
  private static DateFormat df = new SimpleDateFormat();

  public static ModelFacade getNoPackageFacade() {
    return noPackageFacade;
  }

  public static InstanceFacade getNoInstanceFacade() {
    return noInstanceFacade;
  }

  public static ModelFacade getModelFacade() {
    return modelFacade;
  }

  public static InstanceFacade getInstanceFacade() {
    return instanceFacade;
  }

  static Calendar conDate(int day, int month, int year) {
    Calendar result = Calendar.getInstance();
    result.set(Calendar.YEAR, year);
    result.set(Calendar.DAY_OF_MONTH, day);
    result.set(Calendar.MONTH, month);
    return result;
  }

  static public InstanceFacade getPreformanceFacade(ModelFacade modelFacade, 
                                                    int count) {
    Person[] persons = new Person[count];
    for (int i = 0; i < persons.length; i++) {
      persons[i] = new Person("FirstName" + i, "LastName" + i, 
                              conDate(1, 1, 1971), false);
    }

    List l = Arrays.asList(persons);
    return new ReflectionInstanceFacade(modelFacade, l);
  }

  static public InstanceFacade getGUIFacade(ModelFacade modelFacade) {
    Person p1 = new Person("First1", "smith", conDate(1, 1, 1971), false);
    Person p2 = new Person("First2", "johnsen", conDate(2, 2, 1972), true);
    Person p3 = new Person("First3", "jones", conDate(3, 3, 1973), false);  	

    Company c1 = new Company("henkel");
    Company c2 = new Company("siemens");
    
    c1.employ(p1);
    c1.employ(p2);
    c2.employ(p2);
    c2.employ(p3);
    
    List l = Arrays.asList(new Object[] {c1,c2,p1,p2,p3});
    return new ReflectionInstanceFacade(modelFacade, l);
  }
  
  static private InstanceFacade getFacade(ModelFacade modelFacade) {
    Person p1 = new Person("FirstName1", "LastName1", conDate(1, 1, 1971), 
                           false);
    Person p2 = new Person("FirstName2", "LastName2", conDate(2, 2, 1972), true);
    Person p3 = new Person("FirstName3", "LastName3", conDate(3, 3, 1973), 
                           false);
    Manager p4 = new Manager("FirstName4", "LastName4", conDate(4, 4, 1955), 
                             false);
    Person p5 = new Person("FirstName5", "LastName5", conDate(5, 5, 1975), 
                           false);
    Bank b1 = new Bank("German Bank");

    p1.marry(p2);

    Company c1 = new Company("Henkel");
    Company c2 = new Company("Siemens");

    c1.employ(p1);
    c1.employ(p2);
    c1.employ(p3);
    c1.employ(p4);
    c1.wrongManagerSet.add(p1);
    c1.wrongManagerSet.add(p2);

    c2.employ(p3);
    c2.employ(p4);
    c2.employ(p5);

    p4.manage(c1);
    p4.manage(c2);

    b1.customers.put(new Integer(1001), p1);
    b1.customers.put(new Integer(1002), p2);
    b1.customers.put(new Integer(1003), p3);
    b1.subnames = new String[] {"German Bank Longer 1", "German Bank Longer 2"};

    List l = Arrays.asList(new Object[] {p1, p2, p3, p4, p5, c1, c2, b1});

    return new ReflectionInstanceFacade(modelFacade, l);
  }
}