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

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class Person {
  public String name;
  public boolean isMarried;
  public boolean isUnemployed;
  public Date birthDate;
  public int age;
  public String firstName;
  public String lastName;

  // male = false, female = true
  public boolean sex;

  /**
   * @element-type Company
   */
  public Set employer;
  public Person husband;
  public Person wife;

  public int income(Date date) {
    return 0;
  }

  public Person() {
    this("Heinz", "Müller", SpecFacadeFactory.conDate(1, 1, 1977), false);
  }

  Person(String firstName, String lastName, Calendar birth, boolean sex) {
    this.isMarried = false;
    this.isUnemployed = true;
    this.employer = new HashSet();

    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.sex = sex;

    Calendar now = SpecFacadeFactory.conDate(1, 1, 2002);
    this.age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
    birth.set(Calendar.YEAR, 2002);

    if (birth.after(now)) {
      this.age = this.age - 1;
    }

  }

  void marry(Person p) {
    if (sex == p.sex) {
      throw new RuntimeException();
    }

    if (husband != null || wife != null || p.wife != null || 
        p.husband != null) {
      throw new RuntimeException();
    }

    if (isMarried || p.isMarried) {
      throw new RuntimeException();
    }

    if (sex) {// this is female
      husband = p;
      p.wife = this;
    }// this is male 
    else {
      wife = p;
      p.husband = this;
    }

    isMarried = true;
    p.isMarried = true;

  }
}