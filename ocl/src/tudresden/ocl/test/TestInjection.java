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

package tudresden.ocl.test;

import java.util.*;
import tudresden.ocl.test.royloy.*;

public class TestInjection
{
  /**
     All objects.
  */
  private ArrayList ao=new ArrayList();
  
  private void doTest() 
  {
    tudresden.ocl.lib.Ocl.TOLERATE_NONEXISTENT_FIELDS=false;
    tudresden.ocl.lib.Ocl.setNameAdapter(new tudresden.ocl.lib.SimpleNameAdapter());
    
    Person p1, p2, p3;
    Company c1, c2;
    
    ao.add(p1=new Person("Person1"));
    ao.add(p2=new Person("Person2"));
    ao.add(p3=new Person("Person3"));
    ao.add(c1=new Company("Company1", p3));
    ao.add(c2=new Company("Company2", p3));
    c1.employ(p1);
    c2.employ(p2);
    c2.employ(p3);

    assertAll();
  }
  
  private void assertAll()
  {
    for(Iterator i=ao.iterator(); i.hasNext(); )
      ((RLObject)i.next()).assert();
  }
  
  static public void main(String[] args)
  {
    (new TestInjection()).doTest();
  }

}
